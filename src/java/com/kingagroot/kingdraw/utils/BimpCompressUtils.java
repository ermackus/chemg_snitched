package com.kingagroot.kingdraw.utils;

import java.io.IOException;
import android.content.Context;
import com.kingagroot.kingdraw.utils.LubanCompress.Luban;
import com.kingagroot.kingdraw.base.MApplication;
import java.io.File;

public class BimpCompressUtils
{
    public static File imageCompress(final String s) {
        File value;
        try {
            value = Luban.with((Context)MApplication.getInstance()).load(s).get();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            value = null;
        }
        return value;
    }
    
    public static File imageCompressAndGray(final String s) {
        File grey;
        try {
            grey = Luban.with((Context)MApplication.getInstance()).load(s).getGrey();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            grey = null;
        }
        return grey;
    }
    
    public static File imageCompressWithBackgound(final int n, final String s) {
        File withBackground;
        try {
            withBackground = Luban.with((Context)MApplication.getInstance()).load(s).getWithBackground(n);
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            withBackground = null;
        }
        return withBackground;
    }
}
