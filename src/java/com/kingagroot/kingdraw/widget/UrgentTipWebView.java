package com.kingagroot.kingdraw.widget;

import android.view.View$MeasureSpec;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.util.AttributeSet;
import android.content.Context;
import android.webkit.WebView;

public class UrgentTipWebView extends WebView
{
    int height;
    int maxHeight;
    int minHeight;
    
    public UrgentTipWebView(final Context context) {
        this(context, null);
    }
    
    public UrgentTipWebView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public UrgentTipWebView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.minHeight = GDensityUtil.dp2px(80.0f);
        this.maxHeight = GDensityUtil.dp2px(350.0f);
    }
    
    public void loadUrl(final String s) {
        super.loadUrl(s);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), this.height);
    }
    
    public void setHeight(final int height) {
        this.height = height;
        final int maxHeight = this.maxHeight;
        if (height > maxHeight) {
            this.height = maxHeight;
        }
        final int minHeight = this.minHeight;
        if (height < minHeight) {
            this.height = minHeight;
        }
    }
}
