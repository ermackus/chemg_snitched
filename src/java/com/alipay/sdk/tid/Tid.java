package com.alipay.sdk.tid;

import android.text.TextUtils;

public class Tid
{
    public final String key;
    public final String tid;
    public final long time;
    
    public Tid(final String tid, final String key, final long time) {
        this.tid = tid;
        this.key = key;
        this.time = time;
    }
    
    public static boolean isEmpty(final Tid tid) {
        return tid == null || TextUtils.isEmpty((CharSequence)tid.tid);
    }
    
    public String getTid() {
        return this.tid;
    }
    
    public String getTidSeed() {
        return this.key;
    }
    
    public long getTimestamp() {
        return this.time;
    }
}
