package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;

public class MemoryPersistence implements MqttClientPersistence
{
    private Hashtable<String, MqttPersistable> data;
    
    private void checkIsOpen() throws MqttPersistenceException {
        if (this.data != null) {
            return;
        }
        throw new MqttPersistenceException();
    }
    
    @Override
    public void clear() throws MqttPersistenceException {
        this.checkIsOpen();
        this.data.clear();
    }
    
    @Override
    public void close() throws MqttPersistenceException {
        final Hashtable<String, MqttPersistable> data = this.data;
        if (data != null) {
            data.clear();
        }
    }
    
    @Override
    public boolean containsKey(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        return this.data.containsKey((Object)s);
    }
    
    @Override
    public MqttPersistable get(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        return (MqttPersistable)this.data.get((Object)s);
    }
    
    @Override
    public Enumeration<String> keys() throws MqttPersistenceException {
        this.checkIsOpen();
        return (Enumeration<String>)this.data.keys();
    }
    
    @Override
    public void open(final String s, final String s2) throws MqttPersistenceException {
        this.data = (Hashtable<String, MqttPersistable>)new Hashtable();
    }
    
    @Override
    public void put(final String s, final MqttPersistable mqttPersistable) throws MqttPersistenceException {
        this.checkIsOpen();
        this.data.put((Object)s, (Object)mqttPersistable);
    }
    
    @Override
    public void remove(final String s) throws MqttPersistenceException {
        this.checkIsOpen();
        this.data.remove((Object)s);
    }
}
