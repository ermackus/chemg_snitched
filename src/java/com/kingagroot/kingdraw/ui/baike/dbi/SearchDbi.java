package com.kingagroot.kingdraw.ui.baike.dbi;

import com.kingagroot.kingdraw.ui.baike.model.SearchHistoryModel;
import java.util.List;
import com.kingagroot.component.ui.db.BaseDBI;

public interface SearchDbi extends BaseDBI
{
    boolean addSearchData(final String p0);
    
    boolean deleteAllData();
    
    List<SearchHistoryModel> getAllList();
}
