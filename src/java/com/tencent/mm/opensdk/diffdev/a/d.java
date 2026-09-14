package com.tencent.mm.opensdk.diffdev.a;

public enum d
{
    b(402), 
    c(403), 
    d(404), 
    e(405), 
    f(408), 
    g(500);
    
    private static final d[] h;
    private int a;
    
    private d(final int a) {
        this.a = a;
    }
    
    public int a() {
        return this.a;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("UUIDStatusCode:");
        sb.append(this.a);
        return sb.toString();
    }
}
