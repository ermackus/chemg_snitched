package com.alipay.sdk.util;

public class H5PayResultModel
{
    public String resultCode;
    public String returnUrl;
    
    public String getResultCode() {
        return this.resultCode;
    }
    
    public String getReturnUrl() {
        return this.returnUrl;
    }
    
    public void setResultCode(final String resultCode) {
        this.resultCode = resultCode;
    }
    
    public void setReturnUrl(final String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
