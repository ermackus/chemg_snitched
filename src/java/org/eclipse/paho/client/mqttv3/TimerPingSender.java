package org.eclipse.paho.client.mqttv3;

import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.util.Timer;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class TimerPingSender implements MqttPingSender
{
    private static final String CLASS_NAME;
    private String clientid;
    private ClientComms comms;
    private Logger log;
    private Timer timer;
    
    static {
        CLASS_NAME = TimerPingSender.class.getName();
    }
    
    public TimerPingSender() {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", TimerPingSender.CLASS_NAME);
    }
    
    @Override
    public void init(final ClientComms comms) {
        if (comms != null) {
            this.comms = comms;
            final String clientId = comms.getClient().getClientId();
            this.clientid = clientId;
            this.log.setResourceName(clientId);
            return;
        }
        throw new IllegalArgumentException("ClientComms cannot be null.");
    }
    
    @Override
    public void schedule(final long n) {
        this.timer.schedule((TimerTask)new PingTask((PingTask)null), n);
    }
    
    @Override
    public void start() {
        this.log.fine(TimerPingSender.CLASS_NAME, "start", "659", new Object[] { this.clientid });
        final StringBuilder sb = new StringBuilder("MQTT Ping: ");
        sb.append(this.clientid);
        (this.timer = new Timer(sb.toString())).schedule((TimerTask)new PingTask((PingTask)null), this.comms.getKeepAlive());
    }
    
    @Override
    public void stop() {
        this.log.fine(TimerPingSender.CLASS_NAME, "stop", "661", null);
        final Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }
    
    private class PingTask extends TimerTask
    {
        private static final String methodName = "PingTask.run";
        final TimerPingSender this$0;
        
        private PingTask(final TimerPingSender this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            this.this$0.log.fine(TimerPingSender.CLASS_NAME, "PingTask.run", "660", new Object[] { System.nanoTime() });
            this.this$0.comms.checkForActivity();
        }
    }
}
