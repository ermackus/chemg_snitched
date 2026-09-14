package com.otaliastudios.cameraview.internal;

import android.opengl.GLES20;
import com.otaliastudios.opengl.program.GlProgram;
import com.otaliastudios.cameraview.filter.NoFilter;
import com.otaliastudios.opengl.core.Egloo;
import com.otaliastudios.opengl.texture.GlTexture;
import com.otaliastudios.cameraview.filter.Filter;
import com.otaliastudios.cameraview.CameraLogger;

public class GlTextureDrawer
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private static final int TEXTURE_TARGET = 36197;
    private static final int TEXTURE_UNIT = 33984;
    private Filter mFilter;
    private Filter mPendingFilter;
    private int mProgramHandle;
    private final GlTexture mTexture;
    private float[] mTextureTransform;
    
    static {
        LOG = CameraLogger.create(TAG = GlTextureDrawer.class.getSimpleName());
    }
    
    public GlTextureDrawer() {
        this(new GlTexture(33984, 36197));
    }
    
    public GlTextureDrawer(final int n) {
        this(new GlTexture(33984, 36197, Integer.valueOf(n)));
    }
    
    public GlTextureDrawer(final GlTexture mTexture) {
        this.mTextureTransform = Egloo.IDENTITY_MATRIX.clone();
        this.mFilter = (Filter)new NoFilter();
        this.mPendingFilter = null;
        this.mProgramHandle = -1;
        this.mTexture = mTexture;
    }
    
    public void draw(final long n) {
        if (this.mPendingFilter != null) {
            this.release();
            this.mFilter = this.mPendingFilter;
            this.mPendingFilter = null;
        }
        if (this.mProgramHandle == -1) {
            final int create = GlProgram.create(this.mFilter.getVertexShader(), this.mFilter.getFragmentShader());
            this.mProgramHandle = create;
            this.mFilter.onCreate(create);
            Egloo.checkGlError("program creation");
        }
        GLES20.glUseProgram(this.mProgramHandle);
        Egloo.checkGlError("glUseProgram(handle)");
        this.mTexture.bind();
        this.mFilter.draw(n, this.mTextureTransform);
        this.mTexture.unbind();
        GLES20.glUseProgram(0);
        Egloo.checkGlError("glUseProgram(0)");
    }
    
    public GlTexture getTexture() {
        return this.mTexture;
    }
    
    public float[] getTextureTransform() {
        return this.mTextureTransform;
    }
    
    public void release() {
        if (this.mProgramHandle == -1) {
            return;
        }
        this.mFilter.onDestroy();
        GLES20.glDeleteProgram(this.mProgramHandle);
        this.mProgramHandle = -1;
    }
    
    public void setFilter(final Filter mPendingFilter) {
        this.mPendingFilter = mPendingFilter;
    }
    
    public void setTextureTransform(final float[] mTextureTransform) {
        this.mTextureTransform = mTextureTransform;
    }
}
