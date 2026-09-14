package org.eclipse.paho.client.mqttv3.internal.wire;

public class MultiByteInteger
{
    private int length;
    private int value;
    
    public MultiByteInteger(final int n) {
        this(n, -1);
    }
    
    public MultiByteInteger(final int value, final int length) {
        this.value = value;
        this.length = length;
    }
    
    public int getEncodedLength() {
        return this.length;
    }
    
    public int getValue() {
        return this.value;
    }
}
