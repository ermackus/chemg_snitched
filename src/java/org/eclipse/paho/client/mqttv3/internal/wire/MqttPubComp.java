package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttPubComp extends MqttAck
{
    public MqttPubComp(final byte b, final byte[] array) throws IOException {
        super((byte)7);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }
    
    public MqttPubComp(final int msgId) {
        super((byte)7);
        this.msgId = msgId;
    }
    
    public MqttPubComp(final MqttPublish mqttPublish) {
        super((byte)7);
        this.msgId = mqttPublish.getMessageId();
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return this.encodeMessageId();
    }
}
