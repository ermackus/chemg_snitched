package com.otaliastudios.cameraview.video.encoding;

import com.otaliastudios.cameraview.overlay.Overlay$Target;
import com.otaliastudios.cameraview.overlay.OverlayDrawer;
import android.opengl.EGLContext;

public class TextureConfig extends VideoConfig
{
    public EGLContext eglContext;
    public OverlayDrawer overlayDrawer;
    public int overlayRotation;
    public Overlay$Target overlayTarget;
    public float scaleX;
    public float scaleY;
    public int textureId;
    
    TextureConfig copy() {
        final TextureConfig textureConfig = new TextureConfig();
        this.copy((VideoConfig)textureConfig);
        textureConfig.textureId = this.textureId;
        textureConfig.overlayDrawer = this.overlayDrawer;
        textureConfig.overlayTarget = this.overlayTarget;
        textureConfig.overlayRotation = this.overlayRotation;
        textureConfig.scaleX = this.scaleX;
        textureConfig.scaleY = this.scaleY;
        textureConfig.eglContext = this.eglContext;
        return textureConfig;
    }
    
    boolean hasOverlay() {
        return this.overlayDrawer != null;
    }
}
