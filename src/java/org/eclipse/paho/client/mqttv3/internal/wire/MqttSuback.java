package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttSuback extends MqttAck
{
    private int[] grantedQos;
    
    public MqttSuback(final byte b, final byte[] array) throws IOException {
        super((byte)9);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = dataInputStream.readUnsignedShort();
        this.grantedQos = new int[array.length - 2];
        int i = dataInputStream.read();
        int n = 0;
        while (i != -1) {
            this.grantedQos[n] = i;
            ++n;
            i = dataInputStream.read();
        }
        dataInputStream.close();
    }
    
    public int[] getGrantedQos() {
        return this.grantedQos;
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(" granted Qos");
        for (final int n : this.grantedQos) {
            sb.append(" ");
            sb.append(n);
        }
        return sb.toString();
    }
}
