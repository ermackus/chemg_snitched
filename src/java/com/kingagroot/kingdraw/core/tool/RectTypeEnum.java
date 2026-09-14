package com.kingagroot.kingdraw.core.tool;

public enum RectTypeEnum
{
    private static final RectTypeEnum[] $VALUES;
    
    FLOW_RECT("1"), 
    FLOW_RECT_3D("4"), 
    FLOW_RECT_DASH("2"), 
    FLOW_RECT_ROUNDED("6"), 
    FLOW_RECT_ROUNDED_3D("9"), 
    FLOW_RECT_ROUNDED_DASH("7"), 
    FLOW_RECT_ROUNDED_SHADOW("10"), 
    FLOW_RECT_ROUNDED_SOLID("8"), 
    FLOW_RECT_SHADOW("5"), 
    FLOW_RECT_SOLID("3");
    
    public String rectType;
    
    private RectTypeEnum(final String rectType) {
        this.rectType = rectType;
    }
    
    public static RectTypeEnum valueOfType(final String s) {
        for (final RectTypeEnum rectTypeEnum : values()) {
            if (rectTypeEnum.rectType.equals((Object)s)) {
                return rectTypeEnum;
            }
        }
        return RectTypeEnum.FLOW_RECT;
    }
}
