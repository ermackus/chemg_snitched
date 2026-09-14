package org.xutils.http.loader;

import org.xutils.cache.LruDiskCache;
import java.util.Date;
import android.text.TextUtils;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;
import org.xutils.http.ProgressHandler;
import org.xutils.http.RequestParams;

public abstract class Loader<T>
{
    protected RequestParams params;
    protected ProgressHandler progressHandler;
    
    public abstract T load(final InputStream p0) throws Throwable;
    
    public abstract T load(final UriRequest p0) throws Throwable;
    
    public abstract T loadFromCache(final DiskCacheEntity p0) throws Throwable;
    
    public abstract Loader<T> newInstance();
    
    public abstract void save2Cache(final UriRequest p0);
    
    protected void saveStringCache(final UriRequest uriRequest, final String textContent) {
        if (!TextUtils.isEmpty((CharSequence)textContent)) {
            final DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
            diskCacheEntity.setKey(uriRequest.getCacheKey());
            diskCacheEntity.setLastAccess(System.currentTimeMillis());
            diskCacheEntity.setEtag(uriRequest.getETag());
            diskCacheEntity.setExpires(uriRequest.getExpiration());
            diskCacheEntity.setLastModify(new Date(uriRequest.getLastModified()));
            diskCacheEntity.setTextContent(textContent);
            LruDiskCache.getDiskCache(uriRequest.getParams().getCacheDirName()).put(diskCacheEntity);
        }
    }
    
    public void setParams(final RequestParams params) {
        this.params = params;
    }
    
    public void setProgressHandler(final ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
    }
}
