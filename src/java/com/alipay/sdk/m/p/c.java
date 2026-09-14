package com.alipay.sdk.m.p;

import com.alipay.sdk.m.l.a;
import com.alipay.sdk.m.n.e;
import com.alipay.sdk.m.n.d;
import java.util.Locale;
import com.alipay.sdk.m.u.n;

public final class c
{
    public boolean a;
    public String b;
    
    public c(final boolean a) {
        this.a = a;
        this.b = n.a(24);
    }
    
    public static int a(final String s) {
        return Integer.parseInt(s);
    }
    
    public static String a(final int n) {
        return String.format(Locale.getDefault(), "%05d", new Object[] { n });
    }
    
    public static byte[] a(final String s, final String s2) {
        return d.a(s, s2);
    }
    
    public static byte[] a(final String s, final byte[] array, final String s2) {
        return e.a(s, array, s2);
    }
    
    public static byte[] a(final byte[]... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          8
        //     3: aconst_null    
        //     4: astore          4
        //     6: aconst_null    
        //     7: astore          7
        //     9: aload_0        
        //    10: ifnull          256
        //    13: aload_0        
        //    14: arraylength    
        //    15: ifne            21
        //    18: goto            256
        //    21: new             Ljava/io/ByteArrayOutputStream;
        //    24: astore_3       
        //    25: aload_3        
        //    26: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    29: new             Ljava/io/DataOutputStream;
        //    32: astore          5
        //    34: aload           5
        //    36: aload_3        
        //    37: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    40: aload_3        
        //    41: astore          6
        //    43: aload           5
        //    45: astore          4
        //    47: aload_0        
        //    48: arraylength    
        //    49: istore_2       
        //    50: iconst_0       
        //    51: istore_1       
        //    52: iload_1        
        //    53: iload_2        
        //    54: if_icmpge       103
        //    57: aload_0        
        //    58: iload_1        
        //    59: aaload         
        //    60: astore          9
        //    62: aload_3        
        //    63: astore          6
        //    65: aload           5
        //    67: astore          4
        //    69: aload           5
        //    71: aload           9
        //    73: arraylength    
        //    74: invokestatic    com/alipay/sdk/m/p/c.a:(I)Ljava/lang/String;
        //    77: invokevirtual   java/lang/String.getBytes:()[B
        //    80: invokevirtual   java/io/DataOutputStream.write:([B)V
        //    83: aload_3        
        //    84: astore          6
        //    86: aload           5
        //    88: astore          4
        //    90: aload           5
        //    92: aload           9
        //    94: invokevirtual   java/io/DataOutputStream.write:([B)V
        //    97: iinc            1, 1
        //   100: goto            52
        //   103: aload_3        
        //   104: astore          6
        //   106: aload           5
        //   108: astore          4
        //   110: aload           5
        //   112: invokevirtual   java/io/DataOutputStream.flush:()V
        //   115: aload_3        
        //   116: astore          6
        //   118: aload           5
        //   120: astore          4
        //   122: aload_3        
        //   123: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   126: astore_0       
        //   127: aload_3        
        //   128: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   131: goto            216
        //   134: astore          4
        //   136: aload           5
        //   138: astore_0       
        //   139: aload           4
        //   141: astore          5
        //   143: goto            180
        //   146: astore_0       
        //   147: aconst_null    
        //   148: astore          4
        //   150: goto            232
        //   153: astore          5
        //   155: aconst_null    
        //   156: astore_0       
        //   157: goto            180
        //   160: astore_0       
        //   161: aconst_null    
        //   162: astore          5
        //   164: aload           4
        //   166: astore_3       
        //   167: aload           5
        //   169: astore          4
        //   171: goto            232
        //   174: astore          5
        //   176: aconst_null    
        //   177: astore_3       
        //   178: aconst_null    
        //   179: astore_0       
        //   180: aload_3        
        //   181: astore          6
        //   183: aload_0        
        //   184: astore          4
        //   186: aload           5
        //   188: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   191: aload_3        
        //   192: ifnull          203
        //   195: aload_3        
        //   196: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   199: goto            203
        //   202: astore_3       
        //   203: aload           8
        //   205: astore_3       
        //   206: aload_0        
        //   207: ifnull          223
        //   210: aload_0        
        //   211: astore          5
        //   213: aload           7
        //   215: astore_0       
        //   216: aload           5
        //   218: invokevirtual   java/io/DataOutputStream.close:()V
        //   221: aload_0        
        //   222: astore_3       
        //   223: aload_3        
        //   224: areturn        
        //   225: astore_0       
        //   226: aload           6
        //   228: astore_3       
        //   229: goto            150
        //   232: aload_3        
        //   233: ifnull          244
        //   236: aload_3        
        //   237: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   240: goto            244
        //   243: astore_3       
        //   244: aload           4
        //   246: ifnull          254
        //   249: aload           4
        //   251: invokevirtual   java/io/DataOutputStream.close:()V
        //   254: aload_0        
        //   255: athrow         
        //   256: aconst_null    
        //   257: areturn        
        //   258: astore_3       
        //   259: goto            131
        //   262: astore_3       
        //   263: aload_0        
        //   264: astore_3       
        //   265: goto            223
        //   268: astore_3       
        //   269: goto            254
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     29     174    180    Ljava/lang/Exception;
        //  21     29     160    174    Any
        //  29     40     153    160    Ljava/lang/Exception;
        //  29     40     146    150    Any
        //  47     50     134    146    Ljava/lang/Exception;
        //  47     50     225    232    Any
        //  69     83     134    146    Ljava/lang/Exception;
        //  69     83     225    232    Any
        //  90     97     134    146    Ljava/lang/Exception;
        //  90     97     225    232    Any
        //  110    115    134    146    Ljava/lang/Exception;
        //  110    115    225    232    Any
        //  122    127    134    146    Ljava/lang/Exception;
        //  122    127    225    232    Any
        //  127    131    258    262    Ljava/lang/Exception;
        //  186    191    225    232    Any
        //  195    199    202    203    Ljava/lang/Exception;
        //  216    221    262    268    Ljava/lang/Exception;
        //  236    240    243    244    Ljava/lang/Exception;
        //  249    254    268    272    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 151, Size: 151
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
    
    public static byte[] b(final String s, final byte[] array, final String s2) {
        return e.b(s, array, s2);
    }
    
    public b a(final com.alipay.sdk.m.p.d p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: new             Ljava/io/ByteArrayInputStream;
        //     6: astore          6
        //     8: aload           6
        //    10: aload_1        
        //    11: invokevirtual   com/alipay/sdk/m/p/d.a:()[B
        //    14: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    17: aload           6
        //    19: astore          4
        //    21: iconst_5       
        //    22: newarray        B
        //    24: astore          5
        //    26: aload           6
        //    28: astore          4
        //    30: aload           6
        //    32: aload           5
        //    34: invokevirtual   java/io/ByteArrayInputStream.read:([B)I
        //    37: pop            
        //    38: aload           6
        //    40: astore          4
        //    42: new             Ljava/lang/String;
        //    45: astore          7
        //    47: aload           6
        //    49: astore          4
        //    51: aload           7
        //    53: aload           5
        //    55: invokespecial   java/lang/String.<init>:([B)V
        //    58: aload           6
        //    60: astore          4
        //    62: aload           7
        //    64: invokestatic    com/alipay/sdk/m/p/c.a:(Ljava/lang/String;)I
        //    67: newarray        B
        //    69: astore          5
        //    71: aload           6
        //    73: astore          4
        //    75: aload           6
        //    77: aload           5
        //    79: invokevirtual   java/io/ByteArrayInputStream.read:([B)I
        //    82: pop            
        //    83: aload           6
        //    85: astore          4
        //    87: new             Ljava/lang/String;
        //    90: astore          7
        //    92: aload           6
        //    94: astore          4
        //    96: aload           7
        //    98: aload           5
        //   100: invokespecial   java/lang/String.<init>:([B)V
        //   103: aload           6
        //   105: astore          4
        //   107: iconst_5       
        //   108: newarray        B
        //   110: astore          8
        //   112: aload           6
        //   114: astore          4
        //   116: aload           6
        //   118: aload           8
        //   120: invokevirtual   java/io/ByteArrayInputStream.read:([B)I
        //   123: pop            
        //   124: aload           6
        //   126: astore          4
        //   128: new             Ljava/lang/String;
        //   131: astore          5
        //   133: aload           6
        //   135: astore          4
        //   137: aload           5
        //   139: aload           8
        //   141: invokespecial   java/lang/String.<init>:([B)V
        //   144: aload           6
        //   146: astore          4
        //   148: aload           5
        //   150: invokestatic    com/alipay/sdk/m/p/c.a:(Ljava/lang/String;)I
        //   153: istore_3       
        //   154: iload_3        
        //   155: ifle            254
        //   158: aload           6
        //   160: astore          4
        //   162: iload_3        
        //   163: newarray        B
        //   165: astore          8
        //   167: aload           6
        //   169: astore          4
        //   171: aload           6
        //   173: aload           8
        //   175: invokevirtual   java/io/ByteArrayInputStream.read:([B)I
        //   178: pop            
        //   179: aload           8
        //   181: astore          5
        //   183: aload           6
        //   185: astore          4
        //   187: aload_0        
        //   188: getfield        com/alipay/sdk/m/p/c.a:Z
        //   191: ifeq            210
        //   194: aload           6
        //   196: astore          4
        //   198: aload_0        
        //   199: getfield        com/alipay/sdk/m/p/c.b:Ljava/lang/String;
        //   202: aload           8
        //   204: aload_2        
        //   205: invokestatic    com/alipay/sdk/m/p/c.a:(Ljava/lang/String;[BLjava/lang/String;)[B
        //   208: astore          5
        //   210: aload           5
        //   212: astore_2       
        //   213: aload           6
        //   215: astore          4
        //   217: aload_1        
        //   218: invokevirtual   com/alipay/sdk/m/p/d.b:()Z
        //   221: ifeq            234
        //   224: aload           6
        //   226: astore          4
        //   228: aload           5
        //   230: invokestatic    com/alipay/sdk/m/n/b.b:([B)[B
        //   233: astore_2       
        //   234: aload           6
        //   236: astore          4
        //   238: new             Ljava/lang/String;
        //   241: astore_1       
        //   242: aload           6
        //   244: astore          4
        //   246: aload_1        
        //   247: aload_2        
        //   248: invokespecial   java/lang/String.<init>:([B)V
        //   251: goto            256
        //   254: aconst_null    
        //   255: astore_1       
        //   256: aload           6
        //   258: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   261: aload_1        
        //   262: astore_2       
        //   263: goto            327
        //   266: astore_2       
        //   267: aload_1        
        //   268: astore_2       
        //   269: goto            327
        //   272: astore          5
        //   274: aload           6
        //   276: astore_2       
        //   277: aload           7
        //   279: astore_1       
        //   280: goto            306
        //   283: astore          5
        //   285: aconst_null    
        //   286: astore_1       
        //   287: aload           6
        //   289: astore_2       
        //   290: goto            306
        //   293: astore_1       
        //   294: aload           4
        //   296: astore_2       
        //   297: goto            353
        //   300: astore          5
        //   302: aconst_null    
        //   303: astore_2       
        //   304: aconst_null    
        //   305: astore_1       
        //   306: aload_2        
        //   307: astore          4
        //   309: aload           5
        //   311: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   314: aload_2        
        //   315: ifnull          322
        //   318: aload_2        
        //   319: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   322: aconst_null    
        //   323: astore_2       
        //   324: aload_1        
        //   325: astore          7
        //   327: aload           7
        //   329: ifnonnull       338
        //   332: aload_2        
        //   333: ifnonnull       338
        //   336: aconst_null    
        //   337: areturn        
        //   338: new             Lcom/alipay/sdk/m/p/b;
        //   341: dup            
        //   342: aload           7
        //   344: aload_2        
        //   345: invokespecial   com/alipay/sdk/m/p/b.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   348: areturn        
        //   349: astore_1       
        //   350: aload           4
        //   352: astore_2       
        //   353: aload_2        
        //   354: ifnull          361
        //   357: aload_2        
        //   358: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   361: aload_1        
        //   362: athrow         
        //   363: astore_2       
        //   364: goto            322
        //   367: astore_2       
        //   368: goto            361
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      17     300    306    Ljava/lang/Exception;
        //  3      17     293    300    Any
        //  21     26     283    293    Ljava/lang/Exception;
        //  21     26     349    353    Any
        //  30     38     283    293    Ljava/lang/Exception;
        //  30     38     349    353    Any
        //  42     47     283    293    Ljava/lang/Exception;
        //  42     47     349    353    Any
        //  51     58     283    293    Ljava/lang/Exception;
        //  51     58     349    353    Any
        //  62     71     283    293    Ljava/lang/Exception;
        //  62     71     349    353    Any
        //  75     83     283    293    Ljava/lang/Exception;
        //  75     83     349    353    Any
        //  87     92     283    293    Ljava/lang/Exception;
        //  87     92     349    353    Any
        //  96     103    283    293    Ljava/lang/Exception;
        //  96     103    349    353    Any
        //  107    112    272    283    Ljava/lang/Exception;
        //  107    112    349    353    Any
        //  116    124    272    283    Ljava/lang/Exception;
        //  116    124    349    353    Any
        //  128    133    272    283    Ljava/lang/Exception;
        //  128    133    349    353    Any
        //  137    144    272    283    Ljava/lang/Exception;
        //  137    144    349    353    Any
        //  148    154    272    283    Ljava/lang/Exception;
        //  148    154    349    353    Any
        //  162    167    272    283    Ljava/lang/Exception;
        //  162    167    349    353    Any
        //  171    179    272    283    Ljava/lang/Exception;
        //  171    179    349    353    Any
        //  187    194    272    283    Ljava/lang/Exception;
        //  187    194    349    353    Any
        //  198    210    272    283    Ljava/lang/Exception;
        //  198    210    349    353    Any
        //  217    224    272    283    Ljava/lang/Exception;
        //  217    224    349    353    Any
        //  228    234    272    283    Ljava/lang/Exception;
        //  228    234    349    353    Any
        //  238    242    272    283    Ljava/lang/Exception;
        //  238    242    349    353    Any
        //  246    251    272    283    Ljava/lang/Exception;
        //  246    251    349    353    Any
        //  256    261    266    272    Ljava/lang/Exception;
        //  309    314    349    353    Any
        //  318    322    363    367    Ljava/lang/Exception;
        //  357    361    367    371    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0210:
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
    
    public com.alipay.sdk.m.p.d a(final b b, final boolean b2, final String s) {
        if (b == null) {
            return null;
        }
        final byte[] bytes = b.b().getBytes();
        byte[] array2;
        final byte[] array = array2 = b.a().getBytes();
        boolean b3 = b2;
        if (b2) {
            try {
                array2 = com.alipay.sdk.m.n.b.a(array);
                b3 = b2;
            }
            catch (final Exception ex) {
                b3 = false;
                array2 = array;
            }
        }
        byte[] array3;
        if (this.a) {
            array3 = a(new byte[][] { bytes, a(this.b, com.alipay.sdk.m.l.a.f), b(this.b, array2, s) });
        }
        else {
            array3 = a(new byte[][] { bytes, array2 });
        }
        return new com.alipay.sdk.m.p.d(b3, array3);
    }
}
