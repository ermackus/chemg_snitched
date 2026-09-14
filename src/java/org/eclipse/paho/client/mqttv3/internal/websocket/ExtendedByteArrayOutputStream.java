package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.OutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;

class ExtendedByteArrayOutputStream extends ByteArrayOutputStream
{
    final WebSocketNetworkModule webSocketNetworkModule;
    final WebSocketSecureNetworkModule webSocketSecureNetworkModule;
    
    ExtendedByteArrayOutputStream(final WebSocketNetworkModule webSocketNetworkModule) {
        this.webSocketNetworkModule = webSocketNetworkModule;
        this.webSocketSecureNetworkModule = null;
    }
    
    ExtendedByteArrayOutputStream(final WebSocketSecureNetworkModule webSocketSecureNetworkModule) {
        this.webSocketNetworkModule = null;
        this.webSocketSecureNetworkModule = webSocketSecureNetworkModule;
    }
    
    public void flush() throws IOException {
        synchronized (this) {
            final ByteBuffer wrap = ByteBuffer.wrap(this.toByteArray());
            this.reset();
            monitorexit(this);
            this.getSocketOutputStream().write(new WebSocketFrame((byte)2, true, wrap.array()).encodeFrame());
            this.getSocketOutputStream().flush();
        }
    }
    
    OutputStream getSocketOutputStream() throws IOException {
        final WebSocketNetworkModule webSocketNetworkModule = this.webSocketNetworkModule;
        if (webSocketNetworkModule != null) {
            return webSocketNetworkModule.getSocketOutputStream();
        }
        final WebSocketSecureNetworkModule webSocketSecureNetworkModule = this.webSocketSecureNetworkModule;
        if (webSocketSecureNetworkModule != null) {
            return webSocketSecureNetworkModule.getSocketOutputStream();
        }
        return null;
    }
}
