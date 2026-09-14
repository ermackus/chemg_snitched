package com.tencent.bugly.crashreport.crash;

import java.util.Arrays;
import java.util.LinkedHashMap;
import com.tencent.bugly.proguard.r;
import java.util.regex.Pattern;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.k;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.Collection;
import android.os.Parcelable;
import android.content.ContentValues;
import java.util.Date;
import java.util.Map;
import java.net.URLEncoder;
import java.util.HashMap;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.ah;
import java.util.Map$Entry;
import com.tencent.bugly.proguard.ak;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.tencent.bugly.proguard.aj;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import android.database.Cursor;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.u;
import android.content.Context;

public final class b
{
    private static int a;
    private Context b;
    private u c;
    private p d;
    private a e;
    private o f;
    private BuglyStrategy.a g;
    
    public b(final int a, final Context b, final u c, final p d, final a e, final BuglyStrategy.a g, final o f) {
        com.tencent.bugly.crashreport.crash.b.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.g = g;
        this.f = f;
    }
    
    private static CrashDetailBean a(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            final byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            final long long1 = cursor.getLong(cursor.getColumnIndex("_id"));
            final CrashDetailBean crashDetailBean = z.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = long1;
            }
            return crashDetailBean;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private CrashDetailBean a(final List<com.tencent.bugly.crashreport.crash.a> list, final CrashDetailBean crashDetailBean) {
        if (list != null && list.size() != 0) {
            final CrashDetailBean crashDetailBean2 = null;
            CrashDetailBean crashDetailBean3 = null;
            final ArrayList list2 = new ArrayList(10);
            for (final com.tencent.bugly.crashreport.crash.a a : list) {
                if (a.e) {
                    ((List)list2).add((Object)a);
                }
            }
            CrashDetailBean crashDetailBean4 = crashDetailBean2;
            if (((List)list2).size() > 0) {
                final List<CrashDetailBean> b = this.b((List<com.tencent.bugly.crashreport.crash.a>)list2);
                crashDetailBean4 = crashDetailBean2;
                if (b != null) {
                    crashDetailBean4 = crashDetailBean2;
                    if (b.size() > 0) {
                        Collections.sort((List)b);
                        int n = 0;
                        while (true) {
                            crashDetailBean4 = crashDetailBean3;
                            if (n >= b.size()) {
                                break;
                            }
                            final CrashDetailBean crashDetailBean5 = (CrashDetailBean)b.get(n);
                            CrashDetailBean crashDetailBean6;
                            if (n == 0) {
                                crashDetailBean6 = crashDetailBean5;
                            }
                            else {
                                crashDetailBean6 = crashDetailBean3;
                                if (crashDetailBean5.s != null) {
                                    final String[] split = crashDetailBean5.s.split("\n");
                                    crashDetailBean6 = crashDetailBean3;
                                    if (split != null) {
                                        final int length = split.length;
                                        int n2 = 0;
                                        while (true) {
                                            crashDetailBean6 = crashDetailBean3;
                                            if (n2 >= length) {
                                                break;
                                            }
                                            final String s = split[n2];
                                            final String s2 = crashDetailBean3.s;
                                            final StringBuilder sb = new StringBuilder();
                                            sb.append(s);
                                            if (!s2.contains((CharSequence)sb.toString())) {
                                                ++crashDetailBean3.t;
                                                final StringBuilder sb2 = new StringBuilder();
                                                sb2.append(crashDetailBean3.s);
                                                sb2.append(s);
                                                sb2.append("\n");
                                                crashDetailBean3.s = sb2.toString();
                                            }
                                            ++n2;
                                        }
                                    }
                                }
                            }
                            ++n;
                            crashDetailBean3 = crashDetailBean6;
                        }
                    }
                }
            }
            CrashDetailBean crashDetailBean7;
            if ((crashDetailBean7 = crashDetailBean4) == null) {
                crashDetailBean.j = true;
                crashDetailBean.t = 0;
                crashDetailBean.s = "";
                crashDetailBean7 = crashDetailBean;
            }
            for (final com.tencent.bugly.crashreport.crash.a a2 : list) {
                if (!a2.e && !a2.d) {
                    final String s3 = crashDetailBean7.s;
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(a2.b);
                    if (s3.contains((CharSequence)sb3.toString())) {
                        continue;
                    }
                    ++crashDetailBean7.t;
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(crashDetailBean7.s);
                    sb4.append(a2.b);
                    sb4.append("\n");
                    crashDetailBean7.s = sb4.toString();
                }
            }
            if (crashDetailBean7.r != crashDetailBean.r) {
                final String s4 = crashDetailBean7.s;
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(crashDetailBean.r);
                if (!s4.contains((CharSequence)sb5.toString())) {
                    ++crashDetailBean7.t;
                    final StringBuilder sb6 = new StringBuilder();
                    sb6.append(crashDetailBean7.s);
                    sb6.append(crashDetailBean.r);
                    sb6.append("\n");
                    crashDetailBean7.s = sb6.toString();
                }
            }
            return crashDetailBean7;
        }
        return crashDetailBean;
    }
    
