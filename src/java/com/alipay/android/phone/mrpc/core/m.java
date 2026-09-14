package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public final class m extends FutureTask<u>
{
    public final q a;
    public final l b;
    
    public m(final l b, final Callable callable, final q a) {
        this.b = b;
        this.a = a;
        super(callable);
    }
    
    public final void done() {
        final o a = this.a.a();
        if (((t)a).f() == null) {
            super.done();
            return;
        }
        try {
            this.get();
            if (this.isCancelled() || ((t)a).h()) {
                ((t)a).g();
                if (!this.isCancelled() || !this.isDone()) {
                    this.cancel(false);
                }
            }
        }
        catch (final CancellationException ex) {
            ((t)a).g();
        }
        catch (final ExecutionException ex2) {
            if (ex2.getCause() != null && ex2.getCause() instanceof HttpException) {
                final HttpException ex3 = (HttpException)ex2.getCause();
                ex3.getCode();
                ex3.getMsg();
                return;
            }
            new StringBuilder().append((Object)ex2);
        }
        catch (final InterruptedException ex4) {
            new StringBuilder().append((Object)ex4);
        }
        finally {
            final Throwable t;
            throw new RuntimeException("An error occured while executing http request", t);
        }
    }
}
