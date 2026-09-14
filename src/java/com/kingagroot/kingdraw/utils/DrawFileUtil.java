package com.kingagroot.kingdraw.utils;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import java.io.OutputStream;
import android.net.Uri;
import android.content.ContentResolver;
import android.provider.MediaStore$Images$Media;
import android.os.Environment;
import android.os.Build$VERSION;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.base.MApplication;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.core.image.DrawOption;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import android.content.Context;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import java.util.List;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.goodsrc.ui.library.BaseActivity;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import android.text.TextUtils;
import com.goodsrc.library.utils.DateTimeUtils;
import com.kingagroot.kingdraw.model.FileType;

public class DrawFileUtil
{
    private static final String AUTO_NAME = "KingDraw";
    public static final String FORMAT_FILE_NAME = "yyMMddHHmmss";
    
    public static boolean checkFileReadEnable(String extension) {
        extension = getExtension(extension);
        return extension.equals((Object)FileType.KING.extension) || extension.equals((Object)FileType.MOL_V2000.extension) || extension.equals((Object)FileType.MOL_V3000.extension) || extension.equals((Object)FileType.CDX.extension) || extension.equals((Object)FileType.KDX.extension);
    }
    
    public static String getAutoName() {
        final StringBuilder sb = new StringBuilder();
        sb.append("KingDraw");
        sb.append(DateTimeUtils.format(System.currentTimeMillis(), "yyMMddHHmmss"));
        return sb.toString();
    }
    
    public static String getExtension(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex > 0) {
            return s.substring(lastIndex);
        }
        return "";
    }
    
    public static String getFileContent(String s) {
        try {
            final StringBuilder sb = new StringBuilder();
            final File file = new File(s);
            if (file.exists()) {
                final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file)));
                while (true) {
                    s = bufferedReader.readLine();
                    if (s == null) {
                        break;
                    }
                    sb.append(s);
                    sb.append("\n");
                }
            }
            s = sb.toString();
            return s;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public static String getFileNameNoExtension(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        final int lastIndex = s.lastIndexOf(".");
        String substring = s;
        if (lastIndex > 0) {
            substring = s.substring(0, lastIndex);
        }
        return substring;
    }
    
    public static boolean saveBitmapToPicture(final Bitmap bitmap, final String s, final int n, final Bitmap$CompressFormat bitmap$CompressFormat) {
        if (bitmap == null) {
            return false;
        }
        String s2;
        if (bitmap$CompressFormat == Bitmap$CompressFormat.PNG) {
            s2 = "image/png";
        }
        else if (bitmap$CompressFormat == Bitmap$CompressFormat.WEBP) {
            s2 = "image/webp";
        }
        else {
            s2 = "image/jpeg";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append("_");
        sb.append(System.currentTimeMillis());
        sb.append(".");
        sb.append(bitmap$CompressFormat.name());
        final String string = sb.toString();
        final ContentValues contentValues = new ContentValues();
        final long n2 = System.currentTimeMillis() / 1000L;
        contentValues.put("_display_name", string);
        contentValues.put("mime_type", s2);
        contentValues.put("_size", Integer.valueOf(1));
        contentValues.put("date_added", Long.valueOf(n2));
        contentValues.put("date_modified", Long.valueOf(n2));
        if (Build$VERSION.SDK_INT >= 29) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(System.currentTimeMillis());
            sb2.append("");
            contentValues.put("datetaken", sb2.toString());
            contentValues.put("relative_path", Environment.DIRECTORY_PICTURES);
        }
        else {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            sb3.append(File.separator);
            sb3.append(string);
            contentValues.put("_data", sb3.toString());
        }
        final ContentResolver contentResolver = MApplication.getInstance().getContentResolver();
        final Uri insert = contentResolver.insert(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            try {
                final OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                if (openOutputStream != null) {
                    bitmap.compress(bitmap$CompressFormat, n, openOutputStream);
                    openOutputStream.close();
                }
                return true;
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public static void savePicByFile(final BaseActivity baseActivity, final KingDrawView kingDrawView) {
        final XXPermissions with = XXPermissions.with((Context)baseActivity);
        if (Build$VERSION.SDK_INT >= 33) {
            with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        with.request((OnPermissionCallback)new _$$Lambda$DrawFileUtil$nwBaEdMW_g09KyrfPMrmJ4_NU8U(baseActivity, kingDrawView));
    }
    
    public static void savePicByFile(final BaseActivity baseActivity, final String s, final boolean b) {
        final XXPermissions with = XXPermissions.with((Context)baseActivity);
        if (Build$VERSION.SDK_INT >= 33) {
            with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        with.request((OnPermissionCallback)new DrawFileUtil$1(b, s, baseActivity));
    }
    
    public static void savePicBySelect(final BaseActivity baseActivity, final KingDrawView kingDrawView, final boolean b) {
        final XXPermissions with = XXPermissions.with((Context)baseActivity);
        if (Build$VERSION.SDK_INT >= 33) {
            with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        with.request((OnPermissionCallback)new _$$Lambda$DrawFileUtil$x8oywYQ6uATreO2JT_NMLG6xmQM(kingDrawView, b));
    }
}
