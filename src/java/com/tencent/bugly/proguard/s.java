package com.tencent.bugly.proguard;

import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.util.List;
import java.util.HashMap;
import java.io.OutputStream;
import java.util.Iterator;
import java.net.URLEncoder;
import java.util.Map$Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import java.net.SocketAddress;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import java.util.Locale;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.Context;
import java.util.Map;

public final class s
{
    private static s b;
    public Map<String, String> a;
    private Context c;
    
    private s(final Context c) {
        this.a = null;
        this.c = c;
    }
    
    public static s a(final Context context) {
        if (s.b == null) {
            s.b = new s(context);
        }
        return s.b;
    }
    
    private static HttpURLConnection a(final String s, String property) {
        try {
            final URL url = new URL(property);
            HttpURLConnection httpURLConnection;
            if (a.b() != null) {
                httpURLConnection = (HttpURLConnection)url.openConnection(a.b());
            }
            else if (s != null && s.toLowerCase(Locale.US).contains((CharSequence)"wap")) {
                final String property2 = System.getProperty("http.proxyHost");
                property = System.getProperty("http.proxyPort");
                httpURLConnection = (HttpURLConnection)url.openConnection(new Proxy(Proxy$Type.HTTP, (SocketAddress)new InetSocketAddress(property2, Integer.parseInt(property))));
            }
            else {
                httpURLConnection = (HttpURLConnection)url.openConnection();
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private HttpURLConnection a(final String s, final byte[] array, final String s2, final Map<String, String> map) {
        if (s == null) {
            x.e("destUrl is null.", new Object[0]);
            return null;
        }
        final X509TrustManager x509TrustManager = (X509TrustManager)new X509TrustManager() {
            public final void checkClientTrusted(final X509Certificate[] array, final String s) throws CertificateException {
                x.c("checkClientTrusted", new Object[0]);
            }
            
            public final void checkServerTrusted(final X509Certificate[] array, final String s) throws CertificateException {
                x.c("checkServerTrusted", new Object[0]);
            }
            
            public final X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            final SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[])null, new TrustManager[] { (TrustManager)x509TrustManager }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        final HttpURLConnection a = a(s2, s);
        if (a == null) {
            x.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (final Map$Entry map$Entry : map.entrySet()) {
                    a.setRequestProperty((String)map$Entry.getKey(), URLEncoder.encode((String)map$Entry.getValue(), "utf-8"));
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(s2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(s2, "utf-8"));
            final OutputStream outputStream = a.getOutputStream();
            if (array == null) {
                outputStream.write(0);
            }
            else {
                outputStream.write(array);
            }
            return a;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            x.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }
    
    private static Map<String, String> a(final HttpURLConnection httpURLConnection) {
        final HashMap hashMap = new HashMap();
        final Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields != null && headerFields.size() != 0) {
            for (final String s : headerFields.keySet()) {
                final List list = (List)headerFields.get((Object)s);
                if (list.size() > 0) {
                    hashMap.put((Object)s, list.get(0));
                }
            }
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    private static byte[] b(HttpURLConnection byteArray) {
        if (byteArray == null) {
            return null;
        }
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArray.getInputStream());
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final byte[] array = new byte[1024];
                while (true) {
                    final int read = bufferedInputStream.read(array);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(array, 0, read);
                }
                byteArrayOutputStream.flush();
                byteArray = (HttpURLConnection)(Object)byteArrayOutputStream.toByteArray();
                try {
                    bufferedInputStream.close();
                }
                finally {
                    final Throwable t;
                    t.printStackTrace();
                }
                return (byte[])(Object)byteArray;
            }
            finally {}
        }
        finally {
            byteArray = null;
        }
        try {
            final Throwable t2;
            if (!x.a(t2)) {
                t2.printStackTrace();
            }
            return null;
        }
        finally {
            if (byteArray != null) {
                try {
                    ((BufferedInputStream)byteArray).close();
                }
                finally {
                    final Throwable t3;
                    t3.printStackTrace();
                }
            }
        }
    }
    
    public final byte[] a(final String p0, final byte[] p1, final v p2, final Map<String, String> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          17
        //     3: iconst_0       
        //     4: istore          8
        //     6: aload_1        
        //     7: ifnonnull       23
        //    10: ldc_w           "Failed for no URL."
        //    13: iconst_0       
        //    14: anewarray       Ljava/lang/Object;
        //    17: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    20: pop            
        //    21: aconst_null    
        //    22: areturn        
        //    23: aload_2        
        //    24: ifnonnull       33
        //    27: lconst_0       
        //    28: lstore          11
        //    30: goto            38
        //    33: aload_2        
        //    34: arraylength    
        //    35: i2l            
        //    36: lstore          11
        //    38: ldc_w           "request: %s, send: %d (pid=%d | tid=%d)"
        //    41: iconst_4       
        //    42: anewarray       Ljava/lang/Object;
        //    45: dup            
        //    46: iconst_0       
        //    47: aload_1        
        //    48: aastore        
        //    49: dup            
        //    50: iconst_1       
        //    51: lload           11
        //    53: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    56: aastore        
        //    57: dup            
        //    58: iconst_2       
        //    59: invokestatic    android/os/Process.myPid:()I
        //    62: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    65: aastore        
        //    66: dup            
        //    67: iconst_3       
        //    68: invokestatic    android/os/Process.myTid:()I
        //    71: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    74: aastore        
        //    75: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //    78: pop            
        //    79: iconst_0       
        //    80: istore          5
        //    82: iconst_0       
        //    83: istore          6
        //    85: iconst_0       
        //    86: istore          7
        //    88: iload           5
        //    90: ifgt            771
        //    93: iload           6
        //    95: ifgt            771
        //    98: iload           7
        //   100: ifeq            109
        //   103: iconst_0       
        //   104: istore          7
        //   106: goto            176
        //   109: iinc            5, 1
        //   112: iload           5
        //   114: iconst_1       
        //   115: if_icmple       176
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: ldc_w           "try time: "
        //   125: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   128: astore          18
        //   130: aload           18
        //   132: iload           5
        //   134: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   137: pop            
        //   138: aload           18
        //   140: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   143: iload           8
        //   145: anewarray       Ljava/lang/Object;
        //   148: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   151: pop            
        //   152: new             Ljava/util/Random;
        //   155: dup            
        //   156: invokestatic    java/lang/System.currentTimeMillis:()J
        //   159: invokespecial   java/util/Random.<init>:(J)V
        //   162: sipush          10000
        //   165: invokevirtual   java/util/Random.nextInt:(I)I
        //   168: i2l            
        //   169: ldc2_w          10000
        //   172: ladd           
        //   173: invokestatic    android/os/SystemClock.sleep:(J)V
        //   176: aload_0        
        //   177: getfield        com/tencent/bugly/proguard/s.c:Landroid/content/Context;
        //   180: invokestatic    com/tencent/bugly/crashreport/common/info/b.b:(Landroid/content/Context;)Ljava/lang/String;
        //   183: astore          18
        //   185: aload           18
        //   187: ifnonnull       205
        //   190: ldc_w           "Failed to request for network not avail"
        //   193: iload           8
        //   195: anewarray       Ljava/lang/Object;
        //   198: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   201: pop            
        //   202: goto            88
        //   205: aload_3        
        //   206: lload           11
        //   208: invokevirtual   com/tencent/bugly/proguard/v.a:(J)V
        //   211: aload_0        
        //   212: aload_1        
        //   213: aload_2        
        //   214: aload           18
        //   216: aload           4
        //   218: invokespecial   com/tencent/bugly/proguard/s.a:(Ljava/lang/String;[BLjava/lang/String;Ljava/util/Map;)Ljava/net/HttpURLConnection;
        //   221: astore          19
        //   223: aload           19
        //   225: ifnull          746
        //   228: aload           19
        //   230: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   233: istore          9
        //   235: iload           9
        //   237: sipush          200
        //   240: if_icmpne       305
        //   243: aload_0        
        //   244: aload           19
        //   246: invokestatic    com/tencent/bugly/proguard/s.a:(Ljava/net/HttpURLConnection;)Ljava/util/Map;
        //   249: putfield        com/tencent/bugly/proguard/s.a:Ljava/util/Map;
        //   252: aload           19
        //   254: invokestatic    com/tencent/bugly/proguard/s.b:(Ljava/net/HttpURLConnection;)[B
        //   257: astore          17
        //   259: aload           17
        //   261: ifnonnull       270
        //   264: lconst_0       
        //   265: lstore          13
        //   267: goto            276
        //   270: aload           17
        //   272: arraylength    
        //   273: i2l            
        //   274: lstore          13
        //   276: aload_3        
        //   277: lload           13
        //   279: invokevirtual   com/tencent/bugly/proguard/v.b:(J)V
        //   282: aload           19
        //   284: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   287: goto            302
        //   290: astore_1       
        //   291: aload_1        
        //   292: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   295: ifne            302
        //   298: aload_1        
        //   299: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   302: aload           17
        //   304: areturn        
        //   305: iload           9
        //   307: sipush          301
        //   310: if_icmpeq       346
        //   313: iload           9
        //   315: sipush          302
        //   318: if_icmpeq       346
        //   321: iload           9
        //   323: sipush          303
        //   326: if_icmpeq       346
        //   329: iload           9
        //   331: sipush          307
        //   334: if_icmpne       340
        //   337: goto            346
        //   340: iconst_0       
        //   341: istore          8
        //   343: goto            349
        //   346: iconst_1       
        //   347: istore          8
        //   349: iload           8
        //   351: ifeq            501
        //   354: aload           19
        //   356: ldc_w           "Location"
        //   359: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   362: astore          17
        //   364: aload           17
        //   366: ifnonnull       436
        //   369: new             Ljava/lang/StringBuilder;
        //   372: astore          17
        //   374: aload           17
        //   376: ldc_w           "Failed to redirect: %d"
        //   379: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   382: aload           17
        //   384: iload           9
        //   386: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   389: pop            
        //   390: aload           17
        //   392: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   395: iconst_0       
        //   396: anewarray       Ljava/lang/Object;
        //   399: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   402: pop            
        //   403: aload           19
        //   405: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   408: goto            426
        //   411: astore_1       
        //   412: aload_1        
        //   413: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   416: ifne            408
        //   419: aload_1        
        //   420: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   423: goto            408
        //   426: aconst_null    
        //   427: areturn        
        //   428: astore          17
        //   430: iconst_1       
        //   431: istore          7
        //   433: goto            625
        //   436: iinc            6, 1
        //   439: ldc_w           "redirect code: %d ,to:%s"
        //   442: iconst_2       
        //   443: anewarray       Ljava/lang/Object;
        //   446: dup            
        //   447: iconst_0       
        //   448: iload           9
        //   450: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   453: aastore        
        //   454: dup            
        //   455: iconst_1       
        //   456: aload           17
        //   458: aastore        
        //   459: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   462: pop            
        //   463: aload           17
        //   465: astore_1       
        //   466: iconst_0       
        //   467: istore          5
        //   469: iconst_1       
        //   470: istore          7
        //   472: goto            501
        //   475: astore          18
        //   477: goto            480
        //   480: aload           17
        //   482: astore_1       
        //   483: iconst_0       
        //   484: istore          5
        //   486: aload           18
        //   488: astore          17
        //   490: goto            495
        //   493: astore          17
        //   495: iconst_1       
        //   496: istore          7
        //   498: goto            625
        //   501: new             Ljava/lang/StringBuilder;
        //   504: astore          17
        //   506: aload           17
        //   508: ldc_w           "response code "
        //   511: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   514: aload           17
        //   516: iload           9
        //   518: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   521: pop            
        //   522: aload           17
        //   524: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   527: iconst_0       
        //   528: anewarray       Ljava/lang/Object;
        //   531: invokestatic    com/tencent/bugly/proguard/x.d:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   534: pop            
        //   535: aload           19
        //   537: invokevirtual   java/net/HttpURLConnection.getContentLength:()I
        //   540: i2l            
        //   541: lstore          15
        //   543: lload           15
        //   545: lstore          13
        //   547: lload           15
        //   549: lconst_0       
        //   550: lcmp           
        //   551: ifge            557
        //   554: lconst_0       
        //   555: lstore          13
        //   557: aload_3        
        //   558: lload           13
        //   560: invokevirtual   com/tencent/bugly/proguard/v.b:(J)V
        //   563: aload           19
        //   565: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   568: iload           5
        //   570: istore          10
        //   572: aload_1        
        //   573: astore          17
        //   575: iload           6
        //   577: istore          9
        //   579: iload           7
        //   581: istore          8
        //   583: goto            706
        //   586: astore          18
        //   588: iload           5
        //   590: istore          10
        //   592: aload_1        
        //   593: astore          17
        //   595: iload           6
        //   597: istore          9
        //   599: iload           7
        //   601: istore          8
        //   603: aload           18
        //   605: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   608: ifne            706
        //   611: goto            686
        //   614: astore          17
        //   616: goto            625
        //   619: astore_1       
        //   620: goto            724
        //   623: astore          17
        //   625: aload           17
        //   627: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   630: ifne            638
        //   633: aload           17
        //   635: invokevirtual   java/io/IOException.printStackTrace:()V
        //   638: aload           19
        //   640: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   643: iload           5
        //   645: istore          10
        //   647: aload_1        
        //   648: astore          17
        //   650: iload           6
        //   652: istore          9
        //   654: iload           7
        //   656: istore          8
        //   658: goto            706
        //   661: astore          18
        //   663: iload           5
        //   665: istore          10
        //   667: aload_1        
        //   668: astore          17
        //   670: iload           6
        //   672: istore          9
        //   674: iload           7
        //   676: istore          8
        //   678: aload           18
        //   680: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   683: ifne            706
        //   686: aload           18
        //   688: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   691: iload           7
        //   693: istore          8
        //   695: iload           6
        //   697: istore          9
        //   699: aload_1        
        //   700: astore          17
        //   702: iload           5
        //   704: istore          10
        //   706: iload           10
        //   708: istore          5
        //   710: aload           17
        //   712: astore_1       
        //   713: iload           9
        //   715: istore          6
        //   717: iload           8
        //   719: istore          7
        //   721: goto            762
        //   724: aload           19
        //   726: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   729: goto            744
        //   732: astore_2       
        //   733: aload_2        
        //   734: invokestatic    com/tencent/bugly/proguard/x.a:(Ljava/lang/Throwable;)Z
        //   737: ifne            744
        //   740: aload_2        
        //   741: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   744: aload_1        
        //   745: athrow         
        //   746: ldc_w           "Failed to execute post."
        //   749: iconst_0       
        //   750: anewarray       Ljava/lang/Object;
        //   753: invokestatic    com/tencent/bugly/proguard/x.c:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   756: pop            
        //   757: aload_3        
        //   758: lconst_0       
        //   759: invokevirtual   com/tencent/bugly/proguard/v.b:(J)V
        //   762: aconst_null    
        //   763: astore          17
        //   765: iconst_0       
        //   766: istore          8
        //   768: goto            202
        //   771: aload           17
        //   773: areturn        
        //    Signature:
        //  (Ljava/lang/String;[BLcom/tencent/bugly/proguard/v;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)[B
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  228    235    623    625    Ljava/io/IOException;
        //  228    235    619    746    Any
        //  243    259    623    625    Ljava/io/IOException;
        //  243    259    619    746    Any
        //  270    276    623    625    Ljava/io/IOException;
        //  270    276    619    746    Any
        //  276    282    623    625    Ljava/io/IOException;
        //  276    282    619    746    Any
        //  282    287    290    302    Any
        //  354    364    493    495    Ljava/io/IOException;
        //  354    364    619    746    Any
        //  369    403    428    436    Ljava/io/IOException;
        //  369    403    619    746    Any
        //  403    408    411    426    Any
        //  439    463    475    493    Ljava/io/IOException;
        //  439    463    619    746    Any
        //  501    543    614    619    Ljava/io/IOException;
        //  501    543    619    746    Any
        //  557    563    614    619    Ljava/io/IOException;
        //  557    563    619    746    Any
        //  563    568    586    614    Any
        //  625    638    619    746    Any
        //  638    643    661    686    Any
        //  724    729    732    744    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 374, Size: 374
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
}
