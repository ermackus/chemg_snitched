package com.kingagroot.kingdraw.mqtt;

import java.util.List;

public class MsgModel
{
    private String ClassificationName;
    private int ContentType;
    private String CutOffReceieDatetime;
    private List<MsgActions> MsgActions;
    private String MsgContent;
    private int Platform;
    private String ShowEndDatetime;
    private int ShowType;
    private String Summary;
    private String Title;
    
    public String getClassificationName() {
        return this.ClassificationName;
    }
    
    public int getContentType() {
        return this.ContentType;
    }
    
    public String getCutOffReceieDatetime() {
        return this.CutOffReceieDatetime;
    }
    
    public List<MsgActions> getMsgActions() {
        return this.MsgActions;
    }
    
    public String getMsgContent() {
        return this.MsgContent;
    }
    
    public int getPlatform() {
        return this.Platform;
    }
    
    public String getShowEndDatetime() {
        return this.ShowEndDatetime;
    }
    
    public int getShowType() {
        return this.ShowType;
    }
    
    public String getSummary() {
        return this.Summary;
    }
    
    public String getTitle() {
        return this.Title;
    }
    
    public void setClassificationName(final String classificationName) {
        this.ClassificationName = classificationName;
    }
    
    public void setContentType(final int contentType) {
        this.ContentType = contentType;
    }
    
    public void setCutOffReceieDatetime(final String cutOffReceieDatetime) {
        this.CutOffReceieDatetime = cutOffReceieDatetime;
    }
    
    public void setMsgActions(final List<MsgActions> msgActions) {
        this.MsgActions = msgActions;
    }
    
    public void setMsgContent(final String msgContent) {
        this.MsgContent = msgContent;
    }
    
    public void setPlatform(final int platform) {
        this.Platform = platform;
    }
    
    public void setShowEndDatetime(final String showEndDatetime) {
        this.ShowEndDatetime = showEndDatetime;
    }
    
    public void setShowType(final int showType) {
        this.ShowType = showType;
    }
    
    public void setSummary(final String summary) {
        this.Summary = summary;
    }
    
    public void setTitle(final String title) {
        this.Title = title;
    }
    
    public class MsgActions
    {
        private String ActionLink;
        private String ActionName;
        private int MsgID;
        final MsgModel this$0;
        
        public MsgActions(final MsgModel this$0) {
            this.this$0 = this$0;
        }
        
        public String getActionLink() {
            return this.ActionLink;
        }
        
        public String getActionName() {
            return this.ActionName;
        }
        
        public int getMsgID() {
            return this.MsgID;
        }
        
        public void setActionLink(final String actionLink) {
            this.ActionLink = actionLink;
        }
        
        public void setActionName(final String actionName) {
            this.ActionName = actionName;
        }
        
        public void setMsgID(final int msgID) {
            this.MsgID = msgID;
        }
    }
}
