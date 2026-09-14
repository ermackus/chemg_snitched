package com.kingagroot.kingdraw.widget.FileTag;

import android.view.View$MeasureSpec;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.util.AttributeSet;
import android.content.Context;
import androidx.core.widget.NestedScrollView;

public class LocalScrollView extends NestedScrollView
{
    float animHeight;
    int height;
    boolean isAnim;
    boolean isShrink;
    int maxHeight;
    
    public LocalScrollView(final Context context) {
        this(context, null);
    }
    
    public LocalScrollView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public LocalScrollView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.maxHeight = GDensityUtil.dp2px(120.0f);
    }
    
    public boolean isShrink() {
        return this.isShrink;
    }
    
    protected void onMeasure(final int n, int size) {
        final int mode = View$MeasureSpec.getMode(size);
        View$MeasureSpec.getSize(n);
        size = View$MeasureSpec.getSize(size);
        if (this.isAnim || this.isShrink) {
            size = (int)this.animHeight;
        }
        final int maxHeight = this.maxHeight;
        int n2;
        if ((n2 = size) > maxHeight) {
            n2 = maxHeight;
        }
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(n2, mode));
    }
    
    protected void onSizeChanged(final int n, final int height, final int n2, final int n3) {
        super.onSizeChanged(n, height, n2, n3);
        if (!this.isAnim && !this.isShrink) {
            this.height = height;
            this.animHeight = (float)height;
        }
    }
    
    public void setAnim(final boolean isAnim) {
        this.isAnim = isAnim;
    }
    
    public void setHeight(final float n) {
        this.animHeight = this.height * n;
        this.requestLayout();
    }
    
    public void setShrink(final boolean isShrink) {
        this.isShrink = isShrink;
    }
}
