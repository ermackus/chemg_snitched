package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import java.io.EOFException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import java.util.Enumeration;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.Hashtable;

public class ClientState
{
    private static final String CLASS_NAME;
    private static final int MAX_MSG_ID = 65535;
    private static final int MIN_MSG_ID = 1;
    private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
    private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
    private static final String PERSISTENCE_SENT_BUFFERED_PREFIX = "sb-";
    private static final String PERSISTENCE_SENT_PREFIX = "s-";
    private int actualInFlight;
    private CommsCallback callback;
    private boolean cleanSession;
    private ClientComms clientComms;
    private boolean connected;
    private HighResolutionTimer highResolutionTimer;
    private int inFlightPubRels;
    private Hashtable inUseMsgIds;
    private Hashtable inboundQoS2;
    private long keepAliveNanos;
    private long lastInboundActivity;
    private long lastOutboundActivity;
    private long lastPing;
    private Logger log;
    private int maxInflight;
    private int nextMsgId;
    private Hashtable outboundQoS0;
    private Hashtable outboundQoS1;
    private Hashtable outboundQoS2;
    private volatile Vector pendingFlows;
    private volatile Vector pendingMessages;
    private MqttClientPersistence persistence;
    private MqttWireMessage pingCommand;
    private int pingOutstanding;
    private final Object pingOutstandingLock;
    private MqttPingSender pingSender;
    private final Object queueLock;
    private final Object quiesceLock;
    private boolean quiescing;
    private CommsTokenStore tokenStore;
    
    static {
        CLASS_NAME = ClientState.class.getName();
    }
    
    protected ClientState(final MqttClientPersistence persistence, final CommsTokenStore tokenStore, final CommsCallback callback, final ClientComms clientComms, final MqttPingSender pingSender, final HighResolutionTimer highResolutionTimer) throws MqttException {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", ClientState.CLASS_NAME);
        this.nextMsgId = 0;
        this.clientComms = null;
        this.callback = null;
        this.maxInflight = 0;
        this.actualInFlight = 0;
        this.inFlightPubRels = 0;
        this.queueLock = new Object();
        this.quiesceLock = new Object();
        this.quiescing = false;
        this.lastOutboundActivity = 0L;
        this.lastInboundActivity = 0L;
        this.lastPing = 0L;
        this.pingOutstandingLock = new Object();
        this.pingOutstanding = 0;
        this.connected = false;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.pingSender = null;
        this.log.setResourceName(clientComms.getClient().getClientId());
        this.log.finer(ClientState.CLASS_NAME, "<Init>", "");
        this.inUseMsgIds = new Hashtable();
        this.pendingFlows = new Vector();
        this.outboundQoS2 = new Hashtable();
        this.outboundQoS1 = new Hashtable();
        this.outboundQoS0 = new Hashtable();
        this.inboundQoS2 = new Hashtable();
        this.pingCommand = new MqttPingReq();
        this.inFlightPubRels = 0;
        this.actualInFlight = 0;
        this.persistence = persistence;
        this.callback = callback;
        this.tokenStore = tokenStore;
        this.clientComms = clientComms;
        this.pingSender = pingSender;
        this.highResolutionTimer = highResolutionTimer;
        this.restoreState();
    }
    
    private void decrementInFlight() {
        final Object queueLock = this.queueLock;
        synchronized (queueLock) {
            final int actualInFlight = this.actualInFlight - 1;
            this.actualInFlight = actualInFlight;
            this.log.fine(ClientState.CLASS_NAME, "decrementInFlight", "646", new Object[] { actualInFlight });
            if (!this.checkQuiesceLock()) {
                this.queueLock.notifyAll();
            }
        }
    }
    
    private int getNextMessageId() throws MqttException {
        synchronized (this) {
            final int nextMsgId = this.nextMsgId;
            int n = 0;
            do {
                if (++this.nextMsgId > 65535) {
                    this.nextMsgId = 1;
                }
                int n2 = n;
                if (this.nextMsgId == nextMsgId) {
                    n2 = n + 1;
                    if (n2 == 2) {
                        throw ExceptionHelper.createMqttException(32001);
                    }
                }
                n = n2;
            } while (this.inUseMsgIds.containsKey((Object)this.nextMsgId));
            final Integer value = this.nextMsgId;
            this.inUseMsgIds.put((Object)value, (Object)value);
            return this.nextMsgId;
        }
    }
    
    private String getReceivedPersistenceKey(final int n) {
        final StringBuilder sb = new StringBuilder("r-");
        sb.append(n);
        return sb.toString();
    }
    
    private String getReceivedPersistenceKey(final MqttWireMessage mqttWireMessage) {
        final StringBuilder sb = new StringBuilder("r-");
        sb.append(mqttWireMessage.getMessageId());
        return sb.toString();
    }
    
    private String getSendBufferedPersistenceKey(final MqttWireMessage mqttWireMessage) {
        final StringBuilder sb = new StringBuilder("sb-");
        sb.append(mqttWireMessage.getMessageId());
        return sb.toString();
    }
    
    private String getSendConfirmPersistenceKey(final MqttWireMessage mqttWireMessage) {
        final StringBuilder sb = new StringBuilder("sc-");
        sb.append(mqttWireMessage.getMessageId());
        return sb.toString();
    }
    
    private String getSendPersistenceKey(final int n) {
        final StringBuilder sb = new StringBuilder("s-");
        sb.append(n);
        return sb.toString();
    }
    
    private String getSendPersistenceKey(final MqttWireMessage mqttWireMessage) {
        final StringBuilder sb = new StringBuilder("s-");
        sb.append(mqttWireMessage.getMessageId());
        return sb.toString();
    }
    
    private void insertInOrder(final Vector vector, final MqttWireMessage mqttWireMessage) {
        final int messageId = mqttWireMessage.getMessageId();
        for (int i = 0; i < vector.size(); ++i) {
            if (((MqttWireMessage)vector.elementAt(i)).getMessageId() > messageId) {
                vector.insertElementAt((Object)mqttWireMessage, i);
                return;
            }
        }
        vector.addElement((Object)mqttWireMessage);
    }
    
