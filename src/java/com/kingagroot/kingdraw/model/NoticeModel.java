package com.kingagroot.kingdraw.model;

public class NoticeModel
{
    private String Content;
    private int Id;
    private int ShowNew;
    private String Title;
    private String VisibleTime;
    
    public String getContent() {
        return this.Content;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public int getShowNew() {
        return this.ShowNew;
    }
    
    public String getTitle() {
        return this.Title;
    }
    
    public String getVisibleTime() {
        return this.VisibleTime;
    }
    
    public void setContent(final String content) {
        this.Content = content;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setShowNew(final int showNew) {
        this.ShowNew = showNew;
    }
    
    public void setTitle(final String title) {
        this.Title = title;
    }
    
    public void setVisibleTime(final String visibleTime) {
        this.VisibleTime = visibleTime;
    }
}
