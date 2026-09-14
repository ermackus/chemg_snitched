package com.alibaba.mtl.log.d;

import java.lang.reflect.Field;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alibaba.mtl.log.b;
import android.util.DisplayMetrics;
import java.util.Locale;
import com.alibaba.mtl.log.model.LogField;
import android.content.Context;
import java.util.Map;

public class d
{
    private static Map<String, String> v;
    
    public static Map<String, String> a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          4
        //     5: monitorenter   
        //     6: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //     9: ifnull          64
        //    12: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //    15: getstatic       com/alibaba/mtl/log/model/LogField.CHANNEL:Lcom/alibaba/mtl/log/model/LogField;
        //    18: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //    21: invokestatic    com/alibaba/mtl/log/d/b.m:()Ljava/lang/String;
        //    24: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    29: pop            
        //    30: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //    33: getstatic       com/alibaba/mtl/log/model/LogField.APPKEY:Lcom/alibaba/mtl/log/model/LogField;
        //    36: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //    39: invokestatic    com/alibaba/mtl/log/d/b.getAppkey:()Ljava/lang/String;
        //    42: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    47: pop            
        //    48: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //    51: aload_0        
        //    52: invokestatic    com/alibaba/mtl/log/d/d.a:(Ljava/util/Map;Landroid/content/Context;)V
        //    55: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //    58: astore_0       
        //    59: aload           4
        //    61: monitorexit    
        //    62: aload_0        
        //    63: areturn        
        //    64: aload_0        
        //    65: ifnull          462
        //    68: new             Ljava/util/HashMap;
        //    71: astore_1       
        //    72: aload_1        
        //    73: invokespecial   java/util/HashMap.<init>:()V
        //    76: aload_1        
        //    77: putstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //    80: aload_0        
        //    81: invokestatic    com/alibaba/mtl/log/d/m.getImei:(Landroid/content/Context;)Ljava/lang/String;
        //    84: astore_2       
        //    85: aload_0        
        //    86: invokestatic    com/alibaba/mtl/log/d/m.getImsi:(Landroid/content/Context;)Ljava/lang/String;
        //    89: astore_3       
        //    90: aload_2        
        //    91: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    94: ifne            106
        //    97: aload_3        
        //    98: astore_1       
        //    99: aload_3        
        //   100: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   103: ifeq            112
        //   106: ldc             ""
        //   108: astore_2       
        //   109: ldc             ""
        //   111: astore_1       
        //   112: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   115: getstatic       com/alibaba/mtl/log/model/LogField.IMEI:Lcom/alibaba/mtl/log/model/LogField;
        //   118: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   121: aload_2        
        //   122: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   127: pop            
        //   128: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   131: getstatic       com/alibaba/mtl/log/model/LogField.IMSI:Lcom/alibaba/mtl/log/model/LogField;
        //   134: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   137: aload_1        
        //   138: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   143: pop            
        //   144: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   147: getstatic       com/alibaba/mtl/log/model/LogField.BRAND:Lcom/alibaba/mtl/log/model/LogField;
        //   150: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   153: getstatic       android/os/Build.BRAND:Ljava/lang/String;
        //   156: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   161: pop            
        //   162: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   165: getstatic       com/alibaba/mtl/log/model/LogField.DEVICE_MODEL:Lcom/alibaba/mtl/log/model/LogField;
        //   168: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   171: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   174: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   179: pop            
        //   180: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   183: getstatic       com/alibaba/mtl/log/model/LogField.RESOLUTION:Lcom/alibaba/mtl/log/model/LogField;
        //   186: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   189: aload_0        
        //   190: invokestatic    com/alibaba/mtl/log/d/d.c:(Landroid/content/Context;)Ljava/lang/String;
        //   193: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   198: pop            
        //   199: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   202: getstatic       com/alibaba/mtl/log/model/LogField.CHANNEL:Lcom/alibaba/mtl/log/model/LogField;
        //   205: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   208: invokestatic    com/alibaba/mtl/log/d/b.m:()Ljava/lang/String;
        //   211: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   216: pop            
        //   217: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   220: getstatic       com/alibaba/mtl/log/model/LogField.APPKEY:Lcom/alibaba/mtl/log/model/LogField;
        //   223: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   226: invokestatic    com/alibaba/mtl/log/d/b.getAppkey:()Ljava/lang/String;
        //   229: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   234: pop            
        //   235: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   238: getstatic       com/alibaba/mtl/log/model/LogField.APPVERSION:Lcom/alibaba/mtl/log/model/LogField;
        //   241: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   244: aload_0        
        //   245: invokestatic    com/alibaba/mtl/log/d/d.d:(Landroid/content/Context;)Ljava/lang/String;
        //   248: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   253: pop            
        //   254: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   257: getstatic       com/alibaba/mtl/log/model/LogField.LANGUAGE:Lcom/alibaba/mtl/log/model/LogField;
        //   260: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   263: aload_0        
        //   264: invokestatic    com/alibaba/mtl/log/d/d.b:(Landroid/content/Context;)Ljava/lang/String;
        //   267: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   272: pop            
        //   273: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   276: getstatic       com/alibaba/mtl/log/model/LogField.OS:Lcom/alibaba/mtl/log/model/LogField;
        //   279: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   282: invokestatic    com/alibaba/mtl/log/d/d.q:()Ljava/lang/String;
        //   285: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   290: pop            
        //   291: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   294: getstatic       com/alibaba/mtl/log/model/LogField.OSVERSION:Lcom/alibaba/mtl/log/model/LogField;
        //   297: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   300: invokestatic    com/alibaba/mtl/log/d/d.p:()Ljava/lang/String;
        //   303: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   308: pop            
        //   309: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   312: getstatic       com/alibaba/mtl/log/model/LogField.SDKVERSION:Lcom/alibaba/mtl/log/model/LogField;
        //   315: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   318: ldc             "2.6.4.10_for_bc"
        //   320: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   325: pop            
        //   326: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   329: getstatic       com/alibaba/mtl/log/model/LogField.SDKTYPE:Lcom/alibaba/mtl/log/model/LogField;
        //   332: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   335: ldc             "mini"
        //   337: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   342: pop            
        //   343: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   346: getstatic       com/alibaba/mtl/log/model/LogField.UTDID:Lcom/alibaba/mtl/log/model/LogField;
        //   349: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   352: aload_0        
        //   353: invokestatic    com/ut/device/UTDevice.getUtdid:(Landroid/content/Context;)Ljava/lang/String;
        //   356: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   361: pop            
        //   362: goto            378
        //   365: astore_1       
        //   366: ldc             "DeviceUtil"
        //   368: ldc             "utdid4all jar doesn't exist, please copy the libs folder."
        //   370: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   373: pop            
        //   374: aload_1        
        //   375: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   378: aload_0        
        //   379: ldc             "phone"
        //   381: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   384: checkcast       Landroid/telephony/TelephonyManager;
        //   387: astore_3       
        //   388: ldc             ""
        //   390: astore_2       
        //   391: aload_2        
        //   392: astore_1       
        //   393: aload_3        
        //   394: ifnull          412
        //   397: aload_2        
        //   398: astore_1       
        //   399: aload_3        
        //   400: invokevirtual   android/telephony/TelephonyManager.getSimState:()I
        //   403: iconst_5       
        //   404: if_icmpne       412
        //   407: aload_3        
        //   408: invokevirtual   android/telephony/TelephonyManager.getNetworkOperatorName:()Ljava/lang/String;
        //   411: astore_1       
        //   412: aload_1        
        //   413: astore_2       
        //   414: aload_1        
        //   415: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   418: ifeq            424
        //   421: ldc             "Unknown"
        //   423: astore_2       
        //   424: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   427: getstatic       com/alibaba/mtl/log/model/LogField.CARRIER:Lcom/alibaba/mtl/log/model/LogField;
        //   430: invokevirtual   com/alibaba/mtl/log/model/LogField.toString:()Ljava/lang/String;
        //   433: aload_2        
        //   434: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   439: pop            
        //   440: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   443: aload_0        
        //   444: invokestatic    com/alibaba/mtl/log/d/d.a:(Ljava/util/Map;Landroid/content/Context;)V
        //   447: getstatic       com/alibaba/mtl/log/d/d.v:Ljava/util/Map;
        //   450: astore_0       
        //   451: aload           4
        //   453: monitorexit    
        //   454: aload_0        
        //   455: areturn        
        //   456: astore_0       
        //   457: aload           4
        //   459: monitorexit    
        //   460: aconst_null    
        //   461: areturn        
        //   462: aload           4
        //   464: monitorexit    
        //   465: aconst_null    
        //   466: areturn        
        //   467: astore_0       
        //   468: aload           4
        //   470: monitorexit    
        //   471: aload_0        
        //   472: athrow         
        //   473: astore_1       
        //   474: goto            440
        //    Signature:
        //  (Landroid/content/Context;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      59     467    473    Any
        //  68     80     467    473    Any
        //  80     97     456    462    Ljava/lang/Exception;
        //  80     97     467    473    Any
        //  99     106    456    462    Ljava/lang/Exception;
        //  99     106    467    473    Any
        //  112    343    456    462    Ljava/lang/Exception;
        //  112    343    467    473    Any
        //  343    362    365    378    Any
        //  366    378    456    462    Ljava/lang/Exception;
        //  366    378    467    473    Any
        //  378    388    473    477    Ljava/lang/Exception;
        //  378    388    467    473    Any
        //  399    412    473    477    Ljava/lang/Exception;
        //  399    412    467    473    Any
        //  414    421    473    477    Ljava/lang/Exception;
        //  414    421    467    473    Any
        //  424    440    473    477    Ljava/lang/Exception;
        //  424    440    467    473    Any
        //  440    447    456    462    Ljava/lang/Exception;
        //  440    447    467    473    Any
        //  447    451    467    473    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0378:
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
    
