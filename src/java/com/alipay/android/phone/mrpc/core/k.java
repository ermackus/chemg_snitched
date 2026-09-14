package com.alipay.android.phone.mrpc.core;

import java.util.regex.Matcher;
import android.text.format.Time;
import java.util.regex.Pattern;

public final class k
{
    public static final Pattern a;
    public static final Pattern b;
    
    static {
        a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
        b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");
    }
    
    public static long a(final String s) {
        final Matcher matcher = k.a.matcher((CharSequence)s);
        int n;
        int n2;
        int n3;
        a a;
        if (matcher.find()) {
            n = b(matcher.group(1));
            n2 = c(matcher.group(2));
            n3 = d(matcher.group(3));
            a = e(matcher.group(4));
        }
        else {
            final Matcher matcher2 = k.b.matcher((CharSequence)s);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            n2 = c(matcher2.group(1));
            n = b(matcher2.group(2));
            a = e(matcher2.group(3));
            n3 = d(matcher2.group(4));
        }
        int n4;
        int n5;
        if (n3 >= 2038) {
            n = 1;
            n4 = 0;
            n5 = 2038;
        }
        else {
            final int n6 = n2;
            n5 = n3;
            n4 = n6;
        }
        final Time time = new Time("UTC");
        time.set(a.c, a.b, a.a, n, n4, n5);
        return time.toMillis(false);
    }
    
    public static int b(final String s) {
        if (s.length() == 2) {
            return (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        }
        return s.charAt(0) - '0';
    }
    
    public static int c(final String s) {
        final int n = Character.toLowerCase(s.charAt(0)) + Character.toLowerCase(s.charAt(1)) + Character.toLowerCase(s.charAt(2)) - 291;
        int n2 = 9;
        if (n == 9) {
            return 11;
        }
        if (n == 10) {
            return 1;
        }
        if (n == 22) {
            return 0;
        }
        if (n == 26) {
            return 7;
        }
        if (n == 29) {
            return 2;
        }
        if (n == 32) {
            return 3;
        }
        if (n == 40) {
            return 6;
        }
        if (n == 42) {
            return 5;
        }
        if (n == 48) {
            return 10;
        }
        switch (n) {
            default: {
                throw new IllegalArgumentException();
            }
            case 37: {
                return 8;
            }
            case 36: {
                n2 = 4;
            }
            case 35: {
                return n2;
            }
        }
    }
    
    public static int d(final String s) {
        if (s.length() == 2) {
            final int n = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
            if (n >= 70) {
                return n + 1900;
            }
            return n + 2000;
        }
        else {
            if (s.length() == 3) {
                return (s.charAt(0) - '0') * 100 + (s.charAt(1) - '0') * 10 + (s.charAt(2) - '0') + 1900;
            }
            if (s.length() == 4) {
                return (s.charAt(0) - '0') * 1000 + (s.charAt(1) - '0') * 100 + (s.charAt(2) - '0') * 10 + (s.charAt(3) - '0');
            }
            return 1970;
        }
    }
    
    public static a e(final String s) {
        int n = s.charAt(0) - '0';
        int n2;
        if (s.charAt(1) != ':') {
            n2 = 2;
            n = n * 10 + (s.charAt(1) - '0');
        }
        else {
            n2 = 1;
        }
        final int n3 = n2 + 1;
        final int n4 = n3 + 1;
        final char char1 = s.charAt(n3);
        final char char2 = s.charAt(n4);
        final int n5 = n4 + 1 + 1;
        return new a(n, (char1 - '0') * 10 + (char2 - '0'), (s.charAt(n5) - '0') * 10 + (s.charAt(n5 + 1) - '0'));
    }
    
    public static final class a
    {
        public int a;
        public int b;
        public int c;
        
        public a(final int a, final int b, final int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
