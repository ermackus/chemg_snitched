package com.luck.picture.lib.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import androidx.core.content.FileProvider;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.provider.MediaStore$Audio$Media;
import android.provider.MediaStore$Video$Media;
import android.provider.MediaStore$Images$Media;
import android.content.ContentUris;
import android.provider.DocumentsContract;
import android.os.Build$VERSION;
import android.database.Cursor;
import android.net.Uri;
import java.util.Locale;
import com.luck.picture.lib.config.SelectMimeType;
import java.util.Objects;
import android.os.Environment;
import android.text.TextUtils;
import com.luck.picture.lib.config.PictureMimeType;
import java.io.File;
import android.content.Context;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Closeable;

public class PictureFileUtils
{
    private static final int BYTE_SIZE = 1024;
    private static final String POSTFIX_AMR = ".amr";
    private static final String POSTFIX_JPG = ".jpg";
    private static final String POSTFIX_MP4 = ".mp4";
    static final String TAG = "PictureFileUtils";
    
    private PictureFileUtils() {
    }
    
    public static void close(final Closeable closeable) {
        if (!(closeable instanceof Closeable)) {
            return;
        }
        try {
            closeable.close();
        }
        catch (final Exception ex) {}
    }
    
    public static void copyFile(String s, String s2) {
        if (s.equalsIgnoreCase(s2)) {
            return;
        }
        final String s3 = null;
        Object o = null;
        final String s4 = null;
        final Object o2 = null;
        final Exception ex;
        Label_0145: {
            try {
                final FileChannel channel = new FileInputStream(s).getChannel();
                s = s3;
                try {
                    s = s3;
                    o = new FileOutputStream(s2);
                    s = s3;
                    final FileChannel channel2;
                    final FileChannel fileChannel = (FileChannel)(s = (String)(channel2 = ((FileOutputStream)o).getChannel()));
                    channel.transferTo(0L, channel.size(), (WritableByteChannel)fileChannel);
                    close((Closeable)channel);
                    close((Closeable)fileChannel);
                }
                catch (final Exception channel2) {}
            }
            catch (final Exception ex) {
                s2 = null;
                s = (String)o;
            }
            finally {
                s2 = null;
                s = s4;
                break Label_0145;
            }
            try {
                ex.printStackTrace();
                close((Closeable)s);
                close((Closeable)s2);
                return;
            }
            finally {}
        }
        close((Closeable)s);
        close((Closeable)s2);
        throw ex;
    }
    
    public static File createCameraFile(final Context context, final int n, final String s, final String s2, final String s3) {
        return createMediaFile(context, n, s, s2, s3);
    }
    
    public static String createFilePath(final Context context, String string, final String s) {
        final String lastSourceSuffix = PictureMimeType.getLastSourceSuffix(string);
        File file;
        String s2;
        if (PictureMimeType.isHasVideo(string)) {
            file = getRootDirFile(context, 2);
            s2 = "VID_";
        }
        else if (PictureMimeType.isHasAudio(string)) {
            file = getRootDirFile(context, 3);
            s2 = "AUD_";
        }
        else {
            file = getRootDirFile(context, 1);
            s2 = "IMG_";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(file.getPath());
        sb.append(File.separator);
        string = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(DateUtils.getCreateFileName(s2));
            sb2.append(lastSourceSuffix);
            string = sb2.toString();
        }
        sb.append(string);
        return sb.toString();
    }
    
    private static File createMediaFile(final Context context, final int n, final String s, final String s2, final String s3) {
        return createOutFile(context, n, s, s2, s3);
    }
    
