package org.xutils.db.table;

import java.util.Iterator;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import android.text.TextUtils;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.ex.DbException;
import java.util.HashMap;
import org.xutils.DbManager;

public abstract class DbBase implements DbManager
{
    private final HashMap<Class<?>, TableEntity<?>> tableMap;
    
    public DbBase() {
        this.tableMap = (HashMap<Class<?>, TableEntity<?>>)new HashMap();
    }
    
    @Override
    public void addColumn(final Class<?> clazz, final String s) throws DbException {
        final TableEntity<Object> table = this.getTable(clazz);
        final ColumnEntity columnEntity = (ColumnEntity)table.getColumnMap().get((Object)s);
        if (columnEntity != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE ");
            sb.append("\"");
            sb.append(table.getName());
            sb.append("\"");
            sb.append(" ADD COLUMN ");
            sb.append("\"");
            sb.append(columnEntity.getName());
            sb.append("\"");
            sb.append(" ");
            sb.append((Object)columnEntity.getColumnDbType());
            sb.append(" ");
            sb.append(columnEntity.getProperty());
            this.execNonQuery(sb.toString());
        }
    }
    
    protected void createTableIfNotExist(final TableEntity<?> tableEntity) throws DbException {
        if (!tableEntity.tableIsExist()) {
            final Class<? extends TableEntity> class1 = tableEntity.getClass();
            synchronized (class1) {
                if (!tableEntity.tableIsExist()) {
                    this.execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo(tableEntity));
                    final String onCreated = tableEntity.getOnCreated();
                    if (!TextUtils.isEmpty((CharSequence)onCreated)) {
                        this.execNonQuery(onCreated);
                    }
                    tableEntity.setCheckedDatabase(true);
                    final TableCreateListener tableCreateListener = this.getDaoConfig().getTableCreateListener();
                    if (tableCreateListener != null) {
                        tableCreateListener.onTableCreated(this, tableEntity);
                    }
                }
            }
        }
    }
    
    @Override
    public void dropDb() throws DbException {
        final Cursor execQuery = this.execQuery("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (execQuery != null) {
            try {
                while (execQuery.moveToNext()) {
                    try {
                        final String string = execQuery.getString(0);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("DROP TABLE ");
                        sb.append(string);
                        this.execNonQuery(sb.toString());
                    }
                    finally {
                        final Throwable t;
                        LogUtil.e(t.getMessage(), t);
                    }
                }
                final HashMap<Class<?>, TableEntity<?>> tableMap = this.tableMap;
                synchronized (tableMap) {
                    final Iterator iterator = this.tableMap.values().iterator();
                    while (iterator.hasNext()) {
                        ((TableEntity)iterator.next()).setCheckedDatabase(false);
                    }
                    this.tableMap.clear();
                    monitorexit(tableMap);
                    IOUtil.closeQuietly(execQuery);
                }
            }
            finally {
                try {
                    final Throwable t2;
                    throw new DbException(t2);
                }
                finally {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
    }
    
    @Override
    public void dropTable(final Class<?> clazz) throws DbException {
        final TableEntity<Object> table = this.getTable(clazz);
        if (!table.tableIsExist()) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE \"");
        sb.append(table.getName());
        sb.append("\"");
        this.execNonQuery(sb.toString());
        table.setCheckedDatabase(false);
        this.removeTable(clazz);
    }
    
    @Override
    public <T> TableEntity<T> getTable(final Class<T> clazz) throws DbException {
        final HashMap<Class<?>, TableEntity<?>> tableMap = this.tableMap;
        synchronized (tableMap) {
            Object o;
            if ((o = this.tableMap.get((Object)clazz)) == null) {
                try {
                    this.tableMap.put((Object)clazz, (Object)new TableEntity(this, (Class<Object>)clazz));
                }
                finally {
                    final Throwable t;
                    o = new DbException(t);
                    throw o;
                }
            }
            return (TableEntity<T>)o;
        }
    }
    
    protected void removeTable(final Class<?> clazz) {
        final HashMap<Class<?>, TableEntity<?>> tableMap = this.tableMap;
        synchronized (tableMap) {
            this.tableMap.remove((Object)clazz);
        }
    }
}
