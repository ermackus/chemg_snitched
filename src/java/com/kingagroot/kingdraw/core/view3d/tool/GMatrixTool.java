package com.kingagroot.kingdraw.core.view3d.tool;

import java.util.Arrays;
import android.opengl.Matrix;
import com.kingagroot.kingdraw.core.view3d.opengl.GMatrix;
import java.util.Stack;

public class GMatrixTool
{
    private float[] mMatrixCamera;
    private float[] mMatrixCurrent;
    private float[] mMatrixProjection;
    private Stack<float[]> mStack;
    private GMatrix matrixValue;
    
    public GMatrixTool() {
        this.mMatrixCamera = new float[16];
        this.mMatrixProjection = new float[16];
        this.mMatrixCurrent = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.matrixValue = new GMatrix();
        this.mStack = (Stack<float[]>)new Stack();
    }
    
    public void apply() {
        this.mMatrixCurrent = this.matrixValue.apply();
    }
    
    public float[] getFinalMatrix() {
        final float[] array = new float[16];
        Matrix.multiplyMM(array, 0, this.mMatrixCamera, 0, this.mMatrixCurrent, 0);
        Matrix.multiplyMM(array, 0, this.mMatrixProjection, 0, array, 0);
        return array;
    }
    
    public GMatrix getMatrixValue() {
        return this.matrixValue;
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        Matrix.orthoM(this.mMatrixProjection, 0, n, n2, n3, n4, n5, n6);
    }
    
    public void postRotate(final float n, final float n2, final float n3) {
        this.matrixValue.postRotate(n, n2, n3);
    }
    
    public void postScale(final float n, final float n2, final float n3) {
        this.matrixValue.postScale(n, n2, n3);
    }
    
    public void postTranslate(final float n, final float n2, final float n3) {
        this.matrixValue.postTranslate(n, n2, n3);
    }
    
    public void rest() {
        this.matrixValue.rest();
        this.mMatrixCurrent = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
    }
    
    public void restore() {
        if (!this.mStack.isEmpty()) {
            this.mMatrixCurrent = (float[])this.mStack.pop();
        }
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        Matrix.rotateM(this.mMatrixCurrent, 0, n, n2, n3, n4);
    }
    
    public void save() {
        this.mStack.push((Object)Arrays.copyOf(this.mMatrixCurrent, 16));
    }
    
    public void scale(final float n, final float n2, final float n3) {
        Matrix.scaleM(this.mMatrixCurrent, 0, n, n2, n3);
    }
    
    public void setCamera(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        Matrix.setLookAtM(this.mMatrixCamera, 0, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void translate(final float n, final float n2, final float n3) {
        Matrix.translateM(this.mMatrixCurrent, 0, n, n2, n3);
    }
}
