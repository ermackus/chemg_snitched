package com.kingagroot.kingdraw.widget.photoPicker.editer;

public class EditerPoint
{
    public float x;
    public float y;
    public float z;
    
    public EditerPoint(final float x, final float y) {
        this.x = x;
        this.y = y;
        this.z = 0.0f;
    }
    
    public EditerPoint(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
