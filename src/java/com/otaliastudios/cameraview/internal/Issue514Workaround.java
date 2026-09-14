package com.otaliastudios.cameraview.internal;

import android.opengl.GLES20;

public class Issue514Workaround
{
    private final int textureId;
    
    public Issue514Workaround(final int textureId) {
        this.textureId = textureId;
    }
    
    private void bindTexture(final int n) {
        GLES20.glBindTexture(36197, n);
    }
    
    public void beforeOverlayUpdateTexImage() {
        this.bindTexture(this.textureId);
    }
    
    public void end() {
        this.bindTexture(0);
    }
}
