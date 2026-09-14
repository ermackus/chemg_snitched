package com.kingagroot.kingdraw.ui.account.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "AccountUserModel")
public class AccountUserModel
{
    @Column(name = "countryArea")
    private String countryArea;
    @Column(name = "countryName")
    private String countryName;
    @Column(name = "eMail")
    private String eMail;
    @Column(name = "headImgs")
    private String headImgs;
    @Column(autoGen = false, isId = true, name = "id")
    private int id;
    @Column(name = "industry")
    private String industry;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sex")
    private String sex;
    @Column(name = "suffix")
    private String suffix;
    @Column(name = "userCode")
    private String userCode;
    @Column(name = "userType")
    private int userType;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "vipExpiryTime")
    private String vipExpiryTime;
    @Column(name = "vipState")
    private int vipState;
    
    public String getCountryArea() {
        return this.countryArea;
    }
    
    public String getCountryName() {
        return this.countryName;
    }
    
    public String getHeadImgs() {
        return this.headImgs;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getIndustry() {
        return this.industry;
    }
    
    public String getNickName() {
        return this.nickName;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    public String getUserCode() {
        return this.userCode;
    }
    
    public int getUserType() {
        return this.userType;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public String getVipExpiryTime() {
        return this.vipExpiryTime;
    }
    
    public int getVipState() {
        return this.vipState;
    }
    
    public String geteMail() {
        return this.eMail;
    }
    
    public boolean isEqual(final AccountUserModel accountUserModel) {
        final boolean b = false;
        if (accountUserModel == null) {
            return false;
        }
        boolean b2 = b;
        if (accountUserModel.getNickName().equals((Object)this.nickName)) {
            b2 = b;
            if (accountUserModel.getSex().equals((Object)this.sex)) {
                b2 = b;
                if (accountUserModel.getIndustry().equals((Object)this.industry)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public void setCountryArea(final String countryArea) {
        this.countryArea = countryArea;
    }
    
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }
    
    public void setHeadImgs(final String headImgs) {
        this.headImgs = headImgs;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setIndustry(final String industry) {
        this.industry = industry;
    }
    
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public void setSex(final String sex) {
        this.sex = sex;
    }
    
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }
    
    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }
    
    public void setUserType(final int userType) {
        this.userType = userType;
    }
    
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
    
    public void setVipExpiryTime(final String vipExpiryTime) {
        this.vipExpiryTime = vipExpiryTime;
    }
    
    public void setVipState(final int vipState) {
        this.vipState = vipState;
    }
    
    public void seteMail(final String eMail) {
        this.eMail = eMail;
    }
}
