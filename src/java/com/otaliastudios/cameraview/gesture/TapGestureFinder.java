package com.otaliastudios.cameraview.gesture;

import android.view.MotionEvent;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;

public class TapGestureFinder extends GestureFinder
{
    private GestureDetector mDetector;
    private boolean mNotify;
    
    public TapGestureFinder(final GestureFinder$Controller gestureFinder$Controller) {
        super(gestureFinder$Controller, 1);
        (this.mDetector = new GestureDetector(gestureFinder$Controller.getContext(), (GestureDetector$OnGestureListener)new TapGestureFinder$1(this))).setIsLongpressEnabled(true);
    }
    
    public float getValue(final float n, final float n2, final float n3) {
        return 0.0f;
    }
    
    protected boolean handleTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mNotify = false;
        }
        this.mDetector.onTouchEvent(motionEvent);
        if (this.mNotify) {
            this.getPoint(0).x = motionEvent.getX();
            this.getPoint(0).y = motionEvent.getY();
            return true;
        }
        return false;
    }
}
