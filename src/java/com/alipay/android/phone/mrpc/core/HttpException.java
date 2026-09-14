package com.alipay.android.phone.mrpc.core;

public class HttpException extends Exception
{
    public static final int NETWORK_AUTH_ERROR = 8;
    public static final int NETWORK_CONNECTION_EXCEPTION = 3;
    public static final int NETWORK_DNS_ERROR = 9;
    public static final int NETWORK_IO_EXCEPTION = 6;
    public static final int NETWORK_SCHEDULE_ERROR = 7;
    public static final int NETWORK_SERVER_EXCEPTION = 5;
    public static final int NETWORK_SOCKET_EXCEPTION = 4;
    public static final int NETWORK_SSL_EXCEPTION = 2;
    public static final int NETWORK_UNAVAILABLE = 1;
    public static final int NETWORK_UNKNOWN_ERROR = 0;
    public static final long serialVersionUID = -6320569206365033676L;
    public int mCode;
    public String mMsg;
    
    public HttpException(final Integer n, final String mMsg) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Http Transport error");
        if (n != null) {
            sb.append("[");
            sb.append((Object)n);
            sb.append("]");
        }
        sb.append(" : ");
        if (mMsg != null) {
            sb.append(mMsg);
        }
        super(sb.toString());
        this.mCode = n;
        this.mMsg = mMsg;
    }
    
    public HttpException(final String mMsg) {
        super(mMsg);
        this.mCode = 0;
        this.mMsg = mMsg;
    }
    
    public int getCode() {
        return this.mCode;
    }
    
    public String getMsg() {
        return this.mMsg;
    }
}
