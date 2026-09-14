package com.luck.picture.lib.utils;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import com.luck.picture.lib.basic.PictureContentResolver;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.content.Context;

public class SandboxTransformUtils
{
    public static String copyPathToSandbox(final Context context, final String s, final String s2) {
        return copyPathToSandbox(context, s, s2, "");
    }
    
    public static String copyPathToSandbox(final Context context, final String s, String filePath, final String s2) {
        try {
            if (PictureMimeType.isHasHttp(s)) {
                return null;
            }
            filePath = PictureFileUtils.createFilePath(context, filePath, s2);
            Object openInputStream;
            if (PictureMimeType.isContent(s)) {
                openInputStream = PictureContentResolver.openInputStream(context, Uri.parse(s));
            }
            else {
                openInputStream = new FileInputStream(s);
            }
            if (PictureFileUtils.writeFileFromIS((InputStream)openInputStream, (OutputStream)new FileOutputStream(filePath))) {
                return filePath;
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
