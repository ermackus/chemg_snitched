package com.yalantis.ucrop.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import android.text.TextUtils;
import java.io.IOException;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.content.Context;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ImageHeaderParser
{
    private static final int[] BYTES_PER_FORMAT;
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    public static final int UNKNOWN_ORIENTATION = -1;
    private final Reader reader;
    
    static {
        JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
        BYTES_PER_FORMAT = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
    }
    
    public ImageHeaderParser(final InputStream inputStream) {
        this.reader = (Reader)new StreamReader(inputStream);
    }
    
    private static int calcTagOffset(final int n, final int n2) {
        return n + 2 + n2 * 12;
    }
    
    public static void copyExif(final Context p0, final int p1, final int p2, final Uri p3, final Uri p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       13
        //     4: ldc             "ImageHeaderParser"
        //     6: ldc             "context is null"
        //     8: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: return         
        //    13: aconst_null    
        //    14: astore          8
        //    16: aconst_null    
        //    17: astore          5
        //    19: aconst_null    
        //    20: astore          9
        //    22: aconst_null    
        //    23: astore          7
        //    25: aload_0        
        //    26: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    29: aload_3        
        //    30: invokevirtual   android/content/ContentResolver.openInputStream:(Landroid/net/Uri;)Ljava/io/InputStream;
        //    33: astore          6
        //    35: aload           7
        //    37: astore_3       
        //    38: aload           8
        //    40: astore          5
        //    42: new             Landroidx/exifinterface/media/ExifInterface;
        //    45: astore          9
        //    47: aload           7
        //    49: astore_3       
        //    50: aload           8
        //    52: astore          5
        //    54: aload           9
        //    56: aload           6
        //    58: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/io/InputStream;)V
        //    61: aload           7
        //    63: astore_3       
        //    64: aload           8
        //    66: astore          5
        //    68: aload_0        
        //    69: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    72: aload           4
        //    74: ldc             "rw"
        //    76: invokevirtual   android/content/ContentResolver.openFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
        //    79: astore_0       
        //    80: aload_0        
        //    81: astore_3       
        //    82: aload_0        
        //    83: astore          5
        //    85: new             Landroidx/exifinterface/media/ExifInterface;
        //    88: astore          4
        //    90: aload_0        
        //    91: astore_3       
        //    92: aload_0        
        //    93: astore          5
        //    95: aload           4
        //    97: aload_0        
        //    98: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //   101: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/io/FileDescriptor;)V
        //   104: aload_0        
        //   105: astore_3       
        //   106: aload_0        
        //   107: astore          5
        //   109: aload           9
        //   111: aload           4
        //   113: iload_1        
        //   114: iload_2        
        //   115: invokestatic    com/yalantis/ucrop/util/ImageHeaderParser.copyExifAttributes:(Landroidx/exifinterface/media/ExifInterface;Landroidx/exifinterface/media/ExifInterface;II)V
        //   118: aload           6
        //   120: ifnull          143
        //   123: aload           6
        //   125: invokevirtual   java/io/InputStream.close:()V
        //   128: goto            143
        //   131: astore_3       
        //   132: ldc             "ImageHeaderParser"
        //   134: aload_3        
        //   135: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   138: aload_3        
        //   139: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   142: pop            
        //   143: aload_0        
        //   144: ifnull          257
        //   147: aload_0        
        //   148: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   151: goto            257
        //   154: astore          5
        //   156: aload_3        
        //   157: astore          4
        //   159: aload           6
        //   161: astore_0       
        //   162: aload           5
        //   164: astore_3       
        //   165: goto            266
        //   168: astore_3       
        //   169: aload           6
        //   171: astore_0       
        //   172: aload_3        
        //   173: astore          4
        //   175: aload           5
        //   177: astore_3       
        //   178: goto            198
        //   181: astore_3       
        //   182: aconst_null    
        //   183: astore          4
        //   185: aload           9
        //   187: astore_0       
        //   188: goto            266
        //   191: astore          4
        //   193: aconst_null    
        //   194: astore_3       
        //   195: aload           5
        //   197: astore_0       
        //   198: ldc             "ImageHeaderParser"
        //   200: aload           4
        //   202: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   205: aload           4
        //   207: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   210: pop            
        //   211: aload_0        
        //   212: ifnull          234
        //   215: aload_0        
        //   216: invokevirtual   java/io/InputStream.close:()V
        //   219: goto            234
        //   222: astore_0       
        //   223: ldc             "ImageHeaderParser"
        //   225: aload_0        
        //   226: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   229: aload_0        
        //   230: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   233: pop            
        //   234: aload_3        
        //   235: ifnull          257
        //   238: aload_3        
        //   239: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   242: goto            257
        //   245: astore_0       
        //   246: ldc             "ImageHeaderParser"
        //   248: aload_0        
        //   249: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   252: aload_0        
        //   253: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   256: pop            
        //   257: return         
        //   258: astore          5
        //   260: aload_3        
        //   261: astore          4
        //   263: aload           5
        //   265: astore_3       
        //   266: aload_0        
        //   267: ifnull          289
        //   270: aload_0        
        //   271: invokevirtual   java/io/InputStream.close:()V
        //   274: goto            289
        //   277: astore_0       
        //   278: ldc             "ImageHeaderParser"
        //   280: aload_0        
        //   281: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   284: aload_0        
        //   285: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   288: pop            
        //   289: aload           4
        //   291: ifnull          314
        //   294: aload           4
        //   296: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   299: goto            314
        //   302: astore_0       
        //   303: ldc             "ImageHeaderParser"
        //   305: aload_0        
        //   306: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   309: aload_0        
        //   310: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   313: pop            
        //   314: aload_3        
        //   315: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  25     35     191    198    Ljava/io/IOException;
        //  25     35     181    191    Any
        //  42     47     168    181    Ljava/io/IOException;
        //  42     47     154    168    Any
        //  54     61     168    181    Ljava/io/IOException;
        //  54     61     154    168    Any
        //  68     80     168    181    Ljava/io/IOException;
        //  68     80     154    168    Any
        //  85     90     168    181    Ljava/io/IOException;
        //  85     90     154    168    Any
        //  95     104    168    181    Ljava/io/IOException;
        //  95     104    154    168    Any
        //  109    118    168    181    Ljava/io/IOException;
        //  109    118    154    168    Any
        //  123    128    131    143    Ljava/io/IOException;
        //  147    151    245    257    Ljava/io/IOException;
        //  198    211    258    266    Any
        //  215    219    222    234    Ljava/io/IOException;
        //  238    242    245    257    Ljava/io/IOException;
        //  270    274    277    289    Ljava/io/IOException;
        //  294    299    302    314    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0234:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
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
    
    public static void copyExif(final Context p0, final int p1, final int p2, final Uri p3, final String p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       13
        //     4: ldc             "ImageHeaderParser"
        //     6: ldc             "context is null"
        //     8: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: return         
        //    13: aconst_null    
        //    14: astore          6
        //    16: aconst_null    
        //    17: astore          5
        //    19: aload_0        
        //    20: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    23: aload_3        
        //    24: invokevirtual   android/content/ContentResolver.openInputStream:(Landroid/net/Uri;)Ljava/io/InputStream;
        //    27: astore_0       
        //    28: aload_0        
        //    29: astore          5
        //    31: aload_0        
        //    32: astore          6
        //    34: new             Landroidx/exifinterface/media/ExifInterface;
        //    37: astore          7
        //    39: aload_0        
        //    40: astore          5
        //    42: aload_0        
        //    43: astore          6
        //    45: aload           7
        //    47: aload_0        
        //    48: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/io/InputStream;)V
        //    51: aload_0        
        //    52: astore          5
        //    54: aload_0        
        //    55: astore          6
        //    57: new             Landroidx/exifinterface/media/ExifInterface;
        //    60: astore_3       
        //    61: aload_0        
        //    62: astore          5
        //    64: aload_0        
        //    65: astore          6
        //    67: aload_3        
        //    68: aload           4
        //    70: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/lang/String;)V
        //    73: aload_0        
        //    74: astore          5
        //    76: aload_0        
        //    77: astore          6
        //    79: aload           7
        //    81: aload_3        
        //    82: iload_1        
        //    83: iload_2        
        //    84: invokestatic    com/yalantis/ucrop/util/ImageHeaderParser.copyExifAttributes:(Landroidx/exifinterface/media/ExifInterface;Landroidx/exifinterface/media/ExifInterface;II)V
        //    87: aload_0        
        //    88: ifnull          143
        //    91: aload_0        
        //    92: invokevirtual   java/io/InputStream.close:()V
        //    95: goto            143
        //    98: astore_0       
        //    99: goto            144
        //   102: astore_0       
        //   103: aload           6
        //   105: astore          5
        //   107: ldc             "ImageHeaderParser"
        //   109: aload_0        
        //   110: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   113: aload_0        
        //   114: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   117: pop            
        //   118: aload           6
        //   120: ifnull          143
        //   123: aload           6
        //   125: invokevirtual   java/io/InputStream.close:()V
        //   128: goto            143
        //   131: astore_0       
        //   132: ldc             "ImageHeaderParser"
        //   134: aload_0        
        //   135: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   138: aload_0        
        //   139: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   142: pop            
        //   143: return         
        //   144: aload           5
        //   146: ifnull          169
        //   149: aload           5
        //   151: invokevirtual   java/io/InputStream.close:()V
        //   154: goto            169
        //   157: astore_3       
        //   158: ldc             "ImageHeaderParser"
        //   160: aload_3        
        //   161: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   164: aload_3        
        //   165: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   168: pop            
        //   169: aload_0        
        //   170: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     28     102    131    Ljava/io/IOException;
        //  19     28     98     171    Any
        //  34     39     102    131    Ljava/io/IOException;
        //  34     39     98     171    Any
        //  45     51     102    131    Ljava/io/IOException;
        //  45     51     98     171    Any
        //  57     61     102    131    Ljava/io/IOException;
        //  57     61     98     171    Any
        //  67     73     102    131    Ljava/io/IOException;
        //  67     73     98     171    Any
        //  79     87     102    131    Ljava/io/IOException;
        //  79     87     98     171    Any
        //  91     95     131    143    Ljava/io/IOException;
        //  107    118    98     171    Any
        //  123    128    131    143    Ljava/io/IOException;
        //  149    154    157    169    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 94, Size: 94
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
    
    public static void copyExif(final Context p0, final ExifInterface p1, final int p2, final int p3, final Uri p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       13
        //     4: ldc             "ImageHeaderParser"
        //     6: ldc             "context is null"
        //     8: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: return         
        //    13: aconst_null    
        //    14: astore          6
        //    16: aconst_null    
        //    17: astore          5
        //    19: aload_0        
        //    20: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    23: aload           4
        //    25: ldc             "rw"
        //    27: invokevirtual   android/content/ContentResolver.openFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
        //    30: astore_0       
        //    31: aload_0        
        //    32: astore          5
        //    34: aload_0        
        //    35: astore          6
        //    37: new             Landroidx/exifinterface/media/ExifInterface;
        //    40: astore          4
        //    42: aload_0        
        //    43: astore          5
        //    45: aload_0        
        //    46: astore          6
        //    48: aload           4
        //    50: aload_0        
        //    51: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    54: invokespecial   androidx/exifinterface/media/ExifInterface.<init>:(Ljava/io/FileDescriptor;)V
        //    57: aload_0        
        //    58: astore          5
        //    60: aload_0        
        //    61: astore          6
        //    63: aload_1        
        //    64: aload           4
        //    66: iload_2        
        //    67: iload_3        
        //    68: invokestatic    com/yalantis/ucrop/util/ImageHeaderParser.copyExifAttributes:(Landroidx/exifinterface/media/ExifInterface;Landroidx/exifinterface/media/ExifInterface;II)V
        //    71: aload_0        
        //    72: ifnull          126
        //    75: aload_0        
        //    76: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //    79: goto            126
        //    82: astore_0       
        //    83: goto            127
        //    86: astore_0       
        //    87: aload           6
        //    89: astore          5
        //    91: ldc             "ImageHeaderParser"
        //    93: aload_0        
        //    94: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //    97: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   100: pop            
        //   101: aload           6
        //   103: ifnull          126
        //   106: aload           6
        //   108: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   111: goto            126
        //   114: astore_0       
        //   115: ldc             "ImageHeaderParser"
        //   117: aload_0        
        //   118: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   121: aload_0        
        //   122: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   125: pop            
        //   126: return         
        //   127: aload           5
        //   129: ifnull          152
        //   132: aload           5
        //   134: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   137: goto            152
        //   140: astore_1       
        //   141: ldc             "ImageHeaderParser"
        //   143: aload_1        
        //   144: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   147: aload_1        
        //   148: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   151: pop            
        //   152: aload_0        
        //   153: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     31     86     114    Ljava/io/IOException;
        //  19     31     82     154    Any
        //  37     42     86     114    Ljava/io/IOException;
        //  37     42     82     154    Any
        //  48     57     86     114    Ljava/io/IOException;
        //  48     57     82     154    Any
        //  63     71     86     114    Ljava/io/IOException;
        //  63     71     82     154    Any
        //  75     79     114    126    Ljava/io/IOException;
        //  91     101    82     154    Any
        //  106    111    114    126    Ljava/io/IOException;
        //  132    137    140    152    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 82, Size: 82
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
    
    public static void copyExif(final ExifInterface exifInterface, final int n, final int n2, final String s) {
        try {
            copyExifAttributes(exifInterface, new ExifInterface(s), n, n2);
        }
        catch (final IOException ex) {
            Log.d("ImageHeaderParser", ex.getMessage());
        }
    }
    
    private static void copyExifAttributes(final ExifInterface exifInterface, final ExifInterface exifInterface2, final int n, final int n2) throws IOException {
        for (int i = 0; i < 22; ++i) {
            final String s = (new String[] { "FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "PhotographicSensitivity", "Make", "Model", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance" })[i];
            final String attribute = exifInterface.getAttribute(s);
            if (!TextUtils.isEmpty((CharSequence)attribute)) {
                exifInterface2.setAttribute(s, attribute);
            }
        }
        exifInterface2.setAttribute("ImageWidth", String.valueOf(n));
        exifInterface2.setAttribute("ImageLength", String.valueOf(n2));
        exifInterface2.setAttribute("Orientation", "0");
        exifInterface2.saveAttributes();
    }
    
    private static boolean handles(final int n) {
        return (n & 0xFFD8) == 0xFFD8 || n == 19789 || n == 18761;
    }
    
    private boolean hasJpegExifPreamble(final byte[] array, int n) {
        final boolean b = false;
        boolean b2 = array != null && n > ImageHeaderParser.JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (b2) {
            n = 0;
            while (true) {
                final byte[] jpeg_EXIF_SEGMENT_PREAMBLE_BYTES = ImageHeaderParser.JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (n >= jpeg_EXIF_SEGMENT_PREAMBLE_BYTES.length) {
                    break;
                }
                if (array[n] != jpeg_EXIF_SEGMENT_PREAMBLE_BYTES[n]) {
                    b2 = b;
                    break;
                }
                ++n;
            }
        }
        return b2;
    }
    
    private int moveToExifSegmentAndGetLength() throws IOException {
        long skip;
        long n;
        short uInt9;
        int n2;
        do {
            final short uInt8 = this.reader.getUInt8();
            if (uInt8 != 255) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Unknown segmentId=");
                    sb.append((int)uInt8);
                    Log.d("ImageHeaderParser", sb.toString());
                }
                return -1;
            }
            uInt9 = this.reader.getUInt8();
            if (uInt9 == 218) {
                return -1;
            }
            if (uInt9 == 217) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            n2 = this.reader.getUInt16() - 2;
            if (uInt9 == 225) {
                return n2;
            }
            final Reader reader = this.reader;
            n = n2;
            skip = reader.skip(n);
        } while (skip == n);
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to skip enough data, type: ");
            sb2.append((int)uInt9);
            sb2.append(", wanted to skip: ");
            sb2.append(n2);
            sb2.append(", but actually skipped: ");
            sb2.append(skip);
            Log.d("ImageHeaderParser", sb2.toString());
        }
        return -1;
    }
    
    private static int parseExifSegment(final RandomAccessReader randomAccessReader) {
        final short int16 = randomAccessReader.getInt16(6);
        ByteOrder byteOrder;
        if (int16 == 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        else if (int16 == 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Unknown endianness = ");
                sb.append((int)int16);
                Log.d("ImageHeaderParser", sb.toString());
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        final int n = randomAccessReader.getInt32(10) + 6;
        for (short int17 = randomAccessReader.getInt16(n), n2 = 0; n2 < int17; ++n2) {
            final int calcTagOffset = calcTagOffset(n, n2);
            final short int18 = randomAccessReader.getInt16(calcTagOffset);
            if (int18 == 274) {
                final short int19 = randomAccessReader.getInt16(calcTagOffset + 2);
                if (int19 >= 1 && int19 <= 12) {
                    final int int20 = randomAccessReader.getInt32(calcTagOffset + 4);
                    if (int20 < 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Negative tiff component count");
                        }
                    }
                    else {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("Got tagIndex=");
                            sb2.append((int)n2);
                            sb2.append(" tagType=");
                            sb2.append((int)int18);
                            sb2.append(" formatCode=");
                            sb2.append((int)int19);
                            sb2.append(" componentCount=");
                            sb2.append(int20);
                            Log.d("ImageHeaderParser", sb2.toString());
                        }
                        final int n3 = int20 + ImageHeaderParser.BYTES_PER_FORMAT[int19];
                        if (n3 > 4) {
                            if (Log.isLoggable("ImageHeaderParser", 3)) {
                                final StringBuilder sb3 = new StringBuilder();
                                sb3.append("Got byte count > 4, not orientation, continuing, formatCode=");
                                sb3.append((int)int19);
                                Log.d("ImageHeaderParser", sb3.toString());
                            }
                        }
                        else {
                            final int n4 = calcTagOffset + 8;
                            if (n4 >= 0 && n4 <= randomAccessReader.length()) {
                                if (n3 >= 0 && n3 + n4 <= randomAccessReader.length()) {
                                    return randomAccessReader.getInt16(n4);
                                }
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    final StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Illegal number of bytes for TI tag data tagType=");
                                    sb4.append((int)int18);
                                    Log.d("ImageHeaderParser", sb4.toString());
                                }
                            }
                            else if (Log.isLoggable("ImageHeaderParser", 3)) {
                                final StringBuilder sb5 = new StringBuilder();
                                sb5.append("Illegal tagValueOffset=");
                                sb5.append(n4);
                                sb5.append(" tagType=");
                                sb5.append((int)int18);
                                Log.d("ImageHeaderParser", sb5.toString());
                            }
                        }
                    }
                }
                else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    final StringBuilder sb6 = new StringBuilder();
                    sb6.append("Got invalid format code = ");
                    sb6.append((int)int19);
                    Log.d("ImageHeaderParser", sb6.toString());
                }
            }
        }
        return -1;
    }
    
    private int parseExifSegment(final byte[] array, final int n) throws IOException {
        final int read = this.reader.read(array, n);
        if (read != n) {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Unable to read exif segment data, length: ");
                sb.append(n);
                sb.append(", actually read: ");
                sb.append(read);
                Log.d("ImageHeaderParser", sb.toString());
            }
            return -1;
        }
        if (this.hasJpegExifPreamble(array, n)) {
            return parseExifSegment(new RandomAccessReader(array, n));
        }
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            Log.d("ImageHeaderParser", "Missing jpeg exif preamble");
        }
        return -1;
    }
    
    public int getOrientation() throws IOException {
        final int uInt16 = this.reader.getUInt16();
        if (!handles(uInt16)) {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Parser doesn't handle magic number: ");
                sb.append(uInt16);
                Log.d("ImageHeaderParser", sb.toString());
            }
            return -1;
        }
        final int moveToExifSegmentAndGetLength = this.moveToExifSegmentAndGetLength();
        if (moveToExifSegmentAndGetLength == -1) {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        return this.parseExifSegment(new byte[moveToExifSegmentAndGetLength], moveToExifSegmentAndGetLength);
    }
    
    private static class RandomAccessReader
    {
        private final ByteBuffer data;
        
        public RandomAccessReader(final byte[] array, final int n) {
            this.data = (ByteBuffer)ByteBuffer.wrap(array).order(ByteOrder.BIG_ENDIAN).limit(n);
        }
        
        public short getInt16(final int n) {
            return this.data.getShort(n);
        }
        
        public int getInt32(final int n) {
            return this.data.getInt(n);
        }
        
        public int length() {
            return this.data.remaining();
        }
        
        public void order(final ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }
    }
    
    private interface Reader
    {
        int getUInt16() throws IOException;
        
        short getUInt8() throws IOException;
        
        int read(final byte[] p0, final int p1) throws IOException;
        
        long skip(final long p0) throws IOException;
    }
    
    private static class StreamReader implements Reader
    {
        private final InputStream is;
        
        public StreamReader(final InputStream is) {
            this.is = is;
        }
        
        @Override
        public int getUInt16() throws IOException {
            return (this.is.read() << 8 & 0xFF00) | (this.is.read() & 0xFF);
        }
        
        @Override
        public short getUInt8() throws IOException {
            return (short)(this.is.read() & 0xFF);
        }
        
        @Override
        public int read(final byte[] array, final int n) throws IOException {
            int i;
            int read;
            for (i = n; i > 0; i -= read) {
                read = this.is.read(array, n - i, i);
                if (read == -1) {
                    break;
                }
            }
            return n - i;
        }
        
        @Override
        public long skip(final long n) throws IOException {
            if (n < 0L) {
                return 0L;
            }
            long n2;
            long skip;
            for (n2 = n; n2 > 0L; n2 -= skip) {
                skip = this.is.skip(n2);
                if (skip <= 0L) {
                    if (this.is.read() == -1) {
                        break;
                    }
                    skip = 1L;
                }
            }
            return n - n2;
        }
    }
}
