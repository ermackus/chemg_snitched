package com.kingagroot.kingdraw.core.tool;

@Deprecated
public enum FlowToolParmEnum
{
    private static final FlowToolParmEnum[] $VALUES;
    
    G_FLOW_3DCIRCLE("3"), 
    G_FLOW_3DELLIPSE("8"), 
    G_FLOW_3DRECT("18"), 
    G_FLOW_3DROUNDEDRECT("13"), 
    G_FLOW_ARC120("29"), 
    G_FLOW_ARC180("27"), 
    G_FLOW_ARC270("25"), 
    G_FLOW_ARC90("31"), 
    G_FLOW_ARCLINE("23"), 
    G_FLOW_BLODLINE("24"), 
    G_FLOW_CIRCLE("1"), 
    G_FLOW_DASHARC120("30"), 
    G_FLOW_DASHARC180("28"), 
    G_FLOW_DASHARC270("26"), 
    G_FLOW_DASHARC90("32"), 
    G_FLOW_DASHLINE("22"), 
    G_FLOW_DSAHCIRCLE("2"), 
    G_FLOW_DSAHELLIPSE("7"), 
    G_FLOW_DSAHRECT("17"), 
    G_FLOW_DSAHROUNDEDRECT("12"), 
    G_FLOW_ELLIPSE("6"), 
    G_FLOW_LINE("21"), 
    G_FLOW_RECT("16"), 
    G_FLOW_ROUNDEDRECT("11"), 
    G_FLOW_SHADOWCIRCLE("5"), 
    G_FLOW_SHADOWELLIPSE("10"), 
    G_FLOW_SHADOWRECT("20"), 
    G_FLOW_SHADOWROUNDEDRECT("15"), 
    G_FLOW_SOLIDCIRCLE("4"), 
    G_FLOW_SOLIDELLIPSE("9"), 
    G_FLOW_SOLIDRECT("19"), 
    G_FLOW_SOLIDROUNDEDRECT("14");
    
    public String flowTypeParm;
    
    private FlowToolParmEnum(final String flowTypeParm) {
        this.flowTypeParm = flowTypeParm;
    }
}
