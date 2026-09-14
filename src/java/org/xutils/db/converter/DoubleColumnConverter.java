package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class DoubleColumnConverter implements ColumnConverter<Double>
{
    @Override
    public Object fieldValue2DbValue(final Double n) {
        return n;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
    
    @Override
    public Double getFieldValue(final Cursor cursor, final int n) {
        Double value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = cursor.getDouble(n);
        }
        return value;
    }
}
