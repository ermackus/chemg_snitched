package com.tencent.bugly.crashreport.common.info;

import java.util.Set;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.UUID;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.os.Process;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import java.util.HashMap;

public final class a
{
    private static a Z;
    public boolean A;
    public boolean B;
    public HashMap<String, String> C;
    public List<String> D;
    public com.tencent.bugly.crashreport.a E;
    public SharedPreferences F;
    private final Context G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private long M;
    private long N;
    private long O;
    private String P;
    private String Q;
    private Map<String, PlugInBean> R;
    private boolean S;
    private String T;
    private String U;
    private Boolean V;
    private String W;
    private Map<String, PlugInBean> X;
    private Map<String, PlugInBean> Y;
    public final long a;
    private int aa;
    private int ab;
    private Map<String, String> ac;
    private Map<String, String> ad;
    private Map<String, String> ae;
    private boolean af;
    private Boolean ag;
    private Boolean ah;
    private final Object ai;
    private final Object aj;
    private final Object ak;
    private final Object al;
    private final Object am;
    private final Object an;
    private final Object ao;
    public final byte b;
    public String c;
    public final String d;
    public boolean e;
    public String f;
    public final String g;
    public final String h;
    public final String i;
    public long j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public List<String> p;
    public String q;
    public long r;
    public long s;
    public long t;
    public long u;
    public boolean v;
    public String w;
    public String x;
    public String y;
    public String z;
    
