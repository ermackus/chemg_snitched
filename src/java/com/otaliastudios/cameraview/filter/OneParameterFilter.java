package com.otaliastudios.cameraview.filter;

public interface OneParameterFilter extends Filter
{
    float getParameter1();
    
    void setParameter1(final float p0);
}
