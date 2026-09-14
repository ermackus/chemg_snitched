package com.alibaba.sdk.android.utils.crashdefend;

import android.util.Log;

public class c implements Cloneable
{
    public int a;
    public long a;
    public SDKMessageCallback a;
    public String a;
    public int b;
    public long b;
    public String b;
    public int c;
    public int crashCount;
    public volatile boolean d;
    
    public c() {
        this.c = 0;
        this.d = false;
        this.a = null;
    }
    
    public Object clone() {
        c c;
        try {
            c = (c)super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            Log.e("CrashSDK", "clone fail:", (Throwable)ex);
            c = null;
        }
        return c;
    }
}
