package com.alipay.apmobilesecuritysdk.f;

import java.util.Map;
import java.util.HashMap;
import org.json.JSONObject;
import com.alipay.sdk.m.b0.b;
import com.alipay.sdk.m.y.c;
import com.alipay.sdk.m.b0.e;
import android.content.Context;

public class a
{
    public static String a(final Context context, final String s, final String s2) {
        String b;
        final String s3 = b = null;
        if (context == null) {
            return b;
        }
        b = s3;
        if (com.alipay.sdk.m.z.a.a(s)) {
            return b;
        }
        if (com.alipay.sdk.m.z.a.a(s2)) {
            b = s3;
            return b;
        }
        try {
            final String a = e.a(context, s, s2, "");
            if (com.alipay.sdk.m.z.a.a(a)) {
                return null;
            }
            b = c.b(c.a(), a);
            return b;
        }
        finally {
            b = s3;
            return b;
        }
    }
    
    public static String a(String s, final String s2) {
        synchronized (a.class) {
            if (!com.alipay.sdk.m.z.a.a(s)) {
                if (!com.alipay.sdk.m.z.a.a(s2)) {
                    try {
                        s = b.a(s);
                        if (com.alipay.sdk.m.z.a.a(s)) {}
                        s = new JSONObject(s).getString(s2);
                        if (com.alipay.sdk.m.z.a.a(s)) {}
                        s = c.b(c.a(), s);
                        return s;
                    }
                    finally {
                        return null;
                    }
                }
            }
            return null;
        }
    }
    
    public static void a(final Context context, final String s, final String s2, final String s3) {
        if (com.alipay.sdk.m.z.a.a(s) || com.alipay.sdk.m.z.a.a(s2)) {
            return;
        }
        if (context == null) {
            return;
        }
        try {
            final String a = c.a(c.a(), s3);
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).put((Object)s2, (Object)a);
            e.a(context, s, (Map<String, String>)hashMap);
        }
        finally {}
    }
    
    public static void a(final String p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: dup            
        //     3: astore          6
        //     5: monitorenter   
        //     6: aload_0        
        //     7: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    10: ifne            178
        //    13: aload_1        
        //    14: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    17: istore_3       
        //    18: iload_3        
        //    19: ifeq            25
        //    22: goto            178
        //    25: aload_0        
        //    26: invokestatic    com/alipay/sdk/m/b0/b.a:(Ljava/lang/String;)Ljava/lang/String;
        //    29: astore          5
        //    31: new             Lorg/json/JSONObject;
        //    34: astore          4
        //    36: aload           4
        //    38: invokespecial   org/json/JSONObject.<init>:()V
        //    41: aload           5
        //    43: invokestatic    com/alipay/sdk/m/z/a.b:(Ljava/lang/String;)Z
        //    46: istore_3       
        //    47: iload_3        
        //    48: ifeq            77
        //    51: new             Lorg/json/JSONObject;
        //    54: astore          4
        //    56: aload           4
        //    58: aload           5
        //    60: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    63: goto            77
        //    66: astore          4
        //    68: new             Lorg/json/JSONObject;
        //    71: dup            
        //    72: invokespecial   org/json/JSONObject.<init>:()V
        //    75: astore          4
        //    77: aload           4
        //    79: aload_1        
        //    80: invokestatic    com/alipay/sdk/m/y/c.a:()Ljava/lang/String;
        //    83: aload_2        
        //    84: invokestatic    com/alipay/sdk/m/y/c.a:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    87: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    90: pop            
        //    91: aload           4
        //    93: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    96: pop            
        //    97: aload_0        
        //    98: invokestatic    java/lang/System.clearProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   101: pop            
        //   102: invokestatic    com/alipay/sdk/m/b0/c.a:()Z
        //   105: ifeq            174
        //   108: new             Ljava/lang/StringBuilder;
        //   111: astore_1       
        //   112: aload_1        
        //   113: ldc             ".SystemConfig"
        //   115: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   118: aload_1        
        //   119: getstatic       java/io/File.separator:Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: pop            
        //   126: aload_1        
        //   127: aload_0        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: pop            
        //   132: aload_1        
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: astore_0       
        //   137: invokestatic    com/alipay/sdk/m/b0/c.a:()Z
        //   140: ifeq            174
        //   143: new             Ljava/io/File;
        //   146: astore_1       
        //   147: aload_1        
        //   148: invokestatic    android/os/Environment.getExternalStorageDirectory:()Ljava/io/File;
        //   151: aload_0        
        //   152: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   155: aload_1        
        //   156: invokevirtual   java/io/File.exists:()Z
        //   159: ifeq            174
        //   162: aload_1        
        //   163: invokevirtual   java/io/File.isFile:()Z
        //   166: ifeq            174
        //   169: aload_1        
        //   170: invokevirtual   java/io/File.delete:()Z
        //   173: pop            
        //   174: aload           6
        //   176: monitorexit    
        //   177: return         
        //   178: aload           6
        //   180: monitorexit    
        //   181: return         
        //   182: astore_0       
        //   183: aload           6
        //   185: monitorexit    
        //   186: aload_0        
        //   187: athrow         
        //   188: astore_0       
        //   189: goto            174
        //   192: astore_1       
        //   193: goto            102
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      18     182    188    Any
        //  25     47     188    192    Any
        //  51     63     66     77     Ljava/lang/Exception;
        //  51     63     188    192    Any
        //  68     77     188    192    Any
        //  77     97     188    192    Any
        //  97     102    192    196    Any
        //  102    137    188    192    Any
        //  137    174    188    192    Ljava/lang/Exception;
        //  137    174    188    192    Any
        //  174    177    182    188    Any
        //  178    181    182    188    Any
        //  183    186    182    188    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 99, Size: 99
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
}
