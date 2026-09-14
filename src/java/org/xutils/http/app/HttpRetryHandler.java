package org.xutils.http.app;

import org.xutils.http.HttpMethod;
import org.xutils.common.util.LogUtil;
import org.xutils.http.request.UriRequest;
import java.net.UnknownHostException;
import org.json.JSONException;
import java.io.FileNotFoundException;
import java.net.ProtocolException;
import java.net.PortUnreachableException;
import java.net.NoRouteToHostException;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import java.util.HashSet;

public class HttpRetryHandler
{
    protected static HashSet<Class<?>> blackList;
    protected int maxRetryCount;
    
    static {
        (HttpRetryHandler.blackList = (HashSet<Class<?>>)new HashSet()).add((Object)HttpException.class);
        HttpRetryHandler.blackList.add((Object)Callback.CancelledException.class);
        HttpRetryHandler.blackList.add((Object)MalformedURLException.class);
        HttpRetryHandler.blackList.add((Object)URISyntaxException.class);
        HttpRetryHandler.blackList.add((Object)NoRouteToHostException.class);
        HttpRetryHandler.blackList.add((Object)PortUnreachableException.class);
        HttpRetryHandler.blackList.add((Object)ProtocolException.class);
        HttpRetryHandler.blackList.add((Object)NullPointerException.class);
        HttpRetryHandler.blackList.add((Object)FileNotFoundException.class);
        HttpRetryHandler.blackList.add((Object)JSONException.class);
        HttpRetryHandler.blackList.add((Object)UnknownHostException.class);
        HttpRetryHandler.blackList.add((Object)IllegalArgumentException.class);
    }
    
    public HttpRetryHandler() {
        this.maxRetryCount = 2;
    }
    
    public boolean canRetry(final UriRequest uriRequest, final Throwable t, final int n) {
        LogUtil.w(t.getMessage(), t);
        if (n > this.maxRetryCount) {
            LogUtil.w(uriRequest.toString());
            LogUtil.w("The Max Retry times has been reached!");
            return false;
        }
        if (!HttpMethod.permitsRetry(uriRequest.getParams().getMethod())) {
            LogUtil.w(uriRequest.toString());
            LogUtil.w("The Request Method can not be retried.");
            return false;
        }
        if (HttpRetryHandler.blackList.contains((Object)t.getClass())) {
            LogUtil.w(uriRequest.toString());
            LogUtil.w("The Exception can not be retried.");
            return false;
        }
        return true;
    }
    
    public void setMaxRetryCount(final int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
}
