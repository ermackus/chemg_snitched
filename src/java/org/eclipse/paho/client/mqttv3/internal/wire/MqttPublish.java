package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublish extends MqttPersistableWireMessage
{
    private byte[] encodedPayload;
    private MqttMessage message;
    private String topicName;
    
    public MqttPublish(final byte b, byte[] payload) throws MqttException, IOException {
        super((byte)3);
        this.encodedPayload = null;
        (this.message = new MqttReceivedMessage()).setQos(0x3 & b >> 1);
        if ((b & 0x1) == 0x1) {
            this.message.setRetained(true);
        }
        if ((b & 0x8) == 0x8) {
            ((MqttReceivedMessage)this.message).setDuplicate(true);
        }
        final CountingInputStream countingInputStream = new CountingInputStream((InputStream)new ByteArrayInputStream(payload));
        final DataInputStream dataInputStream = new DataInputStream((InputStream)countingInputStream);
        this.topicName = MqttWireMessage.decodeUTF8(dataInputStream);
        if (this.message.getQos() > 0) {
            this.msgId = dataInputStream.readUnsignedShort();
        }
        payload = new byte[payload.length - countingInputStream.getCounter()];
        dataInputStream.readFully(payload);
        dataInputStream.close();
        this.message.setPayload(payload);
    }
    
    public MqttPublish(final String topicName, final MqttMessage message) {
        super((byte)3);
        this.encodedPayload = null;
        this.topicName = topicName;
        this.message = message;
    }
    
    protected static byte[] encodePayload(final MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }
    
    public MqttMessage getMessage() {
        return this.message;
    }
    
    @Override
    protected byte getMessageInfo() {
        byte b2;
        final byte b = b2 = (byte)(this.message.getQos() << 1);
        if (this.message.isRetained()) {
            b2 = (byte)(b | 0x1);
        }
        if (!this.message.isDuplicate()) {
            final byte b3 = b2;
            if (!this.duplicate) {
                return b3;
            }
        }
        return (byte)(b2 | 0x8);
    }
    
    @Override
    public byte[] getPayload() throws MqttException {
        if (this.encodedPayload == null) {
            this.encodedPayload = encodePayload(this.message);
        }
        return this.encodedPayload;
    }
    
    @Override
    public int getPayloadLength() {
        int length;
        try {
            length = this.getPayload().length;
        }
        catch (final MqttException ex) {
            length = 0;
        }
        return length;
    }
    
    public String getTopicName() {
        return this.topicName;
    }
    
    @Override
    protected byte[] getVariableHeader() throws MqttException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
            MqttWireMessage.encodeUTF8(dataOutputStream, this.topicName);
            if (this.message.getQos() > 0) {
                dataOutputStream.writeShort(this.msgId);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    @Override
    public boolean isMessageIdRequired() {
        return true;
    }
    
    @Override
    public void setMessageId(final int n) {
        super.setMessageId(n);
        final MqttMessage message = this.message;
        if (message instanceof MqttReceivedMessage) {
            ((MqttReceivedMessage)message).setMessageId(n);
        }
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final byte[] payload = this.message.getPayload();
        final int min = Math.min(payload.length, 20);
        for (int i = 0; i < min; ++i) {
            String s2;
            final String s = s2 = Integer.toHexString((int)payload[i]);
            if (s.length() == 1) {
                final StringBuilder sb2 = new StringBuilder("0");
                sb2.append(s);
                s2 = sb2.toString();
            }
            sb.append(s2);
        }
        String s3;
        try {
            s3 = new String(payload, 0, min, "UTF-8");
        }
        catch (final Exception ex) {
            s3 = "?";
        }
        final StringBuffer sb3 = new StringBuffer();
        sb3.append(super.toString());
        sb3.append(" qos:");
        sb3.append(this.message.getQos());
        if (this.message.getQos() > 0) {
            sb3.append(" msgId:");
            sb3.append(this.msgId);
        }
        sb3.append(" retained:");
        sb3.append(this.message.isRetained());
        sb3.append(" dup:");
        sb3.append(this.duplicate);
        sb3.append(" topic:\"");
        sb3.append(this.topicName);
        sb3.append("\"");
        sb3.append(" payload:[hex:");
        sb3.append(sb);
        sb3.append(" utf8:\"");
        sb3.append(s3);
        sb3.append("\"");
        sb3.append(" length:");
        sb3.append(payload.length);
        sb3.append("]");
        return sb3.toString();
    }
}
