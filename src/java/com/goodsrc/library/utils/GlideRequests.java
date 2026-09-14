package com.goodsrc.library.utils;

import com.bumptech.glide.request.BaseRequestOptions;
import java.net.URL;
import android.net.Uri;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.File;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.RequestListener;
import android.content.Context;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class GlideRequests extends RequestManager
{
    public GlideRequests(final Glide glide, final Lifecycle lifecycle, final RequestManagerTreeNode requestManagerTreeNode, final Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }
    
    public GlideRequests addDefaultRequestListener(final RequestListener<Object> requestListener) {
        return (GlideRequests)super.addDefaultRequestListener((RequestListener)requestListener);
    }
    
    public GlideRequests applyDefaultRequestOptions(final RequestOptions requestOptions) {
        synchronized (this) {
            return (GlideRequests)super.applyDefaultRequestOptions(requestOptions);
        }
    }
    
    public <ResourceType> GlideRequest<ResourceType> as(final Class<ResourceType> clazz) {
        return new GlideRequest<ResourceType>(this.glide, this, clazz, this.context);
    }
    
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest)super.asBitmap();
    }
    
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest)super.asDrawable();
    }
    
    public GlideRequest<File> asFile() {
        return (GlideRequest)super.asFile();
    }
    
    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest)super.asGif();
    }
    
    public GlideRequest<File> download(final Object o) {
        return (GlideRequest)super.download(o);
    }
    
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest)super.downloadOnly();
    }
    
    public GlideRequest<Drawable> load(final Bitmap bitmap) {
        return (GlideRequest)super.load(bitmap);
    }
    
    public GlideRequest<Drawable> load(final Drawable drawable) {
        return (GlideRequest)super.load(drawable);
    }
    
    public GlideRequest<Drawable> load(final Uri uri) {
        return (GlideRequest)super.load(uri);
    }
    
    public GlideRequest<Drawable> load(final File file) {
        return (GlideRequest)super.load(file);
    }
    
    public GlideRequest<Drawable> load(final Integer n) {
        return (GlideRequest)super.load(n);
    }
    
    public GlideRequest<Drawable> load(final Object o) {
        return (GlideRequest)super.load(o);
    }
    
    public GlideRequest<Drawable> load(final String s) {
        return (GlideRequest)super.load(s);
    }
    
    @Deprecated
    public GlideRequest<Drawable> load(final URL url) {
        return (GlideRequest)super.load(url);
    }
    
    public GlideRequest<Drawable> load(final byte[] array) {
        return (GlideRequest)super.load(array);
    }
    
    public GlideRequests setDefaultRequestOptions(final RequestOptions defaultRequestOptions) {
        synchronized (this) {
            return (GlideRequests)super.setDefaultRequestOptions(defaultRequestOptions);
        }
    }
    
    protected void setRequestOptions(final RequestOptions requestOptions) {
        if (requestOptions instanceof GlideOptions) {
            super.setRequestOptions(requestOptions);
        }
        else {
            super.setRequestOptions((RequestOptions)new GlideOptions().apply((BaseRequestOptions<?>)requestOptions));
        }
    }
}
