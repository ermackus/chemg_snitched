package org.eclipse.paho.client.mqttv3.internal;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

public class TCPNetworkModuleFactory implements NetworkModuleFactory
{
    @Override
    public NetworkModule createNetworkModule(final URI uri, final MqttConnectOptions mqttConnectOptions, final String s) throws MqttException {
        final String host = uri.getHost();
        int port;
        if ((port = uri.getPort()) == -1) {
            port = 1883;
        }
        final String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            throw new IllegalArgumentException(uri.toString());
        }
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        if (socketFactory == null) {
            socketFactory = SocketFactory.getDefault();
        }
        else if (socketFactory instanceof SSLSocketFactory) {
            throw ExceptionHelper.createMqttException(32105);
        }
        final TCPNetworkModule tcpNetworkModule = new TCPNetworkModule(socketFactory, host, port, s);
        tcpNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
        return tcpNetworkModule;
    }
    
    @Override
    public Set<String> getSupportedUriSchemes() {
        return (Set<String>)Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[] { "tcp" })));
    }
    
    @Override
    public void validateURI(final URI uri) throws IllegalArgumentException {
        final String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            final StringBuilder sb = new StringBuilder("URI path must be empty \"");
            sb.append(uri.toString());
            sb.append("\"");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
