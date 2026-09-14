package com.kingagroot.kingdraw.model;

public class MessageCountModel
{
    private String messageTypeName;
    private int udidUnreadCount;
    private int uuidUnreadCount;
    
    public String getMessageTypeName() {
        return this.messageTypeName;
    }
    
    public int getUdidUnreadCount() {
        return this.udidUnreadCount;
    }
    
    public int getUuidUnreadCount() {
        return this.uuidUnreadCount;
    }
    
    public void setMessageTypeName(final String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }
    
    public void setUdidUnreadCount(final int udidUnreadCount) {
        this.udidUnreadCount = udidUnreadCount;
    }
    
    public void setUuidUnreadCount(final int uuidUnreadCount) {
        this.uuidUnreadCount = uuidUnreadCount;
    }
}
