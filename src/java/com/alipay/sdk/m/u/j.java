package com.alipay.sdk.m.u;

import android.preference.PreferenceManager;
import com.alipay.sdk.m.s.a;
import android.text.TextUtils;
import android.content.Context;

public class j
{
    public static String a;
    
    public static String a(final Context context) {
        if (TextUtils.isEmpty((CharSequence)j.a)) {
            String s = null;
            try {
                context.getApplicationContext().getPackageName();
            }
            finally {
                final Throwable t;
                e.a(t);
                s = "";
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("0000000000000000000000000000");
            j.a = sb.toString().substring(0, 24);
        }
        return j.a;
    }
    
    public static String a(final a a, Context context, final String s, String a2) {
        final Class<j> clazz;
        monitorenter(clazz = j.class);
        final Context context2 = null;
        final String s2 = null;
        Object o = context2;
        try {
            try {
                final String string = PreferenceManager.getDefaultSharedPreferences(context).getString(s, a2);
                a2 = s2;
                o = context2;
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    o = context2;
                    a2 = com.alipay.sdk.m.n.e.a(a(context), string, s);
                }
                o = a2;
                context = (Context)a2;
                if (TextUtils.isEmpty((CharSequence)string)) {}
                o = a2;
                final boolean empty = TextUtils.isEmpty((CharSequence)a2);
                context = (Context)a2;
                if (empty) {
                    o = a2;
                    com.alipay.sdk.m.k.a.b(a, "cp", "TriDesEncryptError", String.format("%s,%s", new Object[] { s, string }));
                    context = (Context)a2;
                }
            }
            finally {}
        }
        catch (final Exception ex) {
            e.a((Throwable)ex);
            context = (Context)o;
        }
        monitorexit(clazz);
        return (String)context;
        monitorexit(clazz);
    }
    
    public static boolean a(final Context context, final String s) {
        final Class<j> clazz;
        monitorenter(clazz = j.class);
        final Throwable t2;
        try {
            PreferenceManager.getDefaultSharedPreferences(context).contains(s);
            return;
        }
        finally {
            final Throwable t = t2;
            e.a(t);
            final boolean b;
            final boolean b2 = b = false;
        }
        try {
            final Throwable t = t2;
            e.a(t);
            return false;
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    public static void b(final Context context, final String s) {
        final Class<j> clazz;
        monitorenter(clazz = j.class);
        final Throwable t2;
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(s).apply();
            return;
        }
        finally {
            final Throwable t = t2;
            e.a(t);
        }
        try {
            final Throwable t = t2;
            e.a(t);
        }
        finally {
            monitorexit(clazz);
        }
    }
    
    public static void b(final a a, final Context context, final String s, final String s2) {
        final Class<j> clazz;
        monitorenter(clazz = j.class);
        final Throwable t2;
        try {
            final String b = com.alipay.sdk.m.n.e.b(a(context), s2, s);
            if (!TextUtils.isEmpty((CharSequence)s2) && TextUtils.isEmpty((CharSequence)b)) {
                com.alipay.sdk.m.k.a.b(a, "cp", "TriDesDecryptError", String.format("%s,%s", new Object[] { s, s2 }));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(s, b).apply();
            return;
        }
        finally {
            final Throwable t = t2;
            e.a(t);
        }
        try {
            final Throwable t = t2;
            e.a(t);
        }
        finally {
            monitorexit(clazz);
        }
    }
}
