package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttPersistable;

public class MqttPersistentData implements MqttPersistable
{
    private int hLength;
    private int hOffset;
    private byte[] header;
    private String key;
    private int pLength;
    private int pOffset;
    private byte[] payload;
    
    public MqttPersistentData(final String key, final byte[] array, final int hOffset, final int hLength, final byte[] array2, final int pOffset, final int pLength) {
        final byte[] array3 = null;
        this.key = null;
        this.header = null;
        this.hOffset = 0;
        this.hLength = 0;
        this.payload = null;
        this.pOffset = 0;
        this.pLength = 0;
        this.key = key;
        this.header = array.clone();
        this.hOffset = hOffset;
        this.hLength = hLength;
        byte[] payload = array3;
        if (array2 != null) {
            payload = array2.clone();
        }
        this.payload = payload;
        this.pOffset = pOffset;
        this.pLength = pLength;
    }
    
    @Override
    public byte[] getHeaderBytes() {
        return this.header;
    }
    
    @Override
    public int getHeaderLength() {
        return this.hLength;
    }
    
    @Override
    public int getHeaderOffset() {
        return this.hOffset;
    }
    
    public String getKey() {
        return this.key;
    }
    
    @Override
    public byte[] getPayloadBytes() {
        return this.payload;
    }
    
    @Override
    public int getPayloadLength() {
        if (this.payload == null) {
            return 0;
        }
        return this.pLength;
    }
    
    @Override
    public int getPayloadOffset() {
        return this.pOffset;
    }
}
