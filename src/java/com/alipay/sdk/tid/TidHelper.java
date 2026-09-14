package com.alipay.sdk.tid;

import android.os.Looper;
import com.alipay.sdk.m.u.e;
import android.text.TextUtils;
import org.json.JSONObject;
import com.alipay.sdk.m.q.c;
import com.alipay.sdk.m.s.b;
import com.alipay.sdk.m.t.a;
import android.content.Context;

public class TidHelper
{
    public static Tid a(final Context context, final a a) {
        if (a != null && !a.i()) {
            return new Tid(a.d(), a.c(), a.e());
        }
        return null;
    }
    
    public static void a(final Context context) {
        if (context == null) {
            return;
        }
        b.d().a(context);
    }
    
    public static Tid b(final Context context) throws Exception {
        try {
            final com.alipay.sdk.m.p.b a = new c().a(com.alipay.sdk.m.s.a.h(), context);
            if (a != null) {
                final JSONObject jsonObject = new JSONObject(a.a());
                final a a2 = com.alipay.sdk.m.t.a.a(context);
                final String optString = jsonObject.optString("tid");
                final String string = jsonObject.getString("client_key");
                if (!TextUtils.isEmpty((CharSequence)optString) && !TextUtils.isEmpty((CharSequence)string)) {
                    a2.a(optString, string);
                }
                return a(context, a2);
            }
            return null;
        }
        finally {
            return null;
        }
    }
    
    public static void clearTID(final Context context) {
        a.a(context).a();
    }
    
    public static String getIMEI(final Context context) {
        a(context);
        return com.alipay.sdk.m.u.c.b(context).b();
    }
    
    public static String getIMSI(final Context context) {
        a(context);
        return com.alipay.sdk.m.u.c.b(context).c();
    }
    
    public static String getTIDValue(final Context context) {
        synchronized (TidHelper.class) {
            final Tid loadOrCreateTID = loadOrCreateTID(context);
            String tid;
            if (Tid.isEmpty(loadOrCreateTID)) {
                tid = "";
            }
            else {
                tid = loadOrCreateTID.getTid();
            }
            return tid;
        }
    }
    
    public static String getVirtualImei(final Context context) {
        a(context);
        com.alipay.sdk.m.m.b.b();
        return com.alipay.sdk.m.m.b.f();
    }
    
    public static String getVirtualImsi(final Context context) {
        a(context);
        com.alipay.sdk.m.m.b.b();
        return com.alipay.sdk.m.m.b.g();
    }
    
    public static Tid loadLocalTid(final Context context) {
        final a a = com.alipay.sdk.m.t.a.a(context);
        if (a.h()) {
            return null;
        }
        return new Tid(a.d(), a.c(), a.e());
    }
    
    public static Tid loadOrCreateTID(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          4
        //     5: monitorenter   
        //     6: ldc             "mspl"
        //     8: ldc             "load_create_tid"
        //    10: invokestatic    com/alipay/sdk/m/u/e.b:(Ljava/lang/String;Ljava/lang/String;)V
        //    13: aload_0        
        //    14: invokestatic    com/alipay/sdk/tid/TidHelper.a:(Landroid/content/Context;)V
        //    17: aload_0        
        //    18: invokestatic    com/alipay/sdk/tid/TidHelper.loadTID:(Landroid/content/Context;)Lcom/alipay/sdk/tid/Tid;
        //    21: astore_2       
        //    22: aload_2        
        //    23: astore_1       
        //    24: aload_2        
        //    25: invokestatic    com/alipay/sdk/tid/Tid.isEmpty:(Lcom/alipay/sdk/tid/Tid;)Z
        //    28: ifeq            54
        //    31: invokestatic    android/os/Looper.myLooper:()Landroid/os/Looper;
        //    34: astore_3       
        //    35: invokestatic    android/os/Looper.getMainLooper:()Landroid/os/Looper;
        //    38: astore_1       
        //    39: aload_3        
        //    40: aload_1        
        //    41: if_acmpne       49
        //    44: aload           4
        //    46: monitorexit    
        //    47: aconst_null    
        //    48: areturn        
        //    49: aload_0        
        //    50: invokestatic    com/alipay/sdk/tid/TidHelper.b:(Landroid/content/Context;)Lcom/alipay/sdk/tid/Tid;
        //    53: astore_1       
        //    54: aload           4
        //    56: monitorexit    
        //    57: aload_1        
        //    58: areturn        
        //    59: astore_0       
        //    60: aload           4
        //    62: monitorexit    
        //    63: aload_0        
        //    64: athrow         
        //    65: astore_0       
        //    66: aload_2        
        //    67: astore_1       
        //    68: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      22     59     65     Any
        //  24     39     59     65     Any
        //  49     54     65     71     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    
    public static Tid loadTID(final Context context) {
        a(context);
        final Tid a = a(context, com.alipay.sdk.m.t.a.a(context));
        if (a == null) {
            e.b("mspl", "load_tid null");
        }
        return a;
    }
    
    public static boolean resetTID(final Context context) throws Exception {
        e.b("mspl", "reset_tid");
        if (Looper.myLooper() != Looper.getMainLooper()) {
            a(context);
            clearTID(context);
            final Tid tid = null;
            Tid tid2;
            try {
                b(context);
            }
            finally {
                tid2 = tid;
            }
            return !Tid.isEmpty(tid2);
        }
        throw new Exception("Must be called on worker thread");
    }
}
