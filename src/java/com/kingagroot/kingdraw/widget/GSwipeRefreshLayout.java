package com.kingagroot.kingdraw.widget;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class GSwipeRefreshLayout extends SwipeRefreshLayout
{
    private GestureDetector gestureDetector;
    private OnFlingListener onFlingListener;
    private final GestureDetector$OnGestureListener onGestureListener;
    
    public GSwipeRefreshLayout(final Context context) {
        this(context, null);
    }
    
    public GSwipeRefreshLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.onGestureListener = (GestureDetector$OnGestureListener)new GSwipeRefreshLayout$1(this);
        this.init();
    }
    
    private void init() {
        this.setColorSchemeResources(new int[] { 17170451, 17170458, 17170456, 17170454 });
        this.gestureDetector = new GestureDetector(this.onGestureListener);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
    
    public void setOnFlingListener(final OnFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }
}
