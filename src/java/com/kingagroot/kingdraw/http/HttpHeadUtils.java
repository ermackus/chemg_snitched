package com.kingagroot.kingdraw.http;

import android.net.NetworkCapabilities;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import android.content.pm.PackageManager$NameNotFoundException;
import com.goodsrc.library.utils.SPUtil;
import java.util.Random;
import android.os.Build$VERSION;
import com.goodsrc.library.utils.AppUtil;
import android.os.Build;
import android.text.TextUtils;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Context;

public class HttpHeadUtils
{
    public static int shareVersionCode = 1;
    
    public static String cellularType(final Context context) {
        return "-";
    }
    
    public static String getAppChannel(final Context context) {
        return "-";
    }
    
    public static String getCurrentLanguage() {
        final String language = LibraryApplication.getLanguage();
        String s;
        if (language.equals((Object)LanguageTool.SER_ZH)) {
            s = "zh-cn";
        }
        else {
            s = language;
            if (language.equals((Object)LanguageTool.SER_EN)) {
                s = "en-us";
            }
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return s;
        }
        return "-";
    }
    
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    public static String getDeviceBrand() {
        return Build.BRAND;
    }
    
    public static String getNetConnectVersion() {
        return "-";
    }
    
    public static String getOldDeviceId() {
        if (TextUtils.isEmpty((CharSequence)AppUtil.getImei(LibraryApplication.getContext()))) {
            return "-";
        }
        return AppUtil.getImei(LibraryApplication.getContext());
    }
    
    public static String getPhoneInfo() {
        return "-";
    }
    
    public static int getPhoneSdk() {
        return Build$VERSION.SDK_INT;
    }
    
    public static String getPlatformType() {
        if (isHarmonyOs()) {
            return "3";
        }
        return "2";
    }
    
    public static String getRandomString(final int n) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36)));
        }
        return sb.toString();
    }
    
    public static String getSystemModel() {
        return Build.MODEL;
    }
    
    public static String getSystemVersion() {
        return Build$VERSION.RELEASE;
    }
    
    public static String getUserDeviceId() {
        return SPUtil.getString("USER_DEVICE", "uuid", "-");
    }
    
    public static String getVerName(final Context context) {
        String versionName;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            versionName = "";
        }
        return versionName;
    }
    
    public static int getVersionCode(final Context context) {
        try {
            if (Build$VERSION.SDK_INT >= 28) {
                return (int)context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
            }
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (final Exception ex) {
            return 0;
        }
    }
    
    public static int getVersionType() {
        if (AppConfig.RELEASE != Release.STANDARD) {
            if (AppConfig.RELEASE == Release.EARLY) {
                return 1;
            }
            if (AppConfig.RELEASE == Release.TEST) {
                return 2;
            }
            if (AppConfig.RELEASE == Release.DEBUG) {
                return 3;
            }
        }
        return 0;
    }
    
    private static boolean isHarmonyOs() {
        final boolean b = false;
        try {
            final Class<?> forName = Class.forName("com.huawei.system.BuildEx");
            final Object invoke = forName.getMethod("getOsBrand", (Class[])new Class[0]).invoke((Object)forName, new Object[0]);
            String string;
            if (invoke != null) {
                string = invoke.toString();
            }
            else {
                string = null;
            }
            return "harmony".equalsIgnoreCase(string);
        }
        finally {
            return b;
        }
    }
    
    public static String netConnectType(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager != null) {
            if (Build$VERSION.SDK_INT < 23) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.getType() == 1) {
                        return "1";
                    }
                    if (activeNetworkInfo.getType() == 0) {
                        return "2";
                    }
                }
            }
            else {
                final Network activeNetwork = connectivityManager.getActiveNetwork();
                if (activeNetwork != null) {
                    final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                    if (networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(1)) {
                            return "1";
                        }
                        if (networkCapabilities.hasTransport(0)) {
                            return "2";
                        }
                    }
                }
            }
        }
        return "-";
    }
    
    public static void setUserDeviceId(final String s) {
        SPUtil.setString("USER_DEVICE", "uuid", s);
    }
}
