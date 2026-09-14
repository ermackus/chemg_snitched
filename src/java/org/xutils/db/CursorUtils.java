package org.xutils.db;

import java.util.HashMap;
import java.util.LinkedHashMap;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.TableEntity;
import org.xutils.db.table.DbModel;
import android.database.Cursor;

final class CursorUtils
{
    public static DbModel getDbModel(final Cursor cursor) {
        final DbModel dbModel = new DbModel();
        for (int columnCount = cursor.getColumnCount(), i = 0; i < columnCount; ++i) {
            dbModel.add(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }
    
    public static <T> T getEntity(final TableEntity<T> tableEntity, final Cursor cursor) throws Throwable {
        final T entity = tableEntity.createEntity();
        final LinkedHashMap columnMap = tableEntity.getColumnMap();
        for (int columnCount = cursor.getColumnCount(), i = 0; i < columnCount; ++i) {
            final ColumnEntity columnEntity = (ColumnEntity)((HashMap)columnMap).get((Object)cursor.getColumnName(i));
            if (columnEntity != null) {
                columnEntity.setValueFromCursor(entity, cursor, i);
            }
        }
        return entity;
    }
}
