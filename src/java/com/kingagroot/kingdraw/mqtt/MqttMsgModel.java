package com.kingagroot.kingdraw.mqtt;

public class MqttMsgModel
{
    private LoginDataModel loginDataModel;
    private int type;
    
    public LoginDataModel getLoginDataModel() {
        return this.loginDataModel;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setLoginDataModel(final LoginDataModel loginDataModel) {
        this.loginDataModel = loginDataModel;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
}
