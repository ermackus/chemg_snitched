package com.kingagroot.kingdraw.utils;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import android.widget.ImageView;
import android.content.Context;
import com.luck.picture.lib.engine.ImageEngine;

public class GlideEngine implements ImageEngine
{
    private GlideEngine() {
    }
    
    public static GlideEngine createGlideEngine() {
        return GlideEngine.GlideEngine$InstanceHolder.instance;
    }
    
    public void loadAlbumCover(final Context context, final String s, final ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        ((RequestBuilder)((RequestBuilder)((RequestBuilder)((RequestBuilder)Glide.with(context).asBitmap().load(s).override(180, 180)).sizeMultiplier(0.5f)).transform(new Transformation[] { (Transformation)new CenterCrop(), (Transformation)new RoundedCorners(8) })).placeholder(2131231615)).into(imageView);
    }
    
    public void loadGridImage(final Context context, final String s, final ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        ((RequestBuilder)((RequestBuilder)((RequestBuilder)Glide.with(context).load(s).override(200, 200)).centerCrop()).placeholder(2131231615)).into(imageView);
    }
    
    public void loadImage(final Context context, final ImageView imageView, final String s, final int n, final int n2) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        ((RequestBuilder)Glide.with(context).load(s).override(n, n2)).into(imageView);
    }
    
    public void loadImage(final Context context, final String s, final ImageView imageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context).load(s).into(imageView);
    }
    
    public void pauseRequests(final Context context) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context).pauseRequests();
    }
    
    public void resumeRequests(final Context context) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        Glide.with(context).resumeRequests();
    }
}
