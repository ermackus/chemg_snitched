package com.alipay.android.phone.mrpc.core;

import com.alipay.android.phone.mrpc.core.a.c;
import com.alipay.android.phone.mrpc.core.a.f;
import java.lang.reflect.Type;
import com.alipay.android.phone.mrpc.core.a.d;
import com.alipay.android.phone.mrpc.core.a.e;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import com.alipay.mobile.framework.service.annotation.OperationType;
import android.os.Looper;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public final class z
{
    public static final ThreadLocal<Object> a;
    public static final ThreadLocal<Map<String, Object>> b;
    public byte c;
    public AtomicInteger d;
    public x e;
    
    static {
        a = new ThreadLocal();
        b = new ThreadLocal();
    }
    
    public z(final x e) {
        this.c = 0;
        this.e = e;
        this.d = new AtomicInteger();
    }
    
    public final Object a(final Method method, final Object[] array) {
        if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
            final OperationType operationType = (OperationType)method.getAnnotation((Class)OperationType.class);
            final boolean b = method.getAnnotation((Class)ResetCookie.class) != null;
            final Type genericReturnType = method.getGenericReturnType();
            method.getAnnotations();
            z.a.set((Object)null);
            z.b.set((Object)null);
            if (operationType != null) {
                final String value = operationType.value();
                final int incrementAndGet = this.d.incrementAndGet();
                try {
                    if (this.c == 0) {
                        final e e = new e(incrementAndGet, value, (Object)array);
                        if (z.b.get() != null) {
                            ((f)e).a(z.b.get());
                        }
                        final byte[] array2 = (byte[])((v)new j(this.e.a(), method, incrementAndGet, value, ((f)e).a(), b)).a();
                        z.b.set((Object)null);
                        final Object a = ((c)new d(genericReturnType, array2)).a();
                        if (genericReturnType != Void.TYPE) {
                            z.a.set(a);
                        }
                    }
                    return z.a.get();
                }
                catch (final RpcException ex) {
                    ex.setOperationType(value);
                    throw ex;
                }
            }
            throw new IllegalStateException("OperationType must be set.");
        }
        throw new IllegalThreadStateException("can't in main thread call rpc .");
    }
}
