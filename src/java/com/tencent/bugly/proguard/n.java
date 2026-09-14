package com.tencent.bugly.proguard;

import android.content.SharedPreferences$Editor;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.HashMap;
import android.content.SharedPreferences;
import java.util.Map;
import android.content.Context;

public final class n
{
    public static final long a;
    private static n b;
    private Context c;
    private String d;
    private Map<Integer, Map<String, m>> e;
    private SharedPreferences f;
    
    static {
        a = System.currentTimeMillis();
    }
    
    private n(final Context c) {
        this.c = c;
        this.e = (Map<Integer, Map<String, m>>)new HashMap();
        this.d = com.tencent.bugly.crashreport.common.info.a.b().d;
        this.f = c.getSharedPreferences("crashrecord", 0);
    }
    
    public static n a() {
        synchronized (n.class) {
            return n.b;
        }
    }
    
    public static n a(final Context context) {
        synchronized (n.class) {
            if (n.b == null) {
                n.b = new n(context);
            }
            return n.b;
        }
    }
    
    private <T extends List<?>> void a(final int n, final T t) {
        monitorenter(this);
        if (t == null) {
            monitorexit(this);
            return;
        }
        Label_0181: {
            Label_0177: {
                try {
                    Object o = new(java.io.File.class)();
                    final File dir = this.c.getDir("crashrecord", 0);
                    final StringBuilder sb = new StringBuilder();
                    sb.append(n);
                    new File(dir, sb.toString());
                    Label_0152: {
                        ObjectOutputStream objectOutputStream = null;
                        try {
                            objectOutputStream = (ObjectOutputStream)(o = new ObjectOutputStream((OutputStream)new FileOutputStream((File)o)));
                            try {
                                final ObjectOutputStream objectOutputStream2 = objectOutputStream;
                                final List<?> list = t;
                                objectOutputStream2.writeObject((Object)list);
                                final ObjectOutputStream objectOutputStream3 = objectOutputStream;
                                objectOutputStream3.close();
                            }
                            catch (final IOException o) {
                                final Object o2 = objectOutputStream;
                                final File file = (File)o;
                            }
                        }
                        catch (final IOException file) {
                            final Object o2 = null;
                        }
                        finally {
                            o = null;
                            break Label_0152;
                        }
                        try {
                            final ObjectOutputStream objectOutputStream2 = objectOutputStream;
                            final List<?> list = t;
                            objectOutputStream2.writeObject((Object)list);
                            final ObjectOutputStream objectOutputStream3 = objectOutputStream;
                            objectOutputStream3.close();
                            break Label_0177;
                            final Object o2;
                            o = o2;
                            final File file;
                            ((IOException)file).printStackTrace();
                            o = o2;
                            x.a("open record file error", new Object[0]);
                            iftrue(Label_0147:)(o2 == null);
                            ((ObjectOutputStream)o2).close();
                            break Label_0177;
                            Label_0147: {
                                monitorexit(this);
                            }
                            return;
                        }
                        finally {}
                    }
                    if (o != null) {
                        ((ObjectOutputStream)o).close();
                    }
                }
                catch (final Exception ex) {
                    x.e("writeCrashRecord error", new Object[0]);
                }
                finally {
                    break Label_0181;
                }
            }
            monitorexit(this);
            return;
        }
        monitorexit(this);
    }
    
