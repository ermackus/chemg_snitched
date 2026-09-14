package com.goodsrc.ui.library.widget.notch;

import java.lang.reflect.InvocationTargetException;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.app.Activity;

public class VivoNotch extends NotchBase
{
    private static final int NOTCH_IN_SCREEN_VOIO = 32;
    private static final int ROUNDED_IN_SCREEN_VOIO = 8;
    
    public VivoNotch(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        final boolean notchScreen = this.isNotchScreen();
        if (notchCallBack != null) {
            notchCallBack.onResult(notchScreen);
        }
    }
    
    @Override
    public int[] getNotchSize() {
        final DisplayMetrics displayMetrics = this.window.getContext().getResources().getDisplayMetrics();
        return new int[] { (int)TypedValue.applyDimension(1, 100.0f, displayMetrics), (int)TypedValue.applyDimension(1, 27.0f, displayMetrics) };
    }
    
    @Override
    public boolean isNotchScreen() {
        try {
            final Class loadClass = this.context.getClassLoader().loadClass("android.util.FtFeature");
            return (boolean)loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke((Object)loadClass, new Object[] { 32 });
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (final NoSuchMethodException ex3) {
            ex3.printStackTrace();
        }
        catch (final ClassNotFoundException ex4) {
            ex4.printStackTrace();
        }
        return false;
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
