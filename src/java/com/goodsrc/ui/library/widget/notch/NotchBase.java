package com.goodsrc.ui.library.widget.notch;

import android.app.Activity;
import android.view.Window;
import android.content.Context;

public abstract class NotchBase
{
    protected Context context;
    protected Window window;
    
    public NotchBase(final Activity context) {
        this.context = (Context)context;
        this.window = context.getWindow();
    }
    
    public abstract void checkNotchInScreen(final NotchCallBack p0);
    
    public abstract int[] getNotchSize();
    
    public abstract boolean isNotchScreen();
    
    public abstract void setFullScreenWindowLayoutInDisplayCutout();
    
    public abstract void setNotFullScreenWindowLayoutInDisplayCutout();
}
