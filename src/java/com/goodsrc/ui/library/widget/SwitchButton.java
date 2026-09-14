package com.goodsrc.ui.library.widget;

import android.widget.CompoundButton;
import android.view.MotionEvent;
import android.graphics.Xfermode;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.graphics.PorterDuff$Mode;
import android.graphics.BitmapFactory;
import com.goodsrc.ui.library.R;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.ViewParent;
import android.graphics.Paint;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.graphics.Bitmap;
import androidx.appcompat.widget.AppCompatCheckBox;

public class SwitchButton extends AppCompatCheckBox
{
    private final float EXTENDED_OFFSET_Y;
    private final int MAX_ALPHA;
    private final float VELOCITY;
    private int mAlpha;
    private float mAnimatedVelocity;
    private boolean mAnimating;
    private float mAnimationPosition;
    private Bitmap mBottom;
    private boolean mBroadcasting;
    private float mBtnInitPos;
    private Bitmap mBtnNormal;
    private float mBtnOffPos;
    private float mBtnOnPos;
    private float mBtnPos;
    private Bitmap mBtnPressed;
    private float mBtnWidth;
    private boolean mChecked;
    private int mClickTimeout;
    private Bitmap mCurBtnPic;
    private float mExtendOffsetY;
    private float mFirstDownX;
    private float mFirstDownY;
    private Bitmap mFrame;
    private Bitmap mMask;
    private float mMaskHeight;
    private float mMaskWidth;
    private CompoundButton$OnCheckedChangeListener mOnCheckedChangeListener;
    private CompoundButton$OnCheckedChangeListener mOnCheckedChangeWidgetListener;
    private Paint mPaint;
    private ViewParent mParent;
    private PerformClick mPerformClick;
    private float mRealPos;
    private RectF mSaveLayerRectF;
    private int mTouchSlop;
    private boolean mTurningOn;
    private float mVelocity;
    private PorterDuffXfermode mXfermode;
    
    public SwitchButton(final Context context) {
        this(context, null);
    }
    
    public SwitchButton(final Context context, final AttributeSet set) {
        this(context, set, 16842860);
    }
    
