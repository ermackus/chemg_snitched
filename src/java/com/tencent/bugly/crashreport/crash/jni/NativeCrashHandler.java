package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import android.os.Build$VERSION;
import java.io.File;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.proguard.w;
import android.content.Context;
import com.tencent.bugly.crashreport.a;

public class NativeCrashHandler implements a
{
    public static int JNI_CALL_TYPE = 1;
    private static NativeCrashHandler a;
    private static boolean l = false;
    private static boolean m = false;
    private static boolean o = true;
    private final Context b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final w d;
    private NativeExceptionHandler e;
    private String f;
    private final boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private b n;
    
    private NativeCrashHandler(final Context context, final com.tencent.bugly.crashreport.common.info.a c, final b n, final w d, final boolean g, String f) {
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.b = z.a(context);
        try {
            if (z.a(f)) {
                f = context.getDir("bugly", 0).getAbsolutePath();
            }
        }
        finally {
            final String c2 = com.tencent.bugly.crashreport.common.info.a.a(context).c;
            final StringBuilder sb = new StringBuilder("/data/data/");
            sb.append(c2);
            sb.append("/app_bugly");
            f = sb.toString();
        }
        this.n = n;
        this.f = f;
        this.c = c;
        this.d = d;
        this.g = g;
        this.e = (NativeExceptionHandler)new com.tencent.bugly.crashreport.crash.jni.a(context, c, n, com.tencent.bugly.crashreport.common.strategy.a.a());
    }
    
