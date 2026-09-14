package com.alipay.sdk.m.c0;

import java.util.Iterator;
import java.io.FileWriter;
import java.io.File;
import com.alipay.sdk.m.z.a;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;

public final class d
{
    public static String a = "";
    public static String b = "";
    public static String c = "";
    
    public static void a(final String s) {
        synchronized (d.class) {
            final ArrayList list = new ArrayList();
            ((List)list).add((Object)s);
            a((List)list);
        }
    }
    
    public static void a(final String a, final String b, final String c) {
        synchronized (d.class) {
            d.a = a;
            d.b = b;
            d.c = c;
        }
    }
    
    public static void a(final Throwable t) {
        synchronized (d.class) {
            final ArrayList list = new ArrayList();
            String string;
            if (t != null) {
                final StringWriter stringWriter = new StringWriter();
                t.printStackTrace(new PrintWriter((Writer)stringWriter));
                string = stringWriter.toString();
            }
            else {
                string = "";
            }
            ((List)list).add((Object)string);
            a((List<String>)list);
        }
    }
    
    public static void a(final List<String> list) {
        synchronized (d.class) {
            if (!com.alipay.sdk.m.z.a.a(d.b)) {
                if (!com.alipay.sdk.m.z.a.a(d.c)) {
                    final StringBuffer sb = new StringBuffer();
                    sb.append(d.c);
                    for (final String s : list) {
                        final StringBuilder sb2 = new StringBuilder(", ");
                        sb2.append(s);
                        sb.append(sb2.toString());
                    }
                    sb.append("\n");
                    try {
                        final File file = new File(d.a);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        final File file2 = new File(d.a, d.b);
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }
                        FileWriter fileWriter;
                        if (sb.length() + file2.length() <= 51200L) {
                            fileWriter = new FileWriter(file2, true);
                        }
                        else {
                            fileWriter = new FileWriter(file2);
                        }
                        fileWriter.write(sb.toString());
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    catch (final Exception ex) {}
                }
            }
        }
    }
}
