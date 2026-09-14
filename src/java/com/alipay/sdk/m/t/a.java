package com.alipay.sdk.m.t;

import android.content.SharedPreferences;
import com.alipay.sdk.m.u.e;
import org.json.JSONObject;
import java.util.Random;
import android.text.TextUtils;
import android.content.Context;

public class a
{
    public static final String g = "alipay_tid_storage";
    public static final String h = "tidinfo";
    public static final String i = "tid";
    public static final String j = "client_key";
    public static final String k = "timestamp";
    public static final String l = "vimei";
    public static final String m = "vimsi";
    public static Context n;
    public static a o;
    public String a;
    public String b;
    public long c;
    public String d;
    public String e;
    public boolean f;
    
    public a() {
        this.f = false;
    }
    
    public static a a(final Context context) {
        synchronized (a.class) {
            if (a.o == null) {
                a.o = new a();
            }
            if (a.n == null) {
                a.o.b(context);
            }
            return a.o;
        }
    }
    
    private void a(final String a, final String b, final String d, final String e, final Long n) {
        if (this.a(a, b, d, e)) {
            return;
        }
        this.a = a;
        this.b = b;
        this.d = d;
        this.e = e;
        if (n == null) {
            this.c = System.currentTimeMillis();
        }
        else {
            this.c = n;
        }
        this.n();
    }
    