    private void a(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: astore          10
        //     4: monitorenter   
        //     5: aload_0        
        //     6: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.j:Z
        //     9: ifeq            26
        //    12: ldc             "[Native] Native crash report has already registered."
        //    14: iconst_0       
        //    15: anewarray       Ljava/lang/Object;
        //    18: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    21: pop            
        //    22: aload           10
        //    24: monitorexit    
        //    25: return         
        //    26: aload_0        
        //    27: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.i:Z
        //    30: istore          4
        //    32: iload           4
        //    34: ifeq            404
        //    37: aload_0        
        //    38: aload_0        
        //    39: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.f:Ljava/lang/String;
        //    42: iload_1        
        //    43: getstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.JNI_CALL_TYPE:I
        //    46: invokevirtual   com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.regist:(Ljava/lang/String;ZI)Ljava/lang/String;
        //    49: astore          7
        //    51: aload           7
        //    53: ifnull          745
        //    56: ldc             "[Native] Native Crash Report enable."
        //    58: iconst_0       
        //    59: anewarray       Ljava/lang/Object;
        //    62: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    65: pop            
        //    66: ldc             "[Native] Check extra jni for Bugly NDK v%s"
        //    68: iconst_1       
        //    69: anewarray       Ljava/lang/Object;
        //    72: dup            
        //    73: iconst_0       
        //    74: aload           7
        //    76: aastore        
        //    77: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    80: pop            
        //    81: ldc             "2.1.1"
        //    83: ldc             "."
        //    85: ldc             ""
        //    87: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    90: astore          9
        //    92: ldc             "2.3.0"
        //    94: ldc             "."
        //    96: ldc             ""
        //    98: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   101: astore          8
        //   103: aload           7
        //   105: ldc             "."
        //   107: ldc             ""
        //   109: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   112: astore          6
        //   114: aload           6
        //   116: invokevirtual   java/lang/String.length:()I
        //   119: iconst_2       
        //   120: if_icmpne       163
        //   123: new             Ljava/lang/StringBuilder;
        //   126: astore          5
        //   128: aload           5
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: aload           5
        //   135: aload           6
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: ldc             "0"
        //   143: astore          6
        //   145: aload           5
        //   147: aload           6
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: pop            
        //   153: aload           5
        //   155: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   158: astore          5
        //   160: goto            201
        //   163: aload           6
        //   165: astore          5
        //   167: aload           6
        //   169: invokevirtual   java/lang/String.length:()I
        //   172: iconst_1       
        //   173: if_icmpne       201
        //   176: new             Ljava/lang/StringBuilder;
        //   179: astore          5
        //   181: aload           5
        //   183: invokespecial   java/lang/StringBuilder.<init>:()V
        //   186: aload           5
        //   188: aload           6
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: pop            
        //   194: ldc             "00"
        //   196: astore          6
        //   198: goto            145
        //   201: aload           5
        //   203: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   206: aload           9
        //   208: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   211: if_icmplt       218
        //   214: iconst_1       
        //   215: putstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.l:Z
        //   218: aload           5
        //   220: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   223: aload           8
        //   225: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   228: if_icmplt       235
        //   231: iconst_1       
        //   232: putstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.m:Z
        //   235: getstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.m:Z
        //   238: ifeq            254
        //   241: ldc             "[Native] Info setting jni can be accessed."
        //   243: iconst_0       
        //   244: anewarray       Ljava/lang/Object;
        //   247: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   250: pop            
        //   251: goto            264
        //   254: ldc             "[Native] Info setting jni can not be accessed."
        //   256: iconst_0       
        //   257: anewarray       Ljava/lang/Object;
        //   260: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   263: pop            
        //   264: getstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.l:Z
        //   267: ifeq            283
        //   270: ldc             "[Native] Extra jni can be accessed."
        //   272: iconst_0       
        //   273: anewarray       Ljava/lang/Object;
        //   276: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   279: pop            
        //   280: goto            293
        //   283: ldc             "[Native] Extra jni can not be accessed."
        //   285: iconst_0       
        //   286: anewarray       Ljava/lang/Object;
        //   289: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   292: pop            
        //   293: aload_0        
        //   294: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   297: aload           7
        //   299: putfield        com/tencent/bugly/crashreport/common/info/a.o:Ljava/lang/String;
        //   302: ldc             "-"
        //   304: aload_0        
        //   305: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   308: getfield        com/tencent/bugly/crashreport/common/info/a.o:Ljava/lang/String;
        //   311: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   314: astore          5
        //   316: aload_0        
        //   317: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   320: getfield        com/tencent/bugly/crashreport/common/info/a.f:Ljava/lang/String;
        //   323: aload           5
        //   325: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   328: ifne            360
        //   331: aload_0        
        //   332: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   335: aload_0        
        //   336: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   339: getfield        com/tencent/bugly/crashreport/common/info/a.f:Ljava/lang/String;
        //   342: ldc             "-"
        //   344: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   347: aload_0        
        //   348: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   351: getfield        com/tencent/bugly/crashreport/common/info/a.o:Ljava/lang/String;
        //   354: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   357: putfield        com/tencent/bugly/crashreport/common/info/a.f:Ljava/lang/String;
        //   360: ldc             "comInfo.sdkVersion %s"
        //   362: iconst_1       
        //   363: anewarray       Ljava/lang/Object;
        //   366: dup            
        //   367: iconst_0       
        //   368: aload_0        
        //   369: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   372: getfield        com/tencent/bugly/crashreport/common/info/a.f:Ljava/lang/String;
        //   375: aastore        
        //   376: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   379: pop            
        //   380: aload_0        
        //   381: iconst_1       
        //   382: putfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.j:Z
        //   385: aload           10
        //   387: monitorexit    
        //   388: return         
        //   389: astore          5
        //   391: ldc             "[Native] Failed to load Bugly SO file."
        //   393: iconst_0       
        //   394: anewarray       Ljava/lang/Object;
        //   397: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   400: pop            
        //   401: goto            745
        //   404: aload_0        
        //   405: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.h:Z
        //   408: istore          4
        //   410: iload           4
        //   412: ifeq            745
        //   415: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //   418: astore          8
        //   420: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //   423: astore          6
        //   425: aload_0        
        //   426: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.f:Ljava/lang/String;
        //   429: astore          5
        //   431: aload_0        
        //   432: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.b:Landroid/content/Context;
        //   435: iconst_0       
        //   436: invokestatic    com/tencent/bugly/crashreport/common/info/b.a:(Landroid/content/Context;Z)Ljava/lang/String;
        //   439: astore          7
        //   441: iconst_5       
        //   442: istore_3       
        //   443: iload_1        
        //   444: ifeq            452
        //   447: iconst_1       
        //   448: istore_2       
        //   449: goto            454
        //   452: iconst_5       
        //   453: istore_2       
        //   454: ldc             "com.tencent.feedback.eup.jni.NativeExceptionUpload"
        //   456: ldc             "registNativeExceptionHandler2"
        //   458: aconst_null    
        //   459: iconst_4       
        //   460: anewarray       Ljava/lang/Class;
        //   463: dup            
        //   464: iconst_0       
        //   465: ldc             Ljava/lang/String;.class
        //   467: aastore        
        //   468: dup            
        //   469: iconst_1       
        //   470: ldc             Ljava/lang/String;.class
        //   472: aastore        
        //   473: dup            
        //   474: iconst_2       
        //   475: aload           8
        //   477: aastore        
        //   478: dup            
        //   479: iconst_3       
        //   480: aload           6
        //   482: aastore        
        //   483: iconst_4       
        //   484: anewarray       Ljava/lang/Object;
        //   487: dup            
        //   488: iconst_0       
        //   489: aload           5
        //   491: aastore        
        //   492: dup            
        //   493: iconst_1       
        //   494: aload           7
        //   496: aastore        
        //   497: dup            
        //   498: iconst_2       
        //   499: iload_2        
        //   500: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   503: aastore        
        //   504: dup            
        //   505: iconst_3       
        //   506: iconst_1       
        //   507: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   510: aastore        
        //   511: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
        //   514: checkcast       Ljava/lang/String;
        //   517: astore          6
        //   519: aload           6
        //   521: astore          5
        //   523: aload           6
        //   525: ifnonnull       610
        //   528: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //   531: astore          5
        //   533: aload_0        
        //   534: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.f:Ljava/lang/String;
        //   537: astore          7
        //   539: aload_0        
        //   540: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.b:Landroid/content/Context;
        //   543: iconst_0       
        //   544: invokestatic    com/tencent/bugly/crashreport/common/info/b.a:(Landroid/content/Context;Z)Ljava/lang/String;
        //   547: astore          6
        //   549: invokestatic    com/tencent/bugly/crashreport/common/info/a.b:()Lcom/tencent/bugly/crashreport/common/info/a;
        //   552: pop            
        //   553: invokestatic    com/tencent/bugly/crashreport/common/info/a.C:()I
        //   556: istore_2       
        //   557: ldc             "com.tencent.feedback.eup.jni.NativeExceptionUpload"
        //   559: ldc             "registNativeExceptionHandler"
        //   561: aconst_null    
        //   562: iconst_3       
        //   563: anewarray       Ljava/lang/Class;
        //   566: dup            
        //   567: iconst_0       
        //   568: ldc             Ljava/lang/String;.class
        //   570: aastore        
        //   571: dup            
        //   572: iconst_1       
        //   573: ldc             Ljava/lang/String;.class
        //   575: aastore        
        //   576: dup            
        //   577: iconst_2       
        //   578: aload           5
        //   580: aastore        
        //   581: iconst_3       
        //   582: anewarray       Ljava/lang/Object;
        //   585: dup            
        //   586: iconst_0       
        //   587: aload           7
        //   589: aastore        
        //   590: dup            
        //   591: iconst_1       
        //   592: aload           6
        //   594: aastore        
        //   595: dup            
        //   596: iconst_2       
        //   597: iload_2        
        //   598: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   601: aastore        
        //   602: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
        //   605: checkcast       Ljava/lang/String;
        //   608: astore          5
        //   610: aload           5
        //   612: ifnull          745
        //   615: aload_0        
        //   616: iconst_1       
        //   617: putfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.j:Z
        //   620: aload_0        
        //   621: getfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.c:Lcom/tencent/bugly/crashreport/common/info/a;
        //   624: aload           5
        //   626: putfield        com/tencent/bugly/crashreport/common/info/a.o:Ljava/lang/String;
        //   629: ldc             "com.tencent.feedback.eup.jni.NativeExceptionUpload"
        //   631: ldc             "checkExtraJni"
        //   633: aconst_null    
        //   634: iconst_1       
        //   635: anewarray       Ljava/lang/Class;
        //   638: dup            
        //   639: iconst_0       
        //   640: ldc             Ljava/lang/String;.class
        //   642: aastore        
        //   643: iconst_1       
        //   644: anewarray       Ljava/lang/Object;
        //   647: dup            
        //   648: iconst_0       
        //   649: aload           5
        //   651: aastore        
        //   652: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
        //   655: checkcast       Ljava/lang/Boolean;
        //   658: astore          5
        //   660: aload           5
        //   662: ifnull          673
        //   665: aload           5
        //   667: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   670: putstatic       com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.l:Z
        //   673: ldc             "com.tencent.feedback.eup.jni.NativeExceptionUpload"
        //   675: ldc             "enableHandler"
        //   677: aconst_null    
        //   678: iconst_1       
        //   679: anewarray       Ljava/lang/Class;
        //   682: dup            
        //   683: iconst_0       
        //   684: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //   687: aastore        
        //   688: iconst_1       
        //   689: anewarray       Ljava/lang/Object;
        //   692: dup            
        //   693: iconst_0       
        //   694: iconst_1       
        //   695: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   698: aastore        
        //   699: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
        //   702: pop            
        //   703: iload_3        
        //   704: istore_2       
        //   705: iload_1        
        //   706: ifeq            711
        //   709: iconst_1       
        //   710: istore_2       
        //   711: ldc             "com.tencent.feedback.eup.jni.NativeExceptionUpload"
        //   713: ldc             "setLogMode"
        //   715: aconst_null    
        //   716: iconst_1       
        //   717: anewarray       Ljava/lang/Class;
        //   720: dup            
        //   721: iconst_0       
        //   722: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //   725: aastore        
        //   726: iconst_1       
        //   727: anewarray       Ljava/lang/Object;
        //   730: dup            
        //   731: iconst_0       
        //   732: iload_2        
        //   733: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   736: aastore        
        //   737: invokestatic    com/tencent/bugly/proguard/z.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
        //   740: pop            
        //   741: aload           10
        //   743: monitorexit    
        //   744: return         
        //   745: aload_0        
        //   746: iconst_0       
        //   747: putfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.i:Z
        //   750: aload_0        
        //   751: iconst_0       
        //   752: putfield        com/tencent/bugly/crashreport/crash/jni/NativeCrashHandler.h:Z
        //   755: aload           10
        //   757: monitorexit    
        //   758: return         
        //   759: astore          5
        //   761: aload           10
        //   763: monitorexit    
        //   764: aload           5
        //   766: athrow         
        //   767: astore          5
        //   769: goto            235
        //   772: astore          5
        //   774: goto            745
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      22     759    767    Any
        //  26     32     759    767    Any
        //  37     51     389    404    Any
        //  56     141    389    404    Any
        //  145    160    389    404    Any
        //  167    194    389    404    Any
        //  201    218    767    772    Any
        //  218    235    767    772    Any
        //  235    251    389    404    Any
        //  254    264    389    404    Any
        //  264    280    389    404    Any
        //  283    293    389    404    Any
        //  293    360    389    404    Any
        //  360    385    389    404    Any
        //  391    401    759    767    Any
        //  404    410    759    767    Any
        //  415    441    772    777    Any
        //  454    519    772    777    Any
        //  528    610    772    777    Any
        //  615    660    772    777    Any
        //  665    673    772    777    Any
        //  673    703    772    777    Any
        //  711    741    772    777    Any
        //  745    755    759    767    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 407, Size: 407
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
    
    private boolean a(final int n, final String s) {
        if (this.i) {
            if (NativeCrashHandler.m) {
                try {
                    this.setNativeInfo(n, s);
                    return true;
                }
                catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
                    NativeCrashHandler.m = false;
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    private static boolean a(final String s, final boolean b) {
        boolean b2 = false;
        try {
            x.a("[Native] Trying to load so: %s", new Object[] { s });
            if (b) {
                System.load(s);
            }
            else {
                System.loadLibrary(s);
            }
            try {
                x.a("[Native] Successfully loaded SO: %s", new Object[] { s });
            }
            finally {}
        }
        finally {
            b2 = false;
        }
        final Throwable t;
        x.d(t.getMessage(), new Object[0]);
        x.d("[Native] Failed to load so: %s", new Object[] { s });
        return b2;
    }
    
    private void b(final boolean b) {
        monitorenter(this);
        Label_0015: {
            if (!b) {
                break Label_0015;
            }
            try {
                this.startNativeMonitor();
                return;
                this.c();
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    private void c() {
        synchronized (this) {
            if (!this.j) {
                x.d("[Native] Native crash report has already unregistered.", new Object[0]);
                return;
            }
            try {
                if (this.unregist() != null) {
                    x.a("[Native] Successfully closed native crash report.", new Object[0]);
                    this.j = false;
                    return;
                }
            }
            finally {
                x.c("[Native] Failed to close native crash report.", new Object[0]);
            }
            try {
                z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", (Object)null, new Class[] { Boolean.TYPE }, new Object[] { false });
                this.j = false;
                x.a("[Native] Successfully closed native crash report.", new Object[0]);
            }
            finally {
                x.c("[Native] Failed to close native crash report.", new Object[0]);
                this.i = false;
                this.h = false;
            }
        }
    }
    
    private void c(final boolean k) {
        synchronized (this) {
            if (this.k != k) {
                x.a("user change native %b", new Object[] { k });
                this.k = k;
            }
        }
    }
    
    public static NativeCrashHandler getInstance() {
        synchronized (NativeCrashHandler.class) {
            return NativeCrashHandler.a;
        }
    }
    
    public static NativeCrashHandler getInstance(final Context context, final com.tencent.bugly.crashreport.common.info.a a, final b b, final com.tencent.bugly.crashreport.common.strategy.a a2, final w w, final boolean b2, final String s) {
        synchronized (NativeCrashHandler.class) {
            if (NativeCrashHandler.a == null) {
                NativeCrashHandler.a = new NativeCrashHandler(context, a, b, w, b2, s);
            }
            return NativeCrashHandler.a;
        }
    }
    
    public static boolean isShouldHandleInJava() {
        return NativeCrashHandler.o;
    }
    
    public static void setShouldHandleInJava(final boolean o) {
        NativeCrashHandler.o = o;
        final NativeCrashHandler a = NativeCrashHandler.a;
        if (a != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(o);
            a.a(999, sb.toString());
        }
    }
    
    protected final void a() {
        final long b = z.b();
        final long g = com.tencent.bugly.crashreport.crash.c.g;
        final long b2 = z.b();
        final File file = new File(this.f);
        if (file.exists() && file.isDirectory()) {
            try {
                final File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    if (listFiles.length != 0) {
                        final int length = listFiles.length;
                        int i = 0;
                        int n = 0;
                        int n2 = 0;
                        while (i < length) {
                            final File file2 = listFiles[i];
                            final long lastModified = file2.lastModified();
                            int n3 = 0;
                            int n4 = 0;
                            Label_0169: {
                                if (lastModified >= b - g) {
                                    n3 = n;
                                    n4 = n2;
                                    if (lastModified < b2 + 86400000L) {
                                        break Label_0169;
                                    }
                                }
                                x.a("[Native] Delete record file: %s", new Object[] { file2.getAbsolutePath() });
                                n3 = ++n;
                                n4 = n2;
                                if (file2.delete()) {
                                    n4 = n2 + 1;
                                    n3 = n;
                                }
                            }
                            ++i;
                            n = n3;
                            n2 = n4;
                        }
                        x.c("[Native] Number of record files overdue: %d, has deleted: %d", new Object[] { n, n2 });
                    }
                }
            }
            finally {
                final Throwable t;
                x.a(t);
            }
        }
    }
    
    public boolean appendLogToNative(final String s, final String s2, final String s3) {
        if (!this.h && !this.i) {
            return false;
        }
        if (!NativeCrashHandler.l) {
            return false;
        }
        if (s != null && s2 != null) {
            if (s3 != null) {
                try {
                    if (this.i) {
                        return this.appendNativeLog(s, s2, s3);
                    }
                    final Boolean b = (Boolean)z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", (Object)null, new Class[] { String.class, String.class, String.class }, new Object[] { s, s2, s3 });
                    return b != null && b;
                }
                catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
                    NativeCrashHandler.l = false;
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    protected native boolean appendNativeLog(final String p0, final String p1, final String p2);
    
    protected native boolean appendWholeNativeLog(final String p0);
    
    public void checkUploadRecordCrash() {
        this.d.a((Runnable)new NativeCrashHandler$1(this));
    }
    
    public void enableCatchAnrTrace() {
        if (Build$VERSION.SDK_INT <= 29 && Build$VERSION.SDK_INT >= 26 && com.tencent.bugly.crashreport.common.info.b.c(this.b).contains((CharSequence)"Oppo")) {
            NativeCrashHandler.JNI_CALL_TYPE |= 0x2;
        }
    }
    
    public boolean filterSigabrtSysLog() {
        return this.a(998, "true");
    }
    
    public String getDumpFilePath() {
        synchronized (this) {
            return this.f;
        }
    }
    
    public String getLogFromNative() {
        if (!this.h && !this.i) {
            return null;
        }
        if (!NativeCrashHandler.l) {
            return null;
        }
        try {
            if (this.i) {
                return this.getNativeLog();
            }
            return (String)z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", (Object)null, (Class[])null, (Object[])null);
        }
        catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
            NativeCrashHandler.l = false;
            return null;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }
    
    protected native String getNativeKeyValueList();
    
    protected native String getNativeLog();
    
    public boolean isEnableCatchAnrTrace() {
        return (NativeCrashHandler.JNI_CALL_TYPE & 0x2) == 0x2;
    }
    
    public boolean isUserOpened() {
        synchronized (this) {
            return this.k;
        }
    }
    
    public void onStrategyChanged(final StrategyBean strategyBean) {
        monitorenter(this);
        Label_0040: {
            if (strategyBean == null) {
                break Label_0040;
            }
            try {
                if (strategyBean.e != this.j) {
                    x.d("server native changed to %b", new Object[] { strategyBean.e });
                }
                final boolean b = com.tencent.bugly.crashreport.common.strategy.a.a().c().e && this.k;
                if (b != this.j) {
                    x.a("native changed to %b", new Object[] { b });
                    this.b(b);
                }
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    public boolean putKeyValueToNative(final String s, final String s2) {
        if (!this.h && !this.i) {
            return false;
        }
        if (!NativeCrashHandler.l) {
            return false;
        }
        if (s != null) {
            if (s2 != null) {
                try {
                    if (this.i) {
                        return this.putNativeKeyValue(s, s2);
                    }
                    final Boolean b = (Boolean)z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", (Object)null, new Class[] { String.class, String.class }, new Object[] { s, s2 });
                    return b != null && b;
                }
                catch (final UnsatisfiedLinkError unsatisfiedLinkError) {
                    NativeCrashHandler.l = false;
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    protected native boolean putNativeKeyValue(final String p0, final String p1);
    
    protected native String regist(final String p0, final boolean p1, final int p2);
    
    public void removeEmptyNativeRecordFiles() {
        com.tencent.bugly.crashreport.crash.jni.b.c(this.f);
    }
    
    protected native String removeNativeKeyValue(final String p0);
    
    public void setDumpFilePath(final String f) {
        synchronized (this) {
            this.f = f;
        }
    }
    
    public boolean setNativeAppChannel(final String s) {
        return this.a(12, s);
    }
    
    public boolean setNativeAppPackage(final String s) {
        return this.a(13, s);
    }
    
    public boolean setNativeAppVersion(final String s) {
        return this.a(10, s);
    }
    
    protected native void setNativeInfo(final int p0, final String p1);
    
    public boolean setNativeIsAppForeground(final boolean b) {
        String s;
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        return this.a(14, s);
    }
    
    public boolean setNativeLaunchTime(final long n) {
        try {
            return this.a(15, String.valueOf(n));
        }
        catch (final NumberFormatException ex) {
            if (!x.a((Throwable)ex)) {
                ex.printStackTrace();
            }
            return false;
        }
    }
    
    public boolean setNativeUserId(final String s) {
        return this.a(11, s);
    }
    
    public void setUserOpened(final boolean b) {
        synchronized (this) {
            this.c(b);
            final boolean userOpened = this.isUserOpened();
            final com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
            boolean b2 = userOpened;
            if (a != null) {
                b2 = (userOpened && a.c().e);
            }
            if (b2 != this.j) {
                x.a("native changed to %b", new Object[] { b2 });
                this.b(b2);
            }
        }
    }
    
    public void startNativeMonitor() {
        synchronized (this) {
            if (this.i || this.h) {
                this.a(this.g);
                return;
            }
            final String s = "Bugly";
            final boolean b = !z.a(this.c.n);
            String n = this.c.n;
            if (!b) {
                this.c.getClass();
                n = s;
            }
            if (!(this.i = a(n, b)) && !this.h) {
                return;
            }
            this.a(this.g);
            if (NativeCrashHandler.l) {
                this.setNativeAppVersion(this.c.k);
                this.setNativeAppChannel(this.c.m);
                this.setNativeAppPackage(this.c.c);
                this.setNativeUserId(this.c.g());
                this.setNativeIsAppForeground(this.c.a());
                this.setNativeLaunchTime(this.c.a);
            }
        }
    }
    
    protected native void testCrash();
    
    public void testNativeCrash() {
        if (!this.i) {
            x.d("[Native] Bugly SO file has not been load.", new Object[0]);
            return;
        }
        this.testCrash();
    }
    
    public void testNativeCrash(final boolean b, final boolean b2, final boolean b3) {
        final StringBuilder sb = new StringBuilder();
        sb.append(b);
        this.a(16, sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(b2);
        this.a(17, sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(b3);
        this.a(18, sb3.toString());
        this.testNativeCrash();
    }
    
    protected native String unregist();
}
