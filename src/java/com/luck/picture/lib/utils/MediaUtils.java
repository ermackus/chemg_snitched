package com.luck.picture.lib.utils;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.media.MediaMetadataRetriever;
import android.content.ContentUris;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Files;
import android.webkit.MimeTypeMap;
import java.net.URLConnection;
import com.luck.picture.lib.app.PictureAppMaster;
import java.io.Closeable;
import android.graphics.Rect;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import java.io.FileInputStream;
import com.luck.picture.lib.basic.PictureContentResolver;
import android.graphics.BitmapFactory$Options;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.provider.MediaStore$Images$Media;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.entity.MediaExtraInfo;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import java.io.File;
import android.net.Uri;
import com.luck.picture.lib.config.PictureMimeType;
import android.text.TextUtils;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.Bundle;

public class MediaUtils
{
    public static final String QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit";
    
    public static Bundle createQueryArgsBundle(final String s, final String[] array, final int n, final int n2, final String s2) {
        final Bundle bundle = new Bundle();
        if (Build$VERSION.SDK_INT >= 26) {
            bundle.putString("android:query-arg-sql-selection", s);
            bundle.putStringArray("android:query-arg-sql-selection-args", array);
            bundle.putString("android:query-arg-sql-sort-order", s2);
            if (SdkVersionUtils.isR()) {
                final StringBuilder sb = new StringBuilder();
                sb.append(n);
                sb.append(" offset ");
                sb.append(n2);
                bundle.putString("android:query-arg-sql-limit", sb.toString());
            }
        }
        return bundle;
    }
    
