package com.alipay.android.phone.mrpc.core;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class HttpUrlHeader implements Serializable
{
    public static final long serialVersionUID = -6098125857367743614L;
    public Map<String, String> headers;
    
    public HttpUrlHeader() {
        this.headers = (Map<String, String>)new HashMap();
    }
    
    public String getHead(final String s) {
        return (String)this.headers.get((Object)s);
    }
    
    public Map<String, String> getHeaders() {
        return this.headers;
    }
    
    public void setHead(final String s, final String s2) {
        this.headers.put((Object)s, (Object)s2);
    }
    
    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }
}
