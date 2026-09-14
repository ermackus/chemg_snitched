package com.tencent.bugly.crashreport.crash.anr;

import java.util.Map;
import java.util.regex.Matcher;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import com.tencent.bugly.proguard.x;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.BufferedReader;

public class TraceFileHelper
{
    private static String a(final BufferedReader bufferedReader) throws IOException {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; ++i) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(line);
            sb2.append("\n");
            sb.append(sb2.toString());
        }
        return sb.toString();
    }
    
    private static Object[] a(final BufferedReader bufferedReader, final Pattern... array) throws IOException {
        if (bufferedReader != null) {
            if (array != null) {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    for (final Pattern pattern : array) {
                        if (pattern.matcher((CharSequence)line).matches()) {
                            return new Object[] { pattern, line };
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private static String b(final BufferedReader bufferedReader) throws IOException {
        final StringBuffer sb = new StringBuffer();
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null || line.trim().length() <= 0) {
                break;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(line);
            sb2.append("\n");
            sb.append(sb2.toString());
        }
        return sb.toString();
    }
    
    public static a readFirstDumpInfo(final String s, final boolean b) {
        if (s == null) {
            x.e("path:%s", s);
            return null;
        }
        final a a = new a();
        readTraceFile(s, (b)new TraceFileHelper$2(a, b));
        if (a.a > 0L && a.c > 0L && a.b != null) {
            return a;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(a.a);
        sb.append(" ");
        sb.append(a.c);
        sb.append(" ");
        sb.append(a.b);
        x.e("first dump error %s", sb.toString());
        return null;
    }
    
    public static a readTargetDumpInfo(final String s, final String s2, final boolean b) {
        if (s != null) {
            if (s2 != null) {
                final a a = new a();
                readTraceFile(s2, (b)new TraceFileHelper$1(a, s, b));
                if (a.a > 0L && a.c > 0L && a.b != null) {
                    return a;
                }
            }
        }
        return null;
    }
    
    public static void readTraceFile(String ex, final b b) {
        if (ex == null || b == null) {
            return;
        }
        final File file = new File((String)ex);
        if (!file.exists()) {
            return;
        }
        file.lastModified();
        file.length();
        Object compile = null;
        final IOException ex2 = ex = null;
        Object o = null;
        Pattern pattern;
        try {
            try {
                ex = ex2;
                ex = ex2;
                final FileReader fileReader = new FileReader(file);
                ex = ex2;
                o = new BufferedReader((Reader)fileReader);
                try {
                    final Pattern compile2 = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                    final Pattern compile3 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                    compile = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                    final Pattern compile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                    ex = (IOException)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    while (true) {
                        final Object[] a = a((BufferedReader)o, compile2);
                        if (a == null) {
                            break;
                        }
                        final String[] split = a[1].toString().split("\\s");
                        final long long1 = Long.parseLong(split[2]);
                        final StringBuilder sb = new StringBuilder();
                        sb.append(split[4]);
                        sb.append(" ");
                        sb.append(split[5]);
                        final long time = ((SimpleDateFormat)ex).parse(sb.toString()).getTime();
                        final Object[] a2 = a((BufferedReader)o, (Pattern)compile);
                        if (a2 == null) {
                            try {
                                ((BufferedReader)o).close();
                                return;
                            }
                            catch (final IOException ex) {
                                if (!x.a((Throwable)ex)) {
                                    ex.printStackTrace();
                                }
                                return;
                            }
                        }
                        final Matcher matcher = ((Pattern)compile).matcher((CharSequence)a2[1].toString());
                        matcher.find();
                        matcher.group(1);
                        if (!b.a(long1, time, matcher.group(1))) {
                            try {
                                ((BufferedReader)o).close();
                                return;
                            }
                            catch (final IOException ex) {
                                if (!x.a((Throwable)ex)) {
                                    ex.printStackTrace();
                                }
                                return;
                            }
                        }
                        while (true) {
                            final Object[] a3 = a((BufferedReader)o, compile4, compile3);
                            if (a3 == null) {
                                break;
                            }
                            if (a3[0] == compile4) {
                                final String string = a3[1].toString();
                                final Matcher matcher2 = Pattern.compile("\".+\"").matcher((CharSequence)string);
                                matcher2.find();
                                final String group = matcher2.group();
                                final String substring = group.substring(1, group.length() - 1);
                                string.contains((CharSequence)"NATIVE");
                                final Matcher matcher3 = Pattern.compile("tid=\\d+").matcher((CharSequence)string);
                                matcher3.find();
                                final String group2 = matcher3.group();
                                b.a(substring, Integer.parseInt(group2.substring(group2.indexOf("=") + 1)), a((BufferedReader)o), b((BufferedReader)o));
                            }
                            else {
                                if (!b.a(Long.parseLong(a3[1].toString().split("\\s")[2]))) {
                                    try {
                                        ((BufferedReader)o).close();
                                        return;
                                    }
                                    catch (final IOException ex) {
                                        if (!x.a((Throwable)ex)) {
                                            ex.printStackTrace();
                                        }
                                        return;
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    try {
                        ((BufferedReader)o).close();
                        return;
                    }
                    catch (final IOException ex) {
                        if (!x.a((Throwable)ex)) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                }
                catch (final Exception ex) {}
                finally {
                    ex = (IOException)o;
                }
            }
            finally {}
        }
        catch (final Exception o) {
            pattern = (Pattern)compile;
        }
        if (!x.a((Throwable)o)) {
            ((Exception)o).printStackTrace();
        }
        final String name = ((Throwable)o).getClass().getName();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(((Exception)o).getMessage());
        x.d("trace open fail:%s : %s", name, sb2.toString());
        if (pattern != null) {
            try {
                ((BufferedReader)pattern).close();
                return;
            }
            catch (final IOException ex3) {
                if (!x.a((Throwable)ex3)) {
                    ex3.printStackTrace();
                }
            }
        }
        return;
        if (ex != null) {
            try {
                ((BufferedReader)ex).close();
            }
            catch (final IOException ex4) {
                if (!x.a((Throwable)ex4)) {
                    ex4.printStackTrace();
                }
            }
        }
    }
    
    public static final class a
    {
        public long a;
        public String b;
        public long c;
        public Map<String, String[]> d;
    }
    
    public interface b
    {
        boolean a(final long p0);
        
        boolean a(final long p0, final long p1, final String p2);
        
        boolean a(final String p0, final int p1, final String p2, final String p3);
    }
}
