package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import java.util.List;

public interface DrawSearchHisDBI
{
    boolean clear();
    
    List<GFileSearchModel> getHisData();
    
    String getSearchFile(final int p0);
    
    OperationState save(final KingDrawView p0);
}
