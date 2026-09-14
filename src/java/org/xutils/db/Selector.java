package org.xutils.db;

import java.util.Iterator;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import java.util.ArrayList;
import org.xutils.ex.DbException;
import org.xutils.db.table.DbModel;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.TableEntity;
import java.util.List;

public final class Selector<T>
{
    private int limit;
    private int offset;
    private List<OrderBy> orderByList;
    private final TableEntity<T> table;
    private WhereBuilder whereBuilder;
    
    private Selector(final TableEntity<T> table) {
        this.limit = 0;
        this.offset = 0;
        this.table = table;
    }
    
    static <T> Selector<T> from(final TableEntity<T> tableEntity) {
        return new Selector<T>(tableEntity);
    }
    
    public Selector<T> and(final String s, final String s2, final Object o) {
        this.whereBuilder.and(s, s2, o);
        return this;
    }
    
    public Selector<T> and(final WhereBuilder whereBuilder) {
        this.whereBuilder.and(whereBuilder);
        return this;
    }
    
    public long count() throws DbException {
        if (!this.table.tableIsExist()) {
            return 0L;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("count(\"");
        sb.append(this.table.getId().getName());
        sb.append("\") as count");
        final DbModel first = this.select(sb.toString()).findFirst();
        if (first != null) {
            return first.getLong("count");
        }
        return 0L;
    }
    
    public Selector<T> expr(final String s) {
        if (this.whereBuilder == null) {
            this.whereBuilder = WhereBuilder.b();
        }
        this.whereBuilder.expr(s);
        return this;
    }
    
    public List<T> findAll() throws DbException {
        final boolean tableIsExist = this.table.tableIsExist();
        final List<T> list = null;
        if (!tableIsExist) {
            return null;
        }
        final Cursor execQuery = this.table.getDb().execQuery(this.toString());
        if (execQuery != null) {
            try {
                final ArrayList list2 = new ArrayList();
                while (execQuery.moveToNext()) {
                    ((List)list2).add((Object)CursorUtils.getEntity(this.table, execQuery));
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
    
    public T findFirst() throws DbException {
        if (!this.table.tableIsExist()) {
            return null;
        }
        this.limit(1);
        final Cursor execQuery = this.table.getDb().execQuery(this.toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    final T entity = CursorUtils.getEntity(this.table, execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return entity;
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
    
    public int getLimit() {
        return this.limit;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public List<OrderBy> getOrderByList() {
        return this.orderByList;
    }
    
    public TableEntity<T> getTable() {
        return this.table;
    }
    
    public WhereBuilder getWhereBuilder() {
        return this.whereBuilder;
    }
    
    public DbModelSelector groupBy(final String s) {
        return new DbModelSelector(this, s);
    }
    
    public Selector<T> limit(final int limit) {
        this.limit = limit;
        return this;
    }
    
    public Selector<T> offset(final int offset) {
        this.offset = offset;
        return this;
    }
    
    public Selector<T> or(final String s, final String s2, final Object o) {
        this.whereBuilder.or(s, s2, o);
        return this;
    }
    
    public Selector or(final WhereBuilder whereBuilder) {
        this.whereBuilder.or(whereBuilder);
        return this;
    }
    
    public Selector<T> orderBy(final String s) {
        if (this.orderByList == null) {
            this.orderByList = (List<OrderBy>)new ArrayList(5);
        }
        this.orderByList.add((Object)new OrderBy(s));
        return this;
    }
    
    public Selector<T> orderBy(final String s, final boolean b) {
        if (this.orderByList == null) {
            this.orderByList = (List<OrderBy>)new ArrayList(5);
        }
        this.orderByList.add((Object)new OrderBy(s, b));
        return this;
    }
    
    public DbModelSelector select(final String... array) {
        return new DbModelSelector(this, array);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(this.table.getName());
        sb.append("\"");
        final WhereBuilder whereBuilder = this.whereBuilder;
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            sb.append(" WHERE ");
            sb.append(this.whereBuilder.toString());
        }
        final List<OrderBy> orderByList = this.orderByList;
        if (orderByList != null && orderByList.size() > 0) {
            sb.append(" ORDER BY ");
            final Iterator iterator = this.orderByList.iterator();
            while (iterator.hasNext()) {
                sb.append(((OrderBy)iterator.next()).toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.limit > 0) {
            sb.append(" LIMIT ");
            sb.append(this.limit);
            sb.append(" OFFSET ");
            sb.append(this.offset);
        }
        return sb.toString();
    }
    
    public Selector<T> where(final String s, final String s2, final Object o) {
        this.whereBuilder = WhereBuilder.b(s, s2, o);
        return this;
    }
    
    public Selector<T> where(final WhereBuilder whereBuilder) {
        this.whereBuilder = whereBuilder;
        return this;
    }
    
    public static class OrderBy
    {
        private String columnName;
        private boolean desc;
        
        public OrderBy(final String columnName) {
            this.columnName = columnName;
        }
        
        public OrderBy(final String columnName, final boolean desc) {
            this.columnName = columnName;
            this.desc = desc;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(this.columnName);
            sb.append("\"");
            String s;
            if (this.desc) {
                s = " DESC";
            }
            else {
                s = " ASC";
            }
            sb.append(s);
            return sb.toString();
        }
    }
}
