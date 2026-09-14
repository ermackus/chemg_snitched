package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;

public abstract class MqttPersistableWireMessage extends MqttWireMessage implements MqttPersistable
{
    public MqttPersistableWireMessage(final byte b) {
        super(b);
    }
    
    @Override
    public byte[] getHeaderBytes() throws MqttPersistenceException {
        try {
            return this.getHeader();
        }
        catch (final MqttException ex) {
            throw new MqttPersistenceException(ex.getCause());
        }
    }
    
    @Override
    public int getHeaderLength() throws MqttPersistenceException {
        return this.getHeaderBytes().length;
    }
    
    @Override
    public int getHeaderOffset() throws MqttPersistenceException {
        return 0;
    }
    
    @Override
    public byte[] getPayloadBytes() throws MqttPersistenceException {
        try {
            return this.getPayload();
        }
        catch (final MqttException ex) {
            throw new MqttPersistenceException(ex.getCause());
        }
    }
    
    @Override
    public int getPayloadLength() throws MqttPersistenceException {
        return 0;
    }
    
    @Override
    public int getPayloadOffset() throws MqttPersistenceException {
        return 0;
    }
}
