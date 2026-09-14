package com.kingagroot.kingdraw.ui;

import android.view.View$MeasureSpec;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import androidx.appcompat.widget.SwitchCompat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class MoreOperationPop extends PopupWindow
{
    private final View contentView;
    private OnViewStateChange onViewStateChange;
    
    public MoreOperationPop(final Context context) {
        super(context);
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        View inflate = null;
        if (layoutInflater != null) {
            inflate = layoutInflater.inflate(2131493096, (ViewGroup)null);
        }
        this.setContentView(this.contentView = inflate);
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView();
    }
    
    private void initView() {
        final SwitchCompat switchCompat = (SwitchCompat)this.contentView.findViewById(2131297446);
        final SwitchCompat switchCompat2 = (SwitchCompat)this.contentView.findViewById(2131297445);
        switchCompat.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new _$$Lambda$MoreOperationPop$7KNy6vMgL2pTcKPlz_8WpALYsYU(this));
        switchCompat2.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new _$$Lambda$MoreOperationPop$QHK6QnnTpEx8CgIhhpzByGB_Ah0(this));
    }
    
    public void dismiss() {
        super.dismiss();
    }
    
    public void setOnViewStateListener(final OnViewStateChange onViewStateChange) {
        this.onViewStateChange = onViewStateChange;
    }
    
    public void show(final View view) {
        final int[] array = new int[2];
        view.getLocationInWindow(array);
        this.contentView.measure(View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE));
        this.showAtLocation(view, 0, array[0] - (this.contentView.getMeasuredWidth() - view.getMeasuredWidth()), array[1] - (this.contentView.getMeasuredHeight() + 20));
    }
    
    public interface OnViewStateChange
    {
        void rotateChangeListener(final boolean p0);
        
        void showChangeListener(final boolean p0);
    }
}
