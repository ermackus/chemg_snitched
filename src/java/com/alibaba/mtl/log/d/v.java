package com.alibaba.mtl.log.d;

import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateExpiredException;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLEngine;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

class v extends X509ExtendedTrustManager
{
    private static TrustManager[] a;
    
    static TrustManager[] getTrustManagers() {
        synchronized (v.class) {
            if (v.a == null) {
                v.a = new TrustManager[] { (TrustManager)new v() };
            }
            return v.a;
        }
    }
    
    public void checkClientTrusted(final X509Certificate[] array, final String s) throws CertificateException {
        i.a("UtExtendTrustManager", new Object[] { "checkClientTrusted1" });
    }
    
    public void checkClientTrusted(final X509Certificate[] array, final String s, final Socket socket) throws CertificateException {
        i.a("UtExtendTrustManager", new Object[] { "checkClientTrusted2" });
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
    
    public void checkClientTrusted(final X509Certificate[] array, final String s, final SSLEngine sslEngine) throws CertificateException {
        i.a("UtExtendTrustManager", new Object[] { "checkClientTrusted3" });
    }
    
    public void checkServerTrusted(final X509Certificate[] array, final String s) throws CertificateException {
        int i = 0;
        com.alibaba.mtl.log.d.i.a("UtExtendTrustManager", new Object[] { "checkServerTrusted1" });
        if (array != null && array.length > 0) {
            try {
                final TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init((KeyStore)null);
                if (instance != null && instance.getTrustManagers() != null) {
                    final TrustManager[] trustManagers = instance.getTrustManagers();
                    while (i < trustManagers.length) {
                        final TrustManager trustManager = trustManagers[i];
                        try {
                            ((X509TrustManager)trustManager).checkServerTrusted(array, s);
                            ++i;
                            continue;
                        }
                        catch (final CertificateException ex) {
                            for (Throwable cause = (Throwable)ex; cause != null; cause = cause.getCause()) {
                                if (cause instanceof CertificateExpiredException || cause instanceof CertificateNotYetValidException) {
                                    return;
                                }
                            }
                            throw ex;
                        }
                        break;
                    }
                }
                return;
            }
            catch (final KeyStoreException ex2) {
                throw new CertificateException((Throwable)ex2);
            }
            catch (final NoSuchAlgorithmException ex3) {
                throw new CertificateException((Throwable)ex3);
            }
        }
        throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
    }
    
    public void checkServerTrusted(final X509Certificate[] array, final String s, final Socket socket) throws CertificateException {
        i.a("UtExtendTrustManager", new Object[] { "checkServerTrusted2" });
    }
    
    public void checkServerTrusted(final X509Certificate[] array, final String s, final SSLEngine sslEngine) throws CertificateException {
        i.a("UtExtendTrustManager", new Object[] { "checkServerTrusted3" });
    }
    
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
