package com.goodsrc.library.http;

public enum NetBeanTypeEnum
{
    private static final NetBeanTypeEnum[] $VALUES;
    
    RELOGIN("relogin");
    
    String data;
    
    private NetBeanTypeEnum(final String data) {
        this.data = data;
    }
    
    public String getData() {
        return this.data;
    }
}
