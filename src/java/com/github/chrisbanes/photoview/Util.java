package com.github.chrisbanes.photoview;

import android.widget.ImageView$ScaleType;
import android.widget.ImageView;

class Util
{
    static void checkZoomLevels(final float n, final float n2, final float n3) {
        if (n >= n2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        }
        if (n2 < n3) {
            return;
        }
        throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
    }
    
    static int getPointerIndex(final int n) {
        return (n & 0xFF00) >> 8;
    }
    
    static boolean hasDrawable(final ImageView imageView) {
        return imageView.getDrawable() != null;
    }
    
    static boolean isSupportedScaleType(final ImageView$ScaleType imageView$ScaleType) {
        if (imageView$ScaleType == null) {
            return false;
        }
        if (Util$1.$SwitchMap$android$widget$ImageView$ScaleType[imageView$ScaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalStateException("Matrix scale type is not supported");
    }
}
