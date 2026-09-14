package com.kingagroot.kingdraw.interfaces.impl;

import java.util.HashMap;
import org.xutils.x;
import com.kingagroot.kingdraw.data.GestureData;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import com.kingagroot.kingdraw.model.FolderFileModel;
import org.xutils.DbManager$DbUpgradeListener;
import org.xutils.DbManager$TableCreateListener;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import java.util.LinkedHashMap;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import java.util.ArrayList;
import java.util.List;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.ex.DbException;
import java.util.Iterator;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.TableEntity;
import android.os.Build$VERSION;
import org.xutils.DbManager;
import org.xutils.DbManager$DaoConfig;
import com.kingagroot.component.ui.db.BaseDBI;

public class BaseDBImpl implements BaseDBI
{
    public static final String DEFAUT_DB_NAME = "KingDrawDB.db";
    public static final int DEFAUT_VERSION_DB = 10;
    protected final int PAGE_SIZE;
    protected DbManager$DaoConfig daoConfig;
    protected DbManager db;
    
    public BaseDBImpl() {
        this.PAGE_SIZE = 20;
        this.initDB();
    }
    
    public BaseDBImpl(final String s) {
        this.PAGE_SIZE = 20;
        this.initDB(s);
    }
    
    public BaseDBImpl(final String s, final int n) {
        this.PAGE_SIZE = 20;
        this.initDB(s, n);
    }
    
    public void addColumn(final DbManager dbManager, final Class<?> clazz, String string) {
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
                final String string2 = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("ALTER TABLE ");
                sb2.append(name);
                sb2.append(" RENAME TO ");
                sb2.append(string2);
                database.execSQL(sb2.toString());
                this.createTableIfNotExist(dbManager, (TableEntity<?>)table);
                final StringBuilder sb3 = new StringBuilder();
                for (final ColumnEntity columnEntity : table.getColumnMap().values()) {
                    if (columnEntity.getName().equals((Object)string)) {
                        continue;
                    }
                    sb3.append(columnEntity.getName());
                    sb3.append(',');
                }
                sb3.deleteCharAt(sb3.length() - 1);
                string = sb3.toString();
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("INSERT INTO ");
                sb4.append(name);
                sb4.append(" (");
                sb4.append(string);
                sb4.append(") SELECT ");
                sb4.append(sb3.toString());
                sb4.append(" FROM ");
                sb4.append(string2);
                database.execSQL(sb4.toString());
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("DROP TABLE IF EXISTS ");
                sb5.append(string2);
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
    
    public boolean clearData(final Class<?> clazz) {
        try {
            this.db.delete((Class)clazz);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
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
    
    public OperationState error() {
        return this.error(MApplication.getInstance().getString(2131820761));
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
                final ArrayList list = new ArrayList();
                while (execQuery.moveToNext()) {
                    ((List)list).add((Object)this.getEntity(clazz, execQuery));
                }
                IOUtil.closeQuietly(execQuery);
            }
            finally {
                try {
                    throw new DbException((Throwable)s);
                }
                finally {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return (List<T>)s;
    }
    
    public DbManager getDbManager() {
        return this.db;
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
    
    public void initDB() {
        this.initDB(null, 10);
    }
    
    public void initDB(final String s) {
        this.initDB(s, 10);
    }
    
    public void initDB(final String s, final int dbVersion) {
        String dbName = s;
        if (s == null) {
            dbName = "KingDrawDB.db";
        }
        final DbManager$DaoConfig setDbUpgradeListener = new DbManager$DaoConfig().setDbName(dbName).setDbDir(new File(FileConfig.DB_FILE_PATH)).setDbVersion(dbVersion).setTableCreateListener((DbManager$TableCreateListener)new DbManager$TableCreateListener(this) {
            final BaseDBImpl this$0;
            
            public void onTableCreated(final DbManager dbManager, final TableEntity<?> tableEntity) {
            }
        }).setDbUpgradeListener((DbManager$DbUpgradeListener)new DbManager$DbUpgradeListener(this) {
            final BaseDBImpl this$0;
            
            public void onUpgrade(final DbManager dbManager, final int n, final int n2) {
                if (n2 == 4) {
                    this.this$0.addColumn(dbManager, FolderFileModel.class, "Tags");
                }
                if (n < 5) {
                    this.this$0.addColumn(dbManager, FolderFileModel.class, "Tags");
                    this.this$0.addColumn(dbManager, FolderFileModel.class, "fromatStr");
                }
                if (n < 8) {
                    this.this$0.addColumn(dbManager, GFileSearchModel.class, "fileStream");
                }
                if (n < 10) {
                    try {
                        dbManager.delete((Class)GestureGroupModel.class);
                        dbManager.saveOrUpdate((Object)GestureData.gestureListData());
                    }
                    catch (final DbException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.daoConfig = setDbUpgradeListener;
        this.db = x.getDb(setDbUpgradeListener);
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
    
    public boolean saveOrUpdate(final Object o) {
        try {
            this.db.saveOrUpdate(o);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public OperationState success() {
        return this.success(MApplication.getInstance().getString(2131820762));
    }
    
    public OperationState success(final String s) {
        return new OperationState(true, s);
    }
}
