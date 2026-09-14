package com.alipay.sdk.m.u;

import java.util.regex.Matcher;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.os.Build$VERSION;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.os.Build;
import android.provider.Settings$Secure;
import android.app.Application;
import android.net.Uri;
import java.net.URLDecoder;
import com.alipay.sdk.m.j.c;
import java.net.URL;
import android.content.pm.Signature;
import android.os.ConditionVariable;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import com.alipay.sdk.m.h.f;
import com.alipay.sdk.m.h.d;
import java.util.Random;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.alipay.sdk.m.m.a$b;
import java.util.List;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.m.s.a;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageInfo;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.s.b;

public class n
{
    public static final String a = "com.alipay.android.app";
    public static final String b = "com.eg.android.AlipayGphone";
    public static final String c = "hk.alipay.wallet";
    public static final String d = "hk.alipay.walletRC";
    public static final String e = "com.eg.android.AlipayGphoneRC";
    public static final int f = 99;
    public static final String[] g;
    public static final int h = 125;
    public static final int i = 460;
    public static final char[] j;
    
    static {
        g = new String[] { "10.1.5.1013151", "10.1.5.1013148" };
        j = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', '/' };
    }
    
    public static int a() {
        final String c = com.alipay.sdk.m.s.b.d().c();
        int n2;
        if (!TextUtils.isEmpty((CharSequence)c)) {
            String s2;
            final String s = s2 = c.replaceAll("=", "");
            if (s.length() >= 5) {
                s2 = s.substring(0, 5);
            }
            final int n = (int)(a(s2) % 10000L);
            if ((n2 = n) < 0) {
                n2 = n * -1;
            }
        }
        else {
            n2 = -1;
        }
        return n2;
    }
    
    public static long a(final String s) {
        return a(s, 6);
    }
    
    public static long a(final String s, int n) {
        final int n2 = (int)Math.pow(2.0, (double)n);
        final int length = s.length();
        long n3 = 0L;
        int i = 0;
        n = length;
        while (i < length) {
            final int n4 = i + 1;
            n3 += Integer.parseInt(String.valueOf(d(s.substring(i, n4)))) * (long)Math.pow((double)n2, (double)(n - 1));
            --n;
            i = n4;
        }
        return n3;
    }
    
    public static ActivityInfo a(final Context context) {
        try {
            if (context instanceof Activity) {
                final Activity activity = (Activity)context;
                for (final ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                    if (TextUtils.equals((CharSequence)activityInfo.name, (CharSequence)activity.getClass().getName())) {
                        return activityInfo;
                    }
                }
            }
            return null;
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
            return null;
        }
    }
    
    public static PackageInfo a(final Context context, final String s) throws PackageManager$NameNotFoundException {
        return context.getPackageManager().getPackageInfo(s, 192);
    }
    
    public static c a(final PackageInfo packageInfo, final int n, final String s) {
        if (packageInfo == null) {
            return null;
        }
        return new c(packageInfo, n, s);
    }
    
    public static c a(final a a, final Context context, final String s, final int n, final String s2) {
        String s3 = s;
        if (EnvUtils.isSandBox()) {
            if ("com.eg.android.AlipayGphone".equals((Object)s)) {
                s3 = "com.eg.android.AlipayGphoneRC";
            }
            else {
                s3 = s;
                if ("hk.alipay.wallet".equals((Object)s)) {
                    s3 = "hk.alipay.walletRC";
                }
            }
        }
        PackageInfo packageInfo = null;
        try {
            a(context, s3);
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.b(a, "auth", "GetPackageInfoEx", t.getMessage());
            packageInfo = null;
        }
        if (!a(a, packageInfo)) {
            return null;
        }
        return a(packageInfo, n, s2);
    }
    
    public static c a(final a a, final Context context, final List<a$b> list) {
        if (list == null) {
            return null;
        }
        for (final a$b a$b : list) {
            if (a$b == null) {
                continue;
            }
            final c a2 = a(a, context, a$b.a, a$b.b, a$b.c);
            if (a2 != null && !a2.a(a) && !a2.a()) {
                return a2;
            }
        }
        return null;
    }
    
