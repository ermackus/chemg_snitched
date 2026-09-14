package com.kingagroot.component.ui.view;

import android.graphics.Canvas;

public interface GCompoundButton
{
    void drawableCorner(final Canvas p0);
    
    boolean hasMore();
    
    void setButtonDrawable(final int p0);
    
    void setButtonDrawableColor(final int p0);
    
    void setClickEnable(final boolean p0);
    
    void setCornerGravity(final int p0);
    
    void setCornerWidth(final int p0);
    
    void setVisibleCorner(final boolean p0);
}
