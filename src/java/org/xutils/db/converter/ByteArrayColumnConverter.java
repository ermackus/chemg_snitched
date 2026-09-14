package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ByteArrayColumnConverter implements ColumnConverter<byte[]>
{
    @Override
    public Object fieldValue2DbValue(final byte[] array) {
        return array;
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.BLOB;
    }
    
    @Override
    public byte[] getFieldValue(final Cursor cursor, final int n) {
        byte[] blob;
        if (cursor.isNull(n)) {
            blob = null;
        }
        else {
            blob = cursor.getBlob(n);
        }
        return blob;
    }
}
