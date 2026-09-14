package com.tencent.bugly.proguard;

import java.util.Set;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import android.text.TextUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import java.util.Iterator;
import java.util.Map;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.Map$Entry;
import com.tencent.bugly.crashreport.common.info.b;
import android.content.Context;
import java.util.HashMap;
import java.net.Proxy;

public class a
{
    private static Proxy e;
    protected HashMap<String, HashMap<String, byte[]>> a;
    protected String b;
    i c;
    private HashMap<String, Object> d;
    
    a() {
        this.a = (HashMap<String, HashMap<String, byte[]>>)new HashMap();
        new HashMap();
        this.d = (HashMap<String, Object>)new HashMap();
        this.b = "GBK";
        this.c = new i();
    }
    
    public static am a(final Context context, final int g, final byte[] array) {
        final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
        final StrategyBean c = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (b != null) {
            if (c != null) {
                try {
                    final am am = new am();
                    synchronized (b) {
                        am.a = 1;
                        am.b = b.f();
                        am.c = b.c;
                        am.d = b.k;
                        am.e = b.m;
                        am.f = b.f;
                        am.g = g;
                        byte[] bytes = array;
                        if (array == null) {
                            bytes = "".getBytes();
                        }
                        am.h = bytes;
                        am.i = b.h;
                        am.j = b.i;
                        am.k = (Map)new HashMap();
                        am.l = b.e();
                        am.m = c.n;
                        am.o = b.h();
                        am.p = com.tencent.bugly.crashreport.common.info.b.b(context);
                        am.q = System.currentTimeMillis();
                        am.r = b.i();
                        final StringBuilder sb = new StringBuilder();
                        sb.append(b.h());
                        am.s = sb.toString();
                        am.t = am.p;
                        b.getClass();
                        am.n = "com.tencent.bugly";
                        final Map k = am.k;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(b.s());
                        k.put((Object)"A26", (Object)sb2.toString());
                        final Map i = am.k;
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(b.D());
                        i.put((Object)"A62", (Object)sb3.toString());
                        final Map j = am.k;
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append(b.E());
                        j.put((Object)"A63", (Object)sb4.toString());
                        final Map l = am.k;
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append(b.B);
                        l.put((Object)"F11", (Object)sb5.toString());
                        final Map m = am.k;
                        final StringBuilder sb6 = new StringBuilder();
                        sb6.append(b.A);
                        m.put((Object)"F12", (Object)sb6.toString());
                        final Map k2 = am.k;
                        final StringBuilder sb7 = new StringBuilder();
                        sb7.append(b.l);
                        k2.put((Object)"D3", (Object)sb7.toString());
                        if (com.tencent.bugly.b.b != null) {
                            for (final com.tencent.bugly.a a : com.tencent.bugly.b.b) {
                                if (a.versionKey != null && a.version != null) {
                                    am.k.put((Object)a.versionKey, (Object)a.version);
                                }
                            }
                        }
                        am.k.put((Object)"G15", (Object)z.b("G15", ""));
                        am.k.put((Object)"D4", (Object)z.b("D4", "0"));
                        monitorexit(b);
                        final Map<String, String> x = b.x();
                        if (x != null) {
                            for (final Map$Entry map$Entry : x.entrySet()) {
                                am.k.put(map$Entry.getKey(), map$Entry.getValue());
                            }
                        }
                        return am;
                    }
                }
                finally {
                    final Throwable t;
                    if (!x.b(t)) {
                        t.printStackTrace();
                    }
                    return null;
                }
            }
        }
        x.e("Can not create request pkg for parameters is invalid.", new Object[0]);
        return null;
    }
    
