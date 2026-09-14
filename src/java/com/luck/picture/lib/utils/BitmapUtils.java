package com.luck.picture.lib.utils;

import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Matrix;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import java.io.Closeable;
import java.io.InputStream;
import androidx.exifinterface.media.ExifInterface;
import com.luck.picture.lib.basic.PictureContentResolver;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.content.Context;

public class BitmapUtils
{
    private static final int ARGB_8888_MEMORY_BYTE = 4;
    private static final int MAX_BITMAP_SIZE = 104857600;
    
    public static int computeSize(int n, int max) {
        final int n2 = 1;
        int n3 = n;
        if (n % 2 == 1) {
            n3 = n + 1;
        }
        n = max;
        if (max % 2 == 1) {
            n = max + 1;
        }
        max = Math.max(n3, n);
        final float n4 = Math.min(n3, n) / (float)max;
        if (n4 <= 1.0f && n4 > 0.5625) {
            if (max < 1664) {
                return 1;
            }
            if (max < 4990) {
                return 2;
            }
            if (max > 4990 && max < 10240) {
                return 4;
            }
            return max / 1280;
        }
        else {
            final double n5 = n4;
            if (n5 <= 0.5625 && n5 > 0.5) {
                n = max / 1280;
                if (n == 0) {
                    n = n2;
                }
                return n;
            }
            return (int)Math.ceil(max / (1280.0 / n5));
        }
    }
    
    public static int[] getMaxImageSize(final int n, final int n2) {
        if (n == 0 && n2 == 0) {
            return new int[] { -1, -1 };
        }
        int computeSize = computeSize(n, n2);
        final long totalMemory = getTotalMemory();
        int n3 = -1;
        int n4 = -1;
        int i = 0;
        while (i == 0) {
            n3 = n / computeSize;
            n4 = n2 / computeSize;
            if (n3 * n4 * 4 > totalMemory) {
                computeSize *= 2;
            }
            else {
                i = 1;
            }
        }
        return new int[] { n3, n4 };
    }
    
    public static long getTotalMemory() {
        long totalMemory;
        if ((totalMemory = Runtime.getRuntime().totalMemory()) > 104857600L) {
            totalMemory = 104857600L;
        }
        return totalMemory;
    }
    
    public static int readPictureDegree(final Context context, final String s) {
        final Closeable closeable = null;
        final Closeable closeable2 = null;
        final InputStream inputStream = null;
        Object openInputStream = closeable;
        Object o = closeable2;
        try {
            try {
                Object o2;
                ExifInterface exifInterface;
                if (PictureMimeType.isContent(s)) {
                    openInputStream = closeable;
                    o = closeable2;
                    o2 = (o = (openInputStream = PictureContentResolver.openInputStream(context, Uri.parse(s))));
                    exifInterface = new(androidx.exifinterface.media.ExifInterface.class)();
                    openInputStream = o2;
                    o = o2;
                    new ExifInterface((InputStream)o2);
                }
                else {
                    openInputStream = closeable;
                    o = closeable2;
                    exifInterface = new ExifInterface(s);
                    o2 = inputStream;
                }
                openInputStream = o2;
                o = o2;
                final int attributeInt = exifInterface.getAttributeInt("Orientation", 1);
                int n;
                if (attributeInt != 3) {
                    if (attributeInt != 6) {
                        if (attributeInt != 8) {
                            PictureFileUtils.close((Closeable)o2);
                            return 0;
                        }
                        n = 270;
                    }
                    else {
                        n = 90;
                    }
                }
                else {
                    n = 180;
                }
                PictureFileUtils.close((Closeable)o2);
                return n;
            }
            finally {}
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            PictureFileUtils.close((Closeable)o);
            return 0;
        }
        PictureFileUtils.close((Closeable)openInputStream);
    }
    
