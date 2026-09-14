package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public interface NetworkModule
{
    InputStream getInputStream() throws IOException;
    
    OutputStream getOutputStream() throws IOException;
    
    String getServerURI();
    
    void start() throws IOException, MqttException;
    
    void stop() throws IOException;
}
