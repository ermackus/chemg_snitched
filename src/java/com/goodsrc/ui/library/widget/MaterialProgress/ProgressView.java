package com.goodsrc.ui.library.widget.MaterialProgress;

import androidx.core.content.ContextCompat;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.goodsrc.library.utils.DisplayUtil;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.widget.LinearLayout;

public class ProgressView extends LinearLayout
{
    private static final int MAX_ALPHA = 255;
    private static final int PROGRESS_BG_COLOR = -328966;
    private static final int SCALE_DOWN_DURATION = 10;
    private CircleImageView mCircleView;
    private final int mMargin;
    private MaterialProgressDrawable mProgress;
    private final Animation$AnimationListener mRefreshListener;
    private boolean mRefreshing;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private final int mWidth;
    
    public ProgressView(final Context context) {
        super(context);
        this.mRefreshing = false;
        this.mWidth = 45;
        this.mMargin = 10;
        this.mRefreshListener = (Animation$AnimationListener)new Animation$AnimationListener() {
            final ProgressView this$0;
            
            public void onAnimationEnd(final Animation animation) {
                if (this.this$0.mRefreshing) {
                    this.this$0.mProgress.setAlpha(255);
                    this.this$0.mProgress.start();
                }
                else {
                    this.this$0.reset();
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        };
        this.init(context);
    }
    
    public ProgressView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mRefreshing = false;
        this.mWidth = 45;
        this.mMargin = 10;
        this.mRefreshListener = (Animation$AnimationListener)new Animation$AnimationListener() {
            final ProgressView this$0;
            
            public void onAnimationEnd(final Animation animation) {
                if (this.this$0.mRefreshing) {
                    this.this$0.mProgress.setAlpha(255);
                    this.this$0.mProgress.start();
                }
                else {
                    this.this$0.reset();
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        };
        this.init(context);
    }
    
    public ProgressView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mRefreshing = false;
        this.mWidth = 45;
        this.mMargin = 10;
        this.mRefreshListener = (Animation$AnimationListener)new Animation$AnimationListener() {
            final ProgressView this$0;
            
            public void onAnimationEnd(final Animation animation) {
                if (this.this$0.mRefreshing) {
                    this.this$0.mProgress.setAlpha(255);
                    this.this$0.mProgress.start();
                }
                else {
                    this.this$0.reset();
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        };
        this.init(context);
    }
    
    private void init(final Context context) {
        this.mCircleView = new CircleImageView(context);
        final int dip2px = DisplayUtil.dip2px(context, 45.0f);
        final int dip2px2 = DisplayUtil.dip2px(context, 10.0f);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(dip2px, dip2px);
        layoutParams.setMargins(dip2px2, dip2px2, dip2px2, dip2px2);
        this.mCircleView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        (this.mProgress = new MaterialProgressDrawable(context, (View)this.mCircleView)).setBackgroundColor(-328966);
        this.mCircleView.setImageDrawable((Drawable)this.mProgress);
        this.setColorSchemeResources(17170451, 17170458, 17170456, 17170454);
        this.addView((View)this.mCircleView);
        this.reset();
    }
    
    private boolean isAlphaUsedForScale() {
        return Build$VERSION.SDK_INT < 11;
    }
    
    private void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.setVisibility(8);
        this.setColorViewAlpha(255);
    }
    
    private void setColorViewAlpha(final int n) {
        final Drawable background = this.mCircleView.getBackground();
        if (background != null) {
            background.setAlpha(n);
        }
        this.mProgress.setAlpha(n);
    }
    
    private void startScaleUpAnimation(final Animation$AnimationListener animation$AnimationListener) {
        this.setVisibility(0);
        if (Build$VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(255);
        }
        this.mCircleView.clearAnimation();
        this.setAnimationProgress(1.0f);
        animation$AnimationListener.onAnimationEnd((Animation)null);
    }
    
    public boolean isRefreshing() {
        return this.mRefreshing;
    }
    
    void setAnimationProgress(final float n) {
        if (this.isAlphaUsedForScale()) {
            this.setColorViewAlpha((int)(n * 255.0f));
        }
        else {
            this.mCircleView.setScaleX(n);
            this.mCircleView.setScaleY(n);
        }
    }
    
    public void setColorSchemeResources(final int... array) {
        final Context context = this.getContext();
        final int[] colorSchemeColors = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            colorSchemeColors[i] = ContextCompat.getColor(context, array[i]);
        }
        this.mProgress.setColorSchemeColors(colorSchemeColors);
    }
    
    public void setRefreshing(final boolean mRefreshing) {
        if (this.mRefreshing == mRefreshing) {
            return;
        }
        this.mRefreshing = mRefreshing;
        if (mRefreshing) {
            this.startScaleUpAnimation(this.mRefreshListener);
        }
        else {
            this.startScaleDownAnimation(this.mRefreshListener);
        }
    }
    
    void startScaleDownAnimation(final Animation$AnimationListener animation$AnimationListener) {
        this.mCircleView.clearAnimation();
        this.setAnimationProgress(0.0f);
        this.reset();
    }
}
