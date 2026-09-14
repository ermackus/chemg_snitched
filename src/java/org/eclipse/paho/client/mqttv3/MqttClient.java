package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.util.Debug;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MqttClient implements IMqttClient
{
    protected MqttAsyncClient aClient;
    protected long timeToWait;
    
    public MqttClient(final String s, final String s2) throws MqttException {
        this(s, s2, new MqttDefaultFilePersistence());
    }
    
    public MqttClient(final String s, final String s2, final MqttClientPersistence mqttClientPersistence) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(s, s2, mqttClientPersistence);
    }
    
    public MqttClient(final String s, final String s2, final MqttClientPersistence mqttClientPersistence, final ScheduledExecutorService scheduledExecutorService) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(s, s2, mqttClientPersistence, new ScheduledExecutorPingSender(scheduledExecutorService), scheduledExecutorService);
    }
    
    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }
    
    @Override
    public void close() throws MqttException {
        this.aClient.close(false);
    }
    
    public void close(final boolean b) throws MqttException {
        this.aClient.close(b);
    }
    
    @Override
    public void connect() throws MqttSecurityException, MqttException {
        this.connect(new MqttConnectOptions());
    }
    
    @Override
    public void connect(final MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        this.aClient.connect(mqttConnectOptions, null, null).waitForCompletion(this.getTimeToWait());
    }
    
    @Override
    public IMqttToken connectWithResult(final MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        final IMqttToken connect = this.aClient.connect(mqttConnectOptions, null, null);
        connect.waitForCompletion(this.getTimeToWait());
        return connect;
    }
    
    @Override
    public void disconnect() throws MqttException {
        this.aClient.disconnect().waitForCompletion();
    }
    
    @Override
    public void disconnect(final long n) throws MqttException {
        this.aClient.disconnect(n, null, null).waitForCompletion();
    }
    
    @Override
    public void disconnectForcibly() throws MqttException {
        this.aClient.disconnectForcibly();
    }
    
    @Override
    public void disconnectForcibly(final long n) throws MqttException {
        this.aClient.disconnectForcibly(n);
    }
    
    @Override
    public void disconnectForcibly(final long n, final long n2) throws MqttException {
        this.aClient.disconnectForcibly(n, n2);
    }
    
    public void disconnectForcibly(final long n, final long n2, final boolean b) throws MqttException {
        this.aClient.disconnectForcibly(n, n2, b);
    }
    
    @Override
    public String getClientId() {
        return this.aClient.getClientId();
    }
    
    public String getCurrentServerURI() {
        return this.aClient.getCurrentServerURI();
    }
    
    public Debug getDebug() {
        return this.aClient.getDebug();
    }
    
    @Override
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.aClient.getPendingDeliveryTokens();
    }
    
    @Override
    public String getServerURI() {
        return this.aClient.getServerURI();
    }
    
    public long getTimeToWait() {
        return this.timeToWait;
    }
    
    @Override
    public MqttTopic getTopic(final String s) {
        return this.aClient.getTopic(s);
    }
    
    @Override
    public boolean isConnected() {
        return this.aClient.isConnected();
    }
    
    @Override
    public void messageArrivedComplete(final int n, final int n2) throws MqttException {
        this.aClient.messageArrivedComplete(n, n2);
    }
    
    @Override
    public void publish(final String s, final MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        this.aClient.publish(s, mqttMessage, null, null).waitForCompletion(this.getTimeToWait());
    }
    
    @Override
    public void publish(final String s, final byte[] array, final int qos, final boolean retained) throws MqttException, MqttPersistenceException {
        final MqttMessage mqttMessage = new MqttMessage(array);
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        this.publish(s, mqttMessage);
    }
    
    @Override
    public void reconnect() throws MqttException {
        this.aClient.reconnect();
    }
    
    @Override
    public void setCallback(final MqttCallback callback) {
        this.aClient.setCallback(callback);
    }
    
    @Override
    public void setManualAcks(final boolean manualAcks) {
        this.aClient.setManualAcks(manualAcks);
    }
    
    public void setTimeToWait(final long timeToWait) throws IllegalArgumentException {
        if (timeToWait >= -1L) {
            this.timeToWait = timeToWait;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    public void subscribe(final String s) throws MqttException {
        this.subscribe(new String[] { s }, new int[] { 1 });
    }
    
    @Override
    public void subscribe(final String s, final int n) throws MqttException {
        this.subscribe(new String[] { s }, new int[] { n });
    }
    
    @Override
    public void subscribe(final String s, final int n, final IMqttMessageListener mqttMessageListener) throws MqttException {
        this.subscribe(new String[] { s }, new int[] { n }, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public void subscribe(final String s, final IMqttMessageListener mqttMessageListener) throws MqttException {
        this.subscribe(new String[] { s }, new int[] { 1 }, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public void subscribe(final String[] array) throws MqttException {
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = 1;
        }
        this.subscribe(array, array2);
    }
    
    @Override
    public void subscribe(final String[] array, final int[] array2) throws MqttException {
        this.subscribe(array, array2, null);
    }
    
    @Override
    public void subscribe(final String[] array, final int[] array2, final IMqttMessageListener[] array3) throws MqttException {
        final IMqttToken subscribe = this.aClient.subscribe(array, array2, null, null, array3);
        subscribe.waitForCompletion(this.getTimeToWait());
        final int[] grantedQos = subscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; ++i) {
            array2[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && array2[0] == 128) {
            throw new MqttException(128);
        }
    }
    
    @Override
    public void subscribe(final String[] array, final IMqttMessageListener[] array2) throws MqttException {
        final int length = array.length;
        final int[] array3 = new int[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = 1;
        }
        this.subscribe(array, array3, array2);
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String s) throws MqttException {
        return this.subscribeWithResponse(new String[] { s }, new int[] { 1 });
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String s, final int n) throws MqttException {
        return this.subscribeWithResponse(new String[] { s }, new int[] { n });
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String s, final int n, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribeWithResponse(new String[] { s }, new int[] { n }, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String s, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribeWithResponse(new String[] { s }, new int[] { 1 }, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String[] array) throws MqttException {
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = 1;
        }
        return this.subscribeWithResponse(array, array2);
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String[] array, final int[] array2) throws MqttException {
        return this.subscribeWithResponse(array, array2, null);
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String[] array, final int[] array2, final IMqttMessageListener[] array3) throws MqttException {
        final IMqttToken subscribe = this.aClient.subscribe(array, array2, null, null, array3);
        subscribe.waitForCompletion(this.getTimeToWait());
        return subscribe;
    }
    
    @Override
    public IMqttToken subscribeWithResponse(final String[] array, final IMqttMessageListener[] array2) throws MqttException {
        final int length = array.length;
        final int[] array3 = new int[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = 1;
        }
        return this.subscribeWithResponse(array, array3, array2);
    }
    
    @Override
    public void unsubscribe(final String s) throws MqttException {
        this.unsubscribe(new String[] { s });
    }
    
    @Override
    public void unsubscribe(final String[] array) throws MqttException {
        this.aClient.unsubscribe(array, null, null).waitForCompletion(this.getTimeToWait());
    }
}
