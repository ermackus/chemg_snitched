package com.alibaba.mtl.log.d;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

class y implements X509TrustManager
{
    private static TrustManager[] a;
    
    static TrustManager[] getTrustManagers() {
        synchronized (y.class) {
            if (y.a == null) {
                y.a = new TrustManager[] { (TrustManager)new y() };
            }
            return y.a;
        }
    }
    
    public void checkClientTrusted(final X509Certificate[] array, final String s) throws CertificateException {
    }
    
    public void checkServerTrusted(final X509Certificate[] array, final String s) throws CertificateException {
        if (array != null && array.length != 0) {
            if (s != null && s.length() != 0) {
                try {
                    array[0].checkValidity();
                    return;
                }
                catch (final Exception ex) {
                    throw new CertificateException("Certificate not valid or trusted.");
                }
            }
            throw new IllegalArgumentException("parameter is not used");
        }
        throw new IllegalArgumentException("parameter is not used");
    }
    
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
