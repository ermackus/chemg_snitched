package org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleCatalog extends MessageCatalog
{
    private ResourceBundle bundle;
    
    public ResourceBundleCatalog() {
        this.bundle = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");
    }
    
    @Override
    protected String getLocalizedMessage(final int n) {
        try {
            return this.bundle.getString(Integer.toString(n));
        }
        catch (final MissingResourceException ex) {
            return "MqttException";
        }
    }
}
