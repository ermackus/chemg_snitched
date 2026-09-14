package com.kingagroot.kingdraw.widget;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;

public class GViewPager extends ViewPager
{
    boolean scrollable;
    
    public GViewPager(final Context context) {
        super(context);
    }
    
    public GViewPager(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.scrollable && super.onInterceptTouchEvent(motionEvent);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.scrollable && super.onTouchEvent(motionEvent);
    }
    
    public void setScrollable(final boolean scrollable) {
        this.scrollable = scrollable;
    }
}
