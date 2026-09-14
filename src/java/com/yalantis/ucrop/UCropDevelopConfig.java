package com.yalantis.ucrop;

public final class UCropDevelopConfig
{
    public static UCropImageEngine imageEngine;
    
    public static void destroy() {
        UCropDevelopConfig.imageEngine = null;
    }
}
