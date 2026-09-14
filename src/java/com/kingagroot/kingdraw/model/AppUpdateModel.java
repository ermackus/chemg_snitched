package com.kingagroot.kingdraw.model;

public class AppUpdateModel
{
    private String fileUrl;
    private String introduction;
    private String title;
    private String versionCode;
    private String versionType;
    
    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public String getIntroduction() {
        return this.introduction;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getVersionCode() {
        return this.versionCode;
    }
    
    public String getVersionType() {
        return this.versionType;
    }
    
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public void setIntroduction(final String introduction) {
        this.introduction = introduction;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setVersionCode(final String versionCode) {
        this.versionCode = versionCode;
    }
    
    public void setVersionType(final String versionType) {
        this.versionType = versionType;
    }
}
