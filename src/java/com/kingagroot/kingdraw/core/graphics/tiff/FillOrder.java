package com.kingagroot.kingdraw.core.graphics.tiff;

public enum FillOrder
{
    private static final FillOrder[] $VALUES;
    
    LSB2MSB(2), 
    MSB2LSB(1);
    
    final int ordinal;
    
    private FillOrder(final int ordinal) {
        this.ordinal = ordinal;
    }
}
