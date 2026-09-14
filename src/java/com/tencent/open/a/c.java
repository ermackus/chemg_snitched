package com.tencent.open.a;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.tencent.open.log.SLog;
import java.util.Arrays;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.SSLSocket;
import java.net.Socket;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLSocketFactory;

public class c extends SSLSocketFactory
{
    private SSLSocketFactory a;
    private TrustManager[] b;
    
    public c() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        final SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[])null, this.b = this.b(), (SecureRandom)null);
        this.a = instance.getSocketFactory();
    }
    
    private Socket a(final Socket socket) {
        if (socket instanceof SSLSocket) {
            final SSLSocket sslSocket = (SSLSocket)socket;
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
        }
        return socket;
    }
    
    private TrustManager[] b() {
        try {
            final TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore)null);
            final TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && trustManagers[0] instanceof X509TrustManager) {
                return trustManagers;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("Unexpected default trust managers: ");
            sb.append(Arrays.toString((Object[])trustManagers));
            SLog.e("openSDK_LOG.Tls2SupportedSocketFactory", sb.toString());
            return null;
        }
        catch (final GeneralSecurityException ex) {
            SLog.e("openSDK_LOG.Tls2SupportedSocketFactory", "The system has no TLS. Just give up.");
            return null;
        }
    }
    
    public TrustManager a() {
        final TrustManager[] b = this.b;
        if (b != null && b.length > 0) {
            return b[0];
        }
        return null;
    }
    
    public Socket createSocket(final String s, final int n) throws IOException, UnknownHostException {
        return this.a(this.a.createSocket(s, n));
    }
    
    public Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) throws IOException, UnknownHostException {
        return this.a(this.a.createSocket(s, n, inetAddress, n2));
    }
    
    public Socket createSocket(final InetAddress inetAddress, final int n) throws IOException {
        return this.a(this.a.createSocket(inetAddress, n));
    }
    
    public Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) throws IOException {
        return this.a(this.a.createSocket(inetAddress, n, inetAddress2, n2));
    }
    
    public Socket createSocket(final Socket socket, final String s, final int n, final boolean b) throws IOException {
        return this.a(this.a.createSocket(socket, s, n, b));
    }
    
    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }
    
    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
