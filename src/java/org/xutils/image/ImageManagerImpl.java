package org.xutils.image;

import java.io.File;
import android.graphics.drawable.Drawable;
import org.xutils.common.Callback;
import android.widget.ImageView;
import org.xutils.x;
import org.xutils.ImageManager;

public final class ImageManagerImpl implements ImageManager
{
    private static volatile ImageManagerImpl instance;
    private static final Object lock;
    
    static {
        lock = new Object();
    }
    
    private ImageManagerImpl() {
    }
    
    public static void registerInstance() {
        if (ImageManagerImpl.instance == null) {
            final Object lock = ImageManagerImpl.lock;
            synchronized (lock) {
                if (ImageManagerImpl.instance == null) {
                    ImageManagerImpl.instance = new ImageManagerImpl();
                }
            }
        }
        x.Ext.setImageManager(ImageManagerImpl.instance);
    }
    
    @Override
    public void bind(final ImageView imageView, final String s) {
        x.task().autoPost((Runnable)new Runnable(this, imageView, s) {
            final ImageManagerImpl this$0;
            final String val$url;
            final ImageView val$view;
            
            public void run() {
                ImageLoader.doBind(this.val$view, this.val$url, (ImageOptions)null, (Callback.CommonCallback)null);
            }
        });
    }
    
    @Override
    public void bind(final ImageView imageView, final String s, final Callback.CommonCallback<Drawable> commonCallback) {
        x.task().autoPost((Runnable)new Runnable(this, imageView, s, commonCallback) {
            final ImageManagerImpl this$0;
            final Callback.CommonCallback val$callback;
            final String val$url;
            final ImageView val$view;
            
            public void run() {
                ImageLoader.doBind(this.val$view, this.val$url, (ImageOptions)null, this.val$callback);
            }
        });
    }
    
    @Override
    public void bind(final ImageView imageView, final String s, final ImageOptions imageOptions) {
        x.task().autoPost((Runnable)new Runnable(this, imageView, s, imageOptions) {
            final ImageManagerImpl this$0;
            final ImageOptions val$options;
            final String val$url;
            final ImageView val$view;
            
            public void run() {
                ImageLoader.doBind(this.val$view, this.val$url, this.val$options, (Callback.CommonCallback)null);
            }
        });
    }
    
    @Override
    public void bind(final ImageView imageView, final String s, final ImageOptions imageOptions, final Callback.CommonCallback<Drawable> commonCallback) {
        x.task().autoPost((Runnable)new Runnable(this, imageView, s, imageOptions, commonCallback) {
            final ImageManagerImpl this$0;
            final Callback.CommonCallback val$callback;
            final ImageOptions val$options;
            final String val$url;
            final ImageView val$view;
            
            public void run() {
                ImageLoader.doBind(this.val$view, this.val$url, this.val$options, this.val$callback);
            }
        });
    }
    
    @Override
    public void clearCacheFiles() {
        ImageLoader.clearCacheFiles();
        ImageDecoder.clearCacheFiles();
    }
    
    @Override
    public void clearMemCache() {
        ImageLoader.clearMemCache();
    }
    
    @Override
    public Callback.Cancelable loadDrawable(final String s, final ImageOptions imageOptions, final Callback.CommonCallback<Drawable> commonCallback) {
        return ImageLoader.doLoadDrawable(s, imageOptions, (Callback.CommonCallback)commonCallback);
    }
    
    @Override
    public Callback.Cancelable loadFile(final String s, final ImageOptions imageOptions, final Callback.CacheCallback<File> cacheCallback) {
        return ImageLoader.doLoadFile(s, imageOptions, (Callback.CacheCallback)cacheCallback);
    }
}
