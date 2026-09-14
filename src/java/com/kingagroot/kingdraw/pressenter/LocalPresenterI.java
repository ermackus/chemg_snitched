package com.kingagroot.kingdraw.pressenter;

import com.kingagroot.kingdraw.model.GSearchModel;
import java.util.List;
import com.kingagroot.kingdraw.dialog.FileExportDialog;
import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.kingdraw.model.FolderFileModel;

public interface LocalPresenterI
{
    void addFile(final FolderFileModel p0);
    
    void checkFile(final FolderFileModel p0);
    
    void copyFile(final FolderFileModel p0);
    
    void deleteFile(final FolderFileModel p0);
    
    void editFile(final FolderFileModel p0);
    
    void exportAsFile(final FolderFileModel p0, final String p1, final FileType p2, final FileExportDialog p3);
    
    List<FolderFileModel> getDatas();
    
    void loadMoreData();
    
    void loadMoreSearchData();
    
    void reLoadData();
    
    @Deprecated
    void refreshData();
    
    void refreshSearchData(final GSearchModel p0);
    
    @Deprecated
    void refreshSearchData(final String p0);
    
    void rename(final FolderFileModel p0, final String p1);
    
    void savePicByFile(final String p0);
}
