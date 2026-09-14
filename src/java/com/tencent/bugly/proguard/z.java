package com.tencent.bugly.proguard;

import java.util.List;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.UnsupportedEncodingException;
import java.util.TimeZone;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import android.os.Parcelable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import android.os.Bundle;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.util.Map$Entry;
import android.os.Looper;
import java.util.Collection;
import java.util.Arrays;
import java.security.MessageDigest;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.io.IOException;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import java.lang.reflect.Method;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import android.content.SharedPreferences;
import android.content.Context;
import java.util.Map;

public class z
{
    private static Map<String, String> a;
    
    public static Context a(final Context context) {
        if (context == null) {
            return context;
        }
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            return context;
        }
        return applicationContext;
    }
    
    public static SharedPreferences a(final String s, final Context context) {
        if (context != null) {
            return context.getSharedPreferences(s, 0);
        }
        return null;
    }
    
    private static BufferedReader a(final File file) {
        if (file != null && file.exists()) {
            if (file.canRead()) {
                try {
                    return new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file), "utf-8"));
                }
                finally {
                    final Throwable t;
                    x.a(t);
                }
            }
        }
        return null;
    }
    
    public static BufferedReader a(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        try {
            final File file = new File(s, s2);
            if (file.exists() && file.canRead()) {
                return a(file);
            }
            return null;
        }
        catch (final NullPointerException ex) {
            x.a((Throwable)ex);
            return null;
        }
    }
    
    public static Object a(final String className, final String name, final Object o, final Class<?>[] parameterTypes, final Object[] array) {
        try {
            final Method declaredMethod = Class.forName(className).getDeclaredMethod(name, parameterTypes);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object)null, array);
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static <T> T a(final byte[] array, final Parcelable$Creator<T> parcelable$Creator) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        try {
            final Object fromParcel = parcelable$Creator.createFromParcel(obtain);
            if (obtain != null) {
                obtain.recycle();
            }
            return (T)fromParcel;
        }
        finally {
            try {
                final Throwable t;
                t.printStackTrace();
                return null;
            }
            finally {
                if (obtain != null) {
                    obtain.recycle();
                }
            }
        }
    }
    
    public static String a() {
        return a(System.currentTimeMillis());
    }
    
    public static String a(final long n) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(n));
        }
        catch (final Exception ex) {
            return new Date().toString();
        }
    }
    
    public static String a(Context exec, final int n, String string) {
        final boolean a = AppInfo.a((Context)exec, "android.permission.READ_LOGS");
        exec = null;
        if (!a) {
            x.d("no read_log permission!", new Object[0]);
            return null;
        }
        String[] array;
        if (string == null) {
            array = new String[] { "logcat", "-d", "-v", "threadtime" };
        }
        else {
            array = new String[] { "logcat", "-d", "-v", "threadtime", "-s", string };
        }
        final StringBuilder sb = new StringBuilder();
        try {
            string = (String)(exec = (exec = (exec = (IOException)Runtime.getRuntime().exec(array))));
            final InputStreamReader inputStreamReader = new InputStreamReader(((Process)string).getInputStream());
            exec = (IOException)string;
            final BufferedReader bufferedReader = new BufferedReader((Reader)inputStreamReader);
            while (true) {
                exec = (IOException)string;
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                exec = (IOException)string;
                sb.append(line);
                exec = (IOException)string;
                sb.append("\n");
                if (n <= 0) {
                    continue;
                }
                exec = (IOException)string;
                if (sb.length() <= n) {
                    continue;
                }
                exec = (IOException)string;
                sb.delete(0, sb.length() - n);
            }
            exec = (IOException)string;
            final String string2 = sb.toString();
            if (string != null) {
                try {
                    ((Process)string).getOutputStream().close();
                }
                catch (final IOException exec) {
                    exec.printStackTrace();
                }
                try {
                    ((Process)string).getInputStream().close();
                }
                catch (final IOException exec) {
                    exec.printStackTrace();
                }
                try {
                    ((Process)string).getErrorStream().close();
                }
                catch (final IOException exec) {
                    exec.printStackTrace();
                }
            }
            return string2;
        }
        finally {
            try {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
                final StringBuilder sb2 = new StringBuilder("\n[error:");
                sb2.append(t.toString());
                sb2.append("]");
                sb.append(sb2.toString());
                string = sb.toString();
                return string;
            }
            finally {
                if (exec != null) {
                    try {
                        ((Process)exec).getOutputStream().close();
                    }
                    catch (final IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        ((Process)exec).getInputStream().close();
                    }
                    catch (final IOException ex2) {
                        ex2.printStackTrace();
                    }
                    try {
                        ((Process)exec).getErrorStream().close();
                    }
                    catch (final IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static String a(final Context context, final String s) {
        if (s == null || s.trim().equals((Object)"")) {
            return "";
        }
        if (z.a == null) {
            z.a = (Map<String, String>)new HashMap();
            String s2 = "/system/bin/sh";
            if (!new File("/system/bin/sh").exists() || !new File("/system/bin/sh").canExecute()) {
                s2 = "sh";
            }
            final ArrayList<String> a = a(context, new String[] { s2, "-c", "getprop" });
            if (a != null && ((List)a).size() > 0) {
                x.b(z.class, "Successfully get 'getprop' list.", new Object[0]);
                final Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                final Iterator iterator = ((List)a).iterator();
                while (iterator.hasNext()) {
                    final Matcher matcher = compile.matcher((CharSequence)iterator.next());
                    if (matcher.find()) {
                        z.a.put((Object)matcher.group(1), (Object)matcher.group(2));
                    }
                }
                x.b(z.class, "Systems properties number: %d.", z.a.size());
            }
        }
        if (z.a.containsKey((Object)s)) {
            return (String)z.a.get((Object)s);
        }
        return "fail";
    }
    
    public static String a(File string, final int n, final boolean b) {
        if (string != null && string.exists()) {
            if (string.canRead()) {
                try {
                    final StringBuilder sb = new StringBuilder();
                    final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(string), "utf-8"));
                    try {
                        while (true) {
                            final String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                            sb.append("\n");
                            if (n <= 0 || sb.length() <= n) {
                                continue;
                            }
                            if (b) {
                                sb.delete(n, sb.length());
                                break;
                            }
                            sb.delete(0, sb.length() - n);
                        }
                        string = (File)sb.toString();
                        try {
                            bufferedReader.close();
                        }
                        catch (final Exception ex) {
                            x.a((Throwable)ex);
                        }
                        return (String)string;
                    }
                    finally {}
                }
                finally {
                    string = null;
                }
                try {
                    final Throwable t;
                    x.a(t);
                    return null;
                }
                finally {
                    if (string != null) {
                        try {
                            ((BufferedReader)string).close();
                        }
                        catch (final Exception ex2) {
                            x.a((Throwable)ex2);
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static String a(final Throwable t) {
        if (t == null) {
            return "";
        }
        try {
            final StringWriter stringWriter = new StringWriter();
            t.printStackTrace(new PrintWriter((Writer)stringWriter));
            return stringWriter.getBuffer().toString();
        }
        finally {
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return "fail";
        }
    }
    
    public static String a(final Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        }
        catch (final Exception ex) {
            return new Date().toString();
        }
    }
    
    public static String a(byte[] digest) {
        if (digest != null) {
            if (digest.length != 0) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("SHA-1");
                    instance.update(digest);
                    digest = instance.digest();
                    if (digest == null) {
                        return "";
                    }
                    final StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < digest.length; ++i) {
                        final String hexString = Integer.toHexString(digest[i] & 0xFF);
                        if (hexString.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(hexString);
                    }
                    return sb.toString().toUpperCase();
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return "NULL";
    }
    
    public static Thread a(final Runnable runnable, final String name) {
        try {
            final Thread thread = new Thread(runnable);
            thread.setName(name);
            thread.start();
            return thread;
        }
        finally {
            final Throwable t;
            x.e("[Util] Failed to start a thread to execute task with message: %s", t.getMessage());
            return null;
        }
    }
    
    private static ArrayList<String> a(Context context, final String[] array) {
        if (AppInfo.e(context)) {
            return (ArrayList<String>)new ArrayList((Collection)Arrays.asList((Object[])new String[] { "unknown(low memory)" }));
        }
        final ArrayList list = new ArrayList();
        BufferedReader bufferedReader2;
        try {
            final Process exec = Runtime.getRuntime().exec(array);
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(exec.getInputStream()));
            try {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    list.add((Object)line);
                }
                context = (Context)new BufferedReader((Reader)new InputStreamReader(exec.getErrorStream()));
                try {
                    while (true) {
                        final String line2 = ((BufferedReader)context).readLine();
                        if (line2 == null) {
                            break;
                        }
                        list.add((Object)line2);
                    }
                    try {
                        bufferedReader.close();
                    }
                    catch (final IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        ((BufferedReader)context).close();
                    }
                    catch (final IOException ex2) {
                        ex2.printStackTrace();
                    }
                    return (ArrayList<String>)list;
                }
                finally {}
            }
            finally {}
        }
        finally {
            bufferedReader2 = null;
            context = null;
        }
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
        finally {
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                }
                catch (final IOException ex3) {
                    ex3.printStackTrace();
                }
            }
            if (context != null) {
                try {
                    ((BufferedReader)context).close();
                }
                catch (final IOException ex4) {
                    ex4.printStackTrace();
                }
            }
        }
    }
    
    public static Map<String, String> a(final int n, final boolean b) {
        final HashMap hashMap = new HashMap(12);
        final Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        final Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey((Object)thread)) {
            allStackTraces.put((Object)thread, (Object)thread.getStackTrace());
        }
        Thread.currentThread().getId();
        final StringBuilder sb = new StringBuilder();
        for (final Map$Entry map$Entry : allStackTraces.entrySet()) {
            int i = 0;
            sb.setLength(0);
            if (map$Entry.getValue() != null && ((StackTraceElement[])map$Entry.getValue()).length != 0) {
                for (StackTraceElement[] array = (StackTraceElement[])map$Entry.getValue(); i < array.length; ++i) {
                    final StackTraceElement stackTraceElement = array[i];
                    if (n > 0 && sb.length() >= n) {
                        final StringBuilder sb2 = new StringBuilder("\n[Stack over limit size :");
                        sb2.append(n);
                        sb2.append(" , has been cut!]");
                        sb.append(sb2.toString());
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(((Thread)map$Entry.getKey()).getName());
                sb3.append("(");
                sb3.append(((Thread)map$Entry.getKey()).getId());
                sb3.append(")");
                ((Map)hashMap).put((Object)sb3.toString(), (Object)sb.toString());
            }
        }
        return (Map<String, String>)hashMap;
    }
    
    public static Map<String, PlugInBean> a(final Parcel parcel) {
        final Bundle bundle = parcel.readBundle();
        Object o = null;
        if (bundle == null) {
            return null;
        }
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        final int intValue = (int)bundle.get("pluginNum");
        final int n = 0;
        for (int i = 0; i < intValue; ++i) {
            final StringBuilder sb = new StringBuilder("pluginKey");
            sb.append(i);
            ((List)list).add((Object)bundle.getString(sb.toString()));
        }
        for (int j = 0; j < intValue; ++j) {
            final StringBuilder sb2 = new StringBuilder("pluginVal");
            sb2.append(j);
            sb2.append("plugInId");
            final String string = bundle.getString(sb2.toString());
            final StringBuilder sb3 = new StringBuilder("pluginVal");
            sb3.append(j);
            sb3.append("plugInUUID");
            final String string2 = bundle.getString(sb3.toString());
            final StringBuilder sb4 = new StringBuilder("pluginVal");
            sb4.append(j);
            sb4.append("plugInVersion");
            ((List)list2).add((Object)new PlugInBean(string, bundle.getString(sb4.toString()), string2));
        }
        if (((List)list).size() == ((List)list2).size()) {
            final HashMap hashMap = new HashMap(((List)list).size());
            int n2 = n;
            while (true) {
                o = hashMap;
                if (n2 >= ((List)list).size()) {
                    break;
                }
                hashMap.put(((List)list).get(n2), (Object)PlugInBean.class.cast(((List)list2).get(n2)));
                ++n2;
            }
        }
        else {
            x.e("map plugin parcel error!", new Object[0]);
        }
        return (Map<String, PlugInBean>)o;
    }
    
    public static void a(final Parcel parcel, final Map<String, PlugInBean> map) {
        if (map != null && map.size() > 0) {
            final int size = map.size();
            final ArrayList list = new ArrayList(size);
            final ArrayList list2 = new ArrayList(size);
            for (final Map$Entry map$Entry : map.entrySet()) {
                list.add(map$Entry.getKey());
                list2.add(map$Entry.getValue());
            }
            final Bundle bundle = new Bundle();
            bundle.putInt("pluginNum", list.size());
            final int n = 0;
            int n2 = 0;
            int i;
            while (true) {
                i = n;
                if (n2 >= list.size()) {
                    break;
                }
                final StringBuilder sb = new StringBuilder("pluginKey");
                sb.append(n2);
                bundle.putString(sb.toString(), (String)list.get(n2));
                ++n2;
            }
            while (i < list.size()) {
                final StringBuilder sb2 = new StringBuilder("pluginVal");
                sb2.append(i);
                sb2.append("plugInId");
                bundle.putString(sb2.toString(), ((PlugInBean)list2.get(i)).a);
                final StringBuilder sb3 = new StringBuilder("pluginVal");
                sb3.append(i);
                sb3.append("plugInUUID");
                bundle.putString(sb3.toString(), ((PlugInBean)list2.get(i)).c);
                final StringBuilder sb4 = new StringBuilder("pluginVal");
                sb4.append(i);
                sb4.append("plugInVersion");
                bundle.putString(sb4.toString(), ((PlugInBean)list2.get(i)).b);
                ++i;
            }
            parcel.writeBundle(bundle);
            return;
        }
        parcel.writeBundle((Bundle)null);
    }
    
    public static void a(final Class<?> clazz, final String s, final Object o, final Object o2) {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            declaredField.setAccessible(true);
            declaredField.set((Object)null, o);
        }
        catch (final Exception ex) {}
    }
    
    public static boolean a(final Context context, final String s, final long n) {
        x.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", s, android.os.Process.myPid(), android.os.Process.myTid());
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append((Object)context.getFilesDir());
            sb.append(File.separator);
            sb.append(s);
            final File file = new File(sb.toString());
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 10000L) {
                    return false;
                }
                x.c("[Util] Lock file (%s) is expired, unlock it.", s);
                b(context, s);
            }
            if (file.createNewFile()) {
                x.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", s, android.os.Process.myPid(), android.os.Process.myTid());
                return true;
            }
            x.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", s, android.os.Process.myPid(), android.os.Process.myTid());
            return false;
        }
        finally {
            final Throwable t;
            x.a(t);
            return false;
        }
    }
    
    public static boolean a(final File file, File file2, int read) {
        x.c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals((Object)file2)) {
            x.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (file.exists() && file.canRead()) {
            try {
                if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            }
            finally {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
            if (file2.exists()) {
                if (file2.canRead()) {
                    Object o = null;
                    ZipOutputStream zipOutputStream;
                    try {
                        final FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            o = new ZipOutputStream((OutputStream)new BufferedOutputStream((OutputStream)new FileOutputStream(file2)));
                            try {
                                ((ZipOutputStream)o).setMethod(8);
                                ((ZipOutputStream)o).putNextEntry(new ZipEntry(file.getName()));
                                final byte[] array = new byte[5000];
                                while (true) {
                                    read = fileInputStream.read(array);
                                    if (read <= 0) {
                                        break;
                                    }
                                    ((ZipOutputStream)o).write(array, 0, read);
                                }
                                ((ZipOutputStream)o).flush();
                                ((ZipOutputStream)o).closeEntry();
                                try {
                                    fileInputStream.close();
                                }
                                catch (final IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    ((ZipOutputStream)o).close();
                                }
                                catch (final IOException ex2) {
                                    ex2.printStackTrace();
                                }
                                x.c("rqdp{  ZF end}", new Object[0]);
                                return true;
                            }
                            finally {}
                        }
                        finally {}
                    }
                    finally {
                        file2 = null;
                        zipOutputStream = (ZipOutputStream)o;
                    }
                    try {
                        final Throwable t2;
                        if (!x.a(t2)) {
                            t2.printStackTrace();
                        }
                        return false;
                    }
                    finally {
                        if (zipOutputStream != null) {
                            try {
                                ((FileInputStream)zipOutputStream).close();
                            }
                            catch (final IOException ex3) {
                                ex3.printStackTrace();
                            }
                        }
                        if (file2 != null) {
                            try {
                                ((ZipOutputStream)file2).close();
                            }
                            catch (final IOException ex4) {
                                ex4.printStackTrace();
                            }
                        }
                        x.c("rqdp{  ZF end}", new Object[0]);
                    }
                }
            }
            return false;
        }
        x.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
        return false;
    }
    
    public static boolean a(final Runnable runnable) {
        if (runnable != null) {
            final w a = w.a();
            if (a != null) {
                return a.a(runnable);
            }
            final String[] split = runnable.getClass().getName().split("\\.");
            if (a(runnable, split[split.length - 1]) != null) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean a(final String s) {
        return s == null || s.trim().length() <= 0;
    }
    
    public static byte[] a(final Parcelable parcelable) {
        final Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
    
    public static byte[] a(File file, String byteArray, final String s) {
        if (byteArray != null) {
            if (byteArray.length() != 0) {
                x.c("rqdp{  ZF start}", new Object[0]);
                try {
                    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray.getBytes("UTF-8"));
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    final ZipOutputStream zipOutputStream = new ZipOutputStream((OutputStream)byteArrayOutputStream);
                    try {
                        zipOutputStream.setMethod(8);
                        zipOutputStream.putNextEntry(new ZipEntry(s));
                        final byte[] array = new byte[1024];
                        while (true) {
                            final int read = byteArrayInputStream.read(array);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(array, 0, read);
                        }
                        zipOutputStream.closeEntry();
                        zipOutputStream.flush();
                        zipOutputStream.finish();
                        byteArray = (String)(Object)byteArrayOutputStream.toByteArray();
                        try {
                            zipOutputStream.close();
                        }
                        catch (final IOException ex) {
                            ex.printStackTrace();
                        }
                        x.c("rqdp{  ZF end}", new Object[0]);
                        return (byte[])(Object)byteArray;
                    }
                    finally {}
                }
                finally {
                    file = null;
                }
                try {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                    return null;
                }
                finally {
                    if (file != null) {
                        try {
                            ((ZipOutputStream)file).close();
                        }
                        catch (final IOException ex2) {
                            ex2.printStackTrace();
                        }
                    }
                    x.c("rqdp{  ZF end}", new Object[0]);
                }
            }
        }
        return null;
    }
    
    public static byte[] a(byte[] a, final int n) {
        if (a == null) {
            return a;
        }
        x.c("[Util] Zip %d bytes data with type %s", a.length, "Gzip");
        try {
            final ae a2 = ad.a(2);
            if (a2 == null) {
                return null;
            }
            a = a2.a(a);
            return a;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static long b() {
        try {
            return (System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()) / 86400000L * 86400000L - TimeZone.getDefault().getRawOffset();
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return -1L;
        }
    }
    
    public static long b(final byte[] array) {
        if (array == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(array, "utf-8"));
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }
    
    public static String b(final String s, final String s2) {
        if (com.tencent.bugly.crashreport.common.info.a.b() != null && com.tencent.bugly.crashreport.common.info.a.b().F != null) {
            return com.tencent.bugly.crashreport.common.info.a.b().F.getString(s, s2);
        }
        return "";
    }
    
    public static String b(final Throwable t) {
        if (t == null) {
            return "";
        }
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter((Writer)stringWriter);
        t.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }
    
    public static Map<String, String> b(final Parcel parcel) {
        final Bundle bundle = parcel.readBundle();
        Map<String, String> map = null;
        if (bundle == null) {
            return null;
        }
        final ArrayList stringArrayList = bundle.getStringArrayList("keys");
        final ArrayList stringArrayList2 = bundle.getStringArrayList("values");
        int n = 0;
        if (stringArrayList != null && stringArrayList2 != null && ((List)stringArrayList).size() == ((List)stringArrayList2).size()) {
            final HashMap hashMap = new HashMap(((List)stringArrayList).size());
            while (true) {
                map = (Map<String, String>)hashMap;
                if (n >= ((List)stringArrayList).size()) {
                    break;
                }
                hashMap.put(((List)stringArrayList).get(n), ((List)stringArrayList2).get(n));
                ++n;
            }
        }
        else {
            x.e("map parcel error!", new Object[0]);
        }
        return map;
    }
    
    public static void b(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (final InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void b(final Parcel parcel, final Map<String, String> map) {
        if (map != null && map.size() > 0) {
            final int size = map.size();
            final ArrayList list = new ArrayList(size);
            final ArrayList list2 = new ArrayList(size);
            for (final Map$Entry map$Entry : map.entrySet()) {
                list.add(map$Entry.getKey());
                list2.add(map$Entry.getValue());
            }
            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("keys", list);
            bundle.putStringArrayList("values", list2);
            parcel.writeBundle(bundle);
            return;
        }
        parcel.writeBundle((Bundle)null);
    }
    
    public static void b(final String s) {
        if (s == null) {
            return;
        }
        final File file = new File(s);
        if (file.isFile() && file.exists() && file.canWrite()) {
            file.delete();
        }
    }
    
    public static boolean b(final Context context, final String s) {
        x.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", s, android.os.Process.myPid(), android.os.Process.myTid());
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append((Object)context.getFilesDir());
            sb.append(File.separator);
            sb.append(s);
            final File file = new File(sb.toString());
            if (!file.exists()) {
                return true;
            }
            if (file.delete()) {
                x.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", s, android.os.Process.myPid(), android.os.Process.myTid());
                return true;
            }
            return false;
        }
        finally {
            final Throwable t;
            x.a(t);
            return false;
        }
    }
    
    public static byte[] b(byte[] b, final int n) {
        if (b == null) {
            return b;
        }
        x.c("[Util] Unzip %d bytes data with type %s", b.length, "Gzip");
        try {
            final ae a = ad.a(2);
            if (a == null) {
                return null;
            }
            b = a.b(b);
            return b;
        }
        finally {
            final Throwable t;
            if (t.getMessage() != null && t.getMessage().contains((CharSequence)"Not in GZIP format")) {
                x.d(t.getMessage(), new Object[0]);
            }
            else if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static boolean c(final String s) {
        if (s == null || s.trim().length() <= 0) {
            return false;
        }
        if (s.length() > 255) {
            x.a("URL(%s)'s length is larger than 255.", s);
            return false;
        }
        if (!s.toLowerCase().startsWith("http")) {
            x.a("URL(%s) is not start with \"http\".", s);
            return false;
        }
        return true;
    }
    
    public static byte[] c(final long n) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(n);
            return sb.toString().getBytes("utf-8");
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
