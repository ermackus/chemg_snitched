package com.king.zxing;

public interface ICameraControl
{
    void enableTorch(final boolean p0);
    
    boolean hasFlashUnit();
    
    boolean isTorchEnabled();
    
    void lineZoomIn();
    
    void lineZoomOut();
    
    void lineZoomTo(final float p0);
    
    void zoomIn();
    
    void zoomOut();
    
    void zoomTo(final float p0);
}
