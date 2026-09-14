package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import java.util.Enumeration;
import org.eclipse.paho.client.mqttv3.MqttToken;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

public class CommsTokenStore
{
    private static final String CLASS_NAME;
    private MqttException closedResponse;
    private Logger log;
    private String logContext;
    private final Hashtable tokens;
    
    static {
        CLASS_NAME = CommsTokenStore.class.getName();
    }
    
    public CommsTokenStore(final String s) {
        final Logger logger = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CommsTokenStore.CLASS_NAME);
        this.log = logger;
        this.closedResponse = null;
        logger.setResourceName(s);
        this.tokens = new Hashtable();
        this.logContext = s;
        this.log.fine(CommsTokenStore.CLASS_NAME, "<Init>", "308");
    }
    
    public void clear() {
        this.log.fine(CommsTokenStore.CLASS_NAME, "clear", "305", new Object[] { this.tokens.size() });
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.tokens.clear();
        }
    }
    
    public int count() {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            return this.tokens.size();
        }
    }
    
    public MqttDeliveryToken[] getOutstandingDelTokens() {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.log.fine(CommsTokenStore.CLASS_NAME, "getOutstandingDelTokens", "311");
            final Vector vector = new Vector();
            final Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                final MqttToken mqttToken = (MqttToken)elements.nextElement();
                if (mqttToken != null && mqttToken instanceof MqttDeliveryToken && !mqttToken.internalTok.isNotified()) {
                    vector.addElement((Object)mqttToken);
                }
            }
            return (MqttDeliveryToken[])vector.toArray((Object[])new MqttDeliveryToken[vector.size()]);
        }
    }
    
    public Vector getOutstandingTokens() {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.log.fine(CommsTokenStore.CLASS_NAME, "getOutstandingTokens", "312");
            final Vector vector = new Vector();
            final Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                final MqttToken mqttToken = (MqttToken)elements.nextElement();
                if (mqttToken != null) {
                    vector.addElement((Object)mqttToken);
                }
            }
            return vector;
        }
    }
    
    public MqttToken getToken(final String s) {
        return (MqttToken)this.tokens.get((Object)s);
    }
    
    public MqttToken getToken(final MqttWireMessage mqttWireMessage) {
        return (MqttToken)this.tokens.get((Object)mqttWireMessage.getKey());
    }
    
    public void open() {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.log.fine(CommsTokenStore.CLASS_NAME, "open", "310");
            this.closedResponse = null;
        }
    }
    
    protected void quiesce(final MqttException closedResponse) {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.log.fine(CommsTokenStore.CLASS_NAME, "quiesce", "309", new Object[] { closedResponse });
            this.closedResponse = closedResponse;
        }
    }
    
    public MqttToken removeToken(final String s) {
        this.log.fine(CommsTokenStore.CLASS_NAME, "removeToken", "306", new Object[] { s });
        if (s != null) {
            return (MqttToken)this.tokens.remove((Object)s);
        }
        return null;
    }
    
    public MqttToken removeToken(final MqttWireMessage mqttWireMessage) {
        if (mqttWireMessage != null) {
            return this.removeToken(mqttWireMessage.getKey());
        }
        return null;
    }
    
    protected MqttDeliveryToken restoreToken(final MqttPublish mqttPublish) {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            final String string = Integer.toString(mqttPublish.getMessageId());
            MqttDeliveryToken mqttDeliveryToken2;
            if (this.tokens.containsKey((Object)string)) {
                final MqttDeliveryToken mqttDeliveryToken = (MqttDeliveryToken)this.tokens.get((Object)string);
                this.log.fine(CommsTokenStore.CLASS_NAME, "restoreToken", "302", new Object[] { string, mqttPublish, mqttDeliveryToken });
                mqttDeliveryToken2 = mqttDeliveryToken;
            }
            else {
                final MqttDeliveryToken mqttDeliveryToken3 = new MqttDeliveryToken(this.logContext);
                mqttDeliveryToken3.internalTok.setKey(string);
                this.tokens.put((Object)string, (Object)mqttDeliveryToken3);
                this.log.fine(CommsTokenStore.CLASS_NAME, "restoreToken", "303", new Object[] { string, mqttPublish, mqttDeliveryToken3 });
                mqttDeliveryToken2 = mqttDeliveryToken3;
            }
            return mqttDeliveryToken2;
        }
    }
    
    protected void saveToken(final MqttToken mqttToken, final String key) {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            this.log.fine(CommsTokenStore.CLASS_NAME, "saveToken", "307", new Object[] { key, mqttToken.toString() });
            mqttToken.internalTok.setKey(key);
            this.tokens.put((Object)key, (Object)mqttToken);
        }
    }
    
    protected void saveToken(final MqttToken mqttToken, final MqttWireMessage mqttWireMessage) throws MqttException {
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            if (this.closedResponse == null) {
                final String key = mqttWireMessage.getKey();
                this.log.fine(CommsTokenStore.CLASS_NAME, "saveToken", "300", new Object[] { key, mqttWireMessage });
                this.saveToken(mqttToken, key);
                return;
            }
            throw this.closedResponse;
        }
    }
    
    @Override
    public String toString() {
        final String property = System.getProperty("line.separator", "\n");
        final StringBuffer sb = new StringBuffer();
        final Hashtable tokens = this.tokens;
        synchronized (tokens) {
            final Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                final MqttToken mqttToken = (MqttToken)elements.nextElement();
                final StringBuilder sb2 = new StringBuilder("{");
                sb2.append((Object)mqttToken.internalTok);
                sb2.append("}");
                sb2.append(property);
                sb.append(sb2.toString());
            }
            return sb.toString();
        }
    }
}
