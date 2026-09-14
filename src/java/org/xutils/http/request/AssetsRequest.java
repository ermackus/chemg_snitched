package org.xutils.http.request;

import java.util.Date;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.LogUtil;
import java.io.File;
import org.xutils.x;
import java.io.IOException;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import java.lang.reflect.Type;
import org.xutils.http.RequestParams;
import java.io.InputStream;

public class AssetsRequest extends UriRequest
{
    private long contentLength;
    private InputStream inputStream;
    
    public AssetsRequest(final RequestParams requestParams, final Type type) throws Throwable {
        super(requestParams, type);
        this.contentLength = 0L;
    }
    
    @Override
    public void clearCacheHeader() {
    }
    
    @Override
    public void close() throws IOException {
        IOUtil.closeQuietly((Closeable)this.inputStream);
        this.inputStream = null;
    }
    
    protected long getAssetsLastModified() {
        return new File(x.app().getApplicationInfo().sourceDir).lastModified();
    }
    
    @Override
    public String getCacheKey() {
        return this.queryUrl;
    }
    
    @Override
    public long getContentLength() {
        try {
            this.getInputStream();
            return this.contentLength;
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            return 0L;
        }
    }
    
    @Override
    public String getETag() {
        return null;
    }
    
    @Override
    public long getExpiration() {
        return Long.MAX_VALUE;
    }
    
    @Override
    public long getHeaderFieldDate(final String s, final long n) {
        return n;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        if (this.inputStream == null && this.callingClassLoader != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("assets/");
            sb.append(this.queryUrl.substring(9));
            final InputStream resourceAsStream = this.callingClassLoader.getResourceAsStream(sb.toString());
            this.inputStream = resourceAsStream;
            this.contentLength = resourceAsStream.available();
        }
        return this.inputStream;
    }
    
    @Override
    public long getLastModified() {
        return this.getAssetsLastModified();
    }
    
    @Override
    public int getResponseCode() throws IOException {
        int n;
        if (this.getInputStream() != null) {
            n = 200;
        }
        else {
            n = 404;
        }
        return n;
    }
    
    @Override
    public String getResponseHeader(final String s) {
        return null;
    }
    
    @Override
    public Map<String, List<String>> getResponseHeaders() {
        return null;
    }
    
    @Override
    public String getResponseMessage() throws IOException {
        return null;
    }
    
    @Override
    public boolean isLoading() {
        return true;
    }
    
    @Override
    public Object loadResult() throws Throwable {
        return this.loader.load(this);
    }
    
    @Override
    public Object loadResultFromCache() throws Throwable {
        final DiskCacheEntity value = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(this.getCacheKey());
        if (value != null) {
            final Date lastModify = value.getLastModify();
            if (lastModify != null) {
                if (lastModify.getTime() >= this.getAssetsLastModified()) {
                    return this.loader.loadFromCache(value);
                }
            }
        }
        return null;
    }
    
    @Override
    public void sendRequest() throws Throwable {
    }
}
