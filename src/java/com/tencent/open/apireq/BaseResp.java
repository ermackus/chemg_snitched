package com.tencent.open.apireq;

public class BaseResp
{
    public static final int CODE_ERROR_PARAMS = -2000;
    public static final int CODE_NOT_LOGIN = -2001;
    public static final int CODE_QQ_LOW_VERSION = -1001;
    public static final int CODE_QQ_NOT_INSTALLED = -1000;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_UNSUPPORTED_BRANCH = -1002;
    private int a;
    private String b;
    
    public BaseResp() {
        this.a = 0;
        this.b = "";
    }
    
    protected String a(final int n) {
        return "Api call failed.";
    }
    
    public int getCode() {
        return this.a;
    }
    
    public String getErrorMsg() {
        return this.b;
    }
    
    public boolean isSuccess() {
        return this.a == 0;
    }
    
    public void setCode(final int a) {
        this.a = a;
        String a2;
        if (a != -2001) {
            if (a != -2000) {
                if (a != 0) {
                    switch (a) {
                        default: {
                            a2 = this.a(a);
                            break;
                        }
                        case -1000: {
                            a2 = "QQ is not installed.";
                            break;
                        }
                        case -1001: {
                            a2 = "QQ version is too low.";
                            break;
                        }
                        case -1002: {
                            a2 = "The QQ branch (e.g. TIM) is not supported";
                            break;
                        }
                    }
                }
                else {
                    a2 = "";
                }
            }
            else {
                a2 = "The given params check failed.";
            }
        }
        else {
            a2 = "Not login.";
        }
        this.setErrorMsg(a2);
    }
    
    public void setErrorMsg(final String b) {
        this.b = b;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BaseResp{mCode=");
        sb.append(this.a);
        sb.append(", mErrorMsg='");
        sb.append(this.b);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
