package com.alipay.apmobilesecuritysdk.face;

import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.h;
import java.util.Map;
import java.util.HashMap;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import android.content.Context;
import com.alipay.sdk.m.a0.f;

public class APSecuritySdk implements f
{
    public static APSecuritySdk a;
    public static APSecBgCheckerInterface bgChecker;
    public static IDeviceInfo c;
    public static Object d;
    public Context b;
    
    static {
        APSecuritySdk.d = new Object();
    }
    
    public APSecuritySdk(final Context b) {
        this.b = b;
    }
    
    public static IDeviceInfo getDeviceInfo() {
        return APSecuritySdk.c;
    }
    
    public static APSecuritySdk getInstance(final Context context) {
        if (APSecuritySdk.a == null) {
            final Object d = APSecuritySdk.d;
            synchronized (d) {
                if (APSecuritySdk.a == null) {
                    APSecuritySdk.a = new APSecuritySdk(context);
                }
            }
        }
        return APSecuritySdk.a;
    }
    
    public static String getUtdid(final Context context) {
        return UtdidWrapper.getUtdid(context);
    }
    
    public static void registerBgChecker(final APSecBgCheckerInterface bgChecker) {
        APSecuritySdk.bgChecker = bgChecker;
    }
    
    public static void registerDeviceInfo(final IDeviceInfo c) {
        APSecuritySdk.c = c;
    }
    
    public String getAndroidId() {
        final IDeviceInfo c = APSecuritySdk.c;
        if (c != null) {
            return c.getAndroidId();
        }
        return null;
    }
    
    public String getApdidToken() {
        final String a = com.alipay.apmobilesecuritysdk.a.a.a(this.b, "");
        if (com.alipay.sdk.m.z.a.a(a)) {
            this.initToken(0, (Map<String, String>)new HashMap(), null);
        }
        return a;
    }
    
    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }
    
    public String getSdkVersion() {
        return "3.4.0.202311031119";
    }
    
    public String getSubscriberId() {
        final IDeviceInfo c = APSecuritySdk.c;
        if (c != null) {
            return c.getSubscriberId();
        }
        return null;
    }
    
    public APSecuritySdk.APSecuritySdk$TokenResult getTokenResult() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore_3       
        //     3: monitorenter   
        //     4: new             Lcom/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult;
        //     7: dup            
        //     8: aload_0        
        //     9: invokespecial   com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.<init>:(Lcom/alipay/apmobilesecuritysdk/face/APSecuritySdk;)V
        //    12: astore_1       
        //    13: aload_1        
        //    14: aload_0        
        //    15: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk.b:Landroid/content/Context;
        //    18: ldc             ""
        //    20: invokestatic    com/alipay/apmobilesecuritysdk/a/a.a:(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
        //    23: putfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.apdidToken:Ljava/lang/String;
        //    26: aload_1        
        //    27: aload_0        
        //    28: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk.b:Landroid/content/Context;
        //    31: invokestatic    com/alipay/apmobilesecuritysdk/e/h.f:(Landroid/content/Context;)Ljava/lang/String;
        //    34: putfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.clientKey:Ljava/lang/String;
        //    37: aload_1        
        //    38: aload_0        
        //    39: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk.b:Landroid/content/Context;
        //    42: invokestatic    com/alipay/apmobilesecuritysdk/a/a.a:(Landroid/content/Context;)Ljava/lang/String;
        //    45: putfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.apdid:Ljava/lang/String;
        //    48: aload_1        
        //    49: aload_0        
        //    50: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk.b:Landroid/content/Context;
        //    53: invokestatic    com/alipay/apmobilesecuritysdk/otherid/UmidSdkWrapper.getSecurityToken:(Landroid/content/Context;)Ljava/lang/String;
        //    56: putfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.umidToken:Ljava/lang/String;
        //    59: aload_1        
        //    60: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.apdid:Ljava/lang/String;
        //    63: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    66: ifne            89
        //    69: aload_1        
        //    70: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.apdidToken:Ljava/lang/String;
        //    73: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    76: ifne            89
        //    79: aload_1        
        //    80: getfield        com/alipay/apmobilesecuritysdk/face/APSecuritySdk$TokenResult.clientKey:Ljava/lang/String;
        //    83: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    86: ifeq            104
        //    89: new             Ljava/util/HashMap;
        //    92: astore_2       
        //    93: aload_2        
        //    94: invokespecial   java/util/HashMap.<init>:()V
        //    97: aload_0        
        //    98: iconst_0       
        //    99: aload_2        
        //   100: aconst_null    
        //   101: invokevirtual   com/alipay/apmobilesecuritysdk/face/APSecuritySdk.initToken:(ILjava/util/Map;Lcom/alipay/apmobilesecuritysdk/face/APSecuritySdk$InitResultListener;)V
        //   104: aload_3        
        //   105: monitorexit    
        //   106: aload_1        
        //   107: areturn        
        //   108: astore_1       
        //   109: aload_3        
        //   110: monitorexit    
        //   111: aload_1        
        //   112: athrow         
        //   113: astore_2       
        //   114: goto            104
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      13     108    113    Any
        //  13     89     113    117    Any
        //  89     104    113    117    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
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
    
    public void initToken(final int n, final Map<String, String> map, final APSecuritySdk.APSecuritySdk$InitResultListener apSecuritySdk$InitResultListener) {
        com.alipay.apmobilesecuritysdk.b.a.a().a(n);
        final String b = h.b(this.b);
        final String c = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (com.alipay.sdk.m.z.a.b(b) && !com.alipay.sdk.m.z.a.a(b, c)) {
            com.alipay.apmobilesecuritysdk.e.a.a(this.b);
            com.alipay.apmobilesecuritysdk.e.d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!com.alipay.sdk.m.z.a.a(b, c)) {
            h.c(this.b, c);
        }
        final String a = com.alipay.sdk.m.z.a.a(map, "utdid", "");
        final String a2 = com.alipay.sdk.m.z.a.a(map, "tid", "");
        final String a3 = com.alipay.sdk.m.z.a.a(map, "userId", "");
        String utdid = a;
        if (com.alipay.sdk.m.z.a.a(a)) {
            utdid = UtdidWrapper.getUtdid(this.b);
        }
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)"utdid", (Object)utdid);
        ((Map)hashMap).put((Object)"tid", (Object)a2);
        ((Map)hashMap).put((Object)"userId", (Object)a3);
        ((Map)hashMap).put((Object)"appName", (Object)"");
        ((Map)hashMap).put((Object)"appKeyClient", (Object)"");
        ((Map)hashMap).put((Object)"appchannel", (Object)"");
        ((Map)hashMap).put((Object)"rpcVersion", (Object)"8");
        com.alipay.apmobilesecuritysdk.f.b.a().a((Runnable)new APSecuritySdk$1(this, (Map)hashMap, apSecuritySdk$InitResultListener));
    }
    
    public boolean isBackgroundRunning() {
        final APSecBgCheckerInterface bgChecker = APSecuritySdk.bgChecker;
        return bgChecker != null && bgChecker.isBackgroundRunning();
    }
}
