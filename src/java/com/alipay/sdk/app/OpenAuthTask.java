package com.alipay.sdk.app;

import android.content.pm.PackageInfo;
import android.net.Uri;
import com.alipay.sdk.m.s.a$a;
import android.content.Intent;
import android.text.TextUtils;
import java.util.List;
import java.util.HashMap;
import com.alipay.sdk.m.u.n;
import android.os.SystemClock;
import com.alipay.sdk.m.s.a;
import com.alipay.sdk.m.u.e;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Map$Entry;
import android.net.Uri$Builder;
import org.json.JSONException;
import android.util.Base64;
import java.nio.charset.Charset;
import org.json.JSONObject;
import android.content.Context;
import com.alipay.sdk.m.s.b;
import android.os.Looper;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Handler;
import android.app.Activity;
import java.util.Map;

public final class OpenAuthTask
{
    public static final int Duplex = 5000;
    public static final int NOT_INSTALLED = 4001;
    public static final int OK = 9000;
    public static final int SYS_ERR = 4000;
    public static final Map<String, Callback> e;
    public static long f = 0L;
    public static final int g = 122;
    public volatile boolean a;
    public final Activity b;
    public Callback c;
    public final Handler d;
    
    static {
        e = (Map)new ConcurrentHashMap();
        OpenAuthTask.f = -1L;
    }
    
    public OpenAuthTask(final Activity b) {
        this.a = false;
        this.d = new Handler(Looper.getMainLooper());
        this.b = b;
        com.alipay.sdk.m.s.b.d().a((Context)b);
    }
    
    public static /* synthetic */ Callback a(final OpenAuthTask openAuthTask) {
        return openAuthTask.c;
    }
    
