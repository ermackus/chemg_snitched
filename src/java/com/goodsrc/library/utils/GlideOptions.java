package com.goodsrc.library.utils;

import android.content.res.Resources$Theme;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.DecodeFormat;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap$CompressFormat;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import android.graphics.Bitmap;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestOptions;

public final class GlideOptions extends RequestOptions implements Cloneable
{
    private static GlideOptions centerCropTransform2;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions noAnimation5;
    private static GlideOptions noTransformation4;
    
    public static GlideOptions bitmapTransform(final Transformation<Bitmap> transformation) {
        return new GlideOptions().transform(transformation);
    }
    
    public static GlideOptions centerCropTransform() {
        if (GlideOptions.centerCropTransform2 == null) {
            GlideOptions.centerCropTransform2 = new GlideOptions().centerCrop().autoClone();
        }
        return GlideOptions.centerCropTransform2;
    }
    
    public static GlideOptions centerInsideTransform() {
        if (GlideOptions.centerInsideTransform1 == null) {
            GlideOptions.centerInsideTransform1 = new GlideOptions().centerInside().autoClone();
        }
        return GlideOptions.centerInsideTransform1;
    }
    
    public static GlideOptions circleCropTransform() {
        if (GlideOptions.circleCropTransform3 == null) {
            GlideOptions.circleCropTransform3 = new GlideOptions().circleCrop().autoClone();
        }
        return GlideOptions.circleCropTransform3;
    }
    
    public static GlideOptions decodeTypeOf(final Class<?> clazz) {
        return new GlideOptions().decode(clazz);
    }
    
    public static GlideOptions diskCacheStrategyOf(final DiskCacheStrategy diskCacheStrategy) {
        return new GlideOptions().diskCacheStrategy(diskCacheStrategy);
    }
    
    public static GlideOptions downsampleOf(final DownsampleStrategy downsampleStrategy) {
        return new GlideOptions().downsample(downsampleStrategy);
    }
    
    public static GlideOptions encodeFormatOf(final Bitmap$CompressFormat bitmap$CompressFormat) {
        return new GlideOptions().encodeFormat(bitmap$CompressFormat);
    }
    
    public static GlideOptions encodeQualityOf(final int n) {
        return new GlideOptions().encodeQuality(n);
    }
    
    public static GlideOptions errorOf(final int n) {
        return new GlideOptions().error(n);
    }
    
    public static GlideOptions errorOf(final Drawable drawable) {
        return new GlideOptions().error(drawable);
    }
    
    public static GlideOptions fitCenterTransform() {
        if (GlideOptions.fitCenterTransform0 == null) {
            GlideOptions.fitCenterTransform0 = new GlideOptions().fitCenter().autoClone();
        }
        return GlideOptions.fitCenterTransform0;
    }
    
    public static GlideOptions formatOf(final DecodeFormat decodeFormat) {
        return new GlideOptions().format(decodeFormat);
    }
    
    public static GlideOptions frameOf(final long n) {
        return new GlideOptions().frame(n);
    }
    
    public static GlideOptions noAnimation() {
        if (GlideOptions.noAnimation5 == null) {
            GlideOptions.noAnimation5 = new GlideOptions().dontAnimate().autoClone();
        }
        return GlideOptions.noAnimation5;
    }
    
    public static GlideOptions noTransformation() {
        if (GlideOptions.noTransformation4 == null) {
            GlideOptions.noTransformation4 = new GlideOptions().dontTransform().autoClone();
        }
        return GlideOptions.noTransformation4;
    }
    
    public static <T> GlideOptions option(final Option<T> option, final T t) {
        return new GlideOptions().set(option, t);
    }
    
    public static GlideOptions overrideOf(final int n) {
        return new GlideOptions().override(n);
    }
    
    public static GlideOptions overrideOf(final int n, final int n2) {
        return new GlideOptions().override(n, n2);
    }
    
    public static GlideOptions placeholderOf(final int n) {
        return new GlideOptions().placeholder(n);
    }
    
    public static GlideOptions placeholderOf(final Drawable drawable) {
        return new GlideOptions().placeholder(drawable);
    }
    
    public static GlideOptions priorityOf(final Priority priority) {
        return new GlideOptions().priority(priority);
    }
    
    public static GlideOptions signatureOf(final Key key) {
        return new GlideOptions().signature(key);
    }
    
    public static GlideOptions sizeMultiplierOf(final float n) {
        return new GlideOptions().sizeMultiplier(n);
    }
    
    public static GlideOptions skipMemoryCacheOf(final boolean b) {
        return new GlideOptions().skipMemoryCache(b);
    }
    
    public static GlideOptions timeoutOf(final int n) {
        return new GlideOptions().timeout(n);
    }
    
    public GlideOptions apply(final BaseRequestOptions<?> baseRequestOptions) {
        return (GlideOptions)super.apply((BaseRequestOptions)baseRequestOptions);
    }
    
    public GlideOptions autoClone() {
        return (GlideOptions)super.autoClone();
    }
    
    public GlideOptions centerCrop() {
        return (GlideOptions)super.centerCrop();
    }
    
    public GlideOptions centerInside() {
        return (GlideOptions)super.centerInside();
    }
    
    public GlideOptions circleCrop() {
        return (GlideOptions)super.circleCrop();
    }
    
    public GlideOptions clone() {
        return (GlideOptions)super.clone();
    }
    
