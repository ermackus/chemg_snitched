package org.xutils.db;

import android.text.TextUtils;
import android.database.Cursor;
import org.xutils.ex.DbException;
import org.xutils.common.util.IOUtil;
import java.util.ArrayList;
import org.xutils.db.table.DbModel;
import java.util.List;
import org.xutils.db.table.TableEntity;
import org.xutils.db.sqlite.WhereBuilder;

public final class DbModelSelector
{
    private String[] columnExpressions;
    private String groupByColumnName;
    private WhereBuilder having;
    private Selector<?> selector;
    
    protected DbModelSelector(final Selector<?> selector, final String groupByColumnName) {
        this.selector = selector;
        this.groupByColumnName = groupByColumnName;
    }
    
    protected DbModelSelector(final Selector<?> selector, final String[] columnExpressions) {
        this.selector = selector;
        this.columnExpressions = columnExpressions;
    }
    
    private DbModelSelector(final TableEntity<?> tableEntity) {
        this.selector = Selector.from(tableEntity);
    }
    
    static DbModelSelector from(final TableEntity<?> tableEntity) {
        return new DbModelSelector(tableEntity);
    }
    
    public DbModelSelector and(final String s, final String s2, final Object o) {
        this.selector.and(s, s2, o);
        return this;
    }
    
    public DbModelSelector and(final WhereBuilder whereBuilder) {
        this.selector.and(whereBuilder);
        return this;
    }
    
    public DbModelSelector expr(final String s) {
        this.selector.expr(s);
        return this;
    }
    
    public List<DbModel> findAll() throws DbException {
        final TableEntity<?> table = this.selector.getTable();
        final boolean tableIsExist = table.tableIsExist();
        final List<DbModel> list = null;
        if (!tableIsExist) {
            return null;
        }
        final Cursor execQuery = table.getDb().execQuery(this.toString());
        if (execQuery != null) {
            try {
                final ArrayList list2 = new ArrayList();
                while (execQuery.moveToNext()) {
                    ((List)list2).add((Object)CursorUtils.getDbModel(execQuery));
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
        return list;
    }
    
    public DbModel findFirst() throws DbException {
        final TableEntity<?> table = this.selector.getTable();
        if (!table.tableIsExist()) {
            return null;
        }
        this.limit(1);
        final Cursor execQuery = table.getDb().execQuery(this.toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    final DbModel dbModel = CursorUtils.getDbModel(execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return dbModel;
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
    
    public TableEntity<?> getTable() {
        return this.selector.getTable();
    }
    
    public DbModelSelector groupBy(final String groupByColumnName) {
        this.groupByColumnName = groupByColumnName;
        return this;
    }
    
    public DbModelSelector having(final WhereBuilder having) {
        this.having = having;
        return this;
    }
    
    public DbModelSelector limit(final int n) {
        this.selector.limit(n);
        return this;
    }
    
    public DbModelSelector offset(final int n) {
        this.selector.offset(n);
        return this;
    }
    
    public DbModelSelector or(final String s, final String s2, final Object o) {
        this.selector.or(s, s2, o);
        return this;
    }
    
    public DbModelSelector or(final WhereBuilder whereBuilder) {
        this.selector.or(whereBuilder);
        return this;
    }
    
    public DbModelSelector orderBy(final String s) {
        this.selector.orderBy(s);
        return this;
    }
    
    public DbModelSelector orderBy(final String s, final boolean b) {
        this.selector.orderBy(s, b);
        return this;
    }
    
    public DbModelSelector select(final String... columnExpressions) {
        this.columnExpressions = columnExpressions;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        final String[] columnExpressions = this.columnExpressions;
        final int n = 0;
        if (columnExpressions != null && columnExpressions.length > 0) {
            for (int length = columnExpressions.length, i = 0; i < length; ++i) {
                sb.append(columnExpressions[i]);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        else if (!TextUtils.isEmpty((CharSequence)this.groupByColumnName)) {
            sb.append(this.groupByColumnName);
        }
        else {
            sb.append("*");
        }
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(this.selector.getTable().getName());
        sb.append("\"");
        final WhereBuilder whereBuilder = this.selector.getWhereBuilder();
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            sb.append(" WHERE ");
            sb.append(whereBuilder.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)this.groupByColumnName)) {
            sb.append(" GROUP BY ");
            sb.append("\"");
            sb.append(this.groupByColumnName);
            sb.append("\"");
            final WhereBuilder having = this.having;
            if (having != null && having.getWhereItemSize() > 0) {
                sb.append(" HAVING ");
                sb.append(this.having.toString());
            }
        }
        final List<Selector.OrderBy> orderByList = this.selector.getOrderByList();
        if (orderByList != null && orderByList.size() > 0) {
            for (int j = n; j < orderByList.size(); ++j) {
                sb.append(" ORDER BY ");
                sb.append(((Selector.OrderBy)orderByList.get(j)).toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.selector.getLimit() > 0) {
            sb.append(" LIMIT ");
            sb.append(this.selector.getLimit());
            sb.append(" OFFSET ");
            sb.append(this.selector.getOffset());
        }
        return sb.toString();
    }
    
    public DbModelSelector where(final String s, final String s2, final Object o) {
        this.selector.where(s, s2, o);
        return this;
    }
    
    public DbModelSelector where(final WhereBuilder whereBuilder) {
        this.selector.where(whereBuilder);
        return this;
    }
}
