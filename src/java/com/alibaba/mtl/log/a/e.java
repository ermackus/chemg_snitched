package com.alibaba.mtl.log.a;

import org.json.JSONObject;
import android.text.TextUtils;

public class e
{
    public static int f() {
        final String h = a.h();
        final boolean empty = TextUtils.isEmpty((CharSequence)h);
        int int1;
        final int n = int1 = 0;
        if (empty) {
            return int1;
        }
        try {
            final JSONObject jsonObject = new JSONObject(h);
            int1 = n;
            if (jsonObject.has("SYSTEM")) {
                final JSONObject jsonObject2 = jsonObject.getJSONObject("SYSTEM");
                int1 = n;
                if (jsonObject2 != null) {
                    int1 = n;
                    if (jsonObject2.has("cdb")) {
                        int1 = jsonObject2.getInt("cdb");
                    }
                }
            }
            return int1;
        }
        finally {
            int1 = n;
            return int1;
        }
    }
    
    public static void j(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifne            324
        //     7: new             Lorg/json/JSONObject;
        //    10: astore_2       
        //    11: aload_2        
        //    12: aload_0        
        //    13: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    16: aload_2        
        //    17: ldc             "SYSTEM"
        //    19: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    22: ifeq            324
        //    25: ldc             "SystemConfig"
        //    27: iconst_2       
        //    28: anewarray       Ljava/lang/Object;
        //    31: dup            
        //    32: iconst_0       
        //    33: ldc             "server system config "
        //    35: aastore        
        //    36: dup            
        //    37: iconst_1       
        //    38: aload_0        
        //    39: aastore        
        //    40: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    43: aload_2        
        //    44: ldc             "SYSTEM"
        //    46: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    49: astore_0       
        //    50: aload_0        
        //    51: ifnull          324
        //    54: aload_0        
        //    55: ldc             "bg_interval"
        //    57: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    60: ifeq            96
        //    63: new             Ljava/lang/StringBuilder;
        //    66: astore_2       
        //    67: aload_2        
        //    68: invokespecial   java/lang/StringBuilder.<init>:()V
        //    71: aload_2        
        //    72: aload_0        
        //    73: ldc             "bg_interval"
        //    75: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //    78: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    81: pop            
        //    82: aload_2        
        //    83: ldc             ""
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: pop            
        //    89: aload_2        
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokestatic    com/alibaba/mtl/log/a/a.h:(Ljava/lang/String;)V
        //    96: aload_0        
        //    97: ldc             "fg_interval"
        //    99: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   102: ifeq            138
        //   105: new             Ljava/lang/StringBuilder;
        //   108: astore_2       
        //   109: aload_2        
        //   110: invokespecial   java/lang/StringBuilder.<init>:()V
        //   113: aload_2        
        //   114: aload_0        
        //   115: ldc             "fg_interval"
        //   117: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   120: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   123: pop            
        //   124: aload_2        
        //   125: ldc             ""
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: pop            
        //   131: aload_2        
        //   132: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   135: invokestatic    com/alibaba/mtl/log/a/a.i:(Ljava/lang/String;)V
        //   138: ldc             "SystemConfig"
        //   140: iconst_2       
        //   141: anewarray       Ljava/lang/Object;
        //   144: dup            
        //   145: iconst_0       
        //   146: ldc             "UTDC.bSendToNewLogStore:"
        //   148: aastore        
        //   149: dup            
        //   150: iconst_1       
        //   151: getstatic       com/alibaba/mtl/log/a.r:Z
        //   154: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   157: aastore        
        //   158: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   161: ldc             "SystemConfig"
        //   163: iconst_2       
        //   164: anewarray       Ljava/lang/Object;
        //   167: dup            
        //   168: iconst_0       
        //   169: ldc             "Config.BACKGROUND_PERIOD:"
        //   171: aastore        
        //   172: dup            
        //   173: iconst_1       
        //   174: invokestatic    com/alibaba/mtl/log/a/a.b:()J
        //   177: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   180: aastore        
        //   181: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   184: ldc             "SystemConfig"
        //   186: iconst_2       
        //   187: anewarray       Ljava/lang/Object;
        //   190: dup            
        //   191: iconst_0       
        //   192: ldc             "Config.FOREGROUND_PERIOD:"
        //   194: aastore        
        //   195: dup            
        //   196: iconst_1       
        //   197: invokestatic    com/alibaba/mtl/log/a/a.a:()J
        //   200: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   203: aastore        
        //   204: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   207: aload_0        
        //   208: ldc             "discard"
        //   210: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   213: ifeq            258
        //   216: aload_0        
        //   217: ldc             "discard"
        //   219: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   222: istore_1       
        //   223: iload_1        
        //   224: iconst_1       
        //   225: if_icmpne       241
        //   228: iconst_1       
        //   229: putstatic       com/alibaba/mtl/log/a/a.B:Z
        //   232: invokestatic    com/alibaba/mtl/log/upload/UploadEngine.getInstance:()Lcom/alibaba/mtl/log/upload/UploadEngine;
        //   235: invokevirtual   com/alibaba/mtl/log/upload/UploadEngine.stop:()V
        //   238: goto            274
        //   241: iload_1        
        //   242: ifne            274
        //   245: iconst_0       
        //   246: putstatic       com/alibaba/mtl/log/a/a.B:Z
        //   249: invokestatic    com/alibaba/mtl/log/upload/UploadEngine.getInstance:()Lcom/alibaba/mtl/log/upload/UploadEngine;
        //   252: invokevirtual   com/alibaba/mtl/log/upload/UploadEngine.start:()V
        //   255: goto            274
        //   258: getstatic       com/alibaba/mtl/log/a/a.B:Z
        //   261: ifeq            274
        //   264: iconst_0       
        //   265: putstatic       com/alibaba/mtl/log/a/a.B:Z
        //   268: invokestatic    com/alibaba/mtl/log/upload/UploadEngine.getInstance:()Lcom/alibaba/mtl/log/upload/UploadEngine;
        //   271: invokevirtual   com/alibaba/mtl/log/upload/UploadEngine.start:()V
        //   274: aload_0        
        //   275: ldc             "cdb"
        //   277: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   280: ifeq            324
        //   283: aload_0        
        //   284: ldc             "cdb"
        //   286: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   289: invokestatic    com/alibaba/mtl/log/a/e.f:()I
        //   292: if_icmple       324
        //   295: invokestatic    com/alibaba/mtl/log/d/s.a:()Lcom/alibaba/mtl/log/d/s;
        //   298: astore_2       
        //   299: new             Lcom/alibaba/mtl/log/a/e$1;
        //   302: astore_0       
        //   303: aload_0        
        //   304: invokespecial   com/alibaba/mtl/log/a/e$1.<init>:()V
        //   307: aload_2        
        //   308: aload_0        
        //   309: invokevirtual   com/alibaba/mtl/log/d/s.b:(Ljava/lang/Runnable;)V
        //   312: goto            324
        //   315: astore_0       
        //   316: ldc             "SystemConfig"
        //   318: ldc             "updateconfig"
        //   320: aload_0        
        //   321: invokestatic    com/alibaba/mtl/log/d/i.a:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Throwable;)V
        //   324: return         
        //   325: astore_2       
        //   326: goto            96
        //   329: astore_2       
        //   330: goto            138
        //   333: astore_2       
        //   334: goto            274
        //   337: astore_0       
        //   338: goto            324
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      50     315    324    Any
        //  54     96     325    329    Any
        //  96     138    329    333    Any
        //  138    207    315    324    Any
        //  207    223    333    337    Any
        //  228    238    333    337    Any
        //  245    255    333    337    Any
        //  258    274    333    337    Any
        //  274    312    337    341    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 173, Size: 173
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
