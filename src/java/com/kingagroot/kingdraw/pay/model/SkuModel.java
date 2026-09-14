package com.kingagroot.kingdraw.pay.model;

public class SkuModel
{
    private String appstorePid;
    private int cost;
    private String googlestorePid;
    private String skuId;
    private String title;
    
    public String getAppstorePid() {
        return this.appstorePid;
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public String getGooglestorePid() {
        return this.googlestorePid;
    }
    
    public String getSkuId() {
        return this.skuId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setAppstorePid(final String appstorePid) {
        this.appstorePid = appstorePid;
    }
    
    public void setCost(final int cost) {
        this.cost = cost;
    }
    
    public void setGooglestorePid(final String googlestorePid) {
        this.googlestorePid = googlestorePid;
    }
    
    public void setSkuId(final String skuId) {
        this.skuId = skuId;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
}
