package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.proguard.z;
import android.os.Handler;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.crashreport.common.info.b;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

public class BuglyBroadcastReceiver extends BroadcastReceiver
{
    private static BuglyBroadcastReceiver d;
    private IntentFilter a;
    private Context b;
    private String c;
    private boolean e;
    
    public BuglyBroadcastReceiver() {
        this.e = true;
        this.a = new IntentFilter();
    }
    
    private boolean a(final Context context, final Intent intent) {
        monitorenter(this);
        if (context != null && intent != null) {
            try {
                if (intent.getAction().equals((Object)"android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (this.e) {
                        this.e = false;
                        return true;
                    }
                    final String b = com.tencent.bugly.crashreport.common.info.b.b(this.b);
                    final StringBuilder sb = new StringBuilder("is Connect BC ");
                    sb.append(b);
                    x.c(sb.toString(), new Object[0]);
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.c);
                    final String string = sb2.toString();
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(b);
                    x.a("network %s changed to %s", string, sb3.toString());
                    if (b == null) {
                        this.c = null;
                        return true;
                    }
                    final String c = this.c;
                    this.c = b;
                    final long currentTimeMillis = System.currentTimeMillis();
                    final a a = com.tencent.bugly.crashreport.common.strategy.a.a();
                    final u a2 = u.a();
                    final com.tencent.bugly.crashreport.common.info.a a3 = com.tencent.bugly.crashreport.common.info.a.a(context);
                    if (a != null && a2 != null && a3 != null) {
                        if (!b.equals((Object)c)) {
                            if (currentTimeMillis - a2.a(com.tencent.bugly.crashreport.crash.c.a) > 30000L) {
                                x.a("try to upload crash on network changed.", new Object[0]);
                                final c a4 = com.tencent.bugly.crashreport.crash.c.a();
                                if (a4 != null) {
                                    a4.a(0L);
                                }
                            }
                            if (currentTimeMillis - a2.a(1001) > 30000L) {
                                x.a("try to upload userinfo on network changed.", new Object[0]);
                                com.tencent.bugly.crashreport.biz.b.a.b();
                            }
                        }
                        return true;
                    }
                    x.d("not inited BC not work", new Object[0]);
                    return true;
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
        return false;
    }
    
    public static BuglyBroadcastReceiver getInstance() {
        synchronized (BuglyBroadcastReceiver.class) {
            if (BuglyBroadcastReceiver.d == null) {
                BuglyBroadcastReceiver.d = new BuglyBroadcastReceiver();
            }
            return BuglyBroadcastReceiver.d;
        }
    }
    
    public void addFilter(final String s) {
        synchronized (this) {
            if (!this.a.hasAction(s)) {
                this.a.addAction(s);
            }
            x.c("add action %s", s);
        }
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        try {
            this.a(context, intent);
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
        }
    }
    
    public void register(final Context b) {
        synchronized (this) {
            this.b = b;
            z.a((Runnable)new Runnable(this, this) {
                private BuglyBroadcastReceiver a;
                private BuglyBroadcastReceiver b;
                
                public final void run() {
                    try {
                        x.a(BuglyBroadcastReceiver.d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                        final BuglyBroadcastReceiver a = this.a;
                        synchronized (a) {
                            this.b.b.registerReceiver((BroadcastReceiver)BuglyBroadcastReceiver.d, this.b.a, "com.tencent.bugly.BuglyBroadcastReceiver.permission", (Handler)null);
                        }
                    }
                    finally {
                        final Throwable t;
                        t.printStackTrace();
                    }
                }
            });
        }
    }
    
    public void unregister(final Context b) {
        monitorenter(this);
        try {
            x.a(this.getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            b.unregisterReceiver((BroadcastReceiver)this);
            this.b = b;
            monitorexit(this);
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
            finally {
                monitorexit(this);
            }
        }
    }
}
