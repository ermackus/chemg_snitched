package com.kingagroot.kingdraw.model;

public class UrgentTipModel
{
    String AppType;
    String Id;
    String Intro;
    String SystemRange;
    String Title;
    String VersionRange;
    String WebContent;
    
    public String getAppType() {
        return this.AppType;
    }
    
    public String getId() {
        return this.Id;
    }
    
    public String getIntro() {
        return this.Intro;
    }
    
    public String getSystemRange() {
        return this.SystemRange;
    }
    
    public String getTitle() {
        return this.Title;
    }
    
    public String getVersionRange() {
        return this.VersionRange;
    }
    
    public String getWebContent() {
        return this.WebContent;
    }
    
    public void setAppType(final String appType) {
        this.AppType = appType;
    }
    
    public void setId(final String id) {
        this.Id = id;
    }
    
    public void setIntro(final String intro) {
        this.Intro = intro;
    }
    
    public void setSystemRange(final String systemRange) {
        this.SystemRange = systemRange;
    }
    
    public void setTitle(final String title) {
        this.Title = title;
    }
    
    public void setVersionRange(final String versionRange) {
        this.VersionRange = versionRange;
    }
    
    public void setWebContent(final String webContent) {
        this.WebContent = webContent;
    }
}