    private static aj a(String s, Context context, String s2) {
        if (s2 != null) {
            if (context != null) {
                x.c("zip %s", s2);
                final File file = new File(s2);
                s2 = (String)new File(context.getCacheDir(), s);
                if (!z.a(file, (File)s2, 5000)) {
                    x.d("zip fail!", new Object[0]);
                    return null;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    final FileInputStream fileInputStream = new FileInputStream((File)s2);
                    try {
                        final byte[] array = new byte[4096];
                        while (true) {
                            final int read = fileInputStream.read(array);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(array, 0, read);
                            byteArrayOutputStream.flush();
                        }
                        final byte[] byteArray = byteArrayOutputStream.toByteArray();
                        x.c("read bytes :%d", byteArray.length);
                        context = (Context)new aj((byte)2, ((File)s2).getName(), byteArray);
                        try {
                            fileInputStream.close();
                        }
                        catch (final IOException ex) {
                            if (!x.a((Throwable)ex)) {
                                ex.printStackTrace();
                            }
                        }
                        if (((File)s2).exists()) {
                            x.c("del tmp", new Object[0]);
                            ((File)s2).delete();
                        }
                        return (aj)context;
                    }
                    finally {}
                }
                finally {
                    s = null;
                }
                try {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return null;
                }
                finally {
                    if (s != null) {
                        try {
                            ((FileInputStream)s).close();
                        }
                        catch (final IOException ex2) {
                            if (!x.a((Throwable)ex2)) {
                                ex2.printStackTrace();
                            }
                        }
                    }
                    if (((File)s2).exists()) {
                        x.c("del tmp", new Object[0]);
                        ((File)s2).delete();
                    }
                }
            }
        }
        x.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
        return null;
    }
    
