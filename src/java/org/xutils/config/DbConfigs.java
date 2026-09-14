package org.xutils.config;

import org.xutils.ex.DbException;
import org.xutils.common.util.LogUtil;
import org.xutils.DbManager;

public enum DbConfigs
{
    private static final DbConfigs[] $VALUES;
    
    COOKIE(new DbManager.DaoConfig().setDbName("xUtils_http_cookie.db").setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener() {
        @Override
        public void onDbOpened(final DbManager dbManager) {
            dbManager.getDatabase().enableWriteAheadLogging();
        }
    }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
        @Override
        public void onUpgrade(final DbManager dbManager, final int n, final int n2) {
            try {
                dbManager.dropDb();
            }
            catch (final DbException ex) {
                LogUtil.e(ex.getMessage(), (Throwable)ex);
            }
        }
    })), 
    HTTP(new DbManager.DaoConfig().setDbName("xUtils_http_cache.db").setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener() {
        @Override
        public void onDbOpened(final DbManager dbManager) {
            dbManager.getDatabase().enableWriteAheadLogging();
        }
    }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
        @Override
        public void onUpgrade(final DbManager dbManager, final int n, final int n2) {
            try {
                dbManager.dropDb();
            }
            catch (final DbException ex) {
                LogUtil.e(ex.getMessage(), (Throwable)ex);
            }
        }
    }));
    
    private DbManager.DaoConfig config;
    
    private DbConfigs(final DbManager.DaoConfig config) {
        this.config = config;
    }
    
    public DbManager.DaoConfig getConfig() {
        return this.config;
    }
}
