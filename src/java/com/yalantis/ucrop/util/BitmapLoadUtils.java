package com.yalantis.ucrop.util;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import android.os.AsyncTask;
import com.yalantis.ucrop.task.BitmapLoadTask;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import android.net.Uri;
import java.io.IOException;
import java.io.Closeable;
import android.graphics.Bitmap;
import android.util.Log;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.WindowManager;
import android.content.Context;
import android.graphics.BitmapFactory$Options;

public class BitmapLoadUtils
{
    private static final String CONTENT_SCHEME = "content";
    private static final int MAX_BITMAP_SIZE = 104857600;
    private static final String TAG = "BitmapLoadUtils";
    
    @Deprecated
    public static int calculateInSampleSize(final BitmapFactory$Options bitmapFactory$Options, final int n, final int n2) {
        final int outHeight = bitmapFactory$Options.outHeight;
        final int outWidth = bitmapFactory$Options.outWidth;
        int n3 = 1;
        int n5;
        final int n4 = n5 = 1;
        if (outHeight <= n2) {
            if (outWidth <= n) {
                return n3;
            }
            n5 = n4;
        }
        while (true) {
            if (outHeight / n5 <= n2) {
                n3 = n5;
                if (outWidth / n5 <= n) {
                    break;
                }
            }
            n5 *= 2;
        }
        return n3;
    }
    
    public static int calculateMaxBitmapSize(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        final Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        final int n = (int)Math.sqrt(Math.pow((double)point.x, 2.0) + Math.pow((double)point.y, 2.0));
        final Canvas canvas = new Canvas();
        final int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        int min2 = n;
        if (min > 0) {
            min2 = Math.min(n, min);
        }
        final int maxTextureSize = EglUtils.getMaxTextureSize();
        int min3 = min2;
        if (maxTextureSize > 0) {
            min3 = Math.min(min2, maxTextureSize);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("maxBitmapSize: ");
        sb.append(min3);
        Log.d("BitmapLoadUtils", sb.toString());
        return min3;
    }
    
    public static boolean checkSize(final Bitmap bitmap, final BitmapFactory$Options bitmapFactory$Options) {
        int byteCount;
        if (bitmap != null) {
            byteCount = bitmap.getByteCount();
        }
        else {
            byteCount = 0;
        }
        if (byteCount > getTotalMemory()) {
            bitmapFactory$Options.inSampleSize *= 2;
            return true;
        }
        return false;
    }
    
    public static void close(final Closeable closeable) {
        if (closeable == null || !(closeable instanceof Closeable)) {
            return;
        }
        try {
            closeable.close();
        }
        catch (final IOException ex) {}
    }
    
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
    
    public static void decodeBitmapInBackground(final Context context, final Uri uri, final Uri uri2, final int n, final int n2, final BitmapLoadCallback bitmapLoadCallback) {
        new BitmapLoadTask(context, uri, uri2, n, n2, bitmapLoadCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
    
    public static int exifToDegrees(int n) {
        switch (n) {
            default: {
                n = 0;
                break;
            }
            case 7:
            case 8: {
                n = 270;
                break;
            }
            case 5:
            case 6: {
                n = 90;
                break;
            }
            case 3:
            case 4: {
                n = 180;
                break;
            }
        }
        return n;
    }
    
    public static int exifToTranslation(int n) {
        if (n != 2 && n != 7 && n != 4 && n != 5) {
            n = 1;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    public static int getExifOrientation(final Context context, final Uri uri) {
        int orientation;
        final int n = orientation = 0;
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return 0;
            }
            orientation = n;
            orientation = n;
            final ImageHeaderParser imageHeaderParser = new ImageHeaderParser(openInputStream);
            orientation = n;
            orientation = imageHeaderParser.getOrientation();
            close((Closeable)openInputStream);
            orientation = orientation;
        }
        catch (final IOException ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getExifOrientation: ");
            sb.append(uri.toString());
            Log.e("BitmapLoadUtils", sb.toString(), (Throwable)ex);
        }
        return orientation;
    }
    
    public static int[] getMaxImageSize(final Context context, final Uri uri) {
        if (FileUtils.isHasHttp(uri.toString())) {
            return new int[] { 0, 0 };
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), (Rect)null, bitmapFactory$Options);
            bitmapFactory$Options.inSampleSize = computeSize(bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        bitmapFactory$Options.inJustDecodeBounds = false;
        Bitmap decodeStream = null;
        int n = 0;
        Bitmap bitmap;
        while (true) {
            bitmap = decodeStream;
            if (n == 0) {
                decodeStream = bitmap;
                Bitmap bitmap2 = bitmap;
                try {
                    final InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                    try {
                        final Bitmap bitmap3 = bitmap2 = (decodeStream = BitmapFactory.decodeStream(openInputStream, (Rect)null, bitmapFactory$Options));
                        close((Closeable)openInputStream);
                        decodeStream = bitmap3;
                        bitmap2 = bitmap3;
                        if (checkSize(bitmap3, bitmapFactory$Options)) {
                            decodeStream = bitmap3;
                            continue;
                        }
                        n = 1;
                        decodeStream = bitmap3;
                        continue;
                    }
                    finally {
                        decodeStream = bitmap;
                        bitmap2 = bitmap;
                        close((Closeable)openInputStream);
                        decodeStream = bitmap;
                        bitmap2 = bitmap;
                    }
                }
                catch (final IOException ex2) {
                    Log.e("BitmapLoadUtils", "doInBackground: ImageDecoder.createSource: ", (Throwable)ex2);
                    continue;
                }
                catch (final OutOfMemoryError outOfMemoryError) {
                    Log.e("BitmapLoadUtils", "doInBackground: BitmapFactory.decodeFileDescriptor: ", (Throwable)outOfMemoryError);
                    bitmapFactory$Options.inSampleSize *= 2;
                    decodeStream = bitmap2;
                    continue;
                }
                break;
            }
            break;
        }
        if (bitmap == null) {
            return new int[] { 0, 0 };
        }
        return new int[] { bitmap.getWidth(), bitmap.getHeight() };
    }
    
    public static long getTotalMemory() {
        long totalMemory;
        if ((totalMemory = Runtime.getRuntime().totalMemory()) > 104857600L) {
            totalMemory = 104857600L;
        }
        return totalMemory;
    }
    
    public static boolean hasContentScheme(final Uri uri) {
        return uri != null && "content".equals((Object)uri.getScheme());
    }
    
    public static Bitmap transformBitmap(Bitmap bitmap, final Matrix matrix) {
        try {
            final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (!bitmap.sameAs(bitmap2)) {
                bitmap = bitmap2;
            }
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            Log.e("BitmapLoadUtils", "transformBitmap: ", (Throwable)outOfMemoryError);
        }
        return bitmap;
    }
}
