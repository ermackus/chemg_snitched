package com.tencent.bugly.crashreport.crash.jni;

import java.util.Iterator;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.util.HashMap;
import com.tencent.bugly.proguard.z;
import android.text.TextUtils;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import android.content.Context;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

public final class b
{
    private static List<File> a;
    
    static {
        b.a = (List<File>)new ArrayList();
    }
    
    public static CrashDetailBean a(final Context p0, final String p1, final NativeExceptionHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: ifnull          307
        //     7: aload_1        
        //     8: ifnull          307
        //    11: aload_2        
        //    12: ifnonnull       18
        //    15: goto            307
        //    18: new             Ljava/io/File;
        //    21: dup            
        //    22: aload_1        
        //    23: ldc             "rqd_record.eup"
        //    25: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    28: astore          5
        //    30: aload           5
        //    32: invokevirtual   java/io/File.exists:()Z
        //    35: ifeq            305
        //    38: aload           5
        //    40: invokevirtual   java/io/File.canRead:()Z
        //    43: ifne            49
        //    46: goto            305
        //    49: new             Ljava/io/BufferedInputStream;
        //    52: astore_3       
        //    53: new             Ljava/io/FileInputStream;
        //    56: astore_1       
        //    57: aload_1        
        //    58: aload           5
        //    60: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    63: aload_3        
        //    64: aload_1        
        //    65: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    68: aload_3        
        //    69: astore_1       
        //    70: aload_3        
        //    71: invokestatic    com/tencent/bugly/crashreport/crash/jni/b.a:(Ljava/io/BufferedInputStream;)Ljava/lang/String;
        //    74: astore          4
        //    76: aload           4
        //    78: ifnull          215
        //    81: aload_3        
        //    82: astore_1       
        //    83: aload           4
        //    85: ldc             "NATIVE_RQD_REPORT"
        //    87: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    90: ifne            96
        //    93: goto            215
        //    96: aload_3        
        //    97: astore_1       
        //    98: new             Ljava/util/HashMap;
        //   101: astore          6
        //   103: aload_3        
        //   104: astore_1       
        //   105: aload           6
        //   107: invokespecial   java/util/HashMap.<init>:()V
        //   110: aconst_null    
        //   111: astore          4
        //   113: aload_3        
        //   114: astore_1       
        //   115: aload_3        
        //   116: invokestatic    com/tencent/bugly/crashreport/crash/jni/b.a:(Ljava/io/BufferedInputStream;)Ljava/lang/String;
        //   119: astore          5
        //   121: aload           5
        //   123: ifnull          155
        //   126: aload           4
        //   128: ifnonnull       138
        //   131: aload           5
        //   133: astore          4
        //   135: goto            113
        //   138: aload_3        
        //   139: astore_1       
        //   140: aload           6
        //   142: aload           4
        //   144: aload           5
        //   146: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   151: pop            
        //   152: goto            110
        //   155: aload           4
        //   157: ifnull          191
        //   160: aload_3        
        //   161: astore_1       
        //   162: ldc             "record not pair! drop! %s"
        //   164: iconst_1       
        //   165: anewarray       Ljava/lang/Object;
        //   168: dup            
        //   169: iconst_0       
        //   170: aload           4
        //   172: aastore        
        //   173: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   176: pop            
        //   177: aload_3        
        //   178: invokevirtual   java/io/BufferedInputStream.close:()V
        //   181: goto            189
        //   184: astore_0       
        //   185: aload_0        
        //   186: invokevirtual   java/io/IOException.printStackTrace:()V
        //   189: aconst_null    
        //   190: areturn        
        //   191: aload_3        
        //   192: astore_1       
        //   193: aload_0        
        //   194: aload           6
        //   196: aload_2        
        //   197: invokestatic    com/tencent/bugly/crashreport/crash/jni/b.a:(Landroid/content/Context;Ljava/util/Map;Lcom/tencent/bugly/crashreport/crash/jni/NativeExceptionHandler;)Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;
        //   200: astore_0       
        //   201: aload_3        
        //   202: invokevirtual   java/io/BufferedInputStream.close:()V
        //   205: goto            213
        //   208: astore_1       
        //   209: aload_1        
        //   210: invokevirtual   java/io/IOException.printStackTrace:()V
        //   213: aload_0        
        //   214: areturn        
        //   215: aload_3        
        //   216: astore_1       
        //   217: ldc             "record read fail! %s"
        //   219: iconst_1       
        //   220: anewarray       Ljava/lang/Object;
        //   223: dup            
        //   224: iconst_0       
        //   225: aload           4
        //   227: aastore        
        //   228: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   231: pop            
        //   232: aload_3        
        //   233: invokevirtual   java/io/BufferedInputStream.close:()V
        //   236: goto            244
        //   239: astore_0       
        //   240: aload_0        
        //   241: invokevirtual   java/io/IOException.printStackTrace:()V
        //   244: aconst_null    
        //   245: areturn        
        //   246: astore_2       
        //   247: aload_3        
        //   248: astore_0       
        //   249: goto            262
        //   252: astore_0       
        //   253: aload           4
        //   255: astore_1       
        //   256: goto            287
        //   259: astore_2       
        //   260: aconst_null    
        //   261: astore_0       
        //   262: aload_0        
        //   263: astore_1       
        //   264: aload_2        
        //   265: invokevirtual   java/io/IOException.printStackTrace:()V
        //   268: aload_0        
        //   269: ifnull          284
        //   272: aload_0        
        //   273: invokevirtual   java/io/BufferedInputStream.close:()V
        //   276: goto            284
        //   279: astore_0       
        //   280: aload_0        
        //   281: invokevirtual   java/io/IOException.printStackTrace:()V
        //   284: aconst_null    
        //   285: areturn        
        //   286: astore_0       
        //   287: aload_1        
        //   288: ifnull          303
        //   291: aload_1        
        //   292: invokevirtual   java/io/BufferedInputStream.close:()V
        //   295: goto            303
        //   298: astore_1       
        //   299: aload_1        
        //   300: invokevirtual   java/io/IOException.printStackTrace:()V
        //   303: aload_0        
        //   304: athrow         
        //   305: aconst_null    
        //   306: areturn        
        //   307: ldc             "get eup record file args error"
        //   309: iconst_0       
        //   310: anewarray       Ljava/lang/Object;
        //   313: invokestatic    com/tencent/bugly/proguard/x.e:(Ljava/lang/String;[Ljava/lang/Object;)Z
        //   316: pop            
        //   317: aconst_null    
        //   318: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  49     68     259    262    Ljava/io/IOException;
        //  49     68     252    259    Any
        //  70     76     246    252    Ljava/io/IOException;
        //  70     76     286    287    Any
        //  83     93     246    252    Ljava/io/IOException;
        //  83     93     286    287    Any
        //  98     103    246    252    Ljava/io/IOException;
        //  98     103    286    287    Any
        //  105    110    246    252    Ljava/io/IOException;
        //  105    110    286    287    Any
        //  115    121    246    252    Ljava/io/IOException;
        //  115    121    286    287    Any
        //  140    152    246    252    Ljava/io/IOException;
        //  140    152    286    287    Any
        //  162    177    246    252    Ljava/io/IOException;
        //  162    177    286    287    Any
        //  177    181    184    189    Ljava/io/IOException;
        //  193    201    246    252    Ljava/io/IOException;
        //  193    201    286    287    Any
        //  201    205    208    213    Ljava/io/IOException;
        //  217    232    246    252    Ljava/io/IOException;
        //  217    232    286    287    Any
        //  232    236    239    244    Ljava/io/IOException;
        //  264    268    286    287    Any
        //  272    276    279    284    Ljava/io/IOException;
        //  291    295    298    303    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0096:
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
    
    private static CrashDetailBean a(Context packageCrashDatas, final Map<String, String> map, final NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (com.tencent.bugly.crashreport.common.info.a.a(packageCrashDatas) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        final String s = (String)map.get((Object)"intStateStr");
        if (s != null) {
            if (s.trim().length() > 0) {
                final Map<String, Integer> d = d(s);
                if (d == null) {
                    x.e("parse intSateMap fail", map.size());
                    return null;
                }
                try {
                    ((Integer)d.get((Object)"sino")).intValue();
                    ((Integer)d.get((Object)"sud")).intValue();
                    final String s2 = (String)map.get((Object)"soVersion");
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        x.e("error format at version", new Object[0]);
                        return null;
                    }
                    String s3 = (String)map.get((Object)"errorAddr");
                    final String s4 = "unknown";
                    if (s3 == null) {
                        s3 = "unknown";
                    }
                    String s5;
                    if ((s5 = (String)map.get((Object)"codeMsg")) == null) {
                        s5 = "unknown";
                    }
                    String s6 = (String)map.get((Object)"tombPath");
                    if (s6 == null) {
                        s6 = "unknown";
                    }
                    String s7;
                    if ((s7 = (String)map.get((Object)"signalName")) == null) {
                        s7 = "unknown";
                    }
                    map.get((Object)"errnoMsg");
                    String s8;
                    if ((s8 = (String)map.get((Object)"stack")) == null) {
                        s8 = "unknown";
                    }
                    final String s9 = (String)map.get((Object)"jstack");
                    String string = s8;
                    if (s9 != null) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(s8);
                        sb.append("java:\n");
                        sb.append(s9);
                        string = sb.toString();
                    }
                    final Integer n = (Integer)d.get((Object)"sico");
                    String string2;
                    String s10;
                    if (n != null && n > 0) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(s7);
                        sb2.append("(");
                        sb2.append(s5);
                        sb2.append(")");
                        string2 = sb2.toString();
                        s10 = "KERNEL";
                    }
                    else {
                        string2 = s7;
                        s10 = s5;
                    }
                    final String s11 = (String)map.get((Object)"nativeLog");
                    byte[] a;
                    if (s11 != null && !s11.isEmpty()) {
                        a = z.a(null, s11, "BuglyNativeLog.txt");
                    }
                    else {
                        a = null;
                    }
                    String s12;
                    if ((s12 = (String)map.get((Object)"sendingProcess")) == null) {
                        s12 = "unknown";
                    }
                    final Integer n2 = (Integer)d.get((Object)"spd");
                    String string3 = s12;
                    if (n2 != null) {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(s12);
                        sb3.append("(");
                        sb3.append((Object)n2);
                        sb3.append(")");
                        string3 = sb3.toString();
                    }
                    String s13;
                    if ((s13 = (String)map.get((Object)"threadName")) == null) {
                        s13 = "unknown";
                    }
                    final Integer n3 = (Integer)d.get((Object)"et");
                    String string4 = s13;
                    if (n3 != null) {
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append(s13);
                        sb4.append("(");
                        sb4.append((Object)n3);
                        sb4.append(")");
                        string4 = sb4.toString();
                    }
                    String s14 = (String)map.get((Object)"processName");
                    if (s14 == null) {
                        s14 = s4;
                    }
                    final Integer n4 = (Integer)d.get((Object)"ep");
                    String string5 = s14;
                    if (n4 != null) {
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append(s14);
                        sb5.append("(");
                        sb5.append((Object)n4);
                        sb5.append(")");
                        string5 = sb5.toString();
                    }
                    final String s15 = (String)map.get((Object)"key-value");
                    Object o;
                    if (s15 != null) {
                        final HashMap hashMap = new HashMap();
                        final String[] split = s15.split("\n");
                        for (int length = split.length, i = 0; i < length; ++i) {
                            final String[] split2 = split[i].split("=");
                            if (split2.length == 2) {
                                ((Map)hashMap).put((Object)split2[0], (Object)split2[1]);
                            }
                        }
                        o = hashMap;
                    }
                    else {
                        o = null;
                    }
                    packageCrashDatas = (Context)nativeExceptionHandler.packageCrashDatas(string5, string4, (int)d.get((Object)"etms") / 1000L + (int)d.get((Object)"ets") * 1000L, string2, s3, a(string), s10, string3, s6, (String)map.get((Object)"sysLogPath"), (String)map.get((Object)"jniLogPath"), s2, a, (Map<String, String>)o, false, false);
                    if (packageCrashDatas != null) {
                        final String m = (String)map.get((Object)"userId");
                        if (m != null) {
                            x.c("[Native record info] userId: %s", m);
                            ((CrashDetailBean)packageCrashDatas).m = m;
                        }
                        final String w = (String)map.get((Object)"sysLog");
                        if (w != null) {
                            ((CrashDetailBean)packageCrashDatas).w = w;
                        }
                        final String f = (String)map.get((Object)"appVersion");
                        if (f != null) {
                            x.c("[Native record info] appVersion: %s", f);
                            ((CrashDetailBean)packageCrashDatas).f = f;
                        }
                        final String s16 = (String)map.get((Object)"isAppForeground");
                        if (s16 != null) {
                            x.c("[Native record info] isAppForeground: %s", s16);
                            ((CrashDetailBean)packageCrashDatas).N = s16.equalsIgnoreCase("true");
                        }
                        final String s17 = (String)map.get((Object)"launchTime");
                        if (s17 != null) {
                            x.c("[Native record info] launchTime: %s", s17);
                            try {
                                ((CrashDetailBean)packageCrashDatas).M = Long.parseLong(s17);
                            }
                            catch (final NumberFormatException ex) {
                                if (!x.a((Throwable)ex)) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        ((CrashDetailBean)packageCrashDatas).z = null;
                        ((CrashDetailBean)packageCrashDatas).k = true;
                    }
                    return (CrashDetailBean)packageCrashDatas;
                }
                finally {
                    x.e("error format", new Object[0]);
                    final Throwable t;
                    t.printStackTrace();
                    return null;
                }
            }
        }
        x.e("no intStateStr", new Object[0]);
        return null;
    }
    
    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        Label_0089: {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                try {
                    while (true) {
                        final int read = bufferedInputStream.read();
                        if (read == -1) {
                            byteArrayOutputStream.close();
                            break Label_0089;
                        }
                        if (read == 0) {
                            final String s = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                            byteArrayOutputStream.close();
                            return s;
                        }
                        byteArrayOutputStream.write(read);
                    }
                }
                finally {}
            }
            finally {
                bufferedInputStream = null;
            }
            try {
                final Throwable t;
                x.a(t);
                if (bufferedInputStream != null) {
                    ((ByteArrayOutputStream)bufferedInputStream).close();
                }
                return null;
            }
            finally {
                if (bufferedInputStream != null) {
                    ((ByteArrayOutputStream)bufferedInputStream).close();
                }
            }
        }
    }
    
    protected static String a(final String s) {
        if (s == null) {
            return "";
        }
        final String[] split = s.split("\n");
        String string = s;
        if (split != null) {
            if (split.length == 0) {
                string = s;
            }
            else {
                final StringBuilder sb = new StringBuilder();
                for (final String s2 : split) {
                    if (!s2.contains((CharSequence)"java.lang.Thread.getStackTrace(")) {
                        sb.append(s2);
                        sb.append("\n");
                    }
                }
                string = sb.toString();
            }
        }
        return string;
    }
    
    public static String a(String o, final int n, String o2, final boolean b) {
        StringBuilder sb = null;
        if (o != null) {
            if (n > 0) {
                final File file = new File((String)o);
                if (file.exists()) {
                    if (file.canRead()) {
                        x.a("Read system log from native record file(length: %s bytes): %s", file.length(), file.getAbsolutePath());
                        b.a.add((Object)file);
                        x.c("Add this record file to list for cleaning lastly.", new Object[0]);
                        if (o2 == null) {
                            o = z.a(new File((String)o), n, b);
                            return (String)o;
                        }
                        final StringBuilder sb2 = new StringBuilder();
                        try {
                            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file), "utf-8"));
                            try {
                                while (true) {
                                    o = bufferedReader.readLine();
                                    if (o == null) {
                                        break;
                                    }
                                    sb = new StringBuilder();
                                    sb.append((String)o2);
                                    sb.append("[ ]*:");
                                    if (Pattern.compile(sb.toString()).matcher((CharSequence)o).find()) {
                                        sb2.append((String)o);
                                        sb2.append("\n");
                                    }
                                    if (n <= 0 || sb2.length() <= n) {
                                        continue;
                                    }
                                    if (b) {
                                        sb2.delete(n, sb2.length());
                                        break;
                                    }
                                    sb2.delete(0, sb2.length() - n);
                                }
                                o2 = (o = sb2.toString());
                                try {
                                    bufferedReader.close();
                                    return (String)o;
                                }
                                catch (final Exception ex) {
                                    x.a((Throwable)ex);
                                    return (String)o;
                                }
                            }
                            finally {}
                        }
                        finally {
                            o2 = sb;
                        }
                        try {
                            x.a((Throwable)o);
                            final StringBuilder sb3 = new StringBuilder("\n[error:");
                            sb3.append(((Throwable)o).toString());
                            sb3.append("]");
                            sb2.append(sb3.toString());
                            final String s = (String)(o = sb2.toString());
                            if (o2 != null) {
                                o = s;
                                ((BufferedReader)o2).close();
                                o = s;
                            }
                            return (String)o;
                        }
                        finally {
                            if (o2 != null) {
                                try {
                                    ((BufferedReader)o2).close();
                                }
                                catch (final Exception ex2) {
                                    x.a((Throwable)ex2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static String a(String c, final String s) {
        if (c != null && s != null) {
            final StringBuilder sb = new StringBuilder();
            final String b = b(c, s);
            if (b != null && !b.isEmpty()) {
                sb.append("Register infos:\n");
                sb.append(b);
            }
            c = c(c, s);
            if (c != null && !c.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append("System SO infos:\n");
                sb.append(c);
            }
            return sb.toString();
        }
        return null;
    }
    
    public static void a(final boolean b, final String s) {
        if (s != null) {
            b.a.add((Object)new File(s, "rqd_record.eup"));
            b.a.add((Object)new File(s, "reg_record.txt"));
            b.a.add((Object)new File(s, "map_record.txt"));
            b.a.add((Object)new File(s, "backup_record.txt"));
            if (b) {
                c(s);
            }
        }
        final List<File> a = b.a;
        if (a != null && a.size() > 0) {
            for (final File file : b.a) {
                if (file.exists() && file.canWrite()) {
                    file.delete();
                    x.c("Delete record file %s", file.getAbsoluteFile());
                }
            }
        }
    }
    
    public static String b(final String s) {
        if (s == null) {
            return null;
        }
        final File file = new File(s, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }
    
    private static String b(String a, String s) {
        a = (Exception)z.a((String)a, "reg_record.txt");
        if (a == null) {
            return null;
        }
        try {
            final StringBuilder sb = new StringBuilder();
            final String line = ((BufferedReader)a).readLine();
            if (line != null && line.startsWith(s)) {
                int n = 18;
                int n2 = 0;
                int length = 0;
                while (true) {
                    s = ((BufferedReader)a).readLine();
                    if (s == null) {
                        break;
                    }
                    if (n2 % 4 == 0) {
                        if (n2 > 0) {
                            sb.append("\n");
                        }
                        sb.append("  ");
                    }
                    else {
                        if (s.length() > 16) {
                            n = 28;
                        }
                        sb.append("                ".substring(0, n - length));
                    }
                    length = s.length();
                    sb.append(s);
                    ++n2;
                }
                sb.append("\n");
                s = sb.toString();
                if (a != null) {
                    try {
                        ((BufferedReader)a).close();
                    }
                    catch (final Exception a) {
                        x.a((Throwable)a);
                    }
                }
                return s;
            }
            if (a != null) {
                try {
                    ((BufferedReader)a).close();
                }
                catch (final Exception a) {
                    x.a((Throwable)a);
                }
            }
            return null;
        }
        finally {
            try {
                final Throwable t;
                x.a(t);
                return null;
            }
            finally {
                if (a != null) {
                    try {
                        ((BufferedReader)a).close();
                    }
                    catch (final Exception ex) {
                        x.a((Throwable)ex);
                    }
                }
            }
        }
    }
    
    private static String c(String a, String s) {
        a = (Exception)z.a((String)a, "map_record.txt");
        if (a == null) {
            return null;
        }
        try {
            final StringBuilder sb = new StringBuilder();
            final String line = ((BufferedReader)a).readLine();
            if (line != null && line.startsWith(s)) {
                while (true) {
                    s = ((BufferedReader)a).readLine();
                    if (s == null) {
                        break;
                    }
                    sb.append("  ");
                    sb.append(s);
                    sb.append("\n");
                }
                s = sb.toString();
                if (a != null) {
                    try {
                        ((BufferedReader)a).close();
                    }
                    catch (final Exception a) {
                        x.a((Throwable)a);
                    }
                }
                return s;
            }
            if (a != null) {
                try {
                    ((BufferedReader)a).close();
                }
                catch (final Exception a) {
                    x.a((Throwable)a);
                }
            }
            return null;
        }
        finally {
            try {
                final Throwable t;
                x.a(t);
                return null;
            }
            finally {
                if (a != null) {
                    try {
                        ((BufferedReader)a).close();
                    }
                    catch (final Exception ex) {
                        x.a((Throwable)ex);
                    }
                }
            }
        }
    }
    
    public static void c(final String s) {
        if (s == null) {
            return;
        }
        try {
            final File file = new File(s);
            if (file.canRead() && file.isDirectory()) {
                final File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (final File file2 : listFiles) {
                        if (file2.canRead() && file2.canWrite() && file2.length() == 0L) {
                            file2.delete();
                            x.c("Delete empty record file %s", file2.getAbsoluteFile());
                        }
                    }
                }
            }
        }
        finally {
            final Throwable t;
            x.a(t);
        }
    }
    
    private static Map<String, Integer> d(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final HashMap hashMap = new HashMap();
            for (final String s2 : s.split(",")) {
                final String[] split2 = s2.split(":");
                if (split2.length != 2) {
                    x.e("error format at %s", s2);
                    return null;
                }
                ((Map)hashMap).put((Object)split2[0], (Object)Integer.parseInt(split2[1]));
            }
            return (Map<String, Integer>)hashMap;
        }
        catch (final Exception ex) {
            x.e("error format intStateStr %s", s);
            ex.printStackTrace();
            return null;
        }
    }
}
