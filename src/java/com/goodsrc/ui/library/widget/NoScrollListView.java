package com.goodsrc.ui.library.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListView;

public class NoScrollListView extends ListView
{
    public NoScrollListView(final Context context) {
        super(context);
    }
    
    public NoScrollListView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NoScrollListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void onMeasure(final int n, final int n2) {
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
