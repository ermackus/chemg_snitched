package com.kingagroot.kingdraw.widget;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.view.GestureDetector$OnGestureListener;
import android.widget.AbsListView$OnScrollListener;
import android.view.GestureDetector;
import android.widget.ListView;

public class MListView extends ListView
{
    private final GestureDetector gestureDetector;
    private final AbsListView$OnScrollListener listViewOnScrollListener;
    private OnFlingListener onFlingListener;
    private final GestureDetector$OnGestureListener onGestureListener;
    private MListView.MListView$OnMListViewlistener onMListViewlistener;
    
    public MListView(final Context context) {
        this(context, null);
    }
    
    public MListView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public MListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.onGestureListener = (GestureDetector$OnGestureListener)new MListView$1(this);
        this.setOnScrollListener(this.listViewOnScrollListener = (AbsListView$OnScrollListener)new MListView.MListView$ListViewOnScrollListener(this, (MListView$1)null));
        this.gestureDetector = new GestureDetector(this.onGestureListener);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
    
    public void setOnFlingListener(final OnFlingListener onFlingListener) {
        this.onFlingListener = onFlingListener;
    }
    
    public void setOnMListViewlistener(final MListView.MListView$OnMListViewlistener onMListViewlistener) {
        this.onMListViewlistener = onMListViewlistener;
    }
}
