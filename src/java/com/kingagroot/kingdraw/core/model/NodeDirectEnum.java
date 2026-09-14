package com.kingagroot.kingdraw.core.model;

public enum NodeDirectEnum
{
    private static final NodeDirectEnum[] $VALUES;
    
    AUTO(0), 
    USER_BOTTOM(4), 
    USER_LEFT(1), 
    USER_RIGHT(3), 
    USER_TOP(2);
    
    private int code;
    
    private NodeDirectEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
}
