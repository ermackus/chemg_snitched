package org.eclipse.paho.client.mqttv3.internal;

import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.Properties;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;

public class SSLNetworkModuleFactory implements NetworkModuleFactory
{
    @Override
    public NetworkModule createNetworkModule(final URI uri, final MqttConnectOptions mqttConnectOptions, final String s) throws MqttException {
        final String host = uri.getHost();
        int port;
        if ((port = uri.getPort()) == -1) {
            port = 8883;
        }
        final String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            throw new IllegalArgumentException(uri.toString());
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
        final SSLNetworkModule sslNetworkModule = new SSLNetworkModule((SSLSocketFactory)o, host, port, s);
        sslNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
        sslNetworkModule.setSSLHostnameVerifier(mqttConnectOptions.getSSLHostnameVerifier());
        sslNetworkModule.setHttpsHostnameVerificationEnabled(mqttConnectOptions.isHttpsHostnameVerificationEnabled());
        if (sslSocketFactoryFactory != null) {
            final String[] enabledCipherSuites = sslSocketFactoryFactory.getEnabledCipherSuites(null);
            if (enabledCipherSuites != null) {
                sslNetworkModule.setEnabledCiphers(enabledCipherSuites);
            }
        }
        return sslNetworkModule;
    }
    
    @Override
    public Set<String> getSupportedUriSchemes() {
        return (Set<String>)Collections.unmodifiableSet((Set)new HashSet((Collection)Arrays.asList((Object[])new String[] { "ssl" })));
    }
    
    @Override
    public void validateURI(final URI uri) throws IllegalArgumentException {
        final String path = uri.getPath();
        if (path != null && !path.isEmpty()) {
            throw new IllegalArgumentException(uri.toString());
        }
    }
}
