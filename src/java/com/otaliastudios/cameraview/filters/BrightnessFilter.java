package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class BrightnessFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float brightness;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = brightness * color;\n}\n";
    private float brightness;
    private int brightnessLocation;
    
    public BrightnessFilter() {
        this.brightness = 2.0f;
        this.brightnessLocation = -1;
    }
    
    public float getBrightness() {
        return this.brightness;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float brightness;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = brightness * color;\n}\n";
    }
    
    public float getParameter1() {
        return this.getBrightness() - 1.0f;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "brightness");
        Egloo.checkGlProgramLocation(this.brightnessLocation = glGetUniformLocation, "brightness");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.brightnessLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.brightnessLocation, this.brightness);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setBrightness(float brightness) {
        float n = brightness;
        if (brightness < 1.0f) {
            n = 1.0f;
        }
        brightness = n;
        if (n > 2.0f) {
            brightness = 2.0f;
        }
        this.brightness = brightness;
    }
    
    public void setParameter1(final float n) {
        this.setBrightness(n + 1.0f);
    }
}
