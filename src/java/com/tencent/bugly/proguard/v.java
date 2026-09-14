package com.tencent.bugly.proguard;

import java.util.UUID;
import java.util.Map;
import com.tencent.bugly.crashreport.common.info.a;
import android.content.Context;

public final class v implements Runnable
{
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    
    public v(final Context c, final int j, final int d, final byte[] e, final String m, final String n, final t k, final int a, final int b, final boolean s, final Map<String, String> o) {
        this.a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0L;
        this.r = 0L;
        this.s = false;
        this.c = c;
        this.f = com.tencent.bugly.crashreport.common.info.a.a(c);
        this.e = e;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = com.tencent.bugly.proguard.s.a(c);
        this.i = u.a();
        this.j = j;
        this.m = m;
        this.n = n;
        this.k = k;
        this.l = null;
        this.d = d;
        if (a > 0) {
            this.a = a;
        }
        if (b > 0) {
            this.b = b;
        }
        this.s = s;
        this.o = o;
    }
    
    public v(final Context context, final int n, final int n2, final byte[] array, final String s, final String s2, final t t, final boolean b, final boolean b2) {
        this(context, n, n2, array, s, s2, t, 2, 30000, b2, null);
    }
    
    private static String a(final String s) {
        if (z.a(s)) {
            return s;
        }
        try {
            return String.format("%s?aid=%s", new Object[] { s, UUID.randomUUID().toString() });
        }
        finally {
            final Throwable t;
            x.a(t);
            return s;
        }
    }
    
    private void a(final an an, final boolean b, final int n, final String s) {
        final int d = this.d;
        String value = null;
        Label_0056: {
            Label_0053: {
                if (d != 630) {
                    if (d != 640) {
                        if (d == 830) {
                            break Label_0053;
                        }
                        if (d != 840) {
                            value = String.valueOf(d);
                            break Label_0056;
                        }
                    }
                    value = "userinfo";
                    break Label_0056;
                }
            }
            value = "crash";
        }
        if (b) {
            x.a("[Upload] Success: %s", value);
        }
        else {
            x.e("[Upload] Failed to upload(%d) %s: %s", n, value, s);
        }
        if (this.q + this.r > 0L) {
            this.i.a(this.i.a(this.s) + this.q + this.r, this.s);
        }
        final t k = this.k;
        if (k != null) {
            k.a(b);
        }
        final t l = this.l;
        if (l != null) {
            l.a(b);
        }
    }
    
