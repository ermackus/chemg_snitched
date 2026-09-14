package org.eclipse.paho.android.service;

import android.os.PowerManager$WakeLock;
import android.os.PowerManager;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import android.os.IBinder;
import android.net.NetworkInfo;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.Intent;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.os.Build$VERSION;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import android.app.Service;

public class MqttService extends Service implements MqttTraceHandler
{
    static final String TAG = "MqttService";
    private volatile boolean backgroundDataEnabled;
    private BackgroundDataPreferenceReceiver backgroundDataPreferenceMonitor;
    private Map<String, MqttConnection> connections;
    MessageStore messageStore;
    private MqttServiceBinder mqttServiceBinder;
    private NetworkConnectionIntentReceiver networkConnectionMonitor;
    private String traceCallbackId;
    private boolean traceEnabled;
    
    public MqttService() {
        this.traceEnabled = false;
        this.backgroundDataEnabled = true;
        this.connections = (Map<String, MqttConnection>)new ConcurrentHashMap();
    }
    
    private MqttConnection getConnection(final String s) {
        final MqttConnection mqttConnection = (MqttConnection)this.connections.get((Object)s);
        if (mqttConnection != null) {
            return mqttConnection;
        }
        throw new IllegalArgumentException("Invalid ClientHandle");
    }
    
    private void notifyClientsOffline() {
        final Iterator iterator = this.connections.values().iterator();
        while (iterator.hasNext()) {
            ((MqttConnection)iterator.next()).offline();
        }
    }
    
