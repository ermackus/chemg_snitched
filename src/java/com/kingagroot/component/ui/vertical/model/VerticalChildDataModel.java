package com.kingagroot.component.ui.vertical.model;

import java.util.List;

public class VerticalChildDataModel
{
    private List<Integer> childList;
    private int parentId;
    private String typeName;
    
    public List<Integer> getChildList() {
        return this.childList;
    }
    
    public int getParentId() {
        return this.parentId;
    }
    
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setChildList(final List<Integer> childList) {
        this.childList = childList;
    }
    
    public void setParentId(final int parentId) {
        this.parentId = parentId;
    }
    
    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }
}
