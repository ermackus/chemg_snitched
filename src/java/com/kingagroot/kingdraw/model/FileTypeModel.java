package com.kingagroot.kingdraw.model;

public class FileTypeModel
{
    private String info;
    private FileType type;
    private String versionName;
    
    public FileTypeModel(final FileType type, final String versionName) {
        this.versionName = "";
        this.info = "";
        this.type = type;
        this.versionName = versionName;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public FileType getType() {
        return this.type;
    }
    
    public String getVersionName() {
        return this.versionName;
    }
    
    public void setInfo(final String info) {
        this.info = info;
    }
    
    public void setType(final FileType type) {
        this.type = type;
    }
    
    public void setVersionName(final String versionName) {
        this.versionName = versionName;
    }
}
