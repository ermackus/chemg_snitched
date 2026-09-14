package com.alipay.sdk.m.o;

import java.util.List;
import java.util.Map;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import android.text.TextUtils;
import java.net.Proxy;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import android.net.NetworkInfo;
import com.alipay.sdk.m.w.b;
import android.content.Context;
import java.net.CookieManager;

public final class a
{
    public static final String a = "msp";
    public static final String b = "application/octet-stream;binary/octet-stream";
    public static final CookieManager c;
    
    static {
        c = new CookieManager();
    }
    
    public static b a(final Context p0, final a p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: new             Ljava/lang/StringBuilder;
        //     9: astore_3       
        //    10: aload_3        
        //    11: invokespecial   java/lang/StringBuilder.<init>:()V
        //    14: aload_3        
        //    15: ldc             "conn config: "
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: pop            
        //    21: aload_3        
        //    22: aload_1        
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    26: pop            
        //    27: ldc             "mspl"
        //    29: aload_3        
        //    30: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    33: invokestatic    com/alipay/sdk/m/u/e.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    36: new             Ljava/net/URL;
        //    39: astore          6
        //    41: aload           6
        //    43: aload_1        
        //    44: getfield        com/alipay/sdk/m/o/a$a.a:Ljava/lang/String;
        //    47: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    50: aload_0        
        //    51: invokestatic    com/alipay/sdk/m/o/a.b:(Landroid/content/Context;)Ljava/net/Proxy;
        //    54: astore_3       
        //    55: new             Ljava/lang/StringBuilder;
        //    58: astore_0       
        //    59: aload_0        
        //    60: invokespecial   java/lang/StringBuilder.<init>:()V
        //    63: aload_0        
        //    64: ldc             "conn proxy: "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: pop            
        //    70: aload_0        
        //    71: aload_3        
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    75: pop            
        //    76: ldc             "mspl"
        //    78: aload_0        
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: invokestatic    com/alipay/sdk/m/u/e.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    85: aload_3        
        //    86: ifnull          102
        //    89: aload           6
        //    91: aload_3        
        //    92: invokevirtual   java/net/URL.openConnection:(Ljava/net/Proxy;)Ljava/net/URLConnection;
        //    95: checkcast       Ljava/net/HttpURLConnection;
        //    98: astore_0       
        //    99: goto            111
        //   102: aload           6
        //   104: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   107: checkcast       Ljava/net/HttpURLConnection;
        //   110: astore_0       
        //   111: ldc             "http.keepAlive"
        //   113: ldc             "false"
        //   115: invokestatic    java/lang/System.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   118: pop            
        //   119: aload_0        
        //   120: instanceof      Ljavax/net/ssl/HttpsURLConnection;
        //   123: ifeq            131
        //   126: aload_0        
        //   127: checkcast       Ljavax/net/ssl/HttpsURLConnection;
        //   130: astore_3       
        //   131: getstatic       com/alipay/sdk/m/o/a.c:Ljava/net/CookieManager;
        //   134: invokevirtual   java/net/CookieManager.getCookieStore:()Ljava/net/CookieStore;
        //   137: invokeinterface java/net/CookieStore.getCookies:()Ljava/util/List;
        //   142: invokeinterface java/util/List.size:()I
        //   147: istore_2       
        //   148: iload_2        
        //   149: ifle            174
        //   152: aload_0        
        //   153: ldc             "Cookie"
        //   155: ldc             ";"
        //   157: getstatic       com/alipay/sdk/m/o/a.c:Ljava/net/CookieManager;
        //   160: invokevirtual   java/net/CookieManager.getCookieStore:()Ljava/net/CookieStore;
        //   163: invokeinterface java/net/CookieStore.getCookies:()Ljava/util/List;
        //   168: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   171: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   174: aload_0        
        //   175: sipush          20000
        //   178: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   181: aload_0        
        //   182: sipush          30000
        //   185: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   188: aload_0        
        //   189: iconst_1       
        //   190: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //   193: aload_0        
        //   194: ldc             "User-Agent"
        //   196: ldc             "msp"
        //   198: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   201: aload_1        
        //   202: getfield        com/alipay/sdk/m/o/a$a.b:[B
        //   205: astore_3       
        //   206: aload_3        
        //   207: ifnull          259
        //   210: aload_1        
        //   211: getfield        com/alipay/sdk/m/o/a$a.b:[B
        //   214: arraylength    
        //   215: ifle            259
        //   218: aload_0        
        //   219: ldc             "POST"
        //   221: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   224: aload_0        
        //   225: ldc             "Content-Type"
        //   227: ldc             "application/octet-stream;binary/octet-stream"
        //   229: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   232: aload_0        
        //   233: ldc             "Accept-Charset"
        //   235: ldc             "UTF-8"
        //   237: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   240: aload_0        
        //   241: ldc             "Connection"
        //   243: ldc             "Keep-Alive"
        //   245: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   248: aload_0        
        //   249: ldc             "Keep-Alive"
        //   251: ldc             "timeout=180, max=100"
        //   253: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   256: goto            265
        //   259: aload_0        
        //   260: ldc             "GET"
        //   262: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   265: aload_1        
        //   266: getfield        com/alipay/sdk/m/o/a$a.c:Ljava/util/Map;
        //   269: ifnull          347
        //   272: aload_1        
        //   273: getfield        com/alipay/sdk/m/o/a$a.c:Ljava/util/Map;
        //   276: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   281: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   286: astore_3       
        //   287: aload_3        
        //   288: invokeinterface java/util/Iterator.hasNext:()Z
        //   293: ifeq            347
        //   296: aload_3        
        //   297: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   302: checkcast       Ljava/util/Map$Entry;
        //   305: astore          4
        //   307: aload           4
        //   309: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   314: ifnonnull       320
        //   317: goto            287
        //   320: aload_0        
        //   321: aload           4
        //   323: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   328: checkcast       Ljava/lang/String;
        //   331: aload           4
        //   333: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   338: checkcast       Ljava/lang/String;
        //   341: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   344: goto            287
        //   347: aload_0        
        //   348: iconst_1       
        //   349: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //   352: ldc             "POST"
        //   354: aload_0        
        //   355: invokevirtual   java/net/HttpURLConnection.getRequestMethod:()Ljava/lang/String;
        //   358: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   361: ifeq            369
        //   364: aload_0        
        //   365: iconst_1       
        //   366: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   369: ldc             "POST"
        //   371: aload_0        
        //   372: invokevirtual   java/net/HttpURLConnection.getRequestMethod:()Ljava/lang/String;
        //   375: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   378: ifeq            420
        //   381: new             Ljava/io/BufferedOutputStream;
        //   384: dup            
        //   385: aload_0        
        //   386: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   389: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   392: astore          4
        //   394: aload           4
        //   396: astore_3       
        //   397: aload           4
        //   399: aload_1        
        //   400: getfield        com/alipay/sdk/m/o/a$a.b:[B
        //   403: invokevirtual   java/io/OutputStream.write:([B)V
        //   406: aload           4
        //   408: astore_3       
        //   409: aload           4
        //   411: invokevirtual   java/io/OutputStream.flush:()V
        //   414: aload           4
        //   416: astore_1       
        //   417: goto            422
        //   420: aconst_null    
        //   421: astore_1       
        //   422: aload_1        
        //   423: astore_3       
        //   424: new             Ljava/io/BufferedInputStream;
        //   427: astore          5
        //   429: aload_1        
        //   430: astore_3       
        //   431: aload           5
        //   433: aload_0        
        //   434: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   437: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   440: aload           5
        //   442: invokestatic    com/alipay/sdk/m/o/a.a:(Ljava/io/InputStream;)[B
        //   445: astore          4
        //   447: aload_0        
        //   448: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   451: astore          7
        //   453: aload           7
        //   455: ifnull          491
        //   458: aload           7
        //   460: aconst_null    
        //   461: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   466: astore_3       
        //   467: aload_3        
        //   468: ifnull          491
        //   471: ldc             ","
        //   473: aload           7
        //   475: aconst_null    
        //   476: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   481: checkcast       Ljava/lang/Iterable;
        //   484: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   487: astore_3       
        //   488: goto            493
        //   491: aconst_null    
        //   492: astore_3       
        //   493: aload           7
        //   495: ldc             "Set-Cookie"
        //   497: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   502: checkcast       Ljava/util/List;
        //   505: astore          8
        //   507: aload           8
        //   509: ifnull          594
        //   512: aload           8
        //   514: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   519: astore          8
        //   521: aload           8
        //   523: invokeinterface java/util/Iterator.hasNext:()Z
        //   528: ifeq            594
        //   531: aload           8
        //   533: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   538: checkcast       Ljava/lang/String;
        //   541: invokestatic    java/net/HttpCookie.parse:(Ljava/lang/String;)Ljava/util/List;
        //   544: astore          9
        //   546: aload           9
        //   548: ifnull          521
        //   551: aload           9
        //   553: invokeinterface java/util/List.isEmpty:()Z
        //   558: ifeq            564
        //   561: goto            521
        //   564: getstatic       com/alipay/sdk/m/o/a.c:Ljava/net/CookieManager;
        //   567: invokevirtual   java/net/CookieManager.getCookieStore:()Ljava/net/CookieStore;
        //   570: aload           6
        //   572: invokevirtual   java/net/URL.toURI:()Ljava/net/URI;
        //   575: aload           9
        //   577: iconst_0       
        //   578: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   583: checkcast       Ljava/net/HttpCookie;
        //   586: invokeinterface java/net/CookieStore.add:(Ljava/net/URI;Ljava/net/HttpCookie;)V
        //   591: goto            521
        //   594: new             Lcom/alipay/sdk/m/o/a$b;
        //   597: dup            
        //   598: aload           7
        //   600: aload_3        
        //   601: aload           4
        //   603: invokespecial   com/alipay/sdk/m/o/a$b.<init>:(Ljava/util/Map;Ljava/lang/String;[B)V
        //   606: astore_3       
        //   607: aload_0        
        //   608: ifnull          615
        //   611: aload_0        
        //   612: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   615: aload           5
        //   617: invokevirtual   java/io/InputStream.close:()V
        //   620: goto            624
        //   623: astore_0       
        //   624: aload_1        
        //   625: ifnull          632
        //   628: aload_1        
        //   629: invokevirtual   java/io/OutputStream.close:()V
        //   632: aload_3        
        //   633: areturn        
        //   634: astore_3       
        //   635: aload_0        
        //   636: astore          4
        //   638: aload           5
        //   640: astore_0       
        //   641: goto            687
        //   644: astore          4
        //   646: aconst_null    
        //   647: astore          5
        //   649: aload_3        
        //   650: astore_1       
        //   651: aload           4
        //   653: astore_3       
        //   654: aload_0        
        //   655: astore          4
        //   657: aload           5
        //   659: astore_0       
        //   660: goto            687
        //   663: astore_3       
        //   664: aload_0        
        //   665: astore_1       
        //   666: aload_3        
        //   667: astore_0       
        //   668: goto            674
        //   671: astore_0       
        //   672: aconst_null    
        //   673: astore_1       
        //   674: aload_0        
        //   675: astore_3       
        //   676: aconst_null    
        //   677: astore_0       
        //   678: aconst_null    
        //   679: astore          5
        //   681: aload_1        
        //   682: astore          4
        //   684: aload           5
        //   686: astore_1       
        //   687: aload_3        
        //   688: invokestatic    com/alipay/sdk/m/u/e.a:(Ljava/lang/Throwable;)V
        //   691: aload           4
        //   693: ifnull          705
        //   696: aload           4
        //   698: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   701: goto            705
        //   704: astore_3       
        //   705: aload_0        
        //   706: ifnull          717
        //   709: aload_0        
        //   710: invokevirtual   java/io/InputStream.close:()V
        //   713: goto            717
        //   716: astore_0       
        //   717: aload_1        
        //   718: ifnull          725
        //   721: aload_1        
        //   722: invokevirtual   java/io/OutputStream.close:()V
        //   725: aconst_null    
        //   726: areturn        
        //   727: astore_3       
        //   728: aload           4
        //   730: ifnull          743
        //   733: aload           4
        //   735: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   738: goto            743
        //   741: astore          4
        //   743: aload_0        
        //   744: ifnull          755
        //   747: aload_0        
        //   748: invokevirtual   java/io/InputStream.close:()V
        //   751: goto            755
        //   754: astore_0       
        //   755: aload_1        
        //   756: ifnull          763
        //   759: aload_1        
        //   760: invokevirtual   java/io/OutputStream.close:()V
        //   763: aload_3        
        //   764: athrow         
        //   765: astore_0       
        //   766: goto            615
        //   769: astore_0       
        //   770: goto            632
        //   773: astore_0       
        //   774: goto            725
        //   777: astore_0       
        //   778: goto            763
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      85     671    674    Any
        //  89     99     671    674    Any
        //  102    111    671    674    Any
        //  111    131    663    671    Any
        //  131    148    663    671    Any
        //  152    174    663    671    Any
        //  174    206    663    671    Any
        //  210    256    663    671    Any
        //  259    265    663    671    Any
        //  265    287    663    671    Any
        //  287    317    663    671    Any
        //  320    344    663    671    Any
        //  347    369    663    671    Any
        //  369    394    663    671    Any
        //  397    406    644    663    Any
        //  409    414    644    663    Any
        //  424    429    644    663    Any
        //  431    440    644    663    Any
        //  440    453    634    644    Any
        //  458    467    634    644    Any
        //  471    488    634    644    Any
        //  493    507    634    644    Any
        //  512    521    634    644    Any
        //  521    546    634    644    Any
        //  551    561    634    644    Any
        //  564    591    634    644    Any
        //  594    607    634    644    Any
        //  611    615    765    769    Any
        //  615    620    623    624    Any
        //  628    632    769    773    Any
        //  687    691    727    765    Any
        //  696    701    704    705    Any
        //  709    713    716    717    Any
        //  721    725    773    777    Any
        //  733    738    741    743    Any
        //  747    751    754    755    Any
        //  759    763    777    781    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 365, Size: 365
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
    
    public static String a(final Context context) {
        try {
            final NetworkInfo a = com.alipay.sdk.m.w.b.a((a)null, context);
            if (a == null || !a.isAvailable()) {
                return "none";
            }
            if (a.getType() == 1) {
                return "wifi";
            }
            return a.getExtraInfo().toLowerCase();
        }
        catch (final Exception ex) {
            return "none";
        }
    }
    
    public static byte[] a(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1024];
        while (true) {
            final int read = inputStream.read(array, 0, 1024);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static Proxy b(final Context context) {
        final String a = a(context);
        final Proxy proxy = null;
        if (a != null && !a.contains((CharSequence)"wap")) {
            return null;
        }
        try {
            final String property = System.getProperty("https.proxyHost");
            final String property2 = System.getProperty("https.proxyPort");
            Proxy proxy2 = proxy;
            if (!TextUtils.isEmpty((CharSequence)property)) {
                proxy2 = new Proxy(Proxy$Type.HTTP, (SocketAddress)new InetSocketAddress(property, Integer.parseInt(property2)));
            }
            return proxy2;
        }
        finally {
            return proxy;
        }
    }
    
    public static final class a
    {
        public final String a;
        public final byte[] b;
        public final Map<String, String> c;
        
        public a(final String a, final Map<String, String> c, final byte[] b) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s headers=%s>", new Object[] { this.a, this.c });
        }
    }
    
    public static final class b
    {
        public final Map<String, List<String>> a;
        public final String b;
        public final byte[] c;
        
        public b(final Map<String, List<String>> a, final String b, final byte[] c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
