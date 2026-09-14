package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import javax.net.ssl.SSLSocketFactory;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

public class WebSocketNetworkModuleFactory implements NetworkModuleFactory
{
    @Override
    public NetworkModule createNetworkModule(final URI uri, final MqttConnectOptions mqttConnectOptions, final String s) throws MqttException {
        final String host = uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            port = 80;
        }
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        if (socketFactory == null) {
            socketFactory = SocketFactory.getDefault();
        }
        else if (socketFactory instanceof SSLSocketFactory) {
            throw ExceptionHelper.createMqttException(32105);
        }
        final WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, uri.toString(), host, port, s, mqttConnectOptions.getCustomWebSocketHeaders());
        webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
        return webSocketNetworkModule;
    }
    
    @Override
    public Set<String> getSupportedUriSchemes() {
        return (Set<String>)Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[] { "ws" })));
    }
    
    @Override
    public void validateURI(final URI uri) throws IllegalArgumentException {
    }
}
