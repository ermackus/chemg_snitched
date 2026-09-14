package org.xutils.http.loader;

import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;

class IntegerLoader extends Loader<Integer>
{
    @Override
    public Integer load(final InputStream inputStream) throws Throwable {
        return 100;
    }
    
    @Override
    public Integer load(final UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return uriRequest.getResponseCode();
    }
    
    @Override
    public Integer loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }
    
    @Override
    public Loader<Integer> newInstance() {
        return new IntegerLoader();
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
    }
}
