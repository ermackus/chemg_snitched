package com.tencent.bugly.proguard;

import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;

public final class ag implements ae
{
    public final byte[] a(byte[] byteArray) throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ZipOutputStream zipOutputStream = new ZipOutputStream((OutputStream)byteArrayOutputStream);
        final ZipEntry zipEntry = new ZipEntry("zip");
        zipEntry.setSize((long)byteArray.length);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(byteArray);
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
    
    public final byte[] b(byte[] byteArray) throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        final ZipInputStream zipInputStream = new ZipInputStream((InputStream)byteArrayInputStream);
        byteArray = null;
        while (zipInputStream.getNextEntry() != null) {
            byteArray = new byte[1024];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                final int read = zipInputStream.read(byteArray, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(byteArray, 0, read);
            }
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        zipInputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }
}
