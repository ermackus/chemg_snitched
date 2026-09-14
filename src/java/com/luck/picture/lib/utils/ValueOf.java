package com.luck.picture.lib.utils;

public class ValueOf
{
    public static <T> T to(final Object o, final T t) {
        if (o == null) {
            return t;
        }
        return (T)o;
    }
    
    public static boolean toBoolean(final Object o) {
        return toBoolean(o, false);
    }
    
    public static boolean toBoolean(final Object o, boolean b) {
        if (o == null) {
            return false;
        }
        try {
            b = ("false".equals((Object)o.toString().trim().trim()) ^ true);
            return b;
        }
        catch (final Exception ex) {
            return b;
        }
    }
    
    public static double toDouble(final Object o) {
        return toDouble(o, 0);
    }
    
    public static double toDouble(final Object o, final int n) {
        if (o == null) {
            return n;
        }
        double double1;
        try {
            double1 = Double.parseDouble(o.toString().trim());
        }
        catch (final Exception ex) {
            double1 = n;
        }
        return double1;
    }
    
    public static float toFloat(final Object o) {
        return toFloat(o, 0L);
    }
    
    public static float toFloat(final Object o, final long n) {
        if (o == null) {
            return (float)n;
        }
        float float1;
        try {
            float1 = Float.parseFloat(o.toString().trim());
        }
        catch (final Exception ex) {
            float1 = (float)n;
        }
        return float1;
    }
    
    public static int toInt(final Object o) {
        return toInt(o, 0);
    }
    
    public static int toInt(final Object o, int n) {
        if (o == null) {
            return n;
        }
        try {
            final String trim = o.toString().trim();
            if (trim.contains((CharSequence)".")) {
                n = Integer.parseInt(trim.substring(0, trim.lastIndexOf(".")));
            }
            else {
                n = Integer.parseInt(trim);
            }
            return n;
        }
        catch (final Exception ex) {
            return n;
        }
    }
    
    public static long toLong(final Object o) {
        return toLong(o, 0L);
    }
    
    public static long toLong(final Object o, long n) {
        if (o == null) {
            return n;
        }
        try {
            final String trim = o.toString().trim();
            if (trim.contains((CharSequence)".")) {
                n = Long.parseLong(trim.substring(0, trim.lastIndexOf(".")));
            }
            else {
                n = Long.parseLong(trim);
            }
            return n;
        }
        catch (final Exception ex) {
            return n;
        }
    }
    
    public static String toString(final Object o) {
        String string;
        try {
            string = o.toString();
        }
        catch (final Exception ex) {
            string = "";
        }
        return string;
    }
}
