package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import java.util.Arrays;
import android.util.Log;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttException;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Iterator;
import android.os.PowerManager;
import android.os.Bundle;
import java.util.HashMap;
import android.os.PowerManager$WakeLock;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;

class MqttConnection implements MqttCallbackExtended
{
    private static final String NOT_CONNECTED = "not connected";
    private static final String TAG = "MqttConnection";
    private AlarmPingSender alarmPingSender;
    private DisconnectedBufferOptions bufferOpts;
    private boolean cleanSession;
    private String clientHandle;
    private String clientId;
    private MqttConnectOptions connectOptions;
    private volatile boolean disconnected;
    private volatile boolean isConnecting;
    private MqttAsyncClient myClient;
    private MqttClientPersistence persistence;
    private String reconnectActivityToken;
    private Map<IMqttDeliveryToken, String> savedActivityTokens;
    private Map<IMqttDeliveryToken, String> savedInvocationContexts;
    private Map<IMqttDeliveryToken, MqttMessage> savedSentMessages;
    private Map<IMqttDeliveryToken, String> savedTopics;
    private String serverURI;
    private MqttService service;
    private String wakeLockTag;
    private PowerManager$WakeLock wakelock;
    
    MqttConnection(final MqttService service, final String serverURI, final String clientId, final MqttClientPersistence persistence, final String clientHandle) {
        this.persistence = null;
        this.reconnectActivityToken = null;
        this.myClient = null;
        this.alarmPingSender = null;
        this.service = null;
        this.disconnected = true;
        this.cleanSession = true;
        this.isConnecting = false;
        this.savedTopics = (Map<IMqttDeliveryToken, String>)new HashMap();
        this.savedSentMessages = (Map<IMqttDeliveryToken, MqttMessage>)new HashMap();
        this.savedActivityTokens = (Map<IMqttDeliveryToken, String>)new HashMap();
        this.savedInvocationContexts = (Map<IMqttDeliveryToken, String>)new HashMap();
        this.wakelock = null;
        this.wakeLockTag = null;
        this.bufferOpts = null;
        this.serverURI = serverURI;
        this.service = service;
        this.clientId = clientId;
        this.persistence = persistence;
        this.clientHandle = clientHandle;
        final StringBuilder sb = new StringBuilder(this.getClass().getCanonicalName());
        sb.append(" ");
        sb.append(clientId);
        sb.append(" ");
        sb.append("on host ");
        sb.append(serverURI);
        this.wakeLockTag = sb.toString();
    }
    
    private void acquireWakeLock() {
        if (this.wakelock == null) {
            this.wakelock = ((PowerManager)this.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag);
        }
        this.wakelock.acquire();
    }
    
    private void deliverBacklog() {
        final Iterator<MessageStore.StoredMessage> allArrivedMessages = this.service.messageStore.getAllArrivedMessages(this.clientHandle);
        while (allArrivedMessages.hasNext()) {
            final MessageStore.StoredMessage storedMessage = (MessageStore.StoredMessage)allArrivedMessages.next();
            final Bundle messageToBundle = this.messageToBundle(storedMessage.getMessageId(), storedMessage.getTopic(), storedMessage.getMessage());
            messageToBundle.putString("MqttService.callbackAction", "messageArrived");
            this.service.callbackToActivity(this.clientHandle, Status.OK, messageToBundle);
        }
    }
    
    private void doAfterConnectFail(final Bundle bundle) {
        this.acquireWakeLock();
        this.disconnected = true;
        this.setConnectingState(false);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        this.releaseWakeLock();
    }
    
    private void doAfterConnectSuccess(final Bundle bundle) {
        this.acquireWakeLock();
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
        this.deliverBacklog();
        this.setConnectingState(false);
        this.disconnected = false;
        this.releaseWakeLock();
    }
    
    private void handleException(final Bundle bundle, final Exception ex) {
        bundle.putString("MqttService.errorMessage", ex.getLocalizedMessage());
        bundle.putSerializable("MqttService.exception", (Serializable)ex);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }
    
    private Bundle messageToBundle(final String s, final String s2, final MqttMessage mqttMessage) {
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.messageId", s);
        bundle.putString("MqttService.destinationName", s2);
        bundle.putParcelable("MqttService.PARCEL", (Parcelable)new ParcelableMqttMessage(mqttMessage));
        return bundle;
    }
    
