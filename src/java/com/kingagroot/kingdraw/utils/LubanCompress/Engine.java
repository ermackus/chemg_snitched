package com.kingagroot.kingdraw.utils.LubanCompress;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import java.io.FileOutputStream;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.content.ContentResolver;
import java.io.FileInputStream;
import android.net.Uri;
import android.os.Build$VERSION;
import com.kingagroot.kingdraw.base.MApplication;
import com.goodsrc.library.utils.UriUtil;
import java.io.InputStream;
import java.io.IOException;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import java.io.File;
import android.media.ExifInterface;

class Engine
{
    private ExifInterface srcExif;
    private final String srcFilePath;
    private int srcHeight;
    private int srcWidth;
    private final File tagImg;
    
    Engine(final String srcFilePath, final File tagImg) throws IOException {
        if (this.isJpeg(srcFilePath)) {
            this.srcExif = new ExifInterface(srcFilePath);
        }
        this.srcFilePath = srcFilePath;
        this.tagImg = tagImg;
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        bitmapFactory$Options.inSampleSize = 1;
        BitmapFactory.decodeStream(this.getSrcImgStream(), (Rect)null, bitmapFactory$Options);
        this.srcWidth = bitmapFactory$Options.outWidth;
        this.srcHeight = bitmapFactory$Options.outHeight;
    }
    
    private int computeSize() {
        final int srcWidth = this.srcWidth;
        final int n = 1;
        final int n2 = 1;
        int srcWidth2 = srcWidth;
        if (srcWidth % 2 == 1) {
            srcWidth2 = srcWidth + 1;
        }
        this.srcWidth = srcWidth2;
        int srcHeight;
        final int n3 = srcHeight = this.srcHeight;
        if (n3 % 2 == 1) {
            srcHeight = n3 + 1;
        }
        this.srcHeight = srcHeight;
        final int max = Math.max(this.srcWidth, srcHeight);
        final float n4 = Math.min(this.srcWidth, this.srcHeight) / (float)max;
        if (n4 <= 1.0f && n4 > 0.5625) {
            if (max < 1664) {
                return 1;
            }
            if (max >= 1664 && max < 4990) {
                return 2;
            }
            if (max > 4990 && max < 10240) {
                return 4;
            }
            int n5 = max / 1280;
            if (n5 == 0) {
                n5 = n2;
            }
            return n5;
        }
        else {
            final double n6 = n4;
            if (n6 <= 0.5625 && n6 > 0.5) {
                int n7 = max / 1280;
                if (n7 == 0) {
                    n7 = n;
                }
                return n7;
            }
            return (int)Math.ceil(max / (1280.0 / n6));
        }
    }
    
    private InputStream getSrcImgStream() {
        if (UriUtil.isUriPath(this.srcFilePath)) {
            final ContentResolver contentResolver = ((Context)MApplication.getInstance()).getContentResolver();
            Uri uri;
            if (Build$VERSION.SDK_INT >= 29) {
                uri = Uri.parse(this.srcFilePath);
            }
            else {
                uri = UriUtil.File2Uri(new File(this.srcFilePath));
            }
            try {
                return contentResolver.openInputStream(uri);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        try {
            return (InputStream)new FileInputStream(this.srcFilePath);
        }
        catch (final Exception ex2) {
            ex2.printStackTrace();
        }
        return null;
    }
    
    private boolean isJpeg(final String s) {
        return s.contains((CharSequence)"jpeg") || s.contains((CharSequence)"jpg");
    }
    
    private Bitmap rotatingImage(final Bitmap bitmap) {
        if (this.srcExif == null) {
            return bitmap;
        }
        final Matrix matrix = new Matrix();
        int n = 0;
        final int attributeInt = this.srcExif.getAttributeInt("Orientation", 1);
        if (attributeInt != 3) {
            if (attributeInt != 6) {
                if (attributeInt == 8) {
                    n = 270;
                }
            }
            else {
                n = 90;
            }
        }
        else {
            n = 180;
        }
        matrix.postRotate((float)n);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    
    File compress() throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inSampleSize = this.computeSize();
        final Bitmap decodeStream = BitmapFactory.decodeStream(this.getSrcImgStream(), (Rect)null, bitmapFactory$Options);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Bitmap rotatingImage = this.rotatingImage(decodeStream);
        rotatingImage.compress(Bitmap$CompressFormat.JPEG, 80, (OutputStream)byteArrayOutputStream);
        rotatingImage.recycle();
        final FileOutputStream fileOutputStream = new FileOutputStream(this.tagImg);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.tagImg;
    }
    
    File compressAndGray() throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inSampleSize = this.computeSize();
        final Bitmap decodeStream = BitmapFactory.decodeStream(this.getSrcImgStream(), (Rect)null, bitmapFactory$Options);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Bitmap lineGray = this.lineGray(this.rotatingImage(decodeStream));
        lineGray.compress(Bitmap$CompressFormat.JPEG, 80, (OutputStream)byteArrayOutputStream);
        lineGray.recycle();
        final FileOutputStream fileOutputStream = new FileOutputStream(this.tagImg);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.tagImg;
    }
    
    File compressWithBackground(final int n) throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inSampleSize = this.computeSize();
        final Bitmap decodeStream = BitmapFactory.decodeStream(this.getSrcImgStream(), (Rect)null, bitmapFactory$Options);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Bitmap writeBackground = this.writeBackground(n, this.rotatingImage(decodeStream));
        writeBackground.compress(Bitmap$CompressFormat.JPEG, 80, (OutputStream)byteArrayOutputStream);
        writeBackground.recycle();
        final FileOutputStream fileOutputStream = new FileOutputStream(this.tagImg);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.tagImg;
    }
    
    public Bitmap lineGray(final Bitmap bitmap) {
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap$Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint();
        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmap2;
    }
    
    public Bitmap writeBackground(final int n, final Bitmap bitmap) {
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap$Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap2);
        canvas.drawColor(n);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint)null);
        return bitmap2;
    }
}
