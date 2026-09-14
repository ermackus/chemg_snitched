package com.alibaba.sdk.android.man.crashreporter.e;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class d
{
    public static String b(final long n) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return simpleDateFormat.format(new Date(n));
    }
}
