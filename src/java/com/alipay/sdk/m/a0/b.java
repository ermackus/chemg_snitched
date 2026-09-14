package com.alipay.sdk.m.a0;

import java.util.Map;
import android.provider.Settings$Secure;
import java.io.FileFilter;
import java.util.Enumeration;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.lang.reflect.Field;
import android.os.Build;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.io.File;
import android.telephony.TelephonyManager;
import android.os.SystemClock;
import java.util.TimeZone;
import java.util.Locale;
import android.util.DisplayMetrics;
import org.json.JSONArray;
import java.util.Iterator;
import java.util.List;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import org.json.JSONObject;
import android.provider.Settings$System;
import com.alipay.sdk.m.z.a;
import android.os.StatFs;
import android.os.Environment;
import android.content.Context;

public final class b
{
    public static b b;
    public f a;
    
    static {
        com.alipay.sdk.m.a0.b.b = new b();
    }
    
    public static b a(final f a) {
        final b b = com.alipay.sdk.m.a0.b.b;
        b.a = a;
        return b;
    }
    
    public static boolean a(final Context context, final String s) {
        return context.getPackageManager().checkPermission(s, context.getPackageName()) != 0;
    }
    
    public static String b() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            final long n = statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
        }
        finally {
            n2 = 0L;
        }
        return String.valueOf(n2);
    }
    
    public static String c() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs("/sdcard");
            final long n = statFs.getBlockSize() * (long)statFs.getAvailableBlocks();
        }
        finally {
            n2 = 0L;
        }
        return String.valueOf(n2);
    }
    
    public static String d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore_1       
        //     3: aconst_null    
        //     4: astore          4
        //     6: new             Ljava/io/FileInputStream;
        //     9: astore_3       
        //    10: new             Ljava/io/File;
        //    13: astore_2       
        //    14: aload_2        
        //    15: ldc             "/proc/cpuinfo"
        //    17: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    20: aload_3        
        //    21: aload_2        
        //    22: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    25: new             Ljava/io/InputStreamReader;
        //    28: astore          5
        //    30: aload           5
        //    32: aload_3        
        //    33: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    36: new             Ljava/io/LineNumberReader;
        //    39: astore          6
        //    41: aload           6
        //    43: aload           5
        //    45: invokespecial   java/io/LineNumberReader.<init>:(Ljava/io/Reader;)V
        //    48: iconst_1       
        //    49: istore_0       
        //    50: aload_1        
        //    51: astore_2       
        //    52: iload_0        
        //    53: bipush          100
        //    55: if_icmpge       127
        //    58: aload           6
        //    60: invokevirtual   java/io/LineNumberReader.readLine:()Ljava/lang/String;
        //    63: astore          4
        //    65: aload_1        
        //    66: astore_2       
        //    67: aload           4
        //    69: ifnull          127
        //    72: aload           4
        //    74: ldc             "Serial"
        //    76: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    79: iflt            108
        //    82: aload           4
        //    84: aload           4
        //    86: ldc             ":"
        //    88: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    91: iconst_1       
        //    92: iadd           
        //    93: aload           4
        //    95: invokevirtual   java/lang/String.length:()I
        //    98: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   101: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   104: astore_2       
        //   105: goto            127
        //   108: iinc            0, 1
        //   111: goto            50
        //   114: astore_2       
        //   115: aload           6
        //   117: astore          4
        //   119: aload_3        
        //   120: astore_2       
        //   121: aload           5
        //   123: astore_3       
        //   124: goto            170
        //   127: aload           6
        //   129: invokevirtual   java/io/LineNumberReader.close:()V
        //   132: aload           5
        //   134: invokevirtual   java/io/InputStreamReader.close:()V
        //   137: aload_2        
        //   138: astore_1       
        //   139: aload_3        
        //   140: astore_2       
        //   141: goto            203
        //   144: astore_2       
        //   145: aload_3        
        //   146: astore_2       
        //   147: aload           5
        //   149: astore_3       
        //   150: goto            170
        //   153: astore_2       
        //   154: aconst_null    
        //   155: astore          5
        //   157: aload_3        
        //   158: astore_2       
        //   159: aload           5
        //   161: astore_3       
        //   162: goto            170
        //   165: astore_2       
        //   166: aconst_null    
        //   167: astore_2       
        //   168: aconst_null    
        //   169: astore_3       
        //   170: aload           4
        //   172: ifnull          185
        //   175: aload           4
        //   177: invokevirtual   java/io/LineNumberReader.close:()V
        //   180: goto            185
        //   183: astore          4
        //   185: aload_3        
        //   186: ifnull          197
        //   189: aload_3        
        //   190: invokevirtual   java/io/InputStreamReader.close:()V
        //   193: goto            197
        //   196: astore_3       
        //   197: aload_1        
        //   198: astore_3       
        //   199: aload_2        
        //   200: ifnull          215
        //   203: aload_2        
        //   204: invokevirtual   java/io/FileInputStream.close:()V
        //   207: aload_1        
        //   208: astore_3       
        //   209: goto            215
        //   212: astore_2       
        //   213: aload_1        
        //   214: astore_3       
        //   215: aload_3        
        //   216: astore_1       
        //   217: aload_3        
        //   218: ifnonnull       224
        //   221: ldc             ""
        //   223: astore_1       
        //   224: aload_1        
        //   225: areturn        
        //   226: astore_1       
        //   227: goto            132
        //   230: astore_1       
        //   231: aload_2        
        //   232: astore_1       
        //   233: aload_3        
        //   234: astore_2       
        //   235: goto            203
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  6      25     165    170    Any
        //  25     36     153    165    Any
        //  36     48     144    153    Any
        //  58     65     114    127    Any
        //  72     105    114    127    Any
        //  127    132    226    230    Any
        //  132    137    230    238    Any
        //  175    180    183    185    Any
        //  189    193    196    197    Any
        //  203    207    212    215    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 132, Size: 132
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
    
    public static String e() {
        final String t = t();
        if (!a.a(t)) {
            return t;
        }
        return u();
    }
    
    public static String f() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: new             Ljava/io/FileReader;
        //     5: astore_1       
        //     6: aload_1        
        //     7: ldc             "/proc/cpuinfo"
        //     9: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    12: new             Ljava/io/BufferedReader;
        //    15: astore_0       
        //    16: aload_0        
        //    17: aload_1        
        //    18: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    21: aload_0        
        //    22: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    25: ldc             ":\\s+"
        //    27: iconst_2       
        //    28: invokevirtual   java/lang/String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //    31: astore_2       
        //    32: aload_2        
        //    33: ifnull          56
        //    36: aload_2        
        //    37: arraylength    
        //    38: iconst_1       
        //    39: if_icmple       56
        //    42: aload_2        
        //    43: iconst_1       
        //    44: aaload         
        //    45: astore_2       
        //    46: aload_1        
        //    47: invokevirtual   java/io/FileReader.close:()V
        //    50: aload_0        
        //    51: invokevirtual   java/io/BufferedReader.close:()V
        //    54: aload_2        
        //    55: areturn        
        //    56: aload_1        
        //    57: invokevirtual   java/io/FileReader.close:()V
        //    60: goto            90
        //    63: astore_0       
        //    64: aconst_null    
        //    65: astore_0       
        //    66: goto            74
        //    69: astore_0       
        //    70: aconst_null    
        //    71: astore_0       
        //    72: aload_2        
        //    73: astore_1       
        //    74: aload_1        
        //    75: ifnull          86
        //    78: aload_1        
        //    79: invokevirtual   java/io/FileReader.close:()V
        //    82: goto            86
        //    85: astore_1       
        //    86: aload_0        
        //    87: ifnull          94
        //    90: aload_0        
        //    91: invokevirtual   java/io/BufferedReader.close:()V
        //    94: ldc             ""
        //    96: areturn        
        //    97: astore_2       
        //    98: goto            66
        //   101: astore_1       
        //   102: goto            50
        //   105: astore_0       
        //   106: goto            54
        //   109: astore_1       
        //   110: goto            90
        //   113: astore_0       
        //   114: goto            94
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      12     69     74     Any
        //  12     21     63     66     Any
        //  21     32     97     101    Any
        //  36     42     97     101    Any
        //  46     50     101    105    Any
        //  50     54     105    109    Any
        //  56     60     109    113    Any
        //  78     82     85     86     Any
        //  90     94     113    117    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 68, Size: 68
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
    
    public static String f(final Context context) {
        final int n = 0;
        int n2;
        try {
            Settings$System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        }
        finally {
            n2 = n;
        }
        if (n2 == 1) {
            return "1";
        }
        return "0";
    }
    
    public static String g() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: lconst_0       
        //     4: lstore_1       
        //     5: new             Ljava/io/FileReader;
        //     8: astore          6
        //    10: aload           6
        //    12: ldc             "/proc/meminfo"
        //    14: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    17: new             Ljava/io/BufferedReader;
        //    20: astore          5
        //    22: aload           5
        //    24: aload           6
        //    26: sipush          8192
        //    29: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    32: aload           5
        //    34: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    37: astore          7
        //    39: lload_1        
        //    40: lstore_3       
        //    41: aload           7
        //    43: ifnull          62
        //    46: aload           7
        //    48: ldc             "\\s+"
        //    50: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    53: iconst_1       
        //    54: aaload         
        //    55: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //    58: istore_0       
        //    59: iload_0        
        //    60: i2l            
        //    61: lstore_3       
        //    62: aload           6
        //    64: invokevirtual   java/io/FileReader.close:()V
        //    67: lload_3        
        //    68: lstore_1       
        //    69: goto            115
        //    72: astore          5
        //    74: aconst_null    
        //    75: astore          5
        //    77: goto            93
        //    80: astore          6
        //    82: aconst_null    
        //    83: astore          7
        //    85: aload           5
        //    87: astore          6
        //    89: aload           7
        //    91: astore          5
        //    93: aload           6
        //    95: ifnull          108
        //    98: aload           6
        //   100: invokevirtual   java/io/FileReader.close:()V
        //   103: goto            108
        //   106: astore          6
        //   108: lload_1        
        //   109: lstore_3       
        //   110: aload           5
        //   112: ifnull          122
        //   115: aload           5
        //   117: invokevirtual   java/io/BufferedReader.close:()V
        //   120: lload_1        
        //   121: lstore_3       
        //   122: lload_3        
        //   123: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   126: areturn        
        //   127: astore          7
        //   129: goto            77
        //   132: astore          6
        //   134: lload_3        
        //   135: lstore_1       
        //   136: goto            115
        //   139: astore          5
        //   141: lload_1        
        //   142: lstore_3       
        //   143: goto            122
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      17     80     93     Any
        //  17     32     72     77     Any
        //  32     39     127    132    Any
        //  46     59     127    132    Any
        //  62     67     132    139    Any
        //  98     103    106    108    Any
        //  115    120    139    146    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 75, Size: 75
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
    
    public static String g(final Context context) {
        final JSONObject jsonObject = new JSONObject();
        try {
            final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
            int n;
            if (audioManager.getRingerMode() == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            final int streamVolume = audioManager.getStreamVolume(0);
            final int streamVolume2 = audioManager.getStreamVolume(1);
            final int streamVolume3 = audioManager.getStreamVolume(2);
            final int streamVolume4 = audioManager.getStreamVolume(3);
            final int streamVolume5 = audioManager.getStreamVolume(4);
            jsonObject.put("ringermode", (Object)String.valueOf(n));
            jsonObject.put("call", (Object)String.valueOf(streamVolume));
            jsonObject.put("system", (Object)String.valueOf(streamVolume2));
            jsonObject.put("ring", (Object)String.valueOf(streamVolume3));
            jsonObject.put("music", (Object)String.valueOf(streamVolume4));
            jsonObject.put("alarm", (Object)String.valueOf(streamVolume5));
            return jsonObject.toString();
        }
        finally {
            return jsonObject.toString();
        }
    }
    
    public static String h() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            final long n = statFs.getBlockCount() * (long)statFs.getBlockSize();
        }
        finally {
            n2 = 0L;
        }
        return String.valueOf(n2);
    }
    
    public static String h(final Context context) {
        while (true) {
            if (context == null) {
                break Label_0112;
            }
            try {
                final SensorManager sensorManager = (SensorManager)context.getSystemService("sensor");
                String e = null;
                Label_0114: {
                    if (sensorManager != null) {
                        final List sensorList = sensorManager.getSensorList(-1);
                        if (sensorList != null && sensorList.size() > 0) {
                            final StringBuilder sb = new StringBuilder();
                            for (final Sensor sensor : sensorList) {
                                sb.append(sensor.getName());
                                sb.append(sensor.getVersion());
                                sb.append(sensor.getVendor());
                            }
                            e = a.e(sb.toString());
                            break Label_0114;
                        }
                    }
                    e = null;
                }
                String s = e;
                if (e == null) {
                    s = "";
                }
                return s;
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public static String i() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs("/sdcard");
            final long n = statFs.getBlockSize() * (long)statFs.getBlockCount();
        }
        finally {
            n2 = 0L;
        }
        return String.valueOf(n2);
    }
    
    public static String i(final Context context) {
        final JSONArray jsonArray = new JSONArray();
        Label_0128: {
            if (context == null) {
                break Label_0128;
            }
            try {
                final SensorManager sensorManager = (SensorManager)context.getSystemService("sensor");
                if (sensorManager != null) {
                    final List sensorList = sensorManager.getSensorList(-1);
                    if (sensorList != null && sensorList.size() > 0) {
                        for (final Sensor sensor : sensorList) {
                            if (sensor != null) {
                                final JSONObject jsonObject = new JSONObject();
                                jsonObject.put("name", (Object)sensor.getName());
                                jsonObject.put("version", sensor.getVersion());
                                jsonObject.put("vendor", (Object)sensor.getVendor());
                                jsonArray.put((Object)jsonObject);
                            }
                        }
                    }
                }
                return jsonArray.toString();
            }
            finally {
                return jsonArray.toString();
            }
        }
    }
    
    public static String j() {
        final String s = "";
        String s3 = null;
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            final String s2 = (String)forName.getMethod("get", String.class, String.class).invoke(forName.newInstance(), new Object[] { "gsm.version.baseband", "no message" });
        }
        finally {
            s3 = "";
        }
        if (s3 == null) {
            s3 = s;
        }
        return s3;
    }
    
    public static String j(final Context context) {
        try {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            final StringBuilder sb = new StringBuilder();
            sb.append(Integer.toString(displayMetrics.widthPixels));
            sb.append("*");
            sb.append(Integer.toString(displayMetrics.heightPixels));
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String k() {
        final String s = "";
        String s2;
        try {
            Locale.getDefault().toString();
        }
        finally {
            s2 = "";
        }
        if (s2 == null) {
            s2 = s;
        }
        return s2;
    }
    
    public static String k(final Context context) {
        try {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            final StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String l() {
        final String s = "";
        String s2;
        try {
            TimeZone.getDefault().getDisplayName(false, 0);
        }
        finally {
            s2 = "";
        }
        if (s2 == null) {
            s2 = s;
        }
        return s2;
    }
    
    public static String l(final Context context) {
        try {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            final StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String m() {
        try {
            final long n = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            final StringBuilder sb = new StringBuilder();
            sb.append(n - n % 1000L);
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String m(final Context context) {
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager != null) {
                return String.valueOf(telephonyManager.getNetworkType());
            }
            return "";
        }
        finally {
            return "";
        }
    }
    
    public static String n() {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String n(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc_w           "keyguard"
        //     4: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //     7: checkcast       Landroid/app/KeyguardManager;
        //    10: invokevirtual   android/app/KeyguardManager.isKeyguardSecure:()Z
        //    13: istore          8
        //    15: lconst_0       
        //    16: lstore_2       
        //    17: iload           8
        //    19: ifne            26
        //    22: ldc_w           "0:0"
        //    25: areturn        
        //    26: iconst_0       
        //    27: istore_1       
        //    28: iload_1        
        //    29: iconst_5       
        //    30: if_icmpge       106
        //    33: iconst_5       
        //    34: anewarray       Ljava/lang/String;
        //    37: dup            
        //    38: iconst_0       
        //    39: ldc_w           "/data/system/password.key"
        //    42: aastore        
        //    43: dup            
        //    44: iconst_1       
        //    45: ldc_w           "/data/system/gesture.key"
        //    48: aastore        
        //    49: dup            
        //    50: iconst_2       
        //    51: ldc_w           "/data/system/gatekeeper.password.key"
        //    54: aastore        
        //    55: dup            
        //    56: iconst_3       
        //    57: ldc_w           "/data/system/gatekeeper.gesture.key"
        //    60: aastore        
        //    61: dup            
        //    62: iconst_4       
        //    63: ldc_w           "/data/system/gatekeeper.pattern.key"
        //    66: aastore        
        //    67: iload_1        
        //    68: aaload         
        //    69: astore_0       
        //    70: ldc2_w          -1
        //    73: lstore          6
        //    75: new             Ljava/io/File;
        //    78: astore          9
        //    80: aload           9
        //    82: aload_0        
        //    83: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    86: aload           9
        //    88: invokevirtual   java/io/File.lastModified:()J
        //    91: lstore          4
        //    93: lload           4
        //    95: lload_2        
        //    96: invokestatic    java/lang/Math.max:(JJ)J
        //    99: lstore_2       
        //   100: iinc            1, 1
        //   103: goto            28
        //   106: new             Ljava/lang/StringBuilder;
        //   109: astore_0       
        //   110: aload_0        
        //   111: ldc_w           "1:"
        //   114: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   117: aload_0        
        //   118: lload_2        
        //   119: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   122: pop            
        //   123: aload_0        
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: astore_0       
        //   128: aload_0        
        //   129: areturn        
        //   130: astore_0       
        //   131: ldc             ""
        //   133: areturn        
        //   134: astore_0       
        //   135: lload           6
        //   137: lstore          4
        //   139: goto            93
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      15     130    134    Any
        //  33     70     130    134    Any
        //  75     93     134    142    Any
        //  93     100    130    134    Any
        //  106    128    130    134    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0093:
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
    
    public static String o() {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("00:");
            for (int i = 0; i < 7; ++i) {
                String s;
                if (new File((new String[] { "/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd" })[i]).exists()) {
                    s = "1";
                }
                else {
                    s = "0";
                }
                sb.append(s);
            }
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String o(final Context context) {
        try {
            final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            final int intExtra = registerReceiver.getIntExtra("level", -1);
            final int intExtra2 = registerReceiver.getIntExtra("status", -1);
            final boolean b = intExtra2 == 2 || intExtra2 == 5;
            final StringBuilder sb = new StringBuilder();
            String s;
            if (b) {
                s = "1";
            }
            else {
                s = "0";
            }
            sb.append(s);
            sb.append(":");
            sb.append(intExtra);
            return sb.toString();
        }
        finally {
            return "";
        }
    }
    
    public static String p() {
        final StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; ++i) {
            final String className = (new String[] { "dalvik.system.Taint" })[0];
            try {
                Class.forName(className);
                sb.append("1");
            }
            finally {
                sb.append("0");
            }
        }
        return sb.toString();
    }
    
    public static String p(final Context context) {
        if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        final String s = null;
        try {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            String s2;
            if (activeNetworkInfo == null) {
                s2 = s;
            }
            else if (activeNetworkInfo.getType() == 1) {
                s2 = "WIFI";
            }
            else {
                s2 = s;
                if (activeNetworkInfo.getType() == 0) {
                    final int subtype = activeNetworkInfo.getSubtype();
                    if (subtype != 4 && subtype != 1 && subtype != 2 && subtype != 7 && subtype != 11) {
                        if (subtype != 3 && subtype != 5 && subtype != 6 && subtype != 8 && subtype != 9 && subtype != 10 && subtype != 12 && subtype != 14 && subtype != 15) {
                            if (subtype == 13) {
                                s2 = "4G";
                            }
                            else {
                                s2 = "UNKNOW";
                            }
                        }
                        else {
                            s2 = "3G";
                        }
                    }
                    else {
                        s2 = "2G";
                    }
                }
            }
            return s2;
        }
        finally {
            return s;
        }
    }
    
    public static String q() {
        final StringBuilder sb = new StringBuilder();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        ((Map)linkedHashMap).put((Object)"/system/build.prop", (Object)"ro.product.name=sdk");
        ((Map)linkedHashMap).put((Object)"/proc/tty/drivers", (Object)"goldfish");
        ((Map)linkedHashMap).put((Object)"/proc/cpuinfo", (Object)"goldfish");
        sb.append("00:");
    Label_0082:
        for (String s : ((Map)linkedHashMap).keySet()) {
            final LineNumberReader lineNumberReader = null;
            final char c = '0';
            LineNumberReader lineNumberReader2;
            try {
                lineNumberReader2 = new LineNumberReader((Reader)new InputStreamReader((InputStream)new FileInputStream(s)));
                try {
                    String line;
                    do {
                        line = lineNumberReader2.readLine();
                        final char c2 = c;
                        if (line != null) {
                            continue;
                        }
                        sb.append(c2);
                        final LineNumberReader lineNumberReader3 = lineNumberReader2;
                        lineNumberReader3.close();
                        continue Label_0082;
                    } while (!line.toLowerCase().contains((CharSequence)((Map)linkedHashMap).get((Object)s)));
                    final char c2 = '1';
                }
                finally {}
            }
            finally {
                lineNumberReader2 = lineNumberReader;
            }
            try {
                final LineNumberReader lineNumberReader3 = lineNumberReader2;
                lineNumberReader3.close();
                sb.append('0');
                iftrue(Label_0082:)(lineNumberReader2 == null);
                lineNumberReader2.close();
            }
            finally {}
        }
        return sb.toString();
    }
    
    public static String r() {
        final StringBuilder sb = new StringBuilder();
        sb.append("00:");
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        ((Map)linkedHashMap).put((Object)"BRAND", (Object)"generic");
        ((Map)linkedHashMap).put((Object)"BOARD", (Object)"unknown");
        ((Map)linkedHashMap).put((Object)"DEVICE", (Object)"generic");
        ((Map)linkedHashMap).put((Object)"HARDWARE", (Object)"goldfish");
        ((Map)linkedHashMap).put((Object)"PRODUCT", (Object)"sdk");
        ((Map)linkedHashMap).put((Object)"MODEL", (Object)"sdk");
        final Iterator iterator = ((Map)linkedHashMap).keySet().iterator();
    Label_0220_Outer:
        while (true) {
            Label_0230: {
                if (!iterator.hasNext()) {
                    break Label_0230;
                }
                final String name = (String)iterator.next();
                final char c = '0';
                while (true) {
                    try {
                        final Field field = Build.class.getField(name);
                        String lowerCase = null;
                        final String s = (String)field.get((Object)null);
                        final String s2 = (String)((Map)linkedHashMap).get((Object)name);
                        if (s != null) {
                            lowerCase = s.toLowerCase();
                        }
                        char c2 = c;
                        if (lowerCase != null) {
                            final boolean contains = lowerCase.contains((CharSequence)s2);
                            c2 = c;
                            if (contains) {
                                c2 = '1';
                            }
                        }
                        sb.append(c2);
                        continue Label_0220_Outer;
                        return sb.toString();
                    }
                    finally {
                        final char c2 = c;
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public static String s() {
        final StringBuilder sb = new StringBuilder();
        sb.append("00:");
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        ((Map)linkedHashMap).put((Object)"ro.hardware", (Object)"goldfish");
        ((Map)linkedHashMap).put((Object)"ro.kernel.qemu", (Object)"1");
        ((Map)linkedHashMap).put((Object)"ro.product.device", (Object)"generic");
        ((Map)linkedHashMap).put((Object)"ro.product.model", (Object)"sdk");
        ((Map)linkedHashMap).put((Object)"ro.product.brand", (Object)"generic");
        ((Map)linkedHashMap).put((Object)"ro.product.name", (Object)"sdk");
        ((Map)linkedHashMap).put((Object)"ro.build.fingerprint", (Object)"test-keys");
        ((Map)linkedHashMap).put((Object)"ro.product.manufacturer", (Object)"unknow");
        for (final String s : ((Map)linkedHashMap).keySet()) {
            final char c = '0';
            final String s2 = (String)((Map)linkedHashMap).get((Object)s);
            final String b = a.b(s, "");
            char c2 = c;
            if (b != null) {
                c2 = c;
                if (b.contains((CharSequence)s2)) {
                    c2 = '1';
                }
            }
            sb.append(c2);
        }
        return sb.toString();
    }
    
    public static String t() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: new             Ljava/io/FileReader;
        //     5: astore_0       
        //     6: aload_0        
        //     7: ldc_w           "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
        //    10: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    13: new             Ljava/io/BufferedReader;
        //    16: astore_2       
        //    17: aload_2        
        //    18: aload_0        
        //    19: sipush          8192
        //    22: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    25: aload_2        
        //    26: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    29: astore_1       
        //    30: aload_1        
        //    31: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    34: ifne            52
        //    37: aload_1        
        //    38: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    41: astore_1       
        //    42: aload_2        
        //    43: invokevirtual   java/io/BufferedReader.close:()V
        //    46: aload_0        
        //    47: invokevirtual   java/io/FileReader.close:()V
        //    50: aload_1        
        //    51: areturn        
        //    52: aload_2        
        //    53: invokevirtual   java/io/BufferedReader.close:()V
        //    56: goto            88
        //    59: astore_1       
        //    60: aload_2        
        //    61: astore_1       
        //    62: goto            72
        //    65: astore_2       
        //    66: goto            72
        //    69: astore_0       
        //    70: aconst_null    
        //    71: astore_0       
        //    72: aload_1        
        //    73: ifnull          84
        //    76: aload_1        
        //    77: invokevirtual   java/io/BufferedReader.close:()V
        //    80: goto            84
        //    83: astore_1       
        //    84: aload_0        
        //    85: ifnull          92
        //    88: aload_0        
        //    89: invokevirtual   java/io/FileReader.close:()V
        //    92: ldc             ""
        //    94: areturn        
        //    95: astore_2       
        //    96: goto            46
        //    99: astore_0       
        //   100: goto            50
        //   103: astore_1       
        //   104: goto            88
        //   107: astore_0       
        //   108: goto            92
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      13     69     72     Any
        //  13     25     65     69     Any
        //  25     42     59     65     Any
        //  42     46     95     99     Any
        //  46     50     99     103    Any
        //  52     56     103    107    Any
        //  76     80     83     84     Any
        //  88     92     107    111    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 60, Size: 60
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
    
    public static String u() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore_0       
        //     3: aconst_null    
        //     4: astore_2       
        //     5: new             Ljava/io/FileReader;
        //     8: astore_3       
        //     9: aload_3        
        //    10: ldc             "/proc/cpuinfo"
        //    12: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    15: new             Ljava/io/BufferedReader;
        //    18: astore_2       
        //    19: aload_2        
        //    20: aload_3        
        //    21: sipush          8192
        //    24: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    27: aload_2        
        //    28: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    31: astore          4
        //    33: aload_0        
        //    34: astore_1       
        //    35: aload           4
        //    37: ifnull          85
        //    40: aload           4
        //    42: invokestatic    com/alipay/sdk/m/z/a.a:(Ljava/lang/String;)Z
        //    45: ifne            27
        //    48: aload           4
        //    50: ldc             ":"
        //    52: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    55: astore_1       
        //    56: aload_1        
        //    57: ifnull          27
        //    60: aload_1        
        //    61: arraylength    
        //    62: iconst_1       
        //    63: if_icmple       27
        //    66: aload_1        
        //    67: iconst_0       
        //    68: aaload         
        //    69: ldc_w           "BogoMIPS"
        //    72: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    75: ifeq            27
        //    78: aload_1        
        //    79: iconst_1       
        //    80: aaload         
        //    81: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    84: astore_1       
        //    85: aload_3        
        //    86: invokevirtual   java/io/FileReader.close:()V
        //    89: aload_1        
        //    90: astore_0       
        //    91: goto            125
        //    94: astore_1       
        //    95: aconst_null    
        //    96: astore_1       
        //    97: aload_3        
        //    98: astore_2       
        //    99: goto            105
        //   102: astore_1       
        //   103: aconst_null    
        //   104: astore_1       
        //   105: aload_2        
        //   106: ifnull          117
        //   109: aload_2        
        //   110: invokevirtual   java/io/FileReader.close:()V
        //   113: goto            117
        //   116: astore_2       
        //   117: aload_0        
        //   118: astore_2       
        //   119: aload_1        
        //   120: ifnull          131
        //   123: aload_1        
        //   124: astore_2       
        //   125: aload_2        
        //   126: invokevirtual   java/io/BufferedReader.close:()V
        //   129: aload_0        
        //   130: astore_2       
        //   131: aload_2        
        //   132: areturn        
        //   133: astore_1       
        //   134: aload_2        
        //   135: astore_1       
        //   136: goto            97
        //   139: astore_0       
        //   140: aload_1        
        //   141: astore_0       
        //   142: goto            125
        //   145: astore_1       
        //   146: aload_0        
        //   147: astore_2       
        //   148: goto            131
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      15     102    105    Any
        //  15     27     94     97     Any
        //  27     33     133    139    Any
        //  40     56     133    139    Any
        //  60     85     133    139    Any
        //  85     89     139    145    Any
        //  109    113    116    117    Any
        //  125    129    145    151    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 90, Size: 90
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
    
    private String v() {
        String s = d.a("ip");
        if (s != null) {
            return s;
        }
        final boolean backgroundRunning = this.a.isBackgroundRunning();
        final String s2 = "";
        if (backgroundRunning) {
            return "";
        }
        String s3 = s;
        String s5 = null;
        try {
            final Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        Label_0041:
            while (true) {
                s3 = s;
                final String s4;
                if (!networkInterfaces.hasMoreElements() || (s4 = s) != null) {
                    break;
                }
                s3 = s;
                final Enumeration inetAddresses = ((NetworkInterface)networkInterfaces.nextElement()).getInetAddresses();
                InetAddress inetAddress;
                while (true) {
                    s3 = s;
                    if (!inetAddresses.hasMoreElements()) {
                        continue Label_0041;
                    }
                    s3 = s;
                    inetAddress = (InetAddress)inetAddresses.nextElement();
                    s3 = s;
                    if (inetAddress.isLoopbackAddress()) {
                        continue;
                    }
                    s3 = s;
                    if (inetAddress instanceof Inet4Address) {
                        break;
                    }
                }
                s3 = s;
                s = inetAddress.getHostAddress().toString();
            }
        }
        finally {
            s5 = s3;
        }
        if (s5 == null) {
            s5 = s2;
        }
        d.a("ip", s5);
        return s5;
    }
    
    public final String a() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles((FileFilter)new c(this)).length);
        }
        finally {
            return "1";
        }
    }
    
    public final String a(final Context context) {
        synchronized (this) {
            final String subscriberId = this.a.getSubscriberId();
            if (subscriberId != null) {
                return subscriberId;
            }
            final String a = d.a("imsi");
            if (a != null) {
                return a;
            }
            if (this.a.isBackgroundRunning()) {
                return "";
            }
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            String s;
            if ((s = a) == null) {
                s = "";
            }
            d.a("imsi", s);
            return s;
        }
    }
    
    public final String b(final Context context) {
        synchronized (this) {
            final String a = d.a("NetworkOperatorName");
            if (a != null) {
                return a;
            }
            String s = a;
            if (context != null) {
                try {
                    final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                    if (telephonyManager != null) {
                        telephonyManager.getNetworkOperatorName();
                    }
                }
                finally {
                    s = a;
                }
            }
            String s2 = null;
            Label_0074: {
                if (s != null) {
                    s2 = s;
                    if (!"null".equals((Object)s)) {
                        break Label_0074;
                    }
                }
                s2 = "";
            }
            d.a("NetworkOperatorName", s2);
            return s2;
        }
    }
    
    public final String c(final Context context) {
        synchronized (this) {
            final String a = d.a("SimSerial");
            if (a != null) {
                return a;
            }
            if (this.a.isBackgroundRunning()) {
                return "";
            }
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            d.a("SimSerial", a);
            return a;
        }
    }
    
    public final String d(final Context context) {
        synchronized (this) {
            final String androidId = this.a.getAndroidId();
            if (androidId != null) {
                return androidId;
            }
            final String a = d.a("ANDROIDID");
            if (a != null) {
                return a;
            }
            if (this.a.isBackgroundRunning()) {
                return "";
            }
            String s;
            try {
                Settings$Secure.getString(context.getContentResolver(), "android_id");
            }
            finally {
                s = a;
            }
            String s2 = s;
            if (s == null) {
                s2 = "";
            }
            d.a("ANDROIDID", s2);
            return s2;
        }
    }
    
    public final String e(final Context context) {
        while (true) {
            try {
                final String p = p(context);
                final String v = this.v();
                String string;
                if (com.alipay.sdk.m.z.a.b(p) && com.alipay.sdk.m.z.a.b(v)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(p);
                    sb.append(":");
                    sb.append(this.v());
                    string = sb.toString();
                }
                else {
                    string = "";
                }
                return string;
            }
            finally {
                continue;
            }
            break;
        }
    }
}
