package com.tencent.open.b;

import android.text.TextUtils;
import com.tencent.open.utils.i;
import android.content.Context;
import java.util.Iterator;
import java.util.Collection;
import com.tencent.open.a.a;
import android.os.Build;
import android.os.Build$VERSION;
import java.util.HashMap;
import java.util.Map;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.k;
import com.tencent.open.utils.f;
import com.tencent.open.utils.j;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.io.Serializable;
import java.util.List;

public class b
{
    private static b a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private List<Serializable> i;
    private List<Serializable> j;
    private Executor k;
    private boolean l;
    
    private b() {
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = (List<Serializable>)Collections.synchronizedList((List)new ArrayList());
        this.j = (List<Serializable>)Collections.synchronizedList((List)new ArrayList());
        this.k = com.tencent.open.utils.j.b();
    }
    
    public static b a() {
        synchronized (b.class) {
            if (b.a == null) {
                b.a = new b();
            }
            return b.a;
        }
    }
    
    private void a(final c c) {
        this.k.execute((Runnable)new Runnable(this, c) {
            final c a;
            final b b;
            
            public void run() {
                this.b.i.add((Object)this.a);
                if (!com.tencent.open.utils.k.b(com.tencent.open.utils.f.a())) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("attaReport net disconnect, ");
                    sb.append((Object)this.a);
                    SLog.i("AttaReporter", sb.toString());
                    return;
                }
                try {
                    this.b.c();
                }
                catch (final Exception ex) {
                    SLog.e("AttaReporter", "Exception", (Throwable)ex);
                }
            }
        });
    }
    
    private c b(String s, String s2, final Object o, final Map<String, Object> map) {
        final long currentTimeMillis = System.currentTimeMillis();
        final HashMap hashMap = new HashMap();
        hashMap.put((Object)"attaid", (Object)"09400051119");
        hashMap.put((Object)"token", (Object)"9389887874");
        final StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis);
        sb.append("_");
        sb.append(this.b);
        sb.append("_");
        sb.append(this.d);
        hashMap.put((Object)"time_appid_openid", (Object)sb.toString());
        hashMap.put((Object)"time", (Object)String.valueOf(currentTimeMillis));
        hashMap.put((Object)"openid", (Object)this.d);
        hashMap.put((Object)"appid", (Object)this.b);
        hashMap.put((Object)"app_name", (Object)this.c);
        hashMap.put((Object)"app_ver", (Object)this.e);
        hashMap.put((Object)"pkg_name", (Object)this.f);
        hashMap.put((Object)"os", (Object)"AND");
        hashMap.put((Object)"os_ver", (Object)Build$VERSION.RELEASE);
        hashMap.put((Object)"sdk_ver", (Object)"3.5.4.lite");
        hashMap.put((Object)"model_name", (Object)Build.MODEL);
        hashMap.put((Object)"interface_name", (Object)s);
        hashMap.put((Object)"interface_data", (Object)s2);
        s2 = "";
        if (o == null) {
            s = "";
        }
        else {
            s = o.toString();
        }
        hashMap.put((Object)"interface_result", (Object)s);
        hashMap.put((Object)"qq_install", (Object)this.g);
        hashMap.put((Object)"qq_ver", (Object)this.h);
        if (map != null && !map.isEmpty()) {
            final Object value = map.get((Object)"reserve1");
            if (value == null) {
                s = "";
            }
            else {
                s = value.toString();
            }
            hashMap.put((Object)"reserve1", (Object)s);
            final Object value2 = map.get((Object)"reserve2");
            if (value2 == null) {
                s = "";
            }
            else {
                s = value2.toString();
            }
            hashMap.put((Object)"reserve2", (Object)s);
            final Object value3 = map.get((Object)"reserve3");
            if (value3 == null) {
                s = "";
            }
            else {
                s = value3.toString();
            }
            hashMap.put((Object)"reserve3", (Object)s);
            final Object value4 = map.get((Object)"reserve4");
            if (value4 == null) {
                s = s2;
            }
            else {
                s = value4.toString();
            }
            hashMap.put((Object)"reserve4", (Object)s);
        }
        return new c((HashMap<String, String>)hashMap);
    }
    
    private void b() {
        while (!this.j.isEmpty()) {
            final c c = (c)this.j.remove(0);
            c.a.put((Object)"appid", (Object)this.b);
            c.a.put((Object)"app_name", (Object)this.c);
            c.a.put((Object)"app_ver", (Object)this.e);
            c.a.put((Object)"pkg_name", (Object)this.f);
            c.a.put((Object)"qq_install", (Object)this.g);
            c.a.put((Object)"qq_ver", (Object)this.h);
            c.a.put((Object)"openid", (Object)this.d);
            final HashMap<String, String> a = c.a;
            final StringBuilder sb = new StringBuilder();
            sb.append((String)c.a.get((Object)"time"));
            sb.append("_");
            sb.append(this.b);
            sb.append("_");
            sb.append(this.d);
            a.put((Object)"time_appid_openid", (Object)sb.toString());
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("fixDirtyData--------------------------");
            sb2.append((Object)c);
            SLog.i("AttaReporter", sb2.toString());
            this.i.add((Object)c);
        }
    }
    
    private boolean b(final c c) {
        boolean b = false;
        int n = 0;
        while (true) {
            final int n2 = n + 1;
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append("doAttaReportItem post ");
                sb.append((Object)c);
                SLog.i("AttaReporter", sb.toString());
                if (com.tencent.open.a.a.a().b("https://h.trace.qq.com/kv", (Map<String, String>)c.a).d() == 200) {
                    b = true;
                }
                return b;
            }
            catch (final Exception ex) {
                SLog.i("AttaReporter", "Exception", (Throwable)ex);
                n = n2;
                if (n2 >= 2) {
                    return false;
                }
                continue;
            }
        }
    }
    
    private void c() {
        SLog.i("AttaReporter", "attaReportAtSubThread");
        if (!this.l) {
            final List<Serializable> a = com.tencent.open.b.g.a().a("report_atta");
            this.l = a.isEmpty();
            this.i.addAll((Collection)a);
            for (final Serializable s : a) {
                final StringBuilder sb = new StringBuilder();
                sb.append("attaReportAtSubThread from db = ");
                sb.append((Object)s);
                SLog.i("AttaReporter", sb.toString());
            }
        }
        final ArrayList list = new ArrayList();
        while (!this.i.isEmpty()) {
            final c c = (c)this.i.remove(0);
            if (!this.b(c)) {
                ((List)list).add((Object)c);
            }
        }
        if (!((List)list).isEmpty()) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("attaReportAtSubThread fail size=");
            sb2.append(((List)list).size());
            SLog.i("AttaReporter", sb2.toString());
            for (final Serializable s2 : list) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("attaReportAtSubThread fail cache to db, ");
                sb3.append((Object)s2);
                SLog.i("AttaReporter", sb3.toString());
            }
            com.tencent.open.b.g.a().a("report_atta", (List<Serializable>)list);
            this.l = false;
        }
        else if (!this.l) {
            SLog.i("AttaReporter", "attaReportAtSubThread clear db");
            com.tencent.open.b.g.a().b("report_atta");
            this.l = true;
        }
    }
    
    public void a(final String s) {
        SLog.i("AttaReporter", "updateOpenId");
        String d = s;
        if (s == null) {
            d = "";
        }
        this.d = d;
    }
    
    public void a(String s, final Context context) {
        SLog.i("AttaReporter", "init");
        this.b = s;
        this.c = com.tencent.open.utils.i.a(context);
        this.e = com.tencent.open.utils.k.d(context, com.tencent.open.utils.f.b());
        this.f = com.tencent.open.utils.f.b();
        if (com.tencent.open.utils.i.b(context)) {
            s = "1";
        }
        else {
            s = "0";
        }
        this.g = s;
        this.h = com.tencent.open.utils.k.c(context, "com.tencent.mobileqq");
        this.b();
    }
    
    public void a(final String s, final Object o) {
        this.a(s, "", o, null);
    }
    
    public void a(final String s, final String s2) {
        this.a(s, s2, null);
    }
    
    public void a(final String s, final String s2, final Object o, final Map<String, Object> map) {
        final c b = this.b(s, s2, o, map);
        if (!TextUtils.isEmpty((CharSequence)this.b) && !TextUtils.isEmpty((CharSequence)this.c) && com.tencent.open.utils.f.a() != null) {
            this.a(b);
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("attaReport cancel appid=");
        sb.append(this.b);
        sb.append(", mAppName=");
        sb.append(this.c);
        sb.append(", context=");
        sb.append((Object)com.tencent.open.utils.f.a());
        sb.append(", ");
        sb.append((Object)b);
        SLog.i("AttaReporter", sb.toString());
        this.j.add((Object)b);
    }
    
    public void a(final String s, final String s2, final Map<String, Object> map) {
        this.a(s, s2, "", map);
    }
}
