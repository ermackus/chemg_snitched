package com.alibaba.mtl.appmonitor;

import android.os.RemoteException;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public class AppMonitorService extends Service
{
    IMonitor a;
    
    public AppMonitorService() {
        this.a = null;
    }
    
    public IBinder onBind(final Intent intent) {
        if (this.a == null) {
            this.a = (IMonitor)new Monitor(this.getApplication());
        }
        return (IBinder)this.a;
    }
    
    public void onDestroy() {
        final IMonitor a = this.a;
        while (true) {
            if (a == null) {
                break Label_0015;
            }
            try {
                a.triggerUpload();
                super.onDestroy();
            }
            catch (final RemoteException ex) {
                continue;
            }
            break;
        }
    }
    
    public void onLowMemory() {
        final IMonitor a = this.a;
        while (true) {
            if (a == null) {
                break Label_0015;
            }
            try {
                a.triggerUpload();
                super.onLowMemory();
            }
            catch (final RemoteException ex) {
                continue;
            }
            break;
        }
    }
}
