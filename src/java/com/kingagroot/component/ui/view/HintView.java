package com.kingagroot.component.ui.view;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.widget.TextView;
import android.widget.LinearLayout$LayoutParams;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.util.AttributeSet;
import android.content.Context;
import android.os.CountDownTimer;
import android.animation.AnimatorSet;
import android.widget.LinearLayout;

public class HintView extends LinearLayout
{
    private static final int SHOW_DURATION = 500;
    private AnimatorSet animatorSet;
    private CountDownTimer countDownTimer;
    
    public HintView(final Context context) {
        this(context, null);
    }
    
    public HintView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    private void init() {
        this.countDownTimer = new CountDownTimer(this, 500L, 500L) {
            final HintView this$0;
            
            public void onFinish() {
                this.this$0.dismiss();
            }
            
            public void onTick(final long n) {
            }
        };
        this.animatorSet = new AnimatorSet();
        this.setVisibility(8);
    }
    
    public void dismiss() {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "alpha", new float[] { 1.0f, 0.0f });
        ofFloat.addListener((Animator$AnimatorListener)new Animator$AnimatorListener(this) {
            final HintView this$0;
            
            public void onAnimationCancel(final Animator animator) {
            }
            
            public void onAnimationEnd(final Animator animator) {
                this.this$0.setVisibility(8);
            }
            
            public void onAnimationRepeat(final Animator animator) {
            }
            
            public void onAnimationStart(final Animator animator) {
            }
        });
        this.animatorSet.setDuration(400L);
        this.animatorSet.play((Animator)ofFloat);
        this.animatorSet.start();
    }
    
    public void show(final String text) {
        this.countDownTimer.cancel();
        if (this.animatorSet.isStarted()) {
            this.animatorSet.cancel();
        }
        this.removeAllViews();
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -2);
        final TextView textView = new TextView(this.getContext());
        textView.setBackgroundColor(-4408132);
        textView.setTextColor(-1);
        final int dp2px = GDensityUtil.dp2px(3.0f);
        textView.setPadding(dp2px, 0, dp2px, 0);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.addView((View)textView);
        textView.setText((CharSequence)text);
        this.setAlpha(1.0f);
        this.setVisibility(0);
        this.countDownTimer.start();
    }
}
