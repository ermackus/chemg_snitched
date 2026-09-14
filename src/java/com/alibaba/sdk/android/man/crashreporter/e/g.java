package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.StreamCorruptedException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class g
{
    public static Map a(final byte[] array) {
        try {
            return b(array);
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    private static byte[] a(final Map map) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream)byteArrayOutputStream);
            objectOutputStream.writeObject((Object)map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            return null;
        }
    }
    
    private static Map b(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final ObjectInputStream objectInputStream = new ObjectInputStream((InputStream)byteArrayInputStream);
            final Map map = (Map)objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return map;
        }
        catch (final StreamCorruptedException | ClassNotFoundException | IOException ex) {
            return null;
        }
    }
    
    public static byte[] c(final Map map) {
        Label_0024: {
            if (map == null || map.size() <= 0) {
                break Label_0024;
            }
            try {
                final byte[] a = a(map);
                if (a != null) {
                    return a;
                }
                return null;
            }
            catch (final Exception ex) {
                return null;
            }
        }
    }
}
