package com.luck.picture.lib.app;

import com.luck.picture.lib.engine.PictureSelectorEngine;
import android.content.Context;

public interface IApp
{
    Context getAppContext();
    
    PictureSelectorEngine getPictureSelectorEngine();
}
