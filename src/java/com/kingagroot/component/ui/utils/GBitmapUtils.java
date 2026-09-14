package com.kingagroot.component.ui.utils;

import android.graphics.BitmapFactory;
import com.kingagroot.component.ui.R;
import android.text.TextUtils;
import android.content.Context;
import android.util.Base64;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;

public class GBitmapUtils
{
    public static String bitmaptoString(final Bitmap bitmap, final int n) {
        if (bitmap == null) {
            return "";
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap$CompressFormat.PNG, n, (OutputStream)byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
    
    public static int getResId(final Context context, final String s) {
        if (!TextUtils.isEmpty((CharSequence)s) && s.equals((Object)"ic_tool_colors")) {
            return R.mipmap.ic_tool_colors;
        }
        return context.getResources().getIdentifier(s, "drawable", context.getPackageName());
    }
    
    public static Bitmap stringtoBitmap(final String s) {
        Bitmap decodeByteArray;
        try {
            final byte[] decode = Base64.decode(s, 0);
            decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            decodeByteArray = null;
        }
        return decodeByteArray;
    }
}