    public static aq a(final UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        final aq aq = new aq();
        aq.a = userInfoBean.e;
        aq.e = userInfoBean.j;
        aq.d = userInfoBean.c;
        aq.c = userInfoBean.d;
        aq.g = (userInfoBean.o == 1);
        final int b = userInfoBean.b;
        if (b != 1) {
            if (b != 2) {
                if (b != 3) {
                    if (b != 4) {
                        if (userInfoBean.b < 10 || userInfoBean.b >= 20) {
                            x.e("unknown uinfo type %d ", userInfoBean.b);
                            return null;
                        }
                        aq.b = (byte)userInfoBean.b;
                    }
                    else {
                        aq.b = 3;
                    }
                }
                else {
                    aq.b = 2;
                }
            }
            else {
                aq.b = 4;
            }
        }
        else {
            aq.b = 1;
        }
        aq.f = (Map)new HashMap();
        if (userInfoBean.p >= 0) {
            final Map f = aq.f;
            final StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            f.put((Object)"C01", (Object)sb.toString());
        }
        if (userInfoBean.q >= 0) {
            final Map f2 = aq.f;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            f2.put((Object)"C02", (Object)sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (final Map$Entry map$Entry : userInfoBean.r.entrySet()) {
                final Map f3 = aq.f;
                final StringBuilder sb3 = new StringBuilder("C03_");
                sb3.append((String)map$Entry.getKey());
                f3.put((Object)sb3.toString(), map$Entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (final Map$Entry map$Entry2 : userInfoBean.s.entrySet()) {
                final Map f4 = aq.f;
                final StringBuilder sb4 = new StringBuilder("C04_");
                sb4.append((String)map$Entry2.getKey());
                f4.put((Object)sb4.toString(), map$Entry2.getValue());
            }
        }
        final Map f5 = aq.f;
        final StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.l ^ true);
        f5.put((Object)"A36", (Object)sb5.toString());
        final Map f6 = aq.f;
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.g);
        f6.put((Object)"F02", (Object)sb6.toString());
        final Map f7 = aq.f;
        final StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.h);
        f7.put((Object)"F03", (Object)sb7.toString());
        final Map f8 = aq.f;
        final StringBuilder sb8 = new StringBuilder();
        sb8.append(userInfoBean.j);
        f8.put((Object)"F04", (Object)sb8.toString());
        final Map f9 = aq.f;
        final StringBuilder sb9 = new StringBuilder();
        sb9.append(userInfoBean.i);
        f9.put((Object)"F05", (Object)sb9.toString());
        final Map f10 = aq.f;
        final StringBuilder sb10 = new StringBuilder();
        sb10.append(userInfoBean.m);
        f10.put((Object)"F06", (Object)sb10.toString());
        final Map f11 = aq.f;
        final StringBuilder sb11 = new StringBuilder();
        sb11.append(userInfoBean.k);
        f11.put((Object)"F10", (Object)sb11.toString());
        x.c("summary type %d vm:%d", aq.b, aq.f.size());
        return aq;
    }
    
