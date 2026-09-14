package com.alipay.sdk.app;

import android.view.View;
import com.alipay.sdk.m.s.a$a;
import android.os.Bundle;
import android.content.res.Configuration;
import com.alipay.sdk.m.j.b;
import com.alipay.sdk.m.j.d;
import com.alipay.sdk.m.u.n;
import android.content.Intent;
import com.alipay.sdk.m.u.e;
import com.alipay.sdk.m.s.a;
import java.lang.ref.WeakReference;
import com.alipay.sdk.m.x.c;
import android.app.Activity;

public class H5PayActivity extends Activity
{
    public c a;
    public String b;
    public String c;
    public String d;
    public String e;
    public boolean f;
    public String g;
    public WeakReference<a> h;
    
    private void b() {
        try {
            super.requestWindowFeature(1);
            this.getWindow().addFlags(8192);
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
        }
    }
    
    public void a() {
        final Object h = PayTask.h;
        final Object o;
        monitorenter(o = h);
        while (true) {
            try {
                try {
                    h.notify();
                }
                finally {
                    monitorexit(o);
                    monitorexit(o);
                }
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void finish() {
        this.a();
        super.finish();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1010) {
            com.alipay.sdk.m.j.d.a((a)n.a((WeakReference)this.h), n, n2, intent);
        }
    }
    
    public void onBackPressed() {
        final c a = this.a;
        if (a == null) {
            this.finish();
            return;
        }
        if (a.a()) {
            a.b();
        }
        else {
            if (!a.b()) {
                super.onBackPressed();
            }
            com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a());
            this.finish();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    
    public void onCreate(Bundle a) {
        this.b();
        super.onCreate(a);
        try {
            a = (Bundle)a$a.a(this.getIntent());
            if (a == null) {
                this.finish();
                return;
            }
            this.h = (WeakReference<a>)new WeakReference((Object)a);
            if (!com.alipay.sdk.m.m.a.z().v()) {
                this.setRequestedOrientation(1);
            }
            else {
                this.setRequestedOrientation(3);
            }
            try {
                final Bundle extras = this.getIntent().getExtras();
                final String string = extras.getString("url", (String)null);
                this.b = string;
                if (!n.f(string)) {
                    this.finish();
                    return;
                }
                this.d = extras.getString("cookie", (String)null);
                this.c = extras.getString("method", (String)null);
                this.e = extras.getString("title", (String)null);
                this.g = extras.getString("version", "v1");
                this.f = extras.getBoolean("backisexit", false);
                try {
                    final com.alipay.sdk.m.x.d d = new com.alipay.sdk.m.x.d((Activity)this, (a)a, this.g);
                    this.setContentView((View)d);
                    d.a(this.e, this.c, this.f);
                    ((c)d).a(this.b, this.d);
                    d.a(this.b);
                    this.a = (c)d;
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.k.a.a((a)a, "biz", "GetInstalledAppEx", t);
                    this.finish();
                }
            }
            catch (final Exception ex) {
                this.finish();
            }
        }
        catch (final Exception ex2) {
            this.finish();
        }
    }
    
    public void onDestroy() {
        super.onDestroy();
        final c a = this.a;
        if (a != null) {
            a.c();
        }
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        final Throwable t2;
        try {
            super.setRequestedOrientation(requestedOrientation);
            return;
        }
        finally {
            final H5PayActivity h5PayActivity = this;
            final WeakReference<a> weakReference = h5PayActivity.h;
            final Object o = n.a((WeakReference)weakReference);
            final a a = (a)o;
            final String s = "biz";
            final String s2 = "H5PayDataAnalysisError";
            final Throwable t = t2;
            com.alipay.sdk.m.k.a.a(a, s, s2, t);
        }
        try {
            final H5PayActivity h5PayActivity = this;
            final WeakReference<a> weakReference = h5PayActivity.h;
            final Object o = n.a((WeakReference)weakReference);
            final a a = (a)o;
            final String s = "biz";
            final String s2 = "H5PayDataAnalysisError";
            final Throwable t = t2;
            com.alipay.sdk.m.k.a.a(a, s, s2, t);
        }
        finally {}
    }
}
