package com.kingagroot.component.ui.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GSGroupModel")
public class GSGroupModel implements Serializable
{
    @Column(name = "base64String")
    String base64String;
    @Column(name = "canEdit")
    int canEdit;
    @Column(name = "content")
    String content;
    @Column(name = "imageData")
    String imageData;
    @Column(name = "isCollection")
    int isCollection;
    @Column(name = "isCustomize")
    boolean isCustomize;
    @Column(name = "isDelete")
    int isDelete;
    @Column(name = "isRHave")
    int isRHave;
    @Column(name = "kdxFileBytes")
    String kdxFileBytes;
    @Column(name = "key")
    String key;
    @Column(name = "name")
    String name;
    @Column(name = "nameHtml")
    String nameHtml;
    @Column(autoGen = true, isId = true, name = "rowid")
    int rowid;
    @Column(name = "smiles")
    String smiles;
    
    public String getBase64String() {
        return this.base64String;
    }
    
    public int getCanEdit() {
        return this.canEdit;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public String getImageData() {
        return this.imageData;
    }
    
    public int getIsCollection() {
        return this.isCollection;
    }
    
    public int getIsDelete() {
        return this.isDelete;
    }
    
    public int getIsRHave() {
        return this.isRHave;
    }
    
    public String getKdxFileBytes() {
        return this.kdxFileBytes;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameHtml() {
        return this.nameHtml;
    }
    
    public int getRowid() {
        return this.rowid;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public boolean isCustomize() {
        return this.isCustomize;
    }
    
    public void setBase64String(final String base64String) {
        this.base64String = base64String;
    }
    
    public void setCanEdit(final int canEdit) {
        this.canEdit = canEdit;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public void setCustomize(final boolean isCustomize) {
        this.isCustomize = isCustomize;
    }
    
    public void setImageData(final String imageData) {
        this.imageData = imageData;
    }
    
    public void setIsCollection(final int isCollection) {
        this.isCollection = isCollection;
    }
    
    public void setIsDelete(final int isDelete) {
        this.isDelete = isDelete;
    }
    
    public void setIsRHave(final int isRHave) {
        this.isRHave = isRHave;
    }
    
    public void setKdxFileBytes(final String kdxFileBytes) {
        this.kdxFileBytes = kdxFileBytes;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setNameHtml(final String nameHtml) {
        this.nameHtml = nameHtml;
    }
    
    public void setRowid(final int rowid) {
        this.rowid = rowid;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
}
