package org.eclipse.paho.client.mqttv3.logging;

import java.util.ResourceBundle;

public interface Logger
{
    public static final int CONFIG = 4;
    public static final int FINE = 5;
    public static final int FINER = 6;
    public static final int FINEST = 7;
    public static final int INFO = 3;
    public static final int SEVERE = 1;
    public static final int WARNING = 2;
    
    void config(final String p0, final String p1, final String p2);
    
    void config(final String p0, final String p1, final String p2, final Object[] p3);
    
    void config(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    void dumpTrace();
    
    void fine(final String p0, final String p1, final String p2);
    
    void fine(final String p0, final String p1, final String p2, final Object[] p3);
    
    void fine(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    void finer(final String p0, final String p1, final String p2);
    
    void finer(final String p0, final String p1, final String p2, final Object[] p3);
    
    void finer(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    void finest(final String p0, final String p1, final String p2);
    
    void finest(final String p0, final String p1, final String p2, final Object[] p3);
    
    void finest(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    String formatMessage(final String p0, final Object[] p1);
    
    void info(final String p0, final String p1, final String p2);
    
    void info(final String p0, final String p1, final String p2, final Object[] p3);
    
    void info(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    void initialise(final ResourceBundle p0, final String p1, final String p2);
    
    boolean isLoggable(final int p0);
    
    void log(final int p0, final String p1, final String p2, final String p3, final Object[] p4, final Throwable p5);
    
    void setResourceName(final String p0);
    
    void severe(final String p0, final String p1, final String p2);
    
    void severe(final String p0, final String p1, final String p2, final Object[] p3);
    
    void severe(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
    
    void trace(final int p0, final String p1, final String p2, final String p3, final Object[] p4, final Throwable p5);
    
    void warning(final String p0, final String p1, final String p2);
    
    void warning(final String p0, final String p1, final String p2, final Object[] p3);
    
    void warning(final String p0, final String p1, final String p2, final Object[] p3, final Throwable p4);
}
