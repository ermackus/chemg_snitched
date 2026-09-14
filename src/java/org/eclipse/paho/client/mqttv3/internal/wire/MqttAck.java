package org.eclipse.paho.client.mqttv3.internal.wire;

public abstract class MqttAck extends MqttWireMessage
{
    public MqttAck(final byte b) {
        super(b);
    }
    
    @Override
    protected byte getMessageInfo() {
        return 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)super.toString()));
        sb.append(" msgId ");
        sb.append(this.msgId);
        return sb.toString();
    }
}