    private static boolean a(final an an, final a a, final com.tencent.bugly.crashreport.common.strategy.a a2) {
        if (an == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        }
        if (an.a != 0) {
            x.e("resp result error %d", an.a);
            return false;
        }
        try {
            if (!z.a(an.e) && !a.b().i().equals((Object)an.e)) {
                p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "device", an.e.getBytes("UTF-8"), null, true);
                a.e(an.e);
            }
        }
        finally {
            final Throwable t;
            x.a(t);
        }
        a.j = an.d;
        if (an.b == 510) {
            if (an.c == null) {
                x.e("[Upload] Strategy data is null. Response cmd: %d", an.b);
                return false;
            }
            final ap ap = a.a(an.c, ap.class);
            if (ap == null) {
                x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", an.b);
                return false;
            }
            a2.a(ap);
        }
        return true;
    }
    
    public final void a(final long n) {
        ++this.p;
        this.q += n;
    }
    
    public final void b(final long n) {
        this.r += n;
    }
    
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore          7
        //     4: aload_0        
        //     5: iconst_0       
        //     6: putfield        com/tencent/bugly/proguard/v.p:I
        //     9: aload_0        
        //    10: lconst_0       
        //    11: putfield        com/tencent/bugly/proguard/v.q:J
        //    14: aload_0        
        //    15: lconst_0       
        //    16: putfield        com/tencent/bugly/proguard/v.r:J
        //    19: aload_0        
        //    20: getfield        com/tencent/bugly/proguard/v.e:[B
        //    23: astore          8
        //    25: aload_0        
        //    26: getfield        com/tencent/bugly/proguard/v.c:Landroid/content/Context;
        //    29: invokestatic    com/tencent/bugly/crashreport/common/info/b.b:(Landroid/content/Context;)Ljava/lang/String;
        //    32: ifnonnull       45
        //    35: aload_0        
        //    36: aconst_null    
        //    37: iconst_0       
        //    38: iconst_0       
        //    39: ldc             "network is not available"
        //    41: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //    44: return         
        //    45: aload           8
        //    47: ifnull          1363
        //    50: aload           8
        //    52: arraylength    
        //    53: ifne            59
        //    56: goto            1363
        //    59: ldc             "[Upload] Run upload task with cmd: %d"
        //    61: iconst_1       
        //    62: anewarray       Ljava/lang/Object;
        //    65: dup            
        //    66: iconst_0       
        //    67: aload_0        
        //    68: getfield        com/tencent/bugly/proguard/v.d:I
        //    71: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    74: aastore        
        //    75: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    78: pop            
        //    79: aload_0        
        //    80: getfield        com/tencent/bugly/proguard/v.c:Landroid/content/Context;
        //    83: ifnull          1352
        //    86: aload_0        
        //    87: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //    90: ifnull          1352
        //    93: aload_0        
        //    94: getfield        com/tencent/bugly/proguard/v.g:Lcom/tencent/bugly/crashreport/common/strategy/a;
        //    97: ifnull          1352
        //   100: aload_0        
        //   101: getfield        com/tencent/bugly/proguard/v.h:Lcom/tencent/bugly/proguard/s;
        //   104: ifnonnull       110
        //   107: goto            1352
        //   110: aload_0        
        //   111: getfield        com/tencent/bugly/proguard/v.g:Lcom/tencent/bugly/crashreport/common/strategy/a;
        //   114: invokevirtual   com/tencent/bugly/crashreport/common/strategy/a.c:()Lcom/tencent/bugly/crashreport/common/strategy/StrategyBean;
        //   117: astore          9
        //   119: aload           9
        //   121: ifnonnull       135
        //   124: aload_0        
        //   125: aconst_null    
        //   126: iconst_0       
        //   127: iconst_0       
        //   128: ldc_w           "illegal local strategy"
        //   131: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //   134: return         
        //   135: new             Ljava/util/HashMap;
        //   138: astore          10
        //   140: aload           10
        //   142: invokespecial   java/util/HashMap.<init>:()V
        //   145: aload           10
        //   147: ldc_w           "tls"
        //   150: ldc_w           "1"
        //   153: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: aload           10
        //   161: ldc_w           "prodId"
        //   164: aload_0        
        //   165: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //   168: invokevirtual   com/tencent/bugly/crashreport/common/info/a.f:()Ljava/lang/String;
        //   171: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   176: pop            
        //   177: aload           10
        //   179: ldc_w           "bundleId"
        //   182: aload_0        
        //   183: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //   186: getfield        com/tencent/bugly/crashreport/common/info/a.c:Ljava/lang/String;
        //   189: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   194: pop            
        //   195: aload           10
        //   197: ldc_w           "appVer"
        //   200: aload_0        
        //   201: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //   204: getfield        com/tencent/bugly/crashreport/common/info/a.k:Ljava/lang/String;
        //   207: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   212: pop            
        //   213: aload_0        
        //   214: getfield        com/tencent/bugly/proguard/v.o:Ljava/util/Map;
        //   217: ifnull          231
        //   220: aload           10
        //   222: aload_0        
        //   223: getfield        com/tencent/bugly/proguard/v.o:Ljava/util/Map;
        //   226: invokeinterface java/util/Map.putAll:(Ljava/util/Map;)V
        //   231: aload           10
        //   233: ldc_w           "cmd"
        //   236: aload_0        
        //   237: getfield        com/tencent/bugly/proguard/v.d:I
        //   240: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   243: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   248: pop            
        //   249: aload           10
        //   251: ldc_w           "platformId"
        //   254: iconst_1       
        //   255: invokestatic    java/lang/Byte.toString:(B)Ljava/lang/String;
        //   258: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   263: pop            
        //   264: aload           10
        //   266: ldc_w           "sdkVer"
        //   269: aload_0        
        //   270: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //   273: getfield        com/tencent/bugly/crashreport/common/info/a.f:Ljava/lang/String;
        //   276: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   281: pop            
        //   282: aload           10
        //   284: ldc_w           "strategylastUpdateTime"
        //   287: aload           9
        //   289: getfield        com/tencent/bugly/crashreport/common/strategy/StrategyBean.n:J
        //   292: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //   295: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   300: pop            
        //   301: iconst_2       
        //   302: istore_2       
        //   303: aload           8
        //   305: iconst_2       
        //   306: invokestatic    com/tencent/bugly/proguard/z.a:([BI)[B
        //   309: astore          11
        //   311: aload           11
        //   313: ifnonnull       327
        //   316: aload_0        
        //   317: aconst_null    
        //   318: iconst_0       
        //   319: iconst_0       
        //   320: ldc_w           "failed to zip request body"
        //   323: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //   326: return         
        //   327: aload           11
        //   329: ifnonnull       343
        //   332: aload_0        
        //   333: aconst_null    
        //   334: iconst_0       
        //   335: iconst_0       
        //   336: ldc_w           "failed to encrypt request body"
        //   339: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //   342: return         
        //   343: aload_0        
        //   344: getfield        com/tencent/bugly/proguard/v.i:Lcom/tencent/bugly/proguard/u;
        //   347: aload_0        
        //   348: getfield        com/tencent/bugly/proguard/v.j:I
        //   351: invokestatic    java/lang/System.currentTimeMillis:()J
        //   354: invokevirtual   com/tencent/bugly/proguard/u.a:(IJ)V
        //   357: aload_0        
        //   358: getfield        com/tencent/bugly/proguard/v.k:Lcom/tencent/bugly/proguard/t;
        //   361: astore          8
        //   363: aload_0        
        //   364: getfield        com/tencent/bugly/proguard/v.l:Lcom/tencent/bugly/proguard/t;
        //   367: astore          8
        //   369: aload_0        
        //   370: getfield        com/tencent/bugly/proguard/v.m:Ljava/lang/String;
        //   373: astore          8
        //   375: iconst_m1      
        //   376: istore_1       
        //   377: iconst_0       
        //   378: istore_3       
        //   379: iconst_0       
        //   380: istore          4
        //   382: iload_3        
        //   383: iconst_1       
        //   384: iadd           
        //   385: istore          5
        //   387: iload_3        
        //   388: aload_0        
        //   389: getfield        com/tencent/bugly/proguard/v.a:I
        //   392: if_icmpge       1340
        //   395: aload           8
        //   397: astore          9
        //   399: iload           5
        //   401: iconst_1       
        //   402: if_icmple       469
        //   405: ldc_w           "[Upload] Failed to upload last time, wait and try(%d) again."
        //   408: iconst_1       
        //   409: anewarray       Ljava/lang/Object;
        //   412: dup            
        //   413: iconst_0       
        //   414: iload           5
        //   416: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   419: aastore        
        //   420: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   423: pop            
        //   424: aload_0        
        //   425: getfield        com/tencent/bugly/proguard/v.b:I
        //   428: i2l            
        //   429: invokestatic    com/tencent/bugly/proguard/z.b:(J)V
        //   432: aload           8
        //   434: astore          9
        //   436: iload           5
        //   438: aload_0        
        //   439: getfield        com/tencent/bugly/proguard/v.a:I
        //   442: if_icmpne       469
        //   445: ldc_w           "[Upload] Use the back-up url at the last time: %s"
        //   448: iconst_1       
        //   449: anewarray       Ljava/lang/Object;
        //   452: dup            
        //   453: iconst_0       
        //   454: aload_0        
        //   455: getfield        com/tencent/bugly/proguard/v.n:Ljava/lang/String;
        //   458: aastore        
        //   459: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   462: pop            
        //   463: aload_0        
        //   464: getfield        com/tencent/bugly/proguard/v.n:Ljava/lang/String;
        //   467: astore          9
        //   469: ldc_w           "[Upload] Send %d bytes"
        //   472: iconst_1       
        //   473: anewarray       Ljava/lang/Object;
        //   476: dup            
        //   477: iconst_0       
        //   478: aload           11
        //   480: arraylength    
        //   481: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   484: aastore        
        //   485: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   488: pop            
        //   489: aload           9
        //   491: invokestatic    com/tencent/bugly/proguard/v.a:(Ljava/lang/String;)Ljava/lang/String;
        //   494: astore          8
        //   496: iconst_4       
        //   497: anewarray       Ljava/lang/Object;
        //   500: astore          9
        //   502: aload           9
        //   504: iconst_0       
        //   505: aload           8
        //   507: aastore        
        //   508: aload           9
        //   510: iconst_1       
        //   511: aload_0        
        //   512: getfield        com/tencent/bugly/proguard/v.d:I
        //   515: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   518: aastore        
        //   519: aload           9
        //   521: iload_2        
        //   522: invokestatic    android/os/Process.myPid:()I
        //   525: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   528: aastore        
        //   529: aload           9
        //   531: iconst_3       
        //   532: invokestatic    android/os/Process.myTid:()I
        //   535: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   538: aastore        
        //   539: ldc_w           "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
        //   542: aload           9
        //   544: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   547: pop            
        //   548: aload_0        
        //   549: getfield        com/tencent/bugly/proguard/v.h:Lcom/tencent/bugly/proguard/s;
        //   552: aload           8
        //   554: aload           11
        //   556: aload_0        
        //   557: aload           10
        //   559: invokevirtual   com/tencent/bugly/proguard/s.a:(Ljava/lang/String;[BLcom/tencent/bugly/proguard/v;Ljava/util/Map;)[B
        //   562: astore          9
        //   564: aload           9
        //   566: ifnonnull       608
        //   569: iload_2        
        //   570: anewarray       Ljava/lang/Object;
        //   573: astore          9
        //   575: aload           9
        //   577: iconst_0       
        //   578: iconst_1       
        //   579: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   582: aastore        
        //   583: aload           9
        //   585: iconst_1       
        //   586: ldc_w           "Failed to upload for no response!"
        //   589: aastore        
        //   590: ldc_w           "[Upload] Failed to upload(%d): %s"
        //   593: aload           9
        //   595: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   598: pop            
        //   599: iload           5
        //   601: istore_3       
        //   602: iconst_1       
        //   603: istore          4
        //   605: goto            382
        //   608: aload_0        
        //   609: getfield        com/tencent/bugly/proguard/v.h:Lcom/tencent/bugly/proguard/s;
        //   612: getfield        com/tencent/bugly/proguard/s.a:Ljava/util/Map;
        //   615: astore          12
        //   617: aload           12
        //   619: ifnull          768
        //   622: aload           12
        //   624: invokeinterface java/util/Map.size:()I
        //   629: ifne            635
        //   632: goto            768
        //   635: aload           12
        //   637: ldc_w           "status"
        //   640: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   645: istore          6
        //   647: iload           6
        //   649: ifne            672
        //   652: ldc_w           "[Upload] Headers does not contain %s"
        //   655: iconst_1       
        //   656: anewarray       Ljava/lang/Object;
        //   659: dup            
        //   660: iconst_0       
        //   661: ldc_w           "status"
        //   664: aastore        
        //   665: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   668: pop            
        //   669: goto            779
        //   672: aload           12
        //   674: aload           7
        //   676: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   681: ifne            703
        //   684: ldc_w           "[Upload] Headers does not contain %s"
        //   687: iconst_1       
        //   688: anewarray       Ljava/lang/Object;
        //   691: dup            
        //   692: iconst_0       
        //   693: aload           7
        //   695: aastore        
        //   696: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   699: pop            
        //   700: goto            669
        //   703: aload           12
        //   705: aload           7
        //   707: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   712: checkcast       Ljava/lang/String;
        //   715: astore          13
        //   717: aload           13
        //   719: ldc_w           "bugly"
        //   722: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   725: ifne            747
        //   728: ldc_w           "[Upload] Bugly version is not valid: %s"
        //   731: iconst_1       
        //   732: anewarray       Ljava/lang/Object;
        //   735: dup            
        //   736: iconst_0       
        //   737: aload           13
        //   739: aastore        
        //   740: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   743: pop            
        //   744: goto            779
        //   747: ldc_w           "[Upload] Bugly version from headers is: %s"
        //   750: iconst_1       
        //   751: anewarray       Ljava/lang/Object;
        //   754: dup            
        //   755: iconst_0       
        //   756: aload           13
        //   758: aastore        
        //   759: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   762: pop            
        //   763: iconst_1       
        //   764: istore_2       
        //   765: goto            781
        //   768: ldc_w           "[Upload] Headers is empty."
        //   771: iconst_0       
        //   772: anewarray       Ljava/lang/Object;
        //   775: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   778: pop            
        //   779: iconst_0       
        //   780: istore_2       
        //   781: iload_2        
        //   782: ifne            934
        //   785: ldc_w           "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
        //   788: iconst_2       
        //   789: anewarray       Ljava/lang/Object;
        //   792: dup            
        //   793: iconst_0       
        //   794: invokestatic    android/os/Process.myPid:()I
        //   797: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   800: aastore        
        //   801: dup            
        //   802: iconst_1       
        //   803: invokestatic    android/os/Process.myTid:()I
        //   806: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   809: aastore        
        //   810: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   813: pop            
        //   814: ldc_w           "[Upload] Failed to upload(%d): %s"
        //   817: iconst_2       
        //   818: anewarray       Ljava/lang/Object;
        //   821: dup            
        //   822: iconst_0       
        //   823: iconst_1       
        //   824: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   827: aastore        
        //   828: dup            
        //   829: iconst_1       
        //   830: ldc_w           "[Upload] Failed to upload for no status header."
        //   833: aastore        
        //   834: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   837: pop            
        //   838: aload           12
        //   840: ifnull          920
        //   843: aload           12
        //   845: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   850: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   855: astore          9
        //   857: aload           9
        //   859: invokeinterface java/util/Iterator.hasNext:()Z
        //   864: ifeq            920
        //   867: aload           9
        //   869: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   874: checkcast       Ljava/util/Map$Entry;
        //   877: astore          12
        //   879: ldc_w           "[key]: %s, [value]: %s"
        //   882: iconst_2       
        //   883: anewarray       Ljava/lang/Object;
        //   886: dup            
        //   887: iconst_0       
        //   888: aload           12
        //   890: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   895: aastore        
        //   896: dup            
        //   897: iconst_1       
        //   898: aload           12
        //   900: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   905: aastore        
        //   906: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   909: iconst_0       
        //   910: anewarray       Ljava/lang/Object;
        //   913: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   916: pop            
        //   917: goto            857
        //   920: ldc_w           "[Upload] Failed to upload for no status header."
        //   923: iconst_0       
        //   924: anewarray       Ljava/lang/Object;
        //   927: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   930: pop            
        //   931: goto            1335
        //   934: aload           12
        //   936: ldc_w           "status"
        //   939: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   944: checkcast       Ljava/lang/String;
        //   947: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   950: istore_2       
        //   951: ldc_w           "[Upload] Status from server is %d (pid=%d | tid=%d)."
        //   954: iconst_3       
        //   955: anewarray       Ljava/lang/Object;
        //   958: dup            
        //   959: iconst_0       
        //   960: iload_2        
        //   961: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   964: aastore        
        //   965: dup            
        //   966: iconst_1       
        //   967: invokestatic    android/os/Process.myPid:()I
        //   970: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   973: aastore        
        //   974: dup            
        //   975: iconst_2       
        //   976: invokestatic    android/os/Process.myTid:()I
        //   979: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   982: aastore        
        //   983: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   986: pop            
        //   987: iload_2        
        //   988: ifeq            1024
        //   991: new             Ljava/lang/StringBuilder;
        //   994: astore          7
        //   996: aload           7
        //   998: ldc_w           "status of server is "
        //  1001: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //  1004: aload           7
        //  1006: iload_2        
        //  1007: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1010: pop            
        //  1011: aload_0        
        //  1012: aconst_null    
        //  1013: iconst_0       
        //  1014: iconst_1       
        //  1015: aload           7
        //  1017: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1020: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1023: return         
        //  1024: ldc_w           "[Upload] Received %d bytes"
        //  1027: iconst_1       
        //  1028: anewarray       Ljava/lang/Object;
        //  1031: dup            
        //  1032: iconst_0       
        //  1033: aload           9
        //  1035: arraylength    
        //  1036: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1039: aastore        
        //  1040: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //  1043: pop            
        //  1044: aload           9
        //  1046: arraylength    
        //  1047: ifne            1131
        //  1050: aload           12
        //  1052: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //  1057: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //  1062: astore          8
        //  1064: aload           8
        //  1066: invokeinterface java/util/Iterator.hasNext:()Z
        //  1071: ifeq            1120
        //  1074: aload           8
        //  1076: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1081: checkcast       Ljava/util/Map$Entry;
        //  1084: astore          7
        //  1086: ldc_w           "[Upload] HTTP headers from server: key = %s, value = %s"
        //  1089: iconst_2       
        //  1090: anewarray       Ljava/lang/Object;
        //  1093: dup            
        //  1094: iconst_0       
        //  1095: aload           7
        //  1097: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //  1102: aastore        
        //  1103: dup            
        //  1104: iconst_1       
        //  1105: aload           7
        //  1107: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //  1112: aastore        
        //  1113: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //  1116: pop            
        //  1117: goto            1064
        //  1120: aload_0        
        //  1121: aconst_null    
        //  1122: iconst_0       
        //  1123: iconst_1       
        //  1124: ldc_w           "response data from server is empty"
        //  1127: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1130: return         
        //  1131: aload           9
        //  1133: ifnonnull       1147
        //  1136: aload_0        
        //  1137: aconst_null    
        //  1138: iconst_0       
        //  1139: iconst_1       
        //  1140: ldc_w           "failed to decrypt response from server"
        //  1143: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1146: return         
        //  1147: aload           9
        //  1149: iconst_2       
        //  1150: invokestatic    com/tencent/bugly/proguard/z.b:([BI)[B
        //  1153: astore          8
        //  1155: aload           9
        //  1157: astore          7
        //  1159: aload           8
        //  1161: ifnull          1168
        //  1164: aload           8
        //  1166: astore          7
        //  1168: aload           7
        //  1170: invokestatic    com/tencent/bugly/proguard/a.b:([B)Lcom/tencent/bugly/proguard/an;
        //  1173: astore          7
        //  1175: aload           7
        //  1177: ifnonnull       1191
        //  1180: aload_0        
        //  1181: aconst_null    
        //  1182: iconst_0       
        //  1183: iconst_1       
        //  1184: ldc_w           "failed to decode response package"
        //  1187: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1190: return         
        //  1191: aload           7
        //  1193: getfield        com/tencent/bugly/proguard/an.b:I
        //  1196: istore_2       
        //  1197: aload           7
        //  1199: getfield        com/tencent/bugly/proguard/an.c:[B
        //  1202: ifnonnull       1210
        //  1205: iconst_0       
        //  1206: istore_1       
        //  1207: goto            1217
        //  1210: aload           7
        //  1212: getfield        com/tencent/bugly/proguard/an.c:[B
        //  1215: arraylength    
        //  1216: istore_1       
        //  1217: ldc_w           "[Upload] Response cmd is: %d, length of sBuffer is: %d"
        //  1220: iconst_2       
        //  1221: anewarray       Ljava/lang/Object;
        //  1224: dup            
        //  1225: iconst_0       
        //  1226: iload_2        
        //  1227: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1230: aastore        
        //  1231: dup            
        //  1232: iconst_1       
        //  1233: iload_1        
        //  1234: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1237: aastore        
        //  1238: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //  1241: pop            
        //  1242: aload           7
        //  1244: aload_0        
        //  1245: getfield        com/tencent/bugly/proguard/v.f:Lcom/tencent/bugly/crashreport/common/info/a;
        //  1248: aload_0        
        //  1249: getfield        com/tencent/bugly/proguard/v.g:Lcom/tencent/bugly/crashreport/common/strategy/a;
        //  1252: invokestatic    com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;Lcom/tencent/bugly/crashreport/common/info/a;Lcom/tencent/bugly/crashreport/common/strategy/a;)Z
        //  1255: ifne            1270
        //  1258: aload_0        
        //  1259: aload           7
        //  1261: iconst_0       
        //  1262: iconst_2       
        //  1263: ldc_w           "failed to process response package"
        //  1266: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1269: return         
        //  1270: aload_0        
        //  1271: aload           7
        //  1273: iconst_1       
        //  1274: iconst_2       
        //  1275: ldc_w           "successfully uploaded"
        //  1278: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1281: return         
        //  1282: astore          9
        //  1284: iload_2        
        //  1285: istore_1       
        //  1286: new             Ljava/lang/StringBuilder;
        //  1289: astore          9
        //  1291: aload           9
        //  1293: ldc_w           "[Upload] Failed to upload for format of status header is invalid: "
        //  1296: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //  1299: aload           9
        //  1301: iload_1        
        //  1302: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  1305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1308: pop            
        //  1309: ldc_w           "[Upload] Failed to upload(%d): %s"
        //  1312: iconst_2       
        //  1313: anewarray       Ljava/lang/Object;
        //  1316: dup            
        //  1317: iconst_0       
        //  1318: iconst_1       
        //  1319: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1322: aastore        
        //  1323: dup            
        //  1324: iconst_1       
        //  1325: aload           9
        //  1327: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1330: aastore        
        //  1331: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //  1334: pop            
        //  1335: iconst_2       
        //  1336: istore_2       
        //  1337: goto            599
        //  1340: aload_0        
        //  1341: aconst_null    
        //  1342: iconst_0       
        //  1343: iload           4
        //  1345: ldc_w           "failed after many attempts"
        //  1348: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1351: return         
        //  1352: aload_0        
        //  1353: aconst_null    
        //  1354: iconst_0       
        //  1355: iconst_0       
        //  1356: ldc_w           "illegal access error"
        //  1359: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1362: return         
        //  1363: aload_0        
        //  1364: aconst_null    
        //  1365: iconst_0       
        //  1366: iconst_0       
        //  1367: ldc_w           "request package is empty!"
        //  1370: invokespecial   com/tencent/bugly/proguard/v.a:(Lcom/tencent/bugly/proguard/an;ZILjava/lang/String;)V
        //  1373: return         
        //  1374: astore          7
        //  1376: aload           7
        //  1378: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //  1381: ifne            1389
        //  1384: aload           7
        //  1386: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1389: return         
        //  1390: astore          9
        //  1392: goto            1286
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      44     1374   1390   Any
        //  50     56     1374   1390   Any
        //  59     107    1374   1390   Any
        //  110    119    1374   1390   Any
        //  124    134    1374   1390   Any
        //  135    231    1374   1390   Any
        //  231    301    1374   1390   Any
        //  303    311    1374   1390   Any
        //  316    326    1374   1390   Any
        //  332    342    1374   1390   Any
        //  343    375    1374   1390   Any
        //  387    395    1374   1390   Any
        //  405    432    1374   1390   Any
        //  436    469    1374   1390   Any
        //  469    502    1374   1390   Any
        //  508    564    1374   1390   Any
        //  569    583    1374   1390   Any
        //  590    599    1374   1390   Any
        //  608    617    1374   1390   Any
        //  622    632    1374   1390   Any
        //  635    647    1374   1390   Any
        //  652    669    1374   1390   Any
        //  672    700    1374   1390   Any
        //  703    744    1374   1390   Any
        //  747    763    1374   1390   Any
        //  768    779    1374   1390   Any
        //  785    838    1374   1390   Any
        //  843    857    1374   1390   Any
        //  857    917    1374   1390   Any
        //  920    931    1374   1390   Any
        //  934    951    1390   1395   Any
        //  951    987    1282   1286   Any
        //  991    1023   1374   1390   Any
        //  1024   1064   1374   1390   Any
        //  1064   1117   1374   1390   Any
        //  1120   1130   1374   1390   Any
        //  1136   1146   1374   1390   Any
        //  1147   1155   1374   1390   Any
        //  1168   1175   1374   1390   Any
        //  1180   1190   1374   1390   Any
        //  1191   1205   1374   1390   Any
        //  1210   1217   1374   1390   Any
        //  1217   1269   1374   1390   Any
        //  1270   1281   1374   1390   Any
        //  1286   1335   1374   1390   Any
        //  1340   1351   1374   1390   Any
        //  1352   1362   1374   1390   Any
        //  1363   1373   1374   1390   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0934:
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
