package com.alipay.sdk.app;

import com.alipay.sdk.m.p.e;
import android.os.SystemClock;
import com.alipay.sdk.m.u.l;
import java.util.Map;
import java.io.IOException;
import com.alipay.sdk.m.j.c;
import com.alipay.sdk.m.u.h$f;
import com.alipay.sdk.m.s.a$a;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.List;
import com.alipay.sdk.m.u.n;
import android.content.Context;
import com.alipay.sdk.m.s.b;
import com.alipay.sdk.m.u.h;
import com.alipay.sdk.m.x.a;
import android.app.Activity;

public class AuthTask
{
    public static final Object c;
    public Activity a;
    public a b;
    
    static {
        c = h.class;
    }
    
    public AuthTask(final Activity a) {
        this.a = a;
        com.alipay.sdk.m.s.b.d().a((Context)this.a);
        this.b = new a(a, "\u53bb\u652f\u4ed8\u5b9d\u6388\u6743");
    }
    
    private String a(final Activity activity, String a, final com.alipay.sdk.m.s.a a2) {
        final String a3 = a2.a(a);
        final List<com.alipay.sdk.m.m.a.b> j = com.alipay.sdk.m.m.a.z().j();
        List<com.alipay.sdk.m.m.a.b> d;
        if (!com.alipay.sdk.m.m.a.z().g || (d = j) == null) {
            d = com.alipay.sdk.m.j.a.d;
        }
        if (!n.a(a2, (Context)this.a, (List)d, true)) {
            com.alipay.sdk.m.k.a.a(a2, "biz", "LogCalledH5");
            return this.b(activity, a3, a2);
        }
        final h h = new h(activity, a2, this.b());
        a = h.a(a3, false);
        h.a();
        if (TextUtils.equals((CharSequence)a, (CharSequence)"failed") || TextUtils.equals((CharSequence)a, (CharSequence)"scheme_failed")) {
            com.alipay.sdk.m.k.a.a(a2, "biz", "LogBindCalledH5");
            return this.b(activity, a3, a2);
        }
        if (TextUtils.isEmpty((CharSequence)a)) {
            return com.alipay.sdk.m.j.b.a();
        }
        return a;
    }
    
    private String a(final com.alipay.sdk.m.s.a a, final com.alipay.sdk.m.r.b b) {
        final String[] c = b.c();
        final Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        final Intent intent = new Intent((Context)this.a, (Class)H5AuthActivity.class);
        intent.putExtras(bundle);
        a$a.a(a, intent);
        this.a.startActivity(intent);
        final Object c2;
        monitorenter(c2 = AuthTask.c);
        try {
            try {
                AuthTask.c.wait();
                monitorexit(c2);
                String s;
                if (TextUtils.isEmpty((CharSequence)(s = com.alipay.sdk.m.j.b.d()))) {
                    s = com.alipay.sdk.m.j.b.a();
                }
                return s;
            }
            finally {
                monitorexit(c2);
            }
        }
        catch (final InterruptedException ex) {}
    }
    
    private void a() {
        final a b = this.b;
        if (b != null) {
            b.a();
        }
    }
    
    private h$f b() {
        return (h$f)new AuthTask$a(this);
    }
    
