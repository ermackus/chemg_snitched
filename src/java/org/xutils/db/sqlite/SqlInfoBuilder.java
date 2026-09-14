package org.xutils.db.sqlite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.ex.DbException;
import java.util.Iterator;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.TableEntity;
import java.util.concurrent.ConcurrentHashMap;

public final class SqlInfoBuilder
{
    private static final ConcurrentHashMap<TableEntity<?>, String> INSERT_SQL_CACHE;
    private static final ConcurrentHashMap<TableEntity<?>, String> REPLACE_SQL_CACHE;
    
    static {
        INSERT_SQL_CACHE = new ConcurrentHashMap();
        REPLACE_SQL_CACHE = new ConcurrentHashMap();
    }
    
    private SqlInfoBuilder() {
    }
    
    public static SqlInfo buildCreateTableSqlInfo(final TableEntity<?> tableEntity) throws DbException {
        final ColumnEntity id = tableEntity.getId();
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append("\"");
        sb.append(tableEntity.getName());
        sb.append("\"");
        sb.append(" ( ");
        if (id.isAutoId()) {
            sb.append("\"");
            sb.append(id.getName());
            sb.append("\"");
            sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        }
        else {
            sb.append("\"");
            sb.append(id.getName());
            sb.append("\"");
            sb.append((Object)id.getColumnDbType());
            sb.append(" PRIMARY KEY, ");
        }
        for (final ColumnEntity columnEntity : tableEntity.getColumnMap().values()) {
            if (columnEntity.isId()) {
                continue;
            }
            sb.append("\"");
            sb.append(columnEntity.getName());
            sb.append("\"");
            sb.append(' ');
            sb.append((Object)columnEntity.getColumnDbType());
            sb.append(' ');
            sb.append(columnEntity.getProperty());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" )");
        return new SqlInfo(sb.toString());
    }
    
