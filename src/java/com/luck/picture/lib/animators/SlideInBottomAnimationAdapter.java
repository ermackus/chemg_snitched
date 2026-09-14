package com.luck.picture.lib.animators;

import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class SlideInBottomAnimationAdapter extends BaseAnimationAdapter
{
    public SlideInBottomAnimationAdapter(final RecyclerView$Adapter recyclerView$Adapter) {
        super(recyclerView$Adapter);
    }
    
    protected Animator[] getAnimators(final View view) {
        return new Animator[] { (Animator)ObjectAnimator.ofFloat((Object)view, "translationY", new float[] { (float)view.getMeasuredHeight(), 0.0f }) };
    }
}
