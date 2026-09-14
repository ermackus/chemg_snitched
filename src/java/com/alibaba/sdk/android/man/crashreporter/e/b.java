package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.FilterInputStream;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.io.UnsupportedEncodingException;
import java.io.Serializable;
import java.io.IOException;

public class b
{
    public static final int H = 0;
    public static final int I = 1;
    public static final int J = 0;
    public static final int K = 2;
    public static final int L = 4;
    public static final int M = 8;
    public static final int N = 32;
    private static final int O = 76;
    public static final int URL_SAFE = 16;
    private static final byte a = 61;
    private static final byte[] a;
    private static final byte b = 10;
    private static final byte[] b;
    private static final byte c = -5;
    static final boolean c;
    private static final byte[] c;
    private static final byte d = -1;
    private static final byte[] d;
    private static final byte[] e;
    private static final byte[] f;
    private static final String w = "US-ASCII";
    
    static {
        c = (b.class.desiredAssertionStatus() ^ true);
        a = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        b = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        c = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        d = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        e = new byte[] { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
        f = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
    }
    
    private b() {
    }
    
    private static int a(final byte[] array, int n, final byte[] array2, final int n2, int n3) {
        if (array == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (array2 != null) {
            if (n >= 0) {
                final int n4 = n + 3;
                if (n4 < array.length) {
                    if (n2 >= 0) {
                        final int n5 = n2 + 2;
                        if (n5 < array2.length) {
                            final byte[] b = b(n3);
                            n3 = n + 2;
                            if (array[n3] == 61) {
                                n3 = b[array[n]];
                                array2[n2] = (byte)(((b[array[n + 1]] & 0xFF) << 12 | (n3 & 0xFF) << 18) >>> 16);
                                return 1;
                            }
                            if (array[n4] == 61) {
                                final byte b2 = b[array[n]];
                                n = b[array[n + 1]];
                                n = ((b[array[n3]] & 0xFF) << 6 | ((n & 0xFF) << 12 | (b2 & 0xFF) << 18));
                                array2[n2] = (byte)(n >>> 16);
                                array2[n2 + 1] = (byte)(n >>> 8);
                                return 2;
                            }
                            final byte b3 = b[array[n]];
                            n = b[array[n + 1]];
                            n3 = b[array[n3]];
                            n = ((b[array[n4]] & 0xFF) | ((n & 0xFF) << 12 | (b3 & 0xFF) << 18 | (n3 & 0xFF) << 6));
                            array2[n2] = (byte)(n >> 16);
                            array2[n2 + 1] = (byte)(n >> 8);
                            array2[n5] = (byte)n;
                            return 3;
                        }
                    }
                    throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[] { array2.length, n2 }));
                }
            }
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[] { array.length, n }));
        }
        throw new NullPointerException("Destination array was null.");
    }
    
    public static Object a(final String s) throws IOException, ClassNotFoundException {
        return a(s, 0, null);
    }
    
    public static Object a(final String p0, final int p1, final ClassLoader p2) throws IOException, ClassNotFoundException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iload_1        
        //     2: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.decode:(Ljava/lang/String;I)[B
        //     5: astore          9
        //     7: aconst_null    
        //     8: astore          8
        //    10: aconst_null    
        //    11: astore          7
        //    13: aconst_null    
        //    14: astore          5
        //    16: aconst_null    
        //    17: astore          6
        //    19: aconst_null    
        //    20: astore          4
        //    22: aconst_null    
        //    23: astore_0       
        //    24: new             Ljava/io/ByteArrayInputStream;
        //    27: astore_3       
        //    28: aload_3        
        //    29: aload           9
        //    31: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    34: aload_2        
        //    35: ifnonnull       74
        //    38: aload_0        
        //    39: astore          4
        //    41: aload           8
        //    43: astore          6
        //    45: aload           7
        //    47: astore          5
        //    49: new             Ljava/io/ObjectInputStream;
        //    52: astore_2       
        //    53: aload_0        
        //    54: astore          4
        //    56: aload           8
        //    58: astore          6
        //    60: aload           7
        //    62: astore          5
        //    64: aload_2        
        //    65: aload_3        
        //    66: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    69: aload_2        
        //    70: astore_0       
        //    71: goto            95
        //    74: aload_0        
        //    75: astore          4
        //    77: aload           8
        //    79: astore          6
        //    81: aload           7
        //    83: astore          5
        //    85: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$1;
        //    88: dup            
        //    89: aload_3        
        //    90: aload_2        
        //    91: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$1.<init>:(Ljava/io/InputStream;Ljava/lang/ClassLoader;)V
        //    94: astore_0       
        //    95: aload_0        
        //    96: astore          4
        //    98: aload_0        
        //    99: astore          6
        //   101: aload_0        
        //   102: astore          5
        //   104: aload_0        
        //   105: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //   108: astore_2       
        //   109: aload_3        
        //   110: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   113: aload_0        
        //   114: invokevirtual   java/io/ObjectInputStream.close:()V
        //   117: aload_2        
        //   118: areturn        
        //   119: astore_0       
        //   120: aload_3        
        //   121: astore_2       
        //   122: aload           4
        //   124: astore_3       
        //   125: goto            192
        //   128: astore_0       
        //   129: aload           6
        //   131: astore_2       
        //   132: aload_3        
        //   133: astore          4
        //   135: aload_0        
        //   136: astore_3       
        //   137: aload           4
        //   139: astore_0       
        //   140: goto            173
        //   143: astore_0       
        //   144: aload           5
        //   146: astore_2       
        //   147: aload_3        
        //   148: astore          4
        //   150: aload_0        
        //   151: astore_3       
        //   152: aload           4
        //   154: astore_0       
        //   155: goto            181
        //   158: astore_0       
        //   159: aconst_null    
        //   160: astore_3       
        //   161: aload           4
        //   163: astore_2       
        //   164: goto            192
        //   167: astore_3       
        //   168: aconst_null    
        //   169: astore_2       
        //   170: aload           5
        //   172: astore_0       
        //   173: aload_3        
        //   174: athrow         
        //   175: astore_3       
        //   176: aconst_null    
        //   177: astore_2       
        //   178: aload           6
        //   180: astore_0       
        //   181: aload_3        
        //   182: athrow         
        //   183: astore          4
        //   185: aload_2        
        //   186: astore_3       
        //   187: aload_0        
        //   188: astore_2       
        //   189: aload           4
        //   191: astore_0       
        //   192: aload_2        
        //   193: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   196: aload_3        
        //   197: invokevirtual   java/io/ObjectInputStream.close:()V
        //   200: aload_0        
        //   201: athrow         
        //   202: astore_3       
        //   203: goto            113
        //   206: astore_0       
        //   207: goto            117
        //   210: astore_2       
        //   211: goto            196
        //   214: astore_2       
        //   215: goto            200
        //    Exceptions:
        //  throws java.io.IOException
        //  throws java.lang.ClassNotFoundException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  24     34     175    181    Ljava/io/IOException;
        //  24     34     167    173    Ljava/lang/ClassNotFoundException;
        //  24     34     158    167    Any
        //  49     53     143    158    Ljava/io/IOException;
        //  49     53     128    143    Ljava/lang/ClassNotFoundException;
        //  49     53     119    128    Any
        //  64     69     143    158    Ljava/io/IOException;
        //  64     69     128    143    Ljava/lang/ClassNotFoundException;
        //  64     69     119    128    Any
        //  85     95     143    158    Ljava/io/IOException;
        //  85     95     128    143    Ljava/lang/ClassNotFoundException;
        //  85     95     119    128    Any
        //  104    109    143    158    Ljava/io/IOException;
        //  104    109    128    143    Ljava/lang/ClassNotFoundException;
        //  104    109    119    128    Any
        //  109    113    202    206    Ljava/lang/Exception;
        //  113    117    206    210    Ljava/lang/Exception;
        //  173    175    183    192    Any
        //  181    183    183    192    Any
        //  192    196    210    214    Ljava/lang/Exception;
        //  196    200    214    218    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 137, Size: 137
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
    
    public static String a(final Serializable s) throws IOException {
        return a(s, 0);
    }
    
    public static String a(final Serializable p0, final int p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          321
        //     4: aconst_null    
        //     5: astore          4
        //     7: aconst_null    
        //     8: astore          7
        //    10: aconst_null    
        //    11: astore          6
        //    13: aconst_null    
        //    14: astore          10
        //    16: new             Ljava/io/ByteArrayOutputStream;
        //    19: astore_2       
        //    20: aload_2        
        //    21: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    24: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$b;
        //    27: astore          9
        //    29: aload           9
        //    31: aload_2        
        //    32: iload_1        
        //    33: iconst_1       
        //    34: ior            
        //    35: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$b.<init>:(Ljava/io/OutputStream;I)V
        //    38: iload_1        
        //    39: iconst_2       
        //    40: iand           
        //    41: ifeq            132
        //    44: new             Ljava/util/zip/GZIPOutputStream;
        //    47: astore_3       
        //    48: aload_3        
        //    49: aload           9
        //    51: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    54: aload           10
        //    56: astore          7
        //    58: aload_3        
        //    59: astore          5
        //    61: aload           4
        //    63: astore          8
        //    65: aload_3        
        //    66: astore          6
        //    68: new             Ljava/io/ObjectOutputStream;
        //    71: astore          11
        //    73: aload           10
        //    75: astore          7
        //    77: aload_3        
        //    78: astore          5
        //    80: aload           4
        //    82: astore          8
        //    84: aload_3        
        //    85: astore          6
        //    87: aload           11
        //    89: aload_3        
        //    90: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    93: aload           11
        //    95: astore          4
        //    97: goto            145
        //   100: astore_0       
        //   101: aload           7
        //   103: astore          6
        //   105: aload           9
        //   107: astore          4
        //   109: aload           5
        //   111: astore_3       
        //   112: goto            301
        //   115: astore          5
        //   117: aload_2        
        //   118: astore_0       
        //   119: aload           8
        //   121: astore_2       
        //   122: aload           9
        //   124: astore          4
        //   126: aload           6
        //   128: astore_3       
        //   129: goto            284
        //   132: new             Ljava/io/ObjectOutputStream;
        //   135: dup            
        //   136: aload           9
        //   138: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   141: astore          4
        //   143: aconst_null    
        //   144: astore_3       
        //   145: aload           4
        //   147: astore          7
        //   149: aload_3        
        //   150: astore          5
        //   152: aload           4
        //   154: astore          8
        //   156: aload_3        
        //   157: astore          6
        //   159: aload           4
        //   161: aload_0        
        //   162: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //   165: aload           4
        //   167: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   170: aload_3        
        //   171: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   174: aload           9
        //   176: invokevirtual   java/io/OutputStream.close:()V
        //   179: aload_2        
        //   180: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   183: new             Ljava/lang/String;
        //   186: dup            
        //   187: aload_2        
        //   188: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   191: ldc             "US-ASCII"
        //   193: invokespecial   java/lang/String.<init>:([BLjava/lang/String;)V
        //   196: astore_0       
        //   197: aload_0        
        //   198: areturn        
        //   199: astore_0       
        //   200: new             Ljava/lang/String;
        //   203: dup            
        //   204: aload_2        
        //   205: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   208: invokespecial   java/lang/String.<init>:([B)V
        //   211: areturn        
        //   212: astore_0       
        //   213: aconst_null    
        //   214: astore_3       
        //   215: aload           9
        //   217: astore          4
        //   219: goto            301
        //   222: astore          5
        //   224: aconst_null    
        //   225: astore_3       
        //   226: aload_2        
        //   227: astore_0       
        //   228: aconst_null    
        //   229: astore_2       
        //   230: aload           9
        //   232: astore          4
        //   234: goto            284
        //   237: astore_0       
        //   238: aconst_null    
        //   239: astore_3       
        //   240: aconst_null    
        //   241: astore          4
        //   243: goto            301
        //   246: astore          5
        //   248: aconst_null    
        //   249: astore_3       
        //   250: aconst_null    
        //   251: astore          4
        //   253: aload_2        
        //   254: astore_0       
        //   255: aload           4
        //   257: astore_2       
        //   258: goto            284
        //   261: astore_0       
        //   262: aconst_null    
        //   263: astore_3       
        //   264: aconst_null    
        //   265: astore_2       
        //   266: aload_2        
        //   267: astore          4
        //   269: goto            301
        //   272: astore          5
        //   274: aconst_null    
        //   275: astore_3       
        //   276: aconst_null    
        //   277: astore_2       
        //   278: aload_2        
        //   279: astore          4
        //   281: aload           7
        //   283: astore_0       
        //   284: aload           5
        //   286: athrow         
        //   287: astore          7
        //   289: aload_0        
        //   290: astore          5
        //   292: aload_2        
        //   293: astore          6
        //   295: aload           7
        //   297: astore_0       
        //   298: aload           5
        //   300: astore_2       
        //   301: aload           6
        //   303: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   306: aload_3        
        //   307: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   310: aload           4
        //   312: invokevirtual   java/io/OutputStream.close:()V
        //   315: aload_2        
        //   316: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   319: aload_0        
        //   320: athrow         
        //   321: new             Ljava/lang/NullPointerException;
        //   324: dup            
        //   325: ldc_w           "Cannot serialize a null object."
        //   328: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   331: athrow         
        //   332: astore_0       
        //   333: goto            170
        //   336: astore_0       
        //   337: goto            174
        //   340: astore_0       
        //   341: goto            179
        //   344: astore_0       
        //   345: goto            183
        //   348: astore          5
        //   350: goto            306
        //   353: astore_3       
        //   354: goto            310
        //   357: astore_3       
        //   358: goto            315
        //   361: astore_2       
        //   362: goto            319
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  16     24     272    284    Ljava/io/IOException;
        //  16     24     261    272    Any
        //  24     38     246    261    Ljava/io/IOException;
        //  24     38     237    246    Any
        //  44     54     222    237    Ljava/io/IOException;
        //  44     54     212    222    Any
        //  68     73     115    132    Ljava/io/IOException;
        //  68     73     100    115    Any
        //  87     93     115    132    Ljava/io/IOException;
        //  87     93     100    115    Any
        //  132    143    222    237    Ljava/io/IOException;
        //  132    143    212    222    Any
        //  159    165    115    132    Ljava/io/IOException;
        //  159    165    100    115    Any
        //  165    170    332    336    Ljava/lang/Exception;
        //  170    174    336    340    Ljava/lang/Exception;
        //  174    179    340    344    Ljava/lang/Exception;
        //  179    183    344    348    Ljava/lang/Exception;
        //  183    197    199    212    Ljava/io/UnsupportedEncodingException;
        //  284    287    287    301    Any
        //  301    306    348    353    Ljava/lang/Exception;
        //  306    310    353    357    Ljava/lang/Exception;
        //  310    315    357    361    Ljava/lang/Exception;
        //  315    319    361    365    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 206, Size: 206
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
    
    public static String a(final byte[] array) {
        String a;
        try {
            a = a(array, 0, array.length, 0);
        }
        catch (final IOException ex) {
            if (!com.alibaba.sdk.android.man.crashreporter.e.b.c) {
                throw new AssertionError((Object)ex.getMessage());
            }
            a = null;
        }
        if (!com.alibaba.sdk.android.man.crashreporter.e.b.c && a == null) {
            throw new AssertionError();
        }
        return a;
    }
    
    public static String a(final byte[] array, final int n) throws IOException {
        return a(array, 0, array.length, n);
    }
    
    public static String a(final byte[] array, final int n, final int n2) {
        String a;
        try {
            a = a(array, n, n2, 0);
        }
        catch (final IOException ex) {
            if (!com.alibaba.sdk.android.man.crashreporter.e.b.c) {
                throw new AssertionError((Object)ex.getMessage());
            }
            a = null;
        }
        if (!com.alibaba.sdk.android.man.crashreporter.e.b.c && a == null) {
            throw new AssertionError();
        }
        return a;
    }
    
    public static String a(byte[] a, final int n, final int n2, final int n3) throws IOException {
        a = a(a, n, n2, n3);
        try {
            return new String(a, "US-ASCII");
        }
        catch (final UnsupportedEncodingException ex) {
            return new String(a);
        }
    }
    
    public static void a(final ByteBuffer byteBuffer, final ByteBuffer byteBuffer2) {
        final byte[] array = new byte[3];
        final byte[] array2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            final int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(array, 0, min);
            a(array2, array, min, 0);
            byteBuffer2.put(array2);
        }
    }
    
    public static void a(final ByteBuffer byteBuffer, final CharBuffer charBuffer) {
        final byte[] array = new byte[3];
        final byte[] array2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            final int min = Math.min(3, byteBuffer.remaining());
            int i = 0;
            byteBuffer.get(array, 0, min);
            a(array2, array, min, 0);
            while (i < 4) {
                charBuffer.put((char)(array2[i] & 0xFF));
                ++i;
            }
        }
    }
    
    public static void a(final byte[] p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          82
        //     4: aconst_null    
        //     5: astore          4
        //     7: aconst_null    
        //     8: astore          5
        //    10: aload           5
        //    12: astore_2       
        //    13: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$b;
        //    16: astore_3       
        //    17: aload           5
        //    19: astore_2       
        //    20: new             Ljava/io/FileOutputStream;
        //    23: astore          6
        //    25: aload           5
        //    27: astore_2       
        //    28: aload           6
        //    30: aload_1        
        //    31: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    34: aload           5
        //    36: astore_2       
        //    37: aload_3        
        //    38: aload           6
        //    40: iconst_1       
        //    41: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$b.<init>:(Ljava/io/OutputStream;I)V
        //    44: aload_3        
        //    45: aload_0        
        //    46: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.write:([B)V
        //    49: aload_3        
        //    50: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //    53: return         
        //    54: astore_0       
        //    55: aload_3        
        //    56: astore_2       
        //    57: goto            76
        //    60: astore_0       
        //    61: aload_3        
        //    62: astore_2       
        //    63: goto            74
        //    66: astore_0       
        //    67: goto            76
        //    70: astore_0       
        //    71: aload           4
        //    73: astore_2       
        //    74: aload_0        
        //    75: athrow         
        //    76: aload_2        
        //    77: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //    80: aload_0        
        //    81: athrow         
        //    82: new             Ljava/lang/NullPointerException;
        //    85: dup            
        //    86: ldc_w           "Data to encode was null."
        //    89: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    92: athrow         
        //    93: astore_0       
        //    94: goto            53
        //    97: astore_1       
        //    98: goto            80
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     17     70     74     Ljava/io/IOException;
        //  13     17     66     70     Any
        //  20     25     70     74     Ljava/io/IOException;
        //  20     25     66     70     Any
        //  28     34     70     74     Ljava/io/IOException;
        //  28     34     66     70     Any
        //  37     44     70     74     Ljava/io/IOException;
        //  37     44     66     70     Any
        //  44     49     60     66     Ljava/io/IOException;
        //  44     49     54     60     Any
        //  49     53     93     97     Ljava/lang/Exception;
        //  74     76     66     70     Any
        //  76     80     97     101    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    private static final byte[] a(final int n) {
        if ((n & 0x10) == 0x10) {
            return com.alibaba.sdk.android.man.crashreporter.e.b.c;
        }
        if ((n & 0x20) == 0x20) {
            return com.alibaba.sdk.android.man.crashreporter.e.b.e;
        }
        return com.alibaba.sdk.android.man.crashreporter.e.b.a;
    }
    
    public static byte[] a(final String s) throws IOException {
        return decode(s, 0);
    }
    
    public static byte[] a(byte[] a) {
        Label_0023: {
            try {
                a = a(a, 0, a.length, 0);
            }
            catch (final IOException ex) {
                if (!com.alibaba.sdk.android.man.crashreporter.e.b.c) {
                    break Label_0023;
                }
                a = null;
            }
            return a;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("IOExceptions only come from GZipping, which is turned off: ");
        final IOException ex;
        sb.append(ex.getMessage());
        throw new AssertionError((Object)sb.toString());
    }
    
    public static byte[] a(final byte[] p0, final int p1, final int p2, final int p3) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          551
        //     4: iload_1        
        //     5: iflt            517
        //     8: iload_2        
        //     9: iflt            483
        //    12: iload_1        
        //    13: iload_2        
        //    14: iadd           
        //    15: aload_0        
        //    16: arraylength    
        //    17: if_icmpgt       443
        //    20: iload_3        
        //    21: iconst_2       
        //    22: iand           
        //    23: ifeq            236
        //    26: aconst_null    
        //    27: astore          9
        //    29: aconst_null    
        //    30: astore          11
        //    32: new             Ljava/io/ByteArrayOutputStream;
        //    35: astore          10
        //    37: aload           10
        //    39: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    42: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$b;
        //    45: astore          11
        //    47: aload           11
        //    49: aload           10
        //    51: iload_3        
        //    52: iconst_1       
        //    53: ior            
        //    54: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$b.<init>:(Ljava/io/OutputStream;I)V
        //    57: new             Ljava/util/zip/GZIPOutputStream;
        //    60: astore          12
        //    62: aload           12
        //    64: aload           11
        //    66: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    69: aload           12
        //    71: aload_0        
        //    72: iload_1        
        //    73: iload_2        
        //    74: invokevirtual   java/util/zip/GZIPOutputStream.write:([BII)V
        //    77: aload           12
        //    79: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    82: aload           12
        //    84: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    87: aload           11
        //    89: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //    92: aload           10
        //    94: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    97: aload           10
        //    99: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   102: areturn        
        //   103: astore_0       
        //   104: aload           11
        //   106: astore          9
        //   108: aload           12
        //   110: astore          11
        //   112: goto            215
        //   115: astore          9
        //   117: aload           12
        //   119: astore_0       
        //   120: goto            164
        //   123: astore_0       
        //   124: aload           9
        //   126: astore          12
        //   128: aload           11
        //   130: astore          9
        //   132: goto            219
        //   135: astore          9
        //   137: aconst_null    
        //   138: astore_0       
        //   139: goto            164
        //   142: astore_0       
        //   143: aconst_null    
        //   144: astore          11
        //   146: aload           9
        //   148: astore          12
        //   150: aload           11
        //   152: astore          9
        //   154: goto            219
        //   157: astore          9
        //   159: aconst_null    
        //   160: astore          11
        //   162: aconst_null    
        //   163: astore_0       
        //   164: aload           9
        //   166: astore          12
        //   168: aload           11
        //   170: astore          9
        //   172: goto            204
        //   175: astore_0       
        //   176: aconst_null    
        //   177: astore          10
        //   179: aconst_null    
        //   180: astore          11
        //   182: aload           9
        //   184: astore          12
        //   186: aload           11
        //   188: astore          9
        //   190: goto            219
        //   193: astore          12
        //   195: aconst_null    
        //   196: astore          9
        //   198: aconst_null    
        //   199: astore_0       
        //   200: aload           11
        //   202: astore          10
        //   204: aload           12
        //   206: athrow         
        //   207: astore          12
        //   209: aload_0        
        //   210: astore          11
        //   212: aload           12
        //   214: astore_0       
        //   215: aload           11
        //   217: astore          12
        //   219: aload           12
        //   221: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   224: aload           9
        //   226: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //   229: aload           10
        //   231: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   234: aload_0        
        //   235: athrow         
        //   236: iload_3        
        //   237: bipush          8
        //   239: iand           
        //   240: ifeq            249
        //   243: iconst_1       
        //   244: istore          6
        //   246: goto            252
        //   249: iconst_0       
        //   250: istore          6
        //   252: iload_2        
        //   253: iconst_3       
        //   254: idiv           
        //   255: istore          5
        //   257: iload_2        
        //   258: iconst_3       
        //   259: irem           
        //   260: ifle            269
        //   263: iconst_4       
        //   264: istore          4
        //   266: goto            272
        //   269: iconst_0       
        //   270: istore          4
        //   272: iload           5
        //   274: iconst_4       
        //   275: imul           
        //   276: iload           4
        //   278: iadd           
        //   279: istore          4
        //   281: iload           4
        //   283: istore          7
        //   285: iload           6
        //   287: ifeq            300
        //   290: iload           4
        //   292: iload           4
        //   294: bipush          76
        //   296: idiv           
        //   297: iadd           
        //   298: istore          7
        //   300: iload           7
        //   302: newarray        B
        //   304: astore          9
        //   306: iconst_0       
        //   307: istore          8
        //   309: iconst_0       
        //   310: istore          4
        //   312: iconst_0       
        //   313: istore          5
        //   315: iload           8
        //   317: iload_2        
        //   318: iconst_2       
        //   319: isub           
        //   320: if_icmpge       380
        //   323: aload_0        
        //   324: iload           8
        //   326: iload_1        
        //   327: iadd           
        //   328: iconst_3       
        //   329: aload           9
        //   331: iload           4
        //   333: iload_3        
        //   334: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.a:([BII[BII)[B
        //   337: pop            
        //   338: iinc            5, 4
        //   341: iload           6
        //   343: ifeq            371
        //   346: iload           5
        //   348: bipush          76
        //   350: if_icmplt       371
        //   353: aload           9
        //   355: iload           4
        //   357: iconst_4       
        //   358: iadd           
        //   359: bipush          10
        //   361: bastore        
        //   362: iinc            4, 1
        //   365: iconst_0       
        //   366: istore          5
        //   368: goto            371
        //   371: iinc            8, 3
        //   374: iinc            4, 4
        //   377: goto            315
        //   380: iload           4
        //   382: istore          5
        //   384: iload           8
        //   386: iload_2        
        //   387: if_icmpge       414
        //   390: aload_0        
        //   391: iload           8
        //   393: iload_1        
        //   394: iadd           
        //   395: iload_2        
        //   396: iload           8
        //   398: isub           
        //   399: aload           9
        //   401: iload           4
        //   403: iload_3        
        //   404: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.a:([BII[BII)[B
        //   407: pop            
        //   408: iload           4
        //   410: iconst_4       
        //   411: iadd           
        //   412: istore          5
        //   414: iload           5
        //   416: iload           7
        //   418: iconst_1       
        //   419: isub           
        //   420: if_icmpgt       440
        //   423: iload           5
        //   425: newarray        B
        //   427: astore_0       
        //   428: aload           9
        //   430: iconst_0       
        //   431: aload_0        
        //   432: iconst_0       
        //   433: iload           5
        //   435: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   438: aload_0        
        //   439: areturn        
        //   440: aload           9
        //   442: areturn        
        //   443: new             Ljava/lang/IllegalArgumentException;
        //   446: dup            
        //   447: ldc_w           "Cannot have offset of %d and length of %d with array of length %d"
        //   450: iconst_3       
        //   451: anewarray       Ljava/lang/Object;
        //   454: dup            
        //   455: iconst_0       
        //   456: iload_1        
        //   457: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   460: aastore        
        //   461: dup            
        //   462: iconst_1       
        //   463: iload_2        
        //   464: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   467: aastore        
        //   468: dup            
        //   469: iconst_2       
        //   470: aload_0        
        //   471: arraylength    
        //   472: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   475: aastore        
        //   476: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   479: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   482: athrow         
        //   483: new             Ljava/lang/StringBuilder;
        //   486: dup            
        //   487: invokespecial   java/lang/StringBuilder.<init>:()V
        //   490: astore_0       
        //   491: aload_0        
        //   492: ldc_w           "Cannot have length offset: "
        //   495: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   498: pop            
        //   499: aload_0        
        //   500: iload_2        
        //   501: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   504: pop            
        //   505: new             Ljava/lang/IllegalArgumentException;
        //   508: dup            
        //   509: aload_0        
        //   510: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   513: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   516: athrow         
        //   517: new             Ljava/lang/StringBuilder;
        //   520: dup            
        //   521: invokespecial   java/lang/StringBuilder.<init>:()V
        //   524: astore_0       
        //   525: aload_0        
        //   526: ldc_w           "Cannot have negative offset: "
        //   529: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   532: pop            
        //   533: aload_0        
        //   534: iload_1        
        //   535: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   538: pop            
        //   539: new             Ljava/lang/IllegalArgumentException;
        //   542: dup            
        //   543: aload_0        
        //   544: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   547: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   550: athrow         
        //   551: new             Ljava/lang/NullPointerException;
        //   554: dup            
        //   555: ldc_w           "Cannot serialize a null array."
        //   558: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   561: athrow         
        //   562: astore_0       
        //   563: goto            87
        //   566: astore_0       
        //   567: goto            92
        //   570: astore_0       
        //   571: goto            97
        //   574: astore          11
        //   576: goto            224
        //   579: astore          9
        //   581: goto            229
        //   584: astore          9
        //   586: goto            234
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     42     193    204    Ljava/io/IOException;
        //  32     42     175    193    Any
        //  42     57     157    164    Ljava/io/IOException;
        //  42     57     142    157    Any
        //  57     69     135    142    Ljava/io/IOException;
        //  57     69     123    135    Any
        //  69     82     115    123    Ljava/io/IOException;
        //  69     82     103    115    Any
        //  82     87     562    566    Ljava/lang/Exception;
        //  87     92     566    570    Ljava/lang/Exception;
        //  92     97     570    574    Ljava/lang/Exception;
        //  204    207    207    215    Any
        //  219    224    574    579    Ljava/lang/Exception;
        //  224    229    579    584    Ljava/lang/Exception;
        //  229    234    584    589    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 322, Size: 322
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
    
    private static byte[] a(final byte[] array, int n, final int n2, final byte[] array2, final int n3, int n4) {
        final byte[] a = a(n4);
        int n5 = 0;
        if (n2 > 0) {
            n4 = array[n] << 24 >>> 8;
        }
        else {
            n4 = 0;
        }
        int n6;
        if (n2 > 1) {
            n6 = array[n + 1] << 24 >>> 16;
        }
        else {
            n6 = 0;
        }
        if (n2 > 2) {
            n5 = array[n + 2] << 24 >>> 24;
        }
        n = (n4 | n6 | n5);
        if (n2 == 1) {
            array2[n3] = a[n >>> 18];
            array2[n3 + 1] = a[n >>> 12 & 0x3F];
            array2[n3 + 3] = (array2[n3 + 2] = 61);
            return array2;
        }
        if (n2 == 2) {
            array2[n3] = a[n >>> 18];
            array2[n3 + 1] = a[n >>> 12 & 0x3F];
            array2[n3 + 2] = a[n >>> 6 & 0x3F];
            array2[n3 + 3] = 61;
            return array2;
        }
        if (n2 != 3) {
            return array2;
        }
        array2[n3] = a[n >>> 18];
        array2[n3 + 1] = a[n >>> 12 & 0x3F];
        array2[n3 + 2] = a[n >>> 6 & 0x3F];
        array2[n3 + 3] = a[n & 0x3F];
        return array2;
    }
    
    private static byte[] a(final byte[] array, final byte[] array2, final int n, final int n2) {
        a(array2, 0, n, array, 0, n2);
        return array;
    }
    
    public static String b(final byte[] array) {
        if (array != null && array.length > 0) {
            return a(array);
        }
        return "";
    }
    
    private static final byte[] b(final int n) {
        if ((n & 0x10) == 0x10) {
            return com.alibaba.sdk.android.man.crashreporter.e.b.d;
        }
        if ((n & 0x20) == 0x20) {
            return com.alibaba.sdk.android.man.crashreporter.e.b.f;
        }
        return com.alibaba.sdk.android.man.crashreporter.e.b.b;
    }
    
    public static byte[] b(final String p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload           5
        //     8: astore_3       
        //     9: new             Ljava/io/File;
        //    12: astore          6
        //    14: aload           5
        //    16: astore_3       
        //    17: aload           6
        //    19: aload_0        
        //    20: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    23: aload           5
        //    25: astore_3       
        //    26: aload           6
        //    28: invokevirtual   java/io/File.length:()J
        //    31: ldc2_w          2147483647
        //    34: lcmp           
        //    35: ifgt            164
        //    38: aload           5
        //    40: astore_3       
        //    41: aload           6
        //    43: invokevirtual   java/io/File.length:()J
        //    46: l2i            
        //    47: newarray        B
        //    49: astore          7
        //    51: aload           5
        //    53: astore_3       
        //    54: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$a;
        //    57: astore_0       
        //    58: aload           5
        //    60: astore_3       
        //    61: new             Ljava/io/BufferedInputStream;
        //    64: astore          9
        //    66: aload           5
        //    68: astore_3       
        //    69: new             Ljava/io/FileInputStream;
        //    72: astore          8
        //    74: aload           5
        //    76: astore_3       
        //    77: aload           8
        //    79: aload           6
        //    81: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    84: aload           5
        //    86: astore_3       
        //    87: aload           9
        //    89: aload           8
        //    91: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    94: aload           5
        //    96: astore_3       
        //    97: aload_0        
        //    98: aload           9
        //   100: iconst_0       
        //   101: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$a.<init>:(Ljava/io/InputStream;I)V
        //   104: iconst_0       
        //   105: istore_1       
        //   106: aload_0        
        //   107: aload           7
        //   109: iload_1        
        //   110: sipush          4096
        //   113: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.read:([BII)I
        //   116: istore_2       
        //   117: iload_2        
        //   118: iflt            128
        //   121: iload_1        
        //   122: iload_2        
        //   123: iadd           
        //   124: istore_1       
        //   125: goto            106
        //   128: iload_1        
        //   129: newarray        B
        //   131: astore_3       
        //   132: aload           7
        //   134: iconst_0       
        //   135: aload_3        
        //   136: iconst_0       
        //   137: iload_1        
        //   138: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   141: aload_0        
        //   142: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.close:()V
        //   145: aload_3        
        //   146: areturn        
        //   147: astore          4
        //   149: aload_0        
        //   150: astore_3       
        //   151: goto            253
        //   154: astore          4
        //   156: aload_0        
        //   157: astore_3       
        //   158: aload           4
        //   160: astore_0       
        //   161: goto            251
        //   164: aload           5
        //   166: astore_3       
        //   167: new             Ljava/io/IOException;
        //   170: astore_0       
        //   171: aload           5
        //   173: astore_3       
        //   174: new             Ljava/lang/StringBuilder;
        //   177: astore          7
        //   179: aload           5
        //   181: astore_3       
        //   182: aload           7
        //   184: invokespecial   java/lang/StringBuilder.<init>:()V
        //   187: aload           5
        //   189: astore_3       
        //   190: aload           7
        //   192: ldc_w           "File is too big for this convenience method ("
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: pop            
        //   199: aload           5
        //   201: astore_3       
        //   202: aload           7
        //   204: aload           6
        //   206: invokevirtual   java/io/File.length:()J
        //   209: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   212: pop            
        //   213: aload           5
        //   215: astore_3       
        //   216: aload           7
        //   218: ldc_w           " bytes)."
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: pop            
        //   225: aload           5
        //   227: astore_3       
        //   228: aload_0        
        //   229: aload           7
        //   231: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   234: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   237: aload           5
        //   239: astore_3       
        //   240: aload_0        
        //   241: athrow         
        //   242: astore          4
        //   244: goto            253
        //   247: astore_0       
        //   248: aload           4
        //   250: astore_3       
        //   251: aload_0        
        //   252: athrow         
        //   253: aload_3        
        //   254: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.close:()V
        //   257: aload           4
        //   259: athrow         
        //   260: astore_0       
        //   261: goto            145
        //   264: astore_0       
        //   265: goto            257
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      14     247    251    Ljava/io/IOException;
        //  9      14     242    247    Any
        //  17     23     247    251    Ljava/io/IOException;
        //  17     23     242    247    Any
        //  26     38     247    251    Ljava/io/IOException;
        //  26     38     242    247    Any
        //  41     51     247    251    Ljava/io/IOException;
        //  41     51     242    247    Any
        //  54     58     247    251    Ljava/io/IOException;
        //  54     58     242    247    Any
        //  61     66     247    251    Ljava/io/IOException;
        //  61     66     242    247    Any
        //  69     74     247    251    Ljava/io/IOException;
        //  69     74     242    247    Any
        //  77     84     247    251    Ljava/io/IOException;
        //  77     84     242    247    Any
        //  87     94     247    251    Ljava/io/IOException;
        //  87     94     242    247    Any
        //  97     104    247    251    Ljava/io/IOException;
        //  97     104    242    247    Any
        //  106    117    154    164    Ljava/io/IOException;
        //  106    117    147    154    Any
        //  128    141    154    164    Ljava/io/IOException;
        //  128    141    147    154    Any
        //  141    145    260    264    Ljava/lang/Exception;
        //  167    171    247    251    Ljava/io/IOException;
        //  167    171    242    247    Any
        //  174    179    247    251    Ljava/io/IOException;
        //  174    179    242    247    Any
        //  182    187    247    251    Ljava/io/IOException;
        //  182    187    242    247    Any
        //  190    199    247    251    Ljava/io/IOException;
        //  190    199    242    247    Any
        //  202    213    247    251    Ljava/io/IOException;
        //  202    213    242    247    Any
        //  216    225    247    251    Ljava/io/IOException;
        //  216    225    242    247    Any
        //  228    237    247    251    Ljava/io/IOException;
        //  228    237    242    247    Any
        //  240    242    247    251    Ljava/io/IOException;
        //  240    242    242    247    Any
        //  251    253    242    247    Any
        //  253    257    264    268    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0145:
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
    
    public static String c(final String p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload           5
        //     8: astore_3       
        //     9: new             Ljava/io/File;
        //    12: astore          6
        //    14: aload           5
        //    16: astore_3       
        //    17: aload           6
        //    19: aload_0        
        //    20: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    23: aload           5
        //    25: astore_3       
        //    26: aload           6
        //    28: invokevirtual   java/io/File.length:()J
        //    31: l2d            
        //    32: ldc2_w          1.4
        //    35: dmul           
        //    36: dconst_1       
        //    37: dadd           
        //    38: d2i            
        //    39: bipush          40
        //    41: invokestatic    java/lang/Math.max:(II)I
        //    44: newarray        B
        //    46: astore          8
        //    48: aload           5
        //    50: astore_3       
        //    51: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$a;
        //    54: astore_0       
        //    55: aload           5
        //    57: astore_3       
        //    58: new             Ljava/io/BufferedInputStream;
        //    61: astore          7
        //    63: aload           5
        //    65: astore_3       
        //    66: new             Ljava/io/FileInputStream;
        //    69: astore          9
        //    71: aload           5
        //    73: astore_3       
        //    74: aload           9
        //    76: aload           6
        //    78: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    81: aload           5
        //    83: astore_3       
        //    84: aload           7
        //    86: aload           9
        //    88: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    91: aload           5
        //    93: astore_3       
        //    94: aload_0        
        //    95: aload           7
        //    97: iconst_1       
        //    98: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$a.<init>:(Ljava/io/InputStream;I)V
        //   101: iconst_0       
        //   102: istore_1       
        //   103: aload_0        
        //   104: aload           8
        //   106: iload_1        
        //   107: sipush          4096
        //   110: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.read:([BII)I
        //   113: istore_2       
        //   114: iload_2        
        //   115: iflt            125
        //   118: iload_1        
        //   119: iload_2        
        //   120: iadd           
        //   121: istore_1       
        //   122: goto            103
        //   125: new             Ljava/lang/String;
        //   128: dup            
        //   129: aload           8
        //   131: iconst_0       
        //   132: iload_1        
        //   133: ldc             "US-ASCII"
        //   135: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //   138: astore_3       
        //   139: aload_0        
        //   140: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.close:()V
        //   143: aload_3        
        //   144: areturn        
        //   145: astore          4
        //   147: aload_0        
        //   148: astore_3       
        //   149: aload           4
        //   151: astore_0       
        //   152: goto            175
        //   155: astore          4
        //   157: aload_0        
        //   158: astore_3       
        //   159: aload           4
        //   161: astore_0       
        //   162: goto            173
        //   165: astore_0       
        //   166: goto            175
        //   169: astore_0       
        //   170: aload           4
        //   172: astore_3       
        //   173: aload_0        
        //   174: athrow         
        //   175: aload_3        
        //   176: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$a.close:()V
        //   179: aload_0        
        //   180: athrow         
        //   181: astore_0       
        //   182: goto            143
        //   185: astore_3       
        //   186: goto            179
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      14     169    173    Ljava/io/IOException;
        //  9      14     165    169    Any
        //  17     23     169    173    Ljava/io/IOException;
        //  17     23     165    169    Any
        //  26     48     169    173    Ljava/io/IOException;
        //  26     48     165    169    Any
        //  51     55     169    173    Ljava/io/IOException;
        //  51     55     165    169    Any
        //  58     63     169    173    Ljava/io/IOException;
        //  58     63     165    169    Any
        //  66     71     169    173    Ljava/io/IOException;
        //  66     71     165    169    Any
        //  74     81     169    173    Ljava/io/IOException;
        //  74     81     165    169    Any
        //  84     91     169    173    Ljava/io/IOException;
        //  84     91     165    169    Any
        //  94     101    169    173    Ljava/io/IOException;
        //  94     101    165    169    Any
        //  103    114    155    165    Ljava/io/IOException;
        //  103    114    145    155    Any
        //  125    139    155    165    Ljava/io/IOException;
        //  125    139    145    155    Any
        //  139    143    181    185    Ljava/lang/Exception;
        //  173    175    165    169    Any
        //  175    179    185    189    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    public static byte[] decode(final String p0, final int p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          313
        //     4: aload_0        
        //     5: ldc             "US-ASCII"
        //     7: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    10: astore_2       
        //    11: aload_2        
        //    12: astore_0       
        //    13: goto            22
        //    16: astore_2       
        //    17: aload_0        
        //    18: invokevirtual   java/lang/String.getBytes:()[B
        //    21: astore_0       
        //    22: aload_0        
        //    23: iconst_0       
        //    24: aload_0        
        //    25: arraylength    
        //    26: iload_1        
        //    27: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.decode:([BIII)[B
        //    30: astore          4
        //    32: iload_1        
        //    33: iconst_4       
        //    34: iand           
        //    35: ifeq            43
        //    38: iconst_1       
        //    39: istore_1       
        //    40: goto            45
        //    43: iconst_0       
        //    44: istore_1       
        //    45: aload           4
        //    47: astore_3       
        //    48: aload           4
        //    50: ifnull          311
        //    53: aload           4
        //    55: astore_3       
        //    56: aload           4
        //    58: arraylength    
        //    59: iconst_4       
        //    60: if_icmplt       311
        //    63: aload           4
        //    65: astore_3       
        //    66: iload_1        
        //    67: ifne            311
        //    70: aload           4
        //    72: astore_3       
        //    73: ldc_w           35615
        //    76: aload           4
        //    78: iconst_0       
        //    79: baload         
        //    80: sipush          255
        //    83: iand           
        //    84: aload           4
        //    86: iconst_1       
        //    87: baload         
        //    88: bipush          8
        //    90: ishl           
        //    91: ldc_w           65280
        //    94: iand           
        //    95: ior            
        //    96: if_icmpne       311
        //    99: sipush          2048
        //   102: newarray        B
        //   104: astore_3       
        //   105: aconst_null    
        //   106: astore_0       
        //   107: aconst_null    
        //   108: astore          6
        //   110: new             Ljava/io/ByteArrayOutputStream;
        //   113: astore          5
        //   115: aload           5
        //   117: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   120: new             Ljava/io/ByteArrayInputStream;
        //   123: astore_0       
        //   124: aload_0        
        //   125: aload           4
        //   127: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   130: new             Ljava/util/zip/GZIPInputStream;
        //   133: astore_2       
        //   134: aload_2        
        //   135: aload_0        
        //   136: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //   139: aload_2        
        //   140: aload_3        
        //   141: invokevirtual   java/util/zip/GZIPInputStream.read:([B)I
        //   144: istore_1       
        //   145: iload_1        
        //   146: iflt            160
        //   149: aload           5
        //   151: aload_3        
        //   152: iconst_0       
        //   153: iload_1        
        //   154: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   157: goto            139
        //   160: aload           5
        //   162: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   165: astore          8
        //   167: aload_0        
        //   168: astore          7
        //   170: aload_2        
        //   171: astore          6
        //   173: aload           8
        //   175: astore_3       
        //   176: aload           5
        //   178: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   181: aload           8
        //   183: astore_3       
        //   184: aload_2        
        //   185: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   188: aload_0        
        //   189: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   192: goto            311
        //   195: astore          4
        //   197: aload_0        
        //   198: astore_3       
        //   199: aload           4
        //   201: astore_0       
        //   202: goto            232
        //   205: astore_3       
        //   206: goto            243
        //   209: astore          4
        //   211: aconst_null    
        //   212: astore_2       
        //   213: aload_0        
        //   214: astore_3       
        //   215: aload           4
        //   217: astore_0       
        //   218: goto            232
        //   221: astore_3       
        //   222: aconst_null    
        //   223: astore_2       
        //   224: goto            243
        //   227: astore_0       
        //   228: aconst_null    
        //   229: astore_3       
        //   230: aconst_null    
        //   231: astore_2       
        //   232: aload_0        
        //   233: astore          4
        //   235: goto            295
        //   238: astore_3       
        //   239: aconst_null    
        //   240: astore_0       
        //   241: aconst_null    
        //   242: astore_2       
        //   243: goto            267
        //   246: astore          4
        //   248: aconst_null    
        //   249: astore_3       
        //   250: aconst_null    
        //   251: astore_2       
        //   252: aload_0        
        //   253: astore          5
        //   255: goto            295
        //   258: astore_3       
        //   259: aconst_null    
        //   260: astore_0       
        //   261: aconst_null    
        //   262: astore_2       
        //   263: aload           6
        //   265: astore          5
        //   267: aload_3        
        //   268: invokevirtual   java/io/IOException.printStackTrace:()V
        //   271: aload_0        
        //   272: astore          7
        //   274: aload_2        
        //   275: astore          6
        //   277: aload           4
        //   279: astore_3       
        //   280: aload           5
        //   282: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   285: aload           4
        //   287: astore_3       
        //   288: goto            184
        //   291: astore          4
        //   293: aload_0        
        //   294: astore_3       
        //   295: aload           5
        //   297: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   300: aload_2        
        //   301: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   304: aload_3        
        //   305: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   308: aload           4
        //   310: athrow         
        //   311: aload_3        
        //   312: areturn        
        //   313: new             Ljava/lang/NullPointerException;
        //   316: dup            
        //   317: ldc_w           "Input string was null."
        //   320: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   323: athrow         
        //   324: astore_0       
        //   325: aload           7
        //   327: astore_0       
        //   328: aload           6
        //   330: astore_2       
        //   331: goto            184
        //   334: astore_2       
        //   335: goto            188
        //   338: astore_0       
        //   339: goto            311
        //   342: astore_0       
        //   343: goto            300
        //   346: astore_0       
        //   347: goto            304
        //   350: astore_0       
        //   351: goto            308
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  4      11     16     22     Ljava/io/UnsupportedEncodingException;
        //  110    120    258    267    Ljava/io/IOException;
        //  110    120    246    258    Any
        //  120    130    238    243    Ljava/io/IOException;
        //  120    130    227    232    Any
        //  130    139    221    227    Ljava/io/IOException;
        //  130    139    209    221    Any
        //  139    145    205    209    Ljava/io/IOException;
        //  139    145    195    205    Any
        //  149    157    205    209    Ljava/io/IOException;
        //  149    157    195    205    Any
        //  160    167    205    209    Ljava/io/IOException;
        //  160    167    195    205    Any
        //  176    181    324    334    Ljava/lang/Exception;
        //  184    188    334    338    Ljava/lang/Exception;
        //  188    192    338    342    Ljava/lang/Exception;
        //  267    271    291    295    Any
        //  280    285    324    334    Ljava/lang/Exception;
        //  295    300    342    346    Ljava/lang/Exception;
        //  300    304    346    350    Ljava/lang/Exception;
        //  304    308    350    354    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 207, Size: 207
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
    
    public static byte[] decode(final byte[] array) throws IOException {
        return decode(array, 0, array.length, 0);
    }
    
    public static byte[] decode(byte[] array, int n, int n2, final int n3) throws IOException {
        if (array != null) {
            if (n >= 0) {
                final int n4 = n + n2;
                if (n4 <= array.length) {
                    if (n2 == 0) {
                        return new byte[0];
                    }
                    if (n2 >= 4) {
                        final byte[] b = b(n3);
                        final byte[] array2 = new byte[n2 * 3 / 4];
                        final byte[] array3 = new byte[4];
                        int n5 = 0;
                        n2 = 0;
                        int n6 = n;
                        n = n2;
                        int n7;
                        while (true) {
                            n7 = n;
                            if (n6 >= n4) {
                                break;
                            }
                            final byte b2 = b[array[n6] & 0xFF];
                            if (b2 < -5) {
                                throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[] { array[n6] & 0xFF, n6 }));
                            }
                            n2 = n5;
                            n7 = n;
                            if (b2 >= -1) {
                                n2 = n5 + 1;
                                array3[n5] = array[n6];
                                if (n2 > 3) {
                                    n7 = n + a(array3, 0, array2, n, n3);
                                    if (array[n6] == 61) {
                                        break;
                                    }
                                    n2 = 0;
                                }
                                else {
                                    n7 = n;
                                }
                            }
                            ++n6;
                            n5 = n2;
                            n = n7;
                        }
                        array = new byte[n7];
                        System.arraycopy((Object)array2, 0, (Object)array, 0, n7);
                        return array;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Base64-encoded string must have at least four characters, but length specified was ");
                    sb.append(n2);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[] { array.length, n, n2 }));
        }
        throw new NullPointerException("Cannot decode null source array.");
    }
    
    public static void f(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload           5
        //     8: astore_2       
        //     9: new             Lcom/alibaba/sdk/android/man/crashreporter/e/b$b;
        //    12: astore_3       
        //    13: aload           5
        //    15: astore_2       
        //    16: new             Ljava/io/FileOutputStream;
        //    19: astore          6
        //    21: aload           5
        //    23: astore_2       
        //    24: aload           6
        //    26: aload_1        
        //    27: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    30: aload           5
        //    32: astore_2       
        //    33: aload_3        
        //    34: aload           6
        //    36: iconst_0       
        //    37: invokespecial   com/alibaba/sdk/android/man/crashreporter/e/b$b.<init>:(Ljava/io/OutputStream;I)V
        //    40: aload_3        
        //    41: aload_0        
        //    42: ldc             "US-ASCII"
        //    44: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    47: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.write:([B)V
        //    50: aload_3        
        //    51: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //    54: return         
        //    55: astore_0       
        //    56: aload_3        
        //    57: astore_2       
        //    58: goto            77
        //    61: astore_0       
        //    62: aload_3        
        //    63: astore_2       
        //    64: goto            75
        //    67: astore_0       
        //    68: goto            77
        //    71: astore_0       
        //    72: aload           4
        //    74: astore_2       
        //    75: aload_0        
        //    76: athrow         
        //    77: aload_2        
        //    78: invokevirtual   com/alibaba/sdk/android/man/crashreporter/e/b$b.close:()V
        //    81: aload_0        
        //    82: athrow         
        //    83: astore_0       
        //    84: goto            54
        //    87: astore_1       
        //    88: goto            81
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      13     71     75     Ljava/io/IOException;
        //  9      13     67     71     Any
        //  16     21     71     75     Ljava/io/IOException;
        //  16     21     67     71     Any
        //  24     30     71     75     Ljava/io/IOException;
        //  24     30     67     71     Any
        //  33     40     71     75     Ljava/io/IOException;
        //  33     40     67     71     Any
        //  40     50     61     67     Ljava/io/IOException;
        //  40     50     55     61     Any
        //  50     54     83     87     Ljava/lang/Exception;
        //  75     77     67     71     Any
        //  77     81     87     91     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
    
    public static void g(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.c:(Ljava/lang/String;)Ljava/lang/String;
        //     4: astore          6
        //     6: aconst_null    
        //     7: astore_3       
        //     8: aconst_null    
        //     9: astore          4
        //    11: aload           4
        //    13: astore_0       
        //    14: new             Ljava/io/BufferedOutputStream;
        //    17: astore_2       
        //    18: aload           4
        //    20: astore_0       
        //    21: new             Ljava/io/FileOutputStream;
        //    24: astore          5
        //    26: aload           4
        //    28: astore_0       
        //    29: aload           5
        //    31: aload_1        
        //    32: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    35: aload           4
        //    37: astore_0       
        //    38: aload_2        
        //    39: aload           5
        //    41: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    44: aload_2        
        //    45: aload           6
        //    47: ldc             "US-ASCII"
        //    49: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    52: invokevirtual   java/io/OutputStream.write:([B)V
        //    55: aload_2        
        //    56: invokevirtual   java/io/OutputStream.close:()V
        //    59: return         
        //    60: astore_1       
        //    61: aload_2        
        //    62: astore_0       
        //    63: goto            81
        //    66: astore_1       
        //    67: aload_2        
        //    68: astore_0       
        //    69: goto            79
        //    72: astore_1       
        //    73: goto            81
        //    76: astore_1       
        //    77: aload_3        
        //    78: astore_0       
        //    79: aload_1        
        //    80: athrow         
        //    81: aload_0        
        //    82: invokevirtual   java/io/OutputStream.close:()V
        //    85: aload_1        
        //    86: athrow         
        //    87: astore_0       
        //    88: goto            59
        //    91: astore_0       
        //    92: goto            85
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  14     18     76     79     Ljava/io/IOException;
        //  14     18     72     76     Any
        //  21     26     76     79     Ljava/io/IOException;
        //  21     26     72     76     Any
        //  29     35     76     79     Ljava/io/IOException;
        //  29     35     72     76     Any
        //  38     44     76     79     Ljava/io/IOException;
        //  38     44     72     76     Any
        //  44     55     66     72     Ljava/io/IOException;
        //  44     55     60     66     Any
        //  55     59     87     91     Ljava/lang/Exception;
        //  79     81     72     76     Any
        //  81     85     91     95     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    public static void h(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/b.b:(Ljava/lang/String;)[B
        //     4: astore          5
        //     6: aconst_null    
        //     7: astore_3       
        //     8: aconst_null    
        //     9: astore          4
        //    11: aload           4
        //    13: astore_0       
        //    14: new             Ljava/io/BufferedOutputStream;
        //    17: astore_2       
        //    18: aload           4
        //    20: astore_0       
        //    21: new             Ljava/io/FileOutputStream;
        //    24: astore          6
        //    26: aload           4
        //    28: astore_0       
        //    29: aload           6
        //    31: aload_1        
        //    32: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    35: aload           4
        //    37: astore_0       
        //    38: aload_2        
        //    39: aload           6
        //    41: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    44: aload_2        
        //    45: aload           5
        //    47: invokevirtual   java/io/OutputStream.write:([B)V
        //    50: aload_2        
        //    51: invokevirtual   java/io/OutputStream.close:()V
        //    54: return         
        //    55: astore_1       
        //    56: aload_2        
        //    57: astore_0       
        //    58: goto            76
        //    61: astore_1       
        //    62: aload_2        
        //    63: astore_0       
        //    64: goto            74
        //    67: astore_1       
        //    68: goto            76
        //    71: astore_1       
        //    72: aload_3        
        //    73: astore_0       
        //    74: aload_1        
        //    75: athrow         
        //    76: aload_0        
        //    77: invokevirtual   java/io/OutputStream.close:()V
        //    80: aload_1        
        //    81: athrow         
        //    82: astore_0       
        //    83: goto            54
        //    86: astore_0       
        //    87: goto            80
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  14     18     71     74     Ljava/io/IOException;
        //  14     18     67     71     Any
        //  21     26     71     74     Ljava/io/IOException;
        //  21     26     67     71     Any
        //  29     35     71     74     Ljava/io/IOException;
        //  29     35     67     71     Any
        //  38     44     71     74     Ljava/io/IOException;
        //  38     44     67     71     Any
        //  44     50     61     67     Ljava/io/IOException;
        //  44     50     55     61     Any
        //  50     54     82     86     Ljava/lang/Exception;
        //  74     76     67     71     Any
        //  76     80     86     90     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
    
    public static class a extends FilterInputStream
    {
        private int P;
        private int Q;
        private int R;
        private int S;
        private byte[] buffer;
        private boolean d;
        private boolean e;
        private byte[] g;
        private int position;
        
        public a(final InputStream inputStream) {
            this(inputStream, 0);
        }
        
        public a(final InputStream inputStream, final int s) {
            super(inputStream);
            this.S = s;
            final boolean b = true;
            this.e = ((s & 0x8) > 0);
            final boolean d = (s & 0x1) > 0 && b;
            this.d = d;
            int p2;
            if (d) {
                p2 = 4;
            }
            else {
                p2 = 3;
            }
            this.P = p2;
            this.buffer = new byte[p2];
            this.position = -1;
            this.R = 0;
            this.g = b(s);
        }
        
        public int read() throws IOException {
            if (this.position < 0) {
                if (this.d) {
                    final byte[] array = new byte[3];
                    int i = 0;
                    int n = 0;
                    while (i < 3) {
                        final int read = this.in.read();
                        if (read < 0) {
                            break;
                        }
                        array[i] = (byte)read;
                        ++n;
                        ++i;
                    }
                    if (n <= 0) {
                        return -1;
                    }
                    a(array, 0, n, this.buffer, 0, this.S);
                    this.position = 0;
                    this.Q = 4;
                }
                else {
                    final byte[] array2 = new byte[4];
                    int j;
                    for (j = 0; j < 4; ++j) {
                        int read2;
                        do {
                            read2 = this.in.read();
                        } while (read2 >= 0 && this.g[read2 & 0x7F] <= -5);
                        if (read2 < 0) {
                            break;
                        }
                        array2[j] = (byte)read2;
                    }
                    if (j == 4) {
                        this.Q = a(array2, 0, this.buffer, 0, this.S);
                        this.position = 0;
                    }
                    else {
                        if (j == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            final int position = this.position;
            if (position < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            }
            if (position >= this.Q) {
                return -1;
            }
            if (this.d && this.e && this.R >= 76) {
                this.R = 0;
                return 10;
            }
            ++this.R;
            final byte[] buffer = this.buffer;
            final int position2 = this.position;
            final int position3 = position2 + 1;
            this.position = position3;
            final byte b = buffer[position2];
            if (position3 >= this.P) {
                this.position = -1;
            }
            return b & 0xFF;
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            int i = 0;
            while (i < n2) {
                final int read = this.read();
                if (read >= 0) {
                    array[n + i] = (byte)read;
                    ++i;
                }
                else {
                    if (i == 0) {
                        return -1;
                    }
                    break;
                }
            }
            return i;
        }
    }
    
    public static class b extends FilterOutputStream
    {
        private int P;
        private int R;
        private int S;
        private byte[] buffer;
        private boolean d;
        private boolean e;
        private boolean f;
        private byte[] g;
        private byte[] h;
        private int position;
        
        public b(final OutputStream outputStream) {
            this(outputStream, 1);
        }
        
        public b(final OutputStream outputStream, final int s) {
            super(outputStream);
            final boolean b = true;
            this.e = ((s & 0x8) != 0x0);
            final boolean d = (s & 0x1) != 0x0 && b;
            this.d = d;
            int p2;
            if (d) {
                p2 = 3;
            }
            else {
                p2 = 4;
            }
            this.P = p2;
            this.buffer = new byte[p2];
            this.position = 0;
            this.R = 0;
            this.f = false;
            this.h = new byte[4];
            this.S = s;
            this.g = b(s);
        }
        
        public void close() throws IOException {
            this.f();
            super.close();
            this.buffer = null;
            this.out = null;
        }
        
        public void f() throws IOException {
            if (this.position > 0) {
                if (!this.d) {
                    throw new IOException("Base64 input not properly padded.");
                }
                this.out.write(a(this.h, this.buffer, this.position, this.S));
                this.position = 0;
            }
        }
        
        public void g() throws IOException {
            this.f();
            this.f = true;
        }
        
        public void h() {
            this.f = false;
        }
        
        public void write(int b) throws IOException {
            if (this.f) {
                this.out.write(b);
                return;
            }
            if (this.d) {
                final byte[] buffer = this.buffer;
                final int position = this.position;
                final int position2 = position + 1;
                this.position = position2;
                buffer[position] = (byte)b;
                if (position2 >= this.P) {
                    this.out.write(a(this.h, this.buffer, this.P, this.S));
                    b = this.R + 4;
                    this.R = b;
                    if (this.e && b >= 76) {
                        this.out.write(10);
                        this.R = 0;
                    }
                    this.position = 0;
                }
            }
            else {
                final byte[] g = this.g;
                final int n = b & 0x7F;
                if (g[n] > -5) {
                    final byte[] buffer2 = this.buffer;
                    final int position3 = this.position;
                    final int position4 = position3 + 1;
                    this.position = position4;
                    buffer2[position3] = (byte)b;
                    if (position4 >= this.P) {
                        b = a(buffer2, 0, this.h, 0, this.S);
                        this.out.write(this.h, 0, b);
                        this.position = 0;
                    }
                }
                else if (g[n] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            if (this.f) {
                this.out.write(array, n, n2);
                return;
            }
            for (int i = 0; i < n2; ++i) {
                this.write(array[n + i]);
            }
        }
    }
}
