package com.goodsrc.ui.library.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.GridView;

public class NoScrollGridView extends GridView
{
    public NoScrollGridView(final Context context) {
        super(context);
    }
    
    public NoScrollGridView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NoScrollGridView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void onMeasure(final int n, final int n2) {
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
