package com.tencent.open.b;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Collection;
import android.text.TextUtils;
import java.net.SocketTimeoutException;
import java.util.Map;
import android.os.Environment;
import android.os.SystemClock;
import java.util.TimeZone;
import android.os.Build;
import android.os.Build$VERSION;
import com.tencent.open.utils.k;
import com.tencent.open.log.SLog;
import android.os.Bundle;
import com.tencent.open.utils.g;
import com.tencent.open.utils.f;
import android.os.Message;
import android.os.Looper;
import com.tencent.open.utils.j;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class h
{
    protected static h a;
    protected Random b;
    protected List<Serializable> c;
    protected List<Serializable> d;
    protected HandlerThread e;
    protected Handler f;
    protected Executor g;
    protected Executor h;
    
    private h() {
        this.e = null;
        this.b = new Random();
        this.d = (List<Serializable>)Collections.synchronizedList((List)new ArrayList());
        this.c = (List<Serializable>)Collections.synchronizedList((List)new ArrayList());
        this.g = j.b();
        this.h = j.b();
        if (this.e == null) {
            (this.e = new HandlerThread("opensdk.report.handlerthread", 10)).start();
        }
        if (this.e.isAlive() && this.e.getLooper() != null) {
            this.f = new Handler(this, this.e.getLooper()) {
                final h a;
                
                public void handleMessage(final Message message) {
                    final int what = message.what;
                    if (what != 1000) {
                        if (what == 1001) {
                            this.a.e();
                        }
                    }
                    else {
                        this.a.b();
                    }
                    super.handleMessage(message);
                }
            };
        }
    }
    
    public static h a() {
        synchronized (h.class) {
            if (h.a == null) {
                h.a = new h();
            }
            return h.a;
        }
    }
    
    protected int a(int n) {
        if (n == 0) {
            if ((n = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Common_CGIReportFrequencySuccess")) == 0) {
                n = 10;
            }
        }
        else if ((n = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Common_CGIReportFrequencyFailed")) == 0) {
            n = 100;
        }
        return n;
    }
    
    public void a(final Bundle bundle, final String s, final boolean b) {
        if (bundle == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->reportVia, bundle: ");
        sb.append(bundle.toString());
        SLog.v("openSDK_LOG.ReportManager", sb.toString());
        if (!this.a("report_via", s) && !b) {
            return;
        }
        this.g.execute((Runnable)new Runnable(this, bundle, b) {
            final Bundle a;
            final boolean b;
            final h c;
            
            public void run() {
                try {
                    final String k = com.tencent.open.utils.k.k(com.tencent.open.b.d.b(com.tencent.open.utils.f.a()));
                    final String i = com.tencent.open.utils.k.k(com.tencent.open.b.d.c(com.tencent.open.utils.f.a()));
                    final String j = com.tencent.open.utils.k.k(com.tencent.open.b.d.a());
                    final String l = com.tencent.open.utils.k.k(com.tencent.open.b.d.d(com.tencent.open.utils.f.a()));
                    final Bundle bundle = new Bundle();
                    bundle.putString("uin", "1000");
                    bundle.putString("imei", k);
                    bundle.putString("imsi", i);
                    bundle.putString("android_id", l);
                    bundle.putString("mac", j);
                    bundle.putString("platform", "1");
                    bundle.putString("os_ver", Build$VERSION.RELEASE);
                    bundle.putString("position", "");
                    bundle.putString("network", com.tencent.open.b.a.a(com.tencent.open.utils.f.a()));
                    bundle.putString("language", com.tencent.open.b.d.b());
                    bundle.putString("resolution", com.tencent.open.b.d.a(com.tencent.open.utils.f.a()));
                    bundle.putString("apn", com.tencent.open.b.a.b(com.tencent.open.utils.f.a()));
                    bundle.putString("model_name", Build.MODEL);
                    bundle.putString("timezone", TimeZone.getDefault().getID());
                    bundle.putString("sdk_ver", "3.5.4.lite");
                    bundle.putString("qz_ver", com.tencent.open.utils.k.d(com.tencent.open.utils.f.a(), "com.qzone"));
                    bundle.putString("qq_ver", com.tencent.open.utils.k.c(com.tencent.open.utils.f.a(), "com.tencent.mobileqq"));
                    bundle.putString("qua", com.tencent.open.utils.k.e(com.tencent.open.utils.f.a(), com.tencent.open.utils.f.b()));
                    bundle.putString("packagename", com.tencent.open.utils.f.b());
                    bundle.putString("app_ver", com.tencent.open.utils.k.d(com.tencent.open.utils.f.a(), com.tencent.open.utils.f.b()));
                    if (this.a != null) {
                        bundle.putAll(this.a);
                    }
                    this.c.d.add((Object)new c(bundle));
                    final int size = this.c.d.size();
                    int a;
                    if ((a = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Agent_ReportTimeInterval")) == 0) {
                        a = 10000;
                    }
                    if (!this.c.a("report_via", size) && !this.b) {
                        if (!this.c.f.hasMessages(1001)) {
                            final Message obtain = Message.obtain();
                            obtain.what = 1001;
                            this.c.f.sendMessageDelayed(obtain, (long)a);
                        }
                    }
                    else {
                        this.c.e();
                        this.c.f.removeMessages(1001);
                    }
                }
                catch (final Exception ex) {
                    SLog.e("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", (Throwable)ex);
                }
            }
        });
    }
    
    public void a(final String s, final long n, final long n2, final long n3, final int n4) {
        this.a(s, n, n2, n3, n4, "", false);
    }
    
    public void a(final String s, final long n, final long n2, final long n3, final int n4, final String s2, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->reportCgi, command: ");
        sb.append(s);
        sb.append(" | startTime: ");
        sb.append(n);
        sb.append(" | reqSize:");
        sb.append(n2);
        sb.append(" | rspSize: ");
        sb.append(n3);
        sb.append(" | responseCode: ");
        sb.append(n4);
        sb.append(" | detail: ");
        sb.append(s2);
        SLog.v("openSDK_LOG.ReportManager", sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(n4);
        if (!this.a("report_cgi", sb2.toString()) && !b) {
            return;
        }
        this.h.execute((Runnable)new Runnable(this, n, s, s2, n4, n2, n3, b) {
            final long a;
            final String b;
            final String c;
            final int d;
            final long e;
            final long f;
            final boolean g;
            final h h;
            
            public void run() {
                try {
                    final long elapsedRealtime = SystemClock.elapsedRealtime();
                    final long a = this.a;
                    final Bundle bundle = new Bundle();
                    final String a2 = com.tencent.open.b.a.a(com.tencent.open.utils.f.a());
                    bundle.putString("apn", a2);
                    bundle.putString("appid", "1000067");
                    bundle.putString("commandid", this.b);
                    bundle.putString("detail", this.c);
                    final StringBuilder sb = new StringBuilder();
                    sb.append("network=");
                    sb.append(a2);
                    sb.append('&');
                    sb.append("sdcard=");
                    final boolean equals = Environment.getExternalStorageState().equals((Object)"mounted");
                    final int n = 1;
                    int n2;
                    if (equals) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    sb.append(n2);
                    sb.append('&');
                    sb.append("wifi=");
                    sb.append(com.tencent.open.b.a.e(com.tencent.open.utils.f.a()));
                    bundle.putString("deviceInfo", sb.toString());
                    int n3 = 100 / this.h.a(this.d);
                    if (n3 <= 0) {
                        n3 = n;
                    }
                    else if (n3 > 100) {
                        n3 = 100;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(n3);
                    sb2.append("");
                    bundle.putString("frequency", sb2.toString());
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(this.e);
                    sb3.append("");
                    bundle.putString("reqSize", sb3.toString());
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(this.d);
                    sb4.append("");
                    bundle.putString("resultCode", sb4.toString());
                    final StringBuilder sb5 = new StringBuilder();
                    sb5.append(this.f);
                    sb5.append("");
                    bundle.putString("rspSize", sb5.toString());
                    final StringBuilder sb6 = new StringBuilder();
                    sb6.append(elapsedRealtime - a);
                    sb6.append("");
                    bundle.putString("timeCost", sb6.toString());
                    bundle.putString("uin", "1000");
                    this.h.c.add((Object)new c(bundle));
                    final int size = this.h.c.size();
                    int a3;
                    if ((a3 = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Agent_ReportTimeInterval")) == 0) {
                        a3 = 10000;
                    }
                    if (!this.h.a("report_cgi", size) && !this.g) {
                        if (!this.h.f.hasMessages(1000)) {
                            final Message obtain = Message.obtain();
                            obtain.what = 1000;
                            this.h.f.sendMessageDelayed(obtain, (long)a3);
                        }
                    }
                    else {
                        this.h.b();
                        this.h.f.removeMessages(1000);
                    }
                }
                catch (final Exception ex) {
                    SLog.e("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", (Throwable)ex);
                }
            }
        });
    }
    
    public void a(final String s, final Map<String, String> map) {
        if (!k.b(com.tencent.open.utils.f.a())) {
            return;
        }
        j.b((Runnable)new Runnable(this, s, map) {
            final String a;
            final Map b;
            final h c;
            
            public void run() {
                int n = 0;
                try {
                    int a;
                    if ((a = com.tencent.open.b.f.a()) == 0) {
                        a = 3;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->httpRequest, retryCount: ");
                    sb.append(a);
                    SLog.d("openSDK_LOG.ReportManager", sb.toString());
                    while (true) {
                        final int n2 = n + 1;
                        try {
                            final int d = com.tencent.open.a.a.a().a(this.a, (Map<String, String>)this.b).d();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("-->httpRequest, statusCode: ");
                            sb2.append(d);
                            SLog.i("openSDK_LOG.ReportManager", sb2.toString());
                        }
                        catch (final Exception ex) {
                            SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception:", (Throwable)ex);
                        }
                        catch (final SocketTimeoutException ex2) {
                            SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException:", (Throwable)ex2);
                            n = n2;
                            if (n2 < a) {
                                continue;
                            }
                        }
                        break;
                    }
                }
                catch (final Exception ex3) {
                    SLog.e("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor:", (Throwable)ex3);
                }
            }
        });
    }
    
    protected boolean a(final String s, final int n) {
        final boolean equals = s.equals((Object)"report_cgi");
        final int n2 = 5;
        int n3;
        if (equals) {
            if ((n3 = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Common_CGIReportMaxcount")) == 0) {
                n3 = n2;
            }
        }
        else if (s.equals((Object)"report_via")) {
            if ((n3 = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Agent_ReportBatchCount")) == 0) {
                n3 = n2;
            }
        }
        else {
            n3 = 0;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->availableCount, report: ");
        sb.append(s);
        sb.append(" | dataSize: ");
        sb.append(n);
        sb.append(" | maxcount: ");
        sb.append(n3);
        SLog.d("openSDK_LOG.ReportManager", sb.toString());
        return n >= n3;
    }
    
    protected boolean a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->availableFrequency, report: ");
        sb.append(s);
        sb.append(" | ext: ");
        sb.append(s2);
        SLog.d("openSDK_LOG.ReportManager", sb.toString());
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        final boolean b2 = false;
        if (empty) {
            return false;
        }
        final boolean equals = s.equals((Object)"report_cgi");
        int n = 100;
        Label_0174: {
            if (equals) {
                try {
                    final int a = this.a(Integer.parseInt(s2));
                    b = b2;
                    n = a;
                    if (this.b.nextInt(100) < a) {
                        b = true;
                        n = a;
                    }
                    break Label_0174;
                }
                catch (final Exception ex) {
                    return false;
                }
            }
            if (s.equals((Object)"report_via")) {
                final int a2 = com.tencent.open.b.f.a(s2);
                b = b2;
                if (this.b.nextInt(100) < (n = a2)) {
                    n = a2;
                    b = true;
                }
            }
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-->availableFrequency, result: ");
        sb2.append(b);
        sb2.append(" | frequency: ");
        sb2.append(n);
        SLog.d("openSDK_LOG.ReportManager", sb2.toString());
        return b;
    }
    
    protected void b() {
        if (!k.b(com.tencent.open.utils.f.a())) {
            return;
        }
        this.h.execute((Runnable)new Runnable(this) {
            final h a;
            
            public void run() {
                try {
                    final Map<String, String> c = this.a.c();
                    if (c == null || c.isEmpty()) {
                        return;
                    }
                    int a;
                    if ((a = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), null).a("Common_HttpRetryCount")) == 0) {
                        a = 3;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("-->doReportCgi, retryCount: ");
                    sb.append(a);
                    SLog.d("openSDK_LOG.ReportManager", sb.toString());
                    final boolean b = false;
                    int n = 0;
                    int n3;
                    while (true) {
                        final int n2 = n + 1;
                        try {
                            final int d = com.tencent.open.a.a.a().b("https://wspeed.qq.com/w.cgi", c).d();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("-->doReportCgi, statusCode: ");
                            sb2.append(d);
                            SLog.i("openSDK_LOG.ReportManager", sb2.toString());
                            n3 = (b ? 1 : 0);
                            if (d == 200) {
                                com.tencent.open.b.g.a().b("report_cgi");
                                n3 = 1;
                            }
                        }
                        catch (final Exception ex) {
                            SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", (Throwable)ex);
                            n3 = (b ? 1 : 0);
                        }
                        catch (final SocketTimeoutException ex2) {
                            SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", (Throwable)ex2);
                            n = n2;
                            if (n2 < a) {
                                continue;
                            }
                            n3 = (b ? 1 : 0);
                        }
                        break;
                    }
                    if (n3 == 0) {
                        com.tencent.open.b.g.a().a("report_cgi", this.a.c);
                    }
                    this.a.c.clear();
                }
                catch (final Exception ex3) {
                    SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception out.", (Throwable)ex3);
                }
            }
        });
    }
    
    protected Map<String, String> c() {
        if (this.c.size() == 0) {
            return null;
        }
        final List<Serializable> c = this.c;
        int i = 0;
        final c c2 = (c)c.get(0);
        if (c2 == null) {
            SLog.d("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        final String s = (String)c2.a.get((Object)"appid");
        final List<Serializable> a = com.tencent.open.b.g.a().a("report_cgi");
        if (a != null) {
            this.c.addAll((Collection)a);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->prepareCgiData, mCgiList size: ");
        sb.append(this.c.size());
        SLog.d("openSDK_LOG.ReportManager", sb.toString());
        if (this.c.size() == 0) {
            return null;
        }
        final HashMap hashMap = new HashMap();
        try {
            ((Map)hashMap).put((Object)"appid", (Object)s);
            ((Map)hashMap).put((Object)"releaseversion", (Object)"OpenSdk_3.5.4.lite");
            ((Map)hashMap).put((Object)"device", (Object)Build.DEVICE);
            ((Map)hashMap).put((Object)"qua", (Object)"V1_AND_OpenSDK_3.5.4.lite_1077_RDM_B");
            ((Map)hashMap).put((Object)"key", (Object)"apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            while (i < this.c.size()) {
                final c c3 = (c)this.c.get(i);
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(i);
                sb2.append("_1");
                ((Map)hashMap).put((Object)sb2.toString(), c3.a.get((Object)"apn"));
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(i);
                sb3.append("_2");
                ((Map)hashMap).put((Object)sb3.toString(), c3.a.get((Object)"frequency"));
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(i);
                sb4.append("_3");
                ((Map)hashMap).put((Object)sb4.toString(), c3.a.get((Object)"commandid"));
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(i);
                sb5.append("_4");
                ((Map)hashMap).put((Object)sb5.toString(), c3.a.get((Object)"resultCode"));
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(i);
                sb6.append("_5");
                ((Map)hashMap).put((Object)sb6.toString(), c3.a.get((Object)"timeCost"));
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(i);
                sb7.append("_6");
                ((Map)hashMap).put((Object)sb7.toString(), c3.a.get((Object)"reqSize"));
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(i);
                sb8.append("_7");
                ((Map)hashMap).put((Object)sb8.toString(), c3.a.get((Object)"rspSize"));
                final StringBuilder sb9 = new StringBuilder();
                sb9.append(i);
                sb9.append("_8");
                ((Map)hashMap).put((Object)sb9.toString(), c3.a.get((Object)"detail"));
                final StringBuilder sb10 = new StringBuilder();
                sb10.append(i);
                sb10.append("_9");
                ((Map)hashMap).put((Object)sb10.toString(), c3.a.get((Object)"uin"));
                final StringBuilder sb11 = new StringBuilder();
                sb11.append(com.tencent.open.b.d.e(com.tencent.open.utils.f.a()));
                sb11.append("&");
                sb11.append((String)c3.a.get((Object)"deviceInfo"));
                final String string = sb11.toString();
                final StringBuilder sb12 = new StringBuilder();
                sb12.append(i);
                sb12.append("_10");
                ((Map)hashMap).put((Object)sb12.toString(), (Object)string);
                ++i;
            }
            final StringBuilder sb13 = new StringBuilder();
            sb13.append("-->prepareCgiData, end. params: ");
            sb13.append(hashMap.toString());
            SLog.v("openSDK_LOG.ReportManager", sb13.toString());
            return (Map<String, String>)hashMap;
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", (Throwable)ex);
            return null;
        }
    }
    
    protected Map<String, String> d() {
        final List<Serializable> a = com.tencent.open.b.g.a().a("report_via");
        if (a != null) {
            this.d.addAll((Collection)a);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->prepareViaData, mViaList size: ");
        sb.append(this.d.size());
        SLog.d("openSDK_LOG.ReportManager", sb.toString());
        if (this.d.size() == 0) {
            return null;
        }
        final JSONArray jsonArray = new JSONArray();
        for (final Serializable s : this.d) {
            final JSONObject jsonObject = new JSONObject();
            final c c = (c)s;
            for (final String s2 : c.a.keySet()) {
                try {
                    String s3;
                    if ((s3 = (String)c.a.get((Object)s2)) == null) {
                        s3 = "";
                    }
                    jsonObject.put(s2, (Object)s3);
                }
                catch (final JSONException ex) {
                    SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", (Throwable)ex);
                }
            }
            jsonArray.put((Object)jsonObject);
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-->prepareViaData, JSONArray array: ");
        sb2.append(jsonArray.toString());
        SLog.v("openSDK_LOG.ReportManager", sb2.toString());
        final JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject2.put("data", (Object)jsonArray);
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"data", (Object)jsonObject2.toString());
            return (Map<String, String>)hashMap;
        }
        catch (final JSONException ex2) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", (Throwable)ex2);
            return null;
        }
    }
    
    protected void e() {
        if (!k.b(com.tencent.open.utils.f.a())) {
            return;
        }
        this.g.execute((Runnable)new Runnable(this) {
            final h a;
            
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/tencent/open/b/h$5.a:Lcom/tencent/open/b/h;
                //     4: invokevirtual   com/tencent/open/b/h.d:()Ljava/util/Map;
                //     7: astore          16
                //     9: aload           16
                //    11: ifnonnull       15
                //    14: return         
                //    15: new             Ljava/lang/StringBuilder;
                //    18: astore          15
                //    20: aload           15
                //    22: invokespecial   java/lang/StringBuilder.<init>:()V
                //    25: aload           15
                //    27: ldc             "-->doReportVia, params: "
                //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    32: pop            
                //    33: aload           15
                //    35: aload           16
                //    37: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
                //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    43: pop            
                //    44: ldc             "openSDK_LOG.ReportManager"
                //    46: aload           15
                //    48: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    51: invokestatic    com/tencent/open/log/SLog.d:(Ljava/lang/String;Ljava/lang/String;)V
                //    54: invokestatic    com/tencent/open/b/f.a:()I
                //    57: istore          5
                //    59: invokestatic    android/os/SystemClock.elapsedRealtime:()J
                //    62: lstore          7
                //    64: iconst_0       
                //    65: istore          4
                //    67: iconst_0       
                //    68: istore_2       
                //    69: iload           4
                //    71: iconst_1       
                //    72: iadd           
                //    73: istore_3       
                //    74: invokestatic    com/tencent/open/a/a.a:()Lcom/tencent/open/a/a;
                //    77: ldc             "https://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report"
                //    79: aload           16
                //    81: invokevirtual   com/tencent/open/a/a.b:(Ljava/lang/String;Ljava/util/Map;)Lcom/tencent/open/a/b;
                //    84: astore          15
                //    86: aload           15
                //    88: invokevirtual   com/tencent/open/a/b.d:()I
                //    91: istore_1       
                //    92: new             Ljava/lang/StringBuilder;
                //    95: astore          17
                //    97: aload           17
                //    99: invokespecial   java/lang/StringBuilder.<init>:()V
                //   102: aload           17
                //   104: ldc             "-->reportVia: statusCode "
                //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   109: pop            
                //   110: aload           17
                //   112: iload_1        
                //   113: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   116: pop            
                //   117: ldc             "openSDK_LOG.ReportManager"
                //   119: aload           17
                //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   124: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
                //   127: aload           15
                //   129: invokevirtual   com/tencent/open/a/b.a:()Ljava/lang/String;
                //   132: invokestatic    com/tencent/open/utils/k.d:(Ljava/lang/String;)Lorg/json/JSONObject;
                //   135: astore          17
                //   137: aload           15
                //   139: invokevirtual   com/tencent/open/a/b.c:()I
                //   142: i2l            
                //   143: lstore          9
                //   145: aload           15
                //   147: invokevirtual   com/tencent/open/a/b.b:()I
                //   150: istore_1       
                //   151: iload_1        
                //   152: i2l            
                //   153: lstore          11
                //   155: aload           15
                //   157: invokevirtual   com/tencent/open/a/b.d:()I
                //   160: sipush          200
                //   163: if_icmpeq       175
                //   166: aload           15
                //   168: invokevirtual   com/tencent/open/a/b.d:()I
                //   171: istore_1       
                //   172: goto            336
                //   175: aload           17
                //   177: ldc             "ret"
                //   179: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
                //   182: istore_1       
                //   183: goto            191
                //   186: astore          15
                //   188: bipush          -4
                //   190: istore_1       
                //   191: iload_1        
                //   192: ifeq            222
                //   195: lload           11
                //   197: lconst_0       
                //   198: lcmp           
                //   199: ifeq            205
                //   202: goto            222
                //   205: lload           9
                //   207: lstore          13
                //   209: iload_2        
                //   210: istore_1       
                //   211: lload           11
                //   213: lstore          9
                //   215: lload           13
                //   217: lstore          11
                //   219: goto            301
                //   222: iconst_1       
                //   223: istore          6
                //   225: iload_2        
                //   226: istore_1       
                //   227: goto            339
                //   230: astore          15
                //   232: goto            258
                //   235: astore          15
                //   237: lconst_0       
                //   238: lstore          11
                //   240: bipush          -6
                //   242: istore_1       
                //   243: iload           5
                //   245: istore_3       
                //   246: lconst_0       
                //   247: lstore          9
                //   249: lload           7
                //   251: lstore          13
                //   253: goto            305
                //   256: astore          15
                //   258: aload           15
                //   260: invokestatic    com/tencent/open/utils/HttpUtils.getErrorCodeFromException:(Ljava/io/IOException;)I
                //   263: istore_1       
                //   264: goto            295
                //   267: astore          15
                //   269: lconst_0       
                //   270: lstore          11
                //   272: lconst_0       
                //   273: lstore          9
                //   275: bipush          -4
                //   277: istore_1       
                //   278: lload           7
                //   280: lstore          13
                //   282: goto            305
                //   285: astore          15
                //   287: invokestatic    android/os/SystemClock.elapsedRealtime:()J
                //   290: lstore          7
                //   292: bipush          -8
                //   294: istore_1       
                //   295: lconst_0       
                //   296: lstore          11
                //   298: lconst_0       
                //   299: lstore          9
                //   301: lload           7
                //   303: lstore          13
                //   305: iload_3        
                //   306: istore          4
                //   308: lload           13
                //   310: lstore          7
                //   312: iload_1        
                //   313: istore_2       
                //   314: iload_3        
                //   315: iload           5
                //   317: if_icmplt       69
                //   320: lload           13
                //   322: lstore          7
                //   324: lload           9
                //   326: lstore          13
                //   328: lload           11
                //   330: lstore          9
                //   332: lload           13
                //   334: lstore          11
                //   336: iconst_0       
                //   337: istore          6
                //   339: aload_0        
                //   340: getfield        com/tencent/open/b/h$5.a:Lcom/tencent/open/b/h;
                //   343: ldc             "mapp_apptrace_sdk"
                //   345: lload           7
                //   347: lload           9
                //   349: lload           11
                //   351: iload_1        
                //   352: aconst_null    
                //   353: iconst_0       
                //   354: invokevirtual   com/tencent/open/b/h.a:(Ljava/lang/String;JJJILjava/lang/String;Z)V
                //   357: iload           6
                //   359: ifeq            373
                //   362: invokestatic    com/tencent/open/b/g.a:()Lcom/tencent/open/b/g;
                //   365: ldc             "report_via"
                //   367: invokevirtual   com/tencent/open/b/g.b:(Ljava/lang/String;)V
                //   370: goto            388
                //   373: invokestatic    com/tencent/open/b/g.a:()Lcom/tencent/open/b/g;
                //   376: ldc             "report_via"
                //   378: aload_0        
                //   379: getfield        com/tencent/open/b/h$5.a:Lcom/tencent/open/b/h;
                //   382: getfield        com/tencent/open/b/h.d:Ljava/util/List;
                //   385: invokevirtual   com/tencent/open/b/g.a:(Ljava/lang/String;Ljava/util/List;)V
                //   388: aload_0        
                //   389: getfield        com/tencent/open/b/h$5.a:Lcom/tencent/open/b/h;
                //   392: getfield        com/tencent/open/b/h.d:Ljava/util/List;
                //   395: invokeinterface java/util/List.clear:()V
                //   400: new             Ljava/lang/StringBuilder;
                //   403: astore          15
                //   405: aload           15
                //   407: invokespecial   java/lang/StringBuilder.<init>:()V
                //   410: aload           15
                //   412: ldc             "-->doReportVia, uploadSuccess: "
                //   414: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   417: pop            
                //   418: aload           15
                //   420: iload           6
                //   422: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
                //   425: pop            
                //   426: aload           15
                //   428: ldc             " resultCode: "
                //   430: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   433: pop            
                //   434: aload           15
                //   436: iload_1        
                //   437: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   440: pop            
                //   441: ldc             "openSDK_LOG.ReportManager"
                //   443: aload           15
                //   445: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   448: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
                //   451: goto            465
                //   454: astore          15
                //   456: ldc             "openSDK_LOG.ReportManager"
                //   458: ldc             "-->doReportVia, exception in serial executor."
                //   460: aload           15
                //   462: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   465: return         
                //   466: astore          15
                //   468: goto            287
                //   471: astore          15
                //   473: goto            269
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      9      454    465    Ljava/lang/Exception;
                //  15     64     454    465    Ljava/lang/Exception;
                //  74     151    285    287    Ljava/net/SocketTimeoutException;
                //  74     151    267    269    Lorg/json/JSONException;
                //  74     151    256    258    Ljava/io/IOException;
                //  74     151    235    256    Ljava/lang/Exception;
                //  155    172    466    471    Ljava/net/SocketTimeoutException;
                //  155    172    471    476    Lorg/json/JSONException;
                //  155    172    230    235    Ljava/io/IOException;
                //  155    172    235    256    Ljava/lang/Exception;
                //  175    183    186    191    Lorg/json/JSONException;
                //  175    183    466    471    Ljava/net/SocketTimeoutException;
                //  175    183    230    235    Ljava/io/IOException;
                //  175    183    235    256    Ljava/lang/Exception;
                //  258    264    454    465    Ljava/lang/Exception;
                //  287    292    454    465    Ljava/lang/Exception;
                //  339    357    454    465    Ljava/lang/Exception;
                //  362    370    454    465    Ljava/lang/Exception;
                //  373    388    454    465    Ljava/lang/Exception;
                //  388    451    454    465    Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0175:
                //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
                //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
                //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
                //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
                //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
                //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
                //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
                //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
                //     at u5.m.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:221)
                //     at u5.m.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:499)
                //     at u5.m.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:74)
                //     at u5.m.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:256)
                //     at u5.m.h(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:46)
                //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:145)
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
        });
    }
}
