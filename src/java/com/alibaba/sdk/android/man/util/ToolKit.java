package com.alibaba.sdk.android.man.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import java.util.regex.Pattern;

public class ToolKit
{
    private static final String TAG = "MAN_ToolKit";
    private static Pattern patternHost;
    private static Pattern patternIp;
    private static final String validHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    private static final String validIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    
    static {
        ToolKit.patternIp = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
        ToolKit.patternHost = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");
    }
    
    public static Object checkNotNull(final Object o, final Object o2) {
        if (o != null) {
            return o;
        }
        throw new IllegalArgumentException(String.valueOf(o2));
    }
    
    public static long convertStr2Long(final String s) {
        try {
            return Long.valueOf(s);
        }
        catch (final NumberFormatException ex) {
            return 0L;
        }
    }
    
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }
    
    public static String getMetaDataAppKey(Context string) {
        final Object o = "";
        if (string == null) {
            return "";
        }
        Object o2 = null;
        Label_0130: {
            try {
                final ApplicationInfo applicationInfo = string.getPackageManager().getApplicationInfo(string.getPackageName(), 128);
                Label_0115: {
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        final Object value = applicationInfo.metaData.get("com.alibaba.app.appkey");
                        if (!(value instanceof String) && value != null) {
                            string = (Context)value.toString();
                        }
                        else {
                            string = (Context)value;
                        }
                        try {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("appKey : ");
                            sb.append((String)string);
                            MANLog.Logi("MAN_ToolKit", sb.toString());
                            break Label_0115;
                        }
                        catch (final PackageManager$NameNotFoundException o) {
                            o2 = o;
                            break Label_0130;
                        }
                    }
                    string = (Context)"";
                }
                if (string == null) {
                    string = (Context)o;
                    return (String)string;
                }
                return (String)string;
            }
            catch (final PackageManager$NameNotFoundException o2) {
                string = (Context)o;
            }
        }
        ((PackageManager$NameNotFoundException)o2).printStackTrace();
        return (String)string;
    }
    
    public static String getMetaDataAppSecret(Context string) {
        final Object o = "";
        if (string == null) {
            return "";
        }
        Object o2 = null;
        Label_0130: {
            try {
                final ApplicationInfo applicationInfo = string.getPackageManager().getApplicationInfo(string.getPackageName(), 128);
                Label_0115: {
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        final Object value = applicationInfo.metaData.get("com.alibaba.app.appsecret");
                        if (!(value instanceof String) && value != null) {
                            string = (Context)value.toString();
                        }
                        else {
                            string = (Context)value;
                        }
                        try {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("appSecret : ");
                            sb.append((String)string);
                            MANLog.Logi("MAN_ToolKit", sb.toString());
                            break Label_0115;
                        }
                        catch (final PackageManager$NameNotFoundException o) {
                            o2 = o;
                            break Label_0130;
                        }
                    }
                    string = (Context)"";
                }
                if (string == null) {
                    string = (Context)o;
                    return (String)string;
                }
                return (String)string;
            }
            catch (final PackageManager$NameNotFoundException o2) {
                string = (Context)o;
            }
        }
        ((PackageManager$NameNotFoundException)o2).printStackTrace();
        return (String)string;
    }
    
    public static String getMetaDataAppVersion(final Context context) {
        String versionName;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        }
        catch (final PackageManager$NameNotFoundException ex) {
            versionName = "Unknown";
        }
        String s = versionName;
        if (isNullOrEmpty(versionName)) {
            s = "-";
        }
        return s;
    }
    
    public static String getMetaDataChannel(Context string) {
        final Object o = "";
        if (string == null) {
            return "";
        }
        Label_0128: {
            try {
                final ApplicationInfo applicationInfo = string.getPackageManager().getApplicationInfo(string.getPackageName(), 128);
                Label_0113: {
                    if (applicationInfo != null && applicationInfo.metaData != null) {
                        final Object value = applicationInfo.metaData.get("ALIYUN_MAN_CHANNEL");
                        if (!(value instanceof String) && value != null) {
                            string = (Context)value.toString();
                        }
                        else {
                            string = (Context)value;
                        }
                        try {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("channel : ");
                            sb.append((String)string);
                            MANLog.Logi("MAN_ToolKit", sb.toString());
                            break Label_0113;
                        }
                        catch (final PackageManager$NameNotFoundException ex) {
                            break Label_0128;
                        }
                    }
                    string = (Context)"";
                }
                if (string == null) {
                    string = (Context)o;
                    return (String)string;
                }
                return (String)string;
            }
            catch (final PackageManager$NameNotFoundException ex) {
                string = (Context)o;
            }
        }
        final PackageManager$NameNotFoundException ex;
        ex.printStackTrace();
        return (String)string;
    }
    
    public static boolean isHost(final String s) {
        return s != null && !s.isEmpty() && ToolKit.patternHost.matcher((CharSequence)s).matches();
    }
    
    public static boolean isIp(final String s) {
        return s != null && !s.isEmpty() && ToolKit.patternIp.matcher((CharSequence)s).matches();
    }
    
    public static boolean isNullOrEmpty(final String s) {
        return s == null || s.length() == 0;
    }
}
