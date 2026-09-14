package com.alipay.sdk.m.m;

import java.util.Iterator;
import java.util.ArrayList;
import org.json.JSONArray;
import com.alipay.sdk.m.u.n;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.u.e;
import com.alipay.sdk.m.u.j;
import com.alipay.sdk.m.s.b;
import org.json.JSONException;
import java.util.List;
import org.json.JSONObject;

public final class a
{
    public static final String A = "DynCon";
    public static final int B = 10000;
    public static final String C = "https://h5.m.taobao.com/mlapp/olist.html";
    public static final int D = 10;
    public static final boolean E = true;
    public static final boolean F = false;
    public static final boolean G = true;
    public static final boolean H = true;
    public static final boolean I = false;
    public static final boolean J = false;
    public static final boolean K = false;
    public static final boolean L = false;
    public static final boolean M = true;
    public static final String N = "";
    public static final boolean O = false;
    public static final boolean P = false;
    public static final int Q = 1000;
    public static final boolean R = true;
    public static final String S = "";
    public static final boolean T = false;
    public static final boolean U = false;
    public static final int V = 1000;
    public static final int W = 20000;
    public static final boolean X = false;
    public static final String Y = "alipay_cashier_dynamic_config";
    public static final String Z = "timeout";
    public static final String a0 = "h5_port_degrade";
    public static final String b0 = "st_sdk_config";
    public static final String c0 = "tbreturl";
    public static final String d0 = "launchAppSwitch";
    public static final String e0 = "configQueryInterval";
    public static final String f0 = "deg_log_mcgw";
    public static final String g0 = "deg_start_srv_first";
    public static final String h0 = "prev_jump_dual";
    public static final String i0 = "bind_use_imp";
    public static final String j0 = "retry_bnd_once";
    public static final String k0 = "skip_trans";
    public static final String l0 = "start_trans";
    public static final String m0 = "up_before_pay";
    public static final String n0 = "lck_k";
    public static final String o0 = "use_sc_lck_a";
    public static final String p0 = "utdid_factor";
    public static final String q0 = "cfg_max_time";
    public static final String r0 = "get_oa_id";
    public static final String s0 = "notifyFailApp";
    public static final String t0 = "startactivity_in_ui_thread";
    public static final String u0 = "intercept_batch";
    public static final String v0 = "bind_with_startActivity";
    public static final String w0 = "enableStartActivityFallback";
    public static final String x0 = "enableBindExFallback";
    public static a y0;
    public int a;
    public boolean b;
    public String c;
    public int d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public String p;
    public String q;
    public boolean r;
    public boolean s;
    public boolean t;
    public int u;
    public boolean v;
    public JSONObject w;
    public boolean x;
    public List<b> y;
    public int z;
    
    public a() {
        this.a = 10000;
        this.b = false;
        this.c = "https://h5.m.taobao.com/mlapp/olist.html";
        this.d = 10;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = true;
        this.j = true;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = true;
        this.p = "";
        this.q = "";
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 1000;
        this.v = false;
        this.x = true;
        this.y = null;
        this.z = -1;
    }
    
