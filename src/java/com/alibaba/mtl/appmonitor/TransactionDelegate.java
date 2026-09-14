package com.alibaba.mtl.appmonitor;

import com.alibaba.mtl.appmonitor.b.b;
import com.alibaba.mtl.appmonitor.d.j;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.a.e;

public class TransactionDelegate
{
    private static void a(final Transaction transaction) {
        if (transaction == null) {
            return;
        }
        if (transaction.b != null) {
            e.a().a(transaction.r, transaction.a, transaction.o, transaction.p, DimensionValueSet.create().addValues(transaction.b));
        }
    }
    
    public static void begin(final Transaction transaction, final String s) {
        try {
            if (!AppMonitorDelegate.i) {
                return;
            }
            if (transaction == null) {
                return;
            }
            i.a("TransactionDelegate", new Object[] { "statEvent begin. module: ", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", s });
            if (f.d.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.d, transaction.o, transaction.p))) {
                e.a().a(transaction.r, transaction.a, transaction.o, transaction.p, s);
                a(transaction);
            }
            else {
                i.a("TransactionDelegate", new Object[] { "log discard", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", s });
            }
        }
        finally {
            final Throwable t;
            b.a(t);
        }
    }
    
    public static void end(final Transaction transaction, final String s) {
        try {
            if (!AppMonitorDelegate.i) {
                return;
            }
            if (transaction == null) {
                return;
            }
            i.a("TransactionDelegate", new Object[] { "statEvent end. module: ", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", s });
            if (f.d.isOpen() && (AppMonitorDelegate.IS_DEBUG || j.a(f.d, transaction.o, transaction.p))) {
                a(transaction);
                e.a().a(transaction.r, s, false, transaction.e);
            }
            else {
                i.a("TransactionDelegate", new Object[] { "log discard", transaction.o, " monitorPoint: ", transaction.p, " measureName: ", s });
            }
        }
        finally {
            final Throwable t;
            b.a(t);
        }
    }
}
