package com.kingagroot.kingdraw.model;

public enum SearchTypeEnum
{
    private static final SearchTypeEnum[] $VALUES;
    
    \u4e91\u7aef\u753b\u5e03\u641c\u7d22(2), 
    \u672c\u5730\u753b\u5e03\u641c\u7d22(1);
    
    int code;
    
    private SearchTypeEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
}
