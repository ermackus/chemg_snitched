package com.goodsrc.library.utils;

import java.io.IOException;
import android.text.TextUtils;
import java.io.InputStream;
import java.io.FilterInputStream;

public class Base64Decoder extends FilterInputStream
{
    private static final char[] chars;
    private static final int[] ints;
    private int carryOver;
    private int charCount;
    
    static {
        chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        ints = new int[128];
        for (int i = 0; i < 64; ++i) {
            Base64Decoder.ints[Base64Decoder.chars[i]] = i;
        }
    }
    
    private Base64Decoder(final InputStream inputStream) {
        super(inputStream);
    }
    
    public static String decode(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        return new String(decodeToBytes(s));
    }
    
    public static byte[] decodeToBytes(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/lang/String.getBytes:()[B
        //     4: astore_2       
        //     5: new             Lcom/goodsrc/library/utils/Base64Decoder;
        //     8: dup            
        //     9: new             Ljava/io/ByteArrayInputStream;
        //    12: dup            
        //    13: aload_2        
        //    14: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    17: invokespecial   com/goodsrc/library/utils/Base64Decoder.<init>:(Ljava/io/InputStream;)V
        //    20: astore_0       
        //    21: new             Ljava/io/ByteArrayOutputStream;
        //    24: dup            
        //    25: aload_2        
        //    26: arraylength    
        //    27: i2d            
        //    28: ldc2_w          0.75
        //    31: dmul           
        //    32: d2i            
        //    33: invokespecial   java/io/ByteArrayOutputStream.<init>:(I)V
        //    36: astore_2       
        //    37: sipush          4096
        //    40: newarray        B
        //    42: astore_3       
        //    43: aload_0        
        //    44: aload_3        
        //    45: invokevirtual   com/goodsrc/library/utils/Base64Decoder.read:([B)I
        //    48: istore_1       
        //    49: iload_1        
        //    50: iconst_m1      
        //    51: if_icmpeq       64
        //    54: aload_2        
        //    55: aload_3        
        //    56: iconst_0       
        //    57: iload_1        
        //    58: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //    61: goto            43
        //    64: aload_2        
        //    65: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    68: astore_3       
        //    69: aload_0        
        //    70: invokevirtual   com/goodsrc/library/utils/Base64Decoder.close:()V
        //    73: aload_2        
        //    74: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    77: aload_3        
        //    78: areturn        
        //    79: astore_0       
        //    80: new             Ljava/lang/RuntimeException;
        //    83: dup            
        //    84: aload_0        
        //    85: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    88: athrow         
        //    89: astore_0       
        //    90: new             Ljava/lang/RuntimeException;
        //    93: dup            
        //    94: aload_0        
        //    95: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    98: athrow         
        //    99: astore_3       
        //   100: goto            118
        //   103: astore_3       
        //   104: new             Ljava/lang/RuntimeException;
        //   107: astore          4
        //   109: aload           4
        //   111: aload_3        
        //   112: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   115: aload           4
        //   117: athrow         
        //   118: aload_0        
        //   119: invokevirtual   com/goodsrc/library/utils/Base64Decoder.close:()V
        //   122: aload_2        
        //   123: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   126: aload_3        
        //   127: athrow         
        //   128: astore_0       
        //   129: new             Ljava/lang/RuntimeException;
        //   132: dup            
        //   133: aload_0        
        //   134: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   137: athrow         
        //   138: astore_0       
        //   139: new             Ljava/lang/RuntimeException;
        //   142: dup            
        //   143: aload_0        
        //   144: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   147: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  37     43     103    118    Ljava/io/IOException;
        //  37     43     99     148    Any
        //  43     49     103    118    Ljava/io/IOException;
        //  43     49     99     148    Any
        //  54     61     103    118    Ljava/io/IOException;
        //  54     61     99     148    Any
        //  64     69     103    118    Ljava/io/IOException;
        //  64     69     99     148    Any
        //  69     73     89     99     Ljava/io/IOException;
        //  73     77     79     89     Ljava/io/IOException;
        //  104    118    99     148    Any
        //  118    122    138    148    Ljava/io/IOException;
        //  122    126    128    138    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
    
    public int read() throws IOException {
        int read;
        do {
            read = this.in.read();
            if (read == -1) {
                return -1;
            }
        } while (Character.isWhitespace((char)read));
        final int charCount = this.charCount + 1;
        this.charCount = charCount;
        if (read == 61) {
            return -1;
        }
        final int n = Base64Decoder.ints[read];
        final int n2 = (charCount - 1) % 4;
        if (n2 == 0) {
            this.carryOver = (n & 0x3F);
            return this.read();
        }
        if (n2 == 1) {
            final int carryOver = this.carryOver;
            this.carryOver = (n & 0xF);
            return (carryOver << 2) + (n >> 4) & 0xFF;
        }
        if (n2 == 2) {
            final int carryOver2 = this.carryOver;
            this.carryOver = (n & 0x3);
            return (carryOver2 << 4) + (n >> 2) & 0xFF;
        }
        if (n2 == 3) {
            return (this.carryOver << 6) + n & 0xFF;
        }
        return -1;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (array.length >= n2 + n - 1) {
            int i;
            for (i = 0; i < n2; ++i) {
                final int read = this.read();
                if (read == -1 && i == 0) {
                    return -1;
                }
                if (read == -1) {
                    break;
                }
                array[n + i] = (byte)read;
            }
            return i;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("The input buffer is too small: ");
        sb.append(n2);
        sb.append(" bytes requested starting at offset ");
        sb.append(n);
        sb.append(" while the buffer  is only ");
        sb.append(array.length);
        sb.append(" bytes long.");
        throw new IOException(sb.toString());
    }
}
