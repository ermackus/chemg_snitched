package com.alibaba.sdk.android.man.network;

import java.util.HashMap;

public class MANNetworkErrorCodeBuilder
{
    private static final int CLIENT_ERROR_4XX = 2001;
    private static final int INTERRUPTED_IO_EXCEPTION = 2004;
    private static final int IO_EXCEPTION = 2006;
    private static final int MALFORMED_URL_EXCEPTION = 2003;
    private static final int SERVER_ERROR_5XX = 2002;
    private static final int SOCKET_TIMEOUT_EXCEPTION = 2005;
    
    private MANNetworkErrorCodeBuilder() {
    }
    
    public static MANNetworkErrorInfo buildCustomErrorCode(final int n) {
        return buildErrorCode(n);
    }
    
    private static MANNetworkErrorInfo buildErrorCode(final int n) {
        final HashMap hashMap = new HashMap();
        hashMap.put((Object)"ErrorCode", (Object)String.valueOf(n));
        return new MANNetworkErrorInfo((HashMap<String, String>)hashMap);
    }
    
    public static MANNetworkErrorInfo buildHttpCodeClientError4XX() {
        return buildErrorCode(2001);
    }
    
    public static MANNetworkErrorInfo buildHttpCodeServerError5XX() {
        return buildErrorCode(2002);
    }
    
    public static MANNetworkErrorInfo buildIOException() {
        return buildErrorCode(2006);
    }
    
    public static MANNetworkErrorInfo buildInterruptedIOException() {
        return buildErrorCode(2004);
    }
    
    public static MANNetworkErrorInfo buildMalformedURLException() {
        return buildErrorCode(2003);
    }
    
    public static MANNetworkErrorInfo buildSocketTimeoutException() {
        return buildErrorCode(2005);
    }
}
