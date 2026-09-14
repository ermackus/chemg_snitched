package org.eclipse.paho.client.mqttv3;

public class MqttSecurityException extends MqttException
{
    private static final long serialVersionUID = 300L;
    
    public MqttSecurityException(final int n) {
        super(n);
    }
    
    public MqttSecurityException(final int n, final Throwable t) {
        super(n, t);
    }
    
    public MqttSecurityException(final Throwable t) {
        super(t);
    }
}
