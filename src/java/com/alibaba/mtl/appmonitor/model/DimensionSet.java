package com.alibaba.mtl.appmonitor.model;

import java.util.Iterator;
import java.util.Collection;
import com.alibaba.mtl.log.d.i;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class DimensionSet implements Parcelable
{
    public static final Parcelable$Creator<DimensionSet> CREATOR;
    private List<Dimension> c;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<DimensionSet>() {
            public DimensionSet[] a(final int n) {
                return new DimensionSet[n];
            }
            
            public DimensionSet b(final Parcel parcel) {
                return DimensionSet.a(parcel);
            }
        };
    }
    
    private DimensionSet() {
        this.c = (List<Dimension>)new ArrayList(3);
    }
    
    static DimensionSet a(final Parcel parcel) {
        final DimensionSet create = create();
        try {
            final Parcelable[] parcelableArray = parcel.readParcelableArray(DimensionSet.class.getClassLoader());
            if (parcelableArray != null) {
                if (create.c == null) {
                    create.c = (List<Dimension>)new ArrayList();
                }
                for (int i = 0; i < parcelableArray.length; ++i) {
                    if (parcelableArray[i] != null && parcelableArray[i] instanceof Dimension) {
                        create.c.add((Object)parcelableArray[i]);
                    }
                    else {
                        i.a("DimensionSet", new Object[] { "parcelables[i]:", parcelableArray[i] });
                    }
                }
            }
        }
        finally {
            final Throwable t;
            i.a("DimensionSet", "[readFromParcel]", t);
        }
        return create;
    }
    
    public static DimensionSet create() {
        return new DimensionSet();
    }
    
    public static DimensionSet create(final Collection<String> collection) {
        final DimensionSet set = new DimensionSet();
        if (collection != null) {
            final Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                set.addDimension(new Dimension((String)iterator.next()));
            }
        }
        return set;
    }
    
    public static DimensionSet create(final String[] array) {
        final DimensionSet set = new DimensionSet();
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                set.addDimension(new Dimension(array[i]));
            }
        }
        return set;
    }
    
    public DimensionSet addDimension(final Dimension dimension) {
        if (this.c.contains((Object)dimension)) {
            return this;
        }
        this.c.add((Object)dimension);
        return this;
    }
    
    public DimensionSet addDimension(final String s) {
        return this.addDimension(new Dimension(s));
    }
    
    public DimensionSet addDimension(final String s, final String s2) {
        return this.addDimension(new Dimension(s, s2));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Dimension getDimension(final String s) {
        for (final Dimension dimension : this.c) {
            if (dimension.getName().equals((Object)s)) {
                return dimension;
            }
        }
        return null;
    }
    
    public List<Dimension> getDimensions() {
        return this.c;
    }
    
    public void setConstantValue(final DimensionValueSet set) {
        final List<Dimension> c = this.c;
        if (c != null && set != null) {
            for (final Dimension dimension : c) {
                if (dimension.getConstantValue() != null && set.getValue(dimension.getName()) == null) {
                    set.setValue(dimension.getName(), dimension.getConstantValue());
                }
            }
        }
    }
    
    public boolean valid(final DimensionValueSet set) {
        final List<Dimension> c = this.c;
        if (c != null) {
            if (set != null) {
                final Iterator iterator = c.iterator();
                while (iterator.hasNext()) {
                    if (!set.containValue(((Dimension)iterator.next()).getName())) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final List<Dimension> c = this.c;
        if (c != null) {
            try {
                final Object[] array = c.toArray();
                Object[] array2 = null;
                if (array != null) {
                    final Dimension[] array3 = new Dimension[array.length];
                    int n2 = 0;
                    while (true) {
                        array2 = array3;
                        if (n2 >= array.length) {
                            break;
                        }
                        array3[n2] = (Dimension)array[n2];
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
