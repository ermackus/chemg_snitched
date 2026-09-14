package com.otaliastudios.cameraview.filter;

public interface Filter
{
    Filter copy();
    
    void draw(final long p0, final float[] p1);
    
    String getFragmentShader();
    
    String getVertexShader();
    
    void onCreate(final int p0);
    
    void onDestroy();
    
    void setSize(final int p0, final int p1);
}