    private static ak a(final Context context, final CrashDetailBean crashDetailBean, final com.tencent.bugly.crashreport.common.info.a a) {
        boolean b = false;
        if (context != null && crashDetailBean != null && a != null) {
            final ak ak = new ak();
            switch (crashDetailBean.b) {
                default: {
                    x.e("crash type error! %d", crashDetailBean.b);
                    break;
                }
                case 7: {
                    String a2;
                    if (crashDetailBean.j) {
                        a2 = "208";
                    }
                    else {
                        a2 = "108";
                    }
                    ak.a = a2;
                    break;
                }
                case 6: {
                    String a3;
                    if (crashDetailBean.j) {
                        a3 = "206";
                    }
                    else {
                        a3 = "106";
                    }
                    ak.a = a3;
                    break;
                }
                case 5: {
                    String a4;
                    if (crashDetailBean.j) {
                        a4 = "207";
                    }
                    else {
                        a4 = "107";
                    }
                    ak.a = a4;
                    break;
                }
                case 4: {
                    String a5;
                    if (crashDetailBean.j) {
                        a5 = "204";
                    }
                    else {
                        a5 = "104";
                    }
                    ak.a = a5;
                    break;
                }
                case 3: {
                    String a6;
                    if (crashDetailBean.j) {
                        a6 = "203";
                    }
                    else {
                        a6 = "103";
                    }
                    ak.a = a6;
                    break;
                }
                case 2: {
                    String a7;
                    if (crashDetailBean.j) {
                        a7 = "202";
                    }
                    else {
                        a7 = "102";
                    }
                    ak.a = a7;
                    break;
                }
                case 1: {
                    String a8;
                    if (crashDetailBean.j) {
                        a8 = "201";
                    }
                    else {
                        a8 = "101";
                    }
                    ak.a = a8;
                    break;
                }
                case 0: {
                    String a9;
                    if (crashDetailBean.j) {
                        a9 = "200";
                    }
                    else {
                        a9 = "100";
                    }
                    ak.a = a9;
                    break;
                }
            }
            ak.b = crashDetailBean.r;
            ak.c = crashDetailBean.n;
            ak.d = crashDetailBean.o;
            ak.e = crashDetailBean.p;
            ak.g = crashDetailBean.q;
            ak.h = crashDetailBean.z;
            ak.i = crashDetailBean.c;
            ak.j = null;
            ak.l = crashDetailBean.m;
            ak.m = crashDetailBean.e;
            ak.f = crashDetailBean.B;
            ak.n = null;
            x.c("libInfo %s", ak.o);
            if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
                ak.p = new ArrayList();
                for (final Map$Entry map$Entry : crashDetailBean.h.entrySet()) {
                    final ah ah = new ah();
                    ah.a = ((PlugInBean)map$Entry.getValue()).a;
                    ah.b = ((PlugInBean)map$Entry.getValue()).c;
                    ah.c = ((PlugInBean)map$Entry.getValue()).b;
                    ak.p.add((Object)ah);
                }
            }
            if (crashDetailBean.j) {
                ak.k = crashDetailBean.t;
                if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                    if (ak.q == null) {
                        ak.q = new ArrayList();
                    }
                    try {
                        ak.q.add((Object)new aj((byte)1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                    }
                    catch (final UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                        ak.q = null;
                    }
                }
                final int k = ak.k;
                int size;
                if (ak.q != null) {
                    size = ak.q.size();
                }
                else {
                    size = 0;
                }
                x.c("crashcount:%d sz:%d", k, size);
            }
            if (crashDetailBean.w != null) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                try {
                    ak.q.add((Object)new aj((byte)1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
                }
                catch (final UnsupportedEncodingException ex2) {
                    ex2.printStackTrace();
                    ak.q = null;
                }
            }
            if (crashDetailBean.x != null) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                try {
                    ak.q.add((Object)new aj((byte)1, "jniLog.txt", crashDetailBean.x.getBytes("utf-8")));
                }
                catch (final UnsupportedEncodingException ex3) {
                    ex3.printStackTrace();
                    ak.q = null;
                }
            }
            if (!z.a(crashDetailBean.V)) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                Object o;
                try {
                    o = new aj((byte)1, "crashInfos.txt", crashDetailBean.V.getBytes("utf-8"));
                }
                catch (final UnsupportedEncodingException ex4) {
                    ex4.printStackTrace();
                    o = null;
                }
                if (o != null) {
                    x.c("attach crash infos", new Object[0]);
                    ak.q.add(o);
                }
            }
            if (crashDetailBean.W != null) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                final aj a10 = a("backupRecord.zip", context, crashDetailBean.W);
                if (a10 != null) {
                    x.c("attach backup record", new Object[0]);
                    ak.q.add((Object)a10);
                }
            }
            if (crashDetailBean.y != null && crashDetailBean.y.length > 0) {
                final aj aj = new aj((byte)2, "buglylog.zip", crashDetailBean.y);
                x.c("attach user log", new Object[0]);
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                ak.q.add((Object)aj);
            }
            if (crashDetailBean.b == 3) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                x.c("crashBean.anrMessages:%s", crashDetailBean.P);
                if (crashDetailBean.P != null && crashDetailBean.P.containsKey((Object)"BUGLY_CR_01")) {
                    try {
                        if (!TextUtils.isEmpty((CharSequence)crashDetailBean.P.get((Object)"BUGLY_CR_01"))) {
                            ak.q.add((Object)new aj((byte)1, "anrMessage.txt", ((String)crashDetailBean.P.get((Object)"BUGLY_CR_01")).getBytes("utf-8")));
                            x.c("attach anr message", new Object[0]);
                        }
                    }
                    catch (final UnsupportedEncodingException ex5) {
                        ex5.printStackTrace();
                        ak.q = null;
                    }
                    crashDetailBean.P.remove((Object)"BUGLY_CR_01");
                }
                if (crashDetailBean.v != null && NativeCrashHandler.getInstance().isEnableCatchAnrTrace()) {
                    final aj a11 = a("trace.zip", context, crashDetailBean.v);
                    if (a11 != null) {
                        x.c("attach traces", new Object[0]);
                        ak.q.add((Object)a11);
                    }
                }
            }
            if (crashDetailBean.b == 1) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                if (crashDetailBean.v != null) {
                    final aj a12 = a("tomb.zip", context, crashDetailBean.v);
                    if (a12 != null) {
                        x.c("attach tombs", new Object[0]);
                        ak.q.add((Object)a12);
                    }
                }
            }
            if (a.D != null && !a.D.isEmpty()) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                final StringBuilder sb = new StringBuilder();
                final Iterator iterator2 = a.D.iterator();
                while (iterator2.hasNext()) {
                    sb.append((String)iterator2.next());
                }
                try {
                    ak.q.add((Object)new aj((byte)1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                    x.c("attach pageTracingList", new Object[0]);
                }
                catch (final UnsupportedEncodingException ex6) {
                    ex6.printStackTrace();
                }
            }
            if (crashDetailBean.U != null && crashDetailBean.U.length > 0) {
                if (ak.q == null) {
                    ak.q = new ArrayList();
                }
                ak.q.add((Object)new aj((byte)1, "userExtraByteData", crashDetailBean.U));
                x.c("attach extraData", new Object[0]);
            }
            ak.r = (Map)new HashMap();
            final Map r = ak.r;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.C);
            r.put((Object)"A9", (Object)sb2.toString());
            final Map r2 = ak.r;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(crashDetailBean.D);
            r2.put((Object)"A11", (Object)sb3.toString());
            final Map r3 = ak.r;
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(crashDetailBean.E);
            r3.put((Object)"A10", (Object)sb4.toString());
            final Map r4 = ak.r;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(crashDetailBean.f);
            r4.put((Object)"A23", (Object)sb5.toString());
            final Map r5 = ak.r;
            final StringBuilder sb6 = new StringBuilder();
            sb6.append(a.g);
            r5.put((Object)"A7", (Object)sb6.toString());
            final Map r6 = ak.r;
            final StringBuilder sb7 = new StringBuilder();
            sb7.append(a.n());
            r6.put((Object)"A6", (Object)sb7.toString());
            final Map r7 = ak.r;
            final StringBuilder sb8 = new StringBuilder();
            sb8.append(a.m());
            r7.put((Object)"A5", (Object)sb8.toString());
            final Map r8 = ak.r;
            final StringBuilder sb9 = new StringBuilder();
            sb9.append(a.h());
            r8.put((Object)"A22", (Object)sb9.toString());
            final Map r9 = ak.r;
            final StringBuilder sb10 = new StringBuilder();
            sb10.append(crashDetailBean.G);
            r9.put((Object)"A2", (Object)sb10.toString());
            final Map r10 = ak.r;
            final StringBuilder sb11 = new StringBuilder();
            sb11.append(crashDetailBean.F);
            r10.put((Object)"A1", (Object)sb11.toString());
            final Map r11 = ak.r;
            final StringBuilder sb12 = new StringBuilder();
            sb12.append(a.i);
            r11.put((Object)"A24", (Object)sb12.toString());
            final Map r12 = ak.r;
            final StringBuilder sb13 = new StringBuilder();
            sb13.append(crashDetailBean.H);
            r12.put((Object)"A17", (Object)sb13.toString());
            final Map r13 = ak.r;
            final StringBuilder sb14 = new StringBuilder();
            sb14.append(a.h());
            r13.put((Object)"A25", (Object)sb14.toString());
            final Map r14 = ak.r;
            final StringBuilder sb15 = new StringBuilder();
            sb15.append(a.q());
            r14.put((Object)"A15", (Object)sb15.toString());
            final Map r15 = ak.r;
            final StringBuilder sb16 = new StringBuilder();
            sb16.append((Object)a.r());
            r15.put((Object)"A13", (Object)sb16.toString());
            final Map r16 = ak.r;
            final StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.A);
            r16.put((Object)"A34", (Object)sb17.toString());
            if (a.y != null) {
                final Map r17 = ak.r;
                final StringBuilder sb18 = new StringBuilder();
                sb18.append(a.y);
                r17.put((Object)"productIdentify", (Object)sb18.toString());
            }
            try {
                final Map r18 = ak.r;
                final StringBuilder sb19 = new StringBuilder();
                sb19.append(URLEncoder.encode(crashDetailBean.I, "utf-8"));
                r18.put((Object)"A26", (Object)sb19.toString());
            }
            catch (final UnsupportedEncodingException ex7) {
                ex7.printStackTrace();
            }
            if (crashDetailBean.b == 1) {
                final Map r19 = ak.r;
                final StringBuilder sb20 = new StringBuilder();
                sb20.append(crashDetailBean.K);
                r19.put((Object)"A27", (Object)sb20.toString());
                final Map r20 = ak.r;
                final StringBuilder sb21 = new StringBuilder();
                sb21.append(crashDetailBean.J);
                r20.put((Object)"A28", (Object)sb21.toString());
                final Map r21 = ak.r;
                final StringBuilder sb22 = new StringBuilder();
                sb22.append(crashDetailBean.k);
                r21.put((Object)"A29", (Object)sb22.toString());
            }
            final Map r22 = ak.r;
            final StringBuilder sb23 = new StringBuilder();
            sb23.append(crashDetailBean.L);
            r22.put((Object)"A30", (Object)sb23.toString());
            final Map r23 = ak.r;
            final StringBuilder sb24 = new StringBuilder();
            sb24.append(crashDetailBean.M);
            r23.put((Object)"A18", (Object)sb24.toString());
            final Map r24 = ak.r;
            final StringBuilder sb25 = new StringBuilder();
            sb25.append(crashDetailBean.N ^ true);
            r24.put((Object)"A36", (Object)sb25.toString());
            final Map r25 = ak.r;
            final StringBuilder sb26 = new StringBuilder();
            sb26.append(a.r);
            r25.put((Object)"F02", (Object)sb26.toString());
            final Map r26 = ak.r;
            final StringBuilder sb27 = new StringBuilder();
            sb27.append(a.s);
            r26.put((Object)"F03", (Object)sb27.toString());
            final Map r27 = ak.r;
            final StringBuilder sb28 = new StringBuilder();
            sb28.append(a.e());
            r27.put((Object)"F04", (Object)sb28.toString());
            final Map r28 = ak.r;
            final StringBuilder sb29 = new StringBuilder();
            sb29.append(a.t);
            r28.put((Object)"F05", (Object)sb29.toString());
            final Map r29 = ak.r;
            final StringBuilder sb30 = new StringBuilder();
            sb30.append(a.q);
            r29.put((Object)"F06", (Object)sb30.toString());
            final Map r30 = ak.r;
            final StringBuilder sb31 = new StringBuilder();
            sb31.append(a.w);
            r30.put((Object)"F08", (Object)sb31.toString());
            final Map r31 = ak.r;
            final StringBuilder sb32 = new StringBuilder();
            sb32.append(a.x);
            r31.put((Object)"F09", (Object)sb32.toString());
            final Map r32 = ak.r;
            final StringBuilder sb33 = new StringBuilder();
            sb33.append(a.u);
            r32.put((Object)"F10", (Object)sb33.toString());
            if (crashDetailBean.Q >= 0) {
                final Map r33 = ak.r;
                final StringBuilder sb34 = new StringBuilder();
                sb34.append(crashDetailBean.Q);
                r33.put((Object)"C01", (Object)sb34.toString());
            }
            if (crashDetailBean.R >= 0) {
                final Map r34 = ak.r;
                final StringBuilder sb35 = new StringBuilder();
                sb35.append(crashDetailBean.R);
                r34.put((Object)"C02", (Object)sb35.toString());
            }
            if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
                for (final Map$Entry map$Entry2 : crashDetailBean.S.entrySet()) {
                    final Map r35 = ak.r;
                    final StringBuilder sb36 = new StringBuilder("C03_");
                    sb36.append((String)map$Entry2.getKey());
                    r35.put((Object)sb36.toString(), map$Entry2.getValue());
                }
            }
            if (crashDetailBean.T != null && crashDetailBean.T.size() > 0) {
                for (final Map$Entry map$Entry3 : crashDetailBean.T.entrySet()) {
                    final Map r36 = ak.r;
                    final StringBuilder sb37 = new StringBuilder("C04_");
                    sb37.append((String)map$Entry3.getKey());
                    r36.put((Object)sb37.toString(), map$Entry3.getValue());
                }
            }
            ak.s = null;
            if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
                ak.s = crashDetailBean.O;
                x.a("setted message size %d", ak.s.size());
            }
            final String n = crashDetailBean.n;
            final String c = crashDetailBean.c;
            final String e = a.e();
            final long n2 = (crashDetailBean.r - crashDetailBean.M) / 1000L;
            final boolean i = crashDetailBean.k;
            final boolean n3 = crashDetailBean.N;
            final boolean j = crashDetailBean.j;
            if (crashDetailBean.b == 1) {
                b = true;
            }
            x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", n, c, e, n2, i, n3, j, b, crashDetailBean.t, crashDetailBean.s, crashDetailBean.d, ak.r.size());
            return ak;
        }
        x.d("enExp args == null", new Object[0]);
        return null;
    }
    
    private static List<com.tencent.bugly.crashreport.crash.a> a(final List<com.tencent.bugly.crashreport.crash.a> list) {
        if (list != null && list.size() != 0) {
            final long currentTimeMillis = System.currentTimeMillis();
            final ArrayList list2 = new ArrayList();
            for (final com.tencent.bugly.crashreport.crash.a a : list) {
                if (a.d && a.b <= currentTimeMillis - 86400000L) {
                    ((List)list2).add((Object)a);
                }
            }
            return (List<com.tencent.bugly.crashreport.crash.a>)list2;
        }
        return null;
    }
    
    public static void a(String string, String h, final String s, final String s2, final String s3, final CrashDetailBean crashDetailBean) {
        final com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
        if (b == null) {
            return;
        }
        x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        x.e("# PKG NAME: %s", b.c);
        x.e("# APP VER: %s", b.k);
        x.e("# SDK VER: %s", b.f);
        x.e("# LAUNCH TIME: %s", z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a)));
        x.e("# CRASH TYPE: %s", string);
        x.e("# CRASH TIME: %s", h);
        x.e("# CRASH PROCESS: %s", s);
        x.e("# CRASH THREAD: %s", s2);
        if (crashDetailBean != null) {
            x.e("# REPORT ID: %s", crashDetailBean.c);
            h = b.h;
            if (b.r()) {
                string = "ROOTED";
            }
            else {
                string = "UNROOT";
            }
            x.e("# CRASH DEVICE: %s %s", h, string);
            x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", crashDetailBean.C, crashDetailBean.D, crashDetailBean.E);
            x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", crashDetailBean.F, crashDetailBean.G, crashDetailBean.H);
            if (!z.a(crashDetailBean.K)) {
                x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
            }
            else if (crashDetailBean.b == 3) {
                if (crashDetailBean.P == null) {
                    string = "null";
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append((String)crashDetailBean.P.get((Object)"BUGLY_CR_01"));
                    string = sb.toString();
                }
                x.e("# EXCEPTION ANR MESSAGE:\n %s", string);
            }
        }
        if (!z.a(s3)) {
            x.e("# CRASH STACK: ", new Object[0]);
            x.e(s3, new Object[0]);
        }
        x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }
    
    public static void a(final boolean d, final List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            x.c("up finish update state %b", d);
            for (final CrashDetailBean crashDetailBean : list) {
                x.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, crashDetailBean.l, crashDetailBean.d, crashDetailBean.j);
                ++crashDetailBean.l;
                crashDetailBean.d = d;
                x.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, crashDetailBean.l, crashDetailBean.d, crashDetailBean.j);
            }
            final Iterator iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                c.a().a((CrashDetailBean)iterator2.next());
            }
            x.c("update state size %d", list.size());
        }
        if (!d) {
            x.b("[crash] upload fail.", new Object[0]);
        }
    }
    
    private static com.tencent.bugly.crashreport.crash.a b(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            final com.tencent.bugly.crashreport.crash.a a = new com.tencent.bugly.crashreport.crash.a();
            a.a = cursor.getLong(cursor.getColumnIndex("_id"));
            a.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            a.c = cursor.getString(cursor.getColumnIndex("_s1"));
            final int int1 = cursor.getInt(cursor.getColumnIndex("_up"));
            final boolean b = false;
            a.d = (int1 == 1);
            boolean e = b;
            if (cursor.getInt(cursor.getColumnIndex("_me")) == 1) {
                e = true;
            }
            a.e = e;
            a.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return a;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private List<com.tencent.bugly.crashreport.crash.a> b() {
        final ArrayList list = new ArrayList();
        Cursor cursor = null;
        try {
            final Cursor a = p.a().a("t_cr", new String[] { "_id", "_tm", "_s1", "_up", "_me", "_uc" }, null, null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                if (a.getCount() <= 0) {
                    if (a != null) {
                        a.close();
                    }
                    return (List<com.tencent.bugly.crashreport.crash.a>)list;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                int n = 0;
                while (a.moveToNext()) {
                    final com.tencent.bugly.crashreport.crash.a b = b(a);
                    if (b != null) {
                        ((List)list).add((Object)b);
                    }
                    else {
                        try {
                            sb.append(a.getLong(a.getColumnIndex("_id")));
                            sb.append(",");
                            ++n;
                        }
                        finally {
                            x.d("unknown id!", new Object[0]);
                        }
                    }
                }
                StringBuilder sb2 = sb;
                if (sb.toString().contains((CharSequence)",")) {
                    sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                }
                sb2.append(")");
                final String string = sb2.toString();
                sb2.setLength(0);
                if (n > 0) {
                    x.d("deleted %s illegal data %d", "t_cr", p.a().a("t_cr", string, null, null, true));
                }
                if (a != null) {
                    a.close();
                }
                return (List<com.tencent.bugly.crashreport.crash.a>)list;
            }
            finally {
                cursor = a;
            }
        }
        finally {}
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return (List<com.tencent.bugly.crashreport.crash.a>)list;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    
    private List<CrashDetailBean> b(List<com.tencent.bugly.crashreport.crash.a> sb) {
        if (sb != null) {
            if (((List)sb).size() != 0) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("_id in ");
                sb2.append("(");
                final Iterator iterator = ((List)sb).iterator();
                while (iterator.hasNext()) {
                    sb2.append(((com.tencent.bugly.crashreport.crash.a)iterator.next()).a);
                    sb2.append(",");
                }
                sb = sb2;
                if (sb2.toString().contains((CharSequence)",")) {
                    sb = new StringBuilder(sb2.substring(0, sb2.lastIndexOf(",")));
                }
                sb.append(")");
                final String string = sb.toString();
                sb.setLength(0);
                try {
                    final Cursor a = p.a().a("t_cr", null, string, null, null, true);
                    if (a == null) {
                        if (a != null) {
                            a.close();
                        }
                        return null;
                    }
                    try {
                        final ArrayList list = new ArrayList();
                        sb.append("_id in ");
                        sb.append("(");
                        int n = 0;
                        while (a.moveToNext()) {
                            final CrashDetailBean a2 = a(a);
                            if (a2 != null) {
                                ((List)list).add((Object)a2);
                            }
                            else {
                                try {
                                    sb.append(a.getLong(a.getColumnIndex("_id")));
                                    sb.append(",");
                                    ++n;
                                }
                                finally {
                                    x.d("unknown id!", new Object[0]);
                                }
                            }
                        }
                        StringBuilder sb3 = sb;
                        if (sb.toString().contains((CharSequence)",")) {
                            sb3 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                        }
                        sb3.append(")");
                        final String string2 = sb3.toString();
                        if (n > 0) {
                            x.d("deleted %s illegal data %d", "t_cr", p.a().a("t_cr", string2, null, null, true));
                        }
                        if (a != null) {
                            a.close();
                        }
                        return (List<CrashDetailBean>)list;
                    }
                    finally {}
                }
                finally {
                    sb = null;
                }
                try {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return null;
                }
                finally {
                    if (sb != null) {
                        ((Cursor)sb).close();
                    }
                }
            }
        }
        return null;
    }
    
    private static void c(final List<com.tencent.bugly.crashreport.crash.a> list) {
        if (list != null) {
            if (list.size() != 0) {
                final StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    sb.append(((com.tencent.bugly.crashreport.crash.a)iterator.next()).a);
                    sb.append(",");
                }
                final StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                sb2.append(")");
                final String string = sb2.toString();
                sb2.setLength(0);
                try {
                    x.c("deleted %s data %d", "t_cr", p.a().a("t_cr", string, null, null, true));
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }
    
    private static void d(final List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    final StringBuilder sb = new StringBuilder();
                    for (final CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(crashDetailBean.a);
                    }
                    String s2;
                    final String s = s2 = sb.toString();
                    if (s.length() > 0) {
                        s2 = s.substring(4);
                    }
                    sb.setLength(0);
                    x.c("deleted %s data %d", "t_cr", p.a().a("t_cr", s2, null, null, true));
                }
            }
            finally {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    private static ContentValues f(final CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            final ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0L) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            final boolean d = crashDetailBean.d;
            final int n = 1;
            int n2;
            if (d) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            contentValues.put("_up", Integer.valueOf(n2));
            int n3;
            if (crashDetailBean.j) {
                n3 = n;
            }
            else {
                n3 = 0;
            }
            contentValues.put("_me", Integer.valueOf(n3));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", z.a((Parcelable)crashDetailBean));
            return contentValues;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public final List<CrashDetailBean> a() {
        final StrategyBean c = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (c == null) {
            x.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!c.e) {
            x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long b = z.b();
        final List<com.tencent.bugly.crashreport.crash.a> b2 = this.b();
        x.c("Size of crash list loaded from DB: %s", b2.size());
        if (b2 != null && b2.size() > 0) {
            final ArrayList list = new ArrayList();
            ((List)list).addAll((Collection)a(b2));
            b2.removeAll((Collection)list);
            final Iterator iterator = b2.iterator();
            while (iterator.hasNext()) {
                final com.tencent.bugly.crashreport.crash.a a = (com.tencent.bugly.crashreport.crash.a)iterator.next();
                if (a.b < b - com.tencent.bugly.crashreport.crash.c.g) {
                    iterator.remove();
                    ((List)list).add((Object)a);
                }
                else if (a.d) {
                    if (a.b >= currentTimeMillis - 86400000L) {
                        iterator.remove();
                    }
                    else {
                        if (a.e) {
                            continue;
                        }
                        iterator.remove();
                        ((List)list).add((Object)a);
                    }
                }
                else {
                    if (a.f < 3L || a.b >= currentTimeMillis - 86400000L) {
                        continue;
                    }
                    iterator.remove();
                    ((List)list).add((Object)a);
                }
            }
            if (((List)list).size() > 0) {
                c((List<com.tencent.bugly.crashreport.crash.a>)list);
            }
            final ArrayList list2 = new ArrayList();
            final List<CrashDetailBean> b3 = this.b(b2);
            if (b3 != null && b3.size() > 0) {
                final String k = com.tencent.bugly.crashreport.common.info.a.b().k;
                final Iterator iterator2 = b3.iterator();
                while (iterator2.hasNext()) {
                    final CrashDetailBean crashDetailBean = (CrashDetailBean)iterator2.next();
                    if (!k.equals((Object)crashDetailBean.f)) {
                        iterator2.remove();
                        ((List)list2).add((Object)crashDetailBean);
                    }
                }
            }
            if (((List)list2).size() > 0) {
                d((List<CrashDetailBean>)list2);
            }
            return b3;
        }
        return null;
    }
    
    public final void a(final CrashDetailBean crashDetailBean, final long n, final boolean b) {
        if (com.tencent.bugly.crashreport.crash.c.l) {
            x.a("try to upload right now", new Object[0]);
            final ArrayList list = new ArrayList();
            ((List)list).add((Object)crashDetailBean);
            this.a((List<CrashDetailBean>)list, 3000L, b, crashDetailBean.b == 7, b);
        }
    }
    
    public final void a(final List<CrashDetailBean> list, final long n, final boolean b, final boolean b2, final boolean b3) {
        if (!com.tencent.bugly.crashreport.common.info.a.a(this.b).e) {
            return;
        }
        final u c = this.c;
        if (c == null) {
            return;
        }
        if (!b3 && !c.b(com.tencent.bugly.crashreport.crash.c.a)) {
            return;
        }
        final StrategyBean c2 = this.e.c();
        if (!c2.e) {
            x.d("remote report is disable!", new Object[0]);
            x.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list != null) {
            if (list.size() != 0) {
                try {
                    final String q = c2.q;
                    final String b4 = StrategyBean.b;
                    final Context b5 = this.b;
                    final com.tencent.bugly.crashreport.common.info.a b6 = com.tencent.bugly.crashreport.common.info.a.b();
                    Object o;
                    if (b5 != null && list != null && list.size() != 0 && b6 != null) {
                        final al al = new al();
                        al.a = new ArrayList();
                        final Iterator iterator = list.iterator();
                        while (true) {
                            o = al;
                            if (!iterator.hasNext()) {
                                break;
                            }
                            al.a.add((Object)a(b5, (CrashDetailBean)iterator.next(), b6));
                        }
                    }
                    else {
                        x.d("enEXPPkg args == null!", new Object[0]);
                        o = null;
                    }
                    if (o == null) {
                        x.d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    final byte[] a = com.tencent.bugly.proguard.a.a((k)o);
                    if (a == null) {
                        x.d("send encode fail!", new Object[0]);
                        return;
                    }
                    final am a2 = com.tencent.bugly.proguard.a.a(this.b, 830, a);
                    if (a2 == null) {
                        x.d("request package is null.", new Object[0]);
                        return;
                    }
                    final b$1 b$1 = new b$1(this, (List)list);
                    if (!b) {
                        this.c.a(b.a, a2, q, b4, (t)b$1, false);
                        return;
                    }
                    this.c.a(b.a, a2, q, b4, (t)b$1, n, b2);
                }
                finally {
                    final Throwable t;
                    x.e("req cr error %s", t.toString());
                    if (!x.b(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }
    
    public final boolean a(final CrashDetailBean crashDetailBean) {
        return this.b(crashDetailBean);
    }
    
    public final boolean b(CrashDetailBean a) {
        if (a == null) {
            return true;
        }
        if (com.tencent.bugly.crashreport.crash.c.n != null && !com.tencent.bugly.crashreport.crash.c.n.isEmpty()) {
            x.c("Crash filter for crash stack is: %s", com.tencent.bugly.crashreport.crash.c.n);
            if (a.q.contains((CharSequence)com.tencent.bugly.crashreport.crash.c.n)) {
                x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (com.tencent.bugly.crashreport.crash.c.o != null && !com.tencent.bugly.crashreport.crash.c.o.isEmpty()) {
            x.c("Crash regular filter for crash stack is: %s", com.tencent.bugly.crashreport.crash.c.o);
            if (Pattern.compile(com.tencent.bugly.crashreport.crash.c.o).matcher((CharSequence)a.q).find()) {
                x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (a.b != 2) {
            final r r = new r();
            r.b = 1;
            r.c = a.A;
            r.d = a.B;
            r.e = a.r;
            this.d.b(1);
            this.d.a(r);
            x.b("[crash] a crash occur, handling...", new Object[0]);
        }
        else {
            x.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        final List<com.tencent.bugly.crashreport.crash.a> b = this.b();
        Object o2;
        final Object o = o2 = null;
        Label_0642: {
            if (b != null) {
                o2 = o;
                if (b.size() > 0) {
                    final ArrayList list = new ArrayList(10);
                    final ArrayList list2 = new ArrayList(10);
                    ((List)list).addAll((Collection)a(b));
                    b.removeAll((Collection)list);
                    if (b.size() > 20L) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("_id in ");
                        sb.append("(");
                        sb.append("SELECT _id");
                        sb.append(" FROM t_cr");
                        sb.append(" order by _id");
                        sb.append(" limit 5");
                        sb.append(")");
                        final String string = sb.toString();
                        sb.setLength(0);
                        try {
                            x.c("deleted first record %s data %d", "t_cr", p.a().a("t_cr", string, null, null, true));
                        }
                        finally {
                            final Throwable t;
                            if (!x.a(t)) {
                                t.printStackTrace();
                            }
                        }
                    }
                    o2 = list;
                    if (!com.tencent.bugly.b.c) {
                        o2 = list;
                        if (com.tencent.bugly.crashreport.crash.c.d) {
                            final Iterator iterator = b.iterator();
                            boolean b2 = false;
                            while (iterator.hasNext()) {
                                final com.tencent.bugly.crashreport.crash.a a2 = (com.tencent.bugly.crashreport.crash.a)iterator.next();
                                if (a.u.equals((Object)a2.c)) {
                                    if (a2.e) {
                                        b2 = true;
                                    }
                                    ((List)list2).add((Object)a2);
                                }
                            }
                            if (!b2) {
                                o2 = list;
                                if (((List)list2).size() < com.tencent.bugly.crashreport.crash.c.c) {
                                    break Label_0642;
                                }
                            }
                            x.a("same crash occur too much do merged!", new Object[0]);
                            a = this.a((List<com.tencent.bugly.crashreport.crash.a>)list2, a);
                            for (final com.tencent.bugly.crashreport.crash.a a3 : list2) {
                                if (a3.a != a.a) {
                                    ((List)list).add((Object)a3);
                                }
                            }
                            this.e(a);
                            c((List<com.tencent.bugly.crashreport.crash.a>)list);
                            x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                            return true;
                        }
                    }
                }
            }
        }
        this.e(a);
        if (o2 != null && !((List)o2).isEmpty()) {
            c((List<com.tencent.bugly.crashreport.crash.a>)o2);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
    }
    
    public final void c(final CrashDetailBean crashDetailBean) {
        final int b = crashDetailBean.b;
        if (b != 0) {
            if (b != 1) {
                if (b == 3) {
                    if (!com.tencent.bugly.crashreport.crash.c.a().q()) {
                        return;
                    }
                }
            }
            else if (!com.tencent.bugly.crashreport.crash.c.a().p()) {
                return;
            }
        }
        else if (!com.tencent.bugly.crashreport.crash.c.a().p()) {
            return;
        }
        if (this.f != null) {
            x.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            final int b2 = crashDetailBean.b;
        }
    }
    
    public final void d(final CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return;
        }
        if (this.g == null && this.f == null) {
            return;
        }
        try {
            int n = 0;
            switch (crashDetailBean.b) {
                default: {
                    return;
                }
                case 7: {
                    n = 7;
                    break;
                }
                case 6: {
                    n = 6;
                    if (!com.tencent.bugly.crashreport.crash.c.a().t()) {
                        return;
                    }
                    break;
                }
                case 5: {
                    n = 5;
                    if (!com.tencent.bugly.crashreport.crash.c.a().s()) {
                        return;
                    }
                    break;
                }
                case 4: {
                    n = 3;
                    if (!com.tencent.bugly.crashreport.crash.c.a().r()) {
                        return;
                    }
                    break;
                }
                case 3: {
                    n = 4;
                    if (!com.tencent.bugly.crashreport.crash.c.a().q()) {
                        return;
                    }
                    break;
                }
                case 2: {
                    n = 1;
                    break;
                }
                case 1: {
                    if (!com.tencent.bugly.crashreport.crash.c.a().p()) {
                        return;
                    }
                    n = 2;
                    break;
                }
                case 0: {
                    if (!com.tencent.bugly.crashreport.crash.c.a().p()) {
                        return;
                    }
                    n = 0;
                    break;
                }
            }
            final int b = crashDetailBean.b;
            final String n2 = crashDetailBean.n;
            final String p = crashDetailBean.p;
            final String q = crashDetailBean.q;
            final long r = crashDetailBean.r;
            final o f = this.f;
            final byte[] array = null;
            Object onCrashHandleStart = null;
            Label_0314: {
                if (f != null) {
                    x.c("Calling 'onCrashHandleStart' of RQD crash listener.", new Object[0]);
                    x.c("Calling 'getCrashExtraMessage' of RQD crash listener.", new Object[0]);
                    final String b2 = this.f.b();
                    if (b2 != null) {
                        onCrashHandleStart = new HashMap(1);
                        ((Map)onCrashHandleStart).put((Object)"userData", (Object)b2);
                        break Label_0314;
                    }
                }
                else if (this.g != null) {
                    x.c("Calling 'onCrashHandleStart' of Bugly crash listener.", new Object[0]);
                    onCrashHandleStart = this.g.onCrashHandleStart(n, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    break Label_0314;
                }
                onCrashHandleStart = null;
            }
            if (onCrashHandleStart != null && ((Map)onCrashHandleStart).size() > 0) {
                crashDetailBean.O = (Map<String, String>)new LinkedHashMap(((Map)onCrashHandleStart).size());
                for (final Map$Entry map$Entry : ((Map)onCrashHandleStart).entrySet()) {
                    if (!z.a((String)map$Entry.getKey())) {
                        String substring;
                        final String s = substring = (String)map$Entry.getKey();
                        if (s.length() > 100) {
                            substring = s.substring(0, 100);
                            x.d("setted key length is over limit %d substring to %s", 100, substring);
                        }
                        String s2;
                        if (!z.a((String)map$Entry.getValue()) && ((String)map$Entry.getValue()).length() > 30000) {
                            s2 = ((String)map$Entry.getValue()).substring(((String)map$Entry.getValue()).length() - 30000);
                            x.d("setted %s value length is over limit %d substring", substring, 30000);
                        }
                        else {
                            final StringBuilder sb = new StringBuilder();
                            sb.append((String)map$Entry.getValue());
                            s2 = sb.toString();
                        }
                        crashDetailBean.O.put((Object)substring, (Object)s2);
                        x.a("add setted key %s value size:%d", substring, s2.length());
                    }
                }
            }
            x.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
            byte[] u;
            if (this.f != null) {
                x.c("Calling 'getCrashExtraData' of RQD crash listener.", new Object[0]);
                u = this.f.a();
            }
            else {
                u = array;
                if (this.g != null) {
                    x.c("Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener.", new Object[0]);
                    u = this.g.onCrashHandleStart2GetExtraDatas(n, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                }
            }
            if ((crashDetailBean.U = u) != null) {
                if (u.length > 30000) {
                    x.d("extra bytes size %d is over limit %d will drop over part", u.length, 30000);
                    crashDetailBean.U = Arrays.copyOf(u, 30000);
                }
                x.a("add extra bytes %d ", u.length);
            }
            if (this.f != null) {
                x.c("Calling 'onCrashSaving' of RQD crash listener.", new Object[0]);
                final o f2 = this.f;
                final String o = crashDetailBean.o;
                final String m = crashDetailBean.m;
                final String e = crashDetailBean.e;
                final String c = crashDetailBean.c;
                final String a = crashDetailBean.A;
                final String b3 = crashDetailBean.B;
                if (!f2.c()) {
                    x.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                }
            }
        }
        finally {
            final Throwable t;
            x.d("crash handle callback something wrong! %s", t.getClass().getName());
            if (!x.a(t)) {
                t.printStackTrace();
            }
        }
    }
    
    public final void e(final CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return;
        }
        final ContentValues f = f(crashDetailBean);
        if (f != null) {
            final long a = p.a().a("t_cr", f, null, true);
            if (a >= 0L) {
                x.c("insert %s success!", "t_cr");
                crashDetailBean.a = a;
            }
        }
    }
}
