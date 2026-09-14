package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.log.d.i;

public enum f
{
    static String TAG;
    
    a(65501, 30, "alarmData", 5000);
    
    private static final f[] a;
    
    b(65502, 30, "counterData", 5000), 
    c(65133, 30, "counterData", 5000), 
    d(65503, 30, "statData", 5000);
    
    private int e;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean m;
    private String t;
    
    static {
        f.TAG = "EventType";
    }
    
    private f(final int e, final int h, final String t, final int k) {
        this.i = 25;
        this.j = 180;
        this.e = e;
        this.h = h;
        this.m = true;
        this.t = t;
        this.k = k;
    }
    
    public static f a(final int n) {
        final f[] values = values();
        for (int i = 0; i < values.length; ++i) {
            final f f = values[i];
            if (f != null && f.a() == n) {
                return f;
            }
        }
        return null;
    }
    
    public int a() {
        return this.e;
    }
    
    public String a() {
        return this.t;
    }
    
    public int b() {
        return this.h;
    }
    
    public void b(final int h) {
        final String tag = f.TAG;
        final String t = this.t;
        final StringBuilder sb = new StringBuilder();
        sb.append(h);
        sb.append("");
        com.alibaba.mtl.log.d.i.a(tag, new Object[] { "[setTriggerCount]", t, sb.toString() });
        this.h = h;
    }
    
    public void b(final boolean m) {
        this.m = m;
    }
    
    public int c() {
        return this.i;
    }
    
    public void c(final int k) {
        this.k = k;
    }
    
    public int d() {
        return this.j;
    }
    
    public int e() {
        return this.k;
    }
    
    public boolean isOpen() {
        return this.m;
    }
    
    public void setStatisticsInterval(final int n) {
        this.i = n;
        this.j = n;
    }
}
