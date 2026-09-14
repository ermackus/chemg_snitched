package com.luck.picture.lib.basic;

import com.luck.picture.lib.language.PictureLanguageUtils;
import android.content.Context;
import android.content.ContextWrapper;

public class PictureContextWrapper extends ContextWrapper
{
    public PictureContextWrapper(final Context context) {
        super(context);
    }
    
    public static ContextWrapper wrap(final Context context, final int n, final int n2) {
        if (n != -2) {
            PictureLanguageUtils.setAppLanguage(context, n, n2);
        }
        return new PictureContextWrapper(context);
    }
    
    public Object getSystemService(final String s) {
        if ("audio".equals((Object)s)) {
            return this.getApplicationContext().getSystemService(s);
        }
        return super.getSystemService(s);
    }
}
