package com.kingagroot.kingdraw.widget;

import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.widget.AppCompatCheckBox;

public class CenterCheckBox extends AppCompatCheckBox
{
    private static final String TAG;
    
    static {
        TAG = CenterCheckBox.class.getSimpleName();
    }
    
    public CenterCheckBox(final Context context) {
        super(context);
    }
    
    public CenterCheckBox(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public CenterCheckBox(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Drawable drawable = this.getCompoundDrawables()[0];
        int n;
        if (this.getGravity() == 17) {
            n = (int)(this.getWidth() - drawable.getIntrinsicWidth() - this.getPaint().measureText(this.getText().toString())) / 2;
        }
        else {
            n = 0;
        }
        drawable.setBounds(n, 0, drawable.getIntrinsicWidth() + n, drawable.getIntrinsicHeight());
    }
}