    private JSONObject A() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("timeout", this.i());
        jsonObject.put("h5_port_degrade", this.v());
        jsonObject.put("tbreturl", (Object)this.o());
        jsonObject.put("configQueryInterval", this.d());
        jsonObject.put("launchAppSwitch", (Object)com.alipay.sdk.m.m.a.b.a(this.j()));
        jsonObject.put("intercept_batch", this.g());
        jsonObject.put("deg_log_mcgw", this.e());
        jsonObject.put("deg_start_srv_first", this.f());
        jsonObject.put("prev_jump_dual", this.k());
        jsonObject.put("bind_use_imp", this.b());
        jsonObject.put("retry_bnd_once", this.l());
        jsonObject.put("skip_trans", this.n());
        jsonObject.put("start_trans", this.x());
        jsonObject.put("up_before_pay", this.p());
        jsonObject.put("use_sc_lck_a", this.m());
        jsonObject.put("lck_k", (Object)this.h());
        jsonObject.put("bind_with_startActivity", (Object)this.c());
        jsonObject.put("cfg_max_time", this.y());
        jsonObject.put("get_oa_id", this.u());
        jsonObject.put("notifyFailApp", this.s());
        jsonObject.put("enableStartActivityFallback", this.t());
        jsonObject.put("enableBindExFallback", this.r());
        jsonObject.put("startactivity_in_ui_thread", this.w());
        jsonObject.put("ap_args", (Object)this.a());
        return jsonObject;
    }
    
    private void a(final com.alipay.sdk.m.s.a a) {
        try {
            com.alipay.sdk.m.u.j.b(a, com.alipay.sdk.m.s.b.d().b(), "alipay_cashier_dynamic_config", this.A().toString());
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.u.e.a((Throwable)ex);
        }
    }
    
    private void a(final com.alipay.sdk.m.s.a a, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final JSONObject optJSONObject = jsonObject.optJSONObject("st_sdk_config");
            com.alipay.sdk.m.u.a.a(a, optJSONObject, com.alipay.sdk.m.u.a.a(a, jsonObject));
            if (optJSONObject != null) {
                this.a(optJSONObject);
            }
            else {
                com.alipay.sdk.m.u.e.e("DynCon", "empty config");
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
        }
    }
    
    private void a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        try {
            this.a(new JSONObject(s));
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
        }
    }
    
    private void a(final JSONObject jsonObject) {
        this.a = jsonObject.optInt("timeout", 10000);
        this.b = jsonObject.optBoolean("h5_port_degrade", false);
        this.c = jsonObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
        this.d = jsonObject.optInt("configQueryInterval", 10);
        this.y = com.alipay.sdk.m.m.a.b.a(jsonObject.optJSONArray("launchAppSwitch"));
        this.e = jsonObject.optBoolean("intercept_batch", true);
        this.h = jsonObject.optBoolean("deg_log_mcgw", false);
        this.i = jsonObject.optBoolean("deg_start_srv_first", true);
        this.j = jsonObject.optBoolean("prev_jump_dual", true);
        this.k = jsonObject.optBoolean("bind_use_imp", false);
        this.l = jsonObject.optBoolean("retry_bnd_once", false);
        this.m = jsonObject.optBoolean("skip_trans", false);
        this.n = jsonObject.optBoolean("start_trans", false);
        this.o = jsonObject.optBoolean("up_before_pay", true);
        this.p = jsonObject.optString("lck_k", "");
        this.t = jsonObject.optBoolean("use_sc_lck_a", false);
        this.v = jsonObject.optBoolean("notifyFailApp", false);
        this.q = jsonObject.optString("bind_with_startActivity", "");
        this.u = jsonObject.optInt("cfg_max_time", 1000);
        this.x = jsonObject.optBoolean("get_oa_id", true);
        this.r = jsonObject.optBoolean("enableStartActivityFallback", false);
        this.s = jsonObject.optBoolean("enableBindExFallback", false);
        this.f = jsonObject.optBoolean("startactivity_in_ui_thread", false);
        this.w = jsonObject.optJSONObject("ap_args");
    }
    
    private int y() {
        return this.u;
    }
    
    public static a z() {
        if (a.y0 == null) {
            (a.y0 = new a()).q();
        }
        return a.y0;
    }
    
    public JSONObject a() {
        return this.w;
    }
    
    public void a(final com.alipay.sdk.m.s.a a, final Context context, final boolean b, int y) {
        final StringBuilder sb = new StringBuilder();
        sb.append("oncfg|");
        sb.append(b);
        sb.append("|");
        sb.append(y);
        com.alipay.sdk.m.k.a.a(a, "biz", sb.toString());
        final Runnable runnable = (Runnable)new Runnable(this, a, context, b, y) {
            public final com.alipay.sdk.m.s.a a;
            public final Context b;
            public final boolean c;
            public final int d;
            public final a e;
            
            public void run() {
                try {
                    final com.alipay.sdk.m.p.b a = ((com.alipay.sdk.m.p.e)new com.alipay.sdk.m.q.b()).a(this.a, this.b);
                    if (a != null) {
                        this.e.a(this.a, a.a());
                        this.e.a(com.alipay.sdk.m.s.a.h());
                        final com.alipay.sdk.m.s.a a2 = this.a;
                        final StringBuilder sb = new StringBuilder();
                        sb.append("offcfg|");
                        sb.append(this.c);
                        sb.append("|");
                        sb.append(this.d);
                        com.alipay.sdk.m.k.a.a(a2, "biz", sb.toString());
                    }
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                }
            }
        };
        if (b && !com.alipay.sdk.m.u.n.h()) {
            y = this.y();
            if (!com.alipay.sdk.m.u.n.a((long)y, (Runnable)runnable, "AlipayDCPBlok")) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(y);
                com.alipay.sdk.m.k.a.b(a, "biz", "LogAppFetchConfigTimeout", sb2.toString());
            }
        }
        else {
            final Thread thread = new Thread((Runnable)runnable);
            thread.setName("AlipayDCP");
            thread.start();
        }
    }
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public boolean a(final Context context, final int n) {
        if (this.z == -1) {
            this.z = n.a();
            com.alipay.sdk.m.u.j.b(com.alipay.sdk.m.s.a.h(), context, "utdid_factor", String.valueOf(this.z));
        }
        return this.z < n;
    }
    
    public boolean b() {
        return this.k;
    }
    
    public String c() {
        return this.q;
    }
    
    public int d() {
        return this.d;
    }
    
    public boolean e() {
        return this.h;
    }
    
    public boolean f() {
        return this.i;
    }
    
    public boolean g() {
        return this.e;
    }
    
    public String h() {
        return this.p;
    }
    
    public int i() {
        final int a = this.a;
        if (a >= 1000 && a <= 20000) {
            final StringBuilder sb = new StringBuilder();
            sb.append("time = ");
            sb.append(this.a);
            com.alipay.sdk.m.u.e.b("DynCon", sb.toString());
            return this.a;
        }
        com.alipay.sdk.m.u.e.b("DynCon", "time(def) = 10000");
        return 10000;
    }
    
    public List<b> j() {
        return this.y;
    }
    
    public boolean k() {
        return this.j;
    }
    
    public boolean l() {
        return this.l;
    }
    
    public boolean m() {
        return this.t;
    }
    
    public boolean n() {
        return this.m;
    }
    
    public String o() {
        return this.c;
    }
    
    public boolean p() {
        return this.o;
    }
    
    public void q() {
        final Context b = com.alipay.sdk.m.s.b.d().b();
        final String a = com.alipay.sdk.m.u.j.a(com.alipay.sdk.m.s.a.h(), b, "alipay_cashier_dynamic_config", (String)null);
        final String a2 = com.alipay.sdk.m.u.j.a(com.alipay.sdk.m.s.a.h(), b, "utdid_factor", "-1");
        while (true) {
            try {
                this.z = Integer.parseInt(a2);
                this.a(a);
            }
            catch (final Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public boolean r() {
        return this.s;
    }
    
    public boolean s() {
        return this.v;
    }
    
    public boolean t() {
        return this.r;
    }
    
    public boolean u() {
        return this.x;
    }
    
    public boolean v() {
        return this.b;
    }
    
    public boolean w() {
        return this.f;
    }
    
    public boolean x() {
        return this.n;
    }
    
    public static final class b
    {
        public final String a;
        public final int b;
        public final String c;
        
        public b(final String a, final int b, final String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public static b a(final JSONObject jsonObject) {
            if (jsonObject == null) {
                return null;
            }
            return new b(jsonObject.optString("pn"), jsonObject.optInt("v", 0), jsonObject.optString("pk"));
        }
        
        public static List<b> a(final JSONArray jsonArray) {
            if (jsonArray == null) {
                return null;
            }
            final ArrayList list = new ArrayList();
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                final b a = a(jsonArray.optJSONObject(i));
                if (a != null) {
                    list.add((Object)a);
                }
            }
            return (List<b>)list;
        }
        
        public static JSONArray a(final List<b> list) {
            if (list == null) {
                return null;
            }
            final JSONArray jsonArray = new JSONArray();
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)a((b)iterator.next()));
            }
            return jsonArray;
        }
        
        public static JSONObject a(final b b) {
            if (b == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", (Object)b.a).put("v", b.b).put("pk", (Object)b.c);
            }
            catch (final JSONException ex) {
                e.a((Throwable)ex);
                return null;
            }
        }
        
        @Override
        public String toString() {
            return String.valueOf((Object)a(this));
        }
    }
}
