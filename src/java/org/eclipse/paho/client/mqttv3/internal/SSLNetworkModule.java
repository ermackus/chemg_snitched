package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.util.List;
import javax.net.ssl.SNIHostName;
import java.util.ArrayList;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import javax.net.ssl.HostnameVerifier;

public class SSLNetworkModule extends TCPNetworkModule
{
    private static final String CLASS_NAME;
    private String[] enabledCiphers;
    private int handshakeTimeoutSecs;
    private String host;
    private HostnameVerifier hostnameVerifier;
    private boolean httpsHostnameVerificationEnabled;
    private Logger log;
    private int port;
    
    static {
        CLASS_NAME = SSLNetworkModule.class.getName();
    }
    
    public SSLNetworkModule(final SSLSocketFactory sslSocketFactory, final String host, final int port, final String resourceName) {
        super((SocketFactory)sslSocketFactory, host, port, resourceName);
        final Logger logger = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", SSLNetworkModule.CLASS_NAME);
        this.log = logger;
        this.httpsHostnameVerificationEnabled = false;
        this.host = host;
        this.port = port;
        logger.setResourceName(resourceName);
    }
    
    public String[] getEnabledCiphers() {
        return this.enabledCiphers;
    }
    
    public HostnameVerifier getSSLHostnameVerifier() {
        return this.hostnameVerifier;
    }
    
    @Override
    public String getServerURI() {
        final StringBuilder sb = new StringBuilder("ssl://");
        sb.append(this.host);
        sb.append(":");
        sb.append(this.port);
        return sb.toString();
    }
    
    public boolean isHttpsHostnameVerificationEnabled() {
        return this.httpsHostnameVerificationEnabled;
    }
    
    public void setEnabledCiphers(final String[] array) {
        if (array != null) {
            this.enabledCiphers = array.clone();
        }
        if (this.socket != null && this.enabledCiphers != null) {
            if (this.log.isLoggable(5)) {
                String string = "";
                for (int i = 0; i < this.enabledCiphers.length; ++i) {
                    String string2 = string;
                    if (i > 0) {
                        final StringBuilder sb = new StringBuilder(String.valueOf((Object)string));
                        sb.append(",");
                        string2 = sb.toString();
                    }
                    final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)string2));
                    sb2.append(this.enabledCiphers[i]);
                    string = sb2.toString();
                }
                this.log.fine(SSLNetworkModule.CLASS_NAME, "setEnabledCiphers", "260", new Object[] { string });
            }
            ((SSLSocket)this.socket).setEnabledCipherSuites(this.enabledCiphers);
        }
    }
    
    public void setHttpsHostnameVerificationEnabled(final boolean httpsHostnameVerificationEnabled) {
        this.httpsHostnameVerificationEnabled = httpsHostnameVerificationEnabled;
    }
    
    public void setSSLHostnameVerifier(final HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }
    
    public void setSSLhandshakeTimeout(final int n) {
        super.setConnectTimeout(n);
        this.handshakeTimeoutSecs = n;
    }
    
    @Override
    public void start() throws IOException, MqttException {
        super.start();
        this.setEnabledCiphers(this.enabledCiphers);
        final int soTimeout = this.socket.getSoTimeout();
        this.socket.setSoTimeout(this.handshakeTimeoutSecs * 1000);
        try {
            final SSLParameters sslParameters = new SSLParameters();
            final ArrayList serverNames = new ArrayList(1);
            ((List)serverNames).add((Object)new SNIHostName(this.host));
            sslParameters.setServerNames((List)serverNames);
            ((SSLSocket)this.socket).setSSLParameters(sslParameters);
        }
        catch (final NoClassDefFoundError noClassDefFoundError) {}
        if (this.httpsHostnameVerificationEnabled) {
            try {
                final SSLParameters sslParameters2 = new SSLParameters();
                sslParameters2.setEndpointIdentificationAlgorithm("HTTPS");
                ((SSLSocket)this.socket).setSSLParameters(sslParameters2);
            }
            catch (final NoSuchMethodError noSuchMethodError) {}
        }
        ((SSLSocket)this.socket).startHandshake();
        if (this.hostnameVerifier != null && !this.httpsHostnameVerificationEnabled) {
            final SSLSession session = ((SSLSocket)this.socket).getSession();
            if (!this.hostnameVerifier.verify(this.host, session)) {
                session.invalidate();
                this.socket.close();
                final StringBuilder sb = new StringBuilder("Host: ");
                sb.append(this.host);
                sb.append(", Peer Host: ");
                sb.append(session.getPeerHost());
                throw new SSLPeerUnverifiedException(sb.toString());
            }
        }
        this.socket.setSoTimeout(soTimeout);
    }
}
