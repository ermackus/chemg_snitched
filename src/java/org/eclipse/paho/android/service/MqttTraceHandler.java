package org.eclipse.paho.android.service;

public interface MqttTraceHandler
{
    void traceDebug(final String p0, final String p1);
    
    void traceError(final String p0, final String p1);
    
    void traceException(final String p0, final String p1, final Exception p2);
}
