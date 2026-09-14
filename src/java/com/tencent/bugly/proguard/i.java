package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map$Entry;
import java.util.Map;
import java.nio.ByteBuffer;

public final class i
{
    private ByteBuffer a;
    private String b;
    
    public i() {
        this.b = "GBK";
    }
    
    public i(final byte[] array) {
        this.b = "GBK";
        this.a = ByteBuffer.wrap(array);
    }
    
    public i(final byte[] array, final int n) {
        this.b = "GBK";
        (this.a = ByteBuffer.wrap(array)).position(4);
    }
    
    private double a(double double1, int a, final boolean b) {
        if (this.a(a)) {
            final a a2 = new a();
            a(a2, this.a);
            a = a2.a;
            if (a != 4) {
                if (a != 5) {
                    if (a != 12) {
                        throw new g("type mismatch.");
                    }
                    double1 = 0.0;
                }
                else {
                    double1 = this.a.getDouble();
                }
            }
            else {
                double1 = this.a.getFloat();
            }
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return double1;
    }
    
    private float a(float float1, int a, final boolean b) {
        if (this.a(a)) {
            final a a2 = new a();
            a(a2, this.a);
            a = a2.a;
            if (a != 4) {
                if (a != 12) {
                    throw new g("type mismatch.");
                }
                float1 = 0.0f;
            }
            else {
                float1 = this.a.getFloat();
            }
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return float1;
    }
    
    private static int a(final a a, final ByteBuffer byteBuffer) {
        final byte value = byteBuffer.get();
        a.a = (byte)(value & 0xF);
        a.b = (value & 0xF0) >> 4;
        if (a.b == 15) {
            a.b = byteBuffer.get();
            return 2;
        }
        return 1;
    }
    
    private <K, V> Map<K, V> a(final Map<K, V> map, final Map<K, V> map2, int i, final boolean b) {
        if (map2 != null && !map2.isEmpty()) {
            final Map$Entry map$Entry = (Map$Entry)map2.entrySet().iterator().next();
            final Object key = map$Entry.getKey();
            final Object value = map$Entry.getValue();
            if (this.a(i)) {
                final a a = new a();
                a(a, this.a);
                if (a.a != 8) {
                    throw new g("type mismatch.");
                }
                final int a2 = this.a(0, 0, true);
                if (a2 < 0) {
                    final StringBuilder sb = new StringBuilder("size invalid: ");
                    sb.append(a2);
                    throw new g(sb.toString());
                }
                for (i = 0; i < a2; ++i) {
                    map.put(this.a(key, 0, true), this.a(value, 1, true));
                }
            }
            else if (b) {
                throw new g("require field not exist.");
            }
            return map;
        }
        return (Map<K, V>)new HashMap();
    }
    
    private void a() {
        final a a = new a();
        do {
            a(a, this.a);
            this.a(a.a);
        } while (a.a != 11);
    }
    
    private void a(final byte b) {
        final int n = 0;
        final int n2 = 0;
        switch (b) {
            default: {
                throw new g("invalid type.");
            }
            case 13: {
                final a a = new a();
                a(a, this.a);
                if (a.a == 0) {
                    final int a2 = this.a(0, 0, true);
                    final ByteBuffer a3 = this.a;
                    a3.position(a3.position() + a2);
                    return;
                }
                final StringBuilder sb = new StringBuilder("skipField with invalid type, type value: ");
                sb.append((int)b);
                sb.append(", ");
                sb.append((int)a.a);
                throw new g(sb.toString());
            }
            case 11:
            case 12: {
                return;
            }
            case 10: {
                this.a();
                return;
            }
            case 9: {
                for (int a4 = this.a(0, 0, true), i = n2; i < a4; ++i) {
                    final a a5 = new a();
                    a(a5, this.a);
                    this.a(a5.a);
                }
                return;
            }
            case 8: {
                for (int a6 = this.a(0, 0, true), j = n; j < a6 << 1; ++j) {
                    final a a7 = new a();
                    a(a7, this.a);
                    this.a(a7.a);
                }
                return;
            }
            case 7: {
                final int int1 = this.a.getInt();
                final ByteBuffer a8 = this.a;
                a8.position(a8.position() + int1);
                return;
            }
            case 6: {
                int value;
                final byte b2 = (byte)(value = this.a.get());
                if (b2 < 0) {
                    value = b2 + 256;
                }
                final ByteBuffer a9 = this.a;
                a9.position(a9.position() + value);
                return;
            }
            case 5: {
                final ByteBuffer a10 = this.a;
                a10.position(a10.position() + 8);
                return;
            }
            case 4: {
                final ByteBuffer a11 = this.a;
                a11.position(a11.position() + 4);
                return;
            }
            case 3: {
                final ByteBuffer a12 = this.a;
                a12.position(a12.position() + 8);
                return;
            }
            case 2: {
                final ByteBuffer a13 = this.a;
                a13.position(a13.position() + 4);
                return;
            }
            case 1: {
                final ByteBuffer a14 = this.a;
                a14.position(a14.position() + 2);
                return;
            }
            case 0: {
                final ByteBuffer a15 = this.a;
                a15.position(a15.position() + 1);
            }
        }
    }
    
    private boolean a(final int n) {
        try {
            final a a = new a();
            while (true) {
                final int a2 = a(a, this.a.duplicate());
                if (n <= a.b || a.a == 11) {
                    break;
                }
                this.a.position(this.a.position() + a2);
                this.a(a.a);
            }
            if (n == a.b) {
                return true;
            }
            return false;
        }
        catch (final g | BufferUnderflowException ex) {
            return false;
        }
    }
    
    private <T> T[] a(final T[] array, final int n, final boolean b) {
        if (array != null && array.length != 0) {
            return this.b(array[0], n, b);
        }
        throw new g("unable to get type of key and value.");
    }
    
    private <T> T[] b(final T t, int i, final boolean b) {
        if (this.a(i)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 >= 0) {
                final Object[] array = (Object[])Array.newInstance((Class)t.getClass(), a2);
                for (i = 0; i < a2; ++i) {
                    array[i] = this.a(t, 0, true);
                }
                return (T[])array;
            }
            final StringBuilder sb = new StringBuilder("size invalid: ");
            sb.append(a2);
            throw new g(sb.toString());
        }
        else {
            if (!b) {
                return null;
            }
            throw new g("require field not exist.");
        }
    }
    
    private boolean[] d(int n, final boolean b) {
        boolean[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final boolean[] array = new boolean[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = (this.a((byte)0, 0, true) != 0);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    private short[] e(int n, final boolean b) {
        short[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final short[] array = new short[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = this.a(array[0], 0, true);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    private int[] f(int n, final boolean b) {
        int[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final int[] array = new int[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = this.a(array[0], 0, true);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    private long[] g(int n, final boolean b) {
        long[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final long[] array = new long[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = this.a(array[0], 0, true);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    private float[] h(int n, final boolean b) {
        float[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final float[] array = new float[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = this.a(array[0], 0, true);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    private double[] i(int n, final boolean b) {
        double[] array2;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            if (a.a != 9) {
                throw new g("type mismatch.");
            }
            final int a2 = this.a(0, 0, true);
            if (a2 < 0) {
                final StringBuilder sb = new StringBuilder("size invalid: ");
                sb.append(a2);
                throw new g(sb.toString());
            }
            final double[] array = new double[a2];
            n = 0;
            while (true) {
                array2 = array;
                if (n >= a2) {
                    break;
                }
                array[n] = this.a(array[0], 0, true);
                ++n;
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array2 = null;
        }
        return array2;
    }
    
    public final byte a(byte value, int a, final boolean b) {
        if (this.a(a)) {
            final a a2 = new a();
            a(a2, this.a);
            a = a2.a;
            if (a != 0) {
                if (a != 12) {
                    throw new g("type mismatch.");
                }
                value = 0;
            }
            else {
                value = this.a.get();
            }
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return value;
    }
    
    public final int a(int n, final int n2, final boolean b) {
        if (this.a(n2)) {
            final a a = new a();
            a(a, this.a);
            n = a.a;
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (n != 12) {
                            throw new g("type mismatch.");
                        }
                        n = 0;
                    }
                    else {
                        n = this.a.getInt();
                    }
                }
                else {
                    n = this.a.getShort();
                }
            }
            else {
                n = this.a.get();
            }
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return n;
    }
    
    public final int a(final String b) {
        this.b = b;
        return 0;
    }
    
    public final long a(long long1, int n, final boolean b) {
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            n = a.a;
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (n == 3) {
                            long1 = this.a.getLong();
                            return long1;
                        }
                        if (n == 12) {
                            long1 = 0L;
                            return long1;
                        }
                        throw new g("type mismatch.");
                    }
                    else {
                        n = this.a.getInt();
                    }
                }
                else {
                    n = this.a.getShort();
                }
            }
            else {
                n = this.a.get();
            }
            long1 = n;
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return long1;
    }
    
    public final k a(k k, final int n, final boolean b) {
        if (this.a(n)) {
            try {
                k = (k)k.getClass().newInstance();
                final a a = new a();
                a(a, this.a);
                if (a.a == 10) {
                    k.a(this);
                    this.a();
                    return k;
                }
                throw new g("type mismatch.");
            }
            catch (final Exception ex) {
                throw new g(ex.getMessage());
            }
        }
        if (b) {
            throw new g("require field not exist.");
        }
        k = null;
        return k;
    }
    
    public final <T> Object a(final T t, int i, final boolean b) {
        final boolean b2 = t instanceof Byte;
        final int n = 0;
        boolean b3 = false;
        if (b2) {
            return this.a((byte)0, i, b);
        }
        if (t instanceof Boolean) {
            if (this.a((byte)0, i, b) != 0) {
                b3 = true;
            }
            return b3;
        }
        if (t instanceof Short) {
            return this.a((short)0, i, b);
        }
        if (t instanceof Integer) {
            return this.a(0, i, b);
        }
        if (t instanceof Long) {
            return this.a(0L, i, b);
        }
        if (t instanceof Float) {
            return this.a(0.0f, i, b);
        }
        if (t instanceof Double) {
            return this.a(0.0, i, b);
        }
        if (t instanceof String) {
            return String.valueOf((Object)this.b(i, b));
        }
        if (t instanceof Map) {
            return this.a((java.util.Map<Object, Object>)new HashMap(), (java.util.Map<Object, Object>)t, i, b);
        }
        if (t instanceof List) {
            final List list = (List)t;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            final Object[] b4 = this.b(list.get(0), i, b);
            if (b4 == null) {
                return null;
            }
            final ArrayList list2 = new ArrayList();
            for (i = n; i < b4.length; ++i) {
                list2.add(b4[i]);
            }
            return list2;
        }
        else {
            if (t instanceof k) {
                return this.a((k)t, i, b);
            }
            if (!t.getClass().isArray()) {
                throw new g("read object error: unsupport type.");
            }
            if (t instanceof byte[] || t instanceof Byte[]) {
                return this.c(i, b);
            }
            if (t instanceof boolean[]) {
                return this.d(i, b);
            }
            if (t instanceof short[]) {
                return this.e(i, b);
            }
            if (t instanceof int[]) {
                return this.f(i, b);
            }
            if (t instanceof long[]) {
                return this.g(i, b);
            }
            if (t instanceof float[]) {
                return this.h(i, b);
            }
            if (t instanceof double[]) {
                return this.i(i, b);
            }
            return this.a((Object[])(Object)t, i, b);
        }
    }
    
    public final <K, V> HashMap<K, V> a(final Map<K, V> map, final int n, final boolean b) {
        return (HashMap<K, V>)this.a((java.util.Map<Object, Object>)new HashMap(), (java.util.Map<Object, Object>)map, n, b);
    }
    
    public final short a(short short1, int a, final boolean b) {
        if (this.a(a)) {
            final a a2 = new a();
            a(a2, this.a);
            a = a2.a;
            if (a != 0) {
                if (a != 1) {
                    if (a != 12) {
                        throw new g("type mismatch.");
                    }
                    short1 = 0;
                }
                else {
                    short1 = this.a.getShort();
                }
            }
            else {
                short1 = this.a.get();
            }
        }
        else if (b) {
            throw new g("require field not exist.");
        }
        return short1;
    }
    
    public final void a(final byte[] array) {
        final ByteBuffer a = this.a;
        if (a != null) {
            a.clear();
        }
        this.a = ByteBuffer.wrap(array);
    }
    
    public final boolean a(final int n, final boolean b) {
        return this.a((byte)0, n, b) != 0;
    }
    
    public final String b(int n, final boolean b) {
        String s;
        if (this.a(n)) {
            final a a = new a();
            a(a, this.a);
            n = a.a;
            if (n != 6) {
                if (n != 7) {
                    throw new g("type mismatch.");
                }
                n = this.a.getInt();
                if (n > 104857600 || n < 0) {
                    final StringBuilder sb = new StringBuilder("String too long: ");
                    sb.append(n);
                    throw new g(sb.toString());
                }
                final byte[] array = new byte[n];
                this.a.get(array);
                try {
                    s = new String(array, this.b);
                }
                catch (final UnsupportedEncodingException ex) {
                    s = new String(array);
                }
            }
            else {
                final int value = this.a.get();
                if ((n = value) < 0) {
                    n = value + 256;
                }
                final byte[] array2 = new byte[n];
                this.a.get(array2);
                try {
                    s = new String(array2, this.b);
                }
                catch (final UnsupportedEncodingException ex2) {
                    s = new String(array2);
                }
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            s = null;
        }
        return s;
    }
    
    public final byte[] c(int i, final boolean b) {
        byte[] array;
        if (this.a(i)) {
            final a a = new a();
            a(a, this.a);
            final byte a2 = a.a;
            if (a2 != 9) {
                if (a2 != 13) {
                    throw new g("type mismatch.");
                }
                final a a3 = new a();
                a(a3, this.a);
                if (a3.a != 0) {
                    final StringBuilder sb = new StringBuilder("type mismatch, tag: ");
                    sb.append(i);
                    sb.append(", type: ");
                    sb.append((int)a.a);
                    sb.append(", ");
                    sb.append((int)a3.a);
                    throw new g(sb.toString());
                }
                final int a4 = this.a(0, 0, true);
                if (a4 < 0) {
                    final StringBuilder sb2 = new StringBuilder("invalid size, tag: ");
                    sb2.append(i);
                    sb2.append(", type: ");
                    sb2.append((int)a.a);
                    sb2.append(", ");
                    sb2.append((int)a3.a);
                    sb2.append(", size: ");
                    sb2.append(a4);
                    throw new g(sb2.toString());
                }
                array = new byte[a4];
                this.a.get(array);
            }
            else {
                final int a5 = this.a(0, 0, true);
                if (a5 < 0) {
                    final StringBuilder sb3 = new StringBuilder("size invalid: ");
                    sb3.append(a5);
                    throw new g(sb3.toString());
                }
                array = new byte[a5];
                for (i = 0; i < a5; ++i) {
                    array[i] = this.a(array[0], 0, true);
                }
            }
        }
        else {
            if (b) {
                throw new g("require field not exist.");
            }
            array = null;
        }
        return array;
    }
    
    public static final class a
    {
        public byte a;
        public int b;
    }
}
