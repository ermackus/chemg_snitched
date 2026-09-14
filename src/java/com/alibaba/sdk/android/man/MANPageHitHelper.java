package com.alibaba.sdk.android.man;

import java.util.Map;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.ut.mini.UTPageHitHelper;
import com.alibaba.sdk.android.man.util.MANLog;
import android.app.Activity;

public class MANPageHitHelper
{
    private static final String TAG;
    private volatile boolean isEnabled;
    
    static {
        TAG = MANTracker.class.getSimpleName();
    }
    
    private MANPageHitHelper() {
        this.isEnabled = true;
    }
    
    protected static MANPageHitHelper getInstance() {
        return Singleton.instance;
    }
    
    public void pageAppear(final Activity activity) {
        if (!this.isEnabled) {
            MANLog.Loge(MANPageHitHelper.TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().pageAppear((Object)activity);
        UTWrapper.commitPageEvent("1");
    }
    
    public void pageDisAppear(final Activity activity) {
        if (!this.isEnabled) {
            MANLog.Loge(MANPageHitHelper.TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().pageDisAppear((Object)activity);
        UTWrapper.commitPageEvent("1");
    }
    
    public void setEnableStatus(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    public void updatePageProperties(final Map<String, String> map) {
        if (!this.isEnabled) {
            MANLog.Loge(MANPageHitHelper.TAG, "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().updatePageProperties((Map)map);
        UTWrapper.commitPageEvent("1");
    }
    
    private static class Singleton
    {
        static MANPageHitHelper instance;
        
        static {
            Singleton.instance = new MANPageHitHelper(null);
        }
    }
}
