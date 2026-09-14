package com.kingagroot.kingdraw.utils;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager
{
    private boolean noScroll;
    
    public NoScrollViewPager(final Context context) {
        super(context);
        this.noScroll = true;
    }
    
    public NoScrollViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.noScroll = true;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return !this.noScroll && super.onInterceptTouchEvent(motionEvent);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return !this.noScroll && super.onTouchEvent(motionEvent);
    }
    
    public void scrollTo(final int n, final int n2) {
        super.scrollTo(n, n2);
    }
    
    public void setCurrentItem(final int currentItem) {
        super.setCurrentItem(currentItem);
    }
    
    public void setCurrentItem(final int n, final boolean b) {
        super.setCurrentItem(n, b);
    }
    
    public void setNoScroll(final boolean noScroll) {
        this.noScroll = noScroll;
    }
}
