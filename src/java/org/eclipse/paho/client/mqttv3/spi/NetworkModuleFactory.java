package org.eclipse.paho.client.mqttv3.spi;

import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.net.URI;

public interface NetworkModuleFactory
{
    NetworkModule createNetworkModule(final URI p0, final MqttConnectOptions p1, final String p2) throws MqttException;
    
    Set<String> getSupportedUriSchemes();
    
    void validateURI(final URI p0) throws IllegalArgumentException;
}
