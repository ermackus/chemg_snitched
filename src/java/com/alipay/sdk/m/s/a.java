package com.alipay.sdk.m.s;

import java.io.Serializable;
import android.content.Intent;
import java.util.UUID;
import java.util.Locale;
import org.json.JSONException;
import java.util.HashMap;
import org.json.JSONObject;
import android.content.pm.PackageInfo;
import com.alipay.sdk.m.u.e;
import com.alipay.sdk.m.u.n;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.m.k.b;
import android.content.pm.ActivityInfo;
import android.content.Context;

public class a
{
    public static final String A = "act_info";
    public static final String B = "UTF-8";
    public static final String C = "new_external_info==";
    public static final String m = "\"&";
    public static final String n = "&";
    public static final String o = "bizcontext=\"";
    public static final String p = "bizcontext=";
    public static final String q = "\"";
    public static final String r = "appkey";
    public static final String s = "ty";
    public static final String t = "sv";
    public static final String u = "an";
    public static final String v = "setting";
    public static final String w = "av";
    public static final String x = "sdk_start_time";
    public static final String y = "extInfo";
    public static final String z = "ap_link_token";
    public String a;
    public String b;
    public Context c;
    public final String d;
    public final long e;
    public final int f;
    public final String g;
    public boolean h;
    public boolean i;
    public boolean j;
    public final ActivityInfo k;
    public final b l;
    
