package com.goodsrc.library.http;

public class CasNetBean<T>
{
    private T data;
    private String info;
    private int status;
    
    public CasNetBean() {
        this.data = null;
        this.info = "";
    }
    
    public T getData() {
        return this.data;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setData(final T data) {
        this.data = data;
    }
    
    public void setInfo(final String info) {
        this.info = info;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
}
