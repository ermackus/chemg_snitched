package com.tencent.bugly.proguard;

import android.os.Looper;
import android.os.SystemClock;
import android.os.Handler;

public final class aa implements Runnable
{
    private final Handler a;
    private final String b;
    private long c;
    private final long d;
    private boolean e;
    private long f;
    
    aa(final Handler a, final String b, final long n) {
        this.a = a;
        this.b = b;
        this.c = n;
        this.d = n;
        this.e = true;
    }
    
    public final void a() {
        if (!this.e) {
            return;
        }
        this.e = false;
        this.f = SystemClock.uptimeMillis();
        this.a.post((Runnable)this);
    }
    
    public final void a(final long n) {
        this.c = Long.MAX_VALUE;
    }
    
    public final boolean b() {
        return !this.e && SystemClock.uptimeMillis() > this.f + this.c;
    }
    
    public final int c() {
        if (this.e) {
            return 0;
        }
        if (SystemClock.uptimeMillis() - this.f < this.c) {
            return 1;
        }
        return 3;
    }
    
    public final String d() {
        return this.b;
    }
    
    public final Looper e() {
        return this.a.getLooper();
    }
    
    public final void run() {
        this.e = true;
        this.c = this.d;
    }
}