    private static void a(final Map<String, String> map, final Context context) {
        try {
            final String[] networkState = l.getNetworkState(context);
            map.put((Object)LogField.ACCESS.toString(), (Object)networkState[0]);
            if (networkState[0].equals((Object)"2G/3G")) {
                map.put((Object)LogField.ACCESS_SUBTYPE.toString(), (Object)networkState[1]);
            }
            else {
                map.put((Object)LogField.ACCESS_SUBTYPE.toString(), (Object)"Unknown");
            }
        }
        catch (final Exception ex) {
            map.put((Object)LogField.ACCESS.toString(), (Object)"Unknown");
            map.put((Object)LogField.ACCESS_SUBTYPE.toString(), (Object)"Unknown");
        }
    }
    
    private static String b(final Context context) {
        String s;
        try {
            Locale.getDefault().getLanguage();
        }
        finally {
            s = "Unknown";
        }
        return s;
    }
    
    private static String c(final Context context) {
        String string;
        try {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            final int widthPixels = displayMetrics.widthPixels;
            final int heightPixels = displayMetrics.heightPixels;
            int n = widthPixels;
            int n2 = heightPixels;
            if (widthPixels > heightPixels) {
                final int n3 = widthPixels ^ heightPixels;
                n2 = (heightPixels ^ n3);
                n = (n3 ^ n2);
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(n2);
            sb.append("*");
            sb.append(n);
            string = sb.toString();
        }
        catch (final Exception ex) {
            string = "Unknown";
        }
        return string;
    }
    
    public static String d(final Context context) {
        final String f = b.a().f();
        if (!TextUtils.isEmpty((CharSequence)f)) {
            return f;
        }
        final String s = "Unknown";
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String versionName = s;
            if (packageInfo != null) {
                d.v.put((Object)LogField.APPVERSION.toString(), (Object)packageInfo.versionName);
                versionName = packageInfo.versionName;
            }
            return versionName;
        }
        finally {
            return s;
        }
    }
    
