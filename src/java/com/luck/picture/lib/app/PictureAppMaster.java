package com.luck.picture.lib.app;

import com.luck.picture.lib.engine.PictureSelectorEngine;
import android.content.Context;

public class PictureAppMaster implements IApp
{
    private static PictureAppMaster mInstance;
    private IApp app;
    
    private PictureAppMaster() {
    }
    
    public static PictureAppMaster getInstance() {
        if (PictureAppMaster.mInstance == null) {
            synchronized (PictureAppMaster.class) {
                if (PictureAppMaster.mInstance == null) {
                    PictureAppMaster.mInstance = new PictureAppMaster();
                }
            }
        }
        return PictureAppMaster.mInstance;
    }
    
    public IApp getApp() {
        return this.app;
    }
    
    public Context getAppContext() {
        final IApp app = this.app;
        if (app == null) {
            return null;
        }
        return app.getAppContext();
    }
    
    public PictureSelectorEngine getPictureSelectorEngine() {
        final IApp app = this.app;
        if (app == null) {
            return null;
        }
        return app.getPictureSelectorEngine();
    }
    
    public void setApp(final IApp app) {
        this.app = app;
    }
}
