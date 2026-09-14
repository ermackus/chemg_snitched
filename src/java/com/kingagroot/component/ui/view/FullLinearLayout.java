package com.kingagroot.component.ui.view;

import android.view.View$MeasureSpec;
import com.goodsrc.library.utils.SystemUtils;
import com.kingagroot.component.ui.R;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class FullLinearLayout extends LinearLayout
{
    private final Context context;
    
    public FullLinearLayout(final Context context) {
        this(context, null);
    }
    
    public FullLinearLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.context = context;
    }
    
    protected void onMeasure(int n, int screenWidth) {
        this.setMeasuredDimension(getDefaultSize(0, n), getDefaultSize(0, screenWidth));
        final int n2 = (int)this.context.getResources().getDimension(R.dimen.element_table_size) * 18 + (int)this.context.getResources().getDimension(R.dimen.element_table_spacing) * 17 + (int)this.context.getResources().getDimension(R.dimen.dp_Unit) * 20;
        final int n3 = (int)this.context.getResources().getDimension(R.dimen.element_table_size);
        final int n4 = (int)this.context.getResources().getDimension(R.dimen.element_table_spacing);
        final int n5 = (int)this.context.getResources().getDimension(R.dimen.dp_Unit);
        screenWidth = SystemUtils.getScreenWidth(this.getContext());
        n = n2;
        if (screenWidth > n2) {
            n = screenWidth;
        }
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), View$MeasureSpec.makeMeasureSpec(n3 * 9 + n4 * 7 + n5 * 30, 1073741824));
    }
}
