package com.tencent.mm.opensdk.diffdev;

public enum OAuthErrCode
{
    private static final OAuthErrCode[] $VALUES;
    
    WechatAuth_Err_Auth_Stopped(-6), 
    WechatAuth_Err_Cancel(-4), 
    WechatAuth_Err_JsonDecodeErr(-3), 
    WechatAuth_Err_NetworkErr(-2), 
    WechatAuth_Err_NormalErr(-1), 
    WechatAuth_Err_OK(0), 
    WechatAuth_Err_Timeout(-5);
    
    private int code;
    
    private OAuthErrCode(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("OAuthErrCode:");
        sb.append(this.code);
        return sb.toString();
    }
}
