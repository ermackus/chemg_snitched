package com.alipay.apmobilesecuritysdk.common;

import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import com.alipay.apmobilesecuritysdk.e.h;
import java.util.ArrayList;
import android.content.Context;

public final class a
{
    public static boolean a(final Context context) {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)h.e(context));
        return a((List<String>)list) || a((List<String>)new RushTimeUtil$1());
    }
    
    public static boolean a(final List<String> list) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setLenient(false);
        final int n = (int)(Math.random() * 24.0 * 60.0 * 60.0);
        try {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                final String[] split = ((String)iterator.next()).split("&");
                if (split != null && split.length == 2) {
                    final Date date = new Date();
                    final StringBuilder sb = new StringBuilder();
                    sb.append(split[0]);
                    sb.append(" 00:00:00");
                    final Date parse = simpleDateFormat.parse(sb.toString());
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(split[1]);
                    sb2.append(" 23:59:59");
                    final Date parse2 = simpleDateFormat.parse(sb2.toString());
                    final Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, n * 1);
                    final Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                    continue;
                }
            }
            return false;
        }
        catch (final Exception ex) {
            return false;
        }
    }
}
