package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class GammaFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float gamma;\nvoid main() {\n  vec4 textureColor = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = vec4(pow(textureColor.rgb, vec3(gamma)), textureColor.w);\n}\n";
    private float gamma;
    private int gammaLocation;
    
    public GammaFilter() {
        this.gamma = 2.0f;
        this.gammaLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float gamma;\nvoid main() {\n  vec4 textureColor = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = vec4(pow(textureColor.rgb, vec3(gamma)), textureColor.w);\n}\n";
    }
    
    public float getGamma() {
        return this.gamma;
    }
    
    public float getParameter1() {
        return this.getGamma() / 2.0f;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "gamma");
        Egloo.checkGlProgramLocation(this.gammaLocation = glGetUniformLocation, "gamma");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.gammaLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.gammaLocation, this.gamma);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setGamma(float gamma) {
        float n = gamma;
        if (gamma < 0.0f) {
            n = 0.0f;
        }
        gamma = n;
        if (n > 2.0f) {
            gamma = 2.0f;
        }
        this.gamma = gamma;
    }
    
    public void setParameter1(final float n) {
        this.setGamma(n * 2.0f);
    }
}
