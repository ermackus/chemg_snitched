package com.kingagroot.component.ui.account.service;

import android.os.CountDownTimer;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public class PhoneRegCodeTimerService extends Service
{
    public static final String PHONE_REG_END_RUNNING = "PHONE_REG_END_RUNNING";
    public static final String PHONE_REG_IN_RUNNING = "PHONE_REG_IN_RUNNING";
    
    private void broadcastUpdate() {
        this.sendBroadcast(new Intent("PHONE_REG_END_RUNNING"));
    }
    
    private void broadcastUpdate(final String s) {
        final Intent intent = new Intent("PHONE_REG_IN_RUNNING");
        intent.putExtra("time", s);
        this.sendBroadcast(intent);
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        new CountDownTimer(this, 60000L, 1000L) {
            final PhoneRegCodeTimerService this$0;
            
            public void onFinish() {
                this.this$0.broadcastUpdate();
                this.this$0.stopSelf();
            }
            
            public void onTick(final long n) {
                final PhoneRegCodeTimerService this$0 = this.this$0;
                final StringBuilder sb = new StringBuilder();
                sb.append(n / 1000L);
                sb.append("");
                this$0.broadcastUpdate(sb.toString());
            }
        }.start();
        return super.onStartCommand(intent, n, n2);
    }
}
