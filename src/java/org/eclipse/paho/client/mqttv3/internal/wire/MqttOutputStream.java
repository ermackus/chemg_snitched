package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.io.BufferedOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import java.io.OutputStream;

public class MqttOutputStream extends OutputStream
{
    private static final String CLASS_NAME;
    private ClientState clientState;
    private Logger log;
    private BufferedOutputStream out;
    
    static {
        CLASS_NAME = MqttOutputStream.class.getName();
    }
    
    public MqttOutputStream(final ClientState clientState, final OutputStream outputStream) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", MqttOutputStream.CLASS_NAME);
        this.clientState = null;
        this.clientState = clientState;
        this.out = new BufferedOutputStream(outputStream);
    }
    
    public void close() throws IOException {
        this.out.close();
    }
    
    public void flush() throws IOException {
        this.out.flush();
    }
    
    public void write(final int n) throws IOException {
        this.out.write(n);
    }
    
    public void write(final MqttWireMessage mqttWireMessage) throws IOException, MqttException {
        final byte[] header = mqttWireMessage.getHeader();
        final byte[] payload = mqttWireMessage.getPayload();
        this.out.write(header, 0, header.length);
        this.clientState.notifySentBytes(header.length);
        int i = 0;
        while (i < payload.length) {
            final int min = Math.min(1024, payload.length - i);
            this.out.write(payload, i, min);
            i += 1024;
            this.clientState.notifySentBytes(min);
        }
        this.log.fine(MqttOutputStream.CLASS_NAME, "write", "529", new Object[] { mqttWireMessage });
    }
    
    public void write(final byte[] array) throws IOException {
        this.out.write(array);
        this.clientState.notifySentBytes(array.length);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.out.write(array, n, n2);
        this.clientState.notifySentBytes(n2);
    }
}
