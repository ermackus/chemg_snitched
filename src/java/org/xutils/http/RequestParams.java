package org.xutils.http;

import org.xutils.common.util.LogUtil;
import java.io.IOException;
import org.xutils.http.body.RequestBody;
import android.text.TextUtils;
import java.util.List;
import java.io.File;
import org.xutils.http.app.DefaultParamsBuilder;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.app.RedirectHandler;
import java.net.Proxy;
import org.xutils.common.task.Priority;
import org.xutils.http.app.HttpRetryHandler;
import org.xutils.http.annotation.HttpRequest;
import java.util.concurrent.Executor;
import org.xutils.http.app.ParamsBuilder;

public class RequestParams extends BaseParams
{
    private boolean autoRename;
    private boolean autoResume;
    private String buildCacheKey;
    private String buildUri;
    private ParamsBuilder builder;
    private String cacheDirName;
    private final String[] cacheKeys;
    private long cacheMaxAge;
    private long cacheSize;
    private boolean cancelFast;
    private int connectTimeout;
    private Executor executor;
    private HttpRequest httpRequest;
    private HttpRetryHandler httpRetryHandler;
    private boolean invokedGetHttpRequest;
    private int loadingUpdateMaxTimeSpan;
    private int maxRetryCount;
    private Priority priority;
    private Proxy proxy;
    private int readTimeout;
    private RedirectHandler redirectHandler;
    private RequestTracker requestTracker;
    private String saveFilePath;
    private final String[] signs;
    private SSLSocketFactory sslSocketFactory;
    private String uri;
    private boolean useCookie;
    
    public RequestParams() {
        this(null, null, null, null);
    }
    
    public RequestParams(final String s) {
        this(s, null, null, null);
    }
    
    public RequestParams(final String uri, final ParamsBuilder paramsBuilder, final String[] signs, final String[] cacheKeys) {
        this.useCookie = true;
        this.priority = Priority.DEFAULT;
        this.connectTimeout = 15000;
        this.readTimeout = 15000;
        this.autoResume = true;
        this.autoRename = false;
        this.maxRetryCount = 2;
        this.cancelFast = false;
        this.loadingUpdateMaxTimeSpan = 300;
        this.invokedGetHttpRequest = false;
        ParamsBuilder builder = paramsBuilder;
        if (uri != null && (builder = paramsBuilder) == null) {
            builder = new DefaultParamsBuilder();
        }
        this.uri = uri;
        this.signs = signs;
        this.cacheKeys = cacheKeys;
        this.builder = builder;
    }
    
    private HttpRequest getHttpRequest() {
        if (this.httpRequest == null && !this.invokedGetHttpRequest) {
            this.invokedGetHttpRequest = true;
            final Class<? extends RequestParams> class1 = this.getClass();
            if (class1 != RequestParams.class) {
                this.httpRequest = class1.getAnnotation(HttpRequest.class);
            }
        }
        return this.httpRequest;
    }
    
    private void initEntityParams() {
        RequestParamsHelper.parseKV(this, this.getClass(), (RequestParamsHelper.ParseKVListener)new RequestParamsHelper.ParseKVListener(this) {
            final RequestParams this$0;
            
            @Override
            public void onParseKV(final String s, final Object o) {
                this.this$0.addParameter(s, o);
            }
        });
    }
    
    public String getCacheDirName() {
        return this.cacheDirName;
    }
    
    public String getCacheKey() {
        if (TextUtils.isEmpty((CharSequence)this.buildCacheKey) && this.builder != null) {
            final HttpRequest httpRequest = this.getHttpRequest();
            if (httpRequest != null) {
                this.buildCacheKey = this.builder.buildCacheKey(this, httpRequest.cacheKeys());
            }
            else {
                this.buildCacheKey = this.builder.buildCacheKey(this, this.cacheKeys);
            }
        }
        return this.buildCacheKey;
    }
    
    public long getCacheMaxAge() {
        return this.cacheMaxAge;
    }
    
    public long getCacheSize() {
        return this.cacheSize;
    }
    
    public int getConnectTimeout() {
        return this.connectTimeout;
    }
    
    public Executor getExecutor() {
        return this.executor;
    }
    
    public HttpRetryHandler getHttpRetryHandler() {
        return this.httpRetryHandler;
    }
    
    public int getLoadingUpdateMaxTimeSpan() {
        return this.loadingUpdateMaxTimeSpan;
    }
    
    public int getMaxRetryCount() {
        return this.maxRetryCount;
    }
    
    public Priority getPriority() {
        return this.priority;
    }
    
    public Proxy getProxy() {
        return this.proxy;
    }
    
    public int getReadTimeout() {
        return this.readTimeout;
    }
    
