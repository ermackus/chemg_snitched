package com.kingagroot.component.ui.utils;

import com.kingagroot.component.ui.R;
import android.view.View;

public class CheckDoubleClick
{
    private static final int MIN_CLICK_DELAY_TIME = 1500;
    private static long lastClickTime;
    
    public static boolean isFastDoubleClick() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long n = currentTimeMillis - CheckDoubleClick.lastClickTime;
        if (0L < n && n < 1500L) {
            return true;
        }
        CheckDoubleClick.lastClickTime = currentTimeMillis;
        return false;
    }
    
    public static boolean isFastDoubleClick(final View view) {
        final Object tag = view.getTag(R.id.check_double_click_tag_key);
        long longValue;
        if (tag != null && tag instanceof Long) {
            longValue = (long)tag;
        }
        else {
            longValue = 0L;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long n = currentTimeMillis - longValue;
        if (0L < n && n < 1500L) {
            return true;
        }
        view.setTag(R.id.check_double_click_tag_key, (Object)currentTimeMillis);
        return false;
    }
}
