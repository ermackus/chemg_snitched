package com.kingagroot.kingdraw.core.view3d.opengl;

import android.opengl.Matrix;
import com.kingagroot.kingdraw.core.view3d.utils.OpenGlUtils;

public class GMatrix
{
    private float[][] matrixValue;
    private float[] rotateMatrixValue;
    
    public GMatrix() {
        this.matrixValue = new float[3][3];
        this.rest();
    }
    
    private void rotate() {
        final float[] array = this.matrixValue[1];
        OpenGlUtils.applyRotation(this.rotateMatrixValue, array[0], array[1], array[2]);
    }
    
    public float[] apply() {
        final float[] array2;
        final float[] array = array2 = new float[16];
        array2[0] = 1.0f;
        array2[2] = (array2[1] = 0.0f);
        array2[4] = (array2[3] = 0.0f);
        array2[5] = 1.0f;
        array2[7] = (array2[6] = 0.0f);
        array2[9] = (array2[8] = 0.0f);
        array2[10] = 1.0f;
        array2[12] = (array2[11] = 0.0f);
        array2[14] = (array2[13] = 0.0f);
        array2[15] = 1.0f;
        final float[] array3 = this.matrixValue[0];
        Matrix.translateM(array, 0, array3[0], array3[1], array3[2]);
        final float[] array4 = new float[16];
        Matrix.multiplyMM(array4, 0, array, 0, this.rotateMatrixValue, 0);
        System.arraycopy((Object)array4, 0, (Object)array, 0, 16);
        final float[] array5 = this.matrixValue[2];
        Matrix.scaleM(array, 0, array5[0], array5[1], array5[2]);
        return array;
    }
    
    public float getScaleX() {
        return this.matrixValue[2][0];
    }
    
    public void postRotate(final float n, final float n2, final float n3) {
        final float[][] matrixValue = this.matrixValue;
        matrixValue[1][0] = n;
        matrixValue[1][1] = n2;
        matrixValue[1][2] = n3;
        this.rotate();
    }
    
    public void postScale(final float n, final float n2, final float n3) {
        final float[][] matrixValue = this.matrixValue;
        final float[] array = matrixValue[2];
        array[0] *= n;
        final float[] array2 = matrixValue[2];
        array2[1] *= n2;
        final float[] array3 = matrixValue[2];
        array3[2] *= n3;
    }
    
    public void postTranslate(final float n, final float n2, final float n3) {
        final float[][] matrixValue = this.matrixValue;
        final float[] array = matrixValue[0];
        array[0] += n;
        final float[] array2 = matrixValue[0];
        array2[1] += n2;
        final float[] array3 = matrixValue[0];
        array3[2] += n3;
    }
    
    public void rest() {
        final float[][] matrixValue = this.matrixValue;
        matrixValue[0][0] = 0.0f;
        matrixValue[0][1] = 0.0f;
        matrixValue[0][2] = 0.0f;
        matrixValue[1][0] = 0.0f;
        matrixValue[1][1] = 0.0f;
        matrixValue[1][2] = 0.0f;
        matrixValue[2][0] = 1.0f;
        matrixValue[2][1] = 1.0f;
        matrixValue[2][2] = 1.0f;
        this.rotateMatrixValue = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
    }
}