    public RedirectHandler getRedirectHandler() {
        return this.redirectHandler;
    }
    
    public RequestTracker getRequestTracker() {
        return this.requestTracker;
    }
    
    public String getSaveFilePath() {
        return this.saveFilePath;
    }
    
    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }
    
    public String getUri() {
        String s;
        if (TextUtils.isEmpty((CharSequence)this.buildUri)) {
            s = this.uri;
        }
        else {
            s = this.buildUri;
        }
        return s;
    }
    
    void init() throws Throwable {
        if (!TextUtils.isEmpty((CharSequence)this.buildUri)) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)this.uri) && this.getHttpRequest() == null) {
            throw new IllegalStateException("uri is empty && @HttpRequest == null");
        }
        this.initEntityParams();
        this.buildUri = this.uri;
        final HttpRequest httpRequest = this.getHttpRequest();
        if (httpRequest != null) {
            final ParamsBuilder builder = (ParamsBuilder)httpRequest.builder().newInstance();
            this.builder = builder;
            this.buildUri = builder.buildUri(this, httpRequest);
            this.builder.buildParams(this);
            this.builder.buildSign(this, httpRequest.signs());
            if (this.sslSocketFactory == null) {
                this.sslSocketFactory = this.builder.getSSLSocketFactory();
            }
        }
        else {
            final ParamsBuilder builder2 = this.builder;
            if (builder2 != null) {
                builder2.buildParams(this);
                this.builder.buildSign(this, this.signs);
                if (this.sslSocketFactory == null) {
                    this.sslSocketFactory = this.builder.getSSLSocketFactory();
                }
            }
        }
    }
    
    public boolean isAutoRename() {
        return this.autoRename;
    }
    
    public boolean isAutoResume() {
        return this.autoResume;
    }
    
    public boolean isCancelFast() {
        return this.cancelFast;
    }
    
    public boolean isUseCookie() {
        return this.useCookie;
    }
    
    public void setAutoRename(final boolean autoRename) {
        this.autoRename = autoRename;
    }
    
    public void setAutoResume(final boolean autoResume) {
        this.autoResume = autoResume;
    }
    
    public void setCacheDirName(final String cacheDirName) {
        this.cacheDirName = cacheDirName;
    }
    
    public void setCacheMaxAge(final long cacheMaxAge) {
        this.cacheMaxAge = cacheMaxAge;
    }
    
    public void setCacheSize(final long cacheSize) {
        this.cacheSize = cacheSize;
    }
    
    public void setCancelFast(final boolean cancelFast) {
        this.cancelFast = cancelFast;
    }
    
    public void setConnectTimeout(final int connectTimeout) {
        if (connectTimeout > 0) {
            this.connectTimeout = connectTimeout;
        }
    }
    
    public void setExecutor(final Executor executor) {
        this.executor = executor;
    }
    
    public void setHttpRetryHandler(final HttpRetryHandler httpRetryHandler) {
        this.httpRetryHandler = httpRetryHandler;
    }
    
    public void setLoadingUpdateMaxTimeSpan(final int loadingUpdateMaxTimeSpan) {
        this.loadingUpdateMaxTimeSpan = loadingUpdateMaxTimeSpan;
    }
    
    public void setMaxRetryCount(final int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    
    public void setPriority(final Priority priority) {
        this.priority = priority;
    }
    
    public void setProxy(final Proxy proxy) {
        this.proxy = proxy;
    }
    
    public void setReadTimeout(final int readTimeout) {
        if (readTimeout > 0) {
            this.readTimeout = readTimeout;
        }
    }
    
    public void setRedirectHandler(final RedirectHandler redirectHandler) {
        this.redirectHandler = redirectHandler;
    }
    
    public void setRequestTracker(final RequestTracker requestTracker) {
        this.requestTracker = requestTracker;
    }
    
    public void setSaveFilePath(final String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }
    
    public void setSslSocketFactory(final SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }
    
    public void setUri(final String s) {
        if (TextUtils.isEmpty((CharSequence)this.buildUri)) {
            this.uri = s;
        }
        else {
            this.buildUri = s;
        }
    }
    
    public void setUseCookie(final boolean useCookie) {
        this.useCookie = useCookie;
    }
    
    @Override
    public String toString() {
        try {
            this.init();
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        final String uri = this.getUri();
        String s;
        if (TextUtils.isEmpty((CharSequence)uri)) {
            s = super.toString();
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append(uri);
            String s2 = "?";
            if (uri.contains((CharSequence)"?")) {
                s2 = "&";
            }
            sb.append(s2);
            sb.append(super.toString());
            s = sb.toString();
        }
        return s;
    }
}
