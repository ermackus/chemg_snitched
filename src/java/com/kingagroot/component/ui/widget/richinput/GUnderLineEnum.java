package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GUnderlineSpan;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;

public enum GUnderLineEnum
{
    private static final GUnderLineEnum[] $VALUES;
    
    normal(0, (Class<? extends BaseSpan>)null), 
    underline(1, (Class<? extends BaseSpan>)GUnderlineSpan.class);
    
    Class<? extends BaseSpan> baseSpan;
    int code;
    
    private GUnderLineEnum(final int code, final Class<? extends BaseSpan> baseSpan) {
        this.code = code;
        this.baseSpan = baseSpan;
    }
    
    public static GUnderLineEnum valueOfCode(final int n) {
        for (final GUnderLineEnum gUnderLineEnum : values()) {
            if (gUnderLineEnum.code == n) {
                return gUnderLineEnum;
            }
        }
        return GUnderLineEnum.normal;
    }
    
    public static GUnderLineEnum valueOfSpan(final Class<? extends BaseSpan> clazz) {
        for (final GUnderLineEnum gUnderLineEnum : values()) {
            if (gUnderLineEnum.baseSpan == clazz) {
                return gUnderLineEnum;
            }
        }
        return GUnderLineEnum.normal;
    }
    
    public Class<? extends BaseSpan> getBaseSpan() {
        return this.baseSpan;
    }
    
    public int getCode() {
        return this.code;
    }
}
