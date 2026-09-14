package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class FillLightFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float mult;\nuniform float igamma;\nvarying vec2 vTextureCoord;\nvoid main() {\n  const vec3 color_weights = vec3(0.25, 0.5, 0.25);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float lightmask = dot(color.rgb, color_weights);\n  float backmask = (1.0 - lightmask);\n  vec3 ones = vec3(1.0, 1.0, 1.0);\n  vec3 diff = pow(mult * color.rgb, igamma * ones) - color.rgb;\n  diff = min(diff, 1.0);\n  vec3 new_color = min(color.rgb + diff * backmask, 1.0);\n  gl_FragColor = vec4(new_color, color.a);\n}\n";
    private int gammaLocation;
    private int multiplierLocation;
    private float strength;
    
    public FillLightFilter() {
        this.strength = 0.5f;
        this.multiplierLocation = -1;
        this.gammaLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float mult;\nuniform float igamma;\nvarying vec2 vTextureCoord;\nvoid main() {\n  const vec3 color_weights = vec3(0.25, 0.5, 0.25);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float lightmask = dot(color.rgb, color_weights);\n  float backmask = (1.0 - lightmask);\n  vec3 ones = vec3(1.0, 1.0, 1.0);\n  vec3 diff = pow(mult * color.rgb, igamma * ones) - color.rgb;\n  diff = min(diff, 1.0);\n  vec3 new_color = min(color.rgb + diff * backmask, 1.0);\n  gl_FragColor = vec4(new_color, color.a);\n}\n";
    }
    
    public float getParameter1() {
        return this.getStrength();
    }
    
    public float getStrength() {
        return this.strength;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.multiplierLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "mult"), "mult");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "igamma");
        Egloo.checkGlProgramLocation(this.gammaLocation = glGetUniformLocation, "igamma");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.multiplierLocation = -1;
        this.gammaLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        final float n2 = 1.0f / ((1.0f - this.strength) * 0.7f + 0.3f);
        GLES20.glUniform1f(this.multiplierLocation, n2);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.gammaLocation, 1.0f / (0.7f * n2 + 0.3f));
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setParameter1(final float strength) {
        this.setStrength(strength);
    }
    
    public void setStrength(float strength) {
        float n = strength;
        if (strength < 0.0f) {
            n = 0.0f;
        }
        strength = n;
        if (n > 1.0f) {
            strength = 1.0f;
        }
        this.strength = strength;
    }
}
