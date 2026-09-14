package org.eclipse.paho.client.mqttv3.internal.websocket;

import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

public class WebSocketSecureNetworkModuleFactory implements NetworkModuleFactory
{
    @Override
    public NetworkModule createNetworkModule(final URI uri, final MqttConnectOptions mqttConnectOptions, final String s) throws MqttException {
        final String host = uri.getHost();
        int port = uri.getPort();
        if (port == -1) {
            port = 443;
        }
        Object o = mqttConnectOptions.getSocketFactory();
        SSLSocketFactoryFactory sslSocketFactoryFactory;
        if (o == null) {
            sslSocketFactoryFactory = new SSLSocketFactoryFactory();
            final Properties sslProperties = mqttConnectOptions.getSSLProperties();
            if (sslProperties != null) {
                sslSocketFactoryFactory.initialize(sslProperties, null);
            }
            o = sslSocketFactoryFactory.createSocketFactory(null);
        }
        else {
            if (!(o instanceof SSLSocketFactory)) {
                throw ExceptionHelper.createMqttException(32105);
            }
            sslSocketFactoryFactory = null;
        }
        final WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory)o, uri.toString(), host, port, s, mqttConnectOptions.getCustomWebSocketHeaders());
        webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
        webSocketSecureNetworkModule.setSSLHostnameVerifier(mqttConnectOptions.getSSLHostnameVerifier());
        webSocketSecureNetworkModule.setHttpsHostnameVerificationEnabled(mqttConnectOptions.isHttpsHostnameVerificationEnabled());
        if (sslSocketFactoryFactory != null) {
            final String[] enabledCipherSuites = sslSocketFactoryFactory.getEnabledCipherSuites(null);
            if (enabledCipherSuites != null) {
                ((SSLNetworkModule)webSocketSecureNetworkModule).setEnabledCiphers(enabledCipherSuites);
            }
        }
        return (NetworkModule)webSocketSecureNetworkModule;
    }
    
    @Override
    public Set<String> getSupportedUriSchemes() {
        return (Set<String>)Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[] { "wss" })));
    }
    
    @Override
    public void validateURI(final URI uri) throws IllegalArgumentException {
    }
}
