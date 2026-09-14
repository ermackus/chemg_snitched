package org.xutils.http;

import org.xutils.common.util.LogUtil;
import org.xutils.http.request.UriRequest;
import org.xutils.http.app.RequestTracker;

final class RequestTrackerWrapper implements RequestTracker
{
    private final RequestTracker base;
    
    public RequestTrackerWrapper(final RequestTracker base) {
        this.base = base;
    }
    
    @Override
    public void onCache(final UriRequest uriRequest, final Object o) {
        try {
            this.base.onCache(uriRequest, o);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onCancelled(final UriRequest uriRequest) {
        try {
            this.base.onCancelled(uriRequest);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onError(final UriRequest uriRequest, final Throwable t, final boolean b) {
        try {
            this.base.onError(uriRequest, t, b);
        }
        finally {
            final Throwable t2;
            LogUtil.e(t2.getMessage(), t2);
        }
    }
    
    @Override
    public void onFinished(final UriRequest uriRequest) {
        try {
            this.base.onFinished(uriRequest);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onRequestCreated(final UriRequest uriRequest) {
        try {
            this.base.onRequestCreated(uriRequest);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onStart(final RequestParams requestParams) {
        try {
            this.base.onStart(requestParams);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onSuccess(final UriRequest uriRequest, final Object o) {
        try {
            this.base.onSuccess(uriRequest, o);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    @Override
    public void onWaiting(final RequestParams requestParams) {
        try {
            this.base.onWaiting(requestParams);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
}
