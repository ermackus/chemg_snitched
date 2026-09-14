package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public interface ColumnConverter<T>
{
    Object fieldValue2DbValue(final T p0);
    
    ColumnDbType getColumnDbType();
    
    T getFieldValue(final Cursor p0, final int p1);
}
