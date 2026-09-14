package org.xutils.db;

import org.xutils.common.util.KeyValue;
import java.util.ArrayList;
import org.xutils.db.table.DbModel;
import android.database.sqlite.SQLiteStatement;
import org.xutils.db.sqlite.SqlInfo;
import java.util.Iterator;
import java.util.List;
import org.xutils.db.sqlite.WhereBuilder;
import java.io.IOException;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.db.table.TableEntity;
import org.xutils.x;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import java.io.File;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import org.xutils.DbManager$DbUpgradeListener;
import org.xutils.ex.DbException;
import org.xutils.common.util.LogUtil;
import android.os.Build$VERSION;
import org.xutils.DbManager$DbOpenListener;
import org.xutils.DbManager;
import android.database.sqlite.SQLiteDatabase;
import org.xutils.DbManager$DaoConfig;
import java.util.HashMap;
import org.xutils.db.table.DbBase;

public final class DbManagerImpl extends DbBase
{
    private static final HashMap<DbManager$DaoConfig, DbManagerImpl> DAO_MAP;
    private boolean allowTransaction;
    private DbManager$DaoConfig daoConfig;
    private SQLiteDatabase database;
    
    static {
        DAO_MAP = new HashMap();
    }
    
    private DbManagerImpl(final DbManager$DaoConfig daoConfig) {
        if (daoConfig != null) {
            this.daoConfig = daoConfig;
            this.allowTransaction = daoConfig.isAllowTransaction();
            this.database = this.openOrCreateDatabase(daoConfig);
            final DbManager$DbOpenListener dbOpenListener = daoConfig.getDbOpenListener();
            if (dbOpenListener != null) {
                dbOpenListener.onDbOpened((DbManager)this);
            }
            return;
        }
        throw new IllegalArgumentException("daoConfig may not be null");
    }
    
    private void beginTransaction() {
        if (this.allowTransaction) {
            if (Build$VERSION.SDK_INT >= 16 && this.database.isWriteAheadLoggingEnabled()) {
                this.database.beginTransactionNonExclusive();
            }
            else {
                this.database.beginTransaction();
            }
        }
    }
    
    private void endTransaction() {
        if (this.allowTransaction) {
            this.database.endTransaction();
        }
    }
    
    public static DbManager getInstance(DbManager$DaoConfig o) {
        final Class<DbManagerImpl> clazz;
        monitorenter(clazz = DbManagerImpl.class);
        Object daoConfig = o;
        Label_0020: {
            if (o != null) {
                break Label_0020;
            }
            try {
                daoConfig = new DbManager$DaoConfig();
                o = DbManagerImpl.DAO_MAP.get(daoConfig);
                if (o == null) {
                    o = new DbManagerImpl((DbManager$DaoConfig)daoConfig);
                    DbManagerImpl.DAO_MAP.put(daoConfig, o);
                }
                else {
                    ((DbManagerImpl)o).daoConfig = (DbManager$DaoConfig)daoConfig;
                }
                final SQLiteDatabase database = ((DbManagerImpl)o).database;
                final int version = database.getVersion();
                final int dbVersion = ((DbManager$DaoConfig)daoConfig).getDbVersion();
                if (version != dbVersion) {
                    if (version != 0) {
                        final DbManager$DbUpgradeListener dbUpgradeListener = ((DbManager$DaoConfig)daoConfig).getDbUpgradeListener();
                        if (dbUpgradeListener != null) {
                            dbUpgradeListener.onUpgrade((DbManager)o, version, dbVersion);
                        }
                        else {
                            try {
                                ((DbManagerImpl)o).dropDb();
                            }
                            catch (final DbException ex) {
                                LogUtil.e(ex.getMessage(), (Throwable)ex);
                            }
                        }
                    }
                    database.setVersion(dbVersion);
                }
                return (DbManager)o;
            }
            finally {
                monitorexit(clazz);
            }
        }
    }
    
