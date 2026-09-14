package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Deprecated
@Table(name = "VipLimitModel")
public class VipLimitModel
{
    public static final String TAG = "VipLimitModel";
    @Column(name = "allowImagesExported")
    private int allowImagesExported;
    @Column(name = "applyType")
    private int applyType;
    @Column(name = "cloudFileStoreNum")
    private int cloudFileStoreNum;
    @Column(name = "customGroups")
    private int customGroups;
    @Column(name = "customizeTemplate")
    private int customizeTemplate;
    @Column(name = "dimensionalSetting")
    private int dimensionalSetting;
    @Column(name = "encyclopediaAvailable")
    private int encyclopediaAvailable;
    @Column(name = "exportImageSetting")
    private int exportImageSetting;
    @Column(name = "fileRetrievalService")
    private int fileRetrievalService;
    @Column(name = "groupUnfolds")
    private int groupUnfolds;
    @Column(autoGen = false, isId = true, name = "id")
    private int id;
    @Column(name = "localFileStoreNum")
    private int localFileStoreNum;
    @Column(name = "moduleProject")
    private int moduleProject;
    @Column(name = "printSetting")
    private int printSetting;
    @Column(name = "userType")
    private int userType;
    @Column(name = "whichEnd")
    private int whichEnd;
    
    public int getAllowImagesExported() {
        return this.allowImagesExported;
    }
    
    public int getApplyType() {
        return this.applyType;
    }
    
    public int getCloudFileStoreNum() {
        return this.cloudFileStoreNum;
    }
    
    public int getCustomGroups() {
        return this.customGroups;
    }
    
    public int getCustomizeTemplate() {
        return this.customizeTemplate;
    }
    
    public int getDimensionalSetting() {
        return this.dimensionalSetting;
    }
    
    public int getEncyclopediaAvailable() {
        return this.encyclopediaAvailable;
    }
    
    public int getExportImageSetting() {
        return this.exportImageSetting;
    }
    
    public int getFileRetrievalService() {
        return this.fileRetrievalService;
    }
    
    public int getGroupUnfolds() {
        return this.groupUnfolds;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getLocalFileStoreNum() {
        return this.localFileStoreNum;
    }
    
    public int getModuleProject() {
        return this.moduleProject;
    }
    
    public int getPrintSetting() {
        return this.printSetting;
    }
    
    public int getUserType() {
        return this.userType;
    }
    
    public int getWhichEnd() {
        return this.whichEnd;
    }
    
    public void setAllowImagesExported(final int allowImagesExported) {
        this.allowImagesExported = allowImagesExported;
    }
    
    public void setApplyType(final int applyType) {
        this.applyType = applyType;
    }
    
    public void setCloudFileStoreNum(final int cloudFileStoreNum) {
        this.cloudFileStoreNum = cloudFileStoreNum;
    }
    
    public void setCustomGroups(final int customGroups) {
        this.customGroups = customGroups;
    }
    
    public void setCustomizeTemplate(final int customizeTemplate) {
        this.customizeTemplate = customizeTemplate;
    }
    
    public void setDimensionalSetting(final int dimensionalSetting) {
        this.dimensionalSetting = dimensionalSetting;
    }
    
    public void setEncyclopediaAvailable(final int encyclopediaAvailable) {
        this.encyclopediaAvailable = encyclopediaAvailable;
    }
    
    public void setExportImageSetting(final int exportImageSetting) {
        this.exportImageSetting = exportImageSetting;
    }
    
    public void setFileRetrievalService(final int fileRetrievalService) {
        this.fileRetrievalService = fileRetrievalService;
    }
    
    public void setGroupUnfolds(final int groupUnfolds) {
        this.groupUnfolds = groupUnfolds;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setLocalFileStoreNum(final int localFileStoreNum) {
        this.localFileStoreNum = localFileStoreNum;
    }
    
    public void setModuleProject(final int moduleProject) {
        this.moduleProject = moduleProject;
    }
    
    public void setPrintSetting(final int printSetting) {
        this.printSetting = printSetting;
    }
    
    public void setUserType(final int userType) {
        this.userType = userType;
    }
    
    public void setWhichEnd(final int whichEnd) {
        this.whichEnd = whichEnd;
    }
}
