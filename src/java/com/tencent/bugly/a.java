package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.x;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public abstract class a
{
    public int id;
    public String moduleName;
    public String version;
    public String versionKey;
    
    public abstract String[] getTables();
    
    public abstract void init(final Context p0, final boolean p1, final BuglyStrategy p2);
    
    public void onDbCreate(final SQLiteDatabase sqLiteDatabase) {
    }
    
    public void onDbDowngrade(final SQLiteDatabase sqLiteDatabase, int i, int length) {
        try {
            if (this.getTables() == null) {
                return;
            }
            final String[] tables = this.getTables();
            String s;
            StringBuilder sb;
            for (length = tables.length, i = 0; i < length; ++i) {
                s = tables[i];
                sb = new StringBuilder("DROP TABLE IF EXISTS ");
                sb.append(s);
                sqLiteDatabase.execSQL(sb.toString());
            }
            this.onDbCreate(sqLiteDatabase);
        }
        finally {
            final Throwable t;
            if (!x.b(t)) {
                t.printStackTrace();
            }
        }
    }
    
    public void onDbUpgrade(final SQLiteDatabase sqLiteDatabase, int i, int length) {
        try {
            if (this.getTables() == null) {
                return;
            }
            final String[] tables = this.getTables();
            String s;
            StringBuilder sb;
            for (length = tables.length, i = 0; i < length; ++i) {
                s = tables[i];
                sb = new StringBuilder("DROP TABLE IF EXISTS ");
                sb.append(s);
                sqLiteDatabase.execSQL(sb.toString());
            }
            this.onDbCreate(sqLiteDatabase);
        }
        finally {
            final Throwable t;
            if (!x.b(t)) {
                t.printStackTrace();
            }
        }
    }
    
    public void onServerStrategyChanged(final StrategyBean strategyBean) {
    }
}
