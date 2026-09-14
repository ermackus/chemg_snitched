package com.ut.mini.plugin;

import android.content.Context;

public class UTPluginContext
{
    public static final int DEBUG_LOG_SWITCH = 1;
    private boolean U;
    private boolean V;
    private Context mContext;
    
    public UTPluginContext() {
        this.mContext = null;
        this.U = false;
        this.V = false;
    }
    
    public void enableRealtimeDebug() {
        this.V = true;
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public boolean isDebugLogEnable() {
        return this.U;
    }
    
    public boolean isRealtimeDebugEnable() {
        return this.V;
    }
    
    public void setContext(final Context mContext) {
        this.mContext = mContext;
    }
    
    public void setDebugLogFlag(final boolean u) {
        this.U = u;
    }
}
