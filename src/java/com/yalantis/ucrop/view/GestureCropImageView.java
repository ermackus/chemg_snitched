package com.yalantis.ucrop.view;

import android.view.MotionEvent;
import com.yalantis.ucrop.util.RotationGestureDetector$OnRotationGestureListener;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.os.Handler;
import android.view.GestureDetector$OnGestureListener;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ScaleGestureDetector;
import com.yalantis.ucrop.util.RotationGestureDetector;
import android.view.GestureDetector;

public class GestureCropImageView extends CropImageView
{
    private static final int DOUBLE_TAP_ZOOM_DURATION = 200;
    private int mDoubleTapScaleSteps;
    private GestureDetector mGestureDetector;
    private boolean mIsGestureEnabled;
    private boolean mIsRotateEnabled;
    private boolean mIsScaleEnabled;
    private float mMidPntX;
    private float mMidPntY;
    private RotationGestureDetector mRotateDetector;
    private ScaleGestureDetector mScaleDetector;
    
    public GestureCropImageView(final Context context) {
        super(context);
        this.mIsRotateEnabled = true;
        this.mIsScaleEnabled = true;
        this.mIsGestureEnabled = true;
        this.mDoubleTapScaleSteps = 5;
    }
    
    public GestureCropImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public GestureCropImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mIsRotateEnabled = true;
        this.mIsScaleEnabled = true;
        this.mIsGestureEnabled = true;
        this.mDoubleTapScaleSteps = 5;
    }
    
    private void setupGestureListeners() {
        this.mGestureDetector = new GestureDetector(this.getContext(), (GestureDetector$OnGestureListener)new GestureCropImageView.GestureCropImageView$GestureListener(this, (GestureCropImageView$1)null), (Handler)null, true);
        this.mScaleDetector = new ScaleGestureDetector(this.getContext(), (ScaleGestureDetector$OnScaleGestureListener)new GestureCropImageView.GestureCropImageView$ScaleListener(this, (GestureCropImageView$1)null));
        this.mRotateDetector = new RotationGestureDetector((RotationGestureDetector$OnRotationGestureListener)new GestureCropImageView.GestureCropImageView$RotateListener(this, (GestureCropImageView$1)null));
    }
    
    public int getDoubleTapScaleSteps() {
        return this.mDoubleTapScaleSteps;
    }
    
    protected float getDoubleTapTargetScale() {
        return this.getCurrentScale() * (float)Math.pow((double)(this.getMaxScale() / this.getMinScale()), (double)(1.0f / this.mDoubleTapScaleSteps));
    }
    
    protected void init() {
        super.init();
        this.setupGestureListeners();
    }
    
    public boolean isGestureEnabled() {
        return this.mIsGestureEnabled;
    }
    
    public boolean isRotateEnabled() {
        return this.mIsRotateEnabled;
    }
    
    public boolean isScaleEnabled() {
        return this.mIsScaleEnabled;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 0xFF) == 0x0) {
            this.cancelAllAnimations();
        }
        if (motionEvent.getPointerCount() > 1) {
            this.mMidPntX = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
            this.mMidPntY = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
        }
        if (this.mIsGestureEnabled) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if (this.mIsScaleEnabled) {
            this.mScaleDetector.onTouchEvent(motionEvent);
        }
        if (this.mIsRotateEnabled) {
            this.mRotateDetector.onTouchEvent(motionEvent);
        }
        if ((motionEvent.getAction() & 0xFF) == 0x1) {
            this.setImageToWrapCropBounds();
        }
        return true;
    }
    
    public void setDoubleTapScaleSteps(final int mDoubleTapScaleSteps) {
        this.mDoubleTapScaleSteps = mDoubleTapScaleSteps;
    }
    
    public void setGestureEnabled(final boolean mIsGestureEnabled) {
        this.mIsGestureEnabled = mIsGestureEnabled;
    }
    
    public void setRotateEnabled(final boolean mIsRotateEnabled) {
        this.mIsRotateEnabled = mIsRotateEnabled;
    }
    
    public void setScaleEnabled(final boolean mIsScaleEnabled) {
        this.mIsScaleEnabled = mIsScaleEnabled;
    }
}
