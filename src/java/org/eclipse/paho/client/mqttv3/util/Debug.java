package org.eclipse.paho.client.mqttv3.util;

import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class Debug
{
    private static final String CLASS_NAME;
    private static final String lineSep;
    private static final String separator = "==============";
    private String clientID;
    private ClientComms comms;
    private Logger log;
    
    static {
        CLASS_NAME = ClientComms.class.getName();
        lineSep = System.getProperty("line.separator", "\n");
    }
    
    public Debug(final String s, final ClientComms comms) {
        final Logger logger = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", Debug.CLASS_NAME);
        this.log = logger;
        this.clientID = s;
        this.comms = comms;
        logger.setResourceName(s);
    }
    
    public static String dumpProperties(final Properties properties, final String s) {
        final StringBuffer sb = new StringBuffer();
        final Enumeration propertyNames = properties.propertyNames();
        final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)Debug.lineSep));
        sb2.append("==============");
        sb2.append(" ");
        sb2.append(s);
        sb2.append(" ");
        sb2.append("==============");
        sb2.append(Debug.lineSep);
        sb.append(sb2.toString());
        while (propertyNames.hasMoreElements()) {
            final String s2 = (String)propertyNames.nextElement();
            final StringBuilder sb3 = new StringBuilder(String.valueOf((Object)left(s2, 28, ' ')));
            sb3.append(":  ");
            sb3.append(properties.get((Object)s2));
            sb3.append(Debug.lineSep);
            sb.append(sb3.toString());
        }
        final StringBuilder sb4 = new StringBuilder("==========================================");
        sb4.append(Debug.lineSep);
        sb.append(sb4.toString());
        return sb.toString();
    }
    
    public static String left(final String s, int n, final char c) {
        if (s.length() >= n) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(n);
        sb.append(s);
        n -= s.length();
        while (--n >= 0) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public void dumpBaseDebug() {
        this.dumpVersion();
        this.dumpSystemProperties();
        this.dumpMemoryTrace();
    }
    
    public void dumpClientComms() {
        final ClientComms comms = this.comms;
        if (comms != null) {
            final Properties debug = comms.getDebug();
            final Logger log = this.log;
            final String class_NAME = Debug.CLASS_NAME;
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.clientID));
            sb.append(" : ClientComms");
            log.fine(class_NAME, "dumpClientComms", dumpProperties(debug, sb.toString()).toString());
        }
    }
    
    public void dumpClientDebug() {
        this.dumpClientComms();
        this.dumpConOptions();
        this.dumpClientState();
        this.dumpBaseDebug();
    }
    
    public void dumpClientState() {
        final ClientComms comms = this.comms;
        if (comms != null && comms.getClientState() != null) {
            final Properties debug = this.comms.getClientState().getDebug();
            final Logger log = this.log;
            final String class_NAME = Debug.CLASS_NAME;
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.clientID));
            sb.append(" : ClientState");
            log.fine(class_NAME, "dumpClientState", dumpProperties(debug, sb.toString()).toString());
        }
    }
    
    public void dumpConOptions() {
        final ClientComms comms = this.comms;
        if (comms != null) {
            final Properties debug = comms.getConOptions().getDebug();
            final Logger log = this.log;
            final String class_NAME = Debug.CLASS_NAME;
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.clientID));
            sb.append(" : Connect Options");
            log.fine(class_NAME, "dumpConOptions", dumpProperties(debug, sb.toString()).toString());
        }
    }
    
    protected void dumpMemoryTrace() {
        this.log.dumpTrace();
    }
    
    public void dumpSystemProperties() {
        this.log.fine(Debug.CLASS_NAME, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }
    
    protected void dumpVersion() {
        final StringBuffer sb = new StringBuffer();
        final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)Debug.lineSep));
        sb2.append("==============");
        sb2.append(" Version Info ");
        sb2.append("==============");
        sb2.append(Debug.lineSep);
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder(String.valueOf((Object)left("Version", 20, ' ')));
        sb3.append(":  ");
        sb3.append(ClientComms.VERSION);
        sb3.append(Debug.lineSep);
        sb.append(sb3.toString());
        final StringBuilder sb4 = new StringBuilder(String.valueOf((Object)left("Build Level", 20, ' ')));
        sb4.append(":  ");
        sb4.append(ClientComms.BUILD_LEVEL);
        sb4.append(Debug.lineSep);
        sb.append(sb4.toString());
        final StringBuilder sb5 = new StringBuilder("==========================================");
        sb5.append(Debug.lineSep);
        sb.append(sb5.toString());
        this.log.fine(Debug.CLASS_NAME, "dumpVersion", sb.toString());
    }
}
