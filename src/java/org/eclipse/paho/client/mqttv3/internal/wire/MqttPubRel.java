package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttPubRel extends MqttPersistableWireMessage
{
    public MqttPubRel(final byte b, final byte[] array) throws IOException {
        super((byte)6);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }
    
    public MqttPubRel(final MqttPubRec mqttPubRec) {
        super((byte)6);
        this.setMessageId(mqttPubRec.getMessageId());
    }
    
    @Override
    protected byte getMessageInfo() {
        int n;
        if (this.duplicate) {
            n = 8;
        }
        else {
            n = 0;
        }
        return (byte)(n | 0x2);
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return this.encodeMessageId();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)super.toString()));
        sb.append(" msgId ");
        sb.append(this.msgId);
        return sb.toString();
    }
}
