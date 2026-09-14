package org.eclipse.paho.client.mqttv3.internal;

public class SystemHighResolutionTimer implements HighResolutionTimer
{
    @Override
    public long nanoTime() {
        return System.nanoTime();
    }
}
