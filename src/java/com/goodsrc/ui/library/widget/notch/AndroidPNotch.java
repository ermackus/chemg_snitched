package com.goodsrc.ui.library.widget.notch;

import android.view.WindowManager$LayoutParams;
import java.util.List;
import android.view.DisplayCutout;
import com.goodsrc.library.utils.ToastUtil;
import android.view.WindowInsets;
import android.view.View;
import android.view.View$OnApplyWindowInsetsListener;
import android.app.Activity;

public class AndroidPNotch extends NotchBase
{
    public AndroidPNotch(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        this.window.getDecorView().setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new View$OnApplyWindowInsetsListener(this) {
            final AndroidPNotch this$0;
            
            public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
                final DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                if (displayCutout != null && displayCutout.getSafeInsetTop() != 0) {
                    final List boundingRects = displayCutout.getBoundingRects();
                    final StringBuilder sb = new StringBuilder();
                    sb.append(boundingRects.size());
                    sb.append("");
                    ToastUtil.showLong((CharSequence)sb.toString());
                }
                else {
                    ToastUtil.showLong((CharSequence)"\u65e0\u5218\u6d77");
                }
                return windowInsets;
            }
        });
    }
    
    @Override
    public int[] getNotchSize() {
        return new int[0];
    }
    
    @Override
    public boolean isNotchScreen() {
        return false;
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
        final WindowManager$LayoutParams attributes = this.window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        this.window.setAttributes(attributes);
        this.window.getDecorView().setSystemUiVisibility(this.window.getDecorView().getSystemUiVisibility() | 0x406);
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
    }
}
