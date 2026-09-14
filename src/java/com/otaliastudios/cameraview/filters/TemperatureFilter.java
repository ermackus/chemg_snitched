package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class TemperatureFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  vec3 new_color = color.rgb;\n  new_color.r = color.r + color.r * ( 1.0 - color.r) * scale;\n  new_color.b = color.b - color.b * ( 1.0 - color.b) * scale;\n  if (scale > 0.0) { \n    new_color.g = color.g + color.g * ( 1.0 - color.g) * scale * 0.25;\n  }\n  float max_value = max(new_color.r, max(new_color.g, new_color.b));\n  if (max_value > 1.0) { \n     new_color /= max_value;\n  } \n  gl_FragColor = vec4(new_color, color.a);\n}\n";
    private float scale;
    private int scaleLocation;
    
    public TemperatureFilter() {
        this.scale = 1.0f;
        this.scaleLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  vec3 new_color = color.rgb;\n  new_color.r = color.r + color.r * ( 1.0 - color.r) * scale;\n  new_color.b = color.b - color.b * ( 1.0 - color.b) * scale;\n  if (scale > 0.0) { \n    new_color.g = color.g + color.g * ( 1.0 - color.g) * scale * 0.25;\n  }\n  float max_value = max(new_color.r, max(new_color.g, new_color.b));\n  if (max_value > 1.0) { \n     new_color /= max_value;\n  } \n  gl_FragColor = vec4(new_color, color.a);\n}\n";
    }
    
    public float getParameter1() {
        return (this.getTemperature() + 1.0f) / 2.0f;
    }
    
    public float getTemperature() {
        return this.scale;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale");
        Egloo.checkGlProgramLocation(this.scaleLocation = glGetUniformLocation, "scale");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.scaleLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.scaleLocation, this.scale);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setParameter1(final float n) {
        this.setTemperature(n * 2.0f - 1.0f);
    }
    
    public void setTemperature(float scale) {
        float n = scale;
        if (scale < -1.0f) {
            n = -1.0f;
        }
        scale = n;
        if (n > 1.0f) {
            scale = 1.0f;
        }
        this.scale = scale;
    }
}
