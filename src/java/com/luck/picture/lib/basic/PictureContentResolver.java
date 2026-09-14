package com.luck.picture.lib.basic;

import java.io.OutputStream;
import java.io.InputStream;
import android.net.Uri;
import android.content.Context;

public final class PictureContentResolver
{
    public static InputStream openInputStream(final Context context, final Uri uri) {
        try {
            return context.getContentResolver().openInputStream(uri);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static OutputStream openOutputStream(final Context context, final Uri uri) {
        try {
            return context.getContentResolver().openOutputStream(uri);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
