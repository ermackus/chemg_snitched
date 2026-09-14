package com.kingagroot.kingdraw.core.graphics.tiff;

public enum PlanarConfig
{
    private static final PlanarConfig[] $VALUES;
    
    CONTIG(1), 
    SEPARATE(2);
    
    final int ordinal;
    
    private PlanarConfig(final int ordinal) {
        this.ordinal = ordinal;
    }
}
