package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.prefs.BackingStoreException;
import java.util.prefs.AbstractPreferences;

public class Base64
{
    private static final Base64Encoder encoder;
    private static final Base64 instance;
    
    static {
        instance = new Base64();
        final Base64 instance2 = Base64.instance;
        instance2.getClass();
        encoder = instance2.new Base64Encoder();
    }
    
    public static String encode(final String s) {
        Base64.encoder.putByteArray("akey", s.getBytes());
        return Base64.encoder.getBase64String();
    }
    
    public static String encodeBytes(final byte[] array) {
        Base64.encoder.putByteArray("aKey", array);
        return Base64.encoder.getBase64String();
    }
    
    public class Base64Encoder extends AbstractPreferences
    {
        private String base64String;
        final Base64 this$0;
        
        public Base64Encoder(final Base64 this$0) {
            this.this$0 = this$0;
            super((AbstractPreferences)null, "");
            this.base64String = null;
        }
        
        protected AbstractPreferences childSpi(final String s) {
            return null;
        }
        
        protected String[] childrenNamesSpi() throws BackingStoreException {
            return null;
        }
        
        protected void flushSpi() throws BackingStoreException {
        }
        
        public String getBase64String() {
            return this.base64String;
        }
        
        protected String getSpi(final String s) {
            return null;
        }
        
        protected String[] keysSpi() throws BackingStoreException {
            return null;
        }
        
        protected void putSpi(final String s, final String base64String) {
            this.base64String = base64String;
        }
        
        protected void removeNodeSpi() throws BackingStoreException {
        }
        
        protected void removeSpi(final String s) {
        }
        
        protected void syncSpi() throws BackingStoreException {
        }
    }
}
