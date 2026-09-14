package com.ut.mini.core.appstatus;

import java.util.Iterator;
import android.os.Bundle;
import android.app.Activity;
import com.alibaba.mtl.log.d.s;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import android.app.Application$ActivityLifecycleCallbacks;

public class UTMCAppStatusMonitor implements Application$ActivityLifecycleCallbacks
{
    private static UTMCAppStatusMonitor a;
    private int K;
    private boolean T;
    private ScheduledFuture<?> a;
    private Object e;
    private Object f;
    private List<UTMCAppStatusCallbacks> m;
    
    private UTMCAppStatusMonitor() {
        this.K = 0;
        this.T = false;
        this.a = null;
        this.e = new Object();
        this.m = (List<UTMCAppStatusCallbacks>)new LinkedList();
        this.f = new Object();
    }
    
    private void J() {
        final Object e = this.e;
        synchronized (e) {
            s.a().f(11);
        }
    }
    
    public static UTMCAppStatusMonitor getInstance() {
        synchronized (UTMCAppStatusMonitor.class) {
            if (UTMCAppStatusMonitor.a == null) {
                UTMCAppStatusMonitor.a = new UTMCAppStatusMonitor();
            }
            return UTMCAppStatusMonitor.a;
        }
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        final Object f = this.f;
        synchronized (f) {
            final Iterator iterator = this.m.iterator();
            while (iterator.hasNext()) {
                ((UTMCAppStatusCallbacks)iterator.next()).onActivityCreated(activity, bundle);
            }
        }
    }
    
    public void onActivityDestroyed(final Activity activity) {
        final Object f = this.f;
        synchronized (f) {
            final Iterator iterator = this.m.iterator();
            while (iterator.hasNext()) {
                ((UTMCAppStatusCallbacks)iterator.next()).onActivityDestroyed(activity);
            }
        }
    }
    
    public void onActivityPaused(final Activity activity) {
        final Object f = this.f;
        synchronized (f) {
            final Iterator iterator = this.m.iterator();
            while (iterator.hasNext()) {
                ((UTMCAppStatusCallbacks)iterator.next()).onActivityPaused(activity);
            }
        }
    }
    
    public void onActivityResumed(final Activity activity) {
        final Object f = this.f;
        synchronized (f) {
            final Iterator iterator = this.m.iterator();
            while (iterator.hasNext()) {
                ((UTMCAppStatusCallbacks)iterator.next()).onActivityResumed(activity);
            }
        }
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        final Object f = this.f;
        synchronized (f) {
            final Iterator iterator = this.m.iterator();
            while (iterator.hasNext()) {
                ((UTMCAppStatusCallbacks)iterator.next()).onActivitySaveInstanceState(activity, bundle);
            }
        }
    }
    
    public void onActivityStarted(final Activity activity) {
        this.J();
        ++this.K;
        if (!this.T) {
            final Object f = this.f;
            synchronized (f) {
                final Iterator iterator = this.m.iterator();
                while (iterator.hasNext()) {
                    ((UTMCAppStatusCallbacks)iterator.next()).onSwitchForeground();
                }
            }
        }
        this.T = true;
    }
    
    public void onActivityStopped(final Activity activity) {
        final int k = this.K - 1;
        this.K = k;
        if (k == 0) {
            this.J();
            s.a().a(11, (Runnable)new a(), 1000L);
        }
    }
    
    public void registerAppStatusCallbacks(final UTMCAppStatusCallbacks utmcAppStatusCallbacks) {
        if (utmcAppStatusCallbacks != null) {
            final Object f = this.f;
            synchronized (f) {
                this.m.add((Object)utmcAppStatusCallbacks);
            }
        }
    }
    
    public void unregisterAppStatusCallbacks(final UTMCAppStatusCallbacks utmcAppStatusCallbacks) {
        if (utmcAppStatusCallbacks != null) {
            final Object f = this.f;
            synchronized (f) {
                this.m.remove((Object)utmcAppStatusCallbacks);
            }
        }
    }
    
    private class a implements Runnable
    {
        final UTMCAppStatusMonitor b;
        
        private a(final UTMCAppStatusMonitor b) {
            this.b = b;
        }
        
        public void run() {
            this.b.T = false;
            final Object a = this.b.f;
            synchronized (a) {
                final Iterator iterator = this.b.m.iterator();
                while (iterator.hasNext()) {
                    ((UTMCAppStatusCallbacks)iterator.next()).onSwitchBackground();
                }
            }
        }
    }
}
