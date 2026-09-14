package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.util.Debug;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import javax.net.ssl.HostnameVerifier;
import javax.net.SocketFactory;
import java.util.Properties;

public class MqttConnectOptions
{
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    private boolean automaticReconnect;
    private boolean cleanSession;
    private int connectionTimeout;
    private Properties customWebSocketHeaders;
    private int executorServiceTimeout;
    private boolean httpsHostnameVerificationEnabled;
    private int keepAliveInterval;
    private int maxInflight;
    private int maxReconnectDelay;
    private int mqttVersion;
    private char[] password;
    private String[] serverURIs;
    private SocketFactory socketFactory;
    private Properties sslClientProps;
    private HostnameVerifier sslHostnameVerifier;
    private String userName;
    private String willDestination;
    private MqttMessage willMessage;
    
    public MqttConnectOptions() {
        this.keepAliveInterval = 60;
        this.maxInflight = 10;
        this.willDestination = null;
        this.willMessage = null;
        this.sslClientProps = null;
        this.httpsHostnameVerificationEnabled = true;
        this.sslHostnameVerifier = null;
        this.cleanSession = true;
        this.connectionTimeout = 30;
        this.serverURIs = null;
        this.mqttVersion = 0;
        this.automaticReconnect = false;
        this.maxReconnectDelay = 128000;
        this.customWebSocketHeaders = null;
        this.executorServiceTimeout = 1;
    }
    
    private void validateWill(final String s, final Object o) {
        if (s != null && o != null) {
            MqttTopic.validate(s, false);
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }
    
    public Properties getCustomWebSocketHeaders() {
        return this.customWebSocketHeaders;
    }
    
    public Properties getDebug() {
        final Properties properties = new Properties();
        properties.put((Object)"MqttVersion", (Object)this.getMqttVersion());
        properties.put((Object)"CleanSession", (Object)this.isCleanSession());
        properties.put((Object)"ConTimeout", (Object)this.getConnectionTimeout());
        properties.put((Object)"KeepAliveInterval", (Object)this.getKeepAliveInterval());
        String userName;
        if (this.getUserName() == null) {
            userName = "null";
        }
        else {
            userName = this.getUserName();
        }
        properties.put((Object)"UserName", (Object)userName);
        String willDestination;
        if (this.getWillDestination() == null) {
            willDestination = "null";
        }
        else {
            willDestination = this.getWillDestination();
        }
        properties.put((Object)"WillDestination", (Object)willDestination);
        if (this.getSocketFactory() == null) {
            properties.put((Object)"SocketFactory", (Object)"null");
        }
        else {
            properties.put((Object)"SocketFactory", (Object)this.getSocketFactory());
        }
        if (this.getSSLProperties() == null) {
            properties.put((Object)"SSLProperties", (Object)"null");
        }
        else {
            properties.put((Object)"SSLProperties", (Object)this.getSSLProperties());
        }
        return properties;
    }
    
    public int getExecutorServiceTimeout() {
        return this.executorServiceTimeout;
    }
    
    public int getKeepAliveInterval() {
        return this.keepAliveInterval;
    }
    
    public int getMaxInflight() {
        return this.maxInflight;
    }
    
    public int getMaxReconnectDelay() {
        return this.maxReconnectDelay;
    }
    
    public int getMqttVersion() {
        return this.mqttVersion;
    }
    
    public char[] getPassword() {
        return this.password;
    }
    
    public HostnameVerifier getSSLHostnameVerifier() {
        return this.sslHostnameVerifier;
    }
    
    public Properties getSSLProperties() {
        return this.sslClientProps;
    }
    
    public String[] getServerURIs() {
        return this.serverURIs;
    }
    
    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getWillDestination() {
        return this.willDestination;
    }
    
    public MqttMessage getWillMessage() {
        return this.willMessage;
    }
    
    public boolean isAutomaticReconnect() {
        return this.automaticReconnect;
    }
    
    public boolean isCleanSession() {
        return this.cleanSession;
    }
    
    public boolean isHttpsHostnameVerificationEnabled() {
        return this.httpsHostnameVerificationEnabled;
    }
    
    public void setAutomaticReconnect(final boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }
    
    public void setCleanSession(final boolean cleanSession) {
        this.cleanSession = cleanSession;
    }
    
    public void setConnectionTimeout(final int connectionTimeout) {
        if (connectionTimeout >= 0) {
            this.connectionTimeout = connectionTimeout;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void setCustomWebSocketHeaders(final Properties customWebSocketHeaders) {
        this.customWebSocketHeaders = customWebSocketHeaders;
    }
    
    public void setExecutorServiceTimeout(final int executorServiceTimeout) {
        this.executorServiceTimeout = executorServiceTimeout;
    }
    
    public void setHttpsHostnameVerificationEnabled(final boolean httpsHostnameVerificationEnabled) {
        this.httpsHostnameVerificationEnabled = httpsHostnameVerificationEnabled;
    }
    
    public void setKeepAliveInterval(final int keepAliveInterval) throws IllegalArgumentException {
        if (keepAliveInterval >= 0) {
            this.keepAliveInterval = keepAliveInterval;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void setMaxInflight(final int maxInflight) {
        if (maxInflight >= 0) {
            this.maxInflight = maxInflight;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void setMaxReconnectDelay(final int maxReconnectDelay) {
        this.maxReconnectDelay = maxReconnectDelay;
    }
    
    public void setMqttVersion(final int mqttVersion) throws IllegalArgumentException {
        if (mqttVersion != 0 && mqttVersion != 3 && mqttVersion != 4) {
            final StringBuilder sb = new StringBuilder("An incorrect version was used \"");
            sb.append(mqttVersion);
            sb.append("\". Acceptable version options are ");
            sb.append(0);
            sb.append(", ");
            sb.append(3);
            sb.append(" and ");
            sb.append(4);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }
        this.mqttVersion = mqttVersion;
    }
    
    public void setPassword(final char[] array) {
        this.password = array.clone();
    }
    
    public void setSSLHostnameVerifier(final HostnameVerifier sslHostnameVerifier) {
        this.sslHostnameVerifier = sslHostnameVerifier;
    }
    
    public void setSSLProperties(final Properties sslClientProps) {
        this.sslClientProps = sslClientProps;
    }
    
    public void setServerURIs(final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            NetworkModuleService.validateURI(array[i]);
        }
        this.serverURIs = array.clone();
    }
    
    public void setSocketFactory(final SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    protected void setWill(final String willDestination, final MqttMessage willMessage, final int qos, final boolean retained) {
        this.willDestination = willDestination;
        (this.willMessage = willMessage).setQos(qos);
        this.willMessage.setRetained(retained);
        this.willMessage.setMutable(false);
    }
    
    public void setWill(final String s, final byte[] array, final int n, final boolean b) {
        this.validateWill(s, array);
        this.setWill(s, new MqttMessage(array), n, b);
    }
    
    public void setWill(final MqttTopic mqttTopic, final byte[] array, final int n, final boolean b) {
        final String name = mqttTopic.getName();
        this.validateWill(name, array);
        this.setWill(name, new MqttMessage(array), n, b);
    }
    
    @Override
    public String toString() {
        return Debug.dumpProperties(this.getDebug(), "Connection options");
    }
}
