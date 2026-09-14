package com.tencent.bugly;

import java.util.Map;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import android.content.Context;

public class Bugly
{
    public static final String SDK_IS_DEV = "false";
    private static boolean a = false;
    public static Context applicationContext;
    private static String[] b;
    private static String[] c;
    public static boolean enable = true;
    public static Boolean isDev;
    
    static {
        Bugly.b = new String[] { "BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule" };
        Bugly.c = new String[] { "BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule" };
    }
    
    public static String getAppChannel() {
        synchronized (Bugly.class) {
            final a b = com.tencent.bugly.crashreport.common.info.a.b();
            if (b == null) {
                return null;
            }
            if (TextUtils.isEmpty((CharSequence)b.m)) {
                final p a = p.a();
                if (a == null) {
                    return b.m;
                }
                final Map<String, byte[]> a2 = a.a(556, null, true);
                if (a2 != null) {
                    final byte[] array = (byte[])a2.get((Object)"app_channel");
                    if (array != null) {
                        return new String(array);
                    }
                }
            }
            return b.m;
        }
    }
    
    public static void init(final Context context, final String s, final boolean b) {
        init(context, s, b, null);
    }
    
    public static void init(final Context p0, final String p1, final boolean p2, final BuglyStrategy p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          8
        //     5: monitorenter   
        //     6: getstatic       com/tencent/bugly/Bugly.a:Z
        //     9: istore          6
        //    11: iload           6
        //    13: ifeq            20
        //    16: aload           8
        //    18: monitorexit    
        //    19: return         
        //    20: iconst_1       
        //    21: putstatic       com/tencent/bugly/Bugly.a:Z
        //    24: aload_0        
        //    25: invokestatic    com/tencent/bugly/proguard/z.a:(Landroid/content/Context;)Landroid/content/Context;
        //    28: astore_0       
        //    29: aload_0        
        //    30: putstatic       com/tencent/bugly/Bugly.applicationContext:Landroid/content/Context;
        //    33: aload_0        
        //    34: ifnonnull       50
        //    37: getstatic       com/tencent/bugly/proguard/x.a:Ljava/lang/String;
        //    40: ldc             "init arg 'context' should not be null!"
        //    42: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    45: pop            
        //    46: aload           8
        //    48: monitorexit    
        //    49: return         
        //    50: invokestatic    com/tencent/bugly/Bugly.isDev:()Z
        //    53: ifeq            62
        //    56: getstatic       com/tencent/bugly/Bugly.c:[Ljava/lang/String;
        //    59: putstatic       com/tencent/bugly/Bugly.b:[Ljava/lang/String;
        //    62: getstatic       com/tencent/bugly/Bugly.b:[Ljava/lang/String;
        //    65: astore_0       
        //    66: aload_0        
        //    67: arraylength    
        //    68: istore          5
        //    70: iconst_0       
        //    71: istore          4
        //    73: iload           4
        //    75: iload           5
        //    77: if_icmpge       150
        //    80: aload_0        
        //    81: iload           4
        //    83: aaload         
        //    84: astore          7
        //    86: aload           7
        //    88: ldc             "BuglyCrashModule"
        //    90: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    93: ifeq            105
        //    96: invokestatic    com/tencent/bugly/CrashModule.getInstance:()Lcom/tencent/bugly/CrashModule;
        //    99: invokestatic    com/tencent/bugly/b.a:(Lcom/tencent/bugly/a;)V
        //   102: goto            144
        //   105: aload           7
        //   107: ldc             "BuglyBetaModule"
        //   109: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   112: ifne            144
        //   115: aload           7
        //   117: ldc             "BuglyRqdModule"
        //   119: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   122: ifne            144
        //   125: aload           7
        //   127: ldc             "BuglyFeedbackModule"
        //   129: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   132: pop            
        //   133: goto            144
        //   136: astore          7
        //   138: aload           7
        //   140: invokestatic    com/tencent/bugly/proguard/x.b:(Ljava/lang/Throwable;)Z
        //   143: pop            
        //   144: iinc            4, 1
        //   147: goto            73
        //   150: getstatic       com/tencent/bugly/Bugly.enable:Z
        //   153: putstatic       com/tencent/bugly/b.a:Z
        //   156: getstatic       com/tencent/bugly/Bugly.applicationContext:Landroid/content/Context;
        //   159: aload_1        
        //   160: iload_2        
        //   161: aload_3        
        //   162: invokestatic    com/tencent/bugly/b.a:(Landroid/content/Context;Ljava/lang/String;ZLcom/tencent/bugly/BuglyStrategy;)V
        //   165: aload           8
        //   167: monitorexit    
        //   168: return         
        //   169: astore_0       
        //   170: aload           8
        //   172: monitorexit    
        //   173: aload_0        
        //   174: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      11     169    175    Any
        //  20     33     169    175    Any
        //  37     46     169    175    Any
        //  50     62     169    175    Any
        //  62     70     169    175    Any
        //  86     102    136    144    Any
        //  105    133    136    144    Any
        //  138    144    169    175    Any
        //  150    165    169    175    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Attempt to invoke virtual method 'g5.m0 g5.d2.L()' on a null object reference
        //     at e5.d0.e(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:26)
        //     at e5.c0.s(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1643)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2651)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
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
    
    public static boolean isDev() {
        if (Bugly.isDev == null) {
            Bugly.isDev = Boolean.parseBoolean("false".replace((CharSequence)"@", (CharSequence)""));
        }
        return Bugly.isDev;
    }
}
