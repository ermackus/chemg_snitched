package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;
import java.util.Date;

public class DateColumnConverter implements ColumnConverter<Date>
{
    @Override
    public Object fieldValue2DbValue(final Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }
    
    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
    
    @Override
    public Date getFieldValue(final Cursor cursor, final int n) {
        Date date;
        if (cursor.isNull(n)) {
            date = null;
        }
        else {
            date = new Date(cursor.getLong(n));
        }
        return date;
    }
}
