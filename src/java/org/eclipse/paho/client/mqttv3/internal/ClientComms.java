package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import java.util.concurrent.TimeUnit;
import java.util.Enumeration;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.concurrent.ExecutorService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;

public class ClientComms
{
    public static String BUILD_LEVEL = "L${build.level}";
    private static final byte CLOSED = 4;
    private static final byte CONNECTED = 0;
    private static final byte CONNECTING = 1;
    private static final byte DISCONNECTED = 3;
    private static final byte DISCONNECTING = 2;
    public static String VERSION = "${project.version}";
    private final String CLASS_NAME;
    private CommsCallback callback;
    private IMqttAsyncClient client;
    private ClientState clientState;
    private boolean closePending;
    private final Object conLock;
    private MqttConnectOptions conOptions;
    private byte conState;
    private DisconnectedMessageBuffer disconnectedMessageBuffer;
    private ExecutorService executorService;
    private final Logger log;
    private int networkModuleIndex;
    private NetworkModule[] networkModules;
    private MqttClientPersistence persistence;
    private MqttPingSender pingSender;
    private CommsReceiver receiver;
    private boolean resting;
    private CommsSender sender;
    private boolean stoppingComms;
    private CommsTokenStore tokenStore;
    
    public ClientComms(final IMqttAsyncClient client, final MqttClientPersistence persistence, final MqttPingSender pingSender, final ExecutorService executorService, final HighResolutionTimer highResolutionTimer) throws MqttException {
        final String name = ClientComms.class.getName();
        this.CLASS_NAME = name;
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", name);
        this.stoppingComms = false;
        this.conState = 3;
        this.conLock = new Object();
        this.closePending = false;
        this.resting = false;
        this.conState = 3;
        this.client = client;
        this.persistence = persistence;
        (this.pingSender = pingSender).init(this);
        this.executorService = executorService;
        this.tokenStore = new CommsTokenStore(this.getClient().getClientId());
        this.callback = new CommsCallback(this);
        final ClientState clientState = new ClientState(persistence, this.tokenStore, this.callback, this, pingSender, highResolutionTimer);
        this.clientState = clientState;
        this.callback.setClientState(clientState);
        this.log.setResourceName(this.getClient().getClientId());
    }
    
    static /* synthetic */ void access$7(final ClientComms clientComms, final CommsReceiver receiver) {
        clientComms.receiver = receiver;
    }
    
    static /* synthetic */ void access$9(final ClientComms clientComms, final CommsSender sender) {
        clientComms.sender = sender;
    }
    
    private MqttToken handleOldTokens(MqttToken mqttToken, final MqttException ex) {
        this.log.fine(this.CLASS_NAME, "handleOldTokens", "222");
        final MqttToken mqttToken2 = null;
        final MqttToken mqttToken3 = null;
        Label_0075: {
            if (mqttToken == null) {
                break Label_0075;
            }
            MqttToken mqttToken4 = mqttToken2;
            try {
                if (!mqttToken.isComplete()) {
                    mqttToken4 = mqttToken2;
                    if (this.tokenStore.getToken(mqttToken.internalTok.getKey()) == null) {
                        mqttToken4 = mqttToken2;
                        this.tokenStore.saveToken(mqttToken, mqttToken.internalTok.getKey());
                    }
                }
                mqttToken4 = mqttToken2;
                final Enumeration elements = this.clientState.resolveOldTokens(ex).elements();
                mqttToken = mqttToken3;
                while (true) {
                    mqttToken4 = mqttToken;
                    if (!elements.hasMoreElements()) {
                        break;
                    }
                    mqttToken4 = mqttToken;
                    final MqttToken mqttToken5 = (MqttToken)elements.nextElement();
                    mqttToken4 = mqttToken;
                    if (!mqttToken5.internalTok.getKey().equals((Object)"Disc")) {
                        mqttToken4 = mqttToken;
                        if (!mqttToken5.internalTok.getKey().equals((Object)"Con")) {
                            mqttToken4 = mqttToken;
                            this.callback.asyncOperationComplete(mqttToken5);
                            continue;
                        }
                    }
                    mqttToken = mqttToken5;
                }
                return mqttToken;
            }
            catch (final Exception ex2) {
                mqttToken = mqttToken4;
                return mqttToken;
            }
        }
    }
    
