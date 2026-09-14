package org.json.alipay;

public class JSONException extends Exception
{
    public Throwable cause;
    
    public JSONException(final String s) {
        super(s);
    }
    
    public JSONException(final Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
}
