package com.kingagroot.component.ui.vertical.model;

import java.util.List;

public class VerticalDataModel
{
    private List<VerticalChildDataModel> list;
    private List<Integer> parentIds;
    
    public List<VerticalChildDataModel> getList() {
        return this.list;
    }
    
    public List<Integer> getParentIds() {
        return this.parentIds;
    }
    
    public void setList(final List<VerticalChildDataModel> list) {
        this.list = list;
    }
    
    public void setParentIds(final List<Integer> parentIds) {
        this.parentIds = parentIds;
    }
}
