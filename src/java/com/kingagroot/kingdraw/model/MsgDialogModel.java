package com.kingagroot.kingdraw.model;

import java.util.List;

public class MsgDialogModel
{
    private String classificationName;
    private int contentType;
    private String cutOffReceiveDatetime;
    private List<MsgDialogActionModel> msgActions;
    private String msgContent;
    private String platform;
    private String showEndDatetime;
    private int showType;
    private String summary;
    private String title;
    
    public String getClassificationName() {
        return this.classificationName;
    }
    
    public int getContentType() {
        return this.contentType;
    }
    
    public String getCutOffReceiveDatetime() {
        return this.cutOffReceiveDatetime;
    }
    
    public List<MsgDialogActionModel> getMsgActions() {
        return this.msgActions;
    }
    
    public String getMsgContent() {
        return this.msgContent;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getShowEndDatetime() {
        return this.showEndDatetime;
    }
    
    public int getShowType() {
        return this.showType;
    }
    
    public String getSummary() {
        return this.summary;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setClassificationName(final String classificationName) {
        this.classificationName = classificationName;
    }
    
    public void setContentType(final int contentType) {
        this.contentType = contentType;
    }
    
    public void setCutOffReceiveDatetime(final String cutOffReceiveDatetime) {
        this.cutOffReceiveDatetime = cutOffReceiveDatetime;
    }
    
    public void setMsgActions(final List<MsgDialogActionModel> msgActions) {
        this.msgActions = msgActions;
    }
    
    public void setMsgContent(final String msgContent) {
        this.msgContent = msgContent;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
    
    public void setShowEndDatetime(final String showEndDatetime) {
        this.showEndDatetime = showEndDatetime;
    }
    
    public void setShowType(final int showType) {
        this.showType = showType;
    }
    
    public void setSummary(final String summary) {
        this.summary = summary;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public class MsgDialogActionModel
    {
        private String actionLink;
        private String actionName;
        private int msgID;
        private int orderID;
        final MsgDialogModel this$0;
        
        public MsgDialogActionModel(final MsgDialogModel this$0) {
            this.this$0 = this$0;
        }
        
        public String getActionLink() {
            return this.actionLink;
        }
        
        public String getActionName() {
            return this.actionName;
        }
        
        public int getMsgID() {
            return this.msgID;
        }
        
        public int getOrderID() {
            return this.orderID;
        }
        
        public void setActionLink(final String actionLink) {
            this.actionLink = actionLink;
        }
        
        public void setActionName(final String actionName) {
            this.actionName = actionName;
        }
        
        public void setMsgID(final int msgID) {
            this.msgID = msgID;
        }
        
        public void setOrderID(final int orderID) {
            this.orderID = orderID;
        }
    }
}
