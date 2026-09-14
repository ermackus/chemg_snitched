package com.tencent.bugly.proguard;

import android.os.SystemClock;
import android.os.Looper;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

public final class ab extends Thread
{
    private boolean a;
    private boolean b;
    private List<aa> c;
    private List<ac> d;
    private ArrayList<aa> e;
    
    public ab() {
        this.a = false;
        this.b = false;
        this.c = (List<aa>)new ArrayList();
        this.d = (List<ac>)new ArrayList();
        this.e = (ArrayList<aa>)new ArrayList();
    }
    
    private void a(final Handler handler, final long n) {
        if (handler == null) {
            x.e("addThread handler should not be null", new Object[0]);
            return;
        }
        final String name = handler.getLooper().getThread().getName();
        int i = 0;
        Label_0102: {
            try {
                Block_4: {
                    while (i < this.c.size()) {
                        if (((aa)this.c.get(i)).d().equals((Object)handler.getLooper().getThread().getName())) {
                            break Block_4;
                        }
                        ++i;
                    }
                    break Label_0102;
                }
                x.e("addThread fail ,this thread has been added in monitor queue", new Object[0]);
                return;
            }
            catch (final Exception ex) {
                x.b((Throwable)ex);
            }
        }
        this.c.add((Object)new aa(handler, name, 5000L));
    }
    
    private int e() {
        int n = 0;
        int max = 0;
        int n2;
        while (true) {
            n2 = max;
            try {
                if (n < this.c.size()) {
                    max = Math.max(n2, ((aa)this.c.get(n)).c());
                    ++n;
                    continue;
                }
            }
            catch (final Exception ex) {
                x.b((Throwable)ex);
            }
            break;
        }
        return n2;
    }
    
    public final void a() {
        this.a(new Handler(Looper.getMainLooper()), 5000L);
    }
    
    public final void a(final ac ac) {
        if (this.d.contains((Object)ac)) {
            x.c("addThreadMonitorListeners fail ,this threadMonitorListener has been added in monitor queue", new Object[0]);
            return;
        }
        this.d.add((Object)ac);
    }
    
    public final void a(final boolean b) {
        this.b = true;
    }
    
    public final void b() {
        int i = 0;
        try {
            while (i < this.c.size()) {
                if (((aa)this.c.get(i)).d().equals((Object)Looper.getMainLooper().getThread().getName())) {
                    x.c("remove handler::%s", this.c.get(i));
                    this.c.remove(i);
                }
                ++i;
            }
        }
        catch (final Exception ex) {
            x.b((Throwable)ex);
        }
    }
    
    public final void b(final ac ac) {
        this.d.remove((Object)ac);
    }
    
    public final boolean c() {
        this.a = true;
        if (!this.isAlive()) {
            return false;
        }
        try {
            this.interrupt();
        }
        catch (final Exception ex) {
            x.b((Throwable)ex);
        }
        return true;
    }
    
    public final boolean d() {
        final boolean alive = this.isAlive();
        boolean b = false;
        if (alive) {
            return false;
        }
        try {
            this.start();
            b = true;
        }
        catch (final Exception ex) {
            x.b((Throwable)ex);
        }
        return b;
    }
    
    public final void run() {
        while (!this.a) {
            int i = 0;
            try {
                while (i < this.c.size()) {
                    ((aa)this.c.get(i)).a();
                    ++i;
                }
                for (long uptimeMillis = SystemClock.uptimeMillis(), n = 2000L; n > 0L && !this.isInterrupted(); n = 2000L - (SystemClock.uptimeMillis() - uptimeMillis)) {
                    sleep(n);
                }
                final int e = this.e();
                if (e == 0) {
                    continue;
                }
                if (e == 1) {
                    continue;
                }
                this.e.clear();
                for (int j = 0; j < this.c.size(); ++j) {
                    final aa aa = (aa)this.c.get(j);
                    if (aa.b()) {
                        this.e.add((Object)aa);
                        aa.a(Long.MAX_VALUE);
                    }
                }
                int n2 = 0;
                while (!this.b) {
                    x.c("do not enable anr continue check", new Object[0]);
                    sleep(2000L);
                    if (++n2 == 15) {
                        this.e.clear();
                        break;
                    }
                }
                for (int k = 0; k < this.e.size(); ++k) {
                    final aa aa2 = (aa)this.e.get(k);
                    for (int l = 0; l < this.d.size(); ++l) {
                        x.e("main thread blocked,now begin to upload anr stack", new Object[0]);
                        ((ac)this.d.get(l)).a(aa2);
                        this.b = false;
                    }
                }
                continue;
            }
            catch (final OutOfMemoryError outOfMemoryError) {
                x.b((Throwable)outOfMemoryError);
                continue;
            }
            catch (final Exception ex) {
                x.b((Throwable)ex);
                continue;
            }
            break;
        }
    }
}
