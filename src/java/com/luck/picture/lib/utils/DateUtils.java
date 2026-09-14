package com.luck.picture.lib.utils;

import java.util.Calendar;
import java.util.Date;
import com.luck.picture.lib.R;
import android.content.Context;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class DateUtils
{
    private static final SimpleDateFormat SDF;
    private static final SimpleDateFormat SDF_YEAR;
    private static final SimpleDateFormat SF;
    
    static {
        SF = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SDF = new SimpleDateFormat("yyyy-MM");
        SDF_YEAR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public static String cdTime(long n, final long n2) {
        n = n2 - n;
        String s;
        if (n > 1000L) {
            final StringBuilder sb = new StringBuilder();
            sb.append(n / 1000L);
            sb.append("\u79d2");
            s = sb.toString();
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(n);
            sb2.append("\u6beb\u79d2");
            s = sb2.toString();
        }
        return s;
    }
    
    public static int dateDiffer(long abs) {
        try {
            abs = Math.abs(getCurrentTimeMillis() - abs);
            return (int)abs;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static String formatDurationTime(long n) {
        String s;
        if (n < 0L) {
            s = "-";
        }
        else {
            s = "";
        }
        final long n2 = Math.abs(n) / 1000L;
        n = n2 % 60L;
        final long n3 = n2 / 60L % 60L;
        final long n4 = n2 / 3600L;
        String s2;
        if (n4 > 0L) {
            s2 = String.format(Locale.getDefault(), "%s%d:%02d:%02d", new Object[] { s, n4, n3, n });
        }
        else {
            s2 = String.format(Locale.getDefault(), "%s%02d:%02d", new Object[] { s, n3, n });
        }
        return s2;
    }
    
    public static String getCreateFileName() {
        return DateUtils.SF.format((Object)System.currentTimeMillis());
    }
    
    public static String getCreateFileName(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(DateUtils.SF.format((Object)currentTimeMillis));
        return sb.toString();
    }
    
    public static long getCurrentTimeMillis() {
        String s2;
        final String s = s2 = ValueOf.toString(System.currentTimeMillis());
        if (s.length() > 10) {
            s2 = s.substring(0, 10);
        }
        return ValueOf.toLong(s2);
    }
    
    public static String getDataFormat(final Context context, long n) {
        if (String.valueOf(n).length() <= 10) {
            n *= 1000L;
        }
        if (isThisWeek(n)) {
            return context.getString(R.string.ps_current_week);
        }
        if (isThisMonth(n)) {
            return context.getString(R.string.ps_current_month);
        }
        return DateUtils.SDF.format((Object)n);
    }
    
    public static String getYearDataFormat(long n) {
        if (String.valueOf(n).length() <= 10) {
            n *= 1000L;
        }
        return DateUtils.SDF_YEAR.format((Object)n);
    }
    
    public static boolean isThisMonth(final long n) {
        return DateUtils.SDF.format(new Date(n)).equals((Object)DateUtils.SDF.format(new Date()));
    }
    
    private static boolean isThisWeek(final long n) {
        final Calendar instance = Calendar.getInstance();
        final int value = instance.get(3);
        instance.setTime(new Date(n));
        return instance.get(3) == value;
    }
    
    public static long millisecondToSecond(final long n) {
        return n / 1000L * 1000L;
    }
}
