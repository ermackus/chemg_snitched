package org.eclipse.paho.android.service;

import android.os.IBinder;
import android.content.ComponentName;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import android.content.ServiceConnection;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.IntentFilter;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import android.os.Bundle;
import java.util.concurrent.Executors;
import android.util.SparseArray;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import android.content.Context;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import java.util.concurrent.ExecutorService;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import android.content.BroadcastReceiver;

public class MqttAndroidClient extends BroadcastReceiver implements IMqttAsyncClient
{
    private static final int BIND_SERVICE_FLAG = 0;
    private static final String SERVICE_NAME = "org.eclipse.paho.android.service.MqttService";
    private static final ExecutorService pool;
    private volatile boolean bindedService;
    private MqttCallback callback;
    private String clientHandle;
    private final String clientId;
    private MqttConnectOptions connectOptions;
    private IMqttToken connectToken;
    private final Ack messageAck;
    private MqttService mqttService;
    private Context myContext;
    private MqttClientPersistence persistence;
    private volatile boolean receiverRegistered;
    private final String serverURI;
    private final MyServiceConnection serviceConnection;
    private final SparseArray<IMqttToken> tokenMap;
    private int tokenNumber;
    private MqttTraceHandler traceCallback;
    private boolean traceEnabled;
    
    static {
        pool = Executors.newCachedThreadPool();
    }
    
    public MqttAndroidClient(final Context context, final String s, final String s2) {
        this(context, s, s2, null, Ack.AUTO_ACK);
    }
    
    public MqttAndroidClient(final Context context, final String s, final String s2, final Ack ack) {
        this(context, s, s2, null, ack);
    }
    
    public MqttAndroidClient(final Context context, final String s, final String s2, final MqttClientPersistence mqttClientPersistence) {
        this(context, s, s2, mqttClientPersistence, Ack.AUTO_ACK);
    }
    
    public MqttAndroidClient(final Context myContext, final String serverURI, final String clientId, final MqttClientPersistence persistence, final Ack messageAck) {
        this.serviceConnection = new MyServiceConnection();
        this.tokenMap = (SparseArray<IMqttToken>)new SparseArray();
        this.tokenNumber = 0;
        this.persistence = null;
        this.traceEnabled = false;
        this.receiverRegistered = false;
        this.bindedService = false;
        this.myContext = myContext;
        this.serverURI = serverURI;
        this.clientId = clientId;
        this.persistence = persistence;
        this.messageAck = messageAck;
    }
    
    private void connectAction(final Bundle bundle) {
        final IMqttToken connectToken = this.connectToken;
        this.removeMqttToken(bundle);
        this.simpleAction(connectToken, bundle);
    }
    
    private void connectExtendedAction(final Bundle bundle) {
        if (this.callback instanceof MqttCallbackExtended) {
            ((MqttCallbackExtended)this.callback).connectComplete(bundle.getBoolean("MqttService.reconnect", false), bundle.getString("MqttService.serverURI"));
        }
    }
    
    private void connectionLostAction(final Bundle bundle) {
        if (this.callback != null) {
            this.callback.connectionLost((Throwable)bundle.getSerializable("MqttService.exception"));
        }
    }
    
    private void disconnected(final Bundle bundle) {
        this.clientHandle = null;
        final IMqttToken removeMqttToken = this.removeMqttToken(bundle);
        if (removeMqttToken != null) {
            ((MqttTokenAndroid)removeMqttToken).notifyComplete();
        }
        final MqttCallback callback = this.callback;
        if (callback != null) {
            callback.connectionLost(null);
        }
    }
    
    private void doConnect() {
        if (this.clientHandle == null) {
            this.clientHandle = this.mqttService.getClient(this.serverURI, this.clientId, this.myContext.getApplicationInfo().packageName, this.persistence);
        }
        this.mqttService.setTraceEnabled(this.traceEnabled);
        this.mqttService.setTraceCallbackId(this.clientHandle);
        final String storeToken = this.storeToken(this.connectToken);
        try {
            this.mqttService.connect(this.clientHandle, this.connectOptions, null, storeToken);
        }
        catch (final MqttException ex) {
            final IMqttActionListener actionCallback = this.connectToken.getActionCallback();
            if (actionCallback != null) {
                actionCallback.onFailure(this.connectToken, (Throwable)ex);
            }
        }
    }
    
