package com.ut.mini.core.sign;

public interface IUTRequestAuthentication
{
    String getAppkey();
    
    String getSign(final String p0);
}
