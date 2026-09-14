package com.goodsrc.library.utils;

import java.io.InputStream;
import android.database.Cursor;
import java.io.FileOutputStream;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Images$Media;
import android.content.ContentUris;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.content.Context;
import androidx.core.content.FileProvider;
import android.os.Build$VERSION;
import com.goodsrc.library.core.LibraryApplication;
import android.net.Uri;
import java.io.File;

public class UriUtil
{
    private UriUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static Uri File2Uri(final File file) {
        final Context context = LibraryApplication.getContext();
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri uri;
        if (Build$VERSION.SDK_INT >= 24) {
            final StringBuilder sb = new StringBuilder();
            sb.append(context.getPackageName());
            sb.append(".fileProvider");
            uri = FileProvider.getUriForFile(context, sb.toString(), file);
        }
        else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
    
    public static Uri File2Uri(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        return File2Uri(new File(s));
    }
    
    public static String Uri2File(final Uri uri) {
        final Context context = LibraryApplication.getContext();
        final Uri uri2 = null;
        if (uri != null && context != null) {
            final boolean equalsIgnoreCase = "file".equalsIgnoreCase(uri.getScheme());
            final String s = "";
            String s2;
            if (equalsIgnoreCase) {
                s2 = uri.getPath();
            }
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    s2 = uri.getLastPathSegment();
                }
                else if ("com.tencent.mm.external.fileprovider".equalsIgnoreCase(uri.getAuthority())) {
                    final String path = uri.getPath();
                    if ((s2 = path) != null) {
                        s2 = path;
                        if (path.startsWith("/external/")) {
                            s2 = path.replace((CharSequence)"/external", (CharSequence)SDCardUtil.getSDCardEnvironmentPath());
                        }
                    }
                }
                else if ("com.tencent.mobileqq.fileprovider".equalsIgnoreCase(uri.getAuthority())) {
                    s2 = uri.getPath();
                    if (s2 != null && s2.startsWith("/external_files/")) {
                        s2 = s2.replace((CharSequence)"/external_files", (CharSequence)"");
                    }
                }
                else {
                    s2 = getDataColumn(context, uri, null, null);
                }
            }
            else {
                s2 = s;
                if (Build$VERSION.SDK_INT >= 19) {
                    s2 = s;
                    if (DocumentsContract.isDocumentUri(context, uri)) {
                        if (isExternalStorageDocument(uri)) {
                            final String[] split = DocumentsContract.getDocumentId(uri).split(":");
                            s2 = s;
                            if ("primary".equalsIgnoreCase(split[0])) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append((Object)Environment.getExternalStorageDirectory());
                                sb.append("/");
                                sb.append(split[1]);
                                s2 = sb.toString();
                            }
                        }
                        else if (isDownloadsDocument(uri)) {
                            s2 = getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), (long)Long.valueOf(DocumentsContract.getDocumentId(uri))), null, null);
                        }
                        else {
                            s2 = s;
                            if (isMediaDocument(uri)) {
                                final String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                                final String s3 = split2[0];
                                Uri uri3;
                                if ("image".equals((Object)s3)) {
                                    uri3 = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
                                }
                                else if ("video".equals((Object)s3)) {
                                    uri3 = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
                                }
                                else {
                                    uri3 = uri2;
                                    if ("audio".equals((Object)s3)) {
                                        uri3 = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
                                    }
                                }
                                s2 = getDataColumn(context, uri3, "_id=?", new String[] { split2[1] });
                            }
                        }
                    }
                }
            }
            String filePathForN = s2;
            if (TextUtils.isEmpty((CharSequence)s2)) {
                filePathForN = s2;
                if (Build$VERSION.SDK_INT >= 24) {
                    filePathForN = getFilePathForN(context, uri);
                }
            }
            return filePathForN;
        }
        return null;
    }
    
    public static String getDataColumn(final Context p0, final Uri p1, final String p2, final String[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
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
        //    25: ifnull          84
        //    28: aload_0        
        //    29: astore_1       
        //    30: aload_0        
        //    31: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    36: ifeq            84
        //    39: aload_0        
        //    40: astore_1       
        //    41: aload_0        
        //    42: ldc             "_data"
        //    44: invokeinterface android/database/Cursor.getColumnIndexOrThrow:(Ljava/lang/String;)I
        //    49: istore          4
        //    51: iload           4
        //    53: iconst_m1      
        //    54: if_icmple       84
        //    57: aload_0        
        //    58: astore_1       
        //    59: aload_0        
        //    60: iload           4
        //    62: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    67: astore_2       
        //    68: aload_0        
        //    69: ifnull          78
        //    72: aload_0        
        //    73: invokeinterface android/database/Cursor.close:()V
        //    78: aload_2        
        //    79: areturn        
        //    80: astore_2       
        //    81: goto            101
        //    84: aload_0        
        //    85: ifnull          117
        //    88: goto            111
        //    91: astore_0       
        //    92: aload           5
        //    94: astore_1       
        //    95: goto            120
        //    98: astore_2       
        //    99: aconst_null    
        //   100: astore_0       
        //   101: aload_0        
        //   102: astore_1       
        //   103: aload_2        
        //   104: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   107: aload_0        
        //   108: ifnull          117
        //   111: aload_0        
        //   112: invokeinterface android/database/Cursor.close:()V
        //   117: aconst_null    
        //   118: areturn        
        //   119: astore_0       
        //   120: aload_1        
        //   121: ifnull          130
        //   124: aload_1        
        //   125: invokeinterface android/database/Cursor.close:()V
        //   130: aload_0        
        //   131: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      24     98     101    Ljava/lang/Exception;
        //  3      24     91     98     Any
        //  30     39     80     84     Ljava/lang/Exception;
        //  30     39     119    120    Any
        //  41     51     80     84     Ljava/lang/Exception;
        //  41     51     119    120    Any
        //  59     68     80     84     Ljava/lang/Exception;
        //  59     68     119    120    Any
        //  103    107    119    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
    
    private static String getFilePathForN(final Context context, final Uri uri) {
        try {
            final Cursor query = context.getContentResolver().query(uri, (String[])null, (String)null, (String[])null, (String)null);
            final int columnIndex = query.getColumnIndex("_display_name");
            query.moveToFirst();
            final File file = new File(context.getFilesDir(), query.getString(columnIndex));
            final InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final byte[] array = new byte[Math.min(openInputStream.available(), 1048576)];
            while (true) {
                final int read = openInputStream.read(array);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(array, 0, read);
            }
            query.close();
            openInputStream.close();
            fileOutputStream.close();
            return file.getPath();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static boolean isDownloadsDocument(final Uri uri) {
        return "com.android.providers.downloads.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isExternalStorageDocument(final Uri uri) {
        return "com.android.externalstorage.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isGooglePhotosUri(final Uri uri) {
        return "com.google.android.apps.photos.content".equals((Object)uri.getAuthority());
    }
    
    public static boolean isMediaDocument(final Uri uri) {
        return "com.android.providers.media.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isUriPath(final String s) {
        return s.startsWith("content:/") || s.startsWith("file:/");
    }
}
