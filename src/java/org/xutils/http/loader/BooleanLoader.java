package org.xutils.http.loader;

import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;

class BooleanLoader extends Loader<Boolean>
{
    @Override
    public Boolean load(final InputStream inputStream) throws Throwable {
        return false;
    }
    
    @Override
    public Boolean load(final UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return uriRequest.getResponseCode() < 300;
    }
    
    @Override
    public Boolean loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }
    
    @Override
    public Loader<Boolean> newInstance() {
        return new BooleanLoader();
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
    }
}
