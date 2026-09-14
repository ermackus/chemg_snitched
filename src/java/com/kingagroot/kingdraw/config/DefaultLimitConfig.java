package com.kingagroot.kingdraw.config;

import com.kingagroot.kingdraw.model.LimitResultModel;
import com.goodsrc.library.utils.DateTimeUtils;
import com.kingagroot.kdlimitconfig.NativeLib;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.model.LimitModel;

public class DefaultLimitConfig
{
    public static final String TAG = "DefaultLimitConfig";
    
    public static LimitModel getLoginLimitData() {
        final LimitModel limitModel = new LimitModel();
        limitModel.setWhichEnd(0);
        limitModel.setModuleProject(3);
        limitModel.setLocalFileStoreNum(-1);
        limitModel.setCloudFileStoreNum(-1);
        limitModel.setExportImageSetting(0);
        limitModel.setAllowImagesExported(1);
        limitModel.setFileRetrievalService(1);
        limitModel.setPrintSetting(1);
        limitModel.setCustomGroups(1);
        limitModel.setCustomizeTemplate(1);
        limitModel.setGroupUnfolds(1);
        limitModel.setDimensionalSetting(1);
        limitModel.setEncyclopediaAvailable(1);
        return limitModel;
    }
    
    public static LimitModel getLogoutLimitData() {
        final LimitModel limitModel = new LimitModel();
        limitModel.setWhichEnd(0);
        limitModel.setModuleProject(1);
        limitModel.setLocalFileStoreNum(20);
        limitModel.setCloudFileStoreNum(0);
        limitModel.setExportImageSetting(1);
        limitModel.setAllowImagesExported(0);
        limitModel.setFileRetrievalService(1);
        limitModel.setPrintSetting(0);
        limitModel.setCustomGroups(0);
        limitModel.setCustomizeTemplate(0);
        limitModel.setGroupUnfolds(0);
        limitModel.setDimensionalSetting(0);
        limitModel.setEncyclopediaAvailable(0);
        return limitModel;
    }
    
    public static boolean overtime(final LimitConfigDbi limitConfigDbi) {
        final LimitResultModel limitData = limitConfigDbi.getLimitData();
        return DateTimeUtils.calculationTime(System.currentTimeMillis(), NativeLib.decryptLimitData(limitData.getData(), limitData.getKey()).getCurrentTime()) > 48L;
    }
}
