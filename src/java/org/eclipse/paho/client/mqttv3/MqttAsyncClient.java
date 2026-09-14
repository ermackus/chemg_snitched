package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.util.Debug;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import java.util.concurrent.ExecutorService;
import org.eclipse.paho.client.mqttv3.internal.SystemHighResolutionTimer;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.internal.HighResolutionTimer;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import java.util.Hashtable;
import java.util.Timer;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class MqttAsyncClient implements IMqttAsyncClient
{
    private static final String CLASS_NAME;
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000L;
    private static final char MAX_HIGH_SURROGATE = '\udbff';
    private static final char MIN_HIGH_SURROGATE = '\ud800';
    private static final long QUIESCE_TIMEOUT = 30000L;
    private static final Object clientLock;
    private static int reconnectDelay;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private ScheduledExecutorService executorService;
    private Logger log;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;
    
    static {
        CLASS_NAME = MqttAsyncClient.class.getName();
        MqttAsyncClient.reconnectDelay = 1000;
        clientLock = new Object();
    }
    
    public MqttAsyncClient(final String s, final String s2) throws MqttException {
        this(s, s2, new MqttDefaultFilePersistence());
    }
    
    public MqttAsyncClient(final String s, final String s2, final MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(s, s2, mqttClientPersistence, new TimerPingSender());
    }
    
    public MqttAsyncClient(final String s, final String s2, final MqttClientPersistence mqttClientPersistence, final MqttPingSender mqttPingSender) throws MqttException {
        this(s, s2, mqttClientPersistence, mqttPingSender, null);
    }
    
    public MqttAsyncClient(final String s, final String s2, final MqttClientPersistence mqttClientPersistence, final MqttPingSender mqttPingSender, final ScheduledExecutorService scheduledExecutorService) throws MqttException {
        this(s, s2, mqttClientPersistence, mqttPingSender, scheduledExecutorService, null);
    }
    
    public MqttAsyncClient(final String serverURI, final String s, final MqttClientPersistence persistence, final MqttPingSender mqttPingSender, final ScheduledExecutorService executorService, HighResolutionTimer highResolutionTimer) throws MqttException {
        final Logger logger = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", MqttAsyncClient.CLASS_NAME);
        this.log = logger;
        this.reconnecting = false;
        logger.setResourceName(s);
        if (s == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i = 0;
        int n = 0;
        while (i < s.length() - 1) {
            int n2 = i;
            if (Character_isHighSurrogate(s.charAt(i))) {
                n2 = i + 1;
            }
            ++n;
            i = n2 + 1;
        }
        if (n <= 65535) {
            NetworkModuleService.validateURI(serverURI);
            this.serverURI = serverURI;
            this.clientId = s;
            if ((this.persistence = persistence) == null) {
                this.persistence = new MemoryPersistence();
            }
            if (highResolutionTimer == null) {
                highResolutionTimer = new SystemHighResolutionTimer();
            }
            this.executorService = executorService;
            this.log.fine(MqttAsyncClient.CLASS_NAME, "MqttAsyncClient", "101", new Object[] { s, serverURI, persistence });
            this.persistence.open(s, serverURI);
            this.comms = new ClientComms(this, this.persistence, mqttPingSender, (ExecutorService)this.executorService, highResolutionTimer);
            this.persistence.close();
            this.topics = new Hashtable();
            return;
        }
        throw new IllegalArgumentException("ClientId longer than 65535 characters");
    }
    
    protected static boolean Character_isHighSurrogate(final char c) {
        return c >= '\ud800' && c <= '\udbff';
    }
    
    static /* synthetic */ void access$3(final MqttAsyncClient mqttAsyncClient, final boolean reconnecting) {
        mqttAsyncClient.reconnecting = reconnecting;
    }
    
    static /* synthetic */ void access$8(final int reconnectDelay) {
        MqttAsyncClient.reconnectDelay = reconnectDelay;
    }
    
    private void attemptReconnect() {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "500", new Object[] { this.clientId });
        try {
            this.connect(this.connOpts, this.userContext, new MqttReconnectActionListener("attemptReconnect"));
        }
        catch (final MqttException ex) {
            this.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "804", null, (Throwable)ex);
        }
        catch (final MqttSecurityException ex2) {
            this.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "804", null, (Throwable)ex2);
        }
    }
    
    private NetworkModule createNetworkModule(final String s, final MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "createNetworkModule", "115", new Object[] { s });
        return NetworkModuleService.createInstance(s, mqttConnectOptions, this.clientId);
    }
    
    public static String generateClientId() {
        final StringBuilder sb = new StringBuilder("paho");
        sb.append(System.nanoTime());
        return sb.toString();
    }
    
    private String getHostName(final String s) {
        int n;
        if ((n = s.indexOf(58)) == -1) {
            n = s.indexOf(47);
        }
        int length;
        if ((length = n) == -1) {
            length = s.length();
        }
        return s.substring(0, length);
    }
    
    private void startReconnectCycle() {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "startReconnectCycle", "503", new Object[] { this.clientId, MqttAsyncClient.reconnectDelay });
        final StringBuilder sb = new StringBuilder("MQTT Reconnect: ");
        sb.append(this.clientId);
        (this.reconnectTimer = new Timer(sb.toString())).schedule((TimerTask)new ReconnectTask((ReconnectTask)null), (long)MqttAsyncClient.reconnectDelay);
    }
    
    private void stopReconnectCycle() {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "stopReconnectCycle", "504", new Object[] { this.clientId });
        final Object clientLock = MqttAsyncClient.clientLock;
        synchronized (clientLock) {
            if (this.connOpts.isAutomaticReconnect()) {
                if (this.reconnectTimer != null) {
                    this.reconnectTimer.cancel();
                    this.reconnectTimer = null;
                }
                MqttAsyncClient.reconnectDelay = 1000;
            }
        }
    }
    
    private IMqttToken subscribeBase(final String[] topics, final int[] array, final Object userContext, final IMqttActionListener actionCallback) throws MqttException {
        if (this.log.isLoggable(5)) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < topics.length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append("topic=");
                sb.append(topics[i]);
                sb.append(" qos=");
                sb.append(array[i]);
            }
            this.log.fine(MqttAsyncClient.CLASS_NAME, "subscribe", "106", new Object[] { sb.toString(), userContext, actionCallback });
        }
        final MqttToken mqttToken = new MqttToken(this.getClientId());
        mqttToken.setActionCallback(actionCallback);
        mqttToken.setUserContext(userContext);
        mqttToken.internalTok.setTopics(topics);
        this.comms.sendNoWait(new MqttSubscribe(topics, array), mqttToken);
        this.log.fine(MqttAsyncClient.CLASS_NAME, "subscribe", "109");
        return mqttToken;
    }
    
    public IMqttToken checkPing(final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "ping", "117");
        final MqttToken checkForActivity = this.comms.checkForActivity(mqttActionListener);
        this.log.fine(MqttAsyncClient.CLASS_NAME, "ping", "118");
        return checkForActivity;
    }
    
    @Override
    public void close() throws MqttException {
        this.close(false);
    }
    
    public void close(final boolean b) throws MqttException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "close", "113");
        this.comms.close(b);
        this.log.fine(MqttAsyncClient.CLASS_NAME, "close", "114");
    }
    
    @Override
    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return this.connect(null, null);
    }
    
    @Override
    public IMqttToken connect(final Object o, final IMqttActionListener mqttActionListener) throws MqttException, MqttSecurityException {
        return this.connect(new MqttConnectOptions(), o, mqttActionListener);
    }
    
    @Override
    public IMqttToken connect(final MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return this.connect(mqttConnectOptions, null, null);
    }
    
    @Override
    public IMqttToken connect(final MqttConnectOptions mqttConnectOptions, Object o, final IMqttActionListener mqttActionListener) throws MqttException, MqttSecurityException {
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (!this.comms.isClosed()) {
            MqttConnectOptions connOpts;
            if ((connOpts = mqttConnectOptions) == null) {
                connOpts = new MqttConnectOptions();
            }
            this.connOpts = connOpts;
            this.userContext = o;
            final boolean automaticReconnect = connOpts.isAutomaticReconnect();
            final Logger log = this.log;
            final String class_NAME = MqttAsyncClient.CLASS_NAME;
            final boolean cleanSession = connOpts.isCleanSession();
            final int connectionTimeout = connOpts.getConnectionTimeout();
            final int keepAliveInterval = connOpts.getKeepAliveInterval();
            final String userName = connOpts.getUserName();
            final char[] password = connOpts.getPassword();
            String s = "[null]";
            String s2;
            if (password == null) {
                s2 = "[null]";
            }
            else {
                s2 = "[notnull]";
            }
            if (connOpts.getWillMessage() != null) {
                s = "[notnull]";
            }
            log.fine(class_NAME, "connect", "103", new Object[] { cleanSession, connectionTimeout, keepAliveInterval, userName, s2, s, o, mqttActionListener });
            this.comms.setNetworkModules(this.createNetworkModules(this.serverURI, connOpts));
            this.comms.setReconnectCallback(new MqttReconnectCallback(automaticReconnect));
            final MqttToken mqttToken = new MqttToken(this.getClientId());
            o = new ConnectActionListener(this, this.persistence, this.comms, connOpts, mqttToken, o, mqttActionListener, this.reconnecting);
            mqttToken.setActionCallback((IMqttActionListener)o);
            mqttToken.setUserContext(this);
            final MqttCallback mqttCallback = this.mqttCallback;
            if (mqttCallback instanceof MqttCallbackExtended) {
                ((ConnectActionListener)o).setMqttCallbackExtended((MqttCallbackExtended)mqttCallback);
            }
            this.comms.setNetworkModuleIndex(0);
            ((ConnectActionListener)o).connect();
            return mqttToken;
        }
        throw new MqttException(32111);
    }
    
    protected NetworkModule[] createNetworkModules(final String s, final MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        final Logger log = this.log;
        final String class_NAME = MqttAsyncClient.CLASS_NAME;
        int i = 0;
        log.fine(class_NAME, "createNetworkModules", "116", new Object[] { s });
        final String[] serverURIs = mqttConnectOptions.getServerURIs();
        String[] array;
        if (serverURIs == null) {
            array = new String[] { s };
        }
        else {
            array = serverURIs;
            if (serverURIs.length == 0) {
                array = new String[] { s };
            }
        }
        final NetworkModule[] array2 = new NetworkModule[array.length];
        while (i < array.length) {
            array2[i] = this.createNetworkModule(array[i], mqttConnectOptions);
            ++i;
        }
        this.log.fine(MqttAsyncClient.CLASS_NAME, "createNetworkModules", "108");
        return array2;
    }
    
    @Override
    public void deleteBufferedMessage(final int n) {
        this.comms.deleteBufferedMessage(n);
    }
    
    @Override
    public IMqttToken disconnect() throws MqttException {
        return this.disconnect(null, null);
    }
    
    @Override
    public IMqttToken disconnect(final long n) throws MqttException {
        return this.disconnect(n, null, null);
    }
    
    @Override
    public IMqttToken disconnect(final long n, final Object userContext, final IMqttActionListener actionCallback) throws MqttException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "disconnect", "104", new Object[] { n, userContext, actionCallback });
        final MqttToken mqttToken = new MqttToken(this.getClientId());
        mqttToken.setActionCallback(actionCallback);
        mqttToken.setUserContext(userContext);
        final MqttDisconnect mqttDisconnect = new MqttDisconnect();
        try {
            this.comms.disconnect(mqttDisconnect, n, mqttToken);
            this.log.fine(MqttAsyncClient.CLASS_NAME, "disconnect", "108");
            return mqttToken;
        }
        catch (final MqttException ex) {
            this.log.fine(MqttAsyncClient.CLASS_NAME, "disconnect", "105", null, (Throwable)ex);
            throw ex;
        }
    }
    
    @Override
    public IMqttToken disconnect(final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        return this.disconnect(30000L, o, mqttActionListener);
    }
    
    @Override
    public void disconnectForcibly() throws MqttException {
        this.disconnectForcibly(30000L, 10000L);
    }
    
    @Override
    public void disconnectForcibly(final long n) throws MqttException {
        this.disconnectForcibly(30000L, n);
    }
    
    @Override
    public void disconnectForcibly(final long n, final long n2) throws MqttException {
        this.comms.disconnectForcibly(n, n2);
    }
    
    public void disconnectForcibly(final long n, final long n2, final boolean b) throws MqttException {
        this.comms.disconnectForcibly(n, n2, b);
    }
    
    @Override
    public MqttMessage getBufferedMessage(final int n) {
        return this.comms.getBufferedMessage(n);
    }
    
    @Override
    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }
    
    @Override
    public String getClientId() {
        return this.clientId;
    }
    
    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }
    
    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }
    
    @Override
    public int getInFlightMessageCount() {
        return this.comms.getActualInFlight();
    }
    
    @Override
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }
    
    @Override
    public String getServerURI() {
        return this.serverURI;
    }
    
    protected MqttTopic getTopic(final String s) {
        MqttTopic.validate(s, false);
        MqttTopic mqttTopic;
        if ((mqttTopic = (MqttTopic)this.topics.get((Object)s)) == null) {
            mqttTopic = new MqttTopic(s, this.comms);
            this.topics.put((Object)s, (Object)mqttTopic);
        }
        return mqttTopic;
    }
    
    @Override
    public boolean isConnected() {
        return this.comms.isConnected();
    }
    
    @Override
    public void messageArrivedComplete(final int n, final int n2) throws MqttException {
        this.comms.messageArrivedComplete(n, n2);
    }
    
    @Override
    public IMqttDeliveryToken publish(final String s, final MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return this.publish(s, mqttMessage, null, null);
    }
    
    @Override
    public IMqttDeliveryToken publish(final String s, final MqttMessage message, final Object userContext, final IMqttActionListener actionCallback) throws MqttException, MqttPersistenceException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "publish", "111", new Object[] { s, userContext, actionCallback });
        MqttTopic.validate(s, false);
        final MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(this.getClientId());
        mqttDeliveryToken.setActionCallback(actionCallback);
        mqttDeliveryToken.setUserContext(userContext);
        mqttDeliveryToken.setMessage(message);
        mqttDeliveryToken.internalTok.setTopics(new String[] { s });
        this.comms.sendNoWait(new MqttPublish(s, message), mqttDeliveryToken);
        this.log.fine(MqttAsyncClient.CLASS_NAME, "publish", "112");
        return mqttDeliveryToken;
    }
    
    @Override
    public IMqttDeliveryToken publish(final String s, final byte[] array, final int n, final boolean b) throws MqttException, MqttPersistenceException {
        return this.publish(s, array, n, b, null, null);
    }
    
    @Override
    public IMqttDeliveryToken publish(final String s, final byte[] array, final int qos, final boolean retained, final Object o, final IMqttActionListener mqttActionListener) throws MqttException, MqttPersistenceException {
        final MqttMessage mqttMessage = new MqttMessage(array);
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        return this.publish(s, mqttMessage, o, mqttActionListener);
    }
    
    @Override
    public void reconnect() throws MqttException {
        this.log.fine(MqttAsyncClient.CLASS_NAME, "reconnect", "500", new Object[] { this.clientId });
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (!this.comms.isClosed()) {
            this.stopReconnectCycle();
            this.attemptReconnect();
            return;
        }
        throw new MqttException(32111);
    }
    
    @Override
    public boolean removeMessage(final IMqttDeliveryToken mqttDeliveryToken) throws MqttException {
        return this.comms.removeMessage(mqttDeliveryToken);
    }
    
    @Override
    public void setBufferOpts(final DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }
    
    @Override
    public void setCallback(final MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }
    
    @Override
    public void setManualAcks(final boolean manualAcks) {
        this.comms.setManualAcks(manualAcks);
    }
    
    @Override
    public IMqttToken subscribe(final String s, final int n) throws MqttException {
        return this.subscribe(new String[] { s }, new int[] { n }, null, null);
    }
    
    @Override
    public IMqttToken subscribe(final String s, final int n, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        return this.subscribe(new String[] { s }, new int[] { n }, o, mqttActionListener);
    }
    
    @Override
    public IMqttToken subscribe(final String s, final int n, final Object o, final IMqttActionListener mqttActionListener, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribe(new String[] { s }, new int[] { n }, o, mqttActionListener, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public IMqttToken subscribe(final String s, final int n, final IMqttMessageListener mqttMessageListener) throws MqttException {
        return this.subscribe(new String[] { s }, new int[] { n }, null, null, new IMqttMessageListener[] { mqttMessageListener });
    }
    
    @Override
    public IMqttToken subscribe(final String[] array, final int[] array2) throws MqttException {
        return this.subscribe(array, array2, null, null);
    }
    
    @Override
    public IMqttToken subscribe(final String[] array, final int[] array2, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        if (array.length == array2.length) {
            for (final String s : array) {
                MqttTopic.validate(s, true);
                this.comms.removeMessageListener(s);
            }
            return this.subscribeBase(array, array2, o, mqttActionListener);
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    public IMqttToken subscribe(final String[] array, final int[] array2, final Object o, final IMqttActionListener mqttActionListener, final IMqttMessageListener[] array3) throws MqttException {
        if ((array3 != null && array3.length != array2.length) || array2.length != array.length) {
            throw new IllegalArgumentException();
        }
        final int n = 0;
        int n2 = 0;
        while (true) {
            if (n2 >= array.length) {
                try {
                    return this.subscribeBase(array, array2, o, mqttActionListener);
                }
                catch (final Exception ex) {
                    for (int length = array.length, i = n; i < length; ++i) {
                        this.comms.removeMessageListener(array[i]);
                    }
                    throw ex;
                }
            }
            MqttTopic.validate(array[n2], true);
            if (array3 != null && array3[n2] != null) {
                this.comms.setMessageListener(array[n2], array3[n2]);
            }
            else {
                this.comms.removeMessageListener(array[n2]);
            }
            ++n2;
        }
    }
    
    @Override
    public IMqttToken subscribe(final String[] array, final int[] array2, final IMqttMessageListener[] array3) throws MqttException {
        return this.subscribe(array, array2, null, null, array3);
    }
    
    @Override
    public IMqttToken unsubscribe(final String s) throws MqttException {
        return this.unsubscribe(new String[] { s }, null, null);
    }
    
    @Override
    public IMqttToken unsubscribe(final String s, final Object o, final IMqttActionListener mqttActionListener) throws MqttException {
        return this.unsubscribe(new String[] { s }, o, mqttActionListener);
    }
    
    @Override
    public IMqttToken unsubscribe(final String[] array) throws MqttException {
        return this.unsubscribe(array, null, null);
    }
    
    @Override
    public IMqttToken unsubscribe(final String[] topics, final Object userContext, final IMqttActionListener actionCallback) throws MqttException {
        final boolean loggable = this.log.isLoggable(5);
        final int n = 0;
        if (loggable) {
            String string = "";
            for (int i = 0; i < topics.length; ++i) {
                String string2 = string;
                if (i > 0) {
                    final StringBuilder sb = new StringBuilder(String.valueOf((Object)string));
                    sb.append(", ");
                    string2 = sb.toString();
                }
                final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)string2));
                sb2.append(topics[i]);
                string = sb2.toString();
            }
            this.log.fine(MqttAsyncClient.CLASS_NAME, "unsubscribe", "107", new Object[] { string, userContext, actionCallback });
        }
        for (int length = topics.length, j = 0; j < length; ++j) {
            MqttTopic.validate(topics[j], true);
        }
        for (int length2 = topics.length, k = n; k < length2; ++k) {
            this.comms.removeMessageListener(topics[k]);
        }
        final MqttToken mqttToken = new MqttToken(this.getClientId());
        mqttToken.setActionCallback(actionCallback);
        mqttToken.setUserContext(userContext);
        mqttToken.internalTok.setTopics(topics);
        this.comms.sendNoWait(new MqttUnsubscribe(topics), mqttToken);
        this.log.fine(MqttAsyncClient.CLASS_NAME, "unsubscribe", "110");
        return mqttToken;
    }
    
    class MqttReconnectActionListener implements IMqttActionListener
    {
        final String methodName;
        final MqttAsyncClient this$0;
        
        MqttReconnectActionListener(final MqttAsyncClient this$0, final String methodName) {
            this.this$0 = this$0;
            this.methodName = methodName;
        }
        
        private void rescheduleReconnectCycle(final int n) {
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.methodName));
            sb.append(":rescheduleReconnectCycle");
            this.this$0.log.fine(MqttAsyncClient.CLASS_NAME, sb.toString(), "505", new Object[] { this.this$0.clientId, String.valueOf(MqttAsyncClient.reconnectDelay) });
            final Object access$10 = MqttAsyncClient.clientLock;
            synchronized (access$10) {
                if (this.this$0.connOpts.isAutomaticReconnect()) {
                    if (this.this$0.reconnectTimer != null) {
                        this.this$0.reconnectTimer.schedule((TimerTask)new ReconnectTask((ReconnectTask)null), (long)n);
                    }
                    else {
                        MqttAsyncClient.access$8(n);
                        this.this$0.startReconnectCycle();
                    }
                }
            }
        }
        
        @Override
        public void onFailure(final IMqttToken mqttToken, final Throwable t) {
            this.this$0.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "502", new Object[] { mqttToken.getClient().getClientId() });
            if (MqttAsyncClient.reconnectDelay < this.this$0.connOpts.getMaxReconnectDelay()) {
                MqttAsyncClient.access$8(MqttAsyncClient.reconnectDelay * 2);
            }
            this.rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
        }
        
        @Override
        public void onSuccess(final IMqttToken mqttToken) {
            this.this$0.log.fine(MqttAsyncClient.CLASS_NAME, this.methodName, "501", new Object[] { mqttToken.getClient().getClientId() });
            this.this$0.comms.setRestingState(false);
            this.this$0.stopReconnectCycle();
        }
    }
    
    class MqttReconnectCallback implements MqttCallbackExtended
    {
        final boolean automaticReconnect;
        final MqttAsyncClient this$0;
        
        MqttReconnectCallback(final MqttAsyncClient this$0, final boolean automaticReconnect) {
            this.this$0 = this$0;
            this.automaticReconnect = automaticReconnect;
        }
        
        @Override
        public void connectComplete(final boolean b, final String s) {
        }
        
        @Override
        public void connectionLost(final Throwable t) {
            if (this.automaticReconnect) {
                this.this$0.comms.setRestingState(true);
                MqttAsyncClient.access$3(this.this$0, true);
                this.this$0.startReconnectCycle();
            }
        }
        
        @Override
        public void deliveryComplete(final IMqttDeliveryToken mqttDeliveryToken) {
        }
        
        @Override
        public void messageArrived(final String s, final MqttMessage mqttMessage) throws Exception {
        }
    }
    
    private class ReconnectTask extends TimerTask
    {
        private static final String methodName = "ReconnectTask.run";
        final MqttAsyncClient this$0;
        
        private ReconnectTask(final MqttAsyncClient this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            this.this$0.log.fine(MqttAsyncClient.CLASS_NAME, "ReconnectTask.run", "506");
            this.this$0.attemptReconnect();
        }
    }
}
