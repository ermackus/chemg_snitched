package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class BooleanColumnConverter implements ColumnConverter<Boolean>
{
    @Override
    public Object fieldValue2DbValue(final Boolean b) {
        if (b == null) {
            return null;
        }
        return ((boolean)b) ? 1 : 0;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Boolean getFieldValue(final Cursor cursor, int int1) {
        Boolean value;
        if (cursor.isNull(int1)) {
            value = null;
        }
        else {
            int1 = cursor.getInt(int1);
            boolean b = true;
            if (int1 != 1) {
                b = false;
            }
            value = b;
        }
        return value;
    }
}
