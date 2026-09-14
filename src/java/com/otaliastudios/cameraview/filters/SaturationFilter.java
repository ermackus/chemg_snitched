package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class SaturationFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nuniform vec3 exponents;\nfloat shift;\nvec3 weights;\nvarying vec2 vTextureCoord;\nvoid main() {\n  weights[0] = 0.25;\n  weights[1] = 0.625;\n  weights[2] = 0.125;\n  shift = 0.003921569;\n  vec4 oldcolor = texture2D(sTexture, vTextureCoord);\n  float kv = dot(oldcolor.rgb, weights) + shift;\n  vec3 new_color = scale * oldcolor.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, oldcolor.a);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 verynew_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(verynew_color.r, verynew_color.g), verynew_color.b), 1.0);\n  gl_FragColor = gl_FragColor+vec4(verynew_color / max_color, color.a);\n}\n";
    private int exponentsLocation;
    private float scale;
    private int scaleLocation;
    
    public SaturationFilter() {
        this.scale = 1.0f;
        this.scaleLocation = -1;
        this.exponentsLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float scale;\nuniform vec3 exponents;\nfloat shift;\nvec3 weights;\nvarying vec2 vTextureCoord;\nvoid main() {\n  weights[0] = 0.25;\n  weights[1] = 0.625;\n  weights[2] = 0.125;\n  shift = 0.003921569;\n  vec4 oldcolor = texture2D(sTexture, vTextureCoord);\n  float kv = dot(oldcolor.rgb, weights) + shift;\n  vec3 new_color = scale * oldcolor.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, oldcolor.a);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 verynew_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(verynew_color.r, verynew_color.g), verynew_color.b), 1.0);\n  gl_FragColor = gl_FragColor+vec4(verynew_color / max_color, color.a);\n}\n";
    }
    
    public float getParameter1() {
        return (this.getSaturation() + 1.0f) / 2.0f;
    }
    
    public float getSaturation() {
        return this.scale;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.scaleLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale"), "scale");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "exponents");
        Egloo.checkGlProgramLocation(this.exponentsLocation = glGetUniformLocation, "exponents");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.scaleLocation = -1;
        this.exponentsLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        final float scale = this.scale;
        if (scale > 0.0f) {
            GLES20.glUniform1f(this.scaleLocation, 0.0f);
            Egloo.checkGlError("glUniform1f");
            final int exponentsLocation = this.exponentsLocation;
            final float scale2 = this.scale;
            GLES20.glUniform3f(exponentsLocation, 0.9f * scale2 + 1.0f, 2.1f * scale2 + 1.0f, scale2 * 2.7f + 1.0f);
            Egloo.checkGlError("glUniform3f");
        }
        else {
            GLES20.glUniform1f(this.scaleLocation, scale + 1.0f);
            Egloo.checkGlError("glUniform1f");
            GLES20.glUniform3f(this.exponentsLocation, 0.0f, 0.0f, 0.0f);
            Egloo.checkGlError("glUniform3f");
        }
    }
    
    public void setParameter1(final float n) {
        this.setSaturation(n * 2.0f - 1.0f);
    }
    
    public void setSaturation(float scale) {
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
