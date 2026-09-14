package com.kingagroot.component.ui.utils.base64imageloader;

import android.text.TextUtils;
import java.util.concurrent.Executor;
import android.widget.ImageView;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.graphics.Bitmap;
import androidx.collection.LruCache;

public class Base64BitmapLoader
{
    private static Base64BitmapLoader base64BitmapLoader;
    private final LruCache<String, Bitmap> LruCache_bitmap;
    private final ExecutorService executorService;
    private final int maxSize;
    
    static {
        Base64BitmapLoader.base64BitmapLoader = new Base64BitmapLoader();
    }
    
    private Base64BitmapLoader() {
        this.executorService = Executors.newFixedThreadPool(5);
        final int maxSize = (int)Runtime.getRuntime().maxMemory() / 1024 / 8;
        this.maxSize = maxSize;
        this.LruCache_bitmap = (LruCache<String, Bitmap>)new Base64BitmapLoader$1(this, maxSize);
    }
    
    public static Base64BitmapLoader getInstance() {
        synchronized (Base64BitmapLoader.class) {
            if (Base64BitmapLoader.base64BitmapLoader == null) {
                Base64BitmapLoader.base64BitmapLoader = new Base64BitmapLoader();
            }
            return Base64BitmapLoader.base64BitmapLoader;
        }
    }
    
    private Bitmap loadDrawable(final String s, final String s2, final ImageView imageView, final ImageCallback imageCallback) {
        if (this.LruCache_bitmap.get((Object)s) != null) {
            return (Bitmap)this.LruCache_bitmap.get((Object)s);
        }
        final ImageLoader imageLoader = new ImageLoader(s, s2, imageView, (Executor)this.executorService);
        imageLoader.setOnCallBackListenre((ImageLoader.OnCallBackListenre)new Base64BitmapLoader$2(this, imageCallback));
        imageLoader.start();
        return null;
    }
    
    public void loadImage(final String s, final String s2, final ImageView imageView) {
        if (TextUtils.isEmpty((CharSequence)s2)) {
            return;
        }
        String s3 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s3 = s2;
        }
        final Bitmap loadDrawable = this.loadDrawable(s3, s2.replace((CharSequence)"data:image/png;base64,", (CharSequence)""), imageView, (ImageCallback)new Base64BitmapLoader$3(this));
        if (loadDrawable != null) {
            imageView.setImageBitmap(loadDrawable);
        }
    }
    
    public void recycle() {
        this.LruCache_bitmap.evictAll();
    }
    
    public interface ImageCallback
    {
        void imageLoaded(final Bitmap p0);
    }
}
