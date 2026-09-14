package com.goodsrc.ui.library.widget.notch;

import java.lang.reflect.InvocationTargetException;
import android.view.WindowManager$LayoutParams;
import android.os.Build$VERSION;
import android.provider.Settings$Secure;
import android.app.Activity;

public class HuaWeiNotch extends NotchBase
{
    private static final String DISPLAY_NOTCH_STATUS = "display_notch_status";
    private static final int FLAG_NOTCH_SUPPORT = 65536;
    
    public HuaWeiNotch(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void checkNotchInScreen(final NotchCallBack notchCallBack) {
        boolean notchScreen = false;
        try {
            if (Settings$Secure.getInt(this.context.getContentResolver(), "display_notch_status", 0) != 1) {
                notchScreen = this.isNotchScreen();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        if (notchCallBack != null) {
            notchCallBack.onResult(notchScreen);
        }
    }
    
    @Override
    public int[] getNotchSize() {
        final int[] array2;
        final int[] array = array2 = new int[2];
        array2[1] = (array2[0] = 0);
        try {
            try {
                final Class loadClass = this.context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return (int[])loadClass.getMethod("getNotchSize", (Class[])new Class[0]).invoke((Object)loadClass, new Object[0]);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                return array;
            }
            catch (final NoSuchMethodException ex2) {
                ex2.printStackTrace();
                return array;
            }
            catch (final ClassNotFoundException ex3) {
                ex3.printStackTrace();
            }
            return array;
        }
        finally {
            return array;
        }
    }
    
    @Override
    public boolean isNotchScreen() {
        try {
            final Class loadClass = this.context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return (boolean)loadClass.getMethod("hasNotchInScreen", (Class[])new Class[0]).invoke((Object)loadClass, new Object[0]);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchMethodException ex2) {
            ex2.printStackTrace();
        }
        catch (final ClassNotFoundException ex3) {
            ex3.printStackTrace();
        }
        return false;
    }
    
    @Override
    public void setFullScreenWindowLayoutInDisplayCutout() {
        if (this.window == null) {
            return;
        }
        if (Build$VERSION.SDK_INT >= 28) {
            final WindowManager$LayoutParams attributes = this.window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            this.window.setAttributes(attributes);
            this.window.getDecorView().setSystemUiVisibility(this.window.getDecorView().getSystemUiVisibility() | 0x406);
        }
        else {
            final WindowManager$LayoutParams attributes2 = this.window.getAttributes();
            Class<?> forName = null;
            try {
                forName = Class.forName("com.huawei.android.view.LayoutParamsEx");
                forName.getMethod("addHwFlags", Integer.TYPE).invoke(forName.getConstructor(WindowManager$LayoutParams.class).newInstance(new Object[] { attributes2 }), new Object[] { 65536 });
                return;
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                return;
            }
            catch (final InvocationTargetException forName) {}
            catch (final InstantiationException forName) {}
            catch (final IllegalAccessException forName) {}
            catch (final NoSuchMethodException forName) {}
            catch (final ClassNotFoundException ex2) {}
            ((ReflectiveOperationException)forName).printStackTrace();
        }
    }
    
    @Override
    public void setNotFullScreenWindowLayoutInDisplayCutout() {
        if (this.window == null) {
            return;
        }
        if (Build$VERSION.SDK_INT == 28) {
            final WindowManager$LayoutParams attributes = this.window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            this.window.setAttributes(attributes);
            this.window.getDecorView().setSystemUiVisibility(this.window.getDecorView().getSystemUiVisibility() | 0x406);
        }
        else {
            final WindowManager$LayoutParams attributes2 = this.window.getAttributes();
            Class<?> forName = null;
            try {
                forName = Class.forName("com.huawei.android.view.LayoutParamsEx");
                forName.getMethod("clearHwFlags", Integer.TYPE).invoke(forName.getConstructor(WindowManager$LayoutParams.class).newInstance(new Object[] { attributes2 }), new Object[] { 65536 });
                return;
            }
            catch (final Exception ex) {
                ex.printStackTrace();
                return;
            }
            catch (final InvocationTargetException forName) {}
            catch (final InstantiationException forName) {}
            catch (final IllegalAccessException forName) {}
            catch (final NoSuchMethodException forName) {}
            catch (final ClassNotFoundException ex2) {}
            ((ReflectiveOperationException)forName).printStackTrace();
        }
    }
}
