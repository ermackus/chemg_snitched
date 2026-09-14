package com.kingagroot.kingdraw.widget;

import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.content.res.TypedArray;
import com.kingagroot.kingdraw.R$styleable;
import android.graphics.Color;
import android.graphics.Paint$Style;
import android.graphics.RectF;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Align;
import android.graphics.Path$Op;
import android.os.Build$VERSION;
import android.graphics.Path$Direction;
import android.graphics.Canvas;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import java.util.List;
import android.graphics.Path;
import android.view.View;

public class WaveSideBarView extends View
{
    private static final double ANGLE = 0.7853981633974483;
    private static final double ANGLE_R = 1.5707963267948966;
    private static int Margin = 0;
    private static final String TAG = "WaveSlideBarView";
    private OnTouchLetterChangeListener listener;
    private int mBackground;
    private int mBallBackground;
    private float mBallCentreX;
    private final Path mBallPath;
    private int mBallRadius;
    private int mCenterY;
    private int mChoose;
    private int mHeight;
    private int mItemHeight;
    private float mItemStartY;
    private float mLargeTextSize;
    private List<String> mLetters;
    private final Paint mLettersPaint;
    private float mPosX;
    private float mPosY;
    private int mRadius;
    private float mRatio;
    ValueAnimator mRatioAnimator;
    private int mTextColor;
    private int mTextColorChoose;
    private final Paint mTextPaint;
    private float mTextSize;
    private int mWaveColor;
    private Paint mWavePaint;
    private final Path mWavePath;
    private int mWidth;
    private int newChoose;
    private int oldChoose;
    
    public WaveSideBarView(final Context context) {
        this(context, null);
    }
    
