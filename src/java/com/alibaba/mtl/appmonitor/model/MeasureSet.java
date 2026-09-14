package com.alibaba.mtl.appmonitor.model;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class MeasureSet implements Parcelable
{
    public static final Parcelable$Creator<MeasureSet> CREATOR;
    private List<Measure> d;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<MeasureSet>() {
            public MeasureSet[] a(final int n) {
                return new MeasureSet[n];
            }
            
            public MeasureSet b(final Parcel parcel) {
                return MeasureSet.a(parcel);
            }
        };
    }
    
    private MeasureSet() {
        this.d = (List<Measure>)new ArrayList(3);
    }
    
    static MeasureSet a(final Parcel parcel) {
        final MeasureSet create = create();
        try {
            final Parcelable[] array = parcel.readParcelableArray(MeasureSet.class.getClassLoader());
            if (array != null) {
                final ArrayList d = new ArrayList(array.length);
                for (int i = 0; i < array.length; ++i) {
                    d.add((Object)array[i]);
                }
                create.d = (List<Measure>)d;
            }
        }
        finally {
            final Throwable t;
            t.printStackTrace();
        }
        return create;
    }
    
    public static MeasureSet create() {
        return new MeasureSet();
    }
    
    public static MeasureSet create(final Collection<String> collection) {
        final MeasureSet set = new MeasureSet();
        if (collection != null) {
            final Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                set.addMeasure((String)iterator.next());
            }
        }
        return set;
    }
    
    public static MeasureSet create(final String[] array) {
        final MeasureSet set = new MeasureSet();
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                set.addMeasure(array[i]);
            }
        }
        return set;
    }
    
    public MeasureSet addMeasure(final Measure measure) {
        if (!this.d.contains((Object)measure)) {
            this.d.add((Object)measure);
        }
        return this;
    }
    
    public MeasureSet addMeasure(final String s) {
        return this.addMeasure(new Measure(s));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Measure getMeasure(final String s) {
        for (final Measure measure : this.d) {
            if (measure.getName().equals((Object)s)) {
                return measure;
            }
        }
        return null;
    }
    
    public List<Measure> getMeasures() {
        return this.d;
    }
    
    public void setConstantValue(final MeasureValueSet set) {
        final List<Measure> d = this.d;
        if (d != null && set != null) {
            for (final Measure measure : d) {
                if (measure.getConstantValue() != null && set.getValue(measure.getName()) == null) {
                    set.setValue(measure.getName(), (double)measure.getConstantValue());
                }
            }
        }
    }
    
    public void upateMeasure(final Measure measure) {
        for (int size = this.d.size(), i = 0; i < size; ++i) {
            if (TextUtils.equals((CharSequence)((Measure)this.d.get(i)).name, (CharSequence)measure.name)) {
                ((Measure)this.d.get(i)).setMax(measure.getMax());
                ((Measure)this.d.get(i)).setMin(measure.getMin());
                ((Measure)this.d.get(i)).setConstantValue(measure.getConstantValue());
            }
        }
    }
    
    public void upateMeasures(final List<Measure> list) {
        final int size = this.d.size();
        final int size2 = list.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size2; ++j) {
                if (TextUtils.equals((CharSequence)((Measure)this.d.get(i)).name, (CharSequence)((Measure)list.get(j)).name)) {
                    ((Measure)this.d.get(i)).setMax(((Measure)list.get(j)).getMax());
                    ((Measure)this.d.get(i)).setMin(((Measure)list.get(j)).getMin());
                }
            }
        }
    }
    
    public boolean valid(final MeasureValueSet set) {
        if (this.d != null) {
            if (set == null) {
                return false;
            }
            for (int i = 0; i < this.d.size(); ++i) {
                final Measure measure = (Measure)this.d.get(i);
                if (measure != null) {
                    final String name = measure.getName();
                    if (!set.containValue(name)) {
                        return false;
                    }
                    if (!measure.valid(set.getValue(name))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final List<Measure> d = this.d;
        if (d != null) {
            try {
                final Object[] array = d.toArray();
                Object[] array2 = null;
                if (array != null) {
                    final Measure[] array3 = new Measure[array.length];
                    int n2 = 0;
                    while (true) {
                        array2 = array3;
                        if (n2 >= array.length) {
                            break;
                        }
                        array3[n2] = (Measure)array[n2];
                        ++n2;
                    }
                }
                parcel.writeParcelableArray((Parcelable[])array2, n);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
