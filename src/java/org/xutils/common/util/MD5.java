package org.xutils.common.util;

import java.nio.channels.FileChannel;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel$MapMode;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.io.File;

public final class MD5
{
    private static final char[] hexDigits;
    
    static {
        hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    private MD5() {
    }
    
    public static String md5(File file) throws IOException {
        Object o = null;
        Object o2 = null;
        Object channel = null;
        final Closeable closeable = null;
        Label_0132: {
            try {
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                final FileInputStream fileInputStream = new FileInputStream(file);
                channel = closeable;
                o2 = o;
                try {
                    o = (o2 = (channel = fileInputStream.getChannel()));
                    instance.update((ByteBuffer)((FileChannel)o).map(FileChannel$MapMode.READ_ONLY, 0L, file.length()));
                    channel = o;
                    o2 = o;
                    final byte[] digest = instance.digest();
                    IOUtil.closeQuietly((Closeable)fileInputStream);
                    IOUtil.closeQuietly((Closeable)o);
                    return toHexString(digest);
                }
                catch (final NoSuchAlgorithmException o) {
                    channel = o2;
                }
            }
            catch (final NoSuchAlgorithmException o) {
                channel = null;
                file = (File)o2;
            }
            finally {
                final Closeable closeable2 = null;
                file = (File)channel;
                channel = closeable2;
                break Label_0132;
            }
            try {
                throw new RuntimeException((Throwable)o);
            }
            finally {}
        }
        IOUtil.closeQuietly((Closeable)file);
        IOUtil.closeQuietly((Closeable)channel);
        throw;
    }
    
    public static String md5(final String s) {
        try {
            return toHexString(MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8")));
        }
        catch (final UnsupportedEncodingException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        catch (final NoSuchAlgorithmException ex2) {
            throw new RuntimeException((Throwable)ex2);
        }
    }
    
    public static String toHexString(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (final byte b : array) {
            sb.append(MD5.hexDigits[b >> 4 & 0xF]);
            sb.append(MD5.hexDigits[b & 0xF]);
        }
        return sb.toString();
    }
}
