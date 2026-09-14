package com.kingagroot.kingdraw.core.tool;

import com.kingagroot.kingdraw.core.OnKingDrawViewListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import android.view.ScaleGestureDetector;
import com.kingagroot.kingdraw.core.view.KingDrawView;

class DragTool extends BaseTool
{
    private static final int ACTION_SCALE_DOWN = 1002;
    private static final int ACTION_SCALE_UP = 1001;
    private KingDrawView kingDrawView;
    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector myDetector;
    private DragTool.DragTool$TouchState touchState;
    
    public DragTool(final KingDrawView kingDrawView) {
        this.touchState = DragTool.DragTool$TouchState.RELEASE;
        this.kingDrawView = kingDrawView;
        this.myDetector = new GestureDetector(kingDrawView.getContext(), (GestureDetector$OnGestureListener)new DragTool.DragTool$MyGestureListener(this, (DragTool$1)null));
        this.mScaleGestureDetector = new ScaleGestureDetector(kingDrawView.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new DragTool.DragTool$MyScaleGestureListener(this, (DragTool$1)null));
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getAction() == 1) {
            this.touchState = DragTool.DragTool$TouchState.RELEASE;
        }
        if (pointerCount == 1) {
            final OnKingDrawViewListener onKingDrawViewListener = this.kingDrawView.getOnKingDrawViewListener();
            if (motionEvent.getAction() == 1 && onKingDrawViewListener != null) {
                onKingDrawViewListener.onDrag(false);
            }
            if (motionEvent.getAction() == 2) {
                if (this.touchState == DragTool.DragTool$TouchState.DRAG) {
                    this.kingDrawView.onTouchEvent(motionEvent);
                }
            }
            else {
                this.kingDrawView.onTouchEvent(motionEvent);
            }
            return this.myDetector.onTouchEvent(motionEvent);
        }
        return motionEvent.getAction() == 1 || this.mScaleGestureDetector.onTouchEvent(motionEvent);
    }
}
