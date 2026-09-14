package com.alipay.sdk.app;

import com.alipay.sdk.util.H5PayResultModel;
import android.net.Uri;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.alipay.sdk.m.u.l;
import android.os.SystemClock;
import com.alipay.sdk.m.u.i;
import java.util.Locale;
import com.alipay.sdk.m.u.n$c;
import java.io.IOException;
import com.alipay.sdk.m.j.c;
import com.alipay.sdk.m.q.f;
import java.util.List;
import java.util.Arrays;
import com.alipay.sdk.m.u.n;
import com.alipay.sdk.m.s.a$a;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import org.json.JSONObject;
import com.alipay.sdk.m.u.e;
import com.alipay.sdk.m.u.h$f;
import android.content.Context;
import com.alipay.sdk.m.s.b;
import java.util.HashMap;
import com.alipay.sdk.m.u.h;
import java.util.Map;
import com.alipay.sdk.m.x.a;
import android.app.Activity;

public class PayTask
{
    public static final Object h;
    public static long i;
    public Activity a;
    public a b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public Map<String, c> g;
    
    static {
        h = h.class;
    }
    
    public PayTask(final Activity a) {
        this.c = "wappaygw.alipay.com/service/rest.htm";
        this.d = "mclient.alipay.com/service/rest.htm";
        this.e = "mclient.alipay.com/home/exterfaceAssign.htm";
        this.f = "mclient.alipay.com/cashier/mobilepay.htm";
        this.g = (Map<String, c>)new HashMap();
        this.a = a;
        com.alipay.sdk.m.s.b.d().a((Context)this.a);
        this.b = new a(a, "\u53bb\u652f\u4ed8\u5b9d\u4ed8\u6b3e");
    }
    
    public static /* synthetic */ Activity a(final PayTask payTask) {
        return payTask.a;
    }
    
    private h$f a() {
        return (h$f)new PayTask$b(this);
    }
    
