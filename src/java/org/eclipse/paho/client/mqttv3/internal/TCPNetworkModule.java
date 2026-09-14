package org.eclipse.paho.client.mqttv3.internal;

import java.net.ConnectException;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.net.Socket;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import javax.net.SocketFactory;

public class TCPNetworkModule implements NetworkModule
{
    private static final String CLASS_NAME;
    private int conTimeout;
    private SocketFactory factory;
    private String host;
    private Logger log;
    private int port;
    protected Socket socket;
    
    static {
        CLASS_NAME = TCPNetworkModule.class.getName();
    }
    
    public TCPNetworkModule(final SocketFactory factory, final String host, final int port, final String resourceName) {
        (this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", TCPNetworkModule.CLASS_NAME)).setResourceName(resourceName);
        this.factory = factory;
        this.host = host;
        this.port = port;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }
    
    @Override
    public String getServerURI() {
        final StringBuilder sb = new StringBuilder("tcp://");
        sb.append(this.host);
        sb.append(":");
        sb.append(this.port);
        return sb.toString();
    }
    
    public void setConnectTimeout(final int conTimeout) {
        this.conTimeout = conTimeout;
    }
    
    @Override
    public void start() throws IOException, MqttException {
        try {
            this.log.fine(TCPNetworkModule.CLASS_NAME, "start", "252", new Object[] { this.host, this.port, this.conTimeout * 1000 });
            (this.socket = this.factory.createSocket()).connect((SocketAddress)new InetSocketAddress(this.host, this.port), this.conTimeout * 1000);
            this.socket.setSoTimeout(1000);
        }
        catch (final ConnectException ex) {
            this.log.fine(TCPNetworkModule.CLASS_NAME, "start", "250", null, (Throwable)ex);
            throw new MqttException(32103, (Throwable)ex);
        }
    }
    
    @Override
    public void stop() throws IOException {
        final Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }
}