    private boolean a(final String s, final String s2, final String s3, final String s4) {
        return TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s3) || TextUtils.isEmpty((CharSequence)s4);
    }
    
    private void b(final Context context) {
        if (context != null) {
            com.alipay.sdk.m.t.a.n = context.getApplicationContext();
        }
        if (this.f) {
            return;
        }
        this.f = true;
        this.l();
    }
    
    public static /* synthetic */ Context j() {
        return a.n;
    }
    
    private String k() {
        final String hexString = Long.toHexString(System.currentTimeMillis());
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        sb.append(hexString);
        sb.append(random.nextInt(9000) + 1000);
        return sb.toString();
    }
    
    private void l() {
        Object o = System.currentTimeMillis();
        Object a = null;
        final String s = null;
        String optString = null;
        String optString2 = null;
        String optString4 = null;
        Label_0170: {
            Label_0156: {
                try {
                    final String a2 = com.alipay.sdk.m.t.a.a.a("alipay_tid_storage", "tidinfo", true);
                    if (!TextUtils.isEmpty((CharSequence)a2)) {
                        final JSONObject jsonObject = new JSONObject(a2);
                        optString = jsonObject.optString("tid", "");
                        try {
                            optString2 = jsonObject.optString("client_key", "");
                            try {
                                a = (o = jsonObject.optLong("timestamp", System.currentTimeMillis()));
                                final String optString3 = jsonObject.optString("vimei", "");
                                try {
                                    optString4 = jsonObject.optString("vimsi", "");
                                    o = a;
                                    a = optString;
                                    optString = optString3;
                                }
                                catch (final Exception ex) {
                                    o = a;
                                    a = optString;
                                    optString = optString3;
                                }
                            }
                            catch (final Exception ex) {
                                final String s2 = null;
                                a = optString;
                                optString = s2;
                            }
                        }
                        catch (final Exception ex) {
                            a = optString;
                            break Label_0156;
                        }
                    }
                    optString4 = null;
                    optString2 = (optString = null);
                    break Label_0170;
                }
                catch (final Exception ex) {
                    a = null;
                }
            }
            optString2 = null;
            optString = null;
            final Exception ex;
            com.alipay.sdk.m.u.e.a((Throwable)ex);
            optString4 = s;
        }
        com.alipay.sdk.m.u.e.b("mspl", "tid_str: load");
        if (this.a((String)a, optString2, optString, optString4)) {
            this.m();
        }
        else {
            this.a = (String)a;
            this.b = optString2;
            this.c = (long)o;
            this.d = optString;
            this.e = optString4;
        }
    }
    
    private void m() {
        this.a = "";
        this.b = this.b();
        this.c = System.currentTimeMillis();
        this.d = this.k();
        this.e = this.k();
        com.alipay.sdk.m.t.a.a.b("alipay_tid_storage", "tidinfo");
    }
    
    private void n() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("tid", (Object)this.a);
            jsonObject.put("client_key", (Object)this.b);
            jsonObject.put("timestamp", this.c);
            jsonObject.put("vimei", (Object)this.d);
            jsonObject.put("vimsi", (Object)this.e);
            com.alipay.sdk.m.t.a.a.a("alipay_tid_storage", "tidinfo", jsonObject.toString(), true);
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.u.e.a((Throwable)ex);
        }
    }
    
    private void o() {
    }
    
    public void a() {
        com.alipay.sdk.m.u.e.b("mspl", "tid_str: del");
        this.m();
    }
    
    public void a(final String a, final String b) {
        com.alipay.sdk.m.u.e.b("mspl", "tid_str: save");
        if (!TextUtils.isEmpty((CharSequence)a)) {
            if (!TextUtils.isEmpty((CharSequence)b)) {
                this.a = a;
                this.b = b;
                this.c = System.currentTimeMillis();
                this.n();
                this.o();
            }
        }
    }
    
    public String b() {
        String s2;
        final String s = s2 = Long.toHexString(System.currentTimeMillis());
        if (s.length() > 10) {
            s2 = s.substring(s.length() - 10);
        }
        return s2;
    }
    
    public String c() {
        return this.b;
    }
    
    public String d() {
        return this.a;
    }
    
    public Long e() {
        return this.c;
    }
    
    public String f() {
        return this.d;
    }
    
    public String g() {
        return this.e;
    }
    
    public boolean h() {
        return this.i();
    }
    
    public boolean i() {
        return TextUtils.isEmpty((CharSequence)this.a) || TextUtils.isEmpty((CharSequence)this.b) || TextUtils.isEmpty((CharSequence)this.d) || TextUtils.isEmpty((CharSequence)this.e);
    }
    
    public static class a
    {
        public static String a() {
            String s = null;
            try {
                com.alipay.sdk.m.t.a.j().getApplicationContext().getPackageName();
            }
            finally {
                final Throwable t;
                e.a(t);
                s = "";
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("0000000000000000000000000000");
            return sb.toString().substring(0, 24);
        }
        
        public static String a(String s, String s2, final boolean b) {
            if (com.alipay.sdk.m.t.a.j() == null) {
                return null;
            }
            s2 = (s = com.alipay.sdk.m.t.a.j().getSharedPreferences(s, 0).getString(s2, (String)null));
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                s = s2;
                if (b) {
                    s2 = (s = com.alipay.sdk.m.n.e.a(a(), s2, s2));
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        e.b("mspl", "tid_str: pref failed");
                        s = s2;
                    }
                }
            }
            e.b("mspl", "tid_str: from local");
            return s;
        }
        
        public static void a(final String s, final String s2, final String s3) {
            a(s, s2, s3, true);
        }
        
        public static void a(String b, final String s, final String s2, final boolean b2) {
            if (com.alipay.sdk.m.t.a.j() == null) {
                return;
            }
            final SharedPreferences sharedPreferences = com.alipay.sdk.m.t.a.j().getSharedPreferences(b, 0);
            b = s2;
            if (b2) {
                final String a = a();
                b = com.alipay.sdk.m.n.e.b(a, s2, s2);
                if (TextUtils.isEmpty((CharSequence)b)) {
                    String.format("LocalPreference::putLocalPreferences failed %s\uff0c%s", new Object[] { s2, a });
                }
            }
            sharedPreferences.edit().putString(s, b).apply();
        }
        
        public static boolean a(final String s, final String s2) {
            return com.alipay.sdk.m.t.a.j() != null && com.alipay.sdk.m.t.a.j().getSharedPreferences(s, 0).contains(s2);
        }
        
        public static void b(final String s, final String s2) {
            if (com.alipay.sdk.m.t.a.j() == null) {
                return;
            }
            com.alipay.sdk.m.t.a.j().getSharedPreferences(s, 0).edit().remove(s2).apply();
        }
        
        public static boolean c(final String s, final String s2) {
            return com.alipay.sdk.m.t.a.j() != null && com.alipay.sdk.m.t.a.j().getSharedPreferences(s, 0).contains(s2);
        }
        
        public static String d(final String s, final String s2) {
            return a(s, s2, true);
        }
    }
}
