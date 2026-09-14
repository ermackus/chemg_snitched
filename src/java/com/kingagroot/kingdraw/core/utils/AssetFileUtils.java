package com.kingagroot.kingdraw.core.utils;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import android.content.Context;

public class AssetFileUtils
{
    public static void copyAssetFile(final Context context, final String s, final String s2, String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s2);
        sb.append("/");
        sb.append(string);
        string = sb.toString();
        final File file = new File(s2);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            if (!new File(string).exists()) {
                final InputStream open = context.getResources().getAssets().open(s);
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                final byte[] array = new byte[1024];
                while (true) {
                    final int read = open.read(array);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(array, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                open.close();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
