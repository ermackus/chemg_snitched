package com.tencent.bugly.proguard;

import com.tencent.bugly.b;
import java.util.Iterator;
import java.util.List;
import android.os.Process;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;
import android.content.Context;

public final class u
{
    private static u a;
    private final p b;
    private final Context c;
    private Map<Integer, Long> d;
    private long e;
    private long f;
    private LinkedBlockingQueue<Runnable> g;
    private LinkedBlockingQueue<Runnable> h;
    private final Object i;
    private int j;
    
    private u(final Context c) {
        this.d = (Map<Integer, Long>)new HashMap();
        this.g = (LinkedBlockingQueue<Runnable>)new LinkedBlockingQueue();
        this.h = (LinkedBlockingQueue<Runnable>)new LinkedBlockingQueue();
        this.i = new Object();
        this.j = 0;
        this.c = c;
        this.b = p.a();
    }
    
    public static u a() {
        synchronized (u.class) {
            return u.a;
        }
    }
    
    public static u a(final Context context) {
        synchronized (u.class) {
            if (u.a == null) {
                u.a = new u(context);
            }
            return u.a;
        }
    }
    
    private void a(final Runnable runnable, final boolean b, final boolean b2, final long n) {
        if (runnable == null) {
            x.d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        x.c("[UploadManager] Add upload task (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
        if (b2) {
            if (runnable == null) {
                x.d("[UploadManager] Upload task should not be null", new Object[0]);
                return;
            }
            x.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
            final Thread a = z.a(runnable, "BUGLY_SYNC_UPLOAD");
            if (a == null) {
                x.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
                this.a(runnable, true);
                return;
            }
            try {
                a.join(n);
                return;
            }
            finally {
                final Throwable t;
                x.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", t.getMessage());
                this.a(runnable, true);
                this.c(0);
                return;
            }
        }
        this.a(runnable, b);
        this.c(0);
    }
    
    private boolean a(final Runnable runnable, final boolean b) {
        if (runnable == null) {
            x.a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            x.c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
            final Object i;
            monitorenter(i = this.i);
            Label_0069: {
                if (!b) {
                    break Label_0069;
                }
                try {
                    this.g.put((Object)runnable);
                    return true;
                    this.h.put((Object)runnable);
                }
                finally {
                    monitorexit(i);
                }
            }
        }
        finally {
            final Throwable t;
            x.e("[UploadManager] Failed to add upload task to queue: %s", t.getMessage());
            return false;
        }
    }
    
    private void c(int size) {
        final w a = w.a();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        final Object i = this.i;
        synchronized (i) {
            x.c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
            final int size2 = this.g.size();
            size = this.h.size();
            if (size2 == 0 && size == 0) {
                x.c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (a == null || !a.c()) {
                size = 0;
            }
            for (int j = 0; j < size2; ++j) {
                final Runnable runnable = (Runnable)this.g.peek();
                if (runnable == null) {
                    break;
                }
                try {
                    linkedBlockingQueue.put((Object)runnable);
                    this.g.poll();
                }
                finally {
                    final Throwable t;
                    x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", t.getMessage());
                }
            }
            for (int k = 0; k < size; ++k) {
                final Runnable runnable2 = (Runnable)this.h.peek();
                if (runnable2 == null) {
                    break;
                }
                try {
                    linkedBlockingQueue2.put((Object)runnable2);
                    this.h.poll();
                }
                finally {
                    final Throwable t2;
                    x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", t2.getMessage());
                }
            }
            monitorexit(i);
            if (size2 > 0) {
                x.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", size2, Process.myPid(), Process.myTid());
            }
            int l = 0;
            while (l < size2) {
                final Runnable runnable3 = (Runnable)linkedBlockingQueue.poll();
                if (runnable3 != null) {
                    synchronized (this.i) {
                        Label_0445: {
                            if (this.j >= 2 && a != null) {
                                a.a(runnable3);
                                monitorexit(i);
                            }
                            else {
                                monitorexit(i);
                                x.a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                                if (z.a((Runnable)new Runnable(this, runnable3) {
                                    private Runnable a;
                                    private u b;
                                    
                                    public final void run() {
                                        this.a.run();
                                        final Object a = this.b.i;
                                        synchronized (a) {
                                            --this.b.j;
                                        }
                                    }
                                }, "BUGLY_ASYNC_UPLOAD") != null) {
                                    synchronized (this.i) {
                                        ++this.j;
                                        break Label_0445;
                                    }
                                }
                                x.d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new Object[0]);
                                this.a(runnable3, true);
                            }
                        }
                        ++l;
                        continue;
                    }
                    break;
                }
                break;
            }
            if (size > 0) {
                x.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", size, Process.myPid(), Process.myTid());
            }
            if (a != null) {
                a.a((Runnable)new Runnable(this, size, linkedBlockingQueue2) {
                    private int a;
                    private LinkedBlockingQueue b;
                    
                    public final void run() {
                        for (int i = 0; i < this.a; ++i) {
                            final Runnable runnable = (Runnable)this.b.poll();
                            if (runnable == null) {
                                break;
                            }
                            runnable.run();
                        }
                    }
                });
            }
        }
    }
    
    public final long a(final int n) {
        monitorenter(this);
        final long n2 = 0L;
        Label_0184: {
            if (n < 0) {
                break Label_0184;
            }
            try {
                final Long n3 = (Long)this.d.get((Object)n);
                if (n3 != null) {
                    return n3;
                }
                final List<r> a = this.b.a(n);
                long e = n2;
                if (a != null) {
                    e = n2;
                    if (a.size() > 0) {
                        if (a.size() > 1) {
                            final Iterator iterator = a.iterator();
                            e = n2;
                            while (iterator.hasNext()) {
                                final r r = (r)iterator.next();
                                if (r.e > e) {
                                    e = r.e;
                                }
                            }
                            this.b.b(n);
                        }
                        else {
                            try {
                                final long e2 = ((r)a.get(0)).e;
                            }
                            finally {
                                final Throwable t;
                                x.a(t);
                                e = n2;
                            }
                        }
                    }
                }
                return e;
                x.e("[UploadManager] Unknown upload ID: %d", n);
                e = n2;
                return e;
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    public final long a(final boolean b) {
        final long b2 = z.b();
        int n;
        if (b) {
            n = 5;
        }
        else {
            n = 3;
        }
        final List<r> a = this.b.a(n);
        long n5;
        if (a != null && a.size() > 0) {
            long n3;
            final long n2 = n3 = 0L;
            long n4;
            try {
                final r r = (r)a.get(0);
                n3 = n2;
                if (r.e >= b2) {
                    n3 = n2;
                    final long b3 = z.b(r.g);
                    if (n == 3) {
                        n3 = b3;
                        this.e = b3;
                    }
                    else {
                        n3 = b3;
                        this.f = b3;
                    }
                    n3 = b3;
                    a.remove((Object)r);
                }
            }
            finally {
                final Throwable t;
                x.a(t);
                n4 = n3;
            }
            n5 = n4;
            if (a.size() > 0) {
                this.b.a(a);
                n5 = n4;
            }
        }
        else {
            long n6;
            if (b) {
                n6 = this.f;
            }
            else {
                n6 = this.e;
            }
            n5 = n6;
        }
        x.c("[UploadManager] Local network consume: %d KB", n5 / 1024L);
        return n5;
    }
    
    public final void a(final int b, final long e) {
        monitorenter(this);
        Label_0120: {
            if (b < 0) {
                break Label_0120;
            }
            try {
                this.d.put((Object)b, (Object)e);
                final r r = new r();
                r.b = b;
                r.e = e;
                r.c = "";
                r.d = "";
                r.g = new byte[0];
                this.b.b(b);
                this.b.a(r);
                x.c("[UploadManager] Uploading(ID:%d) time: %s", b, z.a(e));
                return;
                x.e("[UploadManager] Unknown uploading ID: %d", b);
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    public final void a(final int n, final am am, final String s, final String s2, final t t, final long n2, final boolean b) {
        final int g = am.g;
        final byte[] a = com.tencent.bugly.proguard.a.a(am);
        try {
            try {
                this.a((Runnable)new v(this.c, n, g, a, s, s2, t, true, b), true, true, n2);
                return;
            }
            finally {}
        }
        finally {}
        final Throwable t2;
        if (!x.a(t2)) {
            t2.printStackTrace();
        }
    }
    
    public final void a(final int n, final am am, final String s, final String s2, final t t, final boolean b) {
        final int g = am.g;
        final byte[] a = com.tencent.bugly.proguard.a.a(am);
        try {
            try {
                this.a((Runnable)new v(this.c, n, g, a, s, s2, t, 0, 0, false, null), b, false, 0L);
                return;
            }
            finally {}
        }
        finally {}
        final Throwable t2;
        if (!x.a(t2)) {
            t2.printStackTrace();
        }
    }
    
    protected final void a(final long n, final boolean b) {
        monitorenter(this);
        int b2;
        if (b) {
            b2 = 5;
        }
        else {
            b2 = 3;
        }
        try {
            final r r = new r();
            r.b = b2;
            r.e = z.b();
            r.c = "";
            r.d = "";
            r.g = z.c(n);
            this.b.b(b2);
            this.b.a(r);
            if (b) {
                this.f = n;
            }
            else {
                this.e = n;
            }
            x.c("[UploadManager] Network total consume: %d KB", n / 1024L);
        }
        finally {
            monitorexit(this);
        }
    }
    
    public final boolean b(final int n) {
        if (com.tencent.bugly.b.c) {
            x.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        final long n2 = System.currentTimeMillis() - this.a(n);
        x.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", n2 / 1000L, n);
        if (n2 < 30000L) {
            x.a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
            return false;
        }
        return true;
    }
}