    private void handleRunException(final Exception ex) {
        this.log.fine(this.CLASS_NAME, "handleRunException", "804", null, (Throwable)ex);
        MqttException ex2;
        if (!(ex instanceof MqttException)) {
            ex2 = new MqttException(32109, (Throwable)ex);
        }
        else {
            ex2 = (MqttException)ex;
        }
        this.shutdownConnection(null, ex2);
    }
    
    private void shutdownExecutorService() {
        this.executorService.shutdown();
        try {
            if (this.executorService != null && this.conOptions != null && !this.executorService.awaitTermination((long)this.conOptions.getExecutorServiceTimeout(), TimeUnit.SECONDS)) {
                this.executorService.shutdownNow();
                if (!this.executorService.awaitTermination((long)this.conOptions.getExecutorServiceTimeout(), TimeUnit.SECONDS)) {
                    this.log.fine(this.CLASS_NAME, "shutdownExecutorService", "executorService did not terminate");
                }
            }
        }
        catch (final InterruptedException ex) {
            this.executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    public MqttToken checkForActivity() {
        return this.checkForActivity(null);
    }
    
    public MqttToken checkForActivity(final IMqttActionListener mqttActionListener) {
        try {
            return this.clientState.checkForActivity(mqttActionListener);
        }
        catch (final Exception ex) {
            this.handleRunException(ex);
        }
        catch (final MqttException ex2) {
            this.handleRunException(ex2);
        }
        return null;
    }
    
    public void close(final boolean b) throws MqttException {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            if (!this.isClosed()) {
                if (!this.isDisconnected() || b) {
                    this.log.fine(this.CLASS_NAME, "close", "224");
                    if (this.isConnecting()) {
                        throw new MqttException(32110);
                    }
                    if (this.isConnected()) {
                        throw ExceptionHelper.createMqttException(32100);
                    }
                    if (this.isDisconnecting()) {
                        this.closePending = true;
                        return;
                    }
                }
                this.conState = 4;
                this.clientState.close();
                this.clientState = null;
                this.callback = null;
                this.persistence = null;
                this.sender = null;
                this.pingSender = null;
                this.receiver = null;
                this.networkModules = null;
                this.conOptions = null;
                this.tokenStore = null;
            }
        }
    }
    
    public void connect(final MqttConnectOptions conOptions, final MqttToken mqttToken) throws MqttException {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            if (this.isDisconnected() && !this.closePending) {
                this.log.fine(this.CLASS_NAME, "connect", "214");
                this.conState = 1;
                this.conOptions = conOptions;
                final MqttConnect mqttConnect = new MqttConnect(this.client.getClientId(), this.conOptions.getMqttVersion(), this.conOptions.isCleanSession(), this.conOptions.getKeepAliveInterval(), this.conOptions.getUserName(), this.conOptions.getPassword(), this.conOptions.getWillMessage(), this.conOptions.getWillDestination());
                this.clientState.setKeepAliveSecs(this.conOptions.getKeepAliveInterval());
                this.clientState.setCleanSession(this.conOptions.isCleanSession());
                this.clientState.setMaxInflight(this.conOptions.getMaxInflight());
                this.tokenStore.open();
                new ConnectBG(this, mqttToken, mqttConnect, this.executorService).start();
                return;
            }
            this.log.fine(this.CLASS_NAME, "connect", "207", new Object[] { this.conState });
            if (this.isClosed() || this.closePending) {
                throw new MqttException(32111);
            }
            if (this.isConnecting()) {
                throw new MqttException(32110);
            }
            if (this.isDisconnecting()) {
                throw new MqttException(32102);
            }
            throw ExceptionHelper.createMqttException(32100);
        }
    }
    
    public void connectComplete(final MqttConnack mqttConnack, final MqttException ex) throws MqttException {
        final int returnCode = mqttConnack.getReturnCode();
        final Object conLock;
        monitorenter(conLock = this.conLock);
        Label_0047: {
            if (returnCode != 0) {
                break Label_0047;
            }
            try {
                this.log.fine(this.CLASS_NAME, "connectComplete", "215");
                this.conState = 0;
                return;
                monitorexit(conLock);
                this.log.fine(this.CLASS_NAME, "connectComplete", "204", new Object[] { returnCode });
                throw ex;
            }
            finally {
                monitorexit(conLock);
            }
        }
    }
    
