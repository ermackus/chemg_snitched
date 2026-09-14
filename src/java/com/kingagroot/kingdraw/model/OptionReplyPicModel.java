package com.kingagroot.kingdraw.model;

public class OptionReplyPicModel
{
    private int CreateMan;
    private String CreateTime;
    private int DelFlag;
    private int Id;
    private String PicId;
    private int ReplyId;
    
    public int getCreateMan() {
        return this.CreateMan;
    }
    
    public String getCreateTime() {
        return this.CreateTime;
    }
    
    public int getDelFlag() {
        return this.DelFlag;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public String getPicId() {
        return this.PicId;
    }
    
    public int getReplyId() {
        return this.ReplyId;
    }
    
    public void setCreateMan(final int createMan) {
        this.CreateMan = createMan;
    }
    
    public void setCreateTime(final String createTime) {
        this.CreateTime = createTime;
    }
    
    public void setDelFlag(final int delFlag) {
        this.DelFlag = delFlag;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setPicId(final String picId) {
        this.PicId = picId;
    }
    
    public void setReplyId(final int replyId) {
        this.ReplyId = replyId;
    }
}
