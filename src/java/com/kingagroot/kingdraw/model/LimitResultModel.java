package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "LimitResultModel")
public class LimitResultModel
{
    @Column(name = "data")
    private String data;
    @Column(name = "key")
    private long key;
    @Column(autoGen = false, isId = true, name = "loginState")
    private int loginState;
    
    public String getData() {
        return this.data;
    }
    
    public long getKey() {
        return this.key;
    }
    
    public int getLoginState() {
        return this.loginState;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public void setKey(final long key) {
        this.key = key;
    }
    
    public void setLoginState(final int loginState) {
        this.loginState = loginState;
    }
}
