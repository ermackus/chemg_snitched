package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;

public final class c implements HttpRequestInterceptor
{
    public final void process(final HttpRequest httpRequest, final HttpContext httpContext) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("This thread forbids HTTP requests");
        }
    }
}
