package com.kingagroot.kingdraw.model;

import java.util.List;

public class FeedBackModel
{
    private String content;
    private String createTime;
    private String device;
    private List<FeedbackReplyModel> feedbackReplyList;
    private String headImage;
    private int id;
    private int isRead;
    private String nickName;
    private int num;
    private List<PictureListModel> pictureList;
    
    public String getContent() {
        return this.content;
    }
    
    public String getCreateTime() {
        return this.createTime;
    }
    
    public String getDevice() {
        return this.device;
    }
    
    public List<FeedbackReplyModel> getFeedbackReplyList() {
        return this.feedbackReplyList;
    }
    
    public String getHeadImage() {
        return this.headImage;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getIsRead() {
        return this.isRead;
    }
    
    public String getNickName() {
        return this.nickName;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public List<PictureListModel> getPictureList() {
        return this.pictureList;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }
    
    public void setDevice(final String device) {
        this.device = device;
    }
    
    public void setFeedbackReplyList(final List<FeedbackReplyModel> feedbackReplyList) {
        this.feedbackReplyList = feedbackReplyList;
    }
    
    public void setHeadImage(final String headImage) {
        this.headImage = headImage;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setIsRead(final int isRead) {
        this.isRead = isRead;
    }
    
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }
    
    public void setNum(final int num) {
        this.num = num;
    }
    
    public void setPictureList(final List<PictureListModel> pictureList) {
        this.pictureList = pictureList;
    }
    
    public static class FeedbackReplyModel
    {
        private String adminName;
        private String content;
        private String createTime;
        private String createUserName;
        private int id;
        private int isRead;
        private List<PictureListModel> pictureList;
        
        public String getAdminName() {
            return this.adminName;
        }
        
        public String getContent() {
            return this.content;
        }
        
        public String getCreateTime() {
            return this.createTime;
        }
        
        public String getCreateUserName() {
            return this.createUserName;
        }
        
        public int getId() {
            return this.id;
        }
        
        public int getIsRead() {
            return this.isRead;
        }
        
        public List<PictureListModel> getPictureList() {
            return this.pictureList;
        }
        
        public void setAdminName(final String adminName) {
            this.adminName = adminName;
        }
        
        public void setContent(final String content) {
            this.content = content;
        }
        
        public void setCreateTime(final String createTime) {
            this.createTime = createTime;
        }
        
        public void setCreateUserName(final String createUserName) {
            this.createUserName = createUserName;
        }
        
        public void setId(final int id) {
            this.id = id;
        }
        
        public void setIsRead(final int isRead) {
            this.isRead = isRead;
        }
        
        public void setPictureList(final List<PictureListModel> pictureList) {
            this.pictureList = pictureList;
        }
    }
    
    public static class PictureListModel
    {
        private int id;
        private String picturePath;
        
        public int getId() {
            return this.id;
        }
        
        public String getPicturePath() {
            return this.picturePath;
        }
        
        public void setId(final int id) {
            this.id = id;
        }
        
        public void setPicturePath(final String picturePath) {
            this.picturePath = picturePath;
        }
    }
}