    private String a(final long n, final String s, final BizType bizType, final String s2) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("startTime", (Object)String.valueOf(n));
        jsonObject.put("session", (Object)s);
        jsonObject.put("package", (Object)this.b.getPackageName());
        if (bizType != null) {
            jsonObject.put("appId", (Object)BizType.access$100(bizType));
        }
        jsonObject.put("sdkVersion", (Object)"h.a.3.8.17");
        jsonObject.put("mqpURL", (Object)s2);
        return Base64.encodeToString(jsonObject.toString().getBytes(Charset.forName("UTF-8")), 2);
    }
    
    private String a(final BizType bizType, final Map<String, String> map) {
        if (bizType != null) {
            final Uri$Builder appendQueryParameter = new Uri$Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", BizType.access$100(bizType));
            if (OpenAuthTask$a.a[bizType.ordinal()] == 1) {
                appendQueryParameter.appendQueryParameter("appClearTop", "false").appendQueryParameter("startMultApp", "YES");
            }
            for (final Map$Entry map$Entry : map.entrySet()) {
                appendQueryParameter.appendQueryParameter((String)map$Entry.getKey(), (String)map$Entry.getValue());
            }
            return appendQueryParameter.build().toString();
        }
        throw new RuntimeException("missing bizType");
    }
    
    public static void a(final String s, final int n, final String s2, final Bundle bundle) {
        final Callback callback = (Callback)OpenAuthTask.e.remove((Object)s);
        if (callback != null) {
            try {
                callback.onResult(n, s2, bundle);
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.u.e.a(t);
            }
        }
    }
    
    private boolean a(final a a, String a2, final BizType bizType, Map<String, String> a3, final boolean b) {
        if (this.a) {
            this.d.post((Runnable)new b(4000, "\u8be5 OpenAuthTask \u5df2\u5728\u6267\u884c", null, null));
            return true;
        }
        this.a = true;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - OpenAuthTask.f <= 3000L) {
            this.d.post((Runnable)new b(5000, "3s \u5185\u91cd\u590d\u652f\u4ed8", null, null));
            return true;
        }
        OpenAuthTask.f = elapsedRealtime;
        com.alipay.sdk.m.j.a.a("");
        final String a4 = n.a(32);
        final HashMap hashMap = new HashMap((Map)a3);
        ((Map)hashMap).put((Object)"mqpPkgName", (Object)this.b.getPackageName());
        ((Map)hashMap).put((Object)"mqpScene", (Object)"sdk");
        final List<com.alipay.sdk.m.m.a.b> j = com.alipay.sdk.m.m.a.z().j();
        List<com.alipay.sdk.m.m.a.b> d;
        if (!com.alipay.sdk.m.m.a.z().g || (d = j) == null) {
            d = com.alipay.sdk.m.j.a.d;
        }
        a3 = n.a(a, (Context)this.b, (List)d);
        if (a3 != null && !a3.a(a) && !a3.a()) {
            final PackageInfo a5 = a3.a;
            if (a5 != null) {
                if (a5.versionCode >= 122) {
                    try {
                        final HashMap a6 = a.a(a);
                        ((Map)a6).put((Object)"ts_scheme", (Object)String.valueOf(SystemClock.elapsedRealtime()));
                        ((Map)hashMap).put((Object)"mqpLoc", (Object)new JSONObject((Map)a6).toString());
                    }
                    finally {
                        final Throwable t;
                        com.alipay.sdk.m.k.a.a(a, "biz", "OpenAuthLocEx", t);
                    }
                    try {
                        final String a7 = this.a(bizType, (Map<String, String>)hashMap);
                        OpenAuthTask.e.put((Object)a4, (Object)this.c);
                        a2 = null;
                        try {
                            a2 = this.a(elapsedRealtime, a4, bizType, a7);
                        }
                        catch (final JSONException ex) {
                            com.alipay.sdk.m.k.a.a(a, "biz", "JSONEx", (Throwable)ex);
                        }
                        if (TextUtils.isEmpty((CharSequence)a2)) {
                            this.d.post((Runnable)new b(4000, "\u53c2\u6570\u9519\u8bef", null, null));
                            return true;
                        }
                        final Intent intent = new Intent("android.intent.action.VIEW", new Uri$Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20001129").appendQueryParameter("payload", a2).build());
                        intent.addFlags(268435456);
                        intent.setPackage(a3.a.packageName);
                        try {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(elapsedRealtime);
                            com.alipay.sdk.m.k.a.a(a, "biz", "PgOpenStarting", sb.toString());
                            a$a.a(a, a4);
                            this.b.startActivity(intent);
                        }
                        finally {
                            final Throwable t2;
                            com.alipay.sdk.m.k.a.a(a, "biz", "StartWalletEx", t2);
                        }
                        return false;
                    }
                    finally {
                        this.d.post((Runnable)new b(4000, "\u4e1a\u52a1\u53c2\u6570\u9519\u8bef", null, null));
                        return true;
                    }
                }
            }
        }
        if (b) {
            ((Map)hashMap).put((Object)"mqpScheme", (Object)String.valueOf((Object)a2));
            ((Map)hashMap).put((Object)"mqpNotifyName", (Object)a4);
            ((Map)hashMap).put((Object)"mqpScene", (Object)"landing");
            final String a8 = this.a(bizType, (Map<String, String>)hashMap);
            final Intent intent2 = new Intent((Context)this.b, (Class)H5OpenAuthActivity.class);
            intent2.putExtra("url", String.format("https://render.alipay.com/p/s/i?scheme=%s", new Object[] { Uri.encode(a8) }));
            a$a.a(a, intent2);
            this.b.startActivity(intent2);
            return false;
        }
        this.d.post((Runnable)new b(4001, "\u652f\u4ed8\u5b9d\u672a\u5b89\u88c5\u6216\u7b7e\u540d\u9519\u8bef", null, null));
        return true;
    }
    
    public void execute(final String s, final BizType bizType, final Map<String, String> map, final Callback c, final boolean b) {
        final Activity b2 = this.b;
        final String value = String.valueOf((Object)map);
        final StringBuilder sb = new StringBuilder();
        sb.append("oa-");
        sb.append((Object)bizType);
        final a a = new a((Context)b2, value, sb.toString());
        try {
            this.c = c;
            if (this.a(a, s, bizType, map, b)) {
                com.alipay.sdk.m.k.a.b((Context)this.b, a, "", a.d);
            }
        }
        finally {}
    }
    
    public enum BizType
    {
        public static final BizType[] $VALUES;
        
        AccountAuth("20000067"), 
        Deduct("60000157"), 
        Invoice("20000920");
        
        public String appId;
        
        public BizType(final String appId) {
            this.appId = appId;
        }
        
        public static /* synthetic */ String access$100(final BizType bizType) {
            return bizType.appId;
        }
    }
    
    public interface Callback
    {
        void onResult(final int p0, final String p1, final Bundle p2);
    }
    
    public final class b implements Runnable
    {
        public final int a;
        public final String b;
        public final Bundle c;
        public final OpenAuthTask d;
        
        public b(final OpenAuthTask d, final int a, final String b, final Bundle c) {
            this.d = d;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public void run() {
            if (OpenAuthTask.a(this.d) != null) {
                OpenAuthTask.a(this.d).onResult(this.a, this.b, this.c);
            }
        }
    }
}