    private a(final Context context) {
        this.e = true;
        this.f = "3.3.3";
        this.J = "unknown";
        this.K = "";
        this.L = null;
        this.M = -1L;
        this.N = -1L;
        this.O = -1L;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = true;
        this.T = null;
        this.k = null;
        this.l = null;
        this.U = null;
        this.m = null;
        this.V = null;
        this.W = null;
        this.n = null;
        this.o = null;
        this.X = null;
        this.Y = null;
        this.p = null;
        this.aa = -1;
        this.ab = -1;
        this.ac = (Map<String, String>)new HashMap();
        this.ad = (Map<String, String>)new HashMap();
        this.ae = (Map<String, String>)new HashMap();
        this.af = true;
        this.q = "unknown";
        this.r = 0L;
        this.s = 0L;
        this.t = 0L;
        this.u = 0L;
        this.v = false;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = "";
        this.A = false;
        this.B = false;
        this.ag = null;
        this.ah = null;
        this.C = (HashMap<String, String>)new HashMap();
        this.D = (List<String>)new ArrayList();
        this.E = null;
        this.ai = new Object();
        this.aj = new Object();
        this.ak = new Object();
        this.al = new Object();
        this.am = new Object();
        this.an = new Object();
        this.ao = new Object();
        this.a = System.currentTimeMillis();
        this.G = com.tencent.bugly.proguard.z.a(context);
        this.b = 1;
        final PackageInfo b = AppInfo.b(context);
        if (b != null) {
            try {
                final String versionName = b.versionName;
                this.k = versionName;
                this.w = versionName;
                this.x = Integer.toString(b.versionCode);
            }
            finally {
                final Throwable t;
                if (!com.tencent.bugly.proguard.x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.a(Process.myPid());
        this.g = com.tencent.bugly.crashreport.common.info.b.k();
        this.h = com.tencent.bugly.crashreport.common.info.b.a();
        this.l = AppInfo.c(context);
        final StringBuilder sb = new StringBuilder("Android ");
        sb.append(com.tencent.bugly.crashreport.common.info.b.b());
        sb.append(",level ");
        sb.append(com.tencent.bugly.crashreport.common.info.b.c());
        this.i = sb.toString();
        final Map<String, String> d = AppInfo.d(context);
        if (d != null) {
            try {
                this.p = AppInfo.a(d);
                final String u = (String)d.get((Object)"BUGLY_APPID");
                if (u != null) {
                    this.c("APP_ID", this.U = u);
                }
                final String k = (String)d.get((Object)"BUGLY_APP_VERSION");
                if (k != null) {
                    this.k = k;
                }
                final String m = (String)d.get((Object)"BUGLY_APP_CHANNEL");
                if (m != null) {
                    this.m = m;
                }
                final String s = (String)d.get((Object)"BUGLY_ENABLE_DEBUG");
                if (s != null) {
                    this.v = s.equalsIgnoreCase("true");
                }
                final String y = (String)d.get((Object)"com.tencent.rdm.uuid");
                if (y != null) {
                    this.y = y;
                }
                final String s2 = (String)d.get((Object)"BUGLY_APP_BUILD_NO");
                if (!TextUtils.isEmpty((CharSequence)s2)) {
                    Integer.parseInt(s2);
                }
                final String z = (String)d.get((Object)"BUGLY_AREA");
                if (z != null) {
                    this.z = z;
                }
            }
            finally {
                final Throwable t2;
                if (!com.tencent.bugly.proguard.x.a(t2)) {
                    t2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.B = true;
                com.tencent.bugly.proguard.x.c("App is first time to be installed on the device.", new Object[0]);
            }
        }
        finally {
            if (com.tencent.bugly.b.c) {
                final Throwable t3;
                t3.printStackTrace();
            }
        }
        this.F = com.tencent.bugly.proguard.z.a("BUGLY_COMMON_VALUES", context);
        com.tencent.bugly.proguard.x.c("com info create end", new Object[0]);
    }
    
    public static int C() {
        return b.c();
    }
    
    public static a a(final Context context) {
        synchronized (a.class) {
            if (a.Z == null) {
                a.Z = new a(context);
            }
            return a.Z;
        }
    }
    
    public static a b() {
        synchronized (a.class) {
            return a.Z;
        }
    }
    
    public final int A() {
        return this.ab;
    }
    
    public final Map<String, PlugInBean> B() {
        monitorenter(this);
        monitorexit(this);
        return null;
    }
    
    public final boolean D() {
        if (this.ag == null) {
            this.ag = com.tencent.bugly.crashreport.common.info.b.e(this.G);
            final StringBuilder sb = new StringBuilder("Is it a virtual machine? ");
            sb.append((Object)this.ag);
            com.tencent.bugly.proguard.x.a(sb.toString(), new Object[0]);
        }
        return this.ag;
    }
    
    public final boolean E() {
        if (this.ah == null) {
            this.ah = com.tencent.bugly.crashreport.common.info.b.f(this.G);
            final StringBuilder sb = new StringBuilder("Does it has hook frame? ");
            sb.append((Object)this.ah);
            com.tencent.bugly.proguard.x.a(sb.toString(), new Object[0]);
        }
        return this.ah;
    }
    
    public final void a(final int aa) {
        final Object am = this.am;
        synchronized (am) {
            final int aa2 = this.aa;
            if (aa2 != aa) {
                this.aa = aa;
                com.tencent.bugly.proguard.x.a("user scene tag %d changed to tag %d", aa2, this.aa);
            }
        }
    }
    
    public final void a(final String u) {
        this.c("APP_ID", this.U = u);
    }
    
    public final void a(final String s, final String s2) {
        if (s != null) {
            if (s2 != null) {
                final Object aj = this.aj;
                synchronized (aj) {
                    this.C.put((Object)s, (Object)s2);
                }
            }
        }
    }
    
    public final void a(final boolean b) {
        this.af = b;
        final com.tencent.bugly.crashreport.a e = this.E;
        if (e != null) {
            e.setNativeIsAppForeground(b);
        }
    }
    
    public final boolean a() {
        return this.af;
    }
    
    public final void b(int ab) {
        ab = this.ab;
        if (ab != 24096) {
            this.ab = 24096;
            com.tencent.bugly.proguard.x.a("server scene tag %d changed to tag %d", ab, this.ab);
        }
    }
    
    public final void b(final String s) {
        final Object an;
        monitorenter(an = this.an);
        String s2 = s;
        if (s == null) {
            s2 = "10000";
        }
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(s2);
            this.J = sb.toString();
        }
        finally {
            monitorexit(an);
        }
    }
    
    public final void b(String string, final String s) {
        if (!com.tencent.bugly.proguard.z.a(string)) {
            if (!com.tencent.bugly.proguard.z.a(s)) {
                final Object ak = this.ak;
                synchronized (ak) {
                    this.ac.put((Object)string, (Object)s);
                    return;
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(string);
        string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s);
        com.tencent.bugly.proguard.x.d("key&value should not be empty %s %s", string, sb2.toString());
    }
    
    public final void b(final boolean s) {
        this.S = s;
    }
    
    public final String c() {
        return this.f;
    }
    
    public final void c(final String i) {
        this.I = i;
        final Object ao = this.ao;
        synchronized (ao) {
            this.ad.put((Object)"E8", (Object)i);
        }
    }
    
    public final void c(final String s, final String s2) {
        if (!com.tencent.bugly.proguard.z.a(s)) {
            if (!com.tencent.bugly.proguard.z.a(s2)) {
                final Object al = this.al;
                synchronized (al) {
                    this.ae.put((Object)s, (Object)s2);
                    return;
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s2);
        com.tencent.bugly.proguard.x.d("server key&value should not be empty %s %s", string, sb2.toString());
    }
    
    public final void d() {
        final Object ai = this.ai;
        synchronized (ai) {
            this.H = UUID.randomUUID().toString();
        }
    }
    
    public final void d(final String s) {
        monitorenter(this);
        monitorexit(this);
    }
    
    public final String e() {
        final Object ai = this.ai;
        synchronized (ai) {
            if (this.H == null) {
                final Object ai2 = this.ai;
                synchronized (ai2) {
                    this.H = UUID.randomUUID().toString();
                }
            }
            return this.H;
        }
    }
    
    public final void e(final String s) {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            this.K = sb.toString();
        }
    }
    
    public final String f() {
        if (!com.tencent.bugly.proguard.z.a((String)null)) {
            return null;
        }
        return this.U;
    }
    
    public final String f(String s) {
        if (com.tencent.bugly.proguard.z.a(s)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            com.tencent.bugly.proguard.x.d("key should not be empty %s", sb.toString());
            return null;
        }
        final Object ak = this.ak;
        synchronized (ak) {
            s = (String)this.ac.remove((Object)s);
            return s;
        }
    }
    
    public final String g() {
        final Object an = this.an;
        synchronized (an) {
            return this.J;
        }
    }
    
    public final String g(String s) {
        if (com.tencent.bugly.proguard.z.a(s)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            com.tencent.bugly.proguard.x.d("key should not be empty %s", sb.toString());
            return null;
        }
        final Object ak = this.ak;
        synchronized (ak) {
            s = (String)this.ac.get((Object)s);
            return s;
        }
    }
    
    public final String h() {
        final String i = this.I;
        if (i != null) {
            return i;
        }
        String l;
        if (!this.S) {
            l = "";
        }
        else {
            if (this.L == null) {
                this.L = com.tencent.bugly.crashreport.common.info.b.a(this.G);
            }
            l = this.L;
        }
        return this.I = l;
    }
    
    public final String i() {
        synchronized (this) {
            return this.K;
        }
    }
    
    public final long j() {
        if (this.M <= 0L) {
            this.M = com.tencent.bugly.crashreport.common.info.b.d();
        }
        return this.M;
    }
    
    public final long k() {
        if (this.N <= 0L) {
            this.N = com.tencent.bugly.crashreport.common.info.b.f();
        }
        return this.N;
    }
    
    public final long l() {
        if (this.O <= 0L) {
            this.O = com.tencent.bugly.crashreport.common.info.b.h();
        }
        return this.O;
    }
    
    public final String m() {
        if (this.P == null) {
            this.P = com.tencent.bugly.crashreport.common.info.b.a(this.G, true);
        }
        return this.P;
    }
    
    public final String n() {
        if (this.Q == null) {
            this.Q = com.tencent.bugly.crashreport.common.info.b.d(this.G);
        }
        return this.Q;
    }
    
    public final String o() {
        try {
            final Map all = this.G.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                final Object aj = this.aj;
                synchronized (aj) {
                    for (final Map$Entry map$Entry : all.entrySet()) {
                        try {
                            this.C.put(map$Entry.getKey(), (Object)map$Entry.getValue().toString());
                        }
                        finally {
                            final Throwable t;
                            com.tencent.bugly.proguard.x.a(t);
                        }
                    }
                }
            }
        }
        finally {
            final Throwable t2;
            com.tencent.bugly.proguard.x.a(t2);
        }
        if (!this.C.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final Map$Entry map$Entry2 : this.C.entrySet()) {
                sb.append("[");
                sb.append((String)map$Entry2.getKey());
                sb.append(",");
                sb.append((String)map$Entry2.getValue());
                sb.append("] ");
            }
            com.tencent.bugly.proguard.x.c("SDK_INFO = %s", sb.toString());
            this.c("SDK_INFO", sb.toString());
            return sb.toString();
        }
        com.tencent.bugly.proguard.x.c("SDK_INFO is empty", new Object[0]);
        return null;
    }
    
    public final Map<String, PlugInBean> p() {
        monitorenter(this);
        monitorexit(this);
        return null;
    }
    
    public final String q() {
        if (this.T == null) {
            this.T = com.tencent.bugly.crashreport.common.info.b.j();
        }
        return this.T;
    }
    
    public final Boolean r() {
        if (this.V == null) {
            this.V = com.tencent.bugly.crashreport.common.info.b.l();
        }
        return this.V;
    }
    
    public final String s() {
        if (this.W == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(com.tencent.bugly.crashreport.common.info.b.c(this.G));
            final String string = sb.toString();
            this.W = string;
            com.tencent.bugly.proguard.x.a("ROM ID: %s", string);
        }
        return this.W;
    }
    
    public final Map<String, String> t() {
        final Object ak = this.ak;
        synchronized (ak) {
            if (this.ac.size() <= 0) {
                return null;
            }
            return (Map<String, String>)new HashMap((Map)this.ac);
        }
    }
    
    public final void u() {
        final Object ak = this.ak;
        synchronized (ak) {
            this.ac.clear();
        }
    }
    
    public final int v() {
        final Object ak = this.ak;
        synchronized (ak) {
            return this.ac.size();
        }
    }
    
    public final Set<String> w() {
        final Object ak = this.ak;
        synchronized (ak) {
            return (Set<String>)this.ac.keySet();
        }
    }
    
    public final Map<String, String> x() {
        final Object ao = this.ao;
        synchronized (ao) {
            if (this.ad.size() <= 0) {
                return null;
            }
            return (Map<String, String>)new HashMap((Map)this.ad);
        }
    }
    
    public final Map<String, String> y() {
        final Object al = this.al;
        synchronized (al) {
            if (this.ae.size() <= 0) {
                return null;
            }
            return (Map<String, String>)new HashMap((Map)this.ae);
        }
    }
    
    public final int z() {
        final Object am = this.am;
        synchronized (am) {
            return this.aa;
        }
    }
}
