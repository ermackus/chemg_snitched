package com.kingagroot.kingdraw.pressenter;

import com.kingagroot.kingdraw.pressenter.impl.CloudPresenterImpl;
import java.util.List;
import com.kingagroot.kingdraw.model.CloudFileModel;

public interface CloudPresenterI
{
    void deleteFile(final CloudFileModel p0);
    
    void downloadCheck(final CloudFileModel p0);
    
    List<CloudFileModel> getDates();
    
    int getTotalCount();
    
    void loadMoreData();
    
    void loadMoreSearchData();
    
    void refreshData();
    
    void refreshSearchData(final String p0);
    
    void renameFile(final CloudFileModel p0, final String p1);
    
    void setOnGetCloudFileCount(final CloudPresenterImpl.OnGetCloudFileCount p0);
}
