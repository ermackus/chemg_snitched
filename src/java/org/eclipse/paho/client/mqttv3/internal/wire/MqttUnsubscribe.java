package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttUnsubscribe extends MqttWireMessage
{
    private int count;
    private String[] names;
    
    public MqttUnsubscribe(final byte b, byte[] array) throws IOException {
        super((byte)10);
        array = (byte[])(Object)new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = ((DataInputStream)(Object)array).readUnsignedShort();
        int i = 0;
        this.count = 0;
        this.names = new String[10];
        while (i == 0) {
            try {
                this.names[this.count] = MqttWireMessage.decodeUTF8((DataInputStream)(Object)array);
            }
            catch (final Exception ex) {
                i = 1;
            }
        }
        ((DataInputStream)(Object)array).close();
    }
    
    public MqttUnsubscribe(final String[] array) {
        super((byte)10);
        if (array != null) {
            this.names = array.clone();
        }
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
    public byte[] getPayload() throws MqttException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
            final String[] names = this.names;
            for (int length = names.length, i = 0; i < length; ++i) {
                MqttWireMessage.encodeUTF8(dataOutputStream, names[i]);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
            dataOutputStream.writeShort(this.msgId);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    @Override
    public boolean isRetryable() {
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(" names:[");
        for (int i = 0; i < this.count; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            final StringBuilder sb2 = new StringBuilder("\"");
            sb2.append(this.names[i]);
            sb2.append("\"");
            sb.append(sb2.toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
