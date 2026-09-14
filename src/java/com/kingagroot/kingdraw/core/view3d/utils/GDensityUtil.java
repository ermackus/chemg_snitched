package com.kingagroot.kingdraw.core.view3d.utils;

import com.kingagroot.kingdraw.core.view3d.base.Chem3DConfig;
import android.content.Context;
import android.util.TypedValue;

public final class GDensityUtil
{
    private GDensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    
    public static float cm2px(final float n) {
        return GNumberUtil.keepPrecision(n * getContex().getResources().getDisplayMetrics().xdpi / 2.54f, 4);
    }
    
    public static int dp2px(final float n) {
        return (int)TypedValue.applyDimension(1, n, getContex().getResources().getDisplayMetrics());
    }
    
    private static Context getContex() {
        return Chem3DConfig.getContext();
    }
    
    public static float px2cm(final float n) {
        return GNumberUtil.keepPrecision(n * 2.54f / getContex().getResources().getDisplayMetrics().xdpi, 4);
    }
    
    public static float px2dp(final float n) {
        return n / getContex().getResources().getDisplayMetrics().density;
    }
    
    public static float px2sp(final float n) {
        return n / getContex().getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }
    
    public static int sp2px(final float n) {
        return (int)TypedValue.applyDimension(2, n, getContex().getResources().getDisplayMetrics());
    }
}
