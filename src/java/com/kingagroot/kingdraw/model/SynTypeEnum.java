package com.kingagroot.kingdraw.model;

public enum SynTypeEnum
{
    private static final SynTypeEnum[] $VALUES;
    
    \u4e0a\u4f20(1), 
    \u4e0b\u8f7d(2);
    
    int code;
    
    private SynTypeEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
}
