package com.tencent.open.log;

import java.util.Calendar;
import android.text.TextUtils;
import com.tencent.open.utils.k;
import java.io.File;
import java.text.SimpleDateFormat;

public class b
{
    private static SimpleDateFormat a;
    private String b;
    private int c;
    private int d;
    private int e;
    private long f;
    private File g;
    private int h;
    private String i;
    private long j;
    
    static {
        b.a = d.d.a("yy.MM.dd.HH");
    }
    
    public b(final File file, final int n, final int n2, final int n3, final String s, final long n4, final int n5, final String s2, final long n6) {
        this.b = "Tracer.File";
        this.c = Integer.MAX_VALUE;
        this.d = Integer.MAX_VALUE;
        this.e = 4096;
        this.f = 10000L;
        this.h = 10;
        this.i = ".log";
        this.j = Long.MAX_VALUE;
        this.a(file);
        this.b(n);
        this.a(n2);
        this.c(n3);
        this.a(s);
        this.a(n4);
        this.d(n5);
        this.b(s2);
        this.b(n6);
    }
    
    private String c(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("com.tencent.mobileqq_connectSdk.");
        sb.append(s);
        sb.append(".log");
        return sb.toString();
    }
    
    private File[] c(final long n) {
        File b = this.b();
        final String c = this.c(this.d(n));
        try {
            b = new File(b, c);
        }
        finally {
            final Throwable t;
            SLog.e("openSDK_LOG", "getWorkFile,get old sdcard file exception:", t);
        }
        final String b2 = k.b();
        final File file = null;
        if (TextUtils.isEmpty((CharSequence)b2)) {
            final File file2 = file;
            if (b2 == null) {
                return new File[] { b, file2 };
            }
        }
        File file2;
        try {
            final File file3 = new File(b2, com.tencent.open.log.c.o);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            file2 = new File(file3, c);
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG", "getWorkFile,get app specific file exception:", (Throwable)ex);
            file2 = file;
        }
        return new File[] { b, file2 };
    }
    
    private String d(final long timeInMillis) {
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timeInMillis);
        return new SimpleDateFormat("yy.MM.dd.HH").format(instance.getTime());
    }
    
    public void a(final int c) {
        this.c = c;
    }
    
    public void a(final long f) {
        this.f = f;
    }
    
    public void a(final File g) {
        this.g = g;
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public File[] a() {
        return this.c(System.currentTimeMillis());
    }
    
    public File b() {
        final File e = this.e();
        if (e != null) {
            e.mkdirs();
        }
        return e;
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public void b(final long j) {
        this.j = j;
    }
    
    public void b(final String i) {
        this.i = i;
    }
    
    public String c() {
        return this.b;
    }
    
    public void c(final int e) {
        this.e = e;
    }
    
    public int d() {
        return this.e;
    }
    
    public void d(final int h) {
        this.h = h;
    }
    
    public File e() {
        return this.g;
    }
    
    public int f() {
        return this.h;
    }
}
