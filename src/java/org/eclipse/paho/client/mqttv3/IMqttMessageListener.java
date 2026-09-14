package org.eclipse.paho.client.mqttv3;

public interface IMqttMessageListener
{
    void messageArrived(final String p0, final MqttMessage p1) throws Exception;
}