    public static void rotateImage(final Context context, String s) {
        Closeable closeable;
        Object o;
        Object o2 = null;
        Bitmap bitmap;
        int pictureDegree;
        BitmapFactory$Options bitmapFactory$Options;
        Object o3;
        FileOutputStream fileOutputStream;
        Closeable closeable2 = null;
        Closeable closeable3 = null;
        Object o5 = null;
        Object o4 = null;
        Label_0073_Outer:Label_0384_Outer:
        while (true) {
            closeable = null;
            o = null;
            o2 = null;
            bitmap = null;
            Label_0411: {
                while (true) {
                    try {
                        pictureDegree = readPictureDegree(context, s);
                        Label_0360: {
                            if (pictureDegree > 0) {
                                bitmapFactory$Options = new BitmapFactory$Options();
                                bitmapFactory$Options.inJustDecodeBounds = true;
                                while (true) {
                                    if (PictureMimeType.isContent(s)) {
                                        o3 = (o = (o2 = PictureContentResolver.openInputStream(context, Uri.parse(s))));
                                        Label_0350: {
                                            try {
                                                BitmapFactory.decodeStream((InputStream)o3, (Rect)null, bitmapFactory$Options);
                                                while (true) {
                                                    o2 = o3;
                                                    o = o3;
                                                    bitmapFactory$Options.inSampleSize = computeSize(bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight);
                                                    o2 = o3;
                                                    o = o3;
                                                    bitmapFactory$Options.inJustDecodeBounds = false;
                                                    o2 = o3;
                                                    o = o3;
                                                    if (PictureMimeType.isContent(s)) {
                                                        o2 = o3;
                                                        o = o3;
                                                        o3 = (o = (o2 = PictureContentResolver.openInputStream(context, Uri.parse(s))));
                                                        o = BitmapFactory.decodeStream((InputStream)o3, (Rect)null, bitmapFactory$Options);
                                                    }
                                                    else {
                                                        o2 = o3;
                                                        o = o3;
                                                        o = BitmapFactory.decodeFile(s, bitmapFactory$Options);
                                                    }
                                                    if (o != null) {
                                                        try {
                                                            o2 = rotatingImage((Bitmap)o, pictureDegree);
                                                            o = bitmap;
                                                            try {
                                                                if (PictureMimeType.isContent(s)) {
                                                                    o = bitmap;
                                                                    fileOutputStream = (FileOutputStream)PictureContentResolver.openOutputStream(context, Uri.parse(s));
                                                                }
                                                                else {
                                                                    o = bitmap;
                                                                    fileOutputStream = new FileOutputStream(s);
                                                                }
                                                                o = fileOutputStream;
                                                                saveBitmapFile((Bitmap)o2, fileOutputStream);
                                                            }
                                                            catch (final Exception ex) {}
                                                            finally {
                                                                o = o2;
                                                            }
                                                        }
                                                        catch (final Exception ex2) {
                                                            o2 = null;
                                                            o = o3;
                                                            o3 = o2;
                                                            break Label_0350;
                                                        }
                                                        finally {
                                                            goto Label_0335;
                                                        }
                                                    }
                                                    o2 = o;
                                                    break Label_0360;
                                                    BitmapFactory.decodeFile(s, bitmapFactory$Options);
                                                    o3 = null;
                                                    continue Label_0073_Outer;
                                                }
                                            }
                                            catch (final Exception ex3) {}
                                            finally {
                                                closeable2 = null;
                                                o = null;
                                            }
                                        }
                                        break Label_0411;
                                    }
                                    continue Label_0384_Outer;
                                }
                            }
                            else {
                                closeable2 = null;
                                o2 = null;
                                closeable3 = closeable;
                            }
                        }
                        PictureFileUtils.close(closeable3);
                        PictureFileUtils.close(closeable2);
                        if (o2 != null && !((Bitmap)o2).isRecycled()) {
                            s = (String)o2;
                            ((Bitmap)s).recycle();
                        }
                        return;
                    }
                    catch (final Exception o4) {
                        o5 = null;
                        s = null;
                    }
                    finally {
                        o4 = null;
                        s = null;
                        o5 = o2;
                        o2 = s;
                        break;
                    }
                    try {
                        ((Exception)o4).printStackTrace();
                        PictureFileUtils.close((Closeable)o);
                        PictureFileUtils.close((Closeable)o5);
                        if (s != null && !((Bitmap)s).isRecycled()) {
                            continue;
                        }
                        return;
                    }
                    finally {
                        o4 = o5;
                        o2 = s;
                        o5 = o;
                    }
                    break;
                }
            }
            break;
        }
        PictureFileUtils.close((Closeable)o5);
        PictureFileUtils.close((Closeable)o4);
        if (o2 != null && !((Bitmap)o2).isRecycled()) {
            ((Bitmap)o2).recycle();
        }
        throw;
    }
    
    public static Bitmap rotatingImage(final Bitmap bitmap, final int n) {
        final Matrix matrix = new Matrix();
        matrix.postRotate((float)n);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    
    private static void saveBitmapFile(final Bitmap bitmap, final FileOutputStream fileOutputStream) {
        final Closeable closeable = null;
        Object o = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Closeable closeable2;
        try {
            try {
                o = o;
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap$CompressFormat.JPEG, 60, (OutputStream)fileOutputStream);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    PictureFileUtils.close((Closeable)fileOutputStream);
                    PictureFileUtils.close((Closeable)byteArrayOutputStream);
                }
                catch (final Exception o) {}
                finally {
                    o = byteArrayOutputStream;
                }
            }
            finally {}
        }
        catch (final Exception byteArrayOutputStream) {
            closeable2 = closeable;
        }
        ((Exception)byteArrayOutputStream).printStackTrace();
        PictureFileUtils.close((Closeable)fileOutputStream);
        PictureFileUtils.close(closeable2);
        return;
        PictureFileUtils.close((Closeable)fileOutputStream);
        PictureFileUtils.close((Closeable)o);
    }
}
