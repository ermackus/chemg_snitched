package com.alipay.android.phone.mrpc.core;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.client.ResponseHandler;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import java.util.zip.GZIPOutputStream;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.HttpRequest;
import java.net.URI;
import android.util.Base64;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.Header;
import java.util.zip.GZIPInputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;
import java.security.Security;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import android.net.SSLSessionCache;
import android.net.SSLCertificateSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;

public final class b implements HttpClient
{
    public static long a = 160L;
    public static String[] b;
    public static final HttpRequestInterceptor c;
    public final HttpClient d;
    public RuntimeException e;
    public volatile b f;
    
    static {
        com.alipay.android.phone.mrpc.core.b.b = new String[] { "text/", "application/xml", "application/json" };
        c = (HttpRequestInterceptor)new c();
    }
    
    public b(final ClientConnectionManager clientConnectionManager, final HttpParams httpParams) {
        this.e = (RuntimeException)new IllegalStateException("AndroidHttpClient created and never closed");
        this.d = (HttpClient)new d(this, clientConnectionManager, httpParams);
    }
    
    public static /* synthetic */ b a(final b b) {
        return b.f;
    }
    
    public static b a(final String s) {
        final BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion((HttpParams)basicHttpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue((HttpParams)basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled((HttpParams)basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize((HttpParams)basicHttpParams, 8192);
        HttpClientParams.setRedirecting((HttpParams)basicHttpParams, true);
        HttpClientParams.setAuthenticating((HttpParams)basicHttpParams, false);
        HttpProtocolParams.setUserAgent((HttpParams)basicHttpParams, s);
        final SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", (SocketFactory)SSLCertificateSocketFactory.getHttpSocketFactory(30000, (SSLSessionCache)null), 443));
        final ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout((HttpParams)basicHttpParams, 60000L);
        ConnManagerParams.setMaxConnectionsPerRoute((HttpParams)basicHttpParams, (ConnPerRoute)new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections((HttpParams)basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier((HostnameVerifier)SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new b((ClientConnectionManager)threadSafeClientConnManager, (HttpParams)basicHttpParams);
    }
    
    public static InputStream a(final HttpEntity httpEntity) {
        final InputStream content = httpEntity.getContent();
        if (content == null) {
            return content;
        }
        final Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding == null) {
            return content;
        }
        final String value = contentEncoding.getValue();
        if (value == null) {
            return content;
        }
        Object o = content;
        if (value.contains((CharSequence)"gzip")) {
            o = new GZIPInputStream(content);
        }
        return (InputStream)o;
    }
    
    public static /* synthetic */ String a(final HttpUriRequest httpUriRequest) {
        final StringBuilder sb = new StringBuilder();
        sb.append("curl ");
        for (final Header header : httpUriRequest.getAllHeaders()) {
            if (!header.getName().equals((Object)"Authorization") && !header.getName().equals((Object)"Cookie")) {
                sb.append("--header \"");
                sb.append(header.toString().trim());
                sb.append("\" ");
            }
        }
        URI uri = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            final HttpRequest original = ((RequestWrapper)httpUriRequest).getOriginal();
            uri = uri;
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest)original).getURI();
            }
        }
        sb.append("\"");
        sb.append((Object)uri);
        sb.append("\"");
        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            final HttpEntity entity = ((HttpEntityEnclosingRequest)httpUriRequest).getEntity();
            if (entity != null && entity.isRepeatable()) {
                String s;
                if (entity.getContentLength() < 1024L) {
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    entity.writeTo((OutputStream)byteArrayOutputStream);
                    if (!b(httpUriRequest)) {
                        final String string = byteArrayOutputStream.toString();
                        sb.append(" --data-ascii \"");
                        sb.append(string);
                        sb.append("\"");
                        return sb.toString();
                    }
                    final String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                    final StringBuilder sb2 = new StringBuilder("echo '");
                    sb2.append(encodeToString);
                    sb2.append("' | base64 -d > /tmp/$$.bin; ");
                    sb.insert(0, sb2.toString());
                    s = " --data-binary @/tmp/$$.bin";
                }
                else {
                    s = " [TOO MUCH DATA TO INCLUDE]";
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public static AbstractHttpEntity a(final byte[] array) {
        ByteArrayEntity byteArrayEntity;
        if (array.length < com.alipay.android.phone.mrpc.core.b.a) {
            byteArrayEntity = new ByteArrayEntity(array);
        }
        else {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream((OutputStream)byteArrayOutputStream);
            ((OutputStream)gzipOutputStream).write(array);
            ((OutputStream)gzipOutputStream).close();
            final ByteArrayEntity byteArrayEntity2 = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
            ((AbstractHttpEntity)byteArrayEntity2).setContentEncoding("gzip");
            final StringBuilder sb = new StringBuilder("gzip size:");
            sb.append(array.length);
            sb.append("->");
            sb.append(((AbstractHttpEntity)byteArrayEntity2).getContentLength());
            byteArrayEntity = byteArrayEntity2;
        }
        return (AbstractHttpEntity)byteArrayEntity;
    }
    
    public static void a(final HttpRequest httpRequest) {
        httpRequest.addHeader("Accept-Encoding", "gzip");
    }
    
    public static long b(final String s) {
        return k.a(s);
    }
    
    public static void b(final HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", "Keep-Alive");
    }
    
    public static boolean b(final HttpUriRequest httpUriRequest) {
        final Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (int length = headers.length, i = 0; i < length; ++i) {
                if ("gzip".equalsIgnoreCase(headers[i].getValue())) {
                    return true;
                }
            }
        }
        final Header[] headers2 = httpUriRequest.getHeaders("content-type");
        if (headers2 != null) {
            for (final Header header : headers2) {
                final String[] b = com.alipay.android.phone.mrpc.core.b.b;
                for (int length3 = b.length, k = 0; k < length3; ++k) {
                    if (header.getValue().startsWith(b[k])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public final void a(final HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient)this.d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }
    
    public final <T> T execute(final HttpHost httpHost, final HttpRequest httpRequest, final ResponseHandler<? extends T> responseHandler) {
        return (T)this.d.execute(httpHost, httpRequest, (ResponseHandler)responseHandler);
    }
    
    public final <T> T execute(final HttpHost httpHost, final HttpRequest httpRequest, final ResponseHandler<? extends T> responseHandler, final HttpContext httpContext) {
        return (T)this.d.execute(httpHost, httpRequest, (ResponseHandler)responseHandler, httpContext);
    }
    
    public final <T> T execute(final HttpUriRequest httpUriRequest, final ResponseHandler<? extends T> responseHandler) {
        return (T)this.d.execute(httpUriRequest, (ResponseHandler)responseHandler);
    }
    
    public final <T> T execute(final HttpUriRequest httpUriRequest, final ResponseHandler<? extends T> responseHandler, final HttpContext httpContext) {
        return (T)this.d.execute(httpUriRequest, (ResponseHandler)responseHandler, httpContext);
    }
    
    public final HttpResponse execute(final HttpHost httpHost, final HttpRequest httpRequest) {
        return this.d.execute(httpHost, httpRequest);
    }
    
    public final HttpResponse execute(final HttpHost httpHost, final HttpRequest httpRequest, final HttpContext httpContext) {
        return this.d.execute(httpHost, httpRequest, httpContext);
    }
    
    public final HttpResponse execute(final HttpUriRequest httpUriRequest) {
        return this.d.execute(httpUriRequest);
    }
    
    public final HttpResponse execute(final HttpUriRequest httpUriRequest, final HttpContext httpContext) {
        return this.d.execute(httpUriRequest, httpContext);
    }
    
    public final ClientConnectionManager getConnectionManager() {
        return this.d.getConnectionManager();
    }
    
    public final HttpParams getParams() {
        return this.d.getParams();
    }
    
    public final class a implements HttpRequestInterceptor
    {
        public final b a;
        
        public a(final b a) {
            this.a = a;
        }
        
        public final void process(final HttpRequest httpRequest, final HttpContext httpContext) {
            final b a = com.alipay.android.phone.mrpc.core.b.a(this.a);
            if (a != null && com.alipay.android.phone.mrpc.core.b.b.a(a) && httpRequest instanceof HttpUriRequest) {
                com.alipay.android.phone.mrpc.core.b.b.a(a, com.alipay.android.phone.mrpc.core.b.a((HttpUriRequest)httpRequest));
            }
        }
    }
    
    public static final class b
    {
        public final String a;
        public final int b;
        
        public static /* synthetic */ void a(final b b, final String s) {
            Log.println(b.b, b.a, s);
        }
        
        public static /* synthetic */ boolean a(final b b) {
            return Log.isLoggable(b.a, b.b);
        }
    }
}
