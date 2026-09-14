package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

public final class n implements ThreadFactory
{
    public final AtomicInteger a;
    
    public n() {
        this.a = new AtomicInteger(1);
    }
    
    public final Thread newThread(final Runnable runnable) {
        final StringBuilder sb = new StringBuilder("com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #");
        sb.append(this.a.getAndIncrement());
        final Thread thread = new Thread(runnable, sb.toString());
        thread.setPriority(4);
        return thread;
    }
}
