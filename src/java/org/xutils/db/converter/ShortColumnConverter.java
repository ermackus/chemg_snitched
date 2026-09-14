package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ShortColumnConverter implements ColumnConverter<Short>
{
    @Override
    public Object fieldValue2DbValue(final Short n) {
        return n;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Short getFieldValue(final Cursor cursor, final int n) {
        Short value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = cursor.getShort(n);
        }
        return value;
    }
}
