package com.alipay.sdk.m.i0;

import android.text.TextUtils;
import android.os.Handler;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.content.Context;
import android.util.Log;
import android.content.pm.ProviderInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.content.BroadcastReceiver;

public class f
{
    public static volatile f g;
    public static boolean h;
    public a a;
    public a b;
    public a c;
    public a d;
    public c e;
    public BroadcastReceiver f;
    
    public f() {
        this.a = new a("udid");
        this.b = new a("oaid");
        this.d = new a("vaid");
        this.c = new a("aaid");
        this.e = new c();
    }
    
    public static d a(final Cursor cursor) {
        final d d = new d(null, 0);
        String s;
        if (cursor == null) {
            s = "parseValue fail, cursor is null.";
        }
        else {
            if (!cursor.isClosed()) {
                cursor.moveToFirst();
                final int columnIndex = cursor.getColumnIndex("value");
                if (columnIndex >= 0) {
                    d.a = cursor.getString(columnIndex);
                }
                else {
                    a("parseValue fail, index < 0.");
                }
                final int columnIndex2 = cursor.getColumnIndex("code");
                if (columnIndex2 >= 0) {
                    d.b = cursor.getInt(columnIndex2);
                }
                else {
                    a("parseCode fail, index < 0.");
                }
                final int columnIndex3 = cursor.getColumnIndex("expired");
                if (columnIndex3 >= 0) {
                    d.c = cursor.getLong(columnIndex3);
                }
                else {
                    a("parseExpired fail, index < 0.");
                }
                return d;
            }
            s = "parseValue fail, cursor is closed.";
        }
        a(s);
        return d;
    }
    
    public static final f a() {
        if (f.g == null) {
            synchronized (f.class) {
                if (f.g == null) {
                    f.g = new f();
                }
            }
        }
        return f.g;
    }
    
    public static String a(final PackageManager packageManager, final String s) {
        if (packageManager == null) {
            return null;
        }
        final ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(s, 0);
        if (resolveContentProvider == null) {
            return null;
        }
        if ((resolveContentProvider.applicationInfo.flags & 0x1) == 0x0) {
            return null;
        }
        return resolveContentProvider.packageName;
    }
    
    public static void a(final String s) {
        if (f.h) {
            Log.d("OpenIdManager", s);
        }
    }
    
    public static void a(final boolean h) {
        f.h = h;
    }
    
