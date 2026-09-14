package com.tencent.bugly.crashreport.common.strategy;

import com.tencent.bugly.proguard.z;
import android.os.Parcel;
import java.util.Map;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class StrategyBean implements Parcelable
{
    public static final Parcelable$Creator<StrategyBean> CREATOR;
    public static String a = "https://android.bugly.qq.com/rqd/async";
    public static String b = "https://android.bugly.qq.com/rqd/async";
    public long c;
    public long d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public long o;
    public String p;
    public String q;
    public String r;
    public Map<String, String> s;
    public int t;
    public long u;
    public long v;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<StrategyBean>() {};
    }
    
    public StrategyBean() {
        this.c = -1L;
        this.d = -1L;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = false;
        this.j = true;
        this.k = true;
        this.l = true;
        this.m = true;
        this.o = 30000L;
        this.p = StrategyBean.a;
        this.q = StrategyBean.b;
        this.t = 10;
        this.u = 300000L;
        this.v = -1L;
        this.d = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L");
        sb.append("@)");
        sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K");
        sb.append("@!");
        this.r = sb.toString();
    }
    
    public StrategyBean(final Parcel parcel) {
        this.c = -1L;
        this.d = -1L;
        final boolean b = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = false;
        this.j = true;
        this.k = true;
        this.l = true;
        this.m = true;
        this.o = 30000L;
        this.p = StrategyBean.a;
        this.q = StrategyBean.b;
        this.t = 10;
        this.u = 300000L;
        this.v = -1L;
        try {
            this.d = parcel.readLong();
            this.e = (parcel.readByte() == 1);
            this.f = (parcel.readByte() == 1);
            this.g = (parcel.readByte() == 1);
            this.p = parcel.readString();
            this.q = parcel.readString();
            this.r = parcel.readString();
            this.s = z.b(parcel);
            this.h = (parcel.readByte() == 1);
            this.i = (parcel.readByte() == 1);
            this.l = (parcel.readByte() == 1);
            this.m = (parcel.readByte() == 1);
            this.o = parcel.readLong();
            this.j = (parcel.readByte() == 1);
            this.k = (parcel.readByte() == 1 && b);
            this.n = parcel.readLong();
            this.t = parcel.readInt();
            this.u = parcel.readLong();
            this.v = parcel.readLong();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeLong(this.d);
        parcel.writeByte((byte)(byte)(this.e ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.f ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.g ? 1 : 0));
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        z.b(parcel, this.s);
        parcel.writeByte((byte)(byte)(this.h ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.i ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.l ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.m ? 1 : 0));
        parcel.writeLong(this.o);
        parcel.writeByte((byte)(byte)(this.j ? 1 : 0));
        parcel.writeByte((byte)(byte)(this.k ? 1 : 0));
        parcel.writeLong(this.n);
        parcel.writeInt(this.t);
        parcel.writeLong(this.u);
        parcel.writeLong(this.v);
    }
}
