package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "LimitHintModel")
public class LimitHintModel
{
    @Column(name = "allowImagesExportedHint")
    private String allowImagesExportedHint;
    @Column(name = "cloudFileStoreNumHint")
    private String cloudFileStoreNumHint;
    @Column(name = "customGroupsHint")
    private String customGroupsHint;
    @Column(name = "customizeTemplateHint")
    private String customizeTemplateHint;
    @Column(name = "dimensionalSettingHint")
    private String dimensionalSettingHint;
    @Column(name = "encyclopediaAvailableHint")
    private String encyclopediaAvailableHint;
    @Column(name = "exportImageSettingHint")
    private String exportImageSettingHint;
    @Column(name = "fileRetrievalServiceHint")
    private String fileRetrievalServiceHint;
    @Column(name = "groupUnfoldsHint")
    private String groupUnfoldsHint;
    @Column(autoGen = false, isId = true, name = "id")
    private int id;
    @Column(name = "localFileStoreNumHint")
    private String localFileStoreNumHint;
    @Column(name = "printSettingHint")
    private String printSettingHint;
    
    public LimitHintModel() {
        this.id = 123;
    }
    
    public String getAllowImagesExportedHint() {
        return this.allowImagesExportedHint;
    }
    
    public String getCloudFileStoreNumHint() {
        return this.cloudFileStoreNumHint;
    }
    
    public String getCustomGroupsHint() {
        return this.customGroupsHint;
    }
    
    public String getCustomizeTemplateHint() {
        return this.customizeTemplateHint;
    }
    
    public String getDimensionalSettingHint() {
        return this.dimensionalSettingHint;
    }
    
    public String getEncyclopediaAvailableHint() {
        return this.encyclopediaAvailableHint;
    }
    
    public String getExportImageSettingHint() {
        return this.exportImageSettingHint;
    }
    
    public String getFileRetrievalServiceHint() {
        return this.fileRetrievalServiceHint;
    }
    
    public String getGroupUnfoldsHint() {
        return this.groupUnfoldsHint;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getLocalFileStoreNumHint() {
        return this.localFileStoreNumHint;
    }
    
    public String getPrintSettingHint() {
        return this.printSettingHint;
    }
    
    public void setAllowImagesExportedHint(final String allowImagesExportedHint) {
        this.allowImagesExportedHint = allowImagesExportedHint;
    }
    
    public void setCloudFileStoreNumHint(final String cloudFileStoreNumHint) {
        this.cloudFileStoreNumHint = cloudFileStoreNumHint;
    }
    
    public void setCustomGroupsHint(final String customGroupsHint) {
        this.customGroupsHint = customGroupsHint;
    }
    
    public void setCustomizeTemplateHint(final String customizeTemplateHint) {
        this.customizeTemplateHint = customizeTemplateHint;
    }
    
    public void setDimensionalSettingHint(final String dimensionalSettingHint) {
        this.dimensionalSettingHint = dimensionalSettingHint;
    }
    
    public void setEncyclopediaAvailableHint(final String encyclopediaAvailableHint) {
        this.encyclopediaAvailableHint = encyclopediaAvailableHint;
    }
    
    public void setExportImageSettingHint(final String exportImageSettingHint) {
        this.exportImageSettingHint = exportImageSettingHint;
    }
    
    public void setFileRetrievalServiceHint(final String fileRetrievalServiceHint) {
        this.fileRetrievalServiceHint = fileRetrievalServiceHint;
    }
    
    public void setGroupUnfoldsHint(final String groupUnfoldsHint) {
        this.groupUnfoldsHint = groupUnfoldsHint;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setLocalFileStoreNumHint(final String localFileStoreNumHint) {
        this.localFileStoreNumHint = localFileStoreNumHint;
    }
    
    public void setPrintSettingHint(final String printSettingHint) {
        this.printSettingHint = printSettingHint;
    }
}
