package org.eclipse.paho.android.service;

import android.os.Binder;

class MqttServiceBinder extends Binder
{
    private String activityToken;
    private MqttService mqttService;
    
    MqttServiceBinder(final MqttService mqttService) {
        this.mqttService = mqttService;
    }
    
    public String getActivityToken() {
        return this.activityToken;
    }
    
    public MqttService getService() {
        return this.mqttService;
    }
    
    void setActivityToken(final String activityToken) {
        this.activityToken = activityToken;
    }
}