    private IMqttToken getMqttToken(final Bundle bundle) {
        synchronized (this) {
            return (IMqttToken)this.tokenMap.get(Integer.parseInt(bundle.getString("MqttService.activityToken")));
        }
    }
    
    private void messageArrivedAction(final Bundle bundle) {
        if (this.callback == null) {
            return;
        }
        final String string = bundle.getString("MqttService.messageId");
        final String string2 = bundle.getString("MqttService.destinationName");
        final ParcelableMqttMessage parcelableMqttMessage = (ParcelableMqttMessage)bundle.getParcelable("MqttService.PARCEL");
        try {
            if (this.messageAck == Ack.AUTO_ACK) {
                this.callback.messageArrived(string2, parcelableMqttMessage);
                this.mqttService.acknowledgeMessageArrival(this.clientHandle, string);
            }
            else {
                parcelableMqttMessage.messageId = string;
                this.callback.messageArrived(string2, parcelableMqttMessage);
            }
        }
        catch (final Exception ex) {}
    }
    
    private void messageDeliveredAction(final Bundle bundle) {
        final IMqttToken removeMqttToken = this.removeMqttToken(bundle);
        if (removeMqttToken != null && this.callback != null && (Status)bundle.getSerializable("MqttService.callbackStatus") == Status.OK && removeMqttToken instanceof IMqttDeliveryToken) {
            this.callback.deliveryComplete((IMqttDeliveryToken)removeMqttToken);
        }
    }
    
    private void registerReceiver(final BroadcastReceiver broadcastReceiver) {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MqttService.callbackToActivity.v0");
        LocalBroadcastManager.getInstance(this.myContext).registerReceiver(broadcastReceiver, intentFilter);
        this.receiverRegistered = true;
    }
    
    private IMqttToken removeMqttToken(final Bundle bundle) {
        synchronized (this) {
            final String string = bundle.getString("MqttService.activityToken");
            if (string != null) {
                final int int1 = Integer.parseInt(string);
                final IMqttToken mqttToken = (IMqttToken)this.tokenMap.get(int1);
                this.tokenMap.delete(int1);
                return mqttToken;
            }
            return null;
        }
    }
    
    private void sendAction(final Bundle bundle) {
        this.simpleAction(this.getMqttToken(bundle), bundle);
    }
    
    private void simpleAction(final IMqttToken mqttToken, final Bundle bundle) {
        if (mqttToken != null) {
            if ((Status)bundle.getSerializable("MqttService.callbackStatus") == Status.OK) {
                ((MqttTokenAndroid)mqttToken).notifyComplete();
            }
            else {
                ((MqttTokenAndroid)mqttToken).notifyFailure((Throwable)bundle.getSerializable("MqttService.exception"));
            }
        }
        else {
            this.mqttService.traceError("MqttService", "simpleAction : token is null");
        }
    }
    
    private String storeToken(final IMqttToken mqttToken) {
        synchronized (this) {
            this.tokenMap.put(this.tokenNumber, (Object)mqttToken);
            return Integer.toString(this.tokenNumber++);
        }
    }
    
    private void subscribeAction(final Bundle bundle) {
        this.simpleAction(this.removeMqttToken(bundle), bundle);
    }
    
    private void traceAction(final Bundle bundle) {
        if (this.traceCallback != null) {
            final String string = bundle.getString("MqttService.traceSeverity");
            final String string2 = bundle.getString("MqttService.errorMessage");
            final String string3 = bundle.getString("MqttService.traceTag");
            if ("debug".equals((Object)string)) {
                this.traceCallback.traceDebug(string3, string2);
            }
            else if ("error".equals((Object)string)) {
                this.traceCallback.traceError(string3, string2);
            }
            else {
                this.traceCallback.traceException(string3, string2, (Exception)bundle.getSerializable("MqttService.exception"));
            }
        }
    }
    
    private void unSubscribeAction(final Bundle bundle) {
        this.simpleAction(this.removeMqttToken(bundle), bundle);
    }
    
    public boolean acknowledgeMessage(final String s) {
        final Ack messageAck = this.messageAck;
        final Ack manual_ACK = Ack.MANUAL_ACK;
        boolean b = false;
        if (messageAck == manual_ACK) {
            b = b;
            if (this.mqttService.acknowledgeMessageArrival(this.clientHandle, s) == Status.OK) {
                b = true;
            }
        }
        return b;
    }
    
