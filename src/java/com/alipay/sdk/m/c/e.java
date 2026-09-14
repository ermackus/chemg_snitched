package com.alipay.sdk.m.c;

import android.database.Cursor;
import android.net.Uri;
import android.content.Context;
import com.alipay.sdk.m.b.b;

public class e implements b
{
    public static final String a = "content://cn.nubia.provider.deviceid.dataid/oaid";
    
    public String a(final Context context) {
        final String s = null;
        final String s2 = null;
        if (context == null) {
            return null;
        }
        final Cursor query = context.getContentResolver().query(Uri.parse("content://cn.nubia.provider.deviceid.dataid/oaid"), (String[])null, (String)null, (String[])null, (String)null);
        String string = s;
        if (query != null) {
            string = s2;
            if (query.moveToNext()) {
                string = query.getString(query.getColumnIndex("device_ids_grndid"));
            }
            query.close();
        }
        return string;
    }
}