    private void registerBroadcastReceivers() {
        if (this.networkConnectionMonitor == null) {
            this.registerReceiver((BroadcastReceiver)(this.networkConnectionMonitor = new NetworkConnectionIntentReceiver()), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (Build$VERSION.SDK_INT < 14) {
            this.backgroundDataEnabled = ((ConnectivityManager)this.getSystemService("connectivity")).getBackgroundDataSetting();
            if (this.backgroundDataPreferenceMonitor == null) {
                this.registerReceiver((BroadcastReceiver)(this.backgroundDataPreferenceMonitor = new BackgroundDataPreferenceReceiver()), new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
            }
        }
    }
    
    private void traceCallback(final String s, final String s2, final String s3) {
        if (this.traceCallbackId != null && this.traceEnabled) {
            final Bundle bundle = new Bundle();
            bundle.putString("MqttService.callbackAction", "trace");
            bundle.putString("MqttService.traceSeverity", s);
            bundle.putString("MqttService.traceTag", s2);
            bundle.putString("MqttService.errorMessage", s3);
            this.callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
        }
    }
    
    private void unregisterBroadcastReceivers() {
        final NetworkConnectionIntentReceiver networkConnectionMonitor = this.networkConnectionMonitor;
        if (networkConnectionMonitor != null) {
            this.unregisterReceiver((BroadcastReceiver)networkConnectionMonitor);
            this.networkConnectionMonitor = null;
        }
        if (Build$VERSION.SDK_INT < 14) {
            final BackgroundDataPreferenceReceiver backgroundDataPreferenceMonitor = this.backgroundDataPreferenceMonitor;
            if (backgroundDataPreferenceMonitor != null) {
                this.unregisterReceiver((BroadcastReceiver)backgroundDataPreferenceMonitor);
            }
        }
    }
    
    public Status acknowledgeMessageArrival(final String s, final String s2) {
        if (this.messageStore.discardArrived(s, s2)) {
            return Status.OK;
        }
        return Status.ERROR;
    }
    
    void callbackToActivity(final String s, final Status status, final Bundle bundle) {
        final Intent intent = new Intent("MqttService.callbackToActivity.v0");
        if (s != null) {
            intent.putExtra("MqttService.clientHandle", s);
        }
        intent.putExtra("MqttService.callbackStatus", (Serializable)status);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
    }
    
    public void close(final String s) {
        this.getConnection(s).close();
    }
    
    public void connect(final String s, final MqttConnectOptions mqttConnectOptions, final String s2, final String s3) throws MqttSecurityException, MqttException {
        this.getConnection(s).connect(mqttConnectOptions, null, s3);
    }
    
    public void deleteBufferedMessage(final String s, final int n) {
        this.getConnection(s).deleteBufferedMessage(n);
    }
    
    public void disconnect(final String s, final long n, final String s2, final String s3) {
        this.getConnection(s).disconnect(n, s2, s3);
        this.connections.remove((Object)s);
        this.stopSelf();
    }
    
    public void disconnect(final String s, final String s2, final String s3) {
        this.getConnection(s).disconnect(s2, s3);
        this.connections.remove((Object)s);
        this.stopSelf();
    }
    
    public MqttMessage getBufferedMessage(final String s, final int n) {
        return this.getConnection(s).getBufferedMessage(n);
    }
    
    public int getBufferedMessageCount(final String s) {
        return this.getConnection(s).getBufferedMessageCount();
    }
    
    public String getClient(final String s, final String s2, String string, final MqttClientPersistence mqttClientPersistence) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(":");
        sb.append(s2);
        sb.append(":");
        sb.append(string);
        string = sb.toString();
        if (!this.connections.containsKey((Object)string)) {
            this.connections.put((Object)string, (Object)new MqttConnection(this, s, s2, mqttClientPersistence, string));
        }
        return string;
    }
    
    public IMqttDeliveryToken[] getPendingDeliveryTokens(final String s) {
        return this.getConnection(s).getPendingDeliveryTokens();
    }
    
    public boolean isConnected(final String s) {
        return this.getConnection(s).isConnected();
    }
    
    public boolean isOnline() {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)this.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected() && this.backgroundDataEnabled;
    }
    
    public boolean isTraceEnabled() {
        return this.traceEnabled;
    }
    
    public IBinder onBind(final Intent intent) {
        this.mqttServiceBinder.setActivityToken(intent.getStringExtra("MqttService.activityToken"));
        return (IBinder)this.mqttServiceBinder;
    }
    
    public void onCreate() {
        super.onCreate();
        this.mqttServiceBinder = new MqttServiceBinder(this);
        this.messageStore = new DatabaseMessageStore(this, (Context)this);
    }
    
    public void onDestroy() {
        final Iterator iterator = this.connections.values().iterator();
        while (iterator.hasNext()) {
            ((MqttConnection)iterator.next()).disconnect(null, null);
        }
        if (this.mqttServiceBinder != null) {
            this.mqttServiceBinder = null;
        }
        this.unregisterBroadcastReceivers();
        final MessageStore messageStore = this.messageStore;
        if (messageStore != null) {
            messageStore.close();
        }
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.registerBroadcastReceivers();
        return 1;
    }
    
    public IMqttDeliveryToken publish(final String s, final String s2, final MqttMessage mqttMessage, final String s3, final String s4) throws MqttPersistenceException, MqttException {
        return this.getConnection(s).publish(s2, mqttMessage, s3, s4);
    }
    
    public IMqttDeliveryToken publish(final String s, final String s2, final byte[] array, final int n, final boolean b, final String s3, final String s4) throws MqttPersistenceException, MqttException {
        return this.getConnection(s).publish(s2, array, n, b, s3, s4);
    }
    
    void reconnect() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Reconnect to server, client size=");
        sb.append(this.connections.size());
        this.traceDebug("MqttService", sb.toString());
        for (final MqttConnection mqttConnection : this.connections.values()) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(mqttConnection.getClientId());
            sb2.append('/');
            sb2.append(mqttConnection.getServerURI());
            this.traceDebug("Reconnect Client:", sb2.toString());
            if (this.isOnline()) {
                mqttConnection.reconnect();
            }
        }
    }
    
    public void setBufferOpts(final String s, final DisconnectedBufferOptions bufferOpts) {
        this.getConnection(s).setBufferOpts(bufferOpts);
    }
    
    public void setTraceCallbackId(final String traceCallbackId) {
        this.traceCallbackId = traceCallbackId;
    }
    
    public void setTraceEnabled(final boolean traceEnabled) {
        this.traceEnabled = traceEnabled;
    }
    
    public void subscribe(final String s, final String s2, final int n, final String s3, final String s4) {
        this.getConnection(s).subscribe(s2, n, s3, s4);
    }
    
    public void subscribe(final String s, final String[] array, final int[] array2, final String s2, final String s3) {
        this.getConnection(s).subscribe(array, array2, s2, s3);
    }
    
    public void subscribe(final String s, final String[] array, final int[] array2, final String s2, final String s3, final IMqttMessageListener[] array3) {
        this.getConnection(s).subscribe(array, array2, s2, s3, array3);
    }
    
    public void traceDebug(final String s, final String s2) {
        this.traceCallback("debug", s, s2);
    }
    
    public void traceError(final String s, final String s2) {
        this.traceCallback("error", s, s2);
    }
    
    public void traceException(final String s, final String s2, final Exception ex) {
        if (this.traceCallbackId != null) {
            final Bundle bundle = new Bundle();
            bundle.putString("MqttService.callbackAction", "trace");
            bundle.putString("MqttService.traceSeverity", "exception");
            bundle.putString("MqttService.errorMessage", s2);
            bundle.putSerializable("MqttService.exception", (Serializable)ex);
            bundle.putString("MqttService.traceTag", s);
            this.callbackToActivity(this.traceCallbackId, Status.ERROR, bundle);
        }
    }
    
    public void unsubscribe(final String s, final String s2, final String s3, final String s4) {
        this.getConnection(s).unsubscribe(s2, s3, s4);
    }
    
    public void unsubscribe(final String s, final String[] array, final String s2, final String s3) {
        this.getConnection(s).unsubscribe(array, s2, s3);
    }
    
    private class BackgroundDataPreferenceReceiver extends BroadcastReceiver
    {
        final MqttService this$0;
        
        private BackgroundDataPreferenceReceiver(final MqttService this$0) {
            this.this$0 = this$0;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)this.this$0.getSystemService("connectivity");
            this.this$0.traceDebug("MqttService", "Reconnect since BroadcastReceiver.");
            if (connectivityManager.getBackgroundDataSetting()) {
                if (!this.this$0.backgroundDataEnabled) {
                    this.this$0.backgroundDataEnabled = true;
                    this.this$0.reconnect();
                }
            }
            else {
                this.this$0.backgroundDataEnabled = false;
                this.this$0.notifyClientsOffline();
            }
        }
    }
    
    private class NetworkConnectionIntentReceiver extends BroadcastReceiver
    {
        final MqttService this$0;
        
        private NetworkConnectionIntentReceiver(final MqttService this$0) {
            this.this$0 = this$0;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            this.this$0.traceDebug("MqttService", "Internal network status receive.");
            final PowerManager$WakeLock wakeLock = ((PowerManager)this.this$0.getSystemService("power")).newWakeLock(1, "MQTT");
            wakeLock.acquire();
            this.this$0.traceDebug("MqttService", "Reconnect for Network recovery.");
            if (this.this$0.isOnline()) {
                this.this$0.traceDebug("MqttService", "Online,reconnect.");
                this.this$0.reconnect();
            }
            else {
                this.this$0.notifyClientsOffline();
            }
            wakeLock.release();
        }
    }
}
