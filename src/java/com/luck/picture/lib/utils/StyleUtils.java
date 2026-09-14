package com.luck.picture.lib.utils;

import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.content.ContextCompat;
import android.graphics.ColorFilter;
import android.content.Context;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleUtils
{
    private static final int INVALID = 0;
    
    public static boolean checkArrayValidity(final int[] array) {
        return array != null && array.length > 0;
    }
    
    public static boolean checkSizeValidity(final int n) {
        return n > 0;
    }
    
    public static boolean checkStyleValidity(final int n) {
        return n != 0;
    }
    
    public static boolean checkTextFormatValidity(final String s) {
        return Pattern.compile("\\([^)]*\\)").matcher((CharSequence)s).find();
    }
    
    public static boolean checkTextTwoFormatValidity(final String s) {
        final Matcher matcher = Pattern.compile("%[^%]*\\d").matcher((CharSequence)s);
        boolean b = false;
        int n = 0;
        while (matcher.find()) {
            ++n;
        }
        if (n >= 2) {
            b = true;
        }
        return b;
    }
    
    public static boolean checkTextValidity(final String s) {
        return TextUtils.isEmpty((CharSequence)s) ^ true;
    }
    
    public static ColorFilter getColorFilter(final Context context, final int n) {
        return BlendModeColorFilterCompat.createBlendModeColorFilterCompat(ContextCompat.getColor(context, n), BlendModeCompat.SRC_ATOP);
    }
}
