package com.kingagroot.kingdraw.widget.loadingview;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.View;
import java.util.Random;
import android.content.res.TypedArray;
import android.widget.ImageView;
import android.content.Context;
import android.view.animation.AlphaAnimation;

public class MAnimationUtils
{
    AlphaAnimation animation;
    Context context;
    long duration;
    ImageView imageView;
    boolean isPlaying;
    int max;
    TypedArray prodImg;
    AnimationState state;
    
    MAnimationUtils(final AnimationState state, final long duration, final Context context) {
        this.isPlaying = true;
        this.duration = duration;
        this.state = state;
        this.context = context;
        final TypedArray obtainTypedArray = context.getResources().obtainTypedArray(2130903040);
        this.prodImg = obtainTypedArray;
        this.max = obtainTypedArray.length() - 1;
    }
    
    private int getNum(final int n) {
        if (n > 0) {
            return new Random().nextInt(n);
        }
        return 0;
    }
    
    public static void showAndHiddenAnimation(final View view, final AnimationState animationState, final long duration) {
        final AnimationState state_SHOW = AnimationState.STATE_SHOW;
        float n = 1.0f;
        float n2 = 0.0f;
        if (animationState == state_SHOW) {
            view.setVisibility(0);
            n = 0.0f;
            n2 = 1.0f;
        }
        else if (animationState == AnimationState.STATE_HIDDEN) {
            view.setVisibility(4);
        }
        else {
            n = 0.0f;
        }
        final AlphaAnimation animation = new AlphaAnimation(n, n2);
        animation.setDuration(duration);
        animation.setFillAfter(true);
        animation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener(view) {
            final View val$view;
            
            public void onAnimationEnd(final Animation animation) {
                this.val$view.clearAnimation();
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        view.setAnimation((Animation)animation);
        animation.start();
    }
    
    public void setPlayingImage(final ImageView imageView) {
        this.imageView = imageView;
        final AnimationState state = this.state;
        final AnimationState state_SHOW = AnimationState.STATE_SHOW;
        float n = 1.0f;
        float n2 = 0.0f;
        if (state == state_SHOW) {
            this.imageView.setVisibility(0);
            n = 0.0f;
            n2 = 1.0f;
        }
        else if (this.state == AnimationState.STATE_HIDDEN) {
            this.imageView.setVisibility(4);
        }
        else {
            n = 0.0f;
        }
        (this.animation = new AlphaAnimation(n, n2)).setDuration(this.duration);
        this.animation.setFillAfter(true);
        this.animation.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener(this) {
            final MAnimationUtils this$0;
            
            public void onAnimationEnd(final Animation animation) {
                if (this.this$0.isPlaying) {
                    if (this.this$0.state == AnimationState.STATE_SHOW) {
                        this.this$0.state = AnimationState.STATE_HIDDEN;
                    }
                    else {
                        final TypedArray prodImg = this.this$0.prodImg;
                        final MAnimationUtils this$0 = this.this$0;
                        this.this$0.imageView.setImageResource(prodImg.getResourceId(this$0.getNum(this$0.max), 0));
                        this.this$0.state = AnimationState.STATE_SHOW;
                    }
                    final MAnimationUtils this$2 = this.this$0;
                    this$2.setPlayingImage(this$2.imageView);
                }
                else {
                    this.this$0.imageView.clearAnimation();
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        this.imageView.setAnimation((Animation)this.animation);
        this.animation.start();
    }
    
    public void stop() {
        this.isPlaying = false;
    }
    
    public enum AnimationState
    {
        private static final AnimationState[] $VALUES;
        
        STATE_HIDDEN, 
        STATE_SHOW;
    }
}
