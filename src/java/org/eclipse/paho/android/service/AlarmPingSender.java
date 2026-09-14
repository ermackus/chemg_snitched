package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import android.os.PowerManager;
import android.os.PowerManager$WakeLock;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import android.app.AlarmManager;
import android.util.Log;
import android.app.PendingIntent;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import android.content.BroadcastReceiver;
import org.eclipse.paho.client.mqttv3.MqttPingSender;

class AlarmPingSender implements MqttPingSender
{
    private static final String TAG = "AlarmPingSender";
    private BroadcastReceiver alarmReceiver;
    private ClientComms comms;
    private volatile boolean hasStarted;
    private PendingIntent pendingIntent;
    private MqttService service;
    private AlarmPingSender that;
    
    public AlarmPingSender(final MqttService service) {
        this.hasStarted = false;
        if (service != null) {
            this.service = service;
            this.that = this;
            return;
        }
        throw new IllegalArgumentException("Neither service nor client can be null.");
    }
    
    @Override
    public void init(final ClientComms comms) {
        this.comms = comms;
        this.alarmReceiver = new AlarmReceiver();
    }
    
    @Override
    public void schedule(final long n) {
        final long n2 = System.currentTimeMillis() + n;
        final StringBuilder sb = new StringBuilder();
        sb.append("Schedule next alarm at ");
        sb.append(n2);
        Log.d("AlarmPingSender", sb.toString());
        final AlarmManager alarmManager = (AlarmManager)this.service.getSystemService("alarm");
        if (Build$VERSION.SDK_INT >= 32) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExact(2, n2, this.pendingIntent);
            }
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Alarm scheule using setExactAndAllowWhileIdle, next: ");
            sb2.append(n);
            Log.d("AlarmPingSender", sb2.toString());
            alarmManager.setExactAndAllowWhileIdle(0, n2, this.pendingIntent);
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("Alarm scheule using setExact, delay: ");
            sb3.append(n);
            Log.d("AlarmPingSender", sb3.toString());
            alarmManager.setExact(0, n2, this.pendingIntent);
        }
        else {
            alarmManager.set(0, n2, this.pendingIntent);
        }
    }
    
    @Override
    public void start() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MqttService.pingSender.");
        sb.append(this.comms.getClient().getClientId());
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("Register alarmreceiver to MqttService");
        sb2.append(string);
        Log.d("AlarmPingSender", sb2.toString());
        this.service.registerReceiver(this.alarmReceiver, new IntentFilter(string));
        if (Build$VERSION.SDK_INT >= 23) {
            this.pendingIntent = PendingIntent.getBroadcast((Context)this.service, 0, new Intent(string), 201326592);
        }
        else {
            this.pendingIntent = PendingIntent.getBroadcast((Context)this.service, 0, new Intent(string), 134217728);
        }
        this.schedule(this.comms.getKeepAlive());
        this.hasStarted = true;
    }
    
    @Override
    public void stop() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Unregister alarmreceiver to MqttService");
        sb.append(this.comms.getClient().getClientId());
        Log.d("AlarmPingSender", sb.toString());
        if (!this.hasStarted) {
            return;
        }
        if (this.pendingIntent != null) {
            ((AlarmManager)this.service.getSystemService("alarm")).cancel(this.pendingIntent);
        }
        this.hasStarted = false;
        try {
            this.service.unregisterReceiver(this.alarmReceiver);
        }
        catch (final IllegalArgumentException ex) {}
    }
    
    class AlarmReceiver extends BroadcastReceiver
    {
        final AlarmPingSender this$0;
        private final String wakeLockTag;
        private PowerManager$WakeLock wakelock;
        
        AlarmReceiver(final AlarmPingSender this$0) {
            this.this$0 = this$0;
            final StringBuilder sb = new StringBuilder();
            sb.append("MqttService.client.");
            sb.append(this.this$0.that.comms.getClient().getClientId());
            this.wakeLockTag = sb.toString();
        }
        
        public void onReceive(final Context context, final Intent intent) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Sending Ping at:");
            sb.append(System.currentTimeMillis());
            Log.d("AlarmPingSender", sb.toString());
            (this.wakelock = ((PowerManager)this.this$0.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag)).acquire();
            if (this.this$0.comms.checkForActivity(new IMqttActionListener(this) {
                final AlarmReceiver this$1;
                
                @Override
                public void onFailure(final IMqttToken mqttToken, final Throwable t) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Failure. Release lock(");
                    sb.append(this.this$1.wakeLockTag);
                    sb.append("):");
                    sb.append(System.currentTimeMillis());
                    Log.d("AlarmPingSender", sb.toString());
                    this.this$1.wakelock.release();
                }
                
                @Override
                public void onSuccess(final IMqttToken mqttToken) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Success. Release lock(");
                    sb.append(this.this$1.wakeLockTag);
                    sb.append("):");
                    sb.append(System.currentTimeMillis());
                    Log.d("AlarmPingSender", sb.toString());
                    this.this$1.wakelock.release();
                }
            }) == null && this.wakelock.isHeld()) {
                this.wakelock.release();
            }
        }
    }
}
