package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttToken;

class MqttTokenAndroid implements IMqttToken
{
    private MqttAndroidClient client;
    private IMqttToken delegate;
    private volatile boolean isComplete;
    private volatile MqttException lastException;
    private IMqttActionListener listener;
    private MqttException pendingException;
    private String[] topics;
    private Object userContext;
    private Object waitObject;
    
    MqttTokenAndroid(final MqttAndroidClient mqttAndroidClient, final Object o, final IMqttActionListener mqttActionListener) {
        this(mqttAndroidClient, o, mqttActionListener, null);
    }
    
    MqttTokenAndroid(final MqttAndroidClient client, final Object userContext, final IMqttActionListener listener, final String[] topics) {
        this.waitObject = new Object();
        this.client = client;
        this.userContext = userContext;
        this.listener = listener;
        this.topics = topics;
    }
    
    @Override
    public IMqttActionListener getActionCallback() {
        return this.listener;
    }
    
    @Override
    public IMqttAsyncClient getClient() {
        return this.client;
    }
    
    @Override
    public MqttException getException() {
        return this.lastException;
    }
    
    @Override
    public int[] getGrantedQos() {
        return this.delegate.getGrantedQos();
    }
    
    @Override
    public int getMessageId() {
        final IMqttToken delegate = this.delegate;
        int messageId;
        if (delegate != null) {
            messageId = delegate.getMessageId();
        }
        else {
            messageId = 0;
        }
        return messageId;
    }
    
    @Override
    public MqttWireMessage getResponse() {
        return this.delegate.getResponse();
    }
    
    @Override
    public boolean getSessionPresent() {
        return this.delegate.getSessionPresent();
    }
    
    @Override
    public String[] getTopics() {
        return this.topics;
    }
    
    @Override
    public Object getUserContext() {
        return this.userContext;
    }
    
    @Override
    public boolean isComplete() {
        return this.isComplete;
    }
    
    void notifyComplete() {
        final Object waitObject = this.waitObject;
        synchronized (waitObject) {
            this.isComplete = true;
            this.waitObject.notifyAll();
            if (this.listener != null) {
                this.listener.onSuccess(this);
            }
        }
    }
    
    void notifyFailure(final Throwable t) {
        final Object waitObject = this.waitObject;
        synchronized (waitObject) {
            this.isComplete = true;
            if (t instanceof MqttException) {
                this.pendingException = (MqttException)t;
            }
            else {
                this.pendingException = new MqttException(t);
            }
            this.waitObject.notifyAll();
            if (t instanceof MqttException) {
                this.lastException = (MqttException)t;
            }
            if (this.listener != null) {
                this.listener.onFailure(this, t);
            }
        }
    }
    
    @Override
    public void setActionCallback(final IMqttActionListener listener) {
        this.listener = listener;
    }
    
    void setComplete(final boolean isComplete) {
        this.isComplete = isComplete;
    }
    
    void setDelegate(final IMqttToken delegate) {
        this.delegate = delegate;
    }
    
    void setException(final MqttException lastException) {
        this.lastException = lastException;
    }
    
    @Override
    public void setUserContext(final Object userContext) {
        this.userContext = userContext;
    }
    
    @Override
    public void waitForCompletion() throws MqttException, MqttSecurityException {
        final Object waitObject;
        monitorenter(waitObject = this.waitObject);
        while (true) {
            try {
                try {
                    this.waitObject.wait();
                }
                finally {
                    monitorexit(waitObject);
                    Label_0035: {
                        throw;
                    }
                    monitorexit(waitObject);
                    final MqttException pendingException = this.pendingException;
                    iftrue(Label_0035:)(pendingException != null);
                }
            }
            catch (final InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void waitForCompletion(final long timeout) throws MqttException, MqttSecurityException {
        final Object waitObject;
        monitorenter(waitObject = this.waitObject);
        while (true) {
            try {
                try {
                    this.waitObject.wait(timeout);
                }
                finally {
                    monitorexit(waitObject);
                    iftrue(Label_0049:)(!this.isComplete);
                    iftrue(Label_0044:)(this.pendingException != null);
                    Block_6: {
                        break Block_6;
                        Label_0044: {
                            throw this.pendingException;
                        }
                    }
                    monitorexit(waitObject);
                    return;
                    Label_0049: {
                        throw new MqttException(32000);
                    }
                }
            }
            catch (final InterruptedException ex) {
                continue;
            }
            break;
        }
    }
}
