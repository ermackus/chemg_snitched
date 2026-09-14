package com.kingagroot.kingdraw.mqtt;

public class LoginDataModel
{
    private String clientId;
    private String platform;
    
    public String getClientId() {
        return this.clientId;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
}
