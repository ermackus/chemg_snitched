package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPingResp extends MqttAck
{
    public static final String KEY = "Ping";
    
    public MqttPingResp(final byte b, final byte[] array) {
        super((byte)13);
    }
    
    @Override
    public String getKey() {
        return "Ping";
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }
    
    @Override
    public boolean isMessageIdRequired() {
        return false;
    }
}
