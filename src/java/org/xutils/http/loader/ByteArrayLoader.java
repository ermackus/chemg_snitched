package org.xutils.http.loader;

import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.request.UriRequest;
import java.io.InputStream;

class ByteArrayLoader extends Loader<byte[]>
{
    @Override
    public byte[] load(final InputStream inputStream) throws Throwable {
        return IOUtil.readBytes(inputStream);
    }
    
    @Override
    public byte[] load(final UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return this.load(uriRequest.getInputStream());
    }
    
    @Override
    public byte[] loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }
    
    @Override
    public Loader<byte[]> newInstance() {
        return new ByteArrayLoader();
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
    }
}
