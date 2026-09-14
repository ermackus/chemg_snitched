package com.tencent.open.utils;

public final class m implements Cloneable
{
    private int a;
    
    public m(final int a) {
        this.a = a;
    }
    
    public m(final byte[] array) {
        this(array, 0);
    }
    
    public m(final byte[] array, final int n) {
        final int a = array[n + 1] << 8 & 0xFF00;
        this.a = a;
        this.a = a + (array[n] & 0xFF);
    }
    
    public byte[] a() {
        final int a = this.a;
        return new byte[] { (byte)(a & 0xFF), (byte)((a & 0xFF00) >> 8) };
    }
    
    public int b() {
        return this.a;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o != null) {
            if (!(o instanceof m)) {
                b2 = b;
            }
            else {
                b2 = b;
                if (this.a == ((m)o).b()) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return this.a;
    }
}
