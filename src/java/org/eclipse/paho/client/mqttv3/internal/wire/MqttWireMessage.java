package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.client.mqttv3.MqttToken;
import java.nio.charset.Charset;

public abstract class MqttWireMessage
{
    private static final long FOUR_BYTE_INT_MAX = 4294967295L;
    public static final byte MESSAGE_TYPE_CONNACK = 2;
    public static final byte MESSAGE_TYPE_CONNECT = 1;
    public static final byte MESSAGE_TYPE_DISCONNECT = 14;
    public static final byte MESSAGE_TYPE_PINGREQ = 12;
    public static final byte MESSAGE_TYPE_PINGRESP = 13;
    public static final byte MESSAGE_TYPE_PUBACK = 4;
    public static final byte MESSAGE_TYPE_PUBCOMP = 7;
    public static final byte MESSAGE_TYPE_PUBLISH = 3;
    public static final byte MESSAGE_TYPE_PUBREC = 5;
    public static final byte MESSAGE_TYPE_PUBREL = 6;
    public static final byte MESSAGE_TYPE_SUBACK = 9;
    public static final byte MESSAGE_TYPE_SUBSCRIBE = 8;
    public static final byte MESSAGE_TYPE_UNSUBACK = 11;
    public static final byte MESSAGE_TYPE_UNSUBSCRIBE = 10;
    private static final String[] PACKET_NAMES;
    protected static final Charset STRING_ENCODING;
    private static final int VARIABLE_BYTE_INT_MAX = 268435455;
    protected boolean duplicate;
    protected int msgId;
    private MqttToken token;
    private byte type;
    
    static {
        STRING_ENCODING = StandardCharsets.UTF_8;
        PACKET_NAMES = new String[] { "reserved", "CONNECT", "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT" };
    }
    
    public MqttWireMessage(final byte type) {
        this.duplicate = false;
        this.type = type;
        this.msgId = 0;
    }
    
