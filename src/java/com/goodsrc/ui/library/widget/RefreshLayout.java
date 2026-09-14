package com.goodsrc.ui.library.widget;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class RefreshLayout extends SwipeRefreshLayout
{
    boolean Refresh;
    private float mDownX;
    private float mDownY;
    
    public RefreshLayout(final Context context) {
        this(context, null);
    }
    
    public RefreshLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.Refresh = true;
        this.init();
    }
    
    private void init() {
        this.setColorSchemeResources(new int[] { 17170451, 17170458, 17170456, 17170454 });
    }
    
    public boolean isRefresh() {
        return this.Refresh;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2) {
                final float abs = Math.abs(motionEvent.getY() - this.mDownY);
                final float abs2 = Math.abs(motionEvent.getX() - this.mDownX);
                if (Math.round((float)(Math.asin(abs / Math.sqrt((double)(abs * abs + abs2 * abs2))) / 3.141592653589793 * 180.0)) <= 45 || !this.Refresh) {
                    return false;
                }
            }
        }
        else {
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    public void setRefresh(final boolean refresh) {
        this.Refresh = refresh;
    }
}
