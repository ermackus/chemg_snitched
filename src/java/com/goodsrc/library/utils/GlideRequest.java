package com.goodsrc.library.utils;

import com.bumptech.glide.TransitionOptions;
import java.util.List;
import android.content.res.Resources$Theme;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import java.net.URL;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.File;
import com.bumptech.glide.load.DecodeFormat;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap$CompressFormat;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import android.content.Context;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable
{
    GlideRequest(final Glide glide, final RequestManager requestManager, final Class<TranscodeType> clazz, final Context context) {
        super(glide, requestManager, (Class)clazz, context);
    }
    
    GlideRequest(final Class<TranscodeType> clazz, final RequestBuilder<?> requestBuilder) {
        super((Class)clazz, (RequestBuilder)requestBuilder);
    }
    
    public GlideRequest<TranscodeType> addListener(final RequestListener<TranscodeType> requestListener) {
        return (GlideRequest)super.addListener((RequestListener)requestListener);
    }
    
    public GlideRequest<TranscodeType> apply(final BaseRequestOptions<?> baseRequestOptions) {
        return (GlideRequest)super.apply((BaseRequestOptions)baseRequestOptions);
    }
    
    public GlideRequest<TranscodeType> autoClone() {
        return (GlideRequest)super.autoClone();
    }
    
    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest)super.centerCrop();
    }
    
    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest)super.centerInside();
    }
    
    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest)super.circleCrop();
    }
    
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest)super.clone();
    }
    
    public GlideRequest<TranscodeType> decode(final Class<?> clazz) {
        return (GlideRequest)super.decode((Class)clazz);
    }
    
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest)super.disallowHardwareConfig();
    }
    
    public GlideRequest<TranscodeType> diskCacheStrategy(final DiskCacheStrategy diskCacheStrategy) {
        return (GlideRequest)super.diskCacheStrategy(diskCacheStrategy);
    }
    
    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest)super.dontAnimate();
    }
    
    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest)super.dontTransform();
    }
    
    public GlideRequest<TranscodeType> downsample(final DownsampleStrategy downsampleStrategy) {
        return (GlideRequest)super.downsample(downsampleStrategy);
    }
    
    public GlideRequest<TranscodeType> encodeFormat(final Bitmap$CompressFormat bitmap$CompressFormat) {
        return (GlideRequest)super.encodeFormat(bitmap$CompressFormat);
    }
    
    public GlideRequest<TranscodeType> encodeQuality(final int n) {
        return (GlideRequest)super.encodeQuality(n);
    }
    
    public GlideRequest<TranscodeType> error(final int n) {
        return (GlideRequest)super.error(n);
    }
    
    public GlideRequest<TranscodeType> error(final Drawable drawable) {
        return (GlideRequest)super.error(drawable);
    }
    
    public GlideRequest<TranscodeType> error(final RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest)super.error((RequestBuilder)requestBuilder);
    }
    
    public GlideRequest<TranscodeType> error(final Object o) {
        return (GlideRequest)super.error(o);
    }
    
    public GlideRequest<TranscodeType> fallback(final int n) {
        return (GlideRequest)super.fallback(n);
    }
    
    public GlideRequest<TranscodeType> fallback(final Drawable drawable) {
        return (GlideRequest)super.fallback(drawable);
    }
    
    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest)super.fitCenter();
    }
    
    public GlideRequest<TranscodeType> format(final DecodeFormat decodeFormat) {
        return (GlideRequest)super.format(decodeFormat);
    }
    
    public GlideRequest<TranscodeType> frame(final long n) {
        return (GlideRequest)super.frame(n);
    }
    
    protected GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest<File>(File.class, this).apply((BaseRequestOptions<?>)GlideRequest.DOWNLOAD_ONLY_OPTIONS);
    }
    
    public GlideRequest<TranscodeType> listener(final RequestListener<TranscodeType> requestListener) {
        return (GlideRequest)super.listener((RequestListener)requestListener);
    }
    
    public GlideRequest<TranscodeType> load(final Bitmap bitmap) {
        return (GlideRequest)super.load(bitmap);
    }
    
    public GlideRequest<TranscodeType> load(final Drawable drawable) {
        return (GlideRequest)super.load(drawable);
    }
    
    public GlideRequest<TranscodeType> load(final Uri uri) {
        return (GlideRequest)super.load(uri);
    }
    
    public GlideRequest<TranscodeType> load(final File file) {
        return (GlideRequest)super.load(file);
    }
    
    public GlideRequest<TranscodeType> load(final Integer n) {
        return (GlideRequest)super.load(n);
    }
    
    public GlideRequest<TranscodeType> load(final Object o) {
        return (GlideRequest)super.load(o);
    }
    
    public GlideRequest<TranscodeType> load(final String s) {
        return (GlideRequest)super.load(s);
    }
    
    @Deprecated
    public GlideRequest<TranscodeType> load(final URL url) {
        return (GlideRequest)super.load(url);
    }
    
    public GlideRequest<TranscodeType> load(final byte[] array) {
        return (GlideRequest)super.load(array);
    }
    
    public GlideRequest<TranscodeType> lock() {
        return (GlideRequest)super.lock();
    }
    
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(final boolean b) {
        return (GlideRequest)super.onlyRetrieveFromCache(b);
    }
    
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest)super.optionalCenterCrop();
    }
    
    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest)super.optionalCenterInside();
    }
    
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest)super.optionalCircleCrop();
    }
    
    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest)super.optionalFitCenter();
    }
    
    public GlideRequest<TranscodeType> optionalTransform(final Transformation<Bitmap> transformation) {
        return (GlideRequest)super.optionalTransform((Transformation)transformation);
    }
    
    public <Y> GlideRequest<TranscodeType> optionalTransform(final Class<Y> clazz, final Transformation<Y> transformation) {
        return (GlideRequest)super.optionalTransform((Class)clazz, (Transformation)transformation);
    }
    
    public GlideRequest<TranscodeType> override(final int n) {
        return (GlideRequest)super.override(n);
    }
    
    public GlideRequest<TranscodeType> override(final int n, final int n2) {
        return (GlideRequest)super.override(n, n2);
    }
    
    public GlideRequest<TranscodeType> placeholder(final int n) {
        return (GlideRequest)super.placeholder(n);
    }
    
    public GlideRequest<TranscodeType> placeholder(final Drawable drawable) {
        return (GlideRequest)super.placeholder(drawable);
    }
    
    public GlideRequest<TranscodeType> priority(final Priority priority) {
        return (GlideRequest)super.priority(priority);
    }
    
    public <Y> GlideRequest<TranscodeType> set(final Option<Y> option, final Y y) {
        return (GlideRequest)super.set((Option)option, (Object)y);
    }
    
    public GlideRequest<TranscodeType> signature(final Key key) {
        return (GlideRequest)super.signature(key);
    }
    
    public GlideRequest<TranscodeType> sizeMultiplier(final float n) {
        return (GlideRequest)super.sizeMultiplier(n);
    }
    
    public GlideRequest<TranscodeType> skipMemoryCache(final boolean b) {
        return (GlideRequest)super.skipMemoryCache(b);
    }
    
    public GlideRequest<TranscodeType> theme(final Resources$Theme resources$Theme) {
        return (GlideRequest)super.theme(resources$Theme);
    }
    
    public GlideRequest<TranscodeType> thumbnail(final float n) {
        return (GlideRequest)super.thumbnail(n);
    }
    
    public GlideRequest<TranscodeType> thumbnail(final RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest)super.thumbnail((RequestBuilder)requestBuilder);
    }
    
    public GlideRequest<TranscodeType> thumbnail(final List<RequestBuilder<TranscodeType>> list) {
        return (GlideRequest)super.thumbnail((List)list);
    }
    
    @SafeVarargs
    public final GlideRequest<TranscodeType> thumbnail(final RequestBuilder<TranscodeType>... array) {
        return (GlideRequest)super.thumbnail((RequestBuilder[])array);
    }
    
    public GlideRequest<TranscodeType> timeout(final int n) {
        return (GlideRequest)super.timeout(n);
    }
    
    public GlideRequest<TranscodeType> transform(final Transformation<Bitmap> transformation) {
        return (GlideRequest)super.transform((Transformation)transformation);
    }
    
    public <Y> GlideRequest<TranscodeType> transform(final Class<Y> clazz, final Transformation<Y> transformation) {
        return (GlideRequest)super.transform((Class)clazz, (Transformation)transformation);
    }
    
    public GlideRequest<TranscodeType> transform(final Transformation<Bitmap>... array) {
        return (GlideRequest)super.transform((Transformation[])array);
    }
    
    @Deprecated
    public GlideRequest<TranscodeType> transforms(final Transformation<Bitmap>... array) {
        return (GlideRequest)super.transforms((Transformation[])array);
    }
    
    public GlideRequest<TranscodeType> transition(final TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        return (GlideRequest)super.transition((TransitionOptions)transitionOptions);
    }
    
    public GlideRequest<TranscodeType> useAnimationPool(final boolean b) {
        return (GlideRequest)super.useAnimationPool(b);
    }
    
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(final boolean b) {
        return (GlideRequest)super.useUnlimitedSourceGeneratorsPool(b);
    }
}
