package com.kingagroot.kingdraw.model;

public class OptionPicModel
{
    public int CreateMan;
    public String CreateTime;
    public int DelFlag;
    public int Id;
    public int OptionId;
    public String PicId;
    
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
    
    public int getOptionId() {
        return this.OptionId;
    }
    
    public String getPicId() {
        return this.PicId;
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
    
    public void setOptionId(final int optionId) {
        this.OptionId = optionId;
    }
    
    public void setPicId(final String picId) {
        this.PicId = picId;
    }
}
