package org.xutils;

import org.xutils.common.Callback$TypedCallback;
import org.xutils.http.HttpMethod;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

public interface HttpManager
{
     <T> Callback.Cancelable get(final RequestParams p0, final Callback.CommonCallback<T> p1);
    
     <T> T getSync(final RequestParams p0, final Class<T> p1) throws Throwable;
    
     <T> Callback.Cancelable post(final RequestParams p0, final Callback.CommonCallback<T> p1);
    
     <T> T postSync(final RequestParams p0, final Class<T> p1) throws Throwable;
    
     <T> Callback.Cancelable request(final HttpMethod p0, final RequestParams p1, final Callback.CommonCallback<T> p2);
    
     <T> T requestSync(final HttpMethod p0, final RequestParams p1, final Class<T> p2) throws Throwable;
    
     <T> T requestSync(final HttpMethod p0, final RequestParams p1, final Callback$TypedCallback<T> p2) throws Throwable;
}
