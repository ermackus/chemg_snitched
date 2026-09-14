package org.xutils.http.request;

import org.xutils.http.loader.FileLoader;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import java.io.File;
import java.lang.reflect.Type;
import org.xutils.http.RequestParams;
import java.io.InputStream;

public class LocalFileRequest extends UriRequest
{
    private InputStream inputStream;
    
    LocalFileRequest(final RequestParams requestParams, final Type type) throws Throwable {
        super(requestParams, type);
    }
    
    private File getFile() {
        String s;
        if (this.queryUrl.startsWith("file:")) {
            s = this.queryUrl.substring(5);
        }
        else {
            s = this.queryUrl;
        }
        return new File(s);
    }
    
    @Override
    public void clearCacheHeader() {
    }
    
    @Override
    public void close() throws IOException {
        IOUtil.closeQuietly((Closeable)this.inputStream);
        this.inputStream = null;
    }
    
    @Override
    public String getCacheKey() {
        return null;
    }
    
    @Override
    public long getContentLength() {
        return this.getFile().length();
    }
    
    @Override
    public String getETag() {
        return null;
    }
    
    @Override
    public long getExpiration() {
        return -1L;
    }
    
    @Override
    public long getHeaderFieldDate(final String s, final long n) {
        return n;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        if (this.inputStream == null) {
            this.inputStream = (InputStream)new FileInputStream(this.getFile());
        }
        return this.inputStream;
    }
    
    @Override
    public long getLastModified() {
        return this.getFile().lastModified();
    }
    
    @Override
    public int getResponseCode() throws IOException {
        int n;
        if (this.getFile().exists()) {
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
        if (this.loader instanceof FileLoader) {
            return this.getFile();
        }
        return this.loader.load(this);
    }
    
    @Override
    public Object loadResultFromCache() throws Throwable {
        return null;
    }
    
    @Override
    public void save2Cache() {
    }
    
    @Override
    public void sendRequest() throws Throwable {
    }
}
