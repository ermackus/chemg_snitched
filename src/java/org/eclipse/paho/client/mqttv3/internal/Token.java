package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;

public class Token
{
    private static final String CLASS_NAME;
    private IMqttActionListener callback;
    private IMqttAsyncClient client;
    private volatile boolean completed;
    private MqttException exception;
    private String key;
    private Logger log;
    protected MqttMessage message;
    private int messageID;
    private boolean notified;
    private boolean pendingComplete;
    private MqttWireMessage response;
    private final Object responseLock;
    private boolean sent;
    private final Object sentLock;
    private String[] topics;
    private Object userContext;
    
    static {
        CLASS_NAME = Token.class.getName();
    }
    
    public Token(final String resourceName) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", Token.CLASS_NAME);
        this.completed = false;
        this.pendingComplete = false;
        this.sent = false;
        this.responseLock = new Object();
        this.sentLock = new Object();
        this.message = null;
        this.response = null;
        this.exception = null;
        this.topics = null;
        this.client = null;
        this.callback = null;
        this.userContext = null;
        this.messageID = 0;
        this.notified = false;
        this.log.setResourceName(resourceName);
    }
    
    public boolean checkResult() throws MqttException {
        if (this.getException() == null) {
            return true;
        }
        throw this.getException();
    }
    
    public IMqttActionListener getActionCallback() {
        return this.callback;
    }
    
    public IMqttAsyncClient getClient() {
        return this.client;
    }
    
    public MqttException getException() {
        return this.exception;
    }
    
    public int[] getGrantedQos() {
        int[] grantedQos = new int[0];
        final MqttWireMessage response = this.response;
        if (response instanceof MqttSuback) {
            grantedQos = ((MqttSuback)response).getGrantedQos();
        }
        return grantedQos;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public MqttMessage getMessage() {
        return this.message;
    }
    
    public int getMessageID() {
        return this.messageID;
    }
    
    public MqttWireMessage getResponse() {
        return this.response;
    }
    
    public boolean getSessionPresent() {
        final MqttWireMessage response = this.response;
        return response instanceof MqttConnack && ((MqttConnack)response).getSessionPresent();
    }
    
    public String[] getTopics() {
        return this.topics;
    }
    
    public Object getUserContext() {
        return this.userContext;
    }
    
    public MqttWireMessage getWireMessage() {
        return this.response;
    }
    
    public boolean isComplete() {
        return this.completed;
    }
    
    protected boolean isCompletePending() {
        return this.pendingComplete;
    }
    
    protected boolean isInUse() {
        return this.getClient() != null && !this.isComplete();
    }
    
    public boolean isNotified() {
        return this.notified;
    }
    
    protected void markComplete(final MqttWireMessage response, final MqttException exception) {
        this.log.fine(Token.CLASS_NAME, "markComplete", "404", new Object[] { this.getKey(), response, exception });
        final Object responseLock = this.responseLock;
        synchronized (responseLock) {
            if (response instanceof MqttAck) {
                this.message = null;
            }
            this.pendingComplete = true;
            this.response = response;
            this.exception = exception;
        }
    }
    
    protected void notifyComplete() {
        this.log.fine(Token.CLASS_NAME, "notifyComplete", "404", new Object[] { this.getKey(), this.response, this.exception });
        final Object responseLock = this.responseLock;
        synchronized (responseLock) {
            if (this.exception == null && this.pendingComplete) {
                this.completed = true;
                this.pendingComplete = false;
            }
            else {
                this.pendingComplete = false;
            }
            this.responseLock.notifyAll();
            monitorexit(responseLock);
            synchronized (this.sentLock) {
                this.sent = true;
                this.sentLock.notifyAll();
            }
        }
    }
    
    protected void notifySent() {
        this.log.fine(Token.CLASS_NAME, "notifySent", "403", new Object[] { this.getKey() });
        final Object responseLock = this.responseLock;
        synchronized (responseLock) {
            this.response = null;
            this.completed = false;
            monitorexit(responseLock);
            synchronized (this.sentLock) {
                this.sent = true;
                this.sentLock.notifyAll();
            }
        }
    }
    
    public void reset() throws MqttException {
        if (!this.isInUse()) {
            this.log.fine(Token.CLASS_NAME, "reset", "410", new Object[] { this.getKey() });
            this.client = null;
            this.completed = false;
            this.response = null;
            this.sent = false;
            this.exception = null;
            this.userContext = null;
            return;
        }
        throw new MqttException(32201);
    }
    
    public void setActionCallback(final IMqttActionListener callback) {
        this.callback = callback;
    }
    
    protected void setClient(final IMqttAsyncClient client) {
        this.client = client;
    }
    
    public void setException(final MqttException exception) {
        final Object responseLock = this.responseLock;
        synchronized (responseLock) {
            this.exception = exception;
        }
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setMessage(final MqttMessage message) {
        this.message = message;
    }
    
    public void setMessageID(final int messageID) {
        this.messageID = messageID;
    }
    
    public void setNotified(final boolean notified) {
        this.notified = notified;
    }
    
    public void setTopics(final String[] array) {
        this.topics = array.clone();
    }
    
    public void setUserContext(final Object userContext) {
        this.userContext = userContext;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("key=");
        sb.append(this.getKey());
        sb.append(" ,topics=");
        if (this.getTopics() != null) {
            for (int i = 0; i < this.getTopics().length; ++i) {
                sb.append(this.getTopics()[i]);
                sb.append(", ");
            }
        }
        sb.append(" ,usercontext=");
        sb.append(this.getUserContext());
        sb.append(" ,isComplete=");
        sb.append(this.isComplete());
        sb.append(" ,isNotified=");
        sb.append(this.isNotified());
        sb.append(" ,exception=");
        sb.append((Object)this.getException());
        sb.append(" ,actioncallback=");
        sb.append((Object)this.getActionCallback());
        return sb.toString();
    }
    
    public void waitForCompletion() throws MqttException {
        this.waitForCompletion(-1L);
    }
    
    public void waitForCompletion(final long n) throws MqttException {
        this.log.fine(Token.CLASS_NAME, "waitForCompletion", "407", new Object[] { this.getKey(), n, this });
        if (this.waitForResponse(n) == null && !this.completed) {
            this.log.fine(Token.CLASS_NAME, "waitForCompletion", "406", new Object[] { this.getKey(), this });
            throw this.exception = new MqttException(32000);
        }
        this.checkResult();
    }
    
    protected MqttWireMessage waitForResponse() throws MqttException {
        return this.waitForResponse(-1L);
    }
    
    protected MqttWireMessage waitForResponse(final long timeout) throws MqttException {
        final Object responseLock = this.responseLock;
        synchronized (responseLock) {
            final Logger log = this.log;
            final String class_NAME = Token.CLASS_NAME;
            final String key = this.getKey();
            final boolean sent = this.sent;
            final boolean completed = this.completed;
            String s;
            if (this.exception == null) {
                s = "false";
            }
            else {
                s = "true";
            }
            log.fine(class_NAME, "waitForResponse", "400", new Object[] { key, timeout, sent, completed, s, this.response, this }, (Throwable)this.exception);
            while (true) {
                while (!this.completed) {
                    if (this.exception == null) {
                        try {
                            this.log.fine(Token.CLASS_NAME, "waitForResponse", "408", new Object[] { this.getKey(), timeout });
                            if (timeout <= 0L) {
                                this.responseLock.wait();
                            }
                            else {
                                this.responseLock.wait(timeout);
                            }
                        }
                        catch (final InterruptedException ex) {
                            this.exception = new MqttException((Throwable)ex);
                        }
                    }
                    if (!this.completed) {
                        if (this.exception != null) {
                            this.log.fine(Token.CLASS_NAME, "waitForResponse", "401", null, (Throwable)this.exception);
                            throw this.exception;
                        }
                        if (timeout > 0L) {
                            monitorexit(responseLock);
                            this.log.fine(Token.CLASS_NAME, "waitForResponse", "402", new Object[] { this.getKey(), this.response });
                            return this.response;
                        }
                        continue;
                    }
                }
                continue;
            }
        }
    }
    
    public void waitUntilSent() throws MqttException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/paho/client/mqttv3/internal/Token.sentLock:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: dup            
        //     7: astore          4
        //     9: monitorenter   
        //    10: aload_0        
        //    11: getfield        org/eclipse/paho/client/mqttv3/internal/Token.responseLock:Ljava/lang/Object;
        //    14: astore_3       
        //    15: aload_3        
        //    16: dup            
        //    17: astore          5
        //    19: monitorenter   
        //    20: aload_0        
        //    21: getfield        org/eclipse/paho/client/mqttv3/internal/Token.exception:Lorg/eclipse/paho/client/mqttv3/MqttException;
        //    24: ifnonnull       105
        //    27: aload           5
        //    29: monitorexit    
        //    30: aload_0        
        //    31: getfield        org/eclipse/paho/client/mqttv3/internal/Token.sent:Z
        //    34: ifeq            66
        //    37: aload_0        
        //    38: getfield        org/eclipse/paho/client/mqttv3/internal/Token.sent:Z
        //    41: ifne            62
        //    44: aload_0        
        //    45: getfield        org/eclipse/paho/client/mqttv3/internal/Token.exception:Lorg/eclipse/paho/client/mqttv3/MqttException;
        //    48: ifnonnull       57
        //    51: bipush          6
        //    53: invokestatic    org/eclipse/paho/client/mqttv3/internal/ExceptionHelper.createMqttException:(I)Lorg/eclipse/paho/client/mqttv3/MqttException;
        //    56: athrow         
        //    57: aload_0        
        //    58: getfield        org/eclipse/paho/client/mqttv3/internal/Token.exception:Lorg/eclipse/paho/client/mqttv3/MqttException;
        //    61: athrow         
        //    62: aload           4
        //    64: monitorexit    
        //    65: return         
        //    66: aload_0        
        //    67: getfield        org/eclipse/paho/client/mqttv3/internal/Token.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    70: getstatic       org/eclipse/paho/client/mqttv3/internal/Token.CLASS_NAME:Ljava/lang/String;
        //    73: ldc_w           "waitUntilSent"
        //    76: ldc_w           "409"
        //    79: iconst_1       
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: iconst_0       
        //    85: aload_0        
        //    86: invokevirtual   org/eclipse/paho/client/mqttv3/internal/Token.getKey:()Ljava/lang/String;
        //    89: aastore        
        //    90: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
        //    95: aload_0        
        //    96: getfield        org/eclipse/paho/client/mqttv3/internal/Token.sentLock:Ljava/lang/Object;
        //    99: invokevirtual   java/lang/Object.wait:()V
        //   102: goto            30
        //   105: aload_0        
        //   106: getfield        org/eclipse/paho/client/mqttv3/internal/Token.exception:Lorg/eclipse/paho/client/mqttv3/MqttException;
        //   109: athrow         
        //   110: astore_2       
        //   111: aload           5
        //   113: monitorexit    
        //   114: aload_2        
        //   115: athrow         
        //   116: astore_2       
        //   117: aload           4
        //   119: monitorexit    
        //   120: aload_2        
        //   121: athrow         
        //   122: astore_2       
        //   123: goto            30
        //    Exceptions:
        //  throws org.eclipse.paho.client.mqttv3.MqttException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  10     20     116    122    Any
        //  20     30     110    116    Any
        //  30     57     116    122    Any
        //  57     62     116    122    Any
        //  62     65     116    122    Any
        //  66     102    122    126    Ljava/lang/InterruptedException;
        //  66     102    116    122    Any
        //  105    110    110    116    Any
        //  111    114    110    116    Any
        //  114    116    116    122    Any
        //  117    120    116    122    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
}
