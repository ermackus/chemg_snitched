package com.alipay.android.phone.mrpc.core;

import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;

public final class f implements ConnectionKeepAliveStrategy
{
    public final d a;
    
    public f(final d a) {
        this.a = a;
    }
    
    public final long getKeepAliveDuration(final HttpResponse httpResponse, final HttpContext httpContext) {
        return 180000L;
    }
}
