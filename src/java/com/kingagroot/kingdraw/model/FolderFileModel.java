package com.kingagroot.kingdraw.model;

import com.kingagroot.kingdraw.utils.FileTagUtils;
import java.util.List;
import com.google.gson.Gson;
import com.kingagroot.component.ui.model.GFormatValue;
import java.io.File;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "FolderFileModel")
public class FolderFileModel implements Serializable, Cloneable
{
    @Column(name = "CreateMan")
    int CreateMan;
    @Column(name = "CreateTime")
    private long CreateTime;
    @Column(name = "FileExtension")
    private String FileExtension;
    @Column(name = "FileLength")
    int FileLength;
    @Column(name = "FileName")
    private String FileName;
    @Column(name = "FileOssId")
    String FileOssId;
    @Column(name = "FilePath")
    private String FilePath;
    @Column(name = "FileSmallPicId")
    String FileSmallPicId;
    @Column(name = "FileSmiles")
    String FileSmiles;
    @Column(name = "FolderId")
    int FolderId;
    @Column(isId = true, name = "Id")
    private String Id;
    @Column(name = "ModifyTime")
    private long ModifyTime;
    @Column(name = "Tags")
    String Tags;
    @Column(name = "Version")
    String Version;
    @Column(name = "fileType")
    private String fileType;
    @Column(name = "fromatStr")
    private String fromatStr;
    @Column(name = "picPath")
    private String picPath;
    
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
        return this.CreateTime;
    }
    
    public String getFileExtension() {
        return this.FileExtension;
    }
    
    public int getFileLength() {
        return this.FileLength;
    }
    
    public String getFileName() {
        return this.FileName;
    }
    
    public String getFileNameNoExtension() {
        return DrawFileUtil.getFileNameNoExtension(this.FileName);
    }
    
    public String getFileOssId() {
        return this.FileOssId;
    }
    
    public String getFilePath() {
        return this.FilePath;
    }
    
    public long getFileSize() {
        final File file = new File(this.FilePath);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }
    
    public String getFileSmallPicId() {
        return this.FileSmallPicId;
    }
    
    public String getFileSmiles() {
        return this.FileSmiles;
    }
    
    public String getFileType() {
        return this.fileType;
    }
    
    public FileType getFileTypeEnum() {
        return FileType.valueOfByType(this.fileType);
    }
    
    public String getFromatStr() {
        return this.fromatStr;
    }
    
    public GFormatValue getFromatValue() {
        return (GFormatValue)new Gson().fromJson(this.fromatStr, (Class)GFormatValue.class);
    }
    
    public String getFullFileName() {
        return this.FileName;
    }
    
    public String getId() {
        return this.Id;
    }
    
    public long getModifyTime() {
        return this.ModifyTime;
    }
    
    public String getPicPath() {
        return this.picPath;
    }
    
    public List<String> getTagList() {
        return (List<String>)FileTagUtils.stringToList(this.Tags);
    }
    
    public String getTags() {
        return this.Tags;
    }
    
    public void setCreateTime(final long createTime) {
        this.CreateTime = createTime;
    }
    
    public void setFileExtension(final String fileExtension) {
        this.FileExtension = fileExtension;
    }
    
    public void setFileLength(final int fileLength) {
        this.FileLength = fileLength;
    }
    
    public void setFileName(final String fileName) {
        this.FileName = fileName;
    }
    
    public void setFileOssId(final String fileOssId) {
        this.FileOssId = fileOssId;
    }
    
    public void setFilePath(final String filePath) {
        this.FilePath = filePath;
    }
    
    public void setFileSmallPicId(final String fileSmallPicId) {
        this.FileSmallPicId = fileSmallPicId;
    }
    
    public void setFileSmiles(final String fileSmiles) {
        this.FileSmiles = fileSmiles;
    }
    
    public void setFileType(final String fileType) {
        this.fileType = fileType;
    }
    
    public void setFromatStr(final String fromatStr) {
        this.fromatStr = fromatStr;
    }
    
    public void setId(final String id) {
        this.Id = id;
    }
    
    public void setModifyTime(final long modifyTime) {
        this.ModifyTime = modifyTime;
    }
    
    public void setPicPath(final String picPath) {
        this.picPath = picPath;
    }
    
    public void setTags(final String tags) {
        this.Tags = tags;
    }
}
