package com.kingagroot.kingdraw.pay.model;

import java.util.List;

public class SpuModel
{
    private String name;
    private List<SkuModel> sku;
    private String spuId;
    
    public String getName() {
        return this.name;
    }
    
    public List<SkuModel> getSku() {
        return this.sku;
    }
    
    public String getSpuId() {
        return this.spuId;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSku(final List<SkuModel> sku) {
        this.sku = sku;
    }
    
    public void setSpuId(final String spuId) {
        this.spuId = spuId;
    }
}
