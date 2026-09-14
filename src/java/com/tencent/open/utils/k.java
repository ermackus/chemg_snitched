package com.tencent.open.utils;

import android.database.Cursor;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.net.URLEncoder;
import org.json.JSONException;
import android.util.DisplayMetrics;
import android.os.ParcelFileDescriptor;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ContentUris;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Images$Media;
import android.provider.DocumentsContract;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Build$VERSION;
import android.os.Environment;
import android.content.ComponentName;
import android.content.Intent;
import org.json.JSONObject;
import java.util.Map;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import java.io.UnsupportedEncodingException;
import android.util.Base64;
import java.net.URLDecoder;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import java.io.File;
import com.tencent.tauth.Tencent;
import android.text.TextUtils;
import android.app.Activity;
import android.content.res.AssetManager;
import java.io.InputStream;
import java.io.IOException;
import com.tencent.open.log.SLog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.content.Context;

public class k
{
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static int e = -1;
    private static String f = "0123456789ABCDEF";
    
    private static char a(int n) {
        n &= 0xF;
        if (n < 10) {
            n += 48;
        }
        else {
            n = n - 10 + 97;
        }
        return (char)n;
    }
    
    public static long a(final Context p0, final Uri p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: aload_1        
        //     5: iconst_1       
        //     6: anewarray       Ljava/lang/String;
        //     9: dup            
        //    10: iconst_0       
        //    11: ldc             "_size"
        //    13: aastore        
        //    14: aconst_null    
        //    15: aconst_null    
        //    16: aconst_null    
        //    17: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    20: astore_1       
        //    21: lconst_0       
        //    22: lstore          5
        //    24: aload_1        
        //    25: ifnull          144
        //    28: aload_1        
        //    29: invokeinterface android/database/Cursor.getCount:()I
        //    34: ifne            40
        //    37: goto            144
        //    40: aload_1        
        //    41: ldc             "_size"
        //    43: invokeinterface android/database/Cursor.getColumnIndexOrThrow:(Ljava/lang/String;)I
        //    48: istore_2       
        //    49: lload           5
        //    51: lstore_3       
        //    52: aload_1        
        //    53: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    58: ifeq            69
        //    61: aload_1        
        //    62: iload_2        
        //    63: invokeinterface android/database/Cursor.getLong:(I)J
        //    68: lstore_3       
        //    69: lload_3        
        //    70: lstore          7
        //    72: aload_1        
        //    73: invokeinterface android/database/Cursor.close:()V
        //    78: goto            122
        //    81: astore_0       
        //    82: ldc             "openSDK_LOG.Util"
        //    84: ldc             "cursor exception"
        //    86: aload_0        
        //    87: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    90: lload           7
        //    92: lstore_3       
        //    93: goto            122
        //    96: astore_0       
        //    97: goto            124
        //   100: astore_0       
        //   101: ldc             "openSDK_LOG.Util"
        //   103: ldc             "cursor exception"
        //   105: aload_0        
        //   106: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   109: lload           5
        //   111: lstore          7
        //   113: aload_1        
        //   114: invokeinterface android/database/Cursor.close:()V
        //   119: lload           5
        //   121: lstore_3       
        //   122: lload_3        
        //   123: lreturn        
        //   124: aload_1        
        //   125: invokeinterface android/database/Cursor.close:()V
        //   130: goto            142
        //   133: astore_1       
        //   134: ldc             "openSDK_LOG.Util"
        //   136: ldc             "cursor exception"
        //   138: aload_1        
        //   139: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   142: aload_0        
        //   143: athrow         
        //   144: lconst_0       
        //   145: lreturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  40     49     100    122    Ljava/lang/Exception;
        //  40     49     96     144    Any
        //  52     69     100    122    Ljava/lang/Exception;
        //  52     69     96     144    Any
        //  72     78     81     96     Ljava/lang/Exception;
        //  101    109    96     144    Any
        //  113    119    81     96     Ljava/lang/Exception;
        //  124    130    133    142    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0124:
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
    
    public static Drawable a(final String s, Context open) {
        final InputStream inputStream = null;
        final IOException ex = null;
        if (open == null) {
            SLog.e("openSDK_LOG.Util", "context null!");
            return null;
        }
        final AssetManager assets = open.getAssets();
        Label_0175: {
            Object o;
            while (true) {
                try {
                    final InputStream inputStream2 = (InputStream)(open = (Context)assets.open(s));
                    try {
                        try {
                            Drawable.createFromStream(inputStream2, s);
                            try {
                                inputStream2.close();
                            }
                            catch (final Exception ex2) {
                                open = (Context)new StringBuilder();
                            }
                            ((StringBuilder)open).append("inputStream close exception: ");
                            final Exception ex2;
                            ((StringBuilder)open).append(ex2.getMessage());
                            SLog.e("openSDK_LOG.Util", ((StringBuilder)open).toString());
                        }
                        finally {}
                    }
                    catch (final IOException ex) {}
                }
                catch (final IOException ex) {
                    o = null;
                }
                finally {
                    o = ex;
                    break Label_0175;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("getDrawable exception: ");
                sb.append(ex.getMessage());
                SLog.e("openSDK_LOG.Util", sb.toString());
                try {
                    ((InputStream)o).close();
                    o = inputStream;
                }
                catch (final Exception ex2) {
                    open = (Context)new StringBuilder();
                    continue;
                }
                break;
            }
            return (Drawable)o;
            try {
                ((InputStream)o).close();
            }
            catch (final Exception ex3) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("inputStream close exception: ");
                sb2.append(ex3.getMessage());
                SLog.e("openSDK_LOG.Util", sb2.toString());
            }
        }
    }
    
    public static Uri a(final Activity activity, String authorities, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            SLog.e("openSDK_LOG.Util", "grantUriPermissionToAllQQVersion -- stringForFileUri is empty");
            return null;
        }
        try {
            authorities = Tencent.getAuthorities(authorities);
            if (TextUtils.isEmpty((CharSequence)authorities)) {
                return null;
            }
            final Uri uriForFile = FileProvider.getUriForFile((Context)activity, authorities, new File(s));
            activity.grantUriPermission("com.tencent.mobileqq", uriForFile, 3);
            activity.grantUriPermission("com.tencent.tim", uriForFile, 3);
            activity.grantUriPermission("com.tencent.minihd.qq", uriForFile, 3);
            activity.grantUriPermission("com.tencent.qqlite", uriForFile, 3);
            return uriForFile;
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.Util", "grantUriPermissionToAllQQVersion exception:", (Throwable)ex);
            return null;
        }
    }
    
