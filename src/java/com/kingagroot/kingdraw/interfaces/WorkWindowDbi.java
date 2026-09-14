package com.kingagroot.kingdraw.interfaces;

import java.util.List;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.ui.workstation.WorkStationModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface WorkWindowDbi extends BaseDBI
{
    OperationState addWorkItem(final WorkStationModel p0);
    
    boolean cleanAllWorkItems();
    
    boolean deleteWorkItem(final WorkStationModel p0);
    
    List<WorkStationModel> getAllWorkItems();
}
