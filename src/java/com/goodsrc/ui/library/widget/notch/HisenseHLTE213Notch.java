package com.goodsrc.ui.library.widget.notch;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.res.Resources;
import android.app.Activity;
import android.view.View;

public class HisenseHLTE213Notch extends NotchBase
{
    private static final int DEFAULT_HEIGHT = 1438;
    View referenceView;
    
    public HisenseHLTE213Notch(final Activity activity) {
        super(activity);
    }
    
    public HisenseHLTE213Notch(final Activity activity, final View referenceView) {
        super(activity);
        this.referenceView = referenceView;
    }
    
    private int getStatusBarHeight() {
        final Resources resources = this.context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        if (notchCallBack == null) {
            return;
        }
        final View referenceView = this.referenceView;
        if (referenceView != null) {
            final ViewTreeObserver viewTreeObserver = referenceView.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener(this, notchCallBack) {
                    final HisenseHLTE213Notch this$0;
                    final NotchCallBack val$notchCallBack;
                    
                    public void onGlobalLayout() {
                        final int orientation = this.this$0.context.getResources().getConfiguration().orientation;
                        boolean b = true;
                        int n;
                        if (orientation == 2) {
                            n = this.this$0.referenceView.getWidth();
                        }
                        else if (orientation == 1) {
                            n = this.this$0.referenceView.getHeight();
                        }
                        else {
                            n = 0;
                        }
                        if (this.this$0.referenceView.getViewTreeObserver() != null) {
                            this.this$0.referenceView.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                        }
                        final NotchCallBack val$notchCallBack = this.val$notchCallBack;
                        if (n < this.this$0.getScreenHeight()) {
                            b = false;
                        }
                        val$notchCallBack.onResult(b);
                    }
                });
            }
        }
        notchCallBack.onResult(false);
    }
    
    @Override
    public int[] getNotchSize() {
        return new int[] { 0, this.getStatusBarHeight() };
    }
    
    protected int getScreenHeight() {
        return 1438;
    }
    
    @Override
    public boolean isNotchScreen() {
        return true;
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
    }
}
