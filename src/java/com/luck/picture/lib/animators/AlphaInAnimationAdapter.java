package com.luck.picture.lib.animators;

import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class AlphaInAnimationAdapter extends BaseAnimationAdapter
{
    private static final float DEFAULT_ALPHA_FROM = 0.0f;
    private final float mFrom;
    
    public AlphaInAnimationAdapter(final RecyclerView$Adapter recyclerView$Adapter) {
        this(recyclerView$Adapter, 0.0f);
    }
    
    public AlphaInAnimationAdapter(final RecyclerView$Adapter recyclerView$Adapter, final float mFrom) {
        super(recyclerView$Adapter);
        this.mFrom = mFrom;
    }
    
    protected Animator[] getAnimators(final View view) {
        return new Animator[] { (Animator)ObjectAnimator.ofFloat((Object)view, "alpha", new float[] { this.mFrom, 1.0f }) };
    }
}
