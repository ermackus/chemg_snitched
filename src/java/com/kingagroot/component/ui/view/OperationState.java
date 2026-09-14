package com.kingagroot.component.ui.view;

public class OperationState
{
    public Object data;
    public String info;
    public boolean isSuccess;
    
    public OperationState() {
        this.isSuccess = false;
    }
    
    public OperationState(final boolean isSuccess, final String info) {
        this.isSuccess = false;
        this.isSuccess = isSuccess;
        this.info = info;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public boolean isSuccess() {
        return this.isSuccess;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public void setInfo(final String info) {
        this.info = info;
    }
    
    public void setSuccess(final boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
