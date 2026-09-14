package com.luck.picture.lib.magical;

import androidx.viewpager2.widget.ViewPager2;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.transition.TransitionManager;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.config.SelectorProviders;
import android.util.AttributeSet;
import android.content.Context;
import com.luck.picture.lib.config.SelectorConfig;
import android.view.View;
import android.widget.FrameLayout;

public class MagicalView extends FrameLayout
{
    private final long animationDuration;
    private final int appInScreenHeight;
    private final View backgroundView;
    private final FrameLayout contentLayout;
    private boolean isAnimating;
    private final boolean isPreviewFullScreenMode;
    private float mAlpha;
    private int mOriginHeight;
    private int mOriginLeft;
    private int mOriginTop;
    private int mOriginWidth;
    private final MagicalViewWrapper magicalWrapper;
    private OnMagicalViewCallback onMagicalViewCallback;
    private int realHeight;
    private int realWidth;
    private int screenHeight;
    private int screenWidth;
    private final SelectorConfig selectorConfig;
    private int startX;
    private int startY;
    private int targetEndLeft;
    private int targetImageHeight;
    private int targetImageTop;
    private int targetImageWidth;
    
    public MagicalView(final Context context) {
        this(context, null);
    }
    
    public MagicalView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public MagicalView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAlpha = 0.0f;
        this.animationDuration = 250L;
        this.isAnimating = false;
        final SelectorConfig selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        this.selectorConfig = selectorConfig;
        this.isPreviewFullScreenMode = selectorConfig.isPreviewFullScreenMode;
        this.appInScreenHeight = DensityUtil.getRealScreenHeight(this.getContext());
        this.getScreenSize();
        (this.backgroundView = new View(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.backgroundView.setAlpha(this.mAlpha);
        this.addView(this.backgroundView);
        (this.contentLayout = new FrameLayout(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.addView((View)this.contentLayout);
        this.magicalWrapper = new MagicalViewWrapper((View)this.contentLayout);
    }
    
    private void backToMinWithTransition() {
        this.contentLayout.post((Runnable)new Runnable(this) {
            final MagicalView this$0;
            
            public void run() {
                TransitionManager.beginDelayedTransition((ViewGroup)this.this$0.contentLayout.getParent(), (Transition)new TransitionSet().setDuration(250L).addTransition((Transition)new ChangeBounds()).addTransition((Transition)new ChangeTransform()).addTransition((Transition)new ChangeImageTransform()));
                this.this$0.beginBackToMin(true);
                this.this$0.contentLayout.setTranslationX(0.0f);
                this.this$0.contentLayout.setTranslationY(0.0f);
                this.this$0.magicalWrapper.setWidth((float)this.this$0.mOriginWidth);
                this.this$0.magicalWrapper.setHeight((float)this.this$0.mOriginHeight);
                this.this$0.magicalWrapper.setMarginTop(this.this$0.mOriginTop);
                this.this$0.magicalWrapper.setMarginLeft(this.this$0.mOriginLeft);
                this.this$0.changeBackgroundViewAlpha(true);
            }
        });
    }
    
    private void backToMinWithoutView() {
        this.contentLayout.animate().alpha(0.0f).setDuration(250L).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter(this) {
            final MagicalView this$0;
            
            public void onAnimationEnd(final Animator animator) {
                if (this.this$0.onMagicalViewCallback != null) {
                    this.this$0.onMagicalViewCallback.onMagicalViewFinish();
                }
            }
        }).start();
        this.backgroundView.animate().alpha(0.0f).setDuration(250L).start();
    }
    
    private void beginBackToMin(final boolean b) {
        if (b) {
            this.onMagicalViewCallback.onBeginBackMinMagicalFinish(true);
        }
    }
    
    private void beginShow(final boolean b) {
        if (b) {
            this.mAlpha = 1.0f;
            this.backgroundView.setAlpha(1.0f);
            this.showNormalMin((float)this.targetImageTop, (float)this.targetEndLeft, (float)this.targetImageWidth, (float)this.targetImageHeight);
            this.setShowEndParams();
        }
        else {
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { 0.0f, 1.0f });
            ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener(this) {
                final MagicalView this$0;
                
                public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                    final float floatValue = (float)valueAnimator.getAnimatedValue();
                    final MagicalView this$0 = this.this$0;
                    this$0.showNormalMin(floatValue, (float)this$0.mOriginTop, (float)this.this$0.targetImageTop, (float)this.this$0.mOriginLeft, (float)this.this$0.targetEndLeft, (float)this.this$0.mOriginWidth, (float)this.this$0.targetImageWidth, (float)this.this$0.mOriginHeight, (float)this.this$0.targetImageHeight);
                }
            });
            ofFloat.addListener((Animator$AnimatorListener)new AnimatorListenerAdapter(this) {
                final MagicalView this$0;
                
                public void onAnimationEnd(final Animator animator) {
                    this.this$0.setShowEndParams();
                }
            });
            if (this.selectorConfig.interpolatorFactory != null) {
                final Interpolator interpolator = this.selectorConfig.interpolatorFactory.newInterpolator();
                if (interpolator != null) {
                    ofFloat.setInterpolator((TimeInterpolator)interpolator);
                }
            }
            ofFloat.setDuration(250L).start();
            this.changeBackgroundViewAlpha(false);
        }
    }
    
    private void changeBackgroundViewAlpha(final boolean b) {
        float n;
        if (b) {
            n = 0.0f;
        }
        else {
            n = 1.0f;
        }
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[] { this.mAlpha, n });
        ofFloat.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener(this) {
            final MagicalView this$0;
            
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                this.this$0.isAnimating = true;
                this.this$0.mAlpha = (float)valueAnimator.getAnimatedValue();
                this.this$0.backgroundView.setAlpha(this.this$0.mAlpha);
                if (this.this$0.onMagicalViewCallback != null) {
                    this.this$0.onMagicalViewCallback.onBackgroundAlpha(this.this$0.mAlpha);
                }
            }
        });
        ofFloat.addListener((Animator$AnimatorListener)new AnimatorListenerAdapter(this, b) {
            final MagicalView this$0;
            final boolean val$isAlpha;
            
            public void onAnimationEnd(final Animator animator) {
                this.this$0.isAnimating = false;
                if (this.val$isAlpha && this.this$0.onMagicalViewCallback != null) {
                    this.this$0.onMagicalViewCallback.onMagicalViewFinish();
                }
            }
        });
        ofFloat.setDuration(250L);
        ofFloat.start();
    }
    
    private void changeContentViewToFullscreen() {
        final int screenHeight = this.screenHeight;
        this.targetImageHeight = screenHeight;
        this.targetImageWidth = this.screenWidth;
        this.targetImageTop = 0;
        this.magicalWrapper.setHeight((float)screenHeight);
        this.magicalWrapper.setWidth((float)this.screenWidth);
        this.magicalWrapper.setMarginTop(0);
        this.magicalWrapper.setMarginLeft(0);
    }
    
    private void getScreenSize() {
        this.screenWidth = DensityUtil.getRealScreenWidth(this.getContext());
        if (this.isPreviewFullScreenMode) {
            this.screenHeight = DensityUtil.getRealScreenHeight(this.getContext());
        }
        else {
            this.screenHeight = DensityUtil.getScreenHeight(this.getContext());
        }
    }
    
    private void setOriginParams() {
        this.contentLayout.getLocationOnScreen(new int[2]);
        this.targetEndLeft = 0;
        final int screenWidth = this.screenWidth;
        final float n = (float)screenWidth;
        final int screenHeight = this.screenHeight;
        final float n2 = n / screenHeight;
        final int realWidth = this.realWidth;
        final float n3 = (float)realWidth;
        final int realHeight = this.realHeight;
        if (n2 < n3 / realHeight) {
            this.targetImageWidth = screenWidth;
            final int targetImageHeight = (int)(screenWidth * (realHeight / (float)realWidth));
            this.targetImageHeight = targetImageHeight;
            this.targetImageTop = (screenHeight - targetImageHeight) / 2;
        }
        else {
            this.targetImageHeight = screenHeight;
            final int targetImageWidth = (int)(screenHeight * (realWidth / (float)realHeight));
            this.targetImageWidth = targetImageWidth;
            this.targetImageTop = 0;
            this.targetEndLeft = (screenWidth - targetImageWidth) / 2;
        }
        this.magicalWrapper.setWidth((float)this.mOriginWidth);
        this.magicalWrapper.setHeight((float)this.mOriginHeight);
        this.magicalWrapper.setMarginLeft(this.mOriginLeft);
        this.magicalWrapper.setMarginTop(this.mOriginTop);
    }
    
    private void setShowEndParams() {
        this.isAnimating = false;
        this.changeContentViewToFullscreen();
        final OnMagicalViewCallback onMagicalViewCallback = this.onMagicalViewCallback;
        if (onMagicalViewCallback != null) {
            onMagicalViewCallback.onBeginMagicalAnimComplete(this, false);
        }
    }
    
    private void showNormalMin(final float n, final float n2, final float n3, final float n4) {
        this.showNormalMin(true, 0.0f, 0.0f, n, 0.0f, n2, 0.0f, n3, 0.0f, n4);
    }
    
    private void showNormalMin(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.showNormalMin(false, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    private void showNormalMin(final boolean b, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float width, final float n7, final float height) {
        if (b) {
            this.magicalWrapper.setWidth(width);
            this.magicalWrapper.setHeight(height);
            this.magicalWrapper.setMarginLeft((int)n5);
            this.magicalWrapper.setMarginTop((int)n3);
        }
        else {
            this.magicalWrapper.setWidth(n6 + (width - n6) * n);
            this.magicalWrapper.setHeight(n7 + (height - n7) * n);
            this.magicalWrapper.setMarginLeft((int)(n4 + (n5 - n4) * n));
            this.magicalWrapper.setMarginTop((int)(n2 + n * (n3 - n2)));
        }
    }
    
    public void backToMin() {
        if (this.isAnimating) {
            return;
        }
        if (this.mOriginWidth != 0 && this.mOriginHeight != 0) {
            final OnMagicalViewCallback onMagicalViewCallback = this.onMagicalViewCallback;
            if (onMagicalViewCallback != null) {
                onMagicalViewCallback.onBeginBackMinAnim();
            }
            this.beginBackToMin(false);
            this.backToMinWithTransition();
            return;
        }
        this.backToMinWithoutView();
    }
    
    public void changeRealScreenHeight(final int n, final int n2, final boolean b) {
        if (!this.isPreviewFullScreenMode) {
            final int screenWidth = this.screenWidth;
            final int screenHeight = this.screenHeight;
            if (screenWidth <= screenHeight) {
                if ((int)(screenWidth / (n / (float)n2)) > screenHeight) {
                    this.screenHeight = this.appInScreenHeight;
                    if (b) {
                        this.magicalWrapper.setWidth((float)screenWidth);
                        this.magicalWrapper.setHeight((float)this.screenHeight);
                    }
                }
            }
        }
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        final View child = this.contentLayout.getChildAt(0);
        ViewPager2 viewPager2;
        if (child instanceof ViewPager2) {
            viewPager2 = (ViewPager2)child;
        }
        else {
            viewPager2 = null;
        }
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return super.dispatchTouchEvent(motionEvent);
                    }
                }
                else {
                    final int n = (int)motionEvent.getX();
                    final int n2 = (int)motionEvent.getY();
                    if (Math.abs(n - this.startX) > Math.abs(n2 - this.startY)) {
                        if (viewPager2 != null) {
                            viewPager2.setUserInputEnabled(true);
                            return super.dispatchTouchEvent(motionEvent);
                        }
                        return super.dispatchTouchEvent(motionEvent);
                    }
                    else {
                        if (viewPager2 != null) {
                            viewPager2.setUserInputEnabled(this.canScrollVertically(this.startY - n2));
                            return super.dispatchTouchEvent(motionEvent);
                        }
                        return super.dispatchTouchEvent(motionEvent);
                    }
                }
            }
            if (viewPager2 != null) {
                viewPager2.setUserInputEnabled(true);
            }
        }
        else {
            this.startX = (int)motionEvent.getX();
            this.startY = (int)motionEvent.getY();
            if (viewPager2 != null) {
                viewPager2.setUserInputEnabled(true);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public void resetStart() {
        this.getScreenSize();
        this.start(true);
    }
    
    public void resetStartNormal(final int n, final int n2, final boolean b) {
        this.getScreenSize();
        this.startNormal(n, n2, b);
    }
    
    public void setBackgroundAlpha(final float n) {
        this.mAlpha = n;
        this.backgroundView.setAlpha(n);
    }
    
    public void setBackgroundColor(final int backgroundColor) {
        this.backgroundView.setBackgroundColor(backgroundColor);
    }
    
    public void setMagicalContent(final View view) {
        this.contentLayout.addView(view);
    }
    
    public void setOnMojitoViewCallback(final OnMagicalViewCallback onMagicalViewCallback) {
        this.onMagicalViewCallback = onMagicalViewCallback;
    }
    
    public void setViewParams(final int mOriginLeft, final int mOriginTop, final int mOriginWidth, final int mOriginHeight, final int realWidth, final int realHeight) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.mOriginLeft = mOriginLeft;
        this.mOriginTop = mOriginTop;
        this.mOriginWidth = mOriginWidth;
        this.mOriginHeight = mOriginHeight;
    }
    
    public void start(final boolean b) {
        float n;
        if (b) {
            n = 1.0f;
            this.mAlpha = 1.0f;
        }
        else {
            n = 0.0f;
        }
        this.mAlpha = n;
        this.backgroundView.setAlpha(n);
        this.setVisibility(0);
        this.setOriginParams();
        this.beginShow(b);
    }
    
    public void startNormal(final int realWidth, final int realHeight, final boolean b) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.mOriginLeft = 0;
        this.mOriginTop = 0;
        this.mOriginWidth = 0;
        this.setVisibility(this.mOriginHeight = 0);
        this.setOriginParams();
        this.showNormalMin((float)this.targetImageTop, (float)this.targetEndLeft, (float)this.targetImageWidth, (float)this.targetImageHeight);
        if (b) {
            this.mAlpha = 1.0f;
            this.backgroundView.setAlpha(1.0f);
        }
        else {
            this.mAlpha = 0.0f;
            this.backgroundView.setAlpha(0.0f);
            this.contentLayout.setAlpha(0.0f);
            this.contentLayout.animate().alpha(1.0f).setDuration(250L).start();
            this.backgroundView.animate().alpha(1.0f).setDuration(250L).start();
        }
        this.setShowEndParams();
    }
}
