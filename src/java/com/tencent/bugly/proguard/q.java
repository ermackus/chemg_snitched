package com.tencent.bugly.proguard;

import java.io.File;
import com.tencent.bugly.crashreport.common.info.b;
import java.util.Iterator;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import com.tencent.bugly.a;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public final class q extends SQLiteOpenHelper
{
    public static String a = "bugly_db";
    private static int b = 15;
    private Context c;
    private List<a> d;
    
    public q(final Context c, final List<a> d) {
        final StringBuilder sb = new StringBuilder();
        sb.append(q.a);
        sb.append("_");
        com.tencent.bugly.crashreport.common.info.a.a(c).getClass();
        super(c, sb.toString(), (SQLiteDatabase$CursorFactory)null, q.b);
        this.c = c;
        this.d = d;
    }
    
    private boolean a(final SQLiteDatabase sqLiteDatabase) {
        monitorenter(this);
        int n = 0;
        while (true) {
            Label_0070: {
                if (n >= 3) {
                    break Label_0070;
                }
                try {
                    final String s = (new String[] { "t_lr", "t_ui", "t_pf" })[n];
                    final StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ");
                    sb.append(s);
                    sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
                    ++n;
                    continue;
                    monitorexit(this);
                    return true;
                }
                finally {
                    try {
                        final Throwable t;
                        if (!x.b(t)) {
                            t.printStackTrace();
                        }
                        return false;
                    }
                    finally {
                        monitorexit(this);
                    }
                }
            }
        }
    }
    
    public final SQLiteDatabase getReadableDatabase() {
        monitorenter(this);
        final SQLiteDatabase sqLiteDatabase = null;
        int n = 0;
        while (sqLiteDatabase == null && n < 5) {
            ++n;
            try {
                super.getReadableDatabase();
                continue;
            }
            finally {
                try {
                    x.d("[Database] Try to get db(count: %d).", n);
                    if (n == 5) {
                        x.e("[Database] Failed to get db.", new Object[0]);
                    }
                    try {
                        Thread.sleep(200L);
                        continue;
                    }
                    catch (final InterruptedException ex) {
                        ex.printStackTrace();
                        continue;
                    }
                }
                finally {
                    monitorexit(this);
                }
            }
            break;
        }
        monitorexit(this);
        return sqLiteDatabase;
    }
    
    public final SQLiteDatabase getWritableDatabase() {
        monitorenter(this);
        SQLiteDatabase writableDatabase = null;
        int n = 0;
        while (writableDatabase == null && n < 5) {
            ++n;
            try {
                writableDatabase = super.getWritableDatabase();
                continue;
            }
            finally {
                x.d("[Database] Try to get db(count: %d).", n);
                if (n == 5) {
                    x.e("[Database] Failed to get db.", new Object[0]);
                }
                try {
                    Thread.sleep(200L);
                }
                catch (final InterruptedException ex) {
                    ex.printStackTrace();
                }
                continue;
            }
            break;
        }
        try {
            if (writableDatabase == null) {
                x.d("[Database] db error delay error record 1min.", new Object[0]);
            }
        }
        finally {
            monitorexit(this);
        }
        monitorexit(this);
        return;
    }
    
    public final void onCreate(final SQLiteDatabase sqLiteDatabase) {
        monitorenter(this);
        try {
            final StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_ui");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _ut");
            sb.append(" int");
            sb.append(" , _tp");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" , _pc");
            sb.append(" text");
            sb.append(" ) ");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_lr");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tp");
            sb.append(" int");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _pc");
            sb.append(" text");
            sb.append(" , _th");
            sb.append(" text");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" ) ");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_pf");
            sb.append(" ( _id");
            sb.append(" integer");
            sb.append(" , _tp");
            sb.append(" text");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(",primary key(_id");
            sb.append(",_tp");
            sb.append(" )) ");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS t_cr");
            sb.append(" ( _id");
            sb.append(" INTEGER PRIMARY KEY");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _s1");
            sb.append(" text");
            sb.append(" , _up");
            sb.append(" int");
            sb.append(" , _me");
            sb.append(" int");
            sb.append(" , _uc");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(" ) ");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS dl_1002");
            sb.append(" (_id");
            sb.append(" integer primary key autoincrement, _dUrl");
            sb.append(" varchar(100), _sFile");
            sb.append(" varchar(100), _sLen");
            sb.append(" INTEGER, _tLen");
            sb.append(" INTEGER, _MD5");
            sb.append(" varchar(100), _DLTIME");
            sb.append(" INTEGER)");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append("CREATE TABLE IF NOT EXISTS ge_1002");
            sb.append(" (_id");
            sb.append(" integer primary key autoincrement, _time");
            sb.append(" INTEGER, _datas");
            sb.append(" blob)");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
            sb.setLength(0);
            sb.append(" CREATE TABLE IF NOT EXISTS st_1002");
            sb.append(" ( _id");
            sb.append(" integer");
            sb.append(" , _tp");
            sb.append(" text");
            sb.append(" , _tm");
            sb.append(" int");
            sb.append(" , _dt");
            sb.append(" blob");
            sb.append(",primary key(_id");
            sb.append(",_tp");
            sb.append(" )) ");
            x.c(sb.toString(), new Object[0]);
            sqLiteDatabase.execSQL(sb.toString(), (Object[])new String[0]);
        }
        finally {
            final Throwable t;
            if (!x.b(t)) {
                t.printStackTrace();
            }
        }
        try {
            if (this.d == null) {
                return;
            }
            for (final a a : this.d) {
                try {
                    a.onDbCreate(sqLiteDatabase);
                }
                finally {
                    final Throwable t2;
                    if (x.b(t2)) {
                        continue;
                    }
                    t2.printStackTrace();
                }
            }
        }
        finally {
            monitorexit(this);
        }
    }
    
    public final void onDowngrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        synchronized (this) {
            if (com.tencent.bugly.crashreport.common.info.b.c() >= 11) {
                x.d("[Database] Downgrade %d to %d drop tables.", n, n2);
                if (this.d != null) {
                    for (final a a : this.d) {
                        try {
                            a.onDbDowngrade(sqLiteDatabase, n, n2);
                        }
                        finally {
                            final Throwable t;
                            if (x.b(t)) {
                                continue;
                            }
                            t.printStackTrace();
                        }
                    }
                }
                if (this.a(sqLiteDatabase)) {
                    this.onCreate(sqLiteDatabase);
                    return;
                }
                x.d("[Database] Failed to drop, delete db.", new Object[0]);
                final File databasePath = this.c.getDatabasePath(q.a);
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }
    
    public final void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        synchronized (this) {
            x.d("[Database] Upgrade %d to %d , drop tables!", n, n2);
            if (this.d != null) {
                for (final a a : this.d) {
                    try {
                        a.onDbUpgrade(sqLiteDatabase, n, n2);
                    }
                    finally {
                        final Throwable t;
                        if (x.b(t)) {
                            continue;
                        }
                        t.printStackTrace();
                    }
                }
            }
            if (this.a(sqLiteDatabase)) {
                this.onCreate(sqLiteDatabase);
                return;
            }
            x.d("[Database] Failed to drop, delete db.", new Object[0]);
            final File databasePath = this.c.getDatabasePath(q.a);
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }
}
