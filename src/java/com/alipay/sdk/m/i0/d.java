package com.alipay.sdk.m.i0;

public class d
{
    public String a;
    public int b;
    public long c;
    
    public d(final String a, final int b) {
        this.a = a;
        this.b = b;
        this.c = System.currentTimeMillis() + 86400000L;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValueData{value='");
        sb.append(this.a);
        sb.append('\'');
        sb.append(", code=");
        sb.append(this.b);
        sb.append(", expired=");
        sb.append(this.c);
        sb.append('}');
        return sb.toString();
    }
}