    public void deleteBufferedMessage(final int n) {
        this.disconnectedMessageBuffer.deleteMessage(n);
    }
    
    protected void deliveryComplete(final int n) throws MqttPersistenceException {
        this.clientState.deliveryComplete(n);
    }
    
    protected void deliveryComplete(final MqttPublish mqttPublish) throws MqttPersistenceException {
        this.clientState.deliveryComplete(mqttPublish);
    }
    
    public void disconnect(final MqttDisconnect mqttDisconnect, final long n, final MqttToken mqttToken) throws MqttException {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            if (this.isClosed()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "223");
                throw ExceptionHelper.createMqttException(32111);
            }
            if (this.isDisconnected()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "211");
                throw ExceptionHelper.createMqttException(32101);
            }
            if (this.isDisconnecting()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "219");
                throw ExceptionHelper.createMqttException(32102);
            }
            if (Thread.currentThread() != this.callback.getThread()) {
                this.log.fine(this.CLASS_NAME, "disconnect", "218");
                this.conState = 2;
                new DisconnectBG(mqttDisconnect, n, mqttToken, this.executorService).start();
                return;
            }
            this.log.fine(this.CLASS_NAME, "disconnect", "210");
            throw ExceptionHelper.createMqttException(32107);
        }
    }
    
    public void disconnectForcibly(final long n, final long n2) throws MqttException {
        this.disconnectForcibly(n, n2, true);
    }
    
    public void disconnectForcibly(final long n, final long n2, final boolean b) throws MqttException {
        this.conState = 2;
        final ClientState clientState = this.clientState;
        if (clientState != null) {
            clientState.quiesce(n);
        }
        final MqttToken mqttToken = new MqttToken(this.client.getClientId());
        if (!b) {
            goto Label_0094;
        }
        try {
            this.internalSend(new MqttDisconnect(), mqttToken);
            mqttToken.waitForCompletion(n2);
            goto Label_0094;
        }
        catch (final Exception ex) {
            goto Label_0094;
        }
        finally {
            mqttToken.internalTok.markComplete(null, null);
            this.shutdownConnection(mqttToken, null);
        }
    }
    
    public int getActualInFlight() {
        return this.clientState.getActualInFlight();
    }
    
    public MqttMessage getBufferedMessage(final int n) {
        return ((MqttPublish)this.disconnectedMessageBuffer.getMessage(n).getMessage()).getMessage();
    }
    
    public int getBufferedMessageCount() {
        return this.disconnectedMessageBuffer.getMessageCount();
    }
    
    public IMqttAsyncClient getClient() {
        return this.client;
    }
    
    public ClientState getClientState() {
        return this.clientState;
    }
    
    public MqttConnectOptions getConOptions() {
        return this.conOptions;
    }
    
    public Properties getDebug() {
        final Properties properties = new Properties();
        properties.put((Object)"conState", (Object)(int)this.conState);
        properties.put((Object)"serverURI", (Object)this.getClient().getServerURI());
        properties.put((Object)"callback", (Object)this.callback);
        properties.put((Object)"stoppingComms", (Object)this.stoppingComms);
        return properties;
    }
    
    public long getKeepAlive() {
        return this.clientState.getKeepAlive();
    }
    
    public int getNetworkModuleIndex() {
        return this.networkModuleIndex;
    }
    
    public NetworkModule[] getNetworkModules() {
        return this.networkModules;
    }
    
    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.tokenStore.getOutstandingDelTokens();
    }
    
    CommsReceiver getReceiver() {
        return this.receiver;
    }
    
    protected MqttTopic getTopic(final String s) {
        return new MqttTopic(s, this);
    }
    
    void internalSend(final MqttWireMessage mqttWireMessage, final MqttToken mqttToken) throws MqttException {
        this.log.fine(this.CLASS_NAME, "internalSend", "200", new Object[] { mqttWireMessage.getKey(), mqttWireMessage, mqttToken });
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.setClient(this.getClient());
            try {
                this.clientState.send(mqttWireMessage, mqttToken);
                return;
            }
            catch (final MqttException ex) {
                mqttToken.internalTok.setClient(null);
                if (mqttWireMessage instanceof MqttPublish) {
                    this.clientState.undo((MqttPublish)mqttWireMessage);
                }
                throw ex;
            }
        }
        this.log.fine(this.CLASS_NAME, "internalSend", "213", new Object[] { mqttWireMessage.getKey(), mqttWireMessage, mqttToken });
        throw new MqttException(32201);
    }
    
    public boolean isClosed() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            return this.conState == 4;
        }
    }
    
    public boolean isConnected() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            return this.conState == 0;
        }
    }
    
    public boolean isConnecting() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            final byte conState = this.conState;
            boolean b = true;
            if (conState != 1) {
                b = false;
            }
            return b;
        }
    }
    
    public boolean isDisconnected() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            return this.conState == 3;
        }
    }
    
    public boolean isDisconnecting() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            return this.conState == 2;
        }
    }
    
    public boolean isResting() {
        final Object conLock = this.conLock;
        synchronized (conLock) {
            return this.resting;
        }
    }
    
    public void messageArrivedComplete(final int n, final int n2) throws MqttException {
        this.callback.messageArrivedComplete(n, n2);
    }
    
    public void notifyConnect() {
        if (this.disconnectedMessageBuffer != null) {
            this.log.fine(this.CLASS_NAME, "notifyConnect", "509", null);
            this.disconnectedMessageBuffer.setPublishCallback(new ReconnectDisconnectedBufferCallback("notifyConnect"));
            this.disconnectedMessageBuffer.setMessageDiscardedCallBack(new MessageDiscardedCallback());
            final ExecutorService executorService = this.executorService;
            if (executorService == null) {
                new Thread((Runnable)this.disconnectedMessageBuffer).start();
            }
            else {
                executorService.execute((Runnable)this.disconnectedMessageBuffer);
            }
        }
    }
    
    public boolean removeMessage(final IMqttDeliveryToken mqttDeliveryToken) throws MqttException {
        return this.clientState.removeMessage(mqttDeliveryToken);
    }
    
    public void removeMessageListener(final String s) {
        this.callback.removeMessageListener(s);
    }
    
    public void sendNoWait(final MqttWireMessage mqttWireMessage, final MqttToken mqttToken) throws MqttException {
        if (!this.isConnected() && (this.isConnected() || !(mqttWireMessage instanceof MqttConnect)) && (!this.isDisconnecting() || !(mqttWireMessage instanceof MqttDisconnect))) {
            if (this.disconnectedMessageBuffer == null) {
                this.log.fine(this.CLASS_NAME, "sendNoWait", "208");
                throw ExceptionHelper.createMqttException(32104);
            }
            this.log.fine(this.CLASS_NAME, "sendNoWait", "508", new Object[] { mqttWireMessage.getKey() });
            if (this.disconnectedMessageBuffer.isPersistBuffer()) {
                this.clientState.persistBufferedMessage(mqttWireMessage);
            }
            this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
        }
        else {
            final DisconnectedMessageBuffer disconnectedMessageBuffer = this.disconnectedMessageBuffer;
            if (disconnectedMessageBuffer != null && disconnectedMessageBuffer.getMessageCount() != 0) {
                this.log.fine(this.CLASS_NAME, "sendNoWait", "507", new Object[] { mqttWireMessage.getKey() });
                if (this.disconnectedMessageBuffer.isPersistBuffer()) {
                    this.clientState.persistBufferedMessage(mqttWireMessage);
                }
                this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
            }
            else {
                this.internalSend(mqttWireMessage, mqttToken);
            }
        }
    }
    
    public void setCallback(final MqttCallback callback) {
        this.callback.setCallback(callback);
    }
    
    public void setDisconnectedMessageBuffer(final DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.disconnectedMessageBuffer = disconnectedMessageBuffer;
    }
    
    public void setManualAcks(final boolean manualAcks) {
        this.callback.setManualAcks(manualAcks);
    }
    
    public void setMessageListener(final String s, final IMqttMessageListener mqttMessageListener) {
        this.callback.setMessageListener(s, mqttMessageListener);
    }
    
    public void setNetworkModuleIndex(final int networkModuleIndex) {
        this.networkModuleIndex = networkModuleIndex;
    }
    
    public void setNetworkModules(final NetworkModule[] array) {
        this.networkModules = array.clone();
    }
    
    public void setReconnectCallback(final MqttCallbackExtended reconnectCallback) {
        this.callback.setReconnectCallback(reconnectCallback);
    }
    
    public void setRestingState(final boolean resting) {
        this.resting = resting;
    }
    
    public void shutdownConnection(final MqttToken p0, final MqttException p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.conLock:Ljava/lang/Object;
        //     4: astore          5
        //     6: aload           5
        //     8: dup            
        //     9: astore          6
        //    11: monitorenter   
        //    12: aload_0        
        //    13: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.stoppingComms:Z
        //    16: ifne            406
        //    19: aload_0        
        //    20: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.closePending:Z
        //    23: ifne            406
        //    26: aload_0        
        //    27: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientComms.isClosed:()Z
        //    30: ifeq            36
        //    33: goto            406
        //    36: aload_0        
        //    37: iconst_1       
        //    38: putfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.stoppingComms:Z
        //    41: aload_0        
        //    42: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    45: aload_0        
        //    46: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.CLASS_NAME:Ljava/lang/String;
        //    49: ldc_w           "shutdownConnection"
        //    52: ldc_w           "216"
        //    55: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    60: aload_0        
        //    61: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientComms.isConnected:()Z
        //    64: ifne            79
        //    67: aload_0        
        //    68: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientComms.isDisconnecting:()Z
        //    71: ifne            79
        //    74: iconst_0       
        //    75: istore_3       
        //    76: goto            81
        //    79: iconst_1       
        //    80: istore_3       
        //    81: aload_0        
        //    82: iconst_2       
        //    83: putfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.conState:B
        //    86: aload           6
        //    88: monitorexit    
        //    89: aload_1        
        //    90: ifnull          108
        //    93: aload_1        
        //    94: invokevirtual   org/eclipse/paho/client/mqttv3/MqttToken.isComplete:()Z
        //    97: ifne            108
        //   100: aload_1        
        //   101: getfield        org/eclipse/paho/client/mqttv3/MqttToken.internalTok:Lorg/eclipse/paho/client/mqttv3/internal/Token;
        //   104: aload_2        
        //   105: invokevirtual   org/eclipse/paho/client/mqttv3/internal/Token.setException:(Lorg/eclipse/paho/client/mqttv3/MqttException;)V
        //   108: aload_0        
        //   109: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //   112: astore          5
        //   114: aload           5
        //   116: ifnull          124
        //   119: aload           5
        //   121: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.stop:()V
        //   124: aload_0        
        //   125: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.receiver:Lorg/eclipse/paho/client/mqttv3/internal/CommsReceiver;
        //   128: astore          5
        //   130: aload           5
        //   132: ifnull          140
        //   135: aload           5
        //   137: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsReceiver.stop:()V
        //   140: aload_0        
        //   141: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.networkModules:[Lorg/eclipse/paho/client/mqttv3/internal/NetworkModule;
        //   144: ifnull          170
        //   147: aload_0        
        //   148: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.networkModules:[Lorg/eclipse/paho/client/mqttv3/internal/NetworkModule;
        //   151: aload_0        
        //   152: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.networkModuleIndex:I
        //   155: aaload         
        //   156: astore          5
        //   158: aload           5
        //   160: ifnull          170
        //   163: aload           5
        //   165: invokeinterface org/eclipse/paho/client/mqttv3/internal/NetworkModule.stop:()V
        //   170: aload_0        
        //   171: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.tokenStore:Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
        //   174: new             Lorg/eclipse/paho/client/mqttv3/MqttException;
        //   177: dup            
        //   178: sipush          32102
        //   181: invokespecial   org/eclipse/paho/client/mqttv3/MqttException.<init>:(I)V
        //   184: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsTokenStore.quiesce:(Lorg/eclipse/paho/client/mqttv3/MqttException;)V
        //   187: aload_0        
        //   188: aload_1        
        //   189: aload_2        
        //   190: invokespecial   org/eclipse/paho/client/mqttv3/internal/ClientComms.handleOldTokens:(Lorg/eclipse/paho/client/mqttv3/MqttToken;Lorg/eclipse/paho/client/mqttv3/MqttException;)Lorg/eclipse/paho/client/mqttv3/MqttToken;
        //   193: astore_1       
        //   194: aload_0        
        //   195: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.clientState:Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
        //   198: aload_2        
        //   199: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.disconnected:(Lorg/eclipse/paho/client/mqttv3/MqttException;)V
        //   202: aload_0        
        //   203: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.clientState:Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
        //   206: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.getCleanSession:()Z
        //   209: ifeq            224
        //   212: aload_0        
        //   213: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //   216: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.removeMessageListeners:()V
        //   219: goto            224
        //   222: astore          5
        //   224: aload_0        
        //   225: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.sender:Lorg/eclipse/paho/client/mqttv3/internal/CommsSender;
        //   228: astore          5
        //   230: aload           5
        //   232: ifnull          240
        //   235: aload           5
        //   237: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsSender.stop:()V
        //   240: aload_0        
        //   241: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.pingSender:Lorg/eclipse/paho/client/mqttv3/MqttPingSender;
        //   244: astore          5
        //   246: aload           5
        //   248: ifnull          258
        //   251: aload           5
        //   253: invokeinterface org/eclipse/paho/client/mqttv3/MqttPingSender.stop:()V
        //   258: aload_0        
        //   259: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.disconnectedMessageBuffer:Lorg/eclipse/paho/client/mqttv3/internal/DisconnectedMessageBuffer;
        //   262: ifnonnull       281
        //   265: aload_0        
        //   266: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.persistence:Lorg/eclipse/paho/client/mqttv3/MqttClientPersistence;
        //   269: ifnull          281
        //   272: aload_0        
        //   273: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.persistence:Lorg/eclipse/paho/client/mqttv3/MqttClientPersistence;
        //   276: invokeinterface org/eclipse/paho/client/mqttv3/MqttClientPersistence.close:()V
        //   281: aload_0        
        //   282: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.conLock:Ljava/lang/Object;
        //   285: astore          5
        //   287: aload           5
        //   289: dup            
        //   290: astore          6
        //   292: monitorenter   
        //   293: aload_0        
        //   294: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   297: aload_0        
        //   298: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.CLASS_NAME:Ljava/lang/String;
        //   301: ldc_w           "shutdownConnection"
        //   304: ldc_w           "217"
        //   307: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   312: aload_0        
        //   313: iconst_3       
        //   314: putfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.conState:B
        //   317: aload_0        
        //   318: iconst_0       
        //   319: putfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.stoppingComms:Z
        //   322: aload           6
        //   324: monitorexit    
        //   325: aload_1        
        //   326: ifnull          346
        //   329: aload_0        
        //   330: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //   333: astore          5
        //   335: aload           5
        //   337: ifnull          346
        //   340: aload           5
        //   342: aload_1        
        //   343: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.asyncOperationComplete:(Lorg/eclipse/paho/client/mqttv3/MqttToken;)V
        //   346: iload_3        
        //   347: ifeq            364
        //   350: aload_0        
        //   351: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //   354: astore_1       
        //   355: aload_1        
        //   356: ifnull          364
        //   359: aload_1        
        //   360: aload_2        
        //   361: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.connectionLost:(Lorg/eclipse/paho/client/mqttv3/MqttException;)V
        //   364: aload_0        
        //   365: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.conLock:Ljava/lang/Object;
        //   368: astore_1       
        //   369: aload_1        
        //   370: dup            
        //   371: astore          7
        //   373: monitorenter   
        //   374: aload_0        
        //   375: getfield        org/eclipse/paho/client/mqttv3/internal/ClientComms.closePending:Z
        //   378: istore          4
        //   380: iload           4
        //   382: ifeq            390
        //   385: aload_0        
        //   386: iconst_1       
        //   387: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientComms.close:(Z)V
        //   390: aload           7
        //   392: monitorexit    
        //   393: return         
        //   394: astore_2       
        //   395: aload           7
        //   397: monitorexit    
        //   398: aload_2        
        //   399: athrow         
        //   400: astore_1       
        //   401: aload           6
        //   403: monitorexit    
        //   404: aload_1        
        //   405: athrow         
        //   406: aload           6
        //   408: monitorexit    
        //   409: return         
        //   410: astore_1       
        //   411: aload           6
        //   413: monitorexit    
        //   414: aload_1        
        //   415: athrow         
        //   416: astore          5
        //   418: goto            170
        //   421: astore          5
        //   423: goto            281
        //   426: astore_2       
        //   427: goto            390
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     33     410    416    Any
        //  36     74     410    416    Any
        //  81     89     410    416    Any
        //  140    158    416    421    Ljava/lang/Exception;
        //  163    170    416    421    Ljava/lang/Exception;
        //  194    219    222    224    Ljava/lang/Exception;
        //  258    281    421    426    Ljava/lang/Exception;
        //  293    325    400    406    Any
        //  374    380    394    400    Any
        //  385    390    426    430    Ljava/lang/Exception;
        //  385    390    394    400    Any
        //  390    393    394    400    Any
        //  395    398    394    400    Any
        //  401    404    400    406    Any
        //  406    409    410    416    Any
        //  411    414    410    416    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 211, Size: 211
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private class ConnectBG implements Runnable
    {
        ClientComms clientComms;
        MqttConnect conPacket;
        MqttToken conToken;
        final ClientComms this$0;
        private String threadName;
        
        ConnectBG(final ClientComms this$0, final ClientComms clientComms, final MqttToken conToken, final MqttConnect conPacket, final ExecutorService executorService) {
            this.this$0 = this$0;
            this.clientComms = null;
            this.clientComms = clientComms;
            this.conToken = conToken;
            this.conPacket = conPacket;
            final StringBuilder sb = new StringBuilder("MQTT Con: ");
            sb.append(this$0.getClient().getClientId());
            this.threadName = sb.toString();
        }
        
        public void run() {
            Thread.currentThread().setName(this.threadName);
            this.this$0.log.fine(this.this$0.CLASS_NAME, "connectBG:run", "220");
            MqttException mqttException = null;
            try {
                final MqttDeliveryToken[] outstandingDelTokens = this.this$0.tokenStore.getOutstandingDelTokens();
                for (int length = outstandingDelTokens.length, i = 0; i < length; ++i) {
                    outstandingDelTokens[i].internalTok.setException(null);
                }
                this.this$0.tokenStore.saveToken(this.conToken, this.conPacket);
                final NetworkModule networkModule = this.this$0.networkModules[this.this$0.networkModuleIndex];
                networkModule.start();
                ClientComms.access$7(this.this$0, new CommsReceiver(this.clientComms, this.this$0.clientState, this.this$0.tokenStore, networkModule.getInputStream()));
                final CommsReceiver access$8 = this.this$0.receiver;
                final StringBuilder sb = new StringBuilder("MQTT Rec: ");
                sb.append(this.this$0.getClient().getClientId());
                access$8.start(sb.toString(), this.this$0.executorService);
                ClientComms.access$9(this.this$0, new CommsSender(this.clientComms, this.this$0.clientState, this.this$0.tokenStore, networkModule.getOutputStream()));
                final CommsSender access$9 = this.this$0.sender;
                final StringBuilder sb2 = new StringBuilder("MQTT Snd: ");
                sb2.append(this.this$0.getClient().getClientId());
                access$9.start(sb2.toString(), this.this$0.executorService);
                final CommsCallback access$10 = this.this$0.callback;
                final StringBuilder sb3 = new StringBuilder("MQTT Call: ");
                sb3.append(this.this$0.getClient().getClientId());
                access$10.start(sb3.toString(), this.this$0.executorService);
                this.this$0.internalSend(this.conPacket, this.conToken);
            }
            catch (final Exception ex) {
                this.this$0.log.fine(this.this$0.CLASS_NAME, "connectBG:run", "209", null, (Throwable)ex);
                mqttException = ExceptionHelper.createMqttException((Throwable)ex);
            }
            catch (final MqttException mqttException) {
                this.this$0.log.fine(this.this$0.CLASS_NAME, "connectBG:run", "212", null, (Throwable)mqttException);
            }
            if (mqttException != null) {
                this.this$0.shutdownConnection(this.conToken, mqttException);
            }
        }
        
        void start() {
            if (this.this$0.executorService == null) {
                new Thread((Runnable)this).start();
            }
            else {
                this.this$0.executorService.execute((Runnable)this);
            }
        }
    }
    
    private class DisconnectBG implements Runnable
    {
        MqttDisconnect disconnect;
        long quiesceTimeout;
        final ClientComms this$0;
        private String threadName;
        MqttToken token;
        
        DisconnectBG(final ClientComms this$0, final MqttDisconnect disconnect, final long quiesceTimeout, final MqttToken token, final ExecutorService executorService) {
            this.this$0 = this$0;
            this.disconnect = disconnect;
            this.quiesceTimeout = quiesceTimeout;
            this.token = token;
        }
        
        public void run() {
            Thread.currentThread().setName(this.threadName);
            this.this$0.log.fine(this.this$0.CLASS_NAME, "disconnectBG:run", "221");
            this.this$0.clientState.quiesce(this.quiesceTimeout);
            Label_0239: {
                try {
                    this.this$0.internalSend(this.disconnect, this.token);
                    if (this.this$0.sender != null && this.this$0.sender.isRunning()) {
                        this.token.internalTok.waitUntilSent();
                    }
                    this.token.internalTok.markComplete(null, null);
                    if (this.this$0.sender != null) {
                        if (this.this$0.sender.isRunning()) {
                            break Label_0239;
                        }
                    }
                }
                catch (final MqttException ex) {
                    this.token.internalTok.markComplete(null, null);
                    if (this.this$0.sender != null) {
                        if (this.this$0.sender.isRunning()) {
                            break Label_0239;
                        }
                    }
                }
                finally {
                    this.token.internalTok.markComplete(null, null);
                    if (this.this$0.sender == null || !this.this$0.sender.isRunning()) {
                        this.token.internalTok.notifyComplete();
                    }
                    this.this$0.shutdownConnection(this.token, null);
                }
                this.token.internalTok.notifyComplete();
            }
            this.this$0.shutdownConnection(this.token, null);
        }
        
        void start() {
            final StringBuilder sb = new StringBuilder("MQTT Disc: ");
            sb.append(this.this$0.getClient().getClientId());
            this.threadName = sb.toString();
            if (this.this$0.executorService == null) {
                new Thread((Runnable)this).start();
            }
            else {
                this.this$0.executorService.execute((Runnable)this);
            }
        }
    }
    
    class MessageDiscardedCallback implements IDiscardedBufferMessageCallback
    {
        final ClientComms this$0;
        
        MessageDiscardedCallback(final ClientComms this$0) {
            this.this$0 = this$0;
        }
        
        @Override
        public void messageDiscarded(final MqttWireMessage mqttWireMessage) {
            if (this.this$0.disconnectedMessageBuffer.isPersistBuffer()) {
                this.this$0.clientState.unPersistBufferedMessage(mqttWireMessage);
            }
        }
    }
    
    class ReconnectDisconnectedBufferCallback implements IDisconnectedBufferCallback
    {
        final String methodName;
        final ClientComms this$0;
        
        ReconnectDisconnectedBufferCallback(final ClientComms this$0, final String methodName) {
            this.this$0 = this$0;
            this.methodName = methodName;
        }
        
        @Override
        public void publishBufferedMessage(final BufferedMessage bufferedMessage) throws MqttException {
            if (this.this$0.isConnected()) {
                while (this.this$0.clientState.getActualInFlight() >= this.this$0.clientState.getMaxInFlight() - 3) {
                    Thread.yield();
                }
                this.this$0.log.fine(this.this$0.CLASS_NAME, this.methodName, "510", new Object[] { bufferedMessage.getMessage().getKey() });
                this.this$0.internalSend(bufferedMessage.getMessage(), bufferedMessage.getToken());
                this.this$0.clientState.unPersistBufferedMessage(bufferedMessage.getMessage());
                return;
            }
            this.this$0.log.fine(this.this$0.CLASS_NAME, this.methodName, "208");
            throw ExceptionHelper.createMqttException(32104);
        }
    }
}
