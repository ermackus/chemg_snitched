package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.model.GroupModel;
import java.util.List;
import com.kingagroot.component.ui.db.BaseDBI;

public interface GroupListDBI extends BaseDBI
{
    OperationState addGroupList(final List<GroupModel> p0);
    
    void cleanAllData();
    
    OperationState deleteGroup(final String p0);
    
    GroupModel getDefaultGroup(final int p0);
    
    List<GroupModel> getGroupList();
}
