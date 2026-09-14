package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.log.d.i;
import android.content.Context;
import com.alibaba.mtl.log.a;
import java.util.HashMap;
import java.util.Map;

public class SdkMeta
{
    public static final String SDK_VERSION = "2.6.4.10_for_bc";
    private static final Map<String, String> d;
    
    static {
        (d = (Map)new HashMap()).put((Object)"sdk-version", (Object)"2.6.4.10_for_bc");
    }
    
    public static Map<String, String> getSDKMetaData() {
        a.getContext();
        if (!SdkMeta.d.containsKey((Object)"sdk-version")) {
            SdkMeta.d.put((Object)"sdk-version", (Object)"2.6.4.10_for_bc");
        }
        return SdkMeta.d;
    }
    
    public static String getString(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        final int n = 0;
        int n2 = 0;
        try {
            context.getResources().getIdentifier(s, "string", context.getPackageName());
        }
        finally {
            final Throwable t;
            i.a("SdkMeta", "getString Id error", t);
            n2 = n;
        }
        if (n2 != 0) {
            return context.getString(n2);
        }
        return null;
    }
    
    public static void setExtra(final Map<String, String> map) {
        if (map != null) {
            SdkMeta.d.putAll((Map)map);
        }
    }
}