    public static boolean i() {
        try {
            return (System.getProperty("java.vm.name") != null && System.getProperty("java.vm.name").toLowerCase().contains((CharSequence)"lemur")) || System.getProperty("ro.yunos.version") != null || !TextUtils.isEmpty((CharSequence)r.get("ro.yunos.build.version")) || j();
        }
        finally {
            return false;
        }
    }
    
    private static boolean j() {
        return !TextUtils.isEmpty((CharSequence)r.get("ro.yunos.product.chip")) || !TextUtils.isEmpty((CharSequence)r.get("ro.yunos.hardware"));
    }
    
    private static String p() {
        String s = Build$VERSION.RELEASE;
        if (i()) {
            final String property = System.getProperty("ro.yunos.version");
            if (!TextUtils.isEmpty((CharSequence)property)) {
                return property;
            }
            if (!TextUtils.isEmpty((CharSequence)(s = t()))) {}
        }
        return s;
    }
    
    private static String q() {
        String s;
        if (i() && !j()) {
            s = "y";
        }
        else {
            s = "a";
        }
        return s;
    }
    
    public static String r() {
        String s;
        if ("false".equals((Object)(s = r.get("ro.aliyun.clouduuid", "false")))) {
            s = r.get("ro.sys.aliyun.clouduuid", "false");
        }
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = s();
        }
        return s2;
    }
    
    private static String s() {
        String s = null;
        try {
            s = (String)Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", (Class<?>[])new Class[0]).invoke((Object)null, new Object[0]);
            return s;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    private static String t() {
        try {
            final Field declaredField = Build.class.getDeclaredField("YUNOS_BUILD_VERSION");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return (String)declaredField.get((Object)new String());
            }
            return null;
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
