package com.github.lzyzsd.jsbridge;

public class DefaultHandler implements BridgeHandler
{
    String TAG;
    
    public DefaultHandler() {
        this.TAG = "DefaultHandler";
    }
    
    @Override
    public void handler(final String s, final CallBackFunction callBackFunction) {
        if (callBackFunction != null) {
            callBackFunction.onCallBack("DefaultHandler response data");
        }
    }
}
