package com.kingagroot.kingdraw.model;

import java.util.List;

public class OptionModel
{
    private String Account;
    private String AreaNumber;
    private String Contact;
    private int CreateMan;
    private String CreateTime;
    private String DisposeTime;
    private int Id;
    private String Imei;
    private String Info;
    private int IsRead;
    private String Num;
    private List<OptionPicModel> OptionPicList;
    private List<OptionReplyModel> OptionReplyList;
    private String PhoneModel;
    private String Replay;
    private String SoftwareVersion;
    private String Status;
    private String SystemInfo;
    
    public String getAccount() {
        return this.Account;
    }
    
    public String getAreaNumber() {
        return this.AreaNumber;
    }
    
    public String getContact() {
        return this.Contact;
    }
    
    public int getCreateMan() {
        return this.CreateMan;
    }
    
    public String getCreateTime() {
        return this.CreateTime;
    }
    
    public String getDisposeTime() {
        return this.DisposeTime;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public String getImei() {
        return this.Imei;
    }
    
    public String getInfo() {
        return this.Info;
    }
    
    public int getIsRead() {
        return this.IsRead;
    }
    
    public String getNum() {
        return this.Num;
    }
    
    public List<OptionPicModel> getOptionPicList() {
        return this.OptionPicList;
    }
    
    public List<OptionReplyModel> getOptionReplyList() {
        return this.OptionReplyList;
    }
    
    public String getPhoneModel() {
        return this.PhoneModel;
    }
    
    public String getReplay() {
        return this.Replay;
    }
    
    public String getSoftwareVersion() {
        return this.SoftwareVersion;
    }
    
    public String getStatus() {
        return this.Status;
    }
    
    public String getSystemInfo() {
        return this.SystemInfo;
    }
    
    public void setAccount(final String account) {
        this.Account = account;
    }
    
    public void setAreaNumber(final String areaNumber) {
        this.AreaNumber = areaNumber;
    }
    
    public void setContact(final String contact) {
        this.Contact = contact;
    }
    
    public void setCreateMan(final int createMan) {
        this.CreateMan = createMan;
    }
    
    public void setCreateTime(final String createTime) {
        this.CreateTime = createTime;
    }
    
    public void setDisposeTime(final String disposeTime) {
        this.DisposeTime = disposeTime;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setImei(final String imei) {
        this.Imei = imei;
    }
    
    public void setInfo(final String s) {
    }
    
    public void setIsRead(final int isRead) {
        this.IsRead = isRead;
    }
    
    public void setNum(final String num) {
        this.Num = num;
    }
    
    public void setOptionPicList(final List<OptionPicModel> optionPicList) {
        this.OptionPicList = optionPicList;
    }
    
    public void setOptionReplyList(final List<OptionReplyModel> optionReplyList) {
        this.OptionReplyList = optionReplyList;
    }
    
    public void setPhoneModel(final String phoneModel) {
        this.PhoneModel = phoneModel;
    }
    
    public void setReplay(final String replay) {
        this.Replay = replay;
    }
    
    public void setSoftwareVersion(final String softwareVersion) {
        this.SoftwareVersion = softwareVersion;
    }
    
    public void setStatus(final String status) {
        this.Status = status;
    }
    
    public void setSystemInfo(final String systemInfo) {
        this.SystemInfo = systemInfo;
    }
}
