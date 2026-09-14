package com.alibaba.sdk.android.utils;

import android.util.Log;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class AlicloudTracker
{
    private c a;
    private String a;
    private String b;
    private Map<String, String> b;
    
    AlicloudTracker(final c a, final String a2, final String b) {
        this.b = (Map<String, String>)new HashMap();
        this.a = a;
        this.a = a2;
        this.b = b;
    }
    
    public void removeGlobalProperty(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s) && this.b.containsKey((Object)s)) {
            this.b.remove((Object)s);
        }
        else {
            Log.e("AlicloudTracker", "key is null or key is empty,please check it!");
        }
    }
    
    public void sendCustomHit(final String s, final long n, final Map<String, String> map) {
        try {
            if (this.a == null) {
                Log.e("AlicloudTracker", "dataTracker is null, can not sendCustomHit");
                return;
            }
            Object o;
            if ((o = map) == null) {
                o = new HashMap();
            }
            ((Map)o).putAll((Map)this.b);
            ((Map)o).put((Object)"sdkId", (Object)this.a);
            ((Map)o).put((Object)"sdkVersion", (Object)this.b);
            final c a = this.a;
            final StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("_");
            sb.append(s);
            a.sendCustomHit(sb.toString(), n, (Map<String, String>)o);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendCustomHit(final String s, final Map<String, String> map) {
        this.sendCustomHit(s, 0L, map);
    }
    
    public void setGlobalProperty(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s) && s2 != null) {
            if (this.b.containsKey((Object)s)) {
                this.b.remove((Object)s);
            }
            this.b.put((Object)s, (Object)s2);
        }
        else {
            Log.e("AlicloudTracker", "key is null or key is empty or value is null,please check it!");
        }
    }
}
