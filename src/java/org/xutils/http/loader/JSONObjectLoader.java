package org.xutils.http.loader;

import org.xutils.http.RequestParams;
import android.text.TextUtils;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;
import org.json.JSONObject;

class JSONObjectLoader extends Loader<JSONObject>
{
    private String charset;
    private String resultStr;
    
    JSONObjectLoader() {
        this.charset = "UTF-8";
        this.resultStr = null;
    }
    
    @Override
    public JSONObject load(final InputStream inputStream) throws Throwable {
        this.resultStr = IOUtil.readStr(inputStream, this.charset);
        return new JSONObject(this.resultStr);
    }
    
    @Override
    public JSONObject load(final UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return this.load(uriRequest.getInputStream());
    }
    
    @Override
    public JSONObject loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            final String textContent = diskCacheEntity.getTextContent();
            if (!TextUtils.isEmpty((CharSequence)textContent)) {
                return new JSONObject(textContent);
            }
        }
        return null;
    }
    
    @Override
    public Loader<JSONObject> newInstance() {
        return new JSONObjectLoader();
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
