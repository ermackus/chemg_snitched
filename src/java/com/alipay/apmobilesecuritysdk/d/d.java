package com.alipay.apmobilesecuritysdk.d;

import java.util.HashMap;
import com.alipay.sdk.m.a0.f;
import com.alipay.sdk.m.a0.b;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.m.a0.e;
import android.content.Context;
import java.util.Map;

public final class d
{
    public static Map<String, String> a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore_2       
        //     4: monitorenter   
        //     5: new             Ljava/util/HashMap;
        //     8: dup            
        //     9: invokespecial   java/util/HashMap.<init>:()V
        //    12: astore_1       
        //    13: new             Lcom/alipay/apmobilesecuritysdk/c/b;
        //    16: invokespecial   com/alipay/apmobilesecuritysdk/c/b.<init>:()V
        //    19: aload_1        
        //    20: ldc             "AE16"
        //    22: ldc             ""
        //    24: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    29: pop            
        //    30: aload_2        
        //    31: monitorexit    
        //    32: aload_1        
        //    33: areturn        
        //    34: astore_0       
        //    35: aload_2        
        //    36: monitorexit    
        //    37: aload_0        
        //    38: athrow         
        //    39: astore_0       
        //    40: goto            30
        //    Signature:
        //  ()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      13     34     39     Any
        //  13     30     39     43     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    public static Map<String, String> a(final Context context) {
        synchronized (d.class) {
            e.a();
            b.a((f)APSecuritySdk.getInstance(context));
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)"AE1", (Object)e.b());
            final StringBuilder sb = new StringBuilder();
            String s;
            if (e.c()) {
                s = "1";
            }
            else {
                s = "0";
            }
            sb.append(s);
            ((Map)hashMap).put((Object)"AE2", (Object)sb.toString());
            final StringBuilder sb2 = new StringBuilder();
            String s2;
            if (e.d()) {
                s2 = "1";
            }
            else {
                s2 = "0";
            }
            sb2.append(s2);
            ((Map)hashMap).put((Object)"AE3", (Object)sb2.toString());
            ((Map)hashMap).put((Object)"AE4", (Object)e.e());
            ((Map)hashMap).put((Object)"AE5", (Object)e.f());
            ((Map)hashMap).put((Object)"AE6", (Object)e.g());
            ((Map)hashMap).put((Object)"AE7", (Object)e.h());
            ((Map)hashMap).put((Object)"AE8", (Object)e.i());
            ((Map)hashMap).put((Object)"AE9", (Object)e.j());
            ((Map)hashMap).put((Object)"AE10", (Object)e.k());
            ((Map)hashMap).put((Object)"AE11", (Object)e.l());
            ((Map)hashMap).put((Object)"AE12", (Object)e.m());
            ((Map)hashMap).put((Object)"AE13", (Object)e.n());
            ((Map)hashMap).put((Object)"AE14", (Object)e.o());
            ((Map)hashMap).put((Object)"AE15", (Object)e.p());
            ((Map)hashMap).put((Object)"AE21", (Object)b.f());
            return (Map<String, String>)hashMap;
        }
    }
}
