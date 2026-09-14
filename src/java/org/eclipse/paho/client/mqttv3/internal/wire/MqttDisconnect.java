package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;

public class MqttDisconnect extends MqttWireMessage
{
    public static final String KEY = "Disc";
    
    public MqttDisconnect() {
        super((byte)14);
    }
    
    public MqttDisconnect(final byte b, final byte[] array) throws IOException {
        super((byte)14);
    }
    
    @Override
    public String getKey() {
        return "Disc";
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
