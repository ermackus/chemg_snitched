package com.kingagroot.kingdraw.model;

public class TipInfoModel
{
    String BtnText;
    String ContentId;
    String LinkUrl;
    String Title;
    
    public String getBtnText() {
        return this.BtnText;
    }
    
    public String getContentId() {
        return this.ContentId;
    }
    
    public String getLinkUrl() {
        return this.LinkUrl;
    }
    
    public String getTitle() {
        return this.Title;
    }
    
    public void setBtnText(final String btnText) {
        this.BtnText = btnText;
    }
    
    public void setContentId(final String contentId) {
        this.ContentId = contentId;
    }
    
    public void setLinkUrl(final String linkUrl) {
        this.LinkUrl = linkUrl;
    }
    
    public void setTitle(final String title) {
        this.Title = title;
    }
}
