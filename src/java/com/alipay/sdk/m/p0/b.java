package com.alipay.sdk.m.p0;

import android.database.Cursor;
import android.util.Log;
import android.net.Uri;
import android.content.Context;

public class b
{
    public static final String b = "VMS_IDLG_SDK_DB";
    public static final String c = "content://com.vivo.vms.IdProvider/IdentifierId";
    public static final String d = "value";
    public static final String e = "OAID";
    public static final String f = "AAID";
    public static final String g = "VAID";
    public static final String h = "OAIDSTATUS";
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 4;
    public Context a;
    
    public b(final Context a) {
        this.a = a;
    }
    
    public String a(final int n, String string) {
        final String s = null;
        final String s2 = null;
        Uri uri;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 4) {
                        uri = null;
                    }
                    else {
                        uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS");
                    }
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("content://com.vivo.vms.IdProvider/IdentifierId/AAID_");
                    sb.append(string);
                    uri = Uri.parse(sb.toString());
                }
            }
            else {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("content://com.vivo.vms.IdProvider/IdentifierId/VAID_");
                sb2.append(string);
                uri = Uri.parse(sb2.toString());
            }
        }
        else {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID");
        }
        final Cursor query = this.a.getContentResolver().query(uri, (String[])null, (String)null, (String[])null, (String)null);
        if (query != null) {
            string = s2;
            if (query.moveToNext()) {
                string = query.getString(query.getColumnIndex("value"));
            }
            query.close();
        }
        else {
            Log.d("VMS_IDLG_SDK_DB", "return cursor is null,return");
            string = s;
        }
        return string;
    }
}