    public void close() {
        final MqttService mqttService = this.mqttService;
        if (mqttService != null) {
            if (this.clientHandle == null) {
                this.clientHandle = mqttService.getClient(this.serverURI, this.clientId, this.myContext.getApplicationInfo().packageName, this.persistence);
            }
            this.mqttService.close(this.clientHandle);
        }
    }
    
    public IMqttToken connect() throws MqttException {
        return this.connect(null, null);
    }
    
    public IMqttToken connect(final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        return this.connect(new MqttConnectOptions(), o, mqttActionListener);
    }
    
    public IMqttToken connect(final MqttConnectOptions mqttConnectOptions) throws MqttException {
        return this.connect(mqttConnectOptions, null, null);
    }
    
    public IMqttToken connect(final MqttConnectOptions connectOptions, final Object o, IMqttActionListener actionCallback) throws MqttException {
        final MqttTokenAndroid connectToken = new MqttTokenAndroid(this, o, actionCallback);
        this.connectOptions = connectOptions;
        this.connectToken = connectToken;
        if (this.mqttService == null) {
            final Intent intent = new Intent();
            intent.setClassName(this.myContext, "org.eclipse.paho.android.service.MqttService");
            if (this.myContext.startService(intent) == null) {
                actionCallback = connectToken.getActionCallback();
                if (actionCallback != null) {
                    actionCallback.onFailure(connectToken, (Throwable)new RuntimeException("cannot start service org.eclipse.paho.android.service.MqttService"));
                }
            }
            this.myContext.bindService(intent, (ServiceConnection)this.serviceConnection, 1);
            if (!this.receiverRegistered) {
                this.registerReceiver(this);
            }
        }
        else {
            MqttAndroidClient.pool.execute((Runnable)new Runnable(this) {
                final MqttAndroidClient this$0;
                
                public void run() {
                    this.this$0.doConnect();
                    if (!this.this$0.receiverRegistered) {
                        final MqttAndroidClient this$0 = this.this$0;
                        this$0.registerReceiver(this$0);
                    }
                }
            });
        }
        return connectToken;
    }
    
    public void deleteBufferedMessage(final int n) {
        this.mqttService.deleteBufferedMessage(this.clientHandle, n);
    }
    
