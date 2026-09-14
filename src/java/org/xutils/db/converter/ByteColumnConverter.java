package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ByteColumnConverter implements ColumnConverter<Byte>
{
    @Override
    public Object fieldValue2DbValue(final Byte b) {
        return b;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Byte getFieldValue(final Cursor cursor, final int n) {
        Byte value;
        if (cursor.isNull(n)) {
            value = null;
        }
        else {
            value = (byte)cursor.getInt(n);
        }
        return value;
    }
}
