package com.alipay.sdk.m.u;

import android.text.TextUtils;
import com.alipay.sdk.m.l.a;
import com.alipay.sdk.app.EnvUtils;
import android.database.Cursor;
import android.net.Uri;
import android.content.Context;

public class m
{
    public static final String a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";
    
    public static String a(final Context context) {
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.alipay.android.app.settings.data.ServerProvider/current_server"), (String[])null, (String)null, (String[])null, (String)null);
        final String s = null;
        final String s2 = null;
        String string = s;
        if (query != null) {
            string = s;
            if (query.getCount() > 0) {
                string = s2;
                if (query.moveToFirst()) {
                    string = query.getString(query.getColumnIndex("url"));
                }
                query.close();
            }
        }
        return string;
    }
    
    public static String b(final Context context) {
        if (EnvUtils.isPreSandBox()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (EnvUtils.isNewSanBox()) {
            return "https://mobilegw.dl.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return com.alipay.sdk.m.l.a.a;
        }
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = com.alipay.sdk.m.l.a.a))) {
            s = com.alipay.sdk.m.l.a.a;
        }
        return s;
    }
}
