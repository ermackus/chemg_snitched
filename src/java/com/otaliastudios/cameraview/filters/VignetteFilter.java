package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.TwoParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class VignetteFilter extends BaseFilter implements TwoParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float range;\nuniform float inv_max_dist;\nuniform float shade;\nuniform vec2 scale;\nvarying vec2 vTextureCoord;\nvoid main() {\n  const float slope = 20.0;\n  vec2 coord = vTextureCoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = shade / (1.0 + exp((dist * inv_max_dist - range) * slope)) + (1.0 - shade);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = vec4(color.rgb * lumen, color.a);\n}\n";
    private int mHeight;
    private int mMaxDistLocation;
    private int mRangeLocation;
    private float mScale;
    private int mScaleLocation;
    private float mShade;
    private int mShadeLocation;
    private int mWidth;
    
    public VignetteFilter() {
        this.mScale = 0.85f;
        this.mShade = 0.5f;
        this.mWidth = 1;
        this.mHeight = 1;
        this.mRangeLocation = -1;
        this.mMaxDistLocation = -1;
        this.mShadeLocation = -1;
        this.mScaleLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float range;\nuniform float inv_max_dist;\nuniform float shade;\nuniform vec2 scale;\nvarying vec2 vTextureCoord;\nvoid main() {\n  const float slope = 20.0;\n  vec2 coord = vTextureCoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = shade / (1.0 + exp((dist * inv_max_dist - range) * slope)) + (1.0 - shade);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = vec4(color.rgb * lumen, color.a);\n}\n";
    }
    
    public float getParameter1() {
        return this.getVignetteScale();
    }
    
    public float getParameter2() {
        return this.getVignetteShade();
    }
    
    public float getVignetteScale() {
        return this.mScale;
    }
    
    public float getVignetteShade() {
        return this.mShade;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.mRangeLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "range"), "range");
        Egloo.checkGlProgramLocation(this.mMaxDistLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "inv_max_dist"), "inv_max_dist");
        Egloo.checkGlProgramLocation(this.mShadeLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "shade"), "shade");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale");
        Egloo.checkGlProgramLocation(this.mScaleLocation = glGetUniformLocation, "scale");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mRangeLocation = -1;
        this.mMaxDistLocation = -1;
        this.mShadeLocation = -1;
        this.mScaleLocation = -1;
    }
    
    protected void onPreDraw(final long n, float[] array) {
        super.onPreDraw(n, array);
        array = new float[2];
        final int mWidth = this.mWidth;
        final int mHeight = this.mHeight;
        if (mWidth > mHeight) {
            array[0] = 1.0f;
            array[1] = mHeight / (float)mWidth;
        }
        else {
            array[0] = mWidth / (float)mHeight;
            array[1] = 1.0f;
        }
        GLES20.glUniform2fv(this.mScaleLocation, 1, array, 0);
        Egloo.checkGlError("glUniform2fv");
        GLES20.glUniform1f(this.mMaxDistLocation, 1.0f / ((float)Math.sqrt((double)(array[0] * array[0] + array[1] * array[1])) * 0.5f));
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.mShadeLocation, this.mShade);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.mRangeLocation, 1.3f - (float)Math.sqrt((double)this.mScale) * 0.7f);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setParameter1(final float vignetteScale) {
        this.setVignetteScale(vignetteScale);
    }
    
    public void setParameter2(final float vignetteShade) {
        this.setVignetteShade(vignetteShade);
    }
    
    public void setSize(final int mWidth, final int mHeight) {
        super.setSize(mWidth, mHeight);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public void setVignetteScale(float mScale) {
        float n = mScale;
        if (mScale < 0.0f) {
            n = 0.0f;
        }
        mScale = n;
        if (n > 1.0f) {
            mScale = 1.0f;
        }
        this.mScale = mScale;
    }
    
    public void setVignetteShade(float mShade) {
        float n = mShade;
        if (mShade < 0.0f) {
            n = 0.0f;
        }
        mShade = n;
        if (n > 1.0f) {
            mShade = 1.0f;
        }
        this.mShade = mShade;
    }
}
