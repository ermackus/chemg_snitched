package com.yalantis.ucrop.util;

import android.content.res.Resources;
import android.content.Context;

public class DensityUtil
{
    public static int dip2px(final Context context, final float n) {
        return (int)(n * context.getApplicationContext().getResources().getDisplayMetrics().density + 0.5f);
    }
    
    public static int getStatusBarHeight(final Context context) {
        final int identifier = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        int dip2px = dimensionPixelSize;
        if (dimensionPixelSize == 0) {
            dip2px = dip2px(context, 26.0f);
        }
        return dip2px;
    }
}