    public static <T> T a(final WeakReference<T> weakReference) {
        Object value;
        if (weakReference == null) {
            value = null;
        }
        else {
            value = weakReference.get();
        }
        return (T)value;
    }
    
    public static String a(final int n) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            final int nextInt = random.nextInt(3);
            if (nextInt != 0) {
                if (nextInt != 1) {
                    if (nextInt == 2) {
                        sb.append(String.valueOf(new Random().nextInt(10)));
                    }
                }
                else {
                    sb.append(String.valueOf((char)Math.round(Math.random() * 25.0 + 97.0)));
                }
            }
            else {
                sb.append(String.valueOf((char)Math.round(Math.random() * 25.0 + 65.0)));
            }
        }
        return sb.toString();
    }
    
    public static String a(final a a) {
        return c(a, "ro.build.fingerprint");
    }
    
    public static String a(final a a, final Context context) {
        try {
            final String a2 = com.alipay.sdk.m.u.j.a(a, context, "alipay_cashier_ap_fi", "");
            if (!TextUtils.isEmpty((CharSequence)a2)) {
                return a2;
            }
            try {
                com.alipay.sdk.m.u.j.b(a, context, "alipay_cashier_ap_fi", com.alipay.sdk.m.h.a.a("FU", System.currentTimeMillis(), new d(), (short)0, new f()).a());
                final String a3 = com.alipay.sdk.m.u.j.a(a, context, "alipay_cashier_ap_fi", "");
                if (!TextUtils.isEmpty((CharSequence)a3)) {
                    return a3;
                }
                com.alipay.sdk.m.k.a.b(a, "biz", "e_regen_empty", "");
                return "";
            }
            catch (final Exception ex) {
                com.alipay.sdk.m.k.a.b(a, "biz", "e_gen", ex.getClass().getSimpleName());
                return "";
            }
        }
        catch (final Exception ex2) {
            com.alipay.sdk.m.k.a.a(a, "biz", "e_gen_err", (Throwable)ex2);
            return "";
        }
    }
    
    public static String a(a versionName, final Context context, final String s) {
        String s2 = null;
        try {
            versionName = (a)context.getPackageManager().getPackageInfo(s, 128).versionName;
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(versionName, "biz", "GetPackageInfoEx", t);
            s2 = "";
        }
        return s2;
    }
    
    public static String a(final a a, final byte[] array) {
        try {
            final PublicKey publicKey = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate((InputStream)new ByteArrayInputStream(array))).getPublicKey();
            if (publicKey instanceof RSAPublicKey) {
                final BigInteger modulus = ((RSAPublicKey)publicKey).getModulus();
                if (modulus != null) {
                    return modulus.toString(16);
                }
            }
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.k.a.a(a, "auth", "GetPublicKeyFromSignEx", (Throwable)ex);
        }
        return null;
    }
    
    public static String a(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(s2);
        return sb.toString();
    }
    
    public static String a(String substring, final String s, final String s2) {
        try {
            final int n = s2.indexOf(substring) + substring.length();
            if (n <= substring.length()) {}
            int index = 0;
            if (!TextUtils.isEmpty((CharSequence)s)) {
                index = s2.indexOf(s, n);
            }
            if (index < 1) {
                return s2.substring(n);
            }
            substring = s2.substring(n, index);
            return substring;
        }
        finally {
            return "";
        }
    }
    
    public static String a(String a, final boolean b) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(a.getBytes());
            final byte[] digest = instance.digest();
            if (b && digest.length > 16) {
                final byte[] array = new byte[16];
                System.arraycopy((Object)digest, 0, (Object)array, 0, 16);
                return a(array);
            }
            a = a(digest);
            return a;
        }
        catch (final NoSuchAlgorithmException ex) {
            return "";
        }
    }
    
    public static String a(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (final byte b : array) {
            sb.append(Character.forDigit((b & 0xF0) >> 4, 16));
            sb.append(Character.forDigit(b & 0xF, 16));
        }
        return sb.toString();
    }
    
    public static Map<String, String> a(final JSONObject jsonObject) {
        final HashMap hashMap = new HashMap();
        if (jsonObject == null) {
            return (Map<String, String>)hashMap;
        }
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = (String)keys.next();
            try {
                ((Map)hashMap).put((Object)s, (Object)jsonObject.optString(s));
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.u.e.a(t);
            }
        }
        return (Map<String, String>)hashMap;
    }
    
    public static JSONObject a(Intent extras) {
        final JSONObject jsonObject = new JSONObject();
        if (extras == null) {
            return jsonObject;
        }
        extras = (Intent)extras.getExtras();
        if (extras != null) {
            for (final String s : ((Bundle)extras).keySet()) {
                try {
                    jsonObject.put(s, (Object)String.valueOf(((Bundle)extras).get(s)));
                }
                finally {}
            }
        }
        return jsonObject;
    }
    
    public static void a(final String s, final String s2, final Context context, final a a) {
        if (context == null || TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            return;
        }
        if (b(a)) {
            return;
        }
        if (!com.alipay.sdk.m.m.a.z().s()) {
            return;
        }
        try {
            final Intent intent = new Intent("android.app.intent.action.APP_EXCEPTION_OCCUR");
            intent.putExtra("bizType", s);
            intent.putExtra("exName", s2);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("|");
            sb.append(s2);
            com.alipay.sdk.m.k.a.a(a, "biz", "AppNotify", sb.toString());
        }
        catch (final Exception ex) {}
    }
    
    public static boolean a(final long n, final Runnable runnable, final String name) {
        if (runnable == null) {
            return false;
        }
        final ConditionVariable conditionVariable = new ConditionVariable();
        final Thread thread = new Thread((Runnable)new Runnable(runnable, conditionVariable) {
            public final Runnable a;
            public final ConditionVariable b;
            
            public void run() {
                try {
                    this.a.run();
                }
                finally {
                    this.b.open();
                }
            }
        });
        if (!TextUtils.isEmpty((CharSequence)name)) {
            thread.setName(name);
        }
        thread.start();
        final boolean b = true;
        Label_0070: {
            if (n > 0L) {
                break Label_0070;
            }
            try {
                conditionVariable.block();
                return b;
                block = conditionVariable.block(n);
                return block;
            }
            finally {
                return b;
            }
        }
    }
    
    public static boolean a(final PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            final String versionName = packageInfo.versionName;
            if (!TextUtils.equals((CharSequence)versionName, (CharSequence)n.g[0])) {
                if (TextUtils.equals((CharSequence)versionName, (CharSequence)n.g[1])) {}
            }
            return true;
        }
        finally {
            return false;
        }
    }
    
    public static boolean a(final a p0, final Context p1, final List<a$b> p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //     6: astore          6
        //     8: aload           6
        //    10: invokeinterface java/util/Iterator.hasNext:()Z
        //    15: ifeq            169
        //    18: aload           6
        //    20: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    25: checkcast       Lcom/alipay/sdk/m/m/a$b;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnonnull       36
        //    33: goto            8
        //    36: aload_2        
        //    37: getfield        com/alipay/sdk/m/m/a$b.a:Ljava/lang/String;
        //    40: astore          5
        //    42: aload           5
        //    44: astore_2       
        //    45: invokestatic    com/alipay/sdk/app/EnvUtils.isSandBox:()Z
        //    48: ifeq            91
        //    51: ldc             "com.eg.android.AlipayGphone"
        //    53: aload           5
        //    55: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    58: istore          4
        //    60: iload           4
        //    62: ifeq            71
        //    65: ldc             "com.eg.android.AlipayGphoneRC"
        //    67: astore_2       
        //    68: goto            91
        //    71: ldc             "hk.alipay.wallet"
        //    73: aload           5
        //    75: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    78: istore          4
        //    80: aload           5
        //    82: astore_2       
        //    83: iload           4
        //    85: ifeq            91
        //    88: ldc             "hk.alipay.walletRC"
        //    90: astore_2       
        //    91: aload_1        
        //    92: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    95: astore          5
        //    97: aload           5
        //    99: aload_2        
        //   100: sipush          128
        //   103: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   106: astore          5
        //   108: aload           5
        //   110: ifnull          8
        //   113: iload_3        
        //   114: ifeq            167
        //   117: new             Ljava/lang/StringBuilder;
        //   120: astore_2       
        //   121: aload_2        
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: aload_2        
        //   126: aload           5
        //   128: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   131: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   134: pop            
        //   135: aload_2        
        //   136: ldc_w           "|"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop            
        //   143: aload_2        
        //   144: aload           5
        //   146: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: pop            
        //   153: aload_0        
        //   154: ldc_w           "biz"
        //   157: ldc_w           "PgWltVer"
        //   160: aload_2        
        //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   164: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   167: iconst_1       
        //   168: ireturn        
        //   169: iconst_0       
        //   170: ireturn        
        //   171: astore_1       
        //   172: aload_0        
        //   173: ldc_w           "biz"
        //   176: ldc_w           "CheckLaunchAppExistEx"
        //   179: aload_1        
        //   180: invokestatic    com/alipay/sdk/m/k/a.a:(Lcom/alipay/sdk/m/s/a;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   183: iconst_0       
        //   184: ireturn        
        //   185: astore_2       
        //   186: goto            8
        //    Signature:
        //  (Lcom/alipay/sdk/m/s/a;Landroid/content/Context;Ljava/util/List<Lcom/alipay/sdk/m/m/a$b;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  0      8      171    185    Any
        //  8      29     171    185    Any
        //  36     42     171    185    Any
        //  45     60     171    185    Any
        //  71     80     171    185    Any
        //  91     97     171    185    Any
        //  97     108    185    189    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  97     108    171    185    Any
        //  117    167    185    189    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  117    167    171    185    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0167:
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
    
    public static boolean a(final a a, final PackageInfo packageInfo) {
        final String s = "";
        boolean b = false;
        String s2;
        if (packageInfo == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append("info == null");
            s2 = sb.toString();
        }
        else {
            final Signature[] signatures = packageInfo.signatures;
            if (signatures == null) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append("info.signatures == null");
                s2 = sb2.toString();
            }
            else if (signatures.length <= 0) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("");
                sb3.append("info.signatures.length <= 0");
                s2 = sb3.toString();
            }
            else {
                b = true;
                s2 = s;
            }
        }
        if (!b) {
            com.alipay.sdk.m.k.a.b(a, "auth", "NotIncludeSignatures", s2);
        }
        return b;
    }
    
    public static boolean a(final a a, String host) {
        try {
            host = new URL(host).getHost();
            if (host.endsWith("alipay.com") || host.endsWith("alipay.net")) {
                return true;
            }
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.k.a.a(a, "biz", "ckUrlErr", t);
        }
        return false;
    }
    
    public static boolean a(final a a, String decode, final Activity activity) {
        if (TextUtils.isEmpty((CharSequence)decode)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (!decode.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) {
            if (!decode.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
                if (TextUtils.equals((CharSequence)decode, (CharSequence)"sdklite://h5quit") || TextUtils.equals((CharSequence)decode, (CharSequence)a("http", "://m.alipay.com/?action=h5quit"))) {
                    com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a());
                    activity.finish();
                    return true;
                }
                if (decode.startsWith("sdklite://h5quit?result=")) {
                    try {
                        final String substring = decode.substring(decode.indexOf("sdklite://h5quit?result=") + 24);
                        final int int1 = Integer.parseInt(substring.substring(substring.lastIndexOf("&end_code=") + 10));
                        if (int1 != com.alipay.sdk.m.j.c.c.b() && int1 != com.alipay.sdk.m.j.c.j.b()) {
                            final com.alipay.sdk.m.j.c b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.d.b());
                            com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a(b.b(), b.a(), ""));
                        }
                        else {
                            String s2;
                            if (com.alipay.sdk.m.l.a.x) {
                                final StringBuilder sb = new StringBuilder();
                                decode = URLDecoder.decode(decode);
                                final String decode2 = URLDecoder.decode(decode);
                                final String s = decode2.substring(decode2.indexOf("sdklite://h5quit?result=") + 24, decode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                                final int n = decode.indexOf("&return_url=") + 12;
                                sb.append(s);
                                sb.append("&return_url=");
                                sb.append(decode.substring(n, decode.indexOf("&", n)));
                                sb.append(decode.substring(decode.indexOf("&", n)));
                                s2 = sb.toString();
                            }
                            else {
                                final String decode3 = URLDecoder.decode(decode);
                                s2 = decode3.substring(decode3.indexOf("sdklite://h5quit?result=") + 24, decode3.lastIndexOf("&end_code="));
                            }
                            final com.alipay.sdk.m.j.c b2 = com.alipay.sdk.m.j.c.b(int1);
                            com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a(b2.b(), b2.a(), s2));
                        }
                    }
                    catch (final Exception ex) {
                        com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.e());
                    }
                    activity.runOnUiThread((Runnable)new Runnable(activity) {
                        public final Activity a;
                        
                        public void run() {
                            this.a.finish();
                        }
                    });
                    return true;
                }
                return false;
            }
        }
        try {
            final c a2 = a(a, (Context)activity, (List<a$b>)com.alipay.sdk.m.j.a.d);
            if (a2 != null && !a2.a()) {
                if (!a2.a(a)) {
                    String replaceFirst = decode;
                    if (decode.startsWith("intent://platformapi/startapp")) {
                        replaceFirst = decode.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                    }
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(replaceFirst)));
                }
            }
            return true;
        }
        finally {
            return true;
        }
    }
    
    public static boolean a(final Object o, final Object... array) {
        boolean b = true;
        if (array != null && array.length != 0) {
            for (final Object obj : array) {
                if ((o == null && obj == null) || (o != null && o.equals(obj))) {
                    return true;
                }
            }
            return false;
        }
        if (o != null) {
            b = false;
        }
        return b;
    }
    
    public static int b(final int n) {
        return n / 100000;
    }
    
    public static String b() {
        if (EnvUtils.isSandBox()) {
            if (TextUtils.equals((CharSequence)"hk.alipay.wallet", (CharSequence)((a$b)com.alipay.sdk.m.j.a.d.get(0)).a)) {
                return "hk.alipay.walletRC";
            }
            return "com.eg.android.AlipayGphoneRC";
        }
        else {
            try {
                return ((a$b)com.alipay.sdk.m.j.a.d.get(0)).a;
            }
            finally {
                return "com.eg.android.AlipayGphone";
            }
        }
    }
    
    public static String b(final Context context) {
        return "-1;-1";
    }
    
    public static String b(final a a, final Context context) {
        return a(a, context, context.getPackageName());
    }
    
    public static String b(String string, String s) {
        string = Settings$Secure.getString(((Application)com.alipay.sdk.m.s.b.d().b()).getContentResolver(), string);
        if (string != null) {
            s = string;
        }
        return s;
    }
    
    public static Map<String, String> b(final a a, String s) {
        final HashMap hashMap = new HashMap(4);
        final int index = s.indexOf(63);
        if (index != -1 && index < s.length() - 1) {
            final String[] split = s.substring(index + 1).split("&");
            for (int length = split.length, i = 0; i < length; ++i) {
                s = split[i];
                final int index2 = s.indexOf(61, 1);
                if (index2 != -1) {
                    if (index2 < s.length() - 1) {
                        ((Map)hashMap).put((Object)s.substring(0, index2), (Object)e(a, s.substring(index2 + 1)));
                    }
                }
            }
        }
        return (Map<String, String>)hashMap;
    }
    
    public static Map<String, String> b(final String s) {
        final HashMap hashMap = new HashMap();
        for (final String s2 : s.split("&")) {
            final int index = s2.indexOf("=", 1);
            if (-1 != index) {
                ((Map)hashMap).put((Object)s2.substring(0, index), (Object)URLDecoder.decode(s2.substring(index + 1)));
            }
        }
        return (Map<String, String>)hashMap;
    }
    
    public static boolean b(final a a) {
        return a != null && !TextUtils.isEmpty((CharSequence)a.g) && a.g.toLowerCase().contains((CharSequence)"auth");
    }
    
    public static int c() {
        int n = 1;
        try {
            final String lowerCase = Build.BRAND.toLowerCase();
            final String lowerCase2 = Build.MANUFACTURER.toLowerCase();
            if (!a("huawei", new Object[] { lowerCase, lowerCase2 })) {
                if (a("oppo", new Object[] { lowerCase, lowerCase2 })) {
                    n = 2;
                }
                else if (a("vivo", new Object[] { lowerCase, lowerCase2 })) {
                    n = 4;
                }
                else if (a("lenovo", new Object[] { lowerCase, lowerCase2 })) {
                    n = 8;
                }
                else if (a("xiaomi", new Object[] { lowerCase, lowerCase2 })) {
                    n = 16;
                }
                else if (a("oneplus", new Object[] { lowerCase, lowerCase2 })) {
                    n = 32;
                }
                else {
                    n = 0;
                }
            }
        }
        catch (final Exception ex) {
            n = 61440;
        }
        return n;
    }
    
    public static String c(final Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }
    
    public static String c(a a, String s) {
        final a a2 = null;
        try {
            s = (String)(a = (a)Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke((Object)null, new Object[] { s }));
        }
        catch (final Exception ex) {
            com.alipay.sdk.m.k.a.b(a, "biz", "rflex", ex.getClass().getSimpleName());
            a = a2;
        }
        return (String)a;
    }
    
    public static String c(final String s) {
        if (EnvUtils.isSandBox() && TextUtils.equals((CharSequence)s, (CharSequence)"com.eg.android.AlipayGphoneRC")) {
            return "com.eg.android.AlipayGphoneRC.IAlixPay";
        }
        return "com.eg.android.AlipayGphone.IAlixPay";
    }
    
    public static int d(final String s) {
        final int n = 0;
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= 64) {
                break;
            }
            if (s.equals((Object)String.valueOf(com.alipay.sdk.m.u.n.j[n2]))) {
                n3 = n2;
                break;
            }
            ++n2;
        }
        return n3;
    }
    
    public static DisplayMetrics d(final Context context) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    
    public static String d() {
        try {
            Object matcher = new BufferedReader((Reader)new FileReader("/proc/version"), 256);
            try {
                final String line = ((BufferedReader)matcher).readLine();
                ((BufferedReader)matcher).close();
                matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher((CharSequence)line);
                if (!((Matcher)matcher).matches()) {
                    return "Unavailable";
                }
                if (((Matcher)matcher).groupCount() < 4) {
                    return "Unavailable";
                }
                final StringBuilder sb = new StringBuilder(((Matcher)matcher).group(1));
                sb.append("\n");
                sb.append(((Matcher)matcher).group(2));
                sb.append(" ");
                sb.append(((Matcher)matcher).group(3));
                sb.append("\n");
                sb.append(((Matcher)matcher).group(4));
                return sb.toString();
            }
            finally {
                try {
                    ((BufferedReader)matcher).close();
                }
                catch (final IOException ex) {
                    return "Unavailable";
                }
            }
        }
        catch (final IOException ex2) {}
    }
    
    public static boolean d(final a a, final String s) {
        final boolean b = false;
        try {
            final int e = e(s);
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e);
            com.alipay.sdk.m.k.a.a(a, "biz", "bindExt", sb.toString());
            final boolean m = com.alipay.sdk.m.m.a.z().m();
            boolean b2 = b;
            if (m) {
                b2 = b;
                if ((e & 0x2) == 0x2) {
                    b2 = true;
                }
            }
            return b2;
        }
        finally {
            return b;
        }
    }
    
    public static int e(final String s) {
        int n = 0;
        try {
            final String h = com.alipay.sdk.m.m.a.z().h();
            if (TextUtils.isEmpty((CharSequence)h)) {
                return 0;
            }
            if (b(h, "").contains((CharSequence)s)) {}
        }
        finally {
            n = 61440;
        }
        return n;
    }
    
    public static String e() {
        final String d = d();
        final int index = d.indexOf("-");
        String substring = d;
        if (index != -1) {
            substring = d.substring(0, index);
        }
        final int index2 = substring.indexOf("\n");
        String substring2 = substring;
        if (index2 != -1) {
            substring2 = substring.substring(0, index2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Linux ");
        sb.append(substring2);
        return sb.toString();
    }
    
    public static String e(final Context context) {
        final String b = m.b(context);
        return b.substring(0, b.indexOf("://"));
    }
    
    public static String e(final a a, String decode) {
        try {
            decode = URLDecoder.decode(decode, "utf-8");
            return decode;
        }
        catch (final UnsupportedEncodingException ex) {
            com.alipay.sdk.m.k.a.a(a, "biz", "H5PayDataAnalysisError", (Throwable)ex);
            return "";
        }
    }
    
    public static String f() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Android ");
        sb.append(Build$VERSION.RELEASE);
        return sb.toString();
    }
    
    public static String f(final Context context) {
        final DisplayMetrics d = d(context);
        final StringBuilder sb = new StringBuilder();
        sb.append(d.widthPixels);
        sb.append("*");
        sb.append(d.heightPixels);
        return sb.toString();
    }
    
    public static boolean f(final String s) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher((CharSequence)s).matches();
    }
    
    public static int g() {
        int n = 0;
        try {
            Process.myUid();
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
            n = -200;
        }
        return n;
    }
    
    public static String g(final Context context) {
        final String f = f();
        final String e = e();
        final String c = c(context);
        final String f2 = f(context);
        final StringBuilder sb = new StringBuilder();
        sb.append(" (");
        sb.append(f);
        sb.append(";");
        sb.append(e);
        sb.append(";");
        sb.append(c);
        sb.append(";");
        sb.append(";");
        sb.append(f2);
        sb.append(")");
        sb.append("(sdk android)");
        return sb.toString();
    }
    
    public static String g(final String s) {
        return a(s, true);
    }
    
    public static JSONObject h(final String s) {
        JSONObject jsonObject2;
        try {
            final JSONObject jsonObject = new JSONObject(s);
        }
        finally {
            jsonObject2 = new JSONObject();
        }
        return jsonObject2;
    }
    
    public static boolean h() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
    
    public static boolean h(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) != null;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static String i(String s) {
        try {
            final Uri parse = Uri.parse(s);
            s = parse.getAuthority();
            s = String.format("%s%s", new Object[] { s, parse.getPath() });
            return s;
        }
        finally {
            final Throwable t;
            com.alipay.sdk.m.u.e.a(t);
            return "-";
        }
    }
    
    public static final class c
    {
        public final PackageInfo a;
        public final int b;
        public final String c;
        
        public c(final PackageInfo a, final int b, final String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public boolean a() {
            return this.a.versionCode < this.b;
        }
        
        public boolean a(final a a) {
            final Signature[] signatures = this.a.signatures;
            boolean b2;
            final boolean b = b2 = false;
            if (signatures != null) {
                if (signatures.length == 0) {
                    b2 = b;
                }
                else {
                    final int length = signatures.length;
                    int n = 0;
                    while (true) {
                        b2 = b;
                        if (n >= length) {
                            break;
                        }
                        final String a2 = com.alipay.sdk.m.u.n.a(a, signatures[n].toByteArray());
                        if (a2 != null && !TextUtils.equals((CharSequence)a2, (CharSequence)this.c)) {
                            com.alipay.sdk.m.k.a.b(a, "biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", new Object[] { a2, this.c }));
                            b2 = true;
                            break;
                        }
                        ++n;
                    }
                }
            }
            return b2;
        }
    }
}
