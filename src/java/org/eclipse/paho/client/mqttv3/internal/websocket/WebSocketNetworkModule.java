package org.eclipse.paho.client.mqttv3.internal.websocket;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import javax.net.SocketFactory;
import java.nio.ByteBuffer;
import java.io.PipedInputStream;
import java.io.ByteArrayOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;

public class WebSocketNetworkModule extends TCPNetworkModule
{
    private static final String CLASS_NAME;
    private Properties customWebsocketHeaders;
    private String host;
    private Logger log;
    private ByteArrayOutputStream outputStream;
    private PipedInputStream pipedInputStream;
    private int port;
    ByteBuffer recievedPayload;
    private String uri;
    private WebSocketReceiver webSocketReceiver;
    
    static {
        CLASS_NAME = WebSocketNetworkModule.class.getName();
    }
    
    public WebSocketNetworkModule(final SocketFactory socketFactory, final String uri, final String host, final int port, final String resourceName, final Properties customWebsocketHeaders) {
        super(socketFactory, host, port, resourceName);
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", WebSocketNetworkModule.CLASS_NAME);
        this.outputStream = new ExtendedByteArrayOutputStream(this);
        this.uri = uri;
        this.host = host;
        this.port = port;
        this.customWebsocketHeaders = customWebsocketHeaders;
        this.pipedInputStream = new PipedInputStream();
        this.log.setResourceName(resourceName);
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        return (InputStream)this.pipedInputStream;
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        return (OutputStream)this.outputStream;
    }
    
    @Override
    public String getServerURI() {
        final StringBuilder sb = new StringBuilder("ws://");
        sb.append(this.host);
        sb.append(":");
        sb.append(this.port);
        return sb.toString();
    }
    
    InputStream getSocketInputStream() throws IOException {
        return super.getInputStream();
    }
    
    OutputStream getSocketOutputStream() throws IOException {
        return super.getOutputStream();
    }
    
    @Override
    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(this.getSocketInputStream(), this.getSocketOutputStream(), this.uri, this.host, this.port, this.customWebsocketHeaders).execute();
        (this.webSocketReceiver = new WebSocketReceiver(this.getSocketInputStream(), this.pipedInputStream)).start("webSocketReceiver");
    }
    
    @Override
    public void stop() throws IOException {
        this.getSocketOutputStream().write(new WebSocketFrame((byte)8, true, "1000".getBytes()).encodeFrame());
        this.getSocketOutputStream().flush();
        final WebSocketReceiver webSocketReceiver = this.webSocketReceiver;
        if (webSocketReceiver != null) {
            webSocketReceiver.stop();
        }
        super.stop();
    }
}
