package com.kingagroot.component.ui.model;

public enum GDocumentTypeEnum
{
    private static final GDocumentTypeEnum[] $VALUES;
    
    ACS96\u683c\u5f0f(2, "ACS Document 1996"), 
    KingDraw\u683c\u5f0f(1, "KingDraw Document"), 
    \u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f(3, "User-defined Document");
    
    int code;
    String fullName;
    
    private GDocumentTypeEnum(final int code, final String fullName) {
        this.code = code;
        this.fullName = fullName;
    }
    
    public static String[] getAllType() {
        final GDocumentTypeEnum[] values = values();
        final int length = values.length;
        final String[] array = new String[length];
        for (int i = 0; i < length; ++i) {
            array[i] = values[i].getFullName();
        }
        return array;
    }
    
    public static GDocumentTypeEnum valueOfCode(final int n) {
        for (final GDocumentTypeEnum gDocumentTypeEnum : values()) {
            if (n == gDocumentTypeEnum.code) {
                return gDocumentTypeEnum;
            }
        }
        return GDocumentTypeEnum.KingDraw\u683c\u5f0f;
    }
    
    public static GDocumentTypeEnum valueOfFullName(final String s) {
        for (final GDocumentTypeEnum gDocumentTypeEnum : values()) {
            if (s.equals((Object)gDocumentTypeEnum.getFullName())) {
                return gDocumentTypeEnum;
            }
        }
        return GDocumentTypeEnum.KingDraw\u683c\u5f0f;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
