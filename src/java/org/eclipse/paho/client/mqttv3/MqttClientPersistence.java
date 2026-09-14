package org.eclipse.paho.client.mqttv3;

import java.util.Enumeration;

public interface MqttClientPersistence extends AutoCloseable
{
    void clear() throws MqttPersistenceException;
    
    void close() throws MqttPersistenceException;
    
    boolean containsKey(final String p0) throws MqttPersistenceException;
    
    MqttPersistable get(final String p0) throws MqttPersistenceException;
    
    Enumeration keys() throws MqttPersistenceException;
    
    void open(final String p0, final String p1) throws MqttPersistenceException;
    
    void put(final String p0, final MqttPersistable p1) throws MqttPersistenceException;
    
    void remove(final String p0) throws MqttPersistenceException;
}
