package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ExceptionHelper
{
    private ExceptionHelper() {
    }
    
    public static MqttException createMqttException(final int n) {
        if (n != 4 && n != 5) {
            return new MqttException(n);
        }
        return new MqttSecurityException(n);
    }
    
    public static MqttException createMqttException(final Throwable t) {
        if (t.getClass().getName().equals((Object)"java.security.GeneralSecurityException")) {
            return new MqttSecurityException(t);
        }
        return new MqttException(t);
    }
    
    public static boolean isClassAvailable(final String className) {
        boolean b;
        try {
            Class.forName(className);
            b = true;
        }
        catch (final ClassNotFoundException ex) {
            b = false;
        }
        return b;
    }
}
