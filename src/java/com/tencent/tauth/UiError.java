package com.tencent.tauth;

public class UiError
{
    public int errorCode;
    public String errorDetail;
    public String errorMessage;
    
    public UiError(final int errorCode, final String errorMessage, final String errorDetail) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("errorCode: ");
        sb.append(this.errorCode);
        sb.append(", errorMsg: ");
        sb.append(this.errorMessage);
        sb.append(", errorDetail: ");
        sb.append(this.errorDetail);
        return sb.toString();
    }
}
