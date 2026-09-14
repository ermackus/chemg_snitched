package com.tencent.open.log;

import java.util.Iterator;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentLinkedQueue;

public class f implements Iterable<String>
{
    private ConcurrentLinkedQueue<String> a;
    private AtomicInteger b;
    
    public f() {
        this.a = null;
        this.b = null;
        this.a = (ConcurrentLinkedQueue<String>)new ConcurrentLinkedQueue();
        this.b = new AtomicInteger(0);
    }
    
    public int a() {
        return this.b.get();
    }
    
    public int a(final String s) {
        final int length = s.length();
        this.a.add((Object)s);
        return this.b.addAndGet(length);
    }
    
    public void a(final Writer[] p0, final char[] p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          270
        //     4: aload_2        
        //     5: ifnull          270
        //     8: aload_2        
        //     9: arraylength    
        //    10: ifeq            270
        //    13: aload_1        
        //    14: arraylength    
        //    15: iconst_2       
        //    16: if_icmpge       22
        //    19: goto            270
        //    22: aload_1        
        //    23: iconst_0       
        //    24: aaload         
        //    25: astore          10
        //    27: aload_1        
        //    28: iconst_1       
        //    29: aaload         
        //    30: astore_1       
        //    31: aload_2        
        //    32: arraylength    
        //    33: istore          7
        //    35: aload_0        
        //    36: invokevirtual   com/tencent/open/log/f.iterator:()Ljava/util/Iterator;
        //    39: astore          12
        //    41: iload           7
        //    43: istore          8
        //    45: iconst_0       
        //    46: istore          9
        //    48: aload           12
        //    50: invokeinterface java/util/Iterator.hasNext:()Z
        //    55: ifeq            208
        //    58: aload           12
        //    60: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    65: checkcast       Ljava/lang/String;
        //    68: astore          11
        //    70: aload           11
        //    72: invokevirtual   java/lang/String.length:()I
        //    75: istore          5
        //    77: iconst_0       
        //    78: istore          6
        //    80: iload           8
        //    82: istore_3       
        //    83: iload           9
        //    85: istore          4
        //    87: iload           4
        //    89: istore          9
        //    91: iload_3        
        //    92: istore          8
        //    94: iload           5
        //    96: ifle            48
        //    99: iload_3        
        //   100: iload           5
        //   102: if_icmple       112
        //   105: iload           5
        //   107: istore          9
        //   109: goto            115
        //   112: iload_3        
        //   113: istore          9
        //   115: iload           6
        //   117: iload           9
        //   119: iadd           
        //   120: istore          8
        //   122: aload           11
        //   124: iload           6
        //   126: iload           8
        //   128: aload_2        
        //   129: iload           4
        //   131: invokevirtual   java/lang/String.getChars:(II[CI)V
        //   134: iload_3        
        //   135: iload           9
        //   137: isub           
        //   138: istore_3       
        //   139: iload           4
        //   141: iload           9
        //   143: iadd           
        //   144: istore          4
        //   146: iload           5
        //   148: iload           9
        //   150: isub           
        //   151: istore          5
        //   153: iload_3        
        //   154: ifne            201
        //   157: aload           10
        //   159: ifnull          176
        //   162: aload           10
        //   164: aload_2        
        //   165: iconst_0       
        //   166: iload           7
        //   168: invokevirtual   java/io/Writer.write:([CII)V
        //   171: goto            176
        //   174: astore          13
        //   176: aload_1        
        //   177: ifnull          188
        //   180: aload_1        
        //   181: aload_2        
        //   182: iconst_0       
        //   183: iload           7
        //   185: invokevirtual   java/io/Writer.write:([CII)V
        //   188: iload           7
        //   190: istore_3       
        //   191: iload           8
        //   193: istore          6
        //   195: iconst_0       
        //   196: istore          4
        //   198: goto            87
        //   201: iload           8
        //   203: istore          6
        //   205: goto            87
        //   208: iload           9
        //   210: ifle            248
        //   213: aload           10
        //   215: ifnull          232
        //   218: aload           10
        //   220: aload_2        
        //   221: iconst_0       
        //   222: iload           9
        //   224: invokevirtual   java/io/Writer.write:([CII)V
        //   227: goto            232
        //   230: astore          11
        //   232: aload_1        
        //   233: ifnull          248
        //   236: aload_1        
        //   237: aload_2        
        //   238: iconst_0       
        //   239: iload           9
        //   241: invokevirtual   java/io/Writer.write:([CII)V
        //   244: goto            248
        //   247: astore_2       
        //   248: aload           10
        //   250: ifnull          262
        //   253: aload           10
        //   255: invokevirtual   java/io/Writer.flush:()V
        //   258: goto            262
        //   261: astore_2       
        //   262: aload_1        
        //   263: ifnull          270
        //   266: aload_1        
        //   267: invokevirtual   java/io/Writer.flush:()V
        //   270: return         
        //   271: astore          13
        //   273: goto            188
        //   276: astore_1       
        //   277: goto            270
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  162    171    174    176    Ljava/lang/Exception;
        //  180    188    271    276    Ljava/lang/Exception;
        //  218    227    230    232    Ljava/lang/Exception;
        //  236    244    247    248    Ljava/lang/Exception;
        //  253    258    261    262    Ljava/lang/Exception;
        //  266    270    276    280    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0270:
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
    
    public void b() {
        this.a.clear();
        this.b.set(0);
    }
    
    public Iterator<String> iterator() {
        return (Iterator<String>)this.a.iterator();
    }
}
