package org.xutils.image;

import java.io.FileOutputStream;
import org.xutils.cache.DiskCacheEntity;
import java.util.Arrays;
import org.xutils.cache.DiskCacheFile;
import android.media.ExifInterface;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import org.xutils.common.util.LogUtil;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.widget.ImageView;
import org.xutils.common.Callback;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.Paint;
import java.io.IOException;
import android.backport.webp.WebPFactory;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import java.io.File;
import org.xutils.common.task.PriorityExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executor;
import org.xutils.cache.LruDiskCache;

public final class ImageDecoder
{
    private static final int BITMAP_DECODE_MAX_WORKER;
    private static final byte[] GIF_HEADER;
    private static final LruDiskCache THUMB_CACHE;
    private static final Executor THUMB_CACHE_EXECUTOR;
    private static final byte[] WEBP_HEADER;
    private static final Object bitmapDecodeLock;
    private static final AtomicInteger bitmapDecodeWorker;
    private static final Object gifDecodeLock;
    
    static {
        bitmapDecodeWorker = new AtomicInteger(0);
        bitmapDecodeLock = new Object();
        gifDecodeLock = new Object();
        GIF_HEADER = new byte[] { 71, 73, 70 };
        WEBP_HEADER = new byte[] { 87, 69, 66, 80 };
        int bitmap_DECODE_MAX_WORKER = 1;
        THUMB_CACHE_EXECUTOR = (Executor)new PriorityExecutor(1, true);
        THUMB_CACHE = LruDiskCache.getDiskCache("xUtils_img_thumb");
        if (Runtime.getRuntime().availableProcessors() > 4) {
            bitmap_DECODE_MAX_WORKER = 2;
        }
        BITMAP_DECODE_MAX_WORKER = bitmap_DECODE_MAX_WORKER;
    }
    
    private ImageDecoder() {
    }
    
    public static int calculateSampleSize(final int n, final int n2, final int n3, final int n4) {
        int n5 = 1;
        final int n6 = 1;
        if (n > n3 || n2 > n4) {
            int n7;
            if (n > n2) {
                n7 = Math.round(n2 / (float)n4);
            }
            else {
                n7 = Math.round(n / (float)n3);
            }
            if (n7 < 1) {
                n7 = n6;
            }
            final float n8 = (float)(n * n2);
            final float n9 = (float)(n3 * n4 * 2);
            while (true) {
                n5 = n7;
                if (n8 / (n7 * n7) <= n9) {
                    break;
                }
                ++n7;
            }
        }
        return n5;
    }
    
    static void clearCacheFiles() {
        ImageDecoder.THUMB_CACHE.clearCacheFiles();
    }
    
    public static void compress(final Bitmap bitmap, final Bitmap$CompressFormat bitmap$CompressFormat, final int n, final OutputStream outputStream) throws IOException {
        if (bitmap$CompressFormat == Bitmap$CompressFormat.WEBP) {
            outputStream.write(WebPFactory.encodeBitmap(bitmap, n));
        }
        else {
            bitmap.compress(bitmap$CompressFormat, n, outputStream);
        }
    }
    
