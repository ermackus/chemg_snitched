package com.goodsrc.library.utils;

import android.content.Context;

public class DisplayUtil
{
    public static int dip2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    public static int px2dip(final Context context, final float n) {
        return (int)(n / context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    public static int px2sp(final Context context, final float n) {
        return (int)(n / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
    
    public static int sp2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
