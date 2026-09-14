package com.alipay.android.phone.mrpc.core;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.UUID;
import java.lang.reflect.Method;

public final class j extends a
{
    public g g;
    
    public j(final g g, final Method method, final int n, final String s, final byte[] array, final boolean b) {
        super(method, n, s, array, "application/x-www-form-urlencoded", b);
        this.g = g;
    }
    
    public final Object a() {
        final o o = new o(this.g.a());
        o.a(super.b);
        o.a(super.e);
        o.a(super.f);
        o.a("id", String.valueOf(super.d));
        o.a("operationType", super.c);
        o.a("gzip", String.valueOf(this.g.d()));
        o.a((Header)new BasicHeader("uuid", UUID.randomUUID().toString()));
        final List b = this.g.c().b();
        if (b != null && !b.isEmpty()) {
            final Iterator iterator = b.iterator();
            while (iterator.hasNext()) {
                o.a((Header)iterator.next());
            }
        }
        final StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(o.toString());
        try {
            final u u = (u)this.g.b().a((t)o).get();
            if (u != null) {
                return u.b();
            }
            throw new RpcException(Integer.valueOf(9), "response is null");
        }
        catch (final CancellationException ex) {
            throw new RpcException(Integer.valueOf(13), "", (Throwable)ex);
        }
        catch (final ExecutionException ex2) {
            final Throwable cause = ex2.getCause();
            if (cause != null && cause instanceof HttpException) {
                final HttpException ex3 = (HttpException)cause;
                int code = ex3.getCode();
                switch (code) {
                    case 9: {
                        code = 16;
                        break;
                    }
                    case 8: {
                        code = 15;
                        break;
                    }
                    case 7: {
                        code = 8;
                        break;
                    }
                    case 6: {
                        code = 7;
                        break;
                    }
                    case 5: {
                        code = 6;
                        break;
                    }
                    case 4: {
                        code = 5;
                        break;
                    }
                    case 3: {
                        code = 4;
                        break;
                    }
                    case 2: {
                        code = 3;
                        break;
                    }
                    case 1: {
                        code = 2;
                        break;
                    }
                }
                throw new RpcException(Integer.valueOf(code), ex3.getMsg());
            }
            throw new RpcException(Integer.valueOf(9), "", (Throwable)ex2);
        }
        catch (final InterruptedException ex4) {
            throw new RpcException(Integer.valueOf(13), "", (Throwable)ex4);
        }
    }
}
