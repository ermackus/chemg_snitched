package com.kingagroot.component.ui.utils;

import androidx.core.graphics.drawable.DrawableCompat;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.content.Context;

public class SVGDrawUtils
{
    public static Drawable changeColor(final Context context, final int n, final int n2) {
        return changeColor(ContextCompat.getDrawable(context, n), n2);
    }
    
    public static Drawable changeColor(final Drawable drawable, final int n) {
        return changeColor(drawable, ColorStateList.valueOf(n));
    }
    
    public static Drawable changeColor(Drawable wrap, final ColorStateList list) {
        wrap.mutate();
        wrap = DrawableCompat.wrap(wrap);
        DrawableCompat.setTintList(wrap, list);
        return wrap;
    }
}
