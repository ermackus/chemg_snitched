package com.tencent.open.log;

import android.util.Log;
import android.text.format.Time;

public final class g
{
    public static final g a;
    
    static {
        a = new g();
    }
    
    public final String a(final int n) {
        if (n == 1) {
            return "V";
        }
        if (n == 2) {
            return "D";
        }
        if (n == 4) {
            return "I";
        }
        if (n == 8) {
            return "W";
        }
        if (n == 16) {
            return "E";
        }
        if (n != 32) {
            return "-";
        }
        return "A";
    }
    
    public String a(final int n, final Thread thread, final long n2, final String s, final String s2, final Throwable t) {
        final long n3 = n2 % 1000L;
        final Time time = new Time();
        time.set(n2);
        final StringBuilder sb = new StringBuilder();
        sb.append(this.a(n));
        sb.append('/');
        sb.append(time.format("%Y-%m-%d %H:%M:%S"));
        sb.append('.');
        if (n3 < 10L) {
            sb.append("00");
        }
        else if (n3 < 100L) {
            sb.append('0');
        }
        sb.append(n3);
        sb.append(' ');
        sb.append('[');
        if (thread == null) {
            sb.append("N/A");
        }
        else {
            sb.append(thread.getName());
        }
        sb.append(']');
        sb.append('[');
        sb.append(s);
        sb.append(']');
        sb.append(' ');
        sb.append(s2);
        sb.append('\n');
        if (t != null) {
            sb.append("* Exception : \n");
            sb.append(Log.getStackTraceString(t));
            sb.append('\n');
        }
        return sb.toString();
    }
}
