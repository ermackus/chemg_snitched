package org.eclipse.paho.client.mqttv3;

public interface IMqttAsyncClient extends AutoCloseable
{
    void close() throws MqttException;
    
    IMqttToken connect() throws MqttException, MqttSecurityException;
    
    IMqttToken connect(final Object p0, final IMqttActionListener p1) throws MqttException, MqttSecurityException;
    
    IMqttToken connect(final MqttConnectOptions p0) throws MqttException, MqttSecurityException;
    
    IMqttToken connect(final MqttConnectOptions p0, final Object p1, final IMqttActionListener p2) throws MqttException, MqttSecurityException;
    
    void deleteBufferedMessage(final int p0);
    
    IMqttToken disconnect() throws MqttException;
    
    IMqttToken disconnect(final long p0) throws MqttException;
    
    IMqttToken disconnect(final long p0, final Object p1, final IMqttActionListener p2) throws MqttException;
    
    IMqttToken disconnect(final Object p0, final IMqttActionListener p1) throws MqttException;
    
    void disconnectForcibly() throws MqttException;
    
    void disconnectForcibly(final long p0) throws MqttException;
    
    void disconnectForcibly(final long p0, final long p1) throws MqttException;
    
    MqttMessage getBufferedMessage(final int p0);
    
    int getBufferedMessageCount();
    
    String getClientId();
    
    int getInFlightMessageCount();
    
    IMqttDeliveryToken[] getPendingDeliveryTokens();
    
    String getServerURI();
    
    boolean isConnected();
    
    void messageArrivedComplete(final int p0, final int p1) throws MqttException;
    
    IMqttDeliveryToken publish(final String p0, final MqttMessage p1) throws MqttException, MqttPersistenceException;
    
    IMqttDeliveryToken publish(final String p0, final MqttMessage p1, final Object p2, final IMqttActionListener p3) throws MqttException, MqttPersistenceException;
    
    IMqttDeliveryToken publish(final String p0, final byte[] p1, final int p2, final boolean p3) throws MqttException, MqttPersistenceException;
    
    IMqttDeliveryToken publish(final String p0, final byte[] p1, final int p2, final boolean p3, final Object p4, final IMqttActionListener p5) throws MqttException, MqttPersistenceException;
    
    void reconnect() throws MqttException;
    
    boolean removeMessage(final IMqttDeliveryToken p0) throws MqttException;
    
    void setBufferOpts(final DisconnectedBufferOptions p0);
    
    void setCallback(final MqttCallback p0);
    
    void setManualAcks(final boolean p0);
    
    IMqttToken subscribe(final String p0, final int p1) throws MqttException;
    
    IMqttToken subscribe(final String p0, final int p1, final Object p2, final IMqttActionListener p3) throws MqttException;
    
    IMqttToken subscribe(final String p0, final int p1, final Object p2, final IMqttActionListener p3, final IMqttMessageListener p4) throws MqttException;
    
    IMqttToken subscribe(final String p0, final int p1, final IMqttMessageListener p2) throws MqttException;
    
    IMqttToken subscribe(final String[] p0, final int[] p1) throws MqttException;
    
    IMqttToken subscribe(final String[] p0, final int[] p1, final Object p2, final IMqttActionListener p3) throws MqttException;
    
    IMqttToken subscribe(final String[] p0, final int[] p1, final Object p2, final IMqttActionListener p3, final IMqttMessageListener[] p4) throws MqttException;
    
    IMqttToken subscribe(final String[] p0, final int[] p1, final IMqttMessageListener[] p2) throws MqttException;
    
    IMqttToken unsubscribe(final String p0) throws MqttException;
    
    IMqttToken unsubscribe(final String p0, final Object p1, final IMqttActionListener p2) throws MqttException;
    
    IMqttToken unsubscribe(final String[] p0) throws MqttException;
    
    IMqttToken unsubscribe(final String[] p0, final Object p1, final IMqttActionListener p2) throws MqttException;
}
