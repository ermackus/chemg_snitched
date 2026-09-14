package org.eclipse.paho.client.mqttv3.internal;

import java.util.concurrent.ExecutorService;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.io.OutputStream;
import java.util.concurrent.Future;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;

public class CommsSender implements Runnable
{
    private static final String CLASS_NAME;
    private ClientComms clientComms;
    private ClientState clientState;
    private State current_state;
    private final Object lifecycle;
    private Logger log;
    private MqttOutputStream out;
    private Thread sendThread;
    private Future<?> senderFuture;
    private State target_state;
    private String threadName;
    private CommsTokenStore tokenStore;
    
    static {
        CLASS_NAME = CommsSender.class.getName();
    }
    
    public CommsSender(final ClientComms clientComms, final ClientState clientState, final CommsTokenStore tokenStore, final OutputStream outputStream) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CommsSender.CLASS_NAME);
        this.current_state = State.STOPPED;
        this.target_state = State.STOPPED;
        this.lifecycle = new Object();
        this.sendThread = null;
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.out = new MqttOutputStream(clientState, outputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = tokenStore;
        this.log.setResourceName(clientComms.getClient().getClientId());
    }
    
    private void handleRunException(final MqttWireMessage mqttWireMessage, final Exception ex) {
        this.log.fine(CommsSender.CLASS_NAME, "handleRunException", "804", null, (Throwable)ex);
        MqttException ex2;
        if (!(ex instanceof MqttException)) {
            ex2 = new MqttException(32109, (Throwable)ex);
        }
        else {
            ex2 = (MqttException)ex;
        }
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            this.target_state = State.STOPPED;
            monitorexit(lifecycle);
            this.clientComms.shutdownConnection(null, ex2);
        }
    }
    
    public boolean isRunning() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            return this.current_state == State.RUNNING && this.target_state == State.RUNNING;
        }
    }
    
    public void run() {
        (this.sendThread = Thread.currentThread()).setName(this.threadName);
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            this.current_state = State.RUNNING;
            monitorexit(lifecycle);
            try {
                synchronized (this.lifecycle) {
                    Object o = this.target_state;
                    monitorexit(lifecycle);
                    Object o2 = null;
                    while (o == State.RUNNING) {
                        if (this.out == null) {
                            break;
                        }
                        o = o2;
                        Label_0356: {
                            try {
                                final MqttWireMessage value = this.clientState.get();
                                if (value != null) {
                                    o = value;
                                    o2 = value;
                                    this.log.fine(CommsSender.CLASS_NAME, "run", "802", new Object[] { value.getKey(), value });
                                    o = value;
                                    o2 = value;
                                    if (value instanceof MqttAck) {
                                        o = value;
                                        o2 = value;
                                        this.out.write(value);
                                        o = value;
                                        o2 = value;
                                        this.out.flush();
                                        o2 = value;
                                        break Label_0356;
                                    }
                                    o = value;
                                    o2 = value;
                                    MqttToken mqttToken;
                                    if ((mqttToken = value.getToken()) == null) {
                                        o = value;
                                        o2 = value;
                                        mqttToken = this.tokenStore.getToken(value);
                                    }
                                    o2 = value;
                                    if (mqttToken == null) {
                                        break Label_0356;
                                    }
                                    o = value;
                                    o2 = value;
                                    final MqttToken mqttToken2;
                                    monitorenter(mqttToken2 = mqttToken);
                                    try {
                                        this.out.write(value);
                                        try {
                                            this.out.flush();
                                        }
                                        catch (final IOException o2) {
                                            if (!(value instanceof MqttDisconnect)) {
                                                throw o2;
                                            }
                                        }
                                        this.clientState.notifySent(value);
                                        monitorexit(mqttToken2);
                                        o2 = value;
                                        break Label_0356;
                                    }
                                    finally {
                                        monitorexit(mqttToken2);
                                        o = value;
                                        o2 = value;
                                    }
                                }
                                o = value;
                                o2 = value;
                                this.log.fine(CommsSender.CLASS_NAME, "run", "803");
                                o = value;
                                o2 = value;
                                final Object lifecycle2 = this.lifecycle;
                                o = value;
                                o2 = value;
                                final Object o3;
                                monitorenter(o3 = lifecycle2);
                                try {
                                    this.target_state = State.STOPPED;
                                    monitorexit(o3);
                                    o2 = value;
                                }
                                finally {
                                    monitorexit(o3);
                                    o = value;
                                    o2 = value;
                                }
                            }
                            catch (final Exception ex) {
                                this.handleRunException((MqttWireMessage)o, ex);
                            }
                            catch (final MqttException o) {
                                this.handleRunException((MqttWireMessage)o2, (Exception)o);
                            }
                        }
                        final Object lifecycle3 = this.lifecycle;
                        synchronized (lifecycle3) {
                            o = this.target_state;
                            continue;
                        }
                        break;
                    }
                    synchronized (this.lifecycle) {
                        this.current_state = State.STOPPED;
                        this.sendThread = null;
                        monitorexit(lifecycle);
                        this.log.fine(CommsSender.CLASS_NAME, "run", "805");
                    }
                }
            }
            finally {
                synchronized (this.lifecycle) {
                    this.current_state = State.STOPPED;
                    this.sendThread = null;
                    monitorexit(lifecycle);
                }
            }
        }
    }
    
    public void start(final String threadName, final ExecutorService executorService) {
        this.threadName = threadName;
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (this.current_state == State.STOPPED && this.target_state == State.STOPPED) {
                this.target_state = State.RUNNING;
                if (executorService == null) {
                    new Thread((Runnable)this).start();
                }
                else {
                    this.senderFuture = (Future<?>)executorService.submit((Runnable)this);
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
        //     1: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsSender.isRunning:()Z
        //     4: ifne            8
        //     7: return         
        //     8: aload_0        
        //     9: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.lifecycle:Ljava/lang/Object;
        //    12: astore_2       
        //    13: aload_2        
        //    14: dup            
        //    15: astore_3       
        //    16: monitorenter   
        //    17: aload_0        
        //    18: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.senderFuture:Ljava/util/concurrent/Future;
        //    21: ifnull          35
        //    24: aload_0        
        //    25: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.senderFuture:Ljava/util/concurrent/Future;
        //    28: iconst_1       
        //    29: invokeinterface java/util/concurrent/Future.cancel:(Z)Z
        //    34: pop            
        //    35: aload_0        
        //    36: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    39: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsSender.CLASS_NAME:Ljava/lang/String;
        //    42: ldc             "stop"
        //    44: ldc             "800"
        //    46: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    51: aload_0        
        //    52: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsSender.isRunning:()Z
        //    55: ifeq            72
        //    58: aload_0        
        //    59: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsSender$State.STOPPED:Lorg/eclipse/paho/client/mqttv3/internal/CommsSender$State;
        //    62: putfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.target_state:Lorg/eclipse/paho/client/mqttv3/internal/CommsSender$State;
        //    65: aload_0        
        //    66: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.clientState:Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
        //    69: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.notifyQueueLock:()V
        //    72: aload_3        
        //    73: monitorexit    
        //    74: aload_0        
        //    75: invokevirtual   org/eclipse/paho/client/mqttv3/internal/CommsSender.isRunning:()Z
        //    78: ifne            98
        //    81: aload_0        
        //    82: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    85: getstatic       org/eclipse/paho/client/mqttv3/internal/CommsSender.CLASS_NAME:Ljava/lang/String;
        //    88: ldc             "stop"
        //    90: ldc             "801"
        //    92: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    97: return         
        //    98: ldc2_w          100
        //   101: invokestatic    java/lang/Thread.sleep:(J)V
        //   104: aload_0        
        //   105: getfield        org/eclipse/paho/client/mqttv3/internal/CommsSender.clientState:Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
        //   108: invokevirtual   org/eclipse/paho/client/mqttv3/internal/ClientState.notifyQueueLock:()V
        //   111: goto            74
        //   114: astore_1       
        //   115: aload_3        
        //   116: monitorexit    
        //   117: aload_1        
        //   118: athrow         
        //   119: astore_1       
        //   120: goto            104
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  17     35     114    119    Any
        //  35     72     114    119    Any
        //  72     74     114    119    Any
        //  98     104    119    123    Ljava/lang/Exception;
        //  115    117    114    119    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0098:
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
        
        RUNNING("RUNNING", 1), 
        STARTING("STARTING", 2), 
        STOPPED("STOPPED", 0);
        
        private State(final String s, final int n) {
        }
    }
}