    private long getLastAutoIncrementId(String execQuery) throws DbException {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT seq FROM sqlite_sequence WHERE name='");
        sb.append(execQuery);
        sb.append("' LIMIT 1");
        execQuery = (String)this.execQuery(sb.toString());
        long long1 = -1L;
        if (execQuery != null) {
            long1 = long1;
            try {
                if (((Cursor)execQuery).moveToNext()) {
                    long1 = ((Cursor)execQuery).getLong(0);
                }
                IOUtil.closeQuietly((Cursor)execQuery);
            }
            finally {
                try {
                    final Throwable t;
                    throw new DbException(t);
                }
                finally {
                    IOUtil.closeQuietly((Cursor)execQuery);
                }
            }
        }
        return long1;
    }
    
    private SQLiteDatabase openOrCreateDatabase(final DbManager$DaoConfig dbManager$DaoConfig) {
        final File dbDir = dbManager$DaoConfig.getDbDir();
        SQLiteDatabase sqLiteDatabase;
        if (dbDir != null && (dbDir.exists() || dbDir.mkdirs())) {
            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(new File(dbDir, dbManager$DaoConfig.getDbName()), (SQLiteDatabase$CursorFactory)null);
        }
        else {
            sqLiteDatabase = x.app().openOrCreateDatabase(dbManager$DaoConfig.getDbName(), 0, (SQLiteDatabase$CursorFactory)null);
        }
        return sqLiteDatabase;
    }
    
    private boolean saveBindingIdWithoutTransaction(final TableEntity<?> tableEntity, final Object o) throws DbException {
        final ColumnEntity id = tableEntity.getId();
        if (!id.isAutoId()) {
            this.execNonQuery(SqlInfoBuilder.buildInsertSqlInfo((TableEntity)tableEntity, o));
            return true;
        }
        this.execNonQuery(SqlInfoBuilder.buildInsertSqlInfo((TableEntity)tableEntity, o));
        final long lastAutoIncrementId = this.getLastAutoIncrementId(tableEntity.getName());
        if (lastAutoIncrementId == -1L) {
            return false;
        }
        id.setAutoIdValue(o, lastAutoIncrementId);
        return true;
    }
    
