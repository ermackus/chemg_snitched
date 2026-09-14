package com.luck.picture.lib.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

public class AnimUtils
{
    public static final int DURATION = 250;
    
    public static void rotateArrow(final ImageView imageView, final boolean b) {
        float n = 0.0f;
        float n2 = 180.0f;
        if (!b) {
            n = 180.0f;
            n2 = 0.0f;
        }
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)imageView, "rotation", new float[] { n, n2 });
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat.start();
    }
    
    public static void selectZoom(final View view) {
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(new Animator[] { (Animator)ObjectAnimator.ofFloat((Object)view, "scaleX", new float[] { 1.0f, 1.05f, 1.0f }), (Animator)ObjectAnimator.ofFloat((Object)view, "scaleY", new float[] { 1.0f, 1.05f, 1.0f }) });
        set.setDuration(250L);
        set.setInterpolator((TimeInterpolator)new LinearInterpolator());
        set.start();
    }
}
