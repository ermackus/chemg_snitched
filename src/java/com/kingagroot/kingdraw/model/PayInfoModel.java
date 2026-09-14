package com.kingagroot.kingdraw.model;

import java.util.List;

public class PayInfoModel
{
    private List<PayPackage> payPackage;
    private List<PayPackageImages> payPackageImages;
    private List<PayType> payType;
    
    public List<PayPackage> getPayPackage() {
        return this.payPackage;
    }
    
    public List<PayPackageImages> getPayPackageImages() {
        return this.payPackageImages;
    }
    
    public List<PayType> getPayType() {
        return this.payType;
    }
    
    public void setPayPackage(final List<PayPackage> payPackage) {
        this.payPackage = payPackage;
    }
    
    public void setPayPackageImages(final List<PayPackageImages> payPackageImages) {
        this.payPackageImages = payPackageImages;
    }
    
    public void setPayType(final List<PayType> payType) {
        this.payType = payType;
    }
    
    public static class PayPackage
    {
        private float money;
        private String packageDesc;
        private int packageType;
        private String remarks;
        private int storeId;
        private String symbol;
        private String titleName;
        
        public float getMoney() {
            return this.money;
        }
        
        public String getPackageDesc() {
            return this.packageDesc;
        }
        
        public int getPackageType() {
            return this.packageType;
        }
        
        public String getRemarks() {
            return this.remarks;
        }
        
        public int getStoreId() {
            return this.storeId;
        }
        
        public String getSymbol() {
            return this.symbol;
        }
        
        public String getTitleName() {
            return this.titleName;
        }
        
        public void setMoney(final float money) {
            this.money = money;
        }
        
        public void setPackageDesc(final String packageDesc) {
            this.packageDesc = packageDesc;
        }
        
        public void setPackageType(final int packageType) {
            this.packageType = packageType;
        }
        
        public void setRemarks(final String remarks) {
            this.remarks = remarks;
        }
        
        public void setStoreId(final int storeId) {
            this.storeId = storeId;
        }
        
        public void setSymbol(final String symbol) {
            this.symbol = symbol;
        }
        
        public void setTitleName(final String titleName) {
            this.titleName = titleName;
        }
    }
    
    public static class PayPackageImages
    {
        private String imageUrl;
        private String titleName;
        
        public String getImageUrl() {
            return this.imageUrl;
        }
        
        public String getTitleName() {
            return this.titleName;
        }
        
        public void setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
        }
        
        public void setTitleName(final String titleName) {
            this.titleName = titleName;
        }
    }
    
    public static class PayType
    {
        private int id;
        private String imageUrl;
        private String titleName;
        
        public int getId() {
            return this.id;
        }
        
        public String getImageUrl() {
            return this.imageUrl;
        }
        
        public String getTitleName() {
            return this.titleName;
        }
        
        public void setId(final int id) {
            this.id = id;
        }
        
        public void setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
        }
        
        public void setTitleName(final String titleName) {
            this.titleName = titleName;
        }
    }
}
