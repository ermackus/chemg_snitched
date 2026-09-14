package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.io.EOFException;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.io.DataInputStream;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MqttInputStream extends InputStream
{
    private final String CLASS_NAME;
    private ByteArrayOutputStream bais;
    private ClientState clientState;
    private DataInputStream in;
    private final Logger log;
    private byte[] packet;
    private int packetLen;
    private int remLen;
    
    public MqttInputStream(final ClientState clientState, final InputStream inputStream) {
        final String name = MqttInputStream.class.getName();
        this.CLASS_NAME = name;
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", name);
        this.clientState = null;
        this.clientState = clientState;
        this.in = new DataInputStream(inputStream);
        this.bais = new ByteArrayOutputStream();
        this.remLen = -1;
    }
    
    private void readFully() throws IOException {
        final int size = this.bais.size();
        final int packetLen = this.packetLen;
        final int n = this.remLen - packetLen;
        if (n >= 0) {
            int i = 0;
            while (i < n) {
                try {
                    final int read = this.in.read(this.packet, size + packetLen + i, n - i);
                    if (read >= 0) {
                        this.clientState.notifyReceivedBytes(read);
                        i += read;
                        continue;
                    }
                    throw new EOFException();
                }
                catch (final SocketTimeoutException ex) {
                    this.packetLen += i;
                    throw ex;
                }
                throw new IndexOutOfBoundsException();
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }
    
    public int available() throws IOException {
        return this.in.available();
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public int read() throws IOException {
        return this.in.read();
    }
    
    public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
        final Object o = null;
        Object o2 = null;
        MqttWireMessage wireMessage = (MqttWireMessage)o;
        try {
            if (this.remLen < 0) {
                wireMessage = (MqttWireMessage)o;
                this.bais.reset();
                wireMessage = (MqttWireMessage)o;
                final byte byte1 = this.in.readByte();
                wireMessage = (MqttWireMessage)o;
                this.clientState.notifyReceivedBytes(1);
                final byte b = (byte)(byte1 >>> 4 & 0xF);
                if (b < 1 || b > 14) {
                    wireMessage = (MqttWireMessage)o;
                    throw ExceptionHelper.createMqttException(32108);
                }
                wireMessage = (MqttWireMessage)o;
                this.remLen = MqttWireMessage.readMBI(this.in).getValue();
                wireMessage = (MqttWireMessage)o;
                this.bais.write((int)byte1);
                wireMessage = (MqttWireMessage)o;
                this.bais.write(MqttWireMessage.encodeMBI(this.remLen));
                wireMessage = (MqttWireMessage)o;
                this.packet = new byte[this.bais.size() + this.remLen];
                wireMessage = (MqttWireMessage)o;
                this.packetLen = 0;
            }
            wireMessage = (MqttWireMessage)o;
            if (this.remLen >= 0) {
                wireMessage = (MqttWireMessage)o;
                this.readFully();
                wireMessage = (MqttWireMessage)o;
                this.remLen = -1;
                wireMessage = (MqttWireMessage)o;
                final byte[] byteArray = this.bais.toByteArray();
                wireMessage = (MqttWireMessage)o;
                System.arraycopy((Object)byteArray, 0, (Object)this.packet, 0, byteArray.length);
                wireMessage = (MqttWireMessage)o;
                o2 = (wireMessage = MqttWireMessage.createWireMessage(this.packet));
                this.log.fine(this.CLASS_NAME, "readMqttWireMessage", "301", new Object[] { o2 });
            }
            return (MqttWireMessage)o2;
        }
        catch (final SocketTimeoutException ex) {
            o2 = wireMessage;
            return (MqttWireMessage)o2;
        }
    }
}