    public static boolean a(final Context context) {
        a("querySupport version : 1.0.8");
        final Uri parse = Uri.parse("content://com.meizu.flyme.openidsdk/");
        boolean b = false;
        Cursor cursor = null;
        Cursor cursor2 = null;
        try {
            Label_0176: {
                try {
                    final Cursor query = context.getContentResolver().query(parse, (String[])null, (String)null, new String[] { "supported" }, (String)null);
                    if (query != null) {
                        cursor2 = query;
                        cursor = query;
                        final d a = a(query);
                        cursor2 = query;
                        cursor = query;
                        Label_0100: {
                            if (1000 == a.b) {
                                cursor2 = query;
                                cursor = query;
                                if (!"0".equals((Object)a.a)) {
                                    break Label_0100;
                                }
                            }
                            b = true;
                        }
                        if (query != null) {
                            query.close();
                        }
                        return b;
                    }
                    if (query != null) {
                        break Label_0176;
                    }
                    return false;
                }
                finally {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    final Cursor query = cursor;
                    query.close();
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    private String b(final Context context, a a) {
        final StringBuilder sb = new StringBuilder("queryId : ");
        sb.append(a.c);
        a(sb.toString());
        Object o = Uri.parse("content://com.meizu.flyme.openidsdk/");
        final a a2 = null;
        final Cursor cursor = null;
        Object a3 = null;
        Object o2 = null;
        final a a4 = null;
        Label_0445: {
            Object o3 = null;
            try {
                try {
                    o = context.getContentResolver().query((Uri)o, (String[])null, (String)null, new String[] { a.c }, (String)null);
                    Label_0292: {
                        if (o == null) {
                            break Label_0292;
                        }
                        a3 = cursor;
                        try {
                            final d a5 = a((Cursor)o);
                            a3 = cursor;
                            o2 = (a3 = a5.a);
                            a.a((String)o2);
                            a3 = o2;
                            a.a(a5.c);
                            a3 = o2;
                            a.a(a5.b);
                            a3 = o2;
                            a3 = o2;
                            final StringBuilder sb2 = new StringBuilder();
                            a3 = o2;
                            sb2.append(a.c);
                            a3 = o2;
                            sb2.append(" errorCode : ");
                            a3 = o2;
                            sb2.append(a.d);
                            a3 = o2;
                            a(sb2.toString());
                            a = (a)o2;
                            a3 = o2;
                            while (true) {
                                if (a5.b != 1000) {
                                    a3 = o2;
                                    this.b(context);
                                    a = (a)o2;
                                    a3 = o2;
                                    if (!this.a(context, false)) {
                                        a3 = o2;
                                        final boolean a6 = this.a(context, true);
                                        final String s = "not support, forceQuery isSupported: ";
                                        a3 = o2;
                                        final String s2 = String.valueOf(a6);
                                        a = (a)o2;
                                        a3 = a;
                                        final String concat = s.concat(s2);
                                        a3 = a;
                                        a(concat);
                                    }
                                }
                                Label_0349: {
                                    if (o != null) {
                                        break Label_0445;
                                    }
                                }
                                return (String)o3;
                                a3 = cursor;
                                final boolean a7 = this.a(context, true);
                                o2 = "forceQuery isSupported : ";
                                a3 = cursor;
                                final String s2 = String.valueOf(a7);
                                a = a4;
                                final String s = (String)o2;
                                continue;
                            }
                            a = a2;
                            a3 = cursor;
                            iftrue(Label_0349:)(!this.a(context, false));
                        }
                        catch (final Exception o2) {
                            a = (a)o;
                        }
                        finally {
                            a3 = o;
                        }
                    }
                }
                finally {}
            }
            catch (final Exception o) {
                o3 = null;
                a = (a)o2;
            }
            final StringBuilder sb3 = new StringBuilder("queryId, Exception : ");
            sb3.append(((Exception)o).getMessage());
            a(sb3.toString());
            if (a == null) {
                return (String)o3;
            }
            o = a;
            a = (a)o3;
        }
        ((Cursor)o).close();
        Object o3 = a;
        return (String)o3;
        if (a3 != null) {
            ((Cursor)a3).close();
        }
    }
    
    public static String b(final PackageManager packageManager, final String s) {
        try {
            final PackageInfo packageInfo = packageManager.getPackageInfo(s, 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            final StringBuilder sb = new StringBuilder("getAppVersion, Exception : ");
            sb.append(ex.getMessage());
            a(sb.toString());
        }
        return null;
    }
    
    private void b(final Context context) {
        synchronized (this) {
            if (this.f != null) {
                return;
            }
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.meizu.flyme.openid.ACTION_OPEN_ID_CHANGE");
            context.registerReceiver(this.f = new e(), intentFilter, "com.meizu.flyme.openid.permission.OPEN_ID_CHANGE", (Handler)null);
        }
    }
    
    public final String a(final Context context, final a a) {
        String s;
        if (a == null) {
            s = "getId, openId = null.";
        }
        else {
            if (a.a()) {
                return a.b;
            }
            if (this.a(context, true)) {
                return this.b(context, a);
            }
            s = "getId, isSupported = false.";
        }
        a(s);
        return null;
    }
    
    public final boolean a(final Context context, final boolean b) {
        if (this.e.a() && !b) {
            return this.e.b();
        }
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        final String a = a(packageManager, "com.meizu.flyme.openidsdk");
        if (TextUtils.isEmpty((CharSequence)a)) {
            return false;
        }
        final String b2 = b(packageManager, a);
        if (this.e.a() && this.e.a(b2)) {
            a("use same version cache, safeVersion : ".concat(String.valueOf((Object)b2)));
            return this.e.b();
        }
        this.e.b(b2);
        final boolean a2 = a(context);
        a("query support, result : ".concat(String.valueOf(a2)));
        this.e.a(a2);
        return a2;
    }
}
