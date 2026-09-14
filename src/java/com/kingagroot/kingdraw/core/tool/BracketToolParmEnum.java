package com.kingagroot.kingdraw.core.tool;

public enum BracketToolParmEnum
{
    private static final BracketToolParmEnum[] $VALUES;
    
    G_BRACKET_CURLYBRACES("1"), 
    G_BRACKET_CURVE("3"), 
    G_BRACKET_RECT("2"), 
    G_BRACKET_TEXT("4");
    
    public String bracketTypeParm;
    
    private BracketToolParmEnum(final String bracketTypeParm) {
        this.bracketTypeParm = bracketTypeParm;
    }
}
