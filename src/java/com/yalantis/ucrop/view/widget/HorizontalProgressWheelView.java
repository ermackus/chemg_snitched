package com.yalantis.ucrop.view.widget;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import com.yalantis.ucrop.R$dimen;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.R$color;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class HorizontalProgressWheelView extends View
{
    private final Rect mCanvasClipBounds;
    private float mLastTouchedPosition;
    private int mMiddleLineColor;
    private int mProgressLineHeight;
    private int mProgressLineMargin;
    private Paint mProgressLinePaint;
    private int mProgressLineWidth;
    private Paint mProgressMiddleLinePaint;
    private boolean mScrollStarted;
    private ScrollingListener mScrollingListener;
    private float mTotalScrollDistance;
    
    public HorizontalProgressWheelView(final Context context) {
        this(context, null);
    }
    
    public HorizontalProgressWheelView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public HorizontalProgressWheelView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCanvasClipBounds = new Rect();
        this.init();
    }
    
    public HorizontalProgressWheelView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mCanvasClipBounds = new Rect();
    }
    
    private void init() {
        this.mMiddleLineColor = ContextCompat.getColor(this.getContext(), R$color.ucrop_color_widget_rotate_mid_line);
        this.mProgressLineWidth = this.getContext().getResources().getDimensionPixelSize(R$dimen.ucrop_width_horizontal_wheel_progress_line);
        this.mProgressLineHeight = this.getContext().getResources().getDimensionPixelSize(R$dimen.ucrop_height_horizontal_wheel_progress_line);
        this.mProgressLineMargin = this.getContext().getResources().getDimensionPixelSize(R$dimen.ucrop_margin_horizontal_wheel_progress_line);
        (this.mProgressLinePaint = new Paint(1)).setStyle(Paint$Style.STROKE);
        this.mProgressLinePaint.setStrokeWidth((float)this.mProgressLineWidth);
        this.mProgressLinePaint.setColor(this.getResources().getColor(R$color.ucrop_color_progress_wheel_line));
        (this.mProgressMiddleLinePaint = new Paint(this.mProgressLinePaint)).setColor(this.mMiddleLineColor);
        this.mProgressMiddleLinePaint.setStrokeCap(Paint$Cap.ROUND);
        this.mProgressMiddleLinePaint.setStrokeWidth((float)this.getContext().getResources().getDimensionPixelSize(R$dimen.ucrop_width_middle_wheel_progress_line));
    }
    
    private void onScrollEvent(final MotionEvent motionEvent, final float n) {
        this.mTotalScrollDistance -= n;
        this.postInvalidate();
        this.mLastTouchedPosition = motionEvent.getX();
        final ScrollingListener mScrollingListener = this.mScrollingListener;
        if (mScrollingListener != null) {
            mScrollingListener.onScroll(-n, this.mTotalScrollDistance);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.getClipBounds(this.mCanvasClipBounds);
        final int width = this.mCanvasClipBounds.width();
        final int mProgressLineWidth = this.mProgressLineWidth;
        final int mProgressLineMargin = this.mProgressLineMargin;
        final int n = width / (mProgressLineWidth + mProgressLineMargin);
        final float mTotalScrollDistance = this.mTotalScrollDistance;
        final float n2 = (float)(mProgressLineMargin + mProgressLineWidth);
        for (int i = 0; i < n; ++i) {
            final int n3 = n / 4;
            if (i < n3) {
                this.mProgressLinePaint.setAlpha((int)(i / (float)n3 * 255.0f));
            }
            else if (i > n * 3 / 4) {
                this.mProgressLinePaint.setAlpha((int)((n - i) / (float)n3 * 255.0f));
            }
            else {
                this.mProgressLinePaint.setAlpha(255);
            }
            final float n4 = -(mTotalScrollDistance % n2);
            canvas.drawLine(this.mCanvasClipBounds.left + n4 + (this.mProgressLineWidth + this.mProgressLineMargin) * i, this.mCanvasClipBounds.centerY() - this.mProgressLineHeight / 4.0f, n4 + this.mCanvasClipBounds.left + (this.mProgressLineWidth + this.mProgressLineMargin) * i, this.mCanvasClipBounds.centerY() + this.mProgressLineHeight / 4.0f, this.mProgressLinePaint);
        }
        canvas.drawLine((float)this.mCanvasClipBounds.centerX(), this.mCanvasClipBounds.centerY() - this.mProgressLineHeight / 2.0f, (float)this.mCanvasClipBounds.centerX(), this.mProgressLineHeight / 2.0f + this.mCanvasClipBounds.centerY(), this.mProgressMiddleLinePaint);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    final float n = motionEvent.getX() - this.mLastTouchedPosition;
                    if (n != 0.0f) {
                        if (!this.mScrollStarted) {
                            this.mScrollStarted = true;
                            final ScrollingListener mScrollingListener = this.mScrollingListener;
                            if (mScrollingListener != null) {
                                mScrollingListener.onScrollStart();
                            }
                        }
                        this.onScrollEvent(motionEvent, n);
                    }
                }
            }
            else {
                final ScrollingListener mScrollingListener2 = this.mScrollingListener;
                if (mScrollingListener2 != null) {
                    this.mScrollStarted = false;
                    mScrollingListener2.onScrollEnd();
                }
            }
        }
        else {
            this.mLastTouchedPosition = motionEvent.getX();
        }
        return true;
    }
    
    public void setMiddleLineColor(final int n) {
        this.mMiddleLineColor = n;
        this.mProgressMiddleLinePaint.setColor(n);
        this.invalidate();
    }
    
    public void setScrollingListener(final ScrollingListener mScrollingListener) {
        this.mScrollingListener = mScrollingListener;
    }
    
    public interface ScrollingListener
    {
        void onScroll(final float p0, final float p1);
        
        void onScrollEnd();
        
        void onScrollStart();
    }
}
