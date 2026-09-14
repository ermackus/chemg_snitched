package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class SharpnessFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nuniform float stepsizeX;\nuniform float stepsizeY;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec3 nbr_color = vec3(0.0, 0.0, 0.0);\n  vec2 coord;\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  coord.x = vTextureCoord.x - 0.5 * stepsizeX;\n  coord.y = vTextureCoord.y - stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x - stepsizeX;\n  coord.y = vTextureCoord.y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x + stepsizeX;\n  coord.y = vTextureCoord.y - 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x + stepsizeX;\n  coord.y = vTextureCoord.y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  gl_FragColor = vec4(color.rgb - 2.0 * scale * nbr_color, color.a);\n}\n";
    private int height;
    private float scale;
    private int scaleLocation;
    private int stepSizeXLocation;
    private int stepSizeYLocation;
    private int width;
    
    public SharpnessFilter() {
        this.scale = 0.5f;
        this.width = 1;
        this.height = 1;
        this.scaleLocation = -1;
        this.stepSizeXLocation = -1;
        this.stepSizeYLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nuniform float stepsizeX;\nuniform float stepsizeY;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec3 nbr_color = vec3(0.0, 0.0, 0.0);\n  vec2 coord;\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  coord.x = vTextureCoord.x - 0.5 * stepsizeX;\n  coord.y = vTextureCoord.y - stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x - stepsizeX;\n  coord.y = vTextureCoord.y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x + stepsizeX;\n  coord.y = vTextureCoord.y - 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = vTextureCoord.x + stepsizeX;\n  coord.y = vTextureCoord.y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  gl_FragColor = vec4(color.rgb - 2.0 * scale * nbr_color, color.a);\n}\n";
    }
    
    public float getParameter1() {
        return this.getSharpness();
    }
    
    public float getSharpness() {
        return this.scale;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.scaleLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale"), "scale");
        Egloo.checkGlProgramLocation(this.stepSizeXLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepsizeX"), "stepsizeX");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepsizeY");
        Egloo.checkGlProgramLocation(this.stepSizeYLocation = glGetUniformLocation, "stepsizeY");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.scaleLocation = -1;
        this.stepSizeXLocation = -1;
        this.stepSizeYLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.scaleLocation, this.scale);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepSizeXLocation, 1.0f / this.width);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepSizeYLocation, 1.0f / this.height);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setParameter1(final float sharpness) {
        this.setSharpness(sharpness);
    }
    
    public void setSharpness(float scale) {
        float n = scale;
        if (scale < 0.0f) {
            n = 0.0f;
        }
        scale = n;
        if (n > 1.0f) {
            scale = 1.0f;
        }
        this.scale = scale;
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
    }
}
