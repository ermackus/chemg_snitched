package org.eclipse.paho.client.mqttv3;

import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.util.concurrent.ScheduledFuture;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class ScheduledExecutorPingSender implements MqttPingSender
{
    private static final String CLASS_NAME;
    private String clientid;
    private ClientComms comms;
    private ScheduledExecutorService executorService;
    private final Logger log;
    private ScheduledFuture scheduledFuture;
    
    static {
        CLASS_NAME = ScheduledExecutorPingSender.class.getName();
    }
    
    public ScheduledExecutorPingSender(final ScheduledExecutorService executorService) {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", ScheduledExecutorPingSender.CLASS_NAME);
        if (executorService != null) {
            this.executorService = executorService;
            return;
        }
        throw new IllegalArgumentException("ExecutorService cannot be null.");
    }
    
    @Override
    public void init(final ClientComms comms) {
        if (comms != null) {
            this.comms = comms;
            this.clientid = comms.getClient().getClientId();
            return;
        }
        throw new IllegalArgumentException("ClientComms cannot be null.");
    }
    
    @Override
    public void schedule(final long n) {
        this.scheduledFuture = this.executorService.schedule((Runnable)new PingRunnable((PingRunnable)null), n, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void start() {
        this.log.fine(ScheduledExecutorPingSender.CLASS_NAME, "start", "659", new Object[] { this.clientid });
        this.schedule(this.comms.getKeepAlive());
    }
    
    @Override
    public void stop() {
        this.log.fine(ScheduledExecutorPingSender.CLASS_NAME, "stop", "661", null);
        final ScheduledFuture scheduledFuture = this.scheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
    
    private class PingRunnable implements Runnable
    {
        private static final String methodName = "PingTask.run";
        final ScheduledExecutorPingSender this$0;
        
        private PingRunnable(final ScheduledExecutorPingSender this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            final String name = Thread.currentThread().getName();
            final Thread currentThread = Thread.currentThread();
            final StringBuilder sb = new StringBuilder("MQTT Ping: ");
            sb.append(this.this$0.clientid);
            currentThread.setName(sb.toString());
            this.this$0.log.fine(ScheduledExecutorPingSender.CLASS_NAME, "PingTask.run", "660", new Object[] { System.nanoTime() });
            this.this$0.comms.checkForActivity();
            Thread.currentThread().setName(name);
        }
    }
}
