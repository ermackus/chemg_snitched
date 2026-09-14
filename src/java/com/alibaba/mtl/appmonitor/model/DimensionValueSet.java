package com.alibaba.mtl.appmonitor.model;

import java.util.Iterator;
import java.util.Map$Entry;
import com.alibaba.mtl.appmonitor.c.a;
import android.os.Parcel;
import java.util.LinkedHashMap;
import java.util.Map;
import android.os.Parcelable$Creator;
import com.alibaba.mtl.appmonitor.c.b;
import android.os.Parcelable;

public class DimensionValueSet implements Parcelable, b
{
    public static final Parcelable$Creator<DimensionValueSet> CREATOR;
    protected Map<String, String> map;
    
    static {
        CREATOR = (Parcelable$Creator)new DimensionValueSet$1();
    }
    
    @Deprecated
    public DimensionValueSet() {
        if (this.map == null) {
            this.map = (Map<String, String>)new LinkedHashMap();
        }
    }
    
    static DimensionValueSet a(final Parcel parcel) {
        DimensionValueSet set = null;
        try {
            final DimensionValueSet create = create();
            try {
                create.map = (Map<String, String>)parcel.readHashMap(DimensionValueSet.class.getClassLoader());
            }
            finally {}
        }
        finally {
            set = null;
        }
        final Throwable t;
        t.printStackTrace();
        return set;
    }
    
    public static DimensionValueSet create() {
        return (DimensionValueSet)a.a().a((Class)DimensionValueSet.class, new Object[0]);
    }
    
    @Deprecated
    public static DimensionValueSet create(final int n) {
        return (DimensionValueSet)a.a().a((Class)DimensionValueSet.class, new Object[0]);
    }
    
    public static DimensionValueSet fromStringMap(final Map<String, String> map) {
        final DimensionValueSet set = (DimensionValueSet)a.a().a((Class)DimensionValueSet.class, new Object[0]);
        for (final Map$Entry map$Entry : map.entrySet()) {
            final Map<String, String> map2 = set.map;
            final Object key = map$Entry.getKey();
            String s;
            if (map$Entry.getValue() != null) {
                s = (String)map$Entry.getValue();
            }
            else {
                s = "null";
            }
            map2.put(key, (Object)s);
        }
        return set;
    }
    
    public DimensionValueSet addValues(final DimensionValueSet set) {
        if (set != null) {
            final Map<String, String> map = set.getMap();
            if (map != null) {
                for (final Map$Entry map$Entry : map.entrySet()) {
                    final Map<String, String> map2 = this.map;
                    final Object key = map$Entry.getKey();
                    String s;
                    if (map$Entry.getValue() != null) {
                        s = (String)map$Entry.getValue();
                    }
                    else {
                        s = "null";
                    }
                    map2.put(key, (Object)s);
                }
            }
        }
        return this;
    }
    
    public void clean() {
        this.map.clear();
    }
    
    public boolean containValue(final String s) {
        return this.map.containsKey((Object)s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final DimensionValueSet set = (DimensionValueSet)o;
        final Map<String, String> map = this.map;
        if (map == null) {
            if (set.map != null) {
                return false;
            }
        }
        else if (!map.equals((Object)set.map)) {
            return false;
        }
        return true;
    }
    
    public void fill(final Object... array) {
        if (this.map == null) {
            this.map = (Map<String, String>)new LinkedHashMap();
        }
    }
    
    public Map<String, String> getMap() {
        return this.map;
    }
    
    public String getValue(final String s) {
        return (String)this.map.get((Object)s);
    }
    
    @Override
    public int hashCode() {
        final Map<String, String> map = this.map;
        int hashCode;
        if (map == null) {
            hashCode = 0;
        }
        else {
            hashCode = map.hashCode();
        }
        return 31 + hashCode;
    }
    
    public void setMap(final Map<String, String> map) {
        for (final Map$Entry map$Entry : map.entrySet()) {
            final Map<String, String> map2 = this.map;
            final Object key = map$Entry.getKey();
            String s;
            if (map$Entry.getValue() != null) {
                s = (String)map$Entry.getValue();
            }
            else {
                s = "null";
            }
            map2.put(key, (Object)s);
        }
    }
    
    public DimensionValueSet setValue(final String s, String s2) {
        final Map<String, String> map = this.map;
        if (s2 == null) {
            s2 = "null";
        }
        map.put((Object)s, (Object)s2);
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeMap((Map)this.map);
    }
}
