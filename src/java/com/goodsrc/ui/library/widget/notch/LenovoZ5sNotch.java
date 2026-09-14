package com.goodsrc.ui.library.widget.notch;

import android.view.View;
import android.app.Activity;

public class LenovoZ5sNotch extends LenovoZ5Notch
{
    public LenovoZ5sNotch(final Activity activity) {
        super(activity);
    }
    
    public LenovoZ5sNotch(final Activity activity, final View view) {
        super(activity, view);
    }
    
    @Override
    protected int getScreenHeight() {
        return 2340;
    }
}
