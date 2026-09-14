package com.kingagroot.kingdraw.widget;

import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.util.AttributeSet;
import android.content.Context;
import com.goodsrc.ui.library.widget.NoScrollGridView;

public class EditTagGridView extends NoScrollGridView
{
    int parentViewHeight;
    
    public EditTagGridView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public void onMeasure(int n, int measuredHeight) {
        super.onMeasure(n, measuredHeight);
        final int measuredWidth = this.getMeasuredWidth();
        measuredHeight = this.getMeasuredHeight();
        final int n2 = this.parentViewHeight - GDensityUtil.dp2px(60.0f);
        n = measuredHeight;
        if (measuredHeight < n2) {
            n = n2;
        }
        this.setMeasuredDimension(measuredWidth, n);
    }
    
    public void setParentViewHeight(final int parentViewHeight) {
        this.parentViewHeight = parentViewHeight;
        this.requestLayout();
    }
}
