package com.alibaba.sdk.android.utils.crashdefend;

import java.util.Iterator;
import java.util.List;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.os.Process;
import android.app.ActivityManager;
import android.content.Context;

class e
{
    private static String a(final Context context) {
        final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
        final String s = "";
        if (activityManager == null) {
            return "";
        }
        final List runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }
        final int myPid = Process.myPid();
        final Iterator iterator = runningAppProcesses.iterator();
        ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo;
        do {
            final String processName = s;
            if (!iterator.hasNext()) {
                return processName;
            }
            activityManager$RunningAppProcessInfo = (ActivityManager$RunningAppProcessInfo)iterator.next();
        } while (activityManager$RunningAppProcessInfo.pid != myPid);
        return activityManager$RunningAppProcessInfo.processName;
    }
    
    static void a(final Context p0, final a p1, final List<c> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       5
        //     4: return         
        //     5: aload_2        
        //     6: dup            
        //     7: astore          13
        //     9: monitorenter   
        //    10: aconst_null    
        //    11: astore          6
        //    13: aconst_null    
        //    14: astore          8
        //    16: aconst_null    
        //    17: astore          7
        //    19: aload           7
        //    21: astore_3       
        //    22: aload           6
        //    24: astore          4
        //    26: aload           8
        //    28: astore          5
        //    30: new             Lorg/json/JSONObject;
        //    33: astore          9
        //    35: aload           7
        //    37: astore_3       
        //    38: aload           6
        //    40: astore          4
        //    42: aload           8
        //    44: astore          5
        //    46: aload           9
        //    48: invokespecial   org/json/JSONObject.<init>:()V
        //    51: aload_1        
        //    52: ifnull          78
        //    55: aload           7
        //    57: astore_3       
        //    58: aload           6
        //    60: astore          4
        //    62: aload           8
        //    64: astore          5
        //    66: aload           9
        //    68: ldc             "startSerialNumber"
        //    70: aload_1        
        //    71: getfield        com/alibaba/sdk/android/utils/crashdefend/a.a:J
        //    74: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //    77: pop            
        //    78: aload_2        
        //    79: ifnull          470
        //    82: aload           7
        //    84: astore_3       
        //    85: aload           6
        //    87: astore          4
        //    89: aload           8
        //    91: astore          5
        //    93: new             Lorg/json/JSONArray;
        //    96: astore          11
        //    98: aload           7
        //   100: astore_3       
        //   101: aload           6
        //   103: astore          4
        //   105: aload           8
        //   107: astore          5
        //   109: aload           11
        //   111: invokespecial   org/json/JSONArray.<init>:()V
        //   114: aload           7
        //   116: astore_3       
        //   117: aload           6
        //   119: astore          4
        //   121: aload           8
        //   123: astore          5
        //   125: aload_2        
        //   126: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   131: astore_1       
        //   132: aload           7
        //   134: astore_3       
        //   135: aload           6
        //   137: astore          4
        //   139: aload           8
        //   141: astore          5
        //   143: aload_1        
        //   144: invokeinterface java/util/Iterator.hasNext:()Z
        //   149: ifeq            425
        //   152: aload           7
        //   154: astore_3       
        //   155: aload           6
        //   157: astore          4
        //   159: aload           8
        //   161: astore          5
        //   163: aload_1        
        //   164: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   169: checkcast       Lcom/alibaba/sdk/android/utils/crashdefend/c;
        //   172: astore          10
        //   174: aload           10
        //   176: ifnull          132
        //   179: aload           7
        //   181: astore_3       
        //   182: aload           6
        //   184: astore          4
        //   186: aload           8
        //   188: astore          5
        //   190: new             Lorg/json/JSONObject;
        //   193: astore          12
        //   195: aload           7
        //   197: astore_3       
        //   198: aload           6
        //   200: astore          4
        //   202: aload           8
        //   204: astore          5
        //   206: aload           12
        //   208: invokespecial   org/json/JSONObject.<init>:()V
        //   211: aload           7
        //   213: astore_3       
        //   214: aload           6
        //   216: astore          4
        //   218: aload           8
        //   220: astore          5
        //   222: aload           12
        //   224: ldc             "sdkId"
        //   226: aload           10
        //   228: getfield        com/alibaba/sdk/android/utils/crashdefend/c.a:Ljava/lang/String;
        //   231: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   234: pop            
        //   235: aload           7
        //   237: astore_3       
        //   238: aload           6
        //   240: astore          4
        //   242: aload           8
        //   244: astore          5
        //   246: aload           12
        //   248: ldc             "sdkVersion"
        //   250: aload           10
        //   252: getfield        com/alibaba/sdk/android/utils/crashdefend/c.b:Ljava/lang/String;
        //   255: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   258: pop            
        //   259: aload           7
        //   261: astore_3       
        //   262: aload           6
        //   264: astore          4
        //   266: aload           8
        //   268: astore          5
        //   270: aload           12
        //   272: ldc             "crashLimit"
        //   274: aload           10
        //   276: getfield        com/alibaba/sdk/android/utils/crashdefend/c.a:I
        //   279: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   282: pop            
        //   283: aload           7
        //   285: astore_3       
        //   286: aload           6
        //   288: astore          4
        //   290: aload           8
        //   292: astore          5
        //   294: aload           12
        //   296: ldc             "crashCount"
        //   298: aload           10
        //   300: getfield        com/alibaba/sdk/android/utils/crashdefend/c.crashCount:I
        //   303: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   306: pop            
        //   307: aload           7
        //   309: astore_3       
        //   310: aload           6
        //   312: astore          4
        //   314: aload           8
        //   316: astore          5
        //   318: aload           12
        //   320: ldc             "waitTime"
        //   322: aload           10
        //   324: getfield        com/alibaba/sdk/android/utils/crashdefend/c.b:I
        //   327: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   330: pop            
        //   331: aload           7
        //   333: astore_3       
        //   334: aload           6
        //   336: astore          4
        //   338: aload           8
        //   340: astore          5
        //   342: aload           12
        //   344: ldc             "registerSerialNumber"
        //   346: aload           10
        //   348: getfield        com/alibaba/sdk/android/utils/crashdefend/c.b:J
        //   351: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   354: pop            
        //   355: aload           7
        //   357: astore_3       
        //   358: aload           6
        //   360: astore          4
        //   362: aload           8
        //   364: astore          5
        //   366: aload           12
        //   368: ldc             "startSerialNumber"
        //   370: aload           10
        //   372: getfield        com/alibaba/sdk/android/utils/crashdefend/c.a:J
        //   375: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   378: pop            
        //   379: aload           7
        //   381: astore_3       
        //   382: aload           6
        //   384: astore          4
        //   386: aload           8
        //   388: astore          5
        //   390: aload           12
        //   392: ldc             "restoreCount"
        //   394: aload           10
        //   396: getfield        com/alibaba/sdk/android/utils/crashdefend/c.c:I
        //   399: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   402: pop            
        //   403: aload           7
        //   405: astore_3       
        //   406: aload           6
        //   408: astore          4
        //   410: aload           8
        //   412: astore          5
        //   414: aload           11
        //   416: aload           12
        //   418: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   421: pop            
        //   422: goto            132
        //   425: aload           7
        //   427: astore_3       
        //   428: aload           6
        //   430: astore          4
        //   432: aload           8
        //   434: astore          5
        //   436: aload           9
        //   438: ldc             "sdkList"
        //   440: aload           11
        //   442: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   445: pop            
        //   446: goto            470
        //   449: astore_1       
        //   450: aload           7
        //   452: astore_3       
        //   453: aload           6
        //   455: astore          4
        //   457: aload           8
        //   459: astore          5
        //   461: ldc             "CrashUtils"
        //   463: ldc             "save sdk json fail:"
        //   465: aload_1        
        //   466: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   469: pop            
        //   470: aload           7
        //   472: astore_3       
        //   473: aload           6
        //   475: astore          4
        //   477: aload           8
        //   479: astore          5
        //   481: aload           9
        //   483: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   486: astore_1       
        //   487: aload           7
        //   489: astore_3       
        //   490: aload           6
        //   492: astore          4
        //   494: aload           8
        //   496: astore          5
        //   498: aload_0        
        //   499: invokestatic    com/alibaba/sdk/android/utils/crashdefend/e.a:(Landroid/content/Context;)Z
        //   502: ifeq            527
        //   505: aload           7
        //   507: astore_3       
        //   508: aload           6
        //   510: astore          4
        //   512: aload           8
        //   514: astore          5
        //   516: aload_0        
        //   517: ldc             "com_alibaba_aliyun_crash_defend_sdk_info"
        //   519: iconst_0       
        //   520: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //   523: astore_0       
        //   524: goto            624
        //   527: aload           7
        //   529: astore_3       
        //   530: aload           6
        //   532: astore          4
        //   534: aload           8
        //   536: astore          5
        //   538: new             Ljava/lang/StringBuilder;
        //   541: astore          9
        //   543: aload           7
        //   545: astore_3       
        //   546: aload           6
        //   548: astore          4
        //   550: aload           8
        //   552: astore          5
        //   554: aload           9
        //   556: invokespecial   java/lang/StringBuilder.<init>:()V
        //   559: aload           7
        //   561: astore_3       
        //   562: aload           6
        //   564: astore          4
        //   566: aload           8
        //   568: astore          5
        //   570: aload           9
        //   572: ldc             "com_alibaba_aliyun_crash_defend_sdk_info_"
        //   574: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   577: pop            
        //   578: aload           7
        //   580: astore_3       
        //   581: aload           6
        //   583: astore          4
        //   585: aload           8
        //   587: astore          5
        //   589: aload           9
        //   591: aload_0        
        //   592: invokestatic    com/alibaba/sdk/android/utils/crashdefend/e.a:(Landroid/content/Context;)Ljava/lang/String;
        //   595: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   598: pop            
        //   599: aload           7
        //   601: astore_3       
        //   602: aload           6
        //   604: astore          4
        //   606: aload           8
        //   608: astore          5
        //   610: aload_0        
        //   611: aload           9
        //   613: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   616: iconst_0       
        //   617: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //   620: astore_0       
        //   621: goto            524
        //   624: aload_0        
        //   625: astore_3       
        //   626: aload_0        
        //   627: astore          4
        //   629: aload_0        
        //   630: astore          5
        //   632: aload_0        
        //   633: aload_1        
        //   634: invokevirtual   java/lang/String.getBytes:()[B
        //   637: invokevirtual   java/io/FileOutputStream.write:([B)V
        //   640: aload_0        
        //   641: ifnull          728
        //   644: aload_0        
        //   645: invokevirtual   java/io/FileOutputStream.close:()V
        //   648: goto            728
        //   651: astore_0       
        //   652: ldc             "CrashUtils"
        //   654: ldc             "save sdk io fail:"
        //   656: aload_0        
        //   657: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   660: pop            
        //   661: goto            728
        //   664: astore_0       
        //   665: goto            732
        //   668: astore_0       
        //   669: aload           4
        //   671: astore_3       
        //   672: ldc             "CrashUtils"
        //   674: ldc             "save sdk exception:"
        //   676: aload_0        
        //   677: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   680: pop            
        //   681: aload           4
        //   683: ifnull          728
        //   686: aload           4
        //   688: invokevirtual   java/io/FileOutputStream.close:()V
        //   691: goto            728
        //   694: astore_0       
        //   695: goto            652
        //   698: astore_0       
        //   699: aload           5
        //   701: astore_3       
        //   702: ldc             "CrashUtils"
        //   704: ldc             "save sdk io fail:"
        //   706: aload_0        
        //   707: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   710: pop            
        //   711: aload           5
        //   713: ifnull          728
        //   716: aload           5
        //   718: invokevirtual   java/io/FileOutputStream.close:()V
        //   721: goto            728
        //   724: astore_0       
        //   725: goto            652
        //   728: aload           13
        //   730: monitorexit    
        //   731: return         
        //   732: aload_3        
        //   733: ifnull          753
        //   736: aload_3        
        //   737: invokevirtual   java/io/FileOutputStream.close:()V
        //   740: goto            753
        //   743: astore_1       
        //   744: ldc             "CrashUtils"
        //   746: ldc             "save sdk io fail:"
        //   748: aload_1        
        //   749: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   752: pop            
        //   753: aload_0        
        //   754: athrow         
        //   755: astore_0       
        //   756: aload           13
        //   758: monitorexit    
        //   759: aload_0        
        //   760: athrow         
        //    Signature:
        //  (Landroid/content/Context;Lcom/alibaba/sdk/android/utils/crashdefend/a;Ljava/util/List<Lcom/alibaba/sdk/android/utils/crashdefend/c;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  30     35     698    728    Ljava/io/IOException;
        //  30     35     668    698    Ljava/lang/Exception;
        //  30     35     664    755    Any
        //  46     51     698    728    Ljava/io/IOException;
        //  46     51     668    698    Ljava/lang/Exception;
        //  46     51     664    755    Any
        //  66     78     698    728    Ljava/io/IOException;
        //  66     78     668    698    Ljava/lang/Exception;
        //  66     78     664    755    Any
        //  93     98     449    470    Lorg/json/JSONException;
        //  93     98     698    728    Ljava/io/IOException;
        //  93     98     668    698    Ljava/lang/Exception;
        //  93     98     664    755    Any
        //  109    114    449    470    Lorg/json/JSONException;
        //  109    114    698    728    Ljava/io/IOException;
        //  109    114    668    698    Ljava/lang/Exception;
        //  109    114    664    755    Any
        //  125    132    449    470    Lorg/json/JSONException;
        //  125    132    698    728    Ljava/io/IOException;
        //  125    132    668    698    Ljava/lang/Exception;
        //  125    132    664    755    Any
        //  143    152    449    470    Lorg/json/JSONException;
        //  143    152    698    728    Ljava/io/IOException;
        //  143    152    668    698    Ljava/lang/Exception;
        //  143    152    664    755    Any
        //  163    174    449    470    Lorg/json/JSONException;
        //  163    174    698    728    Ljava/io/IOException;
        //  163    174    668    698    Ljava/lang/Exception;
        //  163    174    664    755    Any
        //  190    195    449    470    Lorg/json/JSONException;
        //  190    195    698    728    Ljava/io/IOException;
        //  190    195    668    698    Ljava/lang/Exception;
        //  190    195    664    755    Any
        //  206    211    449    470    Lorg/json/JSONException;
        //  206    211    698    728    Ljava/io/IOException;
        //  206    211    668    698    Ljava/lang/Exception;
        //  206    211    664    755    Any
        //  222    235    449    470    Lorg/json/JSONException;
        //  222    235    698    728    Ljava/io/IOException;
        //  222    235    668    698    Ljava/lang/Exception;
        //  222    235    664    755    Any
        //  246    259    449    470    Lorg/json/JSONException;
        //  246    259    698    728    Ljava/io/IOException;
        //  246    259    668    698    Ljava/lang/Exception;
        //  246    259    664    755    Any
        //  270    283    449    470    Lorg/json/JSONException;
        //  270    283    698    728    Ljava/io/IOException;
        //  270    283    668    698    Ljava/lang/Exception;
        //  270    283    664    755    Any
        //  294    307    449    470    Lorg/json/JSONException;
        //  294    307    698    728    Ljava/io/IOException;
        //  294    307    668    698    Ljava/lang/Exception;
        //  294    307    664    755    Any
        //  318    331    449    470    Lorg/json/JSONException;
        //  318    331    698    728    Ljava/io/IOException;
        //  318    331    668    698    Ljava/lang/Exception;
        //  318    331    664    755    Any
        //  342    355    449    470    Lorg/json/JSONException;
        //  342    355    698    728    Ljava/io/IOException;
        //  342    355    668    698    Ljava/lang/Exception;
        //  342    355    664    755    Any
        //  366    379    449    470    Lorg/json/JSONException;
        //  366    379    698    728    Ljava/io/IOException;
        //  366    379    668    698    Ljava/lang/Exception;
        //  366    379    664    755    Any
        //  390    403    449    470    Lorg/json/JSONException;
        //  390    403    698    728    Ljava/io/IOException;
        //  390    403    668    698    Ljava/lang/Exception;
        //  390    403    664    755    Any
        //  414    422    449    470    Lorg/json/JSONException;
        //  414    422    698    728    Ljava/io/IOException;
        //  414    422    668    698    Ljava/lang/Exception;
        //  414    422    664    755    Any
        //  436    446    449    470    Lorg/json/JSONException;
        //  436    446    698    728    Ljava/io/IOException;
        //  436    446    668    698    Ljava/lang/Exception;
        //  436    446    664    755    Any
        //  461    470    698    728    Ljava/io/IOException;
        //  461    470    668    698    Ljava/lang/Exception;
        //  461    470    664    755    Any
        //  481    487    698    728    Ljava/io/IOException;
        //  481    487    668    698    Ljava/lang/Exception;
        //  481    487    664    755    Any
        //  498    505    698    728    Ljava/io/IOException;
        //  498    505    668    698    Ljava/lang/Exception;
        //  498    505    664    755    Any
        //  516    524    698    728    Ljava/io/IOException;
        //  516    524    668    698    Ljava/lang/Exception;
        //  516    524    664    755    Any
        //  538    543    698    728    Ljava/io/IOException;
        //  538    543    668    698    Ljava/lang/Exception;
        //  538    543    664    755    Any
        //  554    559    698    728    Ljava/io/IOException;
        //  554    559    668    698    Ljava/lang/Exception;
        //  554    559    664    755    Any
        //  570    578    698    728    Ljava/io/IOException;
        //  570    578    668    698    Ljava/lang/Exception;
        //  570    578    664    755    Any
        //  589    599    698    728    Ljava/io/IOException;
        //  589    599    668    698    Ljava/lang/Exception;
        //  589    599    664    755    Any
        //  610    621    698    728    Ljava/io/IOException;
        //  610    621    668    698    Ljava/lang/Exception;
        //  610    621    664    755    Any
        //  632    640    698    728    Ljava/io/IOException;
        //  632    640    668    698    Ljava/lang/Exception;
        //  632    640    664    755    Any
        //  644    648    651    652    Ljava/io/IOException;
        //  644    648    755    761    Any
        //  652    661    755    761    Any
        //  672    681    664    755    Any
        //  686    691    694    698    Ljava/io/IOException;
        //  686    691    755    761    Any
        //  702    711    664    755    Any
        //  716    721    724    728    Ljava/io/IOException;
        //  716    721    755    761    Any
        //  728    731    755    761    Any
        //  736    740    743    753    Ljava/io/IOException;
        //  736    740    755    761    Any
        //  744    753    755    761    Any
        //  753    755    755    761    Any
        //  756    759    755    761    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 393, Size: 393
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
    
    private static boolean a(final Context context) {
        return context.getPackageName().equalsIgnoreCase(a(context));
    }
    
    static boolean a(final Context p0, final a p1, final List<c> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: aconst_null    
        //     7: astore          11
        //     9: aconst_null    
        //    10: astore          9
        //    12: aconst_null    
        //    13: astore          8
        //    15: aconst_null    
        //    16: astore          10
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: astore          12
        //    27: aload_2        
        //    28: dup            
        //    29: astore          14
        //    31: monitorenter   
        //    32: aload           10
        //    34: astore          4
        //    36: aload           11
        //    38: astore          7
        //    40: aload           9
        //    42: astore          6
        //    44: aload           8
        //    46: astore          5
        //    48: aload_0        
        //    49: invokestatic    com/alibaba/sdk/android/utils/crashdefend/e.a:(Landroid/content/Context;)Z
        //    52: ifeq            81
        //    55: aload           10
        //    57: astore          4
        //    59: aload           11
        //    61: astore          7
        //    63: aload           9
        //    65: astore          6
        //    67: aload           8
        //    69: astore          5
        //    71: aload_0        
        //    72: ldc             "com_alibaba_aliyun_crash_defend_sdk_info"
        //    74: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    77: astore_0       
        //    78: goto            202
        //    81: aload           10
        //    83: astore          4
        //    85: aload           11
        //    87: astore          7
        //    89: aload           9
        //    91: astore          6
        //    93: aload           8
        //    95: astore          5
        //    97: new             Ljava/lang/StringBuilder;
        //   100: astore          13
        //   102: aload           10
        //   104: astore          4
        //   106: aload           11
        //   108: astore          7
        //   110: aload           9
        //   112: astore          6
        //   114: aload           8
        //   116: astore          5
        //   118: aload           13
        //   120: invokespecial   java/lang/StringBuilder.<init>:()V
        //   123: aload           10
        //   125: astore          4
        //   127: aload           11
        //   129: astore          7
        //   131: aload           9
        //   133: astore          6
        //   135: aload           8
        //   137: astore          5
        //   139: aload           13
        //   141: ldc             "com_alibaba_aliyun_crash_defend_sdk_info_"
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: pop            
        //   147: aload           10
        //   149: astore          4
        //   151: aload           11
        //   153: astore          7
        //   155: aload           9
        //   157: astore          6
        //   159: aload           8
        //   161: astore          5
        //   163: aload           13
        //   165: aload_0        
        //   166: invokestatic    com/alibaba/sdk/android/utils/crashdefend/e.a:(Landroid/content/Context;)Ljava/lang/String;
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: pop            
        //   173: aload           10
        //   175: astore          4
        //   177: aload           11
        //   179: astore          7
        //   181: aload           9
        //   183: astore          6
        //   185: aload           8
        //   187: astore          5
        //   189: aload_0        
        //   190: aload           13
        //   192: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   195: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //   198: astore_0       
        //   199: goto            78
        //   202: aload_0        
        //   203: astore          4
        //   205: aload_0        
        //   206: astore          7
        //   208: aload_0        
        //   209: astore          6
        //   211: aload_0        
        //   212: astore          5
        //   214: sipush          512
        //   217: newarray        B
        //   219: astore          8
        //   221: aload_0        
        //   222: astore          4
        //   224: aload_0        
        //   225: astore          7
        //   227: aload_0        
        //   228: astore          6
        //   230: aload_0        
        //   231: astore          5
        //   233: aload_0        
        //   234: aload           8
        //   236: invokevirtual   java/io/FileInputStream.read:([B)I
        //   239: istore_3       
        //   240: iload_3        
        //   241: iconst_m1      
        //   242: if_icmpeq       306
        //   245: aload_0        
        //   246: astore          4
        //   248: aload_0        
        //   249: astore          7
        //   251: aload_0        
        //   252: astore          6
        //   254: aload_0        
        //   255: astore          5
        //   257: new             Ljava/lang/String;
        //   260: astore          9
        //   262: aload_0        
        //   263: astore          4
        //   265: aload_0        
        //   266: astore          7
        //   268: aload_0        
        //   269: astore          6
        //   271: aload_0        
        //   272: astore          5
        //   274: aload           9
        //   276: aload           8
        //   278: iconst_0       
        //   279: iload_3        
        //   280: invokespecial   java/lang/String.<init>:([BII)V
        //   283: aload_0        
        //   284: astore          4
        //   286: aload_0        
        //   287: astore          7
        //   289: aload_0        
        //   290: astore          6
        //   292: aload_0        
        //   293: astore          5
        //   295: aload           12
        //   297: aload           9
        //   299: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   302: pop            
        //   303: goto            221
        //   306: aload_0        
        //   307: ifnull          427
        //   310: aload_0        
        //   311: invokevirtual   java/io/FileInputStream.close:()V
        //   314: goto            427
        //   317: astore_0       
        //   318: ldc             "CrashUtils"
        //   320: ldc             "load sdk io fail:"
        //   322: aload_0        
        //   323: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   326: pop            
        //   327: goto            427
        //   330: astore_0       
        //   331: goto            651
        //   334: astore_0       
        //   335: aload           7
        //   337: astore          4
        //   339: ldc             "CrashUtils"
        //   341: ldc             "load sdk exception:"
        //   343: aload_0        
        //   344: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   347: pop            
        //   348: aload           7
        //   350: ifnull          427
        //   353: aload           7
        //   355: invokevirtual   java/io/FileInputStream.close:()V
        //   358: goto            427
        //   361: astore_0       
        //   362: goto            318
        //   365: astore_0       
        //   366: aload           6
        //   368: astore          4
        //   370: ldc             "CrashUtils"
        //   372: ldc             "load sdk io fail:"
        //   374: aload_0        
        //   375: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   378: pop            
        //   379: aload           6
        //   381: ifnull          427
        //   384: aload           6
        //   386: invokevirtual   java/io/FileInputStream.close:()V
        //   389: goto            427
        //   392: astore_0       
        //   393: goto            318
        //   396: astore_0       
        //   397: aload           5
        //   399: astore          4
        //   401: ldc             "CrashUtils"
        //   403: ldc             "load sdk file fail:"
        //   405: aload_0        
        //   406: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   409: pop            
        //   410: aload           5
        //   412: ifnull          427
        //   415: aload           5
        //   417: invokevirtual   java/io/FileInputStream.close:()V
        //   420: goto            427
        //   423: astore_0       
        //   424: goto            318
        //   427: aload           12
        //   429: invokevirtual   java/lang/StringBuilder.length:()I
        //   432: ifne            440
        //   435: aload           14
        //   437: monitorexit    
        //   438: iconst_0       
        //   439: ireturn        
        //   440: new             Lorg/json/JSONObject;
        //   443: astore_0       
        //   444: aload_0        
        //   445: aload           12
        //   447: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   450: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   453: aload_1        
        //   454: aload_0        
        //   455: ldc             "startSerialNumber"
        //   457: lconst_1       
        //   458: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   461: putfield        com/alibaba/sdk/android/utils/crashdefend/a.a:J
        //   464: aload_0        
        //   465: ldc             "sdkList"
        //   467: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //   470: astore_1       
        //   471: iconst_0       
        //   472: istore_3       
        //   473: iload_3        
        //   474: aload_1        
        //   475: invokevirtual   org/json/JSONArray.length:()I
        //   478: if_icmpge       646
        //   481: aload_1        
        //   482: iload_3        
        //   483: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   486: astore          4
        //   488: aload           4
        //   490: ifnull          617
        //   493: new             Lcom/alibaba/sdk/android/utils/crashdefend/c;
        //   496: astore_0       
        //   497: aload_0        
        //   498: invokespecial   com/alibaba/sdk/android/utils/crashdefend/c.<init>:()V
        //   501: aload_0        
        //   502: aload           4
        //   504: ldc             "sdkId"
        //   506: ldc             ""
        //   508: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   511: putfield        com/alibaba/sdk/android/utils/crashdefend/c.a:Ljava/lang/String;
        //   514: aload_0        
        //   515: aload           4
        //   517: ldc             "sdkVersion"
        //   519: ldc             ""
        //   521: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   524: putfield        com/alibaba/sdk/android/utils/crashdefend/c.b:Ljava/lang/String;
        //   527: aload_0        
        //   528: aload           4
        //   530: ldc             "crashLimit"
        //   532: iconst_m1      
        //   533: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   536: putfield        com/alibaba/sdk/android/utils/crashdefend/c.a:I
        //   539: aload_0        
        //   540: aload           4
        //   542: ldc             "crashCount"
        //   544: iconst_0       
        //   545: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   548: putfield        com/alibaba/sdk/android/utils/crashdefend/c.crashCount:I
        //   551: aload_0        
        //   552: aload           4
        //   554: ldc             "waitTime"
        //   556: iconst_0       
        //   557: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   560: putfield        com/alibaba/sdk/android/utils/crashdefend/c.b:I
        //   563: aload_0        
        //   564: aload           4
        //   566: ldc             "registerSerialNumber"
        //   568: lconst_0       
        //   569: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   572: putfield        com/alibaba/sdk/android/utils/crashdefend/c.b:J
        //   575: aload_0        
        //   576: aload           4
        //   578: ldc             "startSerialNumber"
        //   580: lconst_0       
        //   581: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   584: putfield        com/alibaba/sdk/android/utils/crashdefend/c.a:J
        //   587: aload_0        
        //   588: aload           4
        //   590: ldc             "restoreCount"
        //   592: iconst_0       
        //   593: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   596: putfield        com/alibaba/sdk/android/utils/crashdefend/c.c:I
        //   599: aload_0        
        //   600: getfield        com/alibaba/sdk/android/utils/crashdefend/c.a:Ljava/lang/String;
        //   603: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   606: ifne            617
        //   609: aload_2        
        //   610: aload_0        
        //   611: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   616: pop            
        //   617: iinc            3, 1
        //   620: goto            473
        //   623: astore_0       
        //   624: ldc             "CrashUtils"
        //   626: ldc             "load sdk exception:"
        //   628: aload_0        
        //   629: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   632: pop            
        //   633: goto            646
        //   636: astore_0       
        //   637: ldc             "CrashUtils"
        //   639: ldc             "load sdk json fail:"
        //   641: aload_0        
        //   642: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   645: pop            
        //   646: aload           14
        //   648: monitorexit    
        //   649: iconst_1       
        //   650: ireturn        
        //   651: aload           4
        //   653: ifnull          674
        //   656: aload           4
        //   658: invokevirtual   java/io/FileInputStream.close:()V
        //   661: goto            674
        //   664: astore_1       
        //   665: ldc             "CrashUtils"
        //   667: ldc             "load sdk io fail:"
        //   669: aload_1        
        //   670: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   673: pop            
        //   674: aload_0        
        //   675: athrow         
        //   676: astore_0       
        //   677: aload           14
        //   679: monitorexit    
        //   680: aload_0        
        //   681: athrow         
        //    Signature:
        //  (Landroid/content/Context;Lcom/alibaba/sdk/android/utils/crashdefend/a;Ljava/util/List<Lcom/alibaba/sdk/android/utils/crashdefend/c;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  48     55     396    427    Ljava/io/FileNotFoundException;
        //  48     55     365    396    Ljava/io/IOException;
        //  48     55     334    365    Ljava/lang/Exception;
        //  48     55     330    676    Any
        //  71     78     396    427    Ljava/io/FileNotFoundException;
        //  71     78     365    396    Ljava/io/IOException;
        //  71     78     334    365    Ljava/lang/Exception;
        //  71     78     330    676    Any
        //  97     102    396    427    Ljava/io/FileNotFoundException;
        //  97     102    365    396    Ljava/io/IOException;
        //  97     102    334    365    Ljava/lang/Exception;
        //  97     102    330    676    Any
        //  118    123    396    427    Ljava/io/FileNotFoundException;
        //  118    123    365    396    Ljava/io/IOException;
        //  118    123    334    365    Ljava/lang/Exception;
        //  118    123    330    676    Any
        //  139    147    396    427    Ljava/io/FileNotFoundException;
        //  139    147    365    396    Ljava/io/IOException;
        //  139    147    334    365    Ljava/lang/Exception;
        //  139    147    330    676    Any
        //  163    173    396    427    Ljava/io/FileNotFoundException;
        //  163    173    365    396    Ljava/io/IOException;
        //  163    173    334    365    Ljava/lang/Exception;
        //  163    173    330    676    Any
        //  189    199    396    427    Ljava/io/FileNotFoundException;
        //  189    199    365    396    Ljava/io/IOException;
        //  189    199    334    365    Ljava/lang/Exception;
        //  189    199    330    676    Any
        //  214    221    396    427    Ljava/io/FileNotFoundException;
        //  214    221    365    396    Ljava/io/IOException;
        //  214    221    334    365    Ljava/lang/Exception;
        //  214    221    330    676    Any
        //  233    240    396    427    Ljava/io/FileNotFoundException;
        //  233    240    365    396    Ljava/io/IOException;
        //  233    240    334    365    Ljava/lang/Exception;
        //  233    240    330    676    Any
        //  257    262    396    427    Ljava/io/FileNotFoundException;
        //  257    262    365    396    Ljava/io/IOException;
        //  257    262    334    365    Ljava/lang/Exception;
        //  257    262    330    676    Any
        //  274    283    396    427    Ljava/io/FileNotFoundException;
        //  274    283    365    396    Ljava/io/IOException;
        //  274    283    334    365    Ljava/lang/Exception;
        //  274    283    330    676    Any
        //  295    303    396    427    Ljava/io/FileNotFoundException;
        //  295    303    365    396    Ljava/io/IOException;
        //  295    303    334    365    Ljava/lang/Exception;
        //  295    303    330    676    Any
        //  310    314    317    318    Ljava/io/IOException;
        //  310    314    676    682    Any
        //  318    327    676    682    Any
        //  339    348    330    676    Any
        //  353    358    361    365    Ljava/io/IOException;
        //  353    358    676    682    Any
        //  370    379    330    676    Any
        //  384    389    392    396    Ljava/io/IOException;
        //  384    389    676    682    Any
        //  401    410    330    676    Any
        //  415    420    423    427    Ljava/io/IOException;
        //  415    420    676    682    Any
        //  427    438    676    682    Any
        //  440    471    636    646    Lorg/json/JSONException;
        //  440    471    623    636    Ljava/lang/Exception;
        //  440    471    676    682    Any
        //  473    488    636    646    Lorg/json/JSONException;
        //  473    488    623    636    Ljava/lang/Exception;
        //  473    488    676    682    Any
        //  493    617    636    646    Lorg/json/JSONException;
        //  493    617    623    636    Ljava/lang/Exception;
        //  493    617    676    682    Any
        //  624    633    676    682    Any
        //  637    646    676    682    Any
        //  646    649    676    682    Any
        //  656    661    664    674    Ljava/io/IOException;
        //  656    661    676    682    Any
        //  665    674    676    682    Any
        //  674    676    676    682    Any
        //  677    680    676    682    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 356, Size: 356
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
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
