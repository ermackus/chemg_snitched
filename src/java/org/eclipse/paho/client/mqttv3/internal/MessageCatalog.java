package org.eclipse.paho.client.mqttv3.internal;

public abstract class MessageCatalog
{
    private static MessageCatalog INSTANCE;
    
    public static final String getMessage(final int n) {
        if (MessageCatalog.INSTANCE == null) {
            if (ExceptionHelper.isClassAvailable("java.util.ResourceBundle")) {
                try {
                    MessageCatalog.INSTANCE = (MessageCatalog)Class.forName("org.eclipse.paho.client.mqttv3.internal.ResourceBundleCatalog").newInstance();
                    return MessageCatalog.INSTANCE.getLocalizedMessage(n);
                }
                catch (final Exception ex) {
                    return "";
                }
            }
            if (ExceptionHelper.isClassAvailable("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog")) {
                try {
                    MessageCatalog.INSTANCE = (MessageCatalog)Class.forName("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog").newInstance();
                }
                catch (final Exception ex2) {
                    return "";
                }
            }
        }
        return MessageCatalog.INSTANCE.getLocalizedMessage(n);
    }
    
    protected abstract String getLocalizedMessage(final int p0);
}
