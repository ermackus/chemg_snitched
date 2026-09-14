package com.tencent.bugly.proguard;

import android.os.Process;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import android.content.Context;
import java.text.SimpleDateFormat;

public final class y
{
    public static boolean a = true;
    private static boolean b = true;
    private static SimpleDateFormat c;
    private static int d = 30720;
    private static StringBuilder e;
    private static StringBuilder f;
    private static boolean g;
    private static a h;
    private static String i;
    private static String j;
    private static Context k;
    private static String l;
    private static boolean m;
    private static boolean n;
    private static ExecutorService o;
    private static int p;
    private static final Object q;
    
    static {
        q = new Object();
        try {
            y.c = new SimpleDateFormat("MM-dd HH:mm:ss");
        }
        finally {
            final Throwable t;
            x.b(t.getCause());
        }
    }
    
    private static String a(final String s, final String s2, String s3, final long n) {
        y.e.setLength(0);
        String substring = s3;
        if (s3.length() > 30720) {
            substring = s3.substring(s3.length() - 30720, s3.length() - 1);
        }
        final Date date = new Date();
        final SimpleDateFormat c = y.c;
        if (c != null) {
            s3 = c.format(date);
        }
        else {
            s3 = date.toString();
        }
        final StringBuilder e = y.e;
        e.append(s3);
        e.append(" ");
        e.append(y.p);
        e.append(" ");
        e.append(n);
        e.append(" ");
        e.append(s);
        e.append(" ");
        e.append(s2);
        e.append(": ");
        e.append(substring);
        e.append("\u0001\r\n");
        return y.e.toString();
    }
    
    public static void a(final int d) {
        final Object q = y.q;
        synchronized (q) {
            y.d = d;
            if (d < 0) {
                y.d = 0;
            }
            else if (d > 30720) {
                y.d = 30720;
            }
        }
    }
    
