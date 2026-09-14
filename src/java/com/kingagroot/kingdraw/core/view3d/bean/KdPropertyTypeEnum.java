package com.kingagroot.kingdraw.core.view3d.bean;

public enum KdPropertyTypeEnum
{
    private static final KdPropertyTypeEnum[] $VALUES;
    
    CHARGE(5), 
    GLONEPAIR(1), 
    GRADICAL(2), 
    GRADICALANION(4), 
    GRADICALCATION(3);
    
    public int code;
    
    private KdPropertyTypeEnum(final int code) {
        this.code = code;
    }
}
