package org.xutils.http.app;

import javax.net.ssl.SSLSocketFactory;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.RequestParams;

public interface ParamsBuilder
{
    String buildCacheKey(final RequestParams p0, final String[] p1);
    
    void buildParams(final RequestParams p0) throws Throwable;
    
    void buildSign(final RequestParams p0, final String[] p1) throws Throwable;
    
    String buildUri(final RequestParams p0, final HttpRequest p1) throws Throwable;
    
    SSLSocketFactory getSSLSocketFactory() throws Throwable;
}
