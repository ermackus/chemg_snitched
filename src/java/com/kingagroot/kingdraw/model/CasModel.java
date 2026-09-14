package com.kingagroot.kingdraw.model;

public class CasModel
{
    private String CASNo;
    private int CID;
    private String IUPACName;
    private String Keywords;
    private String MF;
    private String MW;
    private String RecordNumber;
    private String RecordTitle;
    private int SupplyCount;
    private String titlecn;
    
    public String getCASNo() {
        return this.CASNo;
    }
    
    public int getCID() {
        return this.CID;
    }
    
    public String getIUPACName() {
        return this.IUPACName;
    }
    
    public String getKeywords() {
        return this.Keywords;
    }
    
    public String getMF() {
        return this.MF;
    }
    
    public String getMW() {
        return this.MW;
    }
    
    public String getRecordNumber() {
        return this.RecordNumber;
    }
    
    public String getRecordTitle() {
        return this.RecordTitle;
    }
    
    public int getSupplyCount() {
        return this.SupplyCount;
    }
    
    public String getTitlecn() {
        return this.titlecn;
    }
    
    public void setCASNo(final String casNo) {
        this.CASNo = casNo;
    }
    
    public void setCID(final int cid) {
        this.CID = cid;
    }
    
    public void setIUPACName(final String iupacName) {
        this.IUPACName = iupacName;
    }
    
    public void setKeywords(final String keywords) {
        this.Keywords = keywords;
    }
    
    public void setMF(final String mf) {
        this.MF = mf;
    }
    
    public void setMW(final String mw) {
        this.MW = mw;
    }
    
    public void setRecordNumber(final String recordNumber) {
        this.RecordNumber = recordNumber;
    }
    
    public void setRecordTitle(final String recordTitle) {
        this.RecordTitle = recordTitle;
    }
    
    public void setSupplyCount(final int supplyCount) {
        this.SupplyCount = supplyCount;
    }
    
    public void setTitlecn(final String titlecn) {
        this.titlecn = titlecn;
    }
}
