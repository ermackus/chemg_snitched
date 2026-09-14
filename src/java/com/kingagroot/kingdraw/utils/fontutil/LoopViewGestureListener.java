package com.kingagroot.kingdraw.utils.fontutil;

import android.view.MotionEvent;
import android.view.GestureDetector$SimpleOnGestureListener;

final class LoopViewGestureListener extends GestureDetector$SimpleOnGestureListener
{
    final LoopView loopView;
    
    LoopViewGestureListener(final LoopView loopView) {
        this.loopView = loopView;
    }
    
    public final boolean onFling(final MotionEvent motionEvent, final MotionEvent motionEvent2, final float n, final float n2) {
        this.loopView.scrollBy(n2);
        return true;
    }
}
