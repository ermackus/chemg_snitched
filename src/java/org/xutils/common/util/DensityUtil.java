package org.xutils.common.util;

import org.xutils.x;

public final class DensityUtil
{
    private static float density = -1.0f;
    private static int heightPixels = -1;
    private static int widthPixels = -1;
    
    private DensityUtil() {
    }
    
    public static int dip2px(final float n) {
        return (int)(n * getDensity() + 0.5f);
    }
    
    public static float getDensity() {
        if (DensityUtil.density <= 0.0f) {
            DensityUtil.density = x.app().getResources().getDisplayMetrics().density;
        }
        return DensityUtil.density;
    }
    
    public static int getScreenHeight() {
        if (DensityUtil.heightPixels <= 0) {
            DensityUtil.heightPixels = x.app().getResources().getDisplayMetrics().heightPixels;
        }
        return DensityUtil.heightPixels;
    }
    
    public static int getScreenWidth() {
        if (DensityUtil.widthPixels <= 0) {
            DensityUtil.widthPixels = x.app().getResources().getDisplayMetrics().widthPixels;
        }
        return DensityUtil.widthPixels;
    }
    
    public static int px2dip(final float n) {
        return (int)(n / getDensity() + 0.5f);
    }
}
