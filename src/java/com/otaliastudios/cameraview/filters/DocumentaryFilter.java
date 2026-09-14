package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import java.util.Random;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class DocumentaryFilter extends BaseFilter
{
    private static final String FRAGMENT_SHADER;
    private static final Random RANDOM;
    private int mHeight;
    private int mMaxDistLocation;
    private int mScaleLocation;
    private int mWidth;
    
    static {
        RANDOM = new Random();
        final StringBuilder sb = new StringBuilder();
        sb.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nvec2 seed;\nfloat stepsize;\nuniform float inv_max_dist;\nuniform vec2 scale;\nvarying vec2 vTextureCoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  seed[0] = ");
        sb.append(DocumentaryFilter.RANDOM.nextFloat());
        sb.append(";\n  seed[1] = ");
        sb.append(DocumentaryFilter.RANDOM.nextFloat());
        sb.append(";\n  stepsize = ");
        sb.append(0.003921569f);
        sb.append(";\n  vec4 color = texture2D(sTexture, ");
        sb.append("vTextureCoord");
        sb.append(");\n  float dither = rand(");
        sb.append("vTextureCoord");
        sb.append(" + seed);\n  vec3 xform = clamp(2.0 * color.rgb, 0.0, 1.0);\n  vec3 temp = clamp(2.0 * (color.rgb + stepsize), 0.0, 1.0);\n  vec3 new_color = clamp(xform + (temp - xform) * (dither - 0.5), 0.0, 1.0);\n  float gray = dot(new_color, vec3(0.299, 0.587, 0.114));\n  new_color = vec3(gray, gray, gray);\n  vec2 coord = ");
        sb.append("vTextureCoord");
        sb.append(" - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = 0.85 / (1.0 + exp((dist * inv_max_dist - 0.83) * 20.0)) + 0.15;\n  gl_FragColor = vec4(new_color * lumen, color.a);\n}\n");
        FRAGMENT_SHADER = sb.toString();
    }
    
    public DocumentaryFilter() {
        this.mWidth = 1;
        this.mHeight = 1;
        this.mScaleLocation = -1;
        this.mMaxDistLocation = -1;
    }
    
    public String getFragmentShader() {
        return DocumentaryFilter.FRAGMENT_SHADER;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.mScaleLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale"), "scale");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "inv_max_dist");
        Egloo.checkGlProgramLocation(this.mMaxDistLocation = glGetUniformLocation, "inv_max_dist");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mScaleLocation = -1;
        this.mMaxDistLocation = -1;
    }
    
    protected void onPreDraw(final long n, float[] array) {
        super.onPreDraw(n, array);
        array = new float[2];
        final int mWidth = this.mWidth;
        final int mHeight = this.mHeight;
        if (mWidth > mHeight) {
            array[0] = 1.0f;
            array[1] = mHeight / (float)mWidth;
        }
        else {
            array[0] = mWidth / (float)mHeight;
            array[1] = 1.0f;
        }
        GLES20.glUniform2fv(this.mScaleLocation, 1, array, 0);
        Egloo.checkGlError("glUniform2fv");
        GLES20.glUniform1f(this.mMaxDistLocation, 1.0f / ((float)Math.sqrt((double)(array[0] * array[0] + array[1] * array[1])) * 0.5f));
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setSize(final int mWidth, final int mHeight) {
        super.setSize(mWidth, mHeight);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
}
