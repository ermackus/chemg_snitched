package com.alipay.sdk.m.k;

import com.alipay.sdk.m.q.d;
import org.json.JSONArray;
import java.util.LinkedHashMap;
import java.util.UUID;
import com.alipay.sdk.m.u.j;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.ArrayList;
import android.text.TextUtils;
import com.alipay.sdk.m.u.e;
import android.content.Context;

public class a
{
    public static void a(final Context context) {
        synchronized (a.class) {
            b.a(context);
        }
    }
    
    public static void a(final Context context, final a a, final String s, final String s2) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        if (context != null) {
            if (a != null) {
                final Throwable t2;
                try {
                    a.a(context, a.l.a(s), s2);
                    return;
                }
                finally {
                    final Throwable t = t2;
                    com.alipay.sdk.m.u.e.a(t);
                }
                try {
                    final Throwable t = t2;
                    com.alipay.sdk.m.u.e.a(t);
                    return;
                }
                finally {
                    monitorexit(clazz);
                }
            }
        }
        monitorexit(clazz);
    }
    
    public static void a(final a a, final String s, final String s2) {
        if (a == null) {
            return;
        }
        a.l.a(s, s2);
    }
    
    public static void a(final a a, final String s, final String s2, final String s3) {
        if (a == null) {
            return;
        }
        a.l.a(s, s2, s3);
    }
    
    public static void a(final a a, final String s, final String s2, final Throwable t) {
        if (a == null) {
            return;
        }
        a.l.a(s, s2, t);
    }
    
    public static void a(final a a, final String s, final String s2, final Throwable t, final String s3) {
        if (a == null) {
            return;
        }
        a.l.a(s, s2, t, s3);
    }
    
    public static void a(final a a, final String s, final Throwable t) {
        if (a != null && t != null) {
            if (t.getClass() != null) {
                a.l.a(s, t.getClass().getSimpleName(), t);
            }
        }
    }
    
    public static void b(final Context context, final a a, final String s, final String s2) {
        final Class<a> clazz;
        monitorenter(clazz = a.class);
        if (context != null) {
            if (a != null) {
                try {
                    b.a(context, a.l, s, s2);
                    return;
                }
                finally {
                    monitorexit(clazz);
                }
            }
        }
        monitorexit(clazz);
    }
    
    public static void b(final a a, final String s, final String s2, final String s3) {
        if (a == null) {
            return;
        }
        a.l.b(s, s2, s3);
    }
    
    public static final class a
    {
        public static final String a = "RecordPref";
        public static final String b = "alipay_cashier_statistic_record";
        
        public static int a(final Context context, final String s) {
            synchronized (a.class) {
                final StringBuilder sb = new StringBuilder();
                sb.append("stat remove ");
                sb.append(s);
                com.alipay.sdk.m.u.e.b("RecordPref", sb.toString());
                if (context != null) {
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        final a a = a(context);
                        if (a.a.isEmpty()) {
                            return 0;
                        }
                        try {
                            final ArrayList list = new ArrayList();
                            for (final Map$Entry map$Entry : a.a.entrySet()) {
                                if (s.equals(map$Entry.getValue())) {
                                    list.add(map$Entry.getKey());
                                }
                            }
                            final Iterator iterator2 = list.iterator();
                            while (iterator2.hasNext()) {
                                a.a.remove((Object)iterator2.next());
                            }
                            a(context, a);
                            return list.size();
                        }
                        finally {
                            final Throwable t;
                            com.alipay.sdk.m.u.e.a(t);
                            final int size = a.a.size();
                            a(context, new a());
                            return size;
                        }
                    }
                }
                return 0;
            }
        }
        
        public static a a(final Context context) {
            final Class<a> clazz;
            monitorenter(clazz = a.class);
            try {
                final String a = j.a((a)null, context, "alipay_cashier_statistic_record", (String)null);
                if (TextUtils.isEmpty((CharSequence)a)) {
                    final a a2 = new a();
                    monitorexit(clazz);
                    return a2;
                }
                final a a3 = new a(a);
                monitorexit(clazz);
                return a3;
            }
            finally {
                try {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                    return new a();
                }
                finally {
                    monitorexit(clazz);
                }
            }
        }
        
        public static String a(final Context context, final String s, final String s2) {
            synchronized (a.class) {
                final StringBuilder sb = new StringBuilder();
                sb.append("stat append ");
                sb.append(s2);
                sb.append(" , ");
                sb.append(s);
                com.alipay.sdk.m.u.e.b("RecordPref", sb.toString());
                if (context != null && !TextUtils.isEmpty((CharSequence)s)) {
                    String string = s2;
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        string = UUID.randomUUID().toString();
                    }
                    final a a = a(context);
                    if (a.a.size() > 20) {
                        a.a.clear();
                    }
                    a.a.put((Object)string, (Object)s);
                    a(context, a);
                    return string;
                }
                return null;
            }
        }
        
        public static void a(final Context context, final a a) {
            final Class<a> clazz;
            monitorenter(clazz = a.class);
            a a2 = a;
            final Throwable t2;
            Label_0019: {
                if (a != null) {
                    break Label_0019;
                }
                try {
                    a2 = new a();
                    j.b((a)null, context, "alipay_cashier_statistic_record", a2.a());
                    return;
                }
                finally {
                    final Throwable t = t2;
                    com.alipay.sdk.m.u.e.a(t);
                }
            }
            try {
                final Throwable t = t2;
                com.alipay.sdk.m.u.e.a(t);
            }
            finally {
                monitorexit(clazz);
            }
        }
        
        public static String b(final Context context) {
            synchronized (a.class) {
                com.alipay.sdk.m.u.e.b("RecordPref", "stat peek");
                if (context == null) {
                    return null;
                }
                final a a = a(context);
                if (a.a.isEmpty()) {
                    return null;
                }
                try {
                    return (String)((Map$Entry)a.a.entrySet().iterator().next()).getValue();
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                    return null;
                }
            }
        }
        
        public static final class a
        {
            public final LinkedHashMap<String, String> a;
            
            public a() {
                this.a = (LinkedHashMap<String, String>)new LinkedHashMap();
            }
            
            public a(final String s) {
                this.a = (LinkedHashMap<String, String>)new LinkedHashMap();
                try {
                    final JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        final JSONArray jsonArray2 = jsonArray.getJSONArray(i);
                        this.a.put((Object)jsonArray2.getString(0), (Object)jsonArray2.getString(1));
                    }
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                }
            }
            
            public String a() {
                try {
                    final JSONArray jsonArray = new JSONArray();
                    for (final Map$Entry map$Entry : this.a.entrySet()) {
                        final JSONArray jsonArray2 = new JSONArray();
                        jsonArray2.put(map$Entry.getKey()).put(map$Entry.getValue());
                        jsonArray.put((Object)jsonArray2);
                    }
                    return jsonArray.toString();
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                    return new JSONArray().toString();
                }
            }
        }
    }
    
    public static final class b
    {
        public static void a(final Context context) {
            synchronized (b.class) {
                a(context, null, null);
            }
        }
        
        public static void a(final Context context, final com.alipay.sdk.m.k.b b, final String s, final String s2) {
            final Class<b> clazz;
            monitorenter(clazz = b.class);
            if (context != null && b != null) {
                if (s != null) {
                    try {
                        a(context, b.a(s), s2);
                        return;
                    }
                    finally {
                        monitorexit(clazz);
                    }
                }
            }
            monitorexit(clazz);
        }
        
        public static void a(final Context context, final String s, final String s2) {
            final Class<b> clazz;
            monitorenter(clazz = b.class);
            if (context == null) {
                monitorexit(clazz);
                return;
            }
            try {
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    a.a(context, s, s2);
                }
                new Thread((Runnable)new Runnable(s, context) {
                    public final String a;
                    public final Context b;
                    
                    public void run() {
                        if (!TextUtils.isEmpty((CharSequence)this.a) && !b(this.b, this.a)) {
                            return;
                        }
                        for (int i = 0; i < 4; ++i) {
                            final String b = com.alipay.sdk.m.k.a.a.b(this.b);
                            if (TextUtils.isEmpty((CharSequence)b)) {
                                break;
                            }
                            if (!b(this.b, b)) {
                                break;
                            }
                        }
                    }
                }).start();
            }
            finally {
                monitorexit(clazz);
            }
        }
        
        public static boolean b(final Context context, final String s) {
            synchronized (b.class) {
                final StringBuilder sb = new StringBuilder();
                sb.append("stat sub ");
                sb.append(s);
                com.alipay.sdk.m.u.e.b("mspl", sb.toString());
                Object o;
                if (com.alipay.sdk.m.m.a.z().e()) {
                    o = new com.alipay.sdk.m.q.d();
                }
                else {
                    o = new com.alipay.sdk.m.q.e();
                }
                try {
                    if (((com.alipay.sdk.m.p.e)o).a((a)null, context, s) != null) {
                        a.a(context, s);
                        return true;
                    }
                    return false;
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.u.e.a(t);
                    return false;
                }
            }
        }
    }
    
    public static final class c
    {
        public static final String a = "alipay_cashier_ap_seq_v";
        
        public static long a(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: dup            
            //     3: astore_2       
            //     4: monitorenter   
            //     5: aload_0        
            //     6: ldc             "alipay_cashier_ap_seq_v"
            //     8: invokestatic    com/alipay/sdk/m/k/a$d.a:(Landroid/content/Context;Ljava/lang/String;)J
            //    11: lstore_1       
            //    12: aload_2        
            //    13: monitorexit    
            //    14: lload_1        
            //    15: lreturn        
            //    16: astore_0       
            //    17: aload_2        
            //    18: monitorexit    
            //    19: aload_0        
            //    20: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  5      12     16     21     Any
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
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
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
    }
    
    public static final class d
    {
        public static long a(final Context p0, final String p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: dup            
            //     3: astore          5
            //     5: monitorenter   
            //     6: aconst_null    
            //     7: aload_0        
            //     8: aload_1        
            //     9: aconst_null    
            //    10: invokestatic    com/alipay/sdk/m/u/j.a:(Lcom/alipay/sdk/m/s/a;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
            //    13: astore          4
            //    15: aload           4
            //    17: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //    20: ifne            32
            //    23: aload           4
            //    25: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
            //    28: lstore_2       
            //    29: goto            34
            //    32: lconst_0       
            //    33: lstore_2       
            //    34: lload_2        
            //    35: lconst_1       
            //    36: ladd           
            //    37: lstore_2       
            //    38: aconst_null    
            //    39: aload_0        
            //    40: aload_1        
            //    41: lload_2        
            //    42: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
            //    45: invokestatic    com/alipay/sdk/m/u/j.b:(Lcom/alipay/sdk/m/s/a;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
            //    48: aload           5
            //    50: monitorexit    
            //    51: lload_2        
            //    52: lreturn        
            //    53: astore          4
            //    55: goto            32
            //    58: astore_0       
            //    59: goto            48
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  6      29     53     58     Any
            //  38     48     58     62     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
            //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
            //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
            //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
            //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
            //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
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
    }
    
    public static final class e
    {
        public static final String a = "alipay_cashier_statistic_v";
        
        public static long a(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: dup            
            //     3: astore_2       
            //     4: monitorenter   
            //     5: aload_0        
            //     6: ldc             "alipay_cashier_statistic_v"
            //     8: invokestatic    com/alipay/sdk/m/k/a$d.a:(Landroid/content/Context;Ljava/lang/String;)J
            //    11: lstore_1       
            //    12: aload_2        
            //    13: monitorexit    
            //    14: lload_1        
            //    15: lreturn        
            //    16: astore_0       
            //    17: aload_2        
            //    18: monitorexit    
            //    19: aload_0        
            //    20: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  5      12     16     21     Any
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
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
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
    }
}
