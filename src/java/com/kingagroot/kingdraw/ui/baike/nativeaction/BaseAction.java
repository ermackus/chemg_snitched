package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public abstract class BaseAction
{
    protected Context context;
    
    public BaseAction(final Context context) {
        this.context = context;
    }
    
    public abstract void action(final String p0, final CallBackFunction p1);
}
