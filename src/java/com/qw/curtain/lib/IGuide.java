package com.qw.curtain.lib;

import android.view.View;

public interface IGuide
{
    void dismissGuide();
    
     <T extends View> T findViewByIdInTopView(final int p0);
    
    void updateHollows(final HollowInfo... p0);
    
    void updateTopView(final int p0);
}
