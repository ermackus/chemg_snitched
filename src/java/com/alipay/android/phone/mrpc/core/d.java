package com.alipay.android.phone.mrpc.core;

import org.apache.http.client.RedirectHandler;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.params.HttpParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

public final class d extends DefaultHttpClient
{
    public final b a;
    
    public d(final b a, final ClientConnectionManager clientConnectionManager, final HttpParams httpParams) {
        this.a = a;
        super(clientConnectionManager, httpParams);
    }
    
    public final ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return (ConnectionKeepAliveStrategy)new f(this);
    }
    
    public final HttpContext createHttpContext() {
        final BasicHttpContext basicHttpContext = new BasicHttpContext();
        ((HttpContext)basicHttpContext).setAttribute("http.authscheme-registry", (Object)this.getAuthSchemes());
        ((HttpContext)basicHttpContext).setAttribute("http.cookiespec-registry", (Object)this.getCookieSpecs());
        ((HttpContext)basicHttpContext).setAttribute("http.auth.credentials-provider", (Object)this.getCredentialsProvider());
        return (HttpContext)basicHttpContext;
    }
    
    public final BasicHttpProcessor createHttpProcessor() {
        final BasicHttpProcessor httpProcessor = super.createHttpProcessor();
        httpProcessor.addRequestInterceptor(b.a());
        httpProcessor.addRequestInterceptor((HttpRequestInterceptor)this.a.new a((byte)0));
        return httpProcessor;
    }
    
    public final RedirectHandler createRedirectHandler() {
        return (RedirectHandler)new e(this);
    }
}
