package com.goodsrc.ui.library.widget.notch;

import android.app.Activity;

public class OppoNotch extends NotchBase
{
    public OppoNotch(final Activity activity) {
        super(activity);
    }
    
    private boolean shouldNonImmersiveAdjustForPkg(final String s) {
        final Object o = null;
        Object invoke;
        try {
            final Class<?> forName = Class.forName("com.color.util.ColorDisplayCompatUtils");
            invoke = forName.getMethod("shouldNonImmersiveAdjustForPkg", s.getClass()).invoke(forName.getMethod("getInstance", (Class[])new Class[0]).invoke((Object)null, new Object[0]), new Object[] { s });
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            invoke = o;
        }
        return invoke != null && "true".equalsIgnoreCase(invoke.toString());
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        final boolean notchScreen = this.isNotchScreen();
        final boolean b = !this.shouldNonImmersiveAdjustForPkg(this.context.getPackageName()) && notchScreen;
        if (notchCallBack != null) {
            notchCallBack.onResult(b);
        }
    }
    
    @Override
    public int[] getNotchSize() {
        final String[] split = SystemProperties.getInstance().get("ro.oppo.screen.heteromorphism").replace((CharSequence)"[", (CharSequence)"").replace((CharSequence)"]", (CharSequence)"").replace((CharSequence)":", (CharSequence)",").split(",");
        if (split.length == 4) {
            try {
                return new int[] { Integer.parseInt(split[2]) - Integer.parseInt(split[0]), Integer.parseInt(split[3]) - Integer.parseInt(split[1]) };
            }
            catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return new int[] { 324, 80 };
    }
    
    @Override
    public boolean isNotchScreen() {
        return this.context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
        this.window.addFlags(1024);
        this.window.addFlags(67108864);
        this.window.getDecorView().setSystemUiVisibility(this.window.getDecorView().getSystemUiVisibility() | 0x400 | 0x100);
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
        this.window.addFlags(1024);
        this.window.addFlags(67108864);
        this.window.getDecorView().setSystemUiVisibility(this.window.getDecorView().getSystemUiVisibility() & 0x400 & 0x100);
    }
}
