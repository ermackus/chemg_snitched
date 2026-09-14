package org.xutils.db.converter;

import org.xutils.db.sqlite.ColumnDbType;
import org.xutils.common.util.LogUtil;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public final class ColumnConverterFactory
{
    private static final ConcurrentHashMap<String, ColumnConverter> columnType_columnConverter_map;
    
    static {
        columnType_columnConverter_map = new ConcurrentHashMap();
        final BooleanColumnConverter booleanColumnConverter = new BooleanColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Boolean.TYPE.getName(), (Object)booleanColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Boolean.class.getName(), (Object)booleanColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)byte[].class.getName(), (Object)new ByteArrayColumnConverter());
        final ByteColumnConverter byteColumnConverter = new ByteColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Byte.TYPE.getName(), (Object)byteColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Byte.class.getName(), (Object)byteColumnConverter);
        final CharColumnConverter charColumnConverter = new CharColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Character.TYPE.getName(), (Object)charColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Character.class.getName(), (Object)charColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Date.class.getName(), (Object)new DateColumnConverter());
        final DoubleColumnConverter doubleColumnConverter = new DoubleColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Double.TYPE.getName(), (Object)doubleColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Double.class.getName(), (Object)doubleColumnConverter);
        final FloatColumnConverter floatColumnConverter = new FloatColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Float.TYPE.getName(), (Object)floatColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Float.class.getName(), (Object)floatColumnConverter);
        final IntegerColumnConverter integerColumnConverter = new IntegerColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Integer.TYPE.getName(), (Object)integerColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Integer.class.getName(), (Object)integerColumnConverter);
        final LongColumnConverter longColumnConverter = new LongColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Long.TYPE.getName(), (Object)longColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Long.class.getName(), (Object)longColumnConverter);
        final ShortColumnConverter shortColumnConverter = new ShortColumnConverter();
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Short.TYPE.getName(), (Object)shortColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)Short.class.getName(), (Object)shortColumnConverter);
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)java.sql.Date.class.getName(), (Object)new SqlDateColumnConverter());
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)String.class.getName(), (Object)new StringColumnConverter());
    }
    
    private ColumnConverterFactory() {
    }
    
    public static ColumnConverter getColumnConverter(final Class cls) {
        Object o = null;
        Label_0081: {
            if (ColumnConverterFactory.columnType_columnConverter_map.containsKey((Object)cls.getName())) {
                o = ColumnConverterFactory.columnType_columnConverter_map.get((Object)cls.getName());
            }
            else {
                if (ColumnConverter.class.isAssignableFrom(cls)) {
                    try {
                        final ColumnConverter columnConverter = cls.newInstance();
                        if (columnConverter != null) {
                            ColumnConverterFactory.columnType_columnConverter_map.put((Object)cls.getName(), (Object)columnConverter);
                        }
                        break Label_0081;
                    }
                    finally {
                        LogUtil.e(((Throwable)o).getMessage(), (Throwable)o);
                    }
                }
                o = null;
            }
        }
        if (o != null) {
            return (ColumnConverter)o;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Database Column Not Support: ");
        sb.append(cls.getName());
        sb.append(", please impl ColumnConverter or use ColumnConverterFactory#registerColumnConverter(...)");
        throw new RuntimeException(sb.toString());
    }
    
    public static ColumnDbType getDbColumnType(final Class clazz) {
        return getColumnConverter(clazz).getColumnDbType();
    }
    
    public static boolean isSupportColumnConverter(final Class cls) {
        final boolean containsKey = ColumnConverterFactory.columnType_columnConverter_map.containsKey((Object)cls.getName());
        boolean b = true;
        if (containsKey) {
            return true;
        }
        if (!ColumnConverter.class.isAssignableFrom(cls)) {
            return false;
        }
        try {
            final ColumnConverter columnConverter = cls.newInstance();
            if (columnConverter != null) {
                ColumnConverterFactory.columnType_columnConverter_map.put((Object)cls.getName(), (Object)columnConverter);
            }
            if (columnConverter != null) {
                b = false;
            }
            return b;
        }
        finally {
            return false;
        }
    }
    
    public static void registerColumnConverter(final Class clazz, final ColumnConverter columnConverter) {
        ColumnConverterFactory.columnType_columnConverter_map.put((Object)clazz.getName(), (Object)columnConverter);
    }
}
