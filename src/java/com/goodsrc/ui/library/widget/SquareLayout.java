package com.goodsrc.ui.library.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class SquareLayout extends LinearLayout
{
    public SquareLayout(final Context context) {
        super(context);
    }
    
    public SquareLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public SquareLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onMeasure(int measureSpec, final int n) {
        this.setMeasuredDimension(getDefaultSize(0, measureSpec), getDefaultSize(0, n));
        measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        super.onMeasure(measureSpec, measureSpec);
    }
}