    private boolean b(final int n) {
        monitorenter(this);
        try {
            try {
                final List c = this.c(n);
                if (c == null) {
                    monitorexit(this);
                    return false;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                final ArrayList list = new ArrayList();
                final ArrayList list2 = new ArrayList();
                for (final m m : c) {
                    if (m.b != null && m.b.equalsIgnoreCase(this.d) && m.d > 0) {
                        ((List)list).add((Object)m);
                    }
                    if (m.c + 86400000L < currentTimeMillis) {
                        ((List)list2).add((Object)m);
                    }
                }
                Collections.sort((List)list);
                if (((List)list).size() < 2) {
                    c.removeAll((Collection)list2);
                    this.a(n, c);
                    monitorexit(this);
                    return false;
                }
                if (((List)list).size() > 0 && ((m)((List)list).get(((List)list).size() - 1)).c + 86400000L < currentTimeMillis) {
                    c.clear();
                    this.a(n, c);
                    monitorexit(this);
                    return false;
                }
                monitorexit(this);
                return true;
            }
            finally {}
        }
        catch (final Exception ex) {
            x.e("isFrequentCrash failed", new Object[0]);
            monitorexit(this);
            return false;
        }
        monitorexit(this);
    }
    
    private <T extends List<?>> T c(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          6
        //     4: monitorenter   
        //     5: new             Ljava/io/File;
        //     8: astore_3       
        //     9: aload_0        
        //    10: getfield        com/tencent/bugly/proguard/n.c:Landroid/content/Context;
        //    13: ldc             "crashrecord"
        //    15: iconst_0       
        //    16: invokevirtual   android/content/Context.getDir:(Ljava/lang/String;I)Ljava/io/File;
        //    19: astore          4
        //    21: new             Ljava/lang/StringBuilder;
        //    24: astore          5
        //    26: aload           5
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: aload           5
        //    33: iload_1        
        //    34: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    37: pop            
        //    38: aload_3        
        //    39: aload           4
        //    41: aload           5
        //    43: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    46: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    49: aload_3        
        //    50: invokevirtual   java/io/File.exists:()Z
        //    53: istore_2       
        //    54: iload_2        
        //    55: ifne            63
        //    58: aload           6
        //    60: monitorexit    
        //    61: aconst_null    
        //    62: areturn        
        //    63: new             Ljava/io/ObjectInputStream;
        //    66: astore          4
        //    68: new             Ljava/io/FileInputStream;
        //    71: astore          5
        //    73: aload           5
        //    75: aload_3        
        //    76: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    79: aload           4
        //    81: aload           5
        //    83: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    86: aload           4
        //    88: astore_3       
        //    89: aload           4
        //    91: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    94: checkcast       Ljava/util/List;
        //    97: astore          5
        //    99: aload           4
        //   101: invokevirtual   java/io/ObjectInputStream.close:()V
        //   104: aload           6
        //   106: monitorexit    
        //   107: aload           5
        //   109: areturn        
        //   110: astore          4
        //   112: aconst_null    
        //   113: astore_3       
        //   114: goto            174
        //   117: astore_3       
        //   118: aconst_null    
        //   119: astore          4
        //   121: aload           4
        //   123: astore_3       
        //   124: ldc             "get object error"
        //   126: iconst_0       
        //   127: anewarray       Ljava/lang/Object;
        //   130: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   133: pop            
        //   134: aload           4
        //   136: ifnull          200
        //   139: aload           4
        //   141: invokevirtual   java/io/ObjectInputStream.close:()V
        //   144: goto            200
        //   147: astore_3       
        //   148: aconst_null    
        //   149: astore          4
        //   151: aload           4
        //   153: astore_3       
        //   154: ldc             "open record file error"
        //   156: iconst_0       
        //   157: anewarray       Ljava/lang/Object;
        //   160: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   163: pop            
        //   164: aload           4
        //   166: ifnull          200
        //   169: goto            139
        //   172: astore          4
        //   174: aload_3        
        //   175: ifnull          182
        //   178: aload_3        
        //   179: invokevirtual   java/io/ObjectInputStream.close:()V
        //   182: aload           4
        //   184: athrow         
        //   185: astore_3       
        //   186: goto            205
        //   189: astore_3       
        //   190: ldc             "readCrashRecord error"
        //   192: iconst_0       
        //   193: anewarray       Ljava/lang/Object;
        //   196: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   199: pop            
        //   200: aload           6
        //   202: monitorexit    
        //   203: aconst_null    
        //   204: areturn        
        //   205: aload           6
        //   207: monitorexit    
        //   208: aload_3        
        //   209: athrow         
        //   210: astore_3       
        //   211: goto            151
        //   214: astore_3       
        //   215: goto            121
        //    Signature:
        //  <T:Ljava/util/List<*>;>(I)TT; [from metadata: <T::Ljava/util/List<*>;>(I)TT;]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  5      54     189    200    Ljava/lang/Exception;
        //  5      54     185    189    Any
        //  63     86     147    151    Ljava/io/IOException;
        //  63     86     117    121    Ljava/lang/ClassNotFoundException;
        //  63     86     110    117    Any
        //  89     99     210    214    Ljava/io/IOException;
        //  89     99     214    218    Ljava/lang/ClassNotFoundException;
        //  89     99     172    174    Any
        //  99     104    189    200    Ljava/lang/Exception;
        //  99     104    185    189    Any
        //  124    134    172    174    Any
        //  139    144    189    200    Ljava/lang/Exception;
        //  139    144    185    189    Any
        //  154    164    172    174    Any
        //  178    182    189    200    Ljava/lang/Exception;
        //  178    182    185    189    Any
        //  182    185    189    200    Ljava/lang/Exception;
        //  182    185    185    189    Any
        //  190    200    185    189    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
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
    
    public final void a(final int n, final int n2) {
        w.a().a((Runnable)new Runnable(this, 1004, n2) {
            private int a;
            private int b;
            private n c;
            
            public final void run() {
                try {
                    if (TextUtils.isEmpty((CharSequence)this.c.d)) {
                        return;
                    }
                    Object a;
                    if ((a = this.c.c(this.a)) == null) {
                        a = new ArrayList();
                    }
                    if (this.c.e.get((Object)this.a) == null) {
                        this.c.e.put((Object)this.a, (Object)new HashMap());
                    }
                    m m;
                    if (((Map)this.c.e.get((Object)this.a)).get((Object)this.c.d) == null) {
                        m = new m();
                        m.a = this.a;
                        m.g = n.a;
                        m.b = this.c.d;
                        m.f = com.tencent.bugly.crashreport.common.info.a.b().k;
                        m.e = com.tencent.bugly.crashreport.common.info.a.b().f;
                        m.c = System.currentTimeMillis();
                        m.d = this.b;
                        ((Map)this.c.e.get((Object)this.a)).put((Object)this.c.d, (Object)m);
                    }
                    else {
                        m = (m)((Map)this.c.e.get((Object)this.a)).get((Object)this.c.d);
                        m.d = this.b;
                    }
                    final ArrayList list = new ArrayList();
                    final Iterator iterator = ((List)a).iterator();
                    int n = 0;
                    while (iterator.hasNext()) {
                        final m i = (m)iterator.next();
                        int n2 = n;
                        if (i.g == m.g) {
                            n2 = n;
                            if (i.b != null) {
                                n2 = n;
                                if (i.b.equalsIgnoreCase(m.b)) {
                                    n2 = 1;
                                    i.d = m.d;
                                }
                            }
                        }
                        if ((i.e == null || i.e.equalsIgnoreCase(m.e)) && (i.f == null || i.f.equalsIgnoreCase(m.f))) {
                            n = n2;
                            if (i.d > 0) {
                                continue;
                            }
                        }
                        ((List)list).add((Object)i);
                        n = n2;
                    }
                    ((List)a).removeAll((Collection)list);
                    if (n == 0) {
                        ((List)a).add((Object)m);
                    }
                    this.c.a(this.a, a);
                }
                catch (final Exception ex) {
                    x.e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }
    
    public final boolean a(final int n) {
        monitorenter(this);
        boolean boolean1;
        final boolean b = boolean1 = true;
        try {
            try {
                final SharedPreferences f = this.f;
                boolean1 = b;
                boolean1 = b;
                final StringBuilder sb = new StringBuilder();
                boolean1 = b;
                sb.append(n);
                boolean1 = b;
                sb.append("_");
                boolean1 = b;
                sb.append(this.d);
                boolean1 = b;
                final boolean b2 = boolean1 = f.getBoolean(sb.toString(), (boolean)(1 != 0));
                final w a = w.a();
                boolean1 = b2;
                boolean1 = b2;
                final Runnable runnable = (Runnable)new Runnable(this, n) {
                    private int a;
                    private n b;
                    
                    public final void run() {
                        final boolean b = this.b.b(this.a);
                        final SharedPreferences$Editor edit = this.b.f.edit();
                        final StringBuilder sb = new StringBuilder();
                        sb.append(this.a);
                        sb.append("_");
                        sb.append(this.b.d);
                        edit.putBoolean(sb.toString(), b ^ true).commit();
                    }
                };
                boolean1 = b2;
                a.a((Runnable)runnable);
                monitorexit(this);
                return b2;
            }
            finally {}
        }
        catch (final Exception ex) {
            x.e("canInit error", new Object[0]);
            monitorexit(this);
            return boolean1;
        }
        monitorexit(this);
    }
}
