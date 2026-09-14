package com.alibaba.sdk.android.man.crashreporter.a.a.a.b;

import java.io.FileReader;
import java.util.List;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import java.io.IOException;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import android.os.Build$VERSION;
import java.util.Arrays;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.util.ArrayList;
import android.os.Process;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;

public class a
{
    public static final int o = 8192;
    public static int p = 100;
    public static int q = 100;
    public static final int r = 10000;
    
    public static String a(String line, final boolean b) {
        final ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
        if (configure != null) {
            a.q = configure.enableSysLogcatMaxCount;
            final int n = a.p = configure.enableSysLogcatLinkMaxCount;
            if (a.q == 0 || n == 0) {
                return "";
            }
        }
        final int myPid = Process.myPid();
        final Reader reader = null;
        final StringBuilder sb = null;
        Object string;
        if (b && myPid > 0) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(Integer.toString(myPid));
            sb2.append("):");
            string = sb2.toString();
        }
        else {
            string = null;
        }
        Object o = new ArrayList();
        ((List)o).add((Object)"logcat");
        if (i.b((CharSequence)line)) {
            ((List)o).add((Object)"-b");
            ((List)o).add((Object)line);
        }
        final int n2 = 0;
        final int n3 = 0;
        final List list = Arrays.asList((Object[])new String[] { "-t", Integer.toString(a.q), "-v", "time" });
        final int index = list.indexOf((Object)"-t");
        int n5;
        final int n4 = n5 = -1;
        if (index > -1) {
            n5 = n4;
            if (index < list.size()) {
                final int n6 = index + 1;
                n5 = Integer.parseInt((String)list.get(n6));
                if (Build$VERSION.SDK_INT < 8) {
                    list.remove(n6);
                    list.remove(index);
                    list.add((Object)"-d");
                }
            }
        }
        if (n5 <= 0) {
            n5 = a.p;
        }
        final com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a a = new com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a<Object>(n5);
        ((List)o).addAll((Collection)list);
        Object line2 = sb;
        Reader reader2;
        try {
            try {
                final java.lang.Process exec = Runtime.getRuntime().exec((String[])((List)o).toArray((Object[])new String[((List)o).size()]));
                line2 = sb;
                line2 = sb;
                line2 = sb;
                final InputStreamReader inputStreamReader = new InputStreamReader(exec.getInputStream());
                line2 = sb;
                o = new BufferedReader((Reader)inputStreamReader, 8192);
                try {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("collectLogCat Retrieving logcat output...");
                    final boolean b2 = i.b((CharSequence)line);
                    int n7 = n2;
                    if (b2) {
                        int n8 = n3;
                        while (true) {
                            line2 = ((BufferedReader)o).readLine();
                            if (line2 == null || n8 >= com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.p) {
                                break;
                            }
                            if (string == null || ((String)line2).contains((CharSequence)string)) {
                                final StringBuilder sb3 = new StringBuilder();
                                sb3.append((String)line2);
                                sb3.append("\n");
                                a.add((Object)sb3.toString());
                            }
                            ++n8;
                        }
                    }
                    else {
                        while (true) {
                            line = ((BufferedReader)o).readLine();
                            if (line == null || n7 >= com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.p) {
                                break;
                            }
                            if ((string == null || line.contains((CharSequence)string)) && (line.contains((CharSequence)"W/") || line.contains((CharSequence)"E/")) && !line.contains((CharSequence)"com.alibaba.motu.crashreporter")) {
                                line2 = new StringBuilder();
                                ((StringBuilder)line2).append(line);
                                ((StringBuilder)line2).append("\n");
                                a.add((Object)((StringBuilder)line2).toString());
                            }
                            ++n7;
                        }
                    }
                    f.a((Reader)o);
                }
                catch (final IOException line2) {}
                finally {
                    line2 = o;
                }
            }
            finally {}
        }
        catch (final IOException o) {
            reader2 = reader;
        }
        com.alibaba.sdk.android.man.crashreporter.b.a.d("MotuLogProber could not retrieve data", (Throwable)o);
        f.a(reader2);
        return a.toString();
        f.a((Reader)line2);
    }
    
    public static String a(final String... array) {
        final StringBuilder sb = new StringBuilder();
        try {
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(Runtime.getRuntime().exec(array).getInputStream()), 8192);
            try {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append("\n");
                }
                f.a((Reader)bufferedReader);
            }
            catch (final IOException o) {}
        }
        catch (final IOException ex) {
            goto Label_0098;
        }
    }
    
    public static String f() {
        try {
            final FileReader fileReader = new FileReader("/proc/cpuinfo");
            final BufferedReader bufferedReader = new BufferedReader((Reader)fileReader, 8192);
            f.a((Reader)fileReader);
            f.a((Reader)bufferedReader);
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("read /proc/cpuinfo error.", (Throwable)ex);
        }
        return "";
    }
    
    public static String g() {
        return a("dumpsys", "meminfo", Integer.toString(Process.myPid()));
    }
}
