package com.goodsrc.library.utils;

import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.app.Fragment;
import android.app.Activity;
import com.bumptech.glide.GlideBuilder;
import java.io.File;
import android.content.Context;
import com.bumptech.glide.Glide;

public final class GlideApp
{
    private GlideApp() {
    }
    
    public static void enableHardwareBitmaps() {
        Glide.enableHardwareBitmaps();
    }
    
    public static Glide get(final Context context) {
        return Glide.get(context);
    }
    
    public static File getPhotoCacheDir(final Context context) {
        return Glide.getPhotoCacheDir(context);
    }
    
    public static File getPhotoCacheDir(final Context context, final String s) {
        return Glide.getPhotoCacheDir(context, s);
    }
    
    public static void init(final Context context, final GlideBuilder glideBuilder) {
        Glide.init(context, glideBuilder);
    }
    
    @Deprecated
    public static void init(final Glide glide) {
        Glide.init(glide);
    }
    
    public static void tearDown() {
        Glide.tearDown();
    }
    
    public static GlideRequests with(final Activity activity) {
        return (GlideRequests)Glide.with(activity);
    }
    
    @Deprecated
    public static GlideRequests with(final Fragment fragment) {
        return (GlideRequests)Glide.with(fragment);
    }
    
    public static GlideRequests with(final Context context) {
        return (GlideRequests)Glide.with(context);
    }
    
    public static GlideRequests with(final View view) {
        return (GlideRequests)Glide.with(view);
    }
    
    public static GlideRequests with(final androidx.fragment.app.Fragment fragment) {
        return (GlideRequests)Glide.with(fragment);
    }
    
    public static GlideRequests with(final FragmentActivity fragmentActivity) {
        return (GlideRequests)Glide.with(fragmentActivity);
    }
}
