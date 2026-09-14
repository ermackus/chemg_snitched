package org.xutils.http.app;

import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.RequestParams;
import org.xutils.common.util.LogUtil;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;

public class DefaultParamsBuilder implements ParamsBuilder
{
    private static SSLSocketFactory trustAllSSlSocketFactory;
    
    public static SSLSocketFactory getTrustAllSSLSocketFactory() {
        if (DefaultParamsBuilder.trustAllSSlSocketFactory == null) {
            synchronized (DefaultParamsBuilder.class) {
                if (DefaultParamsBuilder.trustAllSSlSocketFactory == null) {
                    final X509TrustManager x509TrustManager = (X509TrustManager)new X509TrustManager() {
                        public void checkClientTrusted(final X509Certificate[] array, final String s) {
                        }
                        
                        public void checkServerTrusted(final X509Certificate[] array, final String s) {
                        }
                        
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    };
                    try {
                        final SSLContext instance = SSLContext.getInstance("TLS");
                        instance.init((KeyManager[])null, new TrustManager[] { (TrustManager)x509TrustManager }, (SecureRandom)null);
                        DefaultParamsBuilder.trustAllSSlSocketFactory = instance.getSocketFactory();
                    }
                    finally {
                        final Throwable t;
                        LogUtil.e(t.getMessage(), t);
                    }
                }
            }
        }
        return DefaultParamsBuilder.trustAllSSlSocketFactory;
    }
    
    @Override
    public String buildCacheKey(final RequestParams requestParams, final String[] array) {
        String s;
        if (array != null && array.length > 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(requestParams.getUri());
            sb.append("?");
            String string = sb.toString();
            final int length = array.length;
            int n = 0;
            while (true) {
                s = string;
                if (n >= length) {
                    break;
                }
                final String s2 = array[n];
                final String stringParameter = requestParams.getStringParameter(s2);
                String string2 = string;
                if (stringParameter != null) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(string);
                    sb2.append(s2);
                    sb2.append("=");
                    sb2.append(stringParameter);
                    sb2.append("&");
                    string2 = sb2.toString();
                }
                ++n;
                string = string2;
            }
        }
        else {
            s = null;
        }
        return s;
    }
    
    @Override
    public void buildParams(final RequestParams requestParams) throws Throwable {
    }
    
    @Override
    public void buildSign(final RequestParams requestParams, final String[] array) throws Throwable {
    }
    
    @Override
    public String buildUri(final RequestParams requestParams, final HttpRequest httpRequest) throws Throwable {
        final StringBuilder sb = new StringBuilder();
        sb.append(httpRequest.host());
        sb.append("/");
        sb.append(httpRequest.path());
        return sb.toString();
    }
    
    @Override
    public SSLSocketFactory getSSLSocketFactory() throws Throwable {
        return getTrustAllSSLSocketFactory();
    }
}
