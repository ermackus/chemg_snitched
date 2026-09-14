package com.otaliastudios.cameraview;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Set;

public final class CameraLogger
{
    public static final int LEVEL_ERROR = 3;
    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 2;
    static String lastMessage;
    static String lastTag;
    static Logger sAndroidLogger;
    private static int sLevel;
    private static Set<Logger> sLoggers;
    private String mTag;
    
    static {
        CameraLogger.sLoggers = (Set<Logger>)new CopyOnWriteArraySet();
        CameraLogger.sAndroidLogger = (Logger)new CameraLogger$1();
        setLogLevel(3);
        CameraLogger.sLoggers.add((Object)CameraLogger.sAndroidLogger);
    }
    
    private CameraLogger(final String mTag) {
        this.mTag = mTag;
    }
    
    public static CameraLogger create(final String s) {
        return new CameraLogger(s);
    }
    
    private String log(final int n, final Object... array) {
        final boolean should = this.should(n);
        Throwable t = null;
        if (!should) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (final Object o : array) {
            if (o instanceof Throwable) {
                t = (Throwable)o;
            }
            sb.append(String.valueOf(o));
            sb.append(" ");
        }
        final String trim = sb.toString().trim();
        final Iterator iterator = CameraLogger.sLoggers.iterator();
        while (iterator.hasNext()) {
            ((Logger)iterator.next()).log(n, this.mTag, trim, t);
        }
        CameraLogger.lastMessage = trim;
        CameraLogger.lastTag = this.mTag;
        return trim;
    }
    
    public static void registerLogger(final Logger logger) {
        CameraLogger.sLoggers.add((Object)logger);
    }
    
    public static void setLogLevel(final int sLevel) {
        CameraLogger.sLevel = sLevel;
    }
    
    private boolean should(final int n) {
        return CameraLogger.sLevel <= n && CameraLogger.sLoggers.size() > 0;
    }
    
    public static void unregisterLogger(final Logger logger) {
        CameraLogger.sLoggers.remove((Object)logger);
    }
    
    public String e(final Object... array) {
        return this.log(3, array);
    }
    
    public String i(final Object... array) {
        return this.log(1, array);
    }
    
    public String v(final Object... array) {
        return this.log(0, array);
    }
    
    public String w(final Object... array) {
        return this.log(2, array);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }
    
    public interface Logger
    {
        void log(final int p0, final String p1, final String p2, final Throwable p3);
    }
}
