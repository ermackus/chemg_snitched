package com.kingagroot.component.ui.account.service;

import android.os.Handler;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public class EmailRegCodeTimerService extends Service
{
    public static final String EMAIL_REG_END_RUNNING = "EMAIL_REG_END_RUNNING";
    public static final String EMAIL_REG_IN_RUNNING = "EMAIL_REG_IN_RUNNING";
    
    private void broadcastUpdate() {
        this.sendBroadcast(new Intent("EMAIL_REG_END_RUNNING"));
    }
    
    private void broadcastUpdate(final String s) {
        final Intent intent = new Intent("EMAIL_REG_IN_RUNNING");
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
        new CountDownTimer(this, 61050L, 1000L) {
            final EmailRegCodeTimerService this$0;
            
            public void onFinish() {
                this.this$0.broadcastUpdate();
                this.this$0.stopSelf();
            }
            
            public void onTick(long n) {
                final EmailRegCodeTimerService this$0 = this.this$0;
                final StringBuilder sb = new StringBuilder();
                n = n / 1000L - 1L;
                sb.append(n);
                sb.append("");
                this$0.broadcastUpdate(sb.toString());
                if (n == 0L) {
                    new Handler().postDelayed((Runnable)new Runnable(this) {
                        final EmailRegCodeTimerService$1 this$1;
                        
                        public void run() {
                            this.this$1.onFinish();
                        }
                    }, 1000L);
                }
            }
        }.start();
        return super.onStartCommand(intent, n, n2);
    }
}
