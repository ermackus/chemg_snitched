package com.luck.picture.lib.service;

import android.os.IBinder;
import android.content.Intent;
import android.content.Context;
import androidx.core.app.NotificationCompat$Builder;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectorProviders;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import com.luck.picture.lib.utils.SdkVersionUtils;
import android.app.Notification;
import android.app.Service;

public class ForegroundService extends Service
{
    private static final String CHANNEL_ID;
    private static final String CHANNEL_NAME = "com.luck.picture.lib";
    private static final int NOTIFICATION_ID = 1;
    private static boolean isForegroundServiceIng;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append("com.luck.picture.lib.");
        sb.append(ForegroundService.class.getName());
        CHANNEL_ID = sb.toString();
        ForegroundService.isForegroundServiceIng = false;
    }
    
    private Notification createForegroundNotification() {
        int n;
        if (SdkVersionUtils.isMaxN()) {
            n = 4;
        }
        else {
            n = 0;
        }
        if (SdkVersionUtils.isO()) {
            final NotificationChannel notificationChannel = new NotificationChannel(ForegroundService.CHANNEL_ID, (CharSequence)"com.luck.picture.lib", n);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.canBypassDnd();
            notificationChannel.setBypassDnd(true);
            notificationChannel.setLockscreenVisibility(0);
            ((NotificationManager)this.getSystemService("notification")).createNotificationChannel(notificationChannel);
        }
        int n2;
        if (SelectorProviders.getInstance().getSelectorConfig().chooseMode == SelectMimeType.ofAudio()) {
            n2 = R.string.ps_use_sound;
        }
        else {
            n2 = R.string.ps_use_camera;
        }
        return new NotificationCompat$Builder((Context)this, ForegroundService.CHANNEL_ID).setSmallIcon(R.drawable.ps_ic_trans_1px).setContentTitle((CharSequence)this.getAppName()).setContentText((CharSequence)this.getString(n2)).setOngoing(true).build();
    }
    
    private String getAppName() {
        try {
            return this.getPackageManager().getPackageInfo(this.getPackageName(), 0).applicationInfo.loadLabel(this.getPackageManager()).toString();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public static void startForegroundService(final Context context, final boolean b) {
        try {
            if (!ForegroundService.isForegroundServiceIng && b) {
                final Intent intent = new Intent(context, (Class)ForegroundService.class);
                if (SdkVersionUtils.isO()) {
                    context.startForegroundService(intent);
                }
                else {
                    context.startService(intent);
                }
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void stopService(final Context context) {
        try {
            if (ForegroundService.isForegroundServiceIng) {
                context.stopService(new Intent(context, (Class)ForegroundService.class));
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        this.startForeground(1, this.createForegroundNotification());
    }
    
    public void onDestroy() {
        ForegroundService.isForegroundServiceIng = false;
        this.stopForeground(true);
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        ForegroundService.isForegroundServiceIng = true;
        return super.onStartCommand(intent, n, n2);
    }
}
