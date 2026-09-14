package com.kingagroot.kingdraw.core.view3d.bean;

public class KdPoint implements Cloneable
{
    public float x;
    public float y;
    public float z;
    
    public KdPoint() {
    }
    
    public KdPoint(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public KdPoint(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public KdPoint clone() {
        return new KdPoint(this.x, this.y, this.z);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(x:");
        sb.append(this.x);
        sb.append(",y:");
        sb.append(this.y);
        sb.append(",z:+");
        sb.append(this.z);
        sb.append(")");
        return sb.toString();
    }
}
