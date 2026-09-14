package com.kingagroot.kingdraw.core.graphics.tiff;

public enum ResolutionUnit
{
    private static final ResolutionUnit[] $VALUES;
    
    CENTIMETER(3), 
    INCH(2), 
    NONE(1);
    
    final int ordinal;
    
    private ResolutionUnit(final int ordinal) {
        this.ordinal = ordinal;
    }
}
