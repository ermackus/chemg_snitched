package org.eclipse.paho.client.mqttv3.internal.wire;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttReceivedMessage extends MqttMessage
{
    public int getMessageId() {
        return super.getId();
    }
    
    public void setDuplicate(final boolean duplicate) {
        super.setDuplicate(duplicate);
    }
    
    public void setMessageId(final int id) {
        super.setId(id);
    }
}
