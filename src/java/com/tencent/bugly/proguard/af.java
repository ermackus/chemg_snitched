package com.tencent.bugly.proguard;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;

public final class af implements ae
{
    public final byte[] a(byte[] byteArray) throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
        gzipOutputStream.write(byteArray);
        gzipOutputStream.finish();
        gzipOutputStream.close();
        byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
    
    public final byte[] b(final byte[] array) throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final GZIPInputStream gzipInputStream = new GZIPInputStream((InputStream)byteArrayInputStream);
        final byte[] array2 = new byte[1024];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            final int read = gzipInputStream.read(array2, 0, 1024);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array2, 0, read);
        }
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        gzipInputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }
}
