package org.eclipse.paho.client.mqttv3;

public interface MqttCallback
{
    void connectionLost(final Throwable p0);
    
    void deliveryComplete(final IMqttDeliveryToken p0);
    
    void messageArrived(final String p0, final MqttMessage p1) throws Exception;
}
