package com.alibaba.sdk.android.man.crashreporter.a.c;

import java.util.Map;
import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.a.c.a.a;

public class b
{
    private a a;
    
    public b() {
        this.a = null;
    }
    
    public byte[] a(final a p0, final Context p1, final Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          1178
        //     4: aload_1        
        //     5: ifnull          1178
        //     8: aload_3        
        //     9: ifnull          1178
        //    12: aload_0        
        //    13: aload_1        
        //    14: putfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //    17: ldc             "start build crash file"
        //    19: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //    22: aload_3        
        //    23: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.I:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //    26: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    31: checkcast       Ljava/lang/String;
        //    34: astore          8
        //    36: aload_3        
        //    37: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.H:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //    40: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    45: checkcast       Ljava/lang/String;
        //    48: astore          5
        //    50: aload_3        
        //    51: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.G:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //    54: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    59: checkcast       Ljava/lang/String;
        //    62: astore          7
        //    64: aload_3        
        //    65: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.v:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //    68: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    73: checkcast       Ljava/lang/String;
        //    76: astore_1       
        //    77: aload_1        
        //    78: ifnull          112
        //    81: aload_1        
        //    82: invokevirtual   java/lang/String.length:()I
        //    85: ifgt            91
        //    88: goto            112
        //    91: aload_1        
        //    92: ldc             "BACKGROUND:"
        //    94: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    97: ifeq            106
        //   100: iconst_1       
        //   101: istore          4
        //   103: goto            118
        //   106: iconst_0       
        //   107: istore          4
        //   109: goto            118
        //   112: aload_2        
        //   113: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.a:(Landroid/content/Context;)Z
        //   116: istore          4
        //   118: aload_3        
        //   119: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.w:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   122: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   127: checkcast       Ljava/lang/String;
        //   130: astore          6
        //   132: aload           6
        //   134: astore_1       
        //   135: aload           6
        //   137: ifnonnull       145
        //   140: aload_2        
        //   141: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.b:(Landroid/content/Context;)Ljava/lang/String;
        //   144: astore_1       
        //   145: ldc             "start buildSysMessage"
        //   147: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //   150: aload_0        
        //   151: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   154: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   157: ldc             "build"
        //   159: getstatic       android/os/Build.ID:Ljava/lang/String;
        //   162: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   167: pop            
        //   168: aload_0        
        //   169: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   172: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   175: astore          9
        //   177: aload           5
        //   179: ifnonnull       189
        //   182: ldc             ""
        //   184: astore          6
        //   186: goto            193
        //   189: aload           5
        //   191: astore          6
        //   193: aload           9
        //   195: ldc             "imei"
        //   197: aload           6
        //   199: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   204: pop            
        //   205: aload_0        
        //   206: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   209: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   212: astore          9
        //   214: aload           8
        //   216: astore          6
        //   218: aload           8
        //   220: ifnonnull       227
        //   223: ldc             ""
        //   225: astore          6
        //   227: aload           9
        //   229: ldc             "imsi"
        //   231: aload           6
        //   233: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   238: pop            
        //   239: aload_0        
        //   240: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   243: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   246: astore          8
        //   248: aload           5
        //   250: astore          6
        //   252: aload           5
        //   254: ifnonnull       261
        //   257: ldc             ""
        //   259: astore          6
        //   261: aload           8
        //   263: ldc             "deviceId"
        //   265: aload           6
        //   267: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   272: pop            
        //   273: aload_0        
        //   274: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   277: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   280: astore          6
        //   282: aload           7
        //   284: astore          5
        //   286: aload           7
        //   288: ifnonnull       295
        //   291: ldc             ""
        //   293: astore          5
        //   295: aload           6
        //   297: ldc             "utdid"
        //   299: aload           5
        //   301: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   306: pop            
        //   307: aload_0        
        //   308: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   311: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   314: ldc             "brand"
        //   316: getstatic       android/os/Build.BRAND:Ljava/lang/String;
        //   319: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   324: pop            
        //   325: aload_0        
        //   326: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   329: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   332: ldc             "deviceModel"
        //   334: aload_3        
        //   335: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.m:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   338: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   343: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   348: pop            
        //   349: aload_0        
        //   350: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   353: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   356: ldc             "cpuModel"
        //   358: invokestatic    com/alibaba/sdk/android/man/crashreporter/e/a.f:()Ljava/lang/String;
        //   361: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   366: pop            
        //   367: aload_0        
        //   368: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   371: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   374: ldc             "resolution"
        //   376: aload_3        
        //   377: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.s:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   380: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   385: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   390: pop            
        //   391: aload_0        
        //   392: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   395: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   398: ldc             "os"
        //   400: ldc             "ANDROID"
        //   402: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   407: pop            
        //   408: aload_0        
        //   409: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   412: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   415: ldc             "osVersion"
        //   417: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //   420: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   425: pop            
        //   426: aload_0        
        //   427: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   430: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   433: ldc             "displayName"
        //   435: getstatic       android/os/Build.DISPLAY:Ljava/lang/String;
        //   438: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   443: pop            
        //   444: aload_0        
        //   445: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   448: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   451: ldc             "firmwareName"
        //   453: getstatic       android/os/Build.FINGERPRINT:Ljava/lang/String;
        //   456: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   461: pop            
        //   462: aload_0        
        //   463: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   466: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   469: ldc             "firmwareVersion"
        //   471: getstatic       android/os/Build$VERSION.INCREMENTAL:Ljava/lang/String;
        //   474: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   479: pop            
        //   480: aload_0        
        //   481: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   484: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   487: ldc             "firmwareBuild"
        //   489: getstatic       android/os/Build$VERSION.CODENAME:Ljava/lang/String;
        //   492: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   497: pop            
        //   498: aload_0        
        //   499: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   502: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   505: ldc             "memorySizes"
        //   507: aload_2        
        //   508: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.a:(Landroid/content/Context;)D
        //   511: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   514: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   519: pop            
        //   520: aload_0        
        //   521: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   524: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   527: ldc             "memoryUsed"
        //   529: aload_2        
        //   530: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.b:(Landroid/content/Context;)D
        //   533: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   536: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   541: pop            
        //   542: iconst_1       
        //   543: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.a:(Z)[J
        //   546: astore          5
        //   548: aload_0        
        //   549: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   552: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   555: ldc             "internalStorageTotal"
        //   557: aload           5
        //   559: iconst_0       
        //   560: laload         
        //   561: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   564: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   569: pop            
        //   570: aload_0        
        //   571: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   574: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   577: ldc             "internalStorageFree"
        //   579: aload           5
        //   581: iconst_1       
        //   582: laload         
        //   583: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   586: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   591: pop            
        //   592: aload_0        
        //   593: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   596: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   599: ldc             "internalStorageAvailable"
        //   601: aload           5
        //   603: iconst_2       
        //   604: laload         
        //   605: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   608: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   613: pop            
        //   614: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.getExternalStorageState:()Ljava/lang/String;
        //   617: astore          5
        //   619: aload_0        
        //   620: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   623: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   626: ldc             "externalStorageState"
        //   628: aload           5
        //   630: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   635: pop            
        //   636: ldc             "mounted"
        //   638: aload           5
        //   640: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   643: ifeq            718
        //   646: iconst_0       
        //   647: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.a:(Z)[J
        //   650: astore          5
        //   652: aload_0        
        //   653: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   656: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   659: ldc             "externalStorageTotal"
        //   661: aload           5
        //   663: iconst_0       
        //   664: laload         
        //   665: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   668: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   673: pop            
        //   674: aload_0        
        //   675: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   678: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   681: ldc             "externalStorageFree"
        //   683: aload           5
        //   685: iconst_1       
        //   686: laload         
        //   687: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   690: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   695: pop            
        //   696: aload_0        
        //   697: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   700: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   703: ldc             "externalStorageAvailable"
        //   705: aload           5
        //   707: iconst_2       
        //   708: laload         
        //   709: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   712: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   717: pop            
        //   718: aload_0        
        //   719: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   722: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   725: ldc             "isInstallOnSDCard"
        //   727: aload_2        
        //   728: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.b:(Landroid/content/Context;)Z
        //   731: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   734: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   739: pop            
        //   740: aload_0        
        //   741: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   744: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   747: ldc             "country"
        //   749: aload_3        
        //   750: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.t:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   753: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   758: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   763: pop            
        //   764: aload_0        
        //   765: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   768: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   771: ldc             "language"
        //   773: aload_3        
        //   774: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.u:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   777: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   782: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   787: pop            
        //   788: ldc             "start buildOtherMessage"
        //   790: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //   793: aload_0        
        //   794: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   797: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   800: ldc             "parentProcessName"
        //   802: ldc             "launchd [1]"
        //   804: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   809: pop            
        //   810: aload_0        
        //   811: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   814: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   817: ldc             "processName"
        //   819: aload_2        
        //   820: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.a:(Landroid/content/Context;)Ljava/lang/String;
        //   823: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   828: pop            
        //   829: aload_0        
        //   830: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   833: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   836: ldc             "isRoot"
        //   838: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.b:()Z
        //   841: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   844: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   849: pop            
        //   850: aload_0        
        //   851: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   854: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   857: ldc             "isBackground"
        //   859: iload           4
        //   861: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   864: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   869: pop            
        //   870: aload_0        
        //   871: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   874: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   877: ldc             "clientIp"
        //   879: aload_2        
        //   880: invokestatic    com/alibaba/sdk/android/man/crashreporter/a/c/a.c:(Landroid/content/Context;)Ljava/lang/String;
        //   883: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   888: pop            
        //   889: aload_0        
        //   890: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   893: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   896: ldc             "carrier"
        //   898: aload_3        
        //   899: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.z:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   902: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   907: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   912: pop            
        //   913: aload_0        
        //   914: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   917: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   920: ldc             "access"
        //   922: aload_3        
        //   923: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.A:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   926: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   931: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   936: pop            
        //   937: aload_0        
        //   938: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   941: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   944: ldc             "accessSubtype"
        //   946: aload_3        
        //   947: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.B:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   950: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   955: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   960: pop            
        //   961: aload_0        
        //   962: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   965: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   968: ldc_w           "view"
        //   971: aload_1        
        //   972: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   977: pop            
        //   978: aload_0        
        //   979: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //   982: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //   985: ldc_w           "bundle"
        //   988: aload_3        
        //   989: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.y:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //   992: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   997: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1002: pop            
        //  1003: aload_0        
        //  1004: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1007: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //  1010: ldc_w           "operations"
        //  1013: ldc             ""
        //  1015: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1020: pop            
        //  1021: ldc_w           "start buildCrashMessage"
        //  1024: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //  1027: aload_3        
        //  1028: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.d:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //  1031: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1036: checkcast       Ljava/lang/String;
        //  1039: astore_1       
        //  1040: aload_1        
        //  1041: ifnull          1109
        //  1044: aload_1        
        //  1045: ldc_w           "I/CrashReport"
        //  1048: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //  1051: istore          4
        //  1053: iload           4
        //  1055: ifne            1091
        //  1058: aload_1        
        //  1059: ldc_w           "D/CrashReport"
        //  1062: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //  1065: ifeq            1071
        //  1068: goto            1091
        //  1071: aload_0        
        //  1072: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1075: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //  1078: ldc_w           "sysLog"
        //  1081: aload_1        
        //  1082: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1087: pop            
        //  1088: goto            1109
        //  1091: aload_0        
        //  1092: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1095: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //  1098: ldc_w           "sysLog"
        //  1101: ldc             ""
        //  1103: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1108: pop            
        //  1109: aload_0        
        //  1110: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1113: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //  1116: ldc_w           "eventLog"
        //  1119: aload_3        
        //  1120: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.e:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //  1123: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1128: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1133: pop            
        //  1134: aload_0        
        //  1135: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1138: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/a/a.c:Ljava/util/Map;
        //  1141: ldc_w           "radioLog"
        //  1144: aload_3        
        //  1145: getstatic       com/alibaba/sdk/android/man/crashreporter/global/a.f:Lcom/alibaba/sdk/android/man/crashreporter/global/a;
        //  1148: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1153: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1158: pop            
        //  1159: aload_0        
        //  1160: getfield        com/alibaba/sdk/android/man/crashreporter/a/c/b.a:Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;
        //  1163: invokevirtual   com/alibaba/sdk/android/man/crashreporter/a/c/a/a.a:()[B
        //  1166: astore_1       
        //  1167: goto            1180
        //  1170: astore_1       
        //  1171: ldc_w           "Build data error."
        //  1174: aload_1        
        //  1175: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1178: aconst_null    
        //  1179: astore_1       
        //  1180: ldc_w           "end build crash file"
        //  1183: invokestatic    com/alibaba/sdk/android/man/crashreporter/b/a.e:(Ljava/lang/String;)V
        //  1186: aload_1        
        //  1187: areturn        
        //  1188: astore_1       
        //  1189: goto            1109
        //    Signature:
        //  (Lcom/alibaba/sdk/android/man/crashreporter/a/c/a/a;Landroid/content/Context;Ljava/util/Map<Lcom/alibaba/sdk/android/man/crashreporter/global/a;Ljava/lang/String;>;)[B
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     77     1170   1178   Ljava/lang/Exception;
        //  81     88     1170   1178   Ljava/lang/Exception;
        //  91     100    1170   1178   Ljava/lang/Exception;
        //  112    118    1170   1178   Ljava/lang/Exception;
        //  118    132    1170   1178   Ljava/lang/Exception;
        //  140    145    1170   1178   Ljava/lang/Exception;
        //  145    177    1170   1178   Ljava/lang/Exception;
        //  193    214    1170   1178   Ljava/lang/Exception;
        //  227    248    1170   1178   Ljava/lang/Exception;
        //  261    282    1170   1178   Ljava/lang/Exception;
        //  295    718    1170   1178   Ljava/lang/Exception;
        //  718    1027   1170   1178   Ljava/lang/Exception;
        //  1027   1040   1188   1192   Ljava/lang/Exception;
        //  1044   1053   1188   1192   Ljava/lang/Exception;
        //  1058   1068   1188   1192   Ljava/lang/Exception;
        //  1071   1088   1188   1192   Ljava/lang/Exception;
        //  1091   1109   1188   1192   Ljava/lang/Exception;
        //  1109   1167   1170   1178   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1071:
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
