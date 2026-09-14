package com.alipay.sdk.m.z;

public final class b
{
    public static String a(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore          4
        //     9: aconst_null    
        //    10: astore_3       
        //    11: aconst_null    
        //    12: astore_2       
        //    13: new             Ljava/io/File;
        //    16: astore          5
        //    18: aload           5
        //    20: aload_0        
        //    21: aload_1        
        //    22: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    25: aload           5
        //    27: invokevirtual   java/io/File.exists:()Z
        //    30: ifne            35
        //    33: aconst_null    
        //    34: areturn        
        //    35: new             Ljava/io/BufferedReader;
        //    38: astore_0       
        //    39: new             Ljava/io/InputStreamReader;
        //    42: astore          6
        //    44: new             Ljava/io/FileInputStream;
        //    47: astore_1       
        //    48: aload_1        
        //    49: aload           5
        //    51: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    54: aload           6
        //    56: aload_1        
        //    57: ldc             "UTF-8"
        //    59: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    62: aload_0        
        //    63: aload           6
        //    65: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    68: aload_0        
        //    69: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    72: astore_2       
        //    73: aload_0        
        //    74: astore_1       
        //    75: aload_2        
        //    76: ifnull          119
        //    79: aload           4
        //    81: aload_2        
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: pop            
        //    86: goto            68
        //    89: astore_1       
        //    90: goto            100
        //    93: astore_1       
        //    94: goto            113
        //    97: astore_1       
        //    98: aload_2        
        //    99: astore_0       
        //   100: aload_0        
        //   101: ifnull          108
        //   104: aload_0        
        //   105: invokevirtual   java/io/BufferedReader.close:()V
        //   108: aload_1        
        //   109: athrow         
        //   110: astore_0       
        //   111: aload_3        
        //   112: astore_0       
        //   113: aload_0        
        //   114: ifnull          123
        //   117: aload_0        
        //   118: astore_1       
        //   119: aload_1        
        //   120: invokevirtual   java/io/BufferedReader.close:()V
        //   123: aload           4
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: areturn        
        //   129: astore_0       
        //   130: goto            108
        //   133: astore_0       
        //   134: goto            123
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     33     110    113    Ljava/io/IOException;
        //  13     33     97     100    Any
        //  35     68     110    113    Ljava/io/IOException;
        //  35     68     97     100    Any
        //  68     73     93     97     Ljava/io/IOException;
        //  68     73     89     93     Any
        //  79     86     93     97     Ljava/io/IOException;
        //  79     86     89     93     Any
        //  104    108    129    133    Any
        //  119    123    133    137    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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
}
