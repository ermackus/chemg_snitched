package com.alibaba.sdk.android.man.crashreporter.a.c.a;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class a
{
    public Map<String, Object> c;
    
    public a() {
        this.c = (Map<String, Object>)new HashMap();
    }
    
    private byte[] a(final Map map) {
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
    
    public byte[] a() {
        final Map<String, Object> c = this.c;
        Label_0033: {
            if (c == null || c.size() <= 0) {
                break Label_0033;
            }
            try {
                final byte[] a = this.a(this.c);
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
