package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class LongColumnConverter implements ColumnConverter<Long>
{
    @Override
    public Object fieldValue2DbValue(final Long n) {
        return n;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Long getFieldValue(final Cursor cursor, final int n) {
        Long value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = cursor.getLong(n);
        }
        return value;
    }
}
