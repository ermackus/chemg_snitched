package com.alipay.sdk.m.b;

import com.alipay.sdk.m.c.a;
import android.content.Context;

public class c
{
    public static b a;
    public static boolean b;
    
    public static String a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore_2       
        //     4: monitorenter   
        //     5: aload_0        
        //     6: ifnull          58
        //     9: invokestatic    android/os/Looper.myLooper:()Landroid/os/Looper;
        //    12: invokestatic    android/os/Looper.getMainLooper:()Landroid/os/Looper;
        //    15: if_acmpeq       46
        //    18: aload_0        
        //    19: invokestatic    com/alipay/sdk/m/b/c.b:(Landroid/content/Context;)V
        //    22: getstatic       com/alipay/sdk/m/b/c.a:Lcom/alipay/sdk/m/b/b;
        //    25: astore_1       
        //    26: aload_1        
        //    27: ifnull          42
        //    30: aload_1        
        //    31: aload_0        
        //    32: invokeinterface com/alipay/sdk/m/b/b.a:(Landroid/content/Context;)Ljava/lang/String;
        //    37: astore_0       
        //    38: aload_2        
        //    39: monitorexit    
        //    40: aload_0        
        //    41: areturn        
        //    42: aload_2        
        //    43: monitorexit    
        //    44: aconst_null    
        //    45: areturn        
        //    46: new             Ljava/lang/IllegalStateException;
        //    49: astore_0       
        //    50: aload_0        
        //    51: ldc             "Cannot be called from the main thread"
        //    53: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    56: aload_0        
        //    57: athrow         
        //    58: new             Ljava/lang/RuntimeException;
        //    61: astore_0       
        //    62: aload_0        
        //    63: ldc             "Context is null"
        //    65: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    68: aload_0        
        //    69: athrow         
        //    70: astore_0       
        //    71: aload_2        
        //    72: monitorexit    
        //    73: aload_0        
        //    74: athrow         
        //    75: astore_0       
        //    76: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      26     70     75     Any
        //  30     38     75     79     Ljava/lang/Exception;
        //  30     38     70     75     Any
        //  46     58     70     75     Any
        //  58     70     70     75     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    public static void b(final Context context) {
        if (c.a == null && !c.b) {
            synchronized (c.class) {
                if (c.a == null && !c.b) {
                    c.a = com.alipay.sdk.m.c.a.a(context);
                    c.b = true;
                }
            }
        }
    }
}