    private void releaseWakeLock() {
        final PowerManager$WakeLock wakelock = this.wakelock;
        if (wakelock != null && wakelock.isHeld()) {
            this.wakelock.release();
        }
    }
    
    private void setConnectingState(final boolean isConnecting) {
        synchronized (this) {
            this.isConnecting = isConnecting;
        }
    }
    
    private void storeSendDetails(final String s, final MqttMessage mqttMessage, final IMqttDeliveryToken mqttDeliveryToken, final String s2, final String s3) {
        this.savedTopics.put((Object)mqttDeliveryToken, (Object)s);
        this.savedSentMessages.put((Object)mqttDeliveryToken, (Object)mqttMessage);
        this.savedActivityTokens.put((Object)mqttDeliveryToken, (Object)s3);
        this.savedInvocationContexts.put((Object)mqttDeliveryToken, (Object)s2);
    }
    
    void close() {
        this.service.traceDebug("MqttConnection", "close()");
        try {
            if (this.myClient != null) {
                this.myClient.close();
            }
        }
        catch (final MqttException ex) {
            this.handleException(new Bundle(), ex);
        }
    }
    
    public void connect(final MqttConnectOptions connectOptions, final String s, final String reconnectActivityToken) {
        this.connectOptions = connectOptions;
        this.reconnectActivityToken = reconnectActivityToken;
        if (connectOptions != null) {
            this.cleanSession = connectOptions.isCleanSession();
        }
        if (this.connectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("Connecting {");
        sb.append(this.serverURI);
        sb.append("} as {");
        sb.append(this.clientId);
        sb.append("}");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.activityToken", reconnectActivityToken);
        bundle.putString("MqttService.invocationContext", s);
        bundle.putString("MqttService.callbackAction", "connect");
        try {
            if (this.persistence == null) {
                File file;
                if ((file = this.service.getExternalFilesDir("MqttConnection")) == null && (file = this.service.getDir("MqttConnection", 0)) == null) {
                    bundle.putString("MqttService.errorMessage", "Error! No external and internal storage available");
                    bundle.putSerializable("MqttService.exception", (Serializable)new MqttPersistenceException());
                    this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
                    return;
                }
                this.persistence = new MqttDefaultFilePersistence(file.getAbsolutePath());
            }
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(this, bundle, bundle) {
                final MqttConnection this$0;
                final Bundle val$resultBundle;
                
                @Override
                public void onFailure(final IMqttToken mqttToken, final Throwable t) {
                    this.val$resultBundle.putString("MqttService.errorMessage", t.getLocalizedMessage());
                    this.val$resultBundle.putSerializable("MqttService.exception", (Serializable)t);
                    final MqttService access$200 = this.this$0.service;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("connect fail, call connect to reconnect.reason:");
                    sb.append(t.getMessage());
                    access$200.traceError("MqttConnection", sb.toString());
                    this.this$0.doAfterConnectFail(this.val$resultBundle);
                }
                
                @Override
                public void onSuccess(final IMqttToken mqttToken) {
                    this.this$0.doAfterConnectSuccess(this.val$resultBundle);
                    this.this$0.service.traceDebug("MqttConnection", "connect success!");
                }
            };
            if (this.myClient != null) {
                if (this.isConnecting) {
                    this.service.traceDebug("MqttConnection", "myClient != null and the client is connecting. Connect return directly.");
                    final MqttService service2 = this.service;
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Connect return:isConnecting:");
                    sb2.append(this.isConnecting);
                    sb2.append(".disconnected:");
                    sb2.append(this.disconnected);
                    service2.traceDebug("MqttConnection", sb2.toString());
                }
                else if (!this.disconnected) {
                    this.service.traceDebug("MqttConnection", "myClient != null and the client is connected and notify!");
                    this.doAfterConnectSuccess(bundle);
                }
                else {
                    this.service.traceDebug("MqttConnection", "myClient != null and the client is not connected");
                    this.service.traceDebug("MqttConnection", "Do Real connect!");
                    this.setConnectingState(true);
                    this.myClient.connect(this.connectOptions, s, mqttConnectionListener);
                }
            }
            else {
                final AlarmPingSender alarmPingSender = new AlarmPingSender(this.service);
                this.alarmPingSender = alarmPingSender;
                (this.myClient = new MqttAsyncClient(this.serverURI, this.clientId, this.persistence, alarmPingSender)).setCallback(this);
                this.service.traceDebug("MqttConnection", "Do Real connect!");
                this.setConnectingState(true);
                this.myClient.connect(this.connectOptions, s, mqttConnectionListener);
            }
        }
        catch (final Exception ex) {
            final MqttService service3 = this.service;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("Exception occurred attempting to connect: ");
            sb3.append(ex.getMessage());
            service3.traceError("MqttConnection", sb3.toString());
            this.setConnectingState(false);
            this.handleException(bundle, ex);
        }
    }
    
    @Override
    public void connectComplete(final boolean b, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "connectExtended");
        bundle.putBoolean("MqttService.reconnect", b);
        bundle.putString("MqttService.serverURI", s);
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
    }
    
