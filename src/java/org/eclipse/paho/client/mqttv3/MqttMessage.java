package org.eclipse.paho.client.mqttv3;

public class MqttMessage
{
    private boolean dup;
    private int messageId;
    private boolean mutable;
    private byte[] payload;
    private int qos;
    private boolean retained;
    
    public MqttMessage() {
        this.mutable = true;
        this.qos = 1;
        this.retained = false;
        this.dup = false;
        this.setPayload(new byte[0]);
    }
    
    public MqttMessage(final byte[] payload) {
        this.mutable = true;
        this.qos = 1;
        this.retained = false;
        this.dup = false;
        this.setPayload(payload);
    }
    
    public static void validateQos(final int n) {
        if (n >= 0 && n <= 2) {
            return;
        }
        throw new IllegalArgumentException();
    }
    
    protected void checkMutable() throws IllegalStateException {
        if (this.mutable) {
            return;
        }
        throw new IllegalStateException();
    }
    
    public void clearPayload() {
        this.checkMutable();
        this.payload = new byte[0];
    }
    
    public int getId() {
        return this.messageId;
    }
    
    public byte[] getPayload() {
        return this.payload;
    }
    
    public int getQos() {
        return this.qos;
    }
    
    public boolean isDuplicate() {
        return this.dup;
    }
    
    public boolean isRetained() {
        return this.retained;
    }
    
    protected void setDuplicate(final boolean dup) {
        this.dup = dup;
    }
    
    public void setId(final int messageId) {
        this.messageId = messageId;
    }
    
    protected void setMutable(final boolean mutable) {
        this.mutable = mutable;
    }
    
    public void setPayload(final byte[] array) {
        this.checkMutable();
        if (array != null) {
            this.payload = array.clone();
            return;
        }
        throw null;
    }
    
    public void setQos(final int qos) {
        this.checkMutable();
        validateQos(qos);
        this.qos = qos;
    }
    
    public void setRetained(final boolean retained) {
        this.checkMutable();
        this.retained = retained;
    }
    
    @Override
    public String toString() {
        return new String(this.payload);
    }
}
