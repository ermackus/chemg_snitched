package com.kingagroot.kingdraw.model;

import com.kingagroot.kingdraw.utils.DrawFileUtil;
import org.xutils.db.annotation.Column;
import java.io.Serializable;

public class CloudFileModel implements Serializable, Cloneable
{
    @Column(name = "createTime")
    private long createTime;
    @Column(name = "createUserID")
    private String createUserID;
    @Column(name = "delFlag")
    private int delFlag;
    @Column(name = "fileExtension")
    private String fileExtension;
    @Column(name = "fileLength")
    private long fileLength;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "fileNamePinYin")
    private String fileNamePinYin;
    @Column(name = "fileOSSID")
    private String fileOSSID;
    @Column(name = "filePath")
    private String filePath;
    @Column(name = "fileType")
    private String fileType;
    @Column(isId = true, name = "id")
    private int id;
    @Column(name = "modifyTime")
    private long modifyTime;
    @Column(name = "picPath")
    private String picPath;
    @Column(name = "smiles")
    private String smiles;
    @Column(name = "thumbnailOSSID")
    private String thumbnailOSSID;
    @Column(name = "version")
    private int version;
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public String getCreateUserID() {
        return this.createUserID;
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
    
    public String getFileNameNoExtension() {
        return DrawFileUtil.getFileNameNoExtension(this.fileName);
    }
    
    public String getFileNamePinYin() {
        return this.fileNamePinYin;
    }
    
    public String getFileOSSID() {
        return this.fileOSSID;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public String getFileType() {
        return this.fileType;
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
    
    public void setCreateUserID(final String createUserID) {
        this.createUserID = createUserID;
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
    
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
    
    public void setFileType(final String fileType) {
        this.fileType = fileType;
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
