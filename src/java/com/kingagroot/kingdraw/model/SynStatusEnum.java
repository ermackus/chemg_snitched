package com.kingagroot.kingdraw.model;

public enum SynStatusEnum
{
    private static final SynStatusEnum[] $VALUES;
    
    \u4f20\u8f93\u4e2d(2), 
    \u5931\u8d25(4), 
    \u6210\u529f(3), 
    \u7b49\u5f85\u4e2d(1);
    
    int code;
    
    private SynStatusEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
}
