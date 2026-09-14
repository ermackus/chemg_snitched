package com.luck.picture.lib.magical;

public interface OnMagicalViewCallback
{
    void onBackgroundAlpha(final float p0);
    
    void onBeginBackMinAnim();
    
    void onBeginBackMinMagicalFinish(final boolean p0);
    
    void onBeginMagicalAnimComplete(final MagicalView p0, final boolean p1);
    
    void onMagicalViewFinish();
}
