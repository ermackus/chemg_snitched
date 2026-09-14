package com.kingagroot.kingdraw.mqtt;

import com.kingagroot.kingdraw.base.MApplication;
import com.goodsrc.library.utils.DateTimeUtils;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.app.Service;

public class VipStateCheckService extends Service
{
    public static final String TAG = "VipStateCheckService";
    
    private void refreshVipState() {
        this.sendBroadcast(new Intent("USER_VIP_STATE_CHANGE"));
    }
    
    public static void startService(final Context context) {
        context.startService(new Intent(context, (Class)VipStateCheckService.class));
    }
    
    public static void stopService(final Context context) {
        context.stopService(new Intent(context, (Class)VipStateCheckService.class));
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        if (DateTimeUtils.timeCompare(DateTimeUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), MApplication.getInstance().getAccountUserModel().getVipExpiryTime()) == 1) {
            this.refreshVipState();
            this.stopSelf();
            stopService((Context)this);
        }
        return super.onStartCommand(intent, n, n2);
    }
}
