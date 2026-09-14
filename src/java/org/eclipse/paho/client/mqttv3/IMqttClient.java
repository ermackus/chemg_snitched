package org.eclipse.paho.client.mqttv3;

public interface IMqttClient extends AutoCloseable
{
    void close() throws MqttException;
    
    void connect() throws MqttSecurityException, MqttException;
    
    void connect(final MqttConnectOptions p0) throws MqttSecurityException, MqttException;
    
    IMqttToken connectWithResult(final MqttConnectOptions p0) throws MqttSecurityException, MqttException;
    
    void disconnect() throws MqttException;
    
    void disconnect(final long p0) throws MqttException;
    
    void disconnectForcibly() throws MqttException;
    
    void disconnectForcibly(final long p0) throws MqttException;
    
    void disconnectForcibly(final long p0, final long p1) throws MqttException;
    
    String getClientId();
    
    IMqttDeliveryToken[] getPendingDeliveryTokens();
    
    String getServerURI();
    
    MqttTopic getTopic(final String p0);
    
    boolean isConnected();
    
    void messageArrivedComplete(final int p0, final int p1) throws MqttException;
    
    void publish(final String p0, final MqttMessage p1) throws MqttException, MqttPersistenceException;
    
    void publish(final String p0, final byte[] p1, final int p2, final boolean p3) throws MqttException, MqttPersistenceException;
    
    void reconnect() throws MqttException;
    
    void setCallback(final MqttCallback p0);
    
    void setManualAcks(final boolean p0);
    
    void subscribe(final String p0) throws MqttException, MqttSecurityException;
    
    void subscribe(final String p0, final int p1) throws MqttException;
    
    void subscribe(final String p0, final int p1, final IMqttMessageListener p2) throws MqttException;
    
    void subscribe(final String p0, final IMqttMessageListener p1) throws MqttException, MqttSecurityException;
    
    void subscribe(final String[] p0) throws MqttException;
    
    void subscribe(final String[] p0, final int[] p1) throws MqttException;
    
    void subscribe(final String[] p0, final int[] p1, final IMqttMessageListener[] p2) throws MqttException;
    
    void subscribe(final String[] p0, final IMqttMessageListener[] p1) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String p0) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String p0, final int p1) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String p0, final int p1, final IMqttMessageListener p2) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String p0, final IMqttMessageListener p1) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String[] p0) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String[] p0, final int[] p1) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String[] p0, final int[] p1, final IMqttMessageListener[] p2) throws MqttException;
    
    IMqttToken subscribeWithResponse(final String[] p0, final IMqttMessageListener[] p1) throws MqttException;
    
    void unsubscribe(final String p0) throws MqttException;
    
    void unsubscribe(final String[] p0) throws MqttException;
}
