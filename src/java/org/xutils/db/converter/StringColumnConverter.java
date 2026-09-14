package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class StringColumnConverter implements ColumnConverter<String>
{
    @Override
    public Object fieldValue2DbValue(final String s) {
        return s;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
    
    @Override
    public String getFieldValue(final Cursor cursor, final int n) {
        String string;
        if (cursor.isNull(n)) {
            string = null;
        }
        else {
            string = cursor.getString(n);
        }
        return string;
    }
}
