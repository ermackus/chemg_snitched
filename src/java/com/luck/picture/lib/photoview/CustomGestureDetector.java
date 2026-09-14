package com.luck.picture.lib.photoview;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ViewConfiguration;
import android.content.Context;
import android.view.VelocityTracker;
import android.view.ScaleGestureDetector;

class CustomGestureDetector
{
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId;
    private int mActivePointerIndex;
    private final ScaleGestureDetector mDetector;
    private boolean mIsDragging;
    private float mLastTouchX;
    private float mLastTouchY;
    private OnGestureListener mListener;
    private final float mMinimumVelocity;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;
    
    CustomGestureDetector(final Context context, final OnGestureListener mListener) {
        this.mActivePointerId = -1;
        this.mActivePointerIndex = 0;
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mMinimumVelocity = (float)value.getScaledMinimumFlingVelocity();
        this.mTouchSlop = (float)value.getScaledTouchSlop();
        this.mListener = mListener;
        this.mDetector = new ScaleGestureDetector(context, (ScaleGestureDetector$OnScaleGestureListener)new ScaleGestureDetector$OnScaleGestureListener(this) {
            private float lastFocusX;
            private float lastFocusY = 0.0f;
            final CustomGestureDetector this$0;
            
            public boolean onScale(final ScaleGestureDetector scaleGestureDetector) {
                final float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (!Float.isNaN(scaleFactor) && !Float.isInfinite(scaleFactor)) {
                    if (scaleFactor >= 0.0f) {
                        this.this$0.mListener.onScale(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), scaleGestureDetector.getFocusX() - this.lastFocusX, scaleGestureDetector.getFocusY() - this.lastFocusY);
                        this.lastFocusX = scaleGestureDetector.getFocusX();
                        this.lastFocusY = scaleGestureDetector.getFocusY();
                    }
                    return true;
                }
                return false;
            }
            
            public boolean onScaleBegin(final ScaleGestureDetector scaleGestureDetector) {
                this.lastFocusX = scaleGestureDetector.getFocusX();
                this.lastFocusY = scaleGestureDetector.getFocusY();
                return true;
            }
            
            public void onScaleEnd(final ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }
    
    private float getActiveX(final MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        }
        catch (final Exception ex) {
            return motionEvent.getX();
        }
    }
    
    private float getActiveY(final MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        }
        catch (final Exception ex) {
            return motionEvent.getY();
        }
    }
    
    private boolean processTouchEvent(final MotionEvent motionEvent) {
        final int n = motionEvent.getAction() & 0xFF;
        final int n2 = 0;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n == 6) {
                            final int pointerIndex = Util.getPointerIndex(motionEvent.getAction());
                            if (motionEvent.getPointerId(pointerIndex) == this.mActivePointerId) {
                                int n3;
                                if (pointerIndex == 0) {
                                    n3 = 1;
                                }
                                else {
                                    n3 = 0;
                                }
                                this.mActivePointerId = motionEvent.getPointerId(n3);
                                this.mLastTouchX = motionEvent.getX(n3);
                                this.mLastTouchY = motionEvent.getY(n3);
                            }
                        }
                    }
                    else {
                        this.mActivePointerId = -1;
                        final VelocityTracker mVelocityTracker = this.mVelocityTracker;
                        if (mVelocityTracker != null) {
                            mVelocityTracker.recycle();
                            this.mVelocityTracker = null;
                        }
                    }
                }
                else {
                    final float activeX = this.getActiveX(motionEvent);
                    final float activeY = this.getActiveY(motionEvent);
                    final float n4 = activeX - this.mLastTouchX;
                    final float n5 = activeY - this.mLastTouchY;
                    if (!this.mIsDragging) {
                        this.mIsDragging = (Math.sqrt((double)(n4 * n4 + n5 * n5)) >= this.mTouchSlop);
                    }
                    if (this.mIsDragging) {
                        this.mListener.onDrag(n4, n5);
                        this.mLastTouchX = activeX;
                        this.mLastTouchY = activeY;
                        final VelocityTracker mVelocityTracker2 = this.mVelocityTracker;
                        if (mVelocityTracker2 != null) {
                            mVelocityTracker2.addMovement(motionEvent);
                        }
                    }
                }
            }
            else {
                this.mActivePointerId = -1;
                if (this.mIsDragging && this.mVelocityTracker != null) {
                    this.mLastTouchX = this.getActiveX(motionEvent);
                    this.mLastTouchY = this.getActiveY(motionEvent);
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    final float xVelocity = this.mVelocityTracker.getXVelocity();
                    final float yVelocity = this.mVelocityTracker.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                        this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                    }
                }
                final VelocityTracker mVelocityTracker3 = this.mVelocityTracker;
                if (mVelocityTracker3 != null) {
                    mVelocityTracker3.recycle();
                    this.mVelocityTracker = null;
                }
            }
        }
        else {
            this.mActivePointerId = motionEvent.getPointerId(0);
            final VelocityTracker obtain = VelocityTracker.obtain();
            if ((this.mVelocityTracker = obtain) != null) {
                obtain.addMovement(motionEvent);
            }
            this.mLastTouchX = this.getActiveX(motionEvent);
            this.mLastTouchY = this.getActiveY(motionEvent);
            this.mIsDragging = false;
        }
        final int mActivePointerId = this.mActivePointerId;
        int n6 = n2;
        if (mActivePointerId != -1) {
            n6 = mActivePointerId;
        }
        this.mActivePointerIndex = motionEvent.findPointerIndex(n6);
        return true;
    }
    
    public boolean isDragging() {
        return this.mIsDragging;
    }
    
    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        try {
            this.mDetector.onTouchEvent(motionEvent);
            return this.processTouchEvent(motionEvent);
        }
        catch (final IllegalArgumentException ex) {
            return true;
        }
    }
}