    public a(final Context context, final String s, String string) {
        this.a = "";
        this.b = "";
        this.c = null;
        this.h = false;
        this.i = false;
        this.j = false;
        final boolean empty = TextUtils.isEmpty((CharSequence)string);
        this.l = new b(context, empty);
        this.d = b(s, this.b);
        this.e = SystemClock.elapsedRealtime();
        this.f = com.alipay.sdk.m.u.n.g();
        this.k = com.alipay.sdk.m.u.n.a(context);
        this.g = string;
        if (!empty) {
            final StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("|");
            sb.append(this.d);
            com.alipay.sdk.m.k.a.a(this, "biz", "eptyp", sb.toString());
            if (this.k != null) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(this.k.name);
                sb2.append("|");
                sb2.append(this.k.launchMode);
                string = sb2.toString();
            }
            else {
                string = "null";
            }
            com.alipay.sdk.m.k.a.a(this, "biz", "actInfo", string);
            com.alipay.sdk.m.k.a.a(this, "biz", "sys", com.alipay.sdk.m.u.n.a(this));
            com.alipay.sdk.m.k.a.a(this, "biz", "sdkv", "9eef93b-clean");
        }
        try {
            this.c = context.getApplicationContext();
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.a = packageInfo.versionName;
            this.b = packageInfo.packageName;
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.u.e.a((Throwable)ex);
        }
        if (!empty) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("u");
            sb3.append(com.alipay.sdk.m.u.n.g());
            com.alipay.sdk.m.k.a.a(this, "biz", sb3.toString());
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("");
            sb4.append(SystemClock.elapsedRealtime());
            com.alipay.sdk.m.k.a.a(this, "biz", "PgApiInvoke", sb4.toString());
            com.alipay.sdk.m.k.a.a(context, this, s, this.d);
        }
        if (!empty && com.alipay.sdk.m.m.a.z().p()) {
            com.alipay.sdk.m.m.a.z().a(this, this.c, true, 2);
        }
    }
    
    private String a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(this.a(new JSONObject()));
        sb.append(s2);
        return sb.toString();
    }
    
    private String a(String s, final String s2, final String s3) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final String s4 = null;
        if (empty) {
            return null;
        }
        final String[] split = s.split(s2);
        int n = 0;
        while (true) {
            s = s4;
            if (n >= split.length) {
                break;
            }
            if (!TextUtils.isEmpty((CharSequence)split[n]) && split[n].startsWith(s3)) {
                s = split[n];
                break;
            }
            ++n;
        }
        return s;
    }
    
    public static HashMap<String, String> a(final a a) {
        final HashMap hashMap = new HashMap();
        if (a != null) {
            hashMap.put((Object)"sdk_ver", (Object)"15.8.17");
            hashMap.put((Object)"app_name", (Object)a.b);
            hashMap.put((Object)"token", (Object)a.d);
            hashMap.put((Object)"call_type", (Object)a.g);
            hashMap.put((Object)"ts_api_invoke", (Object)String.valueOf(a.e));
            com.alipay.sdk.m.u.a.a(a, (HashMap<String, String>)hashMap);
        }
        return (HashMap<String, String>)hashMap;
    }
    
    private String b(final String s) throws JSONException {
        return this.a(new JSONObject(s));
    }
    
    public static String b(String s, final String s2) {
        try {
            final Locale default1 = Locale.getDefault();
            String s3 = s;
            if (s == null) {
                s3 = "";
            }
            if ((s = s2) == null) {
                s = "";
            }
            s = com.alipay.sdk.m.u.n.g(String.format(default1, "%s%s%d%s", new Object[] { s3, s, System.currentTimeMillis(), UUID.randomUUID().toString() }));
            s = String.format("EP%s%s_%s", new Object[] { "1", s, System.currentTimeMillis() });
        }
        finally {
            s = "-";
        }
        return s;
    }
    
    private String b(String s, final String s2, final String s3) throws JSONException {
        s = s.substring(s2.length());
        final int length = s.length();
        final int length2 = s3.length();
        boolean b = false;
        s = s.substring(0, length - length2);
        JSONObject jsonObject;
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            jsonObject = new JSONObject(s.substring(1, s.length() - 1));
            b = true;
        }
        else {
            jsonObject = new JSONObject(s);
        }
        final String s4 = s = this.a(jsonObject);
        if (b) {
            final StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(s4);
            sb.append("\"");
            s = sb.toString();
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s2);
        sb2.append(s);
        sb2.append(s3);
        return sb2.toString();
    }
    
    private String c(String s) {
        try {
            final String a = this.a(s, "&", "bizcontext=");
            if (TextUtils.isEmpty((CharSequence)a)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("&");
                sb.append(this.a("bizcontext=", ""));
                s = sb.toString();
            }
            else {
                final int index = s.indexOf(a);
                final String substring = s.substring(0, index);
                final String substring2 = s.substring(index + a.length());
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(substring);
                sb2.append(this.b(a, "bizcontext=", ""));
                sb2.append(substring2);
                s = sb2.toString();
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this, "biz", "fmt1", t, s);
        }
        return s;
    }
    
    private String d(String string) {
        try {
            final JSONObject jsonObject = new JSONObject(string.substring(19));
            jsonObject.put("bizcontext", (Object)this.b(jsonObject.optString("bizcontext")));
            final StringBuilder sb = new StringBuilder();
            sb.append("new_external_info==");
            sb.append(jsonObject.toString());
            string = sb.toString();
            return string;
        }
        finally {
            return string;
        }
    }
    
    private String e(String s) {
        try {
            final String a = this.a(s, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty((CharSequence)a)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("&");
                sb.append(this.a("bizcontext=\"", "\""));
                s = sb.toString();
            }
            else {
                String string = a;
                if (!a.endsWith("\"")) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(a);
                    sb2.append("\"");
                    string = sb2.toString();
                }
                final int index = s.indexOf(string);
                final String substring = s.substring(0, index);
                final String substring2 = s.substring(index + string.length());
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(substring);
                sb3.append(this.b(string, "bizcontext=\"", "\""));
                sb3.append(substring2);
                s = sb3.toString();
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this, "biz", "fmt2", t, s);
        }
        return s;
    }
    
    private boolean f(final String s) {
        return s.contains((CharSequence)"\"&") ^ true;
    }
    
    private JSONObject g() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ap_link_token", (Object)this.d);
            return jsonObject;
        }
        finally {
            return jsonObject;
        }
    }
    
    public static a h() {
        return null;
    }
    
    public Context a() {
        return this.c;
    }
    
    public String a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return s;
        }
        if (s.startsWith("new_external_info==")) {
            return this.d(s);
        }
        if (this.f(s)) {
            return this.c(s);
        }
        return this.e(s);
    }
    
    public String a(final JSONObject jsonObject) {
        try {
            if (!jsonObject.has("appkey")) {
                jsonObject.put("appkey", (Object)"2014052600006128");
            }
            if (!jsonObject.has("ty")) {
                jsonObject.put("ty", (Object)"and_lite");
            }
            if (!jsonObject.has("sv")) {
                jsonObject.put("sv", (Object)"h.a.3.8.17");
            }
            if (!jsonObject.has("an")) {
                jsonObject.put("an", (Object)this.b);
            }
            if (!jsonObject.has("av")) {
                jsonObject.put("av", (Object)this.a);
            }
            if (!jsonObject.has("sdk_start_time")) {
                jsonObject.put("sdk_start_time", System.currentTimeMillis());
            }
            if (!jsonObject.has("extInfo")) {
                jsonObject.put("extInfo", (Object)this.g());
            }
            if (!jsonObject.has("act_info")) {
                String string;
                if (this.k != null) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(this.k.name);
                    sb.append("|");
                    sb.append(this.k.launchMode);
                    string = sb.toString();
                }
                else {
                    string = "null";
                }
                jsonObject.put("act_info", (Object)string);
            }
            return jsonObject.toString();
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(this, "biz", "fmt3", t, String.valueOf((Object)jsonObject));
            com.alipay.sdk.m.u.e.a(t);
            String string2;
            if (jsonObject != null) {
                string2 = jsonObject.toString();
            }
            else {
                string2 = "{}";
            }
            return string2;
        }
    }
    
    public void a(final boolean i) {
        this.i = i;
    }
    
    public String b() {
        return this.b;
    }
    
    public void b(final boolean h) {
        this.h = h;
    }
    
    public String c() {
        return this.a;
    }
    
    public void c(final boolean j) {
        this.j = j;
    }
    
    public boolean d() {
        return this.i;
    }
    
    public boolean e() {
        return this.h;
    }
    
    public boolean f() {
        return this.j;
    }
    
    public static final class a
    {
        public static final HashMap<UUID, com.alipay.sdk.m.s.a> a;
        public static final HashMap<String, com.alipay.sdk.m.s.a> b;
        public static final String c = "i_uuid_b_c";
        
        static {
            a = new HashMap();
            b = new HashMap();
        }
        
        public static com.alipay.sdk.m.s.a a(final Intent intent) {
            if (intent == null) {
                return null;
            }
            final Serializable serializableExtra = intent.getSerializableExtra("i_uuid_b_c");
            if (serializableExtra instanceof UUID) {
                return (com.alipay.sdk.m.s.a)com.alipay.sdk.m.s.a.a.a.remove((Object)serializableExtra);
            }
            return null;
        }
        
        public static com.alipay.sdk.m.s.a a(final String s) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                return null;
            }
            return (com.alipay.sdk.m.s.a)com.alipay.sdk.m.s.a.a.b.remove((Object)s);
        }
        
        public static void a(final com.alipay.sdk.m.s.a a, final Intent intent) {
            if (a != null) {
                if (intent != null) {
                    final UUID randomUUID = UUID.randomUUID();
                    a.a.put((Object)randomUUID, (Object)a);
                    intent.putExtra("i_uuid_b_c", (Serializable)randomUUID);
                }
            }
        }
        
        public static void a(final com.alipay.sdk.m.s.a a, final String s) {
            if (a != null) {
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    a.b.put((Object)s, (Object)a);
                }
            }
        }
    }
}