    public static Bitmap cut2Circular(final Bitmap bitmap, final boolean b) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final int min = Math.min(width, height);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        final Bitmap bitmap2 = Bitmap.createBitmap(min, min, Bitmap$Config.ARGB_8888);
        Bitmap bitmap3 = bitmap;
        if (bitmap2 != null) {
            final Canvas canvas = new Canvas(bitmap2);
            final float n = (float)(min / 2);
            canvas.drawCircle(n, n, n, paint);
            paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
            canvas.drawBitmap(bitmap, (float)((min - width) / 2), (float)((min - height) / 2), paint);
            if (b) {
                bitmap.recycle();
            }
            bitmap3 = bitmap2;
        }
        return bitmap3;
    }
    
    public static Bitmap cut2RoundCorner(final Bitmap bitmap, final int n, final boolean b, final boolean b2) {
        if (n <= 0) {
            return bitmap;
        }
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        int min;
        int n2;
        if (b) {
            n2 = (min = Math.min(width, height));
        }
        else {
            min = width;
            n2 = height;
        }
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        final Bitmap bitmap2 = Bitmap.createBitmap(min, n2, Bitmap$Config.ARGB_8888);
        Bitmap bitmap3 = bitmap;
        if (bitmap2 != null) {
            final Canvas canvas = new Canvas(bitmap2);
            final RectF rectF = new RectF(0.0f, 0.0f, (float)min, (float)n2);
            final float n3 = (float)n;
            canvas.drawRoundRect(rectF, n3, n3, paint);
            paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.SRC_IN));
            canvas.drawBitmap(bitmap, (float)((min - width) / 2), (float)((n2 - height) / 2), paint);
            if (b2) {
                bitmap.recycle();
            }
            bitmap3 = bitmap2;
        }
        return bitmap3;
    }
    
    public static Bitmap cut2ScaleSize(final Bitmap bitmap, int n, int n2, final boolean b) {
        final int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == n && height == n2) {
            return bitmap;
        }
        final Matrix matrix = new Matrix();
        final float n3 = (float)n;
        final float n4 = (float)width;
        final float n5 = n3 / n4;
        final float n6 = (float)n2;
        final float n7 = (float)height;
        float n8 = n6 / n7;
        int n11;
        if (n5 > n8) {
            final float n9 = n6 / n5;
            n2 = (int)((n7 - n9) / 2.0f);
            height = (int)((n7 + n9) / 2.0f);
            final int n10 = 0;
            n = width;
            n8 = n5;
            n11 = n10;
        }
        else {
            final float n12 = n3 / n8;
            n11 = (int)((n4 - n12) / 2.0f);
            n = (int)((n4 + n12) / 2.0f);
            n2 = 0;
        }
        matrix.setScale(n8, n8);
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, n11, n2, n - n11, height - n2, matrix, true);
        Bitmap bitmap3 = bitmap;
        if (bitmap2 != null) {
            if (b && bitmap2 != bitmap) {
                bitmap.recycle();
            }
            bitmap3 = bitmap2;
        }
        return bitmap3;
    }
    
    public static Bitmap cut2Square(final Bitmap bitmap, final boolean b) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        final int min = Math.min(width, height);
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, (width - min) / 2, (height - min) / 2, min, min);
        Bitmap bitmap3 = bitmap;
        if (bitmap2 != null) {
            if (b && bitmap2 != bitmap) {
                bitmap.recycle();
            }
            bitmap3 = bitmap2;
        }
        return bitmap3;
    }
    
    public static Bitmap decodeBitmap(final File file, final ImageOptions imageOptions, final Callback.Cancelable cancelable) throws IOException {
        if (file != null && file.exists()) {
            if (file.length() >= 1L) {
                ImageOptions default1;
                if ((default1 = imageOptions) == null) {
                    default1 = ImageOptions.DEFAULT;
                }
                if (default1.getMaxWidth() <= 0 || default1.getMaxHeight() <= 0) {
                    default1.optimizeMaxSize(null);
                }
                Label_0088: {
                    if (cancelable == null) {
                        break Label_0088;
                    }
                    try {
                        if (cancelable.isCancelled()) {
                            throw new Callback.CancelledException("cancelled during decode image");
                        }
                        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                        bitmapFactory$Options.inJustDecodeBounds = true;
                        bitmapFactory$Options.inPurgeable = true;
                        bitmapFactory$Options.inInputShareable = true;
                        BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapFactory$Options);
                        int rotateAngle = 0;
                        bitmapFactory$Options.inJustDecodeBounds = false;
                        bitmapFactory$Options.inPreferredConfig = default1.getConfig();
                        final int outWidth = bitmapFactory$Options.outWidth;
                        final int outHeight = bitmapFactory$Options.outHeight;
                        final int width = default1.getWidth();
                        final int height = default1.getHeight();
                        int outHeight2 = outWidth;
                        int outWidth2 = outHeight;
                        if (default1.isAutoRotate()) {
                            final int n = rotateAngle = getRotateAngle(file.getAbsolutePath());
                            outHeight2 = outWidth;
                            outWidth2 = outHeight;
                            if (n / 90 % 2 == 1) {
                                outHeight2 = bitmapFactory$Options.outHeight;
                                outWidth2 = bitmapFactory$Options.outWidth;
                                rotateAngle = n;
                            }
                        }
                        if (!default1.isCrop() && width > 0 && height > 0) {
                            if (rotateAngle / 90 % 2 == 1) {
                                bitmapFactory$Options.outWidth = height;
                                bitmapFactory$Options.outHeight = width;
                            }
                            else {
                                bitmapFactory$Options.outWidth = width;
                                bitmapFactory$Options.outHeight = height;
                            }
                        }
                        bitmapFactory$Options.inSampleSize = calculateSampleSize(outHeight2, outWidth2, default1.getMaxWidth(), default1.getMaxHeight());
                        if (cancelable != null && cancelable.isCancelled()) {
                            throw new Callback.CancelledException("cancelled during decode image");
                        }
                        Bitmap decodeFile;
                        if (isWebP(file)) {
                            decodeFile = WebPFactory.decodeFile(file.getAbsolutePath(), bitmapFactory$Options);
                        }
                        else {
                            decodeFile = null;
                        }
                        Bitmap decodeFile2 = decodeFile;
                        if (decodeFile == null) {
                            decodeFile2 = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapFactory$Options);
                        }
                        if (decodeFile2 == null) {
                            throw new IOException("decode image error");
                        }
                        if (cancelable != null && cancelable.isCancelled()) {
                            throw new Callback.CancelledException("cancelled during decode image");
                        }
                        Bitmap rotate = decodeFile2;
                        if (rotateAngle != 0) {
                            rotate = rotate(decodeFile2, rotateAngle, true);
                        }
                        if (cancelable != null && cancelable.isCancelled()) {
                            throw new Callback.CancelledException("cancelled during decode image");
                        }
                        Bitmap cut2ScaleSize = rotate;
                        if (default1.isCrop()) {
                            cut2ScaleSize = rotate;
                            if (width > 0) {
                                cut2ScaleSize = rotate;
                                if (height > 0) {
                                    cut2ScaleSize = cut2ScaleSize(rotate, width, height, true);
                                }
                            }
                        }
                        if (cut2ScaleSize == null) {
                            throw new IOException("decode image error");
                        }
                        if (cancelable != null && cancelable.isCancelled()) {
                            throw new Callback.CancelledException("cancelled during decode image");
                        }
                        Bitmap bitmap;
                        if (default1.isCircular()) {
                            bitmap = cut2Circular(cut2ScaleSize, true);
                        }
                        else if (default1.getRadius() > 0) {
                            bitmap = cut2RoundCorner(cut2ScaleSize, default1.getRadius(), default1.isSquare(), true);
                        }
                        else {
                            bitmap = cut2ScaleSize;
                            if (default1.isSquare()) {
                                bitmap = cut2Square(cut2ScaleSize, true);
                            }
                        }
                        if (bitmap != null) {
                            goto Label_0646;
                        }
                        throw new IOException("decode image error");
                    }
                    catch (final IOException ex) {
                        throw ex;
                    }
                    finally {
                        final Throwable t;
                        LogUtil.e(t.getMessage(), t);
                    }
                }
            }
        }
        return null;
    }
    
    static Drawable decodeFileWithLock(final File p0, final ImageOptions p1, final Callback.Cancelable p2) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: aload_0        
        //     4: ifnull          382
        //     7: aload_0        
        //     8: invokevirtual   java/io/File.exists:()Z
        //    11: ifeq            382
        //    14: aload_0        
        //    15: invokevirtual   java/io/File.length:()J
        //    18: lconst_1       
        //    19: lcmp           
        //    20: ifge            26
        //    23: goto            382
        //    26: aload_2        
        //    27: ifnull          52
        //    30: aload_2        
        //    31: invokeinterface org/xutils/common/Callback$Cancelable.isCancelled:()Z
        //    36: ifne            42
        //    39: goto            52
        //    42: new             Lorg/xutils/common/Callback$CancelledException;
        //    45: dup            
        //    46: ldc             "cancelled during decode image"
        //    48: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //    51: athrow         
        //    52: aload_1        
        //    53: invokevirtual   org/xutils/image/ImageOptions.isIgnoreGif:()Z
        //    56: ifne            115
        //    59: aload_0        
        //    60: invokestatic    org/xutils/image/ImageDecoder.isGif:(Ljava/io/File;)Z
        //    63: ifeq            115
        //    66: getstatic       org/xutils/image/ImageDecoder.gifDecodeLock:Ljava/lang/Object;
        //    69: astore_3       
        //    70: aload_3        
        //    71: dup            
        //    72: astore          6
        //    74: monitorenter   
        //    75: aload_0        
        //    76: aload_1        
        //    77: aload_2        
        //    78: invokestatic    org/xutils/image/ImageDecoder.decodeGif:(Ljava/io/File;Lorg/xutils/image/ImageOptions;Lorg/xutils/common/Callback$Cancelable;)Landroid/graphics/Movie;
        //    81: astore_2       
        //    82: aload           6
        //    84: monitorexit    
        //    85: aload           5
        //    87: astore_1       
        //    88: aload_2        
        //    89: ifnull          340
        //    92: new             Lorg/xutils/image/GifDrawable;
        //    95: dup            
        //    96: aload_2        
        //    97: aload_0        
        //    98: invokevirtual   java/io/File.length:()J
        //   101: l2i            
        //   102: invokespecial   org/xutils/image/GifDrawable.<init>:(Landroid/graphics/Movie;I)V
        //   105: astore_1       
        //   106: goto            340
        //   109: astore_0       
        //   110: aload           6
        //   112: monitorexit    
        //   113: aload_0        
        //   114: athrow         
        //   115: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeWorker:Ljava/util/concurrent/atomic/AtomicInteger;
        //   118: invokevirtual   java/util/concurrent/atomic/AtomicInteger.get:()I
        //   121: getstatic       org/xutils/image/ImageDecoder.BITMAP_DECODE_MAX_WORKER:I
        //   124: if_icmplt       180
        //   127: aload_2        
        //   128: ifnull          140
        //   131: aload_2        
        //   132: invokeinterface org/xutils/common/Callback$Cancelable.isCancelled:()Z
        //   137: ifne            180
        //   140: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   143: astore_3       
        //   144: aload_3        
        //   145: dup            
        //   146: astore          6
        //   148: monitorenter   
        //   149: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   152: invokevirtual   java/lang/Object.wait:()V
        //   155: aload           6
        //   157: monitorexit    
        //   158: goto            115
        //   161: astore_0       
        //   162: new             Lorg/xutils/common/Callback$CancelledException;
        //   165: astore_0       
        //   166: aload_0        
        //   167: ldc             "cancelled during decode image"
        //   169: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   172: aload_0        
        //   173: athrow         
        //   174: astore_0       
        //   175: aload           6
        //   177: monitorexit    
        //   178: aload_0        
        //   179: athrow         
        //   180: aload_2        
        //   181: ifnull          208
        //   184: aload_2        
        //   185: invokeinterface org/xutils/common/Callback$Cancelable.isCancelled:()Z
        //   190: ifne            196
        //   193: goto            208
        //   196: new             Lorg/xutils/common/Callback$CancelledException;
        //   199: astore_0       
        //   200: aload_0        
        //   201: ldc             "cancelled during decode image"
        //   203: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   206: aload_0        
        //   207: athrow         
        //   208: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeWorker:Ljava/util/concurrent/atomic/AtomicInteger;
        //   211: invokevirtual   java/util/concurrent/atomic/AtomicInteger.incrementAndGet:()I
        //   214: pop            
        //   215: aload_1        
        //   216: invokevirtual   org/xutils/image/ImageOptions.isCompress:()Z
        //   219: ifeq            231
        //   222: aload_0        
        //   223: aload_1        
        //   224: invokestatic    org/xutils/image/ImageDecoder.getThumbCache:(Ljava/io/File;Lorg/xutils/image/ImageOptions;)Landroid/graphics/Bitmap;
        //   227: astore_3       
        //   228: goto            233
        //   231: aconst_null    
        //   232: astore_3       
        //   233: aload_3        
        //   234: astore          4
        //   236: aload_3        
        //   237: ifnonnull       291
        //   240: aload_0        
        //   241: aload_1        
        //   242: aload_2        
        //   243: invokestatic    org/xutils/image/ImageDecoder.decodeBitmap:(Ljava/io/File;Lorg/xutils/image/ImageOptions;Lorg/xutils/common/Callback$Cancelable;)Landroid/graphics/Bitmap;
        //   246: astore_2       
        //   247: aload_2        
        //   248: astore          4
        //   250: aload_2        
        //   251: ifnull          291
        //   254: aload_2        
        //   255: astore          4
        //   257: aload_1        
        //   258: invokevirtual   org/xutils/image/ImageOptions.isCompress:()Z
        //   261: ifeq            291
        //   264: getstatic       org/xutils/image/ImageDecoder.THUMB_CACHE_EXECUTOR:Ljava/util/concurrent/Executor;
        //   267: astore          4
        //   269: new             Lorg/xutils/image/ImageDecoder$1;
        //   272: astore_3       
        //   273: aload_3        
        //   274: aload_0        
        //   275: aload_1        
        //   276: aload_2        
        //   277: invokespecial   org/xutils/image/ImageDecoder$1.<init>:(Ljava/io/File;Lorg/xutils/image/ImageOptions;Landroid/graphics/Bitmap;)V
        //   280: aload           4
        //   282: aload_3        
        //   283: invokeinterface java/util/concurrent/Executor.execute:(Ljava/lang/Runnable;)V
        //   288: aload_2        
        //   289: astore          4
        //   291: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeWorker:Ljava/util/concurrent/atomic/AtomicInteger;
        //   294: invokevirtual   java/util/concurrent/atomic/AtomicInteger.decrementAndGet:()I
        //   297: pop            
        //   298: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   301: astore_0       
        //   302: aload_0        
        //   303: dup            
        //   304: astore          7
        //   306: monitorenter   
        //   307: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   310: invokevirtual   java/lang/Object.notifyAll:()V
        //   313: aload           7
        //   315: monitorexit    
        //   316: aload           5
        //   318: astore_1       
        //   319: aload           4
        //   321: ifnull          340
        //   324: new             Lorg/xutils/image/ReusableBitmapDrawable;
        //   327: dup            
        //   328: invokestatic    org/xutils/x.app:()Landroid/app/Application;
        //   331: invokevirtual   android/app/Application.getResources:()Landroid/content/res/Resources;
        //   334: aload           4
        //   336: invokespecial   org/xutils/image/ReusableBitmapDrawable.<init>:(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
        //   339: astore_1       
        //   340: aload_1        
        //   341: areturn        
        //   342: astore_1       
        //   343: aload           7
        //   345: monitorexit    
        //   346: aload_1        
        //   347: athrow         
        //   348: astore_1       
        //   349: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeWorker:Ljava/util/concurrent/atomic/AtomicInteger;
        //   352: invokevirtual   java/util/concurrent/atomic/AtomicInteger.decrementAndGet:()I
        //   355: pop            
        //   356: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   359: astore_0       
        //   360: aload_0        
        //   361: dup            
        //   362: astore          7
        //   364: monitorenter   
        //   365: getstatic       org/xutils/image/ImageDecoder.bitmapDecodeLock:Ljava/lang/Object;
        //   368: invokevirtual   java/lang/Object.notifyAll:()V
        //   371: aload           7
        //   373: monitorexit    
        //   374: aload_1        
        //   375: athrow         
        //   376: astore_1       
        //   377: aload           7
        //   379: monitorexit    
        //   380: aload_1        
        //   381: athrow         
        //   382: aconst_null    
        //   383: areturn        
        //   384: astore          4
        //   386: goto            155
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  75     85     109    115    Any
        //  110    113    109    115    Any
        //  115    127    348    382    Any
        //  131    140    348    382    Any
        //  140    149    348    382    Any
        //  149    155    161    174    Ljava/lang/InterruptedException;
        //  149    155    384    389    Any
        //  155    158    174    180    Any
        //  162    174    174    180    Any
        //  175    178    174    180    Any
        //  178    180    348    382    Any
        //  184    193    348    382    Any
        //  196    208    348    382    Any
        //  208    228    348    382    Any
        //  240    247    348    382    Any
        //  257    288    348    382    Any
        //  307    316    342    348    Any
        //  343    346    342    348    Any
        //  365    374    376    382    Any
        //  377    380    376    382    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 208, Size: 208
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Movie decodeGif(File file, ImageOptions ex, final Callback.Cancelable cancelable) throws IOException {
        final File file2 = null;
        if (file == null || !file.exists() || file.length() < 1L) {
            return null;
        }
        while (true) {
            if (cancelable != null) {
                try {
                    if (cancelable.isCancelled()) {
                        throw new Callback.CancelledException("cancelled during decode image");
                    }
                    ex = (IOException)new BufferedInputStream((InputStream)new FileInputStream(file), 16384);
                    try {
                        ((InputStream)ex).mark(16384);
                        final Movie decodeStream = Movie.decodeStream((InputStream)ex);
                        if (decodeStream != null) {
                            IOUtil.closeQuietly((Closeable)ex);
                            return decodeStream;
                        }
                        throw new IOException("decode image error");
                    }
                    catch (final IOException ex2) {
                        ex = ex2;
                    }
                }
                catch (final IOException ex) {
                    file = file2;
                }
                finally {
                    file = null;
                }
                try {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                    IOUtil.closeQuietly((Closeable)file);
                    return null;
                }
                finally {}
                File file3;
                Throwable t2;
                try {
                    throw ex;
                }
                finally {
                    file3 = file;
                    final Throwable t3;
                    t2 = t3;
                }
                IOUtil.closeQuietly((Closeable)file3);
                throw t2;
            }
            continue;
        }
    }
    
    public static int getRotateAngle(final String s) {
        int n = 0;
        try {
            final int attributeInt = new ExifInterface(s).getAttributeInt("Orientation", 0);
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
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        return n;
    }
    
    private static Bitmap getThumbCache(File file, final ImageOptions imageOptions) {
        if (!WebPFactory.available()) {
            return null;
        }
        Label_0141: {
            try {
                final LruDiskCache thumb_CACHE = ImageDecoder.THUMB_CACHE;
                final StringBuilder sb = new StringBuilder();
                sb.append(file.getAbsolutePath());
                sb.append("@");
                sb.append(file.lastModified());
                sb.append(imageOptions.toString());
                final DiskCacheFile diskCacheFile = thumb_CACHE.getDiskCacheFile(sb.toString());
                final DiskCacheFile diskCacheFile2;
                if ((diskCacheFile2 = diskCacheFile) == null) {
                    break Label_0141;
                }
                try {
                    if (diskCacheFile.exists()) {
                        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                        bitmapFactory$Options.inJustDecodeBounds = false;
                        bitmapFactory$Options.inPurgeable = true;
                        bitmapFactory$Options.inInputShareable = true;
                        bitmapFactory$Options.inPreferredConfig = Bitmap$Config.ARGB_8888;
                        final Bitmap decodeFile = WebPFactory.decodeFile(diskCacheFile.getAbsolutePath(), bitmapFactory$Options);
                        IOUtil.closeQuietly((Closeable)diskCacheFile);
                        return decodeFile;
                    }
                    break Label_0141;
                }
                finally {}
            }
            finally {
                file = null;
            }
            try {
                final File file2;
                LogUtil.w(((Throwable)file2).getMessage(), (Throwable)file2);
                file2 = file;
                IOUtil.closeQuietly((Closeable)file2);
                return null;
            }
            finally {
                IOUtil.closeQuietly((Closeable)file);
            }
        }
    }
    
    public static boolean isGif(File file) {
        final File file2 = null;
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            try {
                final boolean equals = Arrays.equals(ImageDecoder.GIF_HEADER, IOUtil.readBytes((InputStream)fileInputStream, 0L, 3));
                IOUtil.closeQuietly((Closeable)fileInputStream);
                return equals;
            }
            finally {}
        }
        finally {
            file = file2;
        }
        try {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            return false;
        }
        finally {
            IOUtil.closeQuietly((Closeable)file);
        }
    }
    
    public static boolean isWebP(final File file) {
        Closeable closeable = null;
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            try {
                final boolean equals = Arrays.equals(ImageDecoder.WEBP_HEADER, IOUtil.readBytes((InputStream)fileInputStream, 8L, 4));
                IOUtil.closeQuietly((Closeable)fileInputStream);
                return equals;
            }
            finally {
                closeable = (Closeable)fileInputStream;
            }
        }
        finally {}
        try {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            return false;
        }
        finally {
            IOUtil.closeQuietly(closeable);
        }
    }
    
    public static Bitmap rotate(final Bitmap bitmap, final int n, final boolean b) {
        final Bitmap bitmap2;
        Label_0049: {
            if (n != 0) {
                final Matrix matrix = new Matrix();
                matrix.setRotate((float)n);
                try {
                    Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    break Label_0049;
                }
                finally {
                    LogUtil.e(((Throwable)bitmap2).getMessage(), (Throwable)bitmap2);
                }
            }
            bitmap2 = null;
        }
        Bitmap bitmap3 = bitmap;
        if (bitmap2 != null) {
            if (b && bitmap2 != bitmap) {
                bitmap.recycle();
            }
            bitmap3 = bitmap2;
        }
        return bitmap3;
    }
    
    private static void saveThumbCache(File file, final ImageOptions imageOptions, final Bitmap bitmap) {
        if (!WebPFactory.available()) {
            return;
        }
        final DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        final StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append("@");
        sb.append(file.lastModified());
        sb.append(imageOptions.toString());
        diskCacheEntity.setKey(sb.toString());
        Closeable closeable = null;
        Label_0145: {
            Closeable closeable2;
            try {
                final DiskCacheFile diskCacheFile2;
                final DiskCacheFile diskCacheFile = diskCacheFile2 = ImageDecoder.THUMB_CACHE.createDiskCacheFile(diskCacheEntity);
                if (diskCacheFile != null) {
                    try {
                        final FileOutputStream fileOutputStream = new FileOutputStream((File)diskCacheFile);
                        try {
                            ((OutputStream)fileOutputStream).write(WebPFactory.encodeBitmap(bitmap, 80));
                            ((OutputStream)fileOutputStream).flush();
                            diskCacheFile.commit();
                        }
                        finally {}
                    }
                    finally {
                        closeable = null;
                        break Label_0145;
                    }
                }
                IOUtil.closeQuietly((Closeable)diskCacheFile2);
                IOUtil.closeQuietly(closeable);
                return;
            }
            finally {
                file = null;
                closeable2 = null;
            }
            try {
                IOUtil.deleteFileOrDir((File)closeable2);
                final Throwable t;
                LogUtil.w(t.getMessage(), t);
                IOUtil.closeQuietly(closeable2);
                IOUtil.closeQuietly((Closeable)file);
            }
            finally {
                IOUtil.closeQuietly(closeable2);
                IOUtil.closeQuietly((Closeable)file);
            }
        }
    }
}
