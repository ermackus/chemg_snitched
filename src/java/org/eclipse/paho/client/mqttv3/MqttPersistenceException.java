package org.eclipse.paho.client.mqttv3;

public class MqttPersistenceException extends MqttException
{
    public static final short REASON_CODE_PERSISTENCE_IN_USE = 32200;
    private static final long serialVersionUID = 300L;
    
    public MqttPersistenceException() {
        super(0);
    }
    
    public MqttPersistenceException(final int n) {
        super(n);
    }
    
    public MqttPersistenceException(final int n, final Throwable t) {
        super(n, t);
    }
    
    public MqttPersistenceException(final Throwable t) {
        super(t);
    }
}
