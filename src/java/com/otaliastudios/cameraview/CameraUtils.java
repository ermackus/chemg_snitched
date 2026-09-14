package com.otaliastudios.cameraview;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera$CameraInfo;
import com.otaliastudios.cameraview.engine.mappers.Camera1Mapper;
import com.otaliastudios.cameraview.controls.Facing;
import android.content.Context;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import android.os.Handler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;

public class CameraUtils
{
    private static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = CameraUtils.class.getSimpleName());
    }
    
    private static int computeSampleSize(final int n, final int n2, final int n3, final int n4) {
        int n5 = 1;
        int n7;
        final int n6 = n7 = 1;
        if (n2 <= n4) {
            if (n <= n3) {
                return n5;
            }
            n7 = n6;
        }
        while (true) {
            if (n2 / n7 < n4) {
                n5 = n7;
                if (n / n7 < n3) {
                    break;
                }
            }
            n7 *= 2;
        }
        return n5;
    }
    
    public static Bitmap decodeBitmap(final byte[] array) {
        return decodeBitmap(array, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public static Bitmap decodeBitmap(final byte[] array, final int n, final int n2) {
        return decodeBitmap(array, n, n2, new BitmapFactory$Options());
    }
    
    public static Bitmap decodeBitmap(final byte[] array, final int n, final int n2, final BitmapFactory$Options bitmapFactory$Options) {
        return decodeBitmap(array, n, n2, bitmapFactory$Options, -1);
    }
    
    private static Bitmap decodeBitmap(final byte[] p0, final int p1, final int p2, final BitmapFactory$Options p3, final int p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifgt            11
        //     4: ldc             2147483647
        //     6: istore          5
        //     8: goto            14
        //    11: iload_1        
        //    12: istore          5
        //    14: iload_2        
        //    15: ifgt            25
        //    18: ldc             2147483647
        //    20: istore          6
        //    22: goto            28
        //    25: iload_2        
        //    26: istore          6
        //    28: aconst_null    
        //    29: astore          11
        //    31: aconst_null    
        //    32: astore          8
        //    34: iload           4
        //    36: istore_1       
        //    37: iload_1        
        //    38: iconst_m1      
        //    39: if_icmpne       252
        //    42: new             Ljava/io/ByteArrayInputStream;
        //    45: astore          9
        //    47: aload           9
        //    49: aload_0        
        //    50: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    53: aload           9
        //    55: astore          8
        //    57: new             Landroidx/exifinterface/media/ExifInterface;
        //    60: astore          10
        //    62: aload           9
        //    64: astore          8
        //    66: aload           10
        //    68: aload           9
        //    70: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/io/InputStream;)V
        //    73: aload           9
        //    75: astore          8
        //    77: aload           10
        //    79: ldc             "Orientation"
        //    81: iconst_1       
        //    82: invokevirtual   androidx/exifinterface/media/ExifInterface.getAttributeInt:(Ljava/lang/String;I)I
        //    85: istore_1       
        //    86: aload           9
        //    88: astore          8
        //    90: iload_1        
        //    91: invokestatic    com/otaliastudios/cameraview/internal/ExifHelper.getOrientation:(I)I
        //    94: istore_2       
        //    95: iload_1        
        //    96: iconst_2       
        //    97: if_icmpeq       124
        //   100: iload_1        
        //   101: iconst_4       
        //   102: if_icmpeq       124
        //   105: iload_1        
        //   106: iconst_5       
        //   107: if_icmpeq       124
        //   110: iload_1        
        //   111: bipush          7
        //   113: if_icmpne       119
        //   116: goto            124
        //   119: iconst_0       
        //   120: istore_1       
        //   121: goto            126
        //   124: iconst_1       
        //   125: istore_1       
        //   126: aload           9
        //   128: astore          8
        //   130: getstatic       com/otaliastudios/cameraview/CameraUtils.LOG:Lcom/otaliastudios/cameraview/CameraLogger;
        //   133: iconst_3       
        //   134: anewarray       Ljava/lang/Object;
        //   137: dup            
        //   138: iconst_0       
        //   139: ldc             "decodeBitmap:"
        //   141: aastore        
        //   142: dup            
        //   143: iconst_1       
        //   144: ldc             "got orientation from EXIF."
        //   146: aastore        
        //   147: dup            
        //   148: iconst_2       
        //   149: iload_2        
        //   150: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   153: aastore        
        //   154: invokevirtual   com/otaliastudios/cameraview/CameraLogger.i:([Ljava/lang/Object;)Ljava/lang/String;
        //   157: pop            
        //   158: aload           9
        //   160: invokevirtual   java/io/InputStream.close:()V
        //   163: goto            227
        //   166: astore          10
        //   168: goto            183
        //   171: astore_0       
        //   172: aload           8
        //   174: astore_3       
        //   175: goto            242
        //   178: astore          10
        //   180: aconst_null    
        //   181: astore          9
        //   183: aload           9
        //   185: astore          8
        //   187: getstatic       com/otaliastudios/cameraview/CameraUtils.LOG:Lcom/otaliastudios/cameraview/CameraLogger;
        //   190: iconst_3       
        //   191: anewarray       Ljava/lang/Object;
        //   194: dup            
        //   195: iconst_0       
        //   196: ldc             "decodeBitmap:"
        //   198: aastore        
        //   199: dup            
        //   200: iconst_1       
        //   201: ldc             "could not get orientation from EXIF."
        //   203: aastore        
        //   204: dup            
        //   205: iconst_2       
        //   206: aload           10
        //   208: aastore        
        //   209: invokevirtual   com/otaliastudios/cameraview/CameraLogger.e:([Ljava/lang/Object;)Ljava/lang/String;
        //   212: pop            
        //   213: aload           9
        //   215: ifnull          223
        //   218: aload           9
        //   220: invokevirtual   java/io/InputStream.close:()V
        //   223: iconst_0       
        //   224: istore_1       
        //   225: iconst_0       
        //   226: istore_2       
        //   227: iload_2        
        //   228: istore          4
        //   230: iload_1        
        //   231: istore_2       
        //   232: iload           4
        //   234: istore_1       
        //   235: goto            283
        //   238: astore_0       
        //   239: aload           8
        //   241: astore_3       
        //   242: aload_3        
        //   243: ifnull          250
        //   246: aload_3        
        //   247: invokevirtual   java/io/InputStream.close:()V
        //   250: aload_0        
        //   251: athrow         
        //   252: getstatic       com/otaliastudios/cameraview/CameraUtils.LOG:Lcom/otaliastudios/cameraview/CameraLogger;
        //   255: iconst_3       
        //   256: anewarray       Ljava/lang/Object;
        //   259: dup            
        //   260: iconst_0       
        //   261: ldc             "decodeBitmap:"
        //   263: aastore        
        //   264: dup            
        //   265: iconst_1       
        //   266: ldc             "got orientation from constructor."
        //   268: aastore        
        //   269: dup            
        //   270: iconst_2       
        //   271: iload           4
        //   273: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   276: aastore        
        //   277: invokevirtual   com/otaliastudios/cameraview/CameraLogger.i:([Ljava/lang/Object;)Ljava/lang/String;
        //   280: pop            
        //   281: iconst_0       
        //   282: istore_2       
        //   283: iload           5
        //   285: ldc             2147483647
        //   287: if_icmplt       311
        //   290: iload           6
        //   292: ldc             2147483647
        //   294: if_icmpge       300
        //   297: goto            311
        //   300: aload_0        
        //   301: iconst_0       
        //   302: aload_0        
        //   303: arraylength    
        //   304: invokestatic    android/graphics/BitmapFactory.decodeByteArray:([BII)Landroid/graphics/Bitmap;
        //   307: astore_0       
        //   308: goto            386
        //   311: aload_3        
        //   312: iconst_1       
        //   313: putfield        android/graphics/BitmapFactory$Options.inJustDecodeBounds:Z
        //   316: aload_0        
        //   317: iconst_0       
        //   318: aload_0        
        //   319: arraylength    
        //   320: aload_3        
        //   321: invokestatic    android/graphics/BitmapFactory.decodeByteArray:([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //   324: pop            
        //   325: aload_3        
        //   326: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //   329: istore          4
        //   331: aload_3        
        //   332: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //   335: istore          7
        //   337: iload_1        
        //   338: sipush          180
        //   341: irem           
        //   342: ifeq            357
        //   345: aload_3        
        //   346: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //   349: istore          4
        //   351: aload_3        
        //   352: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //   355: istore          7
        //   357: aload_3        
        //   358: iload           7
        //   360: iload           4
        //   362: iload           5
        //   364: iload           6
        //   366: invokestatic    com/otaliastudios/cameraview/CameraUtils.computeSampleSize:(IIII)I
        //   369: putfield        android/graphics/BitmapFactory$Options.inSampleSize:I
        //   372: aload_3        
        //   373: iconst_0       
        //   374: putfield        android/graphics/BitmapFactory$Options.inJustDecodeBounds:Z
        //   377: aload_0        
        //   378: iconst_0       
        //   379: aload_0        
        //   380: arraylength    
        //   381: aload_3        
        //   382: invokestatic    android/graphics/BitmapFactory.decodeByteArray:([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //   385: astore_0       
        //   386: iload_1        
        //   387: ifne            400
        //   390: iload_2        
        //   391: ifeq            397
        //   394: goto            400
        //   397: goto            437
        //   400: new             Landroid/graphics/Matrix;
        //   403: astore_3       
        //   404: aload_3        
        //   405: invokespecial   android/graphics/Matrix.<init>:()V
        //   408: aload_3        
        //   409: iload_1        
        //   410: i2f            
        //   411: invokevirtual   android/graphics/Matrix.setRotate:(F)V
        //   414: aload_0        
        //   415: iconst_0       
        //   416: iconst_0       
        //   417: aload_0        
        //   418: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //   421: aload_0        
        //   422: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //   425: aload_3        
        //   426: iconst_1       
        //   427: invokestatic    android/graphics/Bitmap.createBitmap:(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
        //   430: astore_3       
        //   431: aload_0        
        //   432: invokevirtual   android/graphics/Bitmap.recycle:()V
        //   435: aload_3        
        //   436: astore_0       
        //   437: aload_0        
        //   438: areturn        
        //   439: astore          8
        //   441: goto            227
        //   444: astore          8
        //   446: goto            223
        //   449: astore_3       
        //   450: goto            250
        //   453: astore_0       
        //   454: aload           11
        //   456: astore_0       
        //   457: goto            437
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  42     53     178    183    Ljava/io/IOException;
        //  42     53     171    178    Any
        //  57     62     166    171    Ljava/io/IOException;
        //  57     62     238    242    Any
        //  66     73     166    171    Ljava/io/IOException;
        //  66     73     238    242    Any
        //  77     86     166    171    Ljava/io/IOException;
        //  77     86     238    242    Any
        //  90     95     166    171    Ljava/io/IOException;
        //  90     95     238    242    Any
        //  130    158    166    171    Ljava/io/IOException;
        //  130    158    238    242    Any
        //  158    163    439    444    Ljava/lang/Exception;
        //  187    213    238    242    Any
        //  218    223    444    449    Ljava/lang/Exception;
        //  246    250    449    453    Ljava/lang/Exception;
        //  300    308    453    460    Ljava/lang/OutOfMemoryError;
        //  311    337    453    460    Ljava/lang/OutOfMemoryError;
        //  345    357    453    460    Ljava/lang/OutOfMemoryError;
        //  357    386    453    460    Ljava/lang/OutOfMemoryError;
        //  400    435    453    460    Ljava/lang/OutOfMemoryError;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 260, Size: 260
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
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
    
    static void decodeBitmap(final byte[] array, final int n, final int n2, final BitmapFactory$Options bitmapFactory$Options, final int n3, final BitmapCallback bitmapCallback) {
        WorkerHandler.execute((Runnable)new Runnable(array, n, n2, bitmapFactory$Options, n3, new Handler(), bitmapCallback) {
            final BitmapCallback val$callback;
            final int val$maxHeight;
            final int val$maxWidth;
            final BitmapFactory$Options val$options;
            final int val$rotation;
            final byte[] val$source;
            final Handler val$ui;
            
            public void run() {
                this.val$ui.post((Runnable)new Runnable(this, decodeBitmap(this.val$source, this.val$maxWidth, this.val$maxHeight, this.val$options, this.val$rotation)) {
                    final CameraUtils$2 this$0;
                    final Bitmap val$bitmap;
                    
                    public void run() {
                        this.this$0.val$callback.onBitmapReady(this.val$bitmap);
                    }
                });
            }
        });
    }
    
    public static void decodeBitmap(final byte[] array, final int n, final int n2, final BitmapFactory$Options bitmapFactory$Options, final BitmapCallback bitmapCallback) {
        decodeBitmap(array, n, n2, bitmapFactory$Options, -1, bitmapCallback);
    }
    
    public static void decodeBitmap(final byte[] array, final int n, final int n2, final BitmapCallback bitmapCallback) {
        decodeBitmap(array, n, n2, new BitmapFactory$Options(), bitmapCallback);
    }
    
    public static void decodeBitmap(final byte[] array, final BitmapCallback bitmapCallback) {
        decodeBitmap(array, Integer.MAX_VALUE, Integer.MAX_VALUE, bitmapCallback);
    }
    
    public static boolean hasCameraFacing(final Context context, final Facing facing) {
        final int mapFacing = Camera1Mapper.get().mapFacing(facing);
        final Camera$CameraInfo camera$CameraInfo = new Camera$CameraInfo();
        for (int numberOfCameras = Camera.getNumberOfCameras(), i = 0; i < numberOfCameras; ++i) {
            Camera.getCameraInfo(i, camera$CameraInfo);
            if (camera$CameraInfo.facing == mapFacing) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasCameras(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.front");
    }
    
    public static File writeToFile(final byte[] array, final File file) {
        if (file.exists() && !file.delete()) {
            return null;
        }
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)new FileOutputStream(file));
            try {
                ((OutputStream)bufferedOutputStream).write(array);
                ((OutputStream)bufferedOutputStream).flush();
                ((OutputStream)bufferedOutputStream).close();
                return file;
            }
            finally {
                try {
                    ((OutputStream)bufferedOutputStream).close();
                }
                finally {
                    final Throwable t;
                    ((Throwable)(Object)array).addSuppressed(t);
                }
            }
        }
        catch (final IOException ex) {
            CameraUtils.LOG.e("writeToFile:", "could not write file.", ex);
            return null;
        }
    }
    
    public static void writeToFile(final byte[] array, final File file, final FileCallback fileCallback) {
        WorkerHandler.execute((Runnable)new Runnable(array, file, new Handler(), fileCallback) {
            final FileCallback val$callback;
            final byte[] val$data;
            final File val$file;
            final Handler val$ui;
            
            public void run() {
                this.val$ui.post((Runnable)new Runnable(this, CameraUtils.writeToFile(this.val$data, this.val$file)) {
                    final CameraUtils$1 this$0;
                    final File val$result;
                    
                    public void run() {
                        this.this$0.val$callback.onFileReady(this.val$result);
                    }
                });
            }
        });
    }
}
