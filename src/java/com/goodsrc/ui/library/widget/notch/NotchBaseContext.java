package com.goodsrc.ui.library.widget.notch;

public interface NotchBaseContext
{
    void checkNotchInScreen(final NotchCallBack p0);
    
    int[] getNotchSize();
    
    boolean isNotchScreen();
    
    void setFullScreenWindowLayoutInDisplayCutout();
    
    void setNotFullScreenWindowLayoutInDisplayCutout();
}