    public WaveSideBarView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public WaveSideBarView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mLetters = (List<String>)new ArrayList();
        this.mChoose = -1;
        this.mLettersPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mWavePaint = new Paint();
        this.mWavePath = new Path();
        this.mBallPath = new Path();
        this.init(context, set);
        this.initData();
    }
    
    private int dip2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    private void drawBallPath(final Canvas canvas) {
        final int mWidth = this.mWidth;
        final int mBallRadius = this.mBallRadius;
        this.mBallCentreX = mWidth + mBallRadius - (this.mRadius * 2.0f + mBallRadius * 2.0f) * this.mRatio;
        this.mBallPath.reset();
        this.mBallPath.addCircle(this.mBallCentreX, (float)this.mCenterY, (float)this.mBallRadius, Path$Direction.CW);
        if (Build$VERSION.SDK_INT >= 19) {
            this.mBallPath.op(this.mWavePath, Path$Op.DIFFERENCE);
        }
        this.mBallPath.close();
        canvas.drawPath(this.mBallPath, this.mWavePaint);
    }
    
    private void drawChooseText(final Canvas canvas) {
        if (this.mChoose != -1) {
            this.mLettersPaint.reset();
            this.mLettersPaint.setColor(this.mTextColorChoose);
            this.mLettersPaint.setTextSize(this.mTextSize);
            this.mLettersPaint.setTextAlign(Paint$Align.CENTER);
            canvas.drawText((String)this.mLetters.get(this.mChoose), this.mPosX, this.mPosY, this.mLettersPaint);
            if (this.mRatio >= 0.9f) {
                final String s = (String)this.mLetters.get(this.mChoose);
                final Paint$FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
                canvas.drawText(s, this.mBallCentreX, this.mCenterY + Math.abs(-fontMetrics.bottom - fontMetrics.top) / 2.0f, this.mTextPaint);
            }
        }
    }
    
    private void drawLetters(final Canvas canvas) {
        final RectF rectF = new RectF();
        rectF.left = this.mPosX - this.mTextSize;
        rectF.right = this.mPosX + this.mTextSize;
        rectF.top = this.mItemStartY;
        rectF.bottom = this.mItemStartY + this.mItemHeight * this.mLetters.size() + WaveSideBarView.Margin;
        this.mLettersPaint.reset();
        this.mLettersPaint.setStyle(Paint$Style.FILL);
        this.mLettersPaint.setColor(this.mBackground);
        this.mLettersPaint.setAntiAlias(true);
        final float mTextSize = this.mTextSize;
        canvas.drawRoundRect(rectF, mTextSize, mTextSize, this.mLettersPaint);
        for (int i = 0; i < this.mLetters.size(); ++i) {
            this.mLettersPaint.reset();
            this.mLettersPaint.setColor(this.mTextColor);
            this.mLettersPaint.setAntiAlias(true);
            this.mLettersPaint.setTextSize(this.mTextSize);
            this.mLettersPaint.setTextAlign(Paint$Align.CENTER);
            final Paint$FontMetrics fontMetrics = this.mLettersPaint.getFontMetrics();
            final float mPosY = this.mItemHeight * i + Math.abs(-fontMetrics.bottom - fontMetrics.top) / 2.0f + this.mItemStartY + WaveSideBarView.Margin * 2;
            if (i == this.mChoose) {
                this.mPosY = mPosY;
            }
            else {
                canvas.drawText((String)this.mLetters.get(i), this.mPosX, mPosY, this.mLettersPaint);
            }
        }
    }
    
    private void drawWavePath(final Canvas canvas) {
        this.mWavePath.reset();
        this.mWavePath.moveTo((float)this.mWidth, (float)(this.mCenterY - this.mRadius * 3));
        final int mCenterY = this.mCenterY;
        final int mRadius = this.mRadius;
        final int n = mCenterY - mRadius * 2;
        final int n2 = (int)(this.mWidth - mRadius * Math.cos(0.7853981633974483) * this.mRatio);
        final int n3 = (int)(n + this.mRadius * Math.sin(0.7853981633974483));
        final Path mWavePath = this.mWavePath;
        final float n4 = (float)this.mWidth;
        final float n5 = (float)n;
        final float n6 = (float)n2;
        mWavePath.quadTo(n4, n5, n6, (float)n3);
        final int n7 = (int)(this.mWidth - this.mRadius * 1.8f * Math.sin(1.5707963267948966) * this.mRatio);
        final int mCenterY2 = this.mCenterY;
        final int mRadius2 = this.mRadius;
        final int n8 = mRadius2 * 2 + mCenterY2;
        this.mWavePath.quadTo((float)n7, (float)mCenterY2, n6, (float)(int)(n8 - mRadius2 * Math.cos(0.7853981633974483)));
        final Path mWavePath2 = this.mWavePath;
        final int mWidth = this.mWidth;
        mWavePath2.quadTo((float)mWidth, (float)n8, (float)mWidth, (float)(n8 + this.mRadius));
        this.mWavePath.close();
        canvas.drawPath(this.mWavePath, this.mWavePaint);
    }
    
    private void init(final Context context, final AttributeSet set) {
        this.mTextColor = Color.parseColor("#969696");
        this.mWaveColor = Color.parseColor("#be69be91");
        this.mTextColorChoose = context.getResources().getColor(17170443);
        WaveSideBarView.Margin = this.dip2px(context, 5.0f);
        if (set != null) {
            final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.WaveSideBarView);
            this.mTextColor = obtainStyledAttributes.getColor(6, this.mTextColor);
            this.mTextColorChoose = obtainStyledAttributes.getColor(2, this.mTextColorChoose);
            this.mTextSize = (float)obtainStyledAttributes.getDimensionPixelSize(7, 20);
            this.mLargeTextSize = (float)obtainStyledAttributes.getDimensionPixelSize(4, 20);
            this.mWaveColor = obtainStyledAttributes.getColor(8, this.mWaveColor);
            this.mRadius = obtainStyledAttributes.getDimensionPixelSize(5, 20);
            this.mBallRadius = obtainStyledAttributes.getDimensionPixelSize(1, 20);
            this.mItemHeight = obtainStyledAttributes.getDimensionPixelOffset(3, 20);
            this.mBackground = obtainStyledAttributes.getColor(0, -16777216);
            obtainStyledAttributes.recycle();
        }
        (this.mWavePaint = new Paint()).setAntiAlias(true);
        this.mWavePaint.setStyle(Paint$Style.FILL);
        this.mWavePaint.setColor(this.mWaveColor);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(this.mTextColorChoose);
        this.mTextPaint.setStyle(Paint$Style.FILL);
        this.mTextPaint.setTextSize(this.mLargeTextSize);
        this.mTextPaint.setTextAlign(Paint$Align.CENTER);
    }
    
    private void initData() {
        this.mLetters.add((Object)"A");
        this.mLetters.add((Object)"B");
        this.mLetters.add((Object)"C");
        this.mLetters.add((Object)"D");
        this.mLetters.add((Object)"E");
        this.mLetters.add((Object)"F");
        this.mLetters.add((Object)"G");
        this.mLetters.add((Object)"H");
        this.mLetters.add((Object)"I");
        this.mLetters.add((Object)"J");
        this.mLetters.add((Object)"K");
        this.mLetters.add((Object)"L");
        this.mLetters.add((Object)"M");
        this.mLetters.add((Object)"N");
        this.mLetters.add((Object)"O");
        this.mLetters.add((Object)"P");
        this.mLetters.add((Object)"Q");
        this.mLetters.add((Object)"R");
        this.mLetters.add((Object)"S");
        this.mLetters.add((Object)"T");
        this.mLetters.add((Object)"U");
        this.mLetters.add((Object)"V");
        this.mLetters.add((Object)"W");
        this.mLetters.add((Object)"X");
        this.mLetters.add((Object)"Y");
        this.mLetters.add((Object)"Z");
        this.mLetters.add((Object)"#");
    }
    
    private void startAnimator(final float... floatValues) {
        if (this.mRatioAnimator == null) {
            this.mRatioAnimator = new ValueAnimator();
        }
        this.mRatioAnimator.cancel();
        this.mRatioAnimator.setFloatValues(floatValues);
        this.mRatioAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener(this) {
            final WaveSideBarView this$0;
            
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                this.this$0.mRatio = (float)valueAnimator.getAnimatedValue();
                if (this.this$0.mRatio == 1.0f && this.this$0.oldChoose != this.this$0.newChoose && this.this$0.newChoose >= 0 && this.this$0.newChoose < this.this$0.mLetters.size()) {
                    final WaveSideBarView this$0 = this.this$0;
                    this$0.mChoose = this$0.newChoose;
                    if (this.this$0.listener != null) {
                        this.this$0.listener.onLetterChange((String)this.this$0.mLetters.get(this.this$0.newChoose));
                    }
                }
                this.this$0.invalidate();
            }
        });
        this.mRatioAnimator.start();
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        final float y = motionEvent.getY();
        final float x = motionEvent.getX();
        this.oldChoose = this.mChoose;
        this.newChoose = (int)(y - this.mItemStartY) / this.mItemHeight;
        final int action = motionEvent.getAction();
        if (action == 0) {
            if (x >= this.mWidth - this.mRadius * 2) {
                final int newChoose = this.newChoose;
                if (newChoose >= 0) {
                    if (newChoose <= this.mLetters.size()) {
                        this.mCenterY = (int)y;
                        this.startAnimator(this.mRatio, 1.0f);
                        return true;
                    }
                }
            }
            return false;
        }
        if (action != 1) {
            if (action == 2) {
                this.mCenterY = (int)y;
                final int oldChoose = this.oldChoose;
                final int newChoose2 = this.newChoose;
                if (oldChoose != newChoose2 && newChoose2 >= 0 && newChoose2 < this.mLetters.size()) {
                    final int newChoose3 = this.newChoose;
                    this.mChoose = newChoose3;
                    final OnTouchLetterChangeListener listener = this.listener;
                    if (listener != null) {
                        listener.onLetterChange((String)this.mLetters.get(newChoose3));
                    }
                }
                this.invalidate();
                return true;
            }
            if (action != 3) {
                return true;
            }
        }
        this.startAnimator(this.mRatio, 0.0f);
        this.mChoose = -1;
        return true;
    }
    
    public List<String> getLetters() {
        return this.mLetters;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        this.drawLetters(canvas);
        this.drawWavePath(canvas);
        this.drawBallPath(canvas);
        this.drawChooseText(canvas);
    }
    
    protected void onMeasure(int measuredWidth, final int n) {
        super.onMeasure(measuredWidth, n);
        this.mHeight = View$MeasureSpec.getSize(n);
        measuredWidth = this.getMeasuredWidth();
        this.mWidth = measuredWidth;
        this.mPosX = measuredWidth - this.mTextSize * 1.6f;
        this.mItemStartY = (float)((this.mHeight - this.mLetters.size() * this.mItemHeight) / 2 + WaveSideBarView.Margin);
    }
    
    public void setLetters(final List<String> mLetters) {
        this.mLetters = mLetters;
        this.invalidate();
        this.requestLayout();
    }
    
    public void setOnTouchLetterChangeListener(final OnTouchLetterChangeListener listener) {
        this.listener = listener;
    }
    
    public interface OnTouchLetterChangeListener
    {
        void onLetterChange(final String p0);
    }
}
