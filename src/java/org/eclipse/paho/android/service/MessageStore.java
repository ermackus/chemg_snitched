package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Iterator;

interface MessageStore
{
    void clearArrivedMessages(final String p0);
    
    void close();
    
    boolean discardArrived(final String p0, final String p1);
    
    Iterator<StoredMessage> getAllArrivedMessages(final String p0);
    
    String storeArrived(final String p0, final String p1, final MqttMessage p2);
    
    public interface StoredMessage
    {
        String getClientHandle();
        
        MqttMessage getMessage();
        
        String getMessageId();
        
        String getTopic();
    }
}
