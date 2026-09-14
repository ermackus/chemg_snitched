package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class CharColumnConverter implements ColumnConverter<Character>
{
    @Override
    public Object fieldValue2DbValue(final Character c) {
        if (c == null) {
            return null;
        }
        return c;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Character getFieldValue(final Cursor cursor, final int n) {
        Character value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = (char)cursor.getInt(n);
        }
        return value;
    }
}
