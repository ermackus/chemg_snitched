package com.otaliastudios.cameraview.preview;

import android.graphics.SurfaceTexture;
import com.otaliastudios.cameraview.filter.Filter;

public interface RendererFrameCallback
{
    void onRendererFilterChanged(final Filter p0);
    
    void onRendererFrame(final SurfaceTexture p0, final int p1, final float p2, final float p3);
    
    void onRendererTextureCreated(final int p0);
}
