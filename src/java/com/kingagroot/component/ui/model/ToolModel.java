package com.kingagroot.component.ui.model;

import com.kingagroot.component.ui.ToolEnum;

public class ToolModel
{
    private boolean enable;
    private boolean isCheck;
    private int resId;
    private ToolEnum toolEnum;
    
    public ToolModel(final int resId) {
        this.enable = true;
        this.isCheck = false;
        this.resId = resId;
    }
    
    public ToolModel(final ToolEnum toolEnum, final int resId) {
        this.enable = true;
        this.isCheck = false;
        this.toolEnum = toolEnum;
        this.resId = resId;
    }
    
    public ToolModel(final ToolEnum toolEnum, final int resId, final boolean enable) {
        this.enable = true;
        this.isCheck = false;
        this.toolEnum = toolEnum;
        this.resId = resId;
        this.enable = enable;
    }
    
    public int getResId() {
        return this.resId;
    }
    
    public ToolEnum getToolEnum() {
        return this.toolEnum;
    }
    
    public boolean isCheck() {
        return this.isCheck;
    }
    
    public boolean isEnable() {
        return this.enable;
    }
    
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
    }
    
    public void setEnable(final boolean enable) {
        this.enable = enable;
    }
    
    public void setResId(final int resId) {
        this.resId = resId;
    }
    
    public void setToolEnum(final ToolEnum toolEnum) {
        this.toolEnum = toolEnum;
    }
}