    @Override
    public void connectionLost(final Throwable t) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("connectionLost(");
        sb.append(t.getMessage());
        sb.append(")");
        service.traceDebug("MqttConnection", sb.toString());
        this.disconnected = true;
        try {
            if (!this.connectOptions.isAutomaticReconnect()) {
                this.myClient.disconnect(null, new IMqttActionListener(this) {
                    final MqttConnection this$0;
                    
                    @Override
                    public void onFailure(final IMqttToken mqttToken, final Throwable t) {
                    }
                    
                    @Override
                    public void onSuccess(final IMqttToken mqttToken) {
                    }
                });
            }
            else {
                this.alarmPingSender.schedule(100L);
            }
        }
        catch (final Exception ex) {}
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "onConnectionLost");
        if (t != null) {
            bundle.putString("MqttService.errorMessage", t.getMessage());
            if (t instanceof MqttException) {
                bundle.putSerializable("MqttService.exception", (Serializable)t);
            }
            bundle.putString("MqttService.exceptionStack", Log.getStackTraceString(t));
        }
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
        this.releaseWakeLock();
    }
    
    public void deleteBufferedMessage(final int n) {
        this.myClient.deleteBufferedMessage(n);
    }
    
    @Override
    public void deliveryComplete(final IMqttDeliveryToken mqttDeliveryToken) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("deliveryComplete(");
        sb.append((Object)mqttDeliveryToken);
        sb.append(")");
        service.traceDebug("MqttConnection", sb.toString());
        final MqttMessage mqttMessage = (MqttMessage)this.savedSentMessages.remove((Object)mqttDeliveryToken);
        if (mqttMessage != null) {
            final String s = (String)this.savedTopics.remove((Object)mqttDeliveryToken);
            final String s2 = (String)this.savedActivityTokens.remove((Object)mqttDeliveryToken);
            final String s3 = (String)this.savedInvocationContexts.remove((Object)mqttDeliveryToken);
            final Bundle messageToBundle = this.messageToBundle(null, s, mqttMessage);
            if (s2 != null) {
                messageToBundle.putString("MqttService.callbackAction", "send");
                messageToBundle.putString("MqttService.activityToken", s2);
                messageToBundle.putString("MqttService.invocationContext", s3);
                this.service.callbackToActivity(this.clientHandle, Status.OK, messageToBundle);
            }
            messageToBundle.putString("MqttService.callbackAction", "messageDelivered");
            this.service.callbackToActivity(this.clientHandle, Status.OK, messageToBundle);
        }
    }
    
    void disconnect(final long n, final String s, final String s2) {
        this.service.traceDebug("MqttConnection", "disconnect()");
        this.disconnected = true;
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.activityToken", s2);
        bundle.putString("MqttService.invocationContext", s);
        bundle.putString("MqttService.callbackAction", "disconnect");
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.disconnect(n, s, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("disconnect", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
        final MqttConnectOptions connectOptions = this.connectOptions;
        if (connectOptions != null && connectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        this.releaseWakeLock();
    }
    
    void disconnect(final String s, final String s2) {
        this.service.traceDebug("MqttConnection", "disconnect()");
        this.disconnected = true;
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.activityToken", s2);
        bundle.putString("MqttService.invocationContext", s);
        bundle.putString("MqttService.callbackAction", "disconnect");
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.disconnect(s, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("disconnect", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
        final MqttConnectOptions connectOptions = this.connectOptions;
        if (connectOptions != null && connectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        this.releaseWakeLock();
    }
    
    public MqttMessage getBufferedMessage(final int n) {
        return this.myClient.getBufferedMessage(n);
    }
    
    public int getBufferedMessageCount() {
        return this.myClient.getBufferedMessageCount();
    }
    
    public String getClientHandle() {
        return this.clientHandle;
    }
    
    public String getClientId() {
        return this.clientId;
    }
    
    public MqttConnectOptions getConnectOptions() {
        return this.connectOptions;
    }
    
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.myClient.getPendingDeliveryTokens();
    }
    
    public String getServerURI() {
        return this.serverURI;
    }
    
    public boolean isConnected() {
        final MqttAsyncClient myClient = this.myClient;
        return myClient != null && myClient.isConnected();
    }
    
    @Override
    public void messageArrived(final String s, final MqttMessage mqttMessage) throws Exception {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("messageArrived(");
        sb.append(s);
        sb.append(",{");
        sb.append(mqttMessage.toString());
        sb.append("})");
        service.traceDebug("MqttConnection", sb.toString());
        final String storeArrived = this.service.messageStore.storeArrived(this.clientHandle, s, mqttMessage);
        final Bundle messageToBundle = this.messageToBundle(storeArrived, s, mqttMessage);
        messageToBundle.putString("MqttService.callbackAction", "messageArrived");
        messageToBundle.putString("MqttService.messageId", storeArrived);
        this.service.callbackToActivity(this.clientHandle, Status.OK, messageToBundle);
    }
    
    void offline() {
        if (!this.disconnected && !this.cleanSession) {
            this.connectionLost((Throwable)new Exception("Android offline"));
        }
    }
    
    public IMqttDeliveryToken publish(final String s, final MqttMessage mqttMessage, final String s2, final String s3) {
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "send");
        bundle.putString("MqttService.activityToken", s3);
        bundle.putString("MqttService.invocationContext", s2);
        final MqttAsyncClient myClient = this.myClient;
        IMqttDeliveryToken publish = null;
        final IMqttDeliveryToken mqttDeliveryToken = null;
        final IMqttDeliveryToken mqttDeliveryToken2 = null;
        IMqttDeliveryToken mqttDeliveryToken4;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            IMqttDeliveryToken publish2 = mqttDeliveryToken2;
            try {
                final IMqttDeliveryToken mqttDeliveryToken3 = publish2 = this.myClient.publish(s, mqttMessage, s2, mqttConnectionListener);
                this.storeSendDetails(s, mqttMessage, mqttDeliveryToken3, s2, s3);
                mqttDeliveryToken4 = mqttDeliveryToken3;
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
                mqttDeliveryToken4 = publish2;
            }
        }
        else {
            if (this.myClient != null) {
                final DisconnectedBufferOptions bufferOpts = this.bufferOpts;
                if (bufferOpts != null && bufferOpts.isBufferEnabled()) {
                    final MqttConnectionListener mqttConnectionListener2 = new MqttConnectionListener(bundle);
                    try {
                        final IMqttDeliveryToken mqttDeliveryToken5 = publish = this.myClient.publish(s, mqttMessage, s2, mqttConnectionListener2);
                        this.storeSendDetails(s, mqttMessage, mqttDeliveryToken5, s2, s3);
                        mqttDeliveryToken4 = mqttDeliveryToken5;
                    }
                    catch (final Exception ex2) {
                        this.handleException(bundle, ex2);
                        mqttDeliveryToken4 = publish;
                    }
                    return mqttDeliveryToken4;
                }
            }
            Log.i("MqttConnection", "Client is not connected, so not sending message");
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("send", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
            mqttDeliveryToken4 = mqttDeliveryToken;
        }
        return mqttDeliveryToken4;
    }
    
    public IMqttDeliveryToken publish(final String s, byte[] publish, final int qos, final boolean retained, final String s2, final String s3) {
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "send");
        bundle.putString("MqttService.activityToken", s3);
        bundle.putString("MqttService.invocationContext", s2);
        final MqttAsyncClient myClient = this.myClient;
        final IMqttDeliveryToken mqttDeliveryToken = null;
        final IMqttDeliveryToken mqttDeliveryToken2 = null;
        Object o;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            Exception ex2 = null;
            try {
                final MqttMessage mqttMessage = new MqttMessage(publish);
                mqttMessage.setQos(qos);
                mqttMessage.setRetained(retained);
                publish = (byte[])(Object)this.myClient.publish(s, publish, qos, retained, s2, mqttConnectionListener);
                try {
                    this.storeSendDetails(s, mqttMessage, (IMqttDeliveryToken)(Object)publish, s2, s3);
                    o = publish;
                }
                catch (final Exception ex) {
                    ex2 = ex;
                    o = publish;
                }
            }
            catch (final Exception ex2) {
                o = mqttDeliveryToken2;
            }
            this.handleException(bundle, ex2);
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("send", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
            o = mqttDeliveryToken;
        }
        return (IMqttDeliveryToken)o;
    }
    
    void reconnect() {
        synchronized (this) {
            if (this.myClient == null) {
                this.service.traceError("MqttConnection", "Reconnect myClient = null. Will not do reconnect");
                return;
            }
            if (this.isConnecting) {
                this.service.traceDebug("MqttConnection", "The client is connecting. Reconnect return directly.");
                return;
            }
            if (!this.service.isOnline()) {
                this.service.traceDebug("MqttConnection", "The network is not reachable. Will not do reconnect");
                return;
            }
            if (this.connectOptions.isAutomaticReconnect()) {
                Log.i("MqttConnection", "Requesting Automatic reconnect using New Java AC");
                final Bundle bundle = new Bundle();
                bundle.putString("MqttService.activityToken", this.reconnectActivityToken);
                bundle.putString("MqttService.invocationContext", (String)null);
                bundle.putString("MqttService.callbackAction", "connect");
                try {
                    this.myClient.reconnect();
                }
                catch (final MqttException ex) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Exception occurred attempting to reconnect: ");
                    sb.append(ex.getMessage());
                    Log.e("MqttConnection", sb.toString());
                    this.setConnectingState(false);
                    this.handleException(bundle, ex);
                }
            }
            else if (this.disconnected && !this.cleanSession) {
                this.service.traceDebug("MqttConnection", "Do Real Reconnect!");
                final Bundle bundle2 = new Bundle();
                bundle2.putString("MqttService.activityToken", this.reconnectActivityToken);
                bundle2.putString("MqttService.invocationContext", (String)null);
                bundle2.putString("MqttService.callbackAction", "connect");
                try {
                    this.myClient.connect(this.connectOptions, null, new MqttConnectionListener(this, bundle2, bundle2) {
                        final MqttConnection this$0;
                        final Bundle val$resultBundle;
                        
                        @Override
                        public void onFailure(final IMqttToken mqttToken, final Throwable t) {
                            this.val$resultBundle.putString("MqttService.errorMessage", t.getLocalizedMessage());
                            this.val$resultBundle.putSerializable("MqttService.exception", (Serializable)t);
                            this.this$0.service.callbackToActivity(this.this$0.clientHandle, Status.ERROR, this.val$resultBundle);
                            this.this$0.doAfterConnectFail(this.val$resultBundle);
                        }
                        
                        @Override
                        public void onSuccess(final IMqttToken mqttToken) {
                            this.this$0.service.traceDebug("MqttConnection", "Reconnect Success!");
                            this.this$0.service.traceDebug("MqttConnection", "DeliverBacklog when reconnect.");
                            this.this$0.doAfterConnectSuccess(this.val$resultBundle);
                        }
                    });
                    this.setConnectingState(true);
                }
                catch (final Exception ex2) {
                    final MqttService service = this.service;
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Cannot reconnect to remote server.");
                    sb2.append(ex2.getMessage());
                    service.traceError("MqttConnection", sb2.toString());
                    this.setConnectingState(false);
                    this.handleException(bundle2, new MqttException(6, ex2.getCause()));
                }
                catch (final MqttException ex3) {
                    final MqttService service2 = this.service;
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("Cannot reconnect to remote server.");
                    sb3.append(ex3.getMessage());
                    service2.traceError("MqttConnection", sb3.toString());
                    this.setConnectingState(false);
                    this.handleException(bundle2, ex3);
                }
            }
        }
    }
    
    public void setBufferOpts(final DisconnectedBufferOptions disconnectedBufferOptions) {
        this.bufferOpts = disconnectedBufferOptions;
        this.myClient.setBufferOpts(disconnectedBufferOptions);
    }
    
    public void setClientHandle(final String clientHandle) {
        this.clientHandle = clientHandle;
    }
    
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }
    
    public void setConnectOptions(final MqttConnectOptions connectOptions) {
        this.connectOptions = connectOptions;
    }
    
    public void setServerURI(final String serverURI) {
        this.serverURI = serverURI;
    }
    
    public void subscribe(final String s, final int n, final String s2, final String s3) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("subscribe({");
        sb.append(s);
        sb.append("},");
        sb.append(n);
        sb.append(",{");
        sb.append(s2);
        sb.append("}, {");
        sb.append(s3);
        sb.append("}");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "subscribe");
        bundle.putString("MqttService.activityToken", s3);
        bundle.putString("MqttService.invocationContext", s2);
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.subscribe(s, n, s2, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("subscribe", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
    }
    
    public void subscribe(final String[] array, final int[] array2, final String s, final String s2) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("subscribe({");
        sb.append(Arrays.toString((Object[])array));
        sb.append("},");
        sb.append(Arrays.toString(array2));
        sb.append(",{");
        sb.append(s);
        sb.append("}, {");
        sb.append(s2);
        sb.append("}");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "subscribe");
        bundle.putString("MqttService.activityToken", s2);
        bundle.putString("MqttService.invocationContext", s);
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.subscribe(array, array2, s, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("subscribe", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
    }
    
    public void subscribe(final String[] array, final int[] array2, final String s, final String s2, final IMqttMessageListener[] array3) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("subscribe({");
        sb.append(Arrays.toString((Object[])array));
        sb.append("},");
        sb.append(Arrays.toString(array2));
        sb.append(",{");
        sb.append(s);
        sb.append("}, {");
        sb.append(s2);
        sb.append("}");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "subscribe");
        bundle.putString("MqttService.activityToken", s2);
        bundle.putString("MqttService.invocationContext", s);
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            new MqttConnectionListener(bundle);
            try {
                this.myClient.subscribe(array, array2, array3);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("subscribe", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
    }
    
    void unsubscribe(final String s, final String s2, final String s3) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("unsubscribe({");
        sb.append(s);
        sb.append("},{");
        sb.append(s2);
        sb.append("}, {");
        sb.append(s3);
        sb.append("})");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "unsubscribe");
        bundle.putString("MqttService.activityToken", s3);
        bundle.putString("MqttService.invocationContext", s2);
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.unsubscribe(s, s2, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("subscribe", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
    }
    
    void unsubscribe(final String[] array, final String s, final String s2) {
        final MqttService service = this.service;
        final StringBuilder sb = new StringBuilder();
        sb.append("unsubscribe({");
        sb.append(Arrays.toString((Object[])array));
        sb.append("},{");
        sb.append(s);
        sb.append("}, {");
        sb.append(s2);
        sb.append("})");
        service.traceDebug("MqttConnection", sb.toString());
        final Bundle bundle = new Bundle();
        bundle.putString("MqttService.callbackAction", "unsubscribe");
        bundle.putString("MqttService.activityToken", s2);
        bundle.putString("MqttService.invocationContext", s);
        final MqttAsyncClient myClient = this.myClient;
        if (myClient != null && myClient.isConnected()) {
            final MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                this.myClient.unsubscribe(array, s, mqttConnectionListener);
            }
            catch (final Exception ex) {
                this.handleException(bundle, ex);
            }
        }
        else {
            bundle.putString("MqttService.errorMessage", "not connected");
            this.service.traceError("subscribe", "not connected");
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
    }
    
    private class MqttConnectionListener implements IMqttActionListener
    {
        private final Bundle resultBundle;
        final MqttConnection this$0;
        
        private MqttConnectionListener(final MqttConnection this$0, final Bundle resultBundle) {
            this.this$0 = this$0;
            this.resultBundle = resultBundle;
        }
        
        @Override
        public void onFailure(final IMqttToken mqttToken, final Throwable t) {
            this.resultBundle.putString("MqttService.errorMessage", t.getLocalizedMessage());
            this.resultBundle.putSerializable("MqttService.exception", (Serializable)t);
            this.this$0.service.callbackToActivity(this.this$0.clientHandle, Status.ERROR, this.resultBundle);
        }
        
        @Override
        public void onSuccess(final IMqttToken mqttToken) {
            this.this$0.service.callbackToActivity(this.this$0.clientHandle, Status.OK, this.resultBundle);
        }
    }
}
