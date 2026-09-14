package com.kingagroot.component.ui.vertical.model;

import java.util.List;

public class ToolBaseModel
{
    private ToolAttributeModel Attribute;
    private String ObjectImage;
    private String ObjectName;
    private boolean enabled;
    private boolean isMultipleSelect;
    private boolean isSelect;
    private boolean isShowImage;
    private String resourceName;
    private String showName;
    private List<ToolBaseModel> subChemList;
    private int toolId;
    private String typeStr;
    
    public ToolAttributeModel getAttribute() {
        return this.Attribute;
    }
    
    public String getObjectImage() {
        return this.ObjectImage;
    }
    
    public String getObjectName() {
        return this.ObjectName;
    }
    
    public String getResourceName() {
        return this.resourceName;
    }
    
    public String getShowName() {
        return this.showName;
    }
    
    public List<ToolBaseModel> getSubChemList() {
        return this.subChemList;
    }
    
    public int getToolId() {
        return this.toolId;
    }
    
    public String getTypeStr() {
        return this.typeStr;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isMultipleSelect() {
        return this.isMultipleSelect;
    }
    
    public boolean isSelect() {
        return this.isSelect;
    }
    
    public boolean isShowImage() {
        return this.isShowImage;
    }
    
    public void setAttribute(final ToolAttributeModel attribute) {
        this.Attribute = attribute;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setMultipleSelect(final boolean isMultipleSelect) {
        this.isMultipleSelect = isMultipleSelect;
    }
    
    public void setObjectImage(final String objectImage) {
        this.ObjectImage = objectImage;
    }
    
    public void setObjectName(final String objectName) {
        this.ObjectName = objectName;
    }
    
    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }
    
    public void setSelect(final boolean isSelect) {
        this.isSelect = isSelect;
    }
    
    public void setShowImage(final boolean isShowImage) {
        this.isShowImage = isShowImage;
    }
    
    public void setShowName(final String showName) {
        this.showName = showName;
    }
    
    public void setSubChemList(final List<ToolBaseModel> subChemList) {
        this.subChemList = subChemList;
    }
    
    public void setToolId(final int toolId) {
        this.toolId = toolId;
    }
    
    public void setTypeStr(final String typeStr) {
        this.typeStr = typeStr;
    }
}
