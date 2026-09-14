package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.LimitResultModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface LimitConfigDbi extends BaseDBI
{
    int getCloudFileLimitNum();
    
    boolean getCustomGroupsState();
    
    boolean getCustomTemplateState();
    
    boolean getDimensionalSetting();
    
    boolean getEncyclopediaAvailable();
    
    boolean getExportPicState();
    
    boolean getFileSearchState();
    
    boolean getGroupOpenState();
    
    LimitResultModel getLimitData();
    
    int getLocalFileLimitNum();
    
    boolean getPicWatermarkState();
    
    boolean getPrintState();
    
    void saveLimitData(final LimitResultModel p0);
}
