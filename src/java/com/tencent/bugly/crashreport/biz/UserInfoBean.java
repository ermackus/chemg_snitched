package com.tencent.bugly.crashreport.biz;

import com.tencent.bugly.proguard.z;
import android.os.Parcel;
import java.util.Map;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class UserInfoBean implements Parcelable
{
    public static final Parcelable$Creator<UserInfoBean> CREATOR;
    public long a;
    public int b;
    public String c;
    public String d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public String j;
    public long k;
    public boolean l;
    public String m;
    public String n;
    public int o;
    public int p;
    public int q;
    public Map<String, String> r;
    public Map<String, String> s;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<UserInfoBean>() {};
    }
    
    public UserInfoBean() {
        this.k = 0L;
        this.l = false;
        this.m = "unknown";
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
    }
    
    public UserInfoBean(final Parcel parcel) {
        this.k = 0L;
        boolean l = false;
        this.l = false;
        this.m = "unknown";
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        if (parcel.readByte() == 1) {
            l = true;
        }
        this.l = l;
        this.m = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = z.b(parcel);
        this.s = z.b(parcel);
        this.n = parcel.readString();
        this.o = parcel.readInt();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeByte((byte)(byte)(this.l ? 1 : 0));
        parcel.writeString(this.m);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        z.b(parcel, this.r);
        z.b(parcel, this.s);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
    }
}
