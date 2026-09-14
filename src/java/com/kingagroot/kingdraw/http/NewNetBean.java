package com.kingagroot.kingdraw.http;

public class NewNetBean<T>
{
    private int code;
    private T data;
    private String message;
    
    public NewNetBean() {
        this.data = null;
        this.message = "";
    }
    
    public int getCode() {
        return this.code;
    }
    
    public T getData() {
        return this.data;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
    
    public void setData(final T data) {
        this.data = data;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
}
