package com.kingagroot.kingdraw.ui.baike;

import android.util.AttributeSet;
import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeWebView;

public class LocalBridgeWebView extends BridgeWebView
{
    private LocalBridgeWebView.LocalBridgeWebView$OnScrollChangeListener mOnScrollChangeListener;
    
    public LocalBridgeWebView(final Context context) {
        super(context);
    }
    
    public LocalBridgeWebView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public LocalBridgeWebView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (Math.abs(this.getContentHeight() * this.getScale() - (this.getHeight() + this.getScrollY())) < 1.0f) {
            this.mOnScrollChangeListener.onPageEnd(n, n2, n3, n4);
        }
        else if (this.getScrollY() == 0) {
            this.mOnScrollChangeListener.onPageTop(n, n2, n3, n4);
        }
        else {
            this.mOnScrollChangeListener.onScrollChanged(n, n2, n3, n4);
        }
    }
    
    public void setOnScrollChangeListener(final LocalBridgeWebView.LocalBridgeWebView$OnScrollChangeListener mOnScrollChangeListener) {
        this.mOnScrollChangeListener = mOnScrollChangeListener;
    }
}
