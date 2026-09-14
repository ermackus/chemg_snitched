package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import android.graphics.Color;
import com.otaliastudios.cameraview.filter.TwoParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class DuotoneFilter extends BaseFilter implements TwoParameterFilter
{
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform vec3 first;\nuniform vec3 second;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float energy = (color.r + color.g + color.b) * 0.3333;\n  vec3 new_color = (1.0 - energy) * first + energy * second;\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    private int mFirstColor;
    private int mFirstColorLocation;
    private int mSecondColor;
    private int mSecondColorLocation;
    
    public DuotoneFilter() {
        this.mFirstColor = -65281;
        this.mSecondColor = -256;
        this.mFirstColorLocation = -1;
        this.mSecondColorLocation = -1;
    }
    
    public int getFirstColor() {
        return this.mFirstColor;
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform vec3 first;\nuniform vec3 second;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float energy = (color.r + color.g + color.b) * 0.3333;\n  vec3 new_color = (1.0 - energy) * first + energy * second;\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    }
    
    public float getParameter1() {
        final int firstColor = this.getFirstColor();
        return Color.argb(0, Color.red(firstColor), Color.green(firstColor), Color.blue(firstColor)) / 1.6777215E7f;
    }
    
    public float getParameter2() {
        final int secondColor = this.getSecondColor();
        return Color.argb(0, Color.red(secondColor), Color.green(secondColor), Color.blue(secondColor)) / 1.6777215E7f;
    }
    
    public int getSecondColor() {
        return this.mSecondColor;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.mFirstColorLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "first"), "first");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "second");
        Egloo.checkGlProgramLocation(this.mSecondColorLocation = glGetUniformLocation, "second");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mFirstColorLocation = -1;
        this.mSecondColorLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        final float n2 = Color.red(this.mFirstColor) / 255.0f;
        final float n3 = Color.green(this.mFirstColor) / 255.0f;
        final float n4 = Color.blue(this.mFirstColor) / 255.0f;
        final float n5 = Color.red(this.mSecondColor) / 255.0f;
        final float n6 = Color.green(this.mSecondColor) / 255.0f;
        final float n7 = Color.blue(this.mSecondColor) / 255.0f;
        GLES20.glUniform3fv(this.mFirstColorLocation, 1, new float[] { n2, n3, n4 }, 0);
        Egloo.checkGlError("glUniform3fv");
        GLES20.glUniform3fv(this.mSecondColorLocation, 1, new float[] { n5, n6, n7 }, 0);
        Egloo.checkGlError("glUniform3fv");
    }
    
    public void setColors(final int firstColor, final int secondColor) {
        this.setFirstColor(firstColor);
        this.setSecondColor(secondColor);
    }
    
    public void setFirstColor(final int n) {
        this.mFirstColor = Color.rgb(Color.red(n), Color.green(n), Color.blue(n));
    }
    
    public void setParameter1(final float n) {
        this.setFirstColor((int)(n * 1.6777215E7f));
    }
    
    public void setParameter2(final float n) {
        this.setSecondColor((int)(n * 1.6777215E7f));
    }
    
    public void setSecondColor(final int n) {
        this.mSecondColor = Color.rgb(Color.red(n), Color.green(n), Color.blue(n));
    }
}
