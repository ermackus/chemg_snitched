package com.kingagroot.kingdraw.model;

public class SynFolderModel
{
    private long createTime;
    private int delFlag;
    private String fileExtension;
    private long fileLength;
    private String fileName;
    private String fileNamePinYin;
    private String fileOSSID;
    private int id;
    private long modifyTime;
    private String picPath;
    private String smiles;
    private String thumbnailOSSID;
    private int version;
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public int getDelFlag() {
        return this.delFlag;
    }
    
    public String getFileExtension() {
        return this.fileExtension;
    }
    
    public long getFileLength() {
        return this.fileLength;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getFileNamePinYin() {
        return this.fileNamePinYin;
    }
    
    public String getFileOSSID() {
        return this.fileOSSID;
    }
    
    public int getId() {
        return this.id;
    }
    
    public long getModifyTime() {
        return this.modifyTime;
    }
    
    public String getPicPath() {
        return this.picPath;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public String getThumbnailOSSID() {
        return this.thumbnailOSSID;
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }
    
    public void setDelFlag(final int delFlag) {
        this.delFlag = delFlag;
    }
    
    public void setFileExtension(final String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    public void setFileLength(final long fileLength) {
        this.fileLength = fileLength;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileNamePinYin(final String fileNamePinYin) {
        this.fileNamePinYin = fileNamePinYin;
    }
    
    public void setFileOSSID(final String fileOSSID) {
        this.fileOSSID = fileOSSID;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setModifyTime(final long modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public void setPicPath(final String picPath) {
        this.picPath = picPath;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
    
    public void setThumbnailOSSID(final String thumbnailOSSID) {
        this.thumbnailOSSID = thumbnailOSSID;
    }
    
    public void setVersion(final int version) {
        this.version = version;
    }
}
