package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.util.Map;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Build$VERSION;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;
import android.content.ComponentName;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.b;

public final class a implements b
{
    private ComponentName a;
    private Context a;
    com.alibaba.sdk.android.man.crashreporter.a.b a;
    c a;
    private Object a;
    private String q;
    
    public a(final Context a, final c a2, final com.alibaba.sdk.android.man.crashreporter.a.b a3) {
        this.a = null;
        this.a = null;
        this.a = new Object();
        this.a = a;
        this.a();
        this.a = a2;
        this.a = a3;
    }
    
    private void a() {
        if (Build$VERSION.SDK_INT >= 14) {
            if (this.a.getApplicationContext() instanceof Application) {
                ((Application)this.a.getApplicationContext()).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new a.a$a(this));
            }
        }
        else {
            com.alibaba.sdk.android.man.crashreporter.b.a.g(String.format("build version %s not suppert registerActivityLifecycleCallbacks, registerActivityLifecycleCallbacks failed", new Object[] { Build$VERSION.SDK_INT }));
        }
    }
    
    private void a(final int n) {
        try {
            BaseDataContent a;
            if ((a = this.a.a()) == null) {
                a = new BaseDataContent();
            }
            if (n == 2) {
                this.a.a(MotuCrashReporter.getInstance().getConfigure(), a, 2);
            }
            else if (n == 1) {
                this.a.a(MotuCrashReporter.getInstance().getConfigure(), a, 1);
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("write app status err", (Throwable)ex);
        }
    }
    
    private String c() {
        final Context a = this.a;
        String s = "";
        String string = null;
        Label_0086: {
            if (a != null) {
                try {
                    final PackageManager packageManager = a.getPackageManager();
                    if (packageManager != null && this.a != null) {
                        try {
                            final ActivityInfo activityInfo = packageManager.getActivityInfo(this.a, 128);
                            if (activityInfo != null && activityInfo.metaData != null) {
                                string = activityInfo.metaData.getString("bundleLocation");
                                break Label_0086;
                            }
                        }
                        catch (final PackageManager$NameNotFoundException ex) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.d("get bundle failed.", (Throwable)ex);
                        }
                    }
                }
                catch (final Exception ex2) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("system error, getBundle failed", (Throwable)ex2);
                }
            }
            string = "";
        }
        if (string != null) {
            s = string;
        }
        return s;
    }
    
    private String d() {
        final ComponentName a = this.a;
        String className;
        if (a != null) {
            className = a.getClassName();
        }
        else {
            className = "";
        }
        return className;
    }
    
    public void a(final Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        map.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.w, (Object)this.d());
        map.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.x, (Object)this.b());
        map.put((Object)com.alibaba.sdk.android.man.crashreporter.global.a.y, (Object)this.c());
    }
    
    public String b() {
        String q;
        if (!i.a((CharSequence)this.q)) {
            q = this.q;
        }
        else {
            q = "";
        }
        return q;
    }
}
