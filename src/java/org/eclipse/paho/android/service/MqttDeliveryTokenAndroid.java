package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

class MqttDeliveryTokenAndroid extends MqttTokenAndroid implements IMqttDeliveryToken
{
    private MqttMessage message;
    
    MqttDeliveryTokenAndroid(final MqttAndroidClient mqttAndroidClient, final Object o, final IMqttActionListener mqttActionListener, final MqttMessage message) {
        super(mqttAndroidClient, o, mqttActionListener);
        this.message = message;
    }
    
    @Override
    public MqttMessage getMessage() throws MqttException {
        return this.message;
    }
    
    void notifyDelivery(final MqttMessage message) {
        this.message = message;
        super.notifyComplete();
    }
    
    void setMessage(final MqttMessage message) {
        this.message = message;
    }
}
