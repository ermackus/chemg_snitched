package com.tencent.open.utils;

import java.security.MessageDigest;
import android.content.pm.Signature;
import android.content.Intent;
import java.lang.reflect.InvocationTargetException;
import android.os.Environment;
import android.os.Build$VERSION;
import android.webkit.WebSettings;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.Activity;
import java.io.IOException;
import com.tencent.open.log.SLog;
import java.io.OutputStream;
import java.io.InputStream;

public class i
{
    public static int a(final String s) {
        if ("shareToQQ".equals((Object)s)) {
            return 10103;
        }
        if ("shareToQzone".equals((Object)s)) {
            return 10104;
        }
        if ("addToQQFavorites".equals((Object)s)) {
            return 10105;
        }
        if ("sendToMyComputer".equals((Object)s)) {
            return 10106;
        }
        if ("shareToTroopBar".equals((Object)s)) {
            return 10107;
        }
        if ("action_login".equals((Object)s)) {
            return 11101;
        }
        if ("action_request".equals((Object)s)) {
            return 10100;
        }
        return -1;
    }
    
    public static int a(final String s, final String s2) {
        if (s == null && s2 == null) {
            return 0;
        }
        if (s != null && s2 == null) {
            return 1;
        }
        if (s == null && s2 != null) {
            return -1;
        }
        final String[] split = s.split("\\.");
        final String[] split2 = s2.split("\\.");
        int n = 0;
        try {
            while (n < split.length && n < split2.length) {
                final int int1 = Integer.parseInt(split[n]);
                final int int2 = Integer.parseInt(split2[n]);
                if (int1 < int2) {
                    return -1;
                }
                if (int1 > int2) {
                    return 1;
                }
                ++n;
            }
            if (split.length > n) {
                return 1;
            }
            if (split2.length > n) {
                return -1;
            }
            return 0;
        }
        catch (final NumberFormatException ex) {
            return s.compareTo(s2);
        }
    }
    
