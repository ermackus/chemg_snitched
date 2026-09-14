package com.kingagroot.kingdraw.core.view3d.base;

import android.content.Context;
import android.app.Application;

public class Chem3DConfig
{
    private static Application application;
    
    public static Context getContext() {
        return (Context)Chem3DConfig.application;
    }
    
    public static void init(final Application application) {
        Chem3DConfig.application = application;
    }
}
