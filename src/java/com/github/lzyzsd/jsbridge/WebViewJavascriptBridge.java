package com.github.lzyzsd.jsbridge;

public interface WebViewJavascriptBridge
{
    void send(final String p0);
    
    void send(final String p0, final CallBackFunction p1);
}
