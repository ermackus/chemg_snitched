package org.eclipse.paho.client.mqttv3;

public interface IMqttActionListener
{
    void onFailure(final IMqttToken p0, final Throwable p1);
    
    void onSuccess(final IMqttToken p0);
}
