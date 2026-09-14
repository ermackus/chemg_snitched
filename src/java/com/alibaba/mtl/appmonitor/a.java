package com.alibaba.mtl.appmonitor;

import android.os.Bundle;
import android.app.Activity;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.d.j;
import android.app.Application$ActivityLifecycleCallbacks;
import android.os.Build$VERSION;
import com.alibaba.mtl.log.d.s;
import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.b;
import android.content.Context;
import android.app.Application;

class a implements Runnable
{
    private static boolean j;
    private static boolean l;
    private Application b;
    private boolean k;
    
    public a(final Application b) {
        this.b = b;
        this.k = true;
    }
    
    private static boolean a(final Context context) {
        final String a = b.a(context);
        i.a("BackgroundTrigger", new Object[] { "[checkRuningProcess]:", a });
        return !TextUtils.isEmpty((CharSequence)a) && a.indexOf(":") != -1;
    }
    
    public static void init(final Application application) {
        if (!a.j) {
            i.a("BackgroundTrigger", new Object[] { "init BackgroundTrigger" });
            a.l = a(application.getApplicationContext());
            final a a = new a(application);
            if (com.alibaba.mtl.appmonitor.a.l) {
                s.a().a(4, (Runnable)a, 60000L);
            }
            else if (Build$VERSION.SDK_INT >= 14) {
                ((Runnable)a).getClass();
                application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)a.new a((Runnable)a));
            }
            com.alibaba.mtl.appmonitor.a.j = true;
        }
    }
    
    public void run() {
        int i = 0;
        final int n = 0;
        com.alibaba.mtl.log.d.i.a("BackgroundTrigger", new Object[] { "[bg check]" });
        final boolean b = com.alibaba.mtl.log.d.b.b(this.b.getApplicationContext());
        if (this.k != b) {
            this.k = b;
            if (b) {
                com.alibaba.mtl.appmonitor.d.j.a().j();
                final f[] values = f.values();
                for (int length = values.length, j = n; j < length; ++j) {
                    final f f = values[j];
                    AppMonitorDelegate.setStatisticsInterval(f, f.c());
                }
                a.l();
            }
            else {
                for (f[] values2 = f.values(); i < values2.length; ++i) {
                    final f f2 = values2[i];
                    AppMonitorDelegate.setStatisticsInterval(f2, f2.d());
                }
                AppMonitorDelegate.triggerUpload();
                a.k();
            }
        }
        if (a.l) {
            s.a().a(4, (Runnable)this, 60000L);
        }
    }
    
    class a implements Application$ActivityLifecycleCallbacks
    {
        final com.alibaba.mtl.appmonitor.a a;
        private Runnable a;
        
        a(final com.alibaba.mtl.appmonitor.a a, final Runnable a2) {
            this.a = a;
            this.a = a2;
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
        }
        
        public void onActivityResumed(final Activity activity) {
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            s.a().f(4);
            s.a().a(4, this.a, 60000L);
        }
        
        public void onActivityStopped(final Activity activity) {
            s.a().f(4);
            s.a().a(4, this.a, 60000L);
        }
    }
}
