package com.alibaba.sdk.android.man;

public class MANServiceProvider implements MANService
{
    private MANServiceProvider() {
    }
    
    public static MANService getService() {
        return MANServiceProvider.MANServiceProvider$Singleton.instance;
    }
    
    public MANAnalytics getMANAnalytics() {
        return MANAnalytics.getInstance();
    }
    
    public MANPageHitHelper getMANPageHitHelper() {
        return MANPageHitHelper.getInstance();
    }
}
