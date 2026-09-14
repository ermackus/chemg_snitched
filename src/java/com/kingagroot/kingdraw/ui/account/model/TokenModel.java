package com.kingagroot.kingdraw.ui.account.model;

public class TokenModel
{
    private int accountResume;
    private String token;
    
    public int getAccountResume() {
        return this.accountResume;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public void setAccountResume(final int accountResume) {
        this.accountResume = accountResume;
    }
    
    public void setToken(final String token) {
        this.token = token;
    }
}
