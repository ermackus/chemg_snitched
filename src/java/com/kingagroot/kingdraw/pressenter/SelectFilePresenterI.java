package com.kingagroot.kingdraw.pressenter;

import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.List;

public interface SelectFilePresenterI
{
    List<FolderFileModel> getFolderFileModels();
    
    void onLoadMore();
    
    void onRefresh(final String p0);
}