    public static ar a(final List<UserInfoBean> list, final int n) {
        if (list == null || list.size() == 0) {
            return null;
        }
        final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
        if (b == null) {
            return null;
        }
        b.o();
        final ar ar = new ar();
        ar.b = b.d;
        ar.c = b.h();
        final ArrayList d = new ArrayList();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            final aq a = a((UserInfoBean)iterator.next());
            if (a != null) {
                d.add((Object)a);
            }
        }
        ar.d = d;
        ar.e = (Map)new HashMap();
        final Map e = ar.e;
        final StringBuilder sb = new StringBuilder();
        sb.append(b.g);
        e.put((Object)"A7", (Object)sb.toString());
        final Map e2 = ar.e;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(b.n());
        e2.put((Object)"A6", (Object)sb2.toString());
        final Map e3 = ar.e;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(b.m());
        e3.put((Object)"A5", (Object)sb3.toString());
        final Map e4 = ar.e;
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(b.k());
        e4.put((Object)"A2", (Object)sb4.toString());
        final Map e5 = ar.e;
        final StringBuilder sb5 = new StringBuilder();
        sb5.append(b.k());
        e5.put((Object)"A1", (Object)sb5.toString());
        final Map e6 = ar.e;
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(b.i);
        e6.put((Object)"A24", (Object)sb6.toString());
        final Map e7 = ar.e;
        final StringBuilder sb7 = new StringBuilder();
        sb7.append(b.l());
        e7.put((Object)"A17", (Object)sb7.toString());
        final Map e8 = ar.e;
        final StringBuilder sb8 = new StringBuilder();
        sb8.append(b.q());
        e8.put((Object)"A15", (Object)sb8.toString());
        final Map e9 = ar.e;
        final StringBuilder sb9 = new StringBuilder();
        sb9.append((Object)b.r());
        e9.put((Object)"A13", (Object)sb9.toString());
        final Map e10 = ar.e;
        final StringBuilder sb10 = new StringBuilder();
        sb10.append(b.w);
        e10.put((Object)"F08", (Object)sb10.toString());
        final Map e11 = ar.e;
        final StringBuilder sb11 = new StringBuilder();
        sb11.append(b.x);
        e11.put((Object)"F09", (Object)sb11.toString());
        final Map<String, String> y = b.y();
        if (y != null && y.size() > 0) {
            for (final Map$Entry map$Entry : y.entrySet()) {
                final Map e12 = ar.e;
                final StringBuilder sb12 = new StringBuilder("C04_");
                sb12.append((String)map$Entry.getKey());
                e12.put((Object)sb12.toString(), map$Entry.getValue());
            }
        }
        if (n != 1) {
            if (n != 2) {
                x.e("unknown up type %d ", n);
                return null;
            }
            ar.a = 2;
        }
        else {
            ar.a = 1;
        }
        return ar;
    }
    
    public static <T extends k> T a(final byte[] array, final Class<T> clazz) {
        if (array != null) {
            if (array.length > 0) {
                try {
                    final k k = clazz.newInstance();
                    final i i = new i(array);
                    i.a("utf-8");
                    k.a(i);
                    return (T)k;
                }
                finally {
                    final Throwable t;
                    if (!x.b(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    
    public static String a(final ArrayList<String> list) {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        while (true) {
            final int size = list.size();
            String s = "map";
            if (n >= size) {
                Collections.reverse((List)list);
                for (int i = 0; i < list.size(); ++i) {
                    final String s2 = (String)list.get(i);
                    if (s2.equals((Object)"list")) {
                        final int n2 = i - 1;
                        final StringBuilder sb2 = new StringBuilder("<");
                        sb2.append((String)list.get(n2));
                        list.set(n2, (Object)sb2.toString());
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append((String)list.get(0));
                        sb3.append(">");
                        list.set(0, (Object)sb3.toString());
                    }
                    else if (s2.equals((Object)"map")) {
                        final int n3 = i - 1;
                        final StringBuilder sb4 = new StringBuilder("<");
                        sb4.append((String)list.get(n3));
                        sb4.append(",");
                        list.set(n3, (Object)sb4.toString());
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append((String)list.get(0));
                        sb5.append(">");
                        list.set(0, (Object)sb5.toString());
                    }
                    else if (s2.equals((Object)"Array")) {
                        final int n4 = i - 1;
                        final StringBuilder sb6 = new StringBuilder("<");
                        sb6.append((String)list.get(n4));
                        list.set(n4, (Object)sb6.toString());
                        final StringBuilder sb7 = new StringBuilder();
                        sb7.append((String)list.get(0));
                        sb7.append(">");
                        list.set(0, (Object)sb7.toString());
                    }
                }
                Collections.reverse((List)list);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    sb.append((String)iterator.next());
                }
                return sb.toString();
            }
            final String s3 = (String)list.get(n);
            if (!s3.equals((Object)"java.lang.Integer") && !s3.equals((Object)"int")) {
                if (!s3.equals((Object)"java.lang.Boolean") && !s3.equals((Object)"boolean")) {
                    if (!s3.equals((Object)"java.lang.Byte") && !s3.equals((Object)"byte")) {
                        if (!s3.equals((Object)"java.lang.Double") && !s3.equals((Object)"double")) {
                            if (!s3.equals((Object)"java.lang.Float") && !s3.equals((Object)"float")) {
                                if (!s3.equals((Object)"java.lang.Long") && !s3.equals((Object)"long")) {
                                    if (!s3.equals((Object)"java.lang.Short") && !s3.equals((Object)"short")) {
                                        if (s3.equals((Object)"java.lang.Character")) {
                                            throw new IllegalArgumentException("can not support java.lang.Character");
                                        }
                                        if (s3.equals((Object)"java.lang.String")) {
                                            s = "string";
                                        }
                                        else if (s3.equals((Object)"java.util.List")) {
                                            s = "list";
                                        }
                                        else if (!s3.equals((Object)"java.util.Map")) {
                                            s = s3;
                                        }
                                    }
                                    else {
                                        s = "short";
                                    }
                                }
                                else {
                                    s = "int64";
                                }
                            }
                            else {
                                s = "float";
                            }
                        }
                        else {
                            s = "double";
                        }
                    }
                    else {
                        s = "char";
                    }
                }
                else {
                    s = "bool";
                }
            }
            else {
                s = "int32";
            }
            list.set(n, (Object)s);
            ++n;
        }
    }
    
    public static void a(final String s, final int n) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            a.e = null;
            return;
        }
        a.e = new Proxy(Proxy$Type.HTTP, (SocketAddress)new InetSocketAddress(s, n));
    }
    
    public static void a(final InetAddress inetAddress, final int n) {
        if (inetAddress == null) {
            a.e = null;
            return;
        }
        a.e = new Proxy(Proxy$Type.HTTP, (SocketAddress)new InetSocketAddress(inetAddress, n));
    }
    
    private void a(final ArrayList<String> list, Object next) {
        if (next.getClass().isArray()) {
            if (!next.getClass().getComponentType().toString().equals((Object)"byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(next) > 0) {
                list.add((Object)"java.util.List");
                this.a(list, Array.get(next, 0));
                return;
            }
            list.add((Object)"Array");
            list.add((Object)"?");
        }
        else {
            if (!(next instanceof Array)) {
                if (next instanceof List) {
                    list.add((Object)"java.util.List");
                    final List list2 = (List)next;
                    if (list2.size() <= 0) {
                        list.add((Object)"?");
                        return;
                    }
                    this.a(list, list2.get(0));
                }
                else if (next instanceof Map) {
                    list.add((Object)"java.util.Map");
                    final Map map = (Map)next;
                    if (map.size() <= 0) {
                        list.add((Object)"?");
                        list.add((Object)"?");
                        return;
                    }
                    next = map.keySet().iterator().next();
                    final Object value = map.get(next);
                    list.add((Object)next.getClass().getName());
                    this.a(list, value);
                }
                else {
                    list.add((Object)next.getClass().getName());
                }
                return;
            }
            throw new IllegalArgumentException("can not support Array, please use List");
        }
    }
    
    public static byte[] a(final k k) {
        try {
            final j j = new j();
            j.a("utf-8");
            k.a(j);
            return j.b();
        }
        finally {
            final Throwable t;
            if (!x.b(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static byte[] a(final Object o) {
        try {
            final d d = new d();
            d.c();
            d.a("utf-8");
            d.a(1);
            d.b("RqdServer");
            d.c("sync");
            d.a("detail", o);
            return d.a();
        }
        finally {
            final Throwable t;
            if (!x.b(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static an b(final byte[] array) {
        final an an = null;
        if (array != null) {
            try {
                final d d = new d();
                d.c();
                d.a("utf-8");
                d.a(array);
                final Object b = d.b("detail", (Object)new an());
                an an2 = an;
                if (an.class.isInstance(b)) {
                    an2 = an.class.cast(b);
                }
                return an2;
            }
            finally {
                final Throwable t;
                if (!x.b(t)) {
                    t.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public static Proxy b() {
        return a.e;
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public <T> void a(final String s, final T t) {
        if (s == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (!(t instanceof Set)) {
            final j j = new j();
            j.a(this.b);
            j.a(t, 0);
            final byte[] a = l.a(j.a());
            final HashMap hashMap = new HashMap(1);
            final ArrayList list = new ArrayList(1);
            this.a((ArrayList<String>)list, t);
            hashMap.put((Object)a((ArrayList<String>)list), (Object)a);
            this.d.remove((Object)s);
            this.a.put((Object)s, (Object)hashMap);
            return;
        }
        throw new IllegalArgumentException("can not support Set");
    }
    
    public void a(final byte[] array) {
        this.c.a(array);
        this.c.a(this.b);
        final HashMap hashMap = new HashMap(1);
        final HashMap hashMap2 = new HashMap(1);
        hashMap2.put((Object)"", (Object)new byte[0]);
        hashMap.put((Object)"", (Object)hashMap2);
        this.a = this.c.a((java.util.Map<String, HashMap<String, byte[]>>)hashMap, 0, false);
    }
    
    public byte[] a() {
        final j j = new j(0);
        j.a(this.b);
        j.a((java.util.Map<Object, Object>)this.a, 0);
        return l.a(j.a());
    }
}
