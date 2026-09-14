package org.xutils.http.loader;

import android.text.TextUtils;
import org.xutils.http.RequestParams;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;

class StringLoader extends Loader<String>
{
    private String charset;
    private String resultStr;
    
    StringLoader() {
        this.charset = "UTF-8";
        this.resultStr = null;
    }
    
    @Override
    public String load(final InputStream inputStream) throws Throwable {
        return this.resultStr = IOUtil.readStr(inputStream, this.charset);
    }
    
    @Override
    public String load(final UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return this.load(uriRequest.getInputStream());
    }
    
    @Override
    public String loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            return diskCacheEntity.getTextContent();
        }
        return null;
    }
    
    @Override
    public Loader<String> newInstance() {
        return new StringLoader();
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
        this.saveStringCache(uriRequest, this.resultStr);
    }
    
    @Override
    public void setParams(final RequestParams requestParams) {
        if (requestParams != null) {
            final String charset = requestParams.getCharset();
            if (!TextUtils.isEmpty((CharSequence)charset)) {
                this.charset = charset;
            }
        }
    }
}
