package com.kingagroot.component.ui.widget.toolpop;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.GridView;

public class GridViewMaxHeight extends GridView
{
    private int maxHeight;
    
    public GridViewMaxHeight(final Context context) {
        super(context);
        this.maxHeight = 320;
    }
    
    public GridViewMaxHeight(final Context context, final AttributeSet set) {
        super(context, set);
        this.maxHeight = 320;
    }
    
    protected void onMeasure(final int n, int n2) {
        final int size = View$MeasureSpec.getSize(n2);
        final int mode = View$MeasureSpec.getMode(n2);
        final int maxHeight = this.maxHeight;
        n2 = size;
        if (size > maxHeight) {
            n2 = maxHeight;
        }
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(n2, mode));
    }
    
    public void setMaxHeight(final int maxHeight) {
        this.maxHeight = maxHeight;
        this.invalidate();
    }
}
