package com.kingagroot.kingdraw.core.tool;

public enum AlignTypeEnum
{
    private static final AlignTypeEnum[] $VALUES;
    
    BOTTOM("3"), 
    CENTER_VERTICAL("4"), 
    CENTRE_HORIZONTAL("5"), 
    LAYER_BOTTOM("7"), 
    LAYER_TOP("6"), 
    LEFT("0"), 
    LEFT_AND_RIGHT("8"), 
    RIGHT("2"), 
    TOP("1"), 
    TOP_AND_BOTTOM("9");
    
    public String alignType;
    
    private AlignTypeEnum(final String alignType) {
        this.alignType = alignType;
    }
    
    public static AlignTypeEnum valueOfTypee(final String s) {
        for (final AlignTypeEnum alignTypeEnum : values()) {
            if (alignTypeEnum.alignType.equals((Object)s)) {
                return alignTypeEnum;
            }
        }
        return AlignTypeEnum.LEFT;
    }
}
