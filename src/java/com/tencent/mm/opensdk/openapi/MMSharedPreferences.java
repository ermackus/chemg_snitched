package com.tencent.mm.opensdk.openapi;

import java.util.Iterator;
import java.util.Map$Entry;
import android.content.ContentValues;
import java.util.HashSet;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import java.util.Set;
import java.util.Map;
import android.content.SharedPreferences$Editor;
import android.database.Cursor;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.a;
import android.content.Context;
import java.util.HashMap;
import android.content.ContentResolver;
import android.content.SharedPreferences;

class MMSharedPreferences implements SharedPreferences
{
    private static final String TAG = "MicroMsg.SDK.SharedPreferences";
    private final String[] columns;
    private final ContentResolver cr;
    private REditor editor;
    private final HashMap<String, Object> values;
    
    public MMSharedPreferences(final Context context) {
        this.columns = new String[] { "_id", "key", "type", "value" };
        this.values = (HashMap<String, Object>)new HashMap();
        this.editor = null;
        this.cr = context.getContentResolver();
    }
    
    private Object getValue(final String s) {
        try {
            final Cursor query = this.cr.query(a.a, this.columns, "key = ?", new String[] { s }, (String)null);
            if (query == null) {
                return null;
            }
            final int columnIndex = query.getColumnIndex("type");
            final int columnIndex2 = query.getColumnIndex("value");
            Object a;
            if (query.moveToFirst()) {
                a = com.tencent.mm.opensdk.channel.a.a.a(query.getInt(columnIndex), query.getString(columnIndex2));
            }
            else {
                a = null;
            }
            query.close();
            return a;
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getValue exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.SharedPreferences", sb.toString());
            return null;
        }
    }
    
    public boolean contains(final String s) {
        return this.getValue(s) != null;
    }
    
    public SharedPreferences$Editor edit() {
        if (this.editor == null) {
            this.editor = new REditor(this.cr);
        }
        return (SharedPreferences$Editor)this.editor;
    }
    
