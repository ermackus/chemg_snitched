package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttConnack extends MqttAck
{
    public static final String KEY = "Con";
    private int returnCode;
    private boolean sessionPresent;
    
    public MqttConnack(final byte b, final byte[] array) throws IOException {
        super((byte)2);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        final int unsignedByte = dataInputStream.readUnsignedByte();
        boolean sessionPresent = true;
        if ((unsignedByte & 0x1) != 0x1) {
            sessionPresent = false;
        }
        this.sessionPresent = sessionPresent;
        this.returnCode = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }
    
    @Override
    public String getKey() {
        return "Con";
    }
    
    public int getReturnCode() {
        return this.returnCode;
    }
    
    public boolean getSessionPresent() {
        return this.sessionPresent;
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }
    
    @Override
    public boolean isMessageIdRequired() {
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)super.toString()));
        sb.append(" session present:");
        sb.append(this.sessionPresent);
        sb.append(" return code: ");
        sb.append(this.returnCode);
        return sb.toString();
    }
}
