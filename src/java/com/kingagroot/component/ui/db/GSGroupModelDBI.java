package com.kingagroot.component.ui.db;

import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import com.kingagroot.component.ui.view.OperationState;

public interface GSGroupModelDBI
{
    OperationState addCollection(final int p0, final boolean p1);
    
    OperationState checkName(final String p0);
    
    OperationState deleteGSGroupModel(final int p0, final boolean p1);
    
    List<GSGroupModel> findAll();
    
    List<GSGroupModel> getCollectionSupModel();
    
    String getKdxFile(final int p0, final boolean p1);
    
    OperationState reName(final int p0, final String p1, final String p2, final boolean p3);
    
    OperationState removeCollection(final int p0, final boolean p1);
    
    OperationState saveFile(final String p0, final String p1, final KingDrawView p2);
    
    List<GSGroupModel> searchGroup(final String p0);
    
    OperationState updateFile(final int p0, final boolean p1, final KingDrawView p2);
    
    OperationState updateFile(final GSGroupModel p0, final boolean p1);
}
