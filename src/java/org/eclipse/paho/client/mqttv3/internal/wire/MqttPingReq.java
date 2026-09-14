package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;

public class MqttPingReq extends MqttWireMessage
{
    public static final String KEY = "Ping";
    
    public MqttPingReq() {
        super((byte)12);
    }
    
    public MqttPingReq(final byte b, final byte[] array) throws IOException {
        super((byte)12);
    }
    
    @Override
    public String getKey() {
        return "Ping";
    }
    
    @Override
    protected byte getMessageInfo() {
        return 0;
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
