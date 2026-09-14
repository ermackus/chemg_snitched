package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.BufferedMessage;

public interface IDisconnectedBufferCallback
{
    void publishBufferedMessage(final BufferedMessage p0) throws MqttException;
}
