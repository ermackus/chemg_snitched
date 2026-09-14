package com.kingagroot.kingdraw.model;

import java.util.List;

public class OptionReplyModel
{
    private String AdminName;
    private int CreateMan;
    private String CreateManName;
    private String CreateTime;
    private int Id;
    private int IsRead;
    private int OptionId;
    private List<OptionReplyPicModel> OptionReplyPicList;
    private String Replay;
    
    public String getAdminName() {
        return this.AdminName;
    }
    
    public int getCreateMan() {
        return this.CreateMan;
    }
    
    public String getCreateManName() {
        return this.CreateManName;
    }
    
    public String getCreateTime() {
        return this.CreateTime;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public int getIsRead() {
        return this.IsRead;
    }
    
    public int getOptionId() {
        return this.OptionId;
    }
    
    public List<OptionReplyPicModel> getOptionReplyPicList() {
        return this.OptionReplyPicList;
    }
    
    public String getReplay() {
        return this.Replay;
    }
    
    public void setAdminName(final String adminName) {
        this.AdminName = adminName;
    }
    
    public void setCreateMan(final int createMan) {
        this.CreateMan = createMan;
    }
    
    public void setCreateManName(final String createManName) {
        this.CreateManName = createManName;
    }
    
    public void setCreateTime(final String createTime) {
        this.CreateTime = createTime;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setIsRead(final int isRead) {
        this.IsRead = isRead;
    }
    
    public void setOptionId(final int optionId) {
        this.OptionId = optionId;
    }
    
    public void setOptionReplyPicList(final List<OptionReplyPicModel> optionReplyPicList) {
        this.OptionReplyPicList = optionReplyPicList;
    }
    
    public void setReplay(final String replay) {
        this.Replay = replay;
    }
}
