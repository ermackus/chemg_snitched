package com.otaliastudios.cameraview.gesture;

import android.view.MotionEvent;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import com.otaliastudios.cameraview.CameraLogger;

public class ScrollGestureFinder extends GestureFinder
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private GestureDetector mDetector;
    private float mFactor;
    private boolean mNotify;
    
    static {
        LOG = CameraLogger.create(TAG = ScrollGestureFinder.class.getSimpleName());
    }
    
    public ScrollGestureFinder(final GestureFinder$Controller gestureFinder$Controller) {
        super(gestureFinder$Controller, 2);
        (this.mDetector = new GestureDetector(gestureFinder$Controller.getContext(), (GestureDetector$OnGestureListener)new ScrollGestureFinder$1(this, gestureFinder$Controller))).setIsLongpressEnabled(false);
    }
    
    protected float getFactor() {
        return this.mFactor;
    }
    
    public float getValue(final float n, final float n2, final float n3) {
        return n + this.getFactor() * (n3 - n2) * 2.0f;
    }
    
    protected boolean handleTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mNotify = false;
        }
        this.mDetector.onTouchEvent(motionEvent);
        if (this.mNotify) {
            ScrollGestureFinder.LOG.i(new Object[] { "Notifying a gesture of type", this.getGesture().name() });
        }
        return this.mNotify;
    }
}
