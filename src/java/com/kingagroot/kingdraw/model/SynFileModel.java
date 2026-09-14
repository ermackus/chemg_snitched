package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "SynFileModel")
public class SynFileModel implements Serializable, Cloneable
{
    @Column(name = "CreatTime")
    private long CreatTime;
    @Column(name = "FileSize")
    private long FileSize;
    @Column(name = "FolderFileModelId")
    private String FolderFileModelId;
    @Column(name = "FolderFileModelStr")
    private String FolderFileModelStr;
    @Column(autoGen = true, isId = true, name = "Id")
    private String Id;
    @Column(name = "Status")
    private int Status;
    @Column(name = "SynProgress")
    long SynProgress;
    @Column(name = "SynType")
    private int SynType;
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public long getCreatTime() {
        return this.CreatTime;
    }
    
    public long getFileSize() {
        return this.FileSize;
    }
    
    public String getFolderFileModelId() {
        return this.FolderFileModelId;
    }
    
    public String getFolderFileModelStr() {
        return this.FolderFileModelStr;
    }
    
    public String getId() {
        return this.Id;
    }
    
    public int getStatus() {
        return this.Status;
    }
    
    public long getSynProgress() {
        return this.SynProgress;
    }
    
    public int getSynType() {
        return this.SynType;
    }
    
    public void setCreatTime(final long creatTime) {
        this.CreatTime = creatTime;
    }
    
    public void setFileSize(final long fileSize) {
        this.FileSize = fileSize;
    }
    
    public void setFolderFileModelId(final String folderFileModelId) {
        this.FolderFileModelId = folderFileModelId;
    }
    
    public void setFolderFileModelStr(final String folderFileModelStr) {
        this.FolderFileModelStr = folderFileModelStr;
    }
    
    public void setId(final String id) {
        this.Id = id;
    }
    
    public void setStatus(final int status) {
        this.Status = status;
    }
    
    public void setSynProgress(final long synProgress) {
        this.SynProgress = synProgress;
    }
    
    public void setSynType(final int synType) {
        this.SynType = synType;
    }
}
