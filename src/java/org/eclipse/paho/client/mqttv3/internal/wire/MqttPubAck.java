package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttPubAck extends MqttAck
{
    public MqttPubAck(final byte b, final byte[] array) throws IOException {
        super((byte)4);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }
    
    public MqttPubAck(final int msgId) {
        super((byte)4);
        this.msgId = msgId;
    }
    
    public MqttPubAck(final MqttPublish mqttPublish) {
        super((byte)4);
        this.msgId = mqttPublish.getMessageId();
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return this.encodeMessageId();
    }
}
