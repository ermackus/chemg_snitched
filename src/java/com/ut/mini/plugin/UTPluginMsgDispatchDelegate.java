package com.ut.mini.plugin;

public abstract class UTPluginMsgDispatchDelegate
{
    private Object g;
    
    public UTPluginMsgDispatchDelegate(final Object g) {
        this.g = null;
        this.g = g;
    }
    
    public Object getDispatchObject(final UTPlugin utPlugin) {
        return this.g;
    }
    
    public final Object getMsgObj() {
        return this.g;
    }
    
    public boolean isMatchPlugin(final UTPlugin utPlugin) {
        return true;
    }
}
