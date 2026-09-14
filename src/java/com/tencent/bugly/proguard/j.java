package com.tencent.bugly.proguard;

import java.util.Map$Entry;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;

public final class j
{
    private ByteBuffer a;
    private String b;
    
    public j() {
        this(128);
    }
    
    public j(final int n) {
        this.b = "GBK";
        this.a = ByteBuffer.allocate(n);
    }
    
    private void a(final int n) {
        if (this.a.remaining() < n) {
            final ByteBuffer allocate = ByteBuffer.allocate(this.a.capacity() + n << 1);
            allocate.put(this.a.array(), 0, this.a.position());
            this.a = allocate;
        }
    }
    
    private void b(final byte b, final int n) {
        if (n < 15) {
            this.a.put((byte)(b | n << 4));
            return;
        }
        if (n < 256) {
            this.a.put((byte)(b | 0xF0));
            this.a.put((byte)n);
            return;
        }
        final StringBuilder sb = new StringBuilder("tag is too large: ");
        sb.append(n);
        throw new b(sb.toString());
    }
    
    public final int a(final String b) {
        this.b = b;
        return 0;
    }
    
    public final ByteBuffer a() {
        return this.a;
    }
    
    public final void a(final byte b, final int n) {
        this.a(3);
        if (b == 0) {
            this.b((byte)12, n);
            return;
        }
        this.b((byte)0, n);
        this.a.put(b);
    }
    
    public final void a(final int n, final int n2) {
        this.a(6);
        if (n >= -32768 && n <= 32767) {
            this.a((short)n, n2);
            return;
        }
        this.b((byte)2, n2);
        this.a.putInt(n);
    }
    
    public final void a(final long n, final int n2) {
        this.a(10);
        if (n >= -2147483648L && n <= 2147483647L) {
            this.a((int)n, n2);
            return;
        }
        this.b((byte)3, n2);
        this.a.putLong(n);
    }
    
    public final void a(final k k, final int n) {
        this.a(2);
        this.b((byte)10, n);
        k.a(this);
        this.a(2);
        this.b((byte)11, 0);
    }
    
