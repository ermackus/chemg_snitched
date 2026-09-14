package org.eclipse.paho.client.mqttv3;

public class MqttDeliveryToken extends MqttToken implements IMqttDeliveryToken
{
    public MqttDeliveryToken() {
    }
    
    public MqttDeliveryToken(final String s) {
        super(s);
    }
    
    @Override
    public MqttMessage getMessage() throws MqttException {
        return this.internalTok.getMessage();
    }
    
    protected void setMessage(final MqttMessage message) {
        this.internalTok.setMessage(message);
    }
}