    public static void a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore_3       
        //     4: monitorenter   
        //     5: getstatic       com/tencent/bugly/proguard/y.m:Z
        //     8: ifne            163
        //    11: aload_0        
        //    12: ifnull          163
        //    15: getstatic       com/tencent/bugly/proguard/y.a:Z
        //    18: istore_1       
        //    19: iload_1        
        //    20: ifne            26
        //    23: goto            163
        //    26: invokestatic    java/util/concurrent/Executors.newSingleThreadExecutor:()Ljava/util/concurrent/ExecutorService;
        //    29: putstatic       com/tencent/bugly/proguard/y.o:Ljava/util/concurrent/ExecutorService;
        //    32: new             Ljava/lang/StringBuilder;
        //    35: astore_2       
        //    36: aload_2        
        //    37: iconst_0       
        //    38: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    41: aload_2        
        //    42: putstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //    45: new             Ljava/lang/StringBuilder;
        //    48: astore_2       
        //    49: aload_2        
        //    50: iconst_0       
        //    51: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    54: aload_2        
        //    55: putstatic       com/tencent/bugly/proguard/y.e:Ljava/lang/StringBuilder;
        //    58: aload_0        
        //    59: putstatic       com/tencent/bugly/proguard/y.k:Landroid/content/Context;
        //    62: aload_0        
        //    63: invokestatic    com/tencent/bugly/crashreport/common/info/a.a:(Landroid/content/Context;)Lcom/tencent/bugly/crashreport/common/info/a;
        //    66: astore_0       
        //    67: aload_0        
        //    68: getfield        com/tencent/bugly/crashreport/common/info/a.d:Ljava/lang/String;
        //    71: putstatic       com/tencent/bugly/proguard/y.i:Ljava/lang/String;
        //    74: aload_0        
        //    75: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    78: pop            
        //    79: ldc             ""
        //    81: putstatic       com/tencent/bugly/proguard/y.j:Ljava/lang/String;
        //    84: new             Ljava/lang/StringBuilder;
        //    87: astore_0       
        //    88: aload_0        
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: aload_0        
        //    93: getstatic       com/tencent/bugly/proguard/y.k:Landroid/content/Context;
        //    96: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    99: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: pop            
        //   106: aload_0        
        //   107: ldc             "/buglylog_"
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: pop            
        //   113: aload_0        
        //   114: getstatic       com/tencent/bugly/proguard/y.i:Ljava/lang/String;
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: pop            
        //   121: aload_0        
        //   122: ldc             "_"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: pop            
        //   128: aload_0        
        //   129: getstatic       com/tencent/bugly/proguard/y.j:Ljava/lang/String;
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: pop            
        //   136: aload_0        
        //   137: ldc             ".txt"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop            
        //   143: aload_0        
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: putstatic       com/tencent/bugly/proguard/y.l:Ljava/lang/String;
        //   150: invokestatic    android/os/Process.myPid:()I
        //   153: putstatic       com/tencent/bugly/proguard/y.p:I
        //   156: iconst_1       
        //   157: putstatic       com/tencent/bugly/proguard/y.m:Z
        //   160: aload_3        
        //   161: monitorexit    
        //   162: return         
        //   163: aload_3        
        //   164: monitorexit    
        //   165: return         
        //   166: astore_0       
        //   167: aload_3        
        //   168: monitorexit    
        //   169: aload_0        
        //   170: athrow         
        //   171: astore_0       
        //   172: goto            156
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      11     166    171    Any
        //  15     19     166    171    Any
        //  26     156    171    175    Any
        //  156    160    166    171    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    public static void a(final String s, final String s2, final String s3) {
        synchronized (y.class) {
            if (y.m) {
                if (y.a) {
                    try {
                        y.o.execute((Runnable)new Runnable(s, s2, s3) {
                            private String a;
                            private String b;
                            private String c;
                            
                            public final void run() {
                                c(this.a, this.b, this.c);
                            }
                        });
                    }
                    catch (final Exception ex) {
                        x.b((Throwable)ex);
                    }
                }
            }
        }
    }
    
    public static void a(final String s, final String s2, final Throwable t) {
        if (t == null) {
            return;
        }
        String message;
        if ((message = t.getMessage()) == null) {
            message = "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append('\n');
        sb.append(z.b(t));
        a(s, s2, sb.toString());
    }
    
    public static byte[] a() {
        if (!y.b) {
            return b();
        }
        if (!y.a) {
            return null;
        }
        return z.a(null, y.f.toString(), "BuglyLog.txt");
    }
    
    private static byte[] b() {
        if (!y.a) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final Object q = y.q;
        synchronized (q) {
            if (y.h != null && y.h.a && y.h.b != null && y.h.b.length() > 0L) {
                sb.append(z.a(y.h.b, 30720, true));
            }
            if (y.f != null && y.f.length() > 0) {
                sb.append(y.f.toString());
            }
            monitorexit(q);
            return z.a(null, sb.toString(), "BuglyLog.txt");
        }
    }
    
    private static void c(final String s, final String s2, final String s3) {
        synchronized (y.class) {
            if (y.b) {
                d(s, s2, s3);
                return;
            }
            e(s, s2, s3);
        }
    }
    
    private static void d(final String s, String a, final String s2) {
        synchronized (y.class) {
            a = a(s, a, s2, Process.myTid());
            final Object q;
            monitorenter(q = y.q);
            try {
                y.f.append(a);
                if (y.f.length() >= y.d) {
                    y.f = y.f.delete(0, y.f.indexOf("\u0001\r\n") + 1);
                }
            }
            finally {
                final Throwable t;
                if (!x.b(t)) {
                    t.printStackTrace();
                }
            }
            try {
                monitorexit(q);
            }
            finally {
                monitorexit(q);
            }
        }
    }
    
    private static void e(final String p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          6
        //     5: monitorenter   
        //     6: aload_0        
        //     7: aload_1        
        //     8: aload_2        
        //     9: invokestatic    android/os/Process.myTid:()I
        //    12: i2l            
        //    13: invokestatic    com/tencent/bugly/proguard/y.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Ljava/lang/String;
        //    16: astore_1       
        //    17: getstatic       com/tencent/bugly/proguard/y.q:Ljava/lang/Object;
        //    20: astore_0       
        //    21: aload_0        
        //    22: dup            
        //    23: astore          7
        //    25: monitorenter   
        //    26: getstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //    29: aload_1        
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: pop            
        //    34: getstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //    37: invokevirtual   java/lang/StringBuilder.length:()I
        //    40: istore_3       
        //    41: getstatic       com/tencent/bugly/proguard/y.d:I
        //    44: istore          4
        //    46: iload_3        
        //    47: iload           4
        //    49: if_icmpgt       59
        //    52: aload           7
        //    54: monitorexit    
        //    55: aload           6
        //    57: monitorexit    
        //    58: return         
        //    59: getstatic       com/tencent/bugly/proguard/y.g:Z
        //    62: istore          5
        //    64: iload           5
        //    66: ifeq            76
        //    69: aload           7
        //    71: monitorexit    
        //    72: aload           6
        //    74: monitorexit    
        //    75: return         
        //    76: iconst_1       
        //    77: putstatic       com/tencent/bugly/proguard/y.g:Z
        //    80: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //    83: ifnonnull       104
        //    86: new             Lcom/tencent/bugly/proguard/y$a;
        //    89: astore_1       
        //    90: aload_1        
        //    91: getstatic       com/tencent/bugly/proguard/y.l:Ljava/lang/String;
        //    94: invokespecial   com/tencent/bugly/proguard/y$a.<init>:(Ljava/lang/String;)V
        //    97: aload_1        
        //    98: putstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   101: goto            147
        //   104: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   107: invokestatic    com/tencent/bugly/proguard/y$a.a:(Lcom/tencent/bugly/proguard/y$a;)Ljava/io/File;
        //   110: ifnull          140
        //   113: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   116: invokestatic    com/tencent/bugly/proguard/y$a.a:(Lcom/tencent/bugly/proguard/y$a;)Ljava/io/File;
        //   119: invokevirtual   java/io/File.length:()J
        //   122: getstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //   125: invokevirtual   java/lang/StringBuilder.length:()I
        //   128: i2l            
        //   129: ladd           
        //   130: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   133: invokestatic    com/tencent/bugly/proguard/y$a.b:(Lcom/tencent/bugly/proguard/y$a;)J
        //   136: lcmp           
        //   137: ifle            147
        //   140: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   143: invokestatic    com/tencent/bugly/proguard/y$a.c:(Lcom/tencent/bugly/proguard/y$a;)Z
        //   146: pop            
        //   147: getstatic       com/tencent/bugly/proguard/y.h:Lcom/tencent/bugly/proguard/y$a;
        //   150: getstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokevirtual   com/tencent/bugly/proguard/y$a.a:(Ljava/lang/String;)Z
        //   159: ifeq            173
        //   162: getstatic       com/tencent/bugly/proguard/y.f:Ljava/lang/StringBuilder;
        //   165: iconst_0       
        //   166: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   169: iconst_0       
        //   170: putstatic       com/tencent/bugly/proguard/y.g:Z
        //   173: aload           7
        //   175: monitorexit    
        //   176: aload           6
        //   178: monitorexit    
        //   179: return         
        //   180: astore_1       
        //   181: aload           7
        //   183: monitorexit    
        //   184: aload_1        
        //   185: athrow         
        //   186: astore_0       
        //   187: aload           6
        //   189: monitorexit    
        //   190: aload_0        
        //   191: athrow         
        //   192: astore_1       
        //   193: goto            173
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      26     186    192    Any
        //  26     46     192    196    Any
        //  52     55     180    186    Any
        //  59     64     192    196    Any
        //  69     72     180    186    Any
        //  76     101    192    196    Any
        //  104    140    192    196    Any
        //  140    147    192    196    Any
        //  147    173    192    196    Any
        //  173    176    180    186    Any
        //  181    186    186    192    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    public static final class a
    {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e;
        
        public a(final String c) {
            this.e = 30720L;
            if (c != null) {
                if (!c.equals((Object)"")) {
                    this.c = c;
                    this.a = this.a();
                }
            }
        }
        
        private boolean a() {
            try {
                final File b = new File(this.c);
                this.b = b;
                if (b.exists() && !this.b.delete()) {
                    return this.a = false;
                }
                return this.b.createNewFile() || (this.a = false);
            }
            finally {
                final Throwable t;
                x.a(t);
                return this.a = false;
            }
        }
        
        public final boolean a(final String p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        com/tencent/bugly/proguard/y$a.a:Z
            //     4: ifne            9
            //     7: iconst_0       
            //     8: ireturn        
            //     9: aconst_null    
            //    10: astore          4
            //    12: new             Ljava/io/FileOutputStream;
            //    15: astore_3       
            //    16: aload_3        
            //    17: aload_0        
            //    18: getfield        com/tencent/bugly/proguard/y$a.b:Ljava/io/File;
            //    21: iconst_1       
            //    22: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
            //    25: aload_1        
            //    26: ldc             "UTF-8"
            //    28: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
            //    31: astore_1       
            //    32: aload_3        
            //    33: aload_1        
            //    34: invokevirtual   java/io/FileOutputStream.write:([B)V
            //    37: aload_3        
            //    38: invokevirtual   java/io/FileOutputStream.flush:()V
            //    41: aload_3        
            //    42: invokevirtual   java/io/FileOutputStream.close:()V
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/tencent/bugly/proguard/y$a.d:J
            //    50: aload_1        
            //    51: arraylength    
            //    52: i2l            
            //    53: ladd           
            //    54: putfield        com/tencent/bugly/proguard/y$a.d:J
            //    57: aload_0        
            //    58: iconst_1       
            //    59: putfield        com/tencent/bugly/proguard/y$a.a:Z
            //    62: aload_3        
            //    63: invokevirtual   java/io/FileOutputStream.close:()V
            //    66: iconst_1       
            //    67: ireturn        
            //    68: astore_2       
            //    69: aload_3        
            //    70: astore_1       
            //    71: goto            78
            //    74: astore_2       
            //    75: aload           4
            //    77: astore_1       
            //    78: aload_2        
            //    79: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
            //    82: pop            
            //    83: aload_0        
            //    84: iconst_0       
            //    85: putfield        com/tencent/bugly/proguard/y$a.a:Z
            //    88: aload_1        
            //    89: ifnull          96
            //    92: aload_1        
            //    93: invokevirtual   java/io/FileOutputStream.close:()V
            //    96: iconst_0       
            //    97: ireturn        
            //    98: astore_2       
            //    99: aload_1        
            //   100: ifnull          107
            //   103: aload_1        
            //   104: invokevirtual   java/io/FileOutputStream.close:()V
            //   107: aload_2        
            //   108: athrow         
            //   109: astore_1       
            //   110: goto            66
            //   113: astore_1       
            //   114: goto            96
            //   117: astore_1       
            //   118: goto            107
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  12     25     74     78     Any
            //  25     62     68     74     Any
            //  62     66     109    113    Ljava/io/IOException;
            //  78     88     98     109    Any
            //  92     96     113    117    Ljava/io/IOException;
            //  103    107    117    121    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 72, Size: 72
            //     at java.util.ArrayList.get(ArrayList.java:437)
            //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
            //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
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