    public final void a(final Object o, int i) {
        if (o instanceof Byte) {
            this.a((byte)o, i);
            return;
        }
        if (o instanceof Boolean) {
            this.a((byte)(((boolean)o) ? 1 : 0), i);
            return;
        }
        if (o instanceof Short) {
            this.a((short)o, i);
            return;
        }
        if (o instanceof Integer) {
            this.a((int)o, i);
            return;
        }
        if (o instanceof Long) {
            this.a((long)o, i);
            return;
        }
        if (o instanceof Float) {
            final float floatValue = (float)o;
            this.a(6);
            this.b((byte)4, i);
            this.a.putFloat(floatValue);
            return;
        }
        if (o instanceof Double) {
            final double doubleValue = (double)o;
            this.a(10);
            this.b((byte)5, i);
            this.a.putDouble(doubleValue);
            return;
        }
        if (o instanceof String) {
            this.a((String)o, i);
            return;
        }
        if (o instanceof Map) {
            this.a((java.util.Map<Object, Object>)o, i);
            return;
        }
        if (o instanceof List) {
            this.a((java.util.Collection<Object>)o, i);
            return;
        }
        if (o instanceof k) {
            final k k = (k)o;
            this.a(2);
            this.b((byte)10, i);
            k.a(this);
            this.a(2);
            this.b((byte)11, 0);
            return;
        }
        if (o instanceof byte[]) {
            this.a((byte[])o, i);
            return;
        }
        if (o instanceof boolean[]) {
            final boolean[] array = (boolean[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array.length, 0);
            int length;
            for (length = array.length, i = 0; i < length; ++i) {
                this.a((byte)(array[i] ? 1 : 0), 0);
            }
            return;
        }
        if (o instanceof short[]) {
            final short[] array2 = (short[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array2.length, 0);
            int length2;
            for (length2 = array2.length, i = 0; i < length2; ++i) {
                this.a(array2[i], 0);
            }
            return;
        }
        if (o instanceof int[]) {
            final int[] array3 = (int[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array3.length, 0);
            int length3;
            for (length3 = array3.length, i = 0; i < length3; ++i) {
                this.a(array3[i], 0);
            }
            return;
        }
        if (o instanceof long[]) {
            final long[] array4 = (long[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array4.length, 0);
            int length4;
            for (length4 = array4.length, i = 0; i < length4; ++i) {
                this.a(array4[i], 0);
            }
            return;
        }
        if (o instanceof float[]) {
            final float[] array5 = (float[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array5.length, 0);
            int length5;
            float n;
            for (length5 = array5.length, i = 0; i < length5; ++i) {
                n = array5[i];
                this.a(6);
                this.b((byte)4, 0);
                this.a.putFloat(n);
            }
            return;
        }
        if (o instanceof double[]) {
            final double[] array6 = (double[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array6.length, 0);
            int length6;
            double n2;
            for (length6 = array6.length, i = 0; i < length6; ++i) {
                n2 = array6[i];
                this.a(10);
                this.b((byte)5, 0);
                this.a.putDouble(n2);
            }
            return;
        }
        if (o.getClass().isArray()) {
            final Object[] array7 = (Object[])o;
            this.a(8);
            this.b((byte)9, i);
            this.a(array7.length, 0);
            int length7;
            for (length7 = array7.length, i = 0; i < length7; ++i) {
                this.a(array7[i], 0);
            }
            return;
        }
        if (o instanceof Collection) {
            this.a((java.util.Collection<Object>)o, i);
            return;
        }
        final StringBuilder sb = new StringBuilder("write object error: unsupport type. ");
        sb.append((Object)o.getClass());
        throw new b(sb.toString());
    }
    
    public final void a(String s, final int n) {
        try {
            s = (String)(Object)s.getBytes(this.b);
        }
        catch (final UnsupportedEncodingException ex) {
            s = (String)(Object)s.getBytes();
        }
        this.a(s.length + 10);
        if (s.length > 255) {
            this.b((byte)7, n);
            this.a.putInt(s.length);
            this.a.put((byte[])(Object)s);
            return;
        }
        this.b((byte)6, n);
        this.a.put((byte)s.length);
        this.a.put((byte[])(Object)s);
    }
    
    public final <T> void a(final Collection<T> collection, int size) {
        this.a(8);
        this.b((byte)9, size);
        if (collection == null) {
            size = 0;
        }
        else {
            size = collection.size();
        }
        this.a(size, 0);
        if (collection != null) {
            final Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.a(iterator.next(), 0);
            }
        }
    }
    
    public final <K, V> void a(final Map<K, V> map, int size) {
        this.a(8);
        this.b((byte)8, size);
        if (map == null) {
            size = 0;
        }
        else {
            size = map.size();
        }
        this.a(size, 0);
        if (map != null) {
            for (final Map$Entry map$Entry : map.entrySet()) {
                this.a(map$Entry.getKey(), 0);
                this.a(map$Entry.getValue(), 1);
            }
        }
    }
    
    public final void a(final short n, final int n2) {
        this.a(4);
        if (n >= -128 && n <= 127) {
            this.a((byte)n, n2);
            return;
        }
        this.b((byte)1, n2);
        this.a.putShort(n);
    }
    
    public final void a(final boolean b, final int n) {
        this.a((byte)(b ? 1 : 0), n);
    }
    
    public final void a(final byte[] array, final int n) {
        this.a(array.length + 8);
        this.b((byte)13, n);
        this.b((byte)0, 0);
        this.a(array.length, 0);
        this.a.put(array);
    }
    
    public final byte[] b() {
        final byte[] array = new byte[this.a.position()];
        System.arraycopy((Object)this.a.array(), 0, (Object)array, 0, this.a.position());
        return array;
    }
}