    private String b(final Activity activity, final String s, final com.alipay.sdk.m.s.a a) {
        this.c();
        final c c = null;
        Label_0156: {
            try {
                final List a2 = com.alipay.sdk.m.r.b.a(((e)new com.alipay.sdk.m.q.a()).a(a, (Context)activity, s).c().optJSONObject("form").optJSONObject("onload"));
                this.a();
                for (int i = 0; i < a2.size(); ++i) {
                    if (((com.alipay.sdk.m.r.b)a2.get(i)).a() == com.alipay.sdk.m.r.a.c) {
                        final String a3 = this.a(a, (com.alipay.sdk.m.r.b)a2.get(i));
                        this.a();
                        return a3;
                    }
                }
                this.a();
                break Label_0156;
            }
            catch (final IOException ex) {
                final Throwable t = (Throwable)ex;
                final c c2 = com.alipay.sdk.m.j.c.f;
                final int n = c2.b();
                final c c3 = com.alipay.sdk.m.j.c.b(n);
                final com.alipay.sdk.m.s.a a4 = a;
                final String s2 = "net";
                final Throwable t2 = t;
                com.alipay.sdk.m.k.a.a(a4, s2, t2);
            }
            finally {
                final Throwable t3;
                com.alipay.sdk.m.k.a.a(a, "biz", "H5AuthDataAnalysisError", t3);
                final c c3 = c;
                break Label_0156;
            }
            try {
                final IOException ex;
                final Throwable t = (Throwable)ex;
                final c c2 = com.alipay.sdk.m.j.c.f;
                final int n = c2.b();
                final c c3 = com.alipay.sdk.m.j.c.b(n);
                final com.alipay.sdk.m.s.a a4 = a;
                final String s2 = "net";
                final Throwable t2 = t;
                com.alipay.sdk.m.k.a.a(a4, s2, t2);
                this.a();
                c b = c3;
                if (c3 == null) {
                    b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.d.b());
                }
                return com.alipay.sdk.m.j.b.a(b.b(), b.a(), "");
            }
            finally {
                this.a();
            }
        }
    }
    
    private void c() {
        final a b = this.b;
        if (b != null) {
            b.d();
        }
    }
    
    public String auth(String innerAuth, final boolean b) {
        synchronized (this) {
            innerAuth = this.innerAuth(new com.alipay.sdk.m.s.a((Context)this.a, innerAuth, "auth"), innerAuth, b);
            return innerAuth;
        }
    }
    
    public Map<String, String> authV2(final String s, final boolean b) {
        synchronized (this) {
            final com.alipay.sdk.m.s.a a = new com.alipay.sdk.m.s.a((Context)this.a, s, "authV2");
            return (Map<String, String>)l.a(a, this.innerAuth(a, s, b));
        }
    }
    
    public String innerAuth(final com.alipay.sdk.m.s.a a, final String s, final boolean b) {
        monitorenter(this);
        Label_0013: {
            if (!b) {
                break Label_0013;
            }
            try {
                this.c();
                b.d().a((Context)this.a);
                final String a2 = com.alipay.sdk.m.j.b.a();
                com.alipay.sdk.m.j.a.a("");
                try {
                    while (true) {
                        Label_0313: {
                            try {
                                final String a3 = this.a(this.a, s, a);
                                final StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(SystemClock.elapsedRealtime());
                                com.alipay.sdk.m.k.a.a(a, "biz", "PgReturn", sb.toString());
                                final StringBuilder sb2 = new StringBuilder();
                                sb2.append(l.a(a3, "resultStatus"));
                                sb2.append("|");
                                sb2.append(l.a(a3, "memo"));
                                com.alipay.sdk.m.k.a.a(a, "biz", "PgReturnV", sb2.toString());
                                final boolean p3 = com.alipay.sdk.m.m.a.z().p();
                                String s2 = a3;
                                if (!p3) {
                                    s2 = a3;
                                    break Label_0300;
                                }
                                break Label_0313;
                            }
                            finally {
                                final StringBuilder sb3 = new StringBuilder();
                                sb3.append("");
                                sb3.append(SystemClock.elapsedRealtime());
                                com.alipay.sdk.m.k.a.a(a, "biz", "PgReturn", sb3.toString());
                                final StringBuilder sb4 = new StringBuilder();
                                sb4.append(l.a(a2, "resultStatus"));
                                sb4.append("|");
                                sb4.append(l.a(a2, "memo"));
                                com.alipay.sdk.m.k.a.a(a, "biz", "PgReturnV", sb4.toString());
                                if (!com.alipay.sdk.m.m.a.z().p()) {
                                    com.alipay.sdk.m.m.a.z().a(a, (Context)this.a, false, 1);
                                }
                                this.a();
                                com.alipay.sdk.m.k.a.b((Context)this.a, a, s, a.d);
                                com.alipay.sdk.m.m.a.z().a(a, (Context)this.a, false, 1);
                                break Label_0313;
                                final String s2 = a2;
                                continue;
                                this.a();
                                com.alipay.sdk.m.k.a.b((Context)this.a, a, s, a.d);
                                return s2;
                            }
                        }
                        break;
                    }
                }
                catch (final Exception ex) {}
            }
            finally {
                monitorexit(this);
            }
        }
    }
}
