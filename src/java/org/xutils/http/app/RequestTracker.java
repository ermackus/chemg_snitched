package org.xutils.http.app;

import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

public interface RequestTracker
{
    void onCache(final UriRequest p0, final Object p1);
    
    void onCancelled(final UriRequest p0);
    
    void onError(final UriRequest p0, final Throwable p1, final boolean p2);
    
    void onFinished(final UriRequest p0);
    
    void onRequestCreated(final UriRequest p0);
    
    void onStart(final RequestParams p0);
    
    void onSuccess(final UriRequest p0, final Object p1);
    
    void onWaiting(final RequestParams p0);
}
