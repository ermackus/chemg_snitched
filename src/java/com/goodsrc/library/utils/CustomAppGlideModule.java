package com.goodsrc.library.utils;

import com.bumptech.glide.load.engine.cache.DiskCache$Factory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.GlideBuilder;
import android.content.Context;
import com.bumptech.glide.module.AppGlideModule;

public class CustomAppGlideModule extends AppGlideModule
{
    public void applyOptions(final Context context, final GlideBuilder glideBuilder) {
        glideBuilder.setMemoryCache((MemoryCache)new LruResourceCache((long)20971520)).setDiskCache((DiskCache$Factory)new InternalCacheDiskCacheFactory(context, (long)104857600));
    }
    
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
