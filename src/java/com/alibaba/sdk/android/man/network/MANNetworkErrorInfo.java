package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.ToolKit;
import java.util.HashMap;

public class MANNetworkErrorInfo
{
    private final HashMap<String, String> properties;
    
    protected MANNetworkErrorInfo(final HashMap<String, String> properties) {
        this.properties = properties;
    }
    
    public HashMap<String, String> getProperties() {
        return this.properties;
    }
    
    public MANNetworkErrorInfo withExtraInfo(final String s, final String s2) {
        if (!ToolKit.isNullOrEmpty(s) && !ToolKit.isNullOrEmpty(s2)) {
            this.properties.put((Object)s, (Object)s2);
        }
        return this;
    }
}
