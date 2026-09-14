package com.alibaba.sdk.android.utils;

import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;

public class AMSDevReporter
{
    private static String TAG;
    private static Context a;
    private static ConcurrentHashMap<AMSSdkTypeEnum, AMSReportStatusEnum> a;
    private static final ExecutorService a;
    private static boolean a;
    
    static {
        a = Executors.newSingleThreadExecutor((ThreadFactory)new a());
        AMSDevReporter.a = (ConcurrentHashMap<AMSSdkTypeEnum, AMSReportStatusEnum>)new ConcurrentHashMap();
        int i = 0;
        AMSDevReporter.a = false;
        AMSDevReporter.TAG = "AMSDevReporter";
        for (AMSSdkTypeEnum[] values = AMSSdkTypeEnum.values(); i < values.length; ++i) {
            AMSDevReporter.a.put((Object)values[i], (Object)AMSReportStatusEnum.UNREPORTED);
        }
    }
    
    private static String a(final AMSSdkTypeEnum amsSdkTypeEnum, final String s, final Map<String, Object> map) {
        final StringBuilder sb = new StringBuilder();
        sb.append((Object)amsSdkTypeEnum);
        sb.append("-");
        sb.append(s);
        if (map != null) {
            final String s2 = (String)map.get((Object)AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_VERSION.toString());
            if (!e.a(s2)) {
                sb.append("-");
                sb.append(s2);
            }
            final String s3 = (String)map.get((Object)AMSSdkExtInfoKeyEnum.AMS_EXTINFO_KEY_PACKAGE.toString());
            if (!e.a(s3)) {
                sb.append("-");
                sb.append(s3);
            }
        }
        return sb.toString();
    }
    
