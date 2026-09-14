package com.alipay.sdk.m.b0;

import java.io.File;
import android.os.Environment;

public final class c
{
    public static String a(final String s) {
        try {
            if (a()) {
                final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), s);
                if (file.exists()) {
                    file.delete();
                    return "";
                }
            }
            return null;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static boolean a() {
        final String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.length() > 0 && (externalStorageState.equals((Object)"mounted") || externalStorageState.equals((Object)"mounted_ro")) && Environment.getExternalStorageDirectory() != null;
    }
}
