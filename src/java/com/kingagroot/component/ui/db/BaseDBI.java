package com.kingagroot.component.ui.db;

import org.xutils.DbManager;

public interface BaseDBI
{
    boolean clearData(final Class<?> p0);
    
    DbManager getDbManager();
    
    boolean isTableExist(final Class p0);
    
    boolean saveOrUpdate(final Object p0);
}