    public IMqttToken disconnect() throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, null);
        this.mqttService.disconnect(this.clientHandle, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken disconnect(final long n) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, null);
        this.mqttService.disconnect(this.clientHandle, n, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken disconnect(final long n, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener);
        this.mqttService.disconnect(this.clientHandle, n, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken disconnect(final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener);
        this.mqttService.disconnect(this.clientHandle, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public void disconnectForcibly() throws MqttException {
        throw new UnsupportedOperationException();
    }
    
    public void disconnectForcibly(final long n) throws MqttException {
        throw new UnsupportedOperationException();
    }
    
    public void disconnectForcibly(final long n, final long n2) throws MqttException {
        throw new UnsupportedOperationException();
    }
    
    public MqttMessage getBufferedMessage(final int n) {
        return this.mqttService.getBufferedMessage(this.clientHandle, n);
    }
    
    public int getBufferedMessageCount() {
        return this.mqttService.getBufferedMessageCount(this.clientHandle);
    }
    
    public String getClientId() {
        return this.clientId;
    }
    
    public int getInFlightMessageCount() {
        return 0;
    }
    
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.mqttService.getPendingDeliveryTokens(this.clientHandle);
    }
    
    public SSLSocketFactory getSSLSocketFactory(InputStream ex, final String s) throws MqttSecurityException {
        try {
            final KeyStore instance = KeyStore.getInstance("BKS");
            instance.load((InputStream)ex, s.toCharArray());
            ex = (KeyManagementException)TrustManagerFactory.getInstance("X509");
            ((TrustManagerFactory)ex).init(instance);
            ex = (KeyManagementException)(Object)((TrustManagerFactory)ex).getTrustManagers();
            final SSLContext instance2 = SSLContext.getInstance("TLSv1");
            instance2.init((KeyManager[])null, (TrustManager[])(Object)ex, (SecureRandom)null);
            ex = (KeyManagementException)instance2.getSocketFactory();
            return (SSLSocketFactory)ex;
        }
        catch (final KeyManagementException ex) {}
        catch (final NoSuchAlgorithmException ex) {}
        catch (final IOException ex) {}
        catch (final CertificateException ex) {}
        catch (final KeyStoreException ex2) {}
        throw new MqttSecurityException((Throwable)ex);
    }
    
    public String getServerURI() {
        return this.serverURI;
    }
    
    public boolean isConnected() {
        final String clientHandle = this.clientHandle;
        if (clientHandle != null) {
            final MqttService mqttService = this.mqttService;
            if (mqttService != null && mqttService.isConnected(clientHandle)) {
                return true;
            }
        }
        return false;
    }
    
    public void messageArrivedComplete(final int n, final int n2) throws MqttException {
        throw new UnsupportedOperationException();
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Bundle extras = intent.getExtras();
        final String string = extras.getString("MqttService.clientHandle");
        if (string != null) {
            if (string.equals((Object)this.clientHandle)) {
                final String string2 = extras.getString("MqttService.callbackAction");
                if ("connect".equals((Object)string2)) {
                    this.connectAction(extras);
                }
                else if ("connectExtended".equals((Object)string2)) {
                    this.connectExtendedAction(extras);
                }
                else if ("messageArrived".equals((Object)string2)) {
                    this.messageArrivedAction(extras);
                }
                else if ("subscribe".equals((Object)string2)) {
                    this.subscribeAction(extras);
                }
                else if ("unsubscribe".equals((Object)string2)) {
                    this.unSubscribeAction(extras);
                }
                else if ("send".equals((Object)string2)) {
                    this.sendAction(extras);
                }
                else if ("messageDelivered".equals((Object)string2)) {
                    this.messageDeliveredAction(extras);
                }
                else if ("onConnectionLost".equals((Object)string2)) {
                    this.connectionLostAction(extras);
                }
                else if ("disconnect".equals((Object)string2)) {
                    this.disconnected(extras);
                }
                else if ("trace".equals((Object)string2)) {
                    this.traceAction(extras);
                }
                else {
                    this.mqttService.traceError("MqttService", "Callback action doesn't exist.");
                }
            }
        }
    }
    
    public IMqttDeliveryToken publish(final String s, final MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return this.publish(s, mqttMessage, null, null);
    }
    
    public IMqttDeliveryToken publish(final String s, final MqttMessage mqttMessage, final Object o, final IMqttActionListener mqttActionListener) throws MqttException, MqttPersistenceException {
        final MqttDeliveryTokenAndroid mqttDeliveryTokenAndroid = new MqttDeliveryTokenAndroid(this, o, mqttActionListener, mqttMessage);
        mqttDeliveryTokenAndroid.setDelegate(this.mqttService.publish(this.clientHandle, s, mqttMessage, null, this.storeToken(mqttDeliveryTokenAndroid)));
        return mqttDeliveryTokenAndroid;
    }
    
    public IMqttDeliveryToken publish(final String s, final byte[] array, final int n, final boolean b) throws MqttException, MqttPersistenceException {
        return this.publish(s, array, n, b, null, null);
    }
    
    public IMqttDeliveryToken publish(final String s, final byte[] array, final int qos, final boolean retained, final Object o, final IMqttActionListener mqttActionListener) throws MqttException, MqttPersistenceException {
        final MqttMessage mqttMessage = new MqttMessage(array);
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        final MqttDeliveryTokenAndroid mqttDeliveryTokenAndroid = new MqttDeliveryTokenAndroid(this, o, mqttActionListener, mqttMessage);
        mqttDeliveryTokenAndroid.setDelegate(this.mqttService.publish(this.clientHandle, s, array, qos, retained, null, this.storeToken(mqttDeliveryTokenAndroid)));
        return mqttDeliveryTokenAndroid;
    }
    
    public void reconnect() throws MqttException {
    }
    
    public void registerResources(final Context myContext) {
        if (myContext != null) {
            this.myContext = myContext;
            if (!this.receiverRegistered) {
                this.registerReceiver(this);
            }
        }
    }
    
    public boolean removeMessage(final IMqttDeliveryToken mqttDeliveryToken) throws MqttException {
        return false;
    }
    
    public void setBufferOpts(final DisconnectedBufferOptions disconnectedBufferOptions) {
        this.mqttService.setBufferOpts(this.clientHandle, disconnectedBufferOptions);
    }
    
    public void setCallback(final MqttCallback callback) {
        this.callback = callback;
    }
    
    public void setManualAcks(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    public void setTraceCallback(final MqttTraceHandler traceCallback) {
        this.traceCallback = traceCallback;
    }
    
    public void setTraceEnabled(final boolean b) {
        this.traceEnabled = b;
        final MqttService mqttService = this.mqttService;
        if (mqttService != null) {
            mqttService.setTraceEnabled(b);
        }
    }
    
    public IMqttToken subscribe(final String s, final int n) throws MqttException, MqttSecurityException {
        return this.subscribe(s, n, null, null);
    }
    
    public IMqttToken subscribe(final String s, final int n, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener, new String[] { s });
        this.mqttService.subscribe(this.clientHandle, s, n, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken subscribe(final String s, final int n, final Object o, final IMqttActionListener mqttActionListener, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribe(new String[] { s }, new int[] { n }, o, mqttActionListener, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    public IMqttToken subscribe(final String s, final int n, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribe(s, n, null, null, mqttMessageListener);
    }
    
    public IMqttToken subscribe(final String[] array, final int[] array2) throws MqttException, MqttSecurityException {
        return this.subscribe(array, array2, null, null);
    }
    
    public IMqttToken subscribe(final String[] array, final int[] array2, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener, array);
        this.mqttService.subscribe(this.clientHandle, array, array2, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken subscribe(final String[] array, final int[] array2, final Object o, final IMqttActionListener mqttActionListener, final IMqttMessageListener[] array3) throws MqttException {
        this.mqttService.subscribe(this.clientHandle, array, array2, null, this.storeToken(new MqttTokenAndroid(this, o, mqttActionListener, array)), array3);
        return null;
    }
    
    public IMqttToken subscribe(final String[] array, final int[] array2, final IMqttMessageListener[] array3) throws MqttException {
        return this.subscribe(array, array2, null, null, array3);
    }
    
    public void unregisterResources() {
        if (this.myContext == null || !this.receiverRegistered) {
            return;
        }
        synchronized (this) {
            LocalBroadcastManager.getInstance(this.myContext).unregisterReceiver((BroadcastReceiver)this);
            this.receiverRegistered = false;
            monitorexit(this);
            if (this.bindedService) {
                final MqttAndroidClient mqttAndroidClient = this;
                final Context context = mqttAndroidClient.myContext;
                final MqttAndroidClient mqttAndroidClient2 = this;
                final MyServiceConnection myServiceConnection = mqttAndroidClient2.serviceConnection;
                context.unbindService((ServiceConnection)myServiceConnection);
                final MqttAndroidClient mqttAndroidClient3 = this;
                final boolean b = false;
                mqttAndroidClient3.bindedService = b;
            }
            return;
        }
        try {
            final MqttAndroidClient mqttAndroidClient = this;
            final Context context = mqttAndroidClient.myContext;
            final MqttAndroidClient mqttAndroidClient2 = this;
            final MyServiceConnection myServiceConnection = mqttAndroidClient2.serviceConnection;
            context.unbindService((ServiceConnection)myServiceConnection);
            final MqttAndroidClient mqttAndroidClient3 = this;
            final boolean b = false;
            mqttAndroidClient3.bindedService = b;
        }
        catch (final IllegalArgumentException ex) {}
    }
    
    public IMqttToken unsubscribe(final String s) throws MqttException {
        return this.unsubscribe(s, null, null);
    }
    
    public IMqttToken unsubscribe(final String s, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener);
        this.mqttService.unsubscribe(this.clientHandle, s, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public IMqttToken unsubscribe(final String[] array) throws MqttException {
        return this.unsubscribe(array, null, null);
    }
    
    public IMqttToken unsubscribe(final String[] array, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        final MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, o, mqttActionListener);
        this.mqttService.unsubscribe(this.clientHandle, array, null, this.storeToken(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
    
    public enum Ack
    {
        private static final Ack[] $VALUES;
        
        AUTO_ACK, 
        MANUAL_ACK;
    }
    
    private final class MyServiceConnection implements ServiceConnection
    {
        final MqttAndroidClient this$0;
        
        private MyServiceConnection(final MqttAndroidClient this$0) {
            this.this$0 = this$0;
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            this.this$0.mqttService = ((MqttServiceBinder)binder).getService();
            this.this$0.bindedService = true;
            this.this$0.doConnect();
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            this.this$0.mqttService = null;
        }
    }
}
