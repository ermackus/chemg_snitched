package org.xutils.db.table;

import android.database.Cursor;
import org.xutils.ex.DbException;
import org.xutils.common.util.IOUtil;
import java.util.Iterator;
import org.xutils.db.annotation.Table;
import org.xutils.DbManager;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;

public final class TableEntity<T>
{
    private volatile boolean checkedDatabase;
    private final LinkedHashMap<String, ColumnEntity> columnMap;
    private Constructor<T> constructor;
    private final DbManager db;
    private Class<T> entityType;
    private ColumnEntity id;
    private final String name;
    private final String onCreated;
    
    TableEntity(final DbManager db, final Class<T> entityType) throws Throwable {
        this.db = db;
        this.entityType = entityType;
        (this.constructor = entityType.getConstructor((Class<?>[])new Class[0])).setAccessible(true);
        final Table table = entityType.getAnnotation(Table.class);
        this.name = table.name();
        this.onCreated = table.onCreated();
        final LinkedHashMap<String, ColumnEntity> columnMap = TableUtils.findColumnMap(entityType);
        this.columnMap = columnMap;
        for (final ColumnEntity id : columnMap.values()) {
            if (id.isId()) {
                this.id = id;
                break;
            }
        }
    }
    
    public T createEntity() throws Throwable {
        return (T)this.constructor.newInstance(new Object[0]);
    }
    
    public LinkedHashMap<String, ColumnEntity> getColumnMap() {
        return this.columnMap;
    }
    
    public DbManager getDb() {
        return this.db;
    }
    
    public Class<T> getEntityType() {
        return this.entityType;
    }
    
    public ColumnEntity getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getOnCreated() {
        return this.onCreated;
    }
    
    boolean isCheckedDatabase() {
        return this.checkedDatabase;
    }
    
    void setCheckedDatabase(final boolean checkedDatabase) {
        this.checkedDatabase = checkedDatabase;
    }
    
    public boolean tableIsExist() throws DbException {
        if (this.isCheckedDatabase()) {
            return true;
        }
        final DbManager db = this.db;
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='");
        sb.append(this.name);
        sb.append("'");
        final Cursor execQuery = db.execQuery(sb.toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext() && execQuery.getInt(0) > 0) {
                    this.setCheckedDatabase(true);
                    IOUtil.closeQuietly(execQuery);
                    return true;
                }
                IOUtil.closeQuietly(execQuery);
            }
            finally {
                try {
                    final Throwable t;
                    throw new DbException(t);
                }
                finally {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
