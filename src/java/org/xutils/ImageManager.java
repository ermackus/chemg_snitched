package org.xutils;

import java.io.File;
import org.xutils.image.ImageOptions;
import android.graphics.drawable.Drawable;
import org.xutils.common.Callback;
import android.widget.ImageView;

public interface ImageManager
{
    void bind(final ImageView p0, final String p1);
    
    void bind(final ImageView p0, final String p1, final Callback.CommonCallback<Drawable> p2);
    
    void bind(final ImageView p0, final String p1, final ImageOptions p2);
    
    void bind(final ImageView p0, final String p1, final ImageOptions p2, final Callback.CommonCallback<Drawable> p3);
    
    void clearCacheFiles();
    
    void clearMemCache();
    
    Callback.Cancelable loadDrawable(final String p0, final ImageOptions p1, final Callback.CommonCallback<Drawable> p2);
    
    Callback.Cancelable loadFile(final String p0, final ImageOptions p1, final Callback.CacheCallback<File> p2);
}
