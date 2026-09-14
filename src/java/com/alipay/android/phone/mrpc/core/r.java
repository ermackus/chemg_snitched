package com.alipay.android.phone.mrpc.core;

import java.io.IOException;
import java.io.Closeable;

public final class r
{
    public static void a(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (final IOException ex) {}
    }
}
