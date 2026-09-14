package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import android.content.Context;
import java.util.concurrent.ThreadFactory;

public final class l implements ab
{
    public static l b;
    public static final ThreadFactory i;
    public Context a;
    public ThreadPoolExecutor c;
    public b d;
    public long e;
    public long f;
    public long g;
    public int h;
    
    static {
        i = (ThreadFactory)new n();
    }
    
    public l(final Context a) {
        this.a = a;
        this.d = com.alipay.android.phone.mrpc.core.b.a("android");
        final ThreadPoolExecutor c = new ThreadPoolExecutor(10, 11, 3L, TimeUnit.SECONDS, (BlockingQueue)new ArrayBlockingQueue(20), l.i, (RejectedExecutionHandler)new ThreadPoolExecutor$CallerRunsPolicy());
        this.c = c;
        while (true) {
            try {
                c.allowCoreThreadTimeOut(true);
                CookieSyncManager.createInstance(this.a);
                CookieManager.getInstance().setAcceptCookie(true);
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public static final l a(final Context context) {
        final l b = l.b;
        if (b != null) {
            return b;
        }
        return b(context);
    }
    
    public static final l b(final Context context) {
        synchronized (l.class) {
            final l b = l.b;
            if (b != null) {
                return b;
            }
            return l.b = new l(context);
        }
    }
    
    public final b a() {
        return this.d;
    }
    
    public final Future<u> a(final t t) {
        if (s.a(this.a)) {
            final StringBuilder sb = new StringBuilder("HttpManager");
            sb.append(this.hashCode());
            sb.append(": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times");
            final String string = sb.toString();
            final int activeCount = this.c.getActiveCount();
            final long completedTaskCount = this.c.getCompletedTaskCount();
            final long taskCount = this.c.getTaskCount();
            final long g = this.g;
            long n = 0L;
            long n2;
            if (g == 0L) {
                n2 = 0L;
            }
            else {
                n2 = this.e * 1000L / g >> 10;
            }
            final int h = this.h;
            if (h != 0) {
                n = this.f / h;
            }
            String.format(string, new Object[] { activeCount, completedTaskCount, taskCount, n2, n, this.e, this.f, this.g, this.h });
        }
        final q q = new q(this, (o)t);
        final m m = new m(this, (Callable)q, q);
        this.c.execute((Runnable)m);
        return (Future<u>)m;
    }
    
    public final void a(final long n) {
        this.e += n;
    }
    
    public final void b(final long n) {
        this.f += n;
        ++this.h;
    }
    
    public final void c(final long n) {
        this.g += n;
    }
}
