package com.kingagroot.kingdraw.core.model;

public class PaletteConfigModel
{
    @JsonValue(key = "carbonShowType")
    private int carbonShowType;
    @JsonValue(key = "normalColor")
    private boolean normalColor;
    
    public PaletteConfigModel() {
        this.carbonShowType = 0;
        this.normalColor = true;
    }
    
    public int getCarbonShowType() {
        return this.carbonShowType;
    }
    
    public boolean isNormalColor() {
        return this.normalColor;
    }
    
    public void setCarbonShowType(final int carbonShowType) {
        this.carbonShowType = carbonShowType;
    }
    
    public void setNormalColor(final boolean normalColor) {
        this.normalColor = normalColor;
    }
}
