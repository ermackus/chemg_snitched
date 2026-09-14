package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class MultiByteArrayInputStream extends InputStream
{
    private byte[] bytesA;
    private byte[] bytesB;
    private int lengthA;
    private int lengthB;
    private int offsetA;
    private int offsetB;
    private int pos;
    
    public MultiByteArrayInputStream(final byte[] array, final int offsetA, final int lengthA, final byte[] array2, final int offsetB, final int lengthB) {
        this.pos = 0;
        this.bytesA = array.clone();
        this.bytesB = array2.clone();
        this.offsetA = offsetA;
        this.offsetB = offsetB;
        this.lengthA = lengthA;
        this.lengthB = lengthB;
    }
    
    public int read() throws IOException {
        final int pos = this.pos;
        final int lengthA = this.lengthA;
        byte b;
        if (pos < lengthA) {
            b = this.bytesA[this.offsetA + pos];
        }
        else {
            if (pos >= this.lengthB + lengthA) {
                return -1;
            }
            b = this.bytesB[this.offsetB + pos - lengthA];
        }
        int n = b;
        if (b < 0) {
            n = b + 256;
        }
        ++this.pos;
        return n;
    }
}
