package com.kingagroot.kingdraw.model;

public class VipOrderModel
{
    private int orderId;
    private int storeId;
    
    public int getOrderId() {
        return this.orderId;
    }
    
    public int getStoreId() {
        return this.storeId;
    }
    
    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }
    
    public void setStoreId(final int storeId) {
        this.storeId = storeId;
    }
}