    public static void deleteUri(final Context context, final String s) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s) && PictureMimeType.isContent(s)) {
                context.getContentResolver().delete(Uri.parse(s), (String)null, (String[])null);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String generateCameraFolderName(String name) {
        final File file = new File(name);
        if (file.getParentFile() != null) {
            name = file.getParentFile().getName();
        }
        else {
            name = "Camera";
        }
        return name;
    }
    
    public static void getAsyncVideoThumbnail(final Context context, final String s, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils.Task<Object>)new MediaUtils$3(context, s, (OnCallbackListener)onCallbackListener));
    }
    
    public static MediaExtraInfo getAudioSize(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   com/luck/picture/lib/entity/MediaExtraInfo.<init>:()V
        //     7: astore_2       
        //     8: aload_1        
        //     9: invokestatic    com/luck/picture/lib/config/PictureMimeType.isHasHttp:(Ljava/lang/String;)Z
        //    12: ifeq            17
        //    15: aload_2        
        //    16: areturn        
        //    17: new             Landroid/media/MediaMetadataRetriever;
        //    20: dup            
        //    21: invokespecial   android/media/MediaMetadataRetriever.<init>:()V
        //    24: astore_3       
        //    25: aload_1        
        //    26: invokestatic    com/luck/picture/lib/config/PictureMimeType.isContent:(Ljava/lang/String;)Z
        //    29: ifeq            44
        //    32: aload_3        
        //    33: aload_0        
        //    34: aload_1        
        //    35: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    38: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Landroid/content/Context;Landroid/net/Uri;)V
        //    41: goto            49
        //    44: aload_3        
        //    45: aload_1        
        //    46: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Ljava/lang/String;)V
        //    49: aload_2        
        //    50: aload_3        
        //    51: bipush          9
        //    53: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //    56: invokestatic    com/luck/picture/lib/utils/ValueOf.toLong:(Ljava/lang/Object;)J
        //    59: invokevirtual   com/luck/picture/lib/entity/MediaExtraInfo.setDuration:(J)V
        //    62: aload_3        
        //    63: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //    66: goto            90
        //    69: astore_0       
        //    70: goto            92
        //    73: astore_0       
        //    74: aload_0        
        //    75: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    78: aload_3        
        //    79: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //    82: goto            90
        //    85: astore_0       
        //    86: aload_0        
        //    87: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    90: aload_2        
        //    91: areturn        
        //    92: aload_3        
        //    93: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //    96: goto            104
        //    99: astore_1       
        //   100: aload_1        
        //   101: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   104: aload_0        
        //   105: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  25     41     73     85     Ljava/lang/Exception;
        //  25     41     69     106    Any
        //  44     49     73     85     Ljava/lang/Exception;
        //  44     49     69     106    Any
        //  49     62     73     85     Ljava/lang/Exception;
        //  49     62     69     106    Any
        //  62     66     85     90     Ljava/lang/Exception;
        //  74     78     69     106    Any
        //  78     82     85     90     Ljava/lang/Exception;
        //  92     96     99     104    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 55, Size: 55
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
    
    public static int getDCIMLastImageId(final Context context, final String s) {
        int n = -1;
        final Cursor cursor = null;
        Cursor cursor3;
        final Cursor cursor2 = cursor3 = null;
        Cursor cursor4 = cursor;
        try {
            try {
                final String[] array = { null };
                cursor3 = cursor2;
                cursor4 = cursor;
                cursor3 = cursor2;
                cursor4 = cursor;
                final StringBuilder sb = new StringBuilder();
                cursor3 = cursor2;
                cursor4 = cursor;
                sb.append("%");
                cursor3 = cursor2;
                cursor4 = cursor;
                sb.append(s);
                cursor3 = cursor2;
                cursor4 = cursor;
                sb.append("%");
                cursor3 = cursor2;
                cursor4 = cursor;
                array[0] = sb.toString();
                cursor3 = cursor2;
                cursor4 = cursor;
                Cursor cursor5;
                if (SdkVersionUtils.isR()) {
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    final Bundle queryArgsBundle = createQueryArgsBundle("_data like ?", array, 1, 0, "_id DESC");
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    cursor5 = context.getApplicationContext().getContentResolver().query(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, (String[])null, queryArgsBundle, (CancellationSignal)null);
                }
                else {
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    cursor5 = context.getApplicationContext().getContentResolver().query(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, (String[])null, "_data like ?", array, "_id DESC limit 1 offset 0");
                }
                if (cursor5 != null) {
                    cursor3 = cursor5;
                    cursor4 = cursor5;
                    if (cursor5.getCount() > 0) {
                        cursor3 = cursor5;
                        cursor4 = cursor5;
                        if (cursor5.moveToFirst()) {
                            cursor3 = cursor5;
                            cursor4 = cursor5;
                            final int int1 = cursor5.getInt(cursor5.getColumnIndex("_id"));
                            cursor3 = cursor5;
                            cursor4 = cursor5;
                            if (DateUtils.dateDiffer(cursor5.getLong(cursor5.getColumnIndex("date_added"))) <= 1) {
                                n = int1;
                            }
                            if (cursor5 != null) {
                                cursor5.close();
                            }
                            return n;
                        }
                    }
                }
                if (cursor5 != null) {
                    cursor5.close();
                }
                return -1;
            }
            finally {
                if (cursor3 != null) {
                    cursor3.close();
                }
                cursor4.close();
                return -1;
            }
        }
        catch (final Exception ex) {}
    }
    
    public static MediaExtraInfo getImageSize(Context openInputStream, final String ex) {
        final MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        if (PictureMimeType.isHasHttp((String)ex)) {
            return mediaExtraInfo;
        }
        final Closeable closeable = null;
        Object o2;
        final Object o = o2 = null;
        Closeable closeable2;
        try {
            try {
                o2 = o;
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                o2 = o;
                bitmapFactory$Options.inJustDecodeBounds = true;
                o2 = o;
                if (PictureMimeType.isContent((String)ex)) {
                    o2 = o;
                    openInputStream = PictureContentResolver.openInputStream((Context)openInputStream, Uri.parse((String)ex));
                }
                else {
                    o2 = o;
                    openInputStream = new FileInputStream((String)ex);
                }
                try {
                    BitmapFactory.decodeStream((InputStream)openInputStream, (Rect)null, bitmapFactory$Options);
                    mediaExtraInfo.setWidth(bitmapFactory$Options.outWidth);
                    mediaExtraInfo.setHeight(bitmapFactory$Options.outHeight);
                    PictureFileUtils.close((Closeable)openInputStream);
                }
                catch (final Exception ex) {}
                finally {
                    o2 = openInputStream;
                }
            }
            finally {}
        }
        catch (final Exception ex) {
            closeable2 = closeable;
        }
        ex.printStackTrace();
        PictureFileUtils.close(closeable2);
        return mediaExtraInfo;
        PictureFileUtils.close((Closeable)o2);
    }
    
    @Deprecated
    public static MediaExtraInfo getImageSize(String openInputStream) {
        final MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        final Closeable closeable = null;
        Object o2;
        final Object o = o2 = null;
        Closeable closeable2;
        try {
            try {
                o2 = o;
                final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                o2 = o;
                bitmapFactory$Options.inJustDecodeBounds = true;
                o2 = o;
                if (PictureMimeType.isContent((String)openInputStream)) {
                    o2 = o;
                    openInputStream = PictureContentResolver.openInputStream(PictureAppMaster.getInstance().getAppContext(), Uri.parse((String)openInputStream));
                }
                else {
                    o2 = o;
                    openInputStream = new FileInputStream((String)openInputStream);
                }
                try {
                    BitmapFactory.decodeStream((InputStream)openInputStream, (Rect)null, bitmapFactory$Options);
                    mediaExtraInfo.setWidth(bitmapFactory$Options.outWidth);
                    mediaExtraInfo.setHeight(bitmapFactory$Options.outHeight);
                    PictureFileUtils.close((Closeable)openInputStream);
                }
                catch (final Exception o) {}
                finally {
                    o2 = openInputStream;
                }
            }
            finally {}
        }
        catch (final Exception o) {
            closeable2 = closeable;
        }
        ((Exception)o).printStackTrace();
        PictureFileUtils.close(closeable2);
        return mediaExtraInfo;
        PictureFileUtils.close((Closeable)o2);
    }
    
    public static void getImageSize(final Context context, final String s, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils.Task<Object>)new MediaUtils$1(context, s, (OnCallbackListener)onCallbackListener));
    }
    
    private static String getMimeType(final File file) {
        return URLConnection.getFileNameMap().getContentTypeFor(file.getName());
    }
    
    public static String getMimeTypeFromMediaHttpUrl(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        if (s.toLowerCase().endsWith(".jpg") || s.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (s.toLowerCase().endsWith(".png")) {
            return "image/png";
        }
        if (s.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        }
        if (s.toLowerCase().endsWith(".webp")) {
            return "image/webp";
        }
        if (s.toLowerCase().endsWith(".bmp")) {
            return "image/bmp";
        }
        if (s.toLowerCase().endsWith(".mp4")) {
            return "video/mp4";
        }
        if (s.toLowerCase().endsWith(".avi")) {
            return "video/avi";
        }
        if (s.toLowerCase().endsWith(".mp3")) {
            return "audio/mpeg";
        }
        if (s.toLowerCase().endsWith(".amr")) {
            return "audio/amr";
        }
        if (s.toLowerCase().endsWith(".m4a")) {
            return "audio/mpeg";
        }
        return null;
    }
    
    public static String getMimeTypeFromMediaUrl(String s) {
        Object o;
        if (TextUtils.isEmpty((CharSequence)(o = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(s).toLowerCase())))) {
            o = getMimeType(new File(s));
        }
        s = (String)o;
        if (TextUtils.isEmpty((CharSequence)o)) {
            s = "image/jpeg";
        }
        return s;
    }
    
    public static Long[] getPathMediaBucketId(final Context context, final String s) {
        final Long[] array = new Long[2];
        array[1] = (array[0] = 0L);
        final Cursor cursor = null;
        Cursor cursor3;
        final Cursor cursor2 = cursor3 = null;
        Cursor cursor4 = cursor;
        try {
            while (true) {
                try {
                    final String[] array2 = { null };
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    final StringBuilder sb = new StringBuilder();
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    sb.append("%");
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    sb.append(s);
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    sb.append("%");
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    array2[0] = sb.toString();
                    cursor3 = cursor2;
                    cursor4 = cursor;
                    Cursor cursor5;
                    if (SdkVersionUtils.isR()) {
                        cursor3 = cursor2;
                        cursor4 = cursor;
                        final Bundle queryArgsBundle = createQueryArgsBundle("_data like ?", array2, 1, 0, "_id DESC");
                        cursor3 = cursor2;
                        cursor4 = cursor;
                        cursor5 = context.getContentResolver().query(MediaStore$Files.getContentUri("external"), (String[])null, queryArgsBundle, (CancellationSignal)null);
                    }
                    else {
                        cursor3 = cursor2;
                        cursor4 = cursor;
                        cursor5 = context.getContentResolver().query(MediaStore$Files.getContentUri("external"), (String[])null, "_data like ?", array2, "_id DESC limit 1 offset 0");
                    }
                    if (cursor5 != null) {
                        cursor3 = cursor5;
                        cursor4 = cursor5;
                        if (cursor5.getCount() > 0) {
                            cursor3 = cursor5;
                            cursor4 = cursor5;
                            if (cursor5.moveToFirst()) {
                                cursor3 = cursor5;
                                cursor4 = cursor5;
                                array[0] = cursor5.getLong(cursor5.getColumnIndex("_id"));
                                cursor3 = cursor5;
                                cursor4 = cursor5;
                                array[1] = cursor5.getLong(cursor5.getColumnIndex("bucket_id"));
                            }
                        }
                    }
                    if (cursor5 != null) {
                        break Label_0323;
                    }
                    return array;
                }
                finally {
                    if (cursor3 != null) {
                        cursor3.close();
                    }
                    final Cursor cursor5;
                    cursor5.close();
                    return array;
                    cursor5 = cursor4;
                    continue;
                }
                break;
            }
        }
        catch (final Exception ex) {}
    }
    
    public static String getRealPathUri(final long n, final String s) {
        Uri uri;
        if (PictureMimeType.isHasImage(s)) {
            uri = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
        }
        else if (PictureMimeType.isHasVideo(s)) {
            uri = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
        }
        else if (PictureMimeType.isHasAudio(s)) {
            uri = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
        }
        else {
            uri = MediaStore$Files.getContentUri("external");
        }
        return ContentUris.withAppendedId(uri, n).toString();
    }
    
    public static MediaExtraInfo getVideoSize(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   com/luck/picture/lib/entity/MediaExtraInfo.<init>:()V
        //     7: astore          4
        //     9: aload_1        
        //    10: invokestatic    com/luck/picture/lib/config/PictureMimeType.isHasHttp:(Ljava/lang/String;)Z
        //    13: ifeq            19
        //    16: aload           4
        //    18: areturn        
        //    19: new             Landroid/media/MediaMetadataRetriever;
        //    22: dup            
        //    23: invokespecial   android/media/MediaMetadataRetriever.<init>:()V
        //    26: astore          5
        //    28: aload_1        
        //    29: invokestatic    com/luck/picture/lib/config/PictureMimeType.isContent:(Ljava/lang/String;)Z
        //    32: ifeq            48
        //    35: aload           5
        //    37: aload_0        
        //    38: aload_1        
        //    39: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    42: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Landroid/content/Context;Landroid/net/Uri;)V
        //    45: goto            54
        //    48: aload           5
        //    50: aload_1        
        //    51: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Ljava/lang/String;)V
        //    54: aload           5
        //    56: bipush          24
        //    58: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //    61: astore_0       
        //    62: ldc_w           "90"
        //    65: aload_0        
        //    66: invokestatic    android/text/TextUtils.equals:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //    69: ifne            110
        //    72: ldc_w           "270"
        //    75: aload_0        
        //    76: invokestatic    android/text/TextUtils.equals:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
        //    79: ifeq            85
        //    82: goto            110
        //    85: aload           5
        //    87: bipush          18
        //    89: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //    92: invokestatic    com/luck/picture/lib/utils/ValueOf.toInt:(Ljava/lang/Object;)I
        //    95: istore_3       
        //    96: aload           5
        //    98: bipush          19
        //   100: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   103: invokestatic    com/luck/picture/lib/utils/ValueOf.toInt:(Ljava/lang/Object;)I
        //   106: istore_2       
        //   107: goto            132
        //   110: aload           5
        //   112: bipush          18
        //   114: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   117: invokestatic    com/luck/picture/lib/utils/ValueOf.toInt:(Ljava/lang/Object;)I
        //   120: istore_2       
        //   121: aload           5
        //   123: bipush          19
        //   125: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   128: invokestatic    com/luck/picture/lib/utils/ValueOf.toInt:(Ljava/lang/Object;)I
        //   131: istore_3       
        //   132: aload           4
        //   134: iload_3        
        //   135: invokevirtual   com/luck/picture/lib/entity/MediaExtraInfo.setWidth:(I)V
        //   138: aload           4
        //   140: iload_2        
        //   141: invokevirtual   com/luck/picture/lib/entity/MediaExtraInfo.setHeight:(I)V
        //   144: aload           4
        //   146: aload_0        
        //   147: invokevirtual   com/luck/picture/lib/entity/MediaExtraInfo.setOrientation:(Ljava/lang/String;)V
        //   150: aload           4
        //   152: aload           5
        //   154: bipush          9
        //   156: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   159: invokestatic    com/luck/picture/lib/utils/ValueOf.toLong:(Ljava/lang/Object;)J
        //   162: invokevirtual   com/luck/picture/lib/entity/MediaExtraInfo.setDuration:(J)V
        //   165: aload           5
        //   167: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //   170: goto            195
        //   173: astore_0       
        //   174: goto            198
        //   177: astore_0       
        //   178: aload_0        
        //   179: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   182: aload           5
        //   184: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //   187: goto            195
        //   190: astore_0       
        //   191: aload_0        
        //   192: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   195: aload           4
        //   197: areturn        
        //   198: aload           5
        //   200: invokevirtual   android/media/MediaMetadataRetriever.release:()V
        //   203: goto            211
        //   206: astore_1       
        //   207: aload_1        
        //   208: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   211: aload_0        
        //   212: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  28     45     177    190    Ljava/lang/Exception;
        //  28     45     173    213    Any
        //  48     54     177    190    Ljava/lang/Exception;
        //  48     54     173    213    Any
        //  54     82     177    190    Ljava/lang/Exception;
        //  54     82     173    213    Any
        //  85     107    177    190    Ljava/lang/Exception;
        //  85     107    173    213    Any
        //  110    132    177    190    Ljava/lang/Exception;
        //  110    132    173    213    Any
        //  132    165    177    190    Ljava/lang/Exception;
        //  132    165    173    213    Any
        //  165    170    190    195    Ljava/lang/Exception;
        //  178    182    173    213    Any
        //  182    187    190    195    Ljava/lang/Exception;
        //  198    203    206    211    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 98, Size: 98
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
    
    public static void getVideoSize(final Context context, final String s, final OnCallbackListener<MediaExtraInfo> onCallbackListener) {
        PictureThreadUtils.executeByIo((PictureThreadUtils.Task<Object>)new MediaUtils$2(context, s, (OnCallbackListener)onCallbackListener));
    }
    
    public static MediaExtraInfo getVideoThumbnail(Context context, String frameAtTime) {
        final MediaExtraInfo mediaExtraInfo = new MediaExtraInfo();
        Object o = null;
        final Closeable closeable = null;
        Closeable closeable2 = null;
        Object o4 = null;
        Label_0322: {
            try {
                final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                if (PictureMimeType.isContent(frameAtTime)) {
                    mediaMetadataRetriever.setDataSource(context, Uri.parse(frameAtTime));
                }
                else {
                    mediaMetadataRetriever.setDataSource(frameAtTime);
                }
                frameAtTime = (String)mediaMetadataRetriever.getFrameAtTime();
                if (frameAtTime != null) {
                    try {
                        if (!((Bitmap)frameAtTime).isRecycled()) {
                            Object o2 = new ByteArrayOutputStream();
                            try {
                                ((Bitmap)frameAtTime).compress(Bitmap$CompressFormat.JPEG, 50, (OutputStream)o2);
                                final String videoThumbnailDir = PictureFileUtils.getVideoThumbnailDir(context);
                                o = new(java.io.File.class)();
                                final StringBuilder sb = new StringBuilder();
                                sb.append(DateUtils.getCreateFileName("vid_"));
                                sb.append("_thumb.jpg");
                                new File(videoThumbnailDir, sb.toString());
                                context = (Context)new FileOutputStream((File)o);
                                try {
                                    ((FileOutputStream)context).write(((ByteArrayOutputStream)o2).toByteArray());
                                    ((FileOutputStream)context).flush();
                                    mediaExtraInfo.setVideoThumbnail(((File)o).getAbsolutePath());
                                    mediaExtraInfo.setWidth(((Bitmap)frameAtTime).getWidth());
                                    mediaExtraInfo.setHeight(((Bitmap)frameAtTime).getHeight());
                                }
                                catch (final IOException ex) {}
                                finally {
                                    o = o2;
                                    o2 = context;
                                    final String s;
                                    frameAtTime = s;
                                }
                            }
                            catch (final IOException closeable) {
                                frameAtTime = null;
                                o = o2;
                                o2 = closeable;
                            }
                        }
                    }
                    catch (final IOException ex2) {
                        frameAtTime = null;
                        break Label_0322;
                    }
                    finally {
                        final Object o3 = frameAtTime;
                        final Closeable closeable3 = null;
                        o = closeable;
                        final String s2;
                        frameAtTime = s2;
                        closeable2 = closeable3;
                        break Label_0322;
                    }
                }
                final Object o3 = null;
                PictureFileUtils.close(closeable2);
                PictureFileUtils.close((Closeable)o3);
                if (frameAtTime != null && !((Bitmap)frameAtTime).isRecycled()) {
                    ((Bitmap)frameAtTime).recycle();
                    return mediaExtraInfo;
                }
                return mediaExtraInfo;
            }
            catch (final IOException o4) {
                frameAtTime = null;
                context = null;
            }
            finally {
                o4 = null;
                context = null;
                o = closeable;
                break Label_0322;
            }
            try {
                ((IOException)o4).printStackTrace();
                PictureFileUtils.close((Closeable)o);
                PictureFileUtils.close((Closeable)frameAtTime);
                if (context != null && !((Bitmap)context).isRecycled()) {
                    ((Bitmap)context).recycle();
                }
                return mediaExtraInfo;
            }
            finally {
                o4 = frameAtTime;
                final String s3;
                frameAtTime = s3;
            }
        }
        PictureFileUtils.close((Closeable)o);
        PictureFileUtils.close((Closeable)o4);
        if (context != null && !((Bitmap)context).isRecycled()) {
            ((Bitmap)context).recycle();
        }
        throw frameAtTime;
    }
    
    public static boolean isLongImage(final int n, final int n2) {
        boolean b2;
        final boolean b = b2 = false;
        if (n > 0) {
            if (n2 <= 0) {
                b2 = b;
            }
            else {
                b2 = b;
                if (n2 > n * 3) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static void removeMedia(final Context context, final int n) {
        try {
            context.getApplicationContext().getContentResolver().delete(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, "_id=?", new String[] { Long.toString((long)n) });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