    public SwitchButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.MAX_ALPHA = 255;
        this.mAlpha = 255;
        this.mChecked = false;
        this.VELOCITY = 350.0f;
        this.EXTENDED_OFFSET_Y = 15.0f;
        this.initView(context);
    }
    
    private void attemptClaimDrag() {
        final ViewParent parent = this.getParent();
        this.mParent = parent;
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
    
    private void doAnimation() {
        final float mAnimationPosition = this.mAnimationPosition + this.mAnimatedVelocity * 16.0f / 1000.0f;
        this.mAnimationPosition = mAnimationPosition;
        if (mAnimationPosition <= this.mBtnOnPos) {
            this.stopAnimation();
            this.mAnimationPosition = this.mBtnOnPos;
            this.setCheckedDelayed(true);
        }
        else if (mAnimationPosition >= this.mBtnOffPos) {
            this.stopAnimation();
            this.mAnimationPosition = this.mBtnOffPos;
            this.setCheckedDelayed(false);
        }
        this.moveView(this.mAnimationPosition);
    }
    
    private float getRealPos(final float n) {
        return n - this.mBtnWidth / 2.0f;
    }
    
    private void initView(final Context context) {
        (this.mPaint = new Paint()).setColor(-1);
        final Resources resources = context.getResources();
        this.mClickTimeout = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mBottom = BitmapFactory.decodeResource(resources, R.drawable.bottom);
        this.mBtnPressed = BitmapFactory.decodeResource(resources, R.drawable.btn_pressed);
        this.mBtnNormal = BitmapFactory.decodeResource(resources, R.drawable.btn_unpressed);
        this.mFrame = BitmapFactory.decodeResource(resources, R.drawable.frame);
        this.mMask = BitmapFactory.decodeResource(resources, R.drawable.mask);
        this.mCurBtnPic = this.mBtnNormal;
        this.mBtnWidth = (float)this.mBtnPressed.getWidth();
        this.mMaskWidth = (float)this.mMask.getWidth();
        this.mMaskHeight = (float)this.mMask.getHeight();
        final float mBtnWidth = this.mBtnWidth;
        float n = mBtnWidth / 2.0f;
        this.mBtnOffPos = n;
        final float mBtnOnPos = this.mMaskWidth - mBtnWidth / 2.0f;
        this.mBtnOnPos = mBtnOnPos;
        if (this.mChecked) {
            n = mBtnOnPos;
        }
        this.mBtnPos = n;
        this.mRealPos = this.getRealPos(n);
        final float density = this.getResources().getDisplayMetrics().density;
        this.mVelocity = (float)(int)(350.0f * density + 0.5f);
        this.mExtendOffsetY = (float)(int)(density * 15.0f + 0.5f);
        this.mSaveLayerRectF = new RectF(0.0f, this.mExtendOffsetY, (float)this.mMask.getWidth(), this.mMask.getHeight() + this.mExtendOffsetY);
        this.mXfermode = new PorterDuffXfermode(PorterDuff$Mode.SRC_IN);
    }
    
    private void moveView(final float mBtnPos) {
        this.mBtnPos = mBtnPos;
        this.mRealPos = this.getRealPos(mBtnPos);
        this.invalidate();
    }
    
    private void setCheckedDelayed(final boolean b) {
        this.postDelayed((Runnable)new Runnable(this, b) {
            final SwitchButton this$0;
            final boolean val$checked;
            
            public void run() {
                this.this$0.setChecked(this.val$checked);
            }
        }, 10L);
    }
    
    private void startAnimation(final boolean b) {
        this.mAnimating = true;
        float mVelocity;
        if (b) {
            mVelocity = -this.mVelocity;
        }
        else {
            mVelocity = this.mVelocity;
        }
        this.mAnimatedVelocity = mVelocity;
        this.mAnimationPosition = this.mBtnPos;
        new SwitchAnimation().run();
    }
    
    private void stopAnimation() {
        this.mAnimating = false;
    }
    
    public boolean isChecked() {
        return this.mChecked;
    }
    
    protected void onDraw(final Canvas canvas) {
        canvas.saveLayerAlpha(this.mSaveLayerRectF, this.mAlpha, 31);
        canvas.drawBitmap(this.mMask, 0.0f, this.mExtendOffsetY, this.mPaint);
        this.mPaint.setXfermode((Xfermode)this.mXfermode);
        canvas.drawBitmap(this.mBottom, this.mRealPos, this.mExtendOffsetY, this.mPaint);
        this.mPaint.setXfermode((Xfermode)null);
        canvas.drawBitmap(this.mFrame, 0.0f, this.mExtendOffsetY, this.mPaint);
        canvas.drawBitmap(this.mCurBtnPic, this.mRealPos, this.mExtendOffsetY, this.mPaint);
        canvas.restore();
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension((int)this.mMaskWidth, (int)(this.mMaskHeight + this.mExtendOffsetY * 2.0f));
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        final float abs = Math.abs(x - this.mFirstDownX);
        final float abs2 = Math.abs(y - this.mFirstDownY);
        if (action != 0) {
            boolean mTurningOn = true;
            if (action != 1) {
                if (action == 2) {
                    motionEvent.getEventTime();
                    motionEvent.getDownTime();
                    final float mBtnPos = this.mBtnInitPos + motionEvent.getX() - this.mFirstDownX;
                    this.mBtnPos = mBtnPos;
                    final float mBtnOffPos = this.mBtnOffPos;
                    if (mBtnPos >= mBtnOffPos) {
                        this.mBtnPos = mBtnOffPos;
                    }
                    final float mBtnPos2 = this.mBtnPos;
                    final float mBtnOnPos = this.mBtnOnPos;
                    if (mBtnPos2 <= mBtnOnPos) {
                        this.mBtnPos = mBtnOnPos;
                    }
                    final float mBtnPos3 = this.mBtnPos;
                    final float mBtnOffPos2 = this.mBtnOffPos;
                    final float mBtnOnPos2 = this.mBtnOnPos;
                    if (mBtnPos3 <= (mBtnOffPos2 - mBtnOnPos2) / 2.0f + mBtnOnPos2) {
                        mTurningOn = false;
                    }
                    this.mTurningOn = mTurningOn;
                    this.mRealPos = this.getRealPos(this.mBtnPos);
                }
            }
            else {
                this.mCurBtnPic = this.mBtnNormal;
                final float n = (float)(motionEvent.getEventTime() - motionEvent.getDownTime());
                final int mTouchSlop = this.mTouchSlop;
                if (abs2 < mTouchSlop && abs < mTouchSlop && n < this.mClickTimeout) {
                    if (this.mPerformClick == null) {
                        this.mPerformClick = new PerformClick();
                    }
                    if (!this.post((Runnable)this.mPerformClick)) {
                        this.performClick();
                    }
                }
                else {
                    this.startAnimation(this.mTurningOn ^ true);
                }
            }
        }
        else {
            this.attemptClaimDrag();
            this.mFirstDownX = x;
            this.mFirstDownY = y;
            this.mCurBtnPic = this.mBtnPressed;
            float mBtnInitPos;
            if (this.mChecked) {
                mBtnInitPos = this.mBtnOnPos;
            }
            else {
                mBtnInitPos = this.mBtnOffPos;
            }
            this.mBtnInitPos = mBtnInitPos;
        }
        this.invalidate();
        return this.isEnabled();
    }
    
    public boolean performClick() {
        this.startAnimation(this.mChecked ^ true);
        return true;
    }
    
    public void setChecked(final boolean mChecked) {
        if (this.mChecked != mChecked) {
            this.mChecked = mChecked;
            float mBtnPos;
            if (mChecked) {
                mBtnPos = this.mBtnOnPos;
            }
            else {
                mBtnPos = this.mBtnOffPos;
            }
            this.mBtnPos = mBtnPos;
            this.mRealPos = this.getRealPos(mBtnPos);
            this.invalidate();
            if (this.mBroadcasting) {
                return;
            }
            this.mBroadcasting = true;
            final CompoundButton$OnCheckedChangeListener mOnCheckedChangeListener = this.mOnCheckedChangeListener;
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged((CompoundButton)this, this.mChecked);
            }
            final CompoundButton$OnCheckedChangeListener mOnCheckedChangeWidgetListener = this.mOnCheckedChangeWidgetListener;
            if (mOnCheckedChangeWidgetListener != null) {
                mOnCheckedChangeWidgetListener.onCheckedChanged((CompoundButton)this, this.mChecked);
            }
            this.mBroadcasting = false;
        }
    }
    
    public void setEnabled(final boolean enabled) {
        int mAlpha;
        if (enabled) {
            mAlpha = 255;
        }
        else {
            mAlpha = 127;
        }
        this.mAlpha = mAlpha;
        super.setEnabled(enabled);
    }
    
    public void setOnCheckedChangeListener(final CompoundButton$OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }
    
    void setOnCheckedChangeWidgetListener(final CompoundButton$OnCheckedChangeListener mOnCheckedChangeWidgetListener) {
        this.mOnCheckedChangeWidgetListener = mOnCheckedChangeWidgetListener;
    }
    
    public void toggle() {
        this.setChecked(this.mChecked ^ true);
    }
    
    private final class PerformClick implements Runnable
    {
        final SwitchButton this$0;
        
        private PerformClick(final SwitchButton this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            this.this$0.performClick();
        }
    }
    
    private final class SwitchAnimation implements Runnable
    {
        final SwitchButton this$0;
        
        private SwitchAnimation(final SwitchButton this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            if (!this.this$0.mAnimating) {
                return;
            }
            this.this$0.doAnimation();
            FrameAnimationController.requestAnimationFrame((Runnable)this);
        }
    }
}
