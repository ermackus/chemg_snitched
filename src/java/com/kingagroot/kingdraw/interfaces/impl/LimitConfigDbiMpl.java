package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.LimitResultModel;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.kingagroot.kdlimitconfig.NativeLib;
import com.kingagroot.kdlimitconfig.LimitModel;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;

public class LimitConfigDbiMpl extends BaseDBImpl implements LimitConfigDbi
{
    public static final String LIMIT_NO_LOGIN_DB_NAME = "LimitNoLoginData.db";
    private static final int LIMIT_NO_LOGIN_VERSION_DB = 1;
    
    public LimitConfigDbiMpl() {
        super("LimitNoLoginData.db", 1);
    }
    
    private LimitModel getDeData() {
        return NativeLib.decryptLimitData(this.getLimitData().getData(), this.getLimitData().getKey());
    }
    
    public int getCloudFileLimitNum() {
        if (this.getLimitData() != null) {
            return this.getDeData().getCloudFileStoreNum();
        }
        return DefaultLimitConfig.getLogoutLimitData().getCloudFileStoreNum();
    }
    
    public boolean getCustomGroupsState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getCustomGroups() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getCustomGroups() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getCustomTemplateState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getCustomizeTemplate() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getCustomizeTemplate() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getDimensionalSetting() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getDimensionalSetting() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getDimensionalSetting() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getEncyclopediaAvailable() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getEncyclopediaAvailable() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLogoutLimitData().getEncyclopediaAvailable() == 1) {
            b3 = true;
        }
        return b3;
    }
    
    public boolean getExportPicState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getAllowImagesExported() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getAllowImagesExported() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getFileSearchState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getFileRetrievalService() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getFileRetrievalService() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getGroupOpenState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getGroupUnfolds() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getGroupUnfolds() == 1) {
            b = true;
        }
        return b;
    }
    
    public LimitResultModel getLimitData() {
        try {
            return (LimitResultModel)this.db.findFirst((Class)LimitResultModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getLocalFileLimitNum() {
        if (this.getLimitData() != null) {
            return this.getDeData().getLocalFileStoreNum();
        }
        return DefaultLimitConfig.getLogoutLimitData().getLocalFileStoreNum();
    }
    
    public boolean getPicWatermarkState() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = true;
        final boolean b2 = true;
        if (limitData != null) {
            return this.getDeData().getExportImageSetting() == 0 && b2;
        }
        if (DefaultLimitConfig.getLogoutLimitData().getExportImageSetting() != 0) {
            b = false;
        }
        return b;
    }
    
    public boolean getPrintState() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getPrintSetting() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLogoutLimitData().getPrintSetting() == 1) {
            b3 = true;
        }
        return b3;
    }
    
    public void saveLimitData(final LimitResultModel limitResultModel) {
        try {
            this.db.saveOrUpdate((Object)limitResultModel);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
}
