package org.xutils.ex;

import android.text.TextUtils;

public class HttpException extends BaseException
{
    private static final long serialVersionUID = 1L;
    private int code;
    private String customMessage;
    private String errorCode;
    private String result;
    
    public HttpException(final int code, final String s) {
        super(s);
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getErrorCode() {
        String s;
        if ((s = this.errorCode) == null) {
            s = String.valueOf(this.code);
        }
        return s;
    }
    
    public String getMessage() {
        if (!TextUtils.isEmpty((CharSequence)this.customMessage)) {
            return this.customMessage;
        }
        return super.getMessage();
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setMessage(final String customMessage) {
        this.customMessage = customMessage;
    }
    
    public void setResult(final String result) {
        this.result = result;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("errorCode: ");
        sb.append(this.getErrorCode());
        sb.append(", msg: ");
        sb.append(this.getMessage());
        sb.append(", result: ");
        sb.append(this.result);
        return sb.toString();
    }
}
