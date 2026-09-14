package com.kingagroot.component.ui.widget.richinput;

public enum GFontStyleEnum
{
    private static final GFontStyleEnum[] $VALUES;
    
    Bold(2, "Bold"), 
    BoldItalic(4, "Bold Italic"), 
    Italic(3, "Italic"), 
    Regular(1, "Regular");
    
    int code;
    String fullName;
    
    private GFontStyleEnum(final int code, final String fullName) {
        this.code = code;
        this.fullName = fullName;
    }
    
    public static String[] getAllFontStyle() {
        return new String[] { GFontStyleEnum.Regular.fullName, GFontStyleEnum.Bold.fullName, GFontStyleEnum.Italic.fullName, GFontStyleEnum.BoldItalic.fullName };
    }
    
    public static GFontStyleEnum valueOfCode(final int n) {
        for (final GFontStyleEnum gFontStyleEnum : values()) {
            if (gFontStyleEnum.code == n) {
                return gFontStyleEnum;
            }
        }
        return GFontStyleEnum.Regular;
    }
    
    public static GFontStyleEnum valueOfStyle(final String s) {
        for (final GFontStyleEnum gFontStyleEnum : values()) {
            if (gFontStyleEnum.fullName.equals((Object)s)) {
                return gFontStyleEnum;
            }
        }
        return GFontStyleEnum.Regular;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public String toString() {
        return this.fullName;
    }
}