    private static long a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[8192];
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array, 0, 8192);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->copy, copyed size is: ");
        sb.append(n);
        SLog.i("openSDK_LOG.SystemUtils", sb.toString());
        return n;
    }
    
    public static String a(final int n) {
        if (n == 10103) {
            return "shareToQQ";
        }
        if (n == 10104) {
            return "shareToQzone";
        }
        if (n == 10105) {
            return "addToQQFavorites";
        }
        if (n == 10106) {
            return "sendToMyComputer";
        }
        if (n == 10107) {
            return "shareToTroopBar";
        }
        if (n == 11101) {
            return "action_login";
        }
        if (n == 10100) {
            return "action_request";
        }
        return null;
    }
    
    public static String a(final Activity activity) {
        final String packageName = activity.getApplicationContext().getPackageName();
        final PackageManager packageManager = activity.getPackageManager();
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 128);
            final StringBuilder sb = new StringBuilder();
            sb.append("apkPath=");
            sb.append(applicationInfo.sourceDir);
            SLog.i("openSDK_LOG.SystemUtils", sb.toString());
            return applicationInfo.sourceDir;
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.SystemUtils", "Exception", (Throwable)ex);
            return null;
        }
        catch (final PackageManager$NameNotFoundException ex2) {
            SLog.e("openSDK_LOG.SystemUtils", "NameNotFoundException", (Throwable)ex2);
            return null;
        }
    }
    
    public static String a(final Activity activity, final String s) {
        if (activity == null) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName activity==null !!!!!!");
            return "";
        }
        try {
            final byte[] a = e.a(s);
            if (a == null) {
                SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName shaBytes==null !!!!!!");
                return "";
            }
            final byte[] array = new byte[8];
            System.arraycopy((Object)a, 5, (Object)array, 0, 8);
            final byte[] array2 = new byte[16];
            System.arraycopy((Object)a, 8, (Object)array2, 0, 16);
            return e.a(activity.getPackageName(), e.a(array2), array);
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName", (Throwable)ex);
            return "";
        }
    }
    
    public static String a(final Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }
    
    public static String a(final Context context, final String s) {
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(s, 0).versionName;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static void a(final WebSettings webSettings) {
        try {
            webSettings.setSavePassword(false);
            webSettings.setAllowFileAccess(false);
            if (Build$VERSION.SDK_INT >= 16) {
                webSettings.setAllowFileAccessFromFileURLs(false);
            }
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.SystemUtils", "Exception", (Throwable)ex);
        }
    }
    
    private static boolean a() {
        boolean booleanValue = false;
        try {
            booleanValue = (boolean)Environment.class.getMethod("isExternalStorageLegacy", (Class<?>[])new Class[0]).invoke((Object)Environment.class, new Object[0]);
            return booleanValue;
        }
        catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            return booleanValue;
        }
    }
    
    public static boolean a(final Context context, final Intent intent) {
        boolean b2;
        final boolean b = b2 = false;
        if (context != null) {
            if (intent == null) {
                b2 = b;
            }
            else {
                b2 = b;
                if (context.getPackageManager().queryIntentActivities(intent, 0).size() != 0) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static boolean a(final Context context, final String s, final String s2) {
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            final Signature[] signatures = context.getPackageManager().getPackageInfo(s, 64).signatures;
            for (int length = signatures.length, i = 0; i < length; ++i) {
                if (k.g(signatures[i].toCharsString()).equals((Object)s2)) {
                    return true;
                }
            }
            return false;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean a(final String p0, final String p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore          4
        //     9: aload           4
        //    11: ldc_w           "-->extractSecureLib, libName: "
        //    14: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    17: pop            
        //    18: aload           4
        //    20: aload_0        
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: pop            
        //    25: ldc             "openSDK_LOG.SystemUtils"
        //    27: aload           4
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //    35: invokestatic    com/tencent/open/utils/f.a:()Landroid/content/Context;
        //    38: astore          10
        //    40: aload           10
        //    42: ifnonnull       55
        //    45: ldc             "openSDK_LOG.SystemUtils"
        //    47: ldc_w           "-->extractSecureLib, global context is null. "
        //    50: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //    53: iconst_0       
        //    54: ireturn        
        //    55: aload           10
        //    57: ldc_w           "secure_lib"
        //    60: iconst_0       
        //    61: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //    64: astore          9
        //    66: new             Ljava/io/File;
        //    69: dup            
        //    70: aload           10
        //    72: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    75: aload_1        
        //    76: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    79: astore          5
        //    81: aload           5
        //    83: invokevirtual   java/io/File.exists:()Z
        //    86: ifne            128
        //    89: aload           5
        //    91: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    94: astore          4
        //    96: aload           4
        //    98: ifnull          198
        //   101: aload           4
        //   103: invokevirtual   java/io/File.mkdirs:()Z
        //   106: ifeq            198
        //   109: aload           5
        //   111: invokevirtual   java/io/File.createNewFile:()Z
        //   114: pop            
        //   115: goto            198
        //   118: astore          4
        //   120: aload           4
        //   122: invokevirtual   java/io/IOException.printStackTrace:()V
        //   125: goto            198
        //   128: aload           9
        //   130: ldc_w           "version"
        //   133: iconst_0       
        //   134: invokeinterface android/content/SharedPreferences.getInt:(Ljava/lang/String;I)I
        //   139: istore_3       
        //   140: new             Ljava/lang/StringBuilder;
        //   143: dup            
        //   144: invokespecial   java/lang/StringBuilder.<init>:()V
        //   147: astore          4
        //   149: aload           4
        //   151: ldc_w           "-->extractSecureLib, libVersion: "
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: pop            
        //   158: aload           4
        //   160: iload_2        
        //   161: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   164: pop            
        //   165: aload           4
        //   167: ldc_w           " | oldVersion: "
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: pop            
        //   174: aload           4
        //   176: iload_3        
        //   177: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   180: pop            
        //   181: ldc             "openSDK_LOG.SystemUtils"
        //   183: aload           4
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   191: iload_2        
        //   192: iload_3        
        //   193: if_icmpne       198
        //   196: iconst_1       
        //   197: ireturn        
        //   198: aconst_null    
        //   199: astore          7
        //   201: aconst_null    
        //   202: astore          4
        //   204: aconst_null    
        //   205: astore          6
        //   207: aconst_null    
        //   208: astore          8
        //   210: aload           10
        //   212: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //   215: aload_0        
        //   216: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //   219: astore          5
        //   221: aload           8
        //   223: astore_0       
        //   224: aload           7
        //   226: astore          4
        //   228: aload           10
        //   230: aload_1        
        //   231: iconst_0       
        //   232: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //   235: astore_1       
        //   236: aload_1        
        //   237: astore_0       
        //   238: aload_1        
        //   239: astore          4
        //   241: aload           5
        //   243: aload_1        
        //   244: invokestatic    com/tencent/open/utils/i.a:(Ljava/io/InputStream;Ljava/io/OutputStream;)J
        //   247: pop2           
        //   248: aload_1        
        //   249: astore_0       
        //   250: aload_1        
        //   251: astore          4
        //   253: aload           9
        //   255: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   260: astore          6
        //   262: aload_1        
        //   263: astore_0       
        //   264: aload_1        
        //   265: astore          4
        //   267: aload           6
        //   269: ldc_w           "version"
        //   272: iload_2        
        //   273: invokeinterface android/content/SharedPreferences$Editor.putInt:(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;
        //   278: pop            
        //   279: aload_1        
        //   280: astore_0       
        //   281: aload_1        
        //   282: astore          4
        //   284: aload           6
        //   286: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   291: pop            
        //   292: aload           5
        //   294: ifnull          306
        //   297: aload           5
        //   299: invokevirtual   java/io/InputStream.close:()V
        //   302: goto            306
        //   305: astore_0       
        //   306: aload_1        
        //   307: ifnull          314
        //   310: aload_1        
        //   311: invokevirtual   java/io/OutputStream.close:()V
        //   314: iconst_1       
        //   315: ireturn        
        //   316: astore          4
        //   318: aload           5
        //   320: astore_1       
        //   321: goto            386
        //   324: astore          6
        //   326: aload           5
        //   328: astore_1       
        //   329: aload           4
        //   331: astore_0       
        //   332: goto            352
        //   335: astore          4
        //   337: aconst_null    
        //   338: astore_0       
        //   339: aload           6
        //   341: astore_1       
        //   342: goto            386
        //   345: astore          6
        //   347: aconst_null    
        //   348: astore_0       
        //   349: aload           4
        //   351: astore_1       
        //   352: ldc             "openSDK_LOG.SystemUtils"
        //   354: ldc_w           "-->extractSecureLib, when copy lib execption."
        //   357: aload           6
        //   359: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   362: aload_1        
        //   363: ifnull          374
        //   366: aload_1        
        //   367: invokevirtual   java/io/InputStream.close:()V
        //   370: goto            374
        //   373: astore_1       
        //   374: aload_0        
        //   375: ifnull          382
        //   378: aload_0        
        //   379: invokevirtual   java/io/OutputStream.close:()V
        //   382: iconst_0       
        //   383: ireturn        
        //   384: astore          4
        //   386: aload_1        
        //   387: ifnull          398
        //   390: aload_1        
        //   391: invokevirtual   java/io/InputStream.close:()V
        //   394: goto            398
        //   397: astore_1       
        //   398: aload_0        
        //   399: ifnull          406
        //   402: aload_0        
        //   403: invokevirtual   java/io/OutputStream.close:()V
        //   406: aload           4
        //   408: athrow         
        //   409: astore_0       
        //   410: goto            314
        //   413: astore_0       
        //   414: goto            382
        //   417: astore_0       
        //   418: goto            406
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  109    115    118    128    Ljava/io/IOException;
        //  210    221    345    352    Ljava/lang/Exception;
        //  210    221    335    345    Any
        //  228    236    324    335    Ljava/lang/Exception;
        //  228    236    316    324    Any
        //  241    248    324    335    Ljava/lang/Exception;
        //  241    248    316    324    Any
        //  253    262    324    335    Ljava/lang/Exception;
        //  253    262    316    324    Any
        //  267    279    324    335    Ljava/lang/Exception;
        //  267    279    316    324    Any
        //  284    292    324    335    Ljava/lang/Exception;
        //  284    292    316    324    Any
        //  297    302    305    306    Ljava/io/IOException;
        //  310    314    409    413    Ljava/io/IOException;
        //  352    362    384    386    Any
        //  366    370    373    374    Ljava/io/IOException;
        //  378    382    413    417    Ljava/io/IOException;
        //  390    394    397    398    Ljava/io/IOException;
        //  402    406    417    421    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 211, Size: 211
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
    
    public static String b(final Context context, final String s) {
        final String s2 = "";
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        String a = s2;
        String s3;
        try {
            final String packageName = context.getPackageName();
            a = s2;
            final Signature[] signatures = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            a = s2;
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            a = s2;
            instance.update(signatures[0].toByteArray());
            a = s2;
            final String a2 = k.a(instance.digest());
            a = s2;
            instance.reset();
            a = s2;
            a = s2;
            final StringBuilder sb = new StringBuilder();
            a = s2;
            sb.append("-->sign: ");
            a = s2;
            sb.append(a2);
            a = s2;
            SLog.v("openSDK_LOG.SystemUtils", sb.toString());
            a = s2;
            a = s2;
            final StringBuilder sb2 = new StringBuilder();
            a = s2;
            sb2.append(packageName);
            a = s2;
            sb2.append("_");
            a = s2;
            sb2.append(a2);
            a = s2;
            sb2.append("_");
            a = s2;
            sb2.append(s);
            a = s2;
            sb2.append("");
            a = s2;
            instance.update(k.j(sb2.toString()));
            a = s2;
            s3 = (a = k.a(instance.digest()));
            instance.reset();
            a = s3;
            a = s3;
            final StringBuilder sb3 = new StringBuilder();
            a = s3;
            sb3.append("-->signEncryped: ");
            a = s3;
            sb3.append(s3);
            a = s3;
            SLog.v("openSDK_LOG.SystemUtils", sb3.toString());
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            SLog.e("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", (Throwable)ex);
            s3 = a;
        }
        return s3;
    }
    
    public static boolean b(final Context context) {
        final boolean f = f(context, "com.tencent.mobileqq");
        final StringBuilder sb = new StringBuilder();
        sb.append("isQQInstalled ");
        sb.append(f);
        SLog.i("openSDK_LOG.SystemUtils", sb.toString());
        return f;
    }
    
    public static int c(final Context context, final String s) {
        return a(a(context, "com.tencent.mobileqq"), s);
    }
    
    public static boolean c(final Context context) {
        final boolean f = f(context, "com.tencent.mobileqq");
        final boolean f2 = f(context, "com.tencent.tim");
        final boolean f3 = f(context, "com.tencent.minihd.qq");
        final boolean f4 = f(context, "com.tencent.qqlite");
        final StringBuilder sb = new StringBuilder();
        sb.append("isQQBranchInstalled: qq=");
        sb.append(f);
        sb.append(", tim=");
        sb.append(f2);
        sb.append(", pad=");
        sb.append(f3);
        sb.append(", speed=");
        sb.append(f4);
        SLog.i("openSDK_LOG.SystemUtils", sb.toString());
        return f || f2 || f3 || f4;
    }
    
    public static int d(final Context context, final String s) {
        return a(a(context, "com.tencent.tim"), s);
    }
    
    public static boolean d(final Context context) {
        final boolean b = false;
        if (context == null) {
            return false;
        }
        boolean b2 = b;
        if (context.getApplicationInfo().targetSdkVersion >= 29) {
            b2 = b;
            if (Build$VERSION.SDK_INT >= 29) {
                b2 = b;
                if (!a()) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static int e(final Context context, final String s) {
        return a(a(context, "com.tencent.qqlite"), s);
    }
    
    private static boolean f(final Context context, final String s) {
        try {
            context.getPackageManager().getPackageInfo(s, 0);
            return true;
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.SystemUtils", "Exception", (Throwable)ex);
            return false;
        }
        catch (final PackageManager$NameNotFoundException ex2) {
            final StringBuilder sb = new StringBuilder();
            sb.append("PackageManager.NameNotFoundException ");
            sb.append(s);
            SLog.e("openSDK_LOG.SystemUtils", sb.toString(), (Throwable)ex2);
            return false;
        }
    }
}
