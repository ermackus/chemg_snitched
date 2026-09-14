package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends InputStream
{
    private int counter;
    private InputStream in;
    
    public CountingInputStream(final InputStream in) {
        this.in = in;
        this.counter = 0;
    }
    
    public int getCounter() {
        return this.counter;
    }
    
    public int read() throws IOException {
        final int read = this.in.read();
        if (read != -1) {
            ++this.counter;
        }
        return read;
    }
    
    public void resetCounter() {
        this.counter = 0;
    }
}
