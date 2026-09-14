package com.otaliastudios.cameraview.filters;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import java.util.Random;
import com.otaliastudios.cameraview.filter.BaseFilter;

public class LomoishFilter extends BaseFilter
{
    private static final String FRAGMENT_SHADER;
    private static final Random RANDOM;
    private int height;
    private int maxDistLocation;
    private int scaleLocation;
    private int stepSizeXLocation;
    private int stepSizeYLocation;
    private int width;
    
    static {
        RANDOM = new Random();
        final StringBuilder sb = new StringBuilder();
        sb.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nuniform float stepsizeX;\nuniform float stepsizeY;\nuniform vec2 scale;\nuniform float inv_max_dist;\nvec2 seed;\nfloat stepsize;\nvarying vec2 vTextureCoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  seed[0] = ");
        sb.append(LomoishFilter.RANDOM.nextFloat());
        sb.append(";\n  seed[1] = ");
        sb.append(LomoishFilter.RANDOM.nextFloat());
        sb.append(";\n  stepsize = ");
        sb.append(0.003921569f);
        sb.append(";\n  vec3 nbr_color = vec3(0.0, 0.0, 0.0);\n  vec2 coord;\n  vec4 color = texture2D(sTexture, ");
        sb.append("vTextureCoord");
        sb.append(");\n  coord.x = ");
        sb.append("vTextureCoord");
        sb.append(".x - 0.5 * stepsizeX;\n  coord.y = ");
        sb.append("vTextureCoord");
        sb.append(".y - stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = ");
        sb.append("vTextureCoord");
        sb.append(".x - stepsizeX;\n  coord.y = ");
        sb.append("vTextureCoord");
        sb.append(".y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = ");
        sb.append("vTextureCoord");
        sb.append(".x + stepsizeX;\n  coord.y = ");
        sb.append("vTextureCoord");
        sb.append(".y - 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  coord.x = ");
        sb.append("vTextureCoord");
        sb.append(".x + stepsizeX;\n  coord.y = ");
        sb.append("vTextureCoord");
        sb.append(".y + 0.5 * stepsizeY;\n  nbr_color += texture2D(sTexture, coord).rgb - color.rgb;\n  vec3 s_color = vec3(color.rgb + 0.3 * nbr_color);\n  vec3 c_color = vec3(0.0, 0.0, 0.0);\n  float value;\n  if (s_color.r < 0.5) {\n    value = s_color.r;\n  } else {\n    value = 1.0 - s_color.r;\n  }\n  float red = 4.0 * value * value * value;\n  if (s_color.r < 0.5) {\n    c_color.r = red;\n  } else {\n    c_color.r = 1.0 - red;\n  }\n  if (s_color.g < 0.5) {\n    value = s_color.g;\n  } else {\n    value = 1.0 - s_color.g;\n  }\n  float green = 2.0 * value * value;\n  if (s_color.g < 0.5) {\n    c_color.g = green;\n  } else {\n    c_color.g = 1.0 - green;\n  }\n  c_color.b = s_color.b * 0.5 + 0.25;\n  float dither = rand(");
        sb.append("vTextureCoord");
        sb.append(" + seed);\n  vec3 xform = clamp((c_color.rgb - 0.15) * 1.53846, 0.0, 1.0);\n  vec3 temp = clamp((color.rgb + stepsize - 0.15) * 1.53846, 0.0, 1.0);\n  vec3 bw_color = clamp(xform + (temp - xform) * (dither - 0.5), 0.0, 1.0);\n  coord = ");
        sb.append("vTextureCoord");
        sb.append(" - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = 0.85 / (1.0 + exp((dist * inv_max_dist - 0.73) * 20.0)) + 0.15;\n  gl_FragColor = vec4(bw_color * lumen, color.a);\n}\n");
        FRAGMENT_SHADER = sb.toString();
    }
    
    public LomoishFilter() {
        this.width = 1;
        this.height = 1;
        this.scaleLocation = -1;
        this.maxDistLocation = -1;
        this.stepSizeXLocation = -1;
        this.stepSizeYLocation = -1;
    }
    
    public String getFragmentShader() {
        return LomoishFilter.FRAGMENT_SHADER;
    }
    
    public void onCreate(int glGetUniformLocation) {
        super.onCreate(glGetUniformLocation);
        Egloo.checkGlProgramLocation(this.scaleLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "scale"), "scale");
        Egloo.checkGlProgramLocation(this.maxDistLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "inv_max_dist"), "inv_max_dist");
        Egloo.checkGlProgramLocation(this.stepSizeXLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepsizeX"), "stepsizeX");
        glGetUniformLocation = GLES20.glGetUniformLocation(glGetUniformLocation, "stepsizeY");
        Egloo.checkGlProgramLocation(this.stepSizeYLocation = glGetUniformLocation, "stepsizeY");
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.scaleLocation = -1;
        this.maxDistLocation = -1;
        this.stepSizeXLocation = -1;
        this.stepSizeYLocation = -1;
    }
    
    protected void onPreDraw(final long n, float[] array) {
        super.onPreDraw(n, array);
        array = new float[2];
        final int width = this.width;
        final int height = this.height;
        if (width > height) {
            array[0] = 1.0f;
            array[1] = height / (float)width;
        }
        else {
            array[0] = width / (float)height;
            array[1] = 1.0f;
        }
        final float n2 = (float)Math.sqrt((double)(array[0] * array[0] + array[1] * array[1]));
        GLES20.glUniform2fv(this.scaleLocation, 1, array, 0);
        Egloo.checkGlError("glUniform2fv");
        GLES20.glUniform1f(this.maxDistLocation, 1.0f / (n2 * 0.5f));
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepSizeXLocation, 1.0f / this.width);
        Egloo.checkGlError("glUniform1f");
        GLES20.glUniform1f(this.stepSizeYLocation, 1.0f / this.height);
        Egloo.checkGlError("glUniform1f");
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
    }
}
