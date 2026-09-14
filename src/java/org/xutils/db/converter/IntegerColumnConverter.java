package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class IntegerColumnConverter implements ColumnConverter<Integer>
{
    @Override
    public Object fieldValue2DbValue(final Integer n) {
        return n;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Integer getFieldValue(final Cursor cursor, final int n) {
        Integer value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = cursor.getInt(n);
        }
        return value;
    }
}
