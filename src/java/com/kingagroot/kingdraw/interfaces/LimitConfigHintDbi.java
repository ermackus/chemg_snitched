package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.LimitHintModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface LimitConfigHintDbi extends BaseDBI
{
    String getLocalFileNumHint();
    
    String getPediaHint();
    
    String getPicHint();
    
    String getSearchHint();
    
    String getStereoscopicConfigurationHint();
    
    String getSupExportHint();
    
    void saveLimitHintData(final LimitHintModel p0);
}
