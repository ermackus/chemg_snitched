package com.goodsrc.library.utils;

import java.text.DateFormat;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtils
{
    static final boolean $assertionsDisabled = false;
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_CHINA = "yyyy\u5e74MM\u6708dd\u65e5";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_CHINA = "yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss";
    public static final String FORMAT_DATE_TIME_CHINA_M = "yyyy\u5e74MM\u6708dd\u65e5 HH:mm";
    public static final String FORMAT_DATE_TIME_M = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_TIME_M = "HH:mm";
    
    public static long calculationTime(long n, long n2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String format = simpleDateFormat.format((Object)n);
        final String format2 = simpleDateFormat.format((Object)n2);
        final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = null;
        Date parse;
        try {
            parse = ((DateFormat)simpleDateFormat2).parse(format);
            try {
                final Date parse2 = ((DateFormat)simpleDateFormat2).parse(format2);
            }
            catch (final ParseException ex) {}
        }
        catch (final ParseException ex) {
            parse = null;
        }
        final ParseException ex;
        ex.printStackTrace();
        final Date parse2 = date;
        n = parse.getTime() - parse2.getTime();
        n2 = n / 86400000L;
        return n / 3600000L - n2 * 24L;
    }
    
    public static long calculationTimeDay(final long n, final long n2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String format = simpleDateFormat.format((Object)n);
        final String format2 = simpleDateFormat.format((Object)n2);
        final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = null;
        Date parse;
        try {
            parse = ((DateFormat)simpleDateFormat2).parse(format);
            try {
                final Date parse2 = ((DateFormat)simpleDateFormat2).parse(format2);
            }
            catch (final ParseException ex) {}
        }
        catch (final ParseException ex) {
            parse = null;
        }
        final ParseException ex;
        ex.printStackTrace();
        final Date parse2 = date;
        return (parse.getTime() - parse2.getTime()) / 86400000L;
    }
    
    public static String format(final long n, String format) {
        try {
            format = new SimpleDateFormat(format).format((Object)n);
        }
        catch (final Exception ex) {
            format = "";
        }
        return format;
    }
    
    public static String format(String format, final String s) {
        final String s2 = "";
        String replace = format;
        try {
            if (format.startsWith("/")) {
                replace = format.replace((CharSequence)"/", (CharSequence)"").replace((CharSequence)"Date", (CharSequence)"").replace((CharSequence)"(", (CharSequence)"").replace((CharSequence)")", (CharSequence)"");
            }
            format = new SimpleDateFormat(s).format(new Date((long)Long.parseLong(replace)));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            format = s2;
        }
        return format;
    }
    
    public static String format(final Date date, final String s) {
        String format;
        try {
            format = new SimpleDateFormat(s).format(date);
        }
        catch (final Exception ex) {
            format = "";
        }
        return format;
    }
    
    public static long getStringToDate(final String s, String s2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s2);
        s2 = (String)new Date();
        Object parse;
        try {
            parse = simpleDateFormat.parse(s);
        }
        catch (final ParseException ex) {
            ex.printStackTrace();
            parse = s2;
        }
        return ((Date)parse).getTime();
    }
    
    public static int timeCompare(final String s, final String s2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final int n = 0;
        try {
            final Date parse = simpleDateFormat.parse(s);
            final Date parse2 = simpleDateFormat.parse(s2);
            int n2 = n;
            if (parse2 != null) {
                n2 = n;
                if (parse != null) {
                    if (parse2.getTime() < parse.getTime()) {
                        n2 = 1;
                    }
                    else if (parse2.getTime() == parse.getTime()) {
                        n2 = 2;
                    }
                    else {
                        final long time = parse2.getTime();
                        final long time2 = parse.getTime();
                        n2 = n;
                        if (time > time2) {
                            n2 = 3;
                        }
                    }
                }
            }
            return n2;
        }
        catch (final Exception ex) {
            return n;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeFormat {
    }
}
