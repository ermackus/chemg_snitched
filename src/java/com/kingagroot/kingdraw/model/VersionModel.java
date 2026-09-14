package com.kingagroot.kingdraw.model;

import java.io.Serializable;

@Deprecated
public class VersionModel implements Serializable
{
    private static final long serialVersionUID = -2452790267362394035L;
    private String Content;
    private String CreateMan;
    private String CreateTime;
    private String DelFlag;
    private String FileUrl;
    private String Id;
    private String Intro;
    private int IsForce;
    private String Remark;
    private String Title;
    private int VersionCode;
    
    public String getContent() {
        return this.Content;
    }
    
    public String getCreateMan() {
        return this.CreateMan;
    }
    
    public String getCreateTime() {
        return this.CreateTime;
    }
    
    public String getDelFlag() {
        return this.DelFlag;
    }
    
    public String getFileUrl() {
        return this.FileUrl;
    }
    
    public String getId() {
        return this.Id;
    }
    
    public String getIntro() {
        return this.Intro;
    }
    
    public int getIsForce() {
        return this.IsForce;
    }
    
    public String getRemark() {
        return this.Remark;
    }
    
    public String getTitle() {
        return this.Title;
    }
    
    public int getVersionCode() {
        return this.VersionCode;
    }
    
    public boolean isFoce() {
        final int isForce = this.IsForce;
        boolean b = true;
        if (isForce != 1) {
            b = false;
        }
        return b;
    }
    
    public void setContent(final String content) {
        this.Content = content;
    }
    
    public void setCreateMan(final String createMan) {
        this.CreateMan = createMan;
    }
    
    public void setCreateTime(final String createTime) {
        this.CreateTime = createTime;
    }
    
    public void setDelFlag(final String delFlag) {
        this.DelFlag = delFlag;
    }
    
    public void setFileUrl(final String fileUrl) {
        this.FileUrl = fileUrl;
    }
    
    public void setId(final String id) {
        this.Id = id;
    }
    
    public void setIntro(final String intro) {
        this.Intro = intro;
    }
    
    public void setIsForce(final int isForce) {
        this.IsForce = isForce;
    }
    
    public void setRemark(final String remark) {
        this.Remark = remark;
    }
    
    public void setTitle(final String title) {
        this.Title = title;
    }
    
    public void setVersionCode(final int versionCode) {
        this.VersionCode = versionCode;
    }
}
