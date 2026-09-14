package com.alibaba.mtl.log.sign;

import java.util.Map;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.a;
import android.content.Context;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class SecurityRequestAuth implements IRequestAuth
{
    private boolean F;
    private Class a;
    private Field a;
    private Method a;
    private String ad;
    private Object b;
    private Field b;
    private Object c;
    private Field c;
    private String g;
    private int z;
    
    public SecurityRequestAuth(final String g, final String ad) {
        this.g = null;
        this.b = null;
        this.c = null;
        this.a = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = null;
        this.z = 1;
        this.F = false;
        this.g = g;
        this.ad = ad;
    }
    
    private void D() {
        synchronized (this) {
            if (this.F) {
                return;
            }
            final Object o = null;
            Class clazz = null;
            try {
                final Class<?> forName = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
                try {
                    this.b = forName.getMethod("getInstance", Context.class).invoke((Object)null, new Object[] { com.alibaba.mtl.log.a.getContext() });
                    this.c = forName.getMethod("getSecureSignatureComp", (Class[])new Class[0]).invoke(this.b, new Object[0]);
                }
                finally {}
            }
            finally {
                clazz = null;
            }
            final Throwable t;
            i.a("SecurityRequestAuth", (Object)"initSecurityCheck", t);
            if (clazz != null) {
                try {
                    final Class<?> forName2 = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                    this.a = forName2;
                    this.a = forName2.getDeclaredField("appKey");
                    this.b = this.a.getDeclaredField("paramMap");
                    this.c = this.a.getDeclaredField("requestType");
                    Method method = null;
                    try {
                        clazz.getMethod("isOpen", (Class[])new Class[0]);
                    }
                    finally {
                        final Throwable t2;
                        i.a("SecurityRequestAuth", (Object)"initSecurityCheck", t2);
                        method = null;
                    }
                    boolean booleanValue;
                    if (method != null) {
                        booleanValue = (boolean)method.invoke(this.b, new Object[0]);
                    }
                    else {
                        Object o2 = null;
                        try {
                            Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                        }
                        finally {
                            final Throwable t3;
                            i.a("SecurityRequestAuth", (Object)"initSecurityCheck", t3);
                            o2 = o;
                        }
                        booleanValue = (o2 == null);
                    }
                    int z;
                    if (booleanValue) {
                        z = 1;
                    }
                    else {
                        z = 12;
                    }
                    this.z = z;
                    this.a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", this.a, String.class);
                }
                finally {
                    final Throwable t4;
                    i.a("SecurityRequestAuth", (Object)"initSecurityCheck", t4);
                }
            }
            this.F = true;
        }
    }
    
    public String getAppkey() {
        return this.g;
    }
    
    public String getSign(final String s) {
        if (!this.F) {
            this.D();
        }
        final String g = this.g;
        final String s2 = null;
        if (g == null) {
            i.a("SecurityRequestAuth", new Object[] { "There is no appkey,please check it!" });
            return null;
        }
        if (s == null) {
            return null;
        }
        String s3 = s2;
        if (this.b != null) {
            final Class a = this.a;
            s3 = s2;
            if (a != null) {
                s3 = s2;
                if (this.a != null) {
                    s3 = s2;
                    if (this.b != null) {
                        s3 = s2;
                        if (this.c != null) {
                            s3 = s2;
                            if (this.a != null) {
                                s3 = s2;
                                if (this.c != null) {
                                    try {
                                        final Object instance = a.newInstance();
                                        this.a.set(instance, (Object)this.g);
                                        ((Map)this.b.get(instance)).put((Object)"INPUT", (Object)s);
                                        this.c.set(instance, (Object)this.z);
                                        s3 = (String)this.a.invoke(this.c, new Object[] { instance, this.ad });
                                    }
                                    catch (final Exception ex) {
                                        ex.printStackTrace();
                                        s3 = s2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return s3;
    }
}
