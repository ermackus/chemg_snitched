package com.luck.picture.lib.widget;

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
    
    public SquareRelativeLayout(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n);
    }
}
