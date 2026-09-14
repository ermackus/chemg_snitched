package com.kingagroot.kingdraw.jpush;

public class JpushModel
{
    private String MessageOperation;
    private String OpenUrl;
    
    public String getMessageOperation() {
        return this.MessageOperation;
    }
    
    public String getOpenUrl() {
        return this.OpenUrl;
    }
    
    public void setMessageOperation(final String messageOperation) {
        this.MessageOperation = messageOperation;
    }
    
    public void setOpenUrl(final String openUrl) {
        this.OpenUrl = openUrl;
    }
}
