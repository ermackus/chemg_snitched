package com.kingagroot.kingdraw.ui.baike.seekbar;

import androidx.core.view.MotionEventCompat;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.ui.baike.seekbar.drawable.SeekBarBackgroundDrawable;
import com.kingagroot.kingdraw.ui.baike.seekbar.drawable.SeekBarProgressDrawable;
import com.kingagroot.kingdraw.ui.baike.seekbar.drawable.ThumbDrawable;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.kingdraw.ui.baike.seekbar.drawable.SeekBarBaseDrawable;
import android.view.View;

public class GVerticalSeekBar extends View
{
    private float Length;
    private SeekBarBaseDrawable backgroundDrawable;
    private float mDownX;
    private int mDragOffset;
    private boolean mIsDragging;
    private int mMax;
    private int mMin;
    private float mTouchSlop;
    private int mValue;
    private OnProgressChangeListener onProgressChangeListener;
    private SeekBarBaseDrawable progressDrawable;
    private SeekBarBaseDrawable thumbDrawable;
    private int width;
    
    public GVerticalSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.mMax = 100;
        this.mMin = 0;
        this.mValue = 0;
        this.init(context);
    }
    
    private void init(final Context context) {
        this.mTouchSlop = (float)ViewConfiguration.get(context).getScaledTouchSlop();
        this.thumbDrawable = (SeekBarBaseDrawable)new ThumbDrawable(context);
        this.progressDrawable = (SeekBarBaseDrawable)new SeekBarProgressDrawable(context);
        this.backgroundDrawable = (SeekBarBaseDrawable)new SeekBarBackgroundDrawable();
    }
    
    private boolean isDragging() {
        return this.mIsDragging;
    }
    
    private void notifyProgress() {
        this.updateDrawBounds();
    }
    
    private void startDragging(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        if (this.thumbDrawable.isInArea(x, y)) {
            this.mIsDragging = true;
            this.mDragOffset = (int)(y - this.thumbDrawable.getBound().top);
        }
        if (this.mIsDragging) {
            final OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onStart();
            }
        }
    }
    
    private void stopDragging() {
        final OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
        if (onProgressChangeListener != null) {
            onProgressChangeListener.onStop();
        }
        this.setPressed(this.mIsDragging = false);
    }
    
    private void updateDragging(final MotionEvent motionEvent) {
        final float y = motionEvent.getY();
        final float n = (float)this.mDragOffset;
        final float n2 = this.Length - this.thumbDrawable.getSize();
        final float n3 = (n2 - (y - n)) / n2;
        final int mMax = this.mMax;
        final int mMin = this.mMin;
        final int round = Math.round(n3 * (mMax - mMin) + mMin);
        final int mMax2 = this.mMax;
        int n4 = round;
        if (round > mMax2) {
            n4 = mMax2;
        }
        final int mMin2 = this.mMin;
        int progress;
        if ((progress = n4) < mMin2) {
            progress = mMin2;
        }
        this.setProgress(progress);
        this.notifyProgress();
    }
    
    private void updateDrawBounds() {
        final int mValue = this.mValue;
        final int mMin = this.mMin;
        final int n = (int)((1.0f - (mValue - mMin) / (float)(this.mMax - mMin)) * (this.Length - this.thumbDrawable.getSize())) + this.thumbDrawable.getSize() / 2;
        final int n2 = this.width / 2;
        this.thumbDrawable.setBound(new RectF((float)(n2 - this.thumbDrawable.getSize() / 2), (float)(n - this.thumbDrawable.getSize() / 2), (float)(this.thumbDrawable.getSize() / 2 + n2), (float)(this.thumbDrawable.getSize() / 2 + n)));
        final float n3 = (float)(n2 - this.progressDrawable.getSize() / 2);
        final float n4 = (float)(n2 + this.progressDrawable.getSize() / 2);
        final float n5 = (float)n;
        this.progressDrawable.setBound(new RectF(n3, n5, n4, this.Length - this.thumbDrawable.getSize() / 2));
        final OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
        if (onProgressChangeListener != null) {
            onProgressChangeListener.onProgressChanged(n5, this.mValue);
        }
        this.invalidate();
    }
    
    public int getProgress() {
        return this.mValue;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final SeekBarBaseDrawable backgroundDrawable = this.backgroundDrawable;
        if (backgroundDrawable != null) {
            backgroundDrawable.onDraw(canvas);
        }
        final SeekBarBaseDrawable progressDrawable = this.progressDrawable;
        if (progressDrawable != null) {
            progressDrawable.onDraw(canvas);
        }
        final SeekBarBaseDrawable thumbDrawable = this.thumbDrawable;
        if (thumbDrawable != null) {
            thumbDrawable.onDraw(canvas);
        }
    }
    
    protected void onSizeChanged(int width, final int n, final int n2, final int n3) {
        super.onSizeChanged(width, n, n2, n3);
        if (this.Length == 0.0f) {
            this.width = width;
            final float length = (float)n;
            this.Length = length;
            final float n4 = (float)(this.thumbDrawable.getSize() / 2);
            width = this.width / 2;
            this.backgroundDrawable.setBound(new RectF((float)(width - this.backgroundDrawable.getSize() / 2), (float)(this.thumbDrawable.getSize() / 2 + 0), (float)(width + this.backgroundDrawable.getSize() / 2), length - n4));
            this.setProgress(this.mValue);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (!this.isEnabled()) {
            return false;
        }
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked == 3) {
                        this.stopDragging();
                    }
                }
                else if (this.isDragging()) {
                    this.updateDragging(motionEvent);
                }
                else if (Math.abs(motionEvent.getX() - this.mDownX) > this.mTouchSlop) {
                    this.startDragging(motionEvent);
                }
            }
            else {
                if (!this.isDragging()) {
                    this.startDragging(motionEvent);
                    this.updateDragging(motionEvent);
                }
                this.stopDragging();
            }
        }
        else {
            this.mDownX = motionEvent.getX();
            this.startDragging(motionEvent);
        }
        return true;
    }
    
    public void setMax(final int mMax) {
        this.mMax = mMax;
    }
    
    public void setMin(final int mMin) {
        this.mMin = mMin;
    }
    
    public void setOnProgressChangeListener(final OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }
    
    public void setProgress(final int mValue) {
        if (mValue <= this.mMax && mValue >= this.mMin) {
            this.mValue = mValue;
            this.notifyProgress();
        }
    }
}
