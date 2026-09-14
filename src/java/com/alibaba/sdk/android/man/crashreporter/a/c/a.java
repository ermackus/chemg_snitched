package com.alibaba.sdk.android.man.crashreporter.a.c;

import java.util.Map;
import android.app.ActivityManager$MemoryInfo;
import java.io.File;
import android.os.StatFs;
import android.os.Environment;
import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import android.content.Context;

public class a
{
    private static final int s = -1;
    private static final int t = 0;
    private static final int u = 1;
    private static int v = -1;
    
    public static double a(final Context context) {
        try {
            final BufferedReader bufferedReader = new BufferedReader((Reader)new FileReader("/proc/meminfo"), 8192);
            final String line = bufferedReader.readLine();
            final String[] split = line.split("\\s+");
            for (final String s : split) {
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\t");
                com.alibaba.sdk.android.man.crashreporter.b.a.c(line, sb.toString());
            }
            final long n = Integer.valueOf(split[1]);
            bufferedReader.close();
            return (double)(n / 1024L);
        }
        catch (final IOException ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getTotalMemory error.", (Throwable)ex);
            return -1.0;
        }
    }
    
    public static String a(final Context context) {
        if (context != null) {
            try {
                final int myPid = Process.myPid();
                final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                if (activityManager != null) {
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                        if (activityManager$RunningAppProcessInfo.pid == myPid) {
                            return activityManager$RunningAppProcessInfo.processName;
                        }
                    }
                    return null;
                }
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("getCurProcessName error.", (Throwable)ex);
            }
        }
        return null;
    }
    
    public static boolean a(final Context context) {
        try {
            Block_3: {
                for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses()) {
                    if (activityManager$RunningAppProcessInfo.processName.equals((Object)context.getPackageName())) {
                        break Block_3;
                    }
                }
                return false;
            }
            final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo;
            if (activityManager$RunningAppProcessInfo.importance == 400) {
                com.alibaba.sdk.android.man.crashreporter.b.a.b("app is background :", activityManager$RunningAppProcessInfo.processName);
                return true;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.b("app is foreground:", activityManager$RunningAppProcessInfo.processName);
            return false;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("isBackgroundRunning  error.", (Throwable)ex);
        }
        return false;
    }
    
    public static long[] a(final boolean b) {
        final long[] array2;
        final long[] array = array2 = new long[3];
        array2[0] = -1L;
        array2[2] = (array2[1] = -1L);
        while (true) {
            if (b) {
                try {
                    File file = Environment.getDataDirectory();
                    while (true) {
                        if (file != null) {
                            final StatFs statFs = new StatFs(file.getPath());
                            final int blockSize = statFs.getBlockSize();
                            final int blockCount = statFs.getBlockCount();
                            final int freeBlocks = statFs.getFreeBlocks();
                            final int availableBlocks = statFs.getAvailableBlocks();
                            array[0] = blockCount * blockSize;
                            array[1] = freeBlocks * blockSize;
                            array[2] = blockSize * availableBlocks;
                            return array;
                        }
                        return array;
                        file = Environment.getExternalStorageDirectory();
                        continue;
                    }
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("getStorageSize error.", (Throwable)ex);
                }
                return array;
            }
            continue;
        }
    }
    
    public static double b(final Context context) {
        final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
        try {
            ((ActivityManager)context.getSystemService("activity")).getMemoryInfo(activityManager$MemoryInfo);
            return (double)(activityManager$MemoryInfo.availMem / 1024L / 1024L);
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getAvailMemory error.", (Throwable)ex);
            return -1.0;
        }
    }
    
    public static String b(final Context context) {
        String processName;
        try {
            processName = ((ActivityManager$RunningAppProcessInfo)((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses().get(0)).processName;
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getRunningActivityName error.", (Throwable)ex);
            processName = "";
        }
        return processName;
    }
    
    public static String b(final Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            String substring;
            String string = substring = "{";
            String string2;
            try {
                final Iterator iterator = map.keySet().iterator();
                while (true) {
                    substring = string;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    substring = string;
                    final Object next = iterator.next();
                    substring = string;
                    final String s = (String)map.get(next);
                    if (s == null) {
                        continue;
                    }
                    substring = string;
                    substring = string;
                    final StringBuilder sb = new StringBuilder();
                    substring = string;
                    sb.append(string);
                    substring = string;
                    sb.append("\"");
                    substring = string;
                    sb.append(next);
                    substring = string;
                    sb.append("\":\"");
                    substring = string;
                    sb.append(s);
                    substring = string;
                    sb.append("\",");
                    substring = string;
                    string = sb.toString();
                }
                substring = string;
                final String s2 = substring = (substring = string.substring(0, string.length() - 1));
                final StringBuilder sb2 = new StringBuilder();
                substring = s2;
                sb2.append(s2);
                substring = s2;
                sb2.append("}");
                substring = s2;
                string2 = sb2.toString();
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("simpleMapToJsonStr error.", (Throwable)ex);
                string2 = substring;
            }
            return string2;
        }
        return "null";
    }
    
    public static boolean b() {
        final int v = a.v;
        if (v == 1) {
            return true;
        }
        if (v == 0) {
            return false;
        }
        int i = 0;
        while (i < 5) {
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append((new String[] { "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/" })[i]);
                sb.append("su");
                if (new File(sb.toString()).exists()) {
                    a.v = 1;
                    return true;
                }
                ++i;
                continue;
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("isRootSystem error.", (Throwable)ex);
            }
            break;
        }
        a.v = 0;
        return false;
    }
    
    public static boolean b(final Context context) {
        try {
            if ((context.getApplicationInfo().flags & 0x40000) != 0x0) {
                return true;
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("isInstallOnSDCard error.", (Throwable)ex);
        }
        return false;
    }
    
    public static String c(final Context context) {
        try {
            if (com.alibaba.sdk.android.man.crashreporter.e.a.d(context) && com.alibaba.sdk.android.man.crashreporter.e.a.e(context)) {
                return com.alibaba.sdk.android.man.crashreporter.e.a.c(context);
            }
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getWifiIpAddress error.", (Throwable)ex);
        }
        return "127.0.0.1";
    }
    
    public static String getExternalStorageState() {
        try {
            return Environment.getExternalStorageState();
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("hasSDCard error.", (Throwable)ex);
            return "unknown";
        }
    }
}
