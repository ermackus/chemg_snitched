package com.alipay.sdk.m.n;

import java.io.IOException;

public class b
{
    public static byte[] a(final byte[] p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: new             Ljava/io/ByteArrayInputStream;
        //     6: astore_2       
        //     7: aload_2        
        //     8: aload_0        
        //     9: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    12: new             Ljava/io/ByteArrayOutputStream;
        //    15: astore          4
        //    17: aload           4
        //    19: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    22: new             Ljava/util/zip/GZIPOutputStream;
        //    25: astore_3       
        //    26: aload_3        
        //    27: aload           4
        //    29: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    32: sipush          4096
        //    35: newarray        B
        //    37: astore_0       
        //    38: aload_2        
        //    39: aload_0        
        //    40: invokevirtual   java/io/ByteArrayInputStream.read:([B)I
        //    43: istore_1       
        //    44: iload_1        
        //    45: iconst_m1      
        //    46: if_icmpeq       59
        //    49: aload_3        
        //    50: aload_0        
        //    51: iconst_0       
        //    52: iload_1        
        //    53: invokevirtual   java/util/zip/GZIPOutputStream.write:([BII)V
        //    56: goto            38
        //    59: aload_3        
        //    60: invokevirtual   java/util/zip/GZIPOutputStream.flush:()V
        //    63: aload_3        
        //    64: invokevirtual   java/util/zip/GZIPOutputStream.finish:()V
        //    67: aload           4
        //    69: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    72: astore_0       
        //    73: aload_2        
        //    74: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    77: aload           4
        //    79: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    82: aload_3        
        //    83: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    86: aload_0        
        //    87: areturn        
        //    88: astore_0       
        //    89: goto            116
        //    92: astore_0       
        //    93: aconst_null    
        //    94: astore_3       
        //    95: goto            116
        //    98: astore_0       
        //    99: aconst_null    
        //   100: astore_3       
        //   101: aconst_null    
        //   102: astore          4
        //   104: goto            116
        //   107: astore_0       
        //   108: aconst_null    
        //   109: astore          4
        //   111: aconst_null    
        //   112: astore_3       
        //   113: aload           5
        //   115: astore_2       
        //   116: aload_2        
        //   117: ifnull          128
        //   120: aload_2        
        //   121: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   124: goto            128
        //   127: astore_2       
        //   128: aload           4
        //   130: ifnull          142
        //   133: aload           4
        //   135: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   138: goto            142
        //   141: astore_2       
        //   142: aload_3        
        //   143: ifnull          150
        //   146: aload_3        
        //   147: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   150: aload_0        
        //   151: athrow         
        //   152: astore_2       
        //   153: goto            77
        //   156: astore_2       
        //   157: goto            82
        //   160: astore_2       
        //   161: goto            86
        //   164: astore_2       
        //   165: goto            150
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      12     107    116    Any
        //  12     22     98     107    Any
        //  22     32     92     98     Any
        //  32     38     88     92     Any
        //  38     44     88     92     Any
        //  49     56     88     92     Any
        //  59     73     88     92     Any
        //  73     77     152    156    Ljava/lang/Exception;
        //  77     82     156    160    Ljava/lang/Exception;
        //  82     86     160    164    Ljava/lang/Exception;
        //  120    124    127    128    Ljava/lang/Exception;
        //  133    138    141    142    Ljava/lang/Exception;
        //  146    150    164    168    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 92, Size: 92
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
    
    public static byte[] b(final byte[] p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: new             Ljava/io/ByteArrayInputStream;
        //     6: astore_3       
        //     7: aload_3        
        //     8: aload_0        
        //     9: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    12: new             Ljava/util/zip/GZIPInputStream;
        //    15: astore_2       
        //    16: aload_2        
        //    17: aload_3        
        //    18: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //    21: sipush          4096
        //    24: newarray        B
        //    26: astore_0       
        //    27: new             Ljava/io/ByteArrayOutputStream;
        //    30: astore          5
        //    32: aload           5
        //    34: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    37: aload_2        
        //    38: aload_0        
        //    39: iconst_0       
        //    40: sipush          4096
        //    43: invokevirtual   java/util/zip/GZIPInputStream.read:([BII)I
        //    46: istore_1       
        //    47: iload_1        
        //    48: iconst_m1      
        //    49: if_icmpeq       63
        //    52: aload           5
        //    54: aload_0        
        //    55: iconst_0       
        //    56: iload_1        
        //    57: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //    60: goto            37
        //    63: aload           5
        //    65: invokevirtual   java/io/ByteArrayOutputStream.flush:()V
        //    68: aload           5
        //    70: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    73: astore_0       
        //    74: aload           5
        //    76: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    79: aload_2        
        //    80: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //    83: aload_3        
        //    84: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    87: aload_0        
        //    88: areturn        
        //    89: astore_0       
        //    90: aload           5
        //    92: astore          4
        //    94: goto            112
        //    97: astore_0       
        //    98: goto            112
        //   101: astore_0       
        //   102: aconst_null    
        //   103: astore_2       
        //   104: goto            112
        //   107: astore_0       
        //   108: aconst_null    
        //   109: astore_2       
        //   110: aconst_null    
        //   111: astore_3       
        //   112: aload           4
        //   114: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   117: aload_2        
        //   118: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   121: aload_3        
        //   122: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   125: aload_0        
        //   126: athrow         
        //   127: astore          4
        //   129: goto            79
        //   132: astore_2       
        //   133: goto            83
        //   136: astore_2       
        //   137: goto            87
        //   140: astore          4
        //   142: goto            117
        //   145: astore_2       
        //   146: goto            121
        //   149: astore_2       
        //   150: goto            125
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      12     107    112    Any
        //  12     21     101    107    Any
        //  21     37     97     101    Any
        //  37     47     89     97     Any
        //  52     60     89     97     Any
        //  63     74     89     97     Any
        //  74     79     127    132    Ljava/lang/Exception;
        //  79     83     132    136    Ljava/lang/Exception;
        //  83     87     136    140    Ljava/lang/Exception;
        //  112    117    140    145    Ljava/lang/Exception;
        //  117    121    145    149    Ljava/lang/Exception;
        //  121    125    149    153    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 82, Size: 82
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
