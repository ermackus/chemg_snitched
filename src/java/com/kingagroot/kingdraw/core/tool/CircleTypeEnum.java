package com.kingagroot.kingdraw.core.tool;

public enum CircleTypeEnum
{
    private static final CircleTypeEnum[] $VALUES;
    
    FLOW_CIRCLE("1"), 
    FLOW_CIRCLE_3D("4"), 
    FLOW_CIRCLE_DASH("2"), 
    FLOW_CIRCLE_SHADOW("5"), 
    FLOW_CIRCLE_SOLID("3"), 
    FLOW_ELLIPSE("6"), 
    FLOW_ELLIPSE_3D("9"), 
    FLOW_ELLIPSE_DASH("7"), 
    FLOW_ELLIPSE_SHADOW("10"), 
    FLOW_ELLIPSE_SOLID("8");
    
    public String circleType;
    
    private CircleTypeEnum(final String circleType) {
        this.circleType = circleType;
    }
    
    public static CircleTypeEnum valueOfType(final String s) {
        for (final CircleTypeEnum circleTypeEnum : values()) {
            if (circleTypeEnum.circleType.equals((Object)s)) {
                return circleTypeEnum;
            }
        }
        return CircleTypeEnum.FLOW_CIRCLE;
    }
}
