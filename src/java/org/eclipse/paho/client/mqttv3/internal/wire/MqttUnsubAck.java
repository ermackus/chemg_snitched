package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttUnsubAck extends MqttAck
{
    public MqttUnsubAck(final byte b, final byte[] array) throws IOException {
        super((byte)11);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }
}
