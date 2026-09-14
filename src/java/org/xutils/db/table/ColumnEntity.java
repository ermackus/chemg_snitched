package org.xutils.db.table;

import android.database.Cursor;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.ColumnDbType;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.annotation.Column;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.xutils.db.converter.ColumnConverter;

public final class ColumnEntity
{
    protected final ColumnConverter columnConverter;
    protected final Field columnField;
    protected final Method getMethod;
    private final boolean isAutoId;
    private final boolean isId;
    protected final String name;
    private final String property;
    protected final Method setMethod;
    
    ColumnEntity(final Class<?> clazz, final Field columnField, final Column column) {
        columnField.setAccessible(true);
        this.columnField = columnField;
        this.name = column.name();
        this.property = column.property();
        this.isId = column.isId();
        final Class type = columnField.getType();
        this.isAutoId = (this.isId && column.autoGen() && ColumnUtils.isAutoIdType(type));
        this.columnConverter = ColumnConverterFactory.getColumnConverter(type);
        final Method getMethod = ColumnUtils.findGetMethod(clazz, columnField);
        this.getMethod = getMethod;
        if (getMethod != null && !getMethod.isAccessible()) {
            this.getMethod.setAccessible(true);
        }
        final Method setMethod = ColumnUtils.findSetMethod(clazz, columnField);
        if ((this.setMethod = setMethod) != null && !setMethod.isAccessible()) {
            this.setMethod.setAccessible(true);
        }
    }
    
    public ColumnConverter getColumnConverter() {
        return this.columnConverter;
    }
    
    public ColumnDbType getColumnDbType() {
        return this.columnConverter.getColumnDbType();
    }
    
    public Field getColumnField() {
        return this.columnField;
    }
    
    public Object getColumnValue(Object fieldValue) {
        fieldValue = this.getFieldValue(fieldValue);
        if (this.isAutoId && (fieldValue.equals(0L) || fieldValue.equals(0))) {
            return null;
        }
        return this.columnConverter.fieldValue2DbValue(fieldValue);
    }
    
    public Object getFieldValue(Object o) {
        if (o != null) {
            final Method getMethod = this.getMethod;
            final Throwable t;
            if (getMethod != null) {
                try {
                    o = getMethod.invoke(o, new Object[0]);
                    return t;
                }
                finally {
                    LogUtil.e(t.getMessage(), t);
                    return null;
                }
            }
            try {
                o = this.columnField.get(o);
                return t;
            }
            finally {
                LogUtil.e(t.getMessage(), t);
            }
        }
        return null;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getProperty() {
        return this.property;
    }
    
    public boolean isAutoId() {
        return this.isAutoId;
    }
    
    public boolean isId() {
        return this.isId;
    }
    
    public void setAutoIdValue(final Object o, final long n) {
        Object o2 = n;
        if (ColumnUtils.isInteger(this.columnField.getType())) {
            o2 = (int)n;
        }
        final Method setMethod = this.setMethod;
        if (setMethod != null) {
            try {
                setMethod.invoke(o, new Object[] { o2 });
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
        }
        else {
            try {
                this.columnField.set(o, o2);
            }
            finally {
                final Throwable t2;
                LogUtil.e(t2.getMessage(), t2);
            }
        }
    }
    
    public void setValueFromCursor(final Object o, final Cursor cursor, final int n) {
        final Object fieldValue = this.columnConverter.getFieldValue(cursor, n);
        if (fieldValue == null) {
            return;
        }
        final Method setMethod = this.setMethod;
        if (setMethod != null) {
            try {
                setMethod.invoke(o, new Object[] { fieldValue });
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
        }
        else {
            try {
                this.columnField.set(o, fieldValue);
            }
            finally {
                final Throwable t2;
                LogUtil.e(t2.getMessage(), t2);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
