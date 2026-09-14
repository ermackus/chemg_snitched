package org.eclipse.paho.client.mqttv3.internal.websocket;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import javax.net.ssl.SSLSocketFactory;
import java.nio.ByteBuffer;
import java.io.PipedInputStream;
import java.io.ByteArrayOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;

public class WebSocketSecureNetworkModule extends SSLNetworkModule
{
    private static final String CLASS_NAME;
    private Properties customWebSocketHeaders;
    private String host;
    private Logger log;
    private ByteArrayOutputStream outputStream;
    private PipedInputStream pipedInputStream;
    private int port;
    ByteBuffer recievedPayload;
    private String uri;
    private WebSocketReceiver webSocketReceiver;
    
    static {
        CLASS_NAME = WebSocketSecureNetworkModule.class.getName();
    }
    
    public WebSocketSecureNetworkModule(final SSLSocketFactory sslSocketFactory, final String uri, final String host, final int port, final String resourceName, final Properties customWebSocketHeaders) {
        super(sslSocketFactory, host, port, resourceName);
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", WebSocketSecureNetworkModule.CLASS_NAME);
        this.outputStream = (ByteArrayOutputStream)new ExtendedByteArrayOutputStream(this);
        this.uri = uri;
        this.host = host;
        this.port = port;
        this.customWebSocketHeaders = customWebSocketHeaders;
        this.pipedInputStream = new PipedInputStream();
        this.log.setResourceName(resourceName);
    }
    
    public InputStream getInputStream() throws IOException {
        return (InputStream)this.pipedInputStream;
    }
    
    public OutputStream getOutputStream() throws IOException {
        return (OutputStream)this.outputStream;
    }
    
    public String getServerURI() {
        final StringBuilder sb = new StringBuilder("wss://");
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
    
    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(super.getInputStream(), super.getOutputStream(), this.uri, this.host, this.port, this.customWebSocketHeaders).execute();
        (this.webSocketReceiver = new WebSocketReceiver(this.getSocketInputStream(), this.pipedInputStream)).start("WssSocketReceiver");
    }
    
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
