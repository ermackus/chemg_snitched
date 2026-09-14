package com.goodsrc.library.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class Base64Encoder extends FilterOutputStream
{
    private static final char[] chars;
    private int carryOver;
    private int charCount;
    private boolean isWrapBreak;
    
    static {
        chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
    
    private Base64Encoder(final OutputStream outputStream) {
        super(outputStream);
        this.isWrapBreak = true;
    }
    
    private Base64Encoder(final OutputStream outputStream, final boolean isWrapBreak) {
        this(outputStream);
        this.isWrapBreak = isWrapBreak;
    }
    
    public static String encode(final byte[] array) {
        return encode(array, true);
    }
    
    public static String encode(final byte[] p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: arraylength    
        //     6: i2d            
        //     7: ldc2_w          1.4
        //    10: dmul           
        //    11: d2i            
        //    12: invokespecial   java/io/ByteArrayOutputStream.<init>:(I)V
        //    15: astore_3       
        //    16: new             Lcom/goodsrc/library/utils/Base64Encoder;
        //    19: dup            
        //    20: aload_3        
        //    21: iload_1        
        //    22: invokespecial   com/goodsrc/library/utils/Base64Encoder.<init>:(Ljava/io/OutputStream;Z)V
        //    25: astore_2       
        //    26: aload_2        
        //    27: aload_0        
        //    28: invokevirtual   com/goodsrc/library/utils/Base64Encoder.write:([B)V
        //    31: aload_2        
        //    32: invokevirtual   com/goodsrc/library/utils/Base64Encoder.close:()V
        //    35: aload_3        
        //    36: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //    39: areturn        
        //    40: astore_0       
        //    41: new             Ljava/lang/RuntimeException;
        //    44: dup            
        //    45: aload_0        
        //    46: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    49: athrow         
        //    50: astore_0       
        //    51: goto            66
        //    54: astore_0       
        //    55: new             Ljava/lang/RuntimeException;
        //    58: astore_3       
        //    59: aload_3        
        //    60: aload_0        
        //    61: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    64: aload_3        
        //    65: athrow         
        //    66: aload_2        
        //    67: invokevirtual   com/goodsrc/library/utils/Base64Encoder.close:()V
        //    70: aload_0        
        //    71: athrow         
        //    72: astore_0       
        //    73: new             Ljava/lang/RuntimeException;
        //    76: dup            
        //    77: aload_0        
        //    78: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    81: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  26     31     54     66     Ljava/io/IOException;
        //  26     31     50     82     Any
        //  31     35     40     50     Ljava/io/IOException;
        //  55     66     50     82     Any
        //  66     70     72     82     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    public void close() throws IOException {
        final int charCount = this.charCount;
        if (charCount % 3 == 1) {
            this.out.write((int)Base64Encoder.chars[this.carryOver << 4 & 0x3F]);
            this.out.write(61);
            this.out.write(61);
        }
        else if (charCount % 3 == 2) {
            this.out.write((int)Base64Encoder.chars[this.carryOver << 2 & 0x3F]);
            this.out.write(61);
        }
        super.close();
    }
    
    public void write(int charCount) throws IOException {
        int n = charCount;
        if (charCount < 0) {
            n = charCount + 256;
        }
        charCount = this.charCount;
        if (charCount % 3 == 0) {
            this.carryOver = (n & 0x3);
            this.out.write((int)Base64Encoder.chars[n >> 2]);
        }
        else if (charCount % 3 == 1) {
            charCount = this.carryOver;
            this.carryOver = (n & 0xF);
            this.out.write((int)Base64Encoder.chars[(charCount << 4) + (n >> 4) & 0x3F]);
        }
        else if (charCount % 3 == 2) {
            charCount = this.carryOver;
            this.out.write((int)Base64Encoder.chars[(charCount << 2) + (n >> 6) & 0x3F]);
            this.out.write((int)Base64Encoder.chars[n & 0x3F]);
            this.carryOver = 0;
        }
        charCount = this.charCount + 1;
        this.charCount = charCount;
        if (this.isWrapBreak && charCount % 57 == 0) {
            this.out.write(10);
        }
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        for (int i = 0; i < n2; ++i) {
            this.write(array[n + i]);
        }
    }
}
