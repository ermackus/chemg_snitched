package com.luck.picture.lib.widget;

import android.graphics.Rect;
import android.util.AttributeSet;
import android.content.Context;

public class MarqueeTextView extends MediumBoldTextView
{
    public MarqueeTextView(final Context context) {
        super(context);
    }
    
    public MarqueeTextView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public boolean isFocused() {
        return true;
    }
    
    public boolean isSelected() {
        return true;
    }
    
    protected void onFocusChanged(final boolean b, final int n, final Rect rect) {
        if (b) {
            super.onFocusChanged(true, n, rect);
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (b) {
            super.onWindowFocusChanged(true);
        }
    }
}
