package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public interface MqttPingSender
{
    void init(final ClientComms p0);
    
    void schedule(final long p0);
    
    void start();
    
    void stop();
}
