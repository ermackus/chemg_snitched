package com.yalantis.ucrop.util;

import android.graphics.RectF;

public class RectUtils
{
    public static float[] getCenterFromRect(final RectF rectF) {
        return new float[] { rectF.centerX(), rectF.centerY() };
    }
    
    public static float[] getCornersFromRect(final RectF rectF) {
        return new float[] { rectF.left, rectF.top, rectF.right, rectF.top, rectF.right, rectF.bottom, rectF.left, rectF.bottom };
    }
    
    public static float[] getRectSidesFromCorners(final float[] array) {
        return new float[] { (float)Math.sqrt(Math.pow((double)(array[0] - array[2]), 2.0) + Math.pow((double)(array[1] - array[3]), 2.0)), (float)Math.sqrt(Math.pow((double)(array[2] - array[4]), 2.0) + Math.pow((double)(array[3] - array[5]), 2.0)) };
    }
    
    public static RectF trapToRect(final float[] array) {
        final RectF rectF = new RectF(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        for (int i = 1; i < array.length; i += 2) {
            float right = Math.round(array[i - 1] * 10.0f) / 10.0f;
            float bottom = Math.round(array[i] * 10.0f) / 10.0f;
            float left;
            if (right < rectF.left) {
                left = right;
            }
            else {
                left = rectF.left;
            }
            rectF.left = left;
            float top;
            if (bottom < rectF.top) {
                top = bottom;
            }
            else {
                top = rectF.top;
            }
            rectF.top = top;
            if (right <= rectF.right) {
                right = rectF.right;
            }
            rectF.right = right;
            if (bottom <= rectF.bottom) {
                bottom = rectF.bottom;
            }
            rectF.bottom = bottom;
        }
        rectF.sort();
        return rectF;
    }
}
