package com.otaliastudios.cameraview.gesture;

import android.view.MotionEvent;
import android.os.Build$VERSION;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ScaleGestureDetector;

public class PinchGestureFinder extends GestureFinder
{
    private static final float ADD_SENSITIVITY = 2.0f;
    private ScaleGestureDetector mDetector;
    private float mFactor;
    private boolean mNotify;
    
    public PinchGestureFinder(final GestureFinder$Controller gestureFinder$Controller) {
        super(gestureFinder$Controller, 2);
        this.mFactor = 0.0f;
        this.setGesture(Gesture.PINCH);
        this.mDetector = new ScaleGestureDetector(gestureFinder$Controller.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new PinchGestureFinder$1(this));
        if (Build$VERSION.SDK_INT >= 19) {
            this.mDetector.setQuickScaleEnabled(false);
        }
    }
    
    protected float getFactor() {
        return this.mFactor;
    }
    
    public float getValue(final float n, final float n2, final float n3) {
        return n + this.getFactor() * (n3 - n2);
    }
    
    protected boolean handleTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        boolean b = false;
        if (action == 0) {
            this.mNotify = false;
        }
        this.mDetector.onTouchEvent(motionEvent);
        if (this.mNotify) {
            this.getPoint(0).x = motionEvent.getX(0);
            this.getPoint(0).y = motionEvent.getY(0);
            final int pointerCount = motionEvent.getPointerCount();
            b = true;
            if (pointerCount > 1) {
                this.getPoint(1).x = motionEvent.getX(1);
                this.getPoint(1).y = motionEvent.getY(1);
                b = b;
            }
        }
        return b;
    }
}
