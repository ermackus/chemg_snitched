package com.kingagroot.kingdraw.core.view3d.opengl;

import com.kingagroot.kingdraw.core.view3d.element.KdBond;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.element.KdChemElement;
import android.opengl.GLES20;
import com.kingagroot.kingdraw.core.view3d.tool.GMatrixTool;
import com.kingagroot.kingdraw.core.view3d.view.Cylinder;
import com.kingagroot.kingdraw.core.view3d.view.Ball;

public class FragTool
{
    private Ball ball;
    private Cylinder cylinder;
    private int glANormal;
    private int glAPosition;
    private int glUAmbientStrength;
    private int glUBaseColor;
    private int glUDiffuseStrength;
    private int glULightColor;
    private int glULightPosition;
    private int glUMatrix;
    private int glUSpecularStrength;
    private final int mProgram;
    private GMatrixTool matrix;
    
    public FragTool() {
        this.ball = new Ball();
        this.cylinder = new Cylinder();
        this.mProgram = this.createProgram();
        this.initProgram();
    }
    
    private int createProgram() {
        final int glCreateShader = GLES20.glCreateShader(35633);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, "attribute vec4 aPosition;\nattribute vec3 aNormal;\nuniform mat4 uMatrix;\nattribute vec4 uBaseColor;\nuniform vec3 uLightColor;\nuniform float uAmbientStrength;\nuniform float uDiffuseStrength;\nuniform float uSpecularStrength;\nuniform vec3 uLightPosition;\nvarying vec4 vColor;\nvarying vec4 vDiffuse;\nvarying vec4 vAmbient;\nvarying vec4 vSpecular;\n\n//\u5728\u7247\u5143\u7740\u8272\u5668\u4e2d\u8ba1\u7b97\u5149\u7167\u4f1a\u83b7\u5f97\u66f4\u597d\u66f4\u771f\u5b9e\u7684\u5149\u7167\u6548\u679c\uff0c\u4f46\u662f\u4f1a\u6bd4\u8f83\u8017\u6027\u80fd\n\n//\u73af\u5883\u5149\u7684\u8ba1\u7b97\nvec4 ambientColor(){\n    vec3 ambient = uAmbientStrength * uLightColor;\n    return vec4(ambient,1.0);\n}\n\n//\u6f2b\u53cd\u5c04\u7684\u8ba1\u7b97\nvec4 diffuseColor(){\n    //\u6a21\u578b\u53d8\u6362\u540e\u7684\u4f4d\u7f6e\n    vec3 fragPos=(uMatrix*aPosition).xyz;\n    //\u5149\u7167\u65b9\u5411\n    vec3 direction=normalize(uLightPosition-fragPos);\n    //\u6a21\u578b\u53d8\u6362\u540e\u7684\u6cd5\u7ebf\u5411\u91cf\n    vec3 normal=normalize(mat3(uMatrix)*aNormal);\n    //max(cos(\u5165\u5c04\u89d2)\uff0c0)\n    float diff = max(dot(normal,direction), 0.0);\n    //\u6750\u8d28\u7684\u6f2b\u53cd\u5c04\u7cfb\u6570*max(cos(\u5165\u5c04\u89d2)\uff0c0)*\u5149\u7167\u989c\u8272\n    vec3 diffuse=uDiffuseStrength * diff * uLightColor;\n    return vec4(diffuse,1.0);\n}\n\n//\u955c\u9762\u5149\u8ba1\u7b97\uff0c\u955c\u9762\u5149\u8ba1\u7b97\u6709\u4e24\u79cd\u65b9\u5f0f\uff0c\u4e00\u79cd\u662f\u51af\u6c0f\u6a21\u578b\uff0c\u4e00\u79cd\u662fBlinn\u6539\u8fdb\u7684\u51af\u6c0f\u6a21\u578b\n//\u8fd9\u91cc\u4f7f\u7528\u7684\u662f\u6539\u8fdb\u7684\u51af\u6c0f\u6a21\u578b\uff0c\u57fa\u4e8eHalf-Vector\u7684\u8ba1\u7b97\u65b9\u5f0f\nvec4 specularColor(){\n    //\u6a21\u578b\u53d8\u6362\u540e\u7684\u4f4d\u7f6e\n    vec3 fragPos=(uMatrix*aPosition).xyz;\n    //\u5149\u7167\u65b9\u5411\n    vec3 lightDirection=normalize(uLightPosition-fragPos);\n    //\u6a21\u578b\u53d8\u6362\u540e\u7684\u6cd5\u7ebf\u5411\u91cf\n    vec3 normal=normalize(mat3(uMatrix)*aNormal);\n    //\u89c2\u5bdf\u65b9\u5411\uff0c\u8fd9\u91cc\u5c06\u89c2\u5bdf\u70b9\u56fa\u5b9a\u5728\uff080\uff0c0\uff0cuLightPosition.z\uff09\u5904\n    vec3 viewDirection=normalize(vec3(0,0,uLightPosition.z)-fragPos);\n    //\u89c2\u5bdf\u5411\u91cf\u4e0e\u5149\u7167\u5411\u91cf\u7684\u534a\u5411\u91cf\n    vec3 hafVector=normalize(lightDirection+viewDirection);\n    //max(0,cos(\u534a\u5411\u91cf\u4e0e\u6cd5\u5411\u91cf\u7684\u5939\u89d2)^\u7c97\u7cd9\u5ea6\n    float diff=pow(max(dot(normal,hafVector),0.5),25.0);\n    //\u6750\u8d28\u7684\u955c\u9762\u53cd\u5c04\u7cfb\u6570*max(0,cos(\u53cd\u5c04\u5411\u91cf\u4e0e\u89c2\u5bdf\u5411\u91cf\u5939\u89d2)^\u7c97\u7cd9\u5ea6*\u5149\u7167\u989c\u8272\n    //\u6750\u8d28\u7684\u955c\u9762\u53cd\u5c04\u7cfb\u6570*max(0,cos(\u534a\u5411\u91cf\u4e0e\u6cd5\u5411\u91cf\u7684\u5939\u89d2)^\u7c97\u7cd9\u5ea6*\u5149\u7167\u989c\u8272\n    vec3 specular=uSpecularStrength*diff*uLightColor;\n    return vec4(specular,1.0);\n}\n\nvoid main(){\n    gl_Position=uMatrix*aPosition;\n    vDiffuse = diffuseColor();\n    vAmbient = ambientColor();\n    vSpecular = specularColor();\n    vColor=uBaseColor;\n}");
            GLES20.glCompileShader(glCreateShader);
        }
        final int glCreateShader2 = GLES20.glCreateShader(35632);
        if (glCreateShader2 != 0) {
            GLES20.glShaderSource(glCreateShader2, "precision highp float;\n\n//uniform sampler2D uTexture;\nvarying vec4 vColor;\nvarying vec4 vDiffuse;\nvarying vec4 vAmbient;\nvarying vec4 vSpecular;\n\nvoid main(){\n    gl_FragColor=vColor*vSpecular +vColor*vDiffuse+vColor*vAmbient;\n}");
            GLES20.glCompileShader(glCreateShader2);
        }
        final int[] array = { 0 };
        GLES20.glGetShaderiv(glCreateShader, 35713, array, 0);
        if (array[0] == 0) {
            GLES20.glDeleteShader(glCreateShader);
            return 0;
        }
        GLES20.glGetShaderiv(glCreateShader2, 35713, array, 0);
        if (array[0] == 0) {
            GLES20.glDeleteShader(glCreateShader2);
            return 0;
        }
        final int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, glCreateShader);
            GLES20.glAttachShader(glCreateProgram, glCreateShader2);
            GLES20.glLinkProgram(glCreateProgram);
        }
        GLES20.glGetShaderiv(glCreateProgram, 35713, array, 0);
        if (array[0] == 0) {
            GLES20.glDeleteShader(glCreateProgram);
            return 0;
        }
        return glCreateProgram;
    }
    
    private void drawSelf(final float[] array, final boolean b, final String s) {
        GLES20.glUniformMatrix4fv(this.glUMatrix, 1, false, array, 0);
        if (b) {
            this.ball.positionGLBuffer.prepareToDraw(this.glAPosition, 3, 0);
            this.ball.normalGLBuffer.prepareToDraw(this.glANormal, 3, 0);
            final GlBuffer glBuffer = (GlBuffer)this.ball.glBufferMap.get((Object)s);
            if (glBuffer != null) {
                glBuffer.prepareToDraw(this.glUBaseColor, 4, 0);
                GLES20.glDrawArrays(4, 0, this.ball.vertexCount);
            }
        }
        else {
            this.cylinder.positionGLBuffer.prepareToDraw(this.glAPosition, 3, 0);
            this.cylinder.normalGLBuffer.prepareToDraw(this.glANormal, 3, 0);
            final GlBuffer glBuffer2 = (GlBuffer)this.cylinder.glBufferMap.get((Object)s);
            if (glBuffer2 != null) {
                glBuffer2.prepareToDraw(this.glUBaseColor, 4, 0);
                GLES20.glDrawArrays(4, 0, this.cylinder.vertexCount);
            }
        }
    }
    
    private void initProgram() {
        GLES20.glUseProgram(this.mProgram);
        this.glAPosition = GLES20.glGetAttribLocation(this.mProgram, "aPosition");
        this.glANormal = GLES20.glGetAttribLocation(this.mProgram, "aNormal");
        this.glUBaseColor = GLES20.glGetAttribLocation(this.mProgram, "uBaseColor");
        this.glUMatrix = GLES20.glGetUniformLocation(this.mProgram, "uMatrix");
        this.glULightPosition = GLES20.glGetUniformLocation(this.mProgram, "uLightPosition");
        this.glUAmbientStrength = GLES20.glGetUniformLocation(this.mProgram, "uAmbientStrength");
        this.glUDiffuseStrength = GLES20.glGetUniformLocation(this.mProgram, "uDiffuseStrength");
        this.glUSpecularStrength = GLES20.glGetUniformLocation(this.mProgram, "uSpecularStrength");
        this.glULightColor = GLES20.glGetUniformLocation(this.mProgram, "uLightColor");
    }
    
    public void draw(final KdChemElement kdChemElement, int i) {
        if (kdChemElement instanceof KdNode) {
            if (i == 1) {
                return;
            }
            this.matrix.save();
            final KdNode kdNode = (KdNode)kdChemElement;
            this.matrix.translate(kdNode.getPoint().x, kdNode.getPoint().y, kdNode.getPoint().z);
            float n;
            if (i == 0) {
                n = kdNode.getSphereR_opengl();
            }
            else {
                n = kdNode.getOpenGlRadius();
            }
            this.matrix.scale(n, n, n);
            if (this.ball.glBufferMap.get((Object)kdNode.getAtom().name) == null) {
                this.ball.setColor(kdNode.colorVer, kdNode.getAtom().name);
            }
            this.drawSelf(this.matrix.getFinalMatrix(), true, kdNode.getAtom().name);
            this.matrix.restore();
        }
        else {
            if (i == 0) {
                return;
            }
            final KdBond kdBond = (KdBond)kdChemElement;
            final KdNode kdNode2 = (KdNode)kdBond.getRelationElements().get(0);
            final KdNode kdNode3 = (KdNode)kdBond.getRelationElements().get(1);
            final StringBuilder sb = new StringBuilder();
            sb.append(kdNode2.type);
            sb.append(kdNode3.type);
            final String string = sb.toString();
            float[] center;
            for (center = kdBond.center, i = 0; i < center.length; i += 3) {
                this.matrix.save();
                this.matrix.translate(center[i], center[i + 1], center[i + 2]);
                this.matrix.rotate(kdBond.angleXY, 0.0f, 0.0f, 1.0f);
                this.matrix.rotate(kdBond.angleXZ, 0.0f, 1.0f, 0.0f);
                this.matrix.scale(kdBond.length * 1.0f, 1.0f, 1.0f);
                if (this.cylinder.glBufferMap.get((Object)string) == null) {
                    this.cylinder.setColor(string, kdBond.colorVer1, kdBond.colorVer2);
                }
                this.drawSelf(this.matrix.getFinalMatrix(), false, string);
                this.matrix.restore();
            }
        }
    }
    
    public void drawEnd() {
        GLES20.glDisableVertexAttribArray(this.glAPosition);
        GLES20.glDisableVertexAttribArray(this.glANormal);
        GLES20.glDisableVertexAttribArray(this.glUBaseColor);
    }
    
    public void drawStart() {
        GLES20.glUniform1f(this.glUAmbientStrength, 1.0f);
        GLES20.glUniform1f(this.glUDiffuseStrength, 1.0f);
        GLES20.glUniform1f(this.glUSpecularStrength, 0.1f);
        GLES20.glUniform3f(this.glULightColor, 1.0f, 1.0f, 1.0f);
        GLES20.glUniform3f(this.glULightPosition, 0.0f, 0.0f, -100.0f);
        this.matrix.apply();
    }
    
    void setVaryTools(final GMatrixTool matrix) {
        this.matrix = matrix;
    }
}
