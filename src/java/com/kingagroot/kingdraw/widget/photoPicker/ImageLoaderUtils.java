package com.kingagroot.kingdraw.widget.photoPicker;

import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;

public class ImageLoaderUtils
{
    public static boolean assertValidRequest(final Context context) {
        if (context instanceof Activity) {
            return isDestroy((Activity)context) ^ true;
        }
        if (context instanceof ContextWrapper) {
            final ContextWrapper contextWrapper = (ContextWrapper)context;
            if (contextWrapper.getBaseContext() instanceof Activity) {
                return isDestroy((Activity)contextWrapper.getBaseContext()) ^ true;
            }
        }
        return true;
    }
    
    private static boolean isDestroy(final Activity activity) {
        final boolean b = true;
        if (activity == null) {
            return true;
        }
        boolean b2 = b;
        if (!activity.isFinishing()) {
            b2 = (activity.isDestroyed() && b);
        }
        return b2;
    }
}
