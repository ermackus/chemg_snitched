package com.kingagroot.kingdraw.base;

import android.content.Context;

public class DataInite
{
    private static Context context;
    
    public static void getMobileInfo(final Context context) {
        DataInite.context = context;
    }
}
