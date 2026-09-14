package com.tencent.open.utils;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.ProtocolException;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.zip.ZipException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;

public final class b
{
    private static final l a;
    private static final m b;
    
    static {
        a = new l(101010256L);
        b = new m(38651);
    }
    
    public static String a(final File file) throws IOException {
        return a(file, "channelNo");
    }
    
    public static String a(final File file, final String s) throws IOException {
        Object a = null;
        byte[] array;
        try {
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                a = a(randomAccessFile);
                if (a == null) {
                    randomAccessFile.close();
                    return null;
                }
                final a a2 = new a();
                a2.a((byte[])a);
                final String property = a2.a.getProperty(s);
                randomAccessFile.close();
                return property;
            }
            finally {}
        }
        finally {
            array = (byte[])a;
        }
        if (array != null) {
            ((RandomAccessFile)(Object)array).close();
        }
    }
    
    private static byte[] a(final RandomAccessFile randomAccessFile) throws IOException {
        long n = randomAccessFile.length() - 22L;
        randomAccessFile.seek(n);
        final byte[] a = com.tencent.open.utils.b.a.a();
        int n2 = randomAccessFile.read();
        int n4;
        while (true) {
            final int n3 = 1;
            if (n2 == -1) {
                n4 = 0;
                break;
            }
            if (n2 == a[0] && randomAccessFile.read() == a[1] && randomAccessFile.read() == a[2] && randomAccessFile.read() == a[3]) {
                n4 = n3;
                break;
            }
            --n;
            randomAccessFile.seek(n);
            n2 = randomAccessFile.read();
        }
        if (n4 == 0) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek(n + 16L + 4L);
        final byte[] array = new byte[2];
        randomAccessFile.readFully(array);
        final int b = new m(array).b();
        if (b == 0) {
            return null;
        }
        final byte[] array2 = new byte[b];
        randomAccessFile.read(array2);
        return array2;
    }
    
    private static class a
    {
        Properties a;
        byte[] b;
        
        private a() {
            this.a = new Properties();
        }
        
        void a(byte[] b) throws IOException {
            if (b == null) {
                return;
            }
            final ByteBuffer wrap = ByteBuffer.wrap(b);
            final int length = b.b.a().length;
            final byte[] array = new byte[length];
            wrap.get(array);
            if (!b.b.equals(new m(array))) {
                final StringBuilder sb = new StringBuilder();
                sb.append("unknow protocl [");
                sb.append(Arrays.toString(b));
                sb.append("]");
                throw new ProtocolException(sb.toString());
            }
            if (b.length - length <= 2) {
                return;
            }
            final byte[] array2 = new byte[2];
            wrap.get(array2);
            final int b2 = new m(array2).b();
            if (b.length - length - 2 < b2) {
                return;
            }
            final byte[] array3 = new byte[b2];
            wrap.get(array3);
            this.a.load((InputStream)new ByteArrayInputStream(array3));
            final int n = b.length - length - b2 - 2;
            if (n > 0) {
                b = new byte[n];
                wrap.get(this.b = b);
            }
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("ApkExternalInfo [p=");
            sb.append((Object)this.a);
            sb.append(", otherData=");
            sb.append(Arrays.toString(this.b));
            sb.append("]");
            return sb.toString();
        }
    }
}
