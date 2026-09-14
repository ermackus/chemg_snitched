package org.eclipse.paho.client.mqttv3.internal;

import java.util.concurrent.ExecutorService;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.io.InputStream;
import java.util.concurrent.Future;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;

public class CommsReceiver implements Runnable
{
    private static final String CLASS_NAME;
    private ClientComms clientComms;
    private ClientState clientState;
    private State current_state;
    private MqttInputStream in;
    private final Object lifecycle;
    private Logger log;
    private Thread recThread;
    private Future<?> receiverFuture;
    private State target_state;
    private String threadName;
    private CommsTokenStore tokenStore;
    
    static {
        CLASS_NAME = CommsReceiver.class.getName();
    }
    
    public CommsReceiver(final ClientComms clientComms, final ClientState clientState, final CommsTokenStore tokenStore, final InputStream inputStream) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CommsReceiver.CLASS_NAME);
        this.current_state = State.STOPPED;
        this.target_state = State.STOPPED;
        this.lifecycle = new Object();
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.recThread = null;
        this.in = new MqttInputStream(clientState, inputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = tokenStore;
        this.log.setResourceName(clientComms.getClient().getClientId());
    }
    
    public boolean isReceiving() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            return this.current_state == State.RECEIVING;
        }
    }
    
    public boolean isRunning() {
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            return (this.current_state == State.RUNNING || this.current_state == State.RECEIVING) && this.target_state == State.RUNNING;
        }
    }
    
    public void run() {
        (this.recThread = Thread.currentThread()).setName(this.threadName);
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            this.current_state = State.RUNNING;
            monitorexit(lifecycle);
            try {
                synchronized (this.lifecycle) {
                    final State target_state = this.target_state;
                    monitorexit(lifecycle);
                    Object o = null;
                    while (target_state == State.RUNNING) {
                        if (this.in == null) {
                            break;
                        }
                        MqttToken mqttToken = (MqttToken)o;
                        MqttToken mqttToken2 = (MqttToken)o;
                        try {
                            this.log.fine(CommsReceiver.CLASS_NAME, "run", "852");
                            mqttToken = (MqttToken)o;
                            mqttToken2 = (MqttToken)o;
                            if (this.in.available() > 0) {
                                mqttToken = (MqttToken)o;
                                mqttToken2 = (MqttToken)o;
                                final Object lifecycle2 = this.lifecycle;
                                mqttToken = (MqttToken)o;
                                mqttToken2 = (MqttToken)o;
                                final Object o2;
                                monitorenter(o2 = lifecycle2);
                                try {
                                    this.current_state = State.RECEIVING;
                                    monitorexit(o2);
                                }
                                finally {
                                    monitorexit(o2);
                                    mqttToken = (MqttToken)o;
                                    mqttToken2 = (MqttToken)o;
                                }
                            }
                            mqttToken = (MqttToken)o;
                            mqttToken2 = (MqttToken)o;
                            final MqttWireMessage mqttWireMessage = this.in.readMqttWireMessage();
                            mqttToken = (MqttToken)o;
                            mqttToken2 = (MqttToken)o;
                            final Object lifecycle3 = this.lifecycle;
                            mqttToken = (MqttToken)o;
                            mqttToken2 = (MqttToken)o;
                            final Object o3;
                            monitorenter(o3 = lifecycle3);
                            try {
                                this.current_state = State.RUNNING;
                                monitorexit(o3);
                                mqttToken = (MqttToken)o;
                                mqttToken2 = (MqttToken)o;
                                MqttToken mqttToken3 = null;
                                Label_0450: {
                                    if (mqttWireMessage instanceof MqttAck) {
                                        mqttToken = (MqttToken)o;
                                        mqttToken2 = (MqttToken)o;
                                        o = this.tokenStore.getToken(mqttWireMessage);
                                        if (o != null) {
                                            mqttToken = (MqttToken)o;
                                            mqttToken2 = (MqttToken)o;
                                            monitorenter(o);
                                            try {
                                                this.clientState.notifyReceivedAck((MqttAck)mqttWireMessage);
                                                monitorexit(lifecycle);
                                                break Label_0450;
                                            }
                                            finally {
                                                monitorexit(lifecycle);
                                                mqttToken = (MqttToken)o;
                                                mqttToken2 = (MqttToken)o;
                                            }
                                        }
                                        mqttToken = (MqttToken)o;
                                        mqttToken2 = (MqttToken)o;
                                        if (!(mqttWireMessage instanceof MqttPubRec)) {
                                            mqttToken = (MqttToken)o;
                                            mqttToken2 = (MqttToken)o;
                                            if (!(mqttWireMessage instanceof MqttPubComp)) {
                                                mqttToken = (MqttToken)o;
                                                mqttToken2 = (MqttToken)o;
                                                if (!(mqttWireMessage instanceof MqttPubAck)) {
                                                    mqttToken = (MqttToken)o;
                                                    mqttToken2 = (MqttToken)o;
                                                    mqttToken = (MqttToken)o;
                                                    mqttToken2 = (MqttToken)o;
                                                    final MqttException ex = new MqttException(6);
                                                    mqttToken = (MqttToken)o;
                                                    mqttToken2 = (MqttToken)o;
                                                    throw ex;
                                                }
                                            }
                                        }
                                        mqttToken = (MqttToken)o;
                                        mqttToken2 = (MqttToken)o;
                                        this.log.fine(CommsReceiver.CLASS_NAME, "run", "857");
                                        mqttToken3 = (MqttToken)o;
                                    }
                                    else if (mqttWireMessage != null) {
                                        mqttToken = (MqttToken)o;
                                        mqttToken2 = (MqttToken)o;
                                        this.clientState.notifyReceivedMsg(mqttWireMessage);
                                        mqttToken3 = (MqttToken)o;
                                    }
                                    else {
                                        mqttToken3 = (MqttToken)o;
                                        mqttToken = (MqttToken)o;
                                        mqttToken2 = (MqttToken)o;
                                        if (!this.clientComms.isConnected()) {
                                            mqttToken = (MqttToken)o;
                                            mqttToken2 = (MqttToken)o;
                                            if (!this.clientComms.isConnecting()) {
                                                mqttToken = (MqttToken)o;
                                                mqttToken2 = (MqttToken)o;
                                                mqttToken = (MqttToken)o;
                                                mqttToken2 = (MqttToken)o;
                                                final IOException ex2 = new IOException("Connection is lost.");
                                                mqttToken = (MqttToken)o;
                                                mqttToken2 = (MqttToken)o;
                                                throw ex2;
                                            }
                                            mqttToken3 = (MqttToken)o;
                                        }
                                    }
                                }
                                o = this.lifecycle;
                                synchronized (o) {
                                    this.current_state = State.RUNNING;
                                    monitorexit(lifecycle);
                                    o = mqttToken3;
                                }
                            }
                            finally {
                                monitorexit(o3);
                                mqttToken = (MqttToken)o;
                                mqttToken2 = (MqttToken)o;
                            }
                        }
                        catch (final IOException ex3) {}
                        catch (final MqttException ex4) {}
                        finally {
                            o = this.lifecycle;
                            synchronized (o) {
                                this.current_state = State.RUNNING;
                                monitorexit(lifecycle);
                            }
                            final Object lifecycle4 = this.lifecycle;
                            synchronized (lifecycle4) {
                                this.target_state = State.STOPPED;
                                monitorexit(lifecycle4);
                                if (!this.clientComms.isDisconnecting()) {
                                    final Throwable t;
                                    this.clientComms.shutdownConnection(mqttToken, new MqttException(32109, t));
                                }
                            }
                            o = this.lifecycle;
                            Label_0690: {
                                synchronized (o) {
                                    this.current_state = State.RUNNING;
                                    monitorexit(lifecycle);
                                    o = mqttToken;
                                    break Label_0690;
                                }
                                final MqttException ex4;
                                final MqttException ex5 = ex4;
                                this.log.fine(CommsReceiver.CLASS_NAME, "run", "856", null, (Throwable)ex5);
                                o = this.lifecycle;
                                synchronized (o) {
                                    this.target_state = State.STOPPED;
                                    monitorexit(lifecycle);
                                    this.clientComms.shutdownConnection(mqttToken2, ex5);
                                    o = this.lifecycle;
                                    synchronized (o) {
                                        this.current_state = State.RUNNING;
                                        monitorexit(lifecycle);
                                        o = mqttToken2;
                                        final Object lifecycle5 = this.lifecycle;
                                        synchronized (lifecycle5) {
                                            final State target_state2 = this.target_state;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    final Object lifecycle6 = this.lifecycle;
                    synchronized (lifecycle6) {
                        this.current_state = State.STOPPED;
                        monitorexit(lifecycle6);
                        this.recThread = null;
                        this.log.fine(CommsReceiver.CLASS_NAME, "run", "854");
                    }
                }
            }
            finally {
                synchronized (this.lifecycle) {
                    this.current_state = State.STOPPED;
                    monitorexit(lifecycle);
                }
            }
        }
    }
    
    public void start(final String threadName, final ExecutorService executorService) {
        this.threadName = threadName;
        this.log.fine(CommsReceiver.CLASS_NAME, "start", "855");
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (this.current_state == State.STOPPED && this.target_state == State.STOPPED) {
                this.target_state = State.RUNNING;
                if (executorService == null) {
                    new Thread((Runnable)this).start();
                }
                else {
                    this.receiverFuture = (Future<?>)executorService.submit((Runnable)this);
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
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (this.receiverFuture != null) {
                this.receiverFuture.cancel(true);
            }
            this.log.fine(CommsReceiver.CLASS_NAME, "stop", "850");
            if (this.isRunning()) {
                this.target_state = State.STOPPED;
            }
            monitorexit(lifecycle);
            while (this.isRunning()) {
                try {
                    Thread.sleep(100L);
                }
                catch (final Exception ex) {}
            }
            this.log.fine(CommsReceiver.CLASS_NAME, "stop", "851");
        }
    }
    
    private enum State
    {
        private static final State[] ENUM$VALUES;
        
        RECEIVING("RECEIVING", 3), 
        RUNNING("RUNNING", 1), 
        STARTING("STARTING", 2), 
        STOPPED("STOPPED", 0);
        
        private State(final String s, final int n) {
        }
    }
}
