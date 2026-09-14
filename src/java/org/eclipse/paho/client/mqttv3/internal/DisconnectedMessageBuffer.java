package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import java.util.ArrayList;

public class DisconnectedMessageBuffer implements Runnable
{
    private final String CLASS_NAME;
    private final Object bufLock;
    private ArrayList<BufferedMessage> buffer;
    private DisconnectedBufferOptions bufferOpts;
    private IDisconnectedBufferCallback callback;
    private Logger log;
    private IDiscardedBufferMessageCallback messageDiscardedCallBack;
    private int mycount;
    
    public DisconnectedMessageBuffer(final DisconnectedBufferOptions bufferOpts) {
        final String name = DisconnectedMessageBuffer.class.getName();
        this.CLASS_NAME = name;
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", name);
        this.bufLock = new Object();
        this.mycount = 0;
        this.bufferOpts = bufferOpts;
        this.buffer = (ArrayList<BufferedMessage>)new ArrayList();
    }
    
    public void deleteMessage(final int n) {
        final Object bufLock = this.bufLock;
        synchronized (bufLock) {
            this.buffer.remove(n);
        }
    }
    
    public BufferedMessage getMessage(final int n) {
        final Object bufLock = this.bufLock;
        synchronized (bufLock) {
            return (BufferedMessage)this.buffer.get(n);
        }
    }
    
    public int getMessageCount() {
        final Object bufLock = this.bufLock;
        synchronized (bufLock) {
            return this.buffer.size();
        }
    }
    
    public boolean isPersistBuffer() {
        return this.bufferOpts.isPersistBuffer();
    }
    
    public void putMessage(final MqttWireMessage mqttWireMessage, final MqttToken token) throws MqttException {
        if (token != null) {
            mqttWireMessage.setToken(token);
            token.internalTok.setMessageID(mqttWireMessage.getMessageId());
        }
        final BufferedMessage bufferedMessage = new BufferedMessage(mqttWireMessage, token);
        final Object bufLock = this.bufLock;
        synchronized (bufLock) {
            if (this.buffer.size() < this.bufferOpts.getBufferSize()) {
                this.buffer.add((Object)bufferedMessage);
            }
            else {
                if (!this.bufferOpts.isDeleteOldestMessages()) {
                    throw new MqttException(32203);
                }
                if (this.messageDiscardedCallBack != null) {
                    this.messageDiscardedCallBack.messageDiscarded(((BufferedMessage)this.buffer.get(0)).getMessage());
                }
                this.buffer.remove(0);
                this.buffer.add((Object)bufferedMessage);
            }
        }
    }
    
    public void run() {
        this.log.fine(this.CLASS_NAME, "run", "516");
        while (this.getMessageCount() > 0) {
            try {
                this.callback.publishBufferedMessage(this.getMessage(0));
                this.deleteMessage(0);
                continue;
            }
            catch (final MqttException ex) {
                if (ex.getReasonCode() == 32202) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (final Exception ex2) {}
                    continue;
                }
                this.log.severe(this.CLASS_NAME, "run", "519", new Object[] { ex.getReasonCode(), ex.getMessage() });
            }
        }
    }
    
    public void setMessageDiscardedCallBack(final IDiscardedBufferMessageCallback messageDiscardedCallBack) {
        this.messageDiscardedCallBack = messageDiscardedCallBack;
    }
    
    public void setPublishCallback(final IDisconnectedBufferCallback callback) {
        this.callback = callback;
    }
}
