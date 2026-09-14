package com.alibaba.mtl.appmonitor;

import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.mtl.appmonitor.a.e;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.log.d.s;
import com.alibaba.mtl.log.d.i;
import java.util.Map;

class c implements Runnable
{
    private static Map<Integer, c> f;
    private static boolean j;
    private int d;
    private int e;
    private long startTime;
    
    private c(final int e, final int d) {
        this.d = 180000;
        this.e = e;
        this.d = d;
        this.startTime = System.currentTimeMillis();
    }
    
    private static int a(final int n) {
        if (n == 65133) {
            return 11;
        }
        switch (n) {
            default: {
                return 0;
            }
            case 65503: {
                return 10;
            }
            case 65502: {
                return 9;
            }
            case 65501: {
                return 6;
            }
        }
    }
    
    static void a(final int n, int d) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[setStatisticsInterval] eventId");
        sb.append(n);
        sb.append(" statisticsInterval:");
        sb.append(d);
        i.a("CommitTask", new Object[] { sb.toString() });
        final Map<Integer, c> f = c.f;
        synchronized (f) {
            final c c = (c)com.alibaba.mtl.appmonitor.c.f.get((Object)n);
            if (c == null) {
                if (d > 0) {
                    final c c2 = new c(n, d * 1000);
                    com.alibaba.mtl.appmonitor.c.f.put((Object)n, (Object)c2);
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("post next eventId");
                    sb2.append(n);
                    sb2.append(": uploadTask.interval ");
                    sb2.append(c2.d);
                    i.a("CommitTask", new Object[] { sb2.toString() });
                    s.a().a(a(n), (Runnable)c2, c2.d);
                }
            }
            else if (d > 0) {
                final int d2 = c.d;
                d *= 1000;
                if (d2 != d) {
                    s.a().f(a(n));
                    c.d = d;
                    final long currentTimeMillis = System.currentTimeMillis();
                    long n2;
                    if ((n2 = c.d - (currentTimeMillis - c.startTime)) < 0L) {
                        n2 = 0L;
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append((Object)c);
                    sb3.append("post next eventId");
                    sb3.append(n);
                    sb3.append(" next:");
                    sb3.append(n2);
                    sb3.append("  uploadTask.interval: ");
                    sb3.append(c.d);
                    i.a("CommitTask", new Object[] { sb3.toString() });
                    s.a().a(a(n), (Runnable)c, n2);
                    c.startTime = currentTimeMillis;
                }
            }
            else {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("uploadTasks.size:");
                sb4.append(com.alibaba.mtl.appmonitor.c.f.size());
                i.a("CommitTask", new Object[] { sb4.toString() });
                com.alibaba.mtl.appmonitor.c.f.remove((Object)n);
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("uploadTasks.size:");
                sb5.append(com.alibaba.mtl.appmonitor.c.f.size());
                i.a("CommitTask", new Object[] { sb5.toString() });
            }
        }
    }
    
    static void d() {
        final f[] values = com.alibaba.mtl.appmonitor.a.f.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            e.a().a(values[i].a());
        }
    }
    
    static void destroy() {
        final f[] values = com.alibaba.mtl.appmonitor.a.f.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            s.a().f(a(values[i].a()));
        }
        c.j = false;
        c.f = null;
    }
    
    static void init() {
        if (!c.j) {
            int i = 0;
            com.alibaba.mtl.log.d.i.a("CommitTask", new Object[] { "init StatisticsAlarmEvent" });
            c.f = (Map<Integer, c>)new ConcurrentHashMap();
            for (f[] values = com.alibaba.mtl.appmonitor.a.f.values(); i < values.length; ++i) {
                final f f = values[i];
                if (f.isOpen()) {
                    final int a = f.a();
                    final c c = new c(a, f.c() * 1000);
                    com.alibaba.mtl.appmonitor.c.f.put((Object)a, (Object)c);
                    s.a().a(a(a), (Runnable)c, c.d);
                }
            }
            c.j = true;
        }
    }
    
    public void run() {
        i.a("CommitTask", new Object[] { "check&commit event:", this.e });
        com.alibaba.mtl.appmonitor.a.e.a().a(this.e);
        if (c.f.containsValue((Object)this)) {
            this.startTime = System.currentTimeMillis();
            final StringBuilder sb = new StringBuilder();
            sb.append("next:");
            sb.append(this.e);
            i.a("CommitTask", new Object[] { sb.toString() });
            s.a().a(a(this.e), (Runnable)this, this.d);
        }
    }
}
