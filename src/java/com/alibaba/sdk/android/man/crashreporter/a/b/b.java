package com.alibaba.sdk.android.man.crashreporter.a.b;

import java.util.Iterator;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.b.a;
import java.util.Map$Entry;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class b
{
    private static Lock a;
    
    static {
        b.a = (Lock)new ReentrantLock();
    }
    
    public String a(final Map<Thread, StackTraceElement[]> map) {
        b.a.lock();
        Label_0496: {
            if (map != null) {
                try {
                    try {
                        final StringBuffer sb = new StringBuffer();
                        final ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
                        int enableMaxThreadStackTraceNumber = 15;
                        int enableMaxThreadNumber;
                        if (configure != null) {
                            enableMaxThreadNumber = configure.enableMaxThreadNumber;
                            enableMaxThreadStackTraceNumber = configure.enableMaxThreadStackTraceNumber;
                            if (enableMaxThreadNumber == 0) {
                                b.a.unlock();
                                return "";
                            }
                        }
                        else {
                            enableMaxThreadNumber = 15;
                        }
                        final Iterator iterator = map.entrySet().iterator();
                        int n = 0;
                        while (iterator.hasNext()) {
                            final Map$Entry map$Entry = (Map$Entry)iterator.next();
                            if (map$Entry != null) {
                                final Thread thread = (Thread)map$Entry.getKey();
                                if (thread != null) {
                                    final String name = thread.getName();
                                    if (name != null && name.equals((Object)"ANR-WatchDog")) {
                                        continue;
                                    }
                                    final int priority = thread.getPriority();
                                    final long id = thread.getId();
                                    final Thread$State state = thread.getState();
                                    String name2;
                                    if (state != null) {
                                        name2 = state.name();
                                    }
                                    else {
                                        name2 = "";
                                    }
                                    final ThreadGroup threadGroup = thread.getThreadGroup();
                                    String name3;
                                    if (threadGroup != null) {
                                        if ((name3 = threadGroup.getName()).equals((Object)"system")) {
                                            continue;
                                        }
                                    }
                                    else {
                                        name3 = "";
                                    }
                                    final String name4 = thread.getClass().getName();
                                    final ClassLoader contextClassLoader = thread.getContextClassLoader();
                                    String string;
                                    if (contextClassLoader != null) {
                                        string = contextClassLoader.toString();
                                    }
                                    else {
                                        string = "";
                                    }
                                    sb.append(String.format("name:%s prio:%d tid:%d \n|state:%s \n|group:%s \n|class:%s \n|classLoader:%s\n", new Object[] { name, priority, id, name2, name3, name4, string }));
                                    final StackTraceElement[] array = (StackTraceElement[])map$Entry.getValue();
                                    if (array != null && enableMaxThreadStackTraceNumber != 0) {
                                        sb.append("|stackTrace:\n ");
                                        final int length = array.length;
                                        int i = 0;
                                        int n2 = 0;
                                        while (i < length) {
                                            final StackTraceElement stackTraceElement = array[i];
                                            if (stackTraceElement != null) {
                                                sb.append(String.format("%s\n", new Object[] { stackTraceElement.toString() }));
                                            }
                                            if (++n2 >= enableMaxThreadStackTraceNumber) {
                                                break;
                                            }
                                            ++i;
                                        }
                                    }
                                    sb.append("\n");
                                }
                            }
                            if (++n >= enableMaxThreadNumber) {
                                break;
                            }
                        }
                        final String string2 = sb.toString();
                        b.a.unlock();
                        return string2;
                    }
                    finally {}
                }
                catch (final Exception ex) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("serialization failed.", (Throwable)ex);
                    break Label_0496;
                }
                b.a.unlock();
            }
        }
        b.a.unlock();
        return "";
    }
}