    public Map<String, ?> getAll() {
        try {
            final Cursor query = this.cr.query(a.a, this.columns, (String)null, (String[])null, (String)null);
            if (query == null) {
                return null;
            }
            final int columnIndex = query.getColumnIndex("key");
            final int columnIndex2 = query.getColumnIndex("type");
            final int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                this.values.put((Object)query.getString(columnIndex), com.tencent.mm.opensdk.channel.a.a.a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return (Map<String, ?>)this.values;
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getAll exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.SharedPreferences", sb.toString());
            return (Map<String, ?>)this.values;
        }
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        final Object value = this.getValue(s);
        boolean booleanValue = b;
        if (value != null) {
            booleanValue = b;
            if (value instanceof Boolean) {
                booleanValue = (boolean)value;
            }
        }
        return booleanValue;
    }
    
    public float getFloat(final String s, final float n) {
        final Object value = this.getValue(s);
        float floatValue = n;
        if (value != null) {
            floatValue = n;
            if (value instanceof Float) {
                floatValue = (float)value;
            }
        }
        return floatValue;
    }
    
    public int getInt(final String s, final int n) {
        final Object value = this.getValue(s);
        int intValue = n;
        if (value != null) {
            intValue = n;
            if (value instanceof Integer) {
                intValue = (int)value;
            }
        }
        return intValue;
    }
    
    public long getLong(final String s, final long n) {
        final Object value = this.getValue(s);
        long longValue = n;
        if (value != null) {
            longValue = n;
            if (value instanceof Long) {
                longValue = (long)value;
            }
        }
        return longValue;
    }
    
    public String getString(String s, final String s2) {
        final Object value = this.getValue(s);
        s = s2;
        if (value != null) {
            s = s2;
            if (value instanceof String) {
                s = (String)value;
            }
        }
        return s;
    }
    
    public Set<String> getStringSet(final String s, final Set<String> set) {
        return null;
    }
    
    public void registerOnSharedPreferenceChangeListener(final SharedPreferences$OnSharedPreferenceChangeListener sharedPreferences$OnSharedPreferenceChangeListener) {
    }
    
    public void unregisterOnSharedPreferenceChangeListener(final SharedPreferences$OnSharedPreferenceChangeListener sharedPreferences$OnSharedPreferenceChangeListener) {
    }
    
    private static class REditor implements SharedPreferences$Editor
    {
        private boolean clear;
        private ContentResolver cr;
        private Set<String> remove;
        private Map<String, Object> values;
        
        public REditor(final ContentResolver cr) {
            this.values = (Map<String, Object>)new HashMap();
            this.remove = (Set<String>)new HashSet();
            this.clear = false;
            this.cr = cr;
        }
        
        public void apply() {
        }
        
        public SharedPreferences$Editor clear() {
            this.clear = true;
            return (SharedPreferences$Editor)this;
        }
        
        public boolean commit() {
            final ContentValues contentValues = new ContentValues();
            if (this.clear) {
                this.cr.delete(a.a, (String)null, (String[])null);
                this.clear = false;
            }
            final Iterator iterator = this.remove.iterator();
            while (iterator.hasNext()) {
                this.cr.delete(a.a, "key = ?", new String[] { (String)iterator.next() });
            }
            for (final Map$Entry map$Entry : this.values.entrySet()) {
                final Object value = map$Entry.getValue();
                int n = 0;
                Label_0270: {
                    String string;
                    if (value == null) {
                        string = "unresolve failed, null value";
                    }
                    else {
                        if (value instanceof Integer) {
                            n = 1;
                            break Label_0270;
                        }
                        if (value instanceof Long) {
                            n = 2;
                            break Label_0270;
                        }
                        if (value instanceof String) {
                            n = 3;
                            break Label_0270;
                        }
                        if (value instanceof Boolean) {
                            n = 4;
                            break Label_0270;
                        }
                        if (value instanceof Float) {
                            n = 5;
                            break Label_0270;
                        }
                        if (value instanceof Double) {
                            n = 6;
                            break Label_0270;
                        }
                        final StringBuilder sb = new StringBuilder();
                        sb.append("unresolve failed, unknown type=");
                        sb.append(value.getClass().toString());
                        string = sb.toString();
                    }
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", string);
                    n = 0;
                }
                boolean b;
                if (n == 0) {
                    b = false;
                }
                else {
                    contentValues.put("type", Integer.valueOf(n));
                    contentValues.put("value", value.toString());
                    b = true;
                }
                if (b) {
                    this.cr.update(a.a, contentValues, "key = ?", new String[] { (String)map$Entry.getKey() });
                }
            }
            return true;
        }
        
        public SharedPreferences$Editor putBoolean(final String s, final boolean b) {
            this.values.put((Object)s, (Object)b);
            this.remove.remove((Object)s);
            return (SharedPreferences$Editor)this;
        }
        
        public SharedPreferences$Editor putFloat(final String s, final float n) {
            this.values.put((Object)s, (Object)n);
            this.remove.remove((Object)s);
            return (SharedPreferences$Editor)this;
        }
        
        public SharedPreferences$Editor putInt(final String s, final int n) {
            this.values.put((Object)s, (Object)n);
            this.remove.remove((Object)s);
            return (SharedPreferences$Editor)this;
        }
        
        public SharedPreferences$Editor putLong(final String s, final long n) {
            this.values.put((Object)s, (Object)n);
            this.remove.remove((Object)s);
            return (SharedPreferences$Editor)this;
        }
        
        public SharedPreferences$Editor putString(final String s, final String s2) {
            this.values.put((Object)s, (Object)s2);
            this.remove.remove((Object)s);
            return (SharedPreferences$Editor)this;
        }
        
        public SharedPreferences$Editor putStringSet(final String s, final Set<String> set) {
            return null;
        }
        
        public SharedPreferences$Editor remove(final String s) {
            this.remove.add((Object)s);
            return (SharedPreferences$Editor)this;
        }
    }
}
