package com.alipay.sdk.m.q0;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface a extends IInterface
{
    String a() throws RemoteException;
    
    String a(final String p0) throws RemoteException;
    
    String b() throws RemoteException;
    
    String b(final String p0) throws RemoteException;
    
    boolean c() throws RemoteException;
    
    boolean c(final String p0) throws RemoteException;
    
    public abstract static class a extends Binder implements a
    {
        public static final String a = "com.zui.deviceidservice.IDeviceidInterface";
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        
        public a() {
            this.attachInterface((IInterface)this, "com.zui.deviceidservice.IDeviceidInterface");
        }
        
        public static a a(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            if (queryLocalInterface != null && queryLocalInterface instanceof a) {
                return (a)queryLocalInterface;
            }
            return new a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            if (n == 1598968902) {
                parcel2.writeString("com.zui.deviceidservice.IDeviceidInterface");
                return true;
            }
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 6: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    n = (this.c(parcel.readString()) ? 1 : 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    final String a = this.a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    final String b = this.b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    n = (this.c() ? 1 : 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    final String b2 = this.b();
                    parcel2.writeNoException();
                    parcel2.writeString(b2);
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
                    final String a2 = this.a();
                    parcel2.writeNoException();
                    parcel2.writeString(a2);
                    return true;
                }
            }
        }
        
        public static class a implements com.alipay.sdk.m.q0.a
        {
            public IBinder a;
            
            public a(final IBinder a) {
                this.a = a;
            }
            
            @Override
            public String a() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String a(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    obtain.writeString(string);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.a;
            }
            
            @Override
            public String b() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String b(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    obtain.writeString(string);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean c() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    final IBinder a = this.a;
                    boolean b = false;
                    a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean c(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    obtain.writeString(s);
                    final IBinder a = this.a;
                    boolean b = false;
                    a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public String d() {
                return "com.zui.deviceidservice.IDeviceidInterface";
            }
        }
    }
}