    private static void a(final AMSSdkTypeEnum amsSdkTypeEnum, final Map<String, Object> map) {
        final String string = amsSdkTypeEnum.toString();
        if (AMSDevReporter.a.get((Object)amsSdkTypeEnum) != AMSReportStatusEnum.UNREPORTED) {
            final String tag = AMSDevReporter.TAG;
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(string);
            sb.append("] already reported, return.");
            d.b(tag, sb.toString());
            return;
        }
        int n = 0;
        int n2 = 5;
        while (true) {
            final String tag2 = AMSDevReporter.TAG;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Report [");
            sb2.append(string);
            sb2.append("], times: [");
            final int n3 = n + 1;
            sb2.append(n3);
            sb2.append("].");
            d.b(tag2, sb2.toString());
            if (a(amsSdkTypeEnum, map)) {
                final String tag3 = AMSDevReporter.TAG;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("Report [");
                sb3.append(string);
                sb3.append("] stat success.");
                d.b(tag3, sb3.toString());
                AMSDevReporter.a.put((Object)amsSdkTypeEnum, (Object)AMSReportStatusEnum.REPORTED);
                break;
            }
            if (n3 > 10) {
                final String tag4 = AMSDevReporter.TAG;
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("Report [");
                sb4.append(string);
                sb4.append("] stat failed, exceed max retry times, return.");
                d.c(tag4, sb4.toString());
                AMSDevReporter.a.put((Object)amsSdkTypeEnum, (Object)AMSReportStatusEnum.UNREPORTED);
                AMSDevReporter.a = true;
                break;
            }
            final String tag5 = AMSDevReporter.TAG;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("Report [");
            sb5.append(string);
            sb5.append("] failed, wait for [");
            sb5.append(n2);
            sb5.append("] seconds.");
            d.b(tag5, sb5.toString());
            e.a(n2);
            final int n4 = n2 * 2;
            n = n3;
            if ((n2 = n4) < 60) {
                continue;
            }
            n2 = 60;
            n = n3;
        }
        if (AMSDevReporter.a) {
            final String tag6 = AMSDevReporter.TAG;
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("Report [");
            sb6.append(string);
            sb6.append("] failed, clear remain report in queue.");
            d.c(tag6, sb6.toString());
        }
    }
    
    private static boolean a(final AMSSdkTypeEnum p0, final Map<String, Object> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: aconst_null    
        //     4: astore          6
        //     6: aconst_null    
        //     7: astore          4
        //     9: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    12: bipush          14
        //    14: if_icmplt       22
        //    17: ldc             40965
        //    19: invokestatic    android/net/TrafficStats.setThreadStatsTag:(I)V
        //    22: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.a:Landroid/content/Context;
        //    25: invokestatic    com/ut/device/UTDevice.getUtdid:(Landroid/content/Context;)Ljava/lang/String;
        //    28: astore          9
        //    30: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //    33: astore          7
        //    35: new             Ljava/lang/StringBuilder;
        //    38: astore          8
        //    40: aload           8
        //    42: invokespecial   java/lang/StringBuilder.<init>:()V
        //    45: aload           8
        //    47: ldc             "stat: "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: pop            
        //    53: aload           8
        //    55: aload           9
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: pop            
        //    61: aload           7
        //    63: aload           8
        //    65: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    68: invokestatic    com/alibaba/sdk/android/utils/d.b:(Ljava/lang/String;Ljava/lang/String;)V
        //    71: aload_0        
        //    72: aload           9
        //    74: aload_1        
        //    75: invokestatic    com/alibaba/sdk/android/utils/AMSDevReporter.a:(Lcom/alibaba/sdk/android/utils/AMSDevReporter$AMSSdkTypeEnum;Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;
        //    78: astore_1       
        //    79: new             Ljava/lang/StringBuilder;
        //    82: astore_0       
        //    83: aload_0        
        //    84: invokespecial   java/lang/StringBuilder.<init>:()V
        //    87: aload_0        
        //    88: ldc             "23356390Raw"
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    93: pop            
        //    94: aload_0        
        //    95: aload_1        
        //    96: invokestatic    com/alibaba/sdk/android/utils/e.a:(Ljava/lang/String;)Ljava/lang/String;
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: pop            
        //   103: aload_0        
        //   104: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   107: astore          7
        //   109: new             Ljava/lang/StringBuilder;
        //   112: astore_0       
        //   113: aload_0        
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: aload_0        
        //   118: ldc             "16594f72217bece5a457b4803a48f2da"
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: pop            
        //   124: aload_0        
        //   125: aload           7
        //   127: invokestatic    com/alibaba/sdk/android/utils/e.a:(Ljava/lang/String;)Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: pop            
        //   134: aload_0        
        //   135: ldc             "16594f72217bece5a457b4803a48f2da"
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: aload_0        
        //   142: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   145: invokestatic    com/alibaba/sdk/android/utils/e.b:(Ljava/lang/String;)Ljava/lang/String;
        //   148: astore_0       
        //   149: new             Ljava/lang/StringBuilder;
        //   152: astore          7
        //   154: aload           7
        //   156: invokespecial   java/lang/StringBuilder.<init>:()V
        //   159: aload           7
        //   161: ldc             "https://adash.man.aliyuncs.com/man/api?ak=23356390&s="
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: pop            
        //   167: aload           7
        //   169: aload_0        
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: pop            
        //   174: aload           7
        //   176: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   179: astore_0       
        //   180: new             Ljava/net/URL;
        //   183: astore          7
        //   185: aload           7
        //   187: aload_0        
        //   188: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   191: aload           7
        //   193: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   196: checkcast       Ljava/net/HttpURLConnection;
        //   199: astore_0       
        //   200: aload_0        
        //   201: iconst_1       
        //   202: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   205: aload_0        
        //   206: iconst_0       
        //   207: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
        //   210: aload_0        
        //   211: sipush          15000
        //   214: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   217: new             Ljava/lang/StringBuilder;
        //   220: astore          5
        //   222: aload           5
        //   224: invokespecial   java/lang/StringBuilder.<init>:()V
        //   227: aload           5
        //   229: ldc             "==="
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: pop            
        //   235: aload           5
        //   237: invokestatic    java/lang/System.currentTimeMillis:()J
        //   240: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   243: pop            
        //   244: aload           5
        //   246: ldc             "==="
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: pop            
        //   252: aload           5
        //   254: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   257: astore          5
        //   259: new             Ljava/lang/StringBuilder;
        //   262: astore          6
        //   264: aload           6
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: aload           6
        //   271: ldc             "multipart/form-data; boundary="
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: pop            
        //   277: aload           6
        //   279: aload           5
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: pop            
        //   285: aload_0        
        //   286: ldc             "Content-Type"
        //   288: aload           6
        //   290: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   293: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   296: new             Ljava/lang/StringBuilder;
        //   299: astore          6
        //   301: aload           6
        //   303: invokespecial   java/lang/StringBuilder.<init>:()V
        //   306: aload           6
        //   308: ldc             "--"
        //   310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: pop            
        //   314: aload           6
        //   316: aload           5
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: pop            
        //   322: aload           6
        //   324: ldc             "\r\nContent-Disposition: form-data; name=\""
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   329: pop            
        //   330: aload           6
        //   332: ldc             "Raw"
        //   334: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   337: pop            
        //   338: aload           6
        //   340: ldc             "\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n"
        //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   345: pop            
        //   346: aload           6
        //   348: aload_1        
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: pop            
        //   353: aload           6
        //   355: ldc             "\r\n--"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: pop            
        //   361: aload           6
        //   363: aload           5
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: pop            
        //   369: aload           6
        //   371: ldc             "--\r\n"
        //   373: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: pop            
        //   377: aload           6
        //   379: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   382: astore_1       
        //   383: aload_0        
        //   384: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   387: astore          5
        //   389: aload           5
        //   391: aload_1        
        //   392: invokevirtual   java/lang/String.getBytes:()[B
        //   395: invokevirtual   java/io/OutputStream.write:([B)V
        //   398: aload_0        
        //   399: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   402: istore_2       
        //   403: iload_2        
        //   404: sipush          200
        //   407: if_icmpne       628
        //   410: new             Ljava/io/DataInputStream;
        //   413: astore_1       
        //   414: aload_1        
        //   415: aload_0        
        //   416: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   419: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //   422: new             Ljava/lang/StringBuilder;
        //   425: astore          4
        //   427: aload           4
        //   429: invokespecial   java/lang/StringBuilder.<init>:()V
        //   432: sipush          1024
        //   435: newarray        B
        //   437: astore          7
        //   439: aload_1        
        //   440: aload           7
        //   442: invokevirtual   java/io/DataInputStream.read:([B)I
        //   445: istore_2       
        //   446: iload_2        
        //   447: iconst_m1      
        //   448: if_icmpeq       476
        //   451: new             Ljava/lang/String;
        //   454: astore          6
        //   456: aload           6
        //   458: aload           7
        //   460: iconst_0       
        //   461: iload_2        
        //   462: invokespecial   java/lang/String.<init>:([BII)V
        //   465: aload           4
        //   467: aload           6
        //   469: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   472: pop            
        //   473: goto            439
        //   476: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   479: astore          6
        //   481: new             Ljava/lang/StringBuilder;
        //   484: astore          7
        //   486: aload           7
        //   488: invokespecial   java/lang/StringBuilder.<init>:()V
        //   491: aload           7
        //   493: ldc_w           "Get MAN response: "
        //   496: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   499: pop            
        //   500: aload           7
        //   502: aload           4
        //   504: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   507: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   510: pop            
        //   511: aload           6
        //   513: aload           7
        //   515: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   518: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/String;)V
        //   521: new             Lorg/json/JSONObject;
        //   524: astore          6
        //   526: aload           6
        //   528: aload           4
        //   530: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   533: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   536: aload           6
        //   538: ldc_w           "success"
        //   541: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
        //   544: checkcast       Ljava/lang/String;
        //   547: ldc_w           "success"
        //   550: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   553: istore_3       
        //   554: iload_3        
        //   555: ifeq            609
        //   558: aload_0        
        //   559: ifnull          566
        //   562: aload_0        
        //   563: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   566: aload           5
        //   568: ifnull          583
        //   571: aload           5
        //   573: invokevirtual   java/io/OutputStream.close:()V
        //   576: goto            583
        //   579: astore_0       
        //   580: goto            590
        //   583: aload_1        
        //   584: invokevirtual   java/io/DataInputStream.close:()V
        //   587: goto            597
        //   590: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   593: aload_0        
        //   594: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   597: iconst_1       
        //   598: ireturn        
        //   599: astore          4
        //   601: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   604: aload           4
        //   606: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   609: goto            670
        //   612: astore          6
        //   614: aload_1        
        //   615: astore          4
        //   617: aload           6
        //   619: astore_1       
        //   620: goto            821
        //   623: astore          4
        //   625: goto            760
        //   628: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   631: astore_1       
        //   632: new             Ljava/lang/StringBuilder;
        //   635: astore          6
        //   637: aload           6
        //   639: invokespecial   java/lang/StringBuilder.<init>:()V
        //   642: aload           6
        //   644: ldc_w           "MAN API error, response code: "
        //   647: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   650: pop            
        //   651: aload           6
        //   653: iload_2        
        //   654: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   657: pop            
        //   658: aload_1        
        //   659: aload           6
        //   661: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   664: invokestatic    com/alibaba/sdk/android/utils/d.c:(Ljava/lang/String;Ljava/lang/String;)V
        //   667: aload           4
        //   669: astore_1       
        //   670: aload_0        
        //   671: ifnull          678
        //   674: aload_0        
        //   675: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   678: aload           5
        //   680: ifnull          688
        //   683: aload           5
        //   685: invokevirtual   java/io/OutputStream.close:()V
        //   688: aload_1        
        //   689: ifnull          811
        //   692: aload_1        
        //   693: invokevirtual   java/io/DataInputStream.close:()V
        //   696: goto            811
        //   699: astore_1       
        //   700: aconst_null    
        //   701: astore          4
        //   703: goto            821
        //   706: astore          4
        //   708: aconst_null    
        //   709: astore_1       
        //   710: goto            760
        //   713: astore_1       
        //   714: aconst_null    
        //   715: astore          5
        //   717: aconst_null    
        //   718: astore          4
        //   720: goto            821
        //   723: astore          4
        //   725: aconst_null    
        //   726: astore          5
        //   728: aconst_null    
        //   729: astore_1       
        //   730: goto            760
        //   733: astore_1       
        //   734: aconst_null    
        //   735: astore          4
        //   737: aconst_null    
        //   738: astore          5
        //   740: aload           6
        //   742: astore_0       
        //   743: goto            821
        //   746: astore          4
        //   748: aconst_null    
        //   749: astore_1       
        //   750: aconst_null    
        //   751: astore          6
        //   753: aload           5
        //   755: astore_0       
        //   756: aload           6
        //   758: astore          5
        //   760: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   763: aload           4
        //   765: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   768: aload_0        
        //   769: ifnull          776
        //   772: aload_0        
        //   773: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   776: aload           5
        //   778: ifnull          793
        //   781: aload           5
        //   783: invokevirtual   java/io/OutputStream.close:()V
        //   786: goto            793
        //   789: astore_0       
        //   790: goto            804
        //   793: aload_1        
        //   794: ifnull          811
        //   797: aload_1        
        //   798: invokevirtual   java/io/DataInputStream.close:()V
        //   801: goto            811
        //   804: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   807: aload_0        
        //   808: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   811: iconst_0       
        //   812: ireturn        
        //   813: astore          6
        //   815: aload_1        
        //   816: astore          4
        //   818: aload           6
        //   820: astore_1       
        //   821: aload_0        
        //   822: ifnull          829
        //   825: aload_0        
        //   826: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   829: aload           5
        //   831: ifnull          846
        //   834: aload           5
        //   836: invokevirtual   java/io/OutputStream.close:()V
        //   839: goto            846
        //   842: astore_0       
        //   843: goto            859
        //   846: aload           4
        //   848: ifnull          866
        //   851: aload           4
        //   853: invokevirtual   java/io/DataInputStream.close:()V
        //   856: goto            866
        //   859: getstatic       com/alibaba/sdk/android/utils/AMSDevReporter.TAG:Ljava/lang/String;
        //   862: aload_0        
        //   863: invokestatic    com/alibaba/sdk/android/utils/d.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   866: aload_1        
        //   867: athrow         
        //    Signature:
        //  (Lcom/alibaba/sdk/android/utils/AMSDevReporter$AMSSdkTypeEnum;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  9      22     746    760    Ljava/lang/Exception;
        //  9      22     733    746    Any
        //  22     200    746    760    Ljava/lang/Exception;
        //  22     200    733    746    Any
        //  200    389    723    733    Ljava/lang/Exception;
        //  200    389    713    723    Any
        //  389    403    706    713    Ljava/lang/Exception;
        //  389    403    699    706    Any
        //  410    422    706    713    Ljava/lang/Exception;
        //  410    422    699    706    Any
        //  422    439    623    628    Ljava/lang/Exception;
        //  422    439    612    623    Any
        //  439    446    623    628    Ljava/lang/Exception;
        //  439    446    612    623    Any
        //  451    473    623    628    Ljava/lang/Exception;
        //  451    473    612    623    Any
        //  476    521    623    628    Ljava/lang/Exception;
        //  476    521    612    623    Any
        //  521    554    599    609    Lorg/json/JSONException;
        //  521    554    623    628    Ljava/lang/Exception;
        //  521    554    612    623    Any
        //  571    576    579    583    Ljava/io/IOException;
        //  583    587    579    583    Ljava/io/IOException;
        //  601    609    623    628    Ljava/lang/Exception;
        //  601    609    612    623    Any
        //  628    667    706    713    Ljava/lang/Exception;
        //  628    667    699    706    Any
        //  683    688    789    793    Ljava/io/IOException;
        //  692    696    789    793    Ljava/io/IOException;
        //  760    768    813    821    Any
        //  781    786    789    793    Ljava/io/IOException;
        //  797    801    789    793    Ljava/io/IOException;
        //  834    839    842    846    Ljava/io/IOException;
        //  851    856    842    846    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0688:
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
    
    public static void asyncReport(final Context context, final AMSSdkTypeEnum amsSdkTypeEnum) {
        asyncReport(context, amsSdkTypeEnum, null);
    }
    
    public static void asyncReport(final Context a, final AMSSdkTypeEnum amsSdkTypeEnum, final Map<String, Object> map) {
        if (a == null) {
            d.c(AMSDevReporter.TAG, "Context is null, return.");
            return;
        }
        AMSDevReporter.a = a;
        final String tag = AMSDevReporter.TAG;
        final StringBuilder sb = new StringBuilder();
        sb.append("Add [");
        sb.append(amsSdkTypeEnum.toString());
        sb.append("] to report queue.");
        d.b(tag, sb.toString());
        ((ExecutorService)(AMSDevReporter.a = false)).execute((Runnable)new Runnable(amsSdkTypeEnum, map) {
            final AMSSdkTypeEnum a;
            final Map a;
            
            public void run() {
                if (AMSDevReporter.a) {
                    d.c(AMSDevReporter.TAG, "Unable to execute remain task in queue, return.");
                }
                else {
                    final String a = AMSDevReporter.TAG;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Get [");
                    sb.append(this.a.toString());
                    sb.append("] from report queue.");
                    d.b(a, sb.toString());
                    a(this.a, (Map<String, Object>)this.a);
                }
            }
        });
    }
    
    public static AMSReportStatusEnum getReportStatus(final AMSSdkTypeEnum amsSdkTypeEnum) {
        return (AMSReportStatusEnum)AMSDevReporter.a.get((Object)amsSdkTypeEnum);
    }
    
    public static void setLogEnabled(final boolean logEnabled) {
        d.setLogEnabled(logEnabled);
    }
    
    public enum AMSReportStatusEnum
    {
        private static final AMSReportStatusEnum[] $VALUES;
        
        REPORTED, 
        UNREPORTED;
    }
    
    public enum AMSSdkExtInfoKeyEnum
    {
        private static final AMSSdkExtInfoKeyEnum[] $VALUES;
        
        AMS_EXTINFO_KEY_PACKAGE("PackageName"), 
        AMS_EXTINFO_KEY_VERSION("SdkVersion");
        
        private String description;
        
        private AMSSdkExtInfoKeyEnum(final String description) {
            this.description = description;
        }
        
        public String toString() {
            return this.description;
        }
    }
    
    public enum AMSSdkTypeEnum
    {
        private static final AMSSdkTypeEnum[] $VALUES;
        
        AMS_API("API"), 
        AMS_FEEDBACK("FEEDBACK"), 
        AMS_HOTFIX("HOTFIX"), 
        AMS_HTTPDNS("HTTPDNS"), 
        AMS_IM("IM"), 
        AMS_MAC("MAC"), 
        AMS_MAN("MAN"), 
        AMS_MPUSH("MPUSH");
        
        private String description;
        
        private AMSSdkTypeEnum(final String description) {
            this.description = description;
        }
        
        public String toString() {
            return this.description;
        }
    }
}
