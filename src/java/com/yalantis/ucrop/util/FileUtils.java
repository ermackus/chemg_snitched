package com.yalantis.ucrop.util;

import java.io.Closeable;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Images$Media;
import android.util.Log;
import android.content.ContentUris;
import android.text.TextUtils;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.os.Build$VERSION;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.channels.WritableByteChannel;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import android.net.Uri;
import android.content.Context;
import java.text.SimpleDateFormat;

public class FileUtils
{
    public static final String GIF = ".gif";
    public static final String JPEG = ".jpeg";
    private static final String TAG = "FileUtils";
    public static final String WEBP = ".webp";
    private static final SimpleDateFormat sf;
    
    static {
        sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    }
    
    private FileUtils() {
    }
    
    public static void copyFile(final Context context, final Uri uri, final Uri uri2) throws IOException {
        if (uri.equals((Object)uri2)) {
            return;
        }
        Object channel = null;
        OutputStream outputStream;
        FileChannel fileChannel;
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                final OutputStream openOutputStream = context.getContentResolver().openOutputStream(uri2);
                if (openInputStream instanceof FileInputStream && openOutputStream instanceof FileOutputStream) {
                    channel = ((FileInputStream)openInputStream).getChannel();
                    ((FileChannel)channel).transferTo(0L, ((FileChannel)channel).size(), (WritableByteChannel)((FileOutputStream)openOutputStream).getChannel());
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                    return;
                }
                throw new IllegalArgumentException("The input or output URI don't represent a file. uCrop requires then to represent files in order to work properly.");
            }
            finally {}
        }
        finally {
            outputStream = null;
            fileChannel = (FileChannel)channel;
        }
        if (fileChannel != null) {
            ((InputStream)fileChannel).close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
    
    public static void copyFile(final String s, final String s2) throws IOException {
        if (s.equalsIgnoreCase(s2)) {
            return;
        }
        File file = null;
        FileChannel fileChannel;
        try {
            final FileChannel channel = new FileInputStream(new File(s)).getChannel();
            try {
                file = new File(s2);
                final FileChannel channel2 = new FileOutputStream(file).getChannel();
                channel.transferTo(0L, channel.size(), (WritableByteChannel)channel2);
                if (channel != null) {
                    channel.close();
                }
                if (channel2 != null) {
                    channel2.close();
                }
                return;
            }
            finally {
                file = (File)channel;
            }
        }
        finally {
            fileChannel = null;
        }
        if (file != null) {
            ((FileChannel)file).close();
        }
        if (fileChannel != null) {
            fileChannel.close();
        }
    }
    
    public static String getCreateFileName() {
        return FileUtils.sf.format((Object)System.currentTimeMillis());
    }
    
    public static String getCreateFileName(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(FileUtils.sf.format((Object)currentTimeMillis));
        return sb.toString();
    }
    
    public static String getDataColumn(final Context p0, final Uri p1, final String p2, final String[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     7: aload_1        
        //     8: iconst_1       
        //     9: anewarray       Ljava/lang/String;
        //    12: dup            
        //    13: iconst_0       
        //    14: ldc             "_data"
        //    16: aastore        
        //    17: aload_2        
        //    18: aload_3        
        //    19: aconst_null    
        //    20: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    23: astore_0       
        //    24: aload_0        
        //    25: ifnull          72
        //    28: aload_0        
        //    29: astore_1       
        //    30: aload_0        
        //    31: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    36: ifeq            72
        //    39: aload_0        
        //    40: astore_1       
        //    41: aload_0        
        //    42: aload_0        
        //    43: ldc             "_data"
        //    45: invokeinterface android/database/Cursor.getColumnIndexOrThrow:(Ljava/lang/String;)I
        //    50: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    55: astore_2       
        //    56: aload_0        
        //    57: ifnull          66
        //    60: aload_0        
        //    61: invokeinterface android/database/Cursor.close:()V
        //    66: aload_2        
        //    67: areturn        
        //    68: astore_2       
        //    69: goto            95
        //    72: aload_0        
        //    73: ifnull          129
        //    76: aload_0        
        //    77: invokeinterface android/database/Cursor.close:()V
        //    82: goto            129
        //    85: astore_1       
        //    86: aload           4
        //    88: astore_0       
        //    89: goto            136
        //    92: astore_2       
        //    93: aconst_null    
        //    94: astore_0       
        //    95: aload_0        
        //    96: astore_1       
        //    97: ldc             "FileUtils"
        //    99: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   102: ldc             "getDataColumn: _data - [%s]"
        //   104: iconst_1       
        //   105: anewarray       Ljava/lang/Object;
        //   108: dup            
        //   109: iconst_0       
        //   110: aload_2        
        //   111: invokevirtual   java/lang/IllegalArgumentException.getMessage:()Ljava/lang/String;
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   121: pop            
        //   122: aload_0        
        //   123: ifnull          129
        //   126: goto            76
        //   129: aconst_null    
        //   130: areturn        
        //   131: astore_2       
        //   132: aload_1        
        //   133: astore_0       
        //   134: aload_2        
        //   135: astore_1       
        //   136: aload_0        
        //   137: ifnull          146
        //   140: aload_0        
        //   141: invokeinterface android/database/Cursor.close:()V
        //   146: aload_1        
        //   147: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  3      24     92     95     Ljava/lang/IllegalArgumentException;
        //  3      24     85     92     Any
        //  30     39     68     72     Ljava/lang/IllegalArgumentException;
        //  30     39     131    136    Any
        //  41     56     68     72     Ljava/lang/IllegalArgumentException;
        //  41     56     131    136    Any
        //  97     122    131    136    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    public static String getInputPath(final Uri uri) {
        String s;
        if (!isContent(uri.toString()) && !isHasHttp(uri.toString())) {
            s = uri.getPath();
        }
        else {
            s = uri.toString();
        }
        return s;
    }
    
    public static String getMimeTypeFromMediaContentUri(final Context context, final Uri uri) {
        String s;
        if (uri.getScheme().equals((Object)"content")) {
            s = context.getContentResolver().getType(uri);
        }
        else {
            s = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
        }
        return s;
    }
    
    public static String getPath(final Context context, Uri uri) {
        final boolean b = Build$VERSION.SDK_INT >= 19;
        final Uri uri2 = null;
        if (b && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append((Object)Environment.getExternalStorageDirectory());
                    sb.append("/");
                    sb.append(split[1]);
                    return sb.toString();
                }
            }
            else {
                if (isDownloadsDocument(uri)) {
                    final String documentId = DocumentsContract.getDocumentId(uri);
                    if (TextUtils.isEmpty((CharSequence)documentId)) {
                        return null;
                    }
                    try {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), (long)Long.valueOf(documentId)), null, null);
                    }
                    catch (final NumberFormatException ex) {
                        Log.i("FileUtils", ex.getMessage());
                        return null;
                    }
                }
                if (isMediaDocument(uri)) {
                    final String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    final String s = split2[0];
                    if ("image".equals((Object)s)) {
                        uri = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
                    }
                    else if ("video".equals((Object)s)) {
                        uri = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
                    }
                    else {
                        uri = uri2;
                        if ("audio".equals((Object)s)) {
                            uri = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
                        }
                    }
                    return getDataColumn(context, uri, "_id=?", new String[] { split2[1] });
                }
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    
    public static String getPostfixDefaultEmpty(final Context context, final boolean b, final Uri uri) {
        if (b) {
            final String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return "";
    }
    
    public static String getPostfixDefaultJPEG(final Context context, final boolean b, final Uri uri) {
        if (b) {
            final String mimeTypeFromMediaContentUri = getMimeTypeFromMediaContentUri(context, uri);
            if (isGif(mimeTypeFromMediaContentUri)) {
                return ".gif";
            }
            if (isWebp(mimeTypeFromMediaContentUri)) {
                return ".webp";
            }
        }
        return ".jpeg";
    }
    
    public static boolean isContent(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("content://");
    }
    
    public static boolean isDownloadsDocument(final Uri uri) {
        return "com.android.providers.downloads.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isExternalStorageDocument(final Uri uri) {
        return "com.android.externalstorage.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isGif(final String s) {
        return s != null && (s.equals((Object)"image/gif") || s.equals((Object)"image/GIF"));
    }
    
    public static boolean isGooglePhotosUri(final Uri uri) {
        return "com.google.android.apps.photos.content".equals((Object)uri.getAuthority());
    }
    
    public static boolean isHasAudio(final String s) {
        return s != null && s.startsWith("audio");
    }
    
    public static boolean isHasHttp(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        if (empty) {
            return false;
        }
        if (s.startsWith("http") || s.startsWith("https") || s.startsWith("/http") || s.startsWith("/https")) {
            b = true;
        }
        return b;
    }
    
    public static boolean isHasVideo(final String s) {
        return s != null && s.startsWith("video");
    }
    
    public static boolean isMediaDocument(final Uri uri) {
        return "com.android.providers.media.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isUrlHasVideo(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.toLowerCase().endsWith(".mp4");
    }
    
    public static boolean isWebp(final String s) {
        return s != null && (s.equals((Object)"image/webp") || s.equals((Object)"image/WEBP"));
    }
    
    public static Uri replaceOutputUri(final Context context, final boolean b, final Uri uri, final Uri uri2) {
        Uri uri3;
        try {
            final String postfixDefaultEmpty = getPostfixDefaultEmpty(context, b, uri);
            if (TextUtils.isEmpty((CharSequence)postfixDefaultEmpty)) {
                return uri2;
            }
            String s;
            if (isContent(uri2.toString())) {
                s = uri2.toString();
            }
            else {
                s = uri2.getPath();
            }
            final String replace = s.replace((CharSequence)s.substring(s.lastIndexOf(".")), (CharSequence)postfixDefaultEmpty);
            if (isContent(replace)) {
                uri3 = Uri.parse(replace);
            }
            else {
                uri3 = Uri.fromFile(new File(replace));
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            uri3 = uri2;
        }
        return uri3;
    }
    
    public static boolean writeFileFromIS(InputStream inputStream, OutputStream outputStream) {
        Object o = null;
        final InputStream inputStream2 = null;
        Label_0138: {
            try {
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    o = new BufferedOutputStream(outputStream);
                    try {
                        final byte[] array = new byte[1024];
                        while (true) {
                            final int read = bufferedInputStream.read(array);
                            if (read == -1) {
                                break;
                            }
                            outputStream.write(array, 0, read);
                        }
                        outputStream.flush();
                        BitmapLoadUtils.close((Closeable)bufferedInputStream);
                        BitmapLoadUtils.close((Closeable)o);
                        return true;
                    }
                    catch (final Exception ex) {}
                }
                catch (final Exception o) {}
            }
            catch (final Exception o) {
                outputStream = null;
                inputStream = inputStream2;
            }
            finally {
                inputStream = null;
                outputStream = (OutputStream)o;
                o = inputStream;
                break Label_0138;
            }
            try {
                ((Exception)o).printStackTrace();
                BitmapLoadUtils.close((Closeable)inputStream);
                BitmapLoadUtils.close((Closeable)outputStream);
                return false;
            }
            finally {
                o = outputStream;
                outputStream = (OutputStream)inputStream;
            }
        }
        BitmapLoadUtils.close((Closeable)outputStream);
        BitmapLoadUtils.close((Closeable)o);
        throw;
    }
}
