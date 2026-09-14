package com.alibaba.mtl.log.d;

import android.os.Process;
import android.util.Log;

public class i
{
    private static boolean K = false;
    private static boolean L = false;
    private static String aj = "UTAnalytics:";
    
    public static void a(String string, final Object o) {
        if (l() || k()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(i.aj);
            string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(o);
            sb2.append("");
            Log.w(string, sb2.toString());
        }
    }
    
    public static void a(final String s, final Object o, final Throwable t) {
        if (l() || k()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(i.aj);
            final String string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(o);
            sb2.append("");
            Log.w(string, sb2.toString(), t);
        }
    }
    
    public static void a(final String s, final Object... array) {
        if (i.L) {
            final StringBuilder sb = new StringBuilder();
            sb.append(i.aj);
            sb.append(s);
            final String string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("pid:");
            sb2.append(Process.myPid());
            sb2.append(" ");
            if (array != null) {
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != null) {
                        final String string2 = array[i].toString();
                        if (!string2.endsWith(":") && !string2.endsWith(": ")) {
                            sb2.append(string2);
                            sb2.append(",");
                        }
                        else {
                            sb2.append(string2);
                        }
                    }
                }
            }
            Log.d(string, sb2.toString());
        }
    }
    
    public static void a(String string, final String... array) {
        if (i.L) {
            final StringBuilder sb = new StringBuilder();
            sb.append(i.aj);
            sb.append(string);
            string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("pid:");
            sb2.append(Process.myPid());
            sb2.append(" ");
            if (array != null) {
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != null) {
                        final String s = array[i];
                        if (!s.endsWith(":") && !s.endsWith(": ")) {
                            sb2.append(s);
                            sb2.append(",");
                        }
                        else {
                            sb2.append(s);
                        }
                    }
                }
            }
            Log.i(string, sb2.toString());
        }
    }
    
    public static void d(final boolean l) {
        i.L = l;
    }
    
    public static boolean k() {
        return i.K;
    }
    
    public static boolean l() {
        return i.L;
    }
}
