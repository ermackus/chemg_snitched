package com.kingagroot.kingdraw.utils;

import org.xutils.x;
import org.xutils.ImageManager;
import org.xutils.image.ImageOptions$Builder;
import android.content.Context;
import com.goodsrc.library.utils.DisplayUtil;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.bumptech.glide.request.target.Target;
import android.widget.ImageView$ScaleType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.Glide;
import com.goodsrc.library.core.LibraryApplication;
import com.bumptech.glide.RequestBuilder;
import org.xutils.image.ImageOptions;
import android.graphics.drawable.Drawable;
import org.xutils.common.Callback$CommonCallback;
import android.widget.ImageView;

public class ImageLoader
{
    public static void bind(final ImageView imageView, final String s) {
        bind(imageView, getFormatUrl(s), options(), null);
    }
    
    public static void bind(final ImageView imageView, final String s, final int n, final int n2) {
        bind(imageView, getFormatUrl(s, n, n2), null, null);
    }
    
    public static void bind(final ImageView imageView, final String s, final Callback$CommonCallback<Drawable> callback$CommonCallback) {
        bind(imageView, getFormatUrl(s), options(), callback$CommonCallback);
    }
    
    public static void bind(final ImageView imageView, final String s, final ImageOptions imageOptions) {
        bind(imageView, getFormatUrl(s), imageOptions, null);
    }
    
    private static void bind(final ImageView imageView, final String s, final ImageOptions imageOptions, final Callback$CommonCallback<Drawable> callback$CommonCallback) {
        bind(imageView, s, imageOptions, true, callback$CommonCallback);
    }
    
    private static void bind(final ImageView imageView, final String s, final ImageOptions imageOptions, final boolean b, final Callback$CommonCallback<Drawable> callback$CommonCallback) {
        final RequestBuilder requestBuilder = (RequestBuilder)Glide.with(LibraryApplication.getContext()).load(s).error(imageOptions.getFailureDrawable(imageView));
        if (b) {
            requestBuilder.skipMemoryCache(false);
            requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
        }
        else {
            requestBuilder.skipMemoryCache(true);
            requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        RequestBuilder requestBuilder2;
        if (imageView.getScaleType() == ImageView$ScaleType.FIT_CENTER) {
            requestBuilder2 = (RequestBuilder)requestBuilder.fitCenter();
        }
        else {
            requestBuilder2 = requestBuilder;
            if (imageView.getScaleType() == ImageView$ScaleType.CENTER_CROP) {
                requestBuilder2 = (RequestBuilder)requestBuilder.centerCrop();
            }
        }
        if (callback$CommonCallback == null) {
            requestBuilder2.into(imageView);
        }
        else {
            requestBuilder2.into((Target)new ImageLoader$1((Callback$CommonCallback)callback$CommonCallback));
        }
    }
    
    public static void bind(final ImageView imageView, final String s, final boolean b) {
        bind(imageView, getFormatUrl(s), options(), b, null);
    }
    
    public static String getFormatUrl(final String s) {
        return getFormatUrl(s, 0, 0);
    }
    
    public static String getFormatUrl(final String s, int dip2px, int dip2px2) {
        if (isLocalPath(s)) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(NetConfig$BaseData.picDownLoad());
        sb.append("?fileOSSID=");
        sb.append(s);
        final StringBuilder sb2 = new StringBuilder(sb.toString());
        final Context context = LibraryApplication.getContext();
        dip2px = DisplayUtil.dip2px(context, (float)dip2px);
        dip2px2 = DisplayUtil.dip2px(context, (float)dip2px2);
        sb2.append("&width=");
        sb2.append(dip2px);
        sb2.append("&height=");
        sb2.append(dip2px2);
        return sb2.toString();
    }
    
    public static ImageOptions getOptions(final Context context, final int loadingDrawableId, final int failureDrawableId) {
        return new ImageOptions$Builder().setLoadingDrawableId(loadingDrawableId).setFailureDrawableId(failureDrawableId).build();
    }
    
    public static ImageManager image() {
        return x.image();
    }
    
    public static boolean isLocalPath(final String s) {
        boolean b = false;
        if (s != null) {
            if (!s.startsWith("/storage/") && !s.startsWith("/sdcard/") && !s.startsWith("assets/") && !s.startsWith("file:/") && !s.startsWith("/data/") && !s.startsWith("content:/")) {
                b = b;
                if (!s.startsWith("/mnt/")) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
    
    public static ImageOptions options() {
        return new ImageOptions$Builder().setLoadingDrawableId(2131231528).setFailureDrawableId(2131231528).build();
    }
}
