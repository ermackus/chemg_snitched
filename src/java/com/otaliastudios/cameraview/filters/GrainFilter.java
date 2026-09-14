package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import java.util.Random;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class GrainFilter extends BaseFilter implements OneParameterFilter
{
    private static final String FRAGMENT_SHADER;
    private static final Random RANDOM;
    private int height;
    private int stepXLocation;
    private int stepYLocation;
    private float strength;
    private int strengthLocation;
    private int width;
    
    static {
        RANDOM = new Random();
        final StringBuilder sb = new StringBuilder();
        sb.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvec2 seed;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES tex_sampler_0;\nuniform samplerExternalOES tex_sampler_1;\nuniform float scale;\nuniform float stepX;\nuniform float stepY;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  float sum = (part1 + part2 + part3);\n  return fract(sum)*scale;\n}\nvoid main() {\n  seed[0] = ");
        sb.append(GrainFilter.RANDOM.nextFloat());
        sb.append(";\n  seed[1] = ");
        sb.append(GrainFilter.RANDOM.nextFloat());
        sb.append(";\n  float noise = texture2D(tex_sampler_1, ");
        sb.append("vTextureCoord");
        sb.append(" + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, ");
        sb.append("vTextureCoord");
        sb.append(" + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, ");
        sb.append("vTextureCoord");
        sb.append(" + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, ");
        sb.append("vTextureCoord");
        sb.append(" + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, ");
        sb.append("vTextureCoord");
        sb.append(");\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n  gl_FragColor = gl_FragColor+vec4(rand(");
        sb.append("vTextureCoord");
        sb.append(" + seed), rand(");
        sb.append("vTextureCoord");
        sb.append(" + seed),rand(");
        sb.append("vTextureCoord");
        sb.append(" + seed),1);\n}\n");
        FRAGMENT_SHADER = sb.toString();
    }
    
    public GrainFilter() {
        this.strength = 0.5f;
        this.width = 1;
        this.height = 1;
        this.strengthLocation = -1;
        this.stepXLocation = -1;
        this.stepYLocation = -1;
    }
    
    public String getFragmentShader() {
        return GrainFilter.FRAGMENT_SHADER;
    }
    
    public float getParameter1() {
        return this.getStrength();
    }
    
    public float getStrength() {
        return this.strength;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.strengthLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale"), "scale");
        Egloo.checkGlProgramLocation(this.stepXLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepX"), "stepX");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepY");
        Egloo.checkGlProgramLocation(this.stepYLocation = glGetUniformLocation, "stepY");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.strengthLocation = -1;
        this.stepXLocation = -1;
        this.stepYLocation = -1;
    }
    
    protected void onPreDraw(final long n, final float[] array) {
        super.onPreDraw(n, array);
        GLES20.glUniform1f(this.strengthLocation, this.strength);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepXLocation, 0.5f / this.width);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepYLocation, 0.5f / this.height);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setParameter1(final float strength) {
        this.setStrength(strength);
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
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
