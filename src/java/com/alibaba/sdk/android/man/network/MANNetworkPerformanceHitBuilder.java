package com.alibaba.sdk.android.man.network;

import java.util.HashMap;
import java.util.Map;

public class MANNetworkPerformanceHitBuilder
{
    private static final String TAG = "MAN_MANNetworkPerformanceHitBuilder";
    private NetworkEvent networkEvent;
    private String requestHost;
    private String requestMethod;
    private Map<String, String> requestProperties;
    
    private MANNetworkPerformanceHitBuilder() {
        this.networkEvent = new NetworkEvent();
        this.requestProperties = (Map<String, String>)new HashMap();
    }
    
    public MANNetworkPerformanceHitBuilder(final String requestHost, final String s) {
        this.networkEvent = new NetworkEvent();
        this.requestProperties = (Map<String, String>)new HashMap();
        this.requestHost = requestHost;
        if (s != null && (s.equalsIgnoreCase("GET") || s.equalsIgnoreCase("POST"))) {
            this.requestMethod = s.toUpperCase();
        }
        else {
            this.requestMethod = "GET";
        }
    }
    
    public NetworkEvent build() {
        this.requestProperties.put((Object)"Host", (Object)this.requestHost);
        this.requestProperties.put((Object)"Method", (Object)this.requestMethod);
        this.networkEvent.addMANEventProperty(this.requestProperties);
        return this.networkEvent;
    }
    
    public MANNetworkPerformanceHitBuilder hitConnectFinished() {
        this.networkEvent.connectionEnd();
        return this;
    }
    
    public MANNetworkPerformanceHitBuilder hitRecievedFirstByte() {
        this.networkEvent.firstByteEnd();
        return this;
    }
    
    public MANNetworkPerformanceHitBuilder hitRequestEndWithError(final MANNetworkErrorInfo manNetworkErrorInfo) {
        this.networkEvent.requestEndWithError((Map<String, String>)manNetworkErrorInfo.getProperties());
        return this;
    }
    
    public MANNetworkPerformanceHitBuilder hitRequestEndWithLoadBytes(final long n) {
        this.networkEvent.requestEndNormally(n);
        return this;
    }
    
    public MANNetworkPerformanceHitBuilder hitRequestStart() {
        this.networkEvent.requestStart();
        return this;
    }
    
    public MANNetworkPerformanceHitBuilder withExtraInfo(final String s, final String s2) {
        if (s != null && s2 != null) {
            this.requestProperties.put((Object)s, (Object)s2);
        }
        return this;
    }
}