    public static Bundle a(final String s) {
        Bundle bundle2;
        final Bundle bundle = bundle2 = new Bundle();
        if (s != null) {
            try {
                final String[] split = s.split("&");
                final int length = split.length;
                int n = 0;
                while (true) {
                    bundle2 = bundle;
                    if (n >= length) {
                        break;
                    }
                    final String[] split2 = split[n].split("=");
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                    }
                    ++n;
                }
            }
            catch (final Exception ex) {
                bundle2 = null;
            }
        }
        return bundle2;
    }
    
    public static Bundle a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        return a(s, s3, s4, s2, s5, s6, "", "", "", "", "", "");
    }
    
    public static Bundle a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12) {
        final Bundle bundle = new Bundle();
        bundle.putString("openid", s);
        bundle.putString("report_type", s2);
        bundle.putString("act_type", s3);
        bundle.putString("via", s4);
        bundle.putString("app_id", s5);
        bundle.putString("result", s6);
        bundle.putString("type", s7);
        bundle.putString("login_status", s8);
        bundle.putString("need_user_auth", s9);
        bundle.putString("to_uin", s10);
        bundle.putString("call_source", s11);
        bundle.putString("to_type", s12);
        bundle.putString("platform", "1");
        return bundle;
    }
    
    public static final String a(final Context context) {
        if (context != null) {
            final CharSequence applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
            if (applicationLabel != null) {
                return applicationLabel.toString();
            }
        }
        return null;
    }
    
    public static String a(String encodeToString, final int n) {
        if (!TextUtils.isEmpty((CharSequence)encodeToString)) {
            try {
                encodeToString = Base64.encodeToString(encodeToString.getBytes("UTF-8"), n);
                return encodeToString;
            }
            catch (final UnsupportedEncodingException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("convert2Base64String exception: ");
                sb.append(ex.getMessage());
                SLog.e("openSDK_LOG.Util", sb.toString());
            }
        }
        encodeToString = "";
        return encodeToString;
    }
    
    public static final String a(String s, final int n, String s2, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        String s4;
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            s4 = s2;
        }
        else {
            s4 = "UTF-8";
        }
        s2 = s;
        try {
            if (s.getBytes(s4).length <= n) {
                return s;
            }
            int n2 = 0;
            int n3 = 0;
            while (true) {
                s2 = s;
                if (n2 >= s.length()) {
                    return s;
                }
                final int n4 = n2 + 1;
                s2 = s;
                n3 += s.substring(n2, n4).getBytes(s4).length;
                if (n3 > n) {
                    s2 = s;
                    String s5;
                    s = (s2 = (s5 = s.substring(0, n2)));
                    if (!TextUtils.isEmpty((CharSequence)s3)) {
                        s2 = s;
                        s2 = s;
                        final StringBuilder sb = new StringBuilder();
                        s2 = s;
                        sb.append(s);
                        s2 = s;
                        sb.append(s3);
                        s2 = s;
                        s5 = sb.toString();
                    }
                    return s5;
                }
                n2 = n4;
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Util.subString has exception: ");
            sb2.append(ex.getMessage());
            SLog.e("openSDK_LOG.Util", sb2.toString());
            return s2;
        }
    }
    
    public static String a(String string, final Activity activity, String string2, final IUiListener uiListener) {
        final String s = null;
        try {
            final boolean n = n(string2);
            final boolean c = c();
            final StringBuilder sb = new StringBuilder();
            sb.append("doPublishMood() check file: isAppSpecificDir=");
            sb.append(n);
            sb.append(",hasSDPermission=");
            sb.append(c);
            SLog.i("openSDK_LOG.Util", sb.toString());
            if (!n) {
                final File a = com.tencent.open.utils.f.a("Images");
                String s2;
                if (a != null) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(a.getAbsolutePath());
                    sb2.append(File.separator);
                    sb2.append(Constants.QQ_SHARE_TEMP_DIR);
                    s2 = sb2.toString();
                }
                else {
                    final File cacheDir = com.tencent.open.utils.f.a().getCacheDir();
                    if (cacheDir == null) {
                        SLog.e("openSDK_LOG.Util", "getMediaFileUri error, cacheDir is null");
                        return null;
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(cacheDir.getAbsolutePath());
                    sb3.append(File.separator);
                    sb3.append(Constants.QQ_SHARE_TEMP_DIR);
                    s2 = sb3.toString();
                }
                final File file = new File(string2);
                final String absolutePath = file.getAbsolutePath();
                final String name = file.getName();
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(s2);
                sb4.append(File.separator);
                sb4.append(name);
                string2 = sb4.toString();
                if (!a(absolutePath, string2)) {
                    string2 = null;
                }
            }
            final Uri a2 = a(activity, string, string2);
            if (a2 == null) {
                string = s;
            }
            else {
                string = a2.toString();
            }
            return string;
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.Util", "getMediaFileUri error", (Throwable)ex);
            return null;
        }
    }
    
    public static String a(final Map<String, Object> map, final String s, String s2) {
        if (map == null) {
            SLog.e("openSDK_LOG.Util", "getString error, params==null");
            return s2;
        }
        if (!map.containsKey((Object)s)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getString error, not comtain : ");
            sb.append(s);
            SLog.e("openSDK_LOG.Util", sb.toString());
            return s2;
        }
        final Object value = map.get((Object)s);
        if (value instanceof String) {
            s2 = (String)value;
        }
        return s2;
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            String s2;
            final String s = s2 = Integer.toString(array[i] & 0xFF, 16);
            if (s.length() == 1) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("0");
                sb2.append(s);
                s2 = sb2.toString();
            }
            sb.append(s2);
        }
        return sb.toString();
    }
    
    public static JSONObject a(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: ifnonnull       16
        //     7: new             Lorg/json/JSONObject;
        //    10: dup            
        //    11: invokespecial   org/json/JSONObject.<init>:()V
        //    14: astore          4
        //    16: aload_1        
        //    17: ifnull          142
        //    20: aload_1        
        //    21: ldc             "&"
        //    23: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    26: astore_0       
        //    27: aload_0        
        //    28: arraylength    
        //    29: istore_3       
        //    30: iconst_0       
        //    31: istore_2       
        //    32: iload_2        
        //    33: iload_3        
        //    34: if_icmpge       142
        //    37: aload_0        
        //    38: iload_2        
        //    39: aaload         
        //    40: ldc             "="
        //    42: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    45: astore          5
        //    47: aload           5
        //    49: arraylength    
        //    50: iconst_2       
        //    51: if_icmpne       136
        //    54: aload           5
        //    56: iconst_0       
        //    57: aload           5
        //    59: iconst_0       
        //    60: aaload         
        //    61: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;)Ljava/lang/String;
        //    64: aastore        
        //    65: aload           5
        //    67: iconst_1       
        //    68: aload           5
        //    70: iconst_1       
        //    71: aaload         
        //    72: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;)Ljava/lang/String;
        //    75: aastore        
        //    76: goto            84
        //    79: astore          5
        //    81: goto            101
        //    84: aload           4
        //    86: aload           5
        //    88: iconst_0       
        //    89: aaload         
        //    90: aload           5
        //    92: iconst_1       
        //    93: aaload         
        //    94: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    97: pop            
        //    98: goto            136
        //   101: new             Ljava/lang/StringBuilder;
        //   104: dup            
        //   105: invokespecial   java/lang/StringBuilder.<init>:()V
        //   108: astore_1       
        //   109: aload_1        
        //   110: ldc_w           "decodeUrlToJson has exception: "
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: pop            
        //   117: aload_1        
        //   118: aload           5
        //   120: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: pop            
        //   127: ldc             "openSDK_LOG.Util"
        //   129: aload_1        
        //   130: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   133: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   136: iinc            2, 1
        //   139: goto            32
        //   142: aload           4
        //   144: areturn        
        //   145: astore_1       
        //   146: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  54     76     145    149    Ljava/lang/Exception;
        //  54     76     79     84     Lorg/json/JSONException;
        //  84     98     79     84     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException: Attempt to invoke virtual method 'g5.m0 g5.d2.L()' on a null object reference
        //     at e5.d0.e(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:26)
        //     at e5.c0.s(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:1643)
        //     at q5.g.o(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2651)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2099)
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
    
    private static void a(final Context context, final String s, final String s2, final String s3) {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(s, s2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(s3));
        context.startActivity(intent);
    }
    
    public static boolean a() {
        File externalStorageDirectory;
        if (Environment.getExternalStorageState().equals((Object)"mounted")) {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        }
        else {
            externalStorageDirectory = null;
        }
        return externalStorageDirectory != null;
    }
    
    public static boolean a(final Context context, final String s) {
        int g = 0;
        Label_0044: {
            try {
                g = (g(context) ? 1 : 0);
                if (g != 0) {
                    try {
                        a(context, "com.tencent.mtt", "com.tencent.mtt.MainActivity", s);
                        return true;
                    }
                    catch (final Exception ex) {
                        break Label_0044;
                    }
                }
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", s);
                return true;
            }
            catch (final Exception ex2) {
                g = 0;
            }
        }
        if (g != 0) {
            try {
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", s);
                return true;
            }
            catch (final Exception ex3) {
                try {
                    a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", s);
                }
                catch (final Exception ex4) {
                    try {
                        a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", s);
                    }
                    catch (final Exception ex5) {
                        return false;
                    }
                }
            }
        }
        try {
            a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", s);
            return true;
        }
        catch (final Exception ex6) {
            final Context context2 = context;
            final String s2 = "com.android.chrome";
            final String s3 = "com.google.android.apps.chrome.Main";
            final String s4 = s;
            a(context2, s2, s3, s4);
        }
        try {
            final Context context2 = context;
            final String s2 = "com.android.chrome";
            final String s3 = "com.google.android.apps.chrome.Main";
            final String s4 = s;
            a(context2, s2, s3, s4);
            return true;
        }
        catch (final Exception ex7) {
            return false;
        }
    }
    
    public static boolean a(final Context context, final String s, final String s2) {
        boolean a;
        if (Build$VERSION.SDK_INT < 19) {
            a = (context.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", context.getPackageName()) == 0 && a(s, s2));
        }
        else {
            a = a(s, s2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("copyFileByCheckPermission() copy success:");
        sb.append(a);
        SLog.i("openSDK_LOG.Util", sb.toString());
        return a;
    }
    
    public static boolean a(final Context context, final boolean b) {
        final boolean c = c(context);
        final boolean b2 = true;
        if (c && i.a(context, "com.tencent.minihd.qq") != null) {
            return true;
        }
        boolean b3 = b2;
        if (i.c(context, "4.1") < 0) {
            b3 = b2;
            if (i.a(context, "com.tencent.tim") == null) {
                b3 = (i.a(context, "com.tencent.qqlite") != null && b2);
            }
        }
        return b3;
    }
    
    public static boolean a(final File p0, final File p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          4
        //     3: iconst_0       
        //     4: istore          5
        //     6: aconst_null    
        //     7: astore          9
        //     9: aconst_null    
        //    10: astore          8
        //    12: aconst_null    
        //    13: astore          7
        //    15: aload_1        
        //    16: invokevirtual   java/io/File.exists:()Z
        //    19: ifeq            27
        //    22: aload_1        
        //    23: invokevirtual   java/io/File.delete:()Z
        //    26: pop            
        //    27: aload_1        
        //    28: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    31: ifnull          52
        //    34: aload_1        
        //    35: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    38: invokevirtual   java/io/File.exists:()Z
        //    41: ifne            52
        //    44: aload_1        
        //    45: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    48: invokevirtual   java/io/File.mkdirs:()Z
        //    51: pop            
        //    52: new             Ljava/io/FileOutputStream;
        //    55: astore          6
        //    57: aload           6
        //    59: aload_1        
        //    60: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    63: new             Ljava/io/BufferedInputStream;
        //    66: astore_1       
        //    67: new             Ljava/io/FileInputStream;
        //    70: astore          7
        //    72: aload           7
        //    74: aload_0        
        //    75: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    78: aload_1        
        //    79: aload           7
        //    81: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    84: ldc_w           102400
        //    87: newarray        B
        //    89: astore_0       
        //    90: aload_1        
        //    91: aload_0        
        //    92: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //    95: istore_2       
        //    96: iload_2        
        //    97: iconst_m1      
        //    98: if_icmpeq       117
        //   101: aload           6
        //   103: aload_0        
        //   104: iconst_0       
        //   105: iload_2        
        //   106: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //   109: aload           6
        //   111: invokevirtual   java/io/FileOutputStream.flush:()V
        //   114: goto            90
        //   117: iconst_1       
        //   118: istore          4
        //   120: iconst_1       
        //   121: istore_3       
        //   122: aload           6
        //   124: invokevirtual   java/io/FileOutputStream.close:()V
        //   127: goto            140
        //   130: astore_0       
        //   131: ldc             "openSDK_LOG.Util"
        //   133: ldc_w           "copyFile error, "
        //   136: aload_0        
        //   137: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   140: aload_1        
        //   141: invokevirtual   java/io/BufferedInputStream.close:()V
        //   144: iload           4
        //   146: istore_3       
        //   147: goto            368
        //   150: astore_0       
        //   151: ldc             "openSDK_LOG.Util"
        //   153: ldc_w           "copyFile error, "
        //   156: aload_0        
        //   157: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   160: goto            368
        //   163: astore          8
        //   165: aload_1        
        //   166: astore          7
        //   168: goto            184
        //   171: astore_0       
        //   172: goto            196
        //   175: astore_0       
        //   176: goto            212
        //   179: astore          8
        //   181: aconst_null    
        //   182: astore          7
        //   184: aload           6
        //   186: astore_0       
        //   187: aload           8
        //   189: astore_1       
        //   190: goto            378
        //   193: astore_0       
        //   194: aconst_null    
        //   195: astore_1       
        //   196: aload           6
        //   198: astore          7
        //   200: aload_0        
        //   201: astore          8
        //   203: aload_1        
        //   204: astore          6
        //   206: goto            240
        //   209: astore_0       
        //   210: aconst_null    
        //   211: astore_1       
        //   212: aload           6
        //   214: astore          7
        //   216: aload_0        
        //   217: astore          8
        //   219: aload_1        
        //   220: astore          6
        //   222: goto            310
        //   225: astore_1       
        //   226: aconst_null    
        //   227: astore          7
        //   229: aload           8
        //   231: astore_0       
        //   232: goto            378
        //   235: astore          8
        //   237: aconst_null    
        //   238: astore          6
        //   240: aload           7
        //   242: astore_0       
        //   243: aload           6
        //   245: astore_1       
        //   246: ldc             "openSDK_LOG.Util"
        //   248: ldc_w           "copyFile error, "
        //   251: aload           8
        //   253: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   256: aload           7
        //   258: ifnull          279
        //   261: aload           7
        //   263: invokevirtual   java/io/FileOutputStream.close:()V
        //   266: goto            279
        //   269: astore_0       
        //   270: ldc             "openSDK_LOG.Util"
        //   272: ldc_w           "copyFile error, "
        //   275: aload_0        
        //   276: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   279: iload           4
        //   281: istore_3       
        //   282: aload           6
        //   284: ifnull          368
        //   287: iload           5
        //   289: istore_3       
        //   290: aload           6
        //   292: invokevirtual   java/io/BufferedInputStream.close:()V
        //   295: iload           4
        //   297: istore_3       
        //   298: goto            368
        //   301: astore          8
        //   303: aconst_null    
        //   304: astore          6
        //   306: aload           9
        //   308: astore          7
        //   310: aload           7
        //   312: astore_0       
        //   313: aload           6
        //   315: astore_1       
        //   316: ldc             "openSDK_LOG.Util"
        //   318: ldc_w           "copyFile error, "
        //   321: aload           8
        //   323: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   326: aload           7
        //   328: ifnull          349
        //   331: aload           7
        //   333: invokevirtual   java/io/FileOutputStream.close:()V
        //   336: goto            349
        //   339: astore_0       
        //   340: ldc             "openSDK_LOG.Util"
        //   342: ldc_w           "copyFile error, "
        //   345: aload_0        
        //   346: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   349: iload           4
        //   351: istore_3       
        //   352: aload           6
        //   354: ifnull          368
        //   357: iload           5
        //   359: istore_3       
        //   360: aload           6
        //   362: invokevirtual   java/io/BufferedInputStream.close:()V
        //   365: iload           4
        //   367: istore_3       
        //   368: iload_3        
        //   369: ireturn        
        //   370: astore          6
        //   372: aload_1        
        //   373: astore          7
        //   375: aload           6
        //   377: astore_1       
        //   378: aload_0        
        //   379: ifnull          399
        //   382: aload_0        
        //   383: invokevirtual   java/io/FileOutputStream.close:()V
        //   386: goto            399
        //   389: astore_0       
        //   390: ldc             "openSDK_LOG.Util"
        //   392: ldc_w           "copyFile error, "
        //   395: aload_0        
        //   396: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   399: aload           7
        //   401: ifnull          422
        //   404: aload           7
        //   406: invokevirtual   java/io/BufferedInputStream.close:()V
        //   409: goto            422
        //   412: astore_0       
        //   413: ldc             "openSDK_LOG.Util"
        //   415: ldc_w           "copyFile error, "
        //   418: aload_0        
        //   419: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   422: aload_1        
        //   423: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  15     27     301    310    Ljava/io/IOException;
        //  15     27     235    240    Ljava/lang/OutOfMemoryError;
        //  15     27     225    235    Any
        //  27     52     301    310    Ljava/io/IOException;
        //  27     52     235    240    Ljava/lang/OutOfMemoryError;
        //  27     52     225    235    Any
        //  52     63     301    310    Ljava/io/IOException;
        //  52     63     235    240    Ljava/lang/OutOfMemoryError;
        //  52     63     225    235    Any
        //  63     84     209    212    Ljava/io/IOException;
        //  63     84     193    196    Ljava/lang/OutOfMemoryError;
        //  63     84     179    184    Any
        //  84     90     175    179    Ljava/io/IOException;
        //  84     90     171    175    Ljava/lang/OutOfMemoryError;
        //  84     90     163    171    Any
        //  90     96     175    179    Ljava/io/IOException;
        //  90     96     171    175    Ljava/lang/OutOfMemoryError;
        //  90     96     163    171    Any
        //  101    114    175    179    Ljava/io/IOException;
        //  101    114    171    175    Ljava/lang/OutOfMemoryError;
        //  101    114    163    171    Any
        //  122    127    130    140    Ljava/io/IOException;
        //  140    144    150    163    Ljava/io/IOException;
        //  246    256    370    378    Any
        //  261    266    269    279    Ljava/io/IOException;
        //  290    295    150    163    Ljava/io/IOException;
        //  316    326    370    378    Any
        //  331    336    339    349    Ljava/io/IOException;
        //  360    365    150    163    Ljava/io/IOException;
        //  382    386    389    399    Ljava/io/IOException;
        //  404    409    412    422    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0279:
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
    
    public static boolean a(final String s, final String s2) {
        final File file = new File(s);
        if (file.exists()) {
            try {
                return a(file, m(s2));
            }
            catch (final IOException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("copy fail from ");
                sb.append(s);
                sb.append(" to ");
                sb.append(s2);
                sb.append(" ");
                SLog.d("openSDK_LOG.Util", sb.toString(), (Throwable)ex);
            }
        }
        return false;
    }
    
    public static boolean a(final Map<String, Object> map, final String s, boolean booleanValue) {
        if (map == null) {
            SLog.e("openSDK_LOG.Util", "getBoolean error, params==null");
            return booleanValue;
        }
        if (!map.containsKey((Object)s)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getBoolean error, not comtain : ");
            sb.append(s);
            SLog.e("openSDK_LOG.Util", sb.toString());
            return booleanValue;
        }
        final Object value = map.get((Object)s);
        if (value instanceof Boolean) {
            booleanValue = (boolean)value;
        }
        return booleanValue;
    }
    
    private static byte[] a(final byte[] array, final String s) {
        if (array != null) {
            try {
                final char[] charArray = s.toCharArray();
                final int length = array.length;
                final byte[] array2 = new byte[length];
                for (int i = 0; i < length; ++i) {
                    array2[i] = (byte)(array[i] ^ charArray[i % charArray.length]);
                }
                return array2;
            }
            finally {
                final Throwable t;
                SLog.e("Util", "xor Exception! ", t);
            }
        }
        return array;
    }
    
    public static Bundle b(final String s) {
        final String replace = s.replace((CharSequence)"auth://", (CharSequence)"http://");
        try {
            final URL url = new URL(replace);
            final Bundle a = a(url.getQuery());
            a.putAll(a(url.getRef()));
            return a;
        }
        catch (final MalformedURLException ex) {
            return new Bundle();
        }
    }
    
    public static String b() {
        final File e = com.tencent.open.utils.f.e();
        String string;
        if (e != null) {
            if (!e.exists()) {
                e.mkdirs();
            }
            string = e.toString();
        }
        else {
            string = null;
        }
        return string;
    }
    
    public static String b(final Context context, Uri uri) {
        final String s = null;
        if (uri == null) {
            return null;
        }
        if (Build$VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(context, uri)) {
            final String scheme = uri.getScheme();
            String s2;
            if ("content".equals((Object)scheme)) {
                s2 = c(context, uri);
            }
            else {
                s2 = s;
                if ("file".equals((Object)scheme)) {
                    s2 = uri.getPath();
                }
            }
            return s2;
        }
        final String authority = uri.getAuthority();
        if ("com.android.externalstorage.documents".equals((Object)authority)) {
            final String[] split = DocumentsContract.getDocumentId(uri).split(":");
            final String s3 = split[0];
            if ("primary".equals((Object)s3)) {
                return Environment.getExternalStorageDirectory().getAbsolutePath().concat("/").concat(split[1]);
            }
            return "/storage/".concat(s3).concat("/").concat(split[1]);
        }
        else {
            if (!"com.android.providers.downloads.documents".equals((Object)authority)) {
                if ("com.android.providers.media.documents".equals((Object)authority)) {
                    final String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    final String s4 = split2[0];
                    if ("image".equals((Object)s4)) {
                        uri = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
                    }
                    else if ("video".equals((Object)s4)) {
                        uri = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
                    }
                    else {
                        if (!"audio".equals((Object)s4)) {
                            return null;
                        }
                        uri = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
                    }
                    return c(context, ContentUris.withAppendedId(uri, Long.parseLong(split2[1])));
                }
                return null;
            }
            final String documentId = DocumentsContract.getDocumentId(uri);
            if (documentId.startsWith("raw:")) {
                return documentId.replaceFirst("raw:", "");
            }
            return c(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(documentId)));
        }
    }
    
    public static void b(final Context context, final String s) {
        if (context == null) {
            return;
        }
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(s, 0);
            final String s2 = k.b = packageInfo.versionName;
            k.a = s2.substring(0, s2.lastIndexOf(46));
            k.d = k.b.substring(k.b.lastIndexOf(46) + 1, k.b.length());
            k.e = packageInfo.versionCode;
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getPackageInfo has exception: ");
            sb.append(ex.getMessage());
            SLog.e("openSDK_LOG.Util", sb.toString());
        }
        catch (final PackageManager$NameNotFoundException ex2) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("getPackageInfo has exception: ");
            sb2.append(ex2.getMessage());
            SLog.e("openSDK_LOG.Util", sb2.toString());
        }
    }
    
    public static boolean b(final Context context) {
        if (context == null) {
            return true;
        }
        if (Build$VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return true;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        final NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo.length == 0) {
            return true;
        }
        for (int length = allNetworkInfo.length, i = 0; i < length; ++i) {
            if (allNetworkInfo[i].isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }
    
    public static String c(Context ex, Uri absolutePath) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        Object query = null;
        Object o2 = null;
        Label_0792: {
            Object o = null;
            Label_0775: {
                try {
                    query = ((Context)ex).getContentResolver().query((Uri)absolutePath, new String[] { "_data" }, (String)null, (String[])null, (String)null);
                    if (query == null) {
                        break Label_0775;
                    }
                    try {
                        if (((Cursor)query).moveToFirst()) {
                            return ((Cursor)query).getString(((Cursor)query).getColumnIndexOrThrow("_data"));
                        }
                        break Label_0775;
                    }
                    catch (final Exception ex2) {}
                }
                catch (final Exception ex2) {
                    query = null;
                }
                o = new StringBuilder();
                ((StringBuilder)o).append("queryAbsolutePath error : ");
                final Exception ex2;
                ((StringBuilder)o).append(ex2.getMessage());
                SLog.e("openSDK_LOG.Util", ((StringBuilder)o).toString());
                if (query != null) {
                    ((Cursor)query).close();
                }
                try {
                    ParcelFileDescriptor openFileDescriptor = ((Context)ex).getContentResolver().openFileDescriptor((Uri)absolutePath, "r");
                    try {
                        o = new FileInputStream(openFileDescriptor.getFileDescriptor());
                        try {
                            final File h = h((Context)ex, "Images");
                            if (h == null) {
                                SLog.e("openSDK_LOG.Util", "getExternalFilesDir return null");
                                try {
                                    ((FileInputStream)o).close();
                                }
                                catch (final IOException ex3) {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("close fileIuputStream error");
                                    sb.append(ex3.getMessage());
                                    SLog.e("openSDK_LOG.Util", sb.toString());
                                }
                                if (openFileDescriptor != null) {
                                    try {
                                        openFileDescriptor.close();
                                    }
                                    catch (final IOException ex4) {
                                        final StringBuilder sb2 = new StringBuilder();
                                        sb2.append("close ParcelFileDescriptor error");
                                        sb2.append(ex4.getMessage());
                                        SLog.e("openSDK_LOG.Util", sb2.toString());
                                    }
                                }
                                return null;
                            }
                            if (!h.exists()) {
                                h.mkdirs();
                            }
                            final File file = new File(h, ((Uri)absolutePath).getLastPathSegment());
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            ex = (IOException)new FileOutputStream(file);
                            try {
                                final byte[] array = new byte[2048];
                                while (true) {
                                    final int read = ((FileInputStream)o).read(array);
                                    if (read == -1) {
                                        break;
                                    }
                                    ((FileOutputStream)ex).write(array, 0, read);
                                }
                                ((FileOutputStream)ex).flush();
                                absolutePath = (IOException)file.getAbsolutePath();
                                try {
                                    ((FileInputStream)o).close();
                                }
                                catch (final IOException o) {
                                    final StringBuilder sb3 = new StringBuilder();
                                    sb3.append("close fileIuputStream error");
                                    sb3.append(((IOException)o).getMessage());
                                    SLog.e("openSDK_LOG.Util", sb3.toString());
                                }
                                try {
                                    ((FileOutputStream)ex).close();
                                }
                                catch (final IOException ex) {
                                    final StringBuilder sb4 = new StringBuilder();
                                    sb4.append("close fileOutputStream error");
                                    sb4.append(ex.getMessage());
                                    SLog.e("openSDK_LOG.Util", sb4.toString());
                                }
                                if (openFileDescriptor != null) {
                                    try {
                                        openFileDescriptor.close();
                                    }
                                    catch (final IOException openFileDescriptor) {
                                        ex = (IOException)new StringBuilder();
                                        ((StringBuilder)ex).append("close ParcelFileDescriptor error");
                                        ((StringBuilder)ex).append(((IOException)openFileDescriptor).getMessage());
                                        SLog.e("openSDK_LOG.Util", ((StringBuilder)ex).toString());
                                    }
                                }
                                return (String)absolutePath;
                            }
                            catch (final Exception parcelFileDescriptor) {}
                        }
                        catch (final Exception ex5) {
                            parcelFileDescriptor = null;
                            openFileDescriptor = (ParcelFileDescriptor)ex5;
                        }
                        finally {
                            o = null;
                        }
                    }
                    catch (final Exception ex6) {
                        o = null;
                    }
                }
                catch (final Exception o2) {
                    ex = null;
                    query = (absolutePath = null);
                }
                finally {
                    absolutePath = null;
                    o2 = null;
                    query = parcelFileDescriptor;
                    break Label_0792;
                }
                try {
                    final StringBuilder sb5 = new StringBuilder();
                    sb5.append("copy file from uri error : ");
                    sb5.append(((Exception)o2).getMessage());
                    SLog.e("openSDK_LOG.Util", sb5.toString());
                    if (absolutePath != null) {
                        try {
                            ((FileInputStream)absolutePath).close();
                        }
                        catch (final IOException ex7) {
                            absolutePath = (IOException)new StringBuilder();
                            ((StringBuilder)absolutePath).append("close fileIuputStream error");
                            ((StringBuilder)absolutePath).append(ex7.getMessage());
                            SLog.e("openSDK_LOG.Util", ((StringBuilder)absolutePath).toString());
                        }
                    }
                    if (ex != null) {
                        try {
                            ((FileOutputStream)ex).close();
                        }
                        catch (final IOException absolutePath) {
                            ex = (IOException)new StringBuilder();
                            ((StringBuilder)ex).append("close fileOutputStream error");
                            ((StringBuilder)ex).append(absolutePath.getMessage());
                            SLog.e("openSDK_LOG.Util", ((StringBuilder)ex).toString());
                        }
                    }
                    if (query != null) {
                        try {
                            ((ParcelFileDescriptor)query).close();
                        }
                        catch (final IOException ex) {
                            absolutePath = (IOException)new StringBuilder();
                            ((StringBuilder)absolutePath).append("close ParcelFileDescriptor error");
                            ((StringBuilder)absolutePath).append(ex.getMessage());
                            SLog.e("openSDK_LOG.Util", ((StringBuilder)absolutePath).toString());
                        }
                    }
                    return null;
                }
                finally {
                    o = ex;
                    final IOException ex8;
                    ex = ex8;
                    o2 = query;
                }
            }
            query = absolutePath;
            absolutePath = (IOException)o;
        }
        if (query != null) {
            try {
                ((FileInputStream)query).close();
            }
            catch (final IOException ex9) {
                final StringBuilder sb6 = new StringBuilder();
                sb6.append("close fileIuputStream error");
                sb6.append(ex9.getMessage());
                SLog.e("openSDK_LOG.Util", sb6.toString());
            }
        }
        if (absolutePath != null) {
            try {
                ((FileOutputStream)absolutePath).close();
            }
            catch (final IOException ex10) {
                final StringBuilder sb7 = new StringBuilder();
                sb7.append("close fileOutputStream error");
                sb7.append(ex10.getMessage());
                SLog.e("openSDK_LOG.Util", sb7.toString());
            }
        }
        if (o2 != null) {
            try {
                ((ParcelFileDescriptor)o2).close();
            }
            catch (final IOException ex11) {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append("close ParcelFileDescriptor error");
                sb8.append(ex11.getMessage());
                SLog.e("openSDK_LOG.Util", sb8.toString());
            }
        }
        throw ex;
    }
    
    public static String c(final Context context, final String s) {
        if (context == null) {
            return "";
        }
        b(context, s);
        return k.b;
    }
    
    public static JSONObject c(final String s) {
        final String replace = s.replace((CharSequence)"auth://", (CharSequence)"http://");
        try {
            final URL url = new URL(replace);
            final JSONObject a = a((JSONObject)null, url.getQuery());
            a(a, url.getRef());
            return a;
        }
        catch (final MalformedURLException ex) {
            return new JSONObject();
        }
    }
    
    public static boolean c() {
        final Context a = com.tencent.open.utils.f.a();
        boolean b = false;
        if (a != null) {
            b = b;
            if (a.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", a.getPackageName()) == 0) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean c(final Context context) {
        double n = 0.0;
        try {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Math.sqrt(Math.pow((double)(displayMetrics.widthPixels / displayMetrics.xdpi), 2.0) + Math.pow((double)(displayMetrics.heightPixels / displayMetrics.ydpi), 2.0));
        }
        finally {
            n = 0.0;
        }
        return n > 6.5;
    }
    
    public static String d(final Context context, final String s) {
        if (context == null) {
            return "";
        }
        b(context, s);
        return k.a;
    }
    
    public static JSONObject d(String trim) throws JSONException {
        String s = trim;
        if (trim.equals((Object)"false")) {
            s = "{value : false}";
        }
        String s2 = s;
        if (s.equals((Object)"true")) {
            s2 = "{value : true}";
        }
        trim = s2;
        if (s2.contains((CharSequence)"allback(")) {
            trim = s2.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        String string = trim;
        if (trim.contains((CharSequence)"online[0]=")) {
            final StringBuilder sb = new StringBuilder();
            sb.append("{online:");
            sb.append(trim.charAt(trim.length() - 2));
            sb.append("}");
            string = sb.toString();
        }
        return new JSONObject(string);
    }
    
    public static boolean d(final Context context) {
        return i.c(context, "8.1.5") >= 0;
    }
    
    public static String e(final Context context, final String s) {
        if (context == null) {
            return "";
        }
        return k.c = d(context, s);
    }
    
    public static boolean e(final Context context) {
        return i.c(context, "8.1.8") >= 0;
    }
    
    public static boolean e(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static String f(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (final UnsupportedEncodingException ex) {
            SLog.e("openSDK_LOG.Util", "urlEncode: UnsupportedEncodingException", (Throwable)ex);
            return "";
        }
    }
    
    public static boolean f(final Context context) {
        return i.c(context, "5.9.5") >= 0 || i.a(context, "com.tencent.qqlite") != null;
    }
    
    public static boolean f(final Context context, final String s) {
        final boolean c = c(context);
        final boolean b = true;
        boolean b3;
        final boolean b2 = b3 = (!c || i.a(context, "com.tencent.minihd.qq") == null);
        if (b2) {
            b3 = b2;
            if (i.a(context, "com.tencent.tim") != null) {
                b3 = false;
            }
        }
        boolean b4;
        if (b4 = b3) {
            b4 = b3;
            if (i.a(context, "com.tencent.qqlite") != null) {
                b4 = false;
            }
        }
        boolean b5;
        if (b5 = b4) {
            b5 = (i.c(context, s) < 0 && b);
        }
        return b5;
    }
    
    public static String g(final String s) {
        String string;
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(j(s));
            final byte[] digest = instance.digest();
            string = s;
            if (digest != null) {
                final StringBuilder sb = new StringBuilder();
                for (final byte b : digest) {
                    sb.append(a(b >>> 4));
                    sb.append(a(b));
                }
                string = sb.toString();
            }
        }
        catch (final NoSuchAlgorithmException ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("encrypt has exception: ");
            sb2.append(ex.getMessage());
            SLog.e("openSDK_LOG.Util", sb2.toString());
            string = s;
        }
        return string;
    }
    
    private static boolean g(final Context context) {
        try {
            final PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
            final String versionName = packageInfo.versionName;
            if (i.a(versionName, "4.3") >= 0 && !versionName.startsWith("4.4")) {
                final Signature[] signatures = packageInfo.signatures;
                if (signatures != null) {
                    try {
                        final MessageDigest instance = MessageDigest.getInstance("MD5");
                        instance.update(signatures[0].toByteArray());
                        final String a = a(instance.digest());
                        instance.reset();
                        if (a.equals((Object)"d8391a394d4a179e6fe7bdb8a301258b")) {
                            return true;
                        }
                    }
                    catch (final NoSuchAlgorithmException ex) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("isQQBrowerAvailable has exception: ");
                        sb.append(ex.getMessage());
                        SLog.e("openSDK_LOG.Util", sb.toString());
                    }
                }
            }
            return false;
        }
        catch (final PackageManager$NameNotFoundException ex2) {
            return false;
        }
    }
    
    public static boolean g(final Context context, final String s) {
        final boolean c = c(context);
        final boolean b = true;
        boolean b3;
        final boolean b2 = b3 = (!c || i.a(context, "com.tencent.minihd.qq") == null);
        if (b2) {
            b3 = b2;
            if (i.a(context, "com.tencent.qqlite") != null) {
                b3 = false;
            }
        }
        boolean b4;
        if (b4 = b3) {
            b4 = (i.c(context, s) < 0 && b);
        }
        return b4;
    }
    
    public static File h(final Context context, final String s) {
        final File file = null;
        if (context == null) {
            return null;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            final File[] externalFilesDirs = context.getExternalFilesDirs(s);
            File file2 = file;
            if (externalFilesDirs != null) {
                file2 = file;
                if (externalFilesDirs.length > 0) {
                    file2 = externalFilesDirs[0];
                }
            }
            return file2;
        }
        return context.getExternalFilesDir(s);
    }
    
    public static final boolean h(final String s) {
        boolean b = false;
        if (s == null) {
            return false;
        }
        if (s.startsWith("http://") || s.startsWith("https://")) {
            b = true;
        }
        return b;
    }
    
    public static boolean i(final String s) {
        return s != null && new File(s).exists();
    }
    
    public static byte[] j(final String s) {
        try {
            return s.getBytes("UTF-8");
        }
        catch (final UnsupportedEncodingException ex) {
            SLog.e("openSDK_LOG.Util", "getBytesUTF8: UnsupportedEncodingException", (Throwable)ex);
            return new byte[0];
        }
    }
    
    public static String k(final String s) {
        if (s == null) {
            return null;
        }
        return Base64.encodeToString(a(s.getBytes(), "JCPTZXEZ"), 3);
    }
    
    public static String l(final String s) {
        return a(s, 2);
    }
    
    public static File m(final String s) throws IOException {
        final File file = new File(s);
        if (!file.exists()) {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    file.createNewFile();
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("createFile failed");
                    sb.append(s);
                    SLog.d("openSDK_LOG.Util", sb.toString());
                }
            }
            else {
                file.createNewFile();
            }
        }
        return file;
    }
    
    public static boolean n(final String s) {
        final String b = b();
        return !TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)b) && s.contains((CharSequence)b);
    }
}
