package org.xutils.http;

import org.xutils.common.Callback$TypedCallback;
import org.xutils.common.task.AbsTask;
import org.xutils.common.Callback;
import org.xutils.x;
import org.xutils.HttpManager;

public final class HttpManagerImpl implements HttpManager
{
    private static volatile HttpManagerImpl instance;
    private static final Object lock;
    
    static {
        lock = new Object();
    }
    
    private HttpManagerImpl() {
    }
    
    public static void registerInstance() {
        if (HttpManagerImpl.instance == null) {
            final Object lock = HttpManagerImpl.lock;
            synchronized (lock) {
                if (HttpManagerImpl.instance == null) {
                    HttpManagerImpl.instance = new HttpManagerImpl();
                }
            }
        }
        x.Ext.setHttpManager(HttpManagerImpl.instance);
    }
    
    @Override
    public <T> Callback.Cancelable get(final RequestParams requestParams, final Callback.CommonCallback<T> commonCallback) {
        return this.request(HttpMethod.GET, requestParams, commonCallback);
    }
    
    @Override
    public <T> T getSync(final RequestParams requestParams, final Class<T> clazz) throws Throwable {
        return this.requestSync(HttpMethod.GET, requestParams, clazz);
    }
    
    @Override
    public <T> Callback.Cancelable post(final RequestParams requestParams, final Callback.CommonCallback<T> commonCallback) {
        return this.request(HttpMethod.POST, requestParams, commonCallback);
    }
    
    @Override
    public <T> T postSync(final RequestParams requestParams, final Class<T> clazz) throws Throwable {
        return this.requestSync(HttpMethod.POST, requestParams, clazz);
    }
    
    @Override
    public <T> Callback.Cancelable request(final HttpMethod method, final RequestParams requestParams, final Callback.CommonCallback<T> commonCallback) {
        requestParams.setMethod(method);
        Callback.Cancelable cancelable;
        if (commonCallback instanceof Callback.Cancelable) {
            cancelable = (Callback.Cancelable)commonCallback;
        }
        else {
            cancelable = null;
        }
        return x.task().start((AbsTask<Object>)new HttpTask(requestParams, cancelable, (Callback.CommonCallback)commonCallback));
    }
    
    @Override
    public <T> T requestSync(final HttpMethod httpMethod, final RequestParams requestParams, final Class<T> clazz) throws Throwable {
        return this.requestSync(httpMethod, requestParams, (org.xutils.common.Callback$TypedCallback<T>)new HttpManagerImpl.HttpManagerImpl$DefaultSyncCallback(this, (Class)clazz));
    }
    
    @Override
    public <T> T requestSync(final HttpMethod method, final RequestParams requestParams, final Callback$TypedCallback<T> callback$TypedCallback) throws Throwable {
        requestParams.setMethod(method);
        return x.task().startSync((AbsTask<T>)new HttpTask(requestParams, (Callback.Cancelable)null, (Callback.CommonCallback)callback$TypedCallback));
    }
}