    public static String a(Context versionName) {
        try {
            final PackageInfo packageInfo = versionName.getPackageManager().getPackageInfo(versionName.getPackageName(), 0);
            versionName = (Context)packageInfo.versionName;
            try {
                final String packageName = packageInfo.packageName;
            }
            catch (final Exception ex) {}
        }
        catch (final Exception ex) {
            versionName = (Context)"";
        }
        final Exception ex;
        e.a((Throwable)ex);
        final String packageName = "";
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("appkey", (Object)"2014052600006128");
            jsonObject.put("ty", (Object)"and_lite");
            jsonObject.put("sv", (Object)"h.a.3.8.17");
            jsonObject.put("an", (Object)packageName);
            jsonObject.put("av", (Object)versionName);
            jsonObject.put("sdk_start_time", System.currentTimeMillis());
            if (!TextUtils.isEmpty((CharSequence)"sc")) {
                jsonObject.put("sc", (Object)"h5tonative");
            }
            return jsonObject.toString();
        }
        finally {
            final Throwable t;
            e.a(t);
            return "";
        }
    }
    
    private String a(final com.alipay.sdk.m.s.a a, final com.alipay.sdk.m.r.b b) {
        final String[] c = b.c();
        final Intent intent = new Intent((Context)this.a, (Class)H5PayActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        if (c.length == 2) {
            bundle.putString("cookie", c[1]);
        }
        intent.putExtras(bundle);
        a$a.a(a, intent);
        this.a.startActivity(intent);
        final Object h;
        monitorenter(h = PayTask.h);
        try {
            try {
                PayTask.h.wait();
                monitorexit(h);
                String s;
                if (TextUtils.isEmpty((CharSequence)(s = com.alipay.sdk.m.j.b.d()))) {
                    s = com.alipay.sdk.m.j.b.a();
                }
                return s;
            }
            finally {
                monitorexit(h);
            }
        }
        catch (final InterruptedException ex) {}
    }
    
    private String a(final com.alipay.sdk.m.s.a a, com.alipay.sdk.m.r.b b, final String s) {
        b = (com.alipay.sdk.m.r.b)(Object)b.c();
        final Intent intent = new Intent((Context)this.a, (Class)H5PayActivity.class);
        try {
            final JSONObject h = n.h(new String(com.alipay.sdk.m.n.a.a(b[2])));
            intent.putExtra("url", b[0]);
            intent.putExtra("title", b[1]);
            intent.putExtra("version", "v2");
            intent.putExtra("method", h.optString("method", "POST"));
            com.alipay.sdk.m.j.b.a(false);
            com.alipay.sdk.m.j.b.a(null);
            a$a.a(a, intent);
            this.a.startActivity(intent);
            b = (com.alipay.sdk.m.r.b)PayTask.h;
            final com.alipay.sdk.m.r.b b2;
            monitorenter(b2 = b);
            try {
                try {
                    PayTask.h.wait();
                    final boolean c = com.alipay.sdk.m.j.b.c();
                    final String d = com.alipay.sdk.m.j.b.d();
                    com.alipay.sdk.m.j.b.a(false);
                    com.alipay.sdk.m.j.b.a(null);
                    monitorexit(b2);
                    final com.alipay.sdk.m.r.b b3 = b = (com.alipay.sdk.m.r.b)"";
                    Label_0284: {
                        if (c) {
                            try {
                                final List a2 = com.alipay.sdk.m.r.b.a(n.h(new String(com.alipay.sdk.m.n.a.a(d))));
                                Block_10: {
                                    for (int i = 0; i < a2.size(); ++i) {
                                        b = (com.alipay.sdk.m.r.b)a2.get(i);
                                        if (b.a() == com.alipay.sdk.m.r.a.f) {
                                            break Block_10;
                                        }
                                    }
                                    break Label_0284;
                                }
                                final String[] c2 = b.c();
                                com.alipay.sdk.m.j.b.a(Integer.valueOf(c2[1]), c2[0], n.e(a, c2[2]));
                            }
                            finally {
                                com.alipay.sdk.m.u.e.a((Throwable)b);
                                com.alipay.sdk.m.k.a.a(a, "biz", "H5PayDataAnalysisError", (Throwable)b, d);
                                b = b3;
                            }
                        }
                    }
                    Object a3 = b;
                    if (TextUtils.isEmpty((CharSequence)b)) {
                        try {
                            com.alipay.sdk.m.j.b.a(Integer.valueOf(s), "", "");
                        }
                        finally {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("endCode: ");
                            sb.append(s);
                            com.alipay.sdk.m.k.a.a(a, "biz", "H5PayDataAnalysisError", (Throwable)b, sb.toString());
                            a3 = com.alipay.sdk.m.j.b.a(8000, "", "");
                        }
                    }
                    return (String)a3;
                }
                finally {
                    monitorexit(b2);
                }
            }
            catch (final InterruptedException ex) {}
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
            com.alipay.sdk.m.k.a.a(a, "biz", "H5PayDataAnalysisError", t, Arrays.toString((Object[])(Object)b));
            return com.alipay.sdk.m.j.b.a();
        }
    }
    
    private String a(com.alipay.sdk.m.s.a a, String b) {
        this.showLoading();
        Object o = null;
        Label_0368: {
            try {
                final JSONObject c = ((com.alipay.sdk.m.p.e)new f()).a(a, this.a.getApplicationContext(), (String)b).c();
                final String optString = c.optString("end_code", (String)null);
                final List a2 = com.alipay.sdk.m.r.b.a(c.optJSONObject("form").optJSONObject("onload"));
                final int n = 0;
                for (int i = 0; i < a2.size(); ++i) {
                    if (((com.alipay.sdk.m.r.b)a2.get(i)).a() == com.alipay.sdk.m.r.a.d) {
                        com.alipay.sdk.m.r.b.a((com.alipay.sdk.m.r.b)a2.get(i));
                    }
                }
                this.a(a, c);
                this.dismissLoading();
                com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
                for (int j = n; j < a2.size(); ++j) {
                    final com.alipay.sdk.m.r.b b2 = (com.alipay.sdk.m.r.b)a2.get(j);
                    if (b2.a() == com.alipay.sdk.m.r.a.c) {
                        final String a3 = this.a(a, b2);
                        this.dismissLoading();
                        com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
                        return a3;
                    }
                    if (b2.a() == com.alipay.sdk.m.r.a.e) {
                        final String a4 = this.a(a, b2, optString);
                        this.dismissLoading();
                        com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
                        return a4;
                    }
                }
                this.dismissLoading();
                com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
                a = (com.alipay.sdk.m.s.a)o;
                break Label_0368;
            }
            catch (final IOException ex) {
                final Throwable t = (Throwable)ex;
                final com.alipay.sdk.m.j.c c2 = com.alipay.sdk.m.j.c.f;
                final int n2 = c2.b();
                o = com.alipay.sdk.m.j.c.b(n2);
                final com.alipay.sdk.m.s.a a5 = a;
                final String s = "net";
                final Throwable t2 = t;
                com.alipay.sdk.m.k.a.a(a5, s, t2);
                final PayTask payTask = this;
                payTask.dismissLoading();
                final PayTask payTask2 = this;
                final Activity activity = payTask2.a;
                final com.alipay.sdk.m.s.a a6 = a;
                final Object o2 = b;
                final com.alipay.sdk.m.s.a a7 = a;
                final String s2 = a7.d;
                com.alipay.sdk.m.k.a.a((Context)activity, a6, (String)o2, s2);
                final com.alipay.sdk.m.s.a a8 = a = (com.alipay.sdk.m.s.a)o;
            }
            finally {
                final Throwable t3;
                com.alipay.sdk.m.u.e.a(t3);
                com.alipay.sdk.m.k.a.a(a, "biz", "H5PayDataAnalysisError", t3);
                this.dismissLoading();
                com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
                a = (com.alipay.sdk.m.s.a)o;
                break Label_0368;
            }
            try {
                final IOException ex;
                final Throwable t = (Throwable)ex;
                final com.alipay.sdk.m.j.c c2 = com.alipay.sdk.m.j.c.f;
                final int n2 = c2.b();
                o = com.alipay.sdk.m.j.c.b(n2);
                final com.alipay.sdk.m.s.a a5 = a;
                final String s = "net";
                final Throwable t2 = t;
                com.alipay.sdk.m.k.a.a(a5, s, t2);
                final PayTask payTask = this;
                payTask.dismissLoading();
                final PayTask payTask2 = this;
                final Activity activity = payTask2.a;
                final com.alipay.sdk.m.s.a a6 = a;
                final Object o2 = b;
                final com.alipay.sdk.m.s.a a7 = a;
                final String s2 = a7.d;
                com.alipay.sdk.m.k.a.a((Context)activity, a6, (String)o2, s2);
                a = (com.alipay.sdk.m.s.a)o;
                b = a;
                if (a == null) {
                    b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.d.b());
                }
                return com.alipay.sdk.m.j.b.a(((com.alipay.sdk.m.j.c)b).b(), ((com.alipay.sdk.m.j.c)b).a(), "");
            }
            finally {
                this.dismissLoading();
                com.alipay.sdk.m.k.a.a((Context)this.a, a, (String)b, a.d);
            }
        }
    }
    
    public static String a(final com.alipay.sdk.m.s.a a, final String s, final List<com.alipay.sdk.m.m.a.b> list, final String s2, final Activity activity) {
        final n$c a2 = n.a(a, (Context)activity, (List)list);
        if (a2 != null && !a2.a(a) && !a2.a()) {
            if (TextUtils.equals((CharSequence)a2.a.packageName, (CharSequence)"hk.alipay.wallet")) {
                e.b("mspl", "PayTask not_login");
                final String value = String.valueOf(s.hashCode());
                PayResultActivity.c.put((Object)value, new Object());
                final Intent intent = new Intent((Context)activity, (Class)PayResultActivity.class);
                intent.putExtra("orderSuffix", s);
                intent.putExtra("externalPkgName", activity.getPackageName());
                intent.putExtra("phonecashier.pay.hash", value);
                a$a.a(a, intent);
                activity.startActivity(intent);
                final Object value2;
                monitorenter(value2 = PayResultActivity.c.get((Object)value));
                try {
                    try {
                        e.b("mspl", "PayTask wait");
                        PayResultActivity.c.get((Object)value).wait();
                        monitorexit(value2);
                        final String b = PayResultActivity.b.b;
                        final StringBuilder sb = new StringBuilder();
                        sb.append("PayTask ret: ");
                        sb.append(b);
                        e.b("mspl", sb.toString());
                        return b;
                    }
                    finally {
                        monitorexit(value2);
                    }
                }
                catch (final InterruptedException ex) {}
            }
        }
        return s2;
    }
    
    private String a(com.alipay.sdk.m.s.a a, String o, final boolean b) {
        monitorenter(this);
        Label_0013: {
            if (!b) {
                break Label_0013;
            }
            try {
                this.showLoading();
                if (((String)o).contains((CharSequence)"payment_inst=")) {
                    final String substring = ((String)o).substring(((String)o).indexOf("payment_inst=") + 13);
                    final int index = substring.indexOf(38);
                    String substring2 = substring;
                    if (index > 0) {
                        substring2 = substring.substring(0, index);
                    }
                    com.alipay.sdk.m.j.a.a(substring2.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
                }
                else {
                    com.alipay.sdk.m.j.a.a("");
                }
                if (((String)o).contains((CharSequence)"service=alipay.acquire.mr.ord.createandpay")) {
                    com.alipay.sdk.m.l.a.x = true;
                }
                Object o2 = o;
                if (com.alipay.sdk.m.l.a.x) {
                    if (((String)o).startsWith("https://wappaygw.alipay.com/home/exterfaceAssign.htm?")) {
                        o2 = ((String)o).substring(((String)o).indexOf("https://wappaygw.alipay.com/home/exterfaceAssign.htm?") + 53);
                    }
                    else {
                        o2 = o;
                        if (((String)o).startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm?")) {
                            o2 = ((String)o).substring(((String)o).indexOf("https://mclient.alipay.com/home/exterfaceAssign.htm?") + 52);
                        }
                    }
                }
                Object o3;
                o = (o3 = "");
                Label_0583: {
                    try {
                        o3 = o;
                        final StringBuilder sb = new StringBuilder();
                        o3 = o;
                        sb.append("pay prepared: ");
                        o3 = o;
                        sb.append((String)o2);
                        o3 = o;
                        com.alipay.sdk.m.u.e.d("mspl", sb.toString());
                        o3 = o;
                        o = (o3 = (o3 = this.a((String)o2, a)));
                        final StringBuilder sb2 = new StringBuilder();
                        o3 = o;
                        sb2.append("pay raw result: ");
                        o3 = o;
                        sb2.append((String)o);
                        o3 = o;
                        com.alipay.sdk.m.u.e.d("mspl", sb2.toString());
                        o3 = o;
                        com.alipay.sdk.m.u.i.a(a, this.a.getApplicationContext(), (String)o);
                        o3 = new StringBuilder();
                        ((StringBuilder)o3).append("");
                        ((StringBuilder)o3).append(SystemClock.elapsedRealtime());
                        com.alipay.sdk.m.k.a.a(a, "biz", "PgReturn", ((StringBuilder)o3).toString());
                        o3 = new StringBuilder();
                        ((StringBuilder)o3).append(l.a((String)o, "resultStatus"));
                        ((StringBuilder)o3).append("|");
                        ((StringBuilder)o3).append(l.a((String)o, "memo"));
                        com.alipay.sdk.m.k.a.a(a, "biz", "PgReturnV", ((StringBuilder)o3).toString());
                        final boolean p3 = com.alipay.sdk.m.m.a.z().p();
                        o3 = o;
                        if (!p3) {
                            break Label_0583;
                        }
                        break Label_0583;
                    }
                    finally {
                        try {
                            o = (o3 = com.alipay.sdk.m.j.b.a());
                            final Throwable t;
                            com.alipay.sdk.m.u.e.a(t);
                            o3 = new StringBuilder();
                            ((StringBuilder)o3).append("");
                            ((StringBuilder)o3).append(SystemClock.elapsedRealtime());
                            com.alipay.sdk.m.k.a.a(a, "biz", "PgReturn", ((StringBuilder)o3).toString());
                            o3 = new StringBuilder();
                            ((StringBuilder)o3).append(l.a((String)o, "resultStatus"));
                            ((StringBuilder)o3).append("|");
                            ((StringBuilder)o3).append(l.a((String)o, "memo"));
                            com.alipay.sdk.m.k.a.a(a, "biz", "PgReturnV", ((StringBuilder)o3).toString());
                            o3 = o;
                            if (!com.alipay.sdk.m.m.a.z().p()) {
                                com.alipay.sdk.m.m.a.z().a(a, this.a.getApplicationContext(), false, 3);
                                o3 = o;
                            }
                            this.dismissLoading();
                            com.alipay.sdk.m.k.a.b(this.a.getApplicationContext(), a, (String)o2, a.d);
                            a = (com.alipay.sdk.m.s.a)new StringBuilder();
                            ((StringBuilder)a).append("pay returning: ");
                            ((StringBuilder)a).append((String)o3);
                            com.alipay.sdk.m.u.e.d("mspl", ((StringBuilder)a).toString());
                            monitorexit(this);
                            return (String)o3;
                        }
                        finally {
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("");
                            sb3.append(SystemClock.elapsedRealtime());
                            com.alipay.sdk.m.k.a.a(a, "biz", "PgReturn", sb3.toString());
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append(l.a((String)o3, "resultStatus"));
                            sb4.append("|");
                            sb4.append(l.a((String)o3, "memo"));
                            com.alipay.sdk.m.k.a.a(a, "biz", "PgReturnV", sb4.toString());
                            if (!com.alipay.sdk.m.m.a.z().p()) {
                                com.alipay.sdk.m.m.a.z().a(a, this.a.getApplicationContext(), false, 3);
                            }
                            this.dismissLoading();
                            com.alipay.sdk.m.k.a.b(this.a.getApplicationContext(), a, (String)o2, a.d);
                        }
                    }
                }
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    private String a(final String s, final com.alipay.sdk.m.s.a a) {
        final String a2 = a.a(s);
        if (a2.contains((CharSequence)"paymethod=\"expressGateway\"")) {
            return this.a(a, a2);
        }
        final List<com.alipay.sdk.m.m.a.b> j = com.alipay.sdk.m.m.a.z().j();
        List<com.alipay.sdk.m.m.a.b> d;
        if (!com.alipay.sdk.m.m.a.z().g || (d = j) == null) {
            d = com.alipay.sdk.m.j.a.d;
        }
        if (!n.a(a, (Context)this.a, (List)d, true)) {
            com.alipay.sdk.m.k.a.a(a, "biz", "LogCalledH5");
            return this.a(a, a2);
        }
        final h h = new h(this.a, a, this.a());
        final StringBuilder sb = new StringBuilder();
        sb.append("pay inner started: ");
        sb.append(a2);
        com.alipay.sdk.m.u.e.d("mspl", sb.toString());
        String s3;
        final String s2 = s3 = h.a(a2, (boolean)(0 != 0));
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("resultStatus={");
            sb2.append(com.alipay.sdk.m.j.c.g.b());
            sb2.append("}");
            s3 = s2;
            if (s2.contains((CharSequence)sb2.toString())) {
                n.a("alipaySdk", "startActivityEx", (Context)this.a, a);
                s3 = h.a(a2, true);
            }
        }
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("pay inner raw result: ");
        sb3.append(s3);
        com.alipay.sdk.m.u.e.d("mspl", sb3.toString());
        h.a();
        final boolean t = com.alipay.sdk.m.m.a.z().t();
        if (TextUtils.equals((CharSequence)s3, (CharSequence)"failed") || TextUtils.equals((CharSequence)s3, (CharSequence)"scheme_failed") || (t && a.e())) {
            com.alipay.sdk.m.k.a.a(a, "biz", "LogBindCalledH5");
            return this.a(a, a2);
        }
        if (TextUtils.isEmpty((CharSequence)s3)) {
            return com.alipay.sdk.m.j.b.a();
        }
        if (s3.contains((CharSequence)"{\"isLogin\":\"false\"}")) {
            com.alipay.sdk.m.k.a.a(a, "biz", "LogHkLoginByIntent");
            return a(a, a2, d, s3, this.a);
        }
        return s3;
    }
    
    private String a(final String s, String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append("={");
        string = sb.toString();
        return s.substring(s.indexOf(string) + string.length(), s.lastIndexOf("}"));
    }
    
    private String a(String s, final Map<String, String> map) throws UnsupportedEncodingException {
        final boolean equals = "9000".equals(map.get((Object)"resultStatus"));
        final String s2 = (String)map.get((Object)"result");
        final c c = (c)this.g.remove((Object)s);
        if (map.containsKey((Object)"callBackUrl")) {
            return (String)map.get((Object)"callBackUrl");
        }
        if (s2.length() > 15) {
            s = a(n.a("&callBackUrl=\"", "\"", s2), n.a("&call_back_url=\"", "\"", s2), n.a("&return_url=\"", "\"", s2), URLDecoder.decode(n.a("&return_url=", "&", s2), "utf-8"), URLDecoder.decode(n.a("&callBackUrl=", "&", s2), "utf-8"), n.a("call_back_url=\"", "\"", s2));
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return s;
            }
        }
        if (c != null) {
            if (equals) {
                s = c.b();
            }
            else {
                s = c.c();
            }
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return s;
            }
        }
        if (c != null) {
            return com.alipay.sdk.m.m.a.z().o();
        }
        return "";
    }
    
    public static final String a(final String... array) {
        if (array == null) {
            return "";
        }
        for (final String s : array) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return s;
            }
        }
        return "";
    }
    
    private void a(final com.alipay.sdk.m.s.a a, final JSONObject jsonObject) {
        try {
            final String optString = jsonObject.optString("tid");
            final String optString2 = jsonObject.optString("client_key");
            if (!TextUtils.isEmpty((CharSequence)optString) && !TextUtils.isEmpty((CharSequence)optString2)) {
                com.alipay.sdk.m.t.a.a(com.alipay.sdk.m.s.b.d().b()).a(optString, optString2);
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "ParserTidClientKeyEx", t);
        }
    }
    
    private boolean a(final boolean b, final boolean b2, final String s, final StringBuilder sb, final Map<String, String> map, final String... array) {
        while (true) {
            for (final String s2 : array) {
                if (!TextUtils.isEmpty((CharSequence)map.get((Object)s2))) {
                    final String s3 = (String)map.get((Object)s2);
                    if (TextUtils.isEmpty((CharSequence)s3)) {
                        if (b2) {
                            return false;
                        }
                    }
                    else if (b) {
                        sb.append("&");
                        sb.append(s);
                        sb.append("=\"");
                        sb.append(s3);
                        sb.append("\"");
                    }
                    else {
                        sb.append(s);
                        sb.append("=\"");
                        sb.append(s3);
                        sb.append("\"");
                    }
                    return true;
                }
            }
            final String s3 = "";
            continue;
        }
    }
    
    public static boolean fetchSdkConfig(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          5
        //     5: monitorenter   
        //     6: invokestatic    com/alipay/sdk/m/s/b.d:()Lcom/alipay/sdk/m/s/b;
        //     9: aload_0        
        //    10: invokevirtual   com/alipay/sdk/m/s/b.a:(Landroid/content/Context;)V
        //    13: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    16: ldc2_w          1000
        //    19: ldiv           
        //    20: lstore          4
        //    22: getstatic       com/alipay/sdk/app/PayTask.i:J
        //    25: lstore_2       
        //    26: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //    29: invokevirtual   com/alipay/sdk/m/m/a.d:()I
        //    32: istore_1       
        //    33: lload           4
        //    35: lload_2        
        //    36: lsub           
        //    37: iload_1        
        //    38: i2l            
        //    39: lcmp           
        //    40: ifge            48
        //    43: aload           5
        //    45: monitorexit    
        //    46: iconst_0       
        //    47: ireturn        
        //    48: lload           4
        //    50: putstatic       com/alipay/sdk/app/PayTask.i:J
        //    53: invokestatic    com/alipay/sdk/m/m/a.z:()Lcom/alipay/sdk/m/m/a;
        //    56: invokestatic    com/alipay/sdk/m/s/a.h:()Lcom/alipay/sdk/m/s/a;
        //    59: aload_0        
        //    60: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //    63: iconst_0       
        //    64: iconst_4       
        //    65: invokevirtual   com/alipay/sdk/m/m/a.a:(Lcom/alipay/sdk/m/s/a;Landroid/content/Context;ZI)V
        //    68: aload           5
        //    70: monitorexit    
        //    71: iconst_1       
        //    72: ireturn        
        //    73: astore_0       
        //    74: goto            87
        //    77: astore_0       
        //    78: aload_0        
        //    79: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //    82: aload           5
        //    84: monitorexit    
        //    85: iconst_0       
        //    86: ireturn        
        //    87: aload           5
        //    89: monitorexit    
        //    90: aload_0        
        //    91: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      33     77     87     Ljava/lang/Exception;
        //  6      33     73     77     Any
        //  48     68     77     87     Ljava/lang/Exception;
        //  48     68     73     77     Any
        //  78     82     73     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Attempt to read from field 'java.util.ArrayList q5.e.c' on a null object reference
        //     at q5.g.c(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:871)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2394)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void dismissLoading() {
        final a b = this.b;
        if (b != null) {
            b.a();
            this.b = null;
        }
    }
    
    public String fetchOrderInfoFromH5PayUrl(String s) {
        monitorenter(this);
        final Throwable t2;
        try {
            if (TextUtils.isEmpty((CharSequence)s)) {
                return "";
            }
            final String trim = s.trim();
            if (trim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || trim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                final String trim2 = trim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                if (!TextUtils.isEmpty((CharSequence)trim2)) {
                    final String a = n.a("<request_token>", "</request_token>", (String)n.b(trim2).get((Object)"req_data"));
                    final StringBuilder sb = new StringBuilder();
                    sb.append("_input_charset=\"utf-8\"&ordertoken=\"");
                    sb.append(a);
                    sb.append("\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"");
                    sb.append(a((Context)this.a));
                    sb.append("\"");
                    s = sb.toString();
                    return s;
                }
            }
            if (trim.startsWith("https://mclient.alipay.com/service/rest.htm") || trim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                final String trim3 = trim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                if (!TextUtils.isEmpty((CharSequence)trim3)) {
                    s = n.a("<request_token>", "</request_token>", (String)n.b(trim3).get((Object)"req_data"));
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("_input_charset=\"utf-8\"&ordertoken=\"");
                    sb2.append(s);
                    sb2.append("\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"");
                    sb2.append(a((Context)this.a));
                    sb2.append("\"");
                    s = sb2.toString();
                    return s;
                }
            }
            if ((trim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || trim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && (trim.contains((CharSequence)"alipay.wap.create.direct.pay.by.user") || trim.contains((CharSequence)"create_forex_trade_wap")) && !TextUtils.isEmpty((CharSequence)trim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim())) {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("url", (Object)s);
                jsonObject.put("bizcontext", (Object)a((Context)this.a));
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("new_external_info==");
                sb3.append(jsonObject.toString());
                s = sb3.toString();
                return s;
            }
            if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher((CharSequence)s).find()) {
                s = n.a("?", "", s);
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    final Map b = n.b(s);
                    final StringBuilder sb4 = new StringBuilder();
                    if (this.a(false, true, "trade_no", sb4, (Map<String, String>)b, "trade_no", "alipay_trade_no")) {
                        this.a(true, false, "pay_phase_id", sb4, (Map<String, String>)b, "payPhaseId", "pay_phase_id", "out_relation_id");
                        sb4.append("&biz_sub_type=\"TRADE\"");
                        sb4.append("&biz_type=\"trade\"");
                        final Object o = b.get((Object)"app_name");
                        Label_0638: {
                            if (TextUtils.isEmpty((CharSequence)o) && !TextUtils.isEmpty((CharSequence)b.get((Object)"cid"))) {
                                s = "ali1688";
                            }
                            else {
                                s = (String)o;
                                if (TextUtils.isEmpty((CharSequence)o)) {
                                    if (TextUtils.isEmpty((CharSequence)b.get((Object)"sid"))) {
                                        final boolean empty = TextUtils.isEmpty((CharSequence)b.get((Object)"s_id"));
                                        s = (String)o;
                                        if (empty) {
                                            break Label_0638;
                                        }
                                    }
                                    s = "tb";
                                }
                            }
                        }
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append("&app_name=\"");
                        sb5.append(s);
                        sb5.append("\"");
                        sb4.append(sb5.toString());
                        if (!this.a(true, true, "extern_token", sb4, (Map<String, String>)b, "extern_token", "cid", "sid", "s_id")) {
                            return "";
                        }
                        this.a(true, false, "appenv", sb4, (Map<String, String>)b, "appenv");
                        sb4.append("&pay_channel_id=\"alipay_sdk\"");
                        final c c = new c(null);
                        c.b((String)b.get((Object)"return_url"));
                        c.c((String)b.get((Object)"show_url"));
                        c.a((String)b.get((Object)"pay_order_id"));
                        final StringBuilder sb6 = new StringBuilder();
                        sb6.append(sb4.toString());
                        sb6.append("&bizcontext=\"");
                        sb6.append(a((Context)this.a));
                        sb6.append("\"");
                        final String string = sb6.toString();
                        this.g.put((Object)string, (Object)c);
                        return string;
                    }
                }
            }
            if (trim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") || trim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") || (EnvUtils.isSandBox() && trim.contains((CharSequence)"mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                final String a2 = a((Context)this.a);
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("url", (Object)trim);
                jsonObject2.put("bizcontext", (Object)a2);
                s = String.format("new_external_info==%s", new Object[] { jsonObject2.toString() });
                return s;
            }
            if (!com.alipay.sdk.m.m.a.z().g() || !Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher((CharSequence)trim).find()) {
                return "";
            }
            final Uri parse = Uri.parse(trim);
            final String queryParameter = parse.getQueryParameter("return_url");
            final String queryParameter2 = parse.getQueryParameter("show_url");
            final String queryParameter3 = parse.getQueryParameter("pay_order_id");
            final String a3 = a(new String[] { parse.getQueryParameter("trade_nos"), parse.getQueryParameter("alipay_trade_no") });
            final String a4 = a(parse.getQueryParameter("payPhaseId"), parse.getQueryParameter("pay_phase_id"), parse.getQueryParameter("out_relation_id"));
            final String queryParameter4 = parse.getQueryParameter("app_name");
            if (!TextUtils.isEmpty((CharSequence)parse.getQueryParameter("cid"))) {
                s = "ali1688";
            }
            else {
                s = "";
            }
            String s2;
            if (!TextUtils.isEmpty((CharSequence)parse.getQueryParameter("sid"))) {
                s2 = "tb";
            }
            else {
                s2 = "";
            }
            String s3;
            if (!TextUtils.isEmpty((CharSequence)parse.getQueryParameter("s_id"))) {
                s3 = "tb";
            }
            else {
                s3 = "";
            }
            s = a(queryParameter4, s, s2, s3);
            final String a5 = a(parse.getQueryParameter("extern_token"), parse.getQueryParameter("cid"), parse.getQueryParameter("sid"), parse.getQueryParameter("s_id"));
            final String a6 = a(parse.getQueryParameter("appenv"));
            if (!TextUtils.isEmpty((CharSequence)a3) && !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)a5)) {
                final String format = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", new Object[] { a3, a4, s, a5, a6, a((Context)this.a) });
                final c c2 = new c(null);
                c2.b(queryParameter);
                c2.c(queryParameter2);
                c2.a(queryParameter3);
                c2.d(a3);
                this.g.put((Object)format, (Object)c2);
                return format;
            }
            return "";
        }
        finally {
            final Throwable t = t2;
            com.alipay.sdk.m.u.e.a(t);
        }
        try {
            final Throwable t = t2;
            com.alipay.sdk.m.u.e.a(t);
            return "";
        }
        finally {
            monitorexit(this);
        }
    }
    
    public String fetchTradeToken() {
        synchronized (this) {
            return com.alipay.sdk.m.u.i.a(new com.alipay.sdk.m.s.a((Context)this.a, "", "fetchTradeToken"), this.a.getApplicationContext());
        }
    }
    
    public String getVersion() {
        return "15.8.17";
    }
    
    public H5PayResultModel h5Pay(final com.alipay.sdk.m.s.a a, final String s, final boolean b) {
        synchronized (this) {
            final H5PayResultModel h5PayResultModel = new H5PayResultModel();
            try {
                final String[] split = this.a(a, s, b).split(";");
                final HashMap hashMap = new HashMap();
                for (final String s2 : split) {
                    final int index = s2.indexOf("={");
                    if (index >= 0) {
                        final String substring = s2.substring(0, index);
                        ((Map)hashMap).put((Object)substring, (Object)this.a(s2, substring));
                    }
                }
                if (((Map)hashMap).containsKey((Object)"resultStatus")) {
                    h5PayResultModel.setResultCode((String)((Map)hashMap).get((Object)"resultStatus"));
                }
                h5PayResultModel.setReturnUrl(this.a(s, (Map<String, String>)hashMap));
                if (TextUtils.isEmpty((CharSequence)h5PayResultModel.getReturnUrl())) {
                    com.alipay.sdk.m.k.a.b(a, "biz", "H5CbUrlEmpty", "");
                }
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.k.a.a(a, "biz", "H5CbEx", t);
                com.alipay.sdk.m.u.e.a(t);
            }
            return h5PayResultModel;
        }
    }
    
    public String pay(String s, final boolean b) {
        synchronized (this) {
            if (com.alipay.sdk.m.u.b.a()) {
                s = com.alipay.sdk.m.j.b.b();
                return s;
            }
            s = this.a(new com.alipay.sdk.m.s.a((Context)this.a, s, "pay"), s, b);
            return s;
        }
    }
    
    public boolean payInterceptorWithUrl(String fetchOrderInfoFromH5PayUrl, final boolean b, final H5PayCallback h5PayCallback) {
        synchronized (this) {
            fetchOrderInfoFromH5PayUrl = this.fetchOrderInfoFromH5PayUrl(fetchOrderInfoFromH5PayUrl);
            if (!TextUtils.isEmpty((CharSequence)fetchOrderInfoFromH5PayUrl)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("intercepted: ");
                sb.append(fetchOrderInfoFromH5PayUrl);
                com.alipay.sdk.m.u.e.d("mspl", sb.toString());
                new Thread((Runnable)new Runnable(this, fetchOrderInfoFromH5PayUrl, b, h5PayCallback) {
                    public final String a;
                    public final boolean b;
                    public final H5PayCallback c;
                    public final PayTask d;
                    
                    public void run() {
                        final H5PayResultModel h5Pay = this.d.h5Pay(new com.alipay.sdk.m.s.a((Context)PayTask.a(this.d), this.a, "payInterceptorWithUrl"), this.a, this.b);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("inc finished: ");
                        sb.append(h5Pay.getResultCode());
                        com.alipay.sdk.m.u.e.d("mspl", sb.toString());
                        this.c.onPayResult(h5Pay);
                    }
                }).start();
            }
            return TextUtils.isEmpty((CharSequence)fetchOrderInfoFromH5PayUrl) ^ true;
        }
    }
    
    public Map<String, String> payV2(String s, final boolean b) {
        synchronized (this) {
            com.alipay.sdk.m.s.a a;
            if (com.alipay.sdk.m.u.b.a()) {
                a = null;
                s = com.alipay.sdk.m.j.b.b();
            }
            else {
                a = new com.alipay.sdk.m.s.a((Context)this.a, s, "payV2");
                s = this.a(a, s, b);
            }
            return (Map<String, String>)l.a(a, s);
        }
    }
    
    public void showLoading() {
        final a b = this.b;
        if (b != null) {
            b.d();
        }
    }
    
    public class c
    {
        public String a;
        public String b;
        public String c;
        public String d;
        public final PayTask e;
        
        public c(final PayTask e) {
            this.e = e;
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = "";
        }
        
        public String a() {
            return this.c;
        }
        
        public void a(final String c) {
            this.c = c;
        }
        
        public String b() {
            return this.a;
        }
        
        public void b(final String a) {
            this.a = a;
        }
        
        public String c() {
            return this.b;
        }
        
        public void c(final String b) {
            this.b = b;
        }
        
        public String d() {
            return this.d;
        }
        
        public void d(final String d) {
            this.d = d;
        }
    }
}
