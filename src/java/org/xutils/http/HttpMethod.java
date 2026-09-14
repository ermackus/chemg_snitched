package org.xutils.http;

public enum HttpMethod
{
    private static final HttpMethod[] $VALUES;
    
    CONNECT("CONNECT"), 
    COPY("COPY"), 
    DELETE("DELETE"), 
    GET("GET"), 
    HEAD("HEAD"), 
    MOVE("MOVE"), 
    OPTIONS("OPTIONS"), 
    PATCH("PATCH"), 
    POST("POST"), 
    PUT("PUT"), 
    TRACE("TRACE");
    
    private final String value;
    
    private HttpMethod(final String value) {
        this.value = value;
    }
    
    public static boolean permitsCache(final HttpMethod httpMethod) {
        return httpMethod == HttpMethod.GET || httpMethod == HttpMethod.POST;
    }
    
    public static boolean permitsRequestBody(final HttpMethod httpMethod) {
        return httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT || httpMethod == HttpMethod.PATCH || httpMethod == HttpMethod.DELETE;
    }
    
    public static boolean permitsRetry(final HttpMethod httpMethod) {
        return httpMethod == HttpMethod.GET;
    }
    
    public String toString() {
        return this.value;
    }
}
