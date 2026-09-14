package com.ta.utdid2.c.a;

import java.util.HashMap;
import java.io.File;

public class d
{
    private static final Object c;
    private File a;
    private HashMap<File, d.d$a> a;
    private final Object b;
    
    static {
        c = new Object();
    }
    
    public d(final String s) {
        this.b = new Object();
        this.a = (HashMap<File, d.d$a>)new HashMap();
        if (s != null && s.length() > 0) {
            this.a = new File(s);
            return;
        }
        throw new RuntimeException("Directory can not be empty");
    }
    
    private File a() {
        final Object b = this.b;
        synchronized (b) {
            return this.a;
        }
    }
    
    private static File a(final File file) {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)file.getPath()));
        sb.append(".bak");
        return new File(sb.toString());
    }
    
    private File a(final File file, final String s) {
        if (s.indexOf((int)File.separatorChar) < 0) {
            return new File(file, s);
        }
        final StringBuilder sb = new StringBuilder("File ");
        sb.append(s);
        sb.append(" contains a path separator");
        throw new IllegalArgumentException(sb.toString());
    }
    
    private File b(final String s) {
        final File a = this.a();
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
        sb.append(".xml");
        return this.a(a, sb.toString());
    }
    
    public b a(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokespecial   com/ta/utdid2/c/a/d.b:(Ljava/lang/String;)Ljava/io/File;
        //     5: astore          16
        //     7: getstatic       com/ta/utdid2/c/a/d.c:Ljava/lang/Object;
        //    10: astore_1       
        //    11: aload_1        
        //    12: dup            
        //    13: astore          17
        //    15: monitorenter   
        //    16: aload_0        
        //    17: getfield        com/ta/utdid2/c/a/d.a:Ljava/util/HashMap;
        //    20: aload           16
        //    22: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    25: checkcast       Lcom/ta/utdid2/c/a/d$a;
        //    28: astore          10
        //    30: aload           10
        //    32: ifnull          49
        //    35: aload           10
        //    37: invokevirtual   com/ta/utdid2/c/a/d$a.c:()Z
        //    40: ifne            49
        //    43: aload           17
        //    45: monitorexit    
        //    46: aload           10
        //    48: areturn        
        //    49: aload           17
        //    51: monitorexit    
        //    52: aload           16
        //    54: invokestatic    com/ta/utdid2/c/a/d.a:(Ljava/io/File;)Ljava/io/File;
        //    57: astore_1       
        //    58: aload_1        
        //    59: invokevirtual   java/io/File.exists:()Z
        //    62: ifeq            78
        //    65: aload           16
        //    67: invokevirtual   java/io/File.delete:()Z
        //    70: pop            
        //    71: aload_1        
        //    72: aload           16
        //    74: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //    77: pop            
        //    78: aload           16
        //    80: invokevirtual   java/io/File.exists:()Z
        //    83: ifeq            92
        //    86: aload           16
        //    88: invokevirtual   java/io/File.canRead:()Z
        //    91: pop            
        //    92: aload           16
        //    94: invokevirtual   java/io/File.exists:()Z
        //    97: istore          4
        //    99: aconst_null    
        //   100: astore          13
        //   102: aconst_null    
        //   103: astore          12
        //   105: aconst_null    
        //   106: astore          11
        //   108: aconst_null    
        //   109: astore          15
        //   111: aconst_null    
        //   112: astore          9
        //   114: aconst_null    
        //   115: astore          6
        //   117: aconst_null    
        //   118: astore          8
        //   120: aconst_null    
        //   121: astore          7
        //   123: aconst_null    
        //   124: astore          5
        //   126: aconst_null    
        //   127: astore          14
        //   129: aload           5
        //   131: astore_1       
        //   132: iload           4
        //   134: ifeq            579
        //   137: aload           5
        //   139: astore_1       
        //   140: aload           16
        //   142: invokevirtual   java/io/File.canRead:()Z
        //   145: ifeq            579
        //   148: aload           15
        //   150: astore_1       
        //   151: new             Ljava/io/FileInputStream;
        //   154: astore          5
        //   156: aload           15
        //   158: astore_1       
        //   159: aload           5
        //   161: aload           16
        //   163: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   166: aload           14
        //   168: astore          6
        //   170: aload           13
        //   172: astore          7
        //   174: aload           12
        //   176: astore          8
        //   178: aload           11
        //   180: astore          9
        //   182: aload           5
        //   184: invokestatic    com/ta/utdid2/c/a/e.a:(Ljava/io/InputStream;)Ljava/util/HashMap;
        //   187: astore_1       
        //   188: aload_1        
        //   189: astore          6
        //   191: aload_1        
        //   192: astore          7
        //   194: aload_1        
        //   195: astore          8
        //   197: aload_1        
        //   198: astore          9
        //   200: aload           5
        //   202: invokevirtual   java/io/FileInputStream.close:()V
        //   205: aload           5
        //   207: invokevirtual   java/io/FileInputStream.close:()V
        //   210: goto            579
        //   213: astore_1       
        //   214: goto            567
        //   217: astore_1       
        //   218: aload_1        
        //   219: astore          7
        //   221: goto            281
        //   224: astore_1       
        //   225: aload           7
        //   227: astore          6
        //   229: aload_1        
        //   230: astore          7
        //   232: goto            318
        //   235: astore_1       
        //   236: aload           5
        //   238: astore          6
        //   240: aload           8
        //   242: astore          5
        //   244: aload_1        
        //   245: astore          7
        //   247: goto            353
        //   250: astore_1       
        //   251: aload           5
        //   253: astore_1       
        //   254: aload           9
        //   256: astore          5
        //   258: goto            390
        //   261: astore          6
        //   263: aload_1        
        //   264: astore          5
        //   266: aload           6
        //   268: astore_1       
        //   269: goto            567
        //   272: astore          7
        //   274: aconst_null    
        //   275: astore          6
        //   277: aload           9
        //   279: astore          5
        //   281: aload           5
        //   283: astore_1       
        //   284: aload           7
        //   286: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   289: aload           6
        //   291: astore_1       
        //   292: aload           5
        //   294: ifnull          380
        //   297: aload           6
        //   299: astore_1       
        //   300: aload           5
        //   302: astore          6
        //   304: goto            372
        //   307: astore          7
        //   309: aconst_null    
        //   310: astore_1       
        //   311: aload           6
        //   313: astore          5
        //   315: aload_1        
        //   316: astore          6
        //   318: aload           5
        //   320: astore_1       
        //   321: aload           7
        //   323: invokevirtual   java/io/IOException.printStackTrace:()V
        //   326: aload           6
        //   328: astore_1       
        //   329: aload           5
        //   331: ifnull          380
        //   334: aload           6
        //   336: astore_1       
        //   337: aload           5
        //   339: astore          6
        //   341: goto            372
        //   344: astore          7
        //   346: aconst_null    
        //   347: astore          5
        //   349: aload           8
        //   351: astore          6
        //   353: aload           6
        //   355: astore_1       
        //   356: aload           7
        //   358: invokevirtual   java/io/FileNotFoundException.printStackTrace:()V
        //   361: aload           5
        //   363: astore_1       
        //   364: aload           6
        //   366: ifnull          380
        //   369: aload           5
        //   371: astore_1       
        //   372: aload_1        
        //   373: astore          7
        //   375: aload           6
        //   377: invokevirtual   java/io/FileInputStream.close:()V
        //   380: goto            579
        //   383: astore_1       
        //   384: aconst_null    
        //   385: astore          5
        //   387: aload           7
        //   389: astore_1       
        //   390: new             Ljava/io/FileInputStream;
        //   393: astore          6
        //   395: aload           6
        //   397: aload           16
        //   399: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   402: aload           6
        //   404: astore          7
        //   406: aload           6
        //   408: invokevirtual   java/io/FileInputStream.available:()I
        //   411: istore_3       
        //   412: aload           6
        //   414: astore          7
        //   416: iload_3        
        //   417: newarray        B
        //   419: astore_1       
        //   420: aload           6
        //   422: astore          7
        //   424: aload           6
        //   426: aload_1        
        //   427: invokevirtual   java/io/FileInputStream.read:([B)I
        //   430: pop            
        //   431: aload           6
        //   433: astore          7
        //   435: new             Ljava/lang/String;
        //   438: aload_1        
        //   439: iconst_0       
        //   440: iload_3        
        //   441: ldc             "UTF-8"
        //   443: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //   446: aload           6
        //   448: astore_1       
        //   449: aload_1        
        //   450: invokevirtual   java/io/FileInputStream.close:()V
        //   453: aload_1        
        //   454: astore          6
        //   456: goto            528
        //   459: astore          6
        //   461: aload_1        
        //   462: astore          6
        //   464: goto            528
        //   467: astore          8
        //   469: aload           6
        //   471: astore_1       
        //   472: goto            490
        //   475: astore          8
        //   477: aload           6
        //   479: astore_1       
        //   480: goto            510
        //   483: astore          5
        //   485: goto            556
        //   488: astore          8
        //   490: aload_1        
        //   491: astore          7
        //   493: aload           8
        //   495: invokevirtual   java/io/IOException.printStackTrace:()V
        //   498: aload_1        
        //   499: astore          6
        //   501: aload_1        
        //   502: ifnull          528
        //   505: goto            449
        //   508: astore          8
        //   510: aload_1        
        //   511: astore          7
        //   513: aload           8
        //   515: invokevirtual   java/io/FileNotFoundException.printStackTrace:()V
        //   518: aload_1        
        //   519: astore          6
        //   521: aload_1        
        //   522: ifnull          528
        //   525: goto            449
        //   528: aload           5
        //   530: astore_1       
        //   531: aload           6
        //   533: ifnull          380
        //   536: aload           5
        //   538: astore          7
        //   540: aload           6
        //   542: invokevirtual   java/io/FileInputStream.close:()V
        //   545: aload           5
        //   547: astore_1       
        //   548: goto            380
        //   551: astore          5
        //   553: aload           7
        //   555: astore_1       
        //   556: aload_1        
        //   557: ifnull          564
        //   560: aload_1        
        //   561: invokevirtual   java/io/FileInputStream.close:()V
        //   564: aload           5
        //   566: athrow         
        //   567: aload           5
        //   569: ifnull          577
        //   572: aload           5
        //   574: invokevirtual   java/io/FileInputStream.close:()V
        //   577: aload_1        
        //   578: athrow         
        //   579: getstatic       com/ta/utdid2/c/a/d.c:Ljava/lang/Object;
        //   582: astore          7
        //   584: aload           7
        //   586: dup            
        //   587: astore          18
        //   589: monitorenter   
        //   590: aload           10
        //   592: ifnull          608
        //   595: aload           10
        //   597: aload_1        
        //   598: invokevirtual   com/ta/utdid2/c/a/d$a.a:(Ljava/util/Map;)V
        //   601: aload           10
        //   603: astore          5
        //   605: goto            657
        //   608: aload_0        
        //   609: getfield        com/ta/utdid2/c/a/d.a:Ljava/util/HashMap;
        //   612: aload           16
        //   614: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   617: checkcast       Lcom/ta/utdid2/c/a/d$a;
        //   620: astore          6
        //   622: aload           6
        //   624: astore          5
        //   626: aload           6
        //   628: ifnonnull       657
        //   631: new             Lcom/ta/utdid2/c/a/d$a;
        //   634: astore          5
        //   636: aload           5
        //   638: aload           16
        //   640: iload_2        
        //   641: aload_1        
        //   642: invokespecial   com/ta/utdid2/c/a/d$a.<init>:(Ljava/io/File;ILjava/util/Map;)V
        //   645: aload_0        
        //   646: getfield        com/ta/utdid2/c/a/d.a:Ljava/util/HashMap;
        //   649: aload           16
        //   651: aload           5
        //   653: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   656: pop            
        //   657: aload           18
        //   659: monitorexit    
        //   660: aload           5
        //   662: areturn        
        //   663: astore_1       
        //   664: aload           18
        //   666: monitorexit    
        //   667: aload_1        
        //   668: athrow         
        //   669: astore          5
        //   671: aload           17
        //   673: monitorexit    
        //   674: aload           5
        //   676: athrow         
        //   677: astore          5
        //   679: goto            579
        //   682: astore_1       
        //   683: aload           7
        //   685: astore_1       
        //   686: goto            380
        //   689: astore          6
        //   691: goto            564
        //   694: astore          5
        //   696: goto            577
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  16     30     669    677    Any
        //  35     46     669    677    Any
        //  49     52     669    677    Any
        //  151    156    383    390    Lorg/xmlpull/v1/XmlPullParserException;
        //  151    156    344    353    Ljava/io/FileNotFoundException;
        //  151    156    307    318    Ljava/io/IOException;
        //  151    156    272    281    Ljava/lang/Exception;
        //  151    156    261    272    Any
        //  159    166    383    390    Lorg/xmlpull/v1/XmlPullParserException;
        //  159    166    344    353    Ljava/io/FileNotFoundException;
        //  159    166    307    318    Ljava/io/IOException;
        //  159    166    272    281    Ljava/lang/Exception;
        //  159    166    261    272    Any
        //  182    188    250    261    Lorg/xmlpull/v1/XmlPullParserException;
        //  182    188    235    250    Ljava/io/FileNotFoundException;
        //  182    188    224    235    Ljava/io/IOException;
        //  182    188    217    224    Ljava/lang/Exception;
        //  182    188    213    217    Any
        //  200    205    250    261    Lorg/xmlpull/v1/XmlPullParserException;
        //  200    205    235    250    Ljava/io/FileNotFoundException;
        //  200    205    224    235    Ljava/io/IOException;
        //  200    205    217    224    Ljava/lang/Exception;
        //  200    205    213    217    Any
        //  205    210    677    682    Any
        //  284    289    261    272    Any
        //  321    326    261    272    Any
        //  356    361    261    272    Any
        //  375    380    682    689    Any
        //  390    402    508    510    Ljava/io/FileNotFoundException;
        //  390    402    488    490    Ljava/io/IOException;
        //  390    402    483    488    Any
        //  406    412    475    483    Ljava/io/FileNotFoundException;
        //  406    412    467    475    Ljava/io/IOException;
        //  406    412    551    556    Any
        //  416    420    475    483    Ljava/io/FileNotFoundException;
        //  416    420    467    475    Ljava/io/IOException;
        //  416    420    551    556    Any
        //  424    431    475    483    Ljava/io/FileNotFoundException;
        //  424    431    467    475    Ljava/io/IOException;
        //  424    431    551    556    Any
        //  435    446    475    483    Ljava/io/FileNotFoundException;
        //  435    446    467    475    Ljava/io/IOException;
        //  435    446    551    556    Any
        //  449    453    459    467    Any
        //  493    498    551    556    Any
        //  513    518    551    556    Any
        //  540    545    682    689    Any
        //  560    564    689    694    Any
        //  564    567    261    272    Any
        //  572    577    694    699    Any
        //  595    601    663    669    Any
        //  608    622    663    669    Any
        //  631    657    663    669    Any
        //  657    660    663    669    Any
        //  664    667    663    669    Any
        //  671    674    669    677    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException
        //     at java.util.Collections$1.remove(Collections.java:4741)
        //     at java.util.AbstractCollection.removeAll(AbstractCollection.java:376)
        //     at q5.g.c(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1185)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2394)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
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
