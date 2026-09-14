package com.kingagroot.kingdraw.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout
{
    public SquareRelativeLayout(final Context context) {
        super(context);
    }
    
    public SquareRelativeLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public SquareRelativeLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onMeasure(int n, final int n2) {
        this.setMeasuredDimension(getDefaultSize(0, n), getDefaultSize(0, n2));
        n = this.getMeasuredWidth();
        this.getMeasuredHeight();
        n = View$MeasureSpec.makeMeasureSpec(n, 1073741824);
        super.onMeasure(n, n);
    }
}
