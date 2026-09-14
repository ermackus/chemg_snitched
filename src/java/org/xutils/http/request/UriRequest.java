package org.xutils.http.request;

import org.xutils.common.util.LogUtil;
import org.xutils.x;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.IOException;
import org.xutils.http.loader.LoaderFactory;
import java.lang.reflect.Type;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.ProgressHandler;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.Loader;
import java.io.Closeable;

public abstract class UriRequest implements Closeable
{
    protected ClassLoader callingClassLoader;
    protected final Loader<?> loader;
    protected final RequestParams params;
    protected ProgressHandler progressHandler;
    protected final String queryUrl;
    protected RequestInterceptListener requestInterceptListener;
    
    UriRequest(final RequestParams params, final Type type) throws Throwable {
        this.callingClassLoader = null;
        this.progressHandler = null;
        this.requestInterceptListener = null;
        this.params = params;
        this.queryUrl = this.buildQueryUrl(params);
        this.loader = LoaderFactory.getLoader(type, params);
    }
    
    protected String buildQueryUrl(final RequestParams requestParams) {
        return requestParams.getUri();
    }
    
    public abstract void clearCacheHeader();
    
    public abstract void close() throws IOException;
    
    public abstract String getCacheKey();
    
    public abstract long getContentLength();
    
    public abstract String getETag();
    
    public abstract long getExpiration();
    
    public abstract long getHeaderFieldDate(final String p0, final long p1);
    
    public abstract InputStream getInputStream() throws IOException;
    
    public abstract long getLastModified();
    
    public RequestParams getParams() {
        return this.params;
    }
    
    public String getRequestUri() {
        return this.queryUrl;
    }
    
    public abstract int getResponseCode() throws IOException;
    
    public abstract String getResponseHeader(final String p0);
    
    public abstract Map<String, List<String>> getResponseHeaders();
    
    public abstract String getResponseMessage() throws IOException;
    
    public abstract boolean isLoading();
    
    public Object loadResult() throws Throwable {
        return this.loader.load(this);
    }
    
    public abstract Object loadResultFromCache() throws Throwable;
    
    public void save2Cache() {
        x.task().run((Runnable)new Runnable(this) {
            final UriRequest this$0;
            
            public void run() {
                try {
                    this.this$0.loader.save2Cache(this.this$0);
                }
                finally {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                }
            }
        });
    }
    
    public abstract void sendRequest() throws Throwable;
    
    public void setCallingClassLoader(final ClassLoader callingClassLoader) {
        this.callingClassLoader = callingClassLoader;
    }
    
    public void setProgressHandler(final ProgressHandler progressHandler) {
        this.progressHandler = progressHandler;
        this.loader.setProgressHandler(progressHandler);
    }
    
    public void setRequestInterceptListener(final RequestInterceptListener requestInterceptListener) {
        this.requestInterceptListener = requestInterceptListener;
    }
    
    @Override
    public String toString() {
        return this.getRequestUri();
    }
}
