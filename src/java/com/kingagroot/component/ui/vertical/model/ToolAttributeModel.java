package com.kingagroot.component.ui.vertical.model;

public class ToolAttributeModel
{
    private String ShapeName;
    private String alignmentType;
    private int chargeType;
    private String colorStr;
    private int properType;
    private int shapeStyle;
    
    public String getAlignmentType() {
        return this.alignmentType;
    }
    
    public int getChargeType() {
        return this.chargeType;
    }
    
    public String getColorStr() {
        return this.colorStr;
    }
    
    public int getProperType() {
        return this.properType;
    }
    
    public String getShapeName() {
        return this.ShapeName;
    }
    
    public int getShapeStyle() {
        return this.shapeStyle;
    }
    
    public void setAlignmentType(final String alignmentType) {
        this.alignmentType = alignmentType;
    }
    
    public void setChargeType(final int chargeType) {
        this.chargeType = chargeType;
    }
    
    public void setColorStr(final String colorStr) {
        this.colorStr = colorStr;
    }
    
    public void setProperType(final int properType) {
        this.properType = properType;
    }
    
    public void setShapeName(final String shapeName) {
        this.ShapeName = shapeName;
    }
    
    public void setShapeStyle(final int shapeStyle) {
        this.shapeStyle = shapeStyle;
    }
}
