package com.luck.picture.lib.style;

import com.luck.picture.lib.R;

public class PictureWindowAnimationStyle
{
    public int activityEnterAnimation;
    public int activityExitAnimation;
    public int activityPreviewEnterAnimation;
    public int activityPreviewExitAnimation;
    
    public PictureWindowAnimationStyle() {
    }
    
    public PictureWindowAnimationStyle(final int n, final int n2) {
        this.activityEnterAnimation = n;
        this.activityExitAnimation = n2;
        this.activityPreviewEnterAnimation = n;
        this.activityPreviewExitAnimation = n2;
    }
    
    public static PictureWindowAnimationStyle ofDefaultWindowAnimationStyle() {
        return new PictureWindowAnimationStyle(R.anim.ps_anim_enter, R.anim.ps_anim_exit);
    }
    
    public int getActivityEnterAnimation() {
        return this.activityEnterAnimation;
    }
    
    public int getActivityExitAnimation() {
        return this.activityExitAnimation;
    }
    
    public int getActivityPreviewEnterAnimation() {
        return this.activityPreviewEnterAnimation;
    }
    
    public int getActivityPreviewExitAnimation() {
        return this.activityPreviewExitAnimation;
    }
    
    public void setActivityEnterAnimation(final int activityEnterAnimation) {
        this.activityEnterAnimation = activityEnterAnimation;
    }
    
    public void setActivityExitAnimation(final int activityExitAnimation) {
        this.activityExitAnimation = activityExitAnimation;
    }
    
    public void setActivityPreviewEnterAnimation(final int activityPreviewEnterAnimation) {
        this.activityPreviewEnterAnimation = activityPreviewEnterAnimation;
    }
    
    public void setActivityPreviewExitAnimation(final int activityPreviewExitAnimation) {
        this.activityPreviewExitAnimation = activityPreviewExitAnimation;
    }
}
