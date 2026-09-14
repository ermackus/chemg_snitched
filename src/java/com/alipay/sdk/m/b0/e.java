package com.alipay.sdk.m.b0;

import java.util.Iterator;
import android.content.SharedPreferences$Editor;
import java.util.Map;
import android.content.Context;

public final class e
{
    public static String a(final Context context, final String s, final String s2, final String s3) {
        return context.getSharedPreferences(s, 0).getString(s2, s3);
    }
    
    public static void a(final Context context, String s, final Map<String, String> map) {
        final SharedPreferences$Editor edit = context.getSharedPreferences(s, 0).edit();
        if (edit != null) {
            final Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                s = (String)iterator.next();
                edit.putString(s, (String)map.get((Object)s));
            }
            edit.commit();
        }
    }
}