    public GlideOptions decode(final Class<?> clazz) {
        return (GlideOptions)super.decode((Class)clazz);
    }
    
    public GlideOptions disallowHardwareConfig() {
        return (GlideOptions)super.disallowHardwareConfig();
    }
    
    public GlideOptions diskCacheStrategy(final DiskCacheStrategy diskCacheStrategy) {
        return (GlideOptions)super.diskCacheStrategy(diskCacheStrategy);
    }
    
    public GlideOptions dontAnimate() {
        return (GlideOptions)super.dontAnimate();
    }
    
    public GlideOptions dontTransform() {
        return (GlideOptions)super.dontTransform();
    }
    
    public GlideOptions downsample(final DownsampleStrategy downsampleStrategy) {
        return (GlideOptions)super.downsample(downsampleStrategy);
    }
    
    public GlideOptions encodeFormat(final Bitmap$CompressFormat bitmap$CompressFormat) {
        return (GlideOptions)super.encodeFormat(bitmap$CompressFormat);
    }
    
    public GlideOptions encodeQuality(final int n) {
        return (GlideOptions)super.encodeQuality(n);
    }
    
    public GlideOptions error(final int n) {
        return (GlideOptions)super.error(n);
    }
    
    public GlideOptions error(final Drawable drawable) {
        return (GlideOptions)super.error(drawable);
    }
    
    public GlideOptions fallback(final int n) {
        return (GlideOptions)super.fallback(n);
    }
    
    public GlideOptions fallback(final Drawable drawable) {
        return (GlideOptions)super.fallback(drawable);
    }
    
    public GlideOptions fitCenter() {
        return (GlideOptions)super.fitCenter();
    }
    
    public GlideOptions format(final DecodeFormat decodeFormat) {
        return (GlideOptions)super.format(decodeFormat);
    }
    
    public GlideOptions frame(final long n) {
        return (GlideOptions)super.frame(n);
    }
    
    public GlideOptions lock() {
        return (GlideOptions)super.lock();
    }
    
    public GlideOptions onlyRetrieveFromCache(final boolean b) {
        return (GlideOptions)super.onlyRetrieveFromCache(b);
    }
    
    public GlideOptions optionalCenterCrop() {
        return (GlideOptions)super.optionalCenterCrop();
    }
    
    public GlideOptions optionalCenterInside() {
        return (GlideOptions)super.optionalCenterInside();
    }
    
    public GlideOptions optionalCircleCrop() {
        return (GlideOptions)super.optionalCircleCrop();
    }
    
    public GlideOptions optionalFitCenter() {
        return (GlideOptions)super.optionalFitCenter();
    }
    
    public GlideOptions optionalTransform(final Transformation<Bitmap> transformation) {
        return (GlideOptions)super.optionalTransform((Transformation)transformation);
    }
    
    public <Y> GlideOptions optionalTransform(final Class<Y> clazz, final Transformation<Y> transformation) {
        return (GlideOptions)super.optionalTransform((Class)clazz, (Transformation)transformation);
    }
    
    public GlideOptions override(final int n) {
        return (GlideOptions)super.override(n);
    }
    
    public GlideOptions override(final int n, final int n2) {
        return (GlideOptions)super.override(n, n2);
    }
    
    public GlideOptions placeholder(final int n) {
        return (GlideOptions)super.placeholder(n);
    }
    
    public GlideOptions placeholder(final Drawable drawable) {
        return (GlideOptions)super.placeholder(drawable);
    }
    
    public GlideOptions priority(final Priority priority) {
        return (GlideOptions)super.priority(priority);
    }
    
    public <Y> GlideOptions set(final Option<Y> option, final Y y) {
        return (GlideOptions)super.set((Option)option, (Object)y);
    }
    
    public GlideOptions signature(final Key key) {
        return (GlideOptions)super.signature(key);
    }
    
    public GlideOptions sizeMultiplier(final float n) {
        return (GlideOptions)super.sizeMultiplier(n);
    }
    
    public GlideOptions skipMemoryCache(final boolean b) {
        return (GlideOptions)super.skipMemoryCache(b);
    }
    
    public GlideOptions theme(final Resources$Theme resources$Theme) {
        return (GlideOptions)super.theme(resources$Theme);
    }
    
    public GlideOptions timeout(final int n) {
        return (GlideOptions)super.timeout(n);
    }
    
    public GlideOptions transform(final Transformation<Bitmap> transformation) {
        return (GlideOptions)super.transform((Transformation)transformation);
    }
    
    public <Y> GlideOptions transform(final Class<Y> clazz, final Transformation<Y> transformation) {
        return (GlideOptions)super.transform((Class)clazz, (Transformation)transformation);
    }
    
    @SafeVarargs
    public final GlideOptions transform(final Transformation<Bitmap>... array) {
        return (GlideOptions)super.transform((Transformation[])array);
    }
    
    @Deprecated
    @SafeVarargs
    public final GlideOptions transforms(final Transformation<Bitmap>... array) {
        return (GlideOptions)super.transforms((Transformation[])array);
    }
    
    public GlideOptions useAnimationPool(final boolean b) {
        return (GlideOptions)super.useAnimationPool(b);
    }
    
    public GlideOptions useUnlimitedSourceGeneratorsPool(final boolean b) {
        return (GlideOptions)super.useUnlimitedSourceGeneratorsPool(b);
    }
}
