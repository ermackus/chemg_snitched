package com.otaliastudios.cameraview.gesture;

import android.content.Context;
import android.view.MotionEvent;
import android.graphics.PointF;

public abstract class GestureFinder
{
    private static final int GRANULARITY = 50;
    private boolean mActive;
    private Controller mController;
    private PointF[] mPoints;
    Gesture mType;
    
    GestureFinder(final Controller mController, final int n) {
        this.mController = mController;
        this.mPoints = new PointF[n];
        for (int i = 0; i < n; ++i) {
            this.mPoints[i] = new PointF(0.0f, 0.0f);
        }
    }
    
    private static float capValue(final float n, float n2, float n3, final float n4) {
        float n5 = n2;
        if (n2 < n3) {
            n5 = n3;
        }
        n2 = n5;
        if (n5 > n4) {
            n2 = n4;
        }
        n3 = (n4 - n3) / 50.0f / 2.0f;
        if (n2 >= n - n3 && n2 <= n3 + n) {
            return n;
        }
        return n2;
    }
    
    public final float computeValue(final float n, final float n2, final float n3) {
        return capValue(n, this.getValue(n, n2, n3), n2, n3);
    }
    
    protected Controller getController() {
        return this.mController;
    }
    
    public final Gesture getGesture() {
        return this.mType;
    }
    
    protected final PointF getPoint(final int n) {
        return this.mPoints[n];
    }
    
    public final PointF[] getPoints() {
        return this.mPoints;
    }
    
    protected abstract float getValue(final float p0, final float p1, final float p2);
    
    protected abstract boolean handleTouchEvent(final MotionEvent p0);
    
    public boolean isActive() {
        return this.mActive;
    }
    
    public final boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mActive && this.handleTouchEvent(motionEvent);
    }
    
    public void setActive(final boolean mActive) {
        this.mActive = mActive;
    }
    
    protected final void setGesture(final Gesture mType) {
        this.mType = mType;
    }
    
    public interface Controller
    {
        Context getContext();
        
        int getHeight();
        
        int getWidth();
    }
}
