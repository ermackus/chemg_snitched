package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import android.graphics.Color;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class TintFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform vec3 tint;\nvec3 color_ratio;\nvarying vec2 vTextureCoord;\nvoid main() {\n  color_ratio[0] = 0.21;\n  color_ratio[1] = 0.71;\n  color_ratio[2] = 0.07;\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float avg_color = dot(color_ratio, color.rgb);\n  vec3 new_color = min(0.8 * avg_color + 0.2 * tint, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    private int tint;
    private int tintLocation;
    
    public TintFilter() {
        this.tint = -65536;
        this.tintLocation = -1;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform vec3 tint;\nvec3 color_ratio;\nvarying vec2 vTextureCoord;\nvoid main() {\n  color_ratio[0] = 0.21;\n  color_ratio[1] = 0.71;\n  color_ratio[2] = 0.07;\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float avg_color = dot(color_ratio, color.rgb);\n  vec3 new_color = min(0.8 * avg_color + 0.2 * tint, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    }
    
    public float getParameter1() {
        final int tint = this.getTint();
        return Color.argb(0, Color.red(tint), Color.green(tint), Color.blue(tint)) / 1.6777215E7f;
    }
    
    public int getTint() {
        return this.tint;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "tint");
        Egloo.checkGlProgramLocation(this.tintLocation = glGetUniformLocation, "tint");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.tintLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform3fv(this.tintLocation, 1, new float[] { Color.red(this.tint) / 255.0f, Color.green(this.tint) / 255.0f, Color.blue(this.tint) / 255.0f }, 0);
        Egloo.checkGlError("glUniform3fv");
    }
    
    public void setParameter1(final float n) {
        this.setTint((int)(n * 1.6777215E7f));
    }
    
    public void setTint(final int n) {
        this.tint = Color.rgb(Color.red(n), Color.green(n), Color.blue(n));
    }
}
