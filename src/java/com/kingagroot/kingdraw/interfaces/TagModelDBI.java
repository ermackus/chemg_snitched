package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.GTagModel;
import com.kingagroot.component.ui.view.OperationState;
import java.util.List;
import com.kingagroot.component.ui.db.BaseDBI;

public interface TagModelDBI extends BaseDBI
{
    boolean addSelectTagModels(final List<String> p0);
    
    OperationState addTag(final String p0);
    
    boolean clearSelectTags();
    
    boolean deleteTag(final String p0);
    
    List<GTagModel> getSelectTagModels();
    
    List<GTagModel> getTagModels();
    
    List<GTagModel> getUnSelectTagModels();
    
    List<GTagModel> getUnSelectTagModels(final List<String> p0);
    
    boolean removeTag(final String p0);
    
    OperationState updataTag(final int p0, final String p1);
}
