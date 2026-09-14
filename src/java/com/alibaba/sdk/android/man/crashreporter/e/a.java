package com.alibaba.sdk.android.man.crashreporter.e;

import java.util.Random;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import android.telephony.TelephonyManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo$State;
import android.net.ConnectivityManager;
import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import android.content.Context;
import android.os.Build$VERSION;

public class a
{
    public static int a() {
        int n;
        try {
            n = Build$VERSION.class.getField("SDK_INT").getInt((Object)null);
        }
        catch (final IllegalAccessException ex) {
            n = Integer.parseInt(Build$VERSION.SDK);
        }
        catch (final IllegalArgumentException ex2) {
            n = Integer.parseInt(Build$VERSION.SDK);
        }
        catch (final NoSuchFieldException ex3) {
            n = Integer.parseInt(Build$VERSION.SDK);
        }
        catch (final SecurityException ex4) {
            n = Integer.parseInt(Build$VERSION.SDK);
        }
        return n;
    }
    
    public static String a(final Context context) {
        Label_0066: {
            if (context == null) {
                break Label_0066;
            }
            try {
                final int myPid = Process.myPid();
                final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                if (activityManager != null) {
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                        if (activityManager$RunningAppProcessInfo.pid == myPid) {
                            return activityManager$RunningAppProcessInfo.processName;
                        }
                    }
                }
                return null;
            }
            catch (final Exception ex) {
                return null;
            }
        }
    }
    
    public static String[] a(final Context context) {
        final String[] array = { "Unknown", "Unknown" };
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0) {
                final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                    if (networkInfo != null && networkInfo.getState() == NetworkInfo$State.CONNECTED) {
                        array[0] = "Wi-Fi";
                        return array;
                    }
                    final NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                    if (networkInfo2.getState() == NetworkInfo$State.CONNECTED) {
                        array[0] = "2G/3G";
                        array[1] = networkInfo2.getSubtypeName();
                    }
                }
            }
            return array;
        }
        catch (final Exception ex) {
            return array;
        }
    }
    
    public static String c(final Context context) {
        try {
            final WifiInfo connectionInfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                final int ipAddress = connectionInfo.getIpAddress();
                final StringBuilder sb = new StringBuilder();
                sb.append(ipAddress & 0xFF);
                sb.append("");
                sb.append(ipAddress >> 8 & 0xFF);
                sb.append(".");
                sb.append(ipAddress >> 16 & 0xFF);
                sb.append(".");
                sb.append(ipAddress >> 24 & 0xFF);
                return sb.toString();
            }
            return "127.0.0.1";
        }
        catch (final Exception ex) {
            return "127.0.0.1";
        }
    }
    
    public static boolean d(final Context context) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager != null) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isAvailable();
                }
            }
            return false;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static String e(final Context context) {
        String deviceId;
        final String s = deviceId = null;
        if (context != null) {
            try {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                deviceId = s;
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
            }
            catch (final Exception ex) {
                deviceId = s;
            }
        }
        String k = deviceId;
        if (i.b(deviceId)) {
            k = k();
        }
        return k;
    }
    
    public static boolean e(final Context context) {
        return a(context)[0].equals((Object)"Wi-Fi");
    }
    
    public static String f() {
        String line;
        final String s = line = null;
        try {
            line = s;
            final FileReader fileReader = new FileReader("/proc/cpuinfo");
            line = s;
            line = s;
            final BufferedReader bufferedReader = new BufferedReader((Reader)fileReader, 1024);
            line = s;
            final String s2 = line = bufferedReader.readLine();
            bufferedReader.close();
            line = s2;
            fileReader.close();
            line = s2;
        }
        catch (final FileNotFoundException | IOException ex) {}
        if (line != null) {
            return line.substring(line.indexOf(58) + 1).trim();
        }
        return "";
    }
    
    public static String f(final Context context) {
        String subscriberId;
        final String s = subscriberId = null;
        if (context != null) {
            try {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                subscriberId = s;
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                }
            }
            catch (final Exception ex) {
                subscriberId = s;
            }
        }
        String k = subscriberId;
        if (i.b(subscriberId)) {
            k = k();
        }
        return k;
    }
    
    public static String g(final Context context) {
        try {
            final PackageManager packageManager = context.getPackageManager();
            final String packageName = context.getPackageName();
            if (packageManager != null && packageName != null) {
                return packageManager.getApplicationLabel(packageManager.getPackageInfo(packageName, 1).applicationInfo).toString();
            }
            return "";
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    public static String h(final Context context) {
        try {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            if (wifiManager != null) {
                final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    final String macAddress = connectionInfo.getMacAddress();
                    if (i.b((CharSequence)macAddress)) {
                        return macAddress;
                    }
                }
            }
            return "00-00-00-00-00-00";
        }
        catch (final Exception ex) {
            return "00-00-00-00-00-00";
        }
    }
    
    public static String i(final Context context) {
        try {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            final int widthPixels = displayMetrics.widthPixels;
            int heightPixels;
            final int n = heightPixels = displayMetrics.heightPixels;
            int n2 = widthPixels;
            if (widthPixels > n) {
                final int n3 = widthPixels ^ n;
                heightPixels = (n ^ n3);
                n2 = (n3 ^ heightPixels);
            }
            return String.format("%s*%s", new Object[] { heightPixels, n2 });
        }
        catch (final Exception ex) {
            return "Unknown";
        }
    }
    
    public static final String k() {
        String a;
        try {
            final int n = (int)(System.currentTimeMillis() / 1000L);
            final int n2 = (int)System.nanoTime();
            final int nextInt = new Random().nextInt();
            final int nextInt2 = new Random().nextInt();
            final byte[] d = c.d(n);
            final byte[] d2 = c.d(n2);
            final byte[] d3 = c.d(nextInt);
            final byte[] d4 = c.d(nextInt2);
            final byte[] array = new byte[16];
            System.arraycopy((Object)d, 0, (Object)array, 0, 4);
            System.arraycopy((Object)d2, 0, (Object)array, 4, 4);
            System.arraycopy((Object)d3, 0, (Object)array, 8, 4);
            System.arraycopy((Object)d4, 0, (Object)array, 12, 4);
            a = b.a(array, 2);
        }
        catch (final IOException ex) {
            a = "";
        }
        return a;
    }
}
