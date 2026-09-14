package org.xutils.db.sqlite;

import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase;
import java.util.Collection;
import java.util.ArrayList;
import org.xutils.common.util.KeyValue;
import java.util.List;

public final class SqlInfo
{
    private List<KeyValue> bindArgs;
    private String sql;
    
    public SqlInfo() {
    }
    
    public SqlInfo(final String sql) {
        this.sql = sql;
    }
    
    public void addBindArg(final KeyValue keyValue) {
        if (this.bindArgs == null) {
            this.bindArgs = (List<KeyValue>)new ArrayList();
        }
        this.bindArgs.add((Object)keyValue);
    }
    
    public void addBindArgs(final List<KeyValue> bindArgs) {
        final List<KeyValue> bindArgs2 = this.bindArgs;
        if (bindArgs2 == null) {
            this.bindArgs = bindArgs;
        }
        else {
            bindArgs2.addAll((Collection)bindArgs);
        }
    }
    
    public SQLiteStatement buildStatement(final SQLiteDatabase sqLiteDatabase) {
        final SQLiteStatement compileStatement = sqLiteDatabase.compileStatement(this.sql);
        if (this.bindArgs != null) {
            for (int i = 1; i < this.bindArgs.size() + 1; ++i) {
                final Object convert2DbValueIfNeeded = ColumnUtils.convert2DbValueIfNeeded(((KeyValue)this.bindArgs.get(i - 1)).value);
                if (convert2DbValueIfNeeded == null) {
                    compileStatement.bindNull(i);
                }
                else {
                    final int n = SqlInfo$1.$SwitchMap$org$xutils$db$sqlite$ColumnDbType[ColumnConverterFactory.getColumnConverter(((byte[])convert2DbValueIfNeeded).getClass()).getColumnDbType().ordinal()];
                    if (n != 1) {
                        if (n != 2) {
                            if (n != 3) {
                                if (n != 4) {
                                    compileStatement.bindNull(i);
                                }
                                else {
                                    compileStatement.bindBlob(i, (byte[])convert2DbValueIfNeeded);
                                }
                            }
                            else {
                                compileStatement.bindString(i, convert2DbValueIfNeeded.toString());
                            }
                        }
                        else {
                            compileStatement.bindDouble(i, ((Number)convert2DbValueIfNeeded).doubleValue());
                        }
                    }
                    else {
                        compileStatement.bindLong(i, ((Number)convert2DbValueIfNeeded).longValue());
                    }
                }
            }
        }
        return compileStatement;
    }
    
    public Object[] getBindArgs() {
        final List<KeyValue> bindArgs = this.bindArgs;
        Object[] array2;
        if (bindArgs != null) {
            final Object[] array = new Object[bindArgs.size()];
            int n = 0;
            while (true) {
                array2 = array;
                if (n >= this.bindArgs.size()) {
                    break;
                }
                array[n] = ColumnUtils.convert2DbValueIfNeeded(((KeyValue)this.bindArgs.get(n)).value);
                ++n;
            }
        }
        else {
            array2 = null;
        }
        return array2;
    }
    
    public String[] getBindArgsAsStrArray() {
        final List<KeyValue> bindArgs = this.bindArgs;
        String[] array = null;
        if (bindArgs != null) {
            final String[] array2 = new String[bindArgs.size()];
            for (int i = 0; i < this.bindArgs.size(); ++i) {
                final Object convert2DbValueIfNeeded = ColumnUtils.convert2DbValueIfNeeded(((KeyValue)this.bindArgs.get(i)).value);
                String string;
                if (convert2DbValueIfNeeded == null) {
                    string = null;
                }
                else {
                    string = convert2DbValueIfNeeded.toString();
                }
                array2[i] = string;
            }
            array = array2;
        }
        return array;
    }
    
    public String getSql() {
        return this.sql;
    }
    
    public void setSql(final String sql) {
        this.sql = sql;
    }
}
