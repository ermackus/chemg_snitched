package com.goodsrc.ui.library.widget.notch;

import android.os.Build;
import android.view.View;
import android.app.Activity;

public class NotchContext implements NotchBaseContext
{
    NotchBase notchBase;
    
    public NotchContext(final Activity activity) {
        this(activity, null);
    }
    
    public NotchContext(final Activity activity, final View view) {
        if (!this.isFullScreen(activity) && !this.isLandscape(activity)) {
            return;
        }
        final String manufacturer = Build.MANUFACTURER;
        final String model = Build.MODEL;
        if (manufacturer.contains((CharSequence)"Xiaomi")) {
            this.notchBase = new MiuiNotch(activity);
        }
        else if (manufacturer.contains((CharSequence)"HUAWEI")) {
            this.notchBase = new HuaWeiNotch(activity);
        }
        else if (manufacturer.contains((CharSequence)"OPPO")) {
            this.notchBase = new OppoNotch(activity);
        }
        else if (manufacturer.contains((CharSequence)"vivo")) {
            this.notchBase = new VivoNotch(activity);
        }
        else if (manufacturer.contains((CharSequence)"Lenovo")) {
            if (!model.equals((Object)"Lenovo L78011") && !model.equals((Object)"Lenovo L78012") && !model.equals((Object)"Lenovo L58091") && !model.equals((Object)"Lenovo L58041")) {
                if (model.equals((Object)"Lenovo L78071")) {
                    this.notchBase = new LenovoZ5sNotch(activity, view);
                }
            }
            else {
                this.notchBase = new LenovoZ5Notch(activity, view);
            }
        }
        else if (manufacturer.equals((Object)"Hisense")) {
            if (model.equals((Object)"HLTE213M")) {
                this.notchBase = new HisenseHLTE213Notch(activity, view);
            }
        }
        else {
            this.notchBase = new CommonNotch(activity);
        }
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        final NotchBase notchBase = this.notchBase;
        if (notchBase != null) {
            notchBase.checkNotchInScreen(notchCallBack);
        }
        else if (notchCallBack != null) {
            notchCallBack.onResult(false);
        }
    }
    
    public NotchBase getNotchBase() {
        return this.notchBase;
    }
    
    @Override
    public int[] getNotchSize() {
        final NotchBase notchBase = this.notchBase;
        if (notchBase != null) {
            return notchBase.getNotchSize();
        }
        return new int[2];
    }
    
    public boolean isFullScreen(final Activity activity) {
        return (activity.getWindow().getAttributes().flags & 0x400) == 0x400;
    }
    
    public boolean isLandscape(final Activity activity) {
        return activity.getResources().getConfiguration().orientation == 2;
    }
    
    @Override
    public boolean isNotchScreen() {
        final NotchBase notchBase = this.notchBase;
        return notchBase != null && notchBase.isNotchScreen();
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
        final NotchBase notchBase = this.notchBase;
        if (notchBase != null) {
            notchBase.setFullScreenWindowLayoutInDisplayCutout();
        }
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
        final NotchBase notchBase = this.notchBase;
        if (notchBase != null) {
            notchBase.setNotFullScreenWindowLayoutInDisplayCutout();
        }
    }
}
