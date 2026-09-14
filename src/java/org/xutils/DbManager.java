package org.xutils;

import android.text.TextUtils;
import java.io.File;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.db.table.TableEntity;
import android.database.sqlite.SQLiteDatabase;
import org.xutils.db.table.DbModel;
import java.util.List;
import android.database.Cursor;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import java.io.IOException;
import org.xutils.ex.DbException;
import java.io.Closeable;

public interface DbManager extends Closeable
{
    void addColumn(final Class<?> p0, final String p1) throws DbException;
    
    void close() throws IOException;
    
    int delete(final Class<?> p0, final WhereBuilder p1) throws DbException;
    
    void delete(final Class<?> p0) throws DbException;
    
    void delete(final Object p0) throws DbException;
    
    void deleteById(final Class<?> p0, final Object p1) throws DbException;
    
    void dropDb() throws DbException;
    
    void dropTable(final Class<?> p0) throws DbException;
    
    void execNonQuery(final String p0) throws DbException;
    
    void execNonQuery(final SqlInfo p0) throws DbException;
    
    Cursor execQuery(final String p0) throws DbException;
    
    Cursor execQuery(final SqlInfo p0) throws DbException;
    
    int executeUpdateDelete(final String p0) throws DbException;
    
    int executeUpdateDelete(final SqlInfo p0) throws DbException;
    
     <T> List<T> findAll(final Class<T> p0) throws DbException;
    
     <T> T findById(final Class<T> p0, final Object p1) throws DbException;
    
    List<DbModel> findDbModelAll(final SqlInfo p0) throws DbException;
    
    DbModel findDbModelFirst(final SqlInfo p0) throws DbException;
    
     <T> T findFirst(final Class<T> p0) throws DbException;
    
    DaoConfig getDaoConfig();
    
    SQLiteDatabase getDatabase();
    
     <T> TableEntity<T> getTable(final Class<T> p0) throws DbException;
    
    void replace(final Object p0) throws DbException;
    
    void save(final Object p0) throws DbException;
    
    boolean saveBindingId(final Object p0) throws DbException;
    
    void saveOrUpdate(final Object p0) throws DbException;
    
     <T> Selector<T> selector(final Class<T> p0) throws DbException;
    
    int update(final Class<?> p0, final WhereBuilder p1, final KeyValue... p2) throws DbException;
    
    void update(final Object p0, final String... p1) throws DbException;
    
    public static class DaoConfig
    {
        private boolean allowTransaction;
        private File dbDir;
        private String dbName;
        private DbOpenListener dbOpenListener;
        private DbUpgradeListener dbUpgradeListener;
        private int dbVersion;
        private TableCreateListener tableCreateListener;
        
        public DaoConfig() {
            this.dbName = "xUtils.db";
            this.dbVersion = 1;
            this.allowTransaction = true;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals = true;
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final DaoConfig daoConfig = (DaoConfig)o;
            if (!this.dbName.equals((Object)daoConfig.dbName)) {
                return false;
            }
            final File dbDir = this.dbDir;
            final File dbDir2 = daoConfig.dbDir;
            if (dbDir == null) {
                if (dbDir2 != null) {
                    equals = false;
                }
            }
            else {
                equals = dbDir.equals((Object)dbDir2);
            }
            return equals;
        }
        
        public File getDbDir() {
            return this.dbDir;
        }
        
        public String getDbName() {
            return this.dbName;
        }
        
        public DbOpenListener getDbOpenListener() {
            return this.dbOpenListener;
        }
        
        public DbUpgradeListener getDbUpgradeListener() {
            return this.dbUpgradeListener;
        }
        
        public int getDbVersion() {
            return this.dbVersion;
        }
        
        public TableCreateListener getTableCreateListener() {
            return this.tableCreateListener;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = this.dbName.hashCode();
            final File dbDir = this.dbDir;
            int hashCode2;
            if (dbDir != null) {
                hashCode2 = dbDir.hashCode();
            }
            else {
                hashCode2 = 0;
            }
            return hashCode * 31 + hashCode2;
        }
        
        public boolean isAllowTransaction() {
            return this.allowTransaction;
        }
        
        public DaoConfig setAllowTransaction(final boolean allowTransaction) {
            this.allowTransaction = allowTransaction;
            return this;
        }
        
        public DaoConfig setDbDir(final File dbDir) {
            this.dbDir = dbDir;
            return this;
        }
        
        public DaoConfig setDbName(final String dbName) {
            if (!TextUtils.isEmpty((CharSequence)dbName)) {
                this.dbName = dbName;
            }
            return this;
        }
        
        public DaoConfig setDbOpenListener(final DbOpenListener dbOpenListener) {
            this.dbOpenListener = dbOpenListener;
            return this;
        }
        
        public DaoConfig setDbUpgradeListener(final DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
            return this;
        }
        
        public DaoConfig setDbVersion(final int dbVersion) {
            this.dbVersion = dbVersion;
            return this;
        }
        
        public DaoConfig setTableCreateListener(final TableCreateListener tableCreateListener) {
            this.tableCreateListener = tableCreateListener;
            return this;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf((Object)this.dbDir));
            sb.append("/");
            sb.append(this.dbName);
            return sb.toString();
        }
    }
    
    public interface DbOpenListener
    {
        void onDbOpened(final DbManager p0);
    }
    
    public interface DbUpgradeListener
    {
        void onUpgrade(final DbManager p0, final int p1, final int p2);
    }
    
    public interface TableCreateListener
    {
        void onTableCreated(final DbManager p0, final TableEntity<?> p1);
    }
}
