package org.xutils.image;

import java.io.IOException;
import org.xutils.common.util.IOUtil;
import org.xutils.ex.FileLockedException;
import org.xutils.common.Callback$CancelledException;
import android.graphics.Paint;
import android.widget.ImageView$ScaleType;
import android.graphics.Bitmap;
import org.xutils.common.util.LogUtil;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import org.xutils.common.task.Priority;
import org.xutils.http.RequestParams;
import org.xutils.cache.LruDiskCache;
import org.xutils.x;
import android.app.ActivityManager;
import org.xutils.common.task.PriorityExecutor;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import org.xutils.common.Callback$CommonCallback;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.cache.LruCache;
import java.util.HashMap;
import java.util.concurrent.Executor;
import org.xutils.common.Callback$Cancelable;
import org.xutils.common.Callback;
import org.xutils.common.Callback$ProgressCallback;
import org.xutils.common.Callback$CacheCallback;
import android.graphics.drawable.Drawable;
import java.io.File;
import org.xutils.common.Callback$PrepareCallback;

final class ImageLoader implements Callback$PrepareCallback<File, Drawable>, Callback$CacheCallback<Drawable>, Callback$ProgressCallback<Drawable>, TypedCallback<Drawable>, Callback$Cancelable
{
    private static final String DISK_CACHE_DIR_NAME = "xUtils_img";
    private static final Executor EXECUTOR;
    private static final HashMap<String, ImageLoader.ImageLoader$FakeImageView> FAKE_IMG_MAP;
    private static final LruCache<MemCacheKey, Drawable> MEM_CACHE;
    private static final int MEM_CACHE_MIN_SIZE = 4194304;
    private static final AtomicLong SEQ_SEEK;
    private static final Type loadType;
    private Callback$CacheCallback<Drawable> cacheCallback;
    private Callback$CommonCallback<Drawable> callback;
    private Callback$Cancelable cancelable;
    private volatile boolean cancelled;
    private boolean hasCache;
    private MemCacheKey key;
    private ImageOptions options;
    private Callback$PrepareCallback<File, Drawable> prepareCallback;
    private Callback$ProgressCallback<Drawable> progressCallback;
    private final long seq;
    private volatile boolean stopped;
    private WeakReference<ImageView> viewRef;
    
    static {
        SEQ_SEEK = new AtomicLong(0L);
        EXECUTOR = (Executor)new PriorityExecutor(10, false);
        int n = 4194304;
        MEM_CACHE = (LruCache)new ImageLoader$1(4194304);
        final int n2 = ((ActivityManager)x.app().getSystemService("activity")).getMemoryClass() * 1048576 / 8;
        if (n2 >= 4194304) {
            n = n2;
        }
        ImageLoader.MEM_CACHE.resize(n);
        FAKE_IMG_MAP = new HashMap();
        loadType = (Type)File.class;
    }
    
    private ImageLoader() {
        this.seq = ImageLoader.SEQ_SEEK.incrementAndGet();
        this.stopped = false;
        this.cancelled = false;
        this.hasCache = false;
    }
    
    static void clearCacheFiles() {
        LruDiskCache.getDiskCache("xUtils_img").clearCacheFiles();
    }
    
    static void clearMemCache() {
        ImageLoader.MEM_CACHE.evictAll();
    }
    
    private static RequestParams createRequestParams(final String s, final ImageOptions imageOptions) {
        final RequestParams requestParams = new RequestParams(s);
        requestParams.setCacheDirName("xUtils_img");
        requestParams.setConnectTimeout(8000);
        requestParams.setPriority(Priority.BG_LOW);
        requestParams.setExecutor(ImageLoader.EXECUTOR);
        requestParams.setCancelFast(true);
        requestParams.setUseCookie(false);
        RequestParams buildParams = requestParams;
        if (imageOptions != null) {
            final ImageOptions$ParamsBuilder paramsBuilder = imageOptions.getParamsBuilder();
            buildParams = requestParams;
            if (paramsBuilder != null) {
                buildParams = paramsBuilder.buildParams(requestParams, imageOptions);
            }
        }
        return buildParams;
    }
    
