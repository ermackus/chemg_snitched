package com.king.zxing.util;

import com.google.zxing.DecodeHintType;
import com.king.zxing.DecodeFormatManager;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.Result;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.WriterException;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.EncodeHintType;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.graphics.Paint$Align;
import android.text.TextPaint;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.text.TextUtils;
import android.graphics.Bitmap;

public final class CodeUtils
{
    public static final int DEFAULT_REQ_HEIGHT = 640;
    public static final int DEFAULT_REQ_WIDTH = 480;
    
    private CodeUtils() {
        throw new AssertionError();
    }
    
    private static Bitmap addCode(final Bitmap bitmap, final String s, final int n, final int color, final int n2) {
        final Bitmap bitmap2 = null;
        if (bitmap == null) {
            return null;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            return bitmap;
        }
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        Bitmap bitmap3 = bitmap2;
        if (width > 0) {
            if (height <= 0) {
                bitmap3 = bitmap2;
            }
            else {
                try {
                    bitmap3 = Bitmap.createBitmap(width, height + n + n2 * 2, Bitmap$Config.ARGB_8888);
                    final Canvas canvas = new Canvas(bitmap3);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint)null);
                    final TextPaint textPaint = new TextPaint();
                    textPaint.setTextSize((float)n);
                    textPaint.setColor(color);
                    textPaint.setTextAlign(Paint$Align.CENTER);
                    canvas.drawText(s, (float)(width / 2), (float)(height + n / 2 + n2), (Paint)textPaint);
                    canvas.save();
                    canvas.restore();
                }
                catch (final Exception ex) {
                    LogUtils.w(ex.getMessage());
                    bitmap3 = bitmap2;
                }
            }
        }
        return bitmap3;
    }
    
    private static Bitmap addLogo(Bitmap bitmap, final Bitmap bitmap2, float n) {
        final Bitmap bitmap3 = null;
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int width2 = bitmap2.getWidth();
        final int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 != 0 && height2 != 0) {
            n = width * n / width2;
            try {
                final Bitmap bitmap4 = Bitmap.createBitmap(width, height, Bitmap$Config.ARGB_8888);
                final Canvas canvas = new Canvas(bitmap4);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint)null);
                canvas.scale(n, n, (float)(width / 2), (float)(height / 2));
                canvas.drawBitmap(bitmap2, (float)((width - width2) / 2), (float)((height - height2) / 2), (Paint)null);
                canvas.save();
                canvas.restore();
                bitmap = bitmap4;
            }
            catch (final Exception ex) {
                LogUtils.w(ex.getMessage());
                bitmap = bitmap3;
            }
            return bitmap;
        }
        return bitmap;
    }
    
    private static Bitmap compressBitmap(final String s, int max, int n) {
        if (max > 0 && n > 0) {
            final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
            final int n2 = 1;
            bitmapFactory$Options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(s, bitmapFactory$Options);
            final float n3 = (float)bitmapFactory$Options.outWidth;
            final float n4 = (float)bitmapFactory$Options.outHeight;
            final float n5 = (float)max;
            if (n3 > n5) {
                max = (int)(n3 / n5);
            }
            else {
                max = 1;
            }
            final float n6 = (float)n;
            if (n4 > n6) {
                n = (int)(n4 / n6);
            }
            else {
                n = 1;
            }
            max = Math.max(max, n);
            if (max <= 0) {
                max = n2;
            }
            bitmapFactory$Options.inSampleSize = max;
            bitmapFactory$Options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(s, bitmapFactory$Options);
        }
        return BitmapFactory.decodeFile(s);
    }
    
    public static Bitmap createBarCode(final String s, final int n, final int n2) {
        return createBarCode(s, BarcodeFormat.CODE_128, n, n2, null);
    }
    
    public static Bitmap createBarCode(final String s, final int n, final int n2, final boolean b) {
        return createBarCode(s, BarcodeFormat.CODE_128, n, n2, null, b, 40, -16777216);
    }
    
    public static Bitmap createBarCode(final String s, final int n, final int n2, final boolean b, final int n3) {
        return createBarCode(s, BarcodeFormat.CODE_128, n, n2, null, b, 40, n3);
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, final int n, final int n2) {
        return createBarCode(s, barcodeFormat, n, n2, null);
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, final int n, final int n2, final Map<EncodeHintType, ?> map) {
        return createBarCode(s, barcodeFormat, n, n2, map, false, 40, -16777216);
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, final int n, final int n2, final Map<EncodeHintType, ?> map, final boolean b) {
        return createBarCode(s, barcodeFormat, n, n2, map, b, 40, -16777216);
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, final int n, final int n2, final Map<EncodeHintType, ?> map, final boolean b, final int n3) {
        return createBarCode(s, barcodeFormat, n, n2, map, b, 40, n3);
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, int i, int j, final Map<EncodeHintType, ?> map, final boolean b, final int n, final int n2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            final BitMatrix encode = multiFormatWriter.encode(s, barcodeFormat, i, j, (Map)map);
            final int width = encode.getWidth();
            final int height = encode.getHeight();
            final int[] array = new int[width * height];
            int n3;
            for (i = 0; i < height; ++i) {
                for (j = 0; j < width; ++j) {
                    if (encode.get(j, i)) {
                        n3 = n2;
                    }
                    else {
                        n3 = -1;
                    }
                    array[i * width + j] = n3;
                }
            }
            final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap$Config.ARGB_8888);
            bitmap.setPixels(array, 0, width, 0, 0, width, height);
            if (b) {
                return addCode(bitmap, s, n, n2, n / 2);
            }
            return bitmap;
        }
        catch (final WriterException ex) {
            LogUtils.w(ex.getMessage());
            return null;
        }
    }
    
    public static Bitmap createBarCode(final String s, final BarcodeFormat barcodeFormat, final int n, final int n2, final boolean b, final int n3) {
        return createBarCode(s, barcodeFormat, n, n2, null, b, 40, n3);
    }
    
    public static Bitmap createQRCode(final String s, final int n) {
        return createQRCode(s, n, null);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final int n2) {
        return createQRCode(s, n, null, n2);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap) {
        return createQRCode(s, n, bitmap, -16777216);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap, final float n2) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)EncodeHintType.CHARACTER_SET, (Object)"utf-8");
        ((Map)hashMap).put((Object)EncodeHintType.ERROR_CORRECTION, (Object)ErrorCorrectionLevel.H);
        ((Map)hashMap).put((Object)EncodeHintType.MARGIN, (Object)1);
        return createQRCode(s, n, bitmap, n2, (Map<EncodeHintType, ?>)hashMap);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap, final float n2, final int n3) {
        final HashMap hashMap = new HashMap();
        ((Map)hashMap).put((Object)EncodeHintType.CHARACTER_SET, (Object)"utf-8");
        ((Map)hashMap).put((Object)EncodeHintType.ERROR_CORRECTION, (Object)ErrorCorrectionLevel.H);
        ((Map)hashMap).put((Object)EncodeHintType.MARGIN, (Object)1);
        return createQRCode(s, n, bitmap, n2, (Map<EncodeHintType, ?>)hashMap, n3);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap, final float n2, final Map<EncodeHintType, ?> map) {
        return createQRCode(s, n, bitmap, n2, map, -16777216);
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap, final float n2, final Map<EncodeHintType, ?> map, final int n3) {
        try {
            final BitMatrix encode = new QRCodeWriter().encode(s, BarcodeFormat.QR_CODE, n, n, (Map)map);
            final int[] array = new int[n * n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (encode.get(j, i)) {
                        array[i * n + j] = n3;
                    }
                    else {
                        array[i * n + j] = -1;
                    }
                }
            }
            final Bitmap bitmap2 = Bitmap.createBitmap(n, n, Bitmap$Config.ARGB_8888);
            bitmap2.setPixels(array, 0, n, 0, 0, n, n);
            Bitmap addLogo = bitmap2;
            if (bitmap != null) {
                addLogo = addLogo(bitmap2, bitmap, n2);
            }
            return addLogo;
        }
        catch (final Exception ex) {
            LogUtils.w(ex.getMessage());
            return null;
        }
    }
    
    public static Bitmap createQRCode(final String s, final int n, final Bitmap bitmap, final int n2) {
        return createQRCode(s, n, bitmap, 0.2f, n2);
    }
    
    private static Result decodeInternal(final MultiFormatReader multiFormatReader, final LuminanceSource luminanceSource) {
        Result decodeWithState;
        try {
            decodeWithState = multiFormatReader.decodeWithState(new BinaryBitmap((Binarizer)new HybridBinarizer(luminanceSource)));
        }
        catch (final Exception ex) {
            decodeWithState = null;
        }
        Result decodeWithState2 = decodeWithState;
        if (decodeWithState != null) {
            return decodeWithState2;
        }
        try {
            decodeWithState2 = multiFormatReader.decodeWithState(new BinaryBitmap((Binarizer)new GlobalHistogramBinarizer(luminanceSource)));
            return decodeWithState2;
        }
        catch (final Exception ex2) {
            decodeWithState2 = decodeWithState;
            return decodeWithState2;
        }
    }
    
    private static RGBLuminanceSource getRGBLuminanceSource(final Bitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int[] array = new int[width * height];
        bitmap.getPixels(array, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return new RGBLuminanceSource(width, height, array);
    }
    
    public static String parseCode(final Bitmap bitmap) {
        return parseCode(bitmap, DecodeFormatManager.ALL_HINTS);
    }
    
    public static String parseCode(final Bitmap bitmap, final Map<DecodeHintType, Object> map) {
        final Result codeResult = parseCodeResult(bitmap, map);
        if (codeResult != null) {
            return codeResult.getText();
        }
        return null;
    }
    
    public static String parseCode(final String s) {
        return parseCode(s, DecodeFormatManager.ALL_HINTS);
    }
    
    public static String parseCode(final String s, final Map<DecodeHintType, Object> map) {
        final Result codeResult = parseCodeResult(s, map);
        if (codeResult != null) {
            return codeResult.getText();
        }
        return null;
    }
    
    public static Result parseCodeResult(final Bitmap bitmap) {
        return parseCodeResult((LuminanceSource)getRGBLuminanceSource(bitmap), DecodeFormatManager.ALL_HINTS);
    }
    
    public static Result parseCodeResult(final Bitmap bitmap, final Map<DecodeHintType, Object> map) {
        return parseCodeResult((LuminanceSource)getRGBLuminanceSource(bitmap), map);
    }
    
    public static Result parseCodeResult(final LuminanceSource luminanceSource, final Map<DecodeHintType, Object> hints) {
        final MultiFormatReader multiFormatReader = new MultiFormatReader();
        final Result result = null;
        Result result2 = null;
        Result result3 = result;
        while (true) {
            try {
                try {
                    multiFormatReader.setHints((Map)hints);
                    if (luminanceSource != null) {
                        result3 = result;
                        result2 = decodeInternal(multiFormatReader, luminanceSource);
                        Result decodeInternal;
                        if ((decodeInternal = result2) == null) {
                            result3 = result2;
                            decodeInternal = decodeInternal(multiFormatReader, luminanceSource.invert());
                        }
                        if ((result2 = decodeInternal) == null) {
                            result2 = decodeInternal;
                            result3 = decodeInternal;
                            if (luminanceSource.isRotateSupported()) {
                                result3 = decodeInternal;
                                result2 = decodeInternal(multiFormatReader, luminanceSource.rotateCounterClockwise());
                            }
                        }
                    }
                    multiFormatReader.reset();
                }
                finally {}
            }
            catch (final Exception ex) {
                LogUtils.w(ex.getMessage());
                result2 = result3;
                continue;
            }
            break;
        }
        return result2;
        multiFormatReader.reset();
    }
    
    public static Result parseCodeResult(final String s, final int n, final int n2, final Map<DecodeHintType, Object> map) {
        return parseCodeResult(compressBitmap(s, n, n2), map);
    }
    
    public static Result parseCodeResult(final String s, final Map<DecodeHintType, Object> map) {
        return parseCodeResult(s, 480, 640, map);
    }
    
    public static String parseQRCode(final Bitmap bitmap) {
        return parseCode(bitmap, DecodeFormatManager.QR_CODE_HINTS);
    }
    
    public static String parseQRCode(final String s) {
        final Result qrCodeResult = parseQRCodeResult(s);
        if (qrCodeResult != null) {
            return qrCodeResult.getText();
        }
        return null;
    }
    
    public static Result parseQRCodeResult(final String s) {
        return parseQRCodeResult(s, 480, 640);
    }
    
    public static Result parseQRCodeResult(final String s, final int n, final int n2) {
        return parseCodeResult(s, n, n2, DecodeFormatManager.QR_CODE_HINTS);
    }
}
