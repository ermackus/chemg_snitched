package com.tencent.open.log;

import android.os.Message;
import java.io.Writer;
import java.io.IOException;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.File;
import java.io.FileWriter;
import android.os.Handler$Callback;

public class a extends Tracer implements Handler$Callback
{
    private b a;
    private FileWriter b;
    private FileWriter c;
    private File d;
    private File e;
    private char[] f;
    private volatile f g;
    private volatile f h;
    private volatile f i;
    private volatile f j;
    private volatile boolean k;
    private HandlerThread l;
    private Handler m;
    
    public a(final int n, final boolean b, final g g, final b b2) {
        super(n, b, g);
        this.k = false;
        this.a(b2);
        this.g = new f();
        this.h = new f();
        this.i = this.g;
        this.j = this.h;
        this.f = new char[b2.d()];
        final HandlerThread l = new HandlerThread(b2.c(), b2.f());
        this.l = l;
        if (l != null) {
            l.start();
        }
        if (this.l.isAlive() && this.l.getLooper() != null) {
            this.m = new Handler(this.l.getLooper(), (Handler$Callback)this);
        }
    }
    
    public a(final b b) {
        this(com.tencent.open.log.c.b, true, com.tencent.open.log.g.a, b);
    }
    
    private void a(final String s) {
        this.i.a(s);
        if (this.i.a() >= this.c().d()) {
            this.a();
        }
    }
    
    private void f() {
        if (Thread.currentThread() != this.l) {
            return;
        }
        if (this.k) {
            return;
        }
        this.k = true;
        this.j();
        while (true) {
            try {
                try {
                    this.j.a(this.g(), this.f);
                    this.j.b();
                }
                finally {}
            }
            catch (final IOException ex) {
                SLog.e("FileTracer", "flushBuffer exception", (Throwable)ex);
                continue;
            }
            break;
        }
        this.k = false;
        return;
        this.j.b();
    }
    
    private Writer[] g() {
        final File[] a = this.c().a();
        if (a != null && a.length >= 2) {
            final File d = a[0];
            if ((d != null && !d.equals((Object)this.d)) || (this.b == null && d != null)) {
                this.d = d;
                this.h();
                try {
                    this.b = new FileWriter(this.d, true);
                }
                catch (final IOException ex) {
                    this.b = null;
                    SLog.e("openSDK_LOG", "-->obtainFileWriter() old log file permission denied");
                }
            }
            final File e = a[1];
            if ((e != null && !e.equals((Object)this.e)) || (this.c == null && e != null)) {
                this.e = e;
                this.i();
                try {
                    this.c = new FileWriter(this.e, true);
                }
                catch (final IOException ex2) {
                    this.c = null;
                    SLog.e("openSDK_LOG", "-->obtainFileWriter() app specific file permission denied");
                }
            }
        }
        return new Writer[] { (Writer)this.b, (Writer)this.c };
    }
    
    private void h() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        }
        catch (final IOException ex) {
            SLog.e("openSDK_LOG", "-->closeFileWriter() exception:", (Throwable)ex);
        }
    }
    
    private void i() {
        try {
            if (this.c != null) {
                this.c.flush();
                this.c.close();
            }
        }
        catch (final IOException ex) {
            SLog.e("openSDK_LOG", "-->closeAppSpecificFileWriter() exception:", (Throwable)ex);
        }
    }
    
    private void j() {
        synchronized (this) {
            if (this.i == this.g) {
                this.i = this.h;
                this.j = this.g;
            }
            else {
                this.i = this.g;
                this.j = this.h;
            }
        }
    }
    
    public void a() {
        if (this.m.hasMessages(1024)) {
            this.m.removeMessages(1024);
        }
        this.m.sendEmptyMessage(1024);
    }
    
    public void a(final b a) {
        this.a = a;
    }
    
    public void b() {
        this.h();
        this.i();
        this.l.quit();
    }
    
    public b c() {
        return this.a;
    }
    
    protected void doTrace(final int n, final Thread thread, final long n2, final String s, final String s2, final Throwable t) {
        this.a(this.e().a(n, thread, n2, s, s2, t));
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1024) {
            this.f();
        }
        return true;
    }
}
