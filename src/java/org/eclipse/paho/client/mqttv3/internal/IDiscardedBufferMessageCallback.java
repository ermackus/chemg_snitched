package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public interface IDiscardedBufferMessageCallback
{
    void messageDiscarded(final MqttWireMessage p0);
}
