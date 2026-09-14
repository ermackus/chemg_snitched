package com.yalantis.ucrop.util;

import android.view.MotionEvent;

public class RotationGestureDetector
{
    private static final int INVALID_POINTER_INDEX = -1;
    private float fX;
    private float fY;
    private float mAngle;
    private boolean mIsFirstTouch;
    private OnRotationGestureListener mListener;
    private int mPointerIndex1;
    private int mPointerIndex2;
    private float sX;
    private float sY;
    
    public RotationGestureDetector(final OnRotationGestureListener mListener) {
        this.mListener = mListener;
        this.mPointerIndex1 = -1;
        this.mPointerIndex2 = -1;
    }
    
    private float calculateAngleBetweenLines(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        return this.calculateAngleDelta((float)Math.toDegrees((double)(float)Math.atan2((double)(n2 - n4), (double)(n - n3))), (float)Math.toDegrees((double)(float)Math.atan2((double)(n6 - n8), (double)(n5 - n7))));
    }
    
    private float calculateAngleDelta(float mAngle, final float n) {
        mAngle = n % 360.0f - mAngle % 360.0f;
        this.mAngle = mAngle;
        if (mAngle < -180.0f) {
            this.mAngle = mAngle + 360.0f;
        }
        else if (mAngle > 180.0f) {
            this.mAngle = mAngle - 360.0f;
        }
        return this.mAngle;
    }
    
    public float getAngle() {
        return this.mAngle;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            this.mPointerIndex2 = -1;
                        }
                    }
                    else {
                        this.fX = motionEvent.getX();
                        this.fY = motionEvent.getY();
                        this.mPointerIndex2 = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()));
                        this.mAngle = 0.0f;
                        this.mIsFirstTouch = true;
                    }
                }
                else if (this.mPointerIndex1 != -1 && this.mPointerIndex2 != -1 && motionEvent.getPointerCount() > this.mPointerIndex2) {
                    final float x = motionEvent.getX(this.mPointerIndex1);
                    final float y = motionEvent.getY(this.mPointerIndex1);
                    final float x2 = motionEvent.getX(this.mPointerIndex2);
                    final float y2 = motionEvent.getY(this.mPointerIndex2);
                    if (this.mIsFirstTouch) {
                        this.mAngle = 0.0f;
                        this.mIsFirstTouch = false;
                    }
                    else {
                        this.calculateAngleBetweenLines(this.fX, this.fY, this.sX, this.sY, x2, y2, x, y);
                    }
                    final OnRotationGestureListener mListener = this.mListener;
                    if (mListener != null) {
                        mListener.onRotation(this);
                    }
                    this.fX = x2;
                    this.fY = y2;
                    this.sX = x;
                    this.sY = y;
                }
            }
            else {
                this.mPointerIndex1 = -1;
            }
        }
        else {
            this.sX = motionEvent.getX();
            this.sY = motionEvent.getY();
            this.mPointerIndex1 = motionEvent.findPointerIndex(motionEvent.getPointerId(0));
            this.mAngle = 0.0f;
            this.mIsFirstTouch = true;
        }
        return true;
    }
    
    public interface OnRotationGestureListener
    {
        boolean onRotation(final RotationGestureDetector p0);
    }
    
    public static class SimpleOnRotationGestureListener implements OnRotationGestureListener
    {
        @Override
        public boolean onRotation(final RotationGestureDetector rotationGestureDetector) {
            return false;
        }
    }
}
