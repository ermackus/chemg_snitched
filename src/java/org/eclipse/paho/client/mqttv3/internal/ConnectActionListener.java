package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;

public class ConnectActionListener implements IMqttActionListener
{
    private MqttAsyncClient client;
    private ClientComms comms;
    private MqttCallbackExtended mqttCallbackExtended;
    private MqttConnectOptions options;
    private int originalMqttVersion;
    private MqttClientPersistence persistence;
    private boolean reconnect;
    private IMqttActionListener userCallback;
    private Object userContext;
    private MqttToken userToken;
    
    public ConnectActionListener(final MqttAsyncClient client, final MqttClientPersistence persistence, final ClientComms comms, final MqttConnectOptions options, final MqttToken userToken, final Object userContext, final IMqttActionListener userCallback, final boolean reconnect) {
        this.persistence = persistence;
        this.client = client;
        this.comms = comms;
        this.options = options;
        this.userToken = userToken;
        this.userContext = userContext;
        this.userCallback = userCallback;
        this.originalMqttVersion = options.getMqttVersion();
        this.reconnect = reconnect;
    }
    
    public void connect() throws MqttPersistenceException {
        final MqttToken mqttToken = new MqttToken(this.client.getClientId());
        mqttToken.setActionCallback(this);
        mqttToken.setUserContext(this);
        this.persistence.open(this.client.getClientId(), this.client.getServerURI());
        if (this.options.isCleanSession()) {
            this.persistence.clear();
        }
        if (this.options.getMqttVersion() == 0) {
            this.options.setMqttVersion(4);
        }
        try {
            this.comms.connect(this.options, mqttToken);
        }
        catch (final MqttException ex) {
            this.onFailure(mqttToken, (Throwable)ex);
        }
    }
    
    @Override
    public void onFailure(final IMqttToken mqttToken, final Throwable t) {
        final int length = this.comms.getNetworkModules().length;
        final int n = this.comms.getNetworkModuleIndex() + 1;
        if (n >= length && (this.originalMqttVersion != 0 || this.options.getMqttVersion() != 4)) {
            if (this.originalMqttVersion == 0) {
                this.options.setMqttVersion(0);
            }
            MqttException ex;
            if (t instanceof MqttException) {
                ex = (MqttException)t;
            }
            else {
                ex = new MqttException(t);
            }
            this.userToken.internalTok.markComplete(null, ex);
            this.userToken.internalTok.notifyComplete();
            this.userToken.internalTok.setClient(this.client);
            if (this.userCallback != null) {
                this.userToken.setUserContext(this.userContext);
                this.userCallback.onFailure(this.userToken, t);
            }
        }
        else {
            if (this.originalMqttVersion == 0) {
                if (this.options.getMqttVersion() == 4) {
                    this.options.setMqttVersion(3);
                }
                else {
                    this.options.setMqttVersion(4);
                    this.comms.setNetworkModuleIndex(n);
                }
            }
            else {
                this.comms.setNetworkModuleIndex(n);
            }
            try {
                this.connect();
            }
            catch (final MqttPersistenceException ex2) {
                this.onFailure(mqttToken, (Throwable)ex2);
            }
        }
    }
    
    @Override
    public void onSuccess(final IMqttToken mqttToken) {
        if (this.originalMqttVersion == 0) {
            this.options.setMqttVersion(0);
        }
        this.userToken.internalTok.markComplete(mqttToken.getResponse(), null);
        this.userToken.internalTok.notifyComplete();
        this.userToken.internalTok.setClient(this.client);
        this.comms.notifyConnect();
        if (this.userCallback != null) {
            this.userToken.setUserContext(this.userContext);
            this.userCallback.onSuccess(this.userToken);
        }
        if (this.mqttCallbackExtended != null) {
            this.mqttCallbackExtended.connectComplete(this.reconnect, this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI());
        }
    }
    
    public void setMqttCallbackExtended(final MqttCallbackExtended mqttCallbackExtended) {
        this.mqttCallbackExtended = mqttCallbackExtended;
    }
}
