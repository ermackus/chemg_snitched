package com.tencent.mm.opensdk.utils;

import android.net.Uri;
import android.provider.BaseColumns;

public final class a implements BaseColumns
{
    public static final Uri a;
    
    static {
        a = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
