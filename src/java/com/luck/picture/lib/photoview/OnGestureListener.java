package com.luck.picture.lib.photoview;

interface OnGestureListener
{
    void onDrag(final float p0, final float p1);
    
    void onFling(final float p0, final float p1, final float p2, final float p3);
    
    void onScale(final float p0, final float p1, final float p2);
    
    void onScale(final float p0, final float p1, final float p2, final float p3, final float p4);
}
