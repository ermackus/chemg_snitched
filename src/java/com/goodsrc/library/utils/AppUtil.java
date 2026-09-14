package com.goodsrc.library.utils;

import java.util.Map;
import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build$VERSION;
import java.util.UUID;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.Map$Entry;
import java.util.HashMap;
import android.os.Build;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import android.preference.PreferenceManager;
import com.goodsrc.library.core.LibraryApplication;
import android.text.TextUtils;

public class AppUtil
{
    private static String IMEI = "";
    private static final String SP_KEY_IMEI = "sp_key_imei";
    
    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    private static String findImei() {
        final String shareImie = findShareImie();
        if (!TextUtils.isEmpty((CharSequence)shareImie)) {}
        return shareImie;
    }
    
    private static String findShareImie() {
        return PreferenceManager.getDefaultSharedPreferences(LibraryApplication.getContext()).getString("sp_key_imei", "").trim();
    }
    
    public static String getAppName(final Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String getDeviceBrand() {
        return Build.BRAND;
    }
    
    public static String getHardwareinfo() {
        final HashMap hashMap = new HashMap();
        for (final Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                ((Map)hashMap).put((Object)field.getName(), (Object)field.get((Object)null).toString());
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        final StringBuffer sb = new StringBuffer();
        for (final Map$Entry map$Entry : ((Map)hashMap).entrySet()) {
            final String s = (String)map$Entry.getKey();
            final String s2 = (String)map$Entry.getValue();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s);
            sb2.append("=");
            sb2.append(s2);
            sb2.append("\n");
            sb.append(sb2.toString());
        }
        return sb.toString();
    }
    
    public static String getImei(final Context context) {
        if (TextUtils.isEmpty((CharSequence)AppUtil.IMEI)) {
            if (TextUtils.isEmpty((CharSequence)(AppUtil.IMEI = findImei()))) {
                saveImei(AppUtil.IMEI = UUID.randomUUID().toString().trim());
            }
            else if (TextUtils.isEmpty((CharSequence)findShareImie())) {
                saveImeiToShare(AppUtil.IMEI);
            }
        }
        return AppUtil.IMEI;
    }
    
    public static int getPhoneSdk() {
        return Build$VERSION.SDK_INT;
    }
    
    public static String getSystemModel() {
        return Build.MODEL;
    }
    
    public static int getVersionCode(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static String getVersionName(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static boolean isBasicApp(final Context context) {
        return context.getPackageName().equals((Object)"com.goodsrc.qyngbasic");
    }
    
    private static boolean isIntentSafe(final Context context, final Uri uri) {
        final Intent intent = new Intent("android.intent.action.VIEW", uri);
        final PackageManager packageManager = context.getPackageManager();
        boolean b = false;
        if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
            b = true;
        }
        return b;
    }
    
    public static boolean openAppMarket(final Context context) {
        if (TextUtils.isEmpty((CharSequence)context.getPackageName())) {
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(context.getPackageName());
        final Uri parse = Uri.parse(sb.toString());
        try {
            if (isIntentSafe(context, parse)) {
                context.startActivity(new Intent("android.intent.action.VIEW", parse));
                return true;
            }
        }
        catch (final ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private static void saveImei(final String s) {
        saveImeiToShare(s);
    }
    
    private static void saveImeiToShare(final String s) {
        PreferenceManager.getDefaultSharedPreferences(LibraryApplication.getContext()).edit().putString("sp_key_imei", s).commit();
    }
}
