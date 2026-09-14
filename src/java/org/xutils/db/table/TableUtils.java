package org.xutils.db.table;

import java.util.LinkedHashMap;
import java.lang.reflect.Field;
import org.xutils.common.util.LogUtil;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.annotation.Column;
import java.lang.reflect.Modifier;
import java.util.HashMap;

final class TableUtils
{
    private TableUtils() {
    }
    
    private static void addColumns2Map(final Class<?> obj, final HashMap<String, ColumnEntity> hashMap) {
        if (Object.class.equals(obj)) {
            return;
        }
        try {
            for (final Field field : obj.getDeclaredFields()) {
                final int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (!Modifier.isTransient(modifiers)) {
                        final Column column = (Column)field.getAnnotation((Class)Column.class);
                        if (column != null && ColumnConverterFactory.isSupportColumnConverter(field.getType())) {
                            final ColumnEntity columnEntity = new ColumnEntity(obj, field, column);
                            if (!hashMap.containsKey((Object)columnEntity.getName())) {
                                hashMap.put((Object)columnEntity.getName(), (Object)columnEntity);
                            }
                        }
                    }
                }
            }
            addColumns2Map(obj.getSuperclass(), hashMap);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    static LinkedHashMap<String, ColumnEntity> findColumnMap(final Class<?> clazz) {
        synchronized (TableUtils.class) {
            final LinkedHashMap linkedHashMap = new LinkedHashMap();
            addColumns2Map(clazz, (HashMap<String, ColumnEntity>)linkedHashMap);
            return (LinkedHashMap<String, ColumnEntity>)linkedHashMap;
        }
    }
}
