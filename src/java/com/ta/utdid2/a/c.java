package com.ta.utdid2.a;

import com.ta.utdid2.b.a.b;
import android.content.SharedPreferences;
import com.ta.utdid2.b.a.h;
import android.os.Build$VERSION;
import com.ta.utdid2.b.a.i;
import com.ta.utdid2.b.a.d;
import android.util.Log;
import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class c
{
    private static final String TAG;
    private static Map<String, String> a;
    private static Map<String, Long> b;
    
    static {
        TAG = c.class.getName();
        c.a = (Map<String, String>)new ConcurrentHashMap();
        c.b = (Map<String, Long>)new ConcurrentHashMap();
    }
    
    public static long a(final Context context, final String s, final String s2) {
        if (context == null) {
            Log.e(c.TAG, "no context!");
            return 0L;
        }
        final String c = c(s, s2);
        long longValue;
        if (com.ta.utdid2.a.c.b.containsKey((Object)c)) {
            longValue = (long)com.ta.utdid2.a.c.b.get((Object)c);
        }
        else {
            longValue = 0L;
        }
        final Long value = longValue;
        if (d.e) {
            final String tag = com.ta.utdid2.a.c.TAG;
            final StringBuilder sb = new StringBuilder("cache AIDGenTime:");
            sb.append((Object)value);
            Log.d(tag, sb.toString());
        }
        Long value2 = value;
        if (value == 0L) {
            value2 = context.getSharedPreferences("OfJbkLdFbPOMbGyP", 0).getLong("rKrMJgyAEbVtSQGi".concat(c), 0L);
            com.ta.utdid2.a.c.b.put((Object)c, (Object)value2);
        }
        return value2;
    }
    
    public static String a(final Context context, String string, String s) {
        if (context == null) {
            Log.e(c.TAG, "no context!");
            return "";
        }
        final String c = c(string, s);
        s = (String)com.ta.utdid2.a.c.a.get((Object)c);
        if (d.e) {
            final String tag = com.ta.utdid2.a.c.TAG;
            final StringBuilder sb = new StringBuilder("cache AID:");
            sb.append(s);
            Log.d(tag, sb.toString());
        }
        string = s;
        if (i.a(s)) {
            string = context.getSharedPreferences("OfJbkLdFbPOMbGyP", 0).getString("EvQwnbilKezpOJey".concat(c), "");
            com.ta.utdid2.a.c.a.put((Object)c, (Object)string);
        }
        return string;
    }
    
    public static void a(final Context context, String c, final String s, final String s2) {
        if (context == null) {
            Log.e(c.TAG, "no context!");
            return;
        }
        c = c(c, s2);
        final long currentTimeMillis = System.currentTimeMillis();
        c.a.put((Object)c, (Object)s);
        c.b.put((Object)c, (Object)currentTimeMillis);
        final SharedPreferences sharedPreferences = context.getSharedPreferences("OfJbkLdFbPOMbGyP", 0);
        if (Build$VERSION.SDK_INT >= 9) {
            h.a(sharedPreferences.edit().putString("EvQwnbilKezpOJey".concat(c), s));
            h.a(sharedPreferences.edit().putLong("rKrMJgyAEbVtSQGi".concat(c), currentTimeMillis));
        }
        else {
            sharedPreferences.edit().putString("EvQwnbilKezpOJey".concat(c), s).commit();
            sharedPreferences.edit().putLong("rKrMJgyAEbVtSQGi".concat(c), currentTimeMillis).commit();
        }
    }
    
    private static String c(String s, String tag) {
        if (Build$VERSION.SDK_INT >= 8) {
            s = com.ta.utdid2.b.a.c.encodeToString(s.concat(tag).getBytes(), 2);
        }
        else {
            s = com.ta.utdid2.b.a.b.encodeToString(s.concat(tag).getBytes(), 2);
        }
        if (d.e) {
            tag = c.TAG;
            final StringBuilder sb = new StringBuilder("encodedName:");
            sb.append(s);
            Log.d(tag, sb.toString());
        }
        return s;
    }
}
