package com.alibaba.mtl.appmonitor.model;

import java.util.Map$Entry;
import java.util.Iterator;
import com.alibaba.mtl.appmonitor.c.a;
import java.util.HashMap;
import android.os.Parcel;
import java.util.LinkedHashMap;
import java.util.Map;
import android.os.Parcelable$Creator;
import com.alibaba.mtl.appmonitor.c.b;
import android.os.Parcelable;

public class MeasureValueSet implements Parcelable, b
{
    public static final Parcelable$Creator<MeasureValueSet> CREATOR;
    private Map<String, MeasureValue> map;
    
    static {
        CREATOR = (Parcelable$Creator)new MeasureValueSet$1();
    }
    
    @Deprecated
    public MeasureValueSet() {
        this.map = (Map<String, MeasureValue>)new LinkedHashMap();
    }
    
    static MeasureValueSet a(final Parcel parcel) {
        MeasureValueSet set;
        try {
            final MeasureValueSet create;
            set = (create = create());
            final Parcel parcel2 = parcel;
            final Class<DimensionValueSet> clazz = DimensionValueSet.class;
            final ClassLoader classLoader = clazz.getClassLoader();
            final HashMap hashMap = parcel2.readHashMap(classLoader);
            create.map = (Map<String, MeasureValue>)hashMap;
            return set;
        }
        finally {
            final MeasureValueSet set2 = null;
        }
        try {
            final MeasureValueSet create = set;
            final Parcel parcel2 = parcel;
            final Class<DimensionValueSet> clazz = DimensionValueSet.class;
            final ClassLoader classLoader = clazz.getClassLoader();
            final HashMap hashMap = parcel2.readHashMap(classLoader);
            create.map = (Map<String, MeasureValue>)hashMap;
            return set;
        }
        finally {
            return set;
        }
    }
    
    private static Double a(final String s) {
        try {
            return Double.valueOf(s);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static MeasureValueSet create() {
        return (MeasureValueSet)a.a().a((Class)MeasureValueSet.class, new Object[0]);
    }
    
    @Deprecated
    public static MeasureValueSet create(final int n) {
        return (MeasureValueSet)a.a().a((Class)MeasureValueSet.class, new Object[0]);
    }
    
    public static MeasureValueSet create(final Map<String, Double> map) {
        final MeasureValueSet set = (MeasureValueSet)a.a().a((Class)MeasureValueSet.class, new Object[0]);
        if (map != null) {
            for (final String s : map.keySet()) {
                final Double n = (Double)map.get((Object)s);
                if (n != null) {
                    set.map.put((Object)s, (Object)a.a().a((Class)MeasureValue.class, new Object[] { n }));
                }
            }
        }
        return set;
    }
    
    public static MeasureValueSet fromStringMap(final Map<String, String> map) {
        final MeasureValueSet set = (MeasureValueSet)a.a().a((Class)MeasureValueSet.class, new Object[0]);
        if (map != null) {
            for (final Map$Entry map$Entry : map.entrySet()) {
                final Double a = a((String)map$Entry.getValue());
                if (a != null) {
                    set.map.put(map$Entry.getKey(), (Object)com.alibaba.mtl.appmonitor.c.a.a().a((Class)MeasureValue.class, new Object[] { a }));
                }
            }
        }
        return set;
    }
    
    public void clean() {
        final Iterator iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            a.a().a((b)iterator.next());
        }
        this.map.clear();
    }
    
    public boolean containValue(final String s) {
        return this.map.containsKey((Object)s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void fill(final Object... array) {
        if (this.map == null) {
            this.map = (Map<String, MeasureValue>)new LinkedHashMap();
        }
    }
    
    public Map<String, MeasureValue> getMap() {
        return this.map;
    }
    
    public MeasureValue getValue(final String s) {
        return (MeasureValue)this.map.get((Object)s);
    }
    
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    public void merge(final MeasureValueSet set) {
        for (final String s : this.map.keySet()) {
            ((MeasureValue)this.map.get((Object)s)).merge(set.getValue(s));
        }
    }
    
    public void setMap(final Map<String, MeasureValue> map) {
        this.map = map;
    }
    
    public MeasureValueSet setValue(final String s, final double n) {
        this.map.put((Object)s, (Object)a.a().a((Class)MeasureValue.class, new Object[] { n }));
        return this;
    }
    
    public void setValue(final String s, final MeasureValue measureValue) {
        this.map.put((Object)s, (Object)measureValue);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeMap((Map)this.map);
    }
}
