package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class ContrastFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float contrast;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  color -= 0.5;\n  color *= contrast;\n  color += 0.5;\n  gl_FragColor = color;\n}\n";
    private float contrast;
    private int contrastLocation;
    
    public ContrastFilter() {
        this.contrast = 2.0f;
        this.contrastLocation = -1;
    }
    
    public float getContrast() {
        return this.contrast;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float contrast;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  color -= 0.5;\n  color *= contrast;\n  color += 0.5;\n  gl_FragColor = color;\n}\n";
    }
    
    public float getParameter1() {
        return this.getContrast() - 1.0f;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "contrast");
        Egloo.checkGlProgramLocation(this.contrastLocation = glGetUniformLocation, "contrast");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.contrastLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.contrastLocation, this.contrast);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setContrast(float contrast) {
        float n = contrast;
        if (contrast < 1.0f) {
            n = 1.0f;
        }
        contrast = n;
        if (n > 2.0f) {
            contrast = 2.0f;
        }
        this.contrast = contrast;
    }
    
    public void setParameter1(final float n) {
        this.setContrast(n + 1.0f);
    }
}
