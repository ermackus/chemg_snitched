package org.eclipse.paho.client.mqttv3.internal;

import java.util.concurrent.ExecutorService;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import java.util.Enumeration;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttToken;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import java.util.Hashtable;
import java.util.concurrent.Future;

public class CommsCallback implements Runnable
{
    private static final String CLASS_NAME;
    private static final int INBOUND_QUEUE_SIZE = 10;
    private Future<?> callbackFuture;
    private Thread callbackThread;
    private Hashtable<String, IMqttMessageListener> callbacks;
    private ClientComms clientComms;
    private ClientState clientState;
    private final Vector<MqttToken> completeQueue;
    private State current_state;
    private final Object lifecycle;
    private final Logger log;
    private boolean manualAcks;
    private final Vector<MqttWireMessage> messageQueue;
    private MqttCallback mqttCallback;
    private MqttCallbackExtended reconnectInternalCallback;
    private final Object spaceAvailable;
    private State target_state;
    private String threadName;
    private final Object workAvailable;
    
    static {
        CLASS_NAME = CommsCallback.class.getName();
    }
    
    CommsCallback(final ClientComms clientComms) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CommsCallback.CLASS_NAME);
        this.current_state = State.STOPPED;
        this.target_state = State.STOPPED;
        this.lifecycle = new Object();
        this.workAvailable = new Object();
        this.spaceAvailable = new Object();
        this.manualAcks = false;
        this.clientComms = clientComms;
        this.messageQueue = (Vector<MqttWireMessage>)new Vector(10);
        this.completeQueue = (Vector<MqttToken>)new Vector(10);
        this.callbacks = (Hashtable<String, IMqttMessageListener>)new Hashtable();
        this.log.setResourceName(clientComms.getClient().getClientId());
    }
    
    private void handleActionComplete(final MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            this.log.fine(CommsCallback.CLASS_NAME, "handleActionComplete", "705", new Object[] { mqttToken.internalTok.getKey() });
            if (mqttToken.isComplete()) {
                this.clientState.notifyComplete(mqttToken);
            }
            mqttToken.internalTok.notifyComplete();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.mqttCallback != null && mqttToken instanceof MqttDeliveryToken && mqttToken.isComplete()) {
                    this.mqttCallback.deliveryComplete((IMqttDeliveryToken)mqttToken);
                }
                this.fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete() && mqttToken instanceof MqttDeliveryToken) {
                mqttToken.internalTok.setNotified(true);
            }
        }
    }
    
    private void handleMessage(final MqttPublish mqttPublish) throws MqttException, Exception {
        final String topicName = mqttPublish.getTopicName();
        this.log.fine(CommsCallback.CLASS_NAME, "handleMessage", "713", new Object[] { mqttPublish.getMessageId(), topicName });
        this.deliverMessage(topicName, mqttPublish.getMessageId(), mqttPublish.getMessage());
        if (!this.manualAcks) {
            if (mqttPublish.getMessage().getQos() == 1) {
                this.clientComms.internalSend(new MqttPubAck(mqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
            }
            else if (mqttPublish.getMessage().getQos() == 2) {
                this.clientComms.deliveryComplete(mqttPublish);
                final MqttPubComp mqttPubComp = new MqttPubComp(mqttPublish);
                final ClientComms clientComms = this.clientComms;
                clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
            }
        }
    }
    
    public void asyncOperationComplete(final MqttToken mqttToken) {
        if (this.isRunning()) {
            this.completeQueue.addElement((Object)mqttToken);
            final Object workAvailable = this.workAvailable;
            synchronized (workAvailable) {
                this.log.fine(CommsCallback.CLASS_NAME, "asyncOperationComplete", "715", new Object[] { mqttToken.internalTok.getKey() });
                this.workAvailable.notifyAll();
                return;
            }
        }
        try {
            this.handleActionComplete(mqttToken);
        }
        finally {
            final Throwable t;
            this.log.fine(CommsCallback.CLASS_NAME, "asyncOperationComplete", "719", null, t);
            this.clientComms.shutdownConnection(null, new MqttException(t));
        }
    }
    
    public void connectionLost(final MqttException ex) {
        try {
            if (this.mqttCallback != null && ex != null) {
                this.log.fine(CommsCallback.CLASS_NAME, "connectionLost", "708", new Object[] { ex });
                this.mqttCallback.connectionLost((Throwable)ex);
            }
            if (this.reconnectInternalCallback != null && ex != null) {
                this.reconnectInternalCallback.connectionLost((Throwable)ex);
            }
        }
        finally {
            final Throwable t;
            this.log.fine(CommsCallback.CLASS_NAME, "connectionLost", "720", new Object[] { t });
        }
    }
    
    protected boolean deliverMessage(final String s, final int n, final MqttMessage mqttMessage) throws Exception {
        final Enumeration keys = this.callbacks.keys();
        final boolean b = true;
        boolean b2 = false;
        while (keys.hasMoreElements()) {
            final String s2 = (String)keys.nextElement();
            final IMqttMessageListener mqttMessageListener = (IMqttMessageListener)this.callbacks.get((Object)s2);
            if (mqttMessageListener == null) {
                continue;
            }
            if (!MqttTopic.isMatched(s2, s)) {
                continue;
            }
            mqttMessage.setId(n);
            mqttMessageListener.messageArrived(s, mqttMessage);
            b2 = true;
        }
        if (this.mqttCallback != null && !b2) {
            mqttMessage.setId(n);
            this.mqttCallback.messageArrived(s, mqttMessage);
            b2 = b;
        }
        return b2;
    }
    
    public void fireActionEvent(final MqttToken mqttToken) {
        if (mqttToken != null) {
            final IMqttActionListener actionCallback = mqttToken.getActionCallback();
            if (actionCallback != null) {
                if (mqttToken.getException() == null) {
                    this.log.fine(CommsCallback.CLASS_NAME, "fireActionEvent", "716", new Object[] { mqttToken.internalTok.getKey() });
                    actionCallback.onSuccess(mqttToken);
                }
                else {
                    this.log.fine(CommsCallback.CLASS_NAME, "fireActionEvent", "716", new Object[] { mqttToken.internalTok.getKey() });
                    actionCallback.onFailure(mqttToken, (Throwable)mqttToken.getException());
                }
            }
        }
    }
    
    protected Thread getThread() {
        return this.callbackThread;
    }
    
    public boolean isQuiesced() {
        return this.isQuiescing() && this.completeQueue.size() == 0 && this.messageQueue.size() == 0;
    }
    
    public boolean isQuiescing() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            return this.current_state == State.QUIESCING;
        }
    }
    
    public boolean isRunning() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            return (this.current_state == State.RUNNING || this.current_state == State.QUIESCING) && this.target_state == State.RUNNING;
        }
    }
    
    public void messageArrived(final MqttPublish p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.mqttCallback:Lorg/eclipse/paho/client/mqttv3/MqttCallback;
        //     4: ifnonnull       17
        //     7: aload_0        
        //     8: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.callbacks:Ljava/util/Hashtable;
        //    11: invokevirtual   java/util/Hashtable.size:()I
        //    14: ifle            154
        //    17: aload_0        
        //    18: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.spaceAvailable:Ljava/lang/Object;
        //    21: astore_3       
        //    22: aload_3        
        //    23: dup            
        //    24: astore          5
        //    26: monitorenter   
        //    27: aload_0        
        //    28: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isRunning:()Z
        //    31: ifeq            89
        //    34: aload_0        
        //    35: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isQuiescing:()Z
        //    38: ifne            89
        //    41: aload_0        
        //    42: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.messageQueue:Ljava/util/Vector;
        //    45: invokevirtual   java/util/Vector.size:()I
        //    48: istore_2       
        //    49: iload_2        
        //    50: bipush          10
        //    52: if_icmpge       58
        //    55: goto            89
        //    58: aload_0        
        //    59: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    62: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME:Ljava/lang/String;
        //    65: ldc_w           "messageArrived"
        //    68: ldc_w           "709"
        //    71: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    76: aload_0        
        //    77: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.spaceAvailable:Ljava/lang/Object;
        //    80: ldc2_w          200
        //    83: invokevirtual   java/lang/Object.wait:(J)V
        //    86: goto            27
        //    89: aload           5
        //    91: monitorexit    
        //    92: aload_0        
        //    93: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isQuiescing:()Z
        //    96: ifne            154
        //    99: aload_0        
        //   100: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.messageQueue:Ljava/util/Vector;
        //   103: aload_1        
        //   104: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   107: aload_0        
        //   108: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.workAvailable:Ljava/lang/Object;
        //   111: astore_3       
        //   112: aload_3        
        //   113: dup            
        //   114: astore          5
        //   116: monitorenter   
        //   117: aload_0        
        //   118: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   121: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME:Ljava/lang/String;
        //   124: ldc_w           "messageArrived"
        //   127: ldc_w           "710"
        //   130: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   135: aload_0        
        //   136: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.workAvailable:Ljava/lang/Object;
        //   139: invokevirtual   java/lang/Object.notifyAll:()V
        //   142: aload           5
        //   144: monitorexit    
        //   145: goto            154
        //   148: astore_1       
        //   149: aload           5
        //   151: monitorexit    
        //   152: aload_1        
        //   153: athrow         
        //   154: return         
        //   155: astore_1       
        //   156: aload           5
        //   158: monitorexit    
        //   159: aload_1        
        //   160: athrow         
        //   161: astore          4
        //   163: goto            27
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  27     49     155    161    Any
        //  58     86     161    166    Ljava/lang/InterruptedException;
        //  58     86     155    161    Any
        //  89     92     155    161    Any
        //  117    145    148    154    Any
        //  149    152    148    154    Any
        //  156    159    155    161    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    public void messageArrivedComplete(final int n, final int n2) throws MqttException {
        if (n2 == 1) {
            this.clientComms.internalSend(new MqttPubAck(n), new MqttToken(this.clientComms.getClient().getClientId()));
        }
        else if (n2 == 2) {
            this.clientComms.deliveryComplete(n);
            final MqttPubComp mqttPubComp = new MqttPubComp(n);
            final ClientComms clientComms = this.clientComms;
            clientComms.internalSend(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }
    
    public void quiesce() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (this.current_state == State.RUNNING) {
                this.current_state = State.QUIESCING;
            }
            monitorexit(lifecycle);
            synchronized (this.spaceAvailable) {
                this.log.fine(CommsCallback.CLASS_NAME, "quiesce", "711");
                this.spaceAvailable.notifyAll();
            }
        }
    }
    
    public void removeMessageListener(final String s) {
        this.callbacks.remove((Object)s);
    }
    
    public void removeMessageListeners() {
        this.callbacks.clear();
    }
    
    public void run() {
        (this.callbackThread = Thread.currentThread()).setName(this.threadName);
        Object o;
        monitorenter(o = this.lifecycle);
        try {
            this.current_state = State.RUNNING;
            monitorexit(o);
            Object lifecycle;
            Object workAvailable;
            final Throwable t;
            Object spaceAvailable;
            Object spaceAvailable2;
            Vector<MqttToken> completeQueue;
            MqttToken mqttToken;
            MqttPublish mqttPublish;
            Object spaceAvailable3 = null;
            Label_0290_Outer:Block_16_Outer:
            while (true) {
                if (!this.isRunning()) {
                    lifecycle = this.lifecycle;
                    synchronized (lifecycle) {
                        this.current_state = State.STOPPED;
                        monitorexit(lifecycle);
                        this.callbackThread = null;
                        return;
                    }
                }
                try {
                    workAvailable = this.workAvailable;
                    synchronized (workAvailable) {
                        if (this.isRunning() && this.messageQueue.isEmpty() && this.completeQueue.isEmpty()) {
                            this.log.fine(CommsCallback.CLASS_NAME, "run", "704");
                            this.workAvailable.wait();
                        }
                    }
                }
                catch (final InterruptedException ex) {}
                finally {
                    try {
                        this.log.fine(CommsCallback.CLASS_NAME, "run", "714", null, t);
                        this.clientComms.shutdownConnection(null, new MqttException(t));
                        spaceAvailable = this.spaceAvailable;
                        synchronized (spaceAvailable) {
                            this.log.fine(CommsCallback.CLASS_NAME, "run", "706");
                            this.spaceAvailable.notifyAll();
                        }
                    }
                    finally {
                        spaceAvailable2 = this.spaceAvailable;
                        synchronized (spaceAvailable2) {
                            this.log.fine(CommsCallback.CLASS_NAME, "run", "706");
                            this.spaceAvailable.notifyAll();
                            monitorexit(spaceAvailable2);
                        }
                    }
                    Block_18: {
                        while (true) {
                            while (true) {
                                iftrue(Label_0305:)(!this.isQuiescing());
                                break Block_18;
                                completeQueue = this.completeQueue;
                                synchronized (completeQueue) {
                                    if (!this.completeQueue.isEmpty()) {
                                        mqttToken = (MqttToken)this.completeQueue.elementAt(0);
                                        this.completeQueue.removeElementAt(0);
                                    }
                                    else {
                                        mqttToken = null;
                                    }
                                    monitorexit(completeQueue);
                                    if (mqttToken != null) {
                                        this.handleActionComplete(mqttToken);
                                    }
                                    synchronized (o = this.messageQueue) {
                                        if (!this.messageQueue.isEmpty()) {
                                            mqttPublish = (MqttPublish)this.messageQueue.elementAt(0);
                                            this.messageQueue.removeElementAt(0);
                                        }
                                        else {
                                            mqttPublish = null;
                                        }
                                        monitorexit(completeQueue);
                                        if (mqttPublish != null) {
                                            this.handleMessage(mqttPublish);
                                        }
                                    }
                                }
                                continue Block_16_Outer;
                            }
                            iftrue(Label_0290:)(!this.isRunning());
                            continue;
                        }
                    }
                    this.clientState.checkQuiesceLock();
                    Label_0305: {
                        spaceAvailable3 = this.spaceAvailable;
                    }
                    synchronized (spaceAvailable3) {
                        this.log.fine(CommsCallback.CLASS_NAME, "run", "706");
                        this.spaceAvailable.notifyAll();
                        continue Label_0290_Outer;
                    }
                }
            }
        }
        finally {}
    }
    
    public void setCallback(final MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
    }
    
    public void setClientState(final ClientState clientState) {
        this.clientState = clientState;
    }
    
    public void setManualAcks(final boolean manualAcks) {
        this.manualAcks = manualAcks;
    }
    
    public void setMessageListener(final String s, final IMqttMessageListener mqttMessageListener) {
        this.callbacks.put((Object)s, (Object)mqttMessageListener);
    }
    
    public void setReconnectCallback(final MqttCallbackExtended reconnectInternalCallback) {
        this.reconnectInternalCallback = reconnectInternalCallback;
    }
    
    public void start(final String threadName, final ExecutorService executorService) {
        this.threadName = threadName;
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (this.current_state == State.STOPPED) {
                this.messageQueue.clear();
                this.completeQueue.clear();
                this.target_state = State.RUNNING;
                if (executorService == null) {
                    new Thread((Runnable)this).start();
                }
                else {
                    this.callbackFuture = (Future<?>)executorService.submit((Runnable)this);
                }
            }
            monitorexit(lifecycle);
            while (!this.isRunning()) {
                try {
                    Thread.sleep(100L);
                }
                catch (final Exception ex) {}
            }
        }
    }
    
    public void stop() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.lifecycle:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: dup            
        //     7: astore_3       
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.callbackFuture:Ljava/util/concurrent/Future;
        //    13: ifnull          27
        //    16: aload_0        
        //    17: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.callbackFuture:Ljava/util/concurrent/Future;
        //    20: iconst_1       
        //    21: invokeinterface java/util/concurrent/Future.cancel:(Z)Z
        //    26: pop            
        //    27: aload_3        
        //    28: monitorexit    
        //    29: aload_0        
        //    30: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isRunning:()Z
        //    33: ifeq            184
        //    36: aload_0        
        //    37: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    40: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME:Ljava/lang/String;
        //    43: ldc_w           "stop"
        //    46: ldc_w           "700"
        //    49: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    54: aload_0        
        //    55: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.lifecycle:Ljava/lang/Object;
        //    58: astore_1       
        //    59: aload_1        
        //    60: dup            
        //    61: astore          4
        //    63: monitorenter   
        //    64: aload_0        
        //    65: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback$State.STOPPED:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback$State;
        //    68: putfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.target_state:Lorg/eclipse/paho/client/mqttv3/internal/CommsCallback$State;
        //    71: aload           4
        //    73: monitorexit    
        //    74: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    77: aload_0        
        //    78: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.callbackThread:Ljava/lang/Thread;
        //    81: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    84: ifne            157
        //    87: aload_0        
        //    88: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.workAvailable:Ljava/lang/Object;
        //    91: astore_1       
        //    92: aload_1        
        //    93: dup            
        //    94: astore          4
        //    96: monitorenter   
        //    97: aload_0        
        //    98: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   101: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME:Ljava/lang/String;
        //   104: ldc_w           "stop"
        //   107: ldc_w           "701"
        //   110: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   115: aload_0        
        //   116: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.workAvailable:Ljava/lang/Object;
        //   119: invokevirtual   java/lang/Object.notifyAll:()V
        //   122: aload           4
        //   124: monitorexit    
        //   125: aload_0        
        //   126: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsCallback.isRunning:()Z
        //   129: ifne            135
        //   132: goto            157
        //   135: ldc2_w          100
        //   138: invokestatic    java/lang/Thread.sleep:(J)V
        //   141: aload_0        
        //   142: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.clientState:Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
        //   145: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.notifyQueueLock:()V
        //   148: goto            125
        //   151: astore_2       
        //   152: aload           4
        //   154: monitorexit    
        //   155: aload_2        
        //   156: athrow         
        //   157: aload_0        
        //   158: getfield        org/eclipse/paho/client/mqttv3/internal/CommsCallback.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   161: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME:Ljava/lang/String;
        //   164: ldc_w           "stop"
        //   167: ldc_w           "703"
        //   170: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   175: goto            184
        //   178: astore_2       
        //   179: aload           4
        //   181: monitorexit    
        //   182: aload_2        
        //   183: athrow         
        //   184: return         
        //   185: astore_1       
        //   186: aload_3        
        //   187: monitorexit    
        //   188: aload_1        
        //   189: athrow         
        //   190: astore_1       
        //   191: goto            141
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      27     185    190    Any
        //  27     29     185    190    Any
        //  64     74     178    184    Any
        //  97     125    151    157    Any
        //  135    141    190    194    Ljava/lang/Exception;
        //  152    155    151    157    Any
        //  179    182    178    184    Any
        //  186    188    185    190    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0135:
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
    
    private enum State
    {
        private static final State[] ENUM$VALUES;
        
        QUIESCING("QUIESCING", 2), 
        RUNNING("RUNNING", 1), 
        STOPPED("STOPPED", 0);
        
        private State(final String s, final int n) {
        }
    }
}
