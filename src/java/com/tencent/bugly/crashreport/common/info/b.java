package com.tencent.bugly.crashreport.common.info;

import java.lang.reflect.Method;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import android.os.StatFs;
import android.os.Environment;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.net.ConnectivityManager;
import android.os.Build$VERSION;
import com.tencent.bugly.proguard.z;
import android.provider.Settings$Secure;
import android.content.Context;
import com.tencent.bugly.proguard.x;
import android.os.Build;

public class b
{
    private static final String[] a;
    private static final String[] b;
    private static final String[] c;
    
    static {
        a = new String[] { "/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb" };
        b = new String[] { "com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter" };
        c = new String[] { "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd" };
    }
    
    public static String a() {
        try {
            return Build.MODEL;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return "fail";
        }
    }
    
    public static String a(final Context context) {
        String s = "fail";
        if (context == null) {
            return "fail";
        }
        String s2 = null;
        try {
            final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                s = string;
                string.toLowerCase();
            }
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                x.a("Failed to get Android ID.", new Object[0]);
            }
            s2 = s;
        }
        return s2;
    }
    
    public static String a(final Context context, final boolean b) {
        String s2;
        final String s = s2 = null;
        Label_0114: {
            if (!b) {
                break Label_0114;
            }
            try {
                final String a = z.a(context, "ro.product.cpu.abilist");
                String a2 = null;
                Label_0042: {
                    if (!z.a(a)) {
                        a2 = a;
                        if (!a.equals((Object)"fail")) {
                            break Label_0042;
                        }
                    }
                    a2 = z.a(context, "ro.product.cpu.abi");
                }
                s2 = s;
                if (z.a(a2)) {
                    break Label_0114;
                }
                if (a2.equals((Object)"fail")) {
                    s2 = s;
                    break Label_0114;
                }
                final StringBuilder sb = new StringBuilder("ABI list: ");
                sb.append(a2);
                x.b(b.class, sb.toString(), new Object[0]);
                s2 = a2.split(",")[0];
                break Label_0114;
            }
            finally {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
                return "fail";
                while (true) {
                    final StringBuilder sb2 = new StringBuilder();
                    String property = null;
                    sb2.append(property);
                    return sb2.toString();
                    iftrue(Label_0127:)((property = s2) != null);
                    property = System.getProperty("os.arch");
                    continue;
                }
            }
        }
    }
    
    public static String b() {
        try {
            return Build$VERSION.RELEASE;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return "fail";
        }
    }
    
    public static String b(final Context context) {
        final String s = "unknown";
        String string;
        try {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                string = "WIFI";
            }
            else {
                string = s;
                if (activeNetworkInfo.getType() == 0) {
                    final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                    string = s;
                    if (telephonyManager != null) {
                        final int networkType = telephonyManager.getNetworkType();
                        switch (networkType) {
                            default: {
                                final StringBuilder sb = new StringBuilder("MOBILE(");
                                sb.append(networkType);
                                sb.append(")");
                                string = sb.toString();
                                break;
                            }
                            case 15: {
                                string = "HSPA+";
                                break;
                            }
                            case 14: {
                                string = "eHRPD";
                                break;
                            }
                            case 13: {
                                string = "LTE";
                                break;
                            }
                            case 12: {
                                string = "EVDO_B";
                                break;
                            }
                            case 11: {
                                string = "iDen";
                                break;
                            }
                            case 10: {
                                string = "HSPA";
                                break;
                            }
                            case 9: {
                                string = "HSUPA";
                                break;
                            }
                            case 8: {
                                string = "HSDPA";
                                break;
                            }
                            case 7: {
                                string = "1xRTT";
                                break;
                            }
                            case 6: {
                                string = "EVDO_A";
                                break;
                            }
                            case 5: {
                                string = "EVDO_0";
                                break;
                            }
                            case 4: {
                                string = "CDMA";
                                break;
                            }
                            case 3: {
                                string = "UMTS";
                                break;
                            }
                            case 2: {
                                string = "EDGE";
                                break;
                            }
                            case 1: {
                                string = "GPRS";
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            string = s;
            if (!x.a((Throwable)ex)) {
                ex.printStackTrace();
                string = s;
            }
        }
        return string;
    }
    
    public static int c() {
        try {
            return Build$VERSION.SDK_INT;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -1;
        }
    }
    
    public static String c(final Context context) {
        final String a = z.a(context, "ro.miui.ui.version.name");
        if (!z.a(a) && !a.equals((Object)"fail")) {
            final StringBuilder sb = new StringBuilder("XiaoMi/MIUI/");
            sb.append(a);
            return sb.toString();
        }
        final String a2 = z.a(context, "ro.build.version.emui");
        if (!z.a(a2) && !a2.equals((Object)"fail")) {
            final StringBuilder sb2 = new StringBuilder("HuaWei/EMOTION/");
            sb2.append(a2);
            return sb2.toString();
        }
        final String a3 = z.a(context, "ro.lenovo.series");
        if (!z.a(a3) && !a3.equals((Object)"fail")) {
            final String a4 = z.a(context, "ro.build.version.incremental");
            final StringBuilder sb3 = new StringBuilder("Lenovo/VIBE/");
            sb3.append(a4);
            return sb3.toString();
        }
        final String a5 = z.a(context, "ro.build.nubia.rom.name");
        if (!z.a(a5) && !a5.equals((Object)"fail")) {
            final StringBuilder sb4 = new StringBuilder("Zte/NUBIA/");
            sb4.append(a5);
            sb4.append("_");
            sb4.append(z.a(context, "ro.build.nubia.rom.code"));
            return sb4.toString();
        }
        final String a6 = z.a(context, "ro.meizu.product.model");
        if (!z.a(a6) && !a6.equals((Object)"fail")) {
            final StringBuilder sb5 = new StringBuilder("Meizu/FLYME/");
            sb5.append(z.a(context, "ro.build.display.id"));
            return sb5.toString();
        }
        final String a7 = z.a(context, "ro.build.version.opporom");
        if (!z.a(a7) && !a7.equals((Object)"fail")) {
            final StringBuilder sb6 = new StringBuilder("Oppo/COLOROS/");
            sb6.append(a7);
            return sb6.toString();
        }
        final String a8 = z.a(context, "ro.vivo.os.build.display.id");
        if (!z.a(a8) && !a8.equals((Object)"fail")) {
            final StringBuilder sb7 = new StringBuilder("vivo/FUNTOUCH/");
            sb7.append(a8);
            return sb7.toString();
        }
        final String a9 = z.a(context, "ro.aa.romver");
        if (!z.a(a9) && !a9.equals((Object)"fail")) {
            final StringBuilder sb8 = new StringBuilder("htc/");
            sb8.append(a9);
            sb8.append("/");
            sb8.append(z.a(context, "ro.build.description"));
            return sb8.toString();
        }
        final String a10 = z.a(context, "ro.lewa.version");
        if (!z.a(a10) && !a10.equals((Object)"fail")) {
            final StringBuilder sb9 = new StringBuilder("tcl/");
            sb9.append(a10);
            sb9.append("/");
            sb9.append(z.a(context, "ro.build.display.id"));
            return sb9.toString();
        }
        final String a11 = z.a(context, "ro.gn.gnromvernumber");
        if (!z.a(a11) && !a11.equals((Object)"fail")) {
            final StringBuilder sb10 = new StringBuilder("amigo/");
            sb10.append(a11);
            sb10.append("/");
            sb10.append(z.a(context, "ro.build.display.id"));
            return sb10.toString();
        }
        final String a12 = z.a(context, "ro.build.tyd.kbstyle_version");
        if (!z.a(a12) && !a12.equals((Object)"fail")) {
            final StringBuilder sb11 = new StringBuilder("dido/");
            sb11.append(a12);
            return sb11.toString();
        }
        final StringBuilder sb12 = new StringBuilder();
        sb12.append(z.a(context, "ro.build.fingerprint"));
        sb12.append("/");
        sb12.append(z.a(context, "ro.build.rom.id"));
        return sb12.toString();
    }
    
    public static long d() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            final long n = statFs.getBlockCount() * (long)statFs.getBlockSize();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            n2 = -1L;
        }
        return n2;
    }
    
    public static String d(final Context context) {
        return z.a(context, "ro.board.platform");
    }
    
    public static long e() {
        long n2 = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            final long n = statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            n2 = -1L;
        }
        return n2;
    }
    
    public static boolean e(final Context context) {
        if (g(context) == null) {
            final ArrayList list = new ArrayList();
            for (int i = 0; i < com.tencent.bugly.crashreport.common.info.b.c.length; ++i) {
                final String[] c = com.tencent.bugly.crashreport.common.info.b.c;
                if (i == 0) {
                    final File file = new File(c[i]);
                    if (file.exists()) {
                        continue;
                    }
                }
                else {
                    final File file = new File(c[i]);
                    if (!file.exists()) {
                        continue;
                    }
                }
                list.add((Object)i);
            }
            String string;
            if (list.isEmpty()) {
                string = null;
            }
            else {
                string = list.toString();
            }
            if (string == null) {
                return false;
            }
        }
        return true;
    }
    
    public static long f() {
        FileReader fileReader2;
        BufferedReader bufferedReader2;
        try {
            final FileReader fileReader = new FileReader("/proc/meminfo");
            try {
                final BufferedReader bufferedReader = new BufferedReader((Reader)fileReader, 2048);
                try {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        try {
                            bufferedReader.close();
                        }
                        catch (final IOException ex) {
                            if (!x.a((Throwable)ex)) {
                                ex.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        }
                        catch (final IOException ex2) {
                            if (!x.a((Throwable)ex2)) {
                                ex2.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    final long long1 = Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace((CharSequence)"kb", (CharSequence)"").trim());
                    try {
                        bufferedReader.close();
                    }
                    catch (final IOException ex3) {
                        if (!x.a((Throwable)ex3)) {
                            ex3.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                    }
                    catch (final IOException ex4) {
                        if (!x.a((Throwable)ex4)) {
                            ex4.printStackTrace();
                        }
                    }
                    return long1 << 10;
                }
                finally {}
            }
            finally {}
        }
        finally {
            fileReader2 = null;
            bufferedReader2 = null;
        }
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -2L;
        }
        finally {
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                }
                catch (final IOException ex5) {
                    if (!x.a((Throwable)ex5)) {
                        ex5.printStackTrace();
                    }
                }
            }
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                }
                catch (final IOException ex6) {
                    if (!x.a((Throwable)ex6)) {
                        ex6.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static boolean f(final Context context) {
        return (h(context) | p() | q() | o()) > 0;
    }
    
    public static long g() {
        BufferedReader bufferedReader = null;
        FileReader fileReader2;
        try {
            final FileReader fileReader = new FileReader("/proc/meminfo");
            try {
                final BufferedReader bufferedReader2 = new BufferedReader((Reader)fileReader, 2048);
                try {
                    bufferedReader2.readLine();
                    final String line = bufferedReader2.readLine();
                    if (line == null) {
                        try {
                            bufferedReader2.close();
                        }
                        catch (final IOException ex) {
                            if (!x.a((Throwable)ex)) {
                                ex.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        }
                        catch (final IOException ex2) {
                            if (!x.a((Throwable)ex2)) {
                                ex2.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    final long long1 = Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace((CharSequence)"kb", (CharSequence)"").trim());
                    final String line2 = bufferedReader2.readLine();
                    if (line2 == null) {
                        try {
                            bufferedReader2.close();
                        }
                        catch (final IOException ex3) {
                            if (!x.a((Throwable)ex3)) {
                                ex3.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        }
                        catch (final IOException ex4) {
                            if (!x.a((Throwable)ex4)) {
                                ex4.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    final long long2 = Long.parseLong(line2.split(":\\s+", 2)[1].toLowerCase().replace((CharSequence)"kb", (CharSequence)"").trim());
                    final String line3 = bufferedReader2.readLine();
                    if (line3 == null) {
                        try {
                            bufferedReader2.close();
                        }
                        catch (final IOException ex5) {
                            if (!x.a((Throwable)ex5)) {
                                ex5.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        }
                        catch (final IOException ex6) {
                            if (!x.a((Throwable)ex6)) {
                                ex6.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    final long long3 = Long.parseLong(line3.split(":\\s+", 2)[1].toLowerCase().replace((CharSequence)"kb", (CharSequence)"").trim());
                    try {
                        bufferedReader2.close();
                    }
                    catch (final IOException ex7) {
                        if (!x.a((Throwable)ex7)) {
                            ex7.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                    }
                    catch (final IOException ex8) {
                        if (!x.a((Throwable)ex8)) {
                            ex8.printStackTrace();
                        }
                    }
                    return (long1 << 10) + 0L + (long2 << 10) + (long3 << 10);
                }
                finally {
                    bufferedReader = bufferedReader2;
                }
            }
            finally {}
        }
        finally {
            fileReader2 = null;
        }
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -2L;
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (final IOException ex9) {
                    if (!x.a((Throwable)ex9)) {
                        ex9.printStackTrace();
                    }
                }
            }
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                }
                catch (final IOException ex10) {
                    if (!x.a((Throwable)ex10)) {
                        ex10.printStackTrace();
                    }
                }
            }
        }
    }
    
    private static String g(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        context = (Context)new ArrayList();
        int n = 0;
    Label_0043_Outer:
        while (true) {
            final String[] b = com.tencent.bugly.crashreport.common.info.b.b;
            Label_0049: {
                if (n >= b.length) {
                    break Label_0049;
                }
                while (true) {
                    try {
                        packageManager.getPackageInfo(b[n], 1);
                        ((ArrayList)context).add((Object)n);
                        ++n;
                        continue Label_0043_Outer;
                        iftrue(Label_0058:)(!((ArrayList)context).isEmpty());
                        return null;
                        Label_0058: {
                            return ((ArrayList)context).toString();
                        }
                    }
                    catch (final PackageManager$NameNotFoundException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private static int h(Context packageManager) {
        packageManager = (Context)packageManager.getPackageManager();
        int n;
        try {
            ((PackageManager)packageManager).getInstallerPackageName("de.robv.android.xposed.installer");
            n = 1;
        }
        catch (final Exception ex) {
            n = 0;
        }
        try {
            ((PackageManager)packageManager).getInstallerPackageName("com.saurik.substrate");
            n |= 0x2;
            return n;
        }
        catch (final Exception ex2) {
            return n;
        }
    }
    
    public static long h() {
        if (!n()) {
            return 0L;
        }
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCount() * (long)statFs.getBlockSize();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -2L;
        }
    }
    
    public static long i() {
        if (!n()) {
            return 0L;
        }
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -2L;
        }
    }
    
    public static String j() {
        return "";
    }
    
    public static String k() {
        String s = null;
        try {
            final String brand = Build.BRAND;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            s = "fail";
        }
        return s;
    }
    
    public static boolean l() {
        final String[] a = com.tencent.bugly.crashreport.common.info.b.a;
        for (int length = a.length, i = 0; i < length; ++i) {
            if (new File(a[i]).exists()) {
                final boolean b = true;
                return (Build.TAGS != null && Build.TAGS.contains((CharSequence)"test-keys")) || b;
            }
        }
        final boolean b = false;
        return (Build.TAGS != null && Build.TAGS.contains((CharSequence)"test-keys")) || b;
    }
    
    public static boolean m() {
        final float n = (float)(Runtime.getRuntime().maxMemory() / 1048576.0);
        final float n2 = (float)(Runtime.getRuntime().totalMemory() / 1048576.0);
        final float n3 = n - n2;
        x.c("maxMemory : %f", n);
        x.c("totalMemory : %f", n2);
        x.c("freeMemory : %f", n3);
        return n3 < 10.0f;
    }
    
    private static boolean n() {
        try {
            if (Environment.getExternalStorageState().equals((Object)"mounted")) {
                return true;
            }
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
        }
        return false;
    }
    
    private static int o() {
        int n = 256;
        try {
            final Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", (Class<?>[])new Class[0]);
            method.setAccessible(true);
            if (!method.invoke((Object)null, new Object[0]).getClass().getName().startsWith("$Proxy")) {
                n = 0;
            }
            return n;
        }
        catch (final Exception ex) {
            return n;
        }
    }
    
    private static int p() {
        try {
            throw new Exception("detect hook");
        }
        catch (final Exception ex) {
            final StackTraceElement[] stackTrace = ex.getStackTrace();
            final int length = stackTrace.length;
            int i = 0;
            int n = 0;
            int n2 = 0;
            while (i < length) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                int n3 = n;
                if (stackTraceElement.getClassName().equals((Object)"de.robv.android.xposed.XposedBridge")) {
                    n3 = n;
                    if (stackTraceElement.getMethodName().equals((Object)"main")) {
                        n3 = (n | 0x4);
                    }
                }
                int n4 = n3;
                if (stackTraceElement.getClassName().equals((Object)"de.robv.android.xposed.XposedBridge")) {
                    n4 = n3;
                    if (stackTraceElement.getMethodName().equals((Object)"handleHookedMethod")) {
                        n4 = (n3 | 0x8);
                    }
                }
                int n5 = n4;
                if (stackTraceElement.getClassName().equals((Object)"com.saurik.substrate.MS$2")) {
                    n5 = n4;
                    if (stackTraceElement.getMethodName().equals((Object)"invoked")) {
                        n5 = (n4 | 0x10);
                    }
                }
                n = n5;
                int n6 = n2;
                if (stackTraceElement.getClassName().equals((Object)"com.android.internal.os.ZygoteInit")) {
                    ++n2;
                    n = n5;
                    if ((n6 = n2) == 2) {
                        n = (n5 | 0x20);
                        n6 = n2;
                    }
                }
                ++i;
                n2 = n6;
            }
            return n;
        }
    }
    
    private static int q() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: iconst_0       
        //     3: istore_0       
        //     4: iconst_0       
        //     5: istore          5
        //     7: iconst_0       
        //     8: istore          4
        //    10: iconst_0       
        //    11: istore_2       
        //    12: iconst_0       
        //    13: istore_3       
        //    14: iconst_0       
        //    15: istore          6
        //    17: aconst_null    
        //    18: astore          8
        //    20: new             Ljava/util/HashSet;
        //    23: astore          10
        //    25: aload           10
        //    27: invokespecial   java/util/HashSet.<init>:()V
        //    30: new             Ljava/io/BufferedReader;
        //    33: astore          9
        //    35: new             Ljava/io/InputStreamReader;
        //    38: astore          11
        //    40: new             Ljava/io/FileInputStream;
        //    43: astore          12
        //    45: new             Ljava/lang/StringBuilder;
        //    48: astore          13
        //    50: aload           13
        //    52: ldc_w           "/proc/"
        //    55: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    58: aload           13
        //    60: invokestatic    android/os/Process.myPid:()I
        //    63: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    66: pop            
        //    67: aload           13
        //    69: ldc_w           "/maps"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: pop            
        //    76: aload           12
        //    78: aload           13
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: invokespecial   java/io/FileInputStream.<init>:(Ljava/lang/String;)V
        //    86: aload           11
        //    88: aload           12
        //    90: ldc_w           "utf-8"
        //    93: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    96: aload           9
        //    98: aload           11
        //   100: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   103: iload_1        
        //   104: istore          4
        //   106: iload_0        
        //   107: istore_2       
        //   108: iload           5
        //   110: istore_3       
        //   111: aload           9
        //   113: astore          8
        //   115: aload           9
        //   117: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   120: astore          11
        //   122: aload           11
        //   124: ifnull          209
        //   127: iload_1        
        //   128: istore          4
        //   130: iload_0        
        //   131: istore_2       
        //   132: iload           5
        //   134: istore_3       
        //   135: aload           9
        //   137: astore          8
        //   139: aload           11
        //   141: ldc_w           ".so"
        //   144: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   147: ifne            173
        //   150: iload_1        
        //   151: istore          4
        //   153: iload_0        
        //   154: istore_2       
        //   155: iload           5
        //   157: istore_3       
        //   158: aload           9
        //   160: astore          8
        //   162: aload           11
        //   164: ldc_w           ".jar"
        //   167: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   170: ifeq            103
        //   173: iload_1        
        //   174: istore          4
        //   176: iload_0        
        //   177: istore_2       
        //   178: iload           5
        //   180: istore_3       
        //   181: aload           9
        //   183: astore          8
        //   185: aload           10
        //   187: aload           11
        //   189: aload           11
        //   191: ldc_w           " "
        //   194: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   197: iconst_1       
        //   198: iadd           
        //   199: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   202: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   205: pop            
        //   206: goto            103
        //   209: iload_1        
        //   210: istore          4
        //   212: iload_0        
        //   213: istore_2       
        //   214: iload           5
        //   216: istore_3       
        //   217: aload           9
        //   219: astore          8
        //   221: aload           10
        //   223: invokevirtual   java/util/HashSet.iterator:()Ljava/util/Iterator;
        //   226: astore          11
        //   228: iload           6
        //   230: istore_0       
        //   231: iload_0        
        //   232: istore          4
        //   234: iload_0        
        //   235: istore_2       
        //   236: iload_0        
        //   237: istore_3       
        //   238: aload           9
        //   240: astore          8
        //   242: aload           11
        //   244: invokeinterface java/util/Iterator.hasNext:()Z
        //   249: ifeq            347
        //   252: iload_0        
        //   253: istore          4
        //   255: iload_0        
        //   256: istore_2       
        //   257: iload_0        
        //   258: istore_3       
        //   259: aload           9
        //   261: astore          8
        //   263: aload           11
        //   265: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   270: astore          10
        //   272: iload_0        
        //   273: istore_1       
        //   274: iload_0        
        //   275: istore          4
        //   277: iload_0        
        //   278: istore_2       
        //   279: iload_0        
        //   280: istore_3       
        //   281: aload           9
        //   283: astore          8
        //   285: aload           10
        //   287: checkcast       Ljava/lang/String;
        //   290: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   293: ldc_w           "xposed"
        //   296: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   299: ifeq            307
        //   302: iload_0        
        //   303: bipush          64
        //   305: ior            
        //   306: istore_1       
        //   307: iload_1        
        //   308: istore          4
        //   310: iload_1        
        //   311: istore_2       
        //   312: iload_1        
        //   313: istore_3       
        //   314: aload           9
        //   316: astore          8
        //   318: aload           10
        //   320: checkcast       Ljava/lang/String;
        //   323: ldc_w           "com.saurik.substrate"
        //   326: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   329: istore          7
        //   331: iload_1        
        //   332: istore_0       
        //   333: iload           7
        //   335: ifeq            231
        //   338: iload_1        
        //   339: sipush          128
        //   342: ior            
        //   343: istore_0       
        //   344: goto            231
        //   347: iload_0        
        //   348: istore_1       
        //   349: aload           9
        //   351: invokevirtual   java/io/BufferedReader.close:()V
        //   354: goto            488
        //   357: astore          8
        //   359: aload           8
        //   361: invokevirtual   java/io/IOException.printStackTrace:()V
        //   364: iload_1        
        //   365: istore_0       
        //   366: goto            488
        //   369: astore          10
        //   371: goto            394
        //   374: astore          10
        //   376: goto            430
        //   379: astore          10
        //   381: goto            463
        //   384: astore          9
        //   386: goto            492
        //   389: astore          10
        //   391: aconst_null    
        //   392: astore          9
        //   394: aload           9
        //   396: astore          8
        //   398: aload           10
        //   400: invokevirtual   java/io/IOException.printStackTrace:()V
        //   403: iload           4
        //   405: istore_0       
        //   406: aload           9
        //   408: ifnull          488
        //   411: iload           4
        //   413: istore_1       
        //   414: aload           9
        //   416: invokevirtual   java/io/BufferedReader.close:()V
        //   419: iload           4
        //   421: istore_0       
        //   422: goto            488
        //   425: astore          10
        //   427: aconst_null    
        //   428: astore          9
        //   430: aload           9
        //   432: astore          8
        //   434: aload           10
        //   436: invokevirtual   java/io/FileNotFoundException.printStackTrace:()V
        //   439: iload_2        
        //   440: istore_0       
        //   441: aload           9
        //   443: ifnull          488
        //   446: iload_2        
        //   447: istore_1       
        //   448: aload           9
        //   450: invokevirtual   java/io/BufferedReader.close:()V
        //   453: iload_2        
        //   454: istore_0       
        //   455: goto            488
        //   458: astore          10
        //   460: aconst_null    
        //   461: astore          9
        //   463: aload           9
        //   465: astore          8
        //   467: aload           10
        //   469: invokevirtual   java/io/UnsupportedEncodingException.printStackTrace:()V
        //   472: iload_3        
        //   473: istore_0       
        //   474: aload           9
        //   476: ifnull          488
        //   479: iload_3        
        //   480: istore_1       
        //   481: aload           9
        //   483: invokevirtual   java/io/BufferedReader.close:()V
        //   486: iload_3        
        //   487: istore_0       
        //   488: iload_0        
        //   489: ireturn        
        //   490: astore          9
        //   492: aload           8
        //   494: ifnull          512
        //   497: aload           8
        //   499: invokevirtual   java/io/BufferedReader.close:()V
        //   502: goto            512
        //   505: astore          8
        //   507: aload           8
        //   509: invokevirtual   java/io/IOException.printStackTrace:()V
        //   512: aload           9
        //   514: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  20     103    458    463    Ljava/io/UnsupportedEncodingException;
        //  20     103    425    430    Ljava/io/FileNotFoundException;
        //  20     103    389    394    Ljava/io/IOException;
        //  20     103    384    389    Any
        //  115    122    379    384    Ljava/io/UnsupportedEncodingException;
        //  115    122    374    379    Ljava/io/FileNotFoundException;
        //  115    122    369    374    Ljava/io/IOException;
        //  115    122    490    492    Any
        //  139    150    379    384    Ljava/io/UnsupportedEncodingException;
        //  139    150    374    379    Ljava/io/FileNotFoundException;
        //  139    150    369    374    Ljava/io/IOException;
        //  139    150    490    492    Any
        //  162    173    379    384    Ljava/io/UnsupportedEncodingException;
        //  162    173    374    379    Ljava/io/FileNotFoundException;
        //  162    173    369    374    Ljava/io/IOException;
        //  162    173    490    492    Any
        //  185    206    379    384    Ljava/io/UnsupportedEncodingException;
        //  185    206    374    379    Ljava/io/FileNotFoundException;
        //  185    206    369    374    Ljava/io/IOException;
        //  185    206    490    492    Any
        //  221    228    379    384    Ljava/io/UnsupportedEncodingException;
        //  221    228    374    379    Ljava/io/FileNotFoundException;
        //  221    228    369    374    Ljava/io/IOException;
        //  221    228    490    492    Any
        //  242    252    379    384    Ljava/io/UnsupportedEncodingException;
        //  242    252    374    379    Ljava/io/FileNotFoundException;
        //  242    252    369    374    Ljava/io/IOException;
        //  242    252    490    492    Any
        //  263    272    379    384    Ljava/io/UnsupportedEncodingException;
        //  263    272    374    379    Ljava/io/FileNotFoundException;
        //  263    272    369    374    Ljava/io/IOException;
        //  263    272    490    492    Any
        //  285    302    379    384    Ljava/io/UnsupportedEncodingException;
        //  285    302    374    379    Ljava/io/FileNotFoundException;
        //  285    302    369    374    Ljava/io/IOException;
        //  285    302    490    492    Any
        //  318    331    379    384    Ljava/io/UnsupportedEncodingException;
        //  318    331    374    379    Ljava/io/FileNotFoundException;
        //  318    331    369    374    Ljava/io/IOException;
        //  318    331    490    492    Any
        //  349    354    357    369    Ljava/io/IOException;
        //  398    403    490    492    Any
        //  414    419    357    369    Ljava/io/IOException;
        //  434    439    490    492    Any
        //  448    453    357    369    Ljava/io/IOException;
        //  467    472    490    492    Any
        //  481    486    357    369    Ljava/io/IOException;
        //  497    502    505    512    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
}