    private Vector reOrder(final Vector vector) {
        final Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        final int n = 0;
        int i = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        while (i < vector.size()) {
            final int messageId = ((MqttWireMessage)vector.elementAt(i)).getMessageId();
            final int n5 = messageId - n2;
            int n6;
            if (n5 > (n6 = n3)) {
                n4 = i;
                n6 = n5;
            }
            ++i;
            n2 = messageId;
            n3 = n6;
        }
        int n7;
        if (65535 - n2 + ((MqttWireMessage)vector.elementAt(0)).getMessageId() > n3) {
            n7 = 0;
        }
        else {
            n7 = n4;
        }
        for (int j = n7; j < vector.size(); ++j) {
            vector2.addElement(vector.elementAt(j));
        }
        for (int k = n; k < n7; ++k) {
            vector2.addElement(vector.elementAt(k));
        }
        return vector2;
    }
    
    private void releaseMessageId(final int n) {
        synchronized (this) {
            this.inUseMsgIds.remove((Object)n);
        }
    }
    
    private void restoreInflightMessages() {
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        final Enumeration keys = this.outboundQoS2.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            final MqttWireMessage mqttWireMessage = (MqttWireMessage)this.outboundQoS2.get(nextElement);
            if (mqttWireMessage instanceof MqttPublish) {
                this.log.fine(ClientState.CLASS_NAME, "restoreInflightMessages", "610", new Object[] { nextElement });
                mqttWireMessage.setDuplicate(true);
                this.insertInOrder(this.pendingMessages, mqttWireMessage);
            }
            else {
                if (!(mqttWireMessage instanceof MqttPubRel)) {
                    continue;
                }
                this.log.fine(ClientState.CLASS_NAME, "restoreInflightMessages", "611", new Object[] { nextElement });
                this.insertInOrder(this.pendingFlows, mqttWireMessage);
            }
        }
        final Enumeration keys2 = this.outboundQoS1.keys();
        while (keys2.hasMoreElements()) {
            final Object nextElement2 = keys2.nextElement();
            final MqttPublish mqttPublish = (MqttPublish)this.outboundQoS1.get(nextElement2);
            mqttPublish.setDuplicate(true);
            this.log.fine(ClientState.CLASS_NAME, "restoreInflightMessages", "612", new Object[] { nextElement2 });
            this.insertInOrder(this.pendingMessages, mqttPublish);
        }
        final Enumeration keys3 = this.outboundQoS0.keys();
        while (keys3.hasMoreElements()) {
            final Object nextElement3 = keys3.nextElement();
            final MqttPublish mqttPublish2 = (MqttPublish)this.outboundQoS0.get(nextElement3);
            this.log.fine(ClientState.CLASS_NAME, "restoreInflightMessages", "512", new Object[] { nextElement3 });
            this.insertInOrder(this.pendingMessages, mqttPublish2);
        }
        this.pendingFlows = this.reOrder(this.pendingFlows);
        this.pendingMessages = this.reOrder(this.pendingMessages);
    }
    
    private MqttWireMessage restoreMessage(final String s, final MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage wireMessage;
        try {
            wireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        }
        catch (final MqttException ex) {
            this.log.fine(ClientState.CLASS_NAME, "restoreMessage", "602", new Object[] { s }, (Throwable)ex);
            if (!(ex.getCause() instanceof EOFException)) {
                throw ex;
            }
            if (s != null) {
                this.persistence.remove(s);
            }
            wireMessage = null;
        }
        this.log.fine(ClientState.CLASS_NAME, "restoreMessage", "601", new Object[] { s, wireMessage });
        return wireMessage;
    }
    
    public MqttToken checkForActivity(final IMqttActionListener actionCallback) throws MqttException {
        this.log.fine(ClientState.CLASS_NAME, "checkForActivity", "616", new Object[0]);
        final Object quiesceLock = this.quiesceLock;
        synchronized (quiesceLock) {
            final boolean quiescing = this.quiescing;
            final MqttToken mqttToken = null;
            if (quiescing) {
                return null;
            }
            monitorexit(quiesceLock);
            TimeUnit.NANOSECONDS.toMillis(this.keepAliveNanos);
            MqttToken mqttToken2 = mqttToken;
            if (this.connected) {
                mqttToken2 = mqttToken;
                if (this.keepAliveNanos > 0L) {
                    final long nanoTime = this.highResolutionTimer.nanoTime();
                    final Object pingOutstandingLock = this.pingOutstandingLock;
                    synchronized (pingOutstandingLock) {
                        if (this.pingOutstanding > 0 && nanoTime - this.lastInboundActivity >= this.keepAliveNanos + 100000) {
                            this.log.severe(ClientState.CLASS_NAME, "checkForActivity", "619", new Object[] { this.keepAliveNanos, this.lastOutboundActivity, this.lastInboundActivity, nanoTime, this.lastPing });
                            throw ExceptionHelper.createMqttException(32000);
                        }
                        if (this.pingOutstanding == 0 && nanoTime - this.lastOutboundActivity >= this.keepAliveNanos * 2L) {
                            this.log.severe(ClientState.CLASS_NAME, "checkForActivity", "642", new Object[] { this.keepAliveNanos, this.lastOutboundActivity, this.lastInboundActivity, nanoTime, this.lastPing });
                            throw ExceptionHelper.createMqttException(32002);
                        }
                        long n;
                        MqttToken mqttToken3;
                        if ((this.pingOutstanding == 0 && nanoTime - this.lastInboundActivity >= this.keepAliveNanos - 100000) || nanoTime - this.lastOutboundActivity >= this.keepAliveNanos - 100000) {
                            this.log.fine(ClientState.CLASS_NAME, "checkForActivity", "620", new Object[] { this.keepAliveNanos, this.lastOutboundActivity, this.lastInboundActivity });
                            mqttToken2 = new MqttToken(this.clientComms.getClient().getClientId());
                            if (actionCallback != null) {
                                mqttToken2.setActionCallback(actionCallback);
                            }
                            this.tokenStore.saveToken(mqttToken2, this.pingCommand);
                            this.pendingFlows.insertElementAt((Object)this.pingCommand, 0);
                            n = this.getKeepAlive();
                            this.notifyQueueLock();
                            mqttToken3 = mqttToken2;
                        }
                        else {
                            this.log.fine(ClientState.CLASS_NAME, "checkForActivity", "634", null);
                            n = Math.max(1L, this.getKeepAlive() - TimeUnit.NANOSECONDS.toMillis(nanoTime - this.lastOutboundActivity));
                            mqttToken3 = null;
                        }
                        monitorexit(pingOutstandingLock);
                        this.log.fine(ClientState.CLASS_NAME, "checkForActivity", "624", new Object[] { n });
                        this.pingSender.schedule(n);
                        mqttToken2 = mqttToken3;
                    }
                }
            }
            return mqttToken2;
        }
    }
    
    protected boolean checkQuiesceLock() {
        final int count = this.tokenStore.count();
        if (this.quiescing && count == 0 && this.pendingFlows.size() == 0 && this.callback.isQuiesced()) {
            this.log.fine(ClientState.CLASS_NAME, "checkQuiesceLock", "626", new Object[] { this.quiescing, this.actualInFlight, this.pendingFlows.size(), this.inFlightPubRels, this.callback.isQuiesced(), count });
            final Object quiesceLock = this.quiesceLock;
            synchronized (quiesceLock) {
                this.quiesceLock.notifyAll();
                return true;
            }
        }
        return false;
    }
    
    protected void clearState() throws MqttException {
        this.log.fine(ClientState.CLASS_NAME, "clearState", ">");
        this.persistence.clear();
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
    }
    
    protected void close() {
        this.inUseMsgIds.clear();
        if (this.pendingMessages != null) {
            this.pendingMessages.clear();
        }
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
        this.inUseMsgIds = null;
        this.pendingMessages = null;
        this.pendingFlows = null;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.tokenStore = null;
        this.callback = null;
        this.clientComms = null;
        this.persistence = null;
        this.pingCommand = null;
        this.highResolutionTimer = null;
    }
    
    public void connected() {
        this.log.fine(ClientState.CLASS_NAME, "connected", "631");
        this.connected = true;
        this.pingSender.start();
    }
    
    protected void deliveryComplete(final int n) throws MqttPersistenceException {
        this.log.fine(ClientState.CLASS_NAME, "deliveryComplete", "641", new Object[] { n });
        this.persistence.remove(this.getReceivedPersistenceKey(n));
        this.inboundQoS2.remove((Object)n);
    }
    
    protected void deliveryComplete(final MqttPublish mqttPublish) throws MqttPersistenceException {
        this.log.fine(ClientState.CLASS_NAME, "deliveryComplete", "641", new Object[] { mqttPublish.getMessageId() });
        this.persistence.remove(this.getReceivedPersistenceKey(mqttPublish));
        this.inboundQoS2.remove((Object)mqttPublish.getMessageId());
    }
    
    public void disconnected(final MqttException ex) {
        this.log.fine(ClientState.CLASS_NAME, "disconnected", "633", new Object[] { ex });
        this.connected = false;
        try {
            if (this.cleanSession) {
                this.clearState();
            }
            this.pendingMessages.clear();
            this.pendingFlows.clear();
            final Object pingOutstandingLock = this.pingOutstandingLock;
            synchronized (pingOutstandingLock) {
                this.pingOutstanding = 0;
            }
        }
        catch (final MqttException ex) {}
    }
    
    protected MqttWireMessage get() throws MqttException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.queueLock:Ljava/lang/Object;
        //     4: astore          4
        //     6: aload           4
        //     8: dup            
        //     9: astore          6
        //    11: monitorenter   
        //    12: aconst_null    
        //    13: astore_3       
        //    14: aload_3        
        //    15: ifnull          23
        //    18: aload           6
        //    20: monitorexit    
        //    21: aload_3        
        //    22: areturn        
        //    23: aload_0        
        //    24: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingMessages:Ljava/util/Vector;
        //    27: invokevirtual   java/util/Vector.isEmpty:()Z
        //    30: ifeq            43
        //    33: aload_0        
        //    34: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //    37: invokevirtual   java/util/Vector.isEmpty:()Z
        //    40: ifne            68
        //    43: aload_0        
        //    44: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //    47: invokevirtual   java/util/Vector.isEmpty:()Z
        //    50: ifeq            111
        //    53: aload_0        
        //    54: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //    57: istore_1       
        //    58: aload_0        
        //    59: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.maxInflight:I
        //    62: istore_2       
        //    63: iload_1        
        //    64: iload_2        
        //    65: if_icmplt       111
        //    68: aload_0        
        //    69: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    72: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //    75: ldc_w           "get"
        //    78: ldc_w           "644"
        //    81: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    86: aload_0        
        //    87: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.queueLock:Ljava/lang/Object;
        //    90: invokevirtual   java/lang/Object.wait:()V
        //    93: aload_0        
        //    94: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    97: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   100: ldc_w           "get"
        //   103: ldc_w           "647"
        //   106: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   111: aload_0        
        //   112: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   115: ifnull          339
        //   118: aload_0        
        //   119: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.connected:Z
        //   122: ifne            155
        //   125: aload_0        
        //   126: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   129: invokevirtual   java/util/Vector.isEmpty:()Z
        //   132: ifne            339
        //   135: aload_0        
        //   136: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   139: iconst_0       
        //   140: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   143: checkcast       Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
        //   146: instanceof      Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttConnect;
        //   149: ifne            155
        //   152: goto            339
        //   155: aload_0        
        //   156: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   159: invokevirtual   java/util/Vector.isEmpty:()Z
        //   162: ifne            233
        //   165: aload_0        
        //   166: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   169: iconst_0       
        //   170: invokevirtual   java/util/Vector.remove:(I)Ljava/lang/Object;
        //   173: checkcast       Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
        //   176: astore_3       
        //   177: aload_3        
        //   178: instanceof      Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttPubRel;
        //   181: ifeq            225
        //   184: aload_0        
        //   185: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.inFlightPubRels:I
        //   188: iconst_1       
        //   189: iadd           
        //   190: istore_1       
        //   191: aload_0        
        //   192: iload_1        
        //   193: putfield        org/eclipse/paho/client/mqttv3/internal/ClientState.inFlightPubRels:I
        //   196: aload_0        
        //   197: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   200: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   203: ldc_w           "get"
        //   206: ldc_w           "617"
        //   209: iconst_1       
        //   210: anewarray       Ljava/lang/Object;
        //   213: dup            
        //   214: iconst_0       
        //   215: iload_1        
        //   216: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   219: aastore        
        //   220: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   225: aload_0        
        //   226: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.checkQuiesceLock:()Z
        //   229: pop            
        //   230: goto            14
        //   233: aload_0        
        //   234: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingMessages:Ljava/util/Vector;
        //   237: invokevirtual   java/util/Vector.isEmpty:()Z
        //   240: ifne            14
        //   243: aload_0        
        //   244: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //   247: aload_0        
        //   248: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.maxInflight:I
        //   251: if_icmpge       318
        //   254: aload_0        
        //   255: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingMessages:Ljava/util/Vector;
        //   258: iconst_0       
        //   259: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //   262: checkcast       Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
        //   265: astore_3       
        //   266: aload_0        
        //   267: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingMessages:Ljava/util/Vector;
        //   270: iconst_0       
        //   271: invokevirtual   java/util/Vector.removeElementAt:(I)V
        //   274: aload_0        
        //   275: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //   278: iconst_1       
        //   279: iadd           
        //   280: istore_1       
        //   281: aload_0        
        //   282: iload_1        
        //   283: putfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //   286: aload_0        
        //   287: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   290: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   293: ldc_w           "get"
        //   296: ldc_w           "623"
        //   299: iconst_1       
        //   300: anewarray       Ljava/lang/Object;
        //   303: dup            
        //   304: iconst_0       
        //   305: iload_1        
        //   306: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   309: aastore        
        //   310: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   315: goto            14
        //   318: aload_0        
        //   319: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   322: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   325: ldc_w           "get"
        //   328: ldc_w           "622"
        //   331: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   336: goto            14
        //   339: aload_0        
        //   340: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   343: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   346: ldc_w           "get"
        //   349: ldc_w           "621"
        //   352: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   357: aload           6
        //   359: monitorexit    
        //   360: aconst_null    
        //   361: areturn        
        //   362: astore_3       
        //   363: aload           6
        //   365: monitorexit    
        //   366: aload_3        
        //   367: athrow         
        //   368: astore          5
        //   370: goto            111
        //    Exceptions:
        //  throws org.eclipse.paho.client.mqttv3.MqttException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  18     21     362    368    Any
        //  23     43     362    368    Any
        //  43     63     362    368    Any
        //  68     111    368    373    Ljava/lang/InterruptedException;
        //  68     111    362    368    Any
        //  111    152    362    368    Any
        //  155    225    362    368    Any
        //  225    230    362    368    Any
        //  233    315    362    368    Any
        //  318    336    362    368    Any
        //  339    360    362    368    Any
        //  363    366    362    368    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
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
    
    public int getActualInFlight() {
        return this.actualInFlight;
    }
    
    protected boolean getCleanSession() {
        return this.cleanSession;
    }
    
    public Properties getDebug() {
        final Properties properties = new Properties();
        properties.put((Object)"In use msgids", (Object)this.inUseMsgIds);
        properties.put((Object)"pendingMessages", (Object)this.pendingMessages);
        properties.put((Object)"pendingFlows", (Object)this.pendingFlows);
        properties.put((Object)"maxInflight", (Object)this.maxInflight);
        properties.put((Object)"nextMsgID", (Object)this.nextMsgId);
        properties.put((Object)"actualInFlight", (Object)this.actualInFlight);
        properties.put((Object)"inFlightPubRels", (Object)this.inFlightPubRels);
        properties.put((Object)"quiescing", (Object)this.quiescing);
        properties.put((Object)"pingoutstanding", (Object)this.pingOutstanding);
        properties.put((Object)"lastOutboundActivity", (Object)this.lastOutboundActivity);
        properties.put((Object)"lastInboundActivity", (Object)this.lastInboundActivity);
        properties.put((Object)"outboundQoS2", (Object)this.outboundQoS2);
        properties.put((Object)"outboundQoS1", (Object)this.outboundQoS1);
        properties.put((Object)"outboundQoS0", (Object)this.outboundQoS0);
        properties.put((Object)"inboundQoS2", (Object)this.inboundQoS2);
        properties.put((Object)"tokens", (Object)this.tokenStore);
        return properties;
    }
    
    protected long getKeepAlive() {
        return TimeUnit.NANOSECONDS.toMillis(this.keepAliveNanos);
    }
    
    public int getMaxInFlight() {
        return this.maxInflight;
    }
    
    protected void notifyComplete(final MqttToken mqttToken) throws MqttException {
        final MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage != null && wireMessage instanceof MqttAck) {
            this.log.fine(ClientState.CLASS_NAME, "notifyComplete", "629", new Object[] { wireMessage.getMessageId(), mqttToken, wireMessage });
            final MqttAck mqttAck = (MqttAck)wireMessage;
            if (mqttAck instanceof MqttPubAck) {
                this.persistence.remove(this.getSendPersistenceKey(wireMessage));
                this.persistence.remove(this.getSendBufferedPersistenceKey(wireMessage));
                this.outboundQoS1.remove((Object)mqttAck.getMessageId());
                this.decrementInFlight();
                this.releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                this.log.fine(ClientState.CLASS_NAME, "notifyComplete", "650", new Object[] { mqttAck.getMessageId() });
            }
            else if (mqttAck instanceof MqttPubComp) {
                this.persistence.remove(this.getSendPersistenceKey(wireMessage));
                this.persistence.remove(this.getSendConfirmPersistenceKey(wireMessage));
                this.persistence.remove(this.getSendBufferedPersistenceKey(wireMessage));
                this.outboundQoS2.remove((Object)mqttAck.getMessageId());
                --this.inFlightPubRels;
                this.decrementInFlight();
                this.releaseMessageId(wireMessage.getMessageId());
                this.tokenStore.removeToken(wireMessage);
                this.log.fine(ClientState.CLASS_NAME, "notifyComplete", "645", new Object[] { mqttAck.getMessageId(), this.inFlightPubRels });
            }
            this.checkQuiesceLock();
        }
    }
    
    public void notifyQueueLock() {
        final Object queueLock = this.queueLock;
        synchronized (queueLock) {
            this.log.fine(ClientState.CLASS_NAME, "notifyQueueLock", "638");
            this.queueLock.notifyAll();
        }
    }
    
    protected void notifyReceivedAck(final MqttAck mqttAck) throws MqttException {
        this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        this.log.fine(ClientState.CLASS_NAME, "notifyReceivedAck", "627", new Object[] { mqttAck.getMessageId(), mqttAck });
        final MqttToken token = this.tokenStore.getToken(mqttAck);
        Label_0416: {
            if (token == null) {
                this.log.fine(ClientState.CLASS_NAME, "notifyReceivedAck", "662", new Object[] { mqttAck.getMessageId() });
            }
            else if (mqttAck instanceof MqttPubRec) {
                this.send(new MqttPubRel((MqttPubRec)mqttAck), token);
            }
            else if (!(mqttAck instanceof MqttPubAck) && !(mqttAck instanceof MqttPubComp)) {
                if (mqttAck instanceof MqttPingResp) {
                    final Object pingOutstandingLock = this.pingOutstandingLock;
                    synchronized (pingOutstandingLock) {
                        this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
                        this.notifyResult(mqttAck, token, null);
                        if (this.pingOutstanding == 0) {
                            this.tokenStore.removeToken(mqttAck);
                        }
                        monitorexit(pingOutstandingLock);
                        this.log.fine(ClientState.CLASS_NAME, "notifyReceivedAck", "636", new Object[] { this.pingOutstanding });
                        break Label_0416;
                    }
                }
                if (mqttAck instanceof MqttConnack) {
                    final MqttConnack mqttConnack = (MqttConnack)mqttAck;
                    final int returnCode = mqttConnack.getReturnCode();
                    if (returnCode == 0) {
                        final Object queueLock = this.queueLock;
                        synchronized (queueLock) {
                            if (this.cleanSession) {
                                this.clearState();
                                this.tokenStore.saveToken(token, mqttAck);
                            }
                            this.inFlightPubRels = 0;
                            this.actualInFlight = 0;
                            this.restoreInflightMessages();
                            this.connected();
                            monitorexit(queueLock);
                            this.clientComms.connectComplete(mqttConnack, null);
                            this.notifyResult(mqttAck, token, null);
                            this.tokenStore.removeToken(mqttAck);
                            final Object queueLock2 = this.queueLock;
                            synchronized (queueLock2) {
                                this.queueLock.notifyAll();
                            }
                        }
                    }
                    throw ExceptionHelper.createMqttException(returnCode);
                }
                this.notifyResult(mqttAck, token, null);
                this.releaseMessageId(mqttAck.getMessageId());
                this.tokenStore.removeToken(mqttAck);
            }
            else {
                this.notifyResult(mqttAck, token, null);
            }
        }
        this.checkQuiesceLock();
    }
    
    public void notifyReceivedBytes(final int n) {
        if (n > 0) {
            this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        }
        this.log.fine(ClientState.CLASS_NAME, "notifyReceivedBytes", "630", new Object[] { n });
    }
    
    protected void notifyReceivedMsg(final MqttWireMessage mqttWireMessage) throws MqttException {
        this.lastInboundActivity = this.highResolutionTimer.nanoTime();
        this.log.fine(ClientState.CLASS_NAME, "notifyReceivedMsg", "651", new Object[] { mqttWireMessage.getMessageId(), mqttWireMessage });
        if (!this.quiescing) {
            if (mqttWireMessage instanceof MqttPublish) {
                final MqttPublish mqttPublish = (MqttPublish)mqttWireMessage;
                final int qos = mqttPublish.getMessage().getQos();
                if (qos != 0 && qos != 1) {
                    if (qos == 2) {
                        this.persistence.put(this.getReceivedPersistenceKey(mqttWireMessage), mqttPublish);
                        this.inboundQoS2.put((Object)mqttPublish.getMessageId(), (Object)mqttPublish);
                        this.send(new MqttPubRec(mqttPublish), null);
                    }
                }
                else {
                    final CommsCallback callback = this.callback;
                    if (callback != null) {
                        callback.messageArrived(mqttPublish);
                    }
                }
            }
            else if (mqttWireMessage instanceof MqttPubRel) {
                final MqttPublish mqttPublish2 = (MqttPublish)this.inboundQoS2.get((Object)mqttWireMessage.getMessageId());
                if (mqttPublish2 != null) {
                    final CommsCallback callback2 = this.callback;
                    if (callback2 != null) {
                        callback2.messageArrived(mqttPublish2);
                    }
                }
                else {
                    this.send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
                }
            }
        }
    }
    
    protected void notifyResult(final MqttWireMessage mqttWireMessage, final MqttToken mqttToken, final MqttException ex) {
        mqttToken.internalTok.markComplete(mqttWireMessage, ex);
        mqttToken.internalTok.notifyComplete();
        if (mqttWireMessage != null && mqttWireMessage instanceof MqttAck && !(mqttWireMessage instanceof MqttPubRec)) {
            this.log.fine(ClientState.CLASS_NAME, "notifyResult", "648", new Object[] { mqttToken.internalTok.getKey(), mqttWireMessage, ex });
            this.callback.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            this.log.fine(ClientState.CLASS_NAME, "notifyResult", "649", new Object[] { mqttToken.internalTok.getKey(), ex });
            this.callback.asyncOperationComplete(mqttToken);
        }
    }
    
    protected void notifySent(final MqttWireMessage mqttWireMessage) {
        this.lastOutboundActivity = this.highResolutionTimer.nanoTime();
        this.log.fine(ClientState.CLASS_NAME, "notifySent", "625", new Object[] { mqttWireMessage.getKey() });
        MqttToken mqttToken;
        if ((mqttToken = mqttWireMessage.getToken()) == null && (mqttToken = this.tokenStore.getToken(mqttWireMessage)) == null) {
            return;
        }
        mqttToken.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            final Object pingOutstandingLock = this.pingOutstandingLock;
            synchronized (pingOutstandingLock) {
                final long nanoTime = this.highResolutionTimer.nanoTime();
                final Object pingOutstandingLock2 = this.pingOutstandingLock;
                synchronized (pingOutstandingLock2) {
                    this.lastPing = nanoTime;
                    final int pingOutstanding = this.pingOutstanding + 1;
                    this.pingOutstanding = pingOutstanding;
                    monitorexit(pingOutstandingLock2);
                    this.log.fine(ClientState.CLASS_NAME, "notifySent", "635", new Object[] { pingOutstanding });
                }
            }
        }
        if (mqttWireMessage instanceof MqttPublish && ((MqttPublish)mqttWireMessage).getMessage().getQos() == 0) {
            mqttToken.internalTok.markComplete(null, null);
            this.callback.asyncOperationComplete(mqttToken);
            this.decrementInFlight();
            this.releaseMessageId(mqttWireMessage.getMessageId());
            this.tokenStore.removeToken(mqttWireMessage);
            this.checkQuiesceLock();
        }
    }
    
    public void notifySentBytes(final int n) {
        if (n > 0) {
            this.lastOutboundActivity = this.highResolutionTimer.nanoTime();
        }
        this.log.fine(ClientState.CLASS_NAME, "notifySentBytes", "643", new Object[] { n });
    }
    
    public void persistBufferedMessage(final MqttWireMessage mqttWireMessage) throws MqttException {
        Object o = this.getSendBufferedPersistenceKey(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(this.getNextMessageId());
            o = o;
            final String s = (String)(o = this.getSendBufferedPersistenceKey(mqttWireMessage));
            try {
                this.persistence.put(s, (MqttPersistable)mqttWireMessage);
            }
            catch (MqttPersistenceException o) {
                o = s;
                this.log.fine(ClientState.CLASS_NAME, "persistBufferedMessage", "515");
                o = s;
                this.persistence.open(this.clientComms.getClient().getClientId(), this.clientComms.getClient().getServerURI());
                o = s;
                this.persistence.put(s, (MqttPersistable)mqttWireMessage);
            }
            o = s;
            this.log.fine(ClientState.CLASS_NAME, "persistBufferedMessage", "513", new Object[] { s });
        }
        catch (final MqttException ex) {
            this.log.warning(ClientState.CLASS_NAME, "persistBufferedMessage", "514", new Object[] { o });
            throw ex;
        }
    }
    
    public void quiesce(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: lconst_0       
        //     2: lcmp           
        //     3: ifle            273
        //     6: aload_0        
        //     7: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    10: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //    13: ldc_w           "quiesce"
        //    16: ldc_w           "637"
        //    19: iconst_1       
        //    20: anewarray       Ljava/lang/Object;
        //    23: dup            
        //    24: iconst_0       
        //    25: lload_1        
        //    26: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    29: aastore        
        //    30: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //    35: aload_0        
        //    36: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.queueLock:Ljava/lang/Object;
        //    39: astore          5
        //    41: aload           5
        //    43: dup            
        //    44: astore          6
        //    46: monitorenter   
        //    47: aload_0        
        //    48: iconst_1       
        //    49: putfield        org/eclipse/paho/client/mqttv3/internal/ClientState.quiescing:Z
        //    52: aload           6
        //    54: monitorexit    
        //    55: aload_0        
        //    56: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //    59: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.quiesce:()V
        //    62: aload_0        
        //    63: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.notifyQueueLock:()V
        //    66: aload_0        
        //    67: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.quiesceLock:Ljava/lang/Object;
        //    70: astore          4
        //    72: aload           4
        //    74: dup            
        //    75: astore          7
        //    77: monitorenter   
        //    78: aload_0        
        //    79: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.tokenStore:Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
        //    82: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsTokenStore.count:()I
        //    85: istore_3       
        //    86: iload_3        
        //    87: ifgt            110
        //    90: aload_0        
        //    91: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //    94: invokevirtual   java/util/Vector.size:()I
        //    97: ifgt            110
        //   100: aload_0        
        //   101: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.callback:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback;
        //   104: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isQuiesced:()Z
        //   107: ifne            188
        //   110: aload_0        
        //   111: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   114: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   117: ldc_w           "quiesce"
        //   120: ldc_w           "639"
        //   123: iconst_4       
        //   124: anewarray       Ljava/lang/Object;
        //   127: dup            
        //   128: iconst_0       
        //   129: aload_0        
        //   130: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //   133: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   136: aastore        
        //   137: dup            
        //   138: iconst_1       
        //   139: aload_0        
        //   140: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   143: invokevirtual   java/util/Vector.size:()I
        //   146: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   149: aastore        
        //   150: dup            
        //   151: iconst_2       
        //   152: aload_0        
        //   153: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.inFlightPubRels:I
        //   156: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   159: aastore        
        //   160: dup            
        //   161: iconst_3       
        //   162: iload_3        
        //   163: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   166: aastore        
        //   167: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //   172: aload_0        
        //   173: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.quiesceLock:Ljava/lang/Object;
        //   176: lload_1        
        //   177: invokevirtual   java/lang/Object.wait:(J)V
        //   180: goto            188
        //   183: astore          5
        //   185: goto            259
        //   188: aload           7
        //   190: monitorexit    
        //   191: aload_0        
        //   192: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.queueLock:Ljava/lang/Object;
        //   195: astore          5
        //   197: aload           5
        //   199: dup            
        //   200: astore          6
        //   202: monitorenter   
        //   203: aload_0        
        //   204: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingMessages:Ljava/util/Vector;
        //   207: invokevirtual   java/util/Vector.clear:()V
        //   210: aload_0        
        //   211: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.pendingFlows:Ljava/util/Vector;
        //   214: invokevirtual   java/util/Vector.clear:()V
        //   217: aload_0        
        //   218: iconst_0       
        //   219: putfield        org/eclipse/paho/client/mqttv3/internal/ClientState.quiescing:Z
        //   222: aload_0        
        //   223: iconst_0       
        //   224: putfield        org/eclipse/paho/client/mqttv3/internal/ClientState.actualInFlight:I
        //   227: aload           6
        //   229: monitorexit    
        //   230: aload_0        
        //   231: getfield        org/eclipse/paho/client/mqttv3/internal/ClientState.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   234: getstatic       org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME:Ljava/lang/String;
        //   237: ldc_w           "quiesce"
        //   240: ldc_w           "640"
        //   243: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   248: goto            273
        //   251: astore          4
        //   253: aload           6
        //   255: monitorexit    
        //   256: aload           4
        //   258: athrow         
        //   259: aload           7
        //   261: monitorexit    
        //   262: aload           5
        //   264: athrow         
        //   265: astore          4
        //   267: aload           6
        //   269: monitorexit    
        //   270: aload           4
        //   272: athrow         
        //   273: return         
        //   274: astore          5
        //   276: goto            188
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  47     55     265    273    Any
        //  78     86     274    279    Ljava/lang/InterruptedException;
        //  78     86     183    265    Any
        //  90     110    274    279    Ljava/lang/InterruptedException;
        //  90     110    183    265    Any
        //  110    180    274    279    Ljava/lang/InterruptedException;
        //  110    180    183    265    Any
        //  188    191    183    265    Any
        //  203    230    251    259    Any
        //  253    256    251    259    Any
        //  259    262    183    265    Any
        //  267    270    265    273    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0110:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
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
    
    protected boolean removeMessage(final IMqttDeliveryToken mqttDeliveryToken) throws MqttException {
        final MqttMessage message = mqttDeliveryToken.getMessage();
        final int messageId = mqttDeliveryToken.getMessageId();
        final Object queueLock = this.queueLock;
        synchronized (queueLock) {
            final int qos = message.getQos();
            final boolean b = true;
            boolean b2 = qos == 1 && this.outboundQoS1.remove((Object)messageId) != null;
            if (message.getQos() == 2) {
                b2 = b2;
                if (this.outboundQoS2.remove((Object)messageId) != null) {
                    b2 = true;
                }
            }
            if (this.pendingMessages.removeElement((Object)message)) {
                b2 = b;
            }
            this.persistence.remove(this.getSendPersistenceKey(messageId));
            this.tokenStore.removeToken(Integer.toString(messageId));
            this.releaseMessageId(messageId);
            this.decrementInFlight();
            return b2;
        }
    }
    
    public Vector resolveOldTokens(final MqttException ex) {
        this.log.fine(ClientState.CLASS_NAME, "resolveOldTokens", "632", new Object[] { ex });
        MqttException exception = ex;
        if (ex == null) {
            exception = new MqttException(32102);
        }
        final Vector outstandingTokens = this.tokenStore.getOutstandingTokens();
        final Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            final MqttToken mqttToken = (MqttToken)elements.nextElement();
            synchronized (mqttToken) {
                if (!mqttToken.isComplete() && !mqttToken.internalTok.isCompletePending() && mqttToken.getException() == null) {
                    mqttToken.internalTok.setException(exception);
                }
                monitorexit(mqttToken);
                if (mqttToken instanceof MqttDeliveryToken) {
                    continue;
                }
                this.tokenStore.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }
    
    protected void restoreState() throws MqttException {
        final Enumeration keys = this.persistence.keys();
        int nextMsgId = this.nextMsgId;
        final Vector vector = new Vector();
        this.log.fine(ClientState.CLASS_NAME, "restoreState", "600");
        while (keys.hasMoreElements()) {
            final String s = (String)keys.nextElement();
            final MqttWireMessage restoreMessage = this.restoreMessage(s, this.persistence.get(s));
            if (restoreMessage != null) {
                if (s.startsWith("r-")) {
                    this.log.fine(ClientState.CLASS_NAME, "restoreState", "604", new Object[] { s, restoreMessage });
                    this.inboundQoS2.put((Object)restoreMessage.getMessageId(), (Object)restoreMessage);
                }
                else if (s.startsWith("s-")) {
                    final MqttPublish mqttPublish = (MqttPublish)restoreMessage;
                    nextMsgId = Math.max(mqttPublish.getMessageId(), nextMsgId);
                    if (this.persistence.containsKey(this.getSendConfirmPersistenceKey(mqttPublish))) {
                        final MqttPubRel mqttPubRel = (MqttPubRel)this.restoreMessage(s, this.persistence.get(this.getSendConfirmPersistenceKey(mqttPublish)));
                        if (mqttPubRel != null) {
                            this.log.fine(ClientState.CLASS_NAME, "restoreState", "605", new Object[] { s, restoreMessage });
                            this.outboundQoS2.put((Object)mqttPubRel.getMessageId(), (Object)mqttPubRel);
                        }
                        else {
                            this.log.fine(ClientState.CLASS_NAME, "restoreState", "606", new Object[] { s, restoreMessage });
                        }
                    }
                    else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            this.log.fine(ClientState.CLASS_NAME, "restoreState", "607", new Object[] { s, restoreMessage });
                            this.outboundQoS2.put((Object)mqttPublish.getMessageId(), (Object)mqttPublish);
                        }
                        else {
                            this.log.fine(ClientState.CLASS_NAME, "restoreState", "608", new Object[] { s, restoreMessage });
                            this.outboundQoS1.put((Object)mqttPublish.getMessageId(), (Object)mqttPublish);
                        }
                    }
                    this.tokenStore.restoreToken(mqttPublish).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put((Object)mqttPublish.getMessageId(), (Object)mqttPublish.getMessageId());
                }
                else if (s.startsWith("sb-")) {
                    final MqttPublish mqttPublish2 = (MqttPublish)restoreMessage;
                    nextMsgId = Math.max(mqttPublish2.getMessageId(), nextMsgId);
                    if (mqttPublish2.getMessage().getQos() == 2) {
                        this.log.fine(ClientState.CLASS_NAME, "restoreState", "607", new Object[] { s, restoreMessage });
                        this.outboundQoS2.put((Object)mqttPublish2.getMessageId(), (Object)mqttPublish2);
                    }
                    else if (mqttPublish2.getMessage().getQos() == 1) {
                        this.log.fine(ClientState.CLASS_NAME, "restoreState", "608", new Object[] { s, restoreMessage });
                        this.outboundQoS1.put((Object)mqttPublish2.getMessageId(), (Object)mqttPublish2);
                    }
                    else {
                        this.log.fine(ClientState.CLASS_NAME, "restoreState", "511", new Object[] { s, restoreMessage });
                        this.outboundQoS0.put((Object)mqttPublish2.getMessageId(), (Object)mqttPublish2);
                        this.persistence.remove(s);
                    }
                    this.tokenStore.restoreToken(mqttPublish2).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put((Object)mqttPublish2.getMessageId(), (Object)mqttPublish2.getMessageId());
                }
                else {
                    if (!s.startsWith("sc-") || this.persistence.containsKey(this.getSendPersistenceKey(restoreMessage))) {
                        continue;
                    }
                    vector.addElement((Object)s);
                }
            }
        }
        final Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s2 = (String)elements.nextElement();
            this.log.fine(ClientState.CLASS_NAME, "restoreState", "609", new Object[] { s2 });
            this.persistence.remove(s2);
        }
        this.nextMsgId = nextMsgId;
    }
    
    public void send(final MqttWireMessage pingCommand, final MqttToken token) throws MqttException {
        if (pingCommand.isMessageIdRequired() && pingCommand.getMessageId() == 0) {
            if (pingCommand instanceof MqttPublish && ((MqttPublish)pingCommand).getMessage().getQos() != 0) {
                pingCommand.setMessageId(this.getNextMessageId());
            }
            else if (pingCommand instanceof MqttPubAck || pingCommand instanceof MqttPubRec || pingCommand instanceof MqttPubRel || pingCommand instanceof MqttPubComp || pingCommand instanceof MqttSubscribe || pingCommand instanceof MqttSuback || pingCommand instanceof MqttUnsubscribe || pingCommand instanceof MqttUnsubAck) {
                pingCommand.setMessageId(this.getNextMessageId());
            }
        }
        if (token != null) {
            pingCommand.setToken(token);
            try {
                token.internalTok.setMessageID(pingCommand.getMessageId());
            }
            catch (final Exception ex) {}
        }
        if (pingCommand instanceof MqttPublish) {
            final Object queueLock = this.queueLock;
            synchronized (queueLock) {
                if (this.actualInFlight < this.maxInflight) {
                    final MqttMessage message = ((MqttPublish)pingCommand).getMessage();
                    this.log.fine(ClientState.CLASS_NAME, "send", "628", new Object[] { pingCommand.getMessageId(), message.getQos(), pingCommand });
                    final int qos = message.getQos();
                    if (qos != 1) {
                        if (qos == 2) {
                            this.outboundQoS2.put((Object)pingCommand.getMessageId(), (Object)pingCommand);
                            this.persistence.put(this.getSendPersistenceKey(pingCommand), (MqttPersistable)pingCommand);
                            this.tokenStore.saveToken(token, pingCommand);
                        }
                    }
                    else {
                        this.outboundQoS1.put((Object)pingCommand.getMessageId(), (Object)pingCommand);
                        this.persistence.put(this.getSendPersistenceKey(pingCommand), (MqttPersistable)pingCommand);
                        this.tokenStore.saveToken(token, pingCommand);
                    }
                    this.pendingMessages.addElement((Object)pingCommand);
                    this.queueLock.notifyAll();
                    return;
                }
                this.log.fine(ClientState.CLASS_NAME, "send", "613", new Object[] { this.actualInFlight });
                throw new MqttException(32202);
            }
        }
        this.log.fine(ClientState.CLASS_NAME, "send", "615", new Object[] { pingCommand.getMessageId(), pingCommand });
        if (pingCommand instanceof MqttConnect) {
            final Object queueLock2 = this.queueLock;
            synchronized (queueLock2) {
                this.tokenStore.saveToken(token, pingCommand);
                this.pendingFlows.insertElementAt((Object)pingCommand, 0);
                this.queueLock.notifyAll();
                return;
            }
        }
        if (pingCommand instanceof MqttPingReq) {
            this.pingCommand = pingCommand;
        }
        else if (pingCommand instanceof MqttPubRel) {
            this.outboundQoS2.put((Object)pingCommand.getMessageId(), (Object)pingCommand);
            this.persistence.put(this.getSendConfirmPersistenceKey(pingCommand), (MqttPersistable)pingCommand);
        }
        else if (pingCommand instanceof MqttPubComp) {
            this.persistence.remove(this.getReceivedPersistenceKey(pingCommand));
        }
        final Object queueLock3 = this.queueLock;
        synchronized (queueLock3) {
            if (!(pingCommand instanceof MqttAck)) {
                this.tokenStore.saveToken(token, pingCommand);
            }
            this.pendingFlows.addElement((Object)pingCommand);
            this.queueLock.notifyAll();
        }
    }
    
    protected void setCleanSession(final boolean cleanSession) {
        this.cleanSession = cleanSession;
    }
    
    public void setKeepAliveInterval(final long n) {
        this.keepAliveNanos = TimeUnit.MILLISECONDS.toNanos(n);
    }
    
    protected void setKeepAliveSecs(final long n) {
        this.keepAliveNanos = TimeUnit.SECONDS.toNanos(n);
    }
    
    protected void setMaxInflight(final int maxInflight) {
        this.maxInflight = maxInflight;
        this.pendingMessages = new Vector(this.maxInflight);
    }
    
    public void unPersistBufferedMessage(final MqttWireMessage mqttWireMessage) {
        try {
            this.log.fine(ClientState.CLASS_NAME, "unPersistBufferedMessage", "517", new Object[] { mqttWireMessage.getKey() });
            this.persistence.remove(this.getSendBufferedPersistenceKey(mqttWireMessage));
        }
        catch (final MqttPersistenceException ex) {
            this.log.fine(ClientState.CLASS_NAME, "unPersistBufferedMessage", "518", new Object[] { mqttWireMessage.getKey() });
        }
    }
    
    protected void undo(final MqttPublish mqttPublish) throws MqttPersistenceException {
        final Object queueLock = this.queueLock;
        synchronized (queueLock) {
            this.log.fine(ClientState.CLASS_NAME, "undo", "618", new Object[] { mqttPublish.getMessageId(), mqttPublish.getMessage().getQos() });
            if (mqttPublish.getMessage().getQos() == 1) {
                this.outboundQoS1.remove((Object)mqttPublish.getMessageId());
            }
            else {
                this.outboundQoS2.remove((Object)mqttPublish.getMessageId());
            }
            this.pendingMessages.removeElement((Object)mqttPublish);
            this.persistence.remove(this.getSendPersistenceKey(mqttPublish));
            this.tokenStore.removeToken(mqttPublish);
            if (mqttPublish.getMessage().getQos() > 0) {
                this.releaseMessageId(mqttPublish.getMessageId());
                mqttPublish.setMessageId(0);
            }
            this.checkQuiesceLock();
        }
    }
}
