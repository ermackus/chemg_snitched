package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.VipLimitModel;
import com.kingagroot.kingdraw.interfaces.VipLimitConfigDbi;

@Deprecated
public class VipLimitConfigDbiMpl extends BaseDBImpl implements VipLimitConfigDbi
{
    public static final String LIMIT_LOGIN_DB_NAME = "VipLimitData.db";
    private static final int LIMIT_LOGIN_VERSION_DB = 1;
    public static final String TAG = "VipLimitConfigDbiMpl";
    
    public VipLimitConfigDbiMpl() {
        super("VipLimitData.db", 1);
    }
    
    public int getCloudFileLimitNum() {
        return this.getVipLimitData().getCloudFileStoreNum();
    }
    
    public boolean getCustomGroupsState() {
        final int customGroups = this.getVipLimitData().getCustomGroups();
        boolean b = true;
        if (customGroups != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getCustomTemplateState() {
        final int customizeTemplate = this.getVipLimitData().getCustomizeTemplate();
        boolean b = true;
        if (customizeTemplate != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getDimensionalSetting() {
        final int dimensionalSetting = this.getVipLimitData().getDimensionalSetting();
        boolean b = true;
        if (dimensionalSetting != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getEncyclopediaAvailable() {
        final int encyclopediaAvailable = this.getVipLimitData().getEncyclopediaAvailable();
        boolean b = true;
        if (encyclopediaAvailable != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getExportPicState() {
        final int exportImageSetting = this.getVipLimitData().getExportImageSetting();
        boolean b = true;
        if (exportImageSetting != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getFileSearchState() {
        final int fileRetrievalService = this.getVipLimitData().getFileRetrievalService();
        boolean b = true;
        if (fileRetrievalService != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getGroupOpenState() {
        final int groupUnfolds = this.getVipLimitData().getGroupUnfolds();
        boolean b = true;
        if (groupUnfolds != 1) {
            b = false;
        }
        return b;
    }
    
    public int getLocalFileLimitNum() {
        return this.getVipLimitData().getLocalFileStoreNum();
    }
    
    public boolean getPicWatermarkState() {
        final int exportImageSetting = this.getVipLimitData().getExportImageSetting();
        boolean b = true;
        if (exportImageSetting != 1) {
            b = false;
        }
        return b;
    }
    
    public boolean getPrintState() {
        final int printSetting = this.getVipLimitData().getPrintSetting();
        boolean b = true;
        if (printSetting != 1) {
            b = false;
        }
        return b;
    }
    
    public VipLimitModel getVipLimitData() {
        try {
            return (VipLimitModel)this.db.findFirst((Class)VipLimitModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void saveVipLimitData(final VipLimitModel vipLimitModel) {
        try {
            this.db.saveOrUpdate((Object)vipLimitModel);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
}
