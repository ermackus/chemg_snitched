package com.kingagroot.component.ui.widget.richinput;

public class RichConfig
{
    public static final int DEFAULT_FONT_COLOR = -16777216;
    public static final String DEFAULT_FONT_FAMILY;
    public static final int DEFAULT_FONT_SIZE = 10;
    private static final int FONT_SIZE_MAX = 36;
    private static final int FONT_SIZE_MIN = 5;
    public static final float SUBSCALE = 0.7f;
    public static final float sizeOffset = 1.8f;
    
    static {
        DEFAULT_FONT_FAMILY = GFontFamilyEnum.Arial.fontFamily;
    }
    
    public static int getDefaultFontSize() {
        return 10;
    }
    
    public static String[] getFontFamily() {
        return GFontFamilyEnum.getAllFontFamily();
    }
    
    public static int[] getFontSize() {
        final int[] array = new int[32];
        for (int i = 5; i <= 36; ++i) {
            array[i - 5] = i;
        }
        return array;
    }
    
    public static int getFontSizeMax() {
        return 36;
    }
    
    public static int getFontSizeMin() {
        return 5;
    }
    
    public static String[] getFontStyle() {
        return GFontStyleEnum.getAllFontStyle();
    }
}
