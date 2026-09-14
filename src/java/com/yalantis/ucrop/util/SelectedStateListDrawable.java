package com.yalantis.ucrop.util;

import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class SelectedStateListDrawable extends StateListDrawable
{
    private int mSelectionColor;
    
    public SelectedStateListDrawable(final Drawable drawable, final int mSelectionColor) {
        this.mSelectionColor = mSelectionColor;
        this.addState(new int[] { 16842913 }, drawable);
        this.addState(new int[0], drawable);
    }
    
    public boolean isStateful() {
        return true;
    }
    
    protected boolean onStateChange(final int[] array) {
        final int length = array.length;
        int i = 0;
        boolean b = false;
        while (i < length) {
            if (array[i] == 16842913) {
                b = true;
            }
            ++i;
        }
        if (b) {
            super.setColorFilter(this.mSelectionColor, PorterDuff$Mode.SRC_ATOP);
        }
        else {
            super.clearColorFilter();
        }
        return super.onStateChange(array);
    }
}
