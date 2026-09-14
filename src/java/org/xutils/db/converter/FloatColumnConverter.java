package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class FloatColumnConverter implements ColumnConverter<Float>
{
    @Override
    public Object fieldValue2DbValue(final Float n) {
        return n;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
    
    @Override
    public Float getFieldValue(final Cursor cursor, final int n) {
        Float value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = cursor.getFloat(n);
        }
        return value;
    }
}
