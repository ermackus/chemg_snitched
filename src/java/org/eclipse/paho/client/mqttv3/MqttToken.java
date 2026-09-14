package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.internal.Token;

public class MqttToken implements IMqttToken
{
    public Token internalTok;
    
    public MqttToken() {
        this.internalTok = null;
    }
    
    public MqttToken(final String s) {
        this.internalTok = null;
        this.internalTok = new Token(s);
    }
    
    @Override
    public IMqttActionListener getActionCallback() {
        return this.internalTok.getActionCallback();
    }
    
    @Override
    public IMqttAsyncClient getClient() {
        return this.internalTok.getClient();
    }
    
    @Override
    public MqttException getException() {
        return this.internalTok.getException();
    }
    
    @Override
    public int[] getGrantedQos() {
        return this.internalTok.getGrantedQos();
    }
    
    @Override
    public int getMessageId() {
        return this.internalTok.getMessageID();
    }
    
    @Override
    public MqttWireMessage getResponse() {
        return this.internalTok.getResponse();
    }
    
    @Override
    public boolean getSessionPresent() {
        return this.internalTok.getSessionPresent();
    }
    
    @Override
    public String[] getTopics() {
        return this.internalTok.getTopics();
    }
    
    @Override
    public Object getUserContext() {
        return this.internalTok.getUserContext();
    }
    
    @Override
    public boolean isComplete() {
        return this.internalTok.isComplete();
    }
    
    @Override
    public void setActionCallback(final IMqttActionListener actionCallback) {
        this.internalTok.setActionCallback(actionCallback);
    }
    
    @Override
    public void setUserContext(final Object userContext) {
        this.internalTok.setUserContext(userContext);
    }
    
    @Override
    public void waitForCompletion() throws MqttException {
        this.internalTok.waitForCompletion(-1L);
    }
    
    @Override
    public void waitForCompletion(final long n) throws MqttException {
        this.internalTok.waitForCompletion(n);
    }
}
