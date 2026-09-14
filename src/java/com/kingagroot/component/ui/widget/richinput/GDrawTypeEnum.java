package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GChemSubSpan;
import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;

public enum GDrawTypeEnum
{
    private static final GDrawTypeEnum[] $VALUES;
    
    chem(3, (Class<? extends BaseSpan>)GChemStyleSpan.class), 
    chemsub(4, (Class<? extends BaseSpan>)GChemSubSpan.class), 
    normal(0, (Class<? extends BaseSpan>)null), 
    sub(1, (Class<? extends BaseSpan>)GSubscriptSpan.class), 
    sup(2, (Class<? extends BaseSpan>)GSuperscriptSpan.class);
    
    Class<? extends BaseSpan> baseSpan;
    int code;
    
    private GDrawTypeEnum(final int code, final Class<? extends BaseSpan> baseSpan) {
        this.code = code;
        this.baseSpan = baseSpan;
    }
    
    public static GDrawTypeEnum valueOfCode(final int n) {
        for (final GDrawTypeEnum gDrawTypeEnum : values()) {
            if (gDrawTypeEnum.code == n) {
                return gDrawTypeEnum;
            }
        }
        return GDrawTypeEnum.normal;
    }
    
    public static GDrawTypeEnum valueOfSpan(final Class<? extends BaseSpan> clazz) {
        for (final GDrawTypeEnum gDrawTypeEnum : values()) {
            if (gDrawTypeEnum.baseSpan == clazz) {
                return gDrawTypeEnum;
            }
        }
        return GDrawTypeEnum.normal;
    }
    
    public Class<? extends BaseSpan> getBaseSpan() {
        return this.baseSpan;
    }
    
    public int getCode() {
        return this.code;
    }
}