    public static SqlInfo buildDeleteSqlInfo(final TableEntity<?> tableEntity, Object columnValue) throws DbException {
        final SqlInfo sqlInfo = new SqlInfo();
        final ColumnEntity id = tableEntity.getId();
        columnValue = id.getColumnValue(columnValue);
        if (columnValue != null) {
            final StringBuilder sb = new StringBuilder("DELETE FROM ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" WHERE ");
            sb.append((Object)WhereBuilder.b(id.getName(), "=", columnValue));
            sqlInfo.setSql(sb.toString());
            return sqlInfo;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("this entity[");
        sb2.append((Object)tableEntity.getEntityType());
        sb2.append("]'s id value is null");
        throw new DbException(sb2.toString());
    }
    
    public static SqlInfo buildDeleteSqlInfo(final TableEntity<?> tableEntity, final WhereBuilder whereBuilder) throws DbException {
        final StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append("\"");
        sb.append(tableEntity.getName());
        sb.append("\"");
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        return new SqlInfo(sb.toString());
    }
    
    public static SqlInfo buildDeleteSqlInfoById(final TableEntity<?> tableEntity, final Object o) throws DbException {
        final SqlInfo sqlInfo = new SqlInfo();
        final ColumnEntity id = tableEntity.getId();
        if (o != null) {
            final StringBuilder sb = new StringBuilder("DELETE FROM ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" WHERE ");
            sb.append((Object)WhereBuilder.b(id.getName(), "=", o));
            sqlInfo.setSql(sb.toString());
            return sqlInfo;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("this entity[");
        sb2.append((Object)tableEntity.getEntityType());
        sb2.append("]'s id value is null");
        throw new DbException(sb2.toString());
    }
    
    public static SqlInfo buildInsertSqlInfo(final TableEntity<?> tableEntity, final Object o) throws DbException {
        final List<KeyValue> entity2KeyValueList = entity2KeyValueList(tableEntity, o);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        final SqlInfo sqlInfo = new SqlInfo();
        final String sql = (String)SqlInfoBuilder.INSERT_SQL_CACHE.get((Object)tableEntity);
        if (sql == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" (");
            for (final KeyValue keyValue : entity2KeyValueList) {
                sb.append("\"");
                sb.append(keyValue.key);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            for (int size = entity2KeyValueList.size(), i = 0; i < size; ++i) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            final String string = sb.toString();
            sqlInfo.setSql(string);
            sqlInfo.addBindArgs(entity2KeyValueList);
            SqlInfoBuilder.INSERT_SQL_CACHE.put((Object)tableEntity, (Object)string);
        }
        else {
            sqlInfo.setSql(sql);
            sqlInfo.addBindArgs(entity2KeyValueList);
        }
        return sqlInfo;
    }
    
    public static SqlInfo buildReplaceSqlInfo(final TableEntity<?> tableEntity, final Object o) throws DbException {
        final List<KeyValue> entity2KeyValueList = entity2KeyValueList(tableEntity, o);
        if (entity2KeyValueList.size() == 0) {
            return null;
        }
        final SqlInfo sqlInfo = new SqlInfo();
        final String sql = (String)SqlInfoBuilder.REPLACE_SQL_CACHE.get((Object)tableEntity);
        if (sql == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("REPLACE INTO ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" (");
            for (final KeyValue keyValue : entity2KeyValueList) {
                sb.append("\"");
                sb.append(keyValue.key);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            for (int size = entity2KeyValueList.size(), i = 0; i < size; ++i) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            final String string = sb.toString();
            sqlInfo.setSql(string);
            sqlInfo.addBindArgs(entity2KeyValueList);
            SqlInfoBuilder.REPLACE_SQL_CACHE.put((Object)tableEntity, (Object)string);
        }
        else {
            sqlInfo.setSql(sql);
            sqlInfo.addBindArgs(entity2KeyValueList);
        }
        return sqlInfo;
    }
    
    public static SqlInfo buildUpdateSqlInfo(final TableEntity<?> tableEntity, Object columnValue, final String... array) throws DbException {
        final List<KeyValue> entity2KeyValueList = entity2KeyValueList(tableEntity, columnValue);
        final int size = entity2KeyValueList.size();
        final HashSet set = null;
        if (size == 0) {
            return null;
        }
        HashSet set2 = set;
        if (array != null) {
            set2 = set;
            if (array.length > 0) {
                set2 = new HashSet(array.length);
                Collections.addAll((Collection)set2, (Object[])array);
            }
        }
        final ColumnEntity id = tableEntity.getId();
        columnValue = id.getColumnValue(columnValue);
        if (columnValue != null) {
            final SqlInfo sqlInfo = new SqlInfo();
            final StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" SET ");
            for (final KeyValue keyValue : entity2KeyValueList) {
                if (set2 == null || set2.contains((Object)keyValue.key)) {
                    sb.append("\"");
                    sb.append(keyValue.key);
                    sb.append("\"");
                    sb.append("=?,");
                    sqlInfo.addBindArg(keyValue);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" WHERE ");
            sb.append((Object)WhereBuilder.b(id.getName(), "=", columnValue));
            sqlInfo.setSql(sb.toString());
            return sqlInfo;
        }
        columnValue = new StringBuilder();
        ((StringBuilder)columnValue).append("this entity[");
        ((StringBuilder)columnValue).append((Object)tableEntity.getEntityType());
        ((StringBuilder)columnValue).append("]'s id value is null");
        throw new DbException(((StringBuilder)columnValue).toString());
    }
    
    public static SqlInfo buildUpdateSqlInfo(final TableEntity<?> tableEntity, final WhereBuilder whereBuilder, final KeyValue... array) throws DbException {
        if (array != null && array.length != 0) {
            final SqlInfo sqlInfo = new SqlInfo();
            final StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append("\"");
            sb.append(tableEntity.getName());
            sb.append("\"");
            sb.append(" SET ");
            for (final KeyValue keyValue : array) {
                sb.append("\"");
                sb.append(keyValue.key);
                sb.append("\"");
                sb.append("=?,");
                sqlInfo.addBindArg(keyValue);
            }
            sb.deleteCharAt(sb.length() - 1);
            if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
                sb.append(" WHERE ");
                sb.append(whereBuilder.toString());
            }
            sqlInfo.setSql(sb.toString());
            return sqlInfo;
        }
        return null;
    }
    
    private static KeyValue column2KeyValue(final Object o, final ColumnEntity columnEntity) {
        if (columnEntity.isAutoId()) {
            return null;
        }
        return new KeyValue(columnEntity.getName(), columnEntity.getFieldValue(o));
    }
    
    public static List<KeyValue> entity2KeyValueList(final TableEntity<?> tableEntity, final Object o) {
        final Collection values = tableEntity.getColumnMap().values();
        final ArrayList list = new ArrayList(values.size());
        final Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            final KeyValue column2KeyValue = column2KeyValue(o, (ColumnEntity)iterator.next());
            if (column2KeyValue != null) {
                ((List)list).add((Object)column2KeyValue);
            }
        }
        return (List<KeyValue>)list;
    }
}
