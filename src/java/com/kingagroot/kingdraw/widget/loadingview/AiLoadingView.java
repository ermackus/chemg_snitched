package com.kingagroot.kingdraw.widget.loadingview;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.ViewGroup;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public class AiLoadingView extends RelativeLayout
{
    Animation bigAnim;
    private ImageView ivAnimBig;
    private ImageView ivAnimElement;
    private ImageView ivAnimSmall;
    MAnimationUtils mAnimationUtils;
    Animation smallAnim;
    
    public AiLoadingView(final Context context) {
        this(context, null);
    }
    
    public AiLoadingView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(context);
    }
    
    private void init(final Context context) {
        this.bigAnim = AnimationUtils.loadAnimation(this.getContext(), 2130772033);
        this.smallAnim = AnimationUtils.loadAnimation(this.getContext(), 2130772034);
        final View inflate = View.inflate(context, 2131493095, (ViewGroup)this);
        this.ivAnimBig = (ImageView)inflate.findViewById(2131296912);
        this.ivAnimElement = (ImageView)inflate.findViewById(2131296913);
        this.ivAnimSmall = (ImageView)inflate.findViewById(2131296914);
    }
    
    public void playAnimotion() {
        this.bigAnim.setInterpolator((Interpolator)new LinearInterpolator());
        final Animation bigAnim = this.bigAnim;
        if (bigAnim != null) {
            this.ivAnimBig.startAnimation(bigAnim);
            this.ivAnimSmall.startAnimation(this.smallAnim);
        }
        else {
            this.ivAnimBig.setAnimation(bigAnim);
            this.ivAnimBig.startAnimation(this.bigAnim);
            this.ivAnimSmall.setAnimation(this.smallAnim);
            this.ivAnimSmall.startAnimation(this.smallAnim);
        }
        (this.mAnimationUtils = new MAnimationUtils(MAnimationUtils.AnimationState.STATE_SHOW, 1000L, this.getContext())).setPlayingImage(this.ivAnimElement);
    }
    
    public void stop() {
        this.mAnimationUtils.stop();
        this.bigAnim.cancel();
        this.smallAnim.cancel();
        this.ivAnimBig.clearAnimation();
        this.ivAnimSmall.clearAnimation();
    }
}
