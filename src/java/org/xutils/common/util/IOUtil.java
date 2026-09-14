package org.xutils.common.util;

import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.Closeable;
import android.database.Cursor;

public class IOUtil
{
    private IOUtil() {
    }
    
    public static void closeQuietly(final Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            }
            finally {
                final Throwable t;
                LogUtil.d(t.getMessage(), t);
            }
        }
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            finally {
                final Throwable t;
                LogUtil.d(t.getMessage(), t);
            }
        }
    }
    
    public static void copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        Object o = inputStream;
        if (!(inputStream instanceof BufferedInputStream)) {
            o = new BufferedInputStream(inputStream);
        }
        Object o2 = outputStream;
        if (!(outputStream instanceof BufferedOutputStream)) {
            o2 = new BufferedOutputStream(outputStream);
        }
        final byte[] array = new byte[1024];
        while (true) {
            final int read = ((InputStream)o).read(array);
            if (read == -1) {
                break;
            }
            ((OutputStream)o2).write(array, 0, read);
        }
        ((OutputStream)o2).flush();
    }
    
    public static boolean deleteFileOrDir(final File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                deleteFileOrDir(listFiles[i]);
            }
        }
        return file.delete();
    }
    
    public static byte[] readBytes(final InputStream inputStream) throws IOException {
        Object o = inputStream;
        if (!(inputStream instanceof BufferedInputStream)) {
            o = new BufferedInputStream(inputStream);
        }
        final Closeable closeable = null;
        Closeable closeable2;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                final byte[] array = new byte[1024];
                while (true) {
                    final int read = ((InputStream)o).read(array);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(array, 0, read);
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                closeQuietly((Closeable)byteArrayOutputStream);
                return byteArray;
            }
            finally {}
        }
        finally {
            closeable2 = closeable;
        }
        closeQuietly(closeable2);
    }
    
    public static byte[] readBytes(final InputStream inputStream, long n, final int n2) throws IOException {
        if (n > 0L) {
            while (n > 0L) {
                final long skip = inputStream.skip(n);
                if (skip <= 0L) {
                    break;
                }
                n -= skip;
            }
        }
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = (byte)inputStream.read();
        }
        return array;
    }
    
    public static String readStr(final InputStream inputStream) throws IOException {
        return readStr(inputStream, "UTF-8");
    }
    
    public static String readStr(final InputStream inputStream, final String s) throws IOException {
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "UTF-8";
        }
        Object o = inputStream;
        if (!(inputStream instanceof BufferedInputStream)) {
            o = new BufferedInputStream(inputStream);
        }
        final InputStreamReader inputStreamReader = new InputStreamReader((InputStream)o, s2);
        final StringBuilder sb = new StringBuilder();
        final char[] array = new char[1024];
        while (true) {
            final int read = ((Reader)inputStreamReader).read(array);
            if (read < 0) {
                break;
            }
            sb.append(array, 0, read);
        }
        return sb.toString();
    }
    
    public static void writeStr(final OutputStream outputStream, final String s) throws IOException {
        writeStr(outputStream, s, "UTF-8");
    }
    
    public static void writeStr(final OutputStream outputStream, final String s, final String s2) throws IOException {
        String s3 = s2;
        if (TextUtils.isEmpty((CharSequence)s2)) {
            s3 = "UTF-8";
        }
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, s3);
        ((Writer)outputStreamWriter).write(s);
        ((Writer)outputStreamWriter).flush();
    }
}
