package com.alipay.sdk.m.z;

import java.io.ByteArrayInputStream;
import android.util.Base64;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Map;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class a
{
    public static String a(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter((Writer)stringWriter));
        return stringWriter.toString();
    }
    
    public static String a(final Map<String, String> map, final String s, final String s2) {
        if (map == null) {
            return s2;
        }
        final String s3 = (String)map.get((Object)s);
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    public static boolean a(final String s) {
        if (s != null) {
            final int length = s.length();
            if (length != 0) {
                for (int i = 0; i < length; ++i) {
                    if (!Character.isWhitespace(s.charAt(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean a(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equals((Object)s2);
    }
    
    public static String b(String s, final String s2) {
        try {
            s = (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke((Object)null, new Object[] { s, s2 });
            return s;
        }
        catch (final Exception ex) {
            return s2;
        }
    }
    
    public static boolean b(final String s) {
        return !a(s);
    }
    
    public static boolean c(final String s) {
        for (final byte b : s.getBytes()) {
            if ((b >= 0 && b <= 31) || b >= 127) {
                return false;
            }
        }
        return true;
    }
    
    public static String d(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "";
        }
        return s2;
    }
    
    public static String e(String string) {
        try {
            if (a(string)) {
                return null;
            }
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(string.getBytes("UTF-8"));
            final byte[] digest = instance.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(String.format("%02x", new Object[] { digest[i] }));
            }
            string = sb.toString();
            return string;
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    public static String f(String encodeToString) {
        try {
            final byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(encodeToString.length()).array();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(encodeToString.length());
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
            gzipOutputStream.write(encodeToString.getBytes("UTF-8"));
            gzipOutputStream.close();
            byteArrayOutputStream.close();
            final byte[] array2 = new byte[byteArrayOutputStream.toByteArray().length + 4];
            System.arraycopy((Object)array, 0, (Object)array2, 0, 4);
            System.arraycopy((Object)byteArrayOutputStream.toByteArray(), 0, (Object)array2, 4, byteArrayOutputStream.toByteArray().length);
            encodeToString = Base64.encodeToString(array2, 8);
            return encodeToString;
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    public static String g(String s) {
        final boolean a = a(s);
        final String s2 = "";
        if (a) {
            return "";
        }
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes("utf-8"));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
            final byte[] array = new byte[1024];
            while (true) {
                final int read = byteArrayInputStream.read(array, 0, 1024);
                if (read == -1) {
                    break;
                }
                gzipOutputStream.write(array, 0, read);
            }
            gzipOutputStream.flush();
            gzipOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byteArrayInputStream.close();
            s = new String(Base64.encode(byteArray, 2));
            return s;
        }
        catch (final Exception ex) {
            s = s2;
            return s;
        }
    }
}
