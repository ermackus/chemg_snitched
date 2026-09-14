package com.alibaba.sdk.android.man.crashreporter.handler.b;

import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import android.os.Looper;
import android.os.Handler;

public class b extends Thread
{
    private static final a a;
    private static final b a;
    private static final int y = 5000;
    private volatile int A;
    private final Handler a;
    private final Runnable a;
    private a b;
    private b b;
    private boolean b;
    private String s;
    private final int z;
    
    static {
        a = (a)new b$1();
        a = (b)new b$2();
    }
    
    public b() {
        this(5000);
    }
    
    public b(final int z) {
        this.b = com.alibaba.sdk.android.man.crashreporter.handler.b.b.a;
        this.b = com.alibaba.sdk.android.man.crashreporter.handler.b.b.a;
        this.a = new Handler(Looper.getMainLooper());
        this.s = "";
        this.b = false;
        this.A = 0;
        this.a = (Runnable)new Runnable() {
            final b a;
            
            public void run() {
                final b a = this.a;
                a.A = (a.A + 1) % 10;
            }
        };
        this.z = z;
    }
    
    public b a() {
        this.s = null;
        return this;
    }
    
    public b a(final a b) {
        if (b == null) {
            this.b = b.a;
        }
        else {
            this.b = b;
        }
        return this;
    }
    
    public b a(final b b) {
        if (b == null) {
            this.b = b.a;
        }
        else {
            this.b = b;
        }
        return this;
    }
    
    public b a(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        this.s = s2;
        return this;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public void run() {
        this.setName("ANR-WatchDog");
        int n = 1;
        while (!this.isInterrupted()) {
            final int a = this.A;
            this.a.post(this.a);
            try {
                final int n2 = this.z / 1000;
                int n3 = 1;
                int n4;
                while (true) {
                    n4 = n;
                    if (n3 > n2) {
                        break;
                    }
                    Thread.sleep(1000L);
                    if (this.A != a) {
                        this.b.a(String.valueOf(n3), 1);
                        n4 = n + 1;
                        if (n4 > 3) {
                            Thread.sleep(60000L);
                            n4 = 1;
                            break;
                        }
                        Thread.sleep((long)((n2 - n3) * 1000));
                        break;
                    }
                    else {
                        ++n3;
                    }
                }
                n = n4;
                if (this.A != a) {
                    continue;
                }
                n = n4;
                if (MotuCrashReporter.getInstance().getCrashReporterState() == -1) {
                    this.b.c(this.s);
                    return;
                }
                continue;
            }
            catch (final InterruptedException ex) {
                this.b.a(ex);
            }
            break;
        }
    }
    
    public interface a
    {
        void a(final String p0, final int p1);
        
        void c(final String p0);
    }
    
    public interface b
    {
        void a(final InterruptedException p0);
    }
}
