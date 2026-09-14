package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Matrix;

class KDMatrix extends Matrix
{
    public float[] convertPoints(final float[] array) {
        this.mapPoints(array);
        return array;
    }
    
    public float[] getValues() {
        final float[] array = new float[9];
        this.getValues(array);
        return array;
    }
    
    public void setValues(final float[] values) {
        super.setValues(values);
    }
}
