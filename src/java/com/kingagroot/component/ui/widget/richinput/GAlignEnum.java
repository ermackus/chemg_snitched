package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;

public enum GAlignEnum
{
    private static final GAlignEnum[] $VALUES;
    
    center(3, (Class<? extends BaseSpan>)GSuperscriptSpan.class), 
    justified(4, (Class<? extends BaseSpan>)GChemStyleSpan.class), 
    left(1, (Class<? extends BaseSpan>)null), 
    right(2, (Class<? extends BaseSpan>)GSubscriptSpan.class);
    
    Class<? extends BaseSpan> baseSpan;
    int code;
    
    private GAlignEnum(final int code, final Class<? extends BaseSpan> baseSpan) {
        this.code = code;
        this.baseSpan = baseSpan;
    }
    
    public static GAlignEnum valueOfCode(final int n) {
        for (final GAlignEnum gAlignEnum : values()) {
            if (gAlignEnum.code == n) {
                return gAlignEnum;
            }
        }
        return GAlignEnum.left;
    }
    
    public static GAlignEnum valueOfSpan(final Class<? extends BaseSpan> clazz) {
        for (final GAlignEnum gAlignEnum : values()) {
            if (gAlignEnum.baseSpan == clazz) {
                return gAlignEnum;
            }
        }
        return GAlignEnum.left;
    }
    
    public Class<? extends BaseSpan> getBaseSpan() {
        return this.baseSpan;
    }
    
    public int getCode() {
        return this.code;
    }
}
