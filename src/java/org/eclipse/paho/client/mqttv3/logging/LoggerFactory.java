package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoggerFactory
{
    private static final String CLASS_NAME;
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
    private static String jsr47LoggerClassName;
    private static String overrideloggerClassName;
    
    static {
        CLASS_NAME = LoggerFactory.class.getName();
        LoggerFactory.overrideloggerClassName = null;
        LoggerFactory.jsr47LoggerClassName = JSR47Logger.class.getName();
    }
    
    public static Logger getLogger(final String s, final String s2) {
        String s3;
        if ((s3 = LoggerFactory.overrideloggerClassName) == null) {
            s3 = LoggerFactory.jsr47LoggerClassName;
        }
        final Logger logger = getLogger(s3, ResourceBundle.getBundle(s), s2, null);
        if (logger != null) {
            return logger;
        }
        throw new MissingResourceException("Error locating the logging class", LoggerFactory.CLASS_NAME, s2);
    }
    
    private static Logger getLogger(final String className, final ResourceBundle resourceBundle, final String s, final String s2) {
        final Logger logger = null;
        try {
            final Class<?> forName = Class.forName(className);
            Logger logger2 = logger;
            if (forName != null) {
                logger2 = (Logger)forName.newInstance();
                logger2.initialise(resourceBundle, s, s2);
            }
            return logger2;
        }
        catch (final NoClassDefFoundError | ClassNotFoundException | IllegalAccessException | InstantiationException | ExceptionInInitializerError | SecurityException ex) {
            return logger;
        }
    }
    
    public static String getLoggingProperty(String s) {
        final String s2 = null;
        try {
            final Class<?> forName = Class.forName("java.util.logging.LogManager");
            s = (String)forName.getMethod("getProperty", String.class).invoke(forName.getMethod("getLogManager", (Class[])new Class[0]).invoke((Object)null, (Object[])null), new Object[] { s });
            return s;
        }
        catch (final Exception ex) {
            s = s2;
            return s;
        }
    }
    
    public static void setLogger(final String overrideloggerClassName) {
        LoggerFactory.overrideloggerClassName = overrideloggerClassName;
    }
}
