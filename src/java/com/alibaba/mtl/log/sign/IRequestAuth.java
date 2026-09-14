package com.alibaba.mtl.log.sign;

public interface IRequestAuth
{
    String getAppkey();
    
    String getSign(final String p0);
}
