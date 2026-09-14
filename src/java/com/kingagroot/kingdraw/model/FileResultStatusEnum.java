package com.kingagroot.kingdraw.model;

public enum FileResultStatusEnum
{
    private static final FileResultStatusEnum[] $VALUES;
    
    molv2000\u8d85\u8fc7\u9650\u5236(2), 
    \u5931\u8d25(-1), 
    \u6210\u529f(1);
    
    public int code;
    
    private FileResultStatusEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
}
