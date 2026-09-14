package com.goodsrc.ui.library.widget.notch;

import java.lang.reflect.Method;
import android.view.Window;
import android.provider.Settings$Global;
import android.os.Build$VERSION;
import android.content.res.Resources;
import android.app.Activity;

public class MiuiNotch extends NotchBase
{
    public MiuiNotch(final Activity activity) {
        super(activity);
    }
    
    private int getStatusBarHeight() {
        final Resources resources = this.context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = true;
        Label_0056: {
            if (sdk_INT >= 27) {
                final boolean b2 = Settings$Global.getInt(this.context.getContentResolver(), "force_black", 0) == 1;
                final boolean notchScreen = this.isNotchScreen();
                if (b2 && notchScreen) {
                    break Label_0056;
                }
            }
            b = false;
        }
        if (notchCallBack != null) {
            notchCallBack.onResult(b);
        }
    }
    
    @Override
    public int[] getNotchSize() {
        final int identifier = this.context.getResources().getIdentifier("notch_height", "dimen", "android");
        int n;
        if (identifier > 0) {
            n = this.context.getResources().getDimensionPixelSize(identifier);
        }
        else {
            n = this.getStatusBarHeight();
        }
        final int identifier2 = this.context.getResources().getIdentifier("notch_width", "dimen", "android");
        int dimensionPixelSize;
        if (identifier2 > 0) {
            dimensionPixelSize = this.context.getResources().getDimensionPixelSize(identifier2);
        }
        else {
            dimensionPixelSize = 0;
        }
        return new int[] { dimensionPixelSize, n };
    }
    
    @Override
    public boolean isNotchScreen() {
        return Build$VERSION.SDK_INT >= 27 && "1".equals((Object)SystemProperties.getInstance().get("ro.miui.notch"));
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
        try {
            final Method method = Window.class.getMethod("addExtraFlags", Integer.TYPE);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke((Object)this.window, new Object[] { 1792 });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
        try {
            final Method method = Window.class.getMethod("clearExtraFlags", Integer.TYPE);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke((Object)this.window, new Object[] { 1792 });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
