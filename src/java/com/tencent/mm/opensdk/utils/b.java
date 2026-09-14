package com.tencent.mm.opensdk.utils;

import java.io.File;
import android.net.Uri;
import android.content.ContentResolver;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;
import android.content.Context;

public final class b
{
    public static Context a;
    public static ThreadPoolExecutor b;
    
    static {
        com.tencent.mm.opensdk.utils.b.b = new ThreadPoolExecutor(5, 9, 1L, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingDeque());
    }
    
    public static int a(final ContentResolver p0, final Uri p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: ldc             "getFileSize with content url"
        //     4: invokestatic    com/tencent/mm/opensdk/utils/Log.i:(Ljava/lang/String;Ljava/lang/String;)V
        //     7: aload_0        
        //     8: ifnull          282
        //    11: aload_1        
        //    12: ifnonnull       18
        //    15: goto            282
        //    18: aconst_null    
        //    19: astore          4
        //    21: aconst_null    
        //    22: astore_3       
        //    23: aload_0        
        //    24: aload_1        
        //    25: invokevirtual   android/content/ContentResolver.openInputStream:(Landroid/net/Uri;)Ljava/io/InputStream;
        //    28: astore_0       
        //    29: aload_0        
        //    30: ifnonnull       80
        //    33: aload_0        
        //    34: ifnull          78
        //    37: aload_0        
        //    38: invokevirtual   java/io/InputStream.close:()V
        //    41: goto            78
        //    44: astore_1       
        //    45: new             Ljava/lang/StringBuilder;
        //    48: dup            
        //    49: invokespecial   java/lang/StringBuilder.<init>:()V
        //    52: astore_0       
        //    53: aload_0        
        //    54: ldc             "getFileSize exception: "
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: pop            
        //    60: aload_0        
        //    61: aload_1        
        //    62: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: pop            
        //    69: ldc             "MicroMsg.SDK.Util"
        //    71: aload_0        
        //    72: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    75: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //    78: iconst_0       
        //    79: ireturn        
        //    80: aload_0        
        //    81: astore_3       
        //    82: aload_0        
        //    83: astore          4
        //    85: aload_0        
        //    86: invokevirtual   java/io/InputStream.available:()I
        //    89: istore_2       
        //    90: aload_0        
        //    91: invokevirtual   java/io/InputStream.close:()V
        //    94: goto            131
        //    97: astore_0       
        //    98: new             Ljava/lang/StringBuilder;
        //   101: dup            
        //   102: invokespecial   java/lang/StringBuilder.<init>:()V
        //   105: astore_1       
        //   106: aload_1        
        //   107: ldc             "getFileSize exception: "
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: pop            
        //   113: aload_1        
        //   114: aload_0        
        //   115: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   121: pop            
        //   122: ldc             "MicroMsg.SDK.Util"
        //   124: aload_1        
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   131: iload_2        
        //   132: ireturn        
        //   133: astore_0       
        //   134: goto            235
        //   137: astore_1       
        //   138: aload           4
        //   140: astore_3       
        //   141: new             Ljava/lang/StringBuilder;
        //   144: astore_0       
        //   145: aload           4
        //   147: astore_3       
        //   148: aload_0        
        //   149: invokespecial   java/lang/StringBuilder.<init>:()V
        //   152: aload           4
        //   154: astore_3       
        //   155: aload_0        
        //   156: ldc             "getFileSize fail, "
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: pop            
        //   162: aload           4
        //   164: astore_3       
        //   165: aload_0        
        //   166: aload_1        
        //   167: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: pop            
        //   174: aload           4
        //   176: astore_3       
        //   177: ldc             "MicroMsg.SDK.Util"
        //   179: aload_0        
        //   180: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   183: invokestatic    com/tencent/mm/opensdk/utils/Log.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   186: aload           4
        //   188: ifnull          233
        //   191: aload           4
        //   193: invokevirtual   java/io/InputStream.close:()V
        //   196: goto            233
        //   199: astore_0       
        //   200: new             Ljava/lang/StringBuilder;
        //   203: dup            
        //   204: invokespecial   java/lang/StringBuilder.<init>:()V
        //   207: astore_1       
        //   208: aload_1        
        //   209: ldc             "getFileSize exception: "
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: pop            
        //   215: aload_1        
        //   216: aload_0        
        //   217: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: pop            
        //   224: ldc             "MicroMsg.SDK.Util"
        //   226: aload_1        
        //   227: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   230: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   233: iconst_0       
        //   234: ireturn        
        //   235: aload_3        
        //   236: ifnull          280
        //   239: aload_3        
        //   240: invokevirtual   java/io/InputStream.close:()V
        //   243: goto            280
        //   246: astore_3       
        //   247: new             Ljava/lang/StringBuilder;
        //   250: dup            
        //   251: invokespecial   java/lang/StringBuilder.<init>:()V
        //   254: astore_1       
        //   255: aload_1        
        //   256: ldc             "getFileSize exception: "
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: pop            
        //   262: aload_1        
        //   263: aload_3        
        //   264: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: pop            
        //   271: ldc             "MicroMsg.SDK.Util"
        //   273: aload_1        
        //   274: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   277: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   280: aload_0        
        //   281: athrow         
        //   282: ldc             "MicroMsg.SDK.Util"
        //   284: ldc             "getFileSize fail, resolver or uri is null"
        //   286: invokestatic    com/tencent/mm/opensdk/utils/Log.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   289: iconst_0       
        //   290: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  23     29     137    235    Ljava/lang/Exception;
        //  23     29     133    282    Any
        //  37     41     44     78     Ljava/io/IOException;
        //  85     90     137    235    Ljava/lang/Exception;
        //  85     90     133    282    Any
        //  90     94     97     131    Ljava/io/IOException;
        //  141    145    133    282    Any
        //  148    152    133    282    Any
        //  155    162    133    282    Any
        //  165    174    133    282    Any
        //  177    186    133    282    Any
        //  191    196    199    233    Ljava/io/IOException;
        //  239    243    246    280    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 158, Size: 158
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
    
    public static int a(final String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        final File file = new File(s);
        Label_0063: {
            if (file.exists()) {
                break Label_0063;
            }
            if (com.tencent.mm.opensdk.utils.b.a == null || !s.startsWith("content")) {
                return 0;
            }
            try {
                return a(com.tencent.mm.opensdk.utils.b.a.getContentResolver(), Uri.parse(s));
                return (int)file.length();
            }
            catch (final Exception ex) {
                return 0;
            }
        }
    }
    
    public static int a(final String s, final int n) {
        int int1 = n;
        if (s == null) {
            return int1;
        }
        try {
            if (s.length() <= 0) {
                int1 = n;
            }
            else {
                int1 = Integer.parseInt(s);
            }
            return int1;
        }
        catch (final Exception ex) {
            int1 = n;
            return int1;
        }
    }
    
    public static boolean a(final int n) {
        return n == 36 || n == 46;
    }
    
    public static boolean b(final String s) {
        return s == null || s.length() <= 0;
    }
}
