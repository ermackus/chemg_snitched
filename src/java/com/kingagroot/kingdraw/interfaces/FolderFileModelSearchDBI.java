package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.GSearchModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface FolderFileModelSearchDBI extends BaseDBI
{
    void findDrawFiles(final GSearchModel p0, final int p1, final OnSearchResultListener p2);
    
    void reloadData();
}
