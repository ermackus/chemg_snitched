package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GFileSearchModel")
public class GFileSearchModel implements Serializable
{
    @Column(name = "CreatTime")
    long CreatTime;
    @Column(autoGen = true, isId = true, name = "Id")
    private int Id;
    @Column(name = "fileStream")
    byte[] fileStream;
    @Column(name = "formola")
    String formola;
    @Column(name = "kingContent")
    String kingContent;
    @Column(name = "picPath")
    private String picPath;
    @Column(name = "smiles")
    private String smiles;
    
    public long getCreatTime() {
        return this.CreatTime;
    }
    
    public byte[] getFileStream() {
        return this.fileStream;
    }
    
    public String getFormola() {
        return this.formola;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public String getKingContent() {
        return this.kingContent;
    }
    
    public String getPicPath() {
        return this.picPath;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public void setCreatTime(final long creatTime) {
        this.CreatTime = creatTime;
    }
    
    public void setFileStream(final byte[] fileStream) {
        this.fileStream = fileStream;
    }
    
    public void setFormola(final String formola) {
        this.formola = formola;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setKingContent(final String kingContent) {
        this.kingContent = kingContent;
    }
    
    public void setPicPath(final String picPath) {
        this.picPath = picPath;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
}
