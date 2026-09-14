package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.LimitResultModel;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.kingagroot.kdlimitconfig.NativeLib;
import com.kingagroot.kdlimitconfig.LimitModel;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;

public class LimitLoginConfigDbiMpl extends BaseDBImpl implements LimitConfigDbi
{
    public static final String LIMIT_LOGIN_DB_NAME = "LimitLoginData.db";
    private static final int LIMIT_LOGIN_VERSION_DB = 1;
    
    public LimitLoginConfigDbiMpl() {
        super("LimitLoginData.db", 1);
    }
    
    private LimitModel getDeData() {
        return NativeLib.decryptLimitData(this.getLimitData().getData(), this.getLimitData().getKey());
    }
    
    public int getCloudFileLimitNum() {
        if (this.getLimitData() != null) {
            return this.getDeData().getCloudFileStoreNum();
        }
        return DefaultLimitConfig.getLoginLimitData().getCloudFileStoreNum();
    }
    
    public boolean getCustomGroupsState() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getCustomGroups() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLoginLimitData().getCustomGroups() == 1) {
            b3 = true;
        }
        return b3;
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
        if (DefaultLimitConfig.getLoginLimitData().getCustomizeTemplate() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getDimensionalSetting() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getDimensionalSetting() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLoginLimitData().getDimensionalSetting() == 1) {
            b3 = true;
        }
        return b3;
    }
    
    public boolean getEncyclopediaAvailable() {
        final LimitResultModel limitData = this.getLimitData();
        boolean b = false;
        final boolean b2 = false;
        if (limitData != null) {
            boolean b3 = b2;
            if (this.getDeData().getEncyclopediaAvailable() == 1) {
                b3 = true;
            }
            return b3;
        }
        if (DefaultLimitConfig.getLoginLimitData().getEncyclopediaAvailable() == 1) {
            b = true;
        }
        return b;
    }
    
    public boolean getExportPicState() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getAllowImagesExported() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLoginLimitData().getAllowImagesExported() == 1) {
            b3 = true;
        }
        return b3;
    }
    
    public boolean getFileSearchState() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getFileRetrievalService() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLoginLimitData().getFileRetrievalService() == 1) {
            b3 = true;
        }
        return b3;
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
        if (DefaultLimitConfig.getLoginLimitData().getGroupUnfolds() == 1) {
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
        return DefaultLimitConfig.getLoginLimitData().getLocalFileStoreNum();
    }
    
    public boolean getPicWatermarkState() {
        final LimitResultModel limitData = this.getLimitData();
        final boolean b = false;
        boolean b2 = false;
        if (limitData != null) {
            if (this.getDeData().getExportImageSetting() == 1) {
                b2 = true;
            }
            return b2;
        }
        boolean b3 = b;
        if (DefaultLimitConfig.getLoginLimitData().getExportImageSetting() == 1) {
            b3 = true;
        }
        return b3;
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
        if (DefaultLimitConfig.getLoginLimitData().getPrintSetting() == 1) {
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
