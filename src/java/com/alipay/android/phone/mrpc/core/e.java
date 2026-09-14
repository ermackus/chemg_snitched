package com.alipay.android.phone.mrpc.core;

import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;

public final class e extends DefaultRedirectHandler
{
    public int a;
    public final d b;
    
    public e(final d b) {
        this.b = b;
    }
    
    public final boolean isRedirectRequested(final HttpResponse httpResponse, final HttpContext httpContext) {
        ++this.a;
        final boolean redirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (!redirectRequested && this.a < 5) {
            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 301 || statusCode == 302) {
                return true;
            }
        }
        return redirectRequested;
    }
}
