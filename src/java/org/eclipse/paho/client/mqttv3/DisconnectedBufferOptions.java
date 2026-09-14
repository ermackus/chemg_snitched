package org.eclipse.paho.client.mqttv3;

public class DisconnectedBufferOptions
{
    public static final boolean DELETE_OLDEST_MESSAGES_DEFAULT = false;
    public static final boolean DISCONNECTED_BUFFER_ENABLED_DEFAULT = false;
    public static final int DISCONNECTED_BUFFER_SIZE_DEFAULT = 5000;
    public static final boolean PERSIST_DISCONNECTED_BUFFER_DEFAULT = false;
    private boolean bufferEnabled;
    private int bufferSize;
    private boolean deleteOldestMessages;
    private boolean persistBuffer;
    
    public DisconnectedBufferOptions() {
        this.bufferSize = 5000;
        this.bufferEnabled = false;
        this.persistBuffer = false;
        this.deleteOldestMessages = false;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public boolean isBufferEnabled() {
        return this.bufferEnabled;
    }
    
    public boolean isDeleteOldestMessages() {
        return this.deleteOldestMessages;
    }
    
    public boolean isPersistBuffer() {
        return this.persistBuffer;
    }
    
    public void setBufferEnabled(final boolean bufferEnabled) {
        this.bufferEnabled = bufferEnabled;
    }
    
    public void setBufferSize(final int bufferSize) {
        if (bufferSize >= 1) {
            this.bufferSize = bufferSize;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void setDeleteOldestMessages(final boolean deleteOldestMessages) {
        this.deleteOldestMessages = deleteOldestMessages;
    }
    
    public void setPersistBuffer(final boolean persistBuffer) {
        this.persistBuffer = persistBuffer;
    }
}
