package com.ut.mini.plugin;

public abstract class UTPlugin
{
    public static final int MSG_ID_IN_DISPATCH_AGGREGATION_LOG_MAP = 5;
    public static final int MSG_ID_IN_DISPATCH_LOG_STRING_4_UTPERF_PLUGIN = 9;
    public static final int MSG_ID_IN_NOMATCH_ONLINE_CONF = 7;
    public static final int MSG_ID_IN_SWITCH_BACKGROUND = 2;
    public static final int MSG_ID_IN_SWITCH_FOREGROUND = 8;
    public static final int MSG_ID_OUT_AGGREGATED_LOG_MAP = 65536;
    private UTPluginContext a;
    
    public UTPlugin() {
        this.a = null;
    }
    
    void a(final UTPluginContext a) {
        this.a = a;
    }
    
    public final void deliverMsgToSDK(final int n, final Object o) {
        UTPluginMgr.getInstance().dispatchPluginMsg(n, o);
    }
    
    public final UTPluginContext getPluginContext() {
        return this.a;
    }
    
    public void onPluginContextValueUpdate(final int n) {
    }
    
    public abstract void onPluginMsgArrivedFromSDK(final int p0, final Object p1);
    
    public void onRegistered() {
    }
    
    public void onUnRegistered() {
    }
    
    public abstract int[] returnRequiredMsgIds();
    
    public String[] returnRequiredOnlineConfNames() {
        return null;
    }
}
