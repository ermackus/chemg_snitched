package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager$MemoryInfo;
import java.util.HashMap;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.tencent.bugly.proguard.x;
import android.content.Context;
import android.app.ActivityManager;

public class AppInfo
{
    private static ActivityManager a;
    
    static {
        "@buglyAllChannel@".split(",");
        "@buglyAllChannelPriority@".split(",");
    }
    
    public static String a(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: new             Ljava/lang/StringBuilder;
        //     7: astore_3       
        //     8: aload_3        
        //     9: ldc             "/proc/"
        //    11: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    14: aload_3        
        //    15: iload_0        
        //    16: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    19: pop            
        //    20: aload_3        
        //    21: ldc             "/cmdline"
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    26: pop            
        //    27: aload_2        
        //    28: aload_3        
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    35: sipush          512
        //    38: newarray        C
        //    40: astore          4
        //    42: aload_2        
        //    43: aload           4
        //    45: invokevirtual   java/io/FileReader.read:([C)I
        //    48: pop            
        //    49: iconst_0       
        //    50: istore_1       
        //    51: iload_1        
        //    52: sipush          512
        //    55: if_icmpge       71
        //    58: aload           4
        //    60: iload_1        
        //    61: caload         
        //    62: ifeq            71
        //    65: iinc            1, 1
        //    68: goto            51
        //    71: new             Ljava/lang/String;
        //    74: astore_3       
        //    75: aload_3        
        //    76: aload           4
        //    78: invokespecial   java/lang/String.<init>:([C)V
        //    81: aload_3        
        //    82: iconst_0       
        //    83: iload_1        
        //    84: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    87: astore_3       
        //    88: aload_2        
        //    89: invokevirtual   java/io/FileReader.close:()V
        //    92: aload_3        
        //    93: areturn        
        //    94: astore_3       
        //    95: goto            101
        //    98: astore_3       
        //    99: aconst_null    
        //   100: astore_2       
        //   101: aload_3        
        //   102: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   105: ifne            112
        //   108: aload_3        
        //   109: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   112: aload_2        
        //   113: ifnull          120
        //   116: aload_2        
        //   117: invokevirtual   java/io/FileReader.close:()V
        //   120: iload_0        
        //   121: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   124: areturn        
        //   125: astore_3       
        //   126: aload_2        
        //   127: ifnull          134
        //   130: aload_2        
        //   131: invokevirtual   java/io/FileReader.close:()V
        //   134: aload_3        
        //   135: athrow         
        //   136: astore_2       
        //   137: goto            92
        //   140: astore_2       
        //   141: goto            120
        //   144: astore_2       
        //   145: goto            134
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      35     98     101    Any
        //  35     49     94     98     Any
        //  71     88     94     98     Any
        //  88     92     136    140    Any
        //  101    112    125    136    Any
        //  116    120    140    144    Any
        //  130    134    144    148    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 81, Size: 81
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
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
    
    public static String a(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return "fail";
        }
    }
    
    public static List<String> a(final Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            final String s = (String)map.get((Object)"BUGLY_DISABLE");
            if (s != null && s.length() != 0) {
                final String[] split = s.split(",");
                for (int i = 0; i < split.length; ++i) {
                    split[i] = split[i].trim();
                }
                return (List<String>)Arrays.asList((Object[])split);
            }
            return null;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static boolean a(final Context context, final String s) {
        if (context != null && s != null) {
            if (s.trim().length() > 0) {
                try {
                    final String[] requestedPermissions = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                    if (requestedPermissions != null) {
                        for (int length = requestedPermissions.length, i = 0; i < length; ++i) {
                            if (s.equals((Object)requestedPermissions[i])) {
                                return true;
                            }
                        }
                        return false;
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
        return false;
    }
    
    public static PackageInfo b(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a(context), 0);
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static String c(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final PackageManager packageManager = context.getPackageManager();
            final ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (packageManager != null && applicationInfo != null) {
                final CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
                if (applicationLabel != null) {
                    return applicationLabel.toString();
                }
            }
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
        }
        return null;
    }
    
    public static Map<String, String> d(final Context context) {
        final Map<String, String> map = null;
        if (context == null) {
            return null;
        }
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            HashMap hashMap = (HashMap)map;
            if (applicationInfo.metaData != null) {
                hashMap = new HashMap();
                final Object value = applicationInfo.metaData.get("BUGLY_DISABLE");
                if (value != null) {
                    hashMap.put((Object)"BUGLY_DISABLE", (Object)value.toString());
                }
                final Object value2 = applicationInfo.metaData.get("BUGLY_APPID");
                if (value2 != null) {
                    hashMap.put((Object)"BUGLY_APPID", (Object)value2.toString());
                }
                final Object value3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
                if (value3 != null) {
                    hashMap.put((Object)"BUGLY_APP_CHANNEL", (Object)value3.toString());
                }
                final Object value4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
                if (value4 != null) {
                    hashMap.put((Object)"BUGLY_APP_VERSION", (Object)value4.toString());
                }
                final Object value5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
                if (value5 != null) {
                    hashMap.put((Object)"BUGLY_ENABLE_DEBUG", (Object)value5.toString());
                }
                final Object value6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
                if (value6 != null) {
                    hashMap.put((Object)"com.tencent.rdm.uuid", (Object)value6.toString());
                }
                final Object value7 = applicationInfo.metaData.get("BUGLY_APP_BUILD_NO");
                if (value7 != null) {
                    hashMap.put((Object)"BUGLY_APP_BUILD_NO", (Object)value7.toString());
                }
                final Object value8 = applicationInfo.metaData.get("BUGLY_AREA");
                if (value8 != null) {
                    hashMap.put((Object)"BUGLY_AREA", (Object)value8.toString());
                }
            }
            return (Map<String, String>)hashMap;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static boolean e(final Context context) {
        if (context == null) {
            return false;
        }
        if (AppInfo.a == null) {
            AppInfo.a = (ActivityManager)context.getSystemService("activity");
        }
        try {
            final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
            AppInfo.a.getMemoryInfo(activityManager$MemoryInfo);
            if (activityManager$MemoryInfo.lowMemory) {
                x.c("Memory is low.", new Object[0]);
                return true;
            }
            return false;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return false;
        }
    }
}
