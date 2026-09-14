package com.alipay.android.phone.mrpc.core;

import javax.net.ssl.SSLException;
import java.net.SocketException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.protocol.HttpContext;
import java.io.IOException;
import org.apache.http.client.HttpRequestRetryHandler;

public class ad implements HttpRequestRetryHandler
{
    public static final String a;
    
    static {
        a = ad.class.getSimpleName();
    }
    
    public boolean retryRequest(final IOException ex, final int n, final HttpContext httpContext) {
        return n < 3 && (ex instanceof NoHttpResponseException || ((ex instanceof SocketException || ex instanceof SSLException) && ex.getMessage() != null && ex.getMessage().contains((CharSequence)"Broken pipe")));
    }
}
