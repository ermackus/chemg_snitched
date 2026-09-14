package com.alipay.sdk.m.u;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.alipay.sdk.interior.Log$ISdkLogCallback;

public class e
{
    public static Log$ISdkLogCallback a;
    public static final String b = "alipaysdk";
    
    public static String a(String s, final String s2) {
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "";
        }
        return String.format("[%s][%s]", new Object[] { s3, s });
    }
    
    public static void a(final Log$ISdkLogCallback a) {
        e.a = a;
    }
    
    public static void a(final String s) {
        try {
            final Log$ISdkLogCallback a = e.a;
            if (a != null) {
                a.onLogLine(String.format("[AlipaySDK] %s %s", new Object[] { new SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault()).format(new Date()), s }));
            }
        }
        finally {}
    }
    
    public static void a(final String s, String a, final Throwable t) {
        a = a(s, a);
        final StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(" ");
        sb.append(b(t));
        a(sb.toString());
    }
    
    public static void a(final Throwable t) {
        if (t == null) {
            return;
        }
        try {
            a(b(t));
        }
        finally {}
    }
    
    public static String b(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter((Writer)stringWriter));
        return stringWriter.toString();
    }
    
    public static void b(final String s, final String s2) {
        a(a(s, s2));
    }
    
    public static void c(final String s, final String s2) {
        a(a(s, s2));
    }
    
    public static void d(final String s, final String s2) {
        a(a(s, s2));
    }
    
    public static void e(final String s, final String s2) {
        a(a(s, s2));
    }
}
