package com.tencent.open.utils;

public final class l implements Cloneable
{
    private long a;
    
    public l(final long a) {
        this.a = a;
    }
    
    public byte[] a() {
        final long a = this.a;
        return new byte[] { (byte)(0xFFL & a), (byte)((0xFF00L & a) >> 8), (byte)((0xFF0000L & a) >> 16), (byte)((a & 0xFF000000L) >> 24) };
    }
    
    public long b() {
        return this.a;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o != null) {
            if (!(o instanceof l)) {
                b2 = b;
            }
            else {
                b2 = b;
                if (this.a == ((l)o).b()) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return (int)this.a;
    }
}
