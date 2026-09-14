package org.eclipse.paho.client.mqttv3.logging;

import java.util.logging.LogRecord;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.MissingResourceException;
import java.util.logging.Handler;
import java.util.logging.MemoryHandler;
import java.util.ResourceBundle;

public class JSR47Logger implements Logger
{
    private String catalogID;
    private java.util.logging.Logger julLogger;
    private ResourceBundle logMessageCatalog;
    private String loggerName;
    private String resourceName;
    private ResourceBundle traceMessageCatalog;
    
    public JSR47Logger() {
        this.julLogger = null;
        this.logMessageCatalog = null;
        this.traceMessageCatalog = null;
        this.catalogID = null;
        this.resourceName = null;
        this.loggerName = null;
    }
    
    protected static void dumpMemoryTrace47(final java.util.logging.Logger logger) {
        if (logger != null) {
            for (final Handler handler : logger.getHandlers()) {
                if (handler instanceof MemoryHandler) {
                    synchronized (handler) {
                        ((MemoryHandler)handler).push();
                        return;
                    }
                }
            }
            dumpMemoryTrace47(logger.getParent());
        }
    }
    
    private String getResourceMessage(final ResourceBundle resourceBundle, String string) {
        try {
            string = resourceBundle.getString(string);
            return string;
        }
        catch (final MissingResourceException ex) {
            return string;
        }
    }
    
    private void logToJsr47(final Level level, final String sourceClassName, final String sourceMethodName, String format, final ResourceBundle resourceBundle, final String s, final Object[] array, final Throwable thrown) {
        format = s;
        if (!s.contains((CharSequence)"=====")) {
            format = MessageFormat.format(this.getResourceMessage(resourceBundle, s), array);
        }
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.resourceName));
        sb.append(": ");
        sb.append(format);
        final LogRecord logRecord = new LogRecord(level, sb.toString());
        logRecord.setSourceClassName(sourceClassName);
        logRecord.setSourceMethodName(sourceMethodName);
        logRecord.setLoggerName(this.loggerName);
        if (thrown != null) {
            logRecord.setThrown(thrown);
        }
        this.julLogger.log(logRecord);
    }
    
    private Level mapJULLevel(final int n) {
        Level level = null;
        switch (n) {
            default: {
                level = null;
                break;
            }
            case 7: {
                level = Level.FINEST;
                break;
            }
            case 6: {
                level = Level.FINER;
                break;
            }
            case 5: {
                level = Level.FINE;
                break;
            }
            case 4: {
                level = Level.CONFIG;
                break;
            }
            case 3: {
                level = Level.INFO;
                break;
            }
            case 2: {
                level = Level.WARNING;
                break;
            }
            case 1: {
                level = Level.SEVERE;
                break;
            }
        }
        return level;
    }
    
    @Override
    public void config(final String s, final String s2, final String s3) {
        this.log(4, s, s2, s3, null, null);
    }
    
    @Override
    public void config(final String s, final String s2, final String s3, final Object[] array) {
        this.log(4, s, s2, s3, array, null);
    }
    
    @Override
    public void config(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.log(4, s, s2, s3, array, t);
    }
    
    @Override
    public void dumpTrace() {
        dumpMemoryTrace47(this.julLogger);
    }
    
    @Override
    public void fine(final String s, final String s2, final String s3) {
        this.trace(5, s, s2, s3, null, null);
    }
    
    @Override
    public void fine(final String s, final String s2, final String s3, final Object[] array) {
        this.trace(5, s, s2, s3, array, null);
    }
    
    @Override
    public void fine(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.trace(5, s, s2, s3, array, t);
    }
    
    @Override
    public void finer(final String s, final String s2, final String s3) {
        this.trace(6, s, s2, s3, null, null);
    }
    
    @Override
    public void finer(final String s, final String s2, final String s3, final Object[] array) {
        this.trace(6, s, s2, s3, array, null);
    }
    
    @Override
    public void finer(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.trace(6, s, s2, s3, array, t);
    }
    
    @Override
    public void finest(final String s, final String s2, final String s3) {
        this.trace(7, s, s2, s3, null, null);
    }
    
    @Override
    public void finest(final String s, final String s2, final String s3, final Object[] array) {
        this.trace(7, s, s2, s3, array, null);
    }
    
    @Override
    public void finest(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.trace(7, s, s2, s3, array, t);
    }
    
    @Override
    public String formatMessage(String string, final Object[] array) {
        try {
            string = this.logMessageCatalog.getString(string);
            return string;
        }
        catch (final MissingResourceException ex) {
            return string;
        }
    }
    
    @Override
    public void info(final String s, final String s2, final String s3) {
        this.log(3, s, s2, s3, null, null);
    }
    
    @Override
    public void info(final String s, final String s2, final String s3, final Object[] array) {
        this.log(3, s, s2, s3, array, null);
    }
    
    @Override
    public void info(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.log(3, s, s2, s3, array, t);
    }
    
    @Override
    public void initialise(final ResourceBundle resourceBundle, final String loggerName, final String resourceName) {
        this.traceMessageCatalog = this.logMessageCatalog;
        this.resourceName = resourceName;
        this.loggerName = loggerName;
        this.julLogger = java.util.logging.Logger.getLogger(loggerName);
        this.logMessageCatalog = resourceBundle;
        this.traceMessageCatalog = resourceBundle;
        this.catalogID = resourceBundle.getString("0");
    }
    
    @Override
    public boolean isLoggable(final int n) {
        return this.julLogger.isLoggable(this.mapJULLevel(n));
    }
    
    @Override
    public void log(final int n, final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        final Level mapJULLevel = this.mapJULLevel(n);
        if (this.julLogger.isLoggable(mapJULLevel)) {
            this.logToJsr47(mapJULLevel, s, s2, this.catalogID, this.logMessageCatalog, s3, array, t);
        }
    }
    
    @Override
    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }
    
    @Override
    public void severe(final String s, final String s2, final String s3) {
        this.log(1, s, s2, s3, null, null);
    }
    
    @Override
    public void severe(final String s, final String s2, final String s3, final Object[] array) {
        this.log(1, s, s2, s3, array, null);
    }
    
    @Override
    public void severe(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.log(1, s, s2, s3, array, t);
    }
    
    @Override
    public void trace(final int n, final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        final Level mapJULLevel = this.mapJULLevel(n);
        if (this.julLogger.isLoggable(mapJULLevel)) {
            this.logToJsr47(mapJULLevel, s, s2, this.catalogID, this.traceMessageCatalog, s3, array, t);
        }
    }
    
    @Override
    public void warning(final String s, final String s2, final String s3) {
        this.log(2, s, s2, s3, null, null);
    }
    
    @Override
    public void warning(final String s, final String s2, final String s3, final Object[] array) {
        this.log(2, s, s2, s3, array, null);
    }
    
    @Override
    public void warning(final String s, final String s2, final String s3, final Object[] array, final Throwable t) {
        this.log(2, s, s2, s3, array, t);
    }
}
