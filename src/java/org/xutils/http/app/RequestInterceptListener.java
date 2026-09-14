package org.xutils.http.app;

import org.xutils.http.request.UriRequest;

public interface RequestInterceptListener
{
    void afterRequest(final UriRequest p0) throws Throwable;
    
    void beforeRequest(final UriRequest p0) throws Throwable;
}
