package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.List;
import com.kingagroot.kingdraw.model.GSearchModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface DrawFileDataI extends BaseDBI
{
    boolean addTag(final String p0, final String p1);
    
    List<FolderFileModel> checkAll(final GSearchModel p0);
    
    OperationState checkFileNameAvailable(final String p0);
    
    OperationState copyFile(final FolderFileModel p0, final String p1);
    
    OperationState copyFile(final String p0);
    
    OperationState deleteFile(final String p0);
    
    FolderFileModel findDrawFile(final String p0);
    
    FolderFileModel findDrawFileById(final String p0);
    
    List<FolderFileModel> findDrawFiles(final String p0, final int p1);
    
    void findDrawFiles(final GSearchModel p0, final int p1, final OnSearchResultListener p2);
    
    List<FolderFileModel> findDrawFilesWithOutIds(final String p0, final int p1, final String p2);
    
    String getCopyFileName(final String p0);
    
    String getCopyFileNameNoExtension(final String p0);
    
    long getDrawFileAmount();
    
    long getDrawFileAmount(final GSearchModel p0);
    
    List<FolderFileModel> getNoSmilesFiles();
    
    boolean modifyTagByOldTag(final String p0, final String p1, final String p2);
    
    boolean removeTag(final String p0, final String p1);
    
    OperationState rename(final String p0, final String p1, final FileType p2);
    
    OperationState saveFolderFileModel(final FolderFileModel p0);
    
    boolean setTags(final String p0, final List<String> p1);
    
    OperationState updataFolderFileModel(final FolderFileModel p0);
    
    boolean updataModel(final FolderFileModel p0);
}
