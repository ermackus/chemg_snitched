package com.kingagroot.kingdraw.interfaces;

import java.util.List;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface GestureDbi extends BaseDBI
{
    void deleteModel(final GestureGroupModel p0);
    
    List<GestureGroupModel> getAllData();
    
    GestureGroupModel getBondData(final int p0);
    
    List<GestureGroupModel> getDataByKey(final boolean p0);
    
    List<GestureGroupModel> getGestureNoBindData();
    
    void save(final List<GestureGroupModel> p0);
    
    boolean upData(final GestureGroupModel p0);
}
