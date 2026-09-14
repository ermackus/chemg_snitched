package org.xutils.db.table;

import android.text.TextUtils;
import java.util.Date;
import java.util.HashMap;

public final class DbModel
{
    private HashMap<String, String> dataMap;
    
    public DbModel() {
        this.dataMap = (HashMap<String, String>)new HashMap();
    }
    
    public void add(final String s, final String s2) {
        this.dataMap.put((Object)s, (Object)s2);
    }
    
    public boolean getBoolean(String s) {
        s = (String)this.dataMap.get((Object)s);
        if (s != null) {
            boolean b;
            if (s.length() == 1) {
                b = "1".equals((Object)s);
            }
            else {
                b = Boolean.valueOf(s);
            }
            return b;
        }
        return false;
    }
    
    public HashMap<String, String> getDataMap() {
        return this.dataMap;
    }
    
    public Date getDate(final String s) {
        return new Date((long)Long.valueOf((String)this.dataMap.get((Object)s)));
    }
    
    public double getDouble(final String s) {
        return Double.valueOf((String)this.dataMap.get((Object)s));
    }
    
    public float getFloat(final String s) {
        return Float.valueOf((String)this.dataMap.get((Object)s));
    }
    
    public int getInt(final String s) {
        return Integer.valueOf((String)this.dataMap.get((Object)s));
    }
    
    public long getLong(final String s) {
        return Long.valueOf((String)this.dataMap.get((Object)s));
    }
    
    public java.sql.Date getSqlDate(final String s) {
        return new java.sql.Date((long)Long.valueOf((String)this.dataMap.get((Object)s)));
    }
    
    public String getString(final String s) {
        return (String)this.dataMap.get((Object)s);
    }
    
    public boolean isEmpty(final String s) {
        return TextUtils.isEmpty((CharSequence)this.dataMap.get((Object)s));
    }
}
