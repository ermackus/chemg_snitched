package com.kingagroot.component.ui.db.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import java.util.ArrayList;
import java.util.List;
import com.kingagroot.component.ui.R;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.ex.DbException;
import org.xutils.db.sqlite.SqlInfoBuilder;
import java.util.Iterator;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.TableEntity;
import android.os.Build$VERSION;
import org.xutils.x;
import org.xutils.DbManager$DbUpgradeListener;
import java.io.File;
import com.kingagroot.component.ui.UIComponentHelper;
import org.xutils.DbManager$DaoConfig;
import org.xutils.DbManager;

class BaseDBImpl
{
    private static final int DEFAUT_VERSION_DB = 1;
    protected DbManager db;
    
    protected BaseDBImpl() {
    }
    
    public BaseDBImpl(final String s) {
        this.init(s);
    }
    
    public BaseDBImpl(final String s, final int n) {
        this.init(s, n);
    }
    
    private void init(final String s) {
        this.init(s, 1);
    }
    
    private void init(final String dbName, final int dbVersion) {
        this.db = x.getDb(new DbManager$DaoConfig().setDbName(dbName).setDbDir(new File(UIComponentHelper.getDbFilePath())).setDbVersion(dbVersion).setDbUpgradeListener((DbManager$DbUpgradeListener)new BaseDBImpl$1(this)));
    }
    
    public void addColumn(final DbManager dbManager, final Class<?> clazz, final String[] array) {
        final SQLiteDatabase database = dbManager.getDatabase();
        try {
            try {
                if (Build$VERSION.SDK_INT >= 16 && database.isWriteAheadLoggingEnabled()) {
                    database.beginTransactionNonExclusive();
                }
                else {
                    database.beginTransaction();
                }
                final TableEntity table = dbManager.getTable((Class)clazz);
                final String name = table.getName();
                final StringBuilder sb = new StringBuilder();
                sb.append(name);
                sb.append("_temp");
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("ALTER TABLE ");
                sb2.append(name);
                sb2.append(" RENAME TO ");
                sb2.append(string);
                database.execSQL(sb2.toString());
                this.createTableIfNotExist(dbManager, (TableEntity<?>)table);
                final StringBuilder sb3 = new StringBuilder();
                final Iterator iterator = table.getColumnMap().values().iterator();
                while (true) {
                    final boolean hasNext = iterator.hasNext();
                    final int n = 1;
                    if (!hasNext) {
                        break;
                    }
                    final ColumnEntity columnEntity = (ColumnEntity)iterator.next();
                    int n2 = 0;
                    Label_0247: {
                        if (array != null) {
                            for (int length = array.length, i = 0; i < length; ++i) {
                                if (columnEntity.getName().equals((Object)array[i])) {
                                    n2 = n;
                                    break Label_0247;
                                }
                            }
                        }
                        n2 = 0;
                    }
                    if (n2 != 0) {
                        continue;
                    }
                    sb3.append(columnEntity.getName());
                    sb3.append(',');
                }
                sb3.deleteCharAt(sb3.length() - 1);
                final String string2 = sb3.toString();
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("INSERT INTO ");
                sb4.append(name);
                sb4.append(" (");
                sb4.append(string2);
                sb4.append(") SELECT ");
                sb4.append(sb3.toString());
                sb4.append(" FROM ");
                sb4.append(string);
                database.execSQL(sb4.toString());
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("DROP TABLE IF EXISTS ");
                sb5.append(string);
                database.execSQL(sb5.toString());
                database.setTransactionSuccessful();
            }
            finally {}
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        catch (final SQLException ex2) {
            ex2.printStackTrace();
        }
        database.endTransaction();
        return;
        database.endTransaction();
    }
    
    protected void beginTransaction() {
        final SQLiteDatabase database = this.db.getDatabase();
        if (Build$VERSION.SDK_INT >= 16 && database.isWriteAheadLoggingEnabled()) {
            database.beginTransactionNonExclusive();
        }
        else {
            database.beginTransaction();
        }
    }
    
    public void createTableIfNotExist(final DbManager dbManager, final TableEntity<?> tableEntity) throws DbException {
        if (!tableEntity.tableIsExist()) {
            final Class<? extends TableEntity> class1 = tableEntity.getClass();
            synchronized (class1) {
                if (!tableEntity.tableIsExist()) {
                    dbManager.execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo((TableEntity)tableEntity));
                }
            }
        }
    }
    
    protected void endTransaction() {
        this.db.getDatabase().endTransaction();
    }
    
    public OperationState error() {
        return this.error(UIComponentHelper.getInstance().getString(R.string.db_oper_fail));
    }
    
    public OperationState error(final String s) {
        return new OperationState(false, s);
    }
    
    protected <T> List<T> findAllBySql(final Class<T> clazz, String s) throws DbException {
        final TableEntity table = this.db.getTable((Class)clazz);
        final boolean tableIsExist = table.tableIsExist();
        final String s2 = null;
        if (!tableIsExist) {
            return null;
        }
        final Cursor execQuery = table.getDb().execQuery(s);
        s = s2;
        if (execQuery != null) {
            try {
                s = (String)new ArrayList();
                while (execQuery.moveToNext()) {
                    ((List)s).add((Object)this.getEntity(clazz, execQuery));
                }
                IOUtil.closeQuietly(execQuery);
            }
            finally {
                try {
                    final Throwable t;
                    s = (String)new DbException(t);
                    throw s;
                }
                finally {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return (List<T>)s;
    }
    
    protected <T> T getEntity(final Class<T> clazz, final Cursor cursor) throws Throwable {
        final TableEntity table = this.db.getTable((Class)clazz);
        final Object entity = table.createEntity();
        final LinkedHashMap columnMap = table.getColumnMap();
        for (int columnCount = cursor.getColumnCount(), i = 0; i < columnCount; ++i) {
            final ColumnEntity columnEntity = (ColumnEntity)((HashMap)columnMap).get((Object)cursor.getColumnName(i));
            if (columnEntity != null) {
                columnEntity.setValueFromCursor(entity, cursor, i);
            }
        }
        return (T)entity;
    }
    
    public boolean isTableExist(final Class clazz) {
        try {
            return this.db.getTable(clazz).tableIsExist();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    protected void onDBUpgrade(final DbManager dbManager, final int n, final int n2) {
    }
    
    protected void setTransactionSuccessful() {
        this.db.getDatabase().setTransactionSuccessful();
    }
    
    public OperationState success() {
        return this.success(UIComponentHelper.getInstance().getString(R.string.db_oper_success));
    }
    
    public OperationState success(final String s) {
        return new OperationState(true, s);
    }
}
