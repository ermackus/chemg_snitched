package com.kingagroot.kingdraw.config;

public enum Release
{
    private static final Release[] $VALUES;
    
    DEBUG("\u5f00\u53d1\u7248"), 
    EARLY("\u5c1d\u9c9c\u7248"), 
    HOME("\u5c45\u5bb6\u9694\u79bb"), 
    STANDARD("\u6b63\u5f0f\u7248"), 
    TEST("\u5c01\u6d4b\u7248");
    
    public String name;
    
    private Release(final String name) {
        this.name = name;
    }
}