    private static MqttWireMessage createWireMessage(final InputStream inputStream) throws MqttException {
        try {
            final CountingInputStream countingInputStream = new CountingInputStream(inputStream);
            final DataInputStream dataInputStream = new DataInputStream((InputStream)countingInputStream);
            final int unsignedByte = dataInputStream.readUnsignedByte();
            final byte b = (byte)(unsignedByte >> 4);
            final byte b2 = (byte)(unsignedByte & 0xF);
            final long n = countingInputStream.getCounter() + (long)readMBI(dataInputStream).getValue() - countingInputStream.getCounter();
            byte[] array = new byte[0];
            if (n > 0L) {
                final int n2 = (int)n;
                array = new byte[n2];
                dataInputStream.readFully(array, 0, n2);
            }
            MqttWireMessage mqttWireMessage;
            if (b == 1) {
                mqttWireMessage = new MqttConnect(b2, array);
            }
            else if (b == 3) {
                mqttWireMessage = new MqttPublish(b2, array);
            }
            else if (b == 4) {
                mqttWireMessage = new MqttPubAck(b2, array);
            }
            else if (b == 7) {
                mqttWireMessage = new MqttPubComp(b2, array);
            }
            else if (b == 2) {
                mqttWireMessage = new MqttConnack(b2, array);
            }
            else if (b == 12) {
                mqttWireMessage = new MqttPingReq(b2, array);
            }
            else if (b == 13) {
                mqttWireMessage = new MqttPingResp(b2, array);
            }
            else if (b == 8) {
                mqttWireMessage = new MqttSubscribe(b2, array);
            }
            else if (b == 9) {
                mqttWireMessage = new MqttSuback(b2, array);
            }
            else if (b == 10) {
                mqttWireMessage = new MqttUnsubscribe(b2, array);
            }
            else if (b == 11) {
                mqttWireMessage = new MqttUnsubAck(b2, array);
            }
            else if (b == 6) {
                mqttWireMessage = new MqttPubRel(b2, array);
            }
            else if (b == 5) {
                mqttWireMessage = new MqttPubRec(b2, array);
            }
            else {
                if (b != 14) {
                    throw ExceptionHelper.createMqttException(6);
                }
                mqttWireMessage = new MqttDisconnect(b2, array);
            }
            return mqttWireMessage;
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    public static MqttWireMessage createWireMessage(final MqttPersistable mqttPersistable) throws MqttException {
        byte[] payloadBytes;
        if ((payloadBytes = mqttPersistable.getPayloadBytes()) == null) {
            payloadBytes = new byte[0];
        }
        return createWireMessage(new MultiByteArrayInputStream(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength(), payloadBytes, mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength()));
    }
    
    public static MqttWireMessage createWireMessage(final byte[] array) throws MqttException {
        return createWireMessage((InputStream)new ByteArrayInputStream(array));
    }
    
    public static String decodeUTF8(final DataInputStream dataInputStream) throws MqttException {
        try {
            final byte[] array = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(array);
            final String s = new String(array, MqttWireMessage.STRING_ENCODING);
            validateUTF8String(s);
            return s;
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    public static byte[] encodeMBI(long n) {
        validateVariableByteInt((int)n);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n2 = 0;
        long n3;
        int n4;
        do {
            final byte b = (byte)(n % 128L);
            n /= 128L;
            n3 = lcmp(n, 0L);
            byte b2 = b;
            if (n3 > 0) {
                b2 = (byte)(b | 0x80);
            }
            byteArrayOutputStream.write((int)b2);
            n4 = n2 + 1;
        } while (n3 > 0 && (n2 = n4) < 4);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void encodeUTF8(final DataOutputStream dataOutputStream, final String s) throws MqttException {
        validateUTF8String(s);
        try {
            final byte[] bytes = s.getBytes(MqttWireMessage.STRING_ENCODING);
            final byte b = (byte)(bytes.length >>> 8 & 0xFF);
            final byte b2 = (byte)(bytes.length >>> 0 & 0xFF);
            dataOutputStream.write((int)b);
            dataOutputStream.write((int)b2);
            dataOutputStream.write(bytes);
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
        catch (final UnsupportedEncodingException ex2) {
            throw new MqttException((Throwable)ex2);
        }
    }
    
    public static MultiByteInteger readMBI(final DataInputStream dataInputStream) throws IOException {
        int n = 0;
        int n2 = 0;
        int n3 = 1;
        byte byte1;
        int n4;
        int n5;
        do {
            byte1 = dataInputStream.readByte();
            n4 = n + 1;
            n5 = n2 + (byte1 & 0x7F) * n3;
            n3 *= 128;
            n = n4;
            n2 = n5;
        } while ((byte1 & 0x80) != 0x0);
        if (n5 >= 0 && n5 <= 268435455) {
            return new MultiByteInteger(n5, n4);
        }
        final StringBuilder sb = new StringBuilder("This property must be a number between 0 and 268435455. Read value was: ");
        sb.append(n5);
        throw new IOException(sb.toString());
    }
    
    private static void validateUTF8String(final String s) throws IllegalArgumentException {
        int n = 0;
        for (int i = 0; i < s.length(); i = n + 1) {
            final char char1 = s.charAt(i);
            boolean b = false;
            Label_0184: {
                Label_0182: {
                    if (Character.isHighSurrogate(char1)) {
                        if (++i == s.length()) {
                            n = i;
                            break Label_0182;
                        }
                        final char char2 = s.charAt(i);
                        if (Character.isLowSurrogate(char2)) {
                            n = i;
                            break Label_0182;
                        }
                        final int n2 = ((char2 & '\u03ff') | (char1 & '\u03ff') << 10) & 0xFFFF;
                        n = i;
                        if (n2 == 65535) {
                            break Label_0182;
                        }
                        n = i;
                        if (n2 == 65534) {
                            n = i;
                            break Label_0182;
                        }
                    }
                    else {
                        n = i;
                        if (Character.isISOControl(char1)) {
                            break Label_0182;
                        }
                        if (Character.isLowSurrogate(char1)) {
                            n = i;
                            break Label_0182;
                        }
                        n = i;
                        if (char1 >= '\ufdd0') {
                            n = i;
                            if (char1 == '\ufffe') {
                                break Label_0182;
                            }
                            n = i;
                            if (char1 >= '\ufdd0') {
                                break Label_0182;
                            }
                            n = i;
                            if (char1 <= '\ufddf') {
                                n = i;
                                break Label_0182;
                            }
                        }
                    }
                    b = false;
                    break Label_0184;
                }
                b = true;
            }
            if (b) {
                throw new IllegalArgumentException(String.format("Invalid UTF-8 char: [%x]", new Object[] { (int)char1 }));
            }
        }
    }
    
    public static void validateVariableByteInt(final int n) throws IllegalArgumentException {
        if (n >= 0 && n <= 268435455) {
            return;
        }
        throw new IllegalArgumentException("This property must be a number between 0 and 268435455");
    }
    
    protected byte[] encodeMessageId() throws MqttException {
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
    
    public byte[] getHeader() throws MqttException {
        try {
            final byte type = this.getType();
            final byte messageInfo = this.getMessageInfo();
            final byte[] variableHeader = this.getVariableHeader();
            final int length = variableHeader.length;
            final int length2 = this.getPayload().length;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)byteArrayOutputStream);
            dataOutputStream.writeByte((type & 0xF) << 4 ^ (messageInfo & 0xF));
            dataOutputStream.write(encodeMBI(length + length2));
            dataOutputStream.write(variableHeader);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            throw new MqttException((Throwable)ex);
        }
    }
    
    public String getKey() {
        return Integer.toString(this.getMessageId());
    }
    
    public int getMessageId() {
        return this.msgId;
    }
    
    protected abstract byte getMessageInfo();
    
    public byte[] getPayload() throws MqttException {
        return new byte[0];
    }
    
    public MqttToken getToken() {
        return this.token;
    }
    
    public byte getType() {
        return this.type;
    }
    
    protected abstract byte[] getVariableHeader() throws MqttException;
    
    public boolean isMessageIdRequired() {
        return true;
    }
    
    public boolean isRetryable() {
        return false;
    }
    
    public void setDuplicate(final boolean duplicate) {
        this.duplicate = duplicate;
    }
    
    public void setMessageId(final int msgId) {
        this.msgId = msgId;
    }
    
    public void setToken(final MqttToken token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return MqttWireMessage.PACKET_NAMES[this.type];
    }
}
