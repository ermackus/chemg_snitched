package com.goodsrc.ui.library.widget;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;

public class UnScrollViewPager extends ViewPager
{
    public UnScrollViewPager(final Context context) {
        super(context);
    }
    
    public UnScrollViewPager(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return false;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return false;
    }
}
