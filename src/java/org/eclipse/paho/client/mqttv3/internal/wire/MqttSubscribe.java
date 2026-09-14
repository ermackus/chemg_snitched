package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

public class MqttSubscribe extends MqttWireMessage
{
    private int count;
    private String[] names;
    private int[] qos;
    
    public MqttSubscribe(final byte b, byte[] array) throws IOException {
        super((byte)8);
        array = (byte[])(Object)new DataInputStream((InputStream)new ByteArrayInputStream(array));
        this.msgId = ((DataInputStream)(Object)array).readUnsignedShort();
        int i = 0;
        this.count = 0;
        this.names = new String[10];
        this.qos = new int[10];
        while (i == 0) {
            try {
                this.names[this.count] = MqttWireMessage.decodeUTF8((DataInputStream)(Object)array);
                this.qos[this.count++] = ((DataInputStream)(Object)array).readByte();
            }
            catch (final Exception ex) {
                i = 1;
            }
        }
        ((DataInputStream)(Object)array).close();
    }
    
    public MqttSubscribe(final String[] array, final int[] array2) {
        super((byte)8);
        if (array == null || array2 == null) {
            throw new IllegalArgumentException();
        }
        this.names = array.clone();
        final int[] qos = array2.clone();
        this.qos = qos;
        if (this.names.length == qos.length) {
            this.count = array.length;
            for (int length = array2.length, i = 0; i < length; ++i) {
                MqttMessage.validateQos(array2[i]);
            }
            return;
        }
        throw new IllegalArgumentException();
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
            for (int i = 0; i < this.names.length; ++i) {
                MqttWireMessage.encodeUTF8(dataOutputStream, this.names[i]);
                dataOutputStream.writeByte(this.qos[i]);
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
        final int n = 0;
        for (int i = 0; i < this.count; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("\"");
            sb.append(this.names[i]);
            sb.append("\"");
        }
        sb.append("] qos:[");
        for (int j = n; j < this.count; ++j) {
            if (j > 0) {
                sb.append(", ");
            }
            sb.append(this.qos[j]);
        }
        sb.append("]");
        return sb.toString();
    }
}
