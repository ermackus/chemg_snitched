package org.xutils.db.sqlite;

import java.util.Iterator;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;
import java.lang.reflect.Array;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class WhereBuilder
{
    private final List<String> whereItems;
    
    private WhereBuilder() {
        this.whereItems = (List<String>)new ArrayList();
    }
    
    private void appendCondition(String s, String s2, String s3, final Object o) {
        final StringBuilder sb = new StringBuilder();
        if (this.whereItems.size() > 0) {
            sb.append(" ");
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
            sb.append(" ");
        }
        sb.append("\"");
        sb.append(s2);
        sb.append("\"");
        if ("!=".equals((Object)s3)) {
            s = "<>";
        }
        else {
            s = s3;
            if ("==".equals((Object)s3)) {
                s = "=";
            }
        }
        if (o == null) {
            if ("=".equals((Object)s)) {
                sb.append(" IS NULL");
            }
            else if ("<>".equals((Object)s)) {
                sb.append(" IS NOT NULL");
            }
            else {
                sb.append(" ");
                sb.append(s);
                sb.append(" NULL");
            }
        }
        else {
            sb.append(" ");
            sb.append(s);
            sb.append(" ");
            final boolean equalsIgnoreCase = "IN".equalsIgnoreCase(s);
            final int n = 0;
            int n2 = 0;
            final Iterable iterable = null;
            final Iterable iterable2 = null;
            if (equalsIgnoreCase) {
                Object o2;
                if (o instanceof Iterable) {
                    o2 = o;
                }
                else {
                    o2 = iterable2;
                    if (o.getClass().isArray()) {
                        final int length = Array.getLength(o);
                        final ArrayList list = new ArrayList(length);
                        while (true) {
                            o2 = list;
                            if (n2 >= length) {
                                break;
                            }
                            ((List)list).add(Array.get(o, n2));
                            ++n2;
                        }
                    }
                }
                if (o2 == null) {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
                final StringBuilder sb2 = new StringBuilder("(");
                final Iterator iterator = ((Iterable)o2).iterator();
                while (iterator.hasNext()) {
                    final Object convert2DbValueIfNeeded = ColumnUtils.convert2DbValueIfNeeded(iterator.next());
                    if (ColumnDbType.TEXT.equals((Object)ColumnConverterFactory.getDbColumnType(convert2DbValueIfNeeded.getClass()))) {
                        s2 = (s = convert2DbValueIfNeeded.toString());
                        if (s2.indexOf(39) != -1) {
                            s = s2.replace((CharSequence)"'", (CharSequence)"''");
                        }
                        sb2.append("'");
                        sb2.append(s);
                        sb2.append("'");
                    }
                    else {
                        sb2.append(convert2DbValueIfNeeded);
                    }
                    sb2.append(",");
                }
                sb2.deleteCharAt(sb2.length() - 1);
                sb2.append(")");
                sb.append(sb2.toString());
            }
            else if ("BETWEEN".equalsIgnoreCase(s)) {
                Object o3;
                if (o instanceof Iterable) {
                    o3 = o;
                }
                else {
                    o3 = iterable;
                    if (o.getClass().isArray()) {
                        final int length2 = Array.getLength(o);
                        final ArrayList list2 = new ArrayList(length2);
                        int n3 = n;
                        while (true) {
                            o3 = list2;
                            if (n3 >= length2) {
                                break;
                            }
                            ((List)list2).add(Array.get(o, n3));
                            ++n3;
                        }
                    }
                }
                if (o3 == null) {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
                final Iterator iterator2 = ((Iterable)o3).iterator();
                if (!iterator2.hasNext()) {
                    throw new IllegalArgumentException("value must have tow items.");
                }
                final Object next = iterator2.next();
                if (!iterator2.hasNext()) {
                    throw new IllegalArgumentException("value must have tow items.");
                }
                final Object next2 = iterator2.next();
                final Object convert2DbValueIfNeeded2 = ColumnUtils.convert2DbValueIfNeeded(next);
                final Object convert2DbValueIfNeeded3 = ColumnUtils.convert2DbValueIfNeeded(next2);
                if (ColumnDbType.TEXT.equals((Object)ColumnConverterFactory.getDbColumnType(convert2DbValueIfNeeded2.getClass()))) {
                    s2 = (s = convert2DbValueIfNeeded2.toString());
                    if (s2.indexOf(39) != -1) {
                        s = s2.replace((CharSequence)"'", (CharSequence)"''");
                    }
                    s3 = (s2 = convert2DbValueIfNeeded3.toString());
                    if (s3.indexOf(39) != -1) {
                        s2 = s3.replace((CharSequence)"'", (CharSequence)"''");
                    }
                    sb.append("'");
                    sb.append(s);
                    sb.append("'");
                    sb.append(" AND ");
                    sb.append("'");
                    sb.append(s2);
                    sb.append("'");
                }
                else {
                    sb.append(convert2DbValueIfNeeded2);
                    sb.append(" AND ");
                    sb.append(convert2DbValueIfNeeded3);
                }
            }
            else {
                final Object convert2DbValueIfNeeded4 = ColumnUtils.convert2DbValueIfNeeded(o);
                if (ColumnDbType.TEXT.equals((Object)ColumnConverterFactory.getDbColumnType(convert2DbValueIfNeeded4.getClass()))) {
                    s2 = (s = convert2DbValueIfNeeded4.toString());
                    if (s2.indexOf(39) != -1) {
                        s = s2.replace((CharSequence)"'", (CharSequence)"''");
                    }
                    sb.append("'");
                    sb.append(s);
                    sb.append("'");
                }
                else {
                    sb.append(convert2DbValueIfNeeded4);
                }
            }
        }
        this.whereItems.add((Object)sb.toString());
    }
    
    public static WhereBuilder b() {
        return new WhereBuilder();
    }
    
    public static WhereBuilder b(final String s, final String s2, final Object o) {
        final WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.appendCondition(null, s, s2, o);
        return whereBuilder;
    }
    
    public WhereBuilder and(final String s, final String s2, final Object o) {
        String s3;
        if (this.whereItems.size() == 0) {
            s3 = null;
        }
        else {
            s3 = "AND";
        }
        this.appendCondition(s3, s, s2, o);
        return this;
    }
    
    public WhereBuilder and(final WhereBuilder whereBuilder) {
        String s;
        if (this.whereItems.size() == 0) {
            s = " ";
        }
        else {
            s = "AND ";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append("(");
        sb.append(whereBuilder.toString());
        sb.append(")");
        return this.expr(sb.toString());
    }
    
    public WhereBuilder expr(final String s) {
        final List<String> whereItems = this.whereItems;
        final StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(s);
        whereItems.add((Object)sb.toString());
        return this;
    }
    
    public int getWhereItemSize() {
        return this.whereItems.size();
    }
    
    public WhereBuilder or(final String s, final String s2, final Object o) {
        String s3;
        if (this.whereItems.size() == 0) {
            s3 = null;
        }
        else {
            s3 = "OR";
        }
        this.appendCondition(s3, s, s2, o);
        return this;
    }
    
    public WhereBuilder or(final WhereBuilder whereBuilder) {
        String s;
        if (this.whereItems.size() == 0) {
            s = " ";
        }
        else {
            s = "OR ";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append("(");
        sb.append(whereBuilder.toString());
        sb.append(")");
        return this.expr(sb.toString());
    }
    
    @Override
    public String toString() {
        if (this.whereItems.size() == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator iterator = this.whereItems.iterator();
        while (iterator.hasNext()) {
            sb.append((String)iterator.next());
        }
        return sb.toString();
    }
}
