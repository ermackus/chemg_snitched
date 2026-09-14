package com.tencent.bugly.proguard;

import java.io.Serializable;

public abstract class k implements Serializable
{
    public abstract void a(final i p0);
    
    public abstract void a(final j p0);
    
    public abstract void a(final StringBuilder p0, final int p1);
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.a(sb, 0);
        return sb.toString();
    }
}
