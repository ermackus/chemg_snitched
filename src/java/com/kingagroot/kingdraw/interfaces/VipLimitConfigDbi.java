package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.VipLimitModel;
import com.kingagroot.component.ui.db.BaseDBI;

@Deprecated
public interface VipLimitConfigDbi extends BaseDBI
{
    public static final String TAG = "VipLimitConfigDbi";
    
    int getCloudFileLimitNum();
    
    boolean getCustomGroupsState();
    
    boolean getCustomTemplateState();
    
    boolean getDimensionalSetting();
    
    boolean getEncyclopediaAvailable();
    
    boolean getExportPicState();
    
    boolean getFileSearchState();
    
    boolean getGroupOpenState();
    
    int getLocalFileLimitNum();
    
    boolean getPicWatermarkState();
    
    boolean getPrintState();
    
    void saveVipLimitData(final VipLimitModel p0);
}