    private static File createOutFile(Context applicationContext, final int n, String s, final String s2, final String s3) {
        applicationContext = applicationContext.getApplicationContext();
        File file3;
        if (TextUtils.isEmpty((CharSequence)s3)) {
            File file;
            File file2;
            if (TextUtils.equals((CharSequence)"mounted", (CharSequence)Environment.getExternalStorageState())) {
                file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                final StringBuilder sb = new StringBuilder();
                sb.append(file.getAbsolutePath());
                sb.append(File.separator);
                sb.append("Camera");
                sb.append(File.separator);
                file2 = new File(sb.toString());
            }
            else {
                file = getRootDirFile(applicationContext, n);
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getAbsolutePath());
                sb2.append(File.separator);
                file2 = new File(sb2.toString());
            }
            file3 = file2;
            if (!file.exists()) {
                file.mkdirs();
                file3 = file2;
            }
        }
        else {
            file3 = new File(s3);
            if (!((File)Objects.requireNonNull((Object)file3.getParentFile())).exists()) {
                file3.getParentFile().mkdirs();
            }
        }
        if (!file3.exists()) {
            file3.mkdirs();
        }
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        if (n == 2) {
            if (empty) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(DateUtils.getCreateFileName("VID_"));
                sb3.append(".mp4");
                s = sb3.toString();
            }
            return new File(file3, s);
        }
        if (n != 3) {
            String s4 = s2;
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s4 = ".jpg";
            }
            if (empty) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(DateUtils.getCreateFileName("IMG_"));
                sb4.append(s4);
                s = sb4.toString();
            }
            return new File(file3, s);
        }
        if (empty) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(DateUtils.getCreateFileName("AUD_"));
            sb5.append(".amr");
            s = sb5.toString();
        }
        return new File(file3, s);
    }
    
    @Deprecated
    public static void deleteAllCacheDirFile(final Context context) {
        final File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        final int n = 0;
        if (externalFilesDir != null) {
            final File[] listFiles = externalFilesDir.listFiles();
            if (listFiles != null) {
                for (final File file : listFiles) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
        final File externalFilesDir2 = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        if (externalFilesDir2 != null) {
            final File[] listFiles2 = externalFilesDir2.listFiles();
            if (listFiles2 != null) {
                for (final File file2 : listFiles2) {
                    if (file2.isFile()) {
                        file2.delete();
                    }
                }
            }
        }
        final File externalFilesDir3 = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (externalFilesDir3 != null) {
            final File[] listFiles3 = externalFilesDir3.listFiles();
            if (listFiles3 != null) {
                for (int length3 = listFiles3.length, k = n; k < length3; ++k) {
                    final File file3 = listFiles3[k];
                    if (file3.isFile()) {
                        file3.delete();
                    }
                }
            }
        }
    }
    
    @Deprecated
    public static void deleteCacheDirFile(final Context context, int i) {
        String s;
        if (i == SelectMimeType.ofImage()) {
            s = Environment.DIRECTORY_PICTURES;
        }
        else {
            s = Environment.DIRECTORY_MOVIES;
        }
        final File externalFilesDir = context.getExternalFilesDir(s);
        if (externalFilesDir != null) {
            final File[] listFiles = externalFilesDir.listFiles();
            if (listFiles != null) {
                int length;
                File file;
                for (length = listFiles.length, i = 0; i < length; ++i) {
                    file = listFiles[i];
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }
    
    public static String formatAccurateUnitFileSize(final long n) {
        if (n >= 0L) {
            double n2;
            String s;
            if (n < 1000L) {
                n2 = (double)n;
                s = "";
            }
            else if (n < 1000000L) {
                n2 = n / 1000.0;
                s = "KB";
            }
            else if (n < 1000000000L) {
                n2 = n / 1000000.0;
                s = "MB";
            }
            else {
                n2 = n / 1.0E9;
                s = "GB";
            }
            final String format = String.format(new Locale("zh"), "%.2f", new Object[] { n2 });
            final StringBuilder sb = new StringBuilder();
            Object value = format;
            if (Math.round(ValueOf.toDouble(format)) - ValueOf.toDouble(format) == 0.0) {
                value = Math.round(ValueOf.toDouble(format));
            }
            sb.append(value);
            sb.append(s);
            return sb.toString();
        }
        throw new IllegalArgumentException("byteSize shouldn't be less than zero!");
    }
    
    public static String formatFileSize(long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("byteSize shouldn't be less than zero!");
        }
        if (n < 1024L) {
            Object o = String.format("%.2f", new Object[] { Double.valueOf(n) });
            final double double1 = ValueOf.toDouble(o);
            n = Math.round(double1);
            final StringBuilder sb = new StringBuilder();
            if (n - double1 == 0.0) {
                o = n;
            }
            sb.append(o);
            sb.append("B");
            return sb.toString();
        }
        if (n < 1048576L) {
            Object o2 = String.format("%.2f", new Object[] { n / 1024.0 });
            final double double2 = ValueOf.toDouble(o2);
            n = Math.round(double2);
            final StringBuilder sb2 = new StringBuilder();
            if (n - double2 == 0.0) {
                o2 = n;
            }
            sb2.append(o2);
            sb2.append("KB");
            return sb2.toString();
        }
        if (n < 1073741824L) {
            Object o3 = String.format("%.2f", new Object[] { n / 1048576.0 });
            final double double3 = ValueOf.toDouble(o3);
            n = Math.round(double3);
            final StringBuilder sb3 = new StringBuilder();
            if (n - double3 == 0.0) {
                o3 = n;
            }
            sb3.append(o3);
            sb3.append("MB");
            return sb3.toString();
        }
        Object o4 = String.format("%.2f", new Object[] { n / 1.073741824E9 });
        final double double4 = ValueOf.toDouble(o4);
        n = Math.round(double4);
        final StringBuilder sb4 = new StringBuilder();
        if (n - double4 == 0.0) {
            o4 = n;
        }
        sb4.append(o4);
        sb4.append("GB");
        return sb4.toString();
    }
    
    public static String getDataColumn(final Context context, final Uri uri, final String s, final String[] array) {
        Cursor cursor = null;
        Cursor cursor2 = null;
        try {
            while (true) {
                try {
                    final Cursor query = context.getContentResolver().query(uri, new String[] { "_data" }, s, array, (String)null);
                    if (query != null) {
                        cursor2 = query;
                        cursor = query;
                        if (query.moveToFirst()) {
                            cursor2 = query;
                            cursor = query;
                            final String string = query.getString(query.getColumnIndexOrThrow("_data"));
                            if (query != null) {
                                query.close();
                            }
                            return string;
                        }
                    }
                    if (query != null) {
                        query.close();
                        return "";
                    }
                    return "";
                }
                finally {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    final Cursor query = cursor;
                    continue;
                }
                break;
            }
        }
        catch (final IllegalArgumentException ex) {}
    }
    
    public static String getPath(final Context context, final Uri uri) {
        final Context applicationContext = context.getApplicationContext();
        final boolean b = Build$VERSION.SDK_INT >= 19;
        Uri uri2 = null;
        if (b && DocumentsContract.isDocumentUri(applicationContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    if (SdkVersionUtils.isQ()) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append((Object)applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                        sb.append("/");
                        sb.append(split[1]);
                        return sb.toString();
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append((Object)Environment.getExternalStorageDirectory());
                    sb2.append("/");
                    sb2.append(split[1]);
                    return sb2.toString();
                }
            }
            else {
                if (isDownloadsDocument(uri)) {
                    return getDataColumn(applicationContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), ValueOf.toLong(DocumentsContract.getDocumentId(uri))), null, null);
                }
                if (isMediaDocument(uri)) {
                    final String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    final String s = split2[0];
                    if ("image".equals((Object)s)) {
                        uri2 = MediaStore$Images$Media.EXTERNAL_CONTENT_URI;
                    }
                    else if ("video".equals((Object)s)) {
                        uri2 = MediaStore$Video$Media.EXTERNAL_CONTENT_URI;
                    }
                    else if ("audio".equals((Object)s)) {
                        uri2 = MediaStore$Audio$Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(applicationContext, uri2, "_id=?", new String[] { split2[1] });
                }
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(applicationContext, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return "";
    }
    
    private static File getRootDirFile(final Context context, final int n) {
        return new File(FileDirMap.getFileDirPath(context, n));
    }
    
    public static String getVideoThumbnailDir(final Context context) {
        final File file = new File(context.getExternalFilesDir("").getAbsolutePath(), "VideoThumbnail");
        if (!file.exists()) {
            file.mkdirs();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append(File.separator);
        return sb.toString();
    }
    
    public static boolean isDownloadsDocument(final Uri uri) {
        return "com.android.providers.downloads.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isExternalStorageDocument(final Uri uri) {
        return "com.android.externalstorage.documents".equals((Object)uri.getAuthority());
    }
    
    public static boolean isFileExists(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && new File(s).exists();
    }
    
    public static boolean isGooglePhotosUri(final Uri uri) {
        return "com.google.android.apps.photos.content".equals((Object)uri.getAuthority());
    }
    
    public static boolean isImageFileExists(final String s) {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        boolean b = true;
        bitmapFactory$Options.inJustDecodeBounds = true;
        bitmapFactory$Options.inSampleSize = 2;
        BitmapFactory.decodeFile(s, bitmapFactory$Options);
        if (bitmapFactory$Options.outWidth <= 0 || bitmapFactory$Options.outHeight <= 0) {
            b = false;
        }
        return b;
    }
    
    public static boolean isMediaDocument(final Uri uri) {
        return "com.android.providers.media.documents".equals((Object)uri.getAuthority());
    }
    
    public static Uri parUri(final Context context, final File file) {
        final StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append(".luckProvider");
        final String string = sb.toString();
        Uri uri;
        if (Build$VERSION.SDK_INT > 23) {
            uri = FileProvider.getUriForFile(context, string, file);
        }
        else {
            uri = Uri.fromFile(file);
        }
        return uri;
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
                        close((Closeable)bufferedInputStream);
                        close((Closeable)o);
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
                close((Closeable)inputStream);
                close((Closeable)outputStream);
                return false;
            }
            finally {
                o = outputStream;
                outputStream = (OutputStream)inputStream;
            }
        }
        close((Closeable)outputStream);
        close((Closeable)o);
        throw;
    }
}
