package com.otaliastudios.cameraview.overlay;

import android.graphics.Canvas;

public interface Overlay
{
    void drawOn(final Target p0, final Canvas p1);
    
    boolean drawsOn(final Target p0);
    
    boolean getHardwareCanvasEnabled();
    
    void setHardwareCanvasEnabled(final boolean p0);
    
    public enum Target
    {
        private static final Target[] $VALUES;
        
        PICTURE_SNAPSHOT, 
        PREVIEW, 
        VIDEO_SNAPSHOT;
    }
}
