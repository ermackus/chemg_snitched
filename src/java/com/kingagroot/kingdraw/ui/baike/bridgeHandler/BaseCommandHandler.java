package com.kingagroot.kingdraw.ui.baike.bridgeHandler;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.BridgeWebView;

public abstract class BaseCommandHandler
{
    protected BridgeWebView bridgeWebView;
    
    public BaseCommandHandler(final BridgeWebView bridgeWebView) {
        this.bridgeWebView = bridgeWebView;
    }
    
    public abstract void command();
    
    protected void command(final String s) {
        final BridgeWebView bridgeWebView = this.bridgeWebView;
        if (bridgeWebView != null) {
            bridgeWebView.send(s);
        }
    }
    
    protected void command(final String s, final CallBackFunction callBackFunction) {
        final BridgeWebView bridgeWebView = this.bridgeWebView;
        if (bridgeWebView != null) {
            bridgeWebView.send(s, callBackFunction);
        }
    }
}
