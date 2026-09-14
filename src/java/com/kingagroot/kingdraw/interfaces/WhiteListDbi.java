package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.WebWhiteModel;
import java.util.List;
import com.kingagroot.component.ui.db.BaseDBI;

public interface WhiteListDbi extends BaseDBI
{
    void cleanAllData();
    
    List<WebWhiteModel> getWhiteList();
    
    void saveWhiteData(final List<WebWhiteModel> p0);
}
