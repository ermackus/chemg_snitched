package com.kingagroot.component.ui.widget.richinput;

public enum GFontFamilyEnum
{
    private static final GFontFamilyEnum[] $VALUES;
    
    Arial("Arial"), 
    Symbol("Symbol"), 
    Times_New_Roman("Times New Roman");
    
    String fontFamily;
    
    private GFontFamilyEnum(final String fontFamily) {
        this.fontFamily = fontFamily;
    }
    
    public static String[] getAllFontFamily() {
        return new String[] { GFontFamilyEnum.Arial.fontFamily, GFontFamilyEnum.Times_New_Roman.fontFamily, GFontFamilyEnum.Symbol.fontFamily };
    }
    
    public static boolean hasValue(final String s) {
        final GFontFamilyEnum[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (values[i].fontFamily.equals((Object)s)) {
                return true;
            }
        }
        return false;
    }
    
    public String getFontFamily() {
        return this.fontFamily;
    }
}
