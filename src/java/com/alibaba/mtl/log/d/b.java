package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import com.alibaba.mtl.log.a;
import android.os.PowerManager;
import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import android.content.Context;

public class b
{
    private static String ai = "";
    private static String g;
    
    public static String a(final Context context) {
        if (context == null) {
            return "";
        }
        final int myPid = Process.myPid();
        for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses()) {
            if (activityManager$RunningAppProcessInfo.pid == myPid) {
                return activityManager$RunningAppProcessInfo.processName;
            }
        }
        return null;
    }
    
    public static boolean b(final Context context) {
        if (context == null) {
            return false;
        }
        try {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            final PowerManager powerManager = (PowerManager)context.getSystemService("power");
            final String packageName = context.getPackageName();
            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (activityManager$RunningAppProcessInfo.processName.equals((Object)packageName)) {
                    if (activityManager$RunningAppProcessInfo.importance == 400) {
                        return false;
                    }
                    if (powerManager.isScreenOn()) {
                        return true;
                    }
                    continue;
                }
            }
            return false;
        }
        finally {
            return false;
        }
    }
    
    public static String getAppkey() {
        return b.g;
    }
    
    public static String k() {
        final Context context = a.getContext();
        final String s = "";
        if (context == null) {
            return "";
        }
        try {
            final String string = a.getContext().getSharedPreferences("UTCommon", 0).getString("_lun", "");
            String s2 = s;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                s2 = new String(c.decode(string.getBytes(), 2), "UTF-8");
            }
            return s2;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    public static String l() {
        final Context context = a.getContext();
        final String s = "";
        if (context == null) {
            return "";
        }
        try {
            final String string = a.getContext().getSharedPreferences("UTCommon", 0).getString("_luid", "");
            String s2 = s;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                s2 = new String(c.decode(string.getBytes(), 2), "UTF-8");
            }
            return s2;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    public static String m() {
        return b.ai;
    }
    
    public static String n() {
        return "";
    }
    
    public static String o() {
        return "";
    }
    
    public static void o(final String ai) {
        i.a("AppInfoUtil", new Object[] { "[setChannle]", ai });
        if (!TextUtils.isEmpty((CharSequence)ai)) {
            final int index = ai.indexOf("@");
            if (index == -1) {
                b.ai = ai;
            }
            else {
                b.ai = ai.substring(0, index);
            }
        }
    }
    
    public static void p(final String g) {
        i.a("AppInfoUtil", new Object[] { "set Appkey:", g });
        b.g = g;
    }
}
