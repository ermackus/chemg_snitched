package com.github.chrisbanes.photoview;

interface OnGestureListener
{
    void onDrag(final float p0, final float p1);
    
    void onFling(final float p0, final float p1, final float p2, final float p3);
    
    void onScale(final float p0, final float p1, final float p2);
}
