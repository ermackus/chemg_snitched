package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface CloudFileDataI extends BaseDBI
{
    FolderFileModel findFileByFileName(final String p0);
    
    String getCopyFileName(final String p0);
    
    OperationState save(final FolderFileModel p0);
}
