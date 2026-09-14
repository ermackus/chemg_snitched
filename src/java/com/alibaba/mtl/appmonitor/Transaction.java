package com.alibaba.mtl.appmonitor;

import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import android.text.TextUtils;
import java.util.UUID;
import android.os.Parcel;
import java.util.Map;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class Transaction implements Parcelable
{
    public static final Parcelable$Creator<Transaction> CREATOR;
    protected Integer a;
    protected DimensionValueSet b;
    protected Map<String, String> e;
    private Object lock;
    protected String o;
    protected String p;
    protected String r;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Transaction>() {
            public Transaction[] a(final int n) {
                return new Transaction[n];
            }
            
            public Transaction b(final Parcel parcel) {
                return Transaction.a(parcel);
            }
        };
    }
    
    public Transaction() {
    }
    
    Transaction(final Integer n, final String s, final String s2, final DimensionValueSet set) {
        this(n, s, s2, set, null);
    }
    
    Transaction(final Integer a, final String o, final String p5, final DimensionValueSet b, final String s) {
        this.a = a;
        this.o = o;
        this.p = p5;
        this.r = UUID.randomUUID().toString();
        this.b = b;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            (this.e = (Map<String, String>)new HashMap()).put((Object)LogField.APPKEY.toString(), (Object)s);
        }
        this.lock = new Object();
    }
    
    static Transaction a(final Parcel parcel) {
        final Transaction transaction = new Transaction();
        try {
            transaction.b = (DimensionValueSet)parcel.readParcelable(Transaction.class.getClassLoader());
            transaction.a = parcel.readInt();
            transaction.o = parcel.readString();
            transaction.p = parcel.readString();
            transaction.r = parcel.readString();
            transaction.e = (Map<String, String>)parcel.readHashMap(Transaction.class.getClassLoader());
        }
        finally {
            final Throwable t;
            t.printStackTrace();
        }
        return transaction;
    }
    
    public void addDimensionValues(final DimensionValueSet b) {
        final Object lock = this.lock;
        synchronized (lock) {
            if (this.b == null) {
                this.b = b;
            }
            else {
                this.b.addValues(b);
            }
        }
    }
    
    public void addDimensionValues(final String s, final String s2) {
        final Object lock = this.lock;
        synchronized (lock) {
            if (this.b == null) {
                this.b = com.alibaba.mtl.appmonitor.c.a.a().a(DimensionValueSet.class, new Object[0]);
            }
            this.b.setValue(s, s2);
        }
    }
    
    public void begin(final String s) {
        if (AppMonitor.a == null) {
            return;
        }
        try {
            AppMonitor.a.transaction_begin(this, s);
        }
        catch (final RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void end(final String s) {
        if (AppMonitor.a == null) {
            return;
        }
        try {
            AppMonitor.a.transaction_end(this, s);
        }
        catch (final RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.b, n);
        parcel.writeInt((int)this.a);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.r);
        parcel.writeMap((Map)this.e);
    }
}