    static Callback$Cancelable doBind(final ImageView imageView, final String s, ImageOptions doLoad, final Callback$CommonCallback<Drawable> callback$CommonCallback) {
        if (imageView == null) {
            postArgsException(null, doLoad, "view is null", callback$CommonCallback);
            return null;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            postArgsException(imageView, doLoad, "url is null", callback$CommonCallback);
            return null;
        }
        ImageOptions default1;
        if ((default1 = doLoad) == null) {
            default1 = ImageOptions.DEFAULT;
        }
        default1.optimizeMaxSize(imageView);
        final MemCacheKey memCacheKey = new MemCacheKey(s, default1);
        final Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AsyncDrawable) {
            final ImageLoader imageLoader = ((AsyncDrawable)drawable).getImageLoader();
            if (imageLoader != null && !imageLoader.stopped) {
                if (memCacheKey.equals((Object)imageLoader.key)) {
                    return null;
                }
                imageLoader.cancel();
            }
        }
        else if (drawable instanceof ReusableDrawable) {
            final MemCacheKey memCacheKey2 = ((ReusableDrawable)drawable).getMemCacheKey();
            if (memCacheKey2 != null && memCacheKey2.equals((Object)memCacheKey)) {
                ImageLoader.MEM_CACHE.put((Object)memCacheKey, (Object)drawable);
            }
        }
        Drawable imageDrawable = null;
        Label_0222: {
            if (default1.isUseMemCache()) {
                final Drawable drawable2 = imageDrawable = (Drawable)ImageLoader.MEM_CACHE.get((Object)memCacheKey);
                if (!(drawable2 instanceof BitmapDrawable)) {
                    break Label_0222;
                }
                final Bitmap bitmap = ((BitmapDrawable)drawable2).getBitmap();
                if (bitmap != null) {
                    imageDrawable = drawable2;
                    if (!bitmap.isRecycled()) {
                        break Label_0222;
                    }
                }
            }
            imageDrawable = null;
        }
        if (imageDrawable == null) {
            return new ImageLoader().doLoad(imageView, s, default1, callback$CommonCallback);
        }
        final int n = 0;
        try {
            if (callback$CommonCallback instanceof Callback$ProgressCallback) {
                ((Callback$ProgressCallback)callback$CommonCallback).onWaiting();
            }
            imageView.setScaleType(default1.getImageScaleType());
            imageView.setImageDrawable(imageDrawable);
            final int n2 = 1;
            try {
                int onCache;
                if (callback$CommonCallback instanceof Callback$CacheCallback) {
                    final boolean b = (onCache = (((Callback$CacheCallback)callback$CommonCallback).onCache((Object)imageDrawable) ? 1 : 0)) != 0;
                    if (!b) {
                        doLoad = (ImageOptions)new ImageLoader().doLoad(imageView, s, default1, callback$CommonCallback);
                        if (b && callback$CommonCallback != null) {
                            try {
                                callback$CommonCallback.onFinished();
                            }
                            finally {
                                LogUtil.e(((Throwable)imageView).getMessage(), (Throwable)imageView);
                            }
                        }
                        return (Callback$Cancelable)doLoad;
                    }
                }
                else {
                    onCache = n2;
                    if (callback$CommonCallback != null) {
                        callback$CommonCallback.onSuccess((Object)imageDrawable);
                        onCache = n2;
                    }
                }
                if (onCache != 0 && callback$CommonCallback != null) {
                    try {
                        callback$CommonCallback.onFinished();
                    }
                    finally {
                        LogUtil.e(((Throwable)imageView).getMessage(), (Throwable)imageView);
                    }
                }
                return null;
            }
            finally {}
        }
        finally {
            final int n3 = 0;
        }
        int n3;
        try {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            try {
                return new ImageLoader().doLoad(imageView, s, default1, callback$CommonCallback);
            }
            finally {
                n3 = n;
            }
        }
        finally {}
        if (n3 != 0 && callback$CommonCallback != null) {
            try {
                callback$CommonCallback.onFinished();
            }
            finally {
                final Throwable t2;
                LogUtil.e(t2.getMessage(), t2);
            }
        }
    }
    
    private Callback$Cancelable doLoad(final ImageView imageView, final String s, final ImageOptions options, Callback$CommonCallback<Drawable> requestParams) {
        this.viewRef = (WeakReference<ImageView>)new WeakReference((Object)imageView);
        this.options = options;
        this.key = new MemCacheKey(s, options);
        this.callback = (Callback$CommonCallback<Drawable>)requestParams;
        if (requestParams instanceof Callback$ProgressCallback) {
            this.progressCallback = (Callback$ProgressCallback<Drawable>)requestParams;
        }
        if (requestParams instanceof Callback$PrepareCallback) {
            this.prepareCallback = (Callback$PrepareCallback<File, Drawable>)requestParams;
        }
        if (requestParams instanceof Callback$CacheCallback) {
            this.cacheCallback = (Callback$CacheCallback<Drawable>)requestParams;
        }
        if (options.isForceLoadingDrawable()) {
            final Drawable loadingDrawable = options.getLoadingDrawable(imageView);
            imageView.setScaleType(options.getPlaceholderScaleType());
            imageView.setImageDrawable((Drawable)new AsyncDrawable(this, loadingDrawable));
        }
        else {
            imageView.setImageDrawable((Drawable)new AsyncDrawable(this, imageView.getDrawable()));
        }
        requestParams = createRequestParams(s, options);
        if (imageView instanceof ImageLoader.ImageLoader$FakeImageView) {
            final HashMap<String, ImageLoader.ImageLoader$FakeImageView> fake_IMG_MAP = ImageLoader.FAKE_IMG_MAP;
            synchronized (fake_IMG_MAP) {
                ImageLoader.FAKE_IMG_MAP.put((Object)s, (Object)imageView);
            }
        }
        return this.cancelable = x.http().get(requestParams, (Callback$CommonCallback)this);
    }
    
    static Callback$Cancelable doLoadDrawable(final String s, final ImageOptions imageOptions, final Callback$CommonCallback<Drawable> callback$CommonCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            postArgsException(null, imageOptions, "url is null", callback$CommonCallback);
            return null;
        }
        final HashMap<String, ImageLoader.ImageLoader$FakeImageView> fake_IMG_MAP = ImageLoader.FAKE_IMG_MAP;
        synchronized (fake_IMG_MAP) {
            ImageLoader.ImageLoader$FakeImageView imageLoader$FakeImageView;
            if ((imageLoader$FakeImageView = (ImageLoader.ImageLoader$FakeImageView)ImageLoader.FAKE_IMG_MAP.get((Object)s)) == null) {
                imageLoader$FakeImageView = new ImageLoader.ImageLoader$FakeImageView();
            }
            monitorexit(fake_IMG_MAP);
            return doBind((ImageView)imageLoader$FakeImageView, s, imageOptions, callback$CommonCallback);
        }
    }
    
    static Callback$Cancelable doLoadFile(final String s, final ImageOptions imageOptions, final Callback$CacheCallback<File> callback$CacheCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            postArgsException(null, imageOptions, "url is null", (Callback$CommonCallback<?>)callback$CacheCallback);
            return null;
        }
        return x.http().get(createRequestParams(s, imageOptions), (Callback$CommonCallback)callback$CacheCallback);
    }
    
    private static void postArgsException(final ImageView imageView, final ImageOptions imageOptions, final String s, final Callback$CommonCallback<?> callback$CommonCallback) {
        x.task().autoPost((Runnable)new ImageLoader$3((Callback$CommonCallback)callback$CommonCallback, imageView, imageOptions, s));
    }
    
    private void setErrorDrawable4Callback() {
        final ImageView imageView = (ImageView)this.viewRef.get();
        if (imageView != null) {
            final Drawable failureDrawable = this.options.getFailureDrawable(imageView);
            imageView.setScaleType(this.options.getPlaceholderScaleType());
            imageView.setImageDrawable(failureDrawable);
        }
    }
    
    private void setSuccessDrawable4Callback(final Drawable imageDrawable) {
        final ImageView imageView = (ImageView)this.viewRef.get();
        if (imageView != null) {
            imageView.setScaleType(this.options.getImageScaleType());
            if (imageDrawable instanceof GifDrawable) {
                if (imageView.getScaleType() == ImageView$ScaleType.CENTER) {
                    imageView.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
                }
                imageView.setLayerType(1, (Paint)null);
            }
            if (this.options.getAnimation() != null) {
                ImageAnimationHelper.animationDisplay(imageView, imageDrawable, this.options.getAnimation());
            }
            else if (this.options.isFadeIn()) {
                ImageAnimationHelper.fadeInDisplay(imageView, imageDrawable);
            }
            else {
                imageView.setImageDrawable(imageDrawable);
            }
        }
    }
    
    private boolean validView4Callback(final boolean b) {
        final ImageView imageView = (ImageView)this.viewRef.get();
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final ImageLoader imageLoader = ((AsyncDrawable)drawable).getImageLoader();
                if (imageLoader != null) {
                    if (imageLoader == this) {
                        if (imageView.getVisibility() != 0) {
                            imageLoader.cancel();
                            return false;
                        }
                        return true;
                    }
                    else {
                        if (this.seq > imageLoader.seq) {
                            imageLoader.cancel();
                            return true;
                        }
                        this.cancel();
                        return false;
                    }
                }
            }
            else if (b) {
                this.cancel();
                return false;
            }
            return true;
        }
        return false;
    }
    
    public void cancel() {
        this.stopped = true;
        this.cancelled = true;
        final Callback$Cancelable cancelable = this.cancelable;
        if (cancelable != null) {
            cancelable.cancel();
        }
    }
    
    public Type getLoadType() {
        return ImageLoader.loadType;
    }
    
    public boolean isCancelled() {
        final boolean cancelled = this.cancelled;
        boolean b = false;
        if (cancelled || !this.validView4Callback(false)) {
            b = true;
        }
        return b;
    }
    
    public boolean onCache(final Drawable successDrawable4Callback) {
        if (!this.validView4Callback(true)) {
            return false;
        }
        if (successDrawable4Callback == null) {
            return false;
        }
        this.hasCache = true;
        this.setSuccessDrawable4Callback(successDrawable4Callback);
        final Callback$CacheCallback<Drawable> cacheCallback = this.cacheCallback;
        if (cacheCallback != null) {
            return cacheCallback.onCache((Object)successDrawable4Callback);
        }
        final Callback$CommonCallback<Drawable> callback = this.callback;
        if (callback != null) {
            callback.onSuccess((Object)successDrawable4Callback);
        }
        return true;
    }
    
    public void onCancelled(final Callback$CancelledException ex) {
        this.stopped = true;
        if (!this.validView4Callback(false)) {
            return;
        }
        final Callback$CommonCallback<Drawable> callback = this.callback;
        if (callback != null) {
            callback.onCancelled(ex);
        }
    }
    
    public void onError(final Throwable t, final boolean b) {
        this.stopped = true;
        if (!this.validView4Callback(false)) {
            return;
        }
        if (t instanceof FileLockedException) {
            final StringBuilder sb = new StringBuilder();
            sb.append("ImageFileLocked: ");
            sb.append(this.key.url);
            LogUtil.d(sb.toString());
            x.task().postDelayed((Runnable)new ImageLoader$2(this), 10L);
            return;
        }
        LogUtil.e(this.key.url, t);
        this.setErrorDrawable4Callback();
        final Callback$CommonCallback<Drawable> callback = this.callback;
        if (callback != null) {
            callback.onError(t, b);
        }
    }
    
    public void onFinished() {
        this.stopped = true;
        if (((ImageView)this.viewRef.get()) instanceof ImageLoader.ImageLoader$FakeImageView) {
            final HashMap<String, ImageLoader.ImageLoader$FakeImageView> fake_IMG_MAP = ImageLoader.FAKE_IMG_MAP;
            synchronized (fake_IMG_MAP) {
                ImageLoader.FAKE_IMG_MAP.remove((Object)this.key.url);
            }
        }
        if (!this.validView4Callback(false)) {
            return;
        }
        final Callback$CommonCallback<Drawable> callback = this.callback;
        if (callback != null) {
            callback.onFinished();
        }
    }
    
    public void onLoading(final long n, final long n2, final boolean b) {
        if (this.validView4Callback(true)) {
            final Callback$ProgressCallback<Drawable> progressCallback = this.progressCallback;
            if (progressCallback != null) {
                progressCallback.onLoading(n, n2, b);
            }
        }
    }
    
    public void onStarted() {
        if (this.validView4Callback(true)) {
            final Callback$ProgressCallback<Drawable> progressCallback = this.progressCallback;
            if (progressCallback != null) {
                progressCallback.onStarted();
            }
        }
    }
    
    public void onSuccess(final Drawable successDrawable4Callback) {
        if (!this.validView4Callback(this.hasCache ^ true)) {
            return;
        }
        if (successDrawable4Callback != null) {
            this.setSuccessDrawable4Callback(successDrawable4Callback);
            final Callback$CommonCallback<Drawable> callback = this.callback;
            if (callback != null) {
                callback.onSuccess((Object)successDrawable4Callback);
            }
        }
    }
    
    public void onWaiting() {
        final Callback$ProgressCallback<Drawable> progressCallback = this.progressCallback;
        if (progressCallback != null) {
            progressCallback.onWaiting();
        }
    }
    
    public Drawable prepare(final File file) {
        if (!this.validView4Callback(true)) {
            return null;
        }
        try {
            Object o;
            if (this.prepareCallback != null) {
                o = this.prepareCallback.prepare((Object)file);
            }
            else {
                o = null;
            }
            Object decodeFileWithLock = o;
            if (o == null) {
                decodeFileWithLock = ImageDecoder.decodeFileWithLock(file, this.options, (Callback$Cancelable)this);
            }
            if (decodeFileWithLock != null && decodeFileWithLock instanceof ReusableDrawable) {
                ((ReusableDrawable)decodeFileWithLock).setMemCacheKey(this.key);
                ImageLoader.MEM_CACHE.put((Object)this.key, decodeFileWithLock);
            }
            return (Drawable)decodeFileWithLock;
        }
        catch (final IOException ex) {
            IOUtil.deleteFileOrDir(file);
            LogUtil.w(ex.getMessage(), (Throwable)ex);
            return null;
        }
    }
}
