package com.kingagroot.kingdraw.core.data.http;

import java.util.ArrayList;
import androidx.core.util.Pair;
import java.util.List;

public class RequestParams
{
    private String JsonContent;
    private List<Pair<String, String>> bodyParams;
    private String buildUri;
    private int connectTimeout;
    private int readTimeout;
    private String requestMethod;
    
    public RequestParams() {
        this.connectTimeout = 60000;
        this.readTimeout = 60000;
        this.requestMethod = "POST";
        this.bodyParams = (List<Pair<String, String>>)new ArrayList();
        this.JsonContent = "";
    }
    
    public void addBodyParam(final String s, final String s2) {
        this.bodyParams.add((Object)new Pair((Object)s, (Object)s2));
    }
    
    public List<Pair<String, String>> getBodyParams() {
        return this.bodyParams;
    }
    
    public String getBuildUri() {
        return this.buildUri;
    }
    
    public int getConnectTimeout() {
        return this.connectTimeout;
    }
    
    public String getJsonContent() {
        return this.JsonContent;
    }
    
    public int getReadTimeout() {
        return this.readTimeout;
    }
    
    public String getRequestMethod() {
        return this.requestMethod;
    }
    
    public void setBuildUri(final String buildUri) {
        this.buildUri = buildUri;
    }
    
    public void setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    
    public void setJsonContent(final String jsonContent) {
        this.JsonContent = jsonContent;
    }
    
    public void setReadTimeout(final int readTimeout) {
        this.readTimeout = readTimeout;
    }
    
    public void setRequestMethod(final String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
