package com.goodsrc.ui.library.widget.notch;

import android.app.Activity;

public class CommonNotch extends NotchBase
{
    public CommonNotch(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        if (notchCallBack != null) {
            notchCallBack.onResult(this.isNotchScreen());
        }
    }
    
    @Override
    public int[] getNotchSize() {
        return new int[2];
    }
    
    @Override
    public boolean isNotchScreen() {
        return false;
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
    }
}