    private void saveOrUpdateWithoutTransaction(final TableEntity<?> tableEntity, final Object o) throws DbException {
        final ColumnEntity id = tableEntity.getId();
        if (id.isAutoId()) {
            if (id.getColumnValue(o) != null) {
                this.execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo((TableEntity)tableEntity, o, new String[0]));
            }
            else {
                this.saveBindingIdWithoutTransaction(tableEntity, o);
            }
        }
        else {
            this.execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo((TableEntity)tableEntity, o));
        }
    }
    
    private void setTransactionSuccessful() {
        if (this.allowTransaction) {
            this.database.setTransactionSuccessful();
        }
    }
    
    public void close() throws IOException {
        if (DbManagerImpl.DAO_MAP.containsKey((Object)this.daoConfig)) {
            DbManagerImpl.DAO_MAP.remove((Object)this.daoConfig);
            this.database.close();
        }
    }
    
    public int delete(final Class<?> clazz, final WhereBuilder whereBuilder) throws DbException {
        final TableEntity table = this.getTable((Class)clazz);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            this.beginTransaction();
            final int executeUpdateDelete = this.executeUpdateDelete(SqlInfoBuilder.buildDeleteSqlInfo(table, whereBuilder));
            this.setTransactionSuccessful();
            return executeUpdateDelete;
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void delete(final Class<?> clazz) throws DbException {
        this.delete(clazz, null);
    }
    
    public void delete(final Object o) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                if (!table.tableIsExist()) {
                    return;
                }
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table, iterator.next()));
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                if (!table2.tableIsExist()) {
                    return;
                }
                this.execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table2, o));
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void deleteById(final Class<?> clazz, final Object o) throws DbException {
        final TableEntity table = this.getTable((Class)clazz);
        if (!table.tableIsExist()) {
            return;
        }
        try {
            this.beginTransaction();
            this.execNonQuery(SqlInfoBuilder.buildDeleteSqlInfoById(table, o));
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void execNonQuery(final String s) throws DbException {
        try {
            this.database.execSQL(s);
        }
        finally {
            final Throwable t;
            throw new DbException(t);
        }
    }
    
    public void execNonQuery(final SqlInfo sqlInfo) throws DbException {
        SQLiteStatement buildStatement = null;
        try {
            final SQLiteStatement sqLiteStatement = buildStatement = sqlInfo.buildStatement(this.database);
            sqLiteStatement.execute();
            if (sqLiteStatement != null) {
                try {
                    sqLiteStatement.releaseReference();
                }
                finally {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                }
            }
        }
        finally {
            try {
                final Throwable t2;
                throw new DbException(t2);
            }
            finally {
                if (buildStatement != null) {
                    try {
                        buildStatement.releaseReference();
                    }
                    finally {
                        final Throwable t3;
                        LogUtil.e(t3.getMessage(), t3);
                    }
                }
            }
        }
    }
    
    public Cursor execQuery(final String s) throws DbException {
        try {
            return this.database.rawQuery(s, (String[])null);
        }
        finally {
            final Throwable t;
            throw new DbException(t);
        }
    }
    
    public Cursor execQuery(final SqlInfo sqlInfo) throws DbException {
        try {
            return this.database.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        }
        finally {
            final Throwable t;
            throw new DbException(t);
        }
    }
    
    public int executeUpdateDelete(final String s) throws DbException {
        SQLiteStatement compileStatement = null;
        try {
            final SQLiteStatement sqLiteStatement = compileStatement = this.database.compileStatement(s);
            final int executeUpdateDelete = sqLiteStatement.executeUpdateDelete();
            if (sqLiteStatement != null) {
                try {
                    sqLiteStatement.releaseReference();
                }
                finally {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                }
            }
            return executeUpdateDelete;
        }
        finally {
            try {
                final Throwable t2;
                throw new DbException(t2);
            }
            finally {
                if (compileStatement != null) {
                    try {
                        compileStatement.releaseReference();
                    }
                    finally {
                        final Throwable t3;
                        LogUtil.e(t3.getMessage(), t3);
                    }
                }
            }
        }
    }
    
    public int executeUpdateDelete(final SqlInfo sqlInfo) throws DbException {
        SQLiteStatement buildStatement = null;
        try {
            final SQLiteStatement sqLiteStatement = buildStatement = sqlInfo.buildStatement(this.database);
            final int executeUpdateDelete = sqLiteStatement.executeUpdateDelete();
            if (sqLiteStatement != null) {
                try {
                    sqLiteStatement.releaseReference();
                }
                finally {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                }
            }
            return executeUpdateDelete;
        }
        finally {
            try {
                final Throwable t2;
                throw new DbException(t2);
            }
            finally {
                if (buildStatement != null) {
                    try {
                        buildStatement.releaseReference();
                    }
                    finally {
                        final Throwable t3;
                        LogUtil.e(t3.getMessage(), t3);
                    }
                }
            }
        }
    }
    
    public <T> List<T> findAll(final Class<T> clazz) throws DbException {
        return (List<T>)this.selector(clazz).findAll();
    }
    
    public <T> T findById(Class<T> execQuery, Object entity) throws DbException {
        final TableEntity table = this.getTable((Class)execQuery);
        if (!table.tableIsExist()) {
            return null;
        }
        execQuery = this.execQuery(Selector.from(table).where(table.getId().getName(), "=", entity).limit(1).toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    entity = CursorUtils.getEntity(table, execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return (T)entity;
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
        return null;
    }
    
    public List<DbModel> findDbModelAll(SqlInfo execQuery) throws DbException {
        final ArrayList list = new ArrayList();
        execQuery = (SqlInfo)this.execQuery(execQuery);
        if (execQuery != null) {
            try {
                while (((Cursor)execQuery).moveToNext()) {
                    ((List)list).add((Object)CursorUtils.getDbModel((Cursor)execQuery));
                }
                IOUtil.closeQuietly((Cursor)execQuery);
            }
            finally {
                try {
                    final Throwable t;
                    throw new DbException(t);
                }
                finally {
                    IOUtil.closeQuietly((Cursor)execQuery);
                }
            }
        }
        return (List<DbModel>)list;
    }
    
    public DbModel findDbModelFirst(SqlInfo execQuery) throws DbException {
        execQuery = (SqlInfo)this.execQuery(execQuery);
        if (execQuery != null) {
            try {
                if (((Cursor)execQuery).moveToNext()) {
                    final DbModel dbModel = CursorUtils.getDbModel((Cursor)execQuery);
                    IOUtil.closeQuietly((Cursor)execQuery);
                    return dbModel;
                }
                IOUtil.closeQuietly((Cursor)execQuery);
            }
            finally {
                try {
                    final Throwable t;
                    throw new DbException(t);
                }
                finally {
                    IOUtil.closeQuietly((Cursor)execQuery);
                }
            }
        }
        return null;
    }
    
    public <T> T findFirst(final Class<T> clazz) throws DbException {
        return (T)this.selector(clazz).findFirst();
    }
    
    public DbManager$DaoConfig getDaoConfig() {
        return this.daoConfig;
    }
    
    public SQLiteDatabase getDatabase() {
        return this.database;
    }
    
    public void replace(final Object o) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                this.createTableIfNotExist(table);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table, iterator.next()));
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                this.createTableIfNotExist(table2);
                this.execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table2, o));
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void save(final Object o) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                this.createTableIfNotExist(table);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table, iterator.next()));
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                this.createTableIfNotExist(table2);
                this.execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table2, o));
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public boolean saveBindingId(Object o) throws DbException {
        try {
            this.beginTransaction();
            final boolean b = o instanceof List;
            final boolean b2 = false;
            boolean saveBindingIdWithoutTransaction;
            if (b) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return false;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                this.createTableIfNotExist(table);
                final Iterator iterator = list.iterator();
                while (true) {
                    saveBindingIdWithoutTransaction = b2;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    if (this.saveBindingIdWithoutTransaction((TableEntity<?>)table, iterator.next())) {
                        continue;
                    }
                    o = new DbException("saveBindingId error, transaction will not commit!");
                    throw o;
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                this.createTableIfNotExist(table2);
                saveBindingIdWithoutTransaction = this.saveBindingIdWithoutTransaction((TableEntity<?>)table2, o);
            }
            this.setTransactionSuccessful();
            return saveBindingIdWithoutTransaction;
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void saveOrUpdate(final Object o) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                this.createTableIfNotExist(table);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.saveOrUpdateWithoutTransaction((TableEntity<?>)table, iterator.next());
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                this.createTableIfNotExist(table2);
                this.saveOrUpdateWithoutTransaction((TableEntity<?>)table2, o);
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public <T> Selector<T> selector(final Class<T> clazz) throws DbException {
        return (Selector<T>)Selector.from(this.getTable((Class)clazz));
    }
    
    public int update(final Class<?> clazz, final WhereBuilder whereBuilder, final KeyValue... array) throws DbException {
        final TableEntity table = this.getTable((Class)clazz);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            this.beginTransaction();
            final int executeUpdateDelete = this.executeUpdateDelete(SqlInfoBuilder.buildUpdateSqlInfo(table, whereBuilder, array));
            this.setTransactionSuccessful();
            return executeUpdateDelete;
        }
        finally {
            this.endTransaction();
        }
    }
    
    public void update(final Object o, final String... array) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.getTable((Class)list.get(0).getClass());
                if (!table.tableIsExist()) {
                    return;
                }
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, iterator.next(), array));
                }
            }
            else {
                final TableEntity table2 = this.getTable((Class)o.getClass());
                if (!table2.tableIsExist()) {
                    return;
                }
                this.execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table2, o, array));
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
}
