package com.alipay.sdk.m.n;

import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;

public class d
{
    public static final String a = "RSA";
    
    public static byte[] a(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          5
        //     6: aconst_null    
        //     7: astore          7
        //     9: ldc             "RSA"
        //    11: aload_1        
        //    12: invokestatic    com/alipay/sdk/m/n/d.b:(Ljava/lang/String;Ljava/lang/String;)Ljava/security/PublicKey;
        //    15: astore_1       
        //    16: ldc             "RSA/ECB/PKCS1Padding"
        //    18: invokestatic    javax/crypto/Cipher.getInstance:(Ljava/lang/String;)Ljavax/crypto/Cipher;
        //    21: astore          8
        //    23: aload           8
        //    25: iconst_1       
        //    26: aload_1        
        //    27: invokevirtual   javax/crypto/Cipher.init:(ILjava/security/Key;)V
        //    30: aload_0        
        //    31: ldc             "UTF-8"
        //    33: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    36: astore          9
        //    38: aload           8
        //    40: invokevirtual   javax/crypto/Cipher.getBlockSize:()I
        //    43: istore          4
        //    45: new             Ljava/io/ByteArrayOutputStream;
        //    48: astore_1       
        //    49: aload_1        
        //    50: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    53: iconst_0       
        //    54: istore_2       
        //    55: aload_1        
        //    56: astore_0       
        //    57: iload_2        
        //    58: aload           9
        //    60: arraylength    
        //    61: if_icmpge       113
        //    64: aload_1        
        //    65: astore_0       
        //    66: aload           9
        //    68: arraylength    
        //    69: iload_2        
        //    70: isub           
        //    71: iload           4
        //    73: if_icmpge       87
        //    76: aload_1        
        //    77: astore_0       
        //    78: aload           9
        //    80: arraylength    
        //    81: iload_2        
        //    82: isub           
        //    83: istore_3       
        //    84: goto            90
        //    87: iload           4
        //    89: istore_3       
        //    90: aload_1        
        //    91: astore_0       
        //    92: aload_1        
        //    93: aload           8
        //    95: aload           9
        //    97: iload_2        
        //    98: iload_3        
        //    99: invokevirtual   javax/crypto/Cipher.doFinal:([BII)[B
        //   102: invokevirtual   java/io/ByteArrayOutputStream.write:([B)V
        //   105: iload_2        
        //   106: iload           4
        //   108: iadd           
        //   109: istore_2       
        //   110: goto            55
        //   113: aload_1        
        //   114: astore_0       
        //   115: aload_1        
        //   116: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   119: astore          5
        //   121: aload           5
        //   123: astore_0       
        //   124: aload_1        
        //   125: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   128: aload           5
        //   130: astore_0       
        //   131: goto            182
        //   134: astore          5
        //   136: goto            150
        //   139: astore_0       
        //   140: aload           5
        //   142: astore_1       
        //   143: goto            193
        //   146: astore          5
        //   148: aconst_null    
        //   149: astore_1       
        //   150: aload_1        
        //   151: astore_0       
        //   152: aload           5
        //   154: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   157: aload           6
        //   159: astore_0       
        //   160: aload_1        
        //   161: ifnull          182
        //   164: aload           7
        //   166: astore_0       
        //   167: aload_1        
        //   168: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   171: aload           6
        //   173: astore_0       
        //   174: goto            182
        //   177: astore_1       
        //   178: aload_1        
        //   179: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   182: aload_0        
        //   183: areturn        
        //   184: astore_1       
        //   185: aload_0        
        //   186: astore          5
        //   188: aload_1        
        //   189: astore_0       
        //   190: aload           5
        //   192: astore_1       
        //   193: aload_1        
        //   194: ifnull          209
        //   197: aload_1        
        //   198: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   201: goto            209
        //   204: astore_1       
        //   205: aload_1        
        //   206: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   209: aload_0        
        //   210: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      53     146    150    Ljava/lang/Exception;
        //  9      53     139    146    Any
        //  57     64     134    139    Ljava/lang/Exception;
        //  57     64     184    193    Any
        //  66     76     134    139    Ljava/lang/Exception;
        //  66     76     184    193    Any
        //  78     84     134    139    Ljava/lang/Exception;
        //  78     84     184    193    Any
        //  92     105    134    139    Ljava/lang/Exception;
        //  92     105    184    193    Any
        //  115    121    134    139    Ljava/lang/Exception;
        //  115    121    184    193    Any
        //  124    128    177    182    Ljava/io/IOException;
        //  152    157    184    193    Any
        //  167    171    177    182    Ljava/io/IOException;
        //  197    201    204    209    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    public static PublicKey b(final String s, final String s2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(s).generatePublic((KeySpec)new X509EncodedKeySpec(com.alipay.sdk.m.n.a.a(s2)));
    }
}
