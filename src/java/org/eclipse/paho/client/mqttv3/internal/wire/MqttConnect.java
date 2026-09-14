package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttConnect extends MqttWireMessage
{
    public static final String KEY = "Con";
    private boolean cleanSession;
    private String clientId;
    private int keepAliveInterval;
    private int mqttVersion;
    private char[] password;
    private String userName;
    private String willDestination;
    private MqttMessage willMessage;
    
    public MqttConnect(final byte b, final byte[] array) throws IOException, MqttException {
        super((byte)1);
        final DataInputStream dataInputStream = new DataInputStream((InputStream)new ByteArrayInputStream(array));
        MqttWireMessage.decodeUTF8(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.keepAliveInterval = dataInputStream.readUnsignedShort();
        this.clientId = MqttWireMessage.decodeUTF8(dataInputStream);
        dataInputStream.close();
    }
    
    public MqttConnect(final String clientId, final int mqttVersion, final boolean cleanSession, final int keepAliveInterval, final String userName, final char[] array, final MqttMessage willMessage, final String willDestination) {
        super((byte)1);
        this.clientId = clientId;
        this.cleanSession = cleanSession;
        this.keepAliveInterval = keepAliveInterval;
        this.userName = userName;
        if (array != null) {
            this.password = array.clone();
        }
        this.willMessage = willMessage;
        this.willDestination = willDestination;
        this.mqttVersion = mqttVersion;
    }
    
    @Override
    public String getKey() {
        return "Con";
    }
    
    @Override
    protected byte getMessageInfo() {
        return 0;
    }
    
    @Override
    public byte[] getPayload() throws MqttException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
            MqttWireMessage.encodeUTF8(dataOutputStream, this.clientId);
            if (this.willMessage != null) {
                MqttWireMessage.encodeUTF8(dataOutputStream, this.willDestination);
                dataOutputStream.writeShort(this.willMessage.getPayload().length);
                dataOutputStream.write(this.willMessage.getPayload());
            }
            if (this.userName != null) {
                MqttWireMessage.encodeUTF8(dataOutputStream, this.userName);
                if (this.password != null) {
                    MqttWireMessage.encodeUTF8(dataOutputStream, new String(this.password));
                }
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
            if (this.mqttVersion == 3) {
                MqttWireMessage.encodeUTF8(dataOutputStream, "MQIsdp");
            }
            else if (this.mqttVersion == 4) {
                MqttWireMessage.encodeUTF8(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.mqttVersion);
            byte b = 0;
            if (this.cleanSession) {
                b = 2;
            }
            byte b2 = b;
            if (this.willMessage != null) {
                final byte b3 = b2 = (byte)((byte)(b | 0x4) | this.willMessage.getQos() << 3);
                if (this.willMessage.isRetained()) {
                    b2 = (byte)(b3 | 0x20);
                }
            }
            byte b4 = b2;
            if (this.userName != null) {
                final byte b5 = b4 = (byte)(b2 | 0x80);
                if (this.password != null) {
                    b4 = (byte)(b5 | 0x40);
                }
            }
            dataOutputStream.write((int)b4);
            dataOutputStream.writeShort(this.keepAliveInterval);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    public boolean isCleanSession() {
        return this.cleanSession;
    }
    
    @Override
    public boolean isMessageIdRequired() {
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)super.toString()));
        sb.append(" clientId ");
        sb.append(this.clientId);
        sb.append(" keepAliveInterval ");
        sb.append(this.keepAliveInterval);
        return sb.toString();
    }
}
