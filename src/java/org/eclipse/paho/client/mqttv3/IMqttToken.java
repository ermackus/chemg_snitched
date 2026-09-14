package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public interface IMqttToken
{
    IMqttActionListener getActionCallback();
    
    IMqttAsyncClient getClient();
    
    MqttException getException();
    
    int[] getGrantedQos();
    
    int getMessageId();
    
    MqttWireMessage getResponse();
    
    boolean getSessionPresent();
    
    String[] getTopics();
    
    Object getUserContext();
    
    boolean isComplete();
    
    void setActionCallback(final IMqttActionListener p0);
    
    void setUserContext(final Object p0);
    
    void waitForCompletion() throws MqttException;
    
    void waitForCompletion(final long p0) throws MqttException;
}
